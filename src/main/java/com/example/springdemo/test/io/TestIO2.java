/**
 * fshows.com
 * Copyright (C) 2013-2020 All Rights Reserved.
 */
package com.example.springdemo.test.io;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * IO形式读取文件
 *
 * @author xuleyan
 * @version TestIO2.java, v 0.1 2020-04-03 4:38 PM xuleyan
 */
public class TestIO2 {

    public static void main(String[] args) {

//        readChar();
        readLine();
    }

    private static void readLine() {
        String path = "/Volumes/code/java/test/springdemo/src/main/resources/banner.txt";
        InputStream inputStream = null;
        try {
            inputStream = new BufferedInputStream(new FileInputStream(path));
            InputStreamReader reader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void readChar() {
        String path = "/Volumes/code/java/test/springdemo/src/main/resources/banner.txt";
        InputStream inputStream = null;
        try {
            inputStream = new BufferedInputStream(new FileInputStream(path));
            byte[] buf = new byte[1024];
            int bytesRead = inputStream.read(buf);
            while (bytesRead != -1) {
                for (int i = 0; i < bytesRead; i++) {
                    System.out.println((char) buf[i]);
                }
                bytesRead = inputStream.read(buf);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}