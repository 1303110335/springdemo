/**
 * fshows.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.example.springdemo.sort.swapSort;

import java.util.Arrays;

/**
 * 冒泡排序
 * 基本过程：
 * 比较相邻的两个数据，如果第二个数小，就交换位置。
 * 从后向前两两比较，一直到比较最前两个数据。最终最小数被交换到起始的位置，这样第一个最小数的位置就排好了。
 * 继续重复上述过程，依次将第2.3...n-1个最小数排好位置
 * <p>
 * 时间复杂度 O(n2)  稳定
 *
 * @author xuleyan
 * @version BubbleSort.java, v 0.1 2019-10-22 3:43 PM xuleyan
 */
public class BubbleSort {

    private static Integer count = 0;

    public static void main(String[] args) {
        int[] arr = {3, 111, 5, 52, 74, 312, 75, 3, 764, 3, 7, 31, 2111};
        System.out.println("排序后的数组：" + bubbleSortBetter(arr));
    }

    private static String bubbleSort(int[] arr) {
        //临时变量
        int temp;
        //表示趟数，一共arr.length-1次。
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = arr.length - 1; j > i; j--) {
                System.out.println("第[" + ++count + "]次");
                if (arr[j] < arr[j - 1]) {
                    temp = arr[j];
                    arr[j] = arr[j - 1];
                    arr[j - 1] = temp;
                }
            }
        }
        return Arrays.toString(arr);
    }

    /**
     * 优化版冒泡排序
     *
     * @param arr
     * @return
     */
    private static String bubbleSortBetter(int[] arr) {
        //临时变量
        int temp;

        //表示趟数，一共arr.length-1次。
        for (int i = 0; i < arr.length - 1; i++) {
            // flag判断是否交换过
            boolean flag = false;

            for (int j = arr.length - 1; j > i; j--) {
                System.out.println("第[" + ++count + "]次");
                if (arr[j] < arr[j - 1]) {
                    temp = arr[j];
                    arr[j] = arr[j - 1];
                    arr[j - 1] = temp;
                    flag = true;
                }
            }
            if (!flag) {
                break;
            }
        }

        return Arrays.toString(arr);
    }
}