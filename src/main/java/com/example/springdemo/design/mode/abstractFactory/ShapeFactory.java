/**
 * fshows.com
 * Copyright (C) 2013-2020 All Rights Reserved.
 */
package com.example.springdemo.design.mode.abstractFactory;

import com.example.springdemo.design.mode.abstractFactory.color.Color;
import com.example.springdemo.design.mode.abstractFactory.shape.Shape;
import com.example.springdemo.design.mode.abstractFactory.shape.impl.Circle;
import com.example.springdemo.design.mode.abstractFactory.shape.impl.Rectangle;
import com.example.springdemo.design.mode.abstractFactory.shape.impl.Square;

/**
 * @author xuleyan
 * @version ShapeFactory.java, v 0.1 2020-05-05 9:35 PM xuleyan
 */
public class ShapeFactory extends AbstractFactory {

    @Override
    public Shape getShape(String shapeType) {
        if (shapeType == null) {
            return null;
        }
        if (shapeType.equalsIgnoreCase("CIRCLE")) {
            return new Circle();
        } else if (shapeType.equalsIgnoreCase("RECTANGLE")) {
            return new Rectangle();
        } else if (shapeType.equalsIgnoreCase("SQUARE")) {
            return new Square();
        }
        return null;
    }

    @Override
    public Color getColor(String color) {
        return null;
    }
}