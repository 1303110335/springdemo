/**
 * fshows.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.example.springdemo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xuleyan
 * @version BeanController.java, v 0.1 2019-10-08 4:58 PM xuleyan
 */
@RestController
//@Scope("prototype")
public class BeanController {
    private static ThreadLocal<Integer> number = new ThreadLocal<Integer>() {
        @Override
        protected Integer initialValue() {
            return (int) (Math.random() * 10 + 100);
        }
    };
    /**
     * 基本类型  线程不安全
     */
    private int content = 0;
    /**
     * 引用类型  线程不安全
     */
    private String test = null;

    @RequestMapping("testBean")
    public Object getSercurity() {
        System.out.println(content);
        System.out.println(test);
        content = 20;
        test = "单例模式是不安全的";
        return test;
    }

    @RequestMapping("testNumber")
    public Object getNumber() {
        System.out.println(number.get());
        return number.get();
    }

}