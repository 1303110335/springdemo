/**
 * fshows.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.example.springdemo.test.queue;

/**
 * 将该二元查找树转换成一个排序的双向链表
 *
 * @author xuleyan
 * @version TreeChangeToLink.java, v 0.1 2019-12-09 9:19 AM xuleyan
 */
public class TreeChangeToLink {

    private Node head, tail;

    public static void main(String[] args) {
        Node node1 = new Node(4, null, null);
        Node node2 = new Node(8, null, null);
        Node node3 = new Node(12, null, null);
        Node node4 = new Node(16, null, null);
        Node node5 = new Node(6, node1, node2);
        Node node6 = new Node(14, node3, node4);
        Node node7 = new Node(10, node5, node6);
        TreeChangeToLink t = new TreeChangeToLink();
        t.change(node7);
        System.out.println("双向链表从头结点向后遍历:");
        t.printHead();
        System.out.println("双向链表从尾结点向前遍历:");
        t.printTail();
    }

    public void change(Node node) {
        if (node == null) {
            return;
        }

        if (node.left != null) {
            change(node.left);
        }
        changeNode(node);
        if (node.right != null) {
            change(node.right);
        }
    }

    /**
     * 节点转换
     *
     * @param node
     */
    private void changeNode(Node node) {
        if (head == null) {
            head = node;
            tail = node;
        } else {
            node.left = tail;
            tail.right = node;
            tail = node;
        }

    }

    //头结点向后打印
    private void printHead() {
        while (head != null) {
            System.out.print(head.value + " ");
            head = head.right;
        }
    }

    //尾节点向前打印
    private void printTail() {
        while (tail != null) {
            System.out.print(tail.value + " ");
            tail = tail.left;
        }
    }

    public static class Node {
        private int value;
        private Node left;
        private Node right;

        public Node(int value, Node left, Node right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }


}