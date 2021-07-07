/**
 * fshows.com
 * Copyright (C) 2013-2020 All Rights Reserved.
 */
package com.example.springdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


/**
 * @author xuleyan
 * @version LogAspect.java, v 0.1 2020-04-04 5:53 PM xuleyan
 */
@Aspect
@Component
public class LogAspect {

    private Logger logger = LoggerFactory.getLogger(getClass());


}