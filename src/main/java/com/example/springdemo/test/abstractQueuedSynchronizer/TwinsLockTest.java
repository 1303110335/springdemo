/**
 * fshows.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.example.springdemo.test.abstractQueuedSynchronizer;

import java.util.concurrent.locks.Lock;

/**
 * @author xuleyan
 * @version TwinsLockTest.java, v 0.1 2019-06-26 12:00 PM xuleyan
 */
public class TwinsLockTest {

    public static void main(String[] args) {
        TwinsLockTest twinsLockTest = new TwinsLockTest();
        twinsLockTest.testTwinsLock();
    }

    public void testTwinsLock() {
        final Lock lock = new TwinsLock();
        class Worker extends Thread {
            @Override
            public void run() {
                while (true) {
                    // 获取锁
                    lock.lock();
                    try {
                        SleepUtils.second(1);
                        System.out.println(System.currentTimeMillis() + " " + Thread.currentThread().getName());
                        SleepUtils.second(1);
                    } finally {
                        // 释放锁
                        lock.unlock();
                    }
                }
            }
        }

        // 开10个线程运行worker, 如果没有锁，应该是几乎同时很快完成
        // 但 TwinsLock 只允许同时有两个线程获得锁运行
        for (int i = 0; i < 10; i++) {
            Worker w = new Worker();
            w.setDaemon(true);
            w.start();
        }

        // 每隔1s换行
        for (int i = 0; i < 10; i++) {
            SleepUtils.second(1);
            System.out.println();
        }
    }
}

// 睡眠工具类
class SleepUtils {
    public static void second(int sec) {
        try {
            Thread.sleep(sec * 1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}