/**
 * fshows.com
 * Copyright (C) 2013-2020 All Rights Reserved.
 */
package com.example.springdemo.test.threads;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author xuleyan
 * @version RuntimeTest.java, v 0.1 2020-04-20 11:48 AM xuleyan
 */
public class RuntimeTest {

    public static void main(String[] args) {
        RuntimeTest runtimeTest = new RuntimeTest();
        runtimeTest.test();
    }

    private void test() {
        // 10万并发
        int count = 1000;

        CyclicBarrier cyclicBarrier = new CyclicBarrier(count);
        ExecutorService executorService = Executors.newFixedThreadPool(count);
        long now = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            executorService.execute(new Task(cyclicBarrier));
        }

        executorService.shutdown();
        while (!executorService.isTerminated()) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("All is finished!---------" + (end - now));
    }


    public class Task implements Runnable {
        private CyclicBarrier cyclicBarrier;

        public Task(CyclicBarrier cyclicBarrier) {
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {
            try {
                // 等待所有任务准备就绪
                cyclicBarrier.await();
                // 测试内容
                System.out.println("hello word");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}