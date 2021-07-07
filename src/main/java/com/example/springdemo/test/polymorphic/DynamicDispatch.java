/**
 * fshows.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.example.springdemo.test.polymorphic;

/**
 * 方法动态分派演示
 *
 * @author xuleyan
 * @version DynamicDispatch.java, v 0.1 2019-07-04 11:25 AM xuleyan
 */
public class DynamicDispatch {

    public static void main(String[] args) {
        Human man = new Man();
        Human women = new Women();
        man.sayHello();
        women.sayHello();

        man = new Women();
        man.sayHello();
    }

    static abstract class Human {
        protected abstract void sayHello();
    }

    static class Man extends Human {

        @Override
        protected void sayHello() {
            System.out.println("man say hello");
        }
    }

    static class Women extends Human {

        @Override
        protected void sayHello() {
            System.out.println("women say hello");
        }
    }
}