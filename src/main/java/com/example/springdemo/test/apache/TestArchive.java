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
 * @version TestArchive.java, v 0.1 2019-03-21 10:37 PM xuleyan
 */
public class TestArchive {

    public static void main(String[] args) {
        decompressZip2Files();
//        compressFiles2Zip();
    }

    /**
     * 将zip压缩包解压成文件到指定文件夹
     */
    private static void decompressZip2Files() {
        long startInt = System.currentTimeMillis();
        // 1.定义压缩包绝对路径
        String zipFilePath = "/Users/xuleyan/Desktop/test.zip";
        File zipFile = new File(zipFilePath);
        // 2.定义输入流
        InputStream inputStream = null;
        // 3.定义输出流
        OutputStream outputStream = null;
        // 4.zip文件输入流
        ZipArchiveInputStream zipArchiveInputStream = null;
        // 5.定义archiveEntry
        ArchiveEntry archiveEntry = null;
        try {
            inputStream = new FileInputStream(zipFile);
            zipArchiveInputStream = new ZipArchiveInputStream(inputStream, "UTF-8");
            while (null != (archiveEntry = zipArchiveInputStream.getNextEntry())) {
                //获取文件名
                String archiveEntryFileName = archiveEntry.getName();
                //构造解压后文件的存放路径
                String archiveEntryPath = "/Users/xuleyan/Desktop/" + archiveEntryFileName;
                byte[] content = new byte[(int) archiveEntry.getSize()];
                zipArchiveInputStream.read(content);
                //把解压出来的文件写到指定路径
                File entryFile = new File(archiveEntryPath);
                if (!entryFile.exists()) {
                    entryFile.getParentFile().mkdirs();
                }
                outputStream = new FileOutputStream(entryFile);
                outputStream.write(content);
                outputStream.flush();
            }
        } catch (FileNotFoundException e) {
//			e.printStackTrace();
        } catch (IOException e) {
//			e.printStackTrace();
        } finally {
            if (null != outputStream) {
                try {
                    outputStream.close();
                } catch (IOException e) {
//					e.printStackTrace();
                }
            }
            if (null != zipArchiveInputStream) {
                try {
                    zipArchiveInputStream.close();
                } catch (IOException e) {
//					e.printStackTrace();
                }
            }
            if (null != inputStream) {
                try {
                    inputStream.close();
                } catch (IOException e) {
//					e.printStackTrace();
                }
            }
        }
        System.out.println("耗时：" + (System.currentTimeMillis() - startInt) + "毫秒");
    }

    /**
     * 将文件打包成zip压缩包文件
     */
    private static void compressFiles2Zip() {
        long startInt = System.currentTimeMillis();
        File file1 = new File("/Users/xuleyan/Desktop/test.txt");
        File file2 = new File("/Users/xuleyan/Desktop/test2.txt");

        List<File> files = new ArrayList<File>();
        files.add(file1);
        files.add(file2);
        File zipFile = new File("/Users/xuleyan/Desktop/test.zip");
        InputStream inputStream = null;
        ZipArchiveOutputStream zipArchiveOutputStream = null;
        try {
            zipArchiveOutputStream = new ZipArchiveOutputStream(zipFile);
            zipArchiveOutputStream.setUseZip64(Zip64Mode.AsNeeded);
            for (File file : files) {
                //将每个文件用ZipArchiveEntry封装，使用ZipArchiveOutputStream写到压缩文件
                ZipArchiveEntry zipArchiveEntry = new ZipArchiveEntry(file, "test" + File.separator + file.getName());
                zipArchiveOutputStream.putArchiveEntry(zipArchiveEntry);

                inputStream = new FileInputStream(file);
                byte[] buffer = new byte[1024 * 5];
                int len = -1;
                while ((len = inputStream.read(buffer)) != -1) {
                    //把缓冲区的字节写入到ZipArchiveEntry
                    zipArchiveOutputStream.write(buffer, 0, len);
                }
            }
            zipArchiveOutputStream.closeArchiveEntry();
            zipArchiveOutputStream.finish();
        } catch (IOException e) {
//			e.printStackTrace();
        } finally {
            //关闭输入流
            if (null != inputStream) {
                try {
                    inputStream.close();
                } catch (IOException e) {
//					e.printStackTrace();
                }
            }
            //关闭输出流
            if (null != zipArchiveOutputStream) {
                try {
                    zipArchiveOutputStream.close();
                } catch (IOException e) {
//					e.printStackTrace();
                }
            }
        }
        System.out.println("耗时：" + (System.currentTimeMillis() - startInt) + "毫秒");
    }
}


