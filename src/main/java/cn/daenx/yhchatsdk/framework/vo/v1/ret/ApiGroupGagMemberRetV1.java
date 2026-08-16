package cn.daenx.yhchatsdk.framework.vo.v1.ret;

import lombok.Data;

/**
 * 群成员禁言响应体
 *
 * @author DaenMax
 */
@Data
public class ApiGroupGagMemberRetV1 {
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
    }
}