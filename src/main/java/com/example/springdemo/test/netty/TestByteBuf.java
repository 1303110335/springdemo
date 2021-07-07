/**
 * fshows.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.example.springdemo.test.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;

/**
 * @author xuleyan
 * @version TestByteBuf.java, v 0.1 2019-04-02 4:43 PM xuleyan
 */
public class TestByteBuf {

    public static void main(String[] args) {
        int length = 1000;
        ByteBuf buffer = PooledByteBufAllocator.DEFAULT.directBuffer(length);

        // 在此使用对外缓冲池是为了将数据更快地写入内核中
        // 如果使用堆缓存会多一次堆内存向堆内核进行内存拷贝，这样会降低性能
        byte[] context = new byte[length];
        buffer.writeBytes(context);

        //释放自己申请的内存缓冲池
        buffer.release();
    }
}