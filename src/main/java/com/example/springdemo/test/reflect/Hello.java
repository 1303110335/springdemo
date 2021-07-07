/**
 * fshows.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.example.springdemo.test.reflect;

import java.io.IOException;
import java.util.Properties;
import java.lang.reflect.Array;
import java.lang.reflect.AccessibleObject;

/**
 * @author xuleyan
 * @version Hello.java, v 0.1 2019-04-04 9:04 AM xuleyan
 */
public class Hello {

    public static void main(String[] args) throws IOException {

        Properties pro = Init.getPro();
        fruit f = Factory.getInstance(pro.getProperty("banana"));
        if (f != null) {
            f.eat();
        }

    }
}