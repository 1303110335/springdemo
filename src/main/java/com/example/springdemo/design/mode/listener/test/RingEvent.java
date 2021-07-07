/**
 * fshows.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.example.springdemo.design.mode.listener.test;

import java.util.EventObject;

/**
 * @author xuleyan
 * @version RingEvent.java, v 0.1 2019-09-23 4:25 PM xuleyan
 */
public class RingEvent extends EventObject {

    private static final long serialVersionUID = -5009991211659382002L;

    /**
     * true表示上课铃声,false表示下课铃声
     */
    private boolean sound;

    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public RingEvent(Object source, boolean sound) {
        super(source);
        this.sound = sound;
    }

    /**
     * Getter method for property <tt>sound</tt>.
     *
     * @return property value of sound
     */
    public boolean isSound() {
        return sound;
    }

    /**
     * Setter method for property <tt>sound</tt>.
     *
     * @param sound value to be assigned to property sound
     */
    public void setSound(boolean sound) {
        this.sound = sound;
    }
}