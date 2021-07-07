/**
 * fshows.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.example.springdemo.config;

import com.fshows.fsframework.extend.redis.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

/**
 * @author auto
 * @version SpringRedisConfig.java, v 0.1 2018-10-12 17:29 auto
 */
//@Configuration
public class SpringRedisConfig {

    @Autowired
    private SysConfig sysConfig;

    @Bean
    public RedisCache redisCache() {
        RedisCache redisCache = new RedisCache();
        redisCache.setHost("redis://" + sysConfig.getRedisHost() + ":" + sysConfig.getRedisPort());
        redisCache.setPassword(sysConfig.getRedisPassword());
        redisCache.setConnectTimeout(sysConfig.getRedisConnectTimeout());
        return redisCache;
    }
}