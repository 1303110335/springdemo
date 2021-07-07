/**
 * fshows.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.example.springdemo.test.apache;

import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.zip.Zip64Mode;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveInputStream;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xuleyan
 * @version TestZip2.java, v 0.1 2019-03-22 8:42 AM xuleyan
 */
public class TestZip2 {

    public static void main(String[] args) {
//        decompressZip2File();
        compressFile2Zip();
    }

    /**
     * 将zip压缩包解压成文件到指定文件夹
     */
    private static void decompressZip2File() {
        long start = System.currentTimeMillis();
        // 1.定义压缩包地址
        String dir = "/Users/xuleyan/Desktop/";
        String zipFilePath = dir + "test.zip";
        File zipFile = new File(zipFilePath);
        InputStream inputStream = null;
        ArchiveEntry archiveEntry = null;
        OutputStream outputStream = null;
        try {
            inputStream = new FileInputStream(zipFile);
            ZipArchiveInputStream zipArchiveInputStream = new ZipArchiveInputStream(inputStream, "UTF-8");
            while (null != (archiveEntry = zipArchiveInputStream.getNextEntry())) {
                //文件名称
                String fileName = archiveEntry.getName();
                //构造解压后文件的存放路径
                String filePath = dir + fileName;

                byte[] content = new byte[(int) archiveEntry.getSize()];
                //将压缩包解压的内容读取到content
                zipArchiveInputStream.read(content);
                File entryFile = new File(filePath);
                if (!entryFile.exists()) {
                    entryFile.getParentFile().mkdirs();
                }
                // 创建一个指定输出文件的输出流
                outputStream = new FileOutputStream(entryFile);
                outputStream.write(content);
                outputStream.flush();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("耗时：" + (System.currentTimeMillis() - start) + "毫秒");
    }

    /**
     * 将指定文件夹压缩到指定zip压缩包
     */
    private static void compressFile2Zip() {
        long start = System.currentTimeMillis();
        // 1.定义文件地址
        String dir = "/Users/xuleyan/Desktop/test/";
        File file1 = new File(dir + "test.txt");
        File file2 = new File(dir + "test2.txt");

        List<File> files = new ArrayList<>();
        files.add(file1);
        files.add(file2);

        // 2.定义压缩包地址
        File zipFile = new File(dir + "test.zip");

        ZipArchiveOutputStream zipArchiveOutputStream = null;

        try {
            //输出文件压缩包
            zipArchiveOutputStream = new ZipArchiveOutputStream(zipFile);
            zipArchiveOutputStream.setUseZip64(Zip64Mode.AsNeeded);

            for (File file : files) {
                //将每个文件用ZipArchiveEntry封装，使用ZipArchiveOutputStream写到压缩文件
                ZipArchiveEntry zipArchiveEntry = new ZipArchiveEntry("test" + File.separator + file.getName());
                zipArchiveOutputStream.putArchiveEntry(zipArchiveEntry);

                InputStream inputStream = new FileInputStream(file);
                byte[] buffer = new byte[1024 * 5];
                inputStream.read(buffer);

                zipArchiveOutputStream.write(buffer);
            }
            zipArchiveOutputStream.closeArchiveEntry();
            zipArchiveOutputStream.finish();

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("耗时：" + (System.currentTimeMillis() - start) + "毫秒");
    }
}