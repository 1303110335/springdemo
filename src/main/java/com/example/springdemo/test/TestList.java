/**
 * fshows.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.example.springdemo.test;

import cn.hutool.core.util.RandomUtil;

/**
 * @author xuleyan
 * @version TestList.java, v 0.1 2019-12-03 11:43 AM xuleyan
 */
public class TestList {

    public static void main(String[] args) {

        // 生成随机字符串

        for (int i = 1; i < 10000; i++) {
            String randomString = RandomUtil.randomString(10);
            System.out.println(i + ",\"" + randomString + "\"");
        }
    }
}