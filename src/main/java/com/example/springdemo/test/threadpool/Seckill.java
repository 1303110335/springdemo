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
 * @version Seckill.java, v 0.1 2020-03-22 6:25 PM xuleyan
 */
public class Seckill {
    // 库存key
    private static final String redisKey = "stock";

    public static void main(String[] args) {

        Seckill seckill = new Seckill();
        seckill.doKill();
    }

    private void doKill() {
        ExecutorService executorService = Executors.newFixedThreadPool(20);
        Jedis jedis = new Jedis();
        try {
            jedis = JedisInstance.getSingleJedis();
            jedis.set(redisKey, "0");
            jedis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Seckill seckill = new Seckill();
        for (int i = 0; i < 1000; i++) {
            executorService.execute(seckill::kill);
//            Future<?> future = executorService.submit(seckill::kill);
//            System.out.println(future.get());
        }

        executorService.shutdown();
    }

    private String kill() {
        Jedis jedis1 = JedisInstance.getSingleJedis();
        try {
            jedis1.watch(redisKey);
            String redisValue = jedis1.get(redisKey);
            int valInteger = Integer.valueOf(redisValue);
            String userInfo = UUID.randomUUID().toString();

            String message = "";
            // 如果没有秒完
            if (valInteger < 20) {
                Transaction tx = jedis1.multi();
                tx.incr(redisKey);
                List list = tx.exec();
                if (list != null && list.size() > 0) {
                    message = "用户：" + userInfo + ", 秒杀成功！当前成功人数：" + (valInteger + 1);
                    System.out.println(message);
                } else {
                    // 版本变化,别别人抢了
                    message = "用户：" + userInfo + ", 秒杀失败!";
                    System.out.println(message);

                }
            } else {
                // 秒完了
                message = "已有20人秒杀成功，秒杀结束";
                System.out.println(message);
            }
            return message;
        } catch (Exception ex) {
            ex.printStackTrace();
            return "异常";
        }
    }
}