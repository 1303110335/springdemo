/**
 * fshows.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.example.springdemo.test.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * jdk动态代理
 * @author xuleyan
 * @version JdkProxyTest.java, v 0.1 2019-04-08 8:20 PM xuleyan
 */
public class JdkProxyTest implements InvocationHandler {
    // 真实对象
    private Object target = null;

    /**
     * 将真实对象和代理对象建立关系，通过真实对象来返回一个代理对象
     * @param target
     * @return
     */
    public Object bind(Object target) {
        this.target = target;
        /**
         * 参数1：类加载器，采用target本身的类加载器
         * 参数2：生成的动态代理对象挂在那个接口下，采用target实现的接口下，即Woker接口。
         * 参数3：定义实现方法逻辑的代理类，this表示当前对象，，它必须实现InvocationHandler的invoke方法。
         */
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);

    }

    /**
     * 代理方法逻辑
     * @param proxy 代理对象
     * @param method 当前调度方法
     * @param args 当前参数
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//        System.out.println("进入代理逻辑方法");
//        System.out.println("调度真实对象之前的服务");

        Object object = method.invoke(target, args);
//        System.out.println("调度真实对象之后的服务");

        return object;
    }
}