/**
 * fshows.com
 * Copyright (C) 2013-2020 All Rights Reserved.
 */
package com.example.springdemo.test.threads.cyclicbarrier;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author xuleyan
 * @version CurrentTest.java, v 0.1 2020-04-26 9:46 PM xuleyan
 */
public class CurrentTest {

    //定义CyclicBarrier 的屏障，需要等多少个线程到了才发起请求
    CyclicBarrier cyclicBarrier = new CyclicBarrier(200);

    public static void main(String[] args) {
        CurrentTest currentChatTest = new CurrentTest();
        currentChatTest.runThread();
    }

    private void runThread() {
        //定义线程池
        ExecutorService executorService = Executors.newFixedThreadPool(400);
        //执行线程
        for (int i = 0; i < 400; i++) {
            executorService.submit(buildThread(i));
        }
    }

    private Thread buildThread(int i) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("Thread：" + Thread.currentThread().getName() + "准备...");
                    //cyclicBarrier一定要等到满200个线程到了才往后执行
                    cyclicBarrier.await();
                    System.out.println("Thread：" + Thread.currentThread().getName() + "开始执行");
                    //do something

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        thread.setName("name:" + i);
        return thread;
    }
}