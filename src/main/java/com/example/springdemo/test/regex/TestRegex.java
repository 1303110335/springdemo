/**
 * fshows.com
 * Copyright (C) 2013-2020 All Rights Reserved.
 */
package com.example.springdemo.test.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author xuleyan
 * @version TestRegex.java, v 0.1 2020-04-02 10:08 AM xuleyan
 */
public class TestRegex {

    private static final String REGEX = "\\bcat\\b";
    private static final String INPUT = "cat cat cat cattie cat";

    public static void main(String[] args) {
//        testContains();
//        testCode();
        testMatches();
//        testMatch2();
    }

    private static void testContains() {
        String content = "I am noob " +
                "from runoob.com.";

        String pattern = ".*runoob.*";

        boolean isMatch = Pattern.matches(pattern, content);
        System.out.println("字符串中是否包含了 'runoob' 子字符串? " + isMatch);
    }

    private static void testCode() {
        String content = "6211234567812345678";
        String pattern = "^62[1-8]\\d{16}$";
        boolean matches = Pattern.matches(pattern, content);
        System.out.println(matches);

    }

    private static void testMatches() {
        // 按指定模式在字符串查找
        String line = "This order was placed for QT3000! OK?";
        String pattern = "(\\D*)(\\d+)(.*)";

        // 创建 Pattern 对象
        Pattern r = Pattern.compile(pattern);

        // 现在创建 matcher 对象
        Matcher m = r.matcher(line);
        if (m.find()) {
            System.out.println("Found value: " + m.group(0));
            System.out.println("Found value: " + m.group(1));
            System.out.println("Found value: " + m.group(2));
            System.out.println("Found value: " + m.group(3));
        } else {
            System.out.println("NO MATCH");
        }
    }

    private static void testMatch2() {
        Pattern p = Pattern.compile(REGEX);
        // // 获取 matcher 对象
        Matcher m = p.matcher(INPUT);
        int count = 0;

        while (m.find()) {
            count++;
            System.out.println("Match number " + count);
            System.out.println("start(): " + m.start());
            System.out.println("end(): " + m.end());
        }
    }
}