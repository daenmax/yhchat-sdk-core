package cn.daenx.yhchatsdk.system.controller;


import cn.daenx.yhchatsdk.common.utils.ServletUtils;
import cn.daenx.yhchatsdk.common.vo.Result;
import cn.daenx.yhchatsdk.system.GlobalThreadPool;
import cn.daenx.yhchatsdk.system.vo.EventMsgVo;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutorService;

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

        ExecutorService executorService = GlobalThreadPool.getExecutorService();
        for (int i = 0; i < 10; i++) {
            executorService.execute(new MyTask("小明" + i));
        }
        return Result.ok();
    }

}
