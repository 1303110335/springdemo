/**
 * fshows.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.example.springdemo.domain;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import java.io.Serializable;

/**
 * @author xuleyan
 * @version Student.java, v 0.1 2019-10-08 1:56 PM xuleyan
 * @Description:一个学生类(Bean)，能体现其生命周期的Bean
 */
public class Student implements BeanNameAware, DisposableBean, InitializingBean, BeanFactoryAware, Serializable {
    private String name;

    private String beanId;

    private BeanFactory beanFactory;

    //无参构造方法
    public Student() {
        super();
    }

    /**
     * 设置对象属性
     *
     * @param name the name to set
     */
    public void setName(String name) {
        System.out.println("设置对象属性setName().." + name);
        this.name = name;
    }

    //调用BeanNameAware的setBeanName()传递Bean的ID。
    @Override
    public void setBeanName(String name) {
        this.beanId = name;
        System.out.println("调用BeanNameAware的setBeanName()...");
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("【BeanFactoryAware接口】调用BeanFactoryAware.setBeanFactory()");
        this.beanFactory = beanFactory;
    }

    // postProcessBeforeInitialization

    @Override
    public void afterPropertiesSet() {
        System.out.println("【InitializingBean接口】调用InitializingBean.afterPropertiesSet()");
    }

    // postProcessAfterInitialization

    //Bean的初始化方法
    //注解：@PostConstruct
    public void initStudent() {
        System.out.println("Student这个Bean：初始化");
    }


    //Bean的使用
    public void play() {
        System.out.println("Student这个Bean：使用");
    }

    /**
     * 重写toString
     *
     * @return
     */
    @Override
    public String toString() {
        return "Student [name = " + name + "]";
    }

    @Override
    public void destroy() {
        System.out.println("【DiposibleBean接口】调用DiposibleBean.destory()");
    }

    //Bean的销毁方法
    //注解：@PreDestroy
    public void destroyStudent() {
        System.out.println("Student这个Bean：销毁");
    }


}