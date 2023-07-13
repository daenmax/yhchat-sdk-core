package cn.daenx.yhchatsdk.framework.vo.v1.req;

import lombok.Data;

/**
 * 取消设置看板请求体
 *
 * @author DaenMax
 */
@Data
public class ApiDisBoardReqV1 {

    /**
     * 对象ID
     * 用户: userId
     * 群: groupId
     */
    private String recvId;
    /**
     * 对象类型
     * 用户: user
     * 群: group
     */
    private String recvType;

    //快捷构造方法

    /**
     * 文本消息
     * 如果要取消设置全局看板，recvType和recvId请传null
     *
     * @param recvType RecvTypeConstant.
     * @param recvId   当为USER时，填用户ID，当为GROUP时，填群ID
     * @return
     */
    public ApiDisBoardReqV1 Dis(String recvType, String recvId) {
        this.recvId = recvId;
        this.recvType = recvType;
        return this;
    }

}
