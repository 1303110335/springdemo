/**
 * fshows.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.example.springdemo.test;

/**
 * @author xuleyan
 * @version TestDouble2.java, v 0.1 2019-03-13 8:37 AM xuleyan
 */
public class TestDouble2 {

    public static void main(String[] args) {
        // 进制转换有关。十进制的0.1在二进制下将是一个无线循环小数
        float increment = 0.1f;
        float expected = 1;
        float sum = 0;
        for (int i = 0; i < 10; i++) {
            sum += increment;
            System.out.println(sum);
        }

        if (expected == sum) {
            System.out.println("equal");
        } else {
            System.out.println("not equal ");
        }
    }
}