/**
 * fshows.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.example.springdemo.test.producer.consumer;

import java.util.LinkedList;

/**
 * @author xuleyan
 * @version Storage.java, v 0.1 2019-06-26 9:56 AM xuleyan
 */
public class Storage {
    private LinkedList<String> list;
    private int maxSize;

    public Storage(LinkedList<String> list, int maxSize) {
        this.list = list;
        this.maxSize = maxSize;
    }

    public static void main(String[] args) {
        LinkedList<String> linkedList = new LinkedList<>();
        Storage storage = new Storage(linkedList, 10);

        Consumer consumer1 = new Consumer(storage);
        Consumer consumer2 = new Consumer(storage);
        Producer producer1 = new Producer(storage);
        Producer producer2 = new Producer(storage);
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
            synchronized (list) {
                while (list.size() == maxSize) {
                    list.wait();
                }
                list.add("生产一个任务");
                System.out.println(Thread.currentThread().getName() + "生产一个");
                list.notifyAll();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String consume() {
        try {
            synchronized (list) {
                while (list.size() == 0) {
                    list.wait();
                }
                String consume = list.remove();
                System.out.println(Thread.currentThread().getName() + "消费一个");
                list.notifyAll();
                return consume;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }
}

class Consumer implements Runnable {

    private Storage storage;

    public Consumer(Storage storage) {
        this.storage = storage;
    }

    @Override
    public void run() {
        while (true) {
            storage.consume();
        }
    }
}

class Producer implements Runnable {
    private Storage storage;

    public Producer(Storage storage) {
        this.storage = storage;
    }

    @Override
    public void run() {
        while (true) {
            storage.produce();
        }
    }
}