package cn.daenx.yhchatsdk.framework.core;

import cn.hutool.core.thread.ExecutorBuilder;

import java.util.concurrent.ExecutorService;

public class GlobalThreadPool {

    private static final ExecutorService EXECUTOR_SERVICE = ExecutorBuilder.create().build();

    public static ExecutorService getExecutorService() {
        return EXECUTOR_SERVICE;
    }

}
