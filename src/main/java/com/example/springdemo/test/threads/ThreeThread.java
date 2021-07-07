/**
 * fshows.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.example.springdemo.test.threads;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 新建线程的三种方式
 *
 * @author xuleyan
 * @version ThreeThread.java, v 0.1 2019-10-24 10:34 AM xuleyan
 */
public class ThreeThread {

    public static void main(String[] args) {

        // 1.继承Thread
        Thread thread = new Thread() {
            @Override
            public void run() {
                System.out.println("继承Thread");
                super.run();
            }
        };
        thread.start();

        // 实现runable接口
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("实现runable接口");
            }
        });
        thread1.start();

        // 实现callable接口
        ExecutorService service = Executors.newSingleThreadScheduledExecutor();
        Future<String> future = service.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "通过实现callable接口";
            }
        });

        try {
            String result = future.get();
            System.out.println(result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}