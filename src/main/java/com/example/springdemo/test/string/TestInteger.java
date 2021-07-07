/**
 * fshows.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.example.springdemo.test.string;

/**
 * @author xuleyan
 * @version TestInteger.java, v 0.1 2019-09-30 4:59 PM xuleyan
 */
public class TestInteger {

    public static void main(String[] args) {
        // 1.在栈中创建一个num的变量，赋值为0
        int num = 0;
        // 2.将num的值传递给changeNum方法
        changeNum(num);
        System.out.println("main >> num=" + System.identityHashCode(num));
        System.out.println("num=" + num);
    }

    // 3.在栈中创建一个num'的变量来接受传递过来的num的值
    private static void changeNum(int num) {
        // 4.将num'的变量赋值为1
        num = 1;
        System.out.println("num=" + System.identityHashCode(num));
    }
}