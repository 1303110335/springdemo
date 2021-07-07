/**
 * fshows.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.example.springdemo.test.threads.cyclicbarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 导游早上十点带着3位游客来到一景区，一共需要游览三个景点，分别为A、B、C,D为出口（终点）。现在所有人从A出发自有行，
 * 但是必须所有人上午11点在B景点集合完成后，再出发到C，最后13点在D出口处集合统一大巴去其他景区。请使用java多线程实现以上场景。
 *
 * @author xuleyan
 * @version CyclicBarrierDemo.java, v 0.1 2019-12-01 1:42 PM xuleyan
 */
public class CyclicBarrierDemo {

    // 指定必须有3个游客一起到达
    private static CyclicBarrier barrier = new CyclicBarrier(3, () -> {
        System.out.println("11点到达B景点集合,集合完毕后出发");
    });

    // 到达c景点
    private static CountDownLatch countDownLatch = new CountDownLatch(3);

    // 指定必须在13点在D出口处集合
    private static CyclicBarrier barrierEnd = new CyclicBarrier(3, () -> {
        System.out.println("13点在D出口集合统一大巴去其他景区");
    });

    public static void main(String[] args) {
        CyclicBarrierDemo cyclicBarrierDemo = new CyclicBarrierDemo();
        cyclicBarrierDemo.run();
    }

    private void run() {
        ExecutorService service = new ThreadPoolExecutor(3, 3,
                0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>(), new NameThreadFactory());
        System.out.println("现在从A出发自由行");
        for (int i = 0; i < 3; i++) {
            service.execute(() -> {
                try {
                    System.out.println(Thread.currentThread().getName() + " 11点到达B景点");
                    barrier.await();
                    System.out.println(Thread.currentThread().getName() + " 到达C景点");
                    countDownLatch.countDown();
                    countDownLatch.await();
                    System.out.println(Thread.currentThread().getName() + " 13点到D出口集合");
                    barrierEnd.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            });
        }
        service.shutdown();
    }

    private class NameThreadFactory implements ThreadFactory {
        private AtomicInteger count = new AtomicInteger(1);

        @Override
        public Thread newThread(Runnable r) {
            Thread thread = new Thread(r);
            String threadName = "游客-" + count.getAndIncrement();
            thread.setName(threadName);
            return thread;
        }
    }

}