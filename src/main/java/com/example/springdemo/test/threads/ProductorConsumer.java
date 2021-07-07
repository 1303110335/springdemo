/**
 * fshows.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.example.springdemo.test.threads;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author xuleyan
 * @version ProductorConsumer.java, v 0.1 2019-12-01 5:50 PM xuleyan
 */
public class ProductorConsumer {
    public static void main(String[] args) throws InterruptedException {
        LinkedList linkedList = new LinkedList();
        ExecutorService service = Executors.newFixedThreadPool(15);
        for (int i = 0; i < 5; i++) {
            service.submit(new Productor(linkedList, 2));
        }

        Thread.sleep(1000);

        for (int i = 0; i < 10; i++) {
            service.submit(new Consumer(linkedList));
        }

        Thread.sleep(1000);
    }

    static class Productor implements Runnable {

        private List<Integer> list;
        private Integer maxSize;

        Productor(List<Integer> list, Integer maxSize) {
            this.list = list;
            this.maxSize = maxSize;
        }

        @Override
        public void run() {
            while (true) {
                synchronized (list) {
                    try {
                        if (list.size() >= maxSize) {
                            System.out.println("生产者：" + Thread.currentThread().getName() + " 容量已达到最大，请wait");
                            list.wait();
                            System.out.println("生产者: " + Thread.currentThread().getName() + " 退出wait");
                        }

                        Random random = new Random();
                        int i = random.nextInt();
                        System.out.println("生产者：" + Thread.currentThread().getName() + "生产数据: " + i);
                        list.add(i);
                        list.notifyAll();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        }
    }

    static class Consumer implements Runnable {

        private List<Integer> list;

        Consumer(List<Integer> list) {
            this.list = list;
        }

        @Override
        public void run() {
            while (true) {
                synchronized (list) {
                    try {
                        if (list.isEmpty()) {
                            System.out.println("消费者:" + Thread.currentThread().getName() + "数据为空,请wait");
                            list.wait();
                            System.out.println("消费者:" + Thread.currentThread().getName() + "退出wait");
                        }
                        Integer element = list.remove(0);
                        System.out.println("消费者：" + Thread.currentThread().getName() + "消费数据:" + element);
                        list.notifyAll();

                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        }
    }
}