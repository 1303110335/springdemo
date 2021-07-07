/**
 * fshows.com
 * Copyright (C) 2013-2020 All Rights Reserved.
 */
package com.example.springdemo.test.threads.limiter;

/**
 * @author xuleyan
 * @version BlockingThreadPoolExecutor.java, v 0.1 2020-04-10 5:51 PM xuleyan
 */

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Slf4j
public class BlockingThreadPoolExecutor extends ThreadPoolExecutor {
    private final Semaphore semaphore;

    public BlockingThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
        semaphore = new Semaphore(corePoolSize + 50);
    }

    @Override
    protected void beforeExecute(Thread t, Runnable r) {
        super.beforeExecute(t, r);
    }

    @Override
    public void execute(final Runnable task) {
        boolean acquired = false;
        do {
            try {
                System.out.println("获取信号量" + task.toString());
                semaphore.acquire();
                acquired = true;
            } catch (final InterruptedException e) {
                log.warn("InterruptedException whilst aquiring semaphore", e);
            }
        } while (!acquired);
        try {
            super.execute(task);
        } catch (final RejectedExecutionException e) {
            System.out.println("Task Rejected");
            semaphore.release();
            throw e;
        }
    }

    @Override
    protected void afterExecute(Runnable r, Throwable t) {
        super.afterExecute(r, t);
        if (t != null) {
            t.printStackTrace();
        }
        System.out.println("释放信号量" + r.toString());
        semaphore.release();
    }
}