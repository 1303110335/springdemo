/**
 * fshows.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.example.springdemo.test.apache;

import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * @author xuleyan
 * @version TestCollectionUtils.java, v 0.1 2019-03-21 5:56 PM xuleyan
 */
public class TestCollectionUtils {

    public static void main(String[] args) {
        List<String> list1 = new ArrayList<String>();
        list1.add("1");
        list1.add("2");
        list1.add("3");
        List<String> list2 = new ArrayList<String>();
        list2.add("2");
        list2.add("3");
        list2.add("5");
        list2.add("6");

        List<String> list3 = Arrays.asList("7", "8");
//        Collection c = CollectionUtils.retainAll(list1, list2);
//        Collection c = CollectionUtils.union(list1, list2);
//        Collection c = CollectionUtils.intersection(list1, list2);
//        boolean res = CollectionUtils.isEqualCollection(list1, list2);
//        String[] strArray = {"a", "b"};
//        CollectionUtils.addAll(list1, strArray);
//
//        System.out.println(list1);

        boolean res = CollectionUtils.containsAny(list1, list3);
        System.out.println(res);
    }
}