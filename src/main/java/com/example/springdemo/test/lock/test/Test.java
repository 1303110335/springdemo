/**
 * fshows.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.example.springdemo.test.lock.test;

/**
 * @author xuleyan
 * @version Test.java, v 0.1 2019-10-04 3:31 PM xuleyan
 */
public class Test {

    public static void main(String[] args) {
        BufferInterruptibly buff = new BufferInterruptibly();
        final Writer writer = new Writer(buff);
        final Reader reader = new Reader(buff);

        writer.start();
        reader.start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                long start = System.currentTimeMillis();
                for (; ; ) {
                    // 等5s去中断读
                    if (System.currentTimeMillis() - start > 5000) {
                        System.out.println("不等了,尝试中断");
                        reader.interrupt();
                        break;
                    }

                }
            }
        }).start();

    }
}