/**
 * fshows.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.example.springdemo.test.serlize;

import com.example.springdemo.domain.User;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * @author xuleyan
 * @version MainClass.java, v 0.1 2019-04-09 8:31 AM xuleyan
 */
public class MainClass {

    public static void main(String[] args) throws IOException {

        User stu1 = new User(1001, "jack", "play");
        User  stu2 = new User(1002, "tom", "sleep");
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("stu.txt"));
        oos.writeObject(stu1);
        oos.writeObject(stu2);
        oos.close();


    }
}