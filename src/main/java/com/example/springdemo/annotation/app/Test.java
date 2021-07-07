/**
 * fshows.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.example.springdemo.annotation.app;

import com.example.springdemo.annotation.check.UserCheck;
import com.example.springdemo.annotation.domain.User;

/**
 * @author xuleyan
 * @version Test.java, v 0.1 2019-04-22 8:52 AM xuleyan
 */
public class Test {

    public static void main(String[] args) {

        User user = new User();

        user.setName("liang666666");
        user.setAge("1000");

        System.out.println(UserCheck.check(user));
    }
}