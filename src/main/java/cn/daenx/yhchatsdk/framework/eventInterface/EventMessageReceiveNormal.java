package cn.daenx.yhchatsdk.framework.eventInterface;

import cn.daenx.yhchatsdk.common.vo.EventMsgVo;

public interface EventMessageReceiveNormal {
    /**
     * 返回-1，后面的实现类将不再执行
     * 返回0，后面的实现类继续执行
     *
     * @return
     */
    Integer handle(EventMsgVo eventMsgVo);
}
