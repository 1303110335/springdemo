/**
 * fshows.com
 * Copyright (C) 2013-2020 All Rights Reserved.
 */
package com.example.springdemo.test.hashmap;

import java.util.Set;
import java.util.TreeMap;

/**
 * @author xuleyan
 * @version TreeMapTest.java, v 0.1 2020-03-30 4:20 PM xuleyan
 */
public class TreeMapTest {

    public static void main(String[] args) {

        Person p1 = new Person("郑鑫", 100);
        Person p2 = new Person("刘德华", 1000);
        Person p3 = new Person("梁朝伟", 1500);
        Person p4 = new Person("王力宏", 1300);

        TreeMap<Person, String> map = new TreeMap<>((o1, o2) -> (o2.getHandsome() - o1.getHandsome()));


        // Exception in thread "main" java.lang.ClassCastException: com.example.springdemo.test.hashmap.Person cannot be cast to java.lang.Comparable
//         TreeMap<Person, String> map = new TreeMap<>();

        map.put(p1, "bjsxt");
        map.put(p2, "bjsxt");
        map.put(p3, "bjsxt");
        map.put(p4, "bjsxt");

//        TreeMap<String, Person> map = new TreeMap<>();
//        map.put("bjsxt1", p1);
//        map.put("bjsxt2", p2);
//        map.put("bjsxt3", p3);
//        map.put("bjsxt4", p4);
//
//        Collection<Person> persons = map.values();

        Set<Person> persons = map.keySet();
        System.out.println(persons);
    }
}