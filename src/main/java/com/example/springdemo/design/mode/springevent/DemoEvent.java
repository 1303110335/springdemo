/**
 * fshows.com
 * Copyright (C) 2013-2020 All Rights Reserved.
 */
package com.example.springdemo.design.mode.springevent;

import org.springframework.context.ApplicationEvent;

/**
 * @author xuleyan
 * @version DemoEvent.java, v 0.1 2020-05-06 11:01 AM xuleyan
 */
public class DemoEvent extends ApplicationEvent {

    private String message;

    /**
     * Create a new ApplicationEvent.
     *
     * @param source the object on which the event initially occurred (never {@code null})
     */
    public DemoEvent(Object source, String message) {
        super(source);
        this.message = message;
    }

    /**
     * Getter method for property <tt>message</tt>.
     *
     * @return property value of message
     */
    public String getMessage() {
        return message;
    }
}