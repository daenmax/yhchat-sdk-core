package cn.daenx.yhchatsdk.system.controller;


import cn.daenx.yhchatsdk.common.utils.ServletUtils;
import cn.daenx.yhchatsdk.common.vo.Result;
import cn.daenx.yhchatsdk.system.vo.EventMsgVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


}
