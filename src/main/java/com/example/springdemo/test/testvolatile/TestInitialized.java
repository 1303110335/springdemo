/**
 * fshows.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.example.springdemo.test.testvolatile;

import java.util.Map;

/**
 * @author xuleyan
 * @version TestInitialized.java, v 0.1 2019-07-28 6:07 PM xuleyan
 */
public class TestInitialized {

    Map configOptions;
    char[] configText;
    // 此变量必须定义为volatile
    volatile boolean initialized = false;

    protected static void main(String[] args) {
        Integer a = new Integer(123);
        StringBuffer sb = new StringBuffer();
        Integer b = new Integer(2);
//        Integer.parseInt()


        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

            }
        });

    }


    // 假设一下代码在线程A中执行
    // 模拟读配置信息，当读取完成之后将initialized设置为true以通知其他线程配置可用
//    configOptions = new HashMap();
//    configText = readConfigFile(fileName);
//    processConfigOptions(configText, configOptions);
//    initialized = true;
//
//
//
//    // 假设一下代码在线程B中进行
//    while (!initialized) {
//        sleep();
//    }
//    doSomethingWithConfig();

}


class A {
    private String name;

    /**
     * Getter method for property <tt>name</tt>.
     *
     * @return property value of name
     */
    public String getName() {
        return name;
    }

    /**
     * Setter method for property <tt>name</tt>.
     *
     * @param name value to be assigned to property name
     */
    public void setName(String name) {
        this.name = name;
    }
}

class B extends A {

    public static void main(String[] args) {

        B b = new B();
        b.getName();
        b.setName("name");
    }
}