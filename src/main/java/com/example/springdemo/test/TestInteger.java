/**
 * fshows.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.example.springdemo.test;

/**
 * @author xuleyan
 * @version TestInteger.java, v 0.1 2019-03-13 8:47 AM xuleyan
 */
public class TestInteger {

    public static void main(String[] args) {
        int intNum = 1357;
		String s = Integer.toString(intNum);
		System.out.println(s);

		s = Integer.toString(intNum, 2);
		System.out.println(s);

		int iInt = 13579;
		System.out.println(iInt);

//		int jInt = new Integer("246a80");
//		System.out.println(jInt);

		System.out.println(Integer.MIN_VALUE);
		System.out.println(Integer.MAX_VALUE);
    }
}