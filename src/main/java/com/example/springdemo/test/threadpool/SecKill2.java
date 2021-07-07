/**
 * fshows.com
 * Copyright (C) 2013-2020 All Rights Reserved.
 */
package com.example.springdemo.test.threadpool;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author xuleyan
 * @version Seckill2.java, v 0.1 2020-03-22 8:50 PM xuleyan
 */
public class SecKill2 {
    public static void main(String[] arg) {
        //库存key
        String redisKey = "stock";
        ExecutorService executorService = Executors.newFixedThreadPool(20);
        try {
            Jedis jedis = new Jedis("127.0.0.1", 6379);
            jedis.auth("123456");
            // 可以被秒杀的库存的初始值，库存总共20个
            jedis.set(redisKey, "0");
            jedis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (int i = 0; i < 1000; i++) {
            executorService.execute(() -> {
                Jedis jedis1 = new Jedis("127.0.0.1", 6379);
                jedis1.auth("123456");
                try {
                    jedis1.watch(redisKey);
                    String redisValue = jedis1.get(redisKey);
                    int valInteger = Integer.valueOf(redisValue);
                    String userInfo = UUID.randomUUID().toString();
                    // 没有秒完
                    if (valInteger < 20) {
                        Transaction tx = jedis1.multi();
                        tx.incr(redisKey);
                        List list = tx.exec();
                        // 秒成功 失败返回空list而不是空
                        if (list != null && list.size() > 0) {
                            System.out.println("用户：" + userInfo + "，秒杀成 功！当前成功人数：" + (valInteger + 1));
                        }
                        // 版本变化，被别人抢了。
                        else {
                            System.out.println("用户：" + userInfo + "，秒杀失 败");
                        }
                    }
                    // 秒完了
                    else {
                        System.out.println("已经有20人秒杀成功，秒杀结束");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    jedis1.close();
                }
            });
        }
        executorService.shutdown();
    }
}