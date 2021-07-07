/**
 * fshows.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.example.springdemo.test.zookeeper;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * 基于zk的分布式leader节点选举
 * <pre>
 *     方案一：父节点监听方式
 *
 *     实现思路：监听父节点状态
 *     1.在父节点(持久化)下创建临时节点，实际创建的节点路径会根据数量进行自增(ZK自编号方式创建节点)。
 *     2.创建节点成功后，获取父节点下的子节点列表，判断本线程的路径后缀编号是否是所有子节点中最小的，若是则成为leader，
 *     反之监听父节点变动状态(通过getChildren()方法注册watcher)
 *     3.当父节点状态变动(主要是子节点列表变动)后watcher会接收到通知，这时判断父节点下的子节点的排序状态，
 *     若满足本线程的路径后缀编号最小则成为leader，反之继续注册watcher监听父节点状态
 * @author xuleyan
 * @version ZKLeader.java, v 0.1 2019-10-23 11:59 AM xuleyan
 */
public class ZKLeader {

    private final static String BASE_NODE_PATH = "/ZKLeader_Leader";
    private final static String NODE_PATH = "host_process_no_";
    private static ZKLeader zkLeader;
    private Logger logger = LoggerFactory.getLogger(ZKLeader.class);
    private String finalNodePath;

    //是否是主节点标志位
    private boolean leader = false;

    private String host = "127.0.0.1";
    private String port = "2181";
    private ZooKeeper zooKeeper;
    private FatherWatcher fatherWatcher;

    //是否连接成功标志位
    private boolean connected = false;

    private ZKLeader(String host, String port) {
        this.host = host;
        this.port = port;
        this.fatherWatcher = new FatherWatcher(this);
    }

    public static ZKLeader create(String host, String port) {
        ZKLeader zkLeader = new ZKLeader(host, port);
        zkLeader.connectZookeeper();
        return zkLeader;
    }

    public boolean leader() {
        return leader;
    }

    public void close() {
        disconnectZooKeeper();
    }

    private boolean disconnectZooKeeper() {
        if (zooKeeper == null) {
            return false;
        }
        try {
            connected = false;
            leader = false;
            zooKeeper.close();
        } catch (Exception e) {
            logger.warn(String.format("ZK disconnect failed. [%s]", e.getMessage()), e);
        }
        return true;
    }

    private boolean connectZookeeper() {
        try {
            zooKeeper = new ZooKeeper(host + ":" + port, 60000, event -> {
                if (event.getState() == Watcher.Event.KeeperState.AuthFailed) {
                    leader = false;
                } else if (event.getState() == Watcher.Event.KeeperState.Disconnected) {
                    leader = false;
                } else if (event.getState() == Watcher.Event.KeeperState.Expired) {
                    leader = false;
                } else {
                    if (event.getType() == Watcher.Event.EventType.None) {
                        //说明连接成功了
                        connected = true;
                    }
                }
            });

            int i = 1;
            while (!connected) {
                if (i == 100) {
                    break;
                }
                Thread.sleep(300);
                i++;
            }

            if (connected) {
                if (zooKeeper.exists(BASE_NODE_PATH, false) == null) {
                    zooKeeper.create(BASE_NODE_PATH, "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
                }
                // 创建子节点
                finalNodePath = zooKeeper.create(BASE_NODE_PATH + "/" + NODE_PATH, "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);

                //检查一次是否是主节点
                checkLeader();
            } else {
                logger.warn("Connect zookeeper failed. Time consumes 30 s");
                return false;
            }
        } catch (Exception e) {
            logger.warn(e.getMessage(), e);
            return false;
        }
        return true;
    }

    private void checkLeader() {
        if (!connected) {
            return;
        } else {
            try {
                //获取子节点列表同时再次注册监听
                List<String> childrenList = zooKeeper.getChildren(BASE_NODE_PATH, fatherWatcher);

                if (judgePathNumMin(childrenList)) {
                    leader = true;
                }
            } catch (Exception e) {
                logger.warn(e.getMessage(), e);
            }
        }
    }

    private boolean judgePathNumMin(List<String> childrenList) {
        if (childrenList.isEmpty()) {
            return true;
        }

        if (childrenList.size() >= 2) {
            //对无序状态的path列表按照编号升序排序
            childrenList.sort((str1, str2) -> {
                int num1;
                int num2;
                String string1 = str1.substring(NODE_PATH.length());
                String string2 = str2.substring(NODE_PATH.length());
                num1 = Integer.parseInt(string1);
                num2 = Integer.parseInt(string2);
                if (num1 > num2) {
                    return 1;
                } else if (num1 < num2) {
                    return -1;
                } else {
                    return 0;
                }
            });
        }
        String minId = childrenList.get(0);
        return finalNodePath.equals(BASE_NODE_PATH + "/" + minId);
    }


    private class FatherWatcher implements Watcher {
        private ZKLeader context;

        FatherWatcher(ZKLeader context) {
            this.context = context;
        }

        @Override
        public void process(WatchedEvent event) {
            //子节点有变化
            if (event.getType() == Watcher.Event.EventType.NodeChildrenChanged) {
                context.checkLeader();
            }
        }
    }
}