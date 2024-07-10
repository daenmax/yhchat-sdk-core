package cn.daenx.yhchatsdk.framework.utils;

import cn.daenx.yhchatsdk.framework.vo.v1.req.*;
import cn.daenx.yhchatsdk.framework.vo.v1.ret.*;
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

    /**
     * 编辑消息
     * 参数中的recvId、recvType需要和原消息保持一致，否则无法编辑
     *
     * @param apiEditMsgReqV1
     * @return
     */
    public static ApiEditMsgRetV1 editMsg(ApiEditMsgReqV1 apiEditMsgReqV1) {
        String urlPost = url + "/edit?token=" + token;
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
        String urlPost = url + "/recall?token=" + token;
        String content = JSONUtil.toJsonStr(apiRecallMsgReqV1);
        String body = HttpRequest.post(urlPost).header("Content-Type", "application/json; charset=utf-8").body(content).execute().body();
        ApiRecallMsgRetV1 apiRecallMsgRetV1 = JSONUtil.toBean(body, ApiRecallMsgRetV1.class);
        return apiRecallMsgRetV1;
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
            urlPost = url + "/board-all?token=" + token;
        } else {
            urlPost = url + "/board?token=" + token;
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
            urlPost = url + "/board-all-dismiss?token=" + token;
        } else {
            urlPost = url + "/board-dismiss?token=" + token;
        }
        String content = JSONUtil.toJsonStr(apiDisBoardReqV1);
        String body = HttpRequest.post(urlPost).header("Content-Type", "application/json; charset=utf-8").body(content).execute().body();
        ApiDisBoardRetV1 apiDisBoardRetV1 = JSONUtil.toBean(body, ApiDisBoardRetV1.class);
        return apiDisBoardRetV1;
    }

}
