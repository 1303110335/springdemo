/**
 * fshows.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.example.springdemo.test.queue;


import java.util.Date;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @author xuleyan
 * @version TestArrayBlockingQueue.java, v 0.1 2019-11-17 9:50 PM xuleyan
 */
public class TestArrayBlockingQueue {

    public static void main(String[] args) throws InterruptedException {
        // 单线程 10000000 3136ms arrayBlockingQeueu好于linkedBlockingQueue

        int number = 10000000;
        BlockingQueue blockingQueue = new ArrayBlockingQueue(number);
        Date start = new Date();
        for (int i = 0; i < number; i++) {
            // 队列满时抛出异常
            //blockingQueue.add(i);
            // 队列满时返回false
            blockingQueue.offer(i);
            // 如果队列已满，会等待
            //blockingQueue.put(i);
        }

        for (int i = 0; i < number; i++) {
            // 从队列的头部取值，如果为空返回null
            //Integer num = (Integer) blockingQueue.poll();
            // 从头部取值并移除，为空会等待
            Integer num = (Integer) blockingQueue.take();
            //System.out.println(num);
        }

        System.out.println("耗时：" + ((new Date()).getTime() - start.getTime()) + "ms");


    }
}