/**
 * fshows.com
 * Copyright (C) 2013-2020 All Rights Reserved.
 */
package com.example.springdemo.test.threadpool.test123;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 三个不同的线程将会共用一个 Foo 实例。
 * <p>
 * 线程 A 将会调用 one() 方法
 * 线程 B 将会调用 two() 方法
 * 线程 C 将会调用 three() 方法
 * 请设计修改程序，以确保 two() 方法在 one() 方法之后被执行，three() 方法在 two() 方法之后被执行。
 *
 * @author xuleyan
 * @version TestThread123.java, v 0.1 2020-04-22 9:35 AM xuleyan
 */
public class TestThread123 {
    private ReentrantLock lock = new ReentrantLock();

    private AtomicInteger firstJobDone = new AtomicInteger(0);
    private AtomicInteger secondJobDone = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        TestThread123 testThread123 = new TestThread123();
        testThread123.test();
    }

    private void test() throws InterruptedException, ExecutionException {
        ExecutorService executorService = new ThreadPoolExecutor(5, 10, 5, TimeUnit.SECONDS, new LinkedBlockingDeque<>());

        //Jedis jedis = JedisInstance.getSingleJedis();
        for (int i = 0; i < 10; i++) {
            System.out.println("运行第" + (i + 1) + "个任务");

            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    try {
                        // String key, String value, String nxxx, String expx, long time
                        doPrint();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
//            String s = jedis.set("name", "1", "NX", "EX", 1);
//            System.out.println("获取锁：s = " + s);
//            if ("ok".equalsIgnoreCase(s)) {
//                executorService.execute(runnable);
//                jedis.del("name");
//            }

            if (lock.tryLock(1000, TimeUnit.MILLISECONDS)) {
                System.out.println("获取到锁" + (i + 1));
                Future<?> submit = executorService.submit(runnable);
                System.out.println(submit.get());
                lock.unlock();
            } else {
                System.out.println("未获取到锁" + (i + 1));
            }
        }


    }

    //private synchronized void doPrint() throws InterruptedException {

    private void doPrint() throws InterruptedException {
        //TimeUnit.SECONDS.sleep(1);
        first(this::one);
        second(this::two);
        third(this::three);
    }

    public void first(Runnable printFirst) throws InterruptedException {

        // printFirst.run() outputs "first". Do not change or remove this line.
        printFirst.run();
        firstJobDone.getAndIncrement();
    }

    public void second(Runnable printSecond) throws InterruptedException {
        while (firstJobDone.get() != 1) {
            System.out.println("等待第一个任务完成");
            TimeUnit.MILLISECONDS.sleep(10);
        }
        // printSecond.run() outputs "second". Do not change or remove this line.
        printSecond.run();
        secondJobDone.incrementAndGet();
    }

    public void third(Runnable printThird) throws InterruptedException {
        while (secondJobDone.get() != 1) {
            System.out.println("等待第二个任务完成");
            TimeUnit.MILLISECONDS.sleep(10);
        }
        System.out.println("第三个任务完成");
        // printThird.run() outputs "third". Do not change or remove this line.
        printThird.run();
        firstJobDone.set(0);
        secondJobDone.set(0);
    }

    public void one() {
        System.out.println("one");
    }

    public void two() {
        System.out.println("two");
    }

    public void three() {
        System.out.println("three");
    }

};