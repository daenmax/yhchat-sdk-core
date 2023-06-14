package cn.daenx.yhchatsdk.framework.core;

import cn.daenx.yhchatsdk.framework.vo.PluginManagerVo;

import java.util.ArrayList;
import java.util.List;

/**
 * 插件管理器
 *
 * @author DaenMax
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
    private List<PluginManagerVo.EventMessageReceiveNormalVo> eventMessageReceiveNormalVos = new ArrayList<>();
    public List<PluginManagerVo.EventMessageReceiveNormalVo> getEventMessageReceiveNormalVos() {
        return eventMessageReceiveNormalVos;
    }

    /**
     * 指令消息事件插件
     */
    private List<PluginManagerVo.EventMessageReceiveInstructionVo> EventMessageReceiveInstructionVos = new ArrayList<>();
    public List<PluginManagerVo.EventMessageReceiveInstructionVo> getEventMessageReceiveInstructionVos() {
        return EventMessageReceiveInstructionVos;
    }

    /**
     * 关注机器人事件
     */
    private List<PluginManagerVo.EventBotFollwedVo> EventEventBotFollwedVos = new ArrayList<>();
    public List<PluginManagerVo.EventBotFollwedVo> getEventBotFollwedVos() {
        return EventEventBotFollwedVos;
    }

    /**
     * 指令消息事件插件
     */
    private List<PluginManagerVo.EventBotUnfollwedVo> EventBotUnfollwedVos = new ArrayList<>();
    public List<PluginManagerVo.EventBotUnfollwedVo> getEventBotUnfollwedVos() {
        return EventBotUnfollwedVos;
    }

    /**
     * 指令消息事件插件
     */
    private List<PluginManagerVo.EventButtonReportInlineVo> EventButtonReportInlineVos = new ArrayList<>();
    public List<PluginManagerVo.EventButtonReportInlineVo> getEventButtonReportInlineVos() {
        return EventButtonReportInlineVos;
    }

    /**
     * 指令消息事件插件
     */
    private List<PluginManagerVo.EventGroupJoinVo> EventGroupJoinVos = new ArrayList<>();
    public List<PluginManagerVo.EventGroupJoinVo> getEventGroupJoinVos() {
        return EventGroupJoinVos;
    }

    /**
     * 指令消息事件插件
     */
    private List<PluginManagerVo.EventGroupLeaveVo> EventGroupLeaveVos = new ArrayList<>();
    public List<PluginManagerVo.EventGroupLeaveVo> getEventGroupLeaveVos() {
        return EventGroupLeaveVos;
    }

    /**
     * 指令消息事件插件
     */
    private List<PluginManagerVo.EventBotSettingVo> EventBotSettingVos = new ArrayList<>();
    public List<PluginManagerVo.EventBotSettingVo> getEventBotSettingVos() {
        return EventBotSettingVos;
    }
}
