package com.spotify.oauth2.tests;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spotify.oauth2.api.StatusCode;
import com.spotify.oauth2.api.submit.LeadAPI;
import com.spotify.oauth2.pojo.LeadResponse.LeadResponse;
import com.spotify.oauth2.pojo.LeadResponse.MarketingInfo;
import com.spotify.oauth2.pojo.LeadResponse.Order;
import com.spotify.oauth2.pojo.createLead.Lead;
import com.spotify.oauth2.utils.ObjectMapperHelper;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.annotations.Test;
import org.unitils.reflectionassert.ReflectionComparatorFactory;
import org.unitils.reflectionassert.ReflectionComparatorMode;
import org.unitils.reflectionassert.difference.Difference;
import org.unitils.reflectionassert.report.impl.DefaultDifferenceReport;

import java.sql.*;
import java.util.ArrayList;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class SubmitApp extends BaseTest {
    private Statement stmt = null;
    private Connection connection = null;

    @Test
    public void shouldBeAbleToCreateLead() throws Exception {
        String query = "Select * from growth.merchant_lead where email = 'pulkit.agrawal+testingtoday@lover.com' order by created_time desc";
        Lead requestCreateLead = leadBuilder();
        Response response = LeadAPI.put(requestCreateLead);
        assertThat(response.statusCode(), equalTo(StatusCode.CODE_200.getCode()));
        LeadResponse actualResponseLead = response.as(LeadResponse.class);
        JSONArray jsonArray = getDataRows(query);
        LeadResponse expectedResponse = getExpectedResponse(jsonArray);
        String report = compareObjects(expectedResponse, actualResponseLead);
        System.out.println(report);
    }

    @Step
    public Lead leadBuilder() {
        Lead lead = new Lead();
        return lead;
    }

    public static String compareObjects(Object obj1, Object obj2) {
        String report = null;
        try {
            Difference difference = ReflectionComparatorFactory
                    .createRefectionComparator(new ReflectionComparatorMode[]{ReflectionComparatorMode.LENIENT_ORDER,
                            ReflectionComparatorMode.IGNORE_DEFAULTS})
                    .getDifference(obj1, obj2);
            System.out.println("Difference object " + ObjectMapperHelper.objectToString(difference));
            if (difference != null) {
                DefaultDifferenceReport diffReport = new DefaultDifferenceReport();
                report = diffReport.createReport(difference);
            }
            return difference != null ? report : null;
        } catch (Exception e) {
            e.printStackTrace();

        }
        return report;
    }

    public LeadResponse getExpectedResponse(JSONArray jsonArray) throws JSONException, JsonProcessingException {
        LeadResponse expectedResponse = new LeadResponse();
        JSONObject jsonObject = jsonArray.getJSONObject(0);
        expectedResponse.setUuid(jsonObject.get("uuid").toString());
        expectedResponse.setEmail(jsonObject.get("email").toString());
        expectedResponse.setFirst_name(jsonObject.get("first_name").toString());
        expectedResponse.setLast_name(jsonObject.get("last_name").toString());
        expectedResponse.setBusiness_name(jsonObject.get("business_name").toString());
        expectedResponse.setPhone_number(jsonObject.get("phone_number").toString());
        expectedResponse.setMarketing_info(new ObjectMapper().readValue(jsonObject.get("marketing_info").toString(), MarketingInfo.class));
        expectedResponse.setReseller_uuid(jsonObject.get("reseller_uuid").toString());
        String orderStr = "{\"uuid\":\"" + jsonObject.get("order_uuid").toString() + "\"}";
        expectedResponse.setOrder(new ObjectMapper().readValue(orderStr, Order.class));
        expectedResponse.setSalesforce_id(jsonObject.get("salesforce_id").toString());
        expectedResponse.setSalesforce_owner_id(jsonObject.get("salesforce_owner_id").toString());
        expectedResponse.setChain_agent_id(jsonObject.get("chain_agent_id").toString());
        expectedResponse.setCl_analytics_id(jsonObject.get("cl_analytics_id").toString());
        return expectedResponse;
    }

    public JSONArray getDataRows(String query) throws Exception {

        try {
            String DB_URL = "jdbc:mysql://127.0.0.1:3306/growth?useSSL=false&allowPublicKeyRetrieval=true";
            String DB_USER = "growth-api";
            String DB_PASSWORD = "test123";
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            stmt = connection.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = stmt.executeQuery(query);
            ResultSetMetaData metadata = rs.getMetaData();
            int columnCount = metadata.getColumnCount();
            ArrayList<String> columns = new ArrayList<String>();
            for (int i = 1; i <= columnCount; i++) {
                String columnName = metadata.getColumnName(i);
                columns.add(columnName);
            }

            JSONArray resultArray = new JSONArray();
            while (rs.next()) {
                JSONObject rowObj = new JSONObject();
                for (String columnName : columns) {
                    String value = rs.getString(columnName);
                    rowObj.put(columnName, value);
                }
                resultArray.put(rowObj);
            }
            rs.close();
            return resultArray;
        } catch (Exception e) {
            throw e;
        } finally {
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se2) {
            }
            try {
                if (connection != null) connection.close();
            } catch (SQLException se) {
                throw se;
            }
        }
    }
}
    /*public static boolean comparePOJO(Object obj1, Object obj2) {
        return obj1.equals(obj2);
    }

    public static boolean deepCompare(Object o1, Object o2) {
        try {
            ByteArrayOutputStream baos1 = new ByteArrayOutputStream();
            ObjectOutputStream oos1 = new ObjectOutputStream(baos1);
            oos1.writeObject(o1);
            oos1.close();

            ByteArrayOutputStream baos2 = new ByteArrayOutputStream();
            ObjectOutputStream oos2 = new ObjectOutputStream(baos2);
            oos2.writeObject(o2);
            oos2.close();

            return Arrays.equals(baos1.toByteArray(), baos2.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> List<T> convertSQLResultSetToObject(ResultSet resultSet, Class<T> clazz) throws SQLException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        List<Field> fields = Arrays.asList(clazz.getDeclaredFields());
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
    }*/

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
        String query = "Select * from growth.merchant_lead where email = 'pulkit.agrawal+4353746031@lover.com' order by created_time desc";
        Map<String, Object> abc  = jdbc.queryForMap(query);
        System.out.println(abc);*/


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

//String result = compareObjects(expectedResponse, actualResponseLead);


        /*boolean result = actualResponseLead.equals(responseList.get(0));
        System.out.println(ReflectionToStringBuilder.toString(actualResponseLead));
        System.out.println(ReflectionToStringBuilder.toString(responseList.get(0)));
        System.out.println(result);*/

//
//    ObjectMapper mapper = new ObjectMapper();
//    JSONCompareResult result =
//            JSONCompare.compareJSON( response.toString(),  json.get(0).toString(), JSONCompareMode.LENIENT);

  /*QueryRunner run = new QueryRunner(dataSource);
        ResultSetHandler<LeadResponse> h = new BeanHandler<LeadResponse>(LeadResponse.class);
        LeadResponse p = run.query("Select * from growth.merchant_lead where email = 'pulkit.agrawal+testingtoday@lover.com' order by created_time desc", h);
        System.out.println(LeadResponse.class);*/
//List<LeadResponse> responseList = convertSQLResultSetToObject(rs, LeadResponse.class);

//JSONCompareResult result = JSONCompare.compareJSON(actualResponseLead, responseList.get(0), JSONCompareMode.LENIENT);
//boolean result =   actualResponseLead.equals(responseList.get(0));
//System.out.println(result);
//deepCompare(actualResponseLead,responseList.get(0));


// assertEquals(mapper.readTree(response),mapper.read);


