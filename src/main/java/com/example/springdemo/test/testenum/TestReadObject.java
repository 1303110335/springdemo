/**
 * fshows.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.example.springdemo.test.testenum;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashSet;
import java.util.Set;

/**
 * @author xuleyan
 * @version TestReadObject.java, v 0.1 2019-03-31 7:41 PM xuleyan
 */
public class TestReadObject {

    public static void main(String[] args) throws Exception {

        Set<String> set = new HashSet<String>();
        set.add("11111");
        set.add("22222");
        System.out.println(set);

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("C:\\Users\\lianghaohui\\Desktop\\set.obj"))) {
            oos.writeObject(set);
        }
        set.clear();
        System.out.println(set);
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("C:\\Users\\lianghaohui\\Desktop\\set.obj"))) {
            set = (Set<String>) ois.readObject();
        }

        System.out.println(set);
    }
}