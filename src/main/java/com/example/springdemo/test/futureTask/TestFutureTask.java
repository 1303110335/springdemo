/**
 * fshows.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.example.springdemo.test.futureTask;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author xuleyan
 * @version TestFutureTask.java, v 0.1 2019-11-24 9:26 PM xuleyan
 */
public class TestFutureTask {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        Callable<Integer> call = new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                System.out.println("正在计算结果...");
                Thread.sleep(3000);
                return 1;
            }
        };

        FutureTask<Integer> task = new FutureTask<>(call);
        task.run();
//        Thread thread = new Thread(task);
//        thread.start();
        System.out.println(task.isDone());
        System.out.println("干点别的...");

        Integer result = task.get();
        System.out.println("拿到的结果为：" + result);
    }
}