package cn.daenx.yhchatsdk.common.constant.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 事件
 */
public enum EventType {

    MESSAGE_RECEIVE_NORMAL("message.receive.normal", "普通消息事件"),
    MESSAGE_RECEIVE_INSTRUCTION("message.receive.instruction", "指令消息事件"),
    BOT_FOLLOWED("bot.followed", "关注机器人事件"),
    BOT_UNFOLLOWED("bot.unfollowed", "取消关注机器人事件"),
    GROUP_JOIN("group.join", "加入群事件"),
    GROUP_LEAVE("group.leave", "退出群事件"),
    BUTTON_REPORT_INLINE("button.report.inline", "按钮事件"),
    BOT_SETTING("bot.setting", "机器人设置事件");

    private String code;
    private String desc;

    private static final Map<String, String> map;
    private static final List<String> list;

    static {
        map = new HashMap<>();
        list = new ArrayList<>();
        for (EventType value : EventType.values()) {
            map.put(value.getCode(), value.getDesc());
            list.add(value.getCode());
        }
    }

    public static String getDesc(String code) {
        String desc = map.get(code);
        if (desc == null) {
            return "";
        }
        return desc;
    }

    public static boolean hasCode(String code) {
        return list.contains(code);
    }

    EventType(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    EventType() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
