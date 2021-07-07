/**
 * fshows.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.example.springdemo.test.lock.zookeeper;

import org.I0Itec.zkclient.IZkDataListener;

import java.util.concurrent.CountDownLatch;

/**
 * @author xuleyan
 * @version ZookeeperDistrbuteLock.java, v 0.1 2019-12-08 9:55 AM xuleyan
 */
public class ZookeeperDistrbuteLock extends AbstractZookeeperLock {

    private CountDownLatch countDownLatch = new CountDownLatch(20);

    @Override
    void waitLock() {
        IZkDataListener iZkDataListener = new IZkDataListener() {
            @Override
            public void handleDataChange(String s, Object o) throws Exception {

            }

            @Override
            public void handleDataDeleted(String s) throws Exception {
                if (countDownLatch != null) {
                    countDownLatch.countDown();
                }
            }
        };

//        zkClient.subscribeChildChanges(lockPath, iZkDataListener);
    }

    @Override
    boolean tryLock() {
        try {
            zkClient.createEphemeral(lockPath);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}