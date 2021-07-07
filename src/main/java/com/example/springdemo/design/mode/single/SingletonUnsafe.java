/**
 * fshows.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.example.springdemo.design.mode.single;

/**
 * 懒汉，线程不安全，当线程并发的情况下，回创建多个实例
 *
 * @author xuleyan
 * @version SingletonUnsafe.java, v 0.1 2019-09-23 3:34 PM xuleyan
 */
public class SingletonUnsafe {

    private static SingletonUnsafe instance;

    private SingletonUnsafe() {
    }

    public static SingletonUnsafe getInstance() {
        if (instance == null) {
            instance = new SingletonUnsafe();
        }
        return instance;
    }


}