/**
 * fshows.com
 * Copyright (C) 2013-2020 All Rights Reserved.
 */
package com.example.springdemo.test;

/**
 * @author xuleyan
 * @version TestString.java, v 0.1 2020-05-21 8:29 PM xuleyan
 */

public class Main {
    public static void main(String[] args) {

        // 超过1个的都变成两个，再将
        System.out.println("helllooo"
                .replaceAll("(.)\\1+", "$1$1")
                .replaceAll("(.)\\1(.)\\2", "$1$1$2"));

    }
}
