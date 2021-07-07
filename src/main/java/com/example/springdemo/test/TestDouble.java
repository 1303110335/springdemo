/**
 * fshows.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.example.springdemo.test;

/**
 * @author xuleyan
 * @version TestDouble.java, v 0.1 2019-03-13 8:30 AM xuleyan
 */
public class TestDouble {

    public static void main(String[] args) {
        // 32位的浮点数由3部分组成：1比特的符号位，8比特的阶码（exponent,指数），23比特的尾数（Mantissa，尾数）。
        // 这个结构会表示成一个小数点左边为1，以底数为2的科学计数法表示的二进制小数。浮点数的能表示的数据大小范围由阶码决定，
        // 但是能够表示的精度完全取决于尾数的长度。long的最大值是2的64次方减1，需要63个二进制位表示，即便是double，
        // 52位的尾数也无法完整的表示long的最大值。不能表示的部分也就只能被舍去了。对于金额，舍去不能表示的部分，损失也就产生了
        long   l     = Long.MAX_VALUE;
        double d     = l / 1.0;
        double clone = d;

        System.out.println(d);
        for (int i = 0; i < 1000000000; i++) {
            clone += 1;
        }
        System.out.println(clone);
        System.out.println(clone == d);
    }
}