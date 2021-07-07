/**
 * fshows.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.example.springdemo.test.testenum;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;

/**
 * @author xuleyan
 * @version SingletonHungry.java, v 0.1 2019-03-31 6:19 PM xuleyan
 */
public class SingletonHungry implements Serializable {

    private static final long serialVersionUID = -1115601442470145812L;

    private static SingletonHungry instance = new SingletonHungry();

    private SingletonHungry() {}

    private SingletonHungry(String name) {
        this.name = name;
    }

    /**
     * Getter method for property <tt>name</tt>.
     *
     * @return property value of name
     */
    public String getName() {
        return name;
    }

    /**
     * Setter method for property <tt>name</tt>.
     *
     * @param name value to be assigned to property name
     */
    public void setName(String name) {
        this.name = name;
    }

    private String name;

    public static SingletonHungry getInstance() {
        return instance;
    }

    public static void main(String[] args) {
        SingletonHungry singletonHungry = SingletonHungry.getInstance();
        singletonHungry.setName("xly");
        System.out.println(singletonHungry);
        String str = JSONObject.toJSONString(singletonHungry);
        System.out.println(str);
        SingletonHungry singletonHungry2 = JSON.parseObject(str, SingletonHungry.class);
        singletonHungry.setName("haha2");
        System.out.println(singletonHungry2.toString());
        System.out.println(singletonHungry.equals(singletonHungry2));


    }
}