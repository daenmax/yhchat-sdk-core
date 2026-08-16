package cn.daenx.yhchatsdk.framework.vo.v1.req;

import lombok.Data;

/**
 * 群成员禁言请求体
 *
 * @author DaenMax
 */
@Data
public class ApiGroupGagMemberReqV1 {

    /**
     * 被禁言用户ID
     */
    private String userId;

    /**
     * 群组ID
     */
    private String groupId;

    /**
     * 禁言时长（秒）
     * 0: 解除禁言
     * 600: 禁言10分钟
     * 3600: 禁言1小时
     * 21600: 禁言6小时
     * 43200: 禁言12小时
     * -1: 永久禁言
     */
    private Long gag;

    public ApiGroupGagMemberReqV1(String userId, String groupId, Long gag) {
        this.userId = userId;
        this.groupId = groupId;
        this.gag = gag;
    }
}