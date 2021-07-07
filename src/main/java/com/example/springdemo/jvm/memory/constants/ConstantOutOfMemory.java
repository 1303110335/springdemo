/**
 * fshows.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.example.springdemo.jvm.memory.constants;

import com.fshows.fsframework.core.utils.FsDateUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * 在JDK1.7 字符串常量池被从方法区拿到了堆中, 这里没有提到运行时常量池,也就是说字符串常量池被单独拿到堆,运行时常量池剩下的东西还在方法区, 也就是hotspot中的永久代
 * 在JDK1.8 hotspot移除了永久代用元空间(Metaspace)取而代之, 这时候字符串常量池还在堆, 运行时常量池还在方法区, 只不过方法区的实现从永久代变成了元空间(Metaspace) 
 * @Described：常量池内存溢出探究（堆内存）
 * @VM args : -XX:PermSize=10M -XX:MaxPermSize=10M
 * -verbose:gc -Xms10M -Xmx10M -XX:+PrintGCDetails -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/Volumes/code/java/test/springdemo
 * @Result java.lang.OutOfMemoryError: Java heap space
 * @author xuleyan
 * @version ConstantOutOfMemory.java, v 0.1 2019-04-25 9:00 PM xuleyan
 */
public class ConstantOutOfMemory {

    public static void main(String[] args) {

        try {
            List<String> stringList = new ArrayList<>();
            int i = 0;
            while (true) {
                stringList.add(String.valueOf(i++).intern());
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

    }

    public static void testQueue() {
        // 1万的任务存储消耗大概3.5M的JVM内存空间
        // 5万的任务存储消耗大概4.3M的JVM内存空间
        // 10万的任务存储消耗大概5.3M的JVM内存空间
        int length = 100000;

        ArrayBlockingQueue<Runnable> blockingQueue = new ArrayBlockingQueue<Runnable>(length);
        for (int i = 0; i < length; i++) {
            blockingQueue.add(new Runnable() {
                @Override
                public void run() {

                    try {
                        asyncPushMessage();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        while (blockingQueue.iterator().hasNext()) {
            Runnable runnable = blockingQueue.iterator().next();
//            System.out.println("获取任务runable = " + runnable.toString());
            runnable.run();
//            System.out.println("执行任务runable = " + runnable.toString());
            blockingQueue.remove(runnable);
//            System.out.println("移除任务runable = " + runnable.toString());
        }


        System.out.println("执行完毕");
    }

    public static void asyncPushMessage() throws InterruptedException {

        String orderSn = "OSX20190090951552666";
        String bankName = "中国银行";
        String bankCard = "6224180088992322";
        Integer tradeDate = FsDateUtil.getCurrentSecond();
        String resultDesc = "成功";
        BigDecimal tradeMoney = new BigDecimal("2.01");
        Integer updateTime = FsDateUtil.getCurrentSecond();
        String storeId = "20190429100310021574";
        String cardNo = "6227002031011136926";
        SingleCallParam param = new SingleCallParam();
        param.setState(1);
        param.setTradeMoney(tradeMoney);
        param.setAppId("20190822123456788");
        param.setBankCard(bankCard);
        param.setBankName(bankName);
        param.setBankNo(cardNo);
        param.setCardNo(cardNo);
        param.setBillId(orderSn);
        param.setResultDesc(resultDesc);
        param.setTradeDate(tradeDate);
        param.setStoreId(storeId);
        param.setUpdateTime(updateTime);
        param.setUpdateDate(new Date());
        param.setIsAcct(1);
        param.setToken("209691");
        param.setUid(1512292);
    }
}