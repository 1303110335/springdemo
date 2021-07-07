/**
 * fshows.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.example.springdemo.test.proxy;

/**
 * @author xuleyan
 * @version Programmer.java, v 0.1 2019-04-08 8:19 PM xuleyan
 */
public class Programmer implements Worker {

    @Override
    public void doWork() {
        System.out.println("写代码");
    }
}