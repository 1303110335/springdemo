/**
 * fshows.com
 * Copyright (C) 2013-2020 All Rights Reserved.
 */
package com.example.springdemo.design.mode.abstractFactory.shape.impl;

import com.example.springdemo.design.mode.abstractFactory.shape.Shape;

/**
 * @author xuleyan
 * @version Circle.java, v 0.1 2020-05-05 9:33 PM xuleyan
 */
public class Circle implements Shape {

    @Override
    public void draw() {
        System.out.println("Inside Circle::draw() method.");
    }
}