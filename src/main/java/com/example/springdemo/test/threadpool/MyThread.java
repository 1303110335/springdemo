/**
 * fshows.com
 * Copyright (C) 2013-2020 All Rights Reserved.
 */
package com.example.springdemo.test.threadpool;

/**
 * @author xuleyan
 * @version MyTask.java, v 0.1 2020-04-03 9:12 PM xuleyan
 */
public class MyThread implements Runnable {

    private int taskNum;


    public MyThread(int taskNum) {
        this.taskNum = taskNum;
    }

    /**
     * Setter method for property <tt>taskNum</tt>.
     *
     * @param taskNum value to be assigned to property taskNum
     */
    public void setTaskNum(int taskNum) {
        this.taskNum = taskNum;
    }

    @Override
    public void run() {
        System.out.println("正在执行task" + taskNum);

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("task" + taskNum + "执行完毕");
    }
}