/**
 * fshows.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.example.springdemo.test.vector;

import java.util.Vector;

/**
 * @author xuleyan
 * @version TestVector.java, v 0.1 2019-08-28 10:58 PM xuleyan
 */
public class TestVector {

    private static Vector<Integer> vector = new Vector<>();

    public static void main(String[] args) {
        while (true) {
            for (int i = 0; i < 10; i++) {
                vector.add(i);
            }

            Thread removeThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    synchronized (vector) {
                        for (int i = 0; i < vector.size(); i++) {
                            vector.remove(i);
                        }
                    }
                }
            });

            Thread printThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    synchronized (vector) {
                        for (int i = 0; i < vector.size(); i++) {
                            System.out.println(vector.get(i));
                        }
                    }
                }
            });

            removeThread.start();
            printThread.start();

            // 不要同时产生过多的线程，不然会造成操作系统假死
            while (Thread.activeCount() > 30) {
                System.out.println("线程数量已经超过了20个");
                System.exit(1);
            }
        }

    }
}