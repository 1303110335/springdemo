/**
 * fshows.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.example.springdemo.test.hashmap;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xuleyan
 * @version TestHashMap.java, v 0.1 2019-03-24 7:10 PM xuleyan
 */
public class TestHashMap {

    public static final int MAXIMUM_CAPACITY = 1 << 30;

    static final int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }

    public static void main(String[] args) {

//         Integer result = FsDateUtil.formatSecond("2019-06-30", SIMPLE_DATE_FORMAT);
//         System.out.println(result);

//        Integer a = null;
        String str = "";

//        for (int i = 0; i < 1; i++) {
//            int j = 2;
//            System.out.println(i);
//        }

//        Map<String, String> hashMap = new TreeMap<>();
//        hashMap.put("1name", "xly");
//        hashMap.put("3name", "xly3");
//        hashMap.put("2name", "xly2");
//        System.out.println(hashMap);
//

//        RBTree rbTree = new RBTree();

        Map<Integer, Integer> hashMap2 = new HashMap<>(2);
        int size = tableSizeFor(8);
        System.out.println(size);

//        for (int i = 0; i < 1000; i += 16) {
//            Double dou = Math.random() * 100;
//            hashMap2.put(i, dou.intValue());
//        }

//        hashMap2.put(2, 16);
//        hashMap2.put(18, 18);
//        hashMap2.put(2, 32);
//        hashMap2.put(0, 144);
//
//        System.out.println(hashMap2.get(null));
//        System.out.println(hashMap2);


//        ConcurrentMap<String, String> concurrentMap = new ConcurrentHashMap<>();

//        Collections.synchronizedMap(hashMap2);

//        Hashtable<String, String> table = new Hashtable<>();

//        System.out.println((int)0.75);


//        int n = 16;
//
//        System.out.println((n - 1) & 1);
//        System.out.println((n - 1) & 2);
//        System.out.println((n - 1) & 3);
//        System.out.println((n - 1) & 4);
//        System.out.println((n - 1) & 5);
//        System.out.println((n - 1) & 6);
//        System.out.println((n - 1) & 7);
//        System.out.println((n - 1) & 8);
//        System.out.println((n - 1) & 9);
//        System.out.println((n - 1) & 10);
//        System.out.println((n - 1) & 15);


//        int j=0;
//        for(int i=0;i<100;i++) {
//            j=j++;
//        }
//
//        System.out.println(j);

//        AtomicInteger atomicInteger = new AtomicInteger(1);
//        atomicInteger.getAndIncrement();
//        atomicInteger.incrementAndGet();
//        atomicInteger.addAndGet(10);
//        atomicInteger.decrementAndGet();
//        atomicInteger.getAndDecrement();
//
//        System.out.println(atomicInteger);

//        List<Integer> integers = new ArrayList<>(Arrays.asList(1, 2, 3));
//        integers.add(1);
//        integers.add(2, 0);
//        integers.remove(2);
//        integers.removeAll(Arrays.asList(1,2));
//        Integer result = integers.set(2, 5);
//        System.out.println(result);

//        AtomicStampedReference atomicStampedReference = new AtomicStampedReference();

//        LinkedList linkedList = new LinkedList();

    }
}