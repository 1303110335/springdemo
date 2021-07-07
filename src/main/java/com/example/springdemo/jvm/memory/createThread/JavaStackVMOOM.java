/**
 * fshows.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.example.springdemo.jvm.memory.createThread;

/**
 * 执行一下代码很有可能导致操作系统假死
 * -Xss2M -Xmx128M -Xms128M
 *
 * @author xuleyan
 * @version JavaStackVMOOM.java, v 0.1 2019-07-01 3:37 PM xuleyan
 */
public class JavaStackVMOOM {
    public static void main(String[] args) {
        JavaStackVMOOM oom = new JavaStackVMOOM();
        oom.stackLeakByThread();
    }

    private void dontStop() {
        while (true) {

        }
    }

    public void stackLeakByThread() {
        while (true) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    dontStop();
                }
            });
            thread.start();
        }
    }
}