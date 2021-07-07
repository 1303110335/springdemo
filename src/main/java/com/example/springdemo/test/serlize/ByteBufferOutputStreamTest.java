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
 * 序列化后的二进制流大小能体现序列化的性能。序列化后的二进制数组越大，占用的存储空间就越多，存储硬件的成本就越高。如果我们是进行网络传输，则占用的带宽就更多，这时就会影响到系统的吞吐量。
 * Java序列化中使用了ObjectOutputStream来实现对象转二进制编码，那么这种序列化机制实现的二进制编码完成的二进制数组大小，相比于NIO中的ByteBuffer实现的二进制编码完成的数组大小，有没有区别呢？
 *
 * @author xuleyan
 * @version ByteBufferOutputStreamTest.java, v 0.1 2020-05-28 7:29 AM xuleyan
 */
public class ByteBufferOutputStreamTest {

    public static void main(String[] args) throws IOException {
        User user = new User();
        user.setName("test");
        user.setPlay("ball");

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(os);
        out.writeObject(user);

        byte[] testByte = os.toByteArray();
        System.out.println("ObjectOutputStream 字节编码长度" + testByte.length);

        ByteBuffer byteBuffer = ByteBuffer.allocate(2048);
        byte[] userName = user.getName().getBytes();
        byte[] play = user.getPlay().getBytes();
        byteBuffer.putInt(userName.length);
        byteBuffer.put(userName);
        byteBuffer.putInt(play.length);
        byteBuffer.put(play);

        byteBuffer.flip();
        byte[] bytes = new byte[byteBuffer.remaining()];
        System.out.println("ByteBuffer 字节编码长度" + bytes.length);
    }

    //ObjectOutputStream 字节编码长度178
    //ByteBuffer 字节编码长度16

    //这里我们可以清楚地看到：Java序列化实现的二进制编码完成的二进制数组大小，比ByteBuffer实现的二进制编码完成的二进制数组大小要大上几倍。因此，Java序列后的流会变大，最终会影响到系统的吞吐量。
}