/**
 * fshows.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.example.springdemo.config;

import org.springframework.beans.factory.annotation.Value;

/**
 * @author auto
 * @version SysConfig.java, v 0.1 2018-07-20 16:27
 */
//@Configuration
public class SysConfig {
    /********************* 数据源 ******************/
    @Value("${jdbc.pool.validationQuery}")
    private String jdbcPoolValidationQuery;
    @Value("${jdbc.url}")
    private String jdbcUrl;
    @Value("${jdbc.username}")
    private String jdbcUsername;
    @Value("${jdbc.password}")
    private String jdbcPassword;

    /*********************系统当前运行环境********************/
    @Value("${application.env}")
    private String applicationEnv;

    /*********************redis 配置*******************/
    @Value("${redis.host}")
    private String redisHost;
    @Value("${redis.port}")
    private int redisPort;
    @Value("${redis.password}")
    private String redisPassword;
    @Value("${redis.connect.timeout}")
    private Integer redisConnectTimeout;

    /*********************es 配置*******************/
    @Value("${spring.data.elasticsearch.host}")
    private String esHost;
    @Value("${spring.data.elasticsearch.port}")
    private Integer esPort;
    @Value("${spring.data.elasticsearch.username}")
    private String esUsername;
    @Value("${spring.data.elasticsearch.password}")
    private String esPassword;

    /**
     * Getter method for property applicationEnv.
     *
     * @return applicationEnv
     */
    public String getApplicationEnv() {
        return applicationEnv;
    }

    /**
     * Getter method for property <tt>jdbcPoolValidationQuery</tt>.
     *
     * @return property value of jdbcPoolValidationQuery
     */
    public String getJdbcPoolValidationQuery() {
        return jdbcPoolValidationQuery;
    }

    /**
     * Getter method for property <tt>jdbcUrl</tt>.
     *
     * @return property value of jdbcUrl
     */
    public String getJdbcUrl() {
        return jdbcUrl;
    }

    /**
     * Getter method for property <tt>jdbcUsername</tt>.
     *
     * @return property value of jdbcUsername
     */
    public String getJdbcUsername() {
        return jdbcUsername;
    }

    /**
     * Getter method for property <tt>jdbcPassword</tt>.
     *
     * @return property value of jdbcPassword
     */
    public String getJdbcPassword() {
        return jdbcPassword;
    }

    /**
     * Getter method for property <tt>redisHost</tt>.
     *
     * @return property value of redisHost
     */
    public String getRedisHost() {
        return redisHost;
    }

    /**
     * Getter method for property <tt>redisPort</tt>.
     *
     * @return property value of redisPort
     */
    public int getRedisPort() {
        return redisPort;
    }

    /**
     * Getter method for property <tt>redisPassword</tt>.
     *
     * @return property value of redisPassword
     */
    public String getRedisPassword() {
        return redisPassword;
    }

    /**
     * Getter method for property <tt>redisConnectTimeout</tt>.
     *
     * @return property value of redisConnectTimeout
     */
    public Integer getRedisConnectTimeout() {
        return redisConnectTimeout;
    }

    /**
     * Getter method for property <tt>esHost</tt>.
     *
     * @return property value of esHost
     */
    public String getEsHost() {
        return esHost;
    }

    /**
     * Getter method for property <tt>esPort</tt>.
     *
     * @return property value of esPort
     */
    public Integer getEsPort() {
        return esPort;
    }

    /**
     * Getter method for property <tt>esUsername</tt>.
     *
     * @return property value of esUsername
     */
    public String getEsUsername() {
        return esUsername;
    }

    /**
     * Getter method for property <tt>esPassword</tt>.
     *
     * @return property value of esPassword
     */
    public String getEsPassword() {
        return esPassword;
    }
}
