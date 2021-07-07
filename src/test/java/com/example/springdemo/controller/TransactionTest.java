/**
 * fshows.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.example.springdemo.controller;

import com.example.springdemo.dal.fsriskmanagement.dao.ManagerDAO;
import com.example.springdemo.dal.fsriskmanagement.dataobject.ManagerDO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.sql.SQLException;

//import org.apache.shardingsphere.api.config.sharding.ShardingRuleConfiguration;
//import org.apache.shardingsphere.api.config.sharding.TableRuleConfiguration;
//import org.apache.shardingsphere.api.config.sharding.strategy.InlineShardingStrategyConfiguration;
//import org.apache.shardingsphere.shardingjdbc.api.ShardingDataSourceFactory;

/**
 * @author xuleyan
 * @version TransactionTest.java, v 0.1 2019-02-16 8:14 PM xuleyan
 */
@SpringBootTest
@RunWith(SpringRunner.class)
//@Transactional
public class TransactionTest {

    @Autowired
    @Qualifier("lifeCircleTransactionManager")
    private DataSourceTransactionManager transactionManager;

    @Autowired
    private ManagerDAO managerDAO;

    @Test
    public void testTransaction() {
        TransactionStatus transaction = transactionManager.getTransaction(new DefaultTransactionDefinition());

        try {
            //第一步更新
            ManagerDO managerDO1 = new ManagerDO();
            managerDO1.setToken("token first");
            managerDO1.setId(1);
            Integer updateRows = managerDAO.updateById(managerDO1);
            Assert.assertEquals(1, updateRows.intValue());

            ManagerDO managerDO = new ManagerDO();
            managerDO.setToken("tokenadsf9");
            managerDO.setId(2);
            Integer result = managerDAO.updateById(managerDO);
            Assert.assertEquals(1, result.intValue());

            transactionManager.commit(transaction);

        } catch (Exception e) {
            System.out.println("失败了");
            transactionManager.rollback(transaction);

        }
    }

    /**
     * 回滚可以使单元测试每次运行的环境独立
     */
    @Test
//    @Rollback
    public void testRollback() {
        String token = "newToken1";
        ManagerDO managerDO = new ManagerDO();
        managerDO.setToken(token);
        managerDO.setId(2);
        Integer result = managerDAO.updateById(managerDO);
        Assert.assertEquals(1, result.intValue());

        ManagerDO manager = managerDAO.getByUsername("xly");
        System.out.printf("manager is %s", manager);
        Assert.assertEquals(token, manager.getToken());

    }

    @Test
    public void test() throws SQLException {

        System.out.println(123);
// 配置真实数据源
//        Map<String, DataSource> dataSourceMap = new HashMap<>();
//
//        // 配置第一个数据源
//        BasicDataSource dataSource1 = new BasicDataSource();
//        dataSource1.setDriverClassName("com.mysql.jdbc.Driver");
//        dataSource1.setUrl("jdbc:mysql://localhost:3306/ds0");
//        dataSource1.setUsername("root");
//        dataSource1.setPassword("");
//        dataSourceMap.put("ds0", dataSource1);
//
//        // 配置第二个数据源
//        BasicDataSource dataSource2 = new BasicDataSource();
//        dataSource2.setDriverClassName("com.mysql.jdbc.Driver");
//        dataSource2.setUrl("jdbc:mysql://localhost:3306/ds1");
//        dataSource2.setUsername("root");
//        dataSource2.setPassword("");
//        dataSourceMap.put("ds1", dataSource2);
//
//        // 配置Order表规则
//        TableRuleConfiguration orderTableRuleConfig = new TableRuleConfiguration("t_order","ds${0..1}.t_order${0..1}");
//
//        // 配置分库 + 分表策略
//        orderTableRuleConfig.setDatabaseShardingStrategyConfig(new InlineShardingStrategyConfiguration("user_id", "ds${user_id % 2}"));
//        orderTableRuleConfig.setTableShardingStrategyConfig(new InlineShardingStrategyConfiguration("order_id", "t_order${order_id % 2}"));
//
//        // 配置分片规则
//        ShardingRuleConfiguration shardingRuleConfig = new ShardingRuleConfiguration();
//        shardingRuleConfig.getTableRuleConfigs().add(orderTableRuleConfig);
//
//        // 省略配置order_item表规则...
//        // ...
//
//        // 获取数据源对象
//
//        DataSource dataSource = ShardingDataSourceFactory.createDataSource(dataSourceMap, shardingRuleConfig, new Properties());
//        Connection connection = dataSource.getConnection();
//        Statement statement = connection.createStatement();
//        String sql = "select * from t_order";
//        ResultSet resultSet = statement.executeQuery(sql);
//        System.out.println(resultSet);
    }
}