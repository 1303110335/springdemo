/**
 * fshows.com
 * Copyright (C) 2013-2020 All Rights Reserved.
 */
package com.example.springdemo.test;

/**
 * @author xuleyan
 * @version TryWithResource.java, v 0.1 2020-05-21 4:23 PM xuleyan
 */
public class TryWithResource implements AutoCloseable {

    private int age = 18;

    public static void main(String[] args) {
        try (TryWithResource tryWithResource = new TryWithResource()) {
            System.out.println(tryWithResource.age);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() throws Exception {
        System.out.println("this is close 方法");
    }
}