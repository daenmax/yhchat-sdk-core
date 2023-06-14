package cn.daenx.yhchatsdk.framework.init;

import cn.daenx.yhchatsdk.common.utils.SpringUtil;
import cn.daenx.yhchatsdk.framework.eventInterface.*;
import cn.daenx.yhchatsdk.framework.vo.PluginManagerVo;
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
 *
 * @author DaenMax
 */
@Component
@Slf4j
public class ApplicationRunnerImpl implements ApplicationRunner {


    @Override
    public void run(ApplicationArguments args) {
        log.info("【core】加载插件开始...");
        int allNum = 0;
        ApplicationContext applicationContext = SpringUtil.getContext();
        GlobalPluginHandel globalData = GlobalPluginHandel.getInstance();

        //普通消息事件插件
        List<PluginManagerVo.EventMessageReceiveNormalVo> loadList1 = loadEventMessageReceiveNormal(applicationContext);
        List<PluginManagerVo.EventMessageReceiveNormalVo> dataList1 = globalData.getEventMessageReceiveNormalVos();
        dataList1.addAll(loadList1);
        allNum = allNum + loadList1.size();
        log.info("【core】加载[普通消息事件]插件完成，共{}个", loadList1.size());

        //指令消息事件插件
        List<PluginManagerVo.EventMessageReceiveInstructionVo> loadList2 = loadEventMessageReceiveInstruction(applicationContext);
        List<PluginManagerVo.EventMessageReceiveInstructionVo> dataList2 = globalData.getEventMessageReceiveInstructionVos();
        dataList2.addAll(loadList2);
        allNum = allNum + loadList2.size();
        log.info("【core】加载[指令消息事件]插件完成，共{}个", loadList2.size());

        //关注机器人事件
        List<PluginManagerVo.EventBotFollwedVo> loadList3 = loadEventBotFollwed(applicationContext);
        List<PluginManagerVo.EventBotFollwedVo> dataList3 = globalData.getEventBotFollwedVos();
        dataList3.addAll(loadList3);
        allNum = allNum + loadList3.size();
        log.info("【core】加载[关注机器人事件]插件完成，共{}个", loadList3.size());

        //取消关注机器人事件
        List<PluginManagerVo.EventBotUnfollwedVo> loadList4 = loadEventBotUnfollwed(applicationContext);
        List<PluginManagerVo.EventBotUnfollwedVo> dataList4 = globalData.getEventBotUnfollwedVos();
        dataList4.addAll(loadList4);
        allNum = allNum + loadList4.size();
        log.info("【core】加载[取消关注机器人事件]插件完成，共{}个", loadList4.size());

        //消息中按钮点击事件
        List<PluginManagerVo.EventButtonReportInlineVo> loadList5 = loadEventButtonReportInline(applicationContext);
        List<PluginManagerVo.EventButtonReportInlineVo> dataList5 = globalData.getEventButtonReportInlineVos();
        dataList5.addAll(loadList5);
        allNum = allNum + loadList5.size();
        log.info("【core】加载[消息中按钮点击事件]插件完成，共{}个", loadList5.size());

        //加入群事件
        List<PluginManagerVo.EventGroupJoinVo> loadList6 = loadEventGroupJoin(applicationContext);
        List<PluginManagerVo.EventGroupJoinVo> dataList6 = globalData.getEventGroupJoinVos();
        dataList6.addAll(loadList6);
        allNum = allNum + loadList6.size();
        log.info("【core】加载[加入群事件]插件完成，共{}个", loadList6.size());

        //退出群事件
        List<PluginManagerVo.EventGroupLeaveVo> loadList7 = loadEventGroupLeave(applicationContext);
        List<PluginManagerVo.EventGroupLeaveVo> dataList7 = globalData.getEventGroupLeaveVos();
        dataList7.addAll(loadList7);
        allNum = allNum + loadList7.size();
        log.info("【core】加载[退出群事件]插件完成，共{}个", loadList7.size());

        //机器人设置事件
        List<PluginManagerVo.EventBotSettingVo> loadList8 = loadEventBotSetting(applicationContext);
        List<PluginManagerVo.EventBotSettingVo> dataList8 = globalData.getEventBotSettingVos();
        dataList8.addAll(loadList8);
        allNum = allNum + loadList8.size();
        log.info("【core】加载[机器人设置事件]插件完成，共{}个", loadList8.size());

        log.info("【core】加载插件结束，共{}个", allNum);
        log.info("【core】核心启动完成，欢迎使用");

        log.info("\n" +
                " __     ___      _____ _           _       _____                  ____           _____                   __  __            \n" +
                " \\ \\   / / |    / ____| |         | |     / ____|                |  _ \\         |  __ \\                 |  \\/  |           \n" +
                "  \\ \\_/ /| |__ | |    | |__   __ _| |_   | |     ___  _ __ ___   | |_) |_   _   | |  | | __ _  ___ _ __ | \\  / | __ ___  __\n" +
                "   \\   / | '_ \\| |    | '_ \\ / _` | __|  | |    / _ \\| '__/ _ \\  |  _ <| | | |  | |  | |/ _` |/ _ \\ '_ \\| |\\/| |/ _` \\ \\/ /\n" +
                "    | |  | | | | |____| | | | (_| | |_   | |___| (_) | | |  __/  | |_) | |_| |  | |__| | (_| |  __/ | | | |  | | (_| |>  < \n" +
                "    |_|  |_| |_|\\_____|_| |_|\\__,_|\\__|   \\_____\\___/|_|  \\___|  |____/ \\__, |  |_____/ \\__,_|\\___|_| |_|_|  |_|\\__,_/_/\\_\\\n" +
                "                                                                         __/ |                                             \n" +
                "                                                                        |___/                                              \n");

    }

    /**
     * 加载插件：普通消息事件
     *
     * @param applicationContext
     * @return
     */
    private List<PluginManagerVo.EventMessageReceiveNormalVo> loadEventMessageReceiveNormal(ApplicationContext applicationContext) {
        List<PluginManagerVo.EventMessageReceiveNormalVo> list = new ArrayList<>();
        Map<String, EventMessageReceiveNormal> beans = applicationContext.getBeansOfType(EventMessageReceiveNormal.class);
        for (Map.Entry<String, EventMessageReceiveNormal> entry : beans.entrySet()) {
            EventMessageReceiveNormal bean = entry.getValue();
            Class<?> clazz = bean.getClass();
            Order order = AnnotationUtils.findAnnotation(clazz, Order.class);
            PluginManagerVo.EventMessageReceiveNormalVo eventVo = new PluginManagerVo.EventMessageReceiveNormalVo();
            eventVo.setOrder(0);
            if (order != null) {
                eventVo.setOrder(order.value());
            }
            eventVo.setPluginName(entry.getKey());
            eventVo.setBean(bean);
            list.add(eventVo);
        }
        Collections.sort(list, Comparator.comparingInt(PluginManagerVo.EventMessageReceiveNormalVo::getOrder));
        return list;
    }

    /**
     * 加载插件：指令消息事件
     *
     * @param applicationContext
     * @return
     */
    private List<PluginManagerVo.EventMessageReceiveInstructionVo> loadEventMessageReceiveInstruction(ApplicationContext applicationContext) {
        List<PluginManagerVo.EventMessageReceiveInstructionVo> list = new ArrayList<>();
        Map<String, EventMessageReceiveInstruction> beans = applicationContext.getBeansOfType(EventMessageReceiveInstruction.class);
        for (Map.Entry<String, EventMessageReceiveInstruction> entry : beans.entrySet()) {
            EventMessageReceiveInstruction bean = entry.getValue();
            Class<?> clazz = bean.getClass();
            Order order = AnnotationUtils.findAnnotation(clazz, Order.class);
            PluginManagerVo.EventMessageReceiveInstructionVo eventVo = new PluginManagerVo.EventMessageReceiveInstructionVo();
            eventVo.setOrder(0);
            if (order != null) {
                eventVo.setOrder(order.value());
            }
            eventVo.setPluginName(entry.getKey());
            eventVo.setBean(bean);
            list.add(eventVo);
        }
        Collections.sort(list, Comparator.comparingInt(PluginManagerVo.EventMessageReceiveInstructionVo::getOrder));
        return list;
    }

    /**
     * 加载插件：关注机器人事件
     *
     * @param applicationContext
     * @return
     */
    private List<PluginManagerVo.EventBotFollwedVo> loadEventBotFollwed(ApplicationContext applicationContext) {
        List<PluginManagerVo.EventBotFollwedVo> list = new ArrayList<>();
        Map<String, EventBotFollwed> beans = applicationContext.getBeansOfType(EventBotFollwed.class);
        for (Map.Entry<String, EventBotFollwed> entry : beans.entrySet()) {
            EventBotFollwed bean = entry.getValue();
            Class<?> clazz = bean.getClass();
            Order order = AnnotationUtils.findAnnotation(clazz, Order.class);
            PluginManagerVo.EventBotFollwedVo eventVo = new PluginManagerVo.EventBotFollwedVo();
            eventVo.setOrder(0);
            if (order != null) {
                eventVo.setOrder(order.value());
            }
            eventVo.setPluginName(entry.getKey());
            eventVo.setBean(bean);
            list.add(eventVo);
        }
        Collections.sort(list, Comparator.comparingInt(PluginManagerVo.EventBotFollwedVo::getOrder));
        return list;
    }

    /**
     * 加载插件：取消关注机器人事件
     *
     * @param applicationContext
     * @return
     */
    private List<PluginManagerVo.EventBotUnfollwedVo> loadEventBotUnfollwed(ApplicationContext applicationContext) {
        List<PluginManagerVo.EventBotUnfollwedVo> list = new ArrayList<>();
        Map<String, EventBotUnfollwed> beans = applicationContext.getBeansOfType(EventBotUnfollwed.class);
        for (Map.Entry<String, EventBotUnfollwed> entry : beans.entrySet()) {
            EventBotUnfollwed bean = entry.getValue();
            Class<?> clazz = bean.getClass();
            Order order = AnnotationUtils.findAnnotation(clazz, Order.class);
            PluginManagerVo.EventBotUnfollwedVo eventVo = new PluginManagerVo.EventBotUnfollwedVo();
            eventVo.setOrder(0);
            if (order != null) {
                eventVo.setOrder(order.value());
            }
            eventVo.setPluginName(entry.getKey());
            eventVo.setBean(bean);
            list.add(eventVo);
        }
        Collections.sort(list, Comparator.comparingInt(PluginManagerVo.EventBotUnfollwedVo::getOrder));
        return list;
    }

    /**
     * 加载插件：消息中按钮点击事件
     *
     * @param applicationContext
     * @return
     */
    private List<PluginManagerVo.EventButtonReportInlineVo> loadEventButtonReportInline(ApplicationContext applicationContext) {
        List<PluginManagerVo.EventButtonReportInlineVo> list = new ArrayList<>();
        Map<String, EventButtonReportInline> beans = applicationContext.getBeansOfType(EventButtonReportInline.class);
        for (Map.Entry<String, EventButtonReportInline> entry : beans.entrySet()) {
            EventButtonReportInline bean = entry.getValue();
            Class<?> clazz = bean.getClass();
            Order order = AnnotationUtils.findAnnotation(clazz, Order.class);
            PluginManagerVo.EventButtonReportInlineVo eventVo = new PluginManagerVo.EventButtonReportInlineVo();
            eventVo.setOrder(0);
            if (order != null) {
                eventVo.setOrder(order.value());
            }
            eventVo.setPluginName(entry.getKey());
            eventVo.setBean(bean);
            list.add(eventVo);
        }
        Collections.sort(list, Comparator.comparingInt(PluginManagerVo.EventButtonReportInlineVo::getOrder));
        return list;
    }

    /**
     * 加载插件：加入群事件
     *
     * @param applicationContext
     * @return
     */
    private List<PluginManagerVo.EventGroupJoinVo> loadEventGroupJoin(ApplicationContext applicationContext) {
        List<PluginManagerVo.EventGroupJoinVo> list = new ArrayList<>();
        Map<String, EventGroupJoin> beans = applicationContext.getBeansOfType(EventGroupJoin.class);
        for (Map.Entry<String, EventGroupJoin> entry : beans.entrySet()) {
            EventGroupJoin bean = entry.getValue();
            Class<?> clazz = bean.getClass();
            Order order = AnnotationUtils.findAnnotation(clazz, Order.class);
            PluginManagerVo.EventGroupJoinVo eventVo = new PluginManagerVo.EventGroupJoinVo();
            eventVo.setOrder(0);
            if (order != null) {
                eventVo.setOrder(order.value());
            }
            eventVo.setPluginName(entry.getKey());
            eventVo.setBean(bean);
            list.add(eventVo);
        }
        Collections.sort(list, Comparator.comparingInt(PluginManagerVo.EventGroupJoinVo::getOrder));
        return list;
    }

    /**
     * 加载插件：退出群事件
     *
     * @param applicationContext
     * @return
     */
    private List<PluginManagerVo.EventGroupLeaveVo> loadEventGroupLeave(ApplicationContext applicationContext) {
        List<PluginManagerVo.EventGroupLeaveVo> list = new ArrayList<>();
        Map<String, EventGroupLeave> beans = applicationContext.getBeansOfType(EventGroupLeave.class);
        for (Map.Entry<String, EventGroupLeave> entry : beans.entrySet()) {
            EventGroupLeave bean = entry.getValue();
            Class<?> clazz = bean.getClass();
            Order order = AnnotationUtils.findAnnotation(clazz, Order.class);
            PluginManagerVo.EventGroupLeaveVo eventVo = new PluginManagerVo.EventGroupLeaveVo();
            eventVo.setOrder(0);
            if (order != null) {
                eventVo.setOrder(order.value());
            }
            eventVo.setPluginName(entry.getKey());
            eventVo.setBean(bean);
            list.add(eventVo);
        }
        Collections.sort(list, Comparator.comparingInt(PluginManagerVo.EventGroupLeaveVo::getOrder));
        return list;
    }

    /**
     * 加载插件：机器人设置事件
     *
     * @param applicationContext
     * @return
     */
    private List<PluginManagerVo.EventBotSettingVo> loadEventBotSetting(ApplicationContext applicationContext) {
        List<PluginManagerVo.EventBotSettingVo> list = new ArrayList<>();
        Map<String, EventBotSetting> beans = applicationContext.getBeansOfType(EventBotSetting.class);
        for (Map.Entry<String, EventBotSetting> entry : beans.entrySet()) {
            EventBotSetting bean = entry.getValue();
            Class<?> clazz = bean.getClass();
            Order order = AnnotationUtils.findAnnotation(clazz, Order.class);
            PluginManagerVo.EventBotSettingVo eventVo = new PluginManagerVo.EventBotSettingVo();
            eventVo.setOrder(0);
            if (order != null) {
                eventVo.setOrder(order.value());
            }
            eventVo.setPluginName(entry.getKey());
            eventVo.setBean(bean);
            list.add(eventVo);
        }
        Collections.sort(list, Comparator.comparingInt(PluginManagerVo.EventBotSettingVo::getOrder));
        return list;
    }


}
