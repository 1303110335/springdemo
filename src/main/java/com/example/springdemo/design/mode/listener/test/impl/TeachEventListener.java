/**
 * fshows.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.example.springdemo.design.mode.listener.test.impl;

import com.example.springdemo.design.mode.listener.test.BellEventListener;
import com.example.springdemo.design.mode.listener.test.RingEvent;

/**
 * @author xuleyan
 * @version TeachEventListener.java, v 0.1 2019-09-23 4:29 PM xuleyan
 */
public class TeachEventListener implements BellEventListener {
    @Override
    public void heardBell(RingEvent event) {
        if (event.isSound()) {
            System.out.println("老师上课了...");
        } else {
            System.out.println("老师下课了...");
        }
    }
}