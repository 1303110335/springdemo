/**
 * fshows.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.example.springdemo.test.rbTree;

/**
 * @author xuleyan
 * @version LoopPrint.java, v 0.1 2019-05-01 10:31 PM xuleyan
 */
public class LoopPrint {

    /**
     * 层序遍历二叉树递归实现
     * @param pTreeRoot
     * @return
     */
    private int depth(RedBlackTree.Node pTreeRoot){
        if(pTreeRoot==null){
            return 0;
        }
        int l = depth(pTreeRoot.left);
        int r = depth(pTreeRoot.right);
        if(l > r){
            return l + 1;
        }else{
            return r + 1;
        }
    }

    private void levelOrder(RedBlackTree.Node pTreeNode, int level) {
        if(pTreeNode == null || level < 1){
            return ;
        }
        if(level == 1){
            System.out.print(pTreeNode.value+ " " + pTreeNode.color + "\t");
            return ;
        }
        //左子树
        levelOrder(pTreeNode.left, level-1);
        //右子树
        levelOrder(pTreeNode.right, level-1);
    }

    public void printFromTopToBottom(RedBlackTree.Node pTreeRoot){
        if(pTreeRoot==null){
            return ;
        }
        int depth = depth(pTreeRoot);
        for (int i = 1; i <= depth; ++i) {
            levelOrder(pTreeRoot, i);
        }
    }
}