/**
 * fshows.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.example.springdemo.test.atomic;

import com.example.springdemo.annotation.domain.User;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * @author xuleyan
 * @version AtomicDemo.java, v 0.1 2019-11-30 11:36 PM xuleyan
 */
public class AtomicDemo {

    private static AtomicIntegerFieldUpdater updater = AtomicIntegerFieldUpdater.newUpdater(User.class, "number");

    public static void main(String[] args) {
        User user = new User("a", 1);
        int oldValue = updater.getAndAdd(user, 5);
        System.out.println(oldValue);
        System.out.println(updater.get(user));

    }
}