/**
 * fshows.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.example.springdemo.jvm.memory.stack;

/**
 * @Described : 栈层级不足探究(设置每个线程的堆栈大小为256KB)
 * @VM args:-Xss256k -XX:+HeapDumpOnOutOfMemoryError
 * @author xuleyan
 * @Result java.lang.StackOverflowError
 * @version StackOverFlow.java, v 0.1 2019-04-25 8:50 PM xuleyan
 */
public class StackOverFlow {

    private int i;

    public void plus() {
        i ++;
        plus();
    }

    public static void main(String[] args) {
        StackOverFlow stackOverFlow = new StackOverFlow();
        try {
            stackOverFlow.plus();
        } catch (Exception ex) {
            System.out.println("Exception:stack length:" + stackOverFlow.i);
            ex.printStackTrace();
        } catch (Error e) {
            System.out.println("Error:stack length:"+stackOverFlow.i);
            e.printStackTrace();
        }

    }
}