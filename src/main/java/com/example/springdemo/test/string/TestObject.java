/**
 * fshows.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.example.springdemo.test.string;

/**
 * @author xuleyan
 * @version TestObject.java, v 0.1 2019-09-30 4:54 PM xuleyan
 */
public class TestObject {

    public static void main(String[] args) throws InterruptedException {
        // 1.在堆中创建了一个product对象，地址为xx1,在栈中创建了一个变量p,存放堆中product的地址
        Product p = new Product();
        p.setProName("before");
        p.setNum(0);
        // 2.将p在堆中的地址xx1传递给changeProduct方法
        changeProduct(p);
        System.out.println("main >> p=" + System.identityHashCode(p));
        // 5.打印堆中product对象的proName和Num
        System.out.println("p.proName=" + p.getProName());
        System.out.println("p.num=" + p.getNum());

        Thread.sleep(100000);
    }

    // 3.在栈中创建一个变量p'存放main方法传递过来的product对象的地址
    private static void changeProduct(Product p) {
        // 4.设置product对象的proName和Num
        p.setProName("after");
        p.setNum(1);
        System.out.println("p=" + System.identityHashCode(p));
    }
}

class Product {
    private int num;
    private String proName;

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

}