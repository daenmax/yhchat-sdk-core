package cn.daenx.yhchatsdk.framework.vo.v1.ret;

import lombok.Data;

import java.util.HashMap;
import java.util.List;

/**
 * 消息列表响应体
 *
 * @author DaenMax
 */
@Data
public class ApiMessagesRetV1 {
    /**
     * 1=成功
     */
    private Integer code;
    /**
     * success=成功
     */
    private String msg;
    private RetData data;

    @Data
    public static class RetData {
        private List<Messages> list;
        private Integer total;

        @Data
        public static class Messages {
            /**
             * 消息ID，全局唯一
             */
            private String msgId;

            /**
             * 引用消息时的父消息ID
             */
            private String parentId;
            /**
             * 发送者ID
             */
            private String senderId;
            /**
             * 发送者昵称
             */
            private String senderNickname;

            /**
             * 当前消息类型
             * text 文本消息
             * image 图片消息
             * video 视频消息
             * markdown Markdown消息
             * file 文件消息
             * html 网页消息
             * post 帖子消息
             */
            private String contentType;

            /**
             * 消息正文
             */
            private Content content;


            /**
             * 消息发送时间，毫秒13位时间戳
             */
            private Long sendTime;


            /**
             * 指令ID，可用来区分用户发送的指令
             */
            private Integer commandId;

            /**
             * 指令名称，可用来区分用户发送的指令
             */
            private String commandName;

            /**
             * 消息正文
             * 当消息类型为text时，取：text
             * 当消息类型为markdown时，取：text
             * 当消息类型为image时，取：imageUrl、imageName、etag
             * 当消息类型为file时，取：fileName、fileUrl、fileSize、etag
             */
            @Data
            public static class Content {
                private String etag;

                /**
                 * 当有被引用回复时有值，为被引用的消息内容
                 */
                private String parent;

                /**
                 * 当消息类型为text、markdown、html、post时，有值
                 */
                private String text;

                /**
                 * 当消息类型为image时，有值
                 */
                private String imageName;
                private Integer imageHeight;
                private Integer imageWidth;

                /**
                 * 当消息类型为video时，有值
                 */
                private String videoUrl;
                private Integer videoDuration;

                /**
                 * 当消息类型为file时，有值
                 */
                private String fileName;
                private String fileUrl;
                /**
                 * 文件大小，单位Byte，字节
                 */
                private Long fileSize;

                /**
                 * 当消息类型为post时，有值
                 */
                private String postContent;
                private Integer postContentType;
                private String postId;


                /**
                 * 当消息中有按钮时有值
                 */
                private List<Button> buttons;


                /**
                 * 当有菜单的时候有值
                 */
                private HashMap<String, String> menu;

                @Data
                public static class Button {

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
            }
        }
    }

}
