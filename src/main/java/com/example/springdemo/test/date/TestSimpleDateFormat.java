/**
 * fshows.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.example.springdemo.test.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @author xuleyan
 * @version TestSimpleDateFormat.java, v 0.1 2019-06-27 10:32 PM xuleyan
 */
public class TestSimpleDateFormat {
    static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
    static String[] testDate = {"2001-03-03", "1994-04-03", "2045-06-08"};

    public static void main(String[] args) {

        Runnable[] runnables = new Runnable[testDate.length];
        for (int i = 0; i < runnables.length; i++) {
            final int j = i;
            runnables[i] = new Runnable() {
                @Override
                public void run() {
                    try {
                        for (int k = 0; k < 1000; k++) {
                            String originDate = testDate[j];
                            String formattedDate = null;
                            Date date = simpleDateFormat.parse(originDate);
                            formattedDate = simpleDateFormat.format(date);
                            System.out.println("i:" + j + "k:" + "ThreadId:" + Thread.currentThread().getId()
                                    + " ThreadName:" + Thread.currentThread().getName() + "string2:" + formattedDate);
                            if (!originDate.equals(formattedDate)) {
                                throw new RuntimeException("date conversion failed after " + k + " iterations expected "
                                        + originDate + "but got " + formattedDate);
                            }
                        }
                    } catch (ParseException e) {
                        throw new RuntimeException("parse failed");
                    }
                }
            };
            new Thread(runnables[i]).start();
        }
    }
}