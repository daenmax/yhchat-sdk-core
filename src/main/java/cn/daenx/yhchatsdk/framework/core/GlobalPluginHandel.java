package cn.daenx.yhchatsdk.framework.core;

import cn.daenx.yhchatsdk.common.vo.EventVo;

import java.util.ArrayList;
import java.util.List;

/**
 * 插件管理器
 */
public class GlobalPluginHandel {

    private static final GlobalPluginHandel INSTANCE = new GlobalPluginHandel();

    private GlobalPluginHandel() {
    }

    public static GlobalPluginHandel getInstance() {
        return INSTANCE;
    }

    /**
     * 普通消息事件插件
     */
    private List<EventVo.EventMessageReceiveNormalVo> eventMessageReceiveNormalVos = new ArrayList<>();
    public List<EventVo.EventMessageReceiveNormalVo> getEventMessageReceiveNormalVos() {
        return eventMessageReceiveNormalVos;
    }

    /**
     * 指令消息事件插件
     */
    private List<EventVo.EventMessageReceiveInstructionVo> EventMessageReceiveInstructionVos = new ArrayList<>();
    public List<EventVo.EventMessageReceiveInstructionVo> getEventMessageReceiveInstructionVos() {
        return EventMessageReceiveInstructionVos;
    }
}
