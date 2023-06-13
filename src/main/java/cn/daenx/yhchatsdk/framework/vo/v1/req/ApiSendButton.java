package cn.daenx.yhchatsdk.framework.vo.v1.req;

import lombok.Data;

@Data
public class ApiSendButton {

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
