package cn.daenx.yhchatsdk.framework.vo.v1.req;

import lombok.Data;

/**
 * 获取群标签列表请求体
 *
 * @author DaenMax
 */
@Data
public class ApiGroupTagListReqV1 {

    /**
     * 群组ID
     */
    private String groupId;

    public ApiGroupTagListReqV1(String groupId) {
        this.groupId = groupId;
    }
}