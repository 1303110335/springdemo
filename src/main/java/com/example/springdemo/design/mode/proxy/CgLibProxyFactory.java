/**
 * fshows.com
 * Copyright (C) 2013-2020 All Rights Reserved.
 */
package com.example.springdemo.design.mode.proxy;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author xuleyan
 * @version CgLibProxyFactory.java, v 0.1 2020-01-18 2:08 PM xuleyan
 */
public class CgLibProxyFactory implements MethodInterceptor {

    public static void main(String[] args) {
        CgLibProxyFactory cgLibProxyFactory = new CgLibProxyFactory();
        SayImpl say = (SayImpl) cgLibProxyFactory.getProxyByCgLib(SayImpl.class);
        say.sayHello("fasdfasdf");
    }

    /**
     * @param clazz
     * @return
     */
    public Object getProxyByCgLib(Class clazz) {
        // 创建增强器
        Enhancer enhancer = new Enhancer();
        // 设置需要增强的类的类对象
        enhancer.setSuperclass(clazz);
        // 设置回调函数
        enhancer.setCallback(this);
        //  获取增强之后的代理对象
        return enhancer.create();
    }

    /**
     * @param proxy       代理对象
     * @param method      目标对象的方法
     * @param args        参数
     * @param methodProxy 代理对象的方法
     * @return
     * @throws Throwable
     */
    @Override
    public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {

        System.out.println("这是cglib的代理方法");

        // 通过调用子类【代理类】的invokeSuper方法，去实际调用目标对象的方法
        Object returnValue = methodProxy.invokeSuper(proxy, args);
        return returnValue;
    }
}