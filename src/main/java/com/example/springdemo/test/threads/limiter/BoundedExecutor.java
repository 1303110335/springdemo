/**
 * fshows.com
 * Copyright (C) 2013-2020 All Rights Reserved.
 */
package com.example.springdemo.test.threads.limiter;

/**
 * @author xuleyan
 * @version BoundedExecutor.java, v 0.1 2020-04-10 6:05 PM xuleyan
 */

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Slf4j
public class BoundedExecutor {

    private final ExecutorService executor;
    private final Semaphore semaphore;

    public BoundedExecutor(ExecutorService executor, int bound) {
        this.executor = executor;
        this.semaphore = new Semaphore(bound);
    }

    public static void main(String[] args) {
        ExecutorService executorService = new ThreadPoolExecutor(2, 2, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>(5));
        BoundedExecutor executor = new BoundedExecutor(executorService, 5);
        for (int i = 0; i < 10000; i++) {
            executor.submitTask(new MyThread("" + i));
        }
        executor.stop();
    }

    public void submitTask(final Runnable command) {
        try {
            MyThread myThread = (MyThread) command;
            System.out.println("获取令牌：" + myThread.getName());
            semaphore.acquire();
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        command.run();
                    } finally {
                        System.out.println("释放令牌：" + myThread.getName());
                        semaphore.release();
                    }
                }
            });
        } catch (InterruptedException e) {
            log.warn("异常" + ExceptionUtils.getMessage(e));
            semaphore.release();
        }
    }

    public void stop() {
        this.executor.shutdown();
    }

    static class MyThread extends Thread {
        public String name;

        public MyThread(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            System.out.println("Thread-" + name + " is running....");
//            try {
//                Thread.sleep(new Random().nextInt(10000));
//            }catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        }
    }

}