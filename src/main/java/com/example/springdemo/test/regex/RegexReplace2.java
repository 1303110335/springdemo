/**
 * fshows.com
 * Copyright (C) 2013-2020 All Rights Reserved.
 */
package com.example.springdemo.test.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author xuleyan
 * @version RegexReplace2.java, v 0.1 2020-04-02 10:24 AM xuleyan
 */
public class RegexReplace2 {

    private static String REGEX = "a*b";
    private static String INPUT = "aabfooaabfooabfoobkkk";
    private static String REPLACE = "-";

    public static void main(String[] args) {
        Pattern p = Pattern.compile(REGEX);
        // 获取 matcher 对象
        Matcher m = p.matcher(INPUT);
        StringBuffer sb = new StringBuffer();
        while (m.find()) {
            m.appendReplacement(sb, REPLACE);
        }
        m.appendTail(sb);
        System.out.println(sb.toString());
    }
}