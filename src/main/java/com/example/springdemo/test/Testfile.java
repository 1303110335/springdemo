/**
 * fshows.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.example.springdemo.test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

/**
 * @author xuleyan
 * @version Testfile.java, v 0.1 2019-03-31 8:03 PM xuleyan
 */
public class Testfile {

    public static void main(String[] args) throws IOException {
        // 使用字节流，不关闭
//        File file = new File("test.txt");
//        OutputStream outputStream = new FileOutputStream(file);
//        String str = "hello world!";
//        byte b[] = str.getBytes();
//        outputStream.write(b);
//        outputStream.close();

        // 使用字符流不关闭执行
        // 字符流操作时使用了缓冲区，而在关闭缓冲区的时候会强制将缓冲区中的内容强制输出到，如果程序没有关闭，缓冲区中的内容是无法输出的
        File file = new File("test.txt");
        Writer out = null;
        out = new FileWriter(file);
        String str = "hello world123!";
        out.write(str);
        out.flush();
//        out.close();
    }
}