/**
 * fshows.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.example.springdemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author xuleyan
 * @version TestService.java, v 0.1 2019-05-09 9:49 AM xuleyan
 */
@Service
public class TestService {

    @Autowired
    private UserService userService;

    private Integer age = 0;

    public void sayAge() {
        age = age + 1;
        System.out.println(age);
    }

    public void doTest() {
        System.out.println("this is testService");

        userService.doTest();
    }

}