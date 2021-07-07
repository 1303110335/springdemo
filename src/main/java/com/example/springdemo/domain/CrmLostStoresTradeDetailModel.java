/**
 * fshows.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.example.springdemo.domain;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * crm流失门店交易明细
 *
 * @author sus
 * @version CrmLostStoresTradeDetailModel.java, v 0.1 2019-04-23 10:29 sus
 */
@Data
public class CrmLostStoresTradeDetailModel {

    /**
     * 分片日期
     * yyyy-MM-dd
     */
    @JSONField(name = "pt_day")
    private String ptDay;

    /**
     * 日期
     * yyyy-MM-dd
     */
    @JSONField(name = "trade_time")
    private String tradeTime;

    /**
     * 交易笔数
     */
    @JSONField(name = "trade_number")
    private Integer tradeNumber;

    /**
     * 交易金额
     */
    @JSONField(name = "trade_amount")
    private String tradeAmount;

    /**
     * 门店id
     */
    @JSONField(name = "store_id")
    private Integer storeId;

    /**
     * es 主键
     */
    @JSONField(name = "id")
    private Integer id;
}