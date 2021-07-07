/**
 * fshows.com
 * Copyright (C) 2013-2020 All Rights Reserved.
 */
package com.example.springdemo.design.mode.single.attack;

import com.example.springdemo.design.mode.single.SingletonEnum;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * @author xuleyan
 * @version SerialAttack.java, v 0.1 2020-01-18 10:16 AM xuleyan
 */
public class EnumSerialAttack {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        serializationAttack();
    }

    private static void serializationAttack() throws IOException, ClassNotFoundException {

        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("serFile"));

        // 通过单利代码获取一个对象
        SingletonEnum doubleCheck = SingletonEnum.INSTANCE;
        // 将单例对象，通过序列化流，序列化到文件中
        objectOutputStream.writeObject(doubleCheck);
        // 通过序列化流，将文件序列化的对象信息读取到内存中
        ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(new File("serFile")));
        // 通过序列化流，去创建对象
        SingletonEnum doubleCheck2 = (SingletonEnum) inputStream.readObject();
        doubleCheck.tellEveryOne();
        doubleCheck2.tellEveryOne();
    }
}