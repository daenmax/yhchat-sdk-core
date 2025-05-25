package cn.daenx.yhchatsdk.framework.vo.v1.ret;

import lombok.Data;

/**
 * 上传接口响应体
 *
 * @author DaenMax
 */
@Data
public class ApiUploadRetV1 {
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
            private String imageKey;
            private String videoKey;
            private String fileKey;
    }

}
