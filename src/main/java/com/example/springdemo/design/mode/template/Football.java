/**
 * fshows.com
 * Copyright (C) 2013-2020 All Rights Reserved.
 */
package com.example.springdemo.design.mode.template;

/**
 * @author xuleyan
 * @version Football.java, v 0.1 2020-05-06 8:35 AM xuleyan
 */
public class Football extends Game {

    @Override
    void endPlay() {
        System.out.println("Football Game Finished!");
    }

    @Override
    void initialize() {
        System.out.println("Football Game Initialized! Start playing.");
    }

//    @Override
//    void startPlay() {
//        System.out.println("Football Game Started. Enjoy the game!");
//    }
}