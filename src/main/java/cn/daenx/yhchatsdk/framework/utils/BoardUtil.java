package cn.daenx.yhchatsdk.framework.utils;

import cn.daenx.yhchatsdk.framework.vo.v1.req.*;
import cn.daenx.yhchatsdk.framework.vo.v1.ret.*;
import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONUtil;

/**
 * API工具类（看板）
 *
 * @author DaenMax
 */
public class BoardUtil {


    /**
     * 设置看板
     *
     * @param apiSetBoardReqV1
     * @return
     */
    public static ApiSetBoardRetV1 setBoard(ApiSetBoardReqV1 apiSetBoardReqV1) {
        String urlPost;
        if (apiSetBoardReqV1.getRecvId() == null && apiSetBoardReqV1.getRecvType() == null) {
            urlPost = CommonUtil.getHttpUrl() + "/bot/board-all?token=" + CommonUtil.getToken();
        } else {
            urlPost = CommonUtil.getHttpUrl() + "/bot/board?token=" + CommonUtil.getToken();
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
            urlPost = CommonUtil.getHttpUrl() + "/bot/board-all-dismiss?token=" + CommonUtil.getToken();
        } else {
            urlPost = CommonUtil.getHttpUrl() + "/bot/board-dismiss?token=" + CommonUtil.getToken();
        }
        String content = JSONUtil.toJsonStr(apiDisBoardReqV1);
        String body = HttpRequest.post(urlPost).header("Content-Type", "application/json; charset=utf-8").body(content).execute().body();
        ApiDisBoardRetV1 apiDisBoardRetV1 = JSONUtil.toBean(body, ApiDisBoardRetV1.class);
        return apiDisBoardRetV1;
    }


}
