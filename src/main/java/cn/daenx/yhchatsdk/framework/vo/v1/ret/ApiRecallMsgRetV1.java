package cn.daenx.yhchatsdk.framework.vo.v1.ret;

import lombok.Data;

/**
 * 撤回消息响应体
 *
 * @author DaenMax
 */
@Data
public class ApiRecallMsgRetV1 {
    /**
     * 1=成功
     */
    private Integer code;
    /**
     * success=成功
     */
    private String msg;

}
