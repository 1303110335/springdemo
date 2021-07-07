/**
 * fshows.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.example.springdemo.test.zookeeper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author xuleyan
 * @version zkLeaderTwoTestWithMultiThread.java, v 0.1 2019-10-23 2:53 PM xuleyan
 */
public class zkLeaderTwoTestWithMultiThread {

    public static void main(String[] args) throws Exception {
        zkLeaderTwoTestWithMultiThread twoTestWithMultiThread = new zkLeaderTwoTestWithMultiThread();
        twoTestWithMultiThread.test();
    }

    private void test() throws Exception {
        List<LeaderTwoThread> leaderTwoThreads = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            leaderTwoThreads.add(new LeaderTwoThread(ZKLeader.create("127.0.0.1", "2181"), i));
        }
        leaderTwoThreads.forEach(LeaderTwoThread::start);

        //线程0断连
        Thread.sleep(20000);
        leaderTwoThreads.get(0).getZkLeaderTwo().close();
        System.out.println(String.format("线程: [%s] 断开连接", 0));

        //线程1断连
        Thread.sleep(20000);
        leaderTwoThreads.get(1).getZkLeaderTwo().close();
        System.out.println(String.format("线程: [%s] 断开连接", 1));

        //线程3断连
        Thread.sleep(20000);
        leaderTwoThreads.get(3).getZkLeaderTwo().close();
        System.out.println(String.format("线程: [%s] 断开连接", 3));

        //线程4断连
        Thread.sleep(20000);
        leaderTwoThreads.get(4).getZkLeaderTwo().close();
        System.out.println(String.format("线程: [%s] 断开连接", 4));

        //线程2断连
        Thread.sleep(20000);
        leaderTwoThreads.get(2).getZkLeaderTwo().close();
        System.out.println(String.format("线程: [%s] 断开连接", 2));

        Thread.sleep(60000);
    }

    private class LeaderTwoThread extends Thread {
        private ZKLeader zkLeaderTwo;
        private int threadNum;

        LeaderTwoThread(ZKLeader zkLeaderTwo, int threadNum) {
            this.zkLeaderTwo = zkLeaderTwo;
            this.threadNum = threadNum;
        }

        public ZKLeader getZkLeaderTwo() {
            return zkLeaderTwo;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(5000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Date dt = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String currentTime = sdf.format(dt);
                if (zkLeaderTwo.leader()) {
                    System.out.println(String.format("[%s] 线程: [%s] 是主节点", currentTime, threadNum));
                }
            }
        }
    }
}