/**
 * fshows.com
 * Copyright (C) 2013-2020 All Rights Reserved.
 */
package com.example.springdemo.test.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author xuleyan
 * @version RegexMatches.java, v 0.1 2020-04-02 10:17 AM xuleyan
 */
public class RegexMatches {
    private static final String REGEX = "foo";
    private static final String INPUT = "fooooooooooooooooo";
    private static final String INPUT2 = "ooooofoooooooooooo";
    private static Pattern pattern;
    private static Matcher matcher;
    private static Matcher matcher2;

    public static void main(String args[]) {
        // matches 和 lookingAt 方法都用来尝试匹配一个输入序列模式。
        // 它们的不同是 matches 要求整个序列都匹配，而lookingAt 不要求。

        //lookingAt 方法虽然不需要整句都匹配，但是需要从第一个字符开始匹配。
        pattern = Pattern.compile(REGEX);
        matcher = pattern.matcher(INPUT);
        matcher2 = pattern.matcher(INPUT2);

        System.out.println("Current REGEX is: " + REGEX);
        System.out.println("Current INPUT is: " + INPUT);
        System.out.println("Current INPUT2 is: " + INPUT2);


        System.out.println("lookingAt(): " + matcher.lookingAt());
        System.out.println("matches(): " + matcher.matches());
        System.out.println("lookingAt(): " + matcher2.lookingAt());
    }
}