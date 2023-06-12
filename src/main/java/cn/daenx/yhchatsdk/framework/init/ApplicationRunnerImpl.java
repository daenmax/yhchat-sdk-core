package cn.daenx.yhchatsdk.framework.init;

import cn.daenx.yhchatsdk.common.utils.SpringUtil;
import cn.daenx.yhchatsdk.common.vo.EventVo;
import cn.daenx.yhchatsdk.framework.eventInterface.EventMessageReceiveNormal;
import cn.daenx.yhchatsdk.framework.core.GlobalPluginHandel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * 项目启动后，初始化
 */
@Component
@Slf4j
public class ApplicationRunnerImpl implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) {
        ApplicationContext applicationContext = SpringUtil.getContext();
        GlobalPluginHandel globalData = GlobalPluginHandel.getInstance();

        //普通消息事件插件
        List<EventVo.EventMessageReceiveNormalVo> eventMessageReceiveNormalVos = loadEventMessageReceiveNormal(applicationContext);
        List<EventVo.EventMessageReceiveNormalVo> eventMessageReceiveNormalVoList = globalData.getDataList();
        eventMessageReceiveNormalVoList.addAll(eventMessageReceiveNormalVos);

    }

    /**
     * 加载插件：普通消息事件
     *
     * @param applicationContext
     * @return
     */
    private List<EventVo.EventMessageReceiveNormalVo> loadEventMessageReceiveNormal(ApplicationContext applicationContext) {
        List<EventVo.EventMessageReceiveNormalVo> list = new ArrayList<>();
        Map<String, EventMessageReceiveNormal> beans = applicationContext.getBeansOfType(EventMessageReceiveNormal.class);
        for (Map.Entry<String, EventMessageReceiveNormal> entry : beans.entrySet()) {
            EventMessageReceiveNormal bean = entry.getValue();
            Class<?> clazz = bean.getClass();
            Order order = AnnotationUtils.findAnnotation(clazz, Order.class);
            EventVo.EventMessageReceiveNormalVo myImplVo = new EventVo.EventMessageReceiveNormalVo();
            if (order != null) {
                myImplVo.setOrder(order.value());
            } else {
                myImplVo.setOrder(0);
            }
            myImplVo.setBean(bean);
            list.add(myImplVo);
            //按照order进行排序，从大到小
            Collections.sort(list, Comparator.comparingInt(EventVo.EventMessageReceiveNormalVo::getOrder));
        }
        return list;
    }


}
