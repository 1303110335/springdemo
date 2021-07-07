/**
 * fshows.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.example.springdemo.test.singleton;

/**
 * @author xuleyan
 * @version Singleton3.java, v 0.1 2019-06-26 11:54 AM xuleyan
 */
public class Singleton3 {
    private static Singleton3 instance = new Singleton3();

    private Singleton3() {
    }

    public static Singleton3 getInstance() {
        return instance;
    }
}