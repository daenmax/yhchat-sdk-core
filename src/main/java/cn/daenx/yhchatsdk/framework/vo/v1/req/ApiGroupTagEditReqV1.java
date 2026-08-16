package cn.daenx.yhchatsdk.framework.vo.v1.req;

import lombok.Data;

/**
 * 修改标签请求体
 *
 * @author DaenMax
 */
@Data
public class ApiGroupTagEditReqV1 {

    /**
     * 群组ID
     */
    private String groupId;

    /**
     * 要修改的标签名称
     */
    private String tag;

    /**
     * 新的标签名称，不传则不修改标签名称
     * 非必填
     */
    private String newTag;

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

    public ApiGroupTagEditReqV1(String groupId, String tag, String newTag, String color, String desc, Long sort) {
        this.groupId = groupId;
        this.tag = tag;
        this.newTag = newTag;
        this.color = color;
        this.desc = desc;
        this.sort = sort;
    }
}