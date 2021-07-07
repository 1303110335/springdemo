/**
 * fshows.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.example.springdemo.test.lambda;

import java.util.Arrays;
import java.util.List;

/**
 * @author xuleyan
 * @version Index.java, v 0.1 2019-10-11 11:24 AM xuleyan
 */
public class Index {

    public static void main(String[] args) {

        List<String> names = Arrays.asList("peter", "anna", "mike", "xenia");

//        names.sort((a, b) -> b.compareTo(a));

        System.out.println(names);
    }
}