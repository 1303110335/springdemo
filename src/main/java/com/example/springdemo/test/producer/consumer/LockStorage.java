/**
 * fshows.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.example.springdemo.test.producer.consumer;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author xuleyan
 * @version LockStorage.java, v 0.1 2019-06-26 10:15 AM xuleyan
 */
public class LockStorage {
    private ReentrantLock lock = new ReentrantLock();
    private Condition full = lock.newCondition();
    private Condition empty = lock.newCondition();
    private LinkedList<String> list;
    private int maxSize;

    public LockStorage(LinkedList<String> list, int maxSize) {
        this.list = list;
        this.maxSize = maxSize;
    }

    public static void main(String[] args) {
        LinkedList<String> linkedList = new LinkedList<>();
        LockStorage storage = new LockStorage(linkedList, 10);

        LockConsumer consumer1 = new LockConsumer(storage);
        LockConsumer consumer2 = new LockConsumer(storage);
        LockProducer producer1 = new LockProducer(storage);
        LockProducer producer2 = new LockProducer(storage);
        Thread consumerThread1 = new Thread(consumer1);
        Thread consumerThread2 = new Thread(consumer2);
        Thread produceThread1 = new Thread(producer1);
        Thread produceThread2 = new Thread(producer2);
        consumerThread1.start();
        consumerThread2.start();
        produceThread1.start();
        produceThread2.start();
    }

    public void produce() {
        try {
            lock.lock();
            // 满了则阻塞
            while (list.size() == maxSize) {
                full.await();
            }
            list.add("生产一个任务");
            System.out.println(Thread.currentThread().getName() + "生产一个");
            empty.signal();
        } catch (InterruptedException e) {

        } finally {
            lock.unlock();
        }
    }

    public String consume() {
        try {
            lock.lock();
            while (list.size() == 0) {
                empty.await();
            }
            String consume = list.remove();
            System.out.println(Thread.currentThread().getName() + "消费一个");
            full.signal();
            return consume;
        } catch (InterruptedException e) {
            return null;
        } finally {
            lock.unlock();
        }
    }
}


class LockConsumer implements Runnable {

    private LockStorage storage;

    public LockConsumer(LockStorage storage) {
        this.storage = storage;
    }

    @Override
    public void run() {
        while (true) {
            storage.consume();
        }
    }
}

class LockProducer implements Runnable {
    private LockStorage storage;

    public LockProducer(LockStorage storage) {
        this.storage = storage;
    }

    @Override
    public void run() {
        while (true) {
            storage.produce();
        }
    }
}
