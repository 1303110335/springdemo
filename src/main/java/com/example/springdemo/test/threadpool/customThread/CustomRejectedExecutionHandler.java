/**
 * fshows.com
 * Copyright (C) 2013-2020 All Rights Reserved.
 */
package com.example.springdemo.test.threadpool.customThread;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author xuleyan
 * @version CustomRejectedExecutionHandler.java, v 0.1 2020-04-22 3:37 PM xuleyan
 */
public class CustomRejectedExecutionHandler implements RejectedExecutionHandler {

    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        //System.out.println("error............");

        // 核心改造点，由blockingqueue的offer改成put阻塞方法，可以让所有的任务都执行，
        // 只是会在线程池和队列都满了之后先阻塞一段时间。
        try {
            executor.getQueue().put(r);
            //System.out.println("超出长度依旧插入队列：队列长度：" + executor.getQueue().size());

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}