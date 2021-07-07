/**
 * fshows.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.example.springdemo.test.string;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/**
 * 判断括号是否合法
 *
 * @author xuleyan
 * @version TestStringJudge.java, v 0.1 2019-06-28 4:04 PM xuleyan
 */
public class TestStringJudge {

    public static void main(String[] args) {

        List<String> lists = Arrays.asList("{}", "()[]{}", "(]", "([)]", "{[]}", "]", "(])");
        for (String s : lists) {
            boolean result = checkString(s);
            System.out.println(result);
        }
    }

    public static boolean checkString(String s) {
        Map<Character, Character> hashMap = new HashMap<>();
        hashMap.put(')', '(');
        hashMap.put(']', '[');
        hashMap.put('}', '{');

        Stack<Character> resultStack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            Character sc = s.charAt(i);

            // 如果是个开启的符号，直接加入堆栈
            if (hashMap.containsValue(sc)) {
                resultStack.push(sc);
            } else {
                // 如果是个关闭的符号
                Character topElement = resultStack.isEmpty() ? '#' : resultStack.pop();
                if (!topElement.equals(hashMap.get(sc))) {
                    return false;
                }
            }
        }

        return resultStack.empty();
    }
}