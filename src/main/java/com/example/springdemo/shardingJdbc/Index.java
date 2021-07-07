package com.example.springdemo.shardingJdbc; /**
 * fshows.com
 * Copyright (C) 2013-2020 All Rights Reserved.
 */

//import com.xly.framework.config.MappedStatement;
//import org.apache.commons.dbcp2.BasicDataSource;
//import org.apache.shardingsphere.api.config.sharding.ShardingRuleConfiguration;
//import org.apache.shardingsphere.api.config.sharding.TableRuleConfiguration;
//import org.apache.shardingsphere.api.config.sharding.strategy.InlineShardingStrategyConfiguration;
//import org.apache.shardingsphere.shardingjdbc.api.ShardingDataSourceFactory;

import java.sql.SQLException;

/**
 * @author xuleyan
 * @version Index.java, v 0.1 2020-03-15 9:18 PM xuleyan
 */
public class Index {

    public static void main(String[] args) throws SQLException {

    }

//    private List<Object> handleResultSet(ResultSet rs) throws SQLException, NoSuchFieldException, IllegalAccessException, InstantiationException {
//
//        // 获取每一列的属性值，然后封装到结果对象中对应的属性名称上
//        List<Object> results = new ArrayList<>();
//
//        while (rs.next()) {
//            // 遍历一次是一行，也对应一个对象，利用反射new一个对象
//            Object result = resultTypeClass.newInstance();
//
//            // 要取得每一列的值，然后封装到结果对象中对应的属性名称上
//            ResultSetMetaData metaData = rs.getMetaData();
//            // 获取对象有几列
//            int columnCount = metaData.getColumnCount();
//            for (int i = 0; i < columnCount; i++) {
//                // 从metaData 中获取列的名称
//                String columnName = metaData.getColumnName(i + 1);
//                // 获取列的值
//                Object value = rs.getObject(i + 1);
//                // 列名和属性名完全一致
//                // 赋值到对应的结果集中
//                Field field = resultTypeClass.getDeclaredField(columnName);
//                field.setAccessible(true);
//                // 赋值
//                field.set(result, value);
//            }
//            results.add(result);
//        }
//
//        return results;
//    }
}