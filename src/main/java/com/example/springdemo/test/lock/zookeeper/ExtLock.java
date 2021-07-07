/**
 * fshows.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.example.springdemo.test.lock.zookeeper;

/**
 * @author xuleyan
 * @version ExtLock.java, v 0.1 2019-12-08 9:29 AM xuleyan
 */
public interface ExtLock {

    public void getLock();

    public void unLock();
}
