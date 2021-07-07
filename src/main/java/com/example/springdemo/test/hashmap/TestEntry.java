/**
 * fshows.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.example.springdemo.test.hashmap;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xuleyan
 * @version TestEntry.java, v 0.1 2019-10-14 9:06 AM xuleyan
 */
public class TestEntry {

    public static void main(String[] args) {
        Map<String, String> stringStringMap = new HashMap<>();
        stringStringMap.put("name", "0");
        stringStringMap.put("name1", "1");
        stringStringMap.put("name2", "2");

        for (Object o : stringStringMap.entrySet()) {
            Map.Entry entry = (Map.Entry) o;
            String key = (String) entry.getKey();
            String value = (String) entry.getValue();
            System.out.println(key);
            System.out.println(value);

        }
    }
}