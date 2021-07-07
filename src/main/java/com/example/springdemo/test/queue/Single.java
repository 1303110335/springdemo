/**
 * fshows.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.example.springdemo.test.queue;

/**
 * @author xuleyan
 * @version Single.java, v 0.1 2019-12-09 9:06 AM xuleyan
 */
public class Single {

    private static Single instance = null;

    public static synchronized Single getInstance() {
        if (instance == null) {
            instance = new Single();
        }
        return instance;
    }
}