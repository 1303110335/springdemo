/**
 * fshows.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.example.springdemo.test.lock.bf;

/**
 * @author xuleyan
 * @version Lock.java, v 0.1 2019-10-22 9:07 PM xuleyan
 */
public interface Lock {

    void tryLock();

    void unckLock();
}
