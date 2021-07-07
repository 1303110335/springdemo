/**
 * fshows.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.example.springdemo.test.string.again;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author xuleyan
 * @version LengthOfLongestString2.java, v 0.1 2019-08-25 4:34 PM xuleyan
 */
public class LengthOfLongestString2 {

    public static void main(String[] args) {
        System.out.println(countSubstring2("dvdf"));
    }

    private static Integer countSubstring2(String s) {
        Map<Character, Integer> hashMap = new HashMap<>();
        // 最大长度
        int maxLength = 0;
        int length = s.length();
        /**
         * i 该字符起始的位置
         * j 该字符当前的位置
         * j - 1 + 1目前字符的长度
         */
        for (int i = 0, j = 0; j < length; j++) {
            if (hashMap.containsKey(s.charAt(j))) {
                i = Math.max(hashMap.get(s.charAt(j)), i);
            }
            maxLength = Math.max(maxLength, j - i + 1);
            // 下次遇到同样的字符，从j+1开始算起
            hashMap.put(s.charAt(j), j + 1);
        }
        return maxLength;
    }

    private static Integer countSubstring(String s) {
        if ("".equals(s) || s.length() == 0) {
            return 0;
        }

        int maxLength = 1;
        Set<Character> set = new HashSet<>();
        set.add(s.charAt(0));
        for (int i = 1; i < s.length(); i++) {
            // 如果已包含同样的字母，比较set的大小与maxLength那个大，保存大的
            char current = s.charAt(i);
            if (set.contains(current)) {
                if (set.size() > maxLength) {
                    maxLength = set.size();
                    set.clear();
                }
            }
            set.add(current);
        }

        if (set.size() > maxLength) {
            maxLength = set.size();
        }

        return maxLength;
    }
}