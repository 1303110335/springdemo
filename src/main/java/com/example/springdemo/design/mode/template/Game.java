/**
 * fshows.com
 * Copyright (C) 2013-2020 All Rights Reserved.
 */
package com.example.springdemo.design.mode.template;

/**
 * @author xuleyan
 * @version Game.java, v 0.1 2020-05-06 8:33 AM xuleyan
 */
public abstract class Game {
    abstract void initialize();

    //abstract void startPlay();
    abstract void endPlay();

    //模板
    public final void play(GameCallback callback) {

        //初始化游戏
        initialize();

        //游戏内容都不一样
        //startPlay();
        callback.doPlay();

        //结束游戏
        endPlay();
    }

    public final void end() {

    }
}