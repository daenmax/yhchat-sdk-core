package cn.daenx.yhchatsdk.framework.vo.v1.ret;

import lombok.Data;

/**
 * 流式发送消息响应体
 *
 * @author DaenMax
 */
@Data
public class ApiSendMsgStreamRetV1 {
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
        private MessageInfo messageInfo;

        @Data
        public static class MessageInfo {
            private String msgId;
            private String recvId;
            private String recvType;
        }
    }

}
