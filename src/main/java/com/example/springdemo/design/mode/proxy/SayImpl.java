/**
 * fshows.com
 * Copyright (C) 2013-2020 All Rights Reserved.
 */
package com.example.springdemo.design.mode.proxy;

import com.example.springdemo.design.mode.proxy.inter.Say;

/**
 * @author xuleyan
 * @version SayImpl.java, v 0.1 2020-01-18 2:00 PM xuleyan
 */
public class SayImpl implements Say {

    @Override
    public void sayHello(String words) {
        System.out.println("hello:" + words);
    }
}