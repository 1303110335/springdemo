/**
 * fshows.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.example.springdemo.test.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author xuleyan
 * @version TestLock.java, v 0.1 2019-06-23 5:42 PM xuleyan
 */
public class TestLock {
    private Lock lock = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {
        TestLock testLock = new TestLock();


        //ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
        // 声明一个线程 "线程一"
//        Thread thread1 = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                ReadAndWriteLockTest.lockTest(Thread.currentThread());
//            }
//        }, "thread1");
//
//        // 声明一个线程 "线程二"
//        Thread thread2 = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                ReadAndWriteLockTest.lockTest(Thread.currentThread());
//            }
//        }, "thread2");

//        Thread thread1 = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                ReadAndWriteLockTest.tryLockTest(Thread.currentThread());
//            }
//        }, "thread1");
//
//        // 声明一个线程 "线程二"
//        Thread thread2 = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                ReadAndWriteLockTest.tryLockTest(Thread.currentThread());
//            }
//        }, "thread2");

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    testLock.tryLockParamTest(Thread.currentThread());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "thread1");

        // 声明一个线程 "线程二"
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    testLock.tryLockParamTest(Thread.currentThread());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "thread2");
        // 启动2个线程
        thread1.start();
        /**
         * 主线程向下转时，thread1.join(),thread1要申请加入到运行中来，就是要CPU执行权。
         * 这时候CPU执行权在主线程手里，主线程就把CPU执行权给放开，陷入冻结状态。
         * 活着的只有thread1了，只有当thread1拿着执行权把这些数据都打印完了，主线程才恢复到运行中来
         */
        thread1.join();
        thread2.start();
    }

    public void lockTest(Thread thread) {
        // 获取锁
        lock.lock();
        try {
            System.out.println("线程" + thread.getName() + "获取当前锁");
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            System.out.println("线程" + thread.getName() + "发生了异常释放锁");
        } finally {
            System.out.println("线程" + thread.getName() + "执行完毕释放锁");
            lock.unlock();
        }
    }

    public void tryLockTest(Thread thread) {
        // 尝试获取锁
        if (lock.tryLock()) {
            try {
                System.out.println("线程" + thread.getName() + "获取当前锁");
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                System.out.println("线程" + thread.getName() + "发生了异常释放锁");
            } finally {
                System.out.println("线程" + thread.getName() + "执行完毕释放锁");
                lock.unlock();
            }
        } else {
            System.out.println("我是线程" + Thread.currentThread().getName() + "当前锁被别人占用，我无法获取");
        }
    }

    public void tryLockParamTest(Thread thread) throws InterruptedException {
        // 尝试获取锁
        if (lock.tryLock(3000, TimeUnit.MILLISECONDS)) {
            try {
                System.out.println("线程" + thread.getName() + "获取当前锁");
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                System.out.println("线程" + thread.getName() + "发生了异常释放锁");
            } finally {
                System.out.println("线程" + thread.getName() + "执行完毕释放锁");
                lock.unlock();
            }
        } else {
            System.out.println("我是线程" + Thread.currentThread().getName() + "当前锁被别人占用,已等待3s，任无法获取，放弃");
        }
    }
}