/**
 * fshows.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.example.springdemo.jvm.memory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Phaser;

import static java.util.stream.Collectors.toList;

/**
 * -XX:+PrintGCDetails -Xms4G -Xmx4G -Xmn4G -XX:SurvivorRatio=2 -XX:MetaspaceSize=1G -XX:ConcGCThreads=8
 * testParallelStream:852    784
 * testStreamtime:1242       1192
 * jdk1.7time:6872           6785
 * <p>
 * -XX:+PrintGCDetails -Xms4G -Xmx4G -Xmn4G -XX:SurvivorRatio=3 -XX:MetaspaceSize=1G -XX:ConcGCThreads=8
 * testParallelStream:869
 * testStreamtime:1374
 * jdk1.7time:6021
 * <p>
 * -XX:+PrintGCDetails -Xms4G -Xmx4G -Xmn4G -XX:SurvivorRatio=4 -XX:MetaspaceSize=1G -XX:ConcGCThreads=8
 * testParallelStream:492
 * testStreamtime:743
 * jdk1.7time:5763
 * <p>
 * -XX:+PrintGCDetails -Xms5G -Xmx5G -Xmn5G -XX:SurvivorRatio=2 -XX:MetaspaceSize=1G -XX:ConcGCThreads=8
 * <p>
 * -XX:+PrintGCDetails -Xms5G -Xmx5G -Xmn5G -XX:SurvivorRatio=2 -XX:MetaspaceSize=1G -XX:ConcGCThreads=8 -XX:NewRatio=2
 *
 * @author xuleyan
 * @version TestStream.java, v 0.1 2019-09-30 5:12 PM xuleyan
 */
public class TestStream {
    static List<Transaction> transactions = new ArrayList<Transaction>();
    static int len = 10000000;

    public static void main(String args[]) {
        System.out.println("数据量 len:" + len + " ,处理器数量：" + Runtime.getRuntime().availableProcessors() + ";总内存:" + Runtime.getRuntime().totalMemory() + ";最大内存:" + Runtime.getRuntime().maxMemory() + ";free 内存:" + Runtime.getRuntime().freeMemory());
        Random rand = new Random();
        for (int i = 0; i < len; i++) {
            Transaction t = new Transaction();
            t.setId(i + 1);
            t.setType(100);
            t.setValue(rand.nextInt(len) + 1);
            transactions.add(t);
        }
        MyThreadQi myq = new MyThreadQi();
        myq.start();
        MyThreadStream mts = new MyThreadStream();
        mts.start();
        MyThreadParallStream mtps = new MyThreadParallStream();
        mtps.start();

    }

    public static void testJdkQie() {
        List<Transaction> groceryTransactions = new ArrayList<Transaction>();
        for (Transaction t : transactions) {
            if (t.getType() == Transaction.GROCERY) {
                groceryTransactions.add(t);
            }
        }
        Collections.sort(groceryTransactions, new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                Transaction t1 = (Transaction) o1;
                Transaction t2 = (Transaction) o2;
                if (t1.getValue() > t2.getValue()) {
                    return 1;
                } else if (t1.getValue() == t2.getValue()) {
                    return 0;
                } else {
                    return -1;
                }
                //return t1.getValue().compareTo(t2.getValue());
            }
        });
        List<Integer> transactionIds = new ArrayList<>();
        for (Transaction t : groceryTransactions) {
            transactionIds.add(t.getId());
            //System.out.println("id："+t.getId());
        }
        //System.out.println("resultLen："+ transactionIds.size());
    }

    public static void testStream() {
        List<Integer> transactionsIds = transactions.stream()
                .filter(t -> t.getType() == Transaction.GROCERY)
//                .sorted(comparing(Transaction::getValue).reversed())
                .map(Transaction::getId)
                .collect(toList());
        //for(int i:transactionsIds){
        //System.out.println("id："+i);
        //}
        //System.out.println("resultLen："+ transactionsIds.size());
    }

    public static void testParallelStream() {
        List<Integer> transactionsIds = transactions.parallelStream()
                .filter(t -> t.getType() == Transaction.GROCERY)
//                .sorted(comparing(Transaction::getValue).reversed())
                .map(Transaction::getId)
                .collect(toList());
        //for(int i:transactionsIds){
        //System.out.println("id："+i );
        //}
        //System.out.println("resultLen："+ transactionsIds.size());
    }
}

class Transaction {
    final static int GROCERY = 100;
    int id;
    int type;
    String name;
    int value;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

class MyThreadQi extends Thread {

    @Override
    public void run() {
        long start = System.currentTimeMillis();
        TestStream.testJdkQie();
        System.out.println("jdk1.7time:" + (System.currentTimeMillis() - start));
    }
}

class MyThreadStream extends Thread {
    @Override
    public void run() {
        long start2 = System.currentTimeMillis();
        TestStream.testStream();
        System.out.println("testStreamtime:" + (System.currentTimeMillis() - start2));
    }

}

class MyThreadParallStream extends Thread {
    @Override
    public void run() {
        long start3 = System.currentTimeMillis();
        TestStream.testParallelStream();
        System.out.println("testParallelStream:" + (System.currentTimeMillis() - start3));
    }
}

class MyTransactionPhaser extends Phaser {

}