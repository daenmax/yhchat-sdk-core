package cn.daenx.yhchatsdk.framework.utils;

import cn.daenx.yhchatsdk.framework.vo.v1.req.ApiSendMsgBatchReqV1;
import cn.daenx.yhchatsdk.framework.vo.v1.req.ApiSendMsgReqV1;
import cn.daenx.yhchatsdk.framework.vo.v1.ret.ApiSendMsgBatchRetV1;
import cn.daenx.yhchatsdk.framework.vo.v1.ret.ApiSendMsgRetV1;
import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * API工具类
 *
 * @author DaenMax
 */
@Component
public class ApiUtil {
    private static String token;
    private static String url = "https://chat-go.jwzhd.com/open-apis/v1/bot";

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
        String urlPost = url + "/send?token=" + token;
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
        String urlPost = url + "/batch_send?token=" + token;
        String content = JSONUtil.toJsonStr(apiSendMsgBatchReqV1);
        String body = HttpRequest.post(urlPost).header("Content-Type", "application/json; charset=utf-8").body(content).execute().body();
        ApiSendMsgBatchRetV1 apiSendMsgBatchRetV1 = JSONUtil.toBean(body, ApiSendMsgBatchRetV1.class);
        return apiSendMsgBatchRetV1;
    }
}
