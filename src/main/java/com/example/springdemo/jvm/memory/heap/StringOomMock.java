/**
 * fshows.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.example.springdemo.jvm.memory.heap;

import java.util.ArrayList;
import java.util.List;

/**
 * -verbose:gc -XX:PermSize=8M -XX:MaxPermSize=8M -Xmx16M -XX:MetaspaceSize=20M -XX:MaxMetaspaceSize=200M -XX:+PrintGCDetails -XX:+HeapDumpOnOutOfMemoryError -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/Users/xuleyan/springdemo/string.hprof
 *
 * @author xuleyan
 * @version StringOomMock.java, v 0.1 2019-04-26 8:35 AM xuleyan
 */
public class StringOomMock {
    private static String  base = "string";
    public static void main(String[] args) {
        List<String> list = new ArrayList<String>();
        for (int i=0;i< Integer.MAX_VALUE;i++){
            String str = base + base;
            base = str;
            list.add(str.intern());
        }
    }
}