/**
 * fshows.com
 * Copyright (C) 2013-2020 All Rights Reserved.
 */
package com.example.springdemo.test.threads.limiter;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author xuleyan
 * @version ConfineManager.java, v 0.1 2020-04-10 5:35 PM xuleyan
 */
public class ConfineManager {

    // 定时线程
    private final ScheduledThreadPoolExecutor scheduledCheck = new ScheduledThreadPoolExecutor(2);
    // 执行补充线程池
    private final ExecutorService executorService = new ThreadPoolExecutor(5, 200,
            60L, TimeUnit.SECONDS, new SynchronousQueue<>(),
            new NameThreadFactory());

    // 限流器容器
    private Map<String, RateLimiter> rateLimiterMap = new ConcurrentHashMap<>();

    @PostConstruct
    public void init() {
        scheduledCheck.scheduleAtFixedRate(new SupplementRateLimiter(), 1, 1, TimeUnit.SECONDS);
    }

    @PreDestroy
    public void destroy() {
        scheduledCheck.shutdown();
    }

    /**
     * 通过key获取相应的限流器
     */
    public void acquire(String key, int tokenCount) {
        RateLimiter rateLimiter = rateLimiterMap.get(key);
        // 双检锁确保安全创建
        if (rateLimiter == null) {
            synchronized (this) {
                // init RateLimiter
                rateLimiter = rateLimiterMap.computeIfAbsent(key, k -> new RateLimiter(tokenCount));
            }
        }
        // 尝试获取令牌
        if (!rateLimiter.acquire()) {
            // 获取失败，根据实际情况进行处理，这里直接抛异常了
            //Assert.throwBizException(ErrorCode.API_CONFINE_RATE_LIMITER);
            System.out.println("异常了");
        }
    }

    /**
     * 补充相应的令牌数
     */
    private class SupplementRateLimiter implements Runnable {
        @Override
        public void run() {
            rateLimiterMap.values().forEach(rateLimiter -> rateLimiter.supplement(executorService));
        }
    }


    private class NameThreadFactory implements ThreadFactory {
        private AtomicInteger count = new AtomicInteger(1);

        @Override
        public Thread newThread(Runnable r) {
            Thread thread = new Thread(r);
            String threadName = "游客-" + count.getAndIncrement();
            thread.setName(threadName);
            return thread;
        }
    }
}