package cn.daenx.yhchatsdk.framework.vo.v1.req;

import cn.daenx.yhchatsdk.common.constant.ContentTypeConstant;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 发送消息请求体
 *
 * @author DaenMax
 */
@Data
public class ApiSendMsgReqV1 {
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
     * text\image\file\markdown
     */
    private String contentType;
    private ApiSendContentV1 content = new ApiSendContentV1();

    @Data
    public static class ApiSendContentV1 {

        /**
         * 当消息类型为text、markdown时
         */
        private String text;

        /**
         * 当消息类型为image时
         */
        private String imageUrl;

        /**
         * 当消息类型为file时，有值
         */
        private String fileName;
        private String fileUrl;

        /**
         * 消息中包括button
         * 非必填
         */
        private List<ApiSendButtonV1> buttons = new ArrayList<>();
    }

    @Data
    public static class ApiSendButtonV1 {

        /**
         * 按钮上的文字
         */
        private String text;

        /**
         * 1: 跳转URL
         * 2: 复制
         * 3: 点击汇报
         */
        private Integer actionType;

        /**
         * 当actionType为1时使用
         * 非必填
         */
        private String url;

        /**
         * 当actionType为2时，该值会复制到剪贴板
         * 当actionType为3时，该值会发送给订阅端
         * 非必填
         */
        private String value;
    }


    //快捷构造方法

    /**
     * 文本消息
     *
     * @param recvType RecvTypeConstant.
     * @param recvId   当为USER时，填用户ID，当为GROUP时，填群ID
     * @param text     消息内容
     * @return
     */
    public ApiSendMsgReqV1 Text(String recvType, String recvId, String text) {
        ApiSendMsgReqV1 reqV1 = new ApiSendMsgReqV1();
        reqV1.recvId = recvId;
        reqV1.recvType = recvType;
        reqV1.contentType = ContentTypeConstant.TEXT;
        ApiSendContentV1 contentV1 = new ApiSendContentV1();
        contentV1.text = text;
        reqV1.content = contentV1;
        return reqV1;
    }

    /**
     * 图片消息
     *
     * @param recvType RecvTypeConstant.
     * @param recvId   当为USER时，填用户ID，当为GROUP时，填群ID
     * @param imageUrl 图片网络直链
     * @return
     */
    public ApiSendMsgReqV1 Image(String recvType, String recvId, String imageUrl) {
        ApiSendMsgReqV1 reqV1 = new ApiSendMsgReqV1();
        this.recvId = recvId;
        this.recvType = recvType;
        this.contentType = ContentTypeConstant.IMAGE;
        ApiSendContentV1 contentV1 = new ApiSendContentV1();
        contentV1.imageUrl = imageUrl;
        reqV1.content = contentV1;
        return reqV1;
    }

    /**
     * Markdown消息
     *
     * @param recvType RecvTypeConstant.
     * @param recvId   当为USER时，填用户ID，当为GROUP时，填群ID
     * @param text     md消息内容
     * @return
     */
    public ApiSendMsgReqV1 Markdown(String recvType, String recvId, String text) {
        ApiSendMsgReqV1 reqV1 = new ApiSendMsgReqV1();
        this.recvId = recvId;
        this.recvType = recvType;
        this.contentType = ContentTypeConstant.MARKDOWN;
        ApiSendContentV1 contentV1 = new ApiSendContentV1();
        contentV1.text = text;
        reqV1.content = contentV1;
        return reqV1;
    }

    /**
     * 文件消息
     * 该方法只能调用一次，并且与其他类型方法互斥
     *
     * @param recvType RecvTypeConstant.
     * @param recvId   当为USER时，填用户ID，当为GROUP时，填群ID
     * @param fileName 文件名
     * @param fileUrl  文件网络直链
     * @return
     */
    public ApiSendMsgReqV1 File(String recvType, String recvId, String fileName, String fileUrl) {
        ApiSendMsgReqV1 reqV1 = new ApiSendMsgReqV1();
        this.recvId = recvId;
        this.recvType = recvType;
        this.contentType = ContentTypeConstant.FILE;
        ApiSendContentV1 contentV1 = new ApiSendContentV1();
        contentV1.fileName = fileName;
        contentV1.fileUrl = fileUrl;
        reqV1.content = contentV1;
        return reqV1;
    }

    /**
     * 添加一个按钮
     * 该方法可多次调用，在类型方法调用之后调用
     *
     * @param text       按钮上的文字
     * @param actionType ButtonActionTypeConstant.
     * @param url        当actionType为JUMP_URL时使用，其他时候传NULL
     * @param value      当actionType为COPY、REPORT时使用，其他时候传NULL
     * @return
     */
    public ApiSendMsgReqV1 addButton(String text, Integer actionType, String url, String value) {
        ApiSendButtonV1 buttonV1 = new ApiSendButtonV1();
        buttonV1.setText(text);
        buttonV1.setActionType(actionType);
        buttonV1.setUrl(url);
        buttonV1.setValue(value);
        this.content.buttons.add(buttonV1);
        return this;
    }
}
