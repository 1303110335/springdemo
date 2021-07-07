/**
 * fshows.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.example.springdemo.test.string;

import java.util.ArrayList;
import java.util.List;

/**
 * 给出 n 代表生成括号的对数，请你写出一个函数，使其能够生成所有可能的并且有效的括号组合。
 * <p>
 * 例如，给出 n = 3，生成结果为：
 * <p>
 * [
 * "((()))",
 * "(()())",
 * "(())()",
 * "()(())",
 * "()()()"
 * ]
 *
 * @author xuleyan
 * @version TestGenerateString.java, v 0.1 2019-06-28 4:59 PM xuleyan
 */
public class TestGenerateString {

    static List<String> res = new ArrayList<>();

    public static void main(String[] args) {

        List<String> list = generateParenthesis(3);
        System.out.println(list);
    }

    public static List<String> generateParenthesis(int n) {
        if (n == 0) {
            return res;
        }
        backTrace(0, 0, "", n);
        return res;
    }

    public static void backTrace(int right, int left, String str, int n) {
        if (right == n && left == n) {
            res.add(str);
            return;
        }
        if (left < n) {
            backTrace(right, left + 1, str + "(", n);
        }
        if (right < left) {
            backTrace(right + 1, left, str + ")", n);
        }
    }
}