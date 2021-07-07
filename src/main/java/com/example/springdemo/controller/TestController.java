/**
 * fshows.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.example.springdemo.controller;

import com.example.springdemo.annotation.XuApi;
import com.example.springdemo.exception.MyException;
import com.example.springdemo.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xuleyan
 * @version TestController.java, v 0.1 2019-02-14 3:48 PM xuleyan
 */
@Controller
public class TestController {

    @Autowired
    private TestService testService;

    @RequestMapping("/hello")
    public String index() throws Exception {
//        return "Hello World";
        throw new Exception("发生错误");
    }

    @RequestMapping("/json")
    public String json() throws MyException {

        testService.sayAge();
        return "123";
//        throw new MyException("发生错误2");
    }

    @XuApi(name = "lele")
    public String testXu() {
        System.out.println("lele");
        return null;
    }


    public static void main(String[] args) {
        System.out.println( 32 ^ 1);
    }
}

