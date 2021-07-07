/**
 * fshows.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.example.springdemo.test.rbTree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.StringJoiner;

/**
 * @author xuleyan
 * @create 2019-04-27
 *
 *  红黑树实现:
 *  性质:
 *  1.节点要么红，要么黑;
 *  2.根是黑色;
 *  3.所有叶子都是黑色;(叶子为null节点)
 *  4.每个红色节点的两个子节点都是黑色(从每个叶子到根的所有路径上不能有两个连续的红色节点)
 *  5.从任一节点到其每个叶子的所有简单路径都包含相同数目的黑色节点
 *
 **/
public class RedBlackTree<T extends Comparable<T>> {
    /**
     * 树的根结点
     */
    private Node<T> root;
    /**
     * 树元素个数
     */
    private int size;
    /**
     * 标志叶子节点表示空节点，颜色为黑色
     */
    private Node<T> nil = new Node<>(null, null, null, null, Color.BLACK);

    /**
     * 节点类
     * @param <E>
     */
    public static class Node<E> {
        E value;
        Node<E> parent;
        Node<E> left;
        Node<E> right;
        Color color;
        public Node(E value, Node<E> parent, Node<E> left, Node<E> right, Color color) {
            this.value = value;
            this.parent = parent;
            this.left = left;
            this.right = right;
            this.color = color;
        }
    }

    /**
     * 获取叔叔的节点
     * @param n
     * @return
     */
    private Node<T> uncle(Node<T> n) {
        Node<T> gp = grandParent(n);
        if (gp == null) {
            return null;
        }
        if (n.parent == gp.left) {
            return gp.right;
        } else {
            return gp.left;
        }
    }

    /**
     * 获取祖父节点
     * @param n 当前节点
     * @return 其祖父节点
     */
    private Node<T> grandParent(Node<T> n) {
        if (n.parent == null) {
            return null;
        }
        return n.parent.parent;
    }

    /**
     * 返回最小节点
     * @param n
     * @return 获取某节点为跟的树的最小节点
     */
    private T min(Node<T> n) {
        Node<T> min = minN(n);
        return min == nil ? null : min.value;
    }

    /**
     * 返回最小节点
     * @param n
     * @return
     */
    private Node<T> minN(Node<T> n) {
        Node<T> min = n;
        while (min.left != nil) {
            min = min.left;
        }
        return min == nil ? null : min;
    }

    /**
     * 获取某节点为根的树的最大元素
     * @return 最大元素, 没有返回null
     */
    public T max(Node<T> n) {
        Node<T> max = maxN(n);
        return max == nil ? null : max.value;
    }

    /**
     * 获取某节点为根的树的最大节点
     * @return 最大节点, 没有返回null
     */
    public Node<T> maxN(Node<T> n) {
        Node<T> max = n;
        while (max.right != nil) {
            max = max.right;
        }
        return max == nil ? null : max;
    }

    /**
     * 左旋以n节点为根的子树：
     * 1.将rightChild的左子树作为n的右子树
     * 2.将rightChild作为根
     * 3.将n节点作为rightChild的左孩子
     *    n                              rc
     *   /  \      --(左旋)-.           / \                #
     *  lc   rc                        n  rcc
     *     /   \                      /\
     *    lcc  rcc                  lc  lcc
     */
    private void leftRotate(Node<T> n) {
        Node<T> rightChild = n.right;
        // 1.将rightChild的左子树作为n的右子树
        // 将rightChild的左子树接到n的右边
        n.right = rightChild.left;
        if (rightChild.left != nil) {
            rightChild.left.parent = n;
        }

        // 2.将rightChild作为根
        rightChild.parent = n.parent;
        if (n.parent == null) {
            // 若n为根树
            root = rightChild;
        } else if (n.parent.left == n) {
            // 若n为父亲的左孩子
            n.parent.left = rightChild;
        } else {
            // 若n为父亲的右孩子
            n.parent.right = rightChild;
        }

        //3.将n节点作为rightChild的左孩子
        rightChild.left = n;
        n.parent = rightChild;
    }

    /**
     * 右旋以n为根节点的子树 np(n.parent) lc(left.child) rc(right.child)
     * 1。将leftChild的右子树作为n的左子树
     * 2。将leftChild作为根
     * 3。将n节点作为leftChild的右孩子
     *            np                               np
     *           /                                /
     *          n                                lc
     *         /  \      --(右旋)->             /  \                     #
     *        lc   rc                        lcc   n
     *       / \                                  / \                   #
     *     lcc  rcc                             rcc  rc
     *
     */
    private void rightRotate(Node<T> n) {
        // 1。将leftChild的右子树作为n的左子树
        Node<T> leftChild = n.left;
        n.left = leftChild.right;
        if (leftChild.right != nil) {
            leftChild.right.parent = n;
        }

        // 2。将leftChild作为根
        leftChild.parent = n.parent;
        if (n.parent == nil) {
            // 左子树为根节点
            root = leftChild;
        } else if (n.parent.left == n) {
            // 若n为左子树
            n.parent.left = leftChild;
        } else {
            n.parent.right = leftChild;
        }

        // 3。将n节点作为leftChild的右孩子
        leftChild.right = n;
        n.parent = leftChild;

    }

    /**
     * 插入元素
     */
    public boolean insert(T t){
        //插入新节点时，以红色着色
        Node<T> n = new Node<T>(t, null, nil, nil, Color.RED);
        Node<T> pointer = root;
        boolean inserted = false;
        //遍历插入
        while(!inserted){
            //空树
            if (root == null){
                root = n;
                inserted = true;
            } else if (n.value.compareTo(pointer.value) > 0){
                //向右子树找
                if (pointer.right == nil){
                    //插入右边
                    n.parent = pointer;
                    pointer.right = n;
                    inserted = true;
                } else{
                    pointer = pointer.right;
                }
            } else if (n.value.compareTo(pointer.value) < 0){
                //向左子树找
                if (pointer.left == nil){
                    //插入左边
                    n.parent = pointer;
                    pointer.left = n;
                    inserted = true;
                } else{
                    pointer = pointer.left;
                }
            } else { //相等了
                return false;
            }
        }
        size++;
        //调整树
        insertFixup(n);
        return inserted;
    }

    /**
     * 调整树以满足红黑树的性质
     * 1.节点为黑色或红色
     * 2.根结点为黑色
     * 3.叶子节点为黑色（叶子节点为nil或null）
     * 4.红色节点的子节点必须为黑色
     * 5.一个节点到其叶子节点的所有通路，黑色节点必须相同（确保没有一条路径会比其他路径长出俩倍）
     * @param n
     */
    private void insertFixup(Node<T> n) {
        //若是树根
        if (n.parent == null){
            n.color = Color.BLACK;
            return;
        }

        //父节点为黑色，无须调整
        if (n.parent.color == Color.BLACK){
            return;
        }

        Node<T> u = uncle(n);
        Node<T> g = grandParent(n);
        // 1.父节点及叔节点都为红色
        if (u != null && u.color == Color.RED){
            //将parent和uncle颜色置BLACK
            n.parent.color = Color.BLACK;
            u.color = Color.BLACK;
            //将grand parent置RED
            g.color = Color.RED;
            //递归调整 grand parent, 这时可想像grand parent为新添加的红色节点
            insertFixup(g);
        } else { //父节点P是红色而叔节点是黑色或缺少
            if (n == n.parent.right && n.parent == g.left){ //n为父节点右孩子,且父节点为祖父节点的左孩子
                //以父左旋
                leftRotate(n.parent);
                n = n.left;
            } else if(n == n.parent.left && n.parent == g.right){ //n为父节点左孩子,且父节点为祖父节点右孩子
                //以父右旋
                rightRotate(n.parent);
                n = n.right;
            }
            n.parent.color = Color.BLACK; //parent颜色置为黑色
            g.color = Color.RED;
            if (n == n.parent.left && n.parent == g.left){ //n节点为父节点的左孩子，且父节点为祖父节点的左孩子
                //以祖父右旋
                rightRotate(g);
            } else{ //n节点为父节点的右孩子，且父节点为祖父节点的右孩子
                //以祖父左旋
                leftRotate(g);
            }
        }
    }
    /**
     * 由于删除节点而做调整
     * @param n 删除节点的后继节点的孩子
     */
    private void removeFixup(Node<T> n) {
        while (n != root && n.color == Color.BLACK) {
            if (n == n.parent.left) { // n为其父节点的左孩子
                Node<T> rightBrother = rightBrother(n);
                if (rightBrother.color == Color.RED) { // 兄弟颜色为红
                    rightBrother.color = Color.BLACK;
                    n.parent.color = Color.RED;
                    leftRotate(n.parent); // 以父左旋
                    rightBrother = n.parent.right;
                }
                // 右兄弟的两个孩子都为黑色
                if (rightBrother.left.color == Color.BLACK
                        && rightBrother.right.color == Color.BLACK) {
                    rightBrother.color = Color.RED;
                    n = n.parent;
                } else if (rightBrother.right.color == Color.BLACK) { // 右兄弟右孩子为黑色
                    rightBrother.left.color = Color.BLACK;
                    rightBrother.color = Color.RED;
                    rightRotate(rightBrother);
                    rightBrother = n.parent.right;
                } else { // 右兄弟右孩子为红色或其他情况，比如为空叶子节点NIL
                    rightBrother.color = n.parent.color;
                    n.parent.color = Color.BLACK;
                    rightBrother.right.color = Color.BLACK;
                    leftRotate(n.parent);
                    n = root;
                }
            } else { // n为其父节点的右孩子
                Node<T> leftBrother = leftBrother(n);
                if (leftBrother.color == Color.RED) { // 左兄弟为红色
                    leftBrother.color = Color.BLACK;
                    n.parent.color = Color.RED;
                    rightRotate(n.parent);
                    leftBrother = n.parent.left;
                }
                if (leftBrother.left.color == Color.BLACK
                        && leftBrother.right.color == Color.BLACK) { // 左兄弟左孩子和右孩子都为黑色
                    leftBrother.color = Color.RED;
                    n = n.parent;
                } else if (leftBrother.left.color == Color.BLACK) { // 仅左兄弟左孩子为黑色
                    leftBrother.color = Color.RED;
                    leftBrother.right.color = Color.BLACK;
                    leftRotate(leftBrother);
                    leftBrother = n.parent.left;
                } else { // 左兄弟左孩子为红色
                    leftBrother.color = n.parent.color;
                    n.parent.color = Color.BLACK;
                    leftBrother.left.color = Color.BLACK;
                    rightRotate(n.parent);
                    n = root;
                }
            }
        }
        n.color = Color.BLACK;
    }

    /**
     * 获取节点的右兄弟
     * @param n 当前节点
     * @return 节点的右兄弟
     */
    private Node<T> rightBrother(Node<T> n) {
        return n == null ? null : (n.parent == null ? null : n.parent.right);
    }

    /**
     * 获取节点的左兄弟
     * @param n 当前节点
     * @return 节点的右兄弟
     */
    private Node<T> leftBrother(Node<T> n) {
        return n == null ? null : (n.parent == null ? null : n.parent.left);
    }

    @Override
    public String toString() {
        StringJoiner joiner = new StringJoiner(", ", RedBlackTree.class.getSimpleName() + "[", "]")
                .add("root:color=" + root.color)
                .add("root:value=" + root.value)
                .add("\n\r");
        joinerLoop(root, joiner);
        return joiner.toString();
    }

    /**
     * 删除元素
     * 类似二叉查找树的删除
     * @param t 带删除节点
     * @return 删除成功返回true,反之返回false
     */
    public boolean remove(T t) {
        boolean removed = false;
        // 获取要删除的节点
        Node<T> n = getN(t);
        // 用于替换的节点
        Node<T> replace = null;
        // 后继节点next的孩子节点
        Node<T> child = null;
        if (n != null) {
            if (n.left == nil || n.right == nil) {
                // 若有最多一个非空孩子
                replace = n;
            } else {
                // 若有两个非空孩子，则找其后继节点
                replace = locateNextN(n);
            }
            // 获取替换节点replace的孩子，有可能为nil
            child = replace.left != nil ? replace.left : replace.right;
            // 删除节点replace, 连接replace父节点-->child节点
            child.parent = replace.parent;
            if (replace.parent == null) {
                // 根节点
                root = child;
            } else if (replace == replace.parent.left) {
                // replace为其父节点左孩子
                replace.parent.left = child;
            } else {
                // replace为其父节点右孩子
                replace.parent.right = child;
            }

            // 替换n节点的值为replace节点
            if (replace != n) {
                n.value = replace.value;
            }

            // 若后继节点为黑色, 则需做调整, 因为删除红色replace节点对红黑树性质无影响
            if (replace.color == Color.BLACK) {
                removeFixup(child);
            }
            removed = true;
        }
        return removed;
    }

    private Node<T> locateNextN(Node<T> n) {
        return nil;
    }

    /**
     * 根据value查找节点
     * @param t
     * @return
     */
    private Node<T> getN(T t) {
        // 二叉树查找
        return findByCompareNode(root, t);
    }

    /**
     * 对节点node的值和t进行比较，获取值相等的节点
     * 1.比较节点与t
     *      1.1.如果t==node.value 返回node
     *      1.2.如果t>node.value 递归调用t与node.right比较
     *      1.3.如果t<node.value 递归调用t与node.left比较
     * @param node
     * @param t
     * @return
     */
    private Node<T> findByCompareNode(Node<T> node, T t) {
        if (t.compareTo(node.value) == 0) {
            return node;
        }
        if (t.compareTo(node.value) > 0) {
            findByCompareNode(node.right, t);
        }
        if (t.compareTo(node.value) < 0) {
            findByCompareNode(node.left, t);
        }
        return nil;
    }

    /**
     * 循环打印tree,level有问题
     * @param node
     * @param joiner
     */
    private void joinerLoop(Node<T> node, StringJoiner joiner) {

        if (node == nil) {
            return;
        }

        if (node.left != nil) {
            joiner.add("node.left:color=" + node.left.color)
                    .add("node.left:value=" + node.left.value)
                    .add("\n\r");
        }
        if (node.right != nil) {
            joiner.add("node.right:color=" + node.right.color)
                    .add("node.right:value=" + node.right.value)
                    .add("\n\r");
        }
        // 不为空+1
        joinerLoop(node.left, joiner);
        //不为空+1
        joinerLoop(node.right, joiner);
    }

    /**
     * 用队列的进（offer()）和出(poll())来实现二叉树的遍历
     * @param pTreeRoot
     * @return
     */
    public static ArrayList<Map<Integer, Color>> printFromTopToBottom(Node pTreeRoot){ //非递归版本
        ArrayList<Map<Integer, Color>> list = new ArrayList<Map<Integer, Color>>();
        if(pTreeRoot == null){
            return null;
        }
        Queue<Node> queue = new LinkedList<Node>();
        queue.offer(pTreeRoot);
        while(!queue.isEmpty()){
            Map<Integer, Color> hashMap = new HashMap<>();
            Node treeNode = queue.poll();
            if(treeNode.left!=null){
                queue.offer(treeNode.left);
            }
            if(treeNode.right!=null){
                queue.offer(treeNode.right);
            }
            hashMap.put((Integer) treeNode.value, treeNode.color);
            list.add(hashMap);
        }
        return list;
    }

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        RedBlackTree tree = new RedBlackTree();
        tree.insert(5);
        tree.insert(10);
        tree.insert(20);
        tree.insert(1);
        tree.insert(100);
        tree.insert(7);
        tree.insert(69);
        //递归遍历
//        System.out.println(tree.toString());

        // 链表遍历
//        ArrayList<Map<Integer, Color>> lists = printFromTopToBottom(tree.root);
//        System.out.println(lists);

        // 递归遍历(前序遍历：根左右，中序遍历：左中右，后序遍历：左右中)
//        LoopPrint loopPrint = new LoopPrint();
//        loopPrint.printFromTopToBottom(tree.root);
    }



}