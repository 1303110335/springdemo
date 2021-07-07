/**
 * fshows.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.example.springdemo.test.lock.test;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author xuleyan
 * @version BufferInterruptibly.java, v 0.1 2019-10-04 3:39 PM xuleyan
 */
public class BufferInterruptibly {

    private ReentrantLock lock = new ReentrantLock();

    public void write() {
        lock.lock();
        try {
            long startTime = System.currentTimeMillis();
            System.out.println("开始往这个buffer写入数据");

            for (; ; ) {// 模拟要处理很长时间
                if (System.currentTimeMillis() - startTime > Integer.MAX_VALUE) {
                    break;
                }
            }
            System.out.println("终于写完了");
        } finally {
            lock.unlock();
        }
    }

    public void read() throws InterruptedException {
        // 可以响应中断
        lock.lockInterruptibly();
        try {
            System.out.println("从这个buffer读取数据");
        } finally {
            lock.unlock();
        }
    }


}