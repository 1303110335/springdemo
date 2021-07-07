/**
 * fshows.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.example.springdemo.elasticsearch;


import com.example.springdemo.elasticsearch.config.ElasticSearchClient;
import org.elasticsearch.action.get.GetResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * @author xuleyan
 * @version Index.java, v 0.1 2019-09-05 8:56 PM xuleyan
 */
public class Index {

    volatile String index = "customer";
    volatile String type = "man";

    @Qualifier("elasticSearchClient")
    @Autowired
    private ElasticSearchClient elasticSearchClient;

    public static void main(String[] args) {


    }


    public void getByIdTest() {
        GetResponse response = elasticSearchClient.getDocument(index, type, "p87_TW0BIVxlxnxkm8QS");
        System.out.println(response);
    }
}