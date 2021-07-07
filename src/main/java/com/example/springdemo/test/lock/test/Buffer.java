/**
 * fshows.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.example.springdemo.test.lock.test;

/**
 * @author xuleyan
 * @version Buffer.java, v 0.1 2019-10-04 3:25 PM xuleyan
 */
public class Buffer {

    private Object lock;

    public Buffer() {
        lock = this;
    }

    public void write() {
        synchronized (lock) {
            long startTime = System.currentTimeMillis();
            System.out.println("开始往这个buffer写入数据");
            for (; ; ) {
                if (System.currentTimeMillis() - startTime > Integer.MAX_VALUE) {
                    break;
                }
            }
            System.out.println("终于写完了");
        }
    }

    public void read() {
        synchronized (lock) {
            System.out.println("从这个buffer读数据");
        }
    }

}