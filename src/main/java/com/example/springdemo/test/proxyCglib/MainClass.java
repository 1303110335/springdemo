/**
 * fshows.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.example.springdemo.test.proxyCglib;

/**
 * @author xuleyan
 * @version MainClass.java, v 0.1 2019-04-08 8:52 PM xuleyan
 */
public class MainClass {

    public static void main(String[] args) {

        long start = System.currentTimeMillis();
        for (int i = 0; i < 1; i++) {
            CglibTest cglibTest = new CglibTest();
            StudentService studentService = (StudentService) cglibTest.getProxy(StudentService.class);
            studentService.study();
        }
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + "ms");//10000 - 288ms  100000 - 702ms
    }
}