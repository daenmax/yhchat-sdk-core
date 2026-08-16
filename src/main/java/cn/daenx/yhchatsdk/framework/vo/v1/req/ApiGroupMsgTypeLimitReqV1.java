package cn.daenx.yhchatsdk.framework.vo.v1.req;

import lombok.Data;

/**
 * 群内消息类型控制请求体
 *
 * @author DaenMax
 */
@Data
public class ApiGroupMsgTypeLimitReqV1 {

    /**
     * 群组ID
     */
    private String groupId;

    /**
     * 要禁用的消息类型，多个类型用逗号分隔，如：text,image,video
     * 为空表示不限制任何消息类型
     * 非必填
     * text	文本消息
     * image	图片消息
     * markdown	MARKDOWN消息
     * file	文件消息
     * post	帖子消息
     * expression	表情消息
     * html	HTML消息
     * video	视频消息
     * audio	语音消息
     * liveAudio	语音通话
     */
    private String type;

    public ApiGroupMsgTypeLimitReqV1(String groupId, String type) {
        this.groupId = groupId;
        this.type = type;
    }
}