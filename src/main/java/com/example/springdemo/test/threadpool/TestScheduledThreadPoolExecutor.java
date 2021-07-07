/**
 * fshows.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.example.springdemo.test.threadpool;

import com.fshows.fsframework.core.utils.LogUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author xuleyan
 * @version TestScheduledThreadPoolExecutor.java, v 0.1 2019-11-19 9:17 PM xuleyan
 */
@Slf4j
public class TestScheduledThreadPoolExecutor {

    public static void main(String[] args) {

        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(2);
        scheduledThreadPoolExecutor.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                LogUtil.info(log, "time:" + new Date());
            }
        }, 0, 40, TimeUnit.MILLISECONDS);

    }

    private static void testTimer() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                LogUtil.info(log, "time:");
            }
        }, 10000, 40);
    }
}