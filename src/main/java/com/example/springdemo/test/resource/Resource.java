/**
 * fshows.com
 * Copyright (C) 2013-2020 All Rights Reserved.
 */
package com.example.springdemo.test.resource;

import java.io.Closeable;
import java.io.IOException;

/**
 * @author xuleyan
 * @version Resource.java, v 0.1 2020-05-21 4:26 PM xuleyan
 */
public class Resource implements Closeable {

    public void sayHello() {
        System.out.println("hello");
    }

    @Override
    public void close() throws IOException {
        System.out.println("this is close method");
    }
}