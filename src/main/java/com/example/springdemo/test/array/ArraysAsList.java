/**
 * fshows.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.example.springdemo.test.array;

import org.apache.commons.collections.CollectionUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author xuleyan
 * @version ArraysAsList.java, v 0.1 2019-05-22 10:09 PM xuleyan
 */
public class ArraysAsList {

    public static void main(String[] args) {
        /**
         * 基本数据类型打印的都是地址值，而String类型的数组输出的是数组中的元素，这是为啥嘞？通过引用Arrays.asList(T...a)方法，
         * 可以知道括号中需要一个含数据类型的实参（T一般就是泛型的意思），而基本数据类型是没有类型的
         */
        // char
        char[] mChar = { 'a', 'b' };
        System.out.println("----->char" + Arrays.asList(mChar));
        // int
        int[] mInt = { 1, 23, 4, 5, 56, 77 };
        System.out.println("---->int" + Arrays.asList(mInt));
        // boolean
        boolean[] mBoolean = { true, false };
        System.out.println("---->boolean" + Arrays.asList(mBoolean));
        // double
        double[] mDouble = { 1.1, 2.4, 5.6, 7.8 };
        System.out.println("---->double" + Arrays.asList(mDouble));
        // String
        String mString[] = { "花花", "草草", "纷纷" };
        List<String> list = Arrays.asList(mString);
        System.out.println("---->String" + Arrays.asList(mString));

        /*
         * 此集合是根据mString[]获得的，长度已经固定，不能改变；
         * 在使用Arrays.asList()后调用add，remove这些method时出现
         * java.lang.UnsupportedOperationException异常。
         * 这是由于Arrays.asList() 返回java.util.Arrays$ArrayList， 而不是ArrayList。
         * Arrays$ArrayList和ArrayList都是继承AbstractList，remove，add等
         * method在AbstractList中是默认throw UnsupportedOperationException而且不作任何操作。
         * ArrayList override这些method来对list进行操作，但是Arrays$ArrayList没有override remove()，add()等，
         * 所以throw UnsupportedOperationException。
         */
//        list.add("test");


        System.out.println(Collections.min(list));
        System.out.println(Collections.max(list));
        Collections.sort(list);
        System.out.println(list);

    }
}