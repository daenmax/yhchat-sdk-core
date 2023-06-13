package cn.daenx.yhchatsdk.framework.vo.v1.ret;

import lombok.Data;

import java.util.List;

/**
 * 接口响应体
 */
@Data
public class ApiSendMsgBatchRetV1 {
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
        private String successCount;
        private List<MessageInfo> successList;

        @Data
        public static class MessageInfo {
            private String msgId;
            private String recvId;
            private String recvType;
        }
    }

}
