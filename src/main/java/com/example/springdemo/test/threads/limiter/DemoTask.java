/**
 * fshows.com
 * Copyright (C) 2013-2020 All Rights Reserved.
 */
package com.example.springdemo.test.threads.limiter;

/**
 * @author xuleyan
 * @version DemoTask.java, v 0.1 2020-04-10 5:53 PM xuleyan
 */
public class DemoTask implements Runnable {
    private String name = null;

    public DemoTask(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Executing : " + name);
    }
}