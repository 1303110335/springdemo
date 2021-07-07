/**
 * fshows.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.example.springdemo.algorithm;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
 * <p>
 * 你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。
 * <p>
 * 示例:
 * <p>
 * 给定 nums = [2, 7, 11, 15], target = 9
 * <p>
 * 因为 nums[0] + nums[1] = 2 + 7 = 9
 * 所以返回 [0, 1]
 *
 * @author xuleyan
 * @version TestSum.java, v 0.1 2019-06-29 5:20 PM xuleyan
 */
public class TestSum {

    public static void main(String[] args) {
        int[] result = twoSum3(new int[]{3, 2, 4}, 6);
        for (int number : result) {
            System.out.println(number);
        }
    }

    /**
     * 耗时：51ms
     * 时间复杂度 O(n2)
     * 空间复杂度 O(1)
     *
     * @param nums
     * @param target
     * @return
     */
    public static int[] twoSum(int[] nums, int target) {
        int length = nums.length - 1;
        for (int i = 0; i < length; i++) {
            for (int j = i + 1; j <= length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }

        return new int[]{};
    }

    /**
     * 两边hash遍历
     * 耗时：4ms
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     *
     * @param nums
     * @param target
     * @return
     */
    public static int[] twoSum2(int[] nums, int target) {
        Map<Integer, Integer> hashMap = new HashMap<>();
        int length = nums.length;
        for (int i = 0; i < length; i++) {
            hashMap.put(nums[i], i);
        }

        for (int i = 0; i < length; i++) {
            int key = target - nums[i];
            // 两个数的key不一样
            if (hashMap.containsKey(key) && hashMap.get(key) != i) {
                return new int[]{i, hashMap.get(key)};
            }
        }
        return new int[]{};
    }

    /**
     * 一遍hash遍历
     */
    public static int[] twoSum3(int[] nums, int target) {
        Map<Integer, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int key = target - nums[i];
            // 两个数的key不一样
            if (hashMap.containsKey(key) && hashMap.get(key) != i) {
                return new int[]{i, hashMap.get(key)};
            }
            hashMap.put(nums[i], i);
        }
        return new int[]{};
    }
}