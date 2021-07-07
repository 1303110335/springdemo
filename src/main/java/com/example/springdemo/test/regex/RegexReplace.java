/**
 * fshows.com
 * Copyright (C) 2013-2020 All Rights Reserved.
 */
package com.example.springdemo.test.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author xuleyan
 * @version RegexReplace.java, v 0.1 2020-04-02 10:20 AM xuleyan
 */
public class RegexReplace {

    private static String REGEX = "dog";
    private static String INPUT = "The dog says meow.  All dogs say meow.";
    private static String REPLACE = "cat";

    public static void main(String[] args) {
        Pattern p = Pattern.compile(REGEX);
        // get a matcher object
        Matcher m = p.matcher(INPUT);
        INPUT = m.replaceAll(REPLACE);
        System.out.println(INPUT);
    }
}