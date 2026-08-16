package cn.daenx.yhchatsdk.framework.vo.v1.req;

import lombok.Data;

/**
 * 给用户添加标签请求体
 *
 * @author DaenMax
 */
@Data
public class ApiGroupTagUserRelateReqV1 {

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 标签名称
     */
    private String tag;

    /**
     * 群组ID
     */
    private String groupId;

    public ApiGroupTagUserRelateReqV1(String userId, String tag, String groupId) {
        this.userId = userId;
        this.tag = tag;
        this.groupId = groupId;
    }
}