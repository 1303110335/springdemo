/**
 * fshows.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.example.springdemo.bean;

import com.example.springdemo.annotation.XuApi;
import com.example.springdemo.bean.exception.ApiInvokeException;
import com.example.springdemo.constants.ApiConstants;
import com.fshows.fsframework.core.annotation.LifecircleApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.text.MessageFormat;

/**
 * @author xuleyan
 * @version ApiScanProcessor.java, v 0.1 2019-04-10 10:14 AM xuleyan
 */
@Component
public class ApiScanProcessor implements BeanPostProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApiScanProcessor.class);

    @Resource
    private ApiContainer apiContainer;

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Method[] methods = ReflectionUtils.getAllDeclaredMethods(bean.getClass());
        // 遍历方法
        for (Method method : methods) {
            // 找到有XuApi 注解的方法
            XuApi xuApi = (XuApi) AnnotationUtils.findAnnotation(method, LifecircleApi.class);
            if (xuApi != null) {
                // 检测参数是否合法
                // 校验参数个数
                int parameterCount = method.getParameterCount();
                if (ApiConstants.DEFAULT_API_PARAM_LENGTH != parameterCount) {
                    String errorMsg = MessageFormat.format("【API加载异常】方法 method = {0}, 注解 @LifecircleApi 不合法, 参数个数不等于1", method.toGenericString());
                    LOGGER.error(errorMsg);
                    throw ApiInvokeException.API_PARAM_VALIDATE_ERROR.newInstance(errorMsg);
                }
                String name = xuApi.name();
                ApiDescriptor apiDescriptor = null;
                try {
                    apiDescriptor = new ApiDescriptor(method.toGenericString(), name, beanName);
                } catch (ClassNotFoundException ex) {
                    String msg = MessageFormat.format("【API加载异常】,msg={0}", ex.getMessage());
                    LOGGER.error(msg);
                    throw ApiInvokeException.API_PARAM_VALIDATE_ERROR.newInstance(msg);
                }
                apiContainer.put(name, apiDescriptor);
                LOGGER.info("已加载XUAPI,name={},api={}", name, apiDescriptor);
            }
        }
        return bean;
    }
}