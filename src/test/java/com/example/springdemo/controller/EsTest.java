/**
 * fshows.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.example.springdemo.controller;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.json.JSONUtil;
import com.example.springdemo.SpringdemoApplication;
import com.example.springdemo.constants.CrmLostStoresConstant;
import com.example.springdemo.domain.CrmLostStoresTradeDetailModel;
import com.example.springdemo.elasticsearch.config.ElasticSearchClient;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.metrics.sum.Sum;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xuleyan
 * @version EsTest.java, v 0.1 2019-09-20 6:08 PM xuleyan
 */
@SpringBootTest(classes = SpringdemoApplication.class)
@RunWith(SpringRunner.class)
public class EsTest {

    final String STORE_INDEX = "crmstoretrade";
    final String STORE_TYPE = "crmstoretrade";
    final String TRADE_NUMBER = "trade_number";
    private volatile String index = "cusomer";
    private volatile String type = "man";
    @Qualifier("elasticSearchClient")
    @Autowired
    private ElasticSearchClient elasticSearchClient;

    @Test
    public void getByIdTest() {
//        GetResponse response = elasticSearchClient.getDocument(index, type, "1");
        GetResponse response = elasticSearchClient.getDocument(CrmLostStoresConstant.INDEX, CrmLostStoresConstant.TYPE, "1");
        System.out.println(response.getSource());
    }

    @Test
    public void saveDocumentTest() {
        Map<String, Object> jsonMap = new HashMap<>();
        jsonMap.put("name", "xuleyan");
        IndexResponse indexResponse = elasticSearchClient.saveDocument(jsonMap, index, type, "2");
        System.out.println(indexResponse);
    }

    @Test
    public void updateDocumentTest() {
        Map<String, Object> jsonMap = new HashMap<>();
        jsonMap.put("name", "xuleyan_update");
        UpdateResponse updateResponse = elasticSearchClient.updateDocument(jsonMap, index, type, "2");
        System.out.println(updateResponse);
    }

    /**
     * crm批量插入
     */
    @Test
    public void crmInsertBatchTest() {
        List<CrmLostStoresTradeDetailModel> entityList = new ArrayList<>();
        for (int i = 0; i <= 15; i++) {
            CrmLostStoresTradeDetailModel model = new CrmLostStoresTradeDetailModel();
            model.setStoreId(333333);
            if (i < 10) {
                model.setTradeTime("2019040" + i);
            } else {
                model.setTradeTime("201904" + i);
            }
            model.setTradeNumber(RandomUtil.randomInt(300));
            model.setTradeAmount(RandomUtil.randomBigDecimal(new BigDecimal("200")).toString());
            entityList.add(model);
        }
        String index = CrmLostStoresConstant.INDEX;
        String type = CrmLostStoresConstant.TYPE;
        Boolean isAsync = false;
        BulkResponse responses = elasticSearchClient.saveEntityBulk(entityList, index, type, isAsync);
        System.out.println(responses);
    }

    /**
     * 分页查询
     */
    @Test
    public void testScroolQuery() {
        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
        TermQueryBuilder termQueryBuilder = new TermQueryBuilder("store_id", 123456);
        boolQueryBuilder.must(termQueryBuilder);
        SearchResponse response = elasticSearchClient.searchByScroll(boolQueryBuilder, STORE_INDEX, STORE_TYPE, 10, "trade_number");
        System.out.println("总条数:" + response.getHits().getTotalHits());

        SearchHit[] searchHits = response.getHits().getHits();
        List<CrmLostStoresTradeDetailModel> storesTradeDetailModels = fmtStores(searchHits);
        System.out.println(storesTradeDetailModels);
    }

    private List<CrmLostStoresTradeDetailModel> fmtStores(SearchHit[] searchHits) {
        if (searchHits.length == 0) {
            return null;
        }
        List<CrmLostStoresTradeDetailModel> storesTradeDetailModels = new ArrayList<>();
        for (SearchHit searchHit : searchHits) {
            String str = searchHit.getSourceAsString();
            CrmLostStoresTradeDetailModel storesTradeDetailModel = JSONUtil.toBean(str, CrmLostStoresTradeDetailModel.class);
            storesTradeDetailModels.add(storesTradeDetailModel);
        }
        return storesTradeDetailModels;
    }

    /**
     * 聚合查询
     */
    @Test
    public void testAggregationBuilder() {
        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
        TermQueryBuilder termQueryBuilder = new TermQueryBuilder("store_id", 123456);
        boolQueryBuilder.must(termQueryBuilder);
        AggregationBuilder aggregationBuilder = AggregationBuilders.sum(TRADE_NUMBER).field(TRADE_NUMBER);
        SearchResponse aggResult = elasticSearchClient.aggByQueryBuilder(boolQueryBuilder, STORE_INDEX, STORE_TYPE, aggregationBuilder);
        Sum agg = aggResult.getAggregations().get(TRADE_NUMBER);
        System.out.println("总交易数:" + agg.getValue());


    }

}