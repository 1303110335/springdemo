/**
 * fshows.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.example.springdemo.controller.redis;

import com.fshows.fsframework.extend.redis.RedisCache;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author xuleyan
 * @version Index.java, v 0.1 2019-10-05 5:58 PM xuleyan
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class Index {

    @Autowired
    private RedisCache redisCache;

    @Test
    public void testRedis() {
        redisCache.put("name", "xuleyan");
        String name = redisCache.get("name");
        System.out.println(name);
    }
}