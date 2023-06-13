package cn.daenx.yhchatsdk.framework.eventInterface;

import cn.daenx.yhchatsdk.framework.vo.EventMsgVo;

/**
 * 退出群事件
 */
public interface EventGroupLeave {
    /**
     * 返回-1，后面的实现类将不再执行
     * 返回0，后面的实现类继续执行
     *
     * @return
     */
    Integer handle(EventMsgVo eventMsgVo);
}
