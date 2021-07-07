/**
 * fshows.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.example.springdemo.test.queue;


import java.util.Date;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * -verbose:gc -Xms20M -Xmx20M -XX:+PrintGCDetails
 *
 * @author xuleyan
 * @version TestArrayBlockingQueue.java, v 0.1 2019-11-17 9:50 PM xuleyan
 */
public class TestLinkedBlockingQueue {


    private static final int number = 1000000;
    /**
     * 单线程 10000000 7136ms
     * <p>
     * 多线程 10000        33ms
     * 多线程 100000       165ms
     * 多线程 1000000      657ms
     * 多线程 10000000     19453ms
     */
    private static LinkedBlockingDeque<Integer> blockingQueue = new LinkedBlockingDeque<>(number);


    /**
     * 单线程 10000000     3136ms
     * <p>
     * 多线程 10000        29ms
     * 多线程 100000       107ms
     * 多线程 1000000      651ms
     * 多线程 10000000     13473ms
     */
    private static ArrayBlockingQueue<Integer> arrayBlockingQueue = new ArrayBlockingQueue<>(number);

    private static CountDownLatch countDownLatch = new CountDownLatch(number);
    private static CountDownLatch countTakeDownLatch = new CountDownLatch(number);

    public static void main(String[] args) throws InterruptedException {

        ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 5, 30, TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(),
                new BlockingThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());

        Date start = new Date();
        for (int i = 0; i < number; i++) {
            executor.execute(new BlockingOfferTask(i));

        }

        for (int i = 0; i < number; i++) {
            executor.execute(new BlockingTakeTask());
        }

        countDownLatch.await();
        countTakeDownLatch.await();

        System.out.println("耗时：" + ((new Date()).getTime() - start.getTime()) + "ms");
        executor.shutdown();


    }

    private static class BlockingOfferTask implements Runnable {

        private Integer num;

        public BlockingOfferTask(Integer num) {
            this.num = num;
        }

        @Override
        public void run() {
            // 队列满时抛出异常
            //blockingQueue.add(i);
            // 队列满时返回false
            arrayBlockingQueue.offer(num);
            countDownLatch.countDown();
            // 如果队列已满，会等待
            //blockingQueue.put(i);
        }
    }

    private static class BlockingTakeTask implements Runnable {

        @Override
        public void run() {
            // 从队列的头部取值，如果为空返回null
            //Integer num = (Integer) blockingQueue.poll();
            // 从头部取值并移除，为空会等待
            try {
                Integer num = arrayBlockingQueue.take();
                countTakeDownLatch.countDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //System.out.println(num);
        }
    }

    private static class BlockingThreadFactory implements ThreadFactory {

        private AtomicInteger integer = new AtomicInteger(0);

        @Override
        public Thread newThread(Runnable r) {
            Thread thread = new Thread(r);
            thread.setName("线程" + integer.incrementAndGet());
            return thread;
        }
    }
}