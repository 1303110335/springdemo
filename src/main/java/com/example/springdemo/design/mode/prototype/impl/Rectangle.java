/**
 * fshows.com
 * Copyright (C) 2013-2020 All Rights Reserved.
 */
package com.example.springdemo.design.mode.prototype.impl;

import com.example.springdemo.design.mode.prototype.Shape;

/**
 * @author xuleyan
 * @version Rectangle.java, v 0.1 2020-05-05 9:54 PM xuleyan
 */
public class Rectangle extends Shape {

    public Rectangle() {
        type = "Rectangle";
    }

    @Override
    public void draw() {
        System.out.println("Inside Rectangle::draw() method.");
    }

}