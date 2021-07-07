/**
 * fshows.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.example.springdemo.jvm.memory.gc;

/**
 * VM参数：-verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:SurvivorRatio=8 -XX:+PrintGCDetails -XX:+PrintHeapAtGC
 *
 * @author xuleyan
 * @version TestAllocation.java, v 0.1 2019-07-02 11:15 AM xuleyan
 */
public class TestAllocation {

    private static final int _1MB = 1024 * 1024;

    /**
     * allocation1,allocation2,allocation3 依次放入到新生代eden, 内存没有超过9M
     * allocation4 再创建发现eden中已经放不下了，survivor也放不下，则触发依次Minor GC
     * 将allocation1,2,3放入到老年代，然后将allocation4放入到新生代eden,由日志可以看出。
     * <p>
     * 新生代gc：[GC (Allocation Failure) [PSYoungGen: 6325K->1021K(9216K)] 6325K->3322K(19456K), 0.0025965 secs] [Times: user=0.01 sys=0.00, real=0.00 secs]
     *
     * @param args
     */
    public static void main(String[] args) {
        byte[] allocation1, allocation2, allocation3, allocation4, allocation5;
        allocation1 = new byte[2 * _1MB];
        allocation2 = new byte[2 * _1MB];
        allocation3 = new byte[2 * _1MB];
        // 出现一次Minor GC
        allocation4 = new byte[4 * _1MB];


        allocation5 = new byte[4 * _1MB];

        // 存在了新生代
        allocation5 = null;
        System.gc();

        // 一半存在了新生代，一半存在了老年代
        allocation4 = null;
        System.gc();

        // allocation1,2,3都存在了老年代
        allocation1 = null;
        System.gc();

        allocation2 = null;
        System.gc();

        allocation3 = null;
        System.gc();
    }
}