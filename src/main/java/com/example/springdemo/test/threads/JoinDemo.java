/**
 * fshows.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.example.springdemo.test.threads;

/**
 * @author xuleyan
 * @version JoinDemo.java, v 0.1 2019-10-24 11:05 AM xuleyan
 */
public class JoinDemo {

    public static void main(String[] args) {
        Thread previousThread = Thread.currentThread();
        for (int i = 1; i <= 10; i++) {
            Thread curThread = new JoinThread(previousThread);
            curThread.start();
            previousThread = curThread;
        }
    }

    static class JoinThread extends Thread {
        private Thread thread;

        public JoinThread(Thread thread) {
            this.thread = thread;
        }

        @Override
        public void run() {
            try {
                thread.join();
                System.out.println(thread.getName() + "terminated.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}