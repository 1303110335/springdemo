/**
 * fshows.com
 * Copyright (C) 2013-2020 All Rights Reserved.
 */
package com.example.springdemo.test.resource;

/**
 * @author xuleyan
 * @version CloseResourceIn7.java, v 0.1 2020-05-21 4:27 PM xuleyan
 */
public class CloseResourceIn7 {

    public static void main(String[] args) {

        try (Resource resource = new Resource()) {
            resource.sayHello();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}