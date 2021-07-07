/**
 * fshows.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.example.springdemo.test.threads.threadLocal;

/**
 * @author xuleyan
 * @version TestThreadLocal.java, v 0.1 2019-06-24 10:45 AM xuleyan
 */
public class TestThreadLocal {

    //    private static A a = new A();
    private static final ThreadLocal<A> threadLocal = new ThreadLocal<A>() {
        @Override
        protected A initialValue() {
            return new A((int) (Math.random() * 100));
//            return a;
        }
    };

    public static void main(String[] args) {
        Thread[] threads = new Thread[5];
        for (int i = 0; i < 5; i++) {
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    threadLocal.get().setNumber(threadLocal.get().getNumber() + 5);

                    System.out.println(Thread.currentThread().getName() + ":" +
                            threadLocal.get().getNumber());
                }
            }, "Thread-" + i);
        }

        for (Thread thread : threads) {
            thread.start();
        }
    }
}

class A {

    private int number = 0;

    public A() {
    }

    public A(int number) {
        this.number = number;
    }

    /**
     * Getter method for property <tt>number</tt>.
     *
     * @return property value of number
     */
    public int getNumber() {
        return number;
    }

    /**
     * Setter method for property <tt>number</tt>.
     *
     * @param number value to be assigned to property number
     */
    public void setNumber(int number) {
        this.number = number;
    }
}