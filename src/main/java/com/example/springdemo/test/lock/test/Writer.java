/**
 * fshows.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.example.springdemo.test.lock.test;

/**
 * @author xuleyan
 * @version Writer.java, v 0.1 2019-10-04 3:32 PM xuleyan
 */
public class Writer extends Thread {

    private BufferInterruptibly buffer;

    public Writer(BufferInterruptibly buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        buffer.write();
    }
}