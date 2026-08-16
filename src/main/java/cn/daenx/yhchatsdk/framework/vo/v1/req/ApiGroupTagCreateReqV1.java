package cn.daenx.yhchatsdk.framework.vo.v1.req;

import lombok.Data;

/**
 * 创建标签请求体
 *
 * @author DaenMax
 */
@Data
public class ApiGroupTagCreateReqV1 {

    /**
     * 群组ID
     */
    private String groupId;

    /**
     * 标签名称，最长9个字符
     */
    private String tag;

    /**
     * 标签颜色，格式为#RRGGBB，如#FF5733
     * 非必填
     */
    private String color;

    /**
     * 标签描述
     * 非必填
     */
    private String desc;

    /**
     * 排序值，值越小越靠前
     * 非必填
     */
    private Long sort;

    public ApiGroupTagCreateReqV1(String groupId, String tag, String color, String desc, Long sort) {
        this.groupId = groupId;
        this.tag = tag;
        this.color = color;
        this.desc = desc;
        this.sort = sort;
    }
}