/**
 * fshows.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.example.springdemo.test.singleton;

/**
 * @author xuleyan
 * @version Singleton2.java, v 0.1 2019-06-26 11:50 AM xuleyan
 */
public class Singleton2 {
    private Singleton2() {
    }

    /**
     * 再说静态内部类，这种方式和两种饿汉方式只有细微差别，只是做法上稍微优雅一点。
     * 这种方式是Singleton类被装载了，instance不一定被初始化。因为SingletonHolder类没有被主动使用，
     * 只有显示通过调用getInstance方法时，才会显示装载SingletonHolder类，从而实例化instance。。。
     * 但是，原理和饿汉一样。
     *
     * @return
     */
    public static final Singleton2 getInstance() {
        return singletonHolder.INSTANCE;
    }

    // 静态内部类
    private static class singletonHolder {
        private static final Singleton2 INSTANCE = new Singleton2();
    }
}