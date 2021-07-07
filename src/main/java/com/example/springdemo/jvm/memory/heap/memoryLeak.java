/**
 * fshows.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.example.springdemo.jvm.memory.heap;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * -verbose:gc -Xms5M -Xmx10M -XX:NewRatio=8 -XX:+PrintGCDetails -XX:+HeapDumpOnOutOfMemoryError
 * @author xuleyan
 * @version memoryLeak.java, v 0.1 2019-05-04 6:01 PM xuleyan
 */
public class memoryLeak {
    public static void main(String s[]){
        Set<MyObject> objects = new LinkedHashSet<MyObject>();
        objects.add(new MyObject());
        objects.add(new MyObject());
        objects.add(new MyObject());
        System.out.println(objects.size());
        while(true){
            objects.add(new MyObject());
        }
    }
}

class MyObject {
    //设置默认数组长度为99999更快的发生OutOfMemoryError
    List<String> list = new ArrayList<>(99999);

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) {
//            return true;
//        }
//        if (!(o instanceof MyObject)) {
//            return false;
//        }
//        MyObject myObject = (MyObject) o;
//        return Objects.equals(list, myObject.list);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(list);
//    }
}