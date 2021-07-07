/**
 * fshows.com
 * Copyright (C) 2013-2020 All Rights Reserved.
 */
package com.example.springdemo.aspect.server;

import org.springframework.stereotype.Service;

/**
 * @author xuleyan
 * @version UsersServiceImpl.java, v 0.1 2020-04-04 5:52 PM xuleyan
 */
@Service("userService2")
public class Users2ServiceImpl implements Users2Service {
    @Override
    public String queryUserById(int id) {
        return "user home";
    }
}