/**
 * fshows.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.example.springdemo.config;

import org.springframework.boot.CommandLineRunner;

/**
 * @author xuleyan
 * @version MyCommandLineRunner1.java, v 0.1 2019-02-17 6:36 PM xuleyan
 */
//@Configuration
public class MyCommandLineRunner1 implements CommandLineRunner {
    @Override
    public void run(String... strings) throws Exception {
        System.out.println("MyCommandLineRunner1.run()");
    }
}