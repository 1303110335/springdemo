/**
 * fshows.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.example.springdemo.test;

/**
 * @author xuleyan
 * @version TestInteger2.java, v 0.1 2019-03-13 8:56 AM xuleyan
 */
public class TestInteger2 {
    public static void main(String[] args) {
        // Java中，数据在byte范围内，JVM为了节约内存，不会重新new对象。(-128, 127)
        Integer a = 1;
        Integer b = 1;
        System.out.println(a == b);
//        System.out.println(a.equals(b));

//        Integer aaa = 128;
//        Integer bbb = 128;
//        System.out.println(aaa == bbb);
//        System.out.println(aaa.equals(bbb));
//
//        Integer aa = new Integer(2);
//        Integer bb = new Integer(2);
//        System.out.println(aa == bb);
//        System.out.println(aa.equals(bb));

    }
}