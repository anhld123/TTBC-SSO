/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.restapi;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.Invocation;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.ArrayList;
import vbsp.ims.bcqt.model.QT_DULIEU_NT;

/**
 *
 * @author HP
 */
public class DuLieuNTService extends ReportService {

    public ArrayList<DuLieuNTRow> getData(String key, String posCode, String posFlag, String reportDate) {
        org.glassfish.jersey.client.ClientConfig config = new org.glassfish.jersey.client.ClientConfig();
        Client client = ClientBuilder.newClient(config);
        WebTarget target = client.target(getBaseURI()).path("report-manual-data")
                .queryParam("key", key)
                .queryParam("posCode", posCode)
                .queryParam("posFlag", posFlag)
                .queryParam("reportDate", reportDate);

        Invocation.Builder invocationBuilder = target.request(MediaType.APPLICATION_XML);
        Response response = invocationBuilder.get();

        if (response.getStatus() == 200) {
            DuLieuNTResp dulieuNTResp = response.readEntity(DuLieuNTResp.class);
            ArrayList<DuLieuNTRow> listOfRow = dulieuNTResp.result;
            return listOfRow;
        } else {
            return null;
        }
    }
    
     public ArrayList<IntDeductionModel> getDataHTLS2021(String posCode, String reportDate, String program, String communeId, String groupId) {
        org.glassfish.jersey.client.ClientConfig config = new org.glassfish.jersey.client.ClientConfig();
        Client client = ClientBuilder.newClient(config);
        WebTarget target = client.target(getBaseURI()).path("int-deduction-data")
//                .queryParam("key", key)
                .queryParam("posCode", posCode)
                .queryParam("reportDate", reportDate)
                .queryParam("program", program)
                .queryParam("communeId", communeId)
                .queryParam("groupId", groupId);                

        Invocation.Builder invocationBuilder = target.request(MediaType.APPLICATION_XML);
        Response response = invocationBuilder.get();

        if (response.getStatus() == 200) {
            InDeductionResp dulieuNTResp = response.readEntity(InDeductionResp.class);
            ArrayList<IntDeductionModel> listOfRow = dulieuNTResp.result;
            return listOfRow;
        } else {
            return null;
        }
    }
     
     public ArrayList<InvestorModel> getDataNDT2021(String posCode, String reportDate) {
        org.glassfish.jersey.client.ClientConfig config = new org.glassfish.jersey.client.ClientConfig();
        Client client = ClientBuilder.newClient(config);
        WebTarget target = client.target(getBaseURI()).path("investor-deduction-data")
//                .queryParam("key", key)
                .queryParam("posCode", posCode)
                .queryParam("reportDate", reportDate);
//                .queryParam("program", program)
//                .queryParam("communeId", communeId)
//                .queryParam("groupId", groupId);                

        Invocation.Builder invocationBuilder = target.request(MediaType.APPLICATION_XML);
        Response response = invocationBuilder.get();

        if (response.getStatus() == 200) {
            InvestorResp dulieuNTResp = response.readEntity(InvestorResp.class);
            ArrayList<InvestorModel> listOfRow = dulieuNTResp.result;
            return listOfRow;
        } else {
            return null;
        }
    }
     
     public int updateData2021Ndt( String posCode,  String reportDate, String makerId,
            ArrayList<InvestorModel> data) {
        org.glassfish.jersey.client.ClientConfig config = new org.glassfish.jersey.client.ClientConfig();
        Client client = ClientBuilder.newClient(config);
        WebTarget target = client.target(getBaseURI()).path("investor-deduction-update")
//                .queryParam("key", key)
                .queryParam("posCode", posCode)
//                .queryParam("posFlag", posFlag)
                .queryParam("reportDate", reportDate)
                .queryParam("updateId", makerId == null || makerId == "" ? "" : makerId);
//                .queryParam("authoriseId", authoriseId == null || authoriseId == "" ? "" : authoriseId);
        Invocation.Builder invocationBuilder = target.request(MediaType.APPLICATION_JSON);
        
        String json = "";

        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(Include.NON_NULL);
        try {
            json = mapper.writeValueAsString(data);
            //System.out.println("ResultingJSONstring = " + json);            
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        //json = "[{\"key\": \"COVID_03\",\"code\": \"1004003452\", \"reportDate\": \"2021-06-30T00:00:00.000Z\",\"posCode\": \"000401\",\"posFlag\": \"S\",\"makerId\": \"trungnt\",\"makerDate\": \"2021-10-28T07:51:49.872Z\",\"d50\": \"1\",\"style\": 0}]";
        //json ="[{\"key\":\"COVID_03\",\"orderValue\":\"0\",\"code\":\"1004003452\",\"reportDate\":\"2021-06-30T00:00:00\",\"reportYear\":2021,\"posCode\":\"000401\",\"posFlag\":\"S\",\"branchCode\":\"000401\",\"makerId\":\"trungnt\",\"makerDate\":\"2021-10-29T15:04:56\",\"d1\":\"1004003452\",\"d50\":\"1\",\"manualFlag\":\"Y\",\"style\":0}]";
        
        Response response = invocationBuilder.post(Entity.entity(json, MediaType.APPLICATION_JSON));

        return response.getStatus();
    }

    public int updateData(String key, String posCode, String posFlag, String reportDate, String makerId, String authoriseId,
            ArrayList<DuLieuNTRow> data) {
        org.glassfish.jersey.client.ClientConfig config = new org.glassfish.jersey.client.ClientConfig();
        Client client = ClientBuilder.newClient(config);
        WebTarget target = client.target(getBaseURI()).path("update-manual-data")
                .queryParam("key", key)
                .queryParam("posCode", posCode)
                .queryParam("posFlag", posFlag)
                .queryParam("reportDate", reportDate)
                .queryParam("makerId", makerId == null || makerId == "" ? "" : makerId)
                .queryParam("authoriseId", authoriseId == null || authoriseId == "" ? "" : authoriseId);
        Invocation.Builder invocationBuilder = target.request(MediaType.APPLICATION_JSON);
        
        String json = "";

        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(Include.NON_NULL);
        try {
            json = mapper.writeValueAsString(data);
            //System.out.println("ResultingJSONstring = " + json);            
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        //json = "[{\"key\": \"COVID_03\",\"code\": \"1004003452\", \"reportDate\": \"2021-06-30T00:00:00.000Z\",\"posCode\": \"000401\",\"posFlag\": \"S\",\"makerId\": \"trungnt\",\"makerDate\": \"2021-10-28T07:51:49.872Z\",\"d50\": \"1\",\"style\": 0}]";
        //json ="[{\"key\":\"COVID_03\",\"orderValue\":\"0\",\"code\":\"1004003452\",\"reportDate\":\"2021-06-30T00:00:00\",\"reportYear\":2021,\"posCode\":\"000401\",\"posFlag\":\"S\",\"branchCode\":\"000401\",\"makerId\":\"trungnt\",\"makerDate\":\"2021-10-29T15:04:56\",\"d1\":\"1004003452\",\"d50\":\"1\",\"manualFlag\":\"Y\",\"style\":0}]";
        
        Response response = invocationBuilder.post(Entity.entity(json, MediaType.APPLICATION_JSON));

        return response.getStatus();
    }
    
    public int insertData(String makerId, String authoriseId, ArrayList<DuLieuNTRow> data) {
        org.glassfish.jersey.client.ClientConfig config = new org.glassfish.jersey.client.ClientConfig();
        Client client = ClientBuilder.newClient(config);
        WebTarget target = client.target(getBaseURI()).path("insert-manual-data")                
                .queryParam("makerId", makerId == null || makerId == "" ? "" : makerId)
                .queryParam("authoriseId", authoriseId == null || authoriseId == "" ? "" : authoriseId);
        Invocation.Builder invocationBuilder = target.request(MediaType.APPLICATION_JSON);
        //DuLieuNTPostModel postData = new DuLieuNTPostModel();
       // postData.setData(data);

        String json = "";

        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(Include.NON_NULL);
        try {
            json = mapper.writeValueAsString(data);            
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        
        Response response = invocationBuilder.post(Entity.entity(json, MediaType.APPLICATION_JSON));

        return response.getStatus();
    }

    public static void main(String[] args) {
        DuLieuNTService service = new DuLieuNTService();

        ArrayList<DuLieuNTRow> lstData = service.getData("COVID_03", "000401", "S", "20210630");
        System.out.println(lstData.size());

        ArrayList<DuLieuNTRow> lstUpdateDate = new ArrayList<>();
        DuLieuNTRow testItem = new DuLieuNTRow();
        testItem.setKey("COVID_03");
        testItem.setCode("1004003452");
        testItem.setReportDate("2021-06-30T00:00:00");
        testItem.setPosCode("000401");
        testItem.setPosFlag("S");
        testItem.setD1("1004003452");
        testItem.setD2("100");
        testItem.setReportYear(2021);
        lstUpdateDate.add(testItem );

        int status = service.updateData("COVID_03", "000401", "S", "20210630", "trungnt", "", lstUpdateDate);

        System.out.println(status);
    }

}
