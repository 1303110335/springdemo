/**
 * fshows.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.example.springdemo.test.vector;

import java.util.Vector;

/**
 * @author xuleyan
 * @version TestVecotr2.java, v 0.1 2019-09-01 9:45 PM xuleyan
 */
public class TestVecotr2 {

    private static Vector<Integer> vector = new Vector<>();
//    private static List<Integer> vector = new ArrayList<>();

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            vector.add(i);
        }


        for (int i = 0; i < vector.size(); i++) {
            vector.remove(i);
        }


        for (int i = 0; i < vector.size(); i++) {
            System.out.println(vector.get(i));
        }

    }
}