/**
 * fshows.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.example.springdemo.test.date;

import java.text.ParseException;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author xuleyan
 * @version TestDateUtil.java, v 0.1 2019-06-28 9:29 AM xuleyan
 */
public class TestDateUtil {
    public static void main(String[] args) {
        final String pattern1 = "yyyy-MM-dd";
        final String pattern2 = "yyyy-MM";
        System.out.println("run on single thread");
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        for (int i = 1; i <= 6; i++) {
            executorService.execute(new Thread() {
                @Override
                public void run() {
                    try {
                        DateUtil.parse("2012-03-" + new Random().nextInt(10), pattern1);
                        DateUtil.parse("2012-03-" + new Random().nextInt(10), pattern2);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        sleep(1000);
        System.out.println();
        System.out.println("run on double thread");
        ExecutorService executorService1 = Executors.newFixedThreadPool(2);
        for (int i = 0; i <= 6; i++) {
            executorService1.execute(new Thread() {
                @Override
                public void run() {
                    try {
                        DateUtil.parse("2012-03-" + new Random().nextInt(10), pattern1);
                        DateUtil.parse("2012-03-" + new Random().nextInt(10), pattern2);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        executorService.shutdown();
        executorService1.shutdown();
    }

    public static void sleep(long millSeconds) {
        try {
            TimeUnit.MICROSECONDS.sleep(millSeconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}