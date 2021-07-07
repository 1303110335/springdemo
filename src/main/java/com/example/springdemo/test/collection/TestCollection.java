/**
 * fshows.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.example.springdemo.test.collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author xuleyan
 * @version TestCollection.java, v 0.1 2019-03-25 8:31 AM xuleyan
 */
public class TestCollection {

    public static void main(String[] args) {

        List<Integer> list = new ArrayList<Integer>();
        int array[] = {112, 111, 23, 456, 231 };
        for (int i = 0; i < array.length; i++) {
            list.add(array[i]);
        }
//        Collections.sort(list);
//        System.out.println(list);
//        Collections.shuffle(list);
//        System.out.println(list);
//        Collections.fill(list, 123);
//        System.out.println(list);
//        List<Integer> list2 = Arrays.asList(1,2,3,4,5,6,7,8,9);
//        // 目标 List 至少与源一样长。如果它更长，则在目标 List 中的剩余元素不受影响。
//        Collections.copy(list2, list);
//        System.out.println(list2);

        System.out.println(Collections.min(list));
        System.out.println(Collections.max(list));
        System.out.println(Collections.lastIndexOfSubList(list, Arrays.asList(456, 112)));
        Collections.rotate(list, -1);
        System.out.println(list);


    }
}