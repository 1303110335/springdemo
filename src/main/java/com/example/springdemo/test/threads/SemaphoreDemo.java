/**
 * fshows.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.example.springdemo.test.threads;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.Semaphore;

/**
 * @author xuleyan
 * @version SemaphoreDemo.java, v 0.1 2019-12-01 2:21 PM xuleyan
 */
public class SemaphoreDemo {

    private static Semaphore semaphore = new Semaphore(5);

    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        ExecutorService executorService = Executors.newFixedThreadPool(10);
//        for (int i =0; i < 10; i++) {
//            executorService.execute(() -> {
//                try {
//                    System.out.println(Thread.currentThread().getName() + " 同学准备获取笔......");
//                    semaphore.acquire();
//                    System.out.println(Thread.currentThread().getName() + " 同学拿到笔");
//                    System.out.println(Thread.currentThread().getName() + " 填写表格ing...");
//                    TimeUnit.SECONDS.sleep(3);
//                    semaphore.release();
//                    System.out.println(Thread.currentThread().getName() + "  填写完表格，归还了笔！！！！！！");
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            });
//        }
//        executorService.shutdown();


        //1. 创建线程池
        ExecutorService pool = Executors.newFixedThreadPool(5);

        List<Future<Integer>> list = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            Future<Integer> future = pool.submit(SemaphoreDemo::test);
            list.add(future);
        }

        pool.shutdown();

        for (Future<Integer> future : list) {
            System.out.println(future.get());
        }

    }

    private static Integer test() {
        int sum = 0;
        for (int i = 0; i <= 100; i++) {
            sum += i;
        }
        return sum;
    }
}