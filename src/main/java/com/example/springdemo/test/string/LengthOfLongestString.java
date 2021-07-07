/**
 * fshows.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.example.springdemo.test.string;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 * <p>
 * 示例 1:
 * <p>
 * 输入: "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * 示例 2:
 * <p>
 * 输入: "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * 示例 3:
 * <p>
 * 输入: "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 *
 * @author xuleyan
 * @version LengthOfLongestString.java, v 0.1 2019-07-28 9:37 AM xuleyan
 */
public class LengthOfLongestString {

    public static void main(String[] args) throws Exception {
        String str = "pwwkew";
        int maxLength = testStr(str);
        System.out.println(maxLength);
    }

    /**
     * 两次循环的方法
     *
     * @param str
     * @return
     */
    private static Integer test(String str) {
        int maxLength = 0;
        for (int i = 0; i < str.length() - maxLength; i++) {
            Set<Character> strSet = new HashSet<>();
            for (int j = i; j < str.length(); j++) {
                char temp = str.charAt(j);
                if (strSet.contains(temp)) {
                    if (strSet.size() > maxLength) {
                        maxLength = strSet.size();
                    }
                    break;
                } else {
                    strSet.add(temp);
                    if (strSet.size() > maxLength) {
                        maxLength = strSet.size();
                    }
                }
            }
        }
        return maxLength;
    }

    /**
     * 时间复杂度O(n)
     *
     * @param s
     * @return
     */
    private static Integer test2(String s) {

        // "pwwkew"  ans = 0;
        // p 1     i = 0; j = 0; ans = max(ans, j - i + 1) = 1
        // w 2     i = 0; j = 1; ans = max(ans, j - i + 1) = 2
        // w 3     i = 2; j = 2; ans = max(ans, j - i + 1) = 2
        // k 4     i = 2; j = 3; ans = max(ans, j - i + 1) = 2
        // e 5     i = 2; j = 4; ans = max(ans, j - i + 1) = 3
        // w 6     i = 6; j = 5; ans = max(ans, j - i + 1) = 0


        int n = s.length();
        // 最长不重复字符串
        int ans = 0;
        // current index of character
        Map<Character, Integer> map = new HashMap<>();
        // i 表示重复的字符最近的位置
        // j 表示当前处理的字符
        // j - i + 1 表示当前不重复字符串的长度
        // try to extend the range [i, j]
        for (int j = 0, i = 0; j < n; j++) {
            if (map.containsKey(s.charAt(j))) {
                i = Math.max(map.get(s.charAt(j)), i);
            }
            ans = Math.max(ans, j - i + 1);
            // 下次遇到同样的字符，从j+1开始算起
            map.put(s.charAt(j), j + 1);
        }


        return ans;
    }


    private static Integer testStr(String s) throws Exception {
        Integer num = null;
        try {
            num = Integer.parseInt(s);
        } catch (Exception e) {
            System.out.println("请输入数字");

            throw new Exception(e.getMessage());
        }


        return num;
    }

}