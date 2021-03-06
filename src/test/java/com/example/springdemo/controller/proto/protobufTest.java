/**
 * fshows.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.example.springdemo.controller.proto;

import com.google.protobuf.InvalidProtocolBufferException;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Protocal Buffers(简称protobuf)是谷歌的一项技术，用于结构化的数据序列化、反序列化，常用于RPC 系统（Remote Procedure Call Protocol System）和持续数据存储系统。
 * 其类似于XML生成和解析，但protobuf的效率高于XML，不过protobuf生成的是字节码，可读性比XML差，类似的还有json、Java的Serializable等。
 * 很适合做数据存储或 RPC 数据交换格式。可用于通讯协议、数据存储等领域的语言无关、平台无关、可扩展的序列化结构数据格式。
 * @author xuleyan
 * @version protobufTest.java, v 0.1 2019-04-10 8:24 AM xuleyan
 */
public class protobufTest {
    @Test
    public void testN() throws InvalidProtocolBufferException {
        PersonModel.Person.Builder builder = PersonModel.Person.newBuilder();
        builder.setId(1);
        builder.setName("jihite");
        builder.setEmail("jihite@jihite.com");

        PersonModel.Person person = builder.build();
        System.out.println("before:" + person);

        System.out.println("===Person Byte:");
        for (byte b : person.toByteArray()) {
            System.out.print(b);
        }
        System.out.println("================");

        byte[] byteArray = person.toByteArray();
        PersonModel.Person p2 = PersonModel.Person.parseFrom(byteArray);
        System.out.println("after id:" + p2.getId());
        System.out.println("after name:" + p2.getName());
        System.out.println("after email:" + p2.getEmail());

    }
}