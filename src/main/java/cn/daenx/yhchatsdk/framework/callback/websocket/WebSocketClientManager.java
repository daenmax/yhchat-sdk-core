package cn.daenx.yhchatsdk.framework.callback.websocket;

import cn.daenx.yhchatsdk.framework.core.GlobalExecutorSubmit;
import cn.daenx.yhchatsdk.framework.utils.CommonUtil;
import cn.daenx.yhchatsdk.framework.vo.EventMsgVo;
import cn.hutool.json.JSONUtil;
import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.WebSocket;
import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 云湖事件入口（WebSocket模式）
 * <p>
 * 接入模式为2时，额外连接云湖WebSocket服务端接收事件推送，
 * 推送过来的事件报文格式与HTTP模式完全一致，均投递到全局事件处理线程池处理
 *
 * @author DaenMax
 */
@Component
@Slf4j
public class WebSocketClientManager implements ApplicationListener<ApplicationReadyEvent> {

    /**
     * 心跳内容
     */
    private static final String HEARTBEAT_MSG = "{\"op\":\"heart\"}";

    /**
     * 心跳间隔：每3分钟
     */
    private static final long HEARTBEAT_INTERVAL_SECONDS = 180L;

    /**
     * 断线重连间隔：每1分钟
     */
    private static final long RECONNECT_INTERVAL_SECONDS = 60L;

    /**
     * 单条WebSocket消息分片长度上限（5MB），防止异常数据导致内存膨胀
     */
    private static final int MAX_MESSAGE_LENGTH = 5 * 1024 * 1024;

    /**
     * 连接超时时间
     */
    private static final long CONNECT_TIMEOUT_SECONDS = 20L;

    @Value("${yhchat.printLog:false}")
    private Boolean printLog;


    private final HttpClient httpClient = HttpClient.newBuilder()
            .connectTimeout(Duration.ofSeconds(CONNECT_TIMEOUT_SECONDS))
            .build();

    /**
     * 当前连接状态
     */
    private final AtomicBoolean connected = new AtomicBoolean(false);

    /**
     * 当前WebSocket连接
     */
    private volatile WebSocket webSocket;

    /**
     * 定时任务线程池：心跳、断线重连
     */
    private ScheduledExecutorService scheduler;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        // 接入模式，空或者1=http，2=websocket
        String mode = CommonUtil.getMode();
        if (mode != null) {
            mode = mode.trim();
        }
        if (!"2".equals(mode)) {
            log.info("【core】当前接入模式为[{}]，不启动WebSocket连接", mode);
            return;
        }
        String url = buildWebSocketUrl();
        if (url == null) {
            log.error("【core】WebSocket连接地址或token为空，无法启动WebSocket连接");
            return;
        }
        log.info("【core】当前接入模式为[2]，开始连接云湖WebSocket服务端");
        scheduler = Executors.newScheduledThreadPool(2, runnable -> {
            Thread thread = new Thread(runnable, "yhchat-ws-scheduler");
            thread.setDaemon(true);
            return thread;
        });
        // 立即建立连接
        connect(url);
        // 心跳：每3分钟发送一次
        scheduler.scheduleAtFixedRate(this::heartbeat, HEARTBEAT_INTERVAL_SECONDS, HEARTBEAT_INTERVAL_SECONDS, TimeUnit.SECONDS);
        // 断线重连：每分钟检查一次，未连接则重连，直至成功
        scheduler.scheduleAtFixedRate(this::reconnect, RECONNECT_INTERVAL_SECONDS, RECONNECT_INTERVAL_SECONDS, TimeUnit.SECONDS);
    }

    /**
     * 建立WebSocket连接
     *
     * @param url 连接地址
     */
    private void connect(String url) {
        if (connected.get()) {
            return;
        }
        // 关闭可能残留的旧连接，避免连接泄漏
        WebSocket oldWs = this.webSocket;
        if (oldWs != null) {
            try {
                oldWs.abort();
            } catch (Exception ignore) {
                // 忽略关闭异常
            }
        }
        CountDownLatch openLatch = new CountDownLatch(1);
        try {
            CompletableFuture<WebSocket> future = httpClient.newWebSocketBuilder()
                    .connectTimeout(Duration.ofSeconds(CONNECT_TIMEOUT_SECONDS))
                    .buildAsync(URI.create(url), new WsListener(openLatch));
            WebSocket ws = future.get(CONNECT_TIMEOUT_SECONDS, TimeUnit.SECONDS);
            // 等待onOpen回调执行完毕，确保连接状态已更新
            openLatch.await(CONNECT_TIMEOUT_SECONDS, TimeUnit.SECONDS);
            // 记录当前连接，供心跳与关闭时使用
            this.webSocket = ws;
            if (connected.get()) {
                log.info("【core】WebSocket连接成功");
            }
        } catch (Exception e) {
            String errMsg = e.getMessage();
            log.error("【core】WebSocket连接失败：{}，将于{}秒后重试", errMsg == null ? e.getClass().getSimpleName() : sanitize(errMsg), RECONNECT_INTERVAL_SECONDS);
        }
    }

    /**
     * 断线重连：每分钟执行一次，直至连接成功
     */
    private void reconnect() {
        if (connected.get()) {
            return;
        }
        String url = buildWebSocketUrl();
        if (url == null) {
            return;
        }
        connect(url);
    }

    /**
     * 发送心跳
     */
    private void heartbeat() {
        if (!connected.get()) {
            return;
        }
        WebSocket ws = webSocket;
        if (ws == null) {
            connected.set(false);
            return;
        }
        try {
            ws.sendText(HEARTBEAT_MSG, true).whenComplete((w, throwable) -> {
                if (throwable != null) {
                    connected.set(false);
                    log.error("【core】WebSocket心跳发送失败：{}，等待断线重连", throwable.getMessage());
                } else {
                    log.info("【core】WebSocket心跳发送成功");
                }
            });
        } catch (Exception e) {
            connected.set(false);
            log.error("【core】WebSocket心跳发送失败：{}，等待断线重连", e.getMessage());
        }
    }

    /**
     * 处理接收到的WebSocket消息
     *
     * @param message 消息内容
     */
    private void handleMessage(String message) {
        try {
            EventMsgVo eventMsgVo = JSONUtil.toBean(message, EventMsgVo.class);
            if (eventMsgVo.getHeader() == null || eventMsgVo.getHeader().getEventType() == null) {
                // 非事件消息（如服务端心跳回执等），忽略，仅在开启printLog时打印
                if (printLog) {
                    log.info("【core】接收到来自WebSocket的非事件消息：{}", message);
                }
                return;
            }
            if (printLog) {
                log.info("【core】接收到来自WebSocket的消息：{}，原始消息为{}", eventMsgVo.getHeader().getEventType(), message);
            }
            // 与HTTP模式一致，投递到全局事件处理线程池
            GlobalExecutorSubmit.submit(eventMsgVo);
        } catch (Exception e) {
            log.error("【core】WebSocket消息解析失败：{}", e.getMessage());
        }
    }

    /**
     * 构建WebSocket连接地址
     * <p>
     * 从配置中读取WebSocket地址与token，拼接为：wss://xxx/subscribe?token=xxxxx
     *
     * @return 连接地址，地址或token为空时返回null
     */
    private String buildWebSocketUrl() {
        String url = CommonUtil.getWebSocketUrl();
        String token = CommonUtil.getToken();
        if (url == null || token == null) {
            return null;
        }
        url = url.trim();
        token = token.trim();
        if (url.isEmpty() || token.isEmpty()) {
            return null;
        }
        // 地址中已携带token参数时，直接使用
        if (url.contains("token=")) {
            return url;
        }
        return url + (url.contains("?") ? "&" : "?") + "token=" + token;
    }

    /**
     * 对日志中的token进行脱敏处理，避免敏感信息泄漏到日志
     *
     * @param text 原始文本
     * @return 脱敏后的文本
     */
    private String sanitize(String text) {
        if (text == null) {
            return null;
        }
        return text.replaceAll("token=[^&\\s\"]+", "token=***");
    }

    @PreDestroy
    public void destroy() {
        if (scheduler != null) {
            scheduler.shutdownNow();
        }
        WebSocket ws = webSocket;
        if (ws != null) {
            try {
                ws.abort();
            } catch (Exception ignore) {
                // 忽略关闭异常
            }
        }
        log.info("【core】WebSocket连接已关闭");
    }

    /**
     * WebSocket事件监听器
     */
    private class WsListener implements WebSocket.Listener {
        private final CountDownLatch openLatch;
        private final StringBuilder messageBuffer = new StringBuilder();

        public WsListener(CountDownLatch openLatch) {
            this.openLatch = openLatch;
        }

        @Override
        public void onOpen(WebSocket webSocket) {
            connected.set(true);
            // 记录当前连接，供心跳与关闭时使用
            WebSocketClientManager.this.webSocket = webSocket;
            openLatch.countDown();
            // 请求继续接收消息
            webSocket.request(1);
        }

        @Override
        public CompletionStage<?> onText(WebSocket webSocket, CharSequence data, boolean last) {
            messageBuffer.append(data);
            if (last) {
                String message = messageBuffer.toString();
                messageBuffer.setLength(0);
                handleMessage(message);
            } else if (messageBuffer.length() > MAX_MESSAGE_LENGTH) {
                // 防止异常数据导致内存膨胀，丢弃超长分片消息
                messageBuffer.setLength(0);
                log.error("【core】WebSocket消息分片长度超过上限，已丢弃该消息");
            }
            // 请求继续接收消息
            webSocket.request(1);
            return null;
        }

        @Override
        public CompletionStage<?> onClose(WebSocket webSocket, int statusCode, String reason) {
            connected.set(false);
            log.warn("【core】WebSocket连接已关闭：statusCode={}，reason={}，将于{}秒后重连", statusCode, reason, RECONNECT_INTERVAL_SECONDS);
            webSocket.request(1);
            return null;
        }

        @Override
        public void onError(WebSocket webSocket, Throwable error) {
            connected.set(false);
            openLatch.countDown();
            log.error("【core】WebSocket连接异常：{}，将于{}秒后重连", sanitize(String.valueOf(error.getMessage())), RECONNECT_INTERVAL_SECONDS);
            // 终止异常连接，等待定时任务重连
            try {
                webSocket.abort();
            } catch (Exception ignore) {
                // 忽略关闭异常
            }
        }
    }
}
