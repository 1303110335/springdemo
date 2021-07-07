/**
 * fshows.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.example.springdemo.test.io;

import org.apache.commons.io.FileSystemUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * @author xuleyan
 * @version TestIO.java, v 0.1 2019-03-22 9:44 PM xuleyan
 */
public class TestIO {

    public static void main(String[] args) throws IOException {
//        InputStream inputStream = new URL("http://jakarta.apache.org").openStream();
//
//        InputStreamReader inR = new InputStreamReader(inputStream);
//        BufferedReader buf = new BufferedReader(inR);
//        String line;
//        while ((line = buf.readLine()) != null) {
//            System.out.println(line);
//        }
//
//        inputStream.close();

        //使用IOUtils
//        InputStream in = new URL("http://jakarta.apache.org").openStream();
//        try {
//            System.out.println(IOUtils.toString(in));
//        } finally {
//            IOUtils.closeQuietly(in);
//        }

//        Resource resource = new ClassPathResource("application.yml");
//        if (resource.exists()) {
//            System.out.println("文件存在");
//        }
//        File f = resource.getFile();
//        List lines = FileUtils.readLines(f);
//        System.out.println(lines);


        long result = FileSystemUtils.freeSpaceKb("/Users/xuleyan");
        System.out.println(result);

    }
}