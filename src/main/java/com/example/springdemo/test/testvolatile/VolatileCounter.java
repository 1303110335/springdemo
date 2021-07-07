/**
 * fshows.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.example.springdemo.test.testvolatile;

import com.example.springdemo.test.threadpool.JedisInstance;
import redis.clients.jedis.Jedis;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author xuleyan
 * @version VolatileCounter.java, v 0.1 2019-04-12 9:35 AM xuleyan
 */
public class VolatileCounter {
    // volatile 只具有可见性和有序性，但不保证原子性
    public static volatile int num = 0;
    //使用CountDownLatch来等待计算线程执行完
    static CountDownLatch countDownLatch = new CountDownLatch(30);

    static Lock reentrantLock = new ReentrantLock();


    public static void main(String []args) throws InterruptedException {
        //testNonSafe();
        //testSafeByLock();
        testSafeByRedis();
    }

    /**
     * 非线程安全
     *
     * @throws InterruptedException
     */
    public static void testNonSafe() throws InterruptedException {
        //开启30个线程进行累加操作
        for (int i = 0; i < 30; i++) {
            new Thread() {
                @Override
                public void run() {
                    for (int j = 0; j < 10000; j++) {
                        num++;//自加操作
                    }
                    countDownLatch.countDown();
                }
            }.start();
        }

        countDownLatch.await();
        System.out.println(num);
    }

    /**
     * 锁安全操作
     *
     * @throws InterruptedException
     */
    public static void testSafeByLock() throws InterruptedException {
        //开启30个线程进行累加操作
        for(int i = 0; i<30; i++){
            new Thread(){
                @Override
                public void run(){
                    for(int j = 0; j < 10000; j++) {
                        reentrantLock.lock();
                        num++;//自加操作
                        reentrantLock.unlock();
                    }
                    countDownLatch.countDown();
                }
            }.start();
        }

        countDownLatch.await();
        System.out.println(num);
    }

    /**
     * 一但占用了key的线程挂了，则会导致锁一直被占用
     *
     * @throws InterruptedException
     */
    public static void testSafeByRedis() throws InterruptedException {
        Jedis jedis = JedisInstance.getSingleJedis();
        //开启30个线程进行累加操作
        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                for (int j = 0; j < 10000; j++) {
                    Long result = jedis.setnx("lock", "test");
                    if (result == 1) {
                        num++;//自加操作
                        jedis.del("lock");
                    }
                }
                countDownLatch.countDown();
            }).start();
        }

        countDownLatch.await();
        jedis.close();
        System.out.println(num);
    }
}