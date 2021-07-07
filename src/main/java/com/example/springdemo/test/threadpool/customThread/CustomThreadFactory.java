/**
 * fshows.com
 * Copyright (C) 2013-2020 All Rights Reserved.
 */
package com.example.springdemo.test.threadpool.customThread;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author xuleyan
 * @version CustomThreadFactory.java, v 0.1 2020-04-22 3:35 PM xuleyan
 */
public class CustomThreadFactory implements ThreadFactory {

    private AtomicInteger count = new AtomicInteger(0);

    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(r);
        // CustomThreadPoolExecutor.class.getSimpleName()
        String threadName = "thread-" + count.addAndGet(1);
        t.setName(threadName);
        return t;
    }
}
