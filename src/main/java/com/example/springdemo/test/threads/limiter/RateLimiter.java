/**
 * fshows.com
 * Copyright (C) 2013-2020 All Rights Reserved.
 */
package com.example.springdemo.test.threads.limiter;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.ExecutorService;

/**
 * 令牌桶算法实现
 *
 * @author xuleyan
 * @version RateLimiter.java, v 0.1 2020-04-10 5:30 PM xuleyan
 */
public class RateLimiter {

    private static final long valueOffset;
    private static Unsafe unsafe = null;

    static {
        try {
            // 应用开发中使用unsafe对象必须通过反射获取
            Class<?> clazz = Unsafe.class;
            Field f = clazz.getDeclaredField("theUnsafe");
            f.setAccessible(true);
            unsafe = (Unsafe) f.get(clazz);
            valueOffset = unsafe.objectFieldOffset(RateLimiter.class.getDeclaredField("token"));
        } catch (Exception ex) {
            throw new Error(ex);
        }
    }

    private final int originToken;
    private final Object lock = new Object();
    private volatile int token;

    public RateLimiter(int token) {
        this.originToken = token;
        this.token = token;
    }

    /**
     * 获取一个令牌
     */
    public boolean acquire() {
        int current = token;
        if (current <= 0) {
            // 保证在token已经用光的情况下依然有获取竞争的能力
            current = originToken;
        }

        long expect = 1000;// max wait 1s
        long future = System.currentTimeMillis() + expect;
        while (current > 0) {
            if (compareAndSet(current, current - 1)) {
                return true;
            }
            current = token;
            if (current <= 0 && expect > 0) {
                // 在有效期内等待通知
                synchronized (lock) {
                    try {
                        lock.wait(expect);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
                current = token;
                if (current <= 0) {
                    current = originToken;
                }
                expect = future - System.currentTimeMillis();
            }
        }
        return false;
    }

    private boolean compareAndSet(int expect, int update) {
        return unsafe.compareAndSwapInt(this, valueOffset, expect, update);
    }

    /**
     * 补充令牌
     */
    public void supplement(final ExecutorService executorService) {
        this.token = originToken;
        executorService.execute(() -> {
            synchronized (lock) {
                lock.notifyAll();
            }
        });
    }
}