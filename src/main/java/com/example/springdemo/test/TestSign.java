/**
 * fshows.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.example.springdemo.test;

/**
 * @author xuleyan
 * @version TestSign.java, v 0.1 2019-03-12 11:06 AM xuleyan
 */
public class TestSign {

    public static void main(String[] args) {
//        Map<String, String> paramterMap = Maps.newHashMap();;
//        paramterMap.put("username", "admin");
//        paramterMap.put("password", "21232f297a57a5a743894a0e4a801fc3");
//        String sortParams = RSAUtils.getSignContent(paramterMap);
//        String getSign = Md5Util.sign(sortParams);
//        System.out.println(getSign);

        TestClass testClass = new TestClass();
        System.out.println(testClass.getNumber());
        testClass.setNumber(100);
        System.out.println(testClass.getNumber());
    }
}