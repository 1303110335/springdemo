/**
 * fshows.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.example.springdemo.test;

import com.fshows.fsframework.core.utils.FsDateUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author xuleyan
 * @version TestFor.java, v 0.1 2019-02-18 10:33 AM xuleyan
 */
public class TestFor {

    private static int integer;

    private static Boolean flag;

    public static void main(String[] args) throws ParseException {
//        System.out.println(lambdaMaxInteger());

//        System.out.println(integer);

//        System.out.println(flag);

        SimpleDateFormat format = new SimpleDateFormat(FsDateUtil.SIMPLE_DATETIME_FORMAT);
        long it = 1403101955000L;
        String d = format.format(it);
        System.out.println(new Date(1403101955 * 1000));
        System.out.println(d);
        System.out.println(format.parse(d));
    }

//    public static int lambdaMaxInteger() {
//        List<Integer> integers = new ArrayList<>();
//        integers.add(0);
//        integers.add(2);
//        integers.add(4);
//        integers.add(3);
//        return integers.stream().reduce(Integer.MIN_VALUE, (a, b) -> Integer.max(a, b));
//    }
}