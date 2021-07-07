/**
 * xuleyan
 * Copyright (C) 2013-2021 All Rights Reserved.
 */
package com.example.springdemo.test.threads;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author xuleyan
 * @version ThredPoolTest.java, v 0.1 2021-05-15 3:10 PM xuleyan
 */
public class ThreadPoolTest {
    // 线程安全问题，1.数组越界，2.名称覆盖 3.名称为null
    protected static List<Object> arrayList = new ArrayList<>();
    // 解决方案1：使用同步集合vector
//    protected static List<Object> arrayList = new Vector<>();
    // 解决方案2：使用同步集合
    protected static List<Object> synList = Collections.synchronizedList(arrayList);
    // 解决方案3：JUC
    protected static List<Object> copyList = new CopyOnWriteArrayList<>();

    public static void main(String[] args) {
        Thread[] threads = new Thread[500];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new MyThread2();
            threads[i].start();
        }

        // 遍历所有线程，等待线程执行完毕
        for (int i = 0; i < threads.length; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // 遍历所有线程名称
        for (Object threadName : copyList) {
            System.out.println("threadName = " + threadName);
        }

    }
}

class MyThread2 extends Thread {

    @Override
    public void run() {

        try {
            Thread.sleep(1000);
            // 向集合中添加自己的线程名称【操作共享内容，会出现线程安全问题】
            ThreadPoolTest.copyList.add(Thread.currentThread().getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}