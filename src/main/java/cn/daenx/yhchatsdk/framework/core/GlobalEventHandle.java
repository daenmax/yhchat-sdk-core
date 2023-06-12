package cn.daenx.yhchatsdk.framework.core;

import cn.daenx.yhchatsdk.common.vo.EventVo;
import cn.daenx.yhchatsdk.common.vo.EventMsgVo;

import java.util.List;

/**
 * 事件处理分发器
 */
public class GlobalEventHandle {
    public static void eventMessageReceiveNormal(EventMsgVo eventMsgVo) {
        GlobalPluginHandel globalData = GlobalPluginHandel.getInstance();
        List<EventVo.EventMessageReceiveNormalVo> dataList = globalData.getDataList();
        for (EventVo.EventMessageReceiveNormalVo eventMessageReceiveNormalVo : dataList) {
            // 执行实现类的方法
            Integer integer = eventMessageReceiveNormalVo.getBean().handle(eventMsgVo);
            System.out.println("返回值=" + integer);
            // 如果返回值是-1，那么将不再继续执行
            if (integer == -1) {
                return;
            }
        }
    }
}
