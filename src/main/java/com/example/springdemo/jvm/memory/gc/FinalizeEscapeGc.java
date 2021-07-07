/**
 * fshows.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.example.springdemo.jvm.memory.gc;

/**
 * 此代码演示了亮点：
 * 1。对象可以在被GC时自我拯救
 * 2。这种自救机会只有一次，因为一个对象的finalize()方法最多只会被系统自动调用一次
 *
 * @author xuleyan
 * @version FinalizeEscapeGc.java, v 0.1 2019-07-01 4:47 PM xuleyan
 */
public class FinalizeEscapeGc {
    public static FinalizeEscapeGc SAVE_HOOK = null;

    public static void main(String[] args) throws InterruptedException {
        SAVE_HOOK = new FinalizeEscapeGc();
        // 对象第一次成功拯救自己
        SAVE_HOOK = null;
        System.gc();
        // 因为Finalizer方法优先级很低，暂停0.5s,以等待他
        Thread.sleep(500);
        if (SAVE_HOOK != null) {
            SAVE_HOOK.isAlive();
        } else {
            System.out.println("no, i am dead :(");
        }


        SAVE_HOOK = null;
        System.gc();
        // 因为Finalizer方法优先级很低，暂停0.5s,以等待他
        Thread.sleep(500);
        if (SAVE_HOOK != null) {
            SAVE_HOOK.isAlive();
        } else {
            System.out.println("no, i am dead :(");
        }
    }

    public void isAlive() {
        System.out.println("yes, i am still alive :)");
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("finalize method executed!");
        FinalizeEscapeGc.SAVE_HOOK = this;
    }
}