/**
 * fshows.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.example.springdemo.test.queue;

/**
 * @author xuleyan
 * @version SingleT.java, v 0.1 2019-12-09 9:08 AM xuleyan
 */
public class SingleT {

    private static SingleT instance = new SingleT();

    public static SingleT getInstance() {
        return new SingleT();
    }
}