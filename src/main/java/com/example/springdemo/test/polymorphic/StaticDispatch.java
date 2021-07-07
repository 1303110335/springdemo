/**
 * fshows.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.example.springdemo.test.polymorphic;

/**
 * 方法静态分派掩饰
 *
 * @author xuleyan
 * @version StaticDispatch.java, v 0.1 2019-07-04 11:03 AM xuleyan
 */
public class StaticDispatch {
    public static void main(String[] args) {
        AbstractHuman man = new Man();
        AbstractHuman women = new Women();
        StaticDispatch sr = new StaticDispatch();
        sr.sayHello((Man) man);
        sr.sayHello(women);
    }

    public void sayHello(AbstractHuman guy) {
        System.out.println("hello,guy!");
    }

    public void sayHello(Man guy) {
        System.out.println("hello,gentleman!");
    }

    public void sayHello(Women guy) {
        System.out.println("hello,lady!");
    }

    static abstract class AbstractHuman {

    }

    static class Man extends AbstractHuman {

    }

    static class Women extends AbstractHuman {

    }
}