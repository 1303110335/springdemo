/**
 * fshows.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.example.springdemo.test.lambda;

/**
 * @author xuleyan
 * @version Something.java, v 0.1 2019-10-11 11:28 AM xuleyan
 */
class Something {
    public static void main(String[] args) {
        Something something = new Something();
        Converter<String, String> converter = something::startsWith;
        String converted = converter.convert("Java");
        System.out.println(converted);    // "J"
    }

    String startsWith(String s) {
        return String.valueOf(s.charAt(0));
    }
}