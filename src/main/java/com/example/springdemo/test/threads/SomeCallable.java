/**
 * fshows.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.example.springdemo.test.threads;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author xuleyan
 * @version SomeCallable.java, v 0.1 2019-06-21 8:01 PM xuleyan
 */
public class SomeCallable implements Callable<String> {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        SomeCallable someCallable = new SomeCallable();
        Future<String> future = executorService.submit(someCallable);
        System.out.println(future.get());
        executorService.shutdown();
    }

    @Override
    public String call() throws Exception {
        System.out.println("someCallable call");
        return "带返回值的线程";
    }

}