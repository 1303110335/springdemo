/**
 * fshows.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.example.springdemo.test.threads;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author xuleyan
 * @version SubmitTest.java, v 0.1 2019-06-27 10:21 PM xuleyan
 */
public class SubmitTest {

    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(2);

        /**
         * execute(Runnable x) 没有返回值。可以执行任务，但是无法判断任务的成功
         */
        pool.execute(new RunnableTest("task1"));

        /**
         * submit(Runnable x) 返回一个future. 可以用这个future来判断任务是否完成.
         */
        Future future = pool.submit(new RunnableTest("Task2"));
        try {
            if (future.get() == null) {
                System.out.println("任务完成");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
//            e.printStackTrace();
            System.out.println(e.getCause().getMessage());
        }
    }
}

class RunnableTest implements Runnable {

    private String taskName;

    public RunnableTest(final String taskName) {
        this.taskName = taskName;
    }

    @Override
    public void run() {
        System.out.println("Inside " + taskName);
        throw new RuntimeException("RuntimeException from inside " + taskName);
    }

}