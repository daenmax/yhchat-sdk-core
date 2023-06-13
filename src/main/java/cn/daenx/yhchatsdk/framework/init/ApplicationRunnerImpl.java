package cn.daenx.yhchatsdk.framework.init;

import cn.daenx.yhchatsdk.common.utils.SpringUtil;
import cn.daenx.yhchatsdk.common.vo.EventVo;
import cn.daenx.yhchatsdk.framework.eventInterface.EventMessageReceiveInstruction;
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
 * 插件加载
 */
@Component
@Slf4j
public class ApplicationRunnerImpl implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) {
        log.info("加载插件开始...");
        int allNum = 0;
        ApplicationContext applicationContext = SpringUtil.getContext();
        GlobalPluginHandel globalData = GlobalPluginHandel.getInstance();

        //普通消息事件插件
        List<EventVo.EventMessageReceiveNormalVo> loadList1 = loadEventMessageReceiveNormal(applicationContext);
        List<EventVo.EventMessageReceiveNormalVo> dataList1 = globalData.getEventMessageReceiveNormalVos();
        dataList1.addAll(loadList1);
        allNum = allNum + loadList1.size();
        log.info("加载【普通消息事件插件】插件完成，共{}个", loadList1.size());

        //指令消息事件插件
        List<EventVo.EventMessageReceiveInstructionVo> loadList2 = loadEventMessageReceiveInstruction(applicationContext);
        List<EventVo.EventMessageReceiveInstructionVo> dataList2 = globalData.getEventMessageReceiveInstructionVos();
        dataList2.addAll(loadList2);
        allNum = allNum + loadList2.size();
        log.info("加载【指令消息事件插件】插件完成，共{}个", loadList2.size());

        log.info("加载插件结束，共{}个", allNum);

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
            EventVo.EventMessageReceiveNormalVo eventVo = new EventVo.EventMessageReceiveNormalVo();
            eventVo.setOrder(0);
            if (order != null) {
                eventVo.setOrder(order.value());
            }
            eventVo.setPluginName(entry.getKey());
            eventVo.setBean(bean);
            list.add(eventVo);
        }
        Collections.sort(list, Comparator.comparingInt(EventVo.EventMessageReceiveNormalVo::getOrder));
        return list;
    }

    /**
     * 加载插件：指令消息事件
     *
     * @param applicationContext
     * @return
     */
    private List<EventVo.EventMessageReceiveInstructionVo> loadEventMessageReceiveInstruction(ApplicationContext applicationContext) {
        List<EventVo.EventMessageReceiveInstructionVo> list = new ArrayList<>();
        Map<String, EventMessageReceiveInstruction> beans = applicationContext.getBeansOfType(EventMessageReceiveInstruction.class);
        for (Map.Entry<String, EventMessageReceiveInstruction> entry : beans.entrySet()) {
            EventMessageReceiveInstruction bean = entry.getValue();
            Class<?> clazz = bean.getClass();
            Order order = AnnotationUtils.findAnnotation(clazz, Order.class);
            EventVo.EventMessageReceiveInstructionVo eventVo = new EventVo.EventMessageReceiveInstructionVo();
            eventVo.setOrder(0);
            if (order != null) {
                eventVo.setOrder(order.value());
            }
            eventVo.setPluginName(entry.getKey());
            eventVo.setBean(bean);
            list.add(eventVo);
        }
        Collections.sort(list, Comparator.comparingInt(EventVo.EventMessageReceiveInstructionVo::getOrder));
        return list;
    }


}
