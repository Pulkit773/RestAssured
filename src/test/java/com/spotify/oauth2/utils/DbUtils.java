package com.spotify.oauth2.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.sql.*;
import java.util.List;
import java.util.Map;
public class DbUtils {

    private static final Logger logger = LoggerFactory.getLogger(DbUtils.class);
    private final JdbcTemplate jdbc;

    //public DbUtils(Map<String, Object> config) {
    public DbUtils() {
        /*String url = (String) config.get("url");
        String username = (String) config.get("username");
        String password = (String) config.get("password");
        String driver = (String) config.get("driverClassName");*/
        String url = "jdbc:mysql://127.0.0.1:3301/growth?useSSL=false&allowPublicKeyRetrieval=true";
        String username = "growth-api";
        String password = "test123";
        String driver = "com.mysql.cj.jdbc.Driver";
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driver);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        jdbc = new JdbcTemplate(dataSource);
        logger.info("init jdbc template: {}", url);
    }

    public Object readValue(String query) {
        return jdbc.queryForObject(query, Object.class);
    }

    public Map<String, Object> readRow(String query) {
        return jdbc.queryForMap(query);
    }

    public List<Map<String, Object>> readRows(String query) {
        return jdbc.queryForList(query);
    }

    // It will be removed in the coming days when API for updating online_order_provider_platform_order_fees table will be available.
    public void updateRows(final String sql) {
        jdbc.batchUpdate(new String[]{sql});
    }

    public static ResultSet dbConnection() throws ClassNotFoundException, SQLException {
        Connection con = null;
        Statement stmt;
        String DB_URL = "jdbc:mysql://127.0.0.1:3306/growth?useSSL=false&allowPublicKeyRetrieval=true";
        //String DB_URL = "jdbc:mysql://dev1-db01.dev.pdx10.clover.network:3306/meta?useSSL=false&allowPublicKeyRetrieval=true";
        String DB_USER = "growth-api";
        //String DB_USER = "remotereadonly";
        String DB_PASSWORD = "test123";
        String query = "Select * from growth.merchant_lead where email = 'pulkit.agrawal+testingtoday@clover.com' order by created_time desc";
        Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        stmt = con.createStatement();
        ResultSet res = stmt.executeQuery(query);
        return res;
    }
}



