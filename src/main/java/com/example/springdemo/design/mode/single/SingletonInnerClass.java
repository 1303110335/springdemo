/**
 * fshows.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.example.springdemo.design.mode.single;

/**
 * 静态内部类 static nested class
 * <p>
 * 这种写法仍然使用JVM本身机制保证了线程安全问题；由于 SingletonHolder 是私有的，除了 getInstance() 之外没有办法访问它，因此它是懒汉式的；
 * 同时读取实例的时候不会进行同步，没有性能缺陷；也不依赖 JDK 版本。
 *
 * @author xuleyan
 * @version SingletonInnerClass.java, v 0.1 2019-09-23 3:47 PM xuleyan
 */
public class SingletonInnerClass {

    private SingletonInnerClass() {
    }

    public static SingletonInnerClass getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static final SingletonInnerClass INSTANCE = new SingletonInnerClass();
    }
}