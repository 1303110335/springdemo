/**
 * fshows.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.example.springdemo.test.threads.producerAndConsumer;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * @author xuleyan
 * @version LinkedBlockingQueue.java, v 0.1 2019-12-01 6:51 PM xuleyan
 */
public class LinkedBlockingQueue {

    private static LinkedBlockingDeque<Integer> blockingDeque = new LinkedBlockingDeque<>();

    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(15);


        for (int i = 0; i < 5; i++) {
            service.execute(new Productor(blockingDeque));
        }

        for (int i = 0; i < 10; i++) {
            service.execute(new Consumer(blockingDeque));
        }
    }

    static class Productor implements Runnable {
        private LinkedBlockingDeque<Integer> blockingDeque;

        public Productor(LinkedBlockingDeque<Integer> blockingDeque) {
            this.blockingDeque = blockingDeque;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    Random random = new Random();
                    int i = random.nextInt();
                    System.out.println("生产者" + Thread.currentThread().getName() + " 生产数据" + i);
                    blockingDeque.put(i);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    static class Consumer implements Runnable {

        private LinkedBlockingDeque<Integer> blockingDeque;

        public Consumer(LinkedBlockingDeque<Integer> blockingDeque) {
            this.blockingDeque = blockingDeque;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    Integer i = (Integer) blockingDeque.take();
                    System.out.println("消费者" + Thread.currentThread().getName() + "  消费数据：" + i);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}