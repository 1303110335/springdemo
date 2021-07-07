/**
 * fshows.com
 * Copyright (C) 2013-2020 All Rights Reserved.
 */
package com.example.springdemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author xuleyan
 * @version UsersService.java, v 0.1 2020-02-28 4:16 PM xuleyan
 */
@Service
public class UserService {

    @Autowired
    private TestService testService;

    public void doTest() {
        System.out.println("this is userService");

        testService.sayAge();
    }
}