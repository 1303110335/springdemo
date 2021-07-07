/**
 * fshows.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.example.springdemo.test.array;
import cn.hutool.core.util.NumberUtil;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.ClassUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.validator.routines.RegexValidator;

import java.util.Arrays;

/**
 * @author xuleyan
 * @version TestArr.java, v 0.1 2019-03-24 1:02 PM xuleyan
 */
public class TestArr {

    public static void main(String[] args) {
        String[] s1 = new String[] {"1", "2", "3"};
        String[] s2 = new String[] {"a", "b", "c"};
        String[] s = (String[]) ArrayUtils.addAll(s1, s2);
//
//        System.out.println(Arrays.asList(s));
//
//        String str = ArrayUtils.toString(s);
//        str = str.substring(1, str.length() - 1);
//        System.out.println(str + ">>" + str.length());

//        String res = subStringAfter2("select * from person", "from");
//        System.out.println(res);
//
//        // 是否是数字
//        boolean bool = isNumeric2("452342341 ".trim());
//        System.out.println(bool);


//        System.out.println(ClassUtils.getShortClassName(TestArr.class));
//        System.out.println(ClassUtils.getPackageName(TestArr.class));
//
//        System.out.println(NumberUtils.isNumber("123.1"));
//
//        System.out.println(RandomStringUtils.randomAlphanumeric(5));
//
//        System.out.println(StringEscapeUtils.escapeHtml("<html>"));
//
//        System.out.println(StringEscapeUtils.escapeJava("String"));
//
//        System.out.println(StringUtils.isBlank("  "));

//        System.out.println(StringUtils.join(s,","));
//
//        System.out.println(StringUtils.rightPad("abc", 6, 'T'));
//
//        System.out.println("123".concat("456"));
//
//        System.out.println(StringUtils.capitalize("abc"));
//
//        System.out.println(deleteWhiteSpace2(" abc f d"));
//
//        System.out.println(StringUtils.contains("abc", "ba"));

//        System.out.println(StringUtils.left("abc", 2));

        // 设置参数
        boolean caseSensitive = false;
        String regex1   = "^([A-Z]*)(?:\\-)([A-Z]*)*$";
        String regex2   = "^([A-Z]*)$";
        String[] regexs = new String[] {regex1, regex2};

        // 创建验证
        RegexValidator validator = new RegexValidator(regexs, caseSensitive);

        // 验证返回boolean
        boolean valid = validator.isValid("abc-def");
        println(valid);

        // 验证返回字符串
        String result = validator.validate("abc-def");
        println(result);

        // 验证返回数组
        String[] groups = validator.match("abc-def");
        println(Arrays.asList(groups));
    }

    private static void println(Object obj) {
        System.out.println(obj);
    }

    private static String deleteWhiteSpace2(String str) {
        if (StringUtils.isEmpty(str)) {
            return null;
        } else {
            int size = str.length();
            char[] buf = new char[size];
            int count = 0;
            for (int i = 0; i < size; i++) {
                if (!Character.isWhitespace(str.charAt(i))) {
                    buf[count++] = str.charAt(i);
                }
            }
            if (count == size) {
                return str;
            } else {
                return new String(buf, 0, count);
            }
        }
    }

    private static String subStringAfter2(String str, String separator) {
        if (StringUtils.isEmpty(str)) {
            return str;
        } else if (separator == null) {
            return "";
        } else {
            int index = str.indexOf(separator);
            return index != -1 ? str.substring(index + separator.length()) : "";
        }
    }

    private static Boolean isNumeric2(String str) {
        if (str == null) {
            return false;
        } else {
            int len = str.length();
            for (int i = 0; i < len; i++) {
                if (!Character.isDigit(str.charAt(i))) {
                    return false;
                }
            }
            return true;
        }
    }
}