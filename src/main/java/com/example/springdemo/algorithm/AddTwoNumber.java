/**
 * fshows.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.example.springdemo.algorithm;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;

/**
 * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 * <p>
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 * <p>
 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 * <p>
 * 示例：
 * <p>
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 0 -> 8
 * 原因：342 + 465 = 807
 * <p>
 * [2,4,3]
 * [5,6,4]
 * [7,0,8]
 * <p>
 * [9]
 * [1,9,9,9,9,9,9,9,9,9]
 *
 * @author xuleyan
 * @version AddTwoNumber.java, v 0.1 2019-07-10 8:20 PM xuleyan
 */
public class AddTwoNumber {

    public static void main(String[] args) {
//        String[] strings = "123-".split("-");
        String[] strings = StringUtils.split("123-", "-");
        System.out.println(Arrays.asList(strings));

//
//        ListNode l1 = new ListNode(2);
//        ListNode l1next = new ListNode(4);
//        l1.next = l1next;
//        l1next.next = new ListNode(3);
//
//        ListNode l2 = new ListNode(5);
//        ListNode l2next = new ListNode(6);
//        l2.next = l2next;
//        l2next.next = new ListNode(4);
//
//        AddTwoNumber addTwoNumber = new AddTwoNumber();
//        ListNode listNode = addTwoNumber.addTwoNumbers(l1, l2);
//
//        StringBuilder msg = printListNode(listNode, new StringBuilder());
//        String resultMsg = msg.toString();
//        System.out.println(resultMsg.substring(0, resultMsg.length() - 3));

    }

    private static StringBuilder printListNode(ListNode node, StringBuilder msg) {
        if (node != null) {
            msg.append(node.val).append(" -> ");
            printListNode(node.next, msg);
        }
        return msg;
    }

    private ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        Integer result1 = changeListNodeToInteger(l1, 0, 0);
        Integer result2 = changeListNodeToInteger(l2, 0, 0);
        Integer result3 = result1 + result2;
        return changeIntegerToListNode(result3, new ListNode(0));
    }

    /**
     * 将listNode转化为Integer
     *
     * @param listNode
     * @param index
     * @param result
     * @return
     */
    private Integer changeListNodeToInteger(ListNode listNode, Integer index, Integer result) {
        if (listNode == null) {
            return result;
        }
        result += listNode.val * (int) Math.pow(10, index);
        return changeListNodeToInteger(listNode.next, index + 1, result);
    }

    /**
     * 将integer转化为ListNode
     *
     * @param number
     * @return
     */
    private ListNode changeIntegerToListNode(Integer number, ListNode result) {
        if (number == 0) {
            return result;
        }
        // 每位上的数
        result.val = number % 10;
        number = number / 10;
        if (number > 0) {
            ListNode next = new ListNode(0);
            result.next = next;
            changeIntegerToListNode(number, next);
        }

        return result;
    }
}


class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}