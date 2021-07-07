/**
 * fshows.com
 * Copyright (C) 2013-2020 All Rights Reserved.
 */
package com.example.springdemo.design.mode.abstractFactory.shape.impl;

import com.example.springdemo.design.mode.abstractFactory.shape.Shape;

/**
 * @author xuleyan
 * @version Square.java, v 0.1 2020-05-05 9:32 PM xuleyan
 */
public class Square implements Shape {

    @Override
    public void draw() {
        System.out.println("Inside Square::draw() method.");
    }
}