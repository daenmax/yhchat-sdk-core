package cn.daenx.yhchatsdk.framework.vo.v1.ret;

import lombok.Data;

import java.util.List;

/**
 * 获取群标签列表响应体
 *
 * @author DaenMax
 */
@Data
public class ApiGroupTagListRetV1 {
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
        /**
         * 标签列表
         */
        private List<TagInfo> list;

        @Data
        public static class TagInfo {
            /**
             * 标签名称
             */
            private String tag;
            /**
             * 标签描述
             */
            private String desc;
            /**
             * 标签颜色
             */
            private String color;
            /**
             * 排序值
             */
            private Long sort;
        }
    }
}