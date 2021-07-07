/**
 * fshows.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.example.springdemo.test.string;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xuleyan
 * @version TestString.java, v 0.1 2019-03-13 8:15 PM xuleyan
 */
public class TestString {

    public static void main(String[] args) {

//        String s = new String("bc");
//
//        StringBuilder stringBuilder = new StringBuilder();
//        stringBuilder.append("c");
//        System.out.println(stringBuilder.toString());
//
//
//
//        StringBuffer stringBuffer = new StringBuffer();
//        stringBuffer.append("b");
//        System.out.println(stringBuffer.toString());
//
//
////        List<String> lists = Arrays.asList("{}", "()[]{}", "(]", "([)]", "{[]}", "]", "(])");
//        List<String> lists = Arrays.asList("()[]{}");
//        for (String s : lists) {
//            checkString(s);
//        }

//        BigDecimal six = new BigDecimal("66.0099999");
//        System.out.println(six.compareTo(new BigDecimal("25.588999")));

//        String str1 = "a";
//        String str2 = "a";
//        System.out.println(str1 == str2);

        String str3 = new String("ab");
        //
        System.out.println(str3.intern() == str3);

        String e = new String("jo") + new String("hn");
        // intern 在常量池中寻找john，
        //      如果没有,则将e在堆中的内存地址放入到常量池中的stringtable中，并返回
        //      如果有，则直接返回在常量池中的地址
        System.out.println(e.intern() == e);


        String str = new String("ja") + new String("va");
        System.out.println(str.intern() == str);

    }

    private static boolean checkString(String s) {

        System.out.println();
        System.out.print("检测字符串:【" + s + "】结果 : ");

        if ("".equals(s)) {
            return true;
        }

        if (s == null || s.length() == 0) {
            System.out.println("字符串不能为空");
            return false;
        }

        int length = s.length();
        // 判断lists中的字符串是否符合标准 {},[],()成对出现，顺序符合要求
        Map<String, String> hashMap = new HashMap<>();
        hashMap.put("[", "]");
        hashMap.put("{", "}");
        hashMap.put("(", ")");

        Map<String, String> rightHashMap = new HashMap<>();
        hashMap.put("]", "[");
        hashMap.put("}", "{");
        hashMap.put(")", "(");

        int i = 0;
        // 字符串长度为3 则检测 前2位
        char[] chars = s.toCharArray();
        int len = (chars.length / 2) + (chars.length % 2);
        for (int j = 0; j < len; j++) {
            // 要检测的字符
            String c = chars[j] + "";
            String key = "";
            if (hashMap.keySet().contains(c)) {
                key = c;
            } else if (rightHashMap.keySet().contains(c)) {
                // 找到对应的左侧字符
                key = rightHashMap.get(c);
            } else {
                continue;
            }

            i++;
            // 1.符合成对的条件，符合前后的条件
            // 找到第一个{的下表
            int index = s.indexOf(key);
            int rightIndex = s.indexOf(hashMap.get(key));
            if (index == -1 || rightIndex == -1 || rightIndex < index) {
                System.out.println("非法！！！字符串由于 " + key + " 非法(左括号必须符合成对的条件)");
                return false;
            }
            // 2.左括号必须以正确的顺序闭合
            if (index == (length - rightIndex - 1)) {
                // {[]}
            } else if (rightIndex - index == 1) {
                // ()[]{}
            } else {
                System.out.println("非法！！！字符串由于 " + key + " 非法(左括号必须以正确的顺序闭合)");
                return false;
            }
        }

        if (i == 0) {
            System.out.println("字符串非法！！！(不包含`({[`任一一个)");
            return false;
        }
        System.out.println("字符串合法");
        return true;
    }
}