/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.restapi;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.Invocation;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.io.IOException;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import vbsp.ims.bcqt.model.QT_DULIEU_NT;
import vbsp.ims.define.Define;
import vbsp.ims.model.DcplnModel;

/**
 *
 * @author HP
 */
public class DuLieuNTService_Api1 extends ReportService_Api {

    public ArrayList<ListOfValue> getListOfValue(String key, String code) {
        org.glassfish.jersey.client.ClientConfig config = new org.glassfish.jersey.client.ClientConfig();
        Client client = ClientBuilder.newClient(config);
        WebTarget target = client.target(getBaseURI()).path("list-value")
                .queryParam("key", key)
                .queryParam("code", code);

        Invocation.Builder invocationBuilder = target.request(MediaType.APPLICATION_XML);
        Response response = invocationBuilder.get();

        if (response.getStatus() == 200) {
            ListOfValueResp dulieuNTResp = response.readEntity(ListOfValueResp.class);
            ArrayList<ListOfValue> listOfRow = dulieuNTResp.result;
            return listOfRow;
        } else {
            return null;
        }
    }
//
//    public ArrayList<ReportApi> Report_Api(String reportId, ReportApi lstParameter) {
//        org.glassfish.jersey.client.ClientConfig config = new org.glassfish.jersey.client.ClientConfig();
//        Client client = ClientBuilder.newClient(config);
//        WebTarget target;
//        target = client.target(getBaseURI()).path("view-report")
//                .queryParam("reportId", reportId);
//
//        Invocation.Builder invocationBuilder = target.request(MediaType.APPLICATION_JSON);
//        String json = "";
//        Response response = invocationBuilder.post(Entity.entity(json, MediaType.APPLICATION_JSON));
//
//        if (response.getStatus() == 200) {
//            String jsonResponse = response.readEntity(String.class);
//            ObjectMapper mapper = new ObjectMapper();
//            try {
//                ArrayList<ReportApi> listOfRow = mapper.readValue(jsonResponse, new TypeReference<ArrayList<ReportApi>>() {
//                });
//                return listOfRow;
//            } catch (IOException e) {
//                e.printStackTrace();
//                return null;
//            }
//        } else {
//            return null;
//        }
//    }
// quyennv 29/02/2024
    public ReportApi Report_Api(String reportId, ReportApi data) {
        org.glassfish.jersey.client.ClientConfig config = new org.glassfish.jersey.client.ClientConfig();
        Client client = ClientBuilder.newClient(config);
        WebTarget target = client.target(getBaseURI()).path("view-report")
                .queryParam("reportId", reportId);
        Invocation.Builder invocationBuilder = target.request(MediaType.APPLICATION_JSON);

        String json = "";

        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(Include.NON_NULL);
        try {
            json = mapper.writeValueAsString(data);
            System.out.println("ResultingJSONstring = " + json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        Response response = invocationBuilder.post(Entity.entity(json, MediaType.APPLICATION_JSON));
        if (response.getStatus() == 200) {
            DuLieuApiResp dulieuNTResp = response.readEntity(DuLieuApiResp.class);
            ReportApi listOfRow = dulieuNTResp.result;
            return listOfRow;
        } else {
            return null;
        }
//        System.out.println("Response code API: " + response.getStatus());
//        return response.getStatus();
    }

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Ví dụ">
    public static void main(String[] args) {
        DuLieuNTService_Api1 service = new DuLieuNTService_Api1();
        List<DuLieuNTRowX> data = new ArrayList<>();

    }

}
