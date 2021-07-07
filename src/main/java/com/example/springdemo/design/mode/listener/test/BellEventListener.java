/**
 * fshows.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.example.springdemo.design.mode.listener.test;

import java.util.EventListener;

/**
 * @author xuleyan
 * @version BellEventListener.java, v 0.1 2019-09-23 4:11 PM xuleyan
 */
public interface BellEventListener extends EventListener {

    /**
     * 听到铃声后的时间处理方法
     *
     * @param event
     */
    public void heardBell(RingEvent event);
}