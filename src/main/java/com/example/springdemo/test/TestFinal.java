/**
 * fshows.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.example.springdemo.test;

import com.example.springdemo.domain.User;

/**
 * @author xuleyan
 * @version TestFinal.java, v 0.1 2019-09-25 10:49 AM xuleyan
 */
public class TestFinal {

    // 使用final修饰对象
    private final User user = new User();

    public static void main(String[] args) {

        TestFinal testFinal = new TestFinal();
        testFinal.print();

    }


    public void print() {
        System.out.println(user);
        // 可以修改对象的内容
        user.setAge(13);
        System.out.println(user);

        // 不能再指向其他的对象
        // user = new User();


    }
}