package cn.daenx.yhchatsdk.framework.core;

import cn.daenx.yhchatsdk.framework.vo.EventVo;

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

    /**
     * 关注机器人事件
     */
    private List<EventVo.EventBotFollwedVo> EventEventBotFollwedVos = new ArrayList<>();
    public List<EventVo.EventBotFollwedVo> getEventBotFollwedVos() {
        return EventEventBotFollwedVos;
    }

    /**
     * 指令消息事件插件
     */
    private List<EventVo.EventBotUnfollwedVo> EventBotUnfollwedVos = new ArrayList<>();
    public List<EventVo.EventBotUnfollwedVo> getEventBotUnfollwedVos() {
        return EventBotUnfollwedVos;
    }

    /**
     * 指令消息事件插件
     */
    private List<EventVo.EventButtonReportInlineVo> EventButtonReportInlineVos = new ArrayList<>();
    public List<EventVo.EventButtonReportInlineVo> getEventButtonReportInlineVos() {
        return EventButtonReportInlineVos;
    }

    /**
     * 指令消息事件插件
     */
    private List<EventVo.EventGroupJoinVo> EventGroupJoinVos = new ArrayList<>();
    public List<EventVo.EventGroupJoinVo> getEventGroupJoinVos() {
        return EventGroupJoinVos;
    }

    /**
     * 指令消息事件插件
     */
    private List<EventVo.EventGroupLeaveVo> EventGroupLeaveVos = new ArrayList<>();
    public List<EventVo.EventGroupLeaveVo> getEventGroupLeaveVos() {
        return EventGroupLeaveVos;
    }

    /**
     * 指令消息事件插件
     */
    private List<EventVo.EventBotSettingVo> EventBotSettingVos = new ArrayList<>();
    public List<EventVo.EventBotSettingVo> getEventBotSettingVos() {
        return EventBotSettingVos;
    }
}
