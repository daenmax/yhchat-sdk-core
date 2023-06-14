package cn.daenx.yhchatsdk.framework.core;

import cn.hutool.core.thread.ExecutorBuilder;

import java.util.concurrent.ExecutorService;

/**
 * 线程池创建
 *
 * @author DaenMax
 */
public class GlobalThreadPool {
    //这里使用的是默认线程池，如需更改，请参考 https://doc.hutool.cn/pages/ExecutorBuilder/
    private static final ExecutorService EXECUTOR_SERVICE = ExecutorBuilder.create().build();

    public static ExecutorService getExecutorService() {
        return EXECUTOR_SERVICE;
    }

}
