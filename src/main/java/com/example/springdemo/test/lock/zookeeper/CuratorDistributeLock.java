/**
 * fshows.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.example.springdemo.test.lock.zookeeper;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;


/**
 * @author xuleyan
 * @version CuratorDistributeLock.java, v 0.1 2019-12-08 8:47 PM xuleyan
 */
public class CuratorDistributeLock {

    public static void main(String[] args) {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework client = CuratorFrameworkFactory.newClient("localhost:2181", retryPolicy);
        client.start();
        CuratorFramework client2 = CuratorFrameworkFactory.newClient("localhost:2181", retryPolicy);
        client2.start();
        //创建分布式锁, 锁空间的根节点路径为/curator/lock
        InterProcessMutex mutex = new InterProcessMutex(client, "/curator/lock");
        final InterProcessMutex mutex2 = new InterProcessMutex(client2, "/curator/lock");
        try {
            mutex.acquire();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //获得了锁, 进行业务流程
        System.out.println("clent Enter mutex");
        Thread client2Th = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    mutex2.acquire();
                    System.out.println("client2 Enter mutex");
                    mutex2.release();
                    System.out.println("client2 release lock");

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
        client2Th.start();
        //完成业务流程, 释放锁
        try {
            Thread.sleep(5000);
            mutex.release();
            System.out.println("client release lock");
            client2Th.join();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //关闭客户端
        client.close();
    }
}
