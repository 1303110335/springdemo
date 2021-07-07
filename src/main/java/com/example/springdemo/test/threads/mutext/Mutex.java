/**
 * fshows.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.example.springdemo.test.threads.mutext;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @author xuleyan
 * @version Mutex.java, v 0.1 2019-10-25 2:03 PM xuleyan
 */
public class Mutex implements Lock, Serializable {

    private final Sync sync = new Sync();

    @Override
    public void lock() {
        sync.acquire(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        sync.acquireInterruptibly(1);
    }

    @Override
    public boolean tryLock() {
        return sync.tryAcquire(1);
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return sync.tryAcquireNanos(1, unit.toNanos(time));
    }

    @Override
    public void unlock() {
        sync.release(1);
    }

    @Override
    public Condition newCondition() {
        return sync.newCondition();
    }

    public boolean isLocked() {
        return sync.isHeldExclusively();
    }

    public boolean hasQueuedThreads() {
        return sync.hasQueuedThreads();
    }

    /**
     * 继承AQS的静态内存类
     */
    private static class Sync extends AbstractQueuedSynchronizer {

        @Override
        protected boolean isHeldExclusively() {
            return getState() == 1;
        }

        /**
         * acquires the lock if state is zero
         *
         * @param acquires
         * @return
         */
        @Override
        protected boolean tryAcquire(int acquires) {
            assert acquires == 1;
            System.out.println(Thread.currentThread().getName() + "try lock");
            if (compareAndSetState(0, 1)) {
                System.out.println(Thread.currentThread().getName() + "lock");
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            return false;
        }

        /**
         * releases the lock by setting state to zero
         *
         * @param releases
         * @return
         */
        @Override
        protected boolean tryRelease(int releases) {
            assert releases == 1;
            System.out.println(Thread.currentThread().getName() + "try release");
            if (getState() == 0) {
                throw new IllegalMonitorStateException();
            }
            System.out.println(Thread.currentThread().getName() + "release");
            setExclusiveOwnerThread(null);
            setState(0);
            return true;
        }

        Condition newCondition() {
            return new ConditionObject();
        }

        private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
            stream.defaultReadObject();
            setState(0);
        }
    }
}

