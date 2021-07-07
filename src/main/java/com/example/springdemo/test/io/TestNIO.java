/**
 * fshows.com
 * Copyright (C) 2013-2020 All Rights Reserved.
 */
package com.example.springdemo.test.io;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author xuleyan
 * @version TestNIO.java, v 0.1 2020-04-03 5:00 PM xuleyan
 */
public class TestNIO {

    public static void main(String[] args) {

        //nioRead1();
        nioRead2();


    }

    private static void nioRead2() {
        String path = "/Volumes/code/java/test/springdemo/src/main/resources/test.txt";
        RandomAccessFile accessFile = null;

        try {
            accessFile = new RandomAccessFile(path, "rw");
            // 获取channel
            FileChannel channel = accessFile.getChannel();
            // 创建buffer并且分配空间大小为1024
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            // 从管道中读取数据写入到Buffer中
            int bytesRead = channel.read(byteBuffer);

            while (!bufferFull(bytesRead)) {
                bytesRead = channel.read(byteBuffer);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean bufferFull(int bytesRead) {
        return false;
    }


    private static void nioRead1() {
        String path = "/Volumes/code/java/test/springdemo/src/main/resources/test.txt";
        RandomAccessFile accessFile = null;

        try {
            accessFile = new RandomAccessFile(path, "rw");
            // 获取channel
            FileChannel channel = accessFile.getChannel();
            // 创建buffer并且分配空间大小为1024
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            // 从管道中读取数据写入到Buffer中
            int bytesRead = channel.read(byteBuffer);

            while (bytesRead != -1) {
                // 将position设为0，limit 指向下一个不可以操作的元素为止
                byteBuffer.flip();
                // 如果缓冲区还有内容
                while (byteBuffer.hasRemaining()) {
                    System.out.println((char) byteBuffer.get());
                }
                // 将buffer 中魏都区的数据拷贝到Buffer的起始位置，供下次读取
                byteBuffer.compact();

                bytesRead = channel.read(byteBuffer);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (accessFile != null) {
                    accessFile.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}