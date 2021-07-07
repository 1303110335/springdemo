/**
 * fshows.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.example.springdemo.test.rbTree;

/**
 * @author xuleyan
 * @version BinaryTreeNode.java, v 0.1 2019-05-01 9:54 PM xuleyan
 */
public class BinaryTreeNode {

    public int data;

    public BinaryTreeNode left;

    public BinaryTreeNode right;

    public BinaryTreeNode(int data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "BinaryTreeNode [data=" + data + ", left=" + left + ", right=" + right
                + "]";
    }
}