/**
 * fshows.com
 * Copyright (C) 2013-2020 All Rights Reserved.
 */
package com.example.springdemo.design.mode.abstractFactory;

import com.example.springdemo.design.mode.abstractFactory.color.Color;
import com.example.springdemo.design.mode.abstractFactory.color.impl.Blue;
import com.example.springdemo.design.mode.abstractFactory.color.impl.Green;
import com.example.springdemo.design.mode.abstractFactory.color.impl.Red;
import com.example.springdemo.design.mode.abstractFactory.shape.Shape;

/**
 * @author xuleyan
 * @version ColorFactory.java, v 0.1 2020-05-05 9:36 PM xuleyan
 */
public class ColorFactory extends AbstractFactory {

    @Override
    public Shape getShape(String shapeType) {
        return null;
    }

    @Override
    public Color getColor(String color) {
        if (color == null) {
            return null;
        }
        if (color.equalsIgnoreCase("RED")) {
            return new Red();
        } else if (color.equalsIgnoreCase("GREEN")) {
            return new Green();
        } else if (color.equalsIgnoreCase("BLUE")) {
            return new Blue();
        }
        return null;
    }
}