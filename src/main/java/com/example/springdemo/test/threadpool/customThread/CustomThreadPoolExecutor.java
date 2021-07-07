/**
 * fshows.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.example.springdemo.test.threadpool.customThread;

import org.apache.commons.lang3.time.DateFormatUtils;

import java.util.Date;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author xuleyan
 * @version CustomThreadPoolExecutor.java, v 0.1 2019-11-18 9:45 PM xuleyan
 */
public class CustomThreadPoolExecutor {

    private static final Integer TASK_NUM = 100;
    private ThreadPoolExecutor pool = null;
    private CountDownLatch countDownLatch = new CountDownLatch(TASK_NUM);

//    private CyclicBarrier cyclicBarrier = new CyclicBarrier(100, () -> {
//        System.out.println("处理完1000个任务，继续下一批任务");
//    });

    public static void main(String[] args) throws InterruptedException {
        CustomThreadPoolExecutor executor = new CustomThreadPoolExecutor();
//        int i = 2;
//        boolean b = executor.addWorker(i);


        executor.test();
    }

    public ExecutorService getCustomThreadPoolExecutor() {
        return this.pool;
    }

    private boolean addWorker(int i) throws InterruptedException {
        retry:
        for (; ; ) {
            TimeUnit.MILLISECONDS.sleep(100);
            System.out.println("第一层");
            if (i == 3) {
                System.out.println("第一层退出");
            }
            for (; ; ) {
                TimeUnit.MILLISECONDS.sleep(100);
                if (i == 0) {
                    break retry;
                }

                if (i == 1) {
                    continue retry;
                }

                if (i == 2) {
                    return false;
                }
            }
        }
        System.out.println("退出所有循环");
        return true;
    }

    private void test() throws InterruptedException {
        System.out.println("开始时间：" + DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
        long startTime = System.currentTimeMillis();

        RejectedExecutionHandler rejected = null;
        RejectedExecutionHandler rejected2 = null;
        RejectedExecutionHandler rejected3 = null;
        RejectedExecutionHandler rejected4 = null;
        // 默认，队列满了丢任务抛出异常
        rejected = new ThreadPoolExecutor.AbortPolicy();
        // 队列满了，丢任务不异常
        rejected2 = new ThreadPoolExecutor.DiscardPolicy();
        // 将最早进入队列的任务删除，之后再尝试加入队列
        rejected3 = new ThreadPoolExecutor.DiscardOldestPolicy();
        // 如果添加到线程池失败，那么主线程会自己去执行该任务
        rejected4 = new ThreadPoolExecutor.CallerRunsPolicy();


        // 1.初始化
        pool = new ThreadPoolExecutor(10, 10, 60, TimeUnit.MINUTES,
                new ArrayBlockingQueue<>(TASK_NUM), new CustomThreadFactory(),
                rejected4);


        for (int j = 0; j < 3; j++) {
            for (int i = 1; i <= TASK_NUM; i++) {
                System.out.println("提交第" + i + "个任务!");
                pool.execute(() -> {
                    System.out.println("task-" + Thread.currentThread().getName() + "正在执行task");

                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println("task-" + Thread.currentThread().getName() + "执行完毕");

                    countDownLatch.countDown();
                    System.out.println("countDownLatch剩余的count值:" + countDownLatch.getCount());
                });
            }
            countDownLatch.await();
            countDownLatch = new CountDownLatch(TASK_NUM);
            System.out.println("处理完100个任务，继续下一批任务");
        }


        // 2.销毁----此处不能销毁,因为任务没有提交执行完,如果销毁线程池,任务也就无法执行了


        Thread.sleep(1000);
        System.out.println("结束时间2：" + DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
        System.out.println("耗时时间：" + (System.currentTimeMillis() - startTime));

        pool.shutdown();
    }


}