/**
 * fshows.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.example.springdemo.domain;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @author xuleyan
 * @version PhpAggregationPushParam.java, v 0.1 2019-04-25 10:42 AM xuleyan
 */
@Data
public class PhpAggregationPushParam {

    /**
     * 商户token
     */
    @NotNull
    private String token;
    /**
     * 商户id
     */
    @NotNull
    private Integer uid;
    /**
     * 银行名称
     */
    private String bankName;
    /**
     * 卡号
     */
    private String cardNo;
    /**
     * 金额
     */
    @NotNull
    private BigDecimal tradeMoney;
    /*
     * 提现时间 yyyy-MM-dd HH:mm:ss
     */
    @NotNull
    private String withdrawTime;
}