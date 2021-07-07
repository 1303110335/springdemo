/**
 * fshows.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.example.springdemo.design.mode.listener.test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xuleyan
 * @version BellEventSource.java, v 0.1 2019-09-23 4:31 PM xuleyan
 */
public class BellEventSource {

    private List<BellEventListener> bellEventListeners;

    public BellEventSource() {
        bellEventListeners = new ArrayList<BellEventListener>();
    }

    /**
     * 给事件源绑定监听器
     *
     * @param bellEventListener
     */
    public void addPersonListener(BellEventListener bellEventListener) {
        bellEventListeners.add(bellEventListener);
    }

    /**
     * 事件触发器：敲钟，当铃声sound的值发生变化时，触发事件。
     *
     * @param sound
     */
    public void ring(boolean sound) {
        String type = sound ? "上课铃" : "下课铃";
        System.out.println(type + "响！");
        RingEvent event = new RingEvent(this, sound);
        notifies(event);
    }

    /**
     * 当事件发生时,通知绑定在该事件源上的所有监听器做出反应（调用事件处理方法）
     *
     * @param event
     */
    private void notifies(RingEvent event) {
        for (BellEventListener listener : bellEventListeners) {
            listener.heardBell(event);
        }
    }
}