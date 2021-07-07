/**
 * fshows.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.example.springdemo.test.queue;

/**
 * @author xuleyan
 * @version SingleTwo.java, v 0.1 2019-12-09 9:08 AM xuleyan
 */
public class SingleTwo {
    private volatile static SingleTwo instance;

    private SingleTwo() {
    }

    public static SingleTwo getInstance() {
        if (instance == null) {
            synchronized (SingleTwo.class) {
                if (instance == null) {
                    instance = new SingleTwo();
                }
            }
        }
        return instance;
    }
}