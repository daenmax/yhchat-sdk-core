package cn.daenx.yhchatsdk.common.constant;

/**
 * 消息类型
 *
 * @author DaenMax
 */
public class ContentTypeConstant {
    public static final String TEXT = "text";
    public static final String IMAGE = "image";
    public static final String VIDEO = "video";
    public static final String FILE = "file";
    public static final String MARKDOWN = "markdown";
    public static final String HTML = "html";
    //仅收到指令消息事件时，并且指令类型为自定义输入时，为此值。不可用于发送消息
    public static final String FORM = "form";

}
