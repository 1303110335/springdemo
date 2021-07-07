/**
 * xuleyan
 * Copyright (C) 2013-2021 All Rights Reserved.
 */
package com.example.springdemo.jvm.memory;

import com.example.springdemo.domain.User;

/**
 * 是否逃逸：即对象是否超出该方法体或该类
 * 逃逸分析相关代码 对象不逃逸则可以进行各种优化,减少对象的产生，内存的浪费
 * -Xms4G -Xmx4G -XX-DoEscapeAnalysis -XX:+PrintGcDetails -XX:+HeapDumpOnOutOfMemoryError
 *
 * @author xuleyan
 * @version EscapeAnalysis.java, v 0.1 2021-04-21 11:17 PM xuleyan
 */
public class EscapeAnalysis {

    public static void main(String[] args) {
        long a1 = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            alloc();
        }
        // 查看执行时间
        long a2 = System.currentTimeMillis();
        System.out.println("cost " + (a2 - a1) + " ms");
        // 方便查看对象个数，线程sleep
        try {
            Thread.sleep(100000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void alloc() {
        User user = new User();
    }


}