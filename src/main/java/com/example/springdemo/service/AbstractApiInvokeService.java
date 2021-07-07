/**
 * fshows.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.example.springdemo.service;

import com.example.springdemo.bean.ApiContainer;
import com.example.springdemo.bean.ApiDescriptor;
import com.example.springdemo.bean.ApplicationContextHelper;
import com.example.springdemo.bean.exception.ApiInvokeException;
import com.fshows.fsframework.core.BaseParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * API调用服务
 * @author xuleyan
 * @version AbstractApiInvokeService.java, v 0.1 2019-04-12 8:45 AM xuleyan
 */
@Component
public abstract class AbstractApiInvokeService {

    /**
     * API调用类
     */
    @Autowired
    protected ApiClient apiClient;

    @Autowired
    protected ApiContainer apiContainer;

    /**
     * 调用api
     * @param appId
     * @param sign
     * @param apiMethodName
     * @param params
     * @param content
     * @param <P>
     * @param <R>
     * @return
     * @throws Throwable
     */
    @SuppressWarnings("unchecked")
    public <P extends BaseParam, R> R invoke(String appId, String sign, String apiMethodName, Map<String, Object> params, String content) throws Throwable {
        // 获得方法
        ApiDescriptor apiDescriptor = apiContainer.get(apiMethodName);
        if (null == apiDescriptor) {
            throw ApiInvokeException.API_NOT_EXIST;
        }
        // 获得bean
        String beanName = apiDescriptor.getBeanName();
        Object bean = ApplicationContextHelper.getBean(beanName);
        if (null == bean) {
            throw ApiInvokeException.API_NOT_EXIST;
        }
        boolean hasPermission = checkPermission(appId, apiMethodName);
        if (!hasPermission) {
            throw ApiInvokeException.DO_NOT_HAS_PERMISSION;
        }
        boolean checkSign = checkSign(params, appId);
        if (!checkSign) {
            throw ApiInvokeException.API_INVALID_SIGIN;
        }
        // 调用方法
        return apiClient.invoke(apiMethodName, content, (ApiInvoker<P, R>) (method, param) ->
                (R) apiDescriptor.getMethod().invoke(bean, param));
    }

    /**
     * 验证签名
     * @param params
     * @param appId
     * @return
     */
    protected abstract boolean checkSign(Map<String, Object> params, String appId);

    /**
     * 权限验证
     * @param appId
     * @param apiMethodName
     * @return
     */
    protected abstract boolean checkPermission(String appId, String apiMethodName);


}