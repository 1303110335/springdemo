/**
 * fshows.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.example.springdemo.jvm.memory.metaspace;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

/**
 * -verbose:gc -XX:MetaspaceSize=10M -XX:MaxMetaspaceSize=10M -XX:+PrintGCDetails
 * @author xuleyan
 * @version PermGenOomMock.java, v 0.1 2019-04-26 9:13 AM xuleyan
 */
public class PermGenOomMock {
    public static void main(String[] args) {
        URL url = null;
        List<ClassLoader> classLoaderList = new ArrayList<ClassLoader>();
        try {
            url = new File("/tmp").toURI().toURL();
            URL[] urls = {url};
            while (true){
                ClassLoader loader = new URLClassLoader(urls);
                classLoaderList.add(loader);
                loader.loadClass("com.example.springdemo.jvm.memory.methodArea.MethodAreaOutOfMemory");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}