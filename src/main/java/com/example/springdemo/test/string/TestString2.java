/**
 * xuleyan
 * Copyright (C) 2013-2021 All Rights Reserved.
 */
package com.example.springdemo.test.string;

import java.util.Random;

/**
 * @author xuleyan
 * @version TestString2.java, v 0.1 2021-05-15 12:47 PM xuleyan
 */
public class TestString2 {

    private static final int MAX = 1000 * 10000;
    private static final String[] ARRAY = new String[MAX];

    public static void main(String[] args) {
        Integer[] dataArr = new Integer[10];
        Random random = new Random(10 * 10000);
        for (int i = 0; i < dataArr.length; i++) {
            dataArr[i] = random.nextInt();
        }
        long t = System.currentTimeMillis();
        for (int i = 0; i < MAX; i++) {
            // intern返回的是常量池中的地址，虽然创建了很多对象但是很快会被回收，因为没有被引用
            ARRAY[i] = String.valueOf(dataArr[i % dataArr.length]).intern();
        }
        System.out.println((System.currentTimeMillis() - t) + "ms");
        System.gc();


    }

}