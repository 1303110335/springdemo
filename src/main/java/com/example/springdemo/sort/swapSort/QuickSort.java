/**
 * fshows.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.example.springdemo.sort.swapSort;

import java.util.Arrays;

/**
 * 快速排序
 * 平均时间复杂度：O(N*logN)
 * 最坏情况O(n^2)
 * 不稳定
 * 先从数列中取出一个数作为key值；
 * 将比这个数小的数全部放在它的左边，大于或等于它的数全部放在它的右边；
 * 对左右两个小数列重复第二步，直至各区间只有1个数。
 *
 * @author xuleyan
 * @version QuickSort.java, v 0.1 2019-10-16 10:13 PM xuleyan
 */
public class QuickSort {

    public static void main(String[] args) {
        int[] list = {16, 7, 3, 20, 17, 8};
        quickSort2(list, 0, list.length - 1);
        System.out.println(Arrays.toString(list));
    }

    public static void quickSort(int a[], int l, int r) {
        if (l >= r) {
            return;
        }

        //选择第一个数为key
        int i = l;
        int j = r;
        int key = a[l];

        while (i < j) {

            //从右向左找第一个小于key的值
            while (i < j && a[j] >= key) {
                j--;
            }
            if (i < j) {
                a[i] = a[j];
                i++;
            }

            //从左向右找第一个大于key的值
            while (i < j && a[i] < key) {
                i++;
            }

            if (i < j) {
                a[j] = a[i];
                j--;
            }
        }
        //i == j
        a[i] = key;
        //递归调用
        quickSort(a, l, i - 1);
        quickSort(a, i + 1, r);
    }

    public static void quickSort2(int a[], int l, int r) {
        if (l >= r) {
            return;
        }
        // 1.i为l, j为r, key 为a[l], 获取左边的第一个数作为基准
        int i = l, j = r, key = a[l];
        // 2.当i < j的时候，不断重复3和4的操作
        while (i < j) {
            // 3.当i < j并且 a[j] >= key时,先从右向左查找小于key值的数，并且不断将j--，找到一个后，如果 i < j,将a[j]赋值给a[i], i++
            while (i < j && a[j] >= key) {
                j--;
            }

            if (i < j) {
                a[i] = a[j];
                i++;
            }

            // 4.当i < j并且 a[i] <= key时,再从左向右查找大于key的值，并且不断将i ++ ,找到一个后，如果 i < j,将a[i]赋值给a[j], j++
            while (i < j && a[i] <= key) {
                i++;
            }

            if (i < j) {
                a[j] = a[i];
                j--;
            }

            // 5.将key赋值给a[i]
            a[i] = key;

            // 6.然后递归调用quickSort2(a, l, i - 1)
            quickSort2(a, l, i - 1);
            // 7.然后递归调用quickSort2(a, i + 1, r)
            quickSort2(a, i + 1, r);
        }
    }
}