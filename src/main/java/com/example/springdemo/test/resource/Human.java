/**
 * fshows.com
 * Copyright (C) 2013-2020 All Rights Reserved.
 */
package com.example.springdemo.test.resource;

/**
 * @author xuleyan
 * @version Human.java, v 0.1 2020-05-21 5:16 PM xuleyan
 */
class Annoyance extends Exception {
}

class Sneeze extends Annoyance {
}

class Human {
    public static void main(String[] args)
            throws Exception {
        try {
            try {
                throw new Sneeze();
            } catch (Annoyance a) {
                System.out.println("Caught Annoyance");
                throw a;
            }
        } catch (Sneeze s) {
            System.out.println("Caught Sneeze");
            return;
        } finally {
            System.out.println("Hello World!");
        }
    }
}