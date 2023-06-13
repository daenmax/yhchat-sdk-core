package cn.daenx.yhchatsdk.framework.core;

import cn.daenx.yhchatsdk.common.constant.enums.EventType;
import cn.daenx.yhchatsdk.framework.vo.EventMsgVo;
import cn.daenx.yhchatsdk.framework.vo.EventVo;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * 事件处理分发器
 */
@Slf4j
public class GlobalEventHandle implements Runnable {

    private static GlobalPluginHandel globalPluginHandel;

    private static GlobalPluginHandel getGlobalPluginHandel() {
        if (globalPluginHandel == null) {
            globalPluginHandel = GlobalPluginHandel.getInstance();
        }
        return globalPluginHandel;
    }

    private EventMsgVo eventMsgVo;

    public GlobalEventHandle(EventMsgVo eventMsgVo) {
        this.eventMsgVo = eventMsgVo;
    }

    @Override
    public void run() {
        String eventType = eventMsgVo.getHeader().getEventType();
        if (EventType.MESSAGE_RECEIVE_NORMAL.getCode().equals(eventType)) {
            eventMessageReceiveNormal(eventMsgVo);
        } else if (EventType.MESSAGE_RECEIVE_INSTRUCTION.getCode().equals(eventType)) {
            eventMessageReceiveInstruction(eventMsgVo);
        } else if (EventType.BOT_FOLLOWED.getCode().equals(eventType)) {
            eventBotFollwed(eventMsgVo);
        } else if (EventType.BOT_UNFOLLOWED.getCode().equals(eventType)) {
            eventBotUnfollwed(eventMsgVo);
        } else if (EventType.BUTTON_REPORT_INLINE.getCode().equals(eventType)) {
            eventButtonReportInline(eventMsgVo);
        } else if (EventType.GROUP_JOIN.getCode().equals(eventType)) {
            eventGroupJoin(eventMsgVo);
        } else if (EventType.GROUP_LEAVE.getCode().equals(eventType)) {
            eventGroupLeave(eventMsgVo);
        } else if (EventType.BOT_SETTING.getCode().equals(eventType)) {
            eventBotSetting(eventMsgVo);
        } else {
            log.error("事件投递失败，未知的消息类型：{}", eventType);
        }
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

    /**
     * 关注机器人事件
     *
     * @param eventMsgVo
     */
    public static void eventBotFollwed(EventMsgVo eventMsgVo) {
        List<EventVo.EventBotFollwedVo> list = getGlobalPluginHandel().getEventBotFollwedVos();
        for (EventVo.EventBotFollwedVo vo : list) {
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
     * 取消关注机器人事件
     *
     * @param eventMsgVo
     */
    public static void eventBotUnfollwed(EventMsgVo eventMsgVo) {
        List<EventVo.EventBotUnfollwedVo> list = getGlobalPluginHandel().getEventBotUnfollwedVos();
        for (EventVo.EventBotUnfollwedVo vo : list) {
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
     * 消息中按钮点击事件
     *
     * @param eventMsgVo
     */
    public static void eventButtonReportInline(EventMsgVo eventMsgVo) {
        List<EventVo.EventButtonReportInlineVo> list = getGlobalPluginHandel().getEventButtonReportInlineVos();
        for (EventVo.EventButtonReportInlineVo vo : list) {
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
     * 加入群事件
     *
     * @param eventMsgVo
     */
    public static void eventGroupJoin(EventMsgVo eventMsgVo) {
        List<EventVo.EventGroupJoinVo> list = getGlobalPluginHandel().getEventGroupJoinVos();
        for (EventVo.EventGroupJoinVo vo : list) {
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
     * 退出群事件
     *
     * @param eventMsgVo
     */
    public static void eventGroupLeave(EventMsgVo eventMsgVo) {
        List<EventVo.EventGroupLeaveVo> list = getGlobalPluginHandel().getEventGroupLeaveVos();
        for (EventVo.EventGroupLeaveVo vo : list) {
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
     * 机器人设置事件
     *
     * @param eventMsgVo
     */
    public static void eventBotSetting(EventMsgVo eventMsgVo) {
        List<EventVo.EventBotSettingVo> list = getGlobalPluginHandel().getEventBotSettingVos();
        for (EventVo.EventBotSettingVo vo : list) {
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
