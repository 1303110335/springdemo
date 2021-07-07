/**
 * fshows.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.example.springdemo.design.mode.single;

/**
 * 饿汉式 static final field
 * 缺点是它不是一种懒加载模式（lazy initialization），单例会在加载类后一开始就被初始化，
 * 即使客户端没有调用 getInstance()方法。
 * 饿汉式的创建方式在一些场景中将无法使用：譬如 Singleton 实例的创建是依赖参数或者配置文件的，
 * 在 getInstance() 之前必须调用某个方法设置参数给它，那样这种单例写法就无法使用了
 *
 * @author xuleyan
 * @version SingletonFinal.java, v 0.1 2019-09-23 3:44 PM xuleyan
 */
public class SingletonFinal {

    private static final SingletonFinal INSTANCE = new SingletonFinal();

    private SingletonFinal() {
    }

    ;

    public static SingletonFinal getInstance() {
        return INSTANCE;
    }
}