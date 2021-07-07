/**
 * fshows.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.example.springdemo.test.string;

/**
 * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
 * <p>
 * 示例 1：
 * <p>
 * 输入: "babad"
 * 输出: "bab"
 * 注意: "aba" 也是一个有效答案。
 * 示例 2：
 * <p>
 * 输入: "cbbd"
 * 输出: "bb"
 *
 * @author xuleyan
 * @version LongestPalindrome.java, v 0.1 2019-08-25 10:29 PM xuleyan
 */
public class LongestPalindrome {

    public static void main(String[] args) {
        String result = longestPalindrome2("babad");
        System.out.println(result);
    }

    /**
     * 1.暴力破解法 O(时间复杂度n3)
     * 获取最大回文字符串
     *
     * @param s
     * @return
     */
    public static String longestPalindrome(String s) {
        int len = s.length();
        int max = 0;
        String result = "";
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                String tempStr = s.substring(i, j);
                if (isPalindrome(tempStr) && tempStr.length() > max) {
                    max = tempStr.length();
                    result = tempStr;
                }
            }
        }

        return result;
    }

    /**
     * 判断字符串是否是回文
     *
     * @param s
     * @return
     */
    private static Boolean isPalindrome(String s) {
        int len = s.length();
        for (int i = 0; i < len; i++) {
            if (s.charAt(i) != s.charAt(len - i - 1)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 动态规划
     * 时间复杂度(On2)
     * 空间复杂度(On2)
     *
     * @param s
     * @return
     */
    public static String longestPalindrome2(String s) {
        if (s.equals("")) {
            return "";
        }
        String origin = s;
        // 字符串倒置
        String reverse = new StringBuffer(s).reverse().toString();
        int length = s.length();
        int[] arr = new int[length];
        int maxLen = 0;
        int maxEnd = 0;
        for (int i = 0; i < length; i++) {
            for (int j = length - 1; j >= 0; j--) {
                // 如果正反字符串对应点的字母是一致的，则在之前的基础上+1
                if (origin.charAt(i) == reverse.charAt(j)) {
                    if (i == 0 || j == 0) {
                        arr[j] = 1;
                    } else {
                        arr[j] = arr[j - 1] + 1;
                    }
                } else {
                    arr[j] = 0;
                }
                if (arr[j] > maxLen) {
                    // 获取当前反字符串最后一个回文字母的下标取之前的下标
                    int beforeRev = length - 1 - j;
                    // 判断上面得到的下标加上回文字符串的长度是否等于当前i（正回文字符串的最后一个字母的下标）
                    if (beforeRev + arr[j] - 1 == i) {
                        maxLen = arr[j];
                        maxEnd = i;
                    }
                }
            }
        }
        return s.substring(maxEnd - maxLen + 1, maxEnd + 1);
    }
}