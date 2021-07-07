/**
 * fshows.com
 * Copyright (C) 2013-2020 All Rights Reserved.
 */
package com.example.springdemo.test.lock.readWriteLock;

import jodd.util.StringUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author xuleyan
 * @version ReadAndWriteLockTest.java, v 0.1 2020-04-24 10:06 AM xuleyan
 */
public class ReadAndWriteLockTest {
    private final static List<String> data = new ArrayList<>();
    static ReentrantReadWriteLock readAndWriteLock = new ReentrantReadWriteLock(true);
    private final static Lock readLock = readAndWriteLock.readLock();
    private final static Lock writeLock = readAndWriteLock.writeLock();
    private Map<String, String> map = new HashMap<>();

    public static void main(String[] args) {
//        new Thread(()->write()).start();
//        new Thread(() -> read()).start();
        test2();
    }

    public static void write() {
        try {
            writeLock.lock();
            data.add("写数据");
            System.out.println(Thread.currentThread().getName() + ":写数据");
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            writeLock.unlock();
        }
    }

    public static void read() {
        try {
            readLock.lock();
            for (String string : data) {
                System.out.println(Thread.currentThread().getName() + ":读数据");
            }
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            readLock.unlock();
        }
    }

    public static void test2() {
        ReadAndWriteLockTest test = new ReadAndWriteLockTest();
        for (int i = 0; i < 4; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "启动");
                test.writeAndRead();
            }).start();
        }
    }

    private void writeAndRead() {
        // 读数据
        readLock.lock();
        String readResult = map.get("a");
        if (readResult == null) {
            System.out.println("空数据， 需要先写");
            readLock.unlock();
            // 没有获取到锁的线程会被阻塞，知道获取到锁为止
            writeLock.lock();
            System.out.println(Thread.currentThread().getName() + "获取写锁:");
            if (StringUtil.isBlank(map.get("a"))) {
                map.put("a", "Java的架构师技术栈");
                System.out.println(Thread.currentThread().getName() + "写完了数据，写锁释放了");
            } else {
                System.out.println(Thread.currentThread().getName() + "判断有值，直接跳过写逻辑");
            }
            readResult = map.get("a");
            writeLock.unlock();
            readLock.lock();
        }
        System.out.println(Thread.currentThread().getName() + "读取的数据是：" + readResult);
        readLock.unlock();
    }


}