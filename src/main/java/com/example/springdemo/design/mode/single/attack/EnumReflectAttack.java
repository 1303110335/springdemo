/**
 * fshows.com
 * Copyright (C) 2013-2020 All Rights Reserved.
 */
package com.example.springdemo.design.mode.single.attack;

import com.example.springdemo.design.mode.single.SingletonEnum;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @author xuleyan
 * @version ReflectAttack.java, v 0.1 2020-01-18 10:34 AM xuleyan
 */
public class EnumReflectAttack {

    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {

        attack();
    }

    private static void attack() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        // 通过反射获取单利类的私有构造器
        Constructor constructor = SingletonEnum.class.getDeclaredConstructor();
        // 设置私有成员的暴力破解
        constructor.setAccessible(true);
        // 通过反射去创建单例类的多个不同的实例
        Object newInstance = constructor.newInstance();
        Object newInstance2 = constructor.newInstance();
        ((SingletonEnum) newInstance).tellEveryOne();
        ((SingletonEnum) newInstance2).tellEveryOne();

    }
}