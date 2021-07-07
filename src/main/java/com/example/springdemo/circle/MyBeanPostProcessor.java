/**
 * fshows.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.example.springdemo.circle;

import com.example.springdemo.domain.Student;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * @author xuleyan
 * @version MyBeanPostProcessor.java, v 0.1 2019-10-08 2:04 PM xuleyan
 */
public class MyBeanPostProcessor implements BeanPostProcessor {

    /**
     * 对初始化之后的Bean进行处理
     *
     * @param bean     即将初始化的bean
     * @param beanname bean的名称
     * @return 返回值：返回给用户的那个bean,可以修改bean也可以返回一个新的bean
     * @throws BeansException
     */
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanname) throws BeansException {
        Student stu = null;
        System.out.println("对初始化之后的Bean进行处理,将Bean的成员变量的值修改了");
        if ("name".equals(beanname) || bean instanceof Student) {
            stu = (Student) bean;
            stu.setName("Jack");
        }
        return stu;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanname) throws BeansException {
        System.out.println("对初始化之前的Bean进行处理,此时我的名字" + bean);
        return bean;
    }
}