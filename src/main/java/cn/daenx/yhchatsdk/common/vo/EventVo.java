package cn.daenx.yhchatsdk.common.vo;

import cn.daenx.yhchatsdk.framework.eventInterface.EventMessageReceiveNormal;
import lombok.Data;

@Data
public class EventVo {
    @Data
    public static class EventMessageReceiveNormalVo extends EventBaseVo {
        private EventMessageReceiveNormal bean;
    }

    @Data
    public static class EventBaseVo {
        private int order;
    }
}