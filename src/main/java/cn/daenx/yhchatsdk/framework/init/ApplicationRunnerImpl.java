package cn.daenx.yhchatsdk.framework.init;

import cn.daenx.yhchatsdk.common.utils.SpringUtil;
import cn.daenx.yhchatsdk.framework.eventInterface.*;
import cn.daenx.yhchatsdk.framework.vo.EventVo;
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

        //关注机器人事件
        List<EventVo.EventBotFollwedVo> loadList3 = loadEventBotFollwed(applicationContext);
        List<EventVo.EventBotFollwedVo> dataList3 = globalData.getEventBotFollwedVos();
        dataList3.addAll(loadList3);
        allNum = allNum + loadList3.size();
        log.info("加载【关注机器人事件】插件完成，共{}个", loadList3.size());

        //取消关注机器人事件
        List<EventVo.EventBotUnfollwedVo> loadList4 = loadEventBotUnfollwed(applicationContext);
        List<EventVo.EventBotUnfollwedVo> dataList4 = globalData.getEventBotUnfollwedVos();
        dataList4.addAll(loadList4);
        allNum = allNum + loadList4.size();
        log.info("加载【取消关注机器人事件】插件完成，共{}个", loadList4.size());

        //消息中按钮点击事件
        List<EventVo.EventButtonReportInlineVo> loadList5 = loadEventButtonReportInline(applicationContext);
        List<EventVo.EventButtonReportInlineVo> dataList5 = globalData.getEventButtonReportInlineVos();
        dataList5.addAll(loadList5);
        allNum = allNum + loadList5.size();
        log.info("加载【消息中按钮点击事件】插件完成，共{}个", loadList5.size());

        //加入群事件
        List<EventVo.EventGroupJoinVo> loadList6 = loadEventGroupJoin(applicationContext);
        List<EventVo.EventGroupJoinVo> dataList6 = globalData.getEventGroupJoinVos();
        dataList6.addAll(loadList6);
        allNum = allNum + loadList6.size();
        log.info("加载【加入群事件】插件完成，共{}个", loadList6.size());

        //退出群事件
        List<EventVo.EventGroupLeaveVo> loadList7 = loadEventGroupLeave(applicationContext);
        List<EventVo.EventGroupLeaveVo> dataList7 = globalData.getEventGroupLeaveVos();
        dataList7.addAll(loadList7);
        allNum = allNum + loadList7.size();
        log.info("加载【退出群事件】插件完成，共{}个", loadList7.size());

        //机器人设置事件
        List<EventVo.EventBotSettingVo> loadList8 = loadEventBotSetting(applicationContext);
        List<EventVo.EventBotSettingVo> dataList8 = globalData.getEventBotSettingVos();
        dataList8.addAll(loadList8);
        allNum = allNum + loadList8.size();
        log.info("加载【机器人设置事件】插件完成，共{}个", loadList8.size());

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

    /**
     * 加载插件：关注机器人事件
     *
     * @param applicationContext
     * @return
     */
    private List<EventVo.EventBotFollwedVo> loadEventBotFollwed(ApplicationContext applicationContext) {
        List<EventVo.EventBotFollwedVo> list = new ArrayList<>();
        Map<String, EventBotFollwed> beans = applicationContext.getBeansOfType(EventBotFollwed.class);
        for (Map.Entry<String, EventBotFollwed> entry : beans.entrySet()) {
            EventBotFollwed bean = entry.getValue();
            Class<?> clazz = bean.getClass();
            Order order = AnnotationUtils.findAnnotation(clazz, Order.class);
            EventVo.EventBotFollwedVo eventVo = new EventVo.EventBotFollwedVo();
            eventVo.setOrder(0);
            if (order != null) {
                eventVo.setOrder(order.value());
            }
            eventVo.setPluginName(entry.getKey());
            eventVo.setBean(bean);
            list.add(eventVo);
        }
        Collections.sort(list, Comparator.comparingInt(EventVo.EventBotFollwedVo::getOrder));
        return list;
    }

    /**
     * 加载插件：取消关注机器人事件
     *
     * @param applicationContext
     * @return
     */
    private List<EventVo.EventBotUnfollwedVo> loadEventBotUnfollwed(ApplicationContext applicationContext) {
        List<EventVo.EventBotUnfollwedVo> list = new ArrayList<>();
        Map<String, EventBotUnfollwed> beans = applicationContext.getBeansOfType(EventBotUnfollwed.class);
        for (Map.Entry<String, EventBotUnfollwed> entry : beans.entrySet()) {
            EventBotUnfollwed bean = entry.getValue();
            Class<?> clazz = bean.getClass();
            Order order = AnnotationUtils.findAnnotation(clazz, Order.class);
            EventVo.EventBotUnfollwedVo eventVo = new EventVo.EventBotUnfollwedVo();
            eventVo.setOrder(0);
            if (order != null) {
                eventVo.setOrder(order.value());
            }
            eventVo.setPluginName(entry.getKey());
            eventVo.setBean(bean);
            list.add(eventVo);
        }
        Collections.sort(list, Comparator.comparingInt(EventVo.EventBotUnfollwedVo::getOrder));
        return list;
    }

    /**
     * 加载插件：消息中按钮点击事件
     *
     * @param applicationContext
     * @return
     */
    private List<EventVo.EventButtonReportInlineVo> loadEventButtonReportInline(ApplicationContext applicationContext) {
        List<EventVo.EventButtonReportInlineVo> list = new ArrayList<>();
        Map<String, EventButtonReportInline> beans = applicationContext.getBeansOfType(EventButtonReportInline.class);
        for (Map.Entry<String, EventButtonReportInline> entry : beans.entrySet()) {
            EventButtonReportInline bean = entry.getValue();
            Class<?> clazz = bean.getClass();
            Order order = AnnotationUtils.findAnnotation(clazz, Order.class);
            EventVo.EventButtonReportInlineVo eventVo = new EventVo.EventButtonReportInlineVo();
            eventVo.setOrder(0);
            if (order != null) {
                eventVo.setOrder(order.value());
            }
            eventVo.setPluginName(entry.getKey());
            eventVo.setBean(bean);
            list.add(eventVo);
        }
        Collections.sort(list, Comparator.comparingInt(EventVo.EventButtonReportInlineVo::getOrder));
        return list;
    }

    /**
     * 加载插件：加入群事件
     *
     * @param applicationContext
     * @return
     */
    private List<EventVo.EventGroupJoinVo> loadEventGroupJoin(ApplicationContext applicationContext) {
        List<EventVo.EventGroupJoinVo> list = new ArrayList<>();
        Map<String, EventGroupJoin> beans = applicationContext.getBeansOfType(EventGroupJoin.class);
        for (Map.Entry<String, EventGroupJoin> entry : beans.entrySet()) {
            EventGroupJoin bean = entry.getValue();
            Class<?> clazz = bean.getClass();
            Order order = AnnotationUtils.findAnnotation(clazz, Order.class);
            EventVo.EventGroupJoinVo eventVo = new EventVo.EventGroupJoinVo();
            eventVo.setOrder(0);
            if (order != null) {
                eventVo.setOrder(order.value());
            }
            eventVo.setPluginName(entry.getKey());
            eventVo.setBean(bean);
            list.add(eventVo);
        }
        Collections.sort(list, Comparator.comparingInt(EventVo.EventGroupJoinVo::getOrder));
        return list;
    }

    /**
     * 加载插件：退出群事件
     *
     * @param applicationContext
     * @return
     */
    private List<EventVo.EventGroupLeaveVo> loadEventGroupLeave(ApplicationContext applicationContext) {
        List<EventVo.EventGroupLeaveVo> list = new ArrayList<>();
        Map<String, EventGroupLeave> beans = applicationContext.getBeansOfType(EventGroupLeave.class);
        for (Map.Entry<String, EventGroupLeave> entry : beans.entrySet()) {
            EventGroupLeave bean = entry.getValue();
            Class<?> clazz = bean.getClass();
            Order order = AnnotationUtils.findAnnotation(clazz, Order.class);
            EventVo.EventGroupLeaveVo eventVo = new EventVo.EventGroupLeaveVo();
            eventVo.setOrder(0);
            if (order != null) {
                eventVo.setOrder(order.value());
            }
            eventVo.setPluginName(entry.getKey());
            eventVo.setBean(bean);
            list.add(eventVo);
        }
        Collections.sort(list, Comparator.comparingInt(EventVo.EventGroupLeaveVo::getOrder));
        return list;
    }

    /**
     * 加载插件：机器人设置事件
     *
     * @param applicationContext
     * @return
     */
    private List<EventVo.EventBotSettingVo> loadEventBotSetting(ApplicationContext applicationContext) {
        List<EventVo.EventBotSettingVo> list = new ArrayList<>();
        Map<String, EventBotSetting> beans = applicationContext.getBeansOfType(EventBotSetting.class);
        for (Map.Entry<String, EventBotSetting> entry : beans.entrySet()) {
            EventBotSetting bean = entry.getValue();
            Class<?> clazz = bean.getClass();
            Order order = AnnotationUtils.findAnnotation(clazz, Order.class);
            EventVo.EventBotSettingVo eventVo = new EventVo.EventBotSettingVo();
            eventVo.setOrder(0);
            if (order != null) {
                eventVo.setOrder(order.value());
            }
            eventVo.setPluginName(entry.getKey());
            eventVo.setBean(bean);
            list.add(eventVo);
        }
        Collections.sort(list, Comparator.comparingInt(EventVo.EventBotSettingVo::getOrder));
        return list;
    }


}
