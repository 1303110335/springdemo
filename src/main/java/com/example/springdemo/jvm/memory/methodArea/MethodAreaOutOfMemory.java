/**
 * fshows.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.example.springdemo.jvm.memory.methodArea;

import com.example.springdemo.jvm.memory.heap.TestCase;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @Described：方法区溢出测试
 * 使用技术 CBlib
 * @VM args : -XX:PermSize=10M -XX:MaxPermSize=10M
 * @author xuleyan
 * @version MethodAreaOutOfMemory.java, v 0.1 2019-04-25 9:25 PM xuleyan
 */
public class MethodAreaOutOfMemory {

    public static void main(String[] args) {
        while (true) {
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(TestCase.class);
            enhancer.setUseCache(false);
            enhancer.setCallback(new MethodInterceptor() {
                @Override
                public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                    return methodProxy.invoke(o, objects);
                }
            });
            enhancer.create();
        }
    }

}