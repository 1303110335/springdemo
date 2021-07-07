/**
 * fshows.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.example.springdemo.test.threads;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

/**
 * 一个 Java 程序的运行是 main 线程和多个其他线程同时运行。
 * [5] Attach Listener //添加事件
 * [4] Signal Dispatcher // 分发处理给 JVM 信号的线程
 * [3] Finalizer //调用对象 finalize 方法的线程
 * [2] Reference Handler //清除 reference 线程
 * [1] main //main 线程,程序入口
 *
 * @author xuleyan
 * @version MultiThread.java, v 0.1 2019-10-09 4:32 PM xuleyan
 */
public class MultiThread {

    public static void main(String[] args) {
        // 获取 Java 线程管理 MXBean
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        // 不需要获取同步的 monitor 和 synchronizer 信息，仅获取线程和线程堆栈信息
        ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(false, false);
        // 遍历线程信息，仅打印线程 ID 和线程名称信息
        for (ThreadInfo threadInfo : threadInfos) {
            System.out.println("[" + threadInfo.getThreadId() + "] " + threadInfo.getThreadName());
        }
    }
}