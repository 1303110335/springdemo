/**
 * fshows.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.example.springdemo.jvm.memory.heap;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @Describe 堆溢出测试
 * @VM args: -verbose:gc -Xms10M -Xmx10M -XX:+PrintGCDetails
 * -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/Users/xuleyan/springdemo/m.hprof
 * @author xuleyan
 * @version HeapOutOfMemory.java, v 0.1 2019-04-25 9:12 AM xuleyan
 */
public class HeapOutOfMemory {
    public static void main(String[] args) throws InterruptedException {
        List<TestCase> cases = new ArrayList<>();
        Integer i = 0;
        while (true) {
            // 如果无限创建对象，但是没有通路到GC Roots，那么就会被垃圾回收器回收，不会因为到达最大堆的容量限制后产生内存溢出
            //new TestCase();
//            TimeUnit.SECONDS.sleep(1);
            // 如果无限创建对象，GC Roots到对象之间有可达路径，那么就不会被垃圾回收器回收，会因为到达最大堆的容量限制后产生内存溢出
            cases.add(new TestCase());
            //System.out.println(++i);
        }
    }
}