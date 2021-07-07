/**
 * fshows.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.example.springdemo.test.proxy;

/**
 * @author xuleyan
 * @version MainClass.java, v 0.1 2019-04-08 8:27 PM xuleyan
 */
public class MainClass {

    public static void main(String[] args) {

        long start = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            JdkProxyTest proxyTest = new JdkProxyTest();
            Worker workerProxy = (Worker) proxyTest.bind(new Programmer());
            workerProxy.doWork();
        }
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + "ms");//10000 - 223ms  100000 - 503ms
    }
}