/**
 * fshows.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.example.springdemo.design.mode.listener.test;

import com.example.springdemo.design.mode.listener.test.impl.StuEventListener;
import com.example.springdemo.design.mode.listener.test.impl.TeachEventListener;

/**
 * @author xuleyan
 * @version Index.java, v 0.1 2019-09-23 4:40 PM xuleyan
 */
public class Index {

    public static void main(String[] args) {
        //铃声（事件源）
        BellEventSource bell = new BellEventSource();
        bell.addPersonListener(new StuEventListener());
        bell.addPersonListener(new TeachEventListener());
        // 打上课铃声
        bell.ring(true);
        System.out.println("------------");
        // 打下课铃声
        bell.ring(false);

    }
}