package cn.daenx.yhchatsdk.framework.vo.v1.ret;

import lombok.Data;

/**
 * 取消设置看板响应体
 *
 * @author DaenMax
 */
@Data
public class ApiDisBoardRetV1 {
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
