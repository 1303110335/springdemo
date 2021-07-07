/**
 * fshows.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.example.springdemo.test.bitmap;

import io.swagger.models.auth.In;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xuleyan
 * @version TestBitMap.java, v 0.1 2019-05-14 7:37 PM xuleyan
 */
public class TestBitMap {

    //1.字节：byte：用来计量存储容量的一种计量单位；位：bit
    //2.一个字节等于8位  1byte = 8bit
    private static final int N = 100000000;

    private int[] a = new int[N / 32 + 1];

    /**
     * 设置所在的bit为1   a[n/32][n%32]
     *
     * @param n
     */
    public void addValue(int n) {
        // row = n / 32 求十进制在数组a中的下标
        int row = n >> 5;
        // 相当于 n % 32 求十进制数在a[i]中的下标  |= 就是相加 +=
        a[row] |= 1 << (n & 0x1F);
    }

    /**
     * 判断所在的bit是否为1
     *
     * @param n
     * @return
     */
    public boolean exists(int n) {
        int row = n >> 5;
        int position = n & 0x1F;
        return (a[row] & (1 << (position))) != 0;
    }

    public void display(int row) {
        System.out.println("BitMap位图展示");
        for (int i = 0; i < row; i++) {
            List<Integer> list = new ArrayList<>();
            int temp = a[i];
            for (int j = 0; j < 32; j++) {
                list.add(temp & 1);
                temp >>= 1;
            }
            System.out.println("a[" + i + "]" + list);
        }
    }

    public static void main(String[] args) {

        int num[] = {1, 5, 30, 32, 64, 56, 159, 120, 21, 17, 35, 45};
        TestBitMap map = new TestBitMap();
        for (int i = 0; i < num.length; i++) {
            map.addValue(num[i]);
        }

        int temp = 2;
        if (map.exists(temp)) {
            System.out.println("temp:" + temp + "has already exists");
        }
        map.display(5);


    }


}