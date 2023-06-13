package cn.daenx.yhchatsdk.framework.vo;

import lombok.Data;

/**
 * 消息事件
 */
@Data
public class EventMsgVo {
    private String version;
    private Header header;
    private Event event;

    /**
     * Header对象
     * 包括事件的基础信息
     */
    @Data
    public static class Header {

        /**
         * 事件ID，全局唯一
         */
        private String eventId;

        /**
         * 事件产生的时间，毫秒13位时间戳
         */
        private Long eventTime;

        /**
         * 事件类型
         */
        private String eventType;
    }

    /**
     * Event对象
     * 包括事件的内容。注意：Event对象的结构会在不同的eventType下发生变化
     */
    @Data
    public static class Event {
        /**
         * bot.setting
         */
        /**
         * 13位时间戳
         */
        private Long time;
        private String chatId;
        private String chatType;
        private String groupId;
        private String groupName;
        private String avatarUrl;
        private String settingJson;


        /**
         * 发送者的信息
         */
        private Sender sender;

        /**
         * 聊天对象
         */
        private Chat chat;

        /**
         * 消息内容
         */
        private Message message;

        @Data
        public static class Sender {

            /**
             * 发送者ID，给用户回复消息需要该字段
             */
            private String senderId;
            /**
             * 发送者用户类型，取值：user
             */
            private String senderType;
            /**
             * 发送者级别，取值：owner、administrator、member、unknown
             */
            private String senderUserLevel;
            /**
             * 发送者昵称
             */
            private String senderNickname;
        }

        @Data
        public static class Chat {

            /**
             * 聊天对象ID
             */
            private String chatId;

            /**
             * 聊天对象类型，取值: bot、group
             */
            private String chatType;
        }

        @Data
        public static class Message {

            /**
             * 消息ID，全局唯一
             */
            private String msgId;

            /**
             * 引用消息时的父消息ID
             */
            private String parentId;

            /**
             * 消息发送时间，毫秒13位时间戳
             */
            private Long sendTime;

            /**
             * 当前聊天的对象ID
             * 单聊消息，chatId即对方用户ID
             * 群聊消息，chatId即群ID
             * 机器人消息，chatId即机器人ID
             */
            private String chatId;

            /**
             * 当前聊天的对象类型
             * group 群聊
             * bot 机器人
             */
            private String chatType;

            /**
             * 当前消息类型
             * text 文本消息
             * image 图片消息
             * markdown Markdown消息
             * file 文件消息
             */
            private String contentType;

            /**
             * 消息正文
             */
            private Content content;

            /**
             * 指令ID，可用来区分用户发送的指令
             */
            private Integer instructionId;

            /**
             * 指令名称，可用来区分用户发送的指令
             */
            private String instructionName;

            /**
             * 消息正文
             * 当消息类型为text时，取：text
             * 当消息类型为markdown时，取：text
             * 当消息类型为image时，取：imageUrl、imageName、etag
             * 当消息类型为file时，取：fileName、fileUrl、fileSize、etag
             */
            @Data
            public static class Content {
                /**
                 * 当消息类型为text、markdown时，有值
                 */
                private String text;

                /**
                 * 当消息类型为image时，有值
                 */
                private String imageUrl;
                private String imageName;

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
                 * 当消息类型为image、file时，有值
                 */
                private String etag;
            }
        }
    }
}
