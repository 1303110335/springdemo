/**
 * fshows.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.example.springdemo.test.lock;

import java.util.concurrent.locks.LockSupport;

/**
 * @author xuleyan
 * @version TestLockSupport.java, v 0.1 2019-10-29 9:27 PM xuleyan
 */
public class TestLockSupport {

    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "park");
            LockSupport.park();
            System.out.println(Thread.currentThread().getName() + "被唤醒");
        });

        thread.start();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        LockSupport.unpark(thread);
    }
}