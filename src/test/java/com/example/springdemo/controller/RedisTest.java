/**
 * fshows.com
 * Copyright (C) 2013-2020 All Rights Reserved.
 */
package com.example.springdemo.controller;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * @author xuleyan
 * @version RedisTest.java, v 0.1 2020-03-24 8:48 AM xuleyan
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class RedisTest {

    @Test
    public void testJedisCluster() throws IOException {
        Set<HostAndPort> nodes = new HashSet<>();
        String host = "127.0.0.1";
        nodes.add(new HostAndPort(host, 7000));
        nodes.add(new HostAndPort(host, 7001));
        nodes.add(new HostAndPort(host, 7002));
        nodes.add(new HostAndPort(host, 7003));
        nodes.add(new HostAndPort(host, 7004));
        nodes.add(new HostAndPort(host, 7005));
        nodes.add(new HostAndPort(host, 7006));

        GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
        JedisCluster cluster = new JedisCluster(nodes, 10, 10, 1, "123456", poolConfig);
        cluster.set("cluster-test", "my jedis cluster test");
        String result = cluster.get("cluster-test");
        System.out.println(result);
        cluster.close();
    }
}