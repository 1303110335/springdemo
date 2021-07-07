/**
 * fshows.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.example.springdemo.test.calendar;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author xuleyan
 * @version TestCalendar.java, v 0.1 2019-06-14 5:13 PM xuleyan
 */
public class TestCalendar {

    public static void main(String[] args) {


        Calendar calendar = Calendar.getInstance();
        int startTime = 1560503650;
        Date date = new Date(startTime * 1000L);
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, 6);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        System.out.println(date.getTime());
        System.out.println(calendar.getTime().getTime());
        System.out.println(sdf.format(calendar.getTime()));

        System.out.println("year:" + calendar.get(Calendar.YEAR));
        System.out.println("month:" + calendar.get(Calendar.MONTH));
        System.out.println("day:" + calendar.get(Calendar.DAY_OF_MONTH));
        System.out.println("hour:" + calendar.get(Calendar.HOUR_OF_DAY));
        System.out.println("minute:" + calendar.get(Calendar.MINUTE));
        System.out.println("seconds:" + calendar.get(Calendar.SECOND));
        System.out.println("dayofweek:" + calendar.get(Calendar.DAY_OF_WEEK));
        System.out.println("dayofYear:" + calendar.get(Calendar.DAY_OF_YEAR));

        int num = 6;
        System.out.println(-num);
    }
}