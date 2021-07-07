/**
 * fshows.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.example.springdemo.service;

import com.alibaba.dubbo.rpc.RpcContext;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.PropertyNamingStrategy;
import com.alibaba.fastjson.parser.ParserConfig;
import com.example.springdemo.bean.ApiContainer;
import com.example.springdemo.bean.ApiDescriptor;
import com.example.springdemo.bean.exception.ApiInvokeException;
import com.example.springdemo.constants.ApiConstants;
import com.example.springdemo.utils.SystemClock;
import com.example.springdemo.utils.TraceIdGenerator;
import com.fshows.fsframework.common.exception.BaseException;
import com.fshows.fsframework.core.BaseParam;
import com.fshows.fsframework.core.ValidateResult;
import com.fshows.fsframework.core.utils.LogUtil;
import com.fshows.fsframework.core.utils.ValidateUtil;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.text.MessageFormat;

/**
 * @author xuleyan
 * @version ApiClient.java, v 0.1 2019-04-12 8:54 AM xuleyan
 */
@Component
@Slf4j
public class ApiClient {
    /**
     * 设置FASTJSON解析使用下划线的方式
     */
    private static final ParserConfig FAST_JSON_PARSER_CONFIG = new ParserConfig();
    private static final String ERROR_INFO = "Http 接口异常结束 methodName = {},  exception = {}, time = {}ms";
    static {
        FAST_JSON_PARSER_CONFIG.propertyNamingStrategy = PropertyNamingStrategy.SnakeCase;
    }

    @Autowired
    private ApiContainer apiContainer;

    public <P extends BaseParam, R> R invoke(String method, String paramJsonStr, ApiInvoker<P, R> invoker) throws Throwable {

        // 获得开始时间
        long start = SystemClock.millisClock().now();
        // 生成traceId
        String traceId = TraceIdGenerator.generate();
        // 将traceId放入日志上下文
        MDC.put("TRACE_ID", traceId);
        // 将traceId放入dubbo（附件）上下文
        RpcContext.getContext().setAttachment("TRACE_ID", traceId);
        LogUtil.info(log, "Http 接口开始 methodName = {}, agruments = {}", method, paramJsonStr);

        // 获得方法信息
        ApiDescriptor apiDescriptor = apiContainer.get(method);
        try {
            // 序列化方法参数
            String[] paramFullNameList = apiDescriptor.getParamFullNameList();
            if (paramFullNameList == null || paramFullNameList.length < ApiConstants.DEFAULT_API_PARAM_LENGTH) {
                throw ApiInvokeException.API_PARAM_LENGTH_ERROR;
            }
            P result = JSON.parseObject(paramJsonStr, Class.forName(paramFullNameList[0]), FAST_JSON_PARSER_CONFIG);
            // 校验参数
            ValidateResult validateResult = ValidateUtil.validate(result);
            if (!validateResult.isResult()) {
                throw ApiInvokeException.API_PARAM_VALIDATE_ERROR.newInstance("参数{}{}", validateResult.getParamName(), validateResult.getMsg());
            }
            // 调用方法
            R invoke = invoker.invoke(apiDescriptor, result);

            LogUtil.info(log, "Http 接口结束 methodName = {}, result = {}, time = {}ms", method, invoke, System.currentTimeMillis());
            return invoke;
        } catch (ApiInvokeException e) {
            printInfoLog(method, start, e);
            throw e;
        } catch (BaseException e) {
            printInfoLog(method, start,  e);
            throw e;
        } catch (InvocationTargetException e){
            printInfoLog(method, start,  e);
            throw e.getTargetException();
        }catch (Exception e) {
            printInfoLog(method, start,  e);
            String msg = MessageFormat.format("API调用异常,msg={0}", e.getMessage());
            LogUtil.error(log, msg, e);
            throw ApiInvokeException.API_INVOKE_ERROR;
        }
    }

    private void printInfoLog(String method, long start, Exception e) {
        LogUtil.info(log, ERROR_INFO, method, e, com.fshows.fsframework.core.utils.SystemClock.millisClock().now() - start);
    }

}