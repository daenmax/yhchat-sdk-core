package cn.daenx.yhchatsdk.framework.utils;

import cn.daenx.yhchatsdk.framework.vo.v1.req.*;
import cn.daenx.yhchatsdk.framework.vo.v1.ret.*;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.PipedInputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;

/**
 * API工具类
 *
 * @author DaenMax
 */
@Component
public class ApiUtil {
    private static String token;
    private static String url = "https://chat-go.jwzhd.com/open-apis/v1";

    @Value("${yhchat.token}")
    public void setToken(String token) {
        ApiUtil.token = token;
    }

    public static String getToken() {
        return token;
    }

    /**
     * 发送消息
     *
     * @param apiSendMsgReqV1
     * @return
     */
    public static ApiSendMsgRetV1 sendMsg(ApiSendMsgReqV1 apiSendMsgReqV1) {
        String urlPost = url + "/bot/send?token=" + token;
        String content = JSONUtil.toJsonStr(apiSendMsgReqV1);
        String body = HttpRequest.post(urlPost).header("Content-Type", "application/json; charset=utf-8").body(content).execute().body();
        ApiSendMsgRetV1 apiSendMsgRetV1 = JSONUtil.toBean(body, ApiSendMsgRetV1.class);
        return apiSendMsgRetV1;
    }

    /**
     * 批量发送消息
     *
     * @param apiSendMsgBatchReqV1
     * @return
     */
    public static ApiSendMsgBatchRetV1 sendMsgBatch(ApiSendMsgBatchReqV1 apiSendMsgBatchReqV1) {
        String urlPost = url + "/bot/batch_send?token=" + token;
        String content = JSONUtil.toJsonStr(apiSendMsgBatchReqV1);
        String body = HttpRequest.post(urlPost).header("Content-Type", "application/json; charset=utf-8").body(content).execute().body();
        ApiSendMsgBatchRetV1 apiSendMsgBatchRetV1 = JSONUtil.toBean(body, ApiSendMsgBatchRetV1.class);
        return apiSendMsgBatchRetV1;
    }


    /**
     * 流式发送消息
     *
     * @param apiSendMsgStreamReqV1
     * @return
     * @throws Exception
     */
    public static ApiSendMsgStreamRetV1 sendMsgStream(ApiSendMsgStreamReqV1 apiSendMsgStreamReqV1) throws Exception {
        String uri = url + "/bot/send-stream?token=" + token +
                "&recvId=" + apiSendMsgStreamReqV1.getRecvId() +
                "&recvType=" + apiSendMsgStreamReqV1.getRecvType() +
                "&contentType=" + apiSendMsgStreamReqV1.getContentType();
        PipedInputStream pipedInputStream = apiSendMsgStreamReqV1.getPipedInputStream();
        HttpClient client = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .connectTimeout(Duration.ofSeconds(60))
                .build();
        java.net.http.HttpRequest.BodyPublisher bodyPublisher = java.net.http.HttpRequest.BodyPublishers.ofInputStream(() -> pipedInputStream);
        java.net.http.HttpRequest request = java.net.http.HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .header("Content-Type", "text/plain; charset=UTF-8")
                .header("Transfer-Encoding", "chunked")
                .POST(bodyPublisher)
                .build();
        java.net.http.HttpResponse<String> response = client.send(request, java.net.http.HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8));
        String responseBody = response.body();
        return JSONUtil.toBean(responseBody, ApiSendMsgStreamRetV1.class);
    }

    /**
     * 编辑消息
     * 参数中的recvId、recvType需要和原消息保持一致，否则无法编辑
     *
     * @param apiEditMsgReqV1
     * @return
     */
    public static ApiEditMsgRetV1 editMsg(ApiEditMsgReqV1 apiEditMsgReqV1) {
        String urlPost = url + "/bot/edit?token=" + token;
        String content = JSONUtil.toJsonStr(apiEditMsgReqV1);
        String body = HttpRequest.post(urlPost).header("Content-Type", "application/json; charset=utf-8").body(content).execute().body();
        ApiEditMsgRetV1 apiEditMsgRetV1 = JSONUtil.toBean(body, ApiEditMsgRetV1.class);
        return apiEditMsgRetV1;
    }

    /**
     * 撤回消息
     * 参数中的chatId、chatType需要和原消息保持一致，否则无法撤回
     *
     * @param apiRecallMsgReqV1
     * @return
     */
    public static ApiRecallMsgRetV1 recallMsg(ApiRecallMsgReqV1 apiRecallMsgReqV1) {
        String urlPost = url + "/bot/recall?token=" + token;
        String content = JSONUtil.toJsonStr(apiRecallMsgReqV1);
        String body = HttpRequest.post(urlPost).header("Content-Type", "application/json; charset=utf-8").body(content).execute().body();
        ApiRecallMsgRetV1 apiRecallMsgRetV1 = JSONUtil.toBean(body, ApiRecallMsgRetV1.class);
        return apiRecallMsgRetV1;
    }

    /**
     * 消息列表
     * 参数例子请查看官网文档https://www.yhchat.com/document/400-450
     *
     * @param apiMessagesReqV1
     * @return
     */
    public static ApiMessagesRetV1 messages(ApiMessagesReqV1 apiMessagesReqV1) {
        String urlPost = url + "/bot/messages?token=" + token +
                "&chat-id=" + apiMessagesReqV1.getChatId() +
                "&chat-type=" + apiMessagesReqV1.getChatType();
        if (ObjectUtil.isNotEmpty(apiMessagesReqV1.getMsgId())) {
            urlPost += "&message-id=" + apiMessagesReqV1.getMsgId();
        }
        if (ObjectUtil.isNotEmpty(apiMessagesReqV1.getBefore())) {
            urlPost += "&before=" + apiMessagesReqV1.getBefore();
        }
        if (ObjectUtil.isNotEmpty(apiMessagesReqV1.getAfter())) {
            urlPost += "&after=" + apiMessagesReqV1.getAfter();
        }
        String body = HttpRequest.get(urlPost).header("Content-Type", "application/json; charset=utf-8").execute().body();
        ApiMessagesRetV1 apiMessagesRetV1 = JSONUtil.toBean(body, ApiMessagesRetV1.class);
        return apiMessagesRetV1;
    }

    /**
     * 设置看板
     *
     * @param apiSetBoardReqV1
     * @return
     */
    public static ApiSetBoardRetV1 setBoard(ApiSetBoardReqV1 apiSetBoardReqV1) {
        String urlPost;
        if (apiSetBoardReqV1.getRecvId() == null && apiSetBoardReqV1.getRecvType() == null) {
            urlPost = url + "/bot/board-all?token=" + token;
        } else {
            urlPost = url + "/bot/board?token=" + token;
        }
        String content = JSONUtil.toJsonStr(apiSetBoardReqV1);
        String body = HttpRequest.post(urlPost).header("Content-Type", "application/json; charset=utf-8").body(content).execute().body();
        ApiSetBoardRetV1 apiSetBoardRetV1 = JSONUtil.toBean(body, ApiSetBoardRetV1.class);
        return apiSetBoardRetV1;
    }

    /**
     * 取消设置看板
     *
     * @param apiDisBoardReqV1
     * @return
     */
    public static ApiDisBoardRetV1 disBoard(ApiDisBoardReqV1 apiDisBoardReqV1) {
        String urlPost;
        if (apiDisBoardReqV1.getRecvId() == null && apiDisBoardReqV1.getRecvType() == null) {
            urlPost = url + "/bot/board-all-dismiss?token=" + token;
        } else {
            urlPost = url + "/bot/board-dismiss?token=" + token;
        }
        String content = JSONUtil.toJsonStr(apiDisBoardReqV1);
        String body = HttpRequest.post(urlPost).header("Content-Type", "application/json; charset=utf-8").body(content).execute().body();
        ApiDisBoardRetV1 apiDisBoardRetV1 = JSONUtil.toBean(body, ApiDisBoardRetV1.class);
        return apiDisBoardRetV1;
    }

    /**
     * 上传图片/视频/文件
     *
     * @param apiUploadReqV1
     * @return
     */
    public static ApiUploadRetV1 upload(ApiUploadReqV1 apiUploadReqV1) {
        String urlPost = url + "/" + apiUploadReqV1.getType() + "/upload?token=" + token;
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put(apiUploadReqV1.getType(), FileUtil.file(apiUploadReqV1.getFilePath()));
        String body = HttpUtil.post(urlPost, paramMap);
        ApiUploadRetV1 apiUploadRetV1 = JSONUtil.toBean(body, ApiUploadRetV1.class);
        return apiUploadRetV1;
    }

}
