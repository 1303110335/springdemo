/**
 * fshows.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.example.springdemo.test.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author xuleyan
 * @version DateUtil.java, v 0.1 2019-06-28 9:15 AM xuleyan
 */
public class DateUtil {
    private static ThreadLocal<Map<String, SimpleDateFormat>> dateFormatMap = new ThreadLocal<Map<String, SimpleDateFormat>>() {
        @Override
        protected Map<String, SimpleDateFormat> initialValue() {
            System.out.println(Thread.currentThread().getName() + " init pattern: " + Thread.currentThread());
            return new HashMap<String, SimpleDateFormat>();
        }
    };

    private static SimpleDateFormat getSimpleDateFormat(final String pattern) {
        Map<String, SimpleDateFormat> map = dateFormatMap.get();
        SimpleDateFormat simpleDateFormat = map.get(pattern);
        if (simpleDateFormat == null) {
            System.out.println(Thread.currentThread().getName() + " put new simpleDateFormat of pattern " + pattern + " to map");
            simpleDateFormat = new SimpleDateFormat(pattern);
            map.put(pattern, simpleDateFormat);
        }
        return simpleDateFormat;
    }

    public static String format(Date date, String pattern) {
        return getSimpleDateFormat(pattern).format(date);
    }

    public static Date parse(String date, String pattern) throws ParseException {
        return getSimpleDateFormat(pattern).parse(date);
    }

}