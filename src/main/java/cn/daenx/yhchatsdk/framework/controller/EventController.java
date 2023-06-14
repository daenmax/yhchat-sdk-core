package cn.daenx.yhchatsdk.framework.controller;


import cn.daenx.yhchatsdk.common.utils.ServletUtils;
import cn.daenx.yhchatsdk.common.vo.Result;
import cn.daenx.yhchatsdk.framework.core.GlobalExecutorSubmit;
import cn.daenx.yhchatsdk.framework.vo.EventMsgVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

/**
 * 云湖事件入口
 *
 * @author DaenMax
 */
@RestController
@RequestMapping("/event")
@Slf4j
public class EventController {
    @Value("${yhchat.printLog}")
    private Boolean printLog;

    /**
     * 接收云湖事件推送
     *
     * @return
     */
    @PostMapping("/msg")
    public Result msg(@RequestBody EventMsgVo eventMsgVo) {
        if (printLog) {
            log.info("【core】接收到来自IP[{}]的请求消息：{}，原始消息为{}", ServletUtils.getClientIP(), eventMsgVo.getHeader().getEventType(), eventMsgVo.toString());
        }
        GlobalExecutorSubmit.submit(eventMsgVo);
        return Result.ok();
    }

//    /**
//     * 接收云湖事件推送
//     *
//     * @return
//     */
//    @PostMapping("/msg")
//    public Result msg(@RequestBody String eventMsgVo) {
//        log.info(eventMsgVo);
//        return Result.ok();
//    }

}
