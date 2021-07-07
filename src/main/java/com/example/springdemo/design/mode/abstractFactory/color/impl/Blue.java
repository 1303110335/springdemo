/**
 * fshows.com
 * Copyright (C) 2013-2020 All Rights Reserved.
 */
package com.example.springdemo.design.mode.abstractFactory.color.impl;

import com.example.springdemo.design.mode.abstractFactory.color.Color;

/**
 * @author xuleyan
 * @version Blue.java, v 0.1 2020-05-05 9:34 PM xuleyan
 */
public class Blue implements Color {

    @Override
    public void fill() {
        System.out.println("Inside Blue::fill() method.");
    }
}