/**
 * fshows.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.example.springdemo.jvm.memory.gc;

/**
 * VM参数：-verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:SurvivorRatio=8 -XX:+PrintGCDetails -XX:+PrintHeapAtGC -XX:PretenureSizeThreshold=3145728
 * -XX:PretenureSizeThreshold=3145728 只对serial和parNew两款收集器有效,令大于3M的对象直接进入老年代
 *
 * @author xuleyan
 * @version TestPretenureSizeThreshold.java, v 0.1 2019-07-02 12:00 PM xuleyan
 */
public class TestPretenureSizeThreshold {
    private static final int _1MB = 1024 * 1024;

    public static void main(String[] args) {
        byte[] allocation;
        allocation = new byte[4 * _1MB];

        allocation = null;
        System.gc();
    }
}