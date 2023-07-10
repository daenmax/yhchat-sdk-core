package cn.daenx.yhchatsdk.framework.vo.v1.req;

import cn.daenx.yhchatsdk.common.constant.ContentTypeConstant;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 设置看板请求体
 *
 * @author DaenMax
 */
@Data
public class ApiSetBoardReqV1 {

    /**
     * 对象ID
     * 用户: userId
     * 群: groupId
     */
    private String recvId;
    /**
     * 对象类型
     * 用户: user
     * 群: group
     */
    private String recvType;
    /**
     * 消息类型，取值如下
     * text\markdown\html
     */
    private String contentType;
    private String content;

    //快捷构造方法


    /**
     * 文本消息
     * 如果要设置全局看板，recvType和recvId请传null
     *
     * @param recvType RecvTypeConstant.
     * @param recvId   当为USER时，填用户ID，当为GROUP时，填群ID
     * @param content  消息内容
     * @return
     */
    public ApiSetBoardReqV1 Text(String recvType, String recvId, String content) {
        this.recvId = recvId;
        this.recvType = recvType;
        this.contentType = ContentTypeConstant.TEXT;
        this.content = content;
        return this;
    }


    /**
     * Markdown消息
     * 如果要设置全局看板，recvType和recvId请传null
     *
     * @param recvType RecvTypeConstant.
     * @param recvId   当为USER时，填用户ID，当为GROUP时，填群ID
     * @param content  md消息内容
     * @return
     */
    public ApiSetBoardReqV1 Markdown(String recvType, String recvId, String content) {
        this.recvId = recvId;
        this.recvType = recvType;
        this.contentType = ContentTypeConstant.MARKDOWN;
        this.content = content;
        return this;
    }


    /**
     * Html消息
     * 如果要设置全局看板，recvType和recvId请传null
     *
     * @param recvType RecvTypeConstant.
     * @param recvId   当为USER时，填用户ID，当为GROUP时，填群ID
     * @param content  html
     * @return
     */
    public ApiSetBoardReqV1 Html(String recvType, String recvId, String content) {
        this.recvId = recvId;
        this.recvType = recvType;
        this.contentType = ContentTypeConstant.HTML;
        this.content = content;
        return this;
    }

}
