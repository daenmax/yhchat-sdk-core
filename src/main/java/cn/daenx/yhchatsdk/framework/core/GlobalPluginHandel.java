package cn.daenx.yhchatsdk.framework.core;

import cn.daenx.yhchatsdk.common.vo.EventVo;

import java.util.ArrayList;
import java.util.List;

public class GlobalPluginHandel {

    private static final GlobalPluginHandel INSTANCE = new GlobalPluginHandel();

    private GlobalPluginHandel() {
    }

    public static GlobalPluginHandel getInstance() {
        return INSTANCE;
    }


    private List<EventVo.EventMessageReceiveNormalVo> eventMessageReceiveNormalVos = new ArrayList<>();
    public List<EventVo.EventMessageReceiveNormalVo> getEventMessageReceiveNormalVos() {
        return eventMessageReceiveNormalVos;
    }

    private List<EventVo.EventMessageReceiveInstructionVo> EventMessageReceiveInstructionVos = new ArrayList<>();

    public List<EventVo.EventMessageReceiveInstructionVo> getEventMessageReceiveInstructionVos() {
        return EventMessageReceiveInstructionVos;
    }
}
