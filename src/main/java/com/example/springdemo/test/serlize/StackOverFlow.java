/**
 * fshows.com
 * Copyright (C) 2013-2020 All Rights Reserved.
 */
package com.example.springdemo.test.serlize;

import java.util.HashSet;
import java.util.Set;

/**
 * @author xuleyan
 * @version StackOverFlow.java, v 0.1 2020-05-28 7:18 AM xuleyan
 */
public class StackOverFlow {

    public static void main(String[] args) {
        Set root = new HashSet();
        Set s1 = root;
        Set s2 = new HashSet();
        for (int i = 0; i < 100; i++) {
            Set t1 = new HashSet();
            Set t2 = new HashSet();
            t1.add("foo"); //使t2不等于t1
            s1.add(t1);
            s1.add(t2);
            s2.add(t1);
            s2.add(t2);
            s1 = t1;
            s2 = t2;
        }
    }
}