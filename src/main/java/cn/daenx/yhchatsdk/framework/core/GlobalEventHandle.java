package cn.daenx.yhchatsdk.framework.core;

import cn.daenx.yhchatsdk.common.vo.EventVo;
import cn.daenx.yhchatsdk.common.vo.EventMsgVo;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * 事件处理分发器
 */
@Slf4j
public class GlobalEventHandle {
    private static GlobalPluginHandel globalPluginHandel;

    private static GlobalPluginHandel getGlobalPluginHandel() {
        if (globalPluginHandel == null) {
            globalPluginHandel = GlobalPluginHandel.getInstance();
        }
        return globalPluginHandel;
    }

    /**
     * 普通消息事件
     *
     * @param eventMsgVo
     */
    public static void eventMessageReceiveNormal(EventMsgVo eventMsgVo) {
        List<EventVo.EventMessageReceiveNormalVo> list = getGlobalPluginHandel().getEventMessageReceiveNormalVos();
        for (EventVo.EventMessageReceiveNormalVo vo : list) {
            try {
                Integer integer = vo.getBean().handle(eventMsgVo);
                // 如果返回值是-1，那么将不再继续执行
                if (integer == -1) {
                    return;
                }
            } catch (Exception e) {
                e.printStackTrace();
                log.error("插件[{}]发送异常：{}", vo.getPluginName(), e.getMessage());
            }

        }
    }

    /**
     * 指令消息事件
     *
     * @param eventMsgVo
     */
    public static void eventMessageReceiveInstruction(EventMsgVo eventMsgVo) {
        List<EventVo.EventMessageReceiveInstructionVo> list = getGlobalPluginHandel().getEventMessageReceiveInstructionVos();
        for (EventVo.EventMessageReceiveInstructionVo vo : list) {
            try {
                Integer integer = vo.getBean().handle(eventMsgVo);
                // 如果返回值是-1，那么将不再继续执行
                if (integer == -1) {
                    return;
                }
            } catch (Exception e) {
                e.printStackTrace();
                log.error("插件[{}]发送异常：{}", vo.getPluginName(), e.getMessage());
            }
        }
    }

}
