package cn.daenx.yhchatsdk.framework.vo.v1.req;

import lombok.Data;

/**
 * 删除标签请求体
 *
 * @author DaenMax
 */
@Data
public class ApiGroupTagDeleteReqV1 {

    /**
     * 标签名称
     */
    private String tag;

    /**
     * 群组ID
     */
    private String groupId;

    public ApiGroupTagDeleteReqV1(String tag, String groupId) {
        this.tag = tag;
        this.groupId = groupId;
    }
}