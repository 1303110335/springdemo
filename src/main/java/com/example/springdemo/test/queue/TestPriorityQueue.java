/**
 * fshows.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.example.springdemo.test.queue;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

/**
 * @author xuleyan
 * @version TestPriorityQueue.java, v 0.1 2019-12-05 4:34 PM xuleyan
 */
public class TestPriorityQueue {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Comparator<Node> cmp = (a, b) -> {
            // 这里是小跟堆
            if (a.getKey() == b.getKey()) {
                return a.getSec() - b.getSec();
            }
            return a.getKey() - b.getKey();
        };

        Queue<Node> pq = new PriorityQueue<>(cmp);
        int n = in.nextInt();
        Node[] a = new Node[n];
        for (int i = 0; i < n; i++) {
            a[i] = new Node();
            a[i].setKey(in.nextInt());
            a[i].setSec(in.nextInt());
            pq.add(a[i]);
        }

        while (!pq.isEmpty()) {
            System.out.println(pq.element().getKey() + " " + pq.element().getSec());
            pq.poll();
        }
    }
}