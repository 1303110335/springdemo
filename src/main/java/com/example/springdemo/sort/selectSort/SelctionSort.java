/**
 * fshows.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.example.springdemo.sort.selectSort;

import java.util.Arrays;

/**
 * 选择排序
 * 基本思想：
 * 在长度为N的无序数组中，第一次遍历n-1个数，找到最小的数值与第一个元素交换；
 * 第二次遍历n-2个数，找到最小的数值与第二个元素交换；
 * 。。。
 * 第n-1次遍历，找到最小的数值与第n-1个元素交换，排序完成。
 * <p>
 * 平均时间复杂度：O(n2)  不稳定
 *
 * @author xuleyan
 * @version SelctionSort.java, v 0.1 2019-10-22 3:16 PM xuleyan
 */
public class SelctionSort {

    public static void main(String[] args) {

        int[] arr = {111, 3, 5, 52, 74, 312, 75, 3, 764, 3, 2111, 7, 31};
        System.out.println("排序后的数组：" + selectSort(arr));
    }

    private static String selectSort(int[] arr) {

        for (int i = 0; i < arr.length; i++) {
            int minIndex = i;
            // 从n-1个数中查询最小的值
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }

            // 如果minIndex != i,交换两个数
            if (minIndex != i) {
                int temp = arr[i];
                arr[i] = arr[minIndex];
                arr[minIndex] = temp;
            }
        }
        return Arrays.toString(arr);
    }
}