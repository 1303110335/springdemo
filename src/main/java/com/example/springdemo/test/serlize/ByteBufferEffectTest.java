/**
 * fshows.com
 * Copyright (C) 2013-2020 All Rights Reserved.
 */
package com.example.springdemo.test.serlize;

import com.example.springdemo.domain.User;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.ByteBuffer;

/**
 * 序列化的速度也是体现序列化性能的重要指标，如果序列化的速度慢，就会影响网络通信的效率，从而增加系统的响应时间。我们再来通过上面这个例子，来对比下Java序列化与NIO中的ByteBuffer编码的性能：
 * <p>
 * 结果：
 * ObjectOutputStream 序列化时间：35
 * ByteBuffer 序列化时间10
 * 通过以上案例，我们可以清楚地看到：Java序列化中的编码耗时要比ByteBuffer长很多。
 *
 * @author xuleyan
 * @version ByteBufferEffectTest.java, v 0.1 2020-05-28 7:45 AM xuleyan
 */
public class ByteBufferEffectTest {

    public static void main(String[] args) throws IOException {
        User user = new User();
        user.setName("test");
        user.setPlay("test");

        testByteArray(user);
        testByteBuffer(user);
    }

    private static void testByteBuffer(User user) {
        long startTime1 = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            ByteBuffer byteBuffer = ByteBuffer.allocate(2048);
            byte[] userName = user.getName().getBytes();
            byte[] play = user.getPlay().getBytes();
            byteBuffer.putInt(userName.length);
            byteBuffer.put(userName);
            byteBuffer.putInt(play.length);
            byteBuffer.put(play);

            byteBuffer.flip();
            byte[] bytes = new byte[byteBuffer.remaining()];
        }
        long endTime = System.currentTimeMillis();
        System.out.println("ByteBuffer 序列化时间" + (endTime - startTime1));
    }

    private static void testByteArray(User user) throws IOException {


        long startTime = System.currentTimeMillis();

        for (int i = 0; i < 1000; i++) {
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(os);
            out.writeObject(user);
            out.flush();
            out.close();
            byte[] testByte = os.toByteArray();
            os.close();
        }


        long endTime = System.currentTimeMillis();
        System.out.print("ObjectOutputStream 序列化时间：" + (endTime - startTime) + "\n");
    }
}