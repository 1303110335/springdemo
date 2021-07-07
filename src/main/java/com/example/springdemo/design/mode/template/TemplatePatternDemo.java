/**
 * fshows.com
 * Copyright (C) 2013-2020 All Rights Reserved.
 */
package com.example.springdemo.design.mode.template;

/**
 * 模板设计模式+回调，不需要在实现类中实现所有的抽象方法，
 * 而是使用Callback 模式与模板方法模式配合，既达到了代码复用的效果，同时增加了灵活性。
 *
 * @author xuleyan
 * @version TemplatePatternDemo.java, v 0.1 2020-05-06 8:36 AM xuleyan
 */
public class TemplatePatternDemo {
    public static void main(String[] args) {

        Game game = new Cricket();
        game.play(() -> {
            System.out.println("play cricket");
            return null;
        });
        System.out.println();
        game = new Football();
        game.play(() -> {
            System.out.println("play football");
            return null;
        });
    }
}