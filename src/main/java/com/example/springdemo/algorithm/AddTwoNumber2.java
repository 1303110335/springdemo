/**
 * fshows.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.example.springdemo.algorithm;

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
public class AddTwoNumber2 {

    public static void main(String[] args) {
        ListNode l1 = new ListNode(5);
//        ListNode l1next = new ListNode(4);
//        l1.next = l1next;
//        l1next.next = new ListNode(3);

        ListNode l2 = new ListNode(5);
//        ListNode l2next = new ListNode(6);
//        l2.next = l2next;
//        l2next.next = new ListNode(4);

        AddTwoNumber2 addTwoNumber = new AddTwoNumber2();
        ListNode listNode = addTwoNumber.addTwoNumbers(l1, l2);

        StringBuilder msg = printListNode(listNode, new StringBuilder());
        String resultMsg = msg.toString();
        System.out.println(resultMsg.substring(0, resultMsg.length() - 3));

    }

    private static StringBuilder printListNode(ListNode node, StringBuilder msg) {
        if (node != null) {
            msg.append(node.val).append(" -> ");
            printListNode(node.next, msg);
        }
        return msg;
    }

    private ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        return addTwoNumbersLoop(l1, l2, new ListNode(0), 0);
    }

    private ListNode addTwoNumbersLoop(ListNode l1, ListNode l2, ListNode result, Integer highResult) {
        int number1 = l1.val;
        int number2 = l2.val;

        int numResult = number1 + number2 + highResult;
        // 保存个位数
        result.val = numResult % 10;
        // 保存十位数
        highResult = numResult / 10;
        if (l1.next == null && l2.next == null) {
            if (highResult > 0) {
                result.next = new ListNode(highResult);
            }
            return result;
        }

        l1.next = (l1.next == null) ? new ListNode(0) : l1.next;
        l2.next = (l2.next == null) ? new ListNode(0) : l2.next;

        result.next = new ListNode(0);
        addTwoNumbersLoop(l1.next, l2.next, result.next, highResult);
        return result;
    }


}