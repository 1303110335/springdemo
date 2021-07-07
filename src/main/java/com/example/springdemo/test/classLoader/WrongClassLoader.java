/**
 * fshows.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.example.springdemo.test.classLoader;

/**
 * @author xuleyan
 * @version WrongClassLoader.java, v 0.1 2019-09-29 4:37 PM xuleyan
 */
public class WrongClassLoader extends ClassLoader {


    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        return super.findClass(name);
    }

}