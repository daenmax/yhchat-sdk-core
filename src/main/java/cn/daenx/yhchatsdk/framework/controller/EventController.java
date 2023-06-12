package cn.daenx.yhchatsdk.framework.controller;


import cn.daenx.yhchatsdk.common.utils.ServletUtils;
import cn.daenx.yhchatsdk.common.vo.Result;
import cn.daenx.yhchatsdk.framework.core.GlobalEventHandle;
import cn.daenx.yhchatsdk.common.vo.EventMsgVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * 云湖事件
 */
@RestController
@RequestMapping("/event")
@Slf4j
public class EventController {

//    /**
//     * 接收云湖事件推送
//     *
//     * @return
//     */
//    @PostMapping("/msg")
//    public Result msg(@RequestBody EventMsgVo eventMsgVo) {
//        String clientIP = ServletUtils.getClientIP();
//        log.info("接收到来自IP[{}]的请求消息：{}", clientIP, eventMsgVo.toString());
//        return Result.ok();
//    }

    /**
     * 接收云湖事件推送
     *
     * @return
     */
    @PostMapping("/msg")
    public Result msg(@RequestBody String eventMsgVo) {
        String clientIP = ServletUtils.getClientIP();
        log.info("接收到来自IP[{}]的请求消息：{}", clientIP, eventMsgVo);
        return Result.ok();
    }

    @GetMapping("/test")
    public Result test() {
        EventMsgVo eventMsgVo = new EventMsgVo();
        eventMsgVo.setVersion("1.0.0");
        GlobalEventHandle.eventMessageReceiveNormal(eventMsgVo);
        return Result.ok();
    }

}
