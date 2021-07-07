/**
 * fshows.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.example.springdemo.elasticsearch.config;

import com.example.springdemo.config.SysConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

/**
 * @author xuleyan
 * @version SpringEsConfig.java, v 0.1 2019-09-21 2:26 PM xuleyan
 */
//@Configuration
public class SpringEsConfig {
    @Autowired
    private SysConfig sysConfig;

    /**
     * 订单ES库
     *
     * @return
     */
    @Bean(name = "elasticSearchClient", initMethod = "init", destroyMethod = "close")
    public ElasticSearchClient elasticSearchClient() {
        ElasticSearchClient elasticSearchClient = new ElasticSearchClient();
        elasticSearchClient.setHost(sysConfig.getEsHost());
        elasticSearchClient.setPort(sysConfig.getEsPort());
        elasticSearchClient.setUsername(sysConfig.getEsUsername());
        elasticSearchClient.setPassword(sysConfig.getEsPassword());
        return elasticSearchClient;
    }
}