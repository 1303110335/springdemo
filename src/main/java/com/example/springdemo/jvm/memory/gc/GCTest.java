/**
 * fshows.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.example.springdemo.jvm.memory.gc;

/**
 * -XX:+PrintGC -XX:+PrintGCDetails
 * @author xuleyan
 * @version GCTest.java, v 0.1 2019-04-26 8:53 AM xuleyan
 */
public class GCTest {
    private Object instance = null;
    private static final int _10M = 10 * 1 << 20;
    // 一个对象占10M，方便在GC日志中看出是否被回收
    private byte[] bigSize = new byte[_10M];

    public static void main(String[] args) {
        GCTest objA = new GCTest();
        GCTest objB = new GCTest();

        objA.instance = objB;
        objB.instance = objA;

        objA = null;
        objB = null;

        System.gc();
    }
}