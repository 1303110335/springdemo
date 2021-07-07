/**
 * fshows.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.example.springdemo.design.mode.single;


import java.io.Serializable;

/**
 * 双重检验锁
 *
 * @author xuleyan
 * @version DoubleCheck.java, v 0.1 2019-09-23 3:38 PM xuleyan
 */
public class DoubleCheck implements Serializable {

    private static final long serialVersionUID = -6910519270934997192L;

    private volatile transient static DoubleCheck instance;

    private DoubleCheck() {
    }

    private Object readResolve() {
        return instance;
    }

    public static DoubleCheck getInstance() {
        // single check
        if (instance == null) {
            synchronized (DoubleCheck.class) {
                // double check
                if (instance == null) {
                    instance = new DoubleCheck();
                }
            }
        }
        return instance;
    }

    public void tellEveryOne() {
        System.out.println("This is a DoubleCheckLockSingleton" + this.hashCode());
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}