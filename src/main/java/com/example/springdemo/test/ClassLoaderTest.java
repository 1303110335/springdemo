/**
 * fshows.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.example.springdemo.test;

/**
 * @author xuleyan
 * @version ClassLoaderTest.java, v 0.1 2019-09-29 4:06 PM xuleyan
 */
public class ClassLoaderTest {

    public static void main(String[] args) {
        try {
            //查看当前系统类路径中包含的路径条目
            System.out.println(System.getProperty("java.class.path"));
            //调用加载当前类的类加载器（这里即为系统类加载器）加载TestBean
            Class typeLoaded = Class.forName("com.example.springdemo.domain.User");
            //查看被加载的TestBean类型是被那个类加载器加载的
            System.out.println(typeLoaded.getClassLoader());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}