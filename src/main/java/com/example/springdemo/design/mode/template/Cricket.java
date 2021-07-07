/**
 * fshows.com
 * Copyright (C) 2013-2020 All Rights Reserved.
 */
package com.example.springdemo.design.mode.template;

/**
 * @author xuleyan
 * @version Circket.java, v 0.1 2020-05-06 8:34 AM xuleyan
 */
public class Cricket extends Game {

    @Override
    void endPlay() {
        System.out.println("Cricket Game Finished!");
    }

    @Override
    void initialize() {
        System.out.println("Cricket Game Initialized! Start playing.");
    }

//    @Override
//    void startPlay() {
//        System.out.println("Cricket Game Started. Enjoy the game!");
//    }
}