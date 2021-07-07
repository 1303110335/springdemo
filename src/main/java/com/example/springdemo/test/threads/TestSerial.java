/**
 * fshows.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.example.springdemo.test.threads;

import com.example.springdemo.domain.User;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * @author xuleyan
 * @version TestSerial.java, v 0.1 2019-12-09 10:07 AM xuleyan
 */
public class TestSerial {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        User user = new User();
        user.setName("xly");

        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("/Users/xuleyan/Desktop/data/file"));
        out.writeObject(user);

        ObjectInputStream ins = new ObjectInputStream(new FileInputStream("/Users/xuleyan/Desktop/data/file"));
        User readUser = (User) ins.readObject();

        System.out.println(readUser);
    }
}