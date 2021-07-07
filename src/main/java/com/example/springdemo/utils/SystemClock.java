/**
 * fshows.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.example.springdemo.utils;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author xuleyan
 * @version SystemClock.java, v 0.1 2019-04-12 9:09 AM xuleyan
 */
public class SystemClock {
    private static final SystemClock MILLIS_CLOCK = new SystemClock(1);
    private final long precision;
    private final AtomicLong now;

    private SystemClock(long precision) {
        this.precision = precision;
        now = new AtomicLong(System.currentTimeMillis());

    }

    public static SystemClock millisClock() {
        return MILLIS_CLOCK;
    }

    private void scheduleClockUpdating() {
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor(runnable -> {
            Thread thread = new Thread(runnable, "system.clock");
            thread.setDaemon(true);
            return thread;
        });
        scheduler.scheduleAtFixedRate(() -> now.set(System.currentTimeMillis()), precision, precision, TimeUnit.MICROSECONDS);
    }

    public long now() {
        return now.get();
    }

    public Integer seconds() {
        return Integer.valueOf(now.get() / 1000 + "");
    }

    public static void main(String[] args) {

        System.out.println(SystemClock.millisClock().now());
    }
}