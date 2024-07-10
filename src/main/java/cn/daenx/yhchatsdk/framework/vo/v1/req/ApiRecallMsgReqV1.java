package cn.daenx.yhchatsdk.framework.vo.v1.req;

import lombok.Data;


/**
 * 撤回消息请求体
 *
 * @author DaenMax
 */
@Data
public class ApiRecallMsgReqV1 {
    /**
     * 要撤回的消息ID
     */
    private String msgId;

    /**
     * 对象类型
     * 用户: user
     * 群: group
     */
    private String chatType;

    /**
     * 消息对象ID
     * 用户: userId
     * 群: groupId
     */
    private String chatId;


    //快捷构造方法


    public ApiRecallMsgReqV1() {
    }

    public ApiRecallMsgReqV1(String msgId, String chatType, String chatId) {
        this.msgId = msgId;
        this.chatType = chatType;
        this.chatId = chatId;
    }
}
