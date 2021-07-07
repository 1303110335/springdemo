/**
 * fshows.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.example.springdemo.jvm.memory.constants;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author xuleyan
 * @version SingleCallbackParam.java, v 0.1 2019-05-08 5:00 PM xuleyan
 */
@Data
public class SingleCallParam {

    /**
     * 金额 单位：元
     */
    private BigDecimal tradeMoney;

    /**
     * 结算卡
     */
    private String bankCard;

    /**
     * 银行名称
     */
    private String bankName;

    /**
     * 联行号
     */
    private String bankNo;

    /**
     * 更新时间 时间戳
     */
    private Integer updateTime;

    /**
     * 更新时间 yyyyMMddHHmmss
     */
    private String updateTimeStr;

    /**
     * 更新时间
     */
    private Date updateDate;

    /**
     * 清算平台门店号：20181016093614023088
     */
    private String storeId;

    /**
     * 机构号：生活圈的
     */
    private String appId;

    /**
     * 打款单号
     */
    private String billId;

    /**
     * 失败原因
     */
    private String resultDesc;

    /**
     * 订单号
     */
    private String orderSn;

    /**
     * token
     */
    private String token;

    /**
     * uid
     */
    private Integer uid;

    /**
     * 结算卡号
     */
    private String cardNo;

    /**
     * 更新日期
     */
    private Integer tradeDate;

    /**
     * 通道类型
     */
    private Integer liquidationType;

    /**
     * 是否新余额
     */
    private Integer isAcct;

    /**
     * 打款状态
     */
    private Integer state;

    /**
     * fileReportChannel 通道: 1、乐刷 2、随行付 3、汇付 4.杉德
     */
    private Integer channel;

    /**
     * 流水号后缀 1.随行付："S" 2.乐刷: "L" 3.汇付: "H" 4.杉德
     */
    private String serialNumberSuffix;

    /**
     * 判断是否新余额
     *
     * @return
     */
    public boolean isNewBalance() {
        return isAcct == 1;
    }
}