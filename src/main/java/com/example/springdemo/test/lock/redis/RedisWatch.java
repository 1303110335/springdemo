/**
 * fshows.com
 * Copyright (C) 2013-2020 All Rights Reserved.
 */
package com.example.springdemo.test.lock.redis;

import com.example.springdemo.test.threadpool.customThread.CustomRejectedExecutionHandler;
import com.example.springdemo.test.threadpool.customThread.CustomThreadFactory;
import com.example.springdemo.test.threadpool.customThread.CustomThreadPoolExecutor;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author xuleyan
 * @version RedisWatch.java, v 0.1 2020-03-22 5:28 PM xuleyan
 */
public class RedisWatch {

    private Jedis jedis;

    public static void main(String[] args) {
        RedisWatch redisWatch = new RedisWatch();
        redisWatch.test();

    }

    private void test() {
        RedisWatch redisWatch = new RedisWatch();
        CustomThreadPoolExecutor poolExecutor = new CustomThreadPoolExecutor();
        ThreadPoolExecutor pool = new ThreadPoolExecutor(
                5,
                10,
                60,
                TimeUnit.MINUTES,
                new ArrayBlockingQueue<>(100),
                new CustomThreadFactory(),
                new CustomRejectedExecutionHandler()
        );
        ExecutorService threadPoolExecutor = poolExecutor.getCustomThreadPoolExecutor();

        try {
            for (int i = 0; i < 10; i++) {
                System.out.println("执行第" + i + "个任务");
//                threadPoolExecutor.submit(redisWatch::watch);
                Future<?> future = threadPoolExecutor.submit(redisWatch::watch);
                System.out.println(future.get());

//                threadPoolExecutor.execute(redisWatch::watch);

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            redisWatch.close();
            threadPoolExecutor.shutdown();
        }
    }

    public void close() {
        if (jedis != null) {
            jedis.close();
        }
    }

    private String watch() {
        System.out.println("开始执行watch");

        String watchKeys = "watchKeys";
        // 初始值 value = 1
        getSingleJedis();

        //jedis.set(watchKeys, "1");
        // 监听key为watchKeys的值
        jedis.watch(watchKeys);

        // 开启事务
        Transaction tx = jedis.multi();

        // watchKeys 自增
        tx.incr(watchKeys);

        // 执行事务，如果其他线程对watchKeys中的value进行修改
        // 通过redis事务以及watch命令实现乐观锁
        List<Object> exec = tx.exec();
        if (exec == null) {
            System.out.println("事务未执行");
            return "事务未执行";
        } else {
//            System.out.println("事务执行成功， watchKyes的value成功修改");
            String s = jedis.get(watchKeys);
            System.out.println("执行完成之后的值：" + s);
            return "执行完成之后的值：" + s;
        }
    }

    public void getSingleJedis() {
        if (jedis == null) {
            Jedis jedis = new Jedis("127.0.0.1", 6379);
            jedis.auth("123456");
            this.jedis = jedis;
        }
    }
}