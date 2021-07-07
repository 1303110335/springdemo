/**
 * fshows.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.example.springdemo.test.lock;

/**
 * @author xuleyan
 * @version DeadLockSample.java, v 0.1 2019-06-27 11:05 AM xuleyan
 */
public class DeadLockSample extends Thread {
    private String first;
    private String second;

    public DeadLockSample(String name, String first, String second) {
        super(name);
        this.first = first;
        this.second = second;
    }

    public static void main(String[] args) throws InterruptedException {
        String lockA = "lockA";
        String lockB = "lockB";
        DeadLockSample t1 = new DeadLockSample("t1", lockA, lockB);
        DeadLockSample t2 = new DeadLockSample("t2", lockB, lockA);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }

    @Override
    public void run() {
        synchronized (first) {
            try {
                System.out.println(this.getName() + "...synchronized:===>【first】" + first);
                Thread.sleep(1000L);
                synchronized (second) {
                    System.out.println(this.getName() + "...synchronized:===>【second】" + second);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}