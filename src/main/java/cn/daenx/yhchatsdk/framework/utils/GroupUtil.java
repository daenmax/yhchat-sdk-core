package cn.daenx.yhchatsdk.framework.utils;

import cn.daenx.yhchatsdk.framework.vo.v1.req.*;
import cn.daenx.yhchatsdk.framework.vo.v1.ret.*;
import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONUtil;

/**
 * API工具类（群组）
 *
 * @author DaenMax
 */
public class GroupUtil {

    /**
     * 创建标签
     * 机器人需要在该群聊中，且拥有“允许控制标签组”权限（allowGroupTagManage = 1）
     *
     * @param apiGroupTagCreateReqV1
     * @return
     */
    public static ApiGroupTagCreateRetV1 createTag(ApiGroupTagCreateReqV1 apiGroupTagCreateReqV1) {
        String urlPost = CommonUtil.getHttpUrl() + "/group/tag/create?token=" + CommonUtil.getToken();
        String content = JSONUtil.toJsonStr(apiGroupTagCreateReqV1);
        String body = HttpRequest.post(urlPost).header("Content-Type", "application/json; charset=utf-8").body(content).execute().body();
        System.out.println(body);
        ApiGroupTagCreateRetV1 apiGroupTagCreateRetV1 = JSONUtil.toBean(body, ApiGroupTagCreateRetV1.class);
        return apiGroupTagCreateRetV1;
    }

    /**
     * 修改标签
     * 机器人需要在该群聊中，且拥有“允许控制标签组”权限（allowGroupTagManage = 1）
     *
     * @param apiGroupTagEditReqV1
     * @return
     */
    public static ApiGroupTagEditRetV1 editTag(ApiGroupTagEditReqV1 apiGroupTagEditReqV1) {
        String urlPost = CommonUtil.getHttpUrl() + "/group/tag/edit?token=" + CommonUtil.getToken();
        String content = JSONUtil.toJsonStr(apiGroupTagEditReqV1);
        String body = HttpRequest.post(urlPost).header("Content-Type", "application/json; charset=utf-8").body(content).execute().body();
        ApiGroupTagEditRetV1 apiGroupTagEditRetV1 = JSONUtil.toBean(body, ApiGroupTagEditRetV1.class);
        return apiGroupTagEditRetV1;
    }

    /**
     * 删除标签
     * 机器人需要在该群聊中，且拥有“允许控制标签组”权限（allowGroupTagManage = 1）
     *
     * @param apiGroupTagDeleteReqV1
     * @return
     */
    public static ApiGroupTagDeleteRetV1 deleteTag(ApiGroupTagDeleteReqV1 apiGroupTagDeleteReqV1) {
        String urlPost = CommonUtil.getHttpUrl() + "/group/tag/delete?token=" + CommonUtil.getToken();
        String content = JSONUtil.toJsonStr(apiGroupTagDeleteReqV1);
        String body = HttpRequest.post(urlPost).header("Content-Type", "application/json; charset=utf-8").body(content).execute().body();
        ApiGroupTagDeleteRetV1 apiGroupTagDeleteRetV1 = JSONUtil.toBean(body, ApiGroupTagDeleteRetV1.class);
        return apiGroupTagDeleteRetV1;
    }

    /**
     * 获取群标签列表
     * 机器人需要在该群聊中
     *
     * @param apiGroupTagListReqV1
     * @return
     */
    public static ApiGroupTagListRetV1 tagList(ApiGroupTagListReqV1 apiGroupTagListReqV1) {
        String urlPost = CommonUtil.getHttpUrl() + "/group/tag/list?token=" + CommonUtil.getToken();
        String content = JSONUtil.toJsonStr(apiGroupTagListReqV1);
        String body = HttpRequest.post(urlPost).header("Content-Type", "application/json; charset=utf-8").body(content).execute().body();
        ApiGroupTagListRetV1 apiGroupTagListRetV1 = JSONUtil.toBean(body, ApiGroupTagListRetV1.class);
        return apiGroupTagListRetV1;
    }

    /**
     * 给用户添加标签
     * 机器人需要在该群聊中，且拥有“允许控制标签组”权限（allowGroupTagManage = 1）
     *
     * @param apiGroupTagUserRelateReqV1
     * @return
     */
    public static ApiGroupTagUserRelateRetV1 addUserTag(ApiGroupTagUserRelateReqV1 apiGroupTagUserRelateReqV1) {
        String urlPost = CommonUtil.getHttpUrl() + "/group/tag/user-relate?token=" + CommonUtil.getToken();
        String content = JSONUtil.toJsonStr(apiGroupTagUserRelateReqV1);
        String body = HttpRequest.post(urlPost).header("Content-Type", "application/json; charset=utf-8").body(content).execute().body();
        ApiGroupTagUserRelateRetV1 apiGroupTagUserRelateRetV1 = JSONUtil.toBean(body, ApiGroupTagUserRelateRetV1.class);
        return apiGroupTagUserRelateRetV1;
    }

    /**
     * 给用户移除标签
     * 机器人需要在该群聊中，且拥有“允许控制标签组”权限（allowGroupTagManage = 1）
     *
     * @param apiGroupTagUserRelateCancelReqV1
     * @return
     */
    public static ApiGroupTagUserRelateCancelRetV1 removeUserTag(ApiGroupTagUserRelateCancelReqV1 apiGroupTagUserRelateCancelReqV1) {
        String urlPost = CommonUtil.getHttpUrl() + "/group/tag/user-relate-cancel?token=" + CommonUtil.getToken();
        String content = JSONUtil.toJsonStr(apiGroupTagUserRelateCancelReqV1);
        String body = HttpRequest.post(urlPost).header("Content-Type", "application/json; charset=utf-8").body(content).execute().body();
        ApiGroupTagUserRelateCancelRetV1 apiGroupTagUserRelateCancelRetV1 = JSONUtil.toBean(body, ApiGroupTagUserRelateCancelRetV1.class);
        return apiGroupTagUserRelateCancelRetV1;
    }

    /**
     * 移除群成员
     * 机器人需要在该群聊中，且拥有“允许移除群成员”权限（allowRemoveMember = 1）
     *
     * @param apiGroupRemoveMemberReqV1
     * @return
     */
    public static ApiGroupRemoveMemberRetV1 removeMember(ApiGroupRemoveMemberReqV1 apiGroupRemoveMemberReqV1) {
        String urlPost = CommonUtil.getHttpUrl() + "/group/remove-member?token=" + CommonUtil.getToken();
        String content = JSONUtil.toJsonStr(apiGroupRemoveMemberReqV1);
        String body = HttpRequest.post(urlPost).header("Content-Type", "application/json; charset=utf-8").body(content).execute().body();
        ApiGroupRemoveMemberRetV1 apiGroupRemoveMemberRetV1 = JSONUtil.toBean(body, ApiGroupRemoveMemberRetV1.class);
        return apiGroupRemoveMemberRetV1;
    }

    /**
     * 群成员禁言
     * 机器人需要在该群聊中，且拥有“允许禁言用户”权限（allowGagMember = 1）
     *
     * @param apiGroupGagMemberReqV1
     * @return
     */
    public static ApiGroupGagMemberRetV1 gagMember(ApiGroupGagMemberReqV1 apiGroupGagMemberReqV1) {
        String urlPost = CommonUtil.getHttpUrl() + "/group/gag-member?token=" + CommonUtil.getToken();
        String content = JSONUtil.toJsonStr(apiGroupGagMemberReqV1);
        String body = HttpRequest.post(urlPost).header("Content-Type", "application/json; charset=utf-8").body(content).execute().body();
        ApiGroupGagMemberRetV1 apiGroupGagMemberRetV1 = JSONUtil.toBean(body, ApiGroupGagMemberRetV1.class);
        return apiGroupGagMemberRetV1;
    }

    /**
     * 群内消息类型控制
     * type为空表示不限制任何消息类型，多个类型用逗号分隔，如：text,image,video
     * 机器人需要在该群聊中，且拥有“允许修改群信息”权限（allowEditGroupInfo = 1）
     *
     * @param apiGroupMsgTypeLimitReqV1
     * @return
     */
    public static ApiGroupMsgTypeLimitRetV1 msgTypeLimit(ApiGroupMsgTypeLimitReqV1 apiGroupMsgTypeLimitReqV1) {
        String urlPost = CommonUtil.getHttpUrl() + "/group/msg-type-limit?token=" + CommonUtil.getToken();
        String content = JSONUtil.toJsonStr(apiGroupMsgTypeLimitReqV1);
        String body = HttpRequest.post(urlPost).header("Content-Type", "application/json; charset=utf-8").body(content).execute().body();
        ApiGroupMsgTypeLimitRetV1 apiGroupMsgTypeLimitRetV1 = JSONUtil.toBean(body, ApiGroupMsgTypeLimitRetV1.class);
        return apiGroupMsgTypeLimitRetV1;
    }

}