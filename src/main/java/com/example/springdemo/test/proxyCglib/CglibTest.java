/**
 * fshows.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.example.springdemo.test.proxyCglib;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author xuleyan
 * @version CglibTest.java, v 0.1 2019-04-08 8:45 PM xuleyan
 */
public class CglibTest implements MethodInterceptor {

    /**
     * 生成Cglib代理对象
     * @param cla 真是对象的class对象
     * @return Cglib代理对象
     */
    public Object getProxy(Class cla) {
        //CGLIB的增强类对象
        Enhancer enhancer = new Enhancer();
        //设置增强类型，增强真实对象
        enhancer.setSuperclass(cla);
        //定义代理逻辑对象为当前对象，要求当前对象实现MethodInterceptor中的抽象方法
        enhancer.setCallback(this);
        return enhancer.create();
    }

    /**
     *
     * @param object
     * @param method
     * @param args
     * @param methodProxy
     * @return
     * @throws Throwable
     */
    @Override
    public Object intercept(Object object, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        System.out.println("调用真实对象前");
        Object result = methodProxy.invokeSuper(object, args);
        System.out.println("调用真实对象后");
        return result;
    }
}