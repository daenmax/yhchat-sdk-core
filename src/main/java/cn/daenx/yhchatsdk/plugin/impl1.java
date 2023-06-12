package cn.daenx.yhchatsdk.plugin;

import cn.daenx.yhchatsdk.framework.eventInterface.EventMessageReceiveNormal;
import cn.daenx.yhchatsdk.common.vo.EventMsgVo;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

@Service
@Order(2)//默认为0
public class impl1 implements EventMessageReceiveNormal {
    @Override
    public Integer handle(EventMsgVo eventMsgVo) {
        System.out.println("执行1");
        return 0;
    }
}
