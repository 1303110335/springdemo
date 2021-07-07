/**
 * fshows.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.example.springdemo.test.classLoader;

/**
 * @author xuleyan
 * @version WrongClassLoaderTest.java, v 0.1 2019-09-29 4:39 PM xuleyan
 */
public class WrongClassLoaderTest {

    public static void main(String[] args) {

//        try {
//            WrongClassLoader loader = new WrongClassLoader();
//            Class classLoaded = loader.loadClass("com.example.springdemo.domain.User");
//            System.out.println(classLoaded.getName());
//            System.out.println(classLoaded.getClassLoader());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        WrongClassLoaderTest wrongClassLoaderTest = new WrongClassLoaderTest();
        System.out.println(wrongClassLoaderTest.getClass().getClassLoader());
    }
}