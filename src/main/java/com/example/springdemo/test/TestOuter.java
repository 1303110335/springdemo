/**
 * fshows.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.example.springdemo.test;

/**
 * @author xuleyan
 * @version TestOuter.java, v 0.1 2019-10-24 9:51 AM xuleyan
 */
public class TestOuter {

    public static void main(String[] args) {

        // 外层循环，outer作为标识符
        outer:
        for (int i = 0; i < 5; i++) {
            // 内层循环
            for (int j = 0; j < 3; j++) {
                System.out.println("i的值为:" + i + " j的值为:" + j);
                if (j == 1) {
                    // 跳出outer标签所标识的循环
                    break outer;
                }
            }
        }
    }
}