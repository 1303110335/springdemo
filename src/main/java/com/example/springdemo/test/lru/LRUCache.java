/**
 * fshows.com
 * Copyright (C) 2013-2020 All Rights Reserved.
 */
package com.example.springdemo.test.lru;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 利用linkedHashMap实现lru算法，超出最近最少使用
 *
 * @author xuleyan
 * @version LRUCache.java, v 0.1 2020-05-09 4:53 PM xuleyan
 */
public class LRUCache<K, V> extends LinkedHashMap<K, V> {
    private static final Logger log = LoggerFactory.getLogger(LRUCache.class);
    private static LRUCache<String, Integer> cache = new LRUCache<>(10);
    private int cacheSize;

    public LRUCache(int cacheSize) {
        super(16, (float) 0.75, true);
        this.cacheSize = cacheSize;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            cache.put("k" + i, i);
        }
        log.info("all cache :'{}'", cache);
        cache.get("k3");
        log.info("get k3 :'{}'", cache);
        cache.get("k0");
        log.info("get k0 :'{}'", cache);
        cache.get("k4");
        log.info("get k4 :'{}'", cache);
        cache.put("k" + 10, 10);
        log.info("After running the LRU algorithm cache :'{}'", cache);
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > cacheSize;
    }
}