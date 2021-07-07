/**
 * fshows.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.example.springdemo.design.mode.listener.test.impl;

import com.example.springdemo.design.mode.listener.test.BellEventListener;
import com.example.springdemo.design.mode.listener.test.RingEvent;

/**
 * @author xuleyan
 * @version StuEventListener.java, v 0.1 2019-09-23 4:31 PM xuleyan
 */
public class StuEventListener implements BellEventListener {
    @Override
    public void heardBell(RingEvent e) {
        if (e.isSound()) {
            System.out.println("同学们，上课了...");
        } else {
            System.out.println("同学们，下课了...");
        }
    }
}