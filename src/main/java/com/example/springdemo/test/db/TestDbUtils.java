/**
 * fshows.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.example.springdemo.test.db;

/**
 * @author xuleyan
 * @version TestDbUtils.java, v 0.1 2019-03-22 9:55 AM xuleyan
 */
import com.example.springdemo.domain.User;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
//转换成list
public class TestDbUtils {
    public static void main(String[] args) {
        Connection conn = null;
        String url = "jdbc:mysql://localhost:3306/index";
        String jdbcDriver = "com.mysql.jdbc.Driver";
        String user = "root";
        String password = "";

        DbUtils.loadDriver(jdbcDriver);
        try {
            conn = DriverManager.getConnection(url, user, password);
            QueryRunner qr = new QueryRunner();
            List results = (List) qr.query(conn, "select id,username as `name` from tp_users ORDER BY id asc limit 10", new BeanListHandler(User.class));
            for (int i = 0; i < results.size(); i++) {
                User p = (User) results.get(i);
                System.out.println("id:" + p.getId() + ",name:" + p.getName());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtils.closeQuietly(conn);
        }
    }
}
