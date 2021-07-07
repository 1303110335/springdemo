/**
 * fshows.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.example.springdemo.test;

/**
 * @author xuleyan
 * @version TestBeanUtils.java, v 0.1 2019-03-21 5:44 PM xuleyan
 */
public class TestBeanUtils {

    public static void main(String[] args) {
//        User user = new User();
//        user.setName("tom");
//        user.setAge(21);
//
//        try {
//            User user2 = (User) BeanUtils.cloneBean(user);
//            System.out.println(user2);
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (InstantiationException e) {
//            e.printStackTrace();
//        } catch (InvocationTargetException e) {
//            e.printStackTrace();
//        } catch (NoSuchMethodException e) {
//            e.printStackTrace();
//        }

//        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
//        System.out.println(classLoader);
        System.out.println(ClassLoader.getSystemClassLoader());
        System.out.println(ClassLoader.getSystemClassLoader().getParent());
        System.out.println(ClassLoader.getSystemClassLoader().getParent().getParent());


//        Map<String, String> map = new HashMap<>();
//        map.put("name","tom");
//        map.put("age","21");
//
//        User user = new User();
//        try {
//            BeanUtils.populate(user, map);
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (InvocationTargetException e) {
//            e.printStackTrace();
//        }
//        System.out.println(user);
//
//        try {
//            Map map2 = BeanUtils.describe(user);
//            System.out.println(map2);
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (InvocationTargetException e) {
//            e.printStackTrace();
//        } catch (NoSuchMethodException e) {
//            e.printStackTrace();
//        }
//
//        try {
//            Class<?> contextClass = Class.forName("com.example.springdemo.domain.User");
//            User user1 = (User) org.springframework.beans.BeanUtils.instantiateClass(contextClass);
//            System.out.println(user1);
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }


    }
}