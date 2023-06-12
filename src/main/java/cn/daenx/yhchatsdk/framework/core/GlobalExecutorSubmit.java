package cn.daenx.yhchatsdk.framework.core;

import cn.daenx.yhchatsdk.common.vo.EventMsgVo;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;

/**
 * 事件处理线程投递器
 */
@Slf4j
public class GlobalExecutorSubmit {
    public static void sub(EventMsgVo eventMsgVo) {
        ExecutorService executorService = GlobalThreadPool.getExecutorService();
        executorService.execute(new GlobalEventHandle(eventMsgVo));
    }
}
