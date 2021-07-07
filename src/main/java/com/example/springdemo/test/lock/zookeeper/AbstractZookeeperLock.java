/**
 * fshows.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.example.springdemo.test.lock.zookeeper;

import org.I0Itec.zkclient.ZkClient;

/**
 * @author xuleyan
 * @version AbstractZookeeperLock.java, v 0.1 2019-12-08 9:50 AM xuleyan
 */
public abstract class AbstractZookeeperLock implements ExtLock {

    private static final String CONNECTION = "127.0.0.1:2181";

    protected ZkClient zkClient = new ZkClient(CONNECTION);

    protected String lockPath = "/lockPath";

    @Override
    public void getLock() {
        // 连接zkClient 创建一个/lock的临时节点
        if (tryLock()) {
            System.out.println("**********成功获取到锁**************");
        } else {
            // 进行等待
            waitLock();
        }
    }

    abstract void waitLock();

    abstract boolean tryLock();

    // 释放锁
    @Override
    public void unLock() {
        //执行完毕，直接连接
        if (zkClient != null) {
            zkClient.close();
            System.out.println("**********释放锁完毕**********");
        }
    }
}