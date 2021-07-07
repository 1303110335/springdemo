/**
 * fshows.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.example.springdemo.design.mode.single;

/**
 * 懒汉线程安全模式
 *
 * @author xuleyan
 * @version SingletonSafe.java, v 0.1 2019-09-23 3:35 PM xuleyan
 */
public class SingletonSafe {

    private static SingletonSafe instance;

    private SingletonSafe() {
    }

    public static synchronized SingletonSafe getInstance() {
        if (instance == null) {
            instance = new SingletonSafe();
        }
        return instance;
    }
}