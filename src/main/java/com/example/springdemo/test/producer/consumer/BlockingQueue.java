/**
 * fshows.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.example.springdemo.test.producer.consumer;

import java.util.concurrent.LinkedBlockingDeque;

/**
 * @author xuleyan
 * @version BlockingQueue.java, v 0.1 2019-06-26 10:42 AM xuleyan
 */
public class BlockingQueue {

    private LinkedBlockingDeque<String> queue = new LinkedBlockingDeque<>(10);

    public static void main(String[] args) {
        BlockingQueue stroge = new BlockingQueue();
        QueueProducer producer = new QueueProducer(stroge);
        QueueConsumer consumer = new QueueConsumer(stroge);
        Thread consumer1 = new Thread(consumer);
        Thread producer1 = new Thread(producer);
        QueueProducer producer2 = new QueueProducer(stroge);
        QueueConsumer consumer2 = new QueueConsumer(stroge);
        Thread consumer22 = new Thread(consumer2);
        Thread producer22 = new Thread(producer2);
        consumer1.start();
        producer1.start();
        consumer22.start();
        producer22.start();
    }

    public void produce() {
        // 满了则会阻塞
        try {
            queue.push("生产了一个任务");
            System.out.println(Thread.currentThread().getName() + "生产一个");
        } catch (IllegalStateException ex) {
            return;
        }
    }

    public String consume() {
        // 空了会阻塞
        try {
            String consume = queue.take();
            System.out.println(Thread.currentThread().getName() + "消费一个");
            return consume;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }

}


class QueueProducer implements Runnable {
    private BlockingQueue stroge;

    public QueueProducer(BlockingQueue stroge) {
        this.stroge = stroge;
    }

    @Override
    public void run() {
        while (true) {
            stroge.produce();
        }
    }

}

class QueueConsumer implements Runnable {

    private BlockingQueue stroge;

    public QueueConsumer(BlockingQueue stroge) {
        this.stroge = stroge;
    }

    @Override
    public void run() {
        while (true) {
            stroge.consume();
        }
    }
}