/**
 * fshows.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.example.springdemo.test.queue;

import java.util.concurrent.Semaphore;

/**
 * @author xuleyan
 * @version SemaphoreDemo.java, v 0.1 2019-12-10 9:14 PM xuleyan
 */
public class SemaphoreDemo {

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(2);
        int threadNum = 5;
        for (int i = 0; i < threadNum; i++) {
            new TaskThread(semaphore).start();
        }
    }

    static class TaskThread extends Thread {
        Semaphore semaphore;

        TaskThread(Semaphore semaphore) {
            this.semaphore = semaphore;
        }

        @Override
        public void run() {
            try {
                // 获取令牌
                semaphore.acquire();
                System.out.println(getName() + " acquire");
                Thread.sleep(1000);
                semaphore.release();
                System.out.println(getName() + " release");


            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}