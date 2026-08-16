package cn.daenx.yhchatsdk.framework.vo.v1.req;

import lombok.Data;

/**
 * 移除群成员请求体
 *
 * @author DaenMax
 */
@Data
public class ApiGroupRemoveMemberReqV1 {

    /**
     * 被移除用户ID
     */
    private String userId;

    /**
     * 群组ID
     */
    private String groupId;

    public ApiGroupRemoveMemberReqV1(String userId, String groupId) {
        this.userId = userId;
        this.groupId = groupId;
    }
}