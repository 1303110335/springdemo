/**
 * fshows.com
 * Copyright (C) 2013-2020 All Rights Reserved.
 */
package com.example.springdemo.design.mode.abstractFactory;

import com.example.springdemo.design.mode.abstractFactory.color.Color;
import com.example.springdemo.design.mode.abstractFactory.shape.Shape;

/**
 * 为 Color 和 Shape 对象创建抽象类来获取工厂。
 *
 * @author xuleyan
 * @version AbstractFactory.java, v 0.1 2020-05-05 9:35 PM xuleyan
 */
public abstract class AbstractFactory {
    public abstract Color getColor(String color);

    public abstract Shape getShape(String shape);
}