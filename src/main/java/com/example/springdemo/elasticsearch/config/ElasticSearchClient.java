/**
 * fshows.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.example.springdemo.elasticsearch.config;

import cn.hutool.core.bean.BeanUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.bulk.BulkItemResponse;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.ClearScrollRequest;
import org.elasticsearch.action.search.ClearScrollResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchScrollRequest;
import org.elasticsearch.action.support.WriteRequest;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.search.Scroll;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author xuleyan
 * @version ElasticSearchClient.java, v 0.1 2019-09-20 4:01 PM xuleyan
 */
public class ElasticSearchClient {
    private static final Logger LOGGER = LoggerFactory.getLogger(ElasticSearchClient.class);
    /**
     * 搜索上下文的时间,用来支持该批次
     */
    private static final Long SCROLL_ALIVE_TIME = 5L;
    private String host;
    private int port;
    private String username;
    private String password;
    private RestHighLevelClient restHighLevelClient;

    public ElasticSearchClient() {
    }

    /**
     * 初始化连接
     */
    public void init() {
        if (restHighLevelClient == null) {
            final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
            credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(username, password));
            RestClientBuilder builder = RestClient.builder(new HttpHost(host, port))
                    .setHttpClientConfigCallback(new RestClientBuilder.HttpClientConfigCallback() {
                        @Override
                        public HttpAsyncClientBuilder customizeHttpClient(HttpAsyncClientBuilder httpClientBuilder) {
                            return httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider);
                        }
                    })
                    //超时时间10s
                    .setMaxRetryTimeoutMillis(10000);
            restHighLevelClient = new RestHighLevelClient(builder);
            LOGGER.info("Start ElasticSearch client success , host = {} , port = {}", host, port);
        } else {
            LOGGER.warn("ElasticSearch client 重复连接, host = {} , port = {}", host, port);
        }
    }

    /**
     * 关闭连接
     */
    public void close() {
        try {
            LOGGER.info("Closing elasticSearch client");
            if (restHighLevelClient != null) {
                restHighLevelClient.close();
            }
        } catch (final Exception e) {
            LOGGER.error("Error closing ElasticSearch client: ", e);
        }
    }

    /**
     * Setter method for property <tt>host</tt>.
     *
     * @param host value to be assigned to property host
     */
    public void setHost(String host) {
        this.host = host;
    }

    /**
     * Setter method for property <tt>port</tt>.
     *
     * @param port value to be assigned to property port
     */
    public void setPort(int port) {
        this.port = port;
    }

    /**
     * Setter method for property <tt>username</tt>.
     *
     * @param username value to be assigned to property username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Setter method for property <tt>password</tt>.
     *
     * @param password value to be assigned to property password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Getter method for property <tt>restHighLevelClient</tt>.
     *
     * @return property value of restHighLevelClient
     */
    public RestHighLevelClient getRestHighLevelClient() {
        return restHighLevelClient;
    }

    /**
     * 根据id查询文档
     *
     * @param index
     * @param type
     * @param id
     * @return
     */
    public GetResponse getDocument(String index, String type, String id) {
        GetRequest getRequest = new GetRequest(
                index,
                type,
                id
        );
        GetResponse response = null;
        try {
            response = restHighLevelClient.get(getRequest);
        } catch (IOException e) {
            LOGGER.error("getDocument ->> 根据id查询失败！index = {},type = {},id = {}", index, type, id, e);
        }
        return response;
    }

    /**
     * 保存文档
     *
     * @param jsonMap
     * @param index
     * @param type
     * @param id
     * @return
     */
    public IndexResponse saveDocument(Map<String, Object> jsonMap, String index, String type, String id) {
        IndexRequest indexRequest = null;
        if (id == null) {
            indexRequest = new IndexRequest(index, type).source(jsonMap);
        } else {
            indexRequest = new IndexRequest(index, type, id).source(jsonMap);
        }
        IndexResponse response = null;
        try {
            response = restHighLevelClient.index(indexRequest);
        } catch (IOException e) {
            LOGGER.error("saveDocument ->> 保存失败！obj = {},index = {},type = {},id = {}",
                    jsonMap, index, type, id, e);
        }
        return response;
    }

    /**
     * 更新文档
     *
     * @param jsonMap
     * @param index
     * @param type
     * @param id
     * @return
     */
    public UpdateResponse updateDocument(Map<String, Object> jsonMap, String index, String type, String id) {
        UpdateRequest request = new UpdateRequest(index, type, id).doc(jsonMap);
        UpdateResponse response = null;
        try {
            response = restHighLevelClient.update(request);
        } catch (IOException e) {
            LOGGER.error("updateDocument ->> 修改失败！str = {},index = {},type = {},id = {}",
                    jsonMap, index, type, id, e);
        }
        return response;
    }

    /**
     * 批量保存数据
     *
     * @param entityList
     * @param index
     * @param type
     * @param isAsync
     * @param <T>
     * @return
     */
    public <T> BulkResponse saveEntityBulk(List<T> entityList, String index, String type, Boolean isAsync) {
        BulkRequest request = new BulkRequest();
        BulkResponse bulkResponse = null;
        for (T t : entityList) {
            Map<String, Object> jsonMap = BeanUtil.beanToMap(t, true, true);
            String id = (String) jsonMap.get("id");
            if (StringUtils.isBlank(id)) {
                IndexRequest indexRequest = new IndexRequest(index, type).source(jsonMap);
                request.add(indexRequest);
            } else {
                IndexRequest indexRequest = new IndexRequest(index, type, id).source(jsonMap);
                request.add(indexRequest);
            }
        }
        request.setRefreshPolicy(WriteRequest.RefreshPolicy.WAIT_UNTIL);
        if (isAsync) {
            saveBulkAsync(request, index, type, "saveEntityBulk");
        } else {
            bulkResponse = saveBulkSync(request, index, type, "saveEntityBulk");
        }
        return bulkResponse;
    }

    /**
     * 批量bulk(同步)
     *
     * @param request
     * @param index
     * @param type
     * @param method
     * @return
     */
    private BulkResponse saveBulkSync(BulkRequest request, String index, String type, String method) {
        BulkResponse bulkResponse = null;
        try {
            bulkResponse = restHighLevelClient.bulk(request);
            if (bulkResponse.hasFailures()) {
                LOGGER.error("saveBulkSync ->> 批量保存部分失败！index = {},type = {},errorMsg = {}",
                        index, type, bulkResponse.buildFailureMessage());
            }
            BulkItemResponse[] responses = bulkResponse.getItems();
            printFailedItems(responses, method);
        } catch (IOException e) {
            LOGGER.error("saveBulkSync ->> 批量保存失败！index = {},type = {}",
                    index, type, e);
        }
        return bulkResponse;
    }

    /**
     * 批量bulk(异步)
     *
     * @param request
     * @param index
     * @param type
     * @param method
     */
    private void saveBulkAsync(BulkRequest request, String index, String type, String method) {
        restHighLevelClient.bulkAsync(request, new ActionListener<BulkResponse>() {
            @Override
            public void onResponse(BulkResponse bulkResponse) {
                if (bulkResponse.hasFailures()) {
                    LOGGER.error("saveEntityBulkAsync ->> 异步批量保存部分失败！index = {},type = {},errorMsg = {}",
                            index, type, bulkResponse.buildFailureMessage());
                }
                BulkItemResponse[] responses = bulkResponse.getItems();
                printFailedItems(responses, method);
            }

            @Override
            public void onFailure(Exception e) {
                LOGGER.error("saveEntityBulkAsync ->> 异步批量保存执行失败！index = {},type = {}",
                        index, type, e);
            }
        });
    }

    public <T> void printFailedItems(BulkItemResponse[] responses, String method) {
        if (null == responses || responses.length == 0) {
            return;
        }
        List<String> ids = new ArrayList<>();
        for (BulkItemResponse bulkItemResponse : responses) {
            if (bulkItemResponse.isFailed()) {
                BulkItemResponse.Failure failure = bulkItemResponse.getFailure();
                ids.add(failure.getId());
            }
        }
        if (ids.size() > 0) {
            LOGGER.error(method + " ->> 部分保存失败! dis = {}", ids.toString());
        }
    }

    /**
     * Scroll分页自定义搜索
     *
     * @param boolQueryBuilder
     * @param index
     * @param type
     * @param size
     * @param sortName
     * @return
     */
    public SearchResponse searchByScroll(BoolQueryBuilder boolQueryBuilder, String index, String type, Integer size, String sortName) {
        SearchRequest searchRequest = new SearchRequest(index);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(boolQueryBuilder);
        searchSourceBuilder.size(size);
        if (StringUtils.isNotBlank(sortName)) {
            searchSourceBuilder.sort(sortName, SortOrder.DESC);
        }
        searchRequest.source(searchSourceBuilder);
        searchRequest.scroll(TimeValue.timeValueMinutes(SCROLL_ALIVE_TIME));
        SearchResponse searchResponse = null;
        try {
            searchResponse = restHighLevelClient.search(searchRequest);
        } catch (IOException e) {
            LOGGER.error("searchByScroll ->> scroll分页搜索失败！index = {},type = {}",
                    index, type, e);
        }
        return searchResponse;
    }

    /**
     * 根据多个条件搜索
     *
     * @param boolQueryBuilder
     * @param index
     * @param type
     * @param page
     * @param pageSize
     * @param sortName
     * @return
     */
    public SearchResponse searchByQueryBuilder(BoolQueryBuilder boolQueryBuilder, String index, String type, Integer page, Integer pageSize, String sortName) {
        SearchRequest searchRequest = new SearchRequest(index);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(boolQueryBuilder);
        searchSourceBuilder.from(page);
        searchSourceBuilder.size(pageSize);
        if (StringUtils.isNotBlank(sortName)) {
            searchSourceBuilder.sort(sortName, SortOrder.DESC);
        }
        searchRequest.source(searchSourceBuilder);
        SearchResponse response = null;
        try {
            response = restHighLevelClient.search(searchRequest);
        } catch (IOException e) {
            LOGGER.error("searchByQueryBuilder ->> 根据多个条件搜索失败！index = {},boolQueryBuilder = {}",
                    index, boolQueryBuilder.toString(), e);
        }
        return response;
    }

    /**
     * Scroll分页根据scrollId搜索
     *
     * @param index
     * @param type
     * @param scrollId
     * @return
     */
    public SearchResponse searchByScrollId(String index, String type, String scrollId) {
        final Scroll scroll = new Scroll(TimeValue.timeValueMinutes(SCROLL_ALIVE_TIME));
        SearchScrollRequest scrollRequest = new SearchScrollRequest(scrollId);
        scrollRequest.scroll(scroll);
        SearchResponse searchResponse = null;
        try {
            searchResponse = restHighLevelClient.searchScroll(scrollRequest);
        } catch (IOException e) {
            LOGGER.error("searchByScrollId ->> scroll分页搜索失败！index = {},type = {}",
                    index, type, e);
        }
        return searchResponse;
    }

    /**
     * 删除scrollId
     *
     * @param scrollId
     */
    public void clearScrollId(String scrollId) {
        ClearScrollRequest clearScrollRequest = new ClearScrollRequest();
        clearScrollRequest.addScrollId(scrollId);
        try {
            ClearScrollResponse clearScrollResponse = restHighLevelClient.clearScroll(clearScrollRequest);
            boolean succeeded = clearScrollResponse.isSucceeded();
            LOGGER.info("clearScrollId ->> result = {}", succeeded);
        } catch (IOException e) {
            LOGGER.error("clearScrollId ->> 删除scrollId失败！scrollId = {}",
                    scrollId, e);
        }
    }


    /**
     * 多条件查询无分页
     *
     * @param boolQueryBuilder
     * @param index
     * @return
     */
    public SearchResponse searchByQueryBuilder(BoolQueryBuilder boolQueryBuilder, String index) {
        SearchRequest searchRequest = new SearchRequest(index);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.from(0);
        searchSourceBuilder.size(0);
        searchSourceBuilder.query(boolQueryBuilder);
        searchRequest.source(searchSourceBuilder);
        SearchResponse response = null;
        try {
            response = restHighLevelClient.search(searchRequest);
        } catch (IOException e) {
            LOGGER.error("searchByQueryBuilder ->> 根据多个条件搜索失败！index = {},boolQueryBuilder = {}",
                    index, boolQueryBuilder.toString(), e);
        }
        return response;
    }

    /**
     * 根据多个条件搜索聚合
     *
     * @param boolQueryBuilder
     * @param index
     * @param type
     * @param aggregationBuilder
     * @return
     */
    public SearchResponse aggByQueryBuilder(BoolQueryBuilder boolQueryBuilder, String index, String type, AggregationBuilder aggregationBuilder) {
        SearchRequest searchRequest = new SearchRequest(index);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(boolQueryBuilder);
        searchSourceBuilder.from(0);
        searchSourceBuilder.size(0);
        searchSourceBuilder.aggregation(aggregationBuilder);
        searchRequest.source(searchSourceBuilder);
        SearchResponse response = null;
        try {
            response = restHighLevelClient.search(searchRequest);
        } catch (IOException e) {
            LOGGER.error("searchByQueryBuilder ->> 根据多个条件搜索失败！index = {},boolQueryBuilder = {}",
                    index, boolQueryBuilder.toString(), e);
        }
        return response;
    }


}