/**
 * fshows.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.example.springdemo.test.bitmap;

/**
 * @author xuleyan
 * @version BloomFilter.java, v 0.1 2019-05-15 8:34 AM xuleyan
 * 屈永泉 布隆过滤器 快速确定哪些网页已经被下载过
 */
/**屈永泉 布隆过滤器 快速确定哪些网页已经被下载过*/

import java.util.BitSet;

public class BloomFilter {
    private int defaultSize = 5000 << 10000;
    private int basic = defaultSize - 1;
    private BitSet bits = new BitSet(defaultSize);

    /**
     * m = 8, m越大，错误概率越小
     * @param key
     * @return
     */
    private int[] lrandom(String key) { // 产生八个随机数并返回
        int[] randomsum = new int[8];
        for (int i = 0; i < 8; i++) {
            randomsum[0] = hashCode(key, i + 1);
        }
        return randomsum;

    }

    // 将一个URL加入
    public synchronized void add(String key) {
        int keyCode[] = lrandom(key);
        for (int i = 0; i < 8; i++) {
            bits.set(keyCode[i]); // 将指定索引处的位设置为 true
        }
    }

    // 判断一个URL是否存在
    public boolean exist(String key) {
        int keyCode[] = lrandom(key);
        if (bits.get(keyCode[0])
                && bits.get(keyCode[1]) // 返回指定索引处的位值。
                && bits.get(keyCode[2]) && bits.get(keyCode[3])
                && bits.get(keyCode[4]) && bits.get(keyCode[5])
                && bits.get(keyCode[6]) && bits.get(keyCode[7])) {
            return true;
        }
        return false;
    }


    private int hashCode(String key, int Q) {
        int h = 0;
        int off = 0;
        char val[] = key.toCharArray(); // 将此URl转换为一个新的字符数组
        int len = key.length();
        for (int i = 0; i < len; i++) {
            h = (30 + Q) * h + val[off++];
        }
        return basic & h;
    }


    public static void main(String[] args) { // TODO Auto-generated method
        long pre = 0;
        long post = 0;
        pre = System.nanoTime();
        BloomFilter f = new BloomFilter(); //初始化
        f.add("http://www.agrilink.cn/");
        f.add("http://www.baidu.com/");
        System.out.println(f.exist("http://www.baidu.com/"));
        System.out.println(f.exist("http://www.baidud.com/"));
        post = System.nanoTime();
        System.out.println("Time: " + (post - pre));

    }

}