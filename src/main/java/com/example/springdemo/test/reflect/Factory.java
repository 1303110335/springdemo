/**
 * fshows.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.example.springdemo.test.reflect;

/**
 * @author xuleyan
 * @version Factory.java, v 0.1 2019-04-04 9:03 AM xuleyan
 */
public class Factory {
    public static fruit getInstance(String className) {
        fruit f = null;
        try {
            f = (fruit) Class.forName(className).newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return f;
    }
}