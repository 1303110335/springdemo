/**
 * fshows.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.example.springdemo.test.lambda;

/**
 * @author xuleyan
 * @version Converter.java, v 0.1 2019-10-11 11:30 AM xuleyan
 */
@FunctionalInterface
public interface Converter<F, T> {
    T convert(F from);
}