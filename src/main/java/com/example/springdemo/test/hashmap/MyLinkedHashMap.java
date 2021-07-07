/**
 * fshows.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.example.springdemo.test.hashmap;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * 手动实现hashmap的顺序插入
 *
 * @author xuleyan
 * @version MyLinkedHashMap.java, v 0.1 2019-10-14 4:52 PM xuleyan
 */
public class MyLinkedHashMap<K, V> extends HashMap<K, V> implements Map<K, V> {


    public static void main(String[] args) {
        Map<String, String> hashMap = new HashMap<>();
        hashMap.put("name", "0");
        hashMap.put("name1", "1");
        hashMap.put("name2", "2");

        Queue<String> queue = new LinkedList<>();
        queue.offer("name");
        queue.offer("name1");
        queue.offer("name2");

        for (String key : queue) {
            System.out.println(key);
            System.out.println(hashMap.get(key));
        }

    }
}