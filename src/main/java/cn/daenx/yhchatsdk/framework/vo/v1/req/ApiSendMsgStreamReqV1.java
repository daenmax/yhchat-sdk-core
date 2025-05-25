package cn.daenx.yhchatsdk.framework.vo.v1.req;

import cn.daenx.yhchatsdk.common.constant.ContentTypeConstant;
import lombok.Data;

import java.io.PipedInputStream;

/**
 * 流式发送消息请求体
 *
 * @author DaenMax
 */
@Data
public class ApiSendMsgStreamReqV1 {
    /**
     * 接收消息对象ID
     * 用户: userId
     * 群: groupId
     */
    private String recvId;
    /**
     * 接收对象类型
     * 用户: user
     * 群: group
     */
    private String recvType;
    /**
     * 消息类型，取值如下
     * text\markdown
     */
    private String contentType;
    private PipedInputStream pipedInputStream;


    //快捷构造方法

    /**
     * 文本消息
     *
     * @param recvType         RecvTypeConstant.
     * @param recvId           当为USER时，填用户ID，当为GROUP时，填群ID
     * @param pipedInputStream Piped Stream
     * @return
     */
    public ApiSendMsgStreamReqV1 Text(String recvType, String recvId, PipedInputStream pipedInputStream) {
        ApiSendMsgStreamReqV1 reqV1 = new ApiSendMsgStreamReqV1();
        reqV1.recvId = recvId;
        reqV1.recvType = recvType;
        reqV1.contentType = ContentTypeConstant.TEXT;
        reqV1.pipedInputStream = pipedInputStream;
        return reqV1;
    }

    /**
     * Markdown消息
     *
     * @param recvType         RecvTypeConstant.
     * @param recvId           当为USER时，填用户ID，当为GROUP时，填群ID
     * @param pipedInputStream Piped Stream
     * @return
     */
    public ApiSendMsgStreamReqV1 Markdown(String recvType, String recvId, PipedInputStream pipedInputStream) {
        ApiSendMsgStreamReqV1 reqV1 = new ApiSendMsgStreamReqV1();
        reqV1.recvId = recvId;
        reqV1.recvType = recvType;
        reqV1.contentType = ContentTypeConstant.MARKDOWN;
        reqV1.pipedInputStream = pipedInputStream;
        return reqV1;
    }

}
