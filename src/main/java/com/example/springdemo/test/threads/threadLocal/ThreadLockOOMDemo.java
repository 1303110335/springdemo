/**
 * fshows.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.example.springdemo.test.threads.threadLocal;

import com.example.springdemo.domain.User;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author xuleyan
 * @version ThreadLockOOMDemo.java, v 0.1 2019-06-26 4:42 PM xuleyan
 */
public class ThreadLockOOMDemo {

    private static final int THREAD_LOOP_SIZE = 500;

    private static final int MOCK_DIB_DATA_LOOP_SIZE = 10000;

    private static ThreadLocal<List<User>> threadLocal = new ThreadLocal<>();

    public static void main(String[] args) {


        ExecutorService executorService = Executors.newFixedThreadPool(THREAD_LOOP_SIZE);
        for (int i = 0; i < THREAD_LOOP_SIZE; i++) {
            executorService.execute(() -> {
                threadLocal.set(new ThreadLockOOMDemo().addBigList());
                Thread t = Thread.currentThread();
                System.out.println(Thread.currentThread().getName());
                // 不取消注释 就可能出现OOM
                threadLocal.remove();
            });
        }
    }

    private List<User> addBigList() {
        List<User> params = new ArrayList<>(MOCK_DIB_DATA_LOOP_SIZE);
        for (int i = 0; i < MOCK_DIB_DATA_LOOP_SIZE; i++) {
            params.add(new User(i, "password" + i, 10));
        }
        return params;
    }
}