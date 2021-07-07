/**
 * fshows.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.example.springdemo.domain;

import lombok.Data;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @author xuleyan
 * @version AggregationPushModel.java, v 0.1 2019-04-02 11:40
 */
@Data
public class AggregationPushModel {

    private Boolean result;

    private String desc;

    @Override
    public String toString() {
        return ReflectionToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
