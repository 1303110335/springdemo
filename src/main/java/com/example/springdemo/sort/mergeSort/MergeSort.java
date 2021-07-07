/**
 * fshows.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.example.springdemo.sort.mergeSort;

/**
 * 归并排序 (分别分组排序)
 * 时间复杂度 O(nlog2n) 稳定
 *
 * @author xuleyan
 * @version MergeSort.java, v 0.1 2019-10-16 9:00 PM xuleyan
 */
public class MergeSort {

    public static void main(String[] args) {
        int[] a = {49, 38, 65, 97, 76, 13, 27, 50};
        mergeSort(a, 0, a.length - 1);
        System.out.println("排好序的数组：");
        for (int e : a) {
            System.out.print(e + " ");
        }
    }


    private static void mergeSort(int[] a, int start, int end) {
        // 将数组分割至小于1个返回
        if (start < end) {
            int mid = (start + end) / 2;
            mergeSort(a, start, mid);
            mergeSort(a, mid + 1, end);
            merge(a, start, mid, end);
        }
    }

    /**
     * 将两个数组合并
     *
     * @param a
     * @param start
     * @param mid
     * @param end
     */
    private static void merge(int[] a, int start, int mid, int end) {
        int[] tmp = new int[a.length];

        int pl = start, pr = mid + 1, k = start;
        // 将左右两个数组进行比较, 将小的放在数组tmp中，并将对应的指针加1
        while (pl <= mid && pr <= end) {
            if (a[pl] <= a[pr]) {
                tmp[k++] = a[pl++];
            } else {
                tmp[k++] = a[pr++];
            }
        }

        // 将左边数组剩余的加到tmp中
        while (pl <= mid) {
            tmp[k++] = a[pl++];
        }

        // 将右边数组剩余的加到tmp中
        while (pr <= end) {
            tmp[k++] = a[pr++];
        }

        // 将tmp数组复制回原来的数组
        if (end + 1 - start >= 0) {
            System.arraycopy(tmp, start, a, start, end + 1 - start);
        }
    }
}