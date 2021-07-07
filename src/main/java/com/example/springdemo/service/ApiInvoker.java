/**
 * fshows.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.example.springdemo.service;

import com.example.springdemo.bean.ApiDescriptor;
import com.fshows.fsframework.core.BaseParam;

import java.lang.reflect.InvocationTargetException;

/**
 * @author xuleyan
 * @version ApiInvoker.java, v 0.1 2019-04-12 9:02 AM xuleyan
 */
public interface ApiInvoker<P extends BaseParam, R> {

    /**
     * api 调用接口
     * @param apiDescriptor
     * @param param
     * @return
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    R invoke(ApiDescriptor apiDescriptor, P param) throws InvocationTargetException, IllegalAccessException;
}