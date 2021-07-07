/**
 * fshows.com
 * Copyright (C) 2013-2020 All Rights Reserved.
 */
package com.example.springdemo.test.threads.limiter;

import com.alibaba.fastjson.JSONObject;
import com.example.springdemo.domain.AggregationPushModel;
import com.example.springdemo.domain.PhpAggregationPushParam;
import com.fshows.fsframework.core.utils.LogUtil;
import com.fshows.fsframework.core.utils.RequestUtil;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * @author xuleyan
 * @version PushTest.java, v 0.1 2020-04-11 8:53 AM xuleyan
 */
@Slf4j
public class PushTest {

    public static void main(String[] args) {
        PushTest test = new PushTest();
        PhpAggregationPushParam param = new PhpAggregationPushParam();
        param.setBankName("中国农业银行");
        param.setCardNo("6228480861176997810");
        param.setToken("100002030");
        param.setTradeMoney(new BigDecimal(2.00));
        param.setUid(3204419);
        param.setWithdrawTime("2020-04-11 09:03:13");
        AggregationPushModel pushModel = test.phpAggregationPush(param);
        System.out.println(pushModel);
    }

    public AggregationPushModel phpAggregationPush(PhpAggregationPushParam param) {


        String pushUrl = "http://www.mscript.com/api/AggregationPush/wxAndOssPush";
        Map<String, String> params = new HashMap<>();
        params.put("token", param.getToken());
        params.put("uid", param.getUid().toString());
        params.put("bankName", param.getBankName());
        params.put("cardNo", param.getCardNo());
        params.put("tradeMoney", param.getTradeMoney().toString());
        params.put("withdrawTime", param.getWithdrawTime());
        AggregationPushModel model = new AggregationPushModel();
        try {
            String postResult = RequestUtil.post(pushUrl, params);
            LogUtil.info(log, "phpAggregationPush >> 推送回调返回值：postResult = {}", postResult);
            JSONObject jsonObject = JSONObject.parseObject(postResult);
            String errMsg = jsonObject.getString("msg");
            Integer errCode = jsonObject.getInteger("status");
            int successCode = 1;
            if (errCode == successCode) {
                model.setResult(true);
                model.setDesc("php聚合推送成功");
            } else {
                model.setResult(false);
                model.setDesc(String.format("php聚合推送失败: 原因：%s", errMsg));
            }
        } catch (Exception ex) {
            LogUtil.info(log, "phpAggregationPush >> 推送回调异常：errMsg = {}", ex.getMessage());
            model.setResult(false);
            model.setDesc(String.format("php聚合推送失败: 原因：%s", ex.getMessage()));
        }
        return model;
    }
}