/**
 * fshows.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.example.springdemo.test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author xuleyan
 * @version TestFloat.java, v 0.1 2019-10-31 2:06 PM xuleyan
 */
public class TestFloat {

    public static void main(String[] args) {
//        BigDecimal num = new BigDecimal(0.00001);
//        System.out.println(num.floatValue());
//        System.out.println(num.setScale(5, BigDecimal.ROUND_HALF_UP));

//        Set<String> strings = new HashSet<>();
//        strings.add("1");
//        System.out.println(strings);

        List<BigDecimal> bigDecimals = new ArrayList<>(Arrays.asList(new BigDecimal(1.23), new BigDecimal(3.456)));

        System.out.println(bigDecimals.stream().count());
    }
}