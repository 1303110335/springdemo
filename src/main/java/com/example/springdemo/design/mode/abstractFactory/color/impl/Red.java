/**
 * fshows.com
 * Copyright (C) 2013-2020 All Rights Reserved.
 */
package com.example.springdemo.design.mode.abstractFactory.color.impl;

import com.example.springdemo.design.mode.abstractFactory.color.Color;

/**
 * @author xuleyan
 * @version Red.java, v 0.1 2020-05-05 9:34 PM xuleyan
 */
public class Red implements Color {

    @Override
    public void fill() {
        System.out.println("Inside Red::fill() method.");
    }
}