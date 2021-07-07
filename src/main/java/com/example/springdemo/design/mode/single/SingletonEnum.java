/**
 * fshows.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.example.springdemo.design.mode.single;

/**
 * 我们可以通过EasySingleton.INSTANCE来访问实例，这比调用getInstance()方法简单多了。
 * 创建枚举默认就是线程安全的，所以不需要担心double checked locking，而且还能防止反序列化导致重新创建新的对象。
 * 但是还是很少看到有人这样写，可能是因为不太熟悉吧。
 *
 * @author xuleyan
 * @version SingletonEnum.java, v 0.1 2019-09-23 3:50 PM xuleyan
 */
public enum SingletonEnum {
    INSTANCE;

    public void tellEveryOne() {
        System.out.println("This is a DoubleCheckLockSingleton" + this.hashCode());
    }
}