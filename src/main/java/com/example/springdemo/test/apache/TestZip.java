/**
 * fshows.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.example.springdemo.test.apache;

import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @author xuleyan
 * @version TestZip.java, v 0.1 2019-03-21 9:36 PM xuleyan
 */
public class TestZip {

    public static void main(String[] args) throws IOException {

        // 创建压缩对象
        ZipArchiveEntry entry = new ZipArchiveEntry("CompressTest");
        // 要压缩的文件
        File f = new File("/Users/xuleyan/Desktop/test.txt");
        FileInputStream file = new FileInputStream(f);
        // 输出对象， 压缩的文件
        ZipArchiveOutputStream zipOutput = new ZipArchiveOutputStream(new File("/Users/xuleyan/Desktop/test.zip"));
        zipOutput.putArchiveEntry(entry);
        int i = 0, j;
        while ((j = file.read()) != -1) {
            zipOutput.write(j);
            i++;
            System.out.println(i);
        }
        zipOutput.closeArchiveEntry();;
        zipOutput.close();
        file.close();


    }
}