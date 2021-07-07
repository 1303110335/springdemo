/**
 * fshows.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.example.springdemo.test.reflect;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * @author xuleyan
 * @version Init.java, v 0.1 2019-04-04 9:00 AM xuleyan
 */
public class Init {

    public static Properties getPro() throws IOException {
        Properties pro = new Properties();
        File f = new File("fruit.properties");
        if (f.exists()) {
            pro.load(new FileInputStream(f));
        } else {
            pro.setProperty("apple", "com.example.springdemo.test.reflect.Apple");
            pro.setProperty("orange", "com.example.springdemo.test.reflect.Orange");
            pro.setProperty("banana", "com.example.springdemo.test.reflect.Banana");
            pro.store(new FileOutputStream(f), "FRUIT CLASS");
        }
        return pro;
    }
}