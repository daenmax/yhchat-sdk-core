package cn.daenx.yhchatsdk.framework.vo;

import cn.daenx.yhchatsdk.framework.eventInterface.*;
import lombok.Data;

@Data
public class EventVo {
    @Data
    public static class EventMessageReceiveNormalVo extends EventBaseVo {
        private EventMessageReceiveNormal bean;
    }
    @Data
    public static class EventMessageReceiveInstructionVo extends EventBaseVo {
        private EventMessageReceiveInstruction bean;
    }
    @Data
    public static class EventBotFollwedVo extends EventBaseVo {
        private EventBotFollwed bean;
    }
    @Data
    public static class EventBotUnfollwedVo extends EventBaseVo {
        private EventBotUnfollwed bean;
    }
    @Data
    public static class EventButtonReportInlineVo extends EventBaseVo {
        private EventButtonReportInline bean;
    }
    @Data
    public static class EventGroupJoinVo extends EventBaseVo {
        private EventGroupJoin bean;
    }
    @Data
    public static class EventGroupLeaveVo extends EventBaseVo {
        private EventGroupLeave bean;
    }
    @Data
    public static class EventBotSettingVo extends EventBaseVo {
        private EventBotSetting bean;
    }

    @Data
    public static class EventBaseVo {
        private int order;
        private String pluginName;
    }
}
