/**
 * fshows.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.example.springdemo.test;

import com.example.springdemo.domain.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author xuleyan
 * @version TestStream.java, v 0.1 2019-04-01 2:08 PM xuleyan
 */
public class TestStream {

    public static void main(String[] args) {

        List<User> students = new ArrayList<>();

        students.add(new User(1, "张三", 90));
        students.add(new User(2, "李四", 50));
        students.add(new User(6, "王五", 90));
        students.add(new User(4, "赵六", 90));

//        Map<Integer, String> nameMap = students.stream().collect(Collectors.toMap(User::getNumber, User::getName));

//        Map<Integer, User> collect = students.stream().collect(Collectors.toMap(User::getNumber, o -> o));


//        Map<Integer, String> nameMap = students.stream().collect(Collectors.toMap(User::getNumber, User::getName));
//        System.out.println(nameMap);

        System.out.println(students.stream().map(User::getName).collect(Collectors.toList()));
    }
}