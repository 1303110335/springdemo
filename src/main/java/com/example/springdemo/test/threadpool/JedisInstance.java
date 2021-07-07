/**
 * fshows.com
 * Copyright (C) 2013-2020 All Rights Reserved.
 */
package com.example.springdemo.test.threadpool;

import redis.clients.jedis.Jedis;

/**
 * @author xuleyan
 * @version JedisInstance.java, v 0.1 2020-03-22 8:18 PM xuleyan
 */
public class JedisInstance {

    public synchronized static Jedis getSingleJedis() {
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        jedis.auth("123456");
        return jedis;
    }
}