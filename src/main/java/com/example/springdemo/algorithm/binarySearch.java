/**
 * fshows.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.example.springdemo.algorithm;

/**
 * 二分查找
 *
 * @author xuleyan
 * @version binarySearch.java, v 0.1 2019-10-23 6:46 PM xuleyan
 */
public class binarySearch {

    public static void main(String[] args) {
        int[] array = {2, 5, 7, 10};
//        System.out.println(binSearch(array, 10, 0, 3));
        System.out.println(binSearchNoRecursive(array, 10));
    }

    private static int binSearchNoRecursive(int[] array, int val) {
        int low = 0;
        int high = array.length - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (array[mid] == val) {
                return mid;
            } else if (array[mid] > val) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return -1;

    }

    /**
     * 递归模式
     *
     * @param array
     * @param val
     * @return
     */
    private static int binSearch(int[] array, int val, int low, int high) {
        int mid = (low + high) / 2;

        if (val < array[mid]) {
            return binSearch(array, val, low, mid);
        } else if (val > array[mid]) {
            return binSearch(array, val, mid + 1, high);
        } else {
            return mid;
        }
    }


}