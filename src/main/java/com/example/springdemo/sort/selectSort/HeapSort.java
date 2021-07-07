/**
 * fshows.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.example.springdemo.sort.selectSort;

/**
 * 堆排序
 * 时间复杂度 O(nlog2n) 不稳定
 * @author xuleyan
 * @version HeapSort.java, v 0.1 2019-05-06 11:07 PM xuleyan
 */

/*
 * 利用《大话数据结构》第9涨堆排序进行修改
 * 注意java中数组从0开始，节点s对应左右孩子是2s,2s+1;
 * 在java中节点s表示为a[s-1]，左右孩子应为a[2s],a[2s-1]
 *没有用到递归
 * */
public class HeapSort {
    public static void main(String[] args) {
        int[] nums = {16,7,3,20,17,8};
        headSort(nums);
        for (int num : nums) {
            System.out.print(num + " ");
        }
    }

    /**
     * 堆排序
     */
    public static void headSort(int[] list) {
        //构造初始堆,从第一个非叶子节点开始调整,左右孩子节点中较大的交换到父节点中
        for (int i = (list.length) / 2 - 1; i >= 0; i--) {
            headAdjust(list, list.length, i);
        }
        //排序，将最大的节点放在堆尾，然后从根节点重新调整
        for (int i = list.length - 1; i >= 1; i--) {
            int temp = list[0];
            list[0] = list[i];
            list[i] = temp;
            headAdjust(list, i, 0);
        }
    }

    /**
     * 详情参考 https://www.jianshu.com/p/11655047ab58
     * 堆排序
     * （左自节点的位置为父节点的位置 *2 +1 ） index = i * 2 + 1
     * （右自节点的位置为父节点的位置 *2 +2 ） index = i * 2 + 2
     * @param list 列表
     * @param len 列表的长度
     * @param i 第一个非叶子节点的位置或根结点
     */
    private static void headAdjust(int[] list, int len, int i) {
        // 要对比调整的节点父节点的位置
        int k = i;
        // 父节点所对应的值
        int temp = list[i];
        // 左自节点所对应的位置
        int index = 2 * k + 1;
        // 如果左自节点存在
        while (index < len) {
            // 如果右子节点存在
            if (index + 1 < len) {
                // 判断左子节点是否小于右子节点
                if (list[index] < list[index + 1]) {
                    // 若小于则将index改为右子节点的位置
                    index = index + 1;
                }
            }
            // 如果左或右子节点值大于父节点的值
            if (list[index] > temp) {
                // 将左或右子节点的值赋给父节点
                list[k] = list[index];
                // 将左或右子节点的位置赋给k
                k = index;
                // 将index赋值左或右子节点的左子节点的位置,再次循环比对
                index = 2 * k + 1;
            } else {
                break;
            }
        }
        // 最终将父节点对应的值赋给左或右子节点(子孙节点)
        list[k] = temp;
    }
}