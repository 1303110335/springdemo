/**
 * fshows.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.example.springdemo.test.arrayaslist;

import org.apache.commons.lang.ArrayUtils;

import java.util.Arrays;

/**
 * @author xuleyan
 * @version TestArrayList.java, v 0.1 2019-03-25 8:55 AM xuleyan
 */
public class TestArrayList {

    public static void main(String[] args) {

        System.out.println(Arrays.asList("a", "b"));
        System.out.println(Arrays.asList(1, 2));
        // 调用一个声明为foo(T… t)的方法，比如foo(new T[]{bar,baz})等同于foo(bar,baz)这样的调用。
        // 在Arrays.asList方法中T是参数类型，因此它必须为一个Object 类型，但是int不是，而int[]却是
        System.out.println(Arrays.asList(new int[]{1, 2}));
        System.out.println(Arrays.asList(new String[]{"a", "b"}, "c"));

        System.out.println(Arrays.asList(ArrayUtils.toObject(new int[]{1,2})));

    }
}