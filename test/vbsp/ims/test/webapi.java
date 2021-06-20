/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.test;
//import java.n

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author BAOANH
 */
public class webapi {

    public static String getJsonfromUrl(String urlpath) throws Exception {
        String jsonString = "";
        try {

            URL url = new URL(urlpath);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            String output;
            System.out.println("Output from Server .... \n");
//            Gson gson =new Gson();
//            List<groupInfor> groupList = new ArrayList<groupInfor>();
//            Type type = new TypeToken<List<groupInfor>>() {}.getType();
            while ((output = br.readLine()) != null) {
//                groupList=gson.fromJson(output, type); 
                jsonString = jsonString + output;
            }

            conn.disconnect();

        } catch (MalformedURLException e) {
            e.printStackTrace();
            throw new Exception(e);

        } catch (IOException e) {

            e.printStackTrace();
            throw new Exception(e);

        }
        return jsonString;
    }

    public static void test() {
        try {

            URL url = new URL("http://localhost:28585/api/loans/GetGroupInfo?posCode=000407&groupId=0007154");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/xml");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            String output;
            System.out.println("Output from Server .... \n");
            Gson gson = new Gson();
            List<groupInfor> groupList = new ArrayList<groupInfor>();
            Type type = new TypeToken<List<groupInfor>>() {
            }.getType();
            while ((output = br.readLine()) != null) {
                groupList = gson.fromJson(output, type);
            }
            for (groupInfor value : groupList) {
                System.err.println(value.toString());
            }
            conn.disconnect();

        } catch (MalformedURLException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        }
    }

    private static List<Object> convertJsonToObject(String jsonString, Type type) throws Exception {
        List<Object> objectList = new ArrayList<Object>();
        try {
            Gson gson = new Gson();

            objectList = gson.fromJson(jsonString, type);
        } catch (Exception e) {
            throw new Exception(e);
        }
        return objectList;
    }

    public static void main(String[] args) {
        String url = "http://localhost:28585/api/Loans/GetCustInfoByCustId?posCode=000401&custId=1004005693";
        try {
            String jsonString = getJsonfromUrl(url);
            Type type = new TypeToken<List<custInfo>>() {
            }.getType();
            List<Object> custList = webapi.convertJsonToObject(jsonString, type);

            for (Object obj : custList) {
                custInfo value = (custInfo) obj;
                System.err.println(value.getDiaChi());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
