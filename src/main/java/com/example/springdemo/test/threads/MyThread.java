/**
 * fshows.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.example.springdemo.test.threads;

/**
 * @author xuleyan
 * @version MyThread.java, v 0.1 2019-06-21 7:56 PM xuleyan
 */
public class MyThread extends Thread {
    @Override
    public void run() {
        System.out.println("my thread run");
    }
}