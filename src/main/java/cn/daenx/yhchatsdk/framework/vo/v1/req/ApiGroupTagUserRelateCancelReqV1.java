package cn.daenx.yhchatsdk.framework.vo.v1.req;

import lombok.Data;

/**
 * 给用户移除标签请求体
 *
 * @author DaenMax
 */
@Data
public class ApiGroupTagUserRelateCancelReqV1 {

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

    public ApiGroupTagUserRelateCancelReqV1(String userId, String tag, String groupId) {
        this.userId = userId;
        this.tag = tag;
        this.groupId = groupId;
    }
}