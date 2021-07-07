/**
 * fshows.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.example.springdemo.test.threads;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author xuleyan
 * @version ExchangerDemo.java, v 0.1 2019-12-01 2:30 PM xuleyan
 */
public class ExchangerDemo {

    private static Exchanger<String> exchanger = new Exchanger<>();

    public static void main(String[] args) {

        ExecutorService service = Executors.newFixedThreadPool(2);
        service.execute(() -> {
            try {
                String girl = exchanger.exchange("我其实暗恋你很久了.....");
                System.out.println("女孩儿说：" + girl);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        service.execute(() -> {
            try {
                System.out.println("女生慢慢的从教室你走出来......");
                TimeUnit.SECONDS.sleep(3);
                //男生对女生说的话
                String boy = exchanger.exchange("我也很喜欢你......");
                System.out.println("男孩儿说：" + boy);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }
}