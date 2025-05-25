package cn.daenx.yhchatsdk.framework.vo.v1.req;

import lombok.Data;


/**
 * 消息列表请求体
 *
 * @author DaenMax
 */
@Data
public class ApiMessagesReqV1 {

    /**
     * 消息对象ID
     * 用户: userId
     * 群: groupId
     */
    private String chatId;

    /**
     * 对象类型
     * 用户: user
     * 群: group
     */
    private String chatType;

    /**
     * 消息ID，不填时可以配合before参数返回最近的N条消息
     */
    private String msgId;

    /**
     * 指定消息ID前N条，默认0条
     */
    private Integer before;

    /**
     * 指定消息ID后N条，默认0条
     */
    private Integer after;


    //快捷构造方法
    public ApiMessagesReqV1(String chatType, String chatId, String msgId, Integer before, Integer after) {
        this.chatType = chatType;
        this.chatId = chatId;
        this.msgId = msgId;
        this.before = before;
        this.after = after;
    }

}
