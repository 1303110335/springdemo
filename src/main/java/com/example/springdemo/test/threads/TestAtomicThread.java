/**
 * fshows.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.example.springdemo.test.threads;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author xuleyan
 * @version TestThread.java, v 0.1 2019-06-01 2:41 PM xuleyan
 */
public class TestAtomicThread {

    public static AtomicInteger count = new AtomicInteger(0);

    public static void main(String[] args) {
        for (int i = 0; i < 2; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    for (int j = 0; j < 100; j++) {
                        // synchronized关键字会让没有得到锁资源的线程进入BLOCKED状态，而后在争夺到锁资源后恢复为RUNNABLE状态，
                        // 这个过程中涉及到操作系统用户模式和内核模式的转换，代价比较高。
                        count.getAndIncrement();
                    }
                }
            }).start();
        }

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("count=" + count);
    }
}