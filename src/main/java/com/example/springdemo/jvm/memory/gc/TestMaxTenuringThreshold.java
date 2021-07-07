/**
 * fshows.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.example.springdemo.jvm.memory.gc;

/**
 * -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:SurvivorRatio=8 -XX:+PrintGCDetails -XX:+PrintHeapAtGC -XX:MaxTenuringThreshold=1 -XX:+PrintTenuringDistribution
 * -XX:MaxTenuringThreshold=1  晋升老年代的阀值（如果对象在Eden出生并经过第一次Minor GC后仍然存活，
 * 并且能被Survivor容纳的话，将被移动到Survivor空间中，并将年龄设置为1。对象在Survivor区中每熬过一次Minor GC,
 * 年龄就会增加一岁，当它的年龄增加到一定程度(默认15岁)时，就会被晋升到老年代）
 *
 * @author xuleyan
 * @version TestMaxTenuringThreshold.java, v 0.1 2019-07-02 2:18 PM xuleyan
 */
public class TestMaxTenuringThreshold {

    private static final int _1MB = 1024 * 1024;

    public static void main(String[] args) {
        testTenuringThreshold2();
    }

    public static void testTenuringThreshold() {
        byte[] allocation1, allocation2, allocation3;

//        System.gc();
        allocation1 = new byte[_1MB / 4];
        allocation2 = new byte[4 * _1MB];
        allocation3 = new byte[4 * _1MB];
        allocation3 = null;
        allocation3 = new byte[4 * _1MB];

//        allocation3 = null;
//        System.gc();
    }

    public static void testTenuringThreshold2() {
        byte[] allocation1, allocation2, allocation3, allocation4;

//        System.gc();
        allocation1 = new byte[_1MB / 4];
        allocation2 = new byte[_1MB / 4];
        allocation3 = new byte[4 * _1MB];
        allocation4 = new byte[4 * _1MB];
        allocation4 = null;
        allocation4 = new byte[4 * _1MB];
    }
}