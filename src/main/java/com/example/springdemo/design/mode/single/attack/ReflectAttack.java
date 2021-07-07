/**
 * fshows.com
 * Copyright (C) 2013-2020 All Rights Reserved.
 */
package com.example.springdemo.design.mode.single.attack;

import com.example.springdemo.design.mode.single.DoubleCheck;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @author xuleyan
 * @version ReflectAttack.java, v 0.1 2020-01-18 10:34 AM xuleyan
 */
public class ReflectAttack {

    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, CloneNotSupportedException {
        
        attack();
    }

    private static void attack() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, CloneNotSupportedException {
        // 通过反射获取单利类的私有构造器
        Constructor constructor = DoubleCheck.class.getDeclaredConstructor();
        // 设置私有成员的暴力破解
        constructor.setAccessible(true);
        // 通过反射去创建单例类的多个不同的实例
        Object newInstance = constructor.newInstance();
        Object newInstance2 = constructor.newInstance();
        ((DoubleCheck)newInstance).tellEveryOne();
        ((DoubleCheck)newInstance2).tellEveryOne();
    }
}