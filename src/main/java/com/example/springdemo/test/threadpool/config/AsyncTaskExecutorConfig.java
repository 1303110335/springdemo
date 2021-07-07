/**
 * fshows.com
 * Copyright (C) 2013-2020 All Rights Reserved.
 */
package com.example.springdemo.test.threadpool.config;

import com.fshows.fsframework.core.utils.LogUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

/**
 * @author xuleyan
 * @version AsyncTaskExecutorConfig.java, v 0.1 2020-04-08 5:54 PM xuleyan
 */
@Component
@Slf4j
public class AsyncTaskExecutorConfig {

    @Bean("testVoice")
    public AsyncTaskExecutor callBackVoiceExecutor() {
        String threadPoolName = "callBackVoiceExecutor";

        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setThreadNamePrefix("CallBackVoice-");
        taskExecutor.setCorePoolSize(5);
        taskExecutor.setMaxPoolSize(10);
        taskExecutor.setKeepAliveSeconds(10);
        // 设置等待队列
        taskExecutor.setQueueCapacity(10);
        taskExecutor.setRejectedExecutionHandler((r, executor) -> LogUtil.warn(log, "【异步线程池】等待队列超过最大长度限制，拒绝任务！ 线程池名称={}，task={}", threadPoolName, r.toString()));
        return taskExecutor;
    }
}