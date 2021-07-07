/**
 * fshows.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.example.springdemo.test.abstractQueuedSynchronizer;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @author xuleyan
 * @version TwinsLock.java, v 0.1 2019-06-26 12:01 PM xuleyan
 */
public class TwinsLock implements Lock {

    private final Sync sync = new Sync(2);

    @Override
    public void lock() {
        sync.acquireShared(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public void unlock() {
        sync.releaseShared(1);
    }

    @Override
    public Condition newCondition() {
        return null;
    }

    private static final class Sync extends AbstractQueuedSynchronizer {
        private static final long serialVersionUID = 1947733228284515036L;

        Sync(int count) {
            if (count < 0) {
                throw new IllegalArgumentException("锁资源数不能为负数~");
            }
            // 调用AQS设置资源总数，备用
            setState(count);
        }

        @Override
        protected int tryAcquireShared(int reduceCount) {
            // cas 获取锁
            // 由AQS的acquireShared -> doAcquireShared 调用
            for (; ; ) {
                int current = getState();
                int newCount = current - reduceCount;
                if (newCount < 0 || compareAndSetState(current, newCount)) {
                    return newCount;
                }
            }
        }

        @Override
        protected boolean tryReleaseShared(int returnCount) {
            // cas 释放锁
            // 由AQS releaseShared -> doReleaseShared调用
            for (; ; ) {
                int current = getState();
                int newState = current + returnCount;
                if (compareAndSetState(current, newState)) {
                    return true;
                }
            }
        }

    }
}