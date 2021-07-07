/**
 * fshows.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.example.springdemo.test.copyonwrite;

/**
 * @author xuleyan
 * @version CopyOnWriteTest.java, v 0.1 2019-05-25 9:46 PM xuleyan
 */
public class CopyOnWriteTest {

    public static void main(String[] args) {

//        CopyOnWriteArrayList<Integer> lists = new CopyOnWriteArrayList<>();
//        for (int i = 0; i < 1000; i++) {
//            lists.add(i);
//        }
//
//        Thread thread = new Thread();
//        Thread thread1 = new Thread();
//
//        for (int i = 0; i < 1000; i++) {
//            System.out.println(lists.get(i));
//        }

        String name = "xly";
        System.out.println(reverse(name));

    }

    private static String reverse(String str) {
        if (str.length() <= 1) {
            return str;
        }
        return reverse(str.substring(1)) + str.charAt(0);
    }


}