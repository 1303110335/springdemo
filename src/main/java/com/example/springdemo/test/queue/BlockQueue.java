/**
 * fshows.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.example.springdemo.test.queue;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 实现一个阻塞队列
 * wait 和 notify 基于 synchronized
 * wait 和 signal 基于 ReentrantLock
 *
 * @author xuleyan
 * @version BlockQueue.java, v 0.1 2019-12-09 8:39 AM xuleyan
 */
public class BlockQueue<T> {

    private int size;
    private LinkedList<Integer> queue = new LinkedList<>();

    private Lock lock = new ReentrantLock();
    private Condition addCondition = lock.newCondition();
    private Condition removeCondition = lock.newCondition();

    private BlockQueue(int size) {
        this.size = size;
    }

    public static void main(String[] args) throws InterruptedException {
        BlockQueue blockQueue = new BlockQueue(10);
        blockQueue.run();
    }

    public void run() throws InterruptedException {

        Consumer consumer = this.new Consumer();
        Producer producer = this.new Producer(100);
        final Thread producerThread = new Thread(producer, "producer");
        final Thread consumerThread = new Thread(consumer, "consumer");
        producerThread.start();
        TimeUnit.SECONDS.sleep(2);
        consumerThread.start();


    }

    public class Producer implements Runnable {

        private Integer numbers;

        public Producer(Integer numbers) {
            this.numbers = numbers;
        }

        @Override
        public void run() {
            String threadName = Thread.currentThread().getName();
            lock.lock();
            try {
                for (int i = 0; i < numbers; i++) {
                    while (queue.size() > size) {
                        System.out.println(threadName + "当前队列已满");
                        try {
                            addCondition.await();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    queue.add(i);
                    System.out.println(threadName + "已产生产品数i:" + i + "，队列个数:" + queue.size());
                    removeCondition.signal();
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

    public class Consumer implements Runnable {
        @Override
        public void run() {
            String threadName = Thread.currentThread().getName();
            while (true) {
                lock.lock();

                try {
                    while (queue.size() <= 0) {
                        System.out.println(threadName + "当前队列是空的");
                        removeCondition.await();
                    }
                    int size = queue.size();
                    for (int i = 0; i < size; i++) {
                        Integer result = queue.remove();
                        System.out.println(threadName + "消费产品:" + result);
                    }
                    addCondition.signal();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }
    }
}