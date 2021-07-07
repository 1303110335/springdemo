/**
 * fshows.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.example.springdemo.test.threads;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author xuleyan
 * @version InterruptDemo.java, v 0.1 2019-10-24 10:53 AM xuleyan
 */
public class InterruptDemo {

    public static void main(String[] args) throws InterruptedException {
        //sleepThread睡眠1000ms
        final Thread sleepThread = new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                super.run();
            }
        };
        //busyThread一直执行死循环
        Thread busyThread = new Thread() {
            @Override
            public void run() {
                while (true) {
                    ;
                }
            }
        };
        sleepThread.start();
        busyThread.start();
        sleepThread.interrupt();
        busyThread.interrupt();
        while (sleepThread.isInterrupted()) {
            System.out.println(1111);
            ;
        }
        System.out.println("sleepThread isInterrupted: " + sleepThread.isInterrupted());
        System.out.println("busyThread isInterrupted: " + busyThread.isInterrupted());
    }

    /**
     * @author xuleyan
     * @version CountDownLatchDemo.java, v 0.1 2019-12-01 1:01 PM xuleyan
     */
    public static class CountDownLatchDemo {

        private static CountDownLatch startSignal = new CountDownLatch(1);
        //用来表示裁判员需要维护的是6个运动员
        private static CountDownLatch endSignal = new CountDownLatch(6);

        public static void main(String[] args) throws InterruptedException {
            //        ExecutorService executorService = Executors.newFixedThreadPool(6);

            ExecutorService executorService = new ThreadPoolExecutor(3, 3, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingDeque<Runnable>());
            for (int i = 0; i < 3; i++) {
                executorService.execute(() -> {
                    try {
                        System.out.println(Thread.currentThread().getName() + "现在出发！");
                        startSignal.await();
                        System.out.println(Thread.currentThread().getName() + "正在全力冲刺!");
                        endSignal.countDown();
                        System.out.println(Thread.currentThread().getName() + "到达终点");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
            }
            System.out.println("裁判员发号施令啦！！！");
            startSignal.countDown();
            Thread.sleep(1000);
            endSignal.await();
            System.out.println("所有运动员到达终点，比赛结束");
            executorService.shutdown();
        }
    }
}