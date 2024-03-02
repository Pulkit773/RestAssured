package com.spotify.oauth2.tests;

import com.spotify.oauth2.api.StatusCode;
import com.spotify.oauth2.api.submit.LeadAPI;
import com.spotify.oauth2.pojo.LeadResponse.LeadResponse;
import com.spotify.oauth2.pojo.createLead.Lead;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.json.JSONException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.testng.annotations.Test;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.spotify.oauth2.utils.DbUtils.dbConnection;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class SubmitApp extends BaseTest {
    private JdbcTemplate jdbc;


    @Test
    public void shouldBeAbleToCreateLead() throws SQLException, ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        Lead requestCreateLead = leadBuilder();
        Response response = LeadAPI.put(requestCreateLead);
        assertThat(response.statusCode(), equalTo(StatusCode.CODE_200.getCode()));
        LeadResponse actualResponseLead = response.as(LeadResponse.class);
       /* String url = "jdbc:mysql://127.0.0.1:3306/growth?useSSL=false&allowPublicKeyRetrieval=true";
        String username = "growth-api";
        String password = "test123";
        String driver = "com.mysql.cj.jdbc.Driver";
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driver);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        jdbc = new JdbcTemplate(dataSource);
        String query = "Select * from growth.merchant_lead where email = 'pulkit.agrawal+4353746031@clover.com' order by created_time desc";
        Map<String, Object> abc  = jdbc.queryForMap(query);
        System.out.println(abc);*/

        ResultSet rs = dbConnection();
        List<LeadResponse> responseList = convertSQLResultSetToObject(rs, LeadResponse.class);
//        JSONArray json = new JSONArray();
//        ResultSetMetaData rsmd = rs.getMetaData();
//        while(rs.next()) {
//            int numColumns = rsmd.getColumnCount();
//            JSONObject obj = new JSONObject();
//            for (int i=1; i<=numColumns; i++) {
//                String column_name = rsmd.getColumnName(i);
//                obj.put(column_name, rs.getObject(column_name));
//            }
//            json.put(obj);
//        }



        /*QueryRunner run = new QueryRunner(dataSource);
        ResultSetHandler<LeadResponse> h = new BeanHandler<LeadResponse>(LeadResponse.class);
        LeadResponse p = run.query("Select * from growth.merchant_lead where email = 'pulkit.agrawal+testingtoday@clover.com' order by created_time desc", h);
        System.out.println(LeadResponse.class);*/
        //List<LeadResponse> responseList = convertSQLResultSetToObject(rs, LeadResponse.class);

    }

    public static <T> List<T> convertSQLResultSetToObject(ResultSet resultSet, Class<T> clazz) throws SQLException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
        }

        List<T> list = new ArrayList<>();
        while (resultSet.next()) {

            T dto = clazz.getConstructor().newInstance();

            for (Field field : fields) {
                String name = field.getName();

                try {
                    String value = resultSet.getString(name);
                    field.set(dto, field.getType().getConstructor(String.class).newInstance(value));
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            list.add(dto);

        }
        return list;
    }


    @Step
    public Lead leadBuilder() {
        Lead lead = new Lead();
        return lead;
    }

}

