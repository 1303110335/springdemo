/**
 * fshows.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.example.springdemo.test.lock.test;

/**
 * @author xuleyan
 * @version Reader.java, v 0.1 2019-10-04 3:29 PM xuleyan
 */
public class Reader extends Thread {

    //    private Buffer buffer;
    private BufferInterruptibly buffer;

    public Reader(BufferInterruptibly buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        try {
            //可以接收到中断的异常
            buffer.read();
        } catch (InterruptedException e) {
            System.out.println("我不读了");
        }

        System.out.println("读结束");
    }
}