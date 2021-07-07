/**
 * fshows.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.example.springdemo.test.serlize;

import com.example.springdemo.domain.Student;

/**
 * 解决双重校验锁的线程安全问题
 * 当前代码有安全问题，有三种解决方法
 * 1)在方法签名上添加synchronized, 使方法变为同步方法
 * 2)在存在线程安全问题的成员变量声明前添加volatile
 * 3)若在线程安全问题的成员变量为Integer、Long、Boolean等，可以将他们定义为AtomicXXX
 *
 * @author xuleyan
 * @version Singleton.java, v 0.1 2019-04-09 8:41 PM xuleyan
 */
public class Singleton {

    private volatile Student student;

    public Student getInstance() {
        if (student == null) {
            synchronized (Singleton.class) {
                if (student == null) {

                    // 1.申请堆空间
                    // 2.使用对象的初始化数据初始化堆空间
                    // 3.将student引用指向堆空间

                    student = new Student();
                }
            }
        }
        return student;
    }
}