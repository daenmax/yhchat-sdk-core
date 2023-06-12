package cn.daenx.yhchatsdk.framework.core;

import cn.daenx.yhchatsdk.common.vo.EventVo;

import java.util.ArrayList;
import java.util.List;

public class GlobalPluginHandel {

    private static final GlobalPluginHandel INSTANCE = new GlobalPluginHandel();
    private List<EventVo.EventMessageReceiveNormalVo> dataList = new ArrayList<>();

    private GlobalPluginHandel() {
        // 私有构造方法，避免外部实例化
    }

    public static GlobalPluginHandel getInstance() {
        return INSTANCE;
    }

    public List<EventVo.EventMessageReceiveNormalVo> getDataList() {
        return dataList;
    }


}
