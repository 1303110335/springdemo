/**
 * fshows.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.example.springdemo.test.vector;

/**
 * @author xuleyan
 * @version TestString.java, v 0.1 2019-09-30 4:00 PM xuleyan
 */
public class TestString {

    public static void main(String[] args) throws InterruptedException {
        String str = "ab";

        changeString(str);
        System.out.println("main >> str=" + System.identityHashCode(str));
        System.out.println("main >> str=" + str);
    }

    private static void changeString(String str) {
        str = "cd";
        System.out.println("str=" + System.identityHashCode(str));
        System.out.println(str);
    }
}