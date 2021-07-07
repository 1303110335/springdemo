/**
 * fshows.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.example.springdemo.sort.insertSort;

/**
 * 插入排序
 * 在已有序列中按顺序插入新的值
 * 时间复杂度 O(n2) 最好情况O(n) 稳定
 *
 * @author xuleyan
 * @version InsertSort.java, v 0.1 2019-10-22 2:02 PM xuleyan
 */
public class InsertSort {

    public static void main(String[] args) {
        int[] ins = {2, 3, 5, 1, 23, 6, 78, 34};
        int[] ins2 = sort(ins);
        for (int in : ins2) {
            System.out.println(in);
        }
    }

    private static int[] sort(int[] ins) {
        // 第一层：从1开始循环所有数据 i -》 temp
        for (int i = 1; i < ins.length; i++) {
            // 第二层：每次将temp和前面所有的值进行比较，若比前面小则交换，否则不变
            int temp = ins[i];
            int j;
            for (j = i; j > 0 && ins[j - 1] > temp; j--) {
                ins[j] = ins[j - 1];
            }
            ins[j] = temp;
        }
        return ins;
    }
}