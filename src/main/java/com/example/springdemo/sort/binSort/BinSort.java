/**
 * fshows.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.example.springdemo.sort.binSort;

/**
 * 基数排序
 * 时间复杂度 O(d(r+n))   稳定
 * 空间复杂度 O(rd+n)
 *
 * @author xuleyan
 * @version BinSort.java, v 0.1 2019-10-20 10:39 AM xuleyan
 */
public class BinSort {

    public static void main(String[] args) {
        //定义整型数组
        int[] arr = {21, 56, 88, 195, 354, 1, 35, 12, 6, 7};

        //调用基数排序函数
        radixSort(arr, 3);

        //输出排序后的数组
        for (int anArr : arr) {
            System.out.print(anArr + "  ");
        }
    }

    private static void lsdRadixsort(int[] arr, int max) {
        // 定义count数组用来计数
        int length = arr.length;
        int[] count = new int[length];
        // bucket用来当桶，放数据，取数据
        int[] bucket = new int[length];

        // 循环按照个位、十位、百位去排序
        for (int k = 1; k <= max; k++) {
            //把count置空，防止上次循环的数据影响
            for (int i = 0; i < length; i++) {
                count[i] = 0;
            }

            //分别统计第k位是0,1,2,3,4,5,6,7,8,9的数量
            //以下便称为桶
            //即此循环用来统计每个桶中的数据的数量
            for (int anArr : arr) {
                count[getFigure(anArr, k)]++;
            }

            //利用count[i]来确定放置数据的位置
            for (int i = 1; i < length; i++) {
                count[i] = count[i] + count[i - 1];
            }

            //利用循环把数据装入各个桶中，注意是从后往前装
            for (int i = arr.length - 1; i >= 0; i--) {
                // 获取对应位数的值
                int j = getFigure(arr[i], k);
                // 将值放入对应的位置
                bucket[count[j] - 1] = arr[i];
                // 将count[i]对应的位置减一（因为在同一个下标的情况下，上面已经对位置做了相加，这里要减掉）
                count[j]--;
            }

            //将桶中的数据取出来，赋值给arr
            for (int i = 0, j = 0; i < arr.length; i++, j++) {
                arr[i] = bucket[j];
            }
        }
    }

    private static void radixSort(int[] arr, int max) {
        int length = arr.length;
        // 定义count数组用于计数
        int[] count = new int[length];
        // 定义bucket数组用于存放数据
        int[] bucket = new int[length];

        //分别对每一位进行循环排序
        for (int k = 1; k <= max; k++) {
            // 重置count数组
            for (int j = 0; j < length; j++) {
                count[j] = 0;
            }

            // 确定每个下标有几个值
            for (int anArr : arr) {
                // 获取对应位数的值,作为count下标并将count对应的值+1
                count[getFigure(anArr, k)]++;
            }

            // 计算每个下标对应的位置
            for (int i = 1; i < length; i++) {
                count[i] = count[i] + count[i - 1];
            }

            // 将arr中的值放入bucket对应的位置
            for (int i = length - 1; i >= 0; i--) {
                // 获取对应位数的值
                int key = getFigure(arr[i], k);
                // 将key放入bucket对应的值
                bucket[count[key] - 1] = arr[i];
                // 将count数组key对应的位置坐标减一
                count[key]--;
            }

            // 将bucket赋值给arr
            System.arraycopy(bucket, 0, arr, 0, length);
        }
    }

    /**
     * 此函数返回整数i的第k位是什么
     *
     * @param i
     * @param k
     * @return
     */
    private static int getFigure(int i, int k) {
        int[] a = {1, 10, 100};
        return (i / a[k - 1]) % 10;
    }
}