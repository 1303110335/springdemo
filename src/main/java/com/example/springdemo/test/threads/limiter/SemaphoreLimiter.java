/**
 * fshows.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.example.springdemo.test.threads.limiter;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @author xuleyan
 * @version SemaphoreLimiter.java, v 0.1 2019-12-09 4:15 PM xuleyan
 */
public class SemaphoreLimiter {

    private final Semaphore permit = new Semaphore(10, true);

    public static void main(String[] args) {
        Executor executors = Executors.newFixedThreadPool(50);
        SemaphoreLimiter sl = new SemaphoreLimiter();

        for (int i = 0; i < 1000; i++) {
            final int num = i;
            executors.execute(new Runnable() {
                @Override
                public void run() {
//                    sl.process(num);
                    sl.processTry(num);
                }
            });
        }
    }

    public void process(int i) {
        try {
            permit.acquire();//阻塞的方式
            System.out.println("处理逻辑" + i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void processTry(int i) {
        boolean acquired = permit.tryAcquire();

        if (acquired) {
            try {
                System.out.println("处理业务逻辑" + i);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                permit.release();
                System.out.println("释放信号量");
            }
        } else {
            System.out.println("降级处理");
        }
    }
}