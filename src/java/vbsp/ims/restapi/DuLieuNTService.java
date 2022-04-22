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
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import vbsp.ims.bcqt.model.QT_DULIEU_NT;
import vbsp.ims.dao.DaoDCPLNO;
import vbsp.ims.dao.DaoDcptNo;
import vbsp.ims.define.Define;
import vbsp.ims.khnv2021.dao.XDKHDao2021;
import vbsp.ims.model.DcplnModel;
import vbsp.ims.model.PLNO_DULIEU;

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
                .queryParam("communeId", communeId.equals("000000") ? "" : communeId)
                .queryParam("groupId", groupId.equals("0000000") ? "" : groupId);

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
    
    
    public ArrayList<LockSendModel> getDataLockSendS2021(String posCode, String flagReport, String reportDate) {
        org.glassfish.jersey.client.ClientConfig config = new org.glassfish.jersey.client.ClientConfig();
        Client client = ClientBuilder.newClient(config);
        WebTarget target = client.target(getBaseURI()).path("pos-send-status-data")
                //                .queryParam("key", key)
                .queryParam("posCode", posCode)
                .queryParam("posFlag", flagReport)
                .queryParam("reportDate", reportDate);

        Invocation.Builder invocationBuilder = target.request(MediaType.APPLICATION_XML);
        Response response = invocationBuilder.get();

        if (response.getStatus() == 200) {
            try {
                LockSenResp dulieuNTResp = response.readEntity(LockSenResp.class);
                ArrayList<LockSendModel> listOfRow = dulieuNTResp.result;
                return listOfRow;
            } catch (Exception e) {
                System.err.println("loi: " + e.getMessage());
                return null;
            }
        } else {
            return null;
        }
    }
    
    
    
    public ArrayList<LockSendModel> getDataLockSendNQ11CP(String posCode, String flagReport, String reportDate) {
        org.glassfish.jersey.client.ClientConfig config = new org.glassfish.jersey.client.ClientConfig();
        Client client = ClientBuilder.newClient(config);
        WebTarget target = client.target(getBaseURI()).path("subsidy-send-status-data")
                //                .queryParam("key", key)
                .queryParam("posCode", posCode)
                .queryParam("posFlag", flagReport)
                .queryParam("reportDate", reportDate);

        Invocation.Builder invocationBuilder = target.request(MediaType.APPLICATION_XML);
        Response response = invocationBuilder.get();

        if (response.getStatus() == 200) {
            try {
                LockSenResp dulieuNTResp = response.readEntity(LockSenResp.class);
                ArrayList<LockSendModel> listOfRow = dulieuNTResp.result;
                return listOfRow;
            } catch (Exception e) {
                System.err.println("loi: " + e.getMessage());
                return null;
            }
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

    public int updateData2021Ndt(String posCode, String reportDate, String makerId,
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
//     1: lưu xác nhận lại; 2: lưu phân loại ht
    public int updateData2021HTLS(String posCode, String reportDate, String makerId,
            ArrayList<IntDeductionModel> data) {
        org.glassfish.jersey.client.ClientConfig config = new org.glassfish.jersey.client.ClientConfig();
        Client client = ClientBuilder.newClient(config);
        WebTarget target = client.target(getBaseURI()).path("int-deduction-update-12")
                //                .queryParam("key", key)
                .queryParam("posCode", posCode)
                //                .queryParam("posFlag", posFlag)
                .queryParam("reportDate", reportDate)
                .queryParam("updateId", makerId == null || makerId == "" ? "" : makerId);
//                .queryParam("authoriseId", authoriseId == null || authoriseId == "" ? "" : authoriseId);
        Invocation.Builder invocationBuilder = target.request(MediaType.APPLICATION_JSON);

        String json = "";

        // chuan hoa du lieu truoc khi day len        
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
    
    //     1: lưu xác nhận lại; 2: lưu phân loại ht
    public int updateDataNQ11CP_001(String posCode, String reportDate, String makerId,
            ArrayList<NQ11cpModel> data) {
        org.glassfish.jersey.client.ClientConfig config = new org.glassfish.jersey.client.ClientConfig();
        Client client = ClientBuilder.newClient(config);
        WebTarget target = client.target(getBaseURI()).path("int-subsidy-update")
                //                .queryParam("key", key)
                .queryParam("posCode", posCode)
                //                .queryParam("posFlag", posFlag)
                .queryParam("reportDate", reportDate)
                .queryParam("updateId", makerId == null || makerId == "" ? "" : makerId);
//                .queryParam("authoriseId", authoriseId == null || authoriseId == "" ? "" : authoriseId);
        Invocation.Builder invocationBuilder = target.request(MediaType.APPLICATION_JSON);

        String json = "";

        // chuan hoa du lieu truoc khi day len        
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

    public int updateData2021HTLS_HT(String posCode, String reportDate, String makerId,
            ArrayList<IntDeductionModel> data) {
        org.glassfish.jersey.client.ClientConfig config = new org.glassfish.jersey.client.ClientConfig();
        Client client = ClientBuilder.newClient(config);
        WebTarget target = client.target(getBaseURI()).path("int-accounting-update")
                //                .queryParam("key", key)
                .queryParam("posCode", posCode)
                //                .queryParam("posFlag", posFlag)
                .queryParam("reportDate", reportDate)
                .queryParam("updateId", makerId == null || makerId == "" ? "" : makerId);
//                .queryParam("authoriseId", authoriseId == null || authoriseId == "" ? "" : authoriseId);
        Invocation.Builder invocationBuilder = target.request(MediaType.APPLICATION_JSON);

        String json = "";

        // chuan hoa du lieu truoc khi day len        
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

    public int updateData2021HTLS_ChotSL(String posCode, String reportDate, String makerId,
            ArrayList<UpdateLockModel> data) {
        org.glassfish.jersey.client.ClientConfig config = new org.glassfish.jersey.client.ClientConfig();
        Client client = ClientBuilder.newClient(config);
        WebTarget target = client.target(getBaseURI()).path("pos-send-status-update")
                //                .queryParam("key", key)
                .queryParam("mainPos", posCode)
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
    
    public int updateDataNQ11CP_ChotSL(String posCode, String reportDate, String makerId,
            ArrayList<UpdateLockModel> data) {
        org.glassfish.jersey.client.ClientConfig config = new org.glassfish.jersey.client.ClientConfig();
        Client client = ClientBuilder.newClient(config);
        WebTarget target = client.target(getBaseURI()).path("subsidy-send-status-update")
                //                .queryParam("key", key)
                .queryParam("mainPos", posCode)
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

    public int updateChotSL(String key, String posCode, String posFlag, String reportDate, String dataFlag, String makerId,
            ArrayList<UpdateLockModel> data) {
        org.glassfish.jersey.client.ClientConfig config = new org.glassfish.jersey.client.ClientConfig();
        Client client = ClientBuilder.newClient(config);
        WebTarget target = client.target(getBaseURI()).path("manual-data-send-status-update")
                .queryParam("key", key)
                .queryParam("posCode", posCode)
                .queryParam("posFlag", posFlag)
                .queryParam("reportDate", reportDate)
                .queryParam("dataFlag", dataFlag)
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

//    Kiểm tra trạng thái khóa nhập tay
    public ArrayList<LockSendModel> getDataLockManual(String key, String posCode, String flagReport, String reportDate) {
        org.glassfish.jersey.client.ClientConfig config = new org.glassfish.jersey.client.ClientConfig();
        Client client = ClientBuilder.newClient(config);
        WebTarget target = client.target(getBaseURI()).path("manual-data-send-status-data")
                .queryParam("key", key)
                .queryParam("posCode", posCode)
                .queryParam("posFlag", flagReport)
                .queryParam("reportDate", reportDate);

        Invocation.Builder invocationBuilder = target.request(MediaType.APPLICATION_XML);
        Response response = invocationBuilder.get();

        if (response.getStatus() == 200) {
            try {
                LockSenResp dulieuNTResp = response.readEntity(LockSenResp.class);
                ArrayList<LockSendModel> listOfRow = dulieuNTResp.result;
                return listOfRow;
            } catch (Exception e) {
                System.err.println("loi: " + e.getMessage());
                return null;
            }
        } else {
            return null;
        }
    }
    
    
   

    //    Khóa nhập tay
    public ArrayList<LockSendModel> getSetLockDataManual(String key, String posCode, String flagReport, String reportDate, String dataFlag, String updateId) {
        org.glassfish.jersey.client.ClientConfig config = new org.glassfish.jersey.client.ClientConfig();
        Client client = ClientBuilder.newClient(config);
        WebTarget target = client.target(getBaseURI()).path("manual-data-send-status-update")
                .queryParam("key", key)
                .queryParam("posCode", posCode)
                .queryParam("posFlag", flagReport)
                .queryParam("reportDate", reportDate)
                .queryParam("dataFlag", dataFlag)
                .queryParam("updateId", updateId);

        Invocation.Builder invocationBuilder = target.request(MediaType.APPLICATION_XML);
        Response response = invocationBuilder.get();

        if (response.getStatus() == 200) {
            try {
                LockSenResp dulieuNTResp = response.readEntity(LockSenResp.class);
                ArrayList<LockSendModel> listOfRow = dulieuNTResp.result;
                return listOfRow;
            } catch (Exception e) {
                System.err.println("loi: " + e.getMessage());
                return null;
            }
        } else {
            return null;
        }
    }

    public int updateLockManual(String key, String posCode, String posFlag, String reportDate, String dataFlag, String makerId,
            ArrayList<UpdateLockModel> data) {
        org.glassfish.jersey.client.ClientConfig config = new org.glassfish.jersey.client.ClientConfig();
        Client client = ClientBuilder.newClient(config);
        WebTarget target = client.target(getBaseURI()).path("manual-data-send-status-update")
                .queryParam("key", key)
                .queryParam("posCode", posCode)
                .queryParam("posFlag", posFlag)
                .queryParam("reportDate", reportDate)
                .queryParam("dataFlag", dataFlag)
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

   

    static String sendDataNV_QTByApi(List<QT_DULIEU_NT> lstDulieuNt, String file) {
        ArrayList<DuLieuNTRow> lstUpdateDate = new ArrayList<>();
        SimpleDateFormat sdf;
        sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        
        for (QT_DULIEU_NT tmp : lstDulieuNt) {
            DuLieuNTRow tempadd = new DuLieuNTRow();
            tempadd.setKey(tmp.getKHOA());
//                tempadd.setOrderValue(tmp.getTHUTU());
            tempadd.setOrderDescription(tmp.getTT_HIENTHI());
            tempadd.setCode(tmp.getMA());
            tempadd.setName(tmp.getTEN());

            String text = sdf.format(tmp.getNGAYBC());
            tempadd.setReportDate(text);
            tempadd.setReportYear(tmp.getNAMBC());
            tempadd.setPosCode(tmp.getMAPGD());

            tempadd.setPosFlag(tmp.getCO_TONGHOP());
            tempadd.setBranchCode(tmp.getMACN());
            tempadd.setMakerId(tmp.getNGUOI_NHAP());
//                tempadd.setMakerDate(tmp.getNGAY_NHAP());
//                tempadd.setAuthoriseId(tmp.getN());
            tempadd.setPosFlag(tmp.getCO_TONGHOP());
            tempadd.setD1(tmp.getD1());
            tempadd.setD2(tmp.getD2());
            tempadd.setD3(tmp.getD3());
            tempadd.setD4(tmp.getD4());
            tempadd.setD5(tmp.getD5());
            tempadd.setD6(tmp.getD6());
            tempadd.setD7(tmp.getD7());
            tempadd.setD8(tmp.getD8());
            tempadd.setD9(tmp.getD9());

            lstUpdateDate.add(tempadd);
        }
        DuLieuNTService service = new DuLieuNTService();
        service = new DuLieuNTService();
//        int status = service.insertData("insert", "system", lstUpdateDate);
        int status = service.updateData(Define.NV_QT, "001801", "S", "20211231", "quyennv", "quyen1", lstUpdateDate);
        if (status == 200) {
            return "";
        }

        return "";
    }
    
    
    public int summaryData(String posCode, String posFlag, String reportDate, String makerId) {
        org.glassfish.jersey.client.ClientConfig config = new org.glassfish.jersey.client.ClientConfig();
        Client client = ClientBuilder.newClient(config);
        WebTarget target = client.target(getBaseURI()).path("capital-source-summary")
//                .queryParam("key", key)
                .queryParam("posCode", posCode)
                .queryParam("posFlag", posFlag)
                .queryParam("reportDate", reportDate)
                .queryParam("updateId", makerId == null || makerId == "" ? "" : makerId);

        Invocation.Builder invocationBuilder = target.request(MediaType.APPLICATION_XML);
        Response response = invocationBuilder.get();

        return response.getStatus();
    }
    
     public String getTimeServer() {
        org.glassfish.jersey.client.ClientConfig config = new org.glassfish.jersey.client.ClientConfig();
        Client client = ClientBuilder.newClient(config);
        WebTarget target = client.target(getBaseURI()).path("system-date");

        Invocation.Builder invocationBuilder = target.request(MediaType.APPLICATION_XML);
        Response response = invocationBuilder.get();
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");  
        Date date;
        if (response.getStatus() == 200) {
            try {
                date = response.readEntity(Date.class);                
                String strDate = dateFormat.format(date);  
                return strDate;                
            } catch (Exception e) {
                System.err.println("loi: " + e.getMessage());                
                return dateFormat.format(new Date());  
            }
        } else {
            return dateFormat.format(new Date());  
        }
    }
     
    public int updatePLN(String posCode, String reportDate, String makerId,
            ArrayList<PlnApiModel> data) {
        org.glassfish.jersey.client.ClientConfig config = new org.glassfish.jersey.client.ClientConfig();
        Client client = ClientBuilder.newClient(config);
        WebTarget target = client.target(getBaseURI()).path("debt-classification-update")
                //                .queryParam("key", key)
                .queryParam("posCode", posCode)
                //                .queryParam("posFlag", posFlag)
                .queryParam("reportDate", reportDate)
                .queryParam("updateId", makerId == null || makerId == "" ? "" : makerId);
//                .queryParam("authoriseId", authoriseId == null || authoriseId == "" ? "" : authoriseId);
        Invocation.Builder invocationBuilder = target.request(MediaType.APPLICATION_JSON);

        String json = "";

        // chuan hoa du lieu truoc khi day len        
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
    
    public String sendDataPLN_ByApi(String posCode, String posFlag, String reportDate, List<DcplnModel> lstDulieuNt, String User) {
        ArrayList<PlnApiModel> lstUpdateDate = new ArrayList<>();
        SimpleDateFormat sdf;
        sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        DecimalFormat df = new DecimalFormat("#.##");
        
        for (DcplnModel tmp : lstDulieuNt) {
            PlnApiModel tempadd = new PlnApiModel();
            tempadd.setLoanId(tmp.getsSoku());
            tempadd.setMassOrgCode(tmp.getsDvut());
            tempadd.setGroupId(tmp.getsMato());
            tempadd.setCustomerId(tmp.getsMakh());

            tempadd.setAble_ToPay_Amt(new BigInteger(tmp.getsC_Kntn_Sodu()));
            tempadd.setUnAble_ToPay_Amt(new BigInteger(tmp.getsK_Kntn_Sodu()));
            tempadd.setUnAble_ToPay_Amt_01(new BigInteger(tmp.getsK_Kntn_Sd01()));
            tempadd.setUnAble_ToPay_Amt_02(new BigInteger(tmp.getsK_Kntn_Sd02()));
            tempadd.setUnAble_ToPay_Amt_03(new BigInteger(tmp.getsK_Kntn_Sd03()));
            tempadd.setUnAble_ToPay_Amt_04(new BigInteger(tmp.getsK_Kntn_Sd04()));
            tempadd.setUnAble_ToPay_Amt_05(new BigInteger(tmp.getsK_Kntn_Sd05()));
            tempadd.setUnAble_ToPay_Amt_06(new BigInteger(tmp.getsK_Kntn_Sd06()));
            tempadd.setUnAble_ToPay_Amt_07(new BigInteger(tmp.getsK_Kntn_Sd07()));
            tempadd.setUnAble_ToPay_Amt_08(new BigInteger(tmp.getsK_Kntn_Sd08()));
            tempadd.setUnAble_ToPay_Amt_09(new BigInteger(tmp.getsK_Kntn_Sd09()));
            tempadd.setUnAble_ToPay_Amt_10(new BigInteger(tmp.getsK_Kntn_Sd10()));
            tempadd.setUnAble_ToPay_Amt_11(new BigInteger(tmp.getsK_Kntn_Sd11()));

            tempadd.setDeviant_Amt(new BigInteger(tmp.getsNogoc_Clech()));
            tempadd.setDeviant_Int(new BigInteger(tmp.getsNolai_Clech()));

            
            
//            Thieu            
             
            
            tempadd.setUnAble_ToPay_Reason(tmp.getsNgnhan_Clech());
            tempadd.setCustRelationship(tmp.getsQuanhe_Kh());            
            tempadd.setStatus(tmp.getsTrangthai());
            
            tempadd.setUpdateBy(tmp.getsNguoi_Pln());
            tempadd.setUpdateTime(tmp.getsNgay_Pln());            
            tempadd.setReason_Deviant02(tmp.getsNgnhan_KckntnC2());

            lstUpdateDate.add(tempadd);
        }
        DuLieuNTService service = new DuLieuNTService();
        service = new DuLieuNTService();
//        int status = service.insertData("insert", "system", lstUpdateDate);
        int status = service.updatePLN(posCode, reportDate,User, lstUpdateDate);
        if (status == 200) {
            return "1";
        }
        else{
            return "0";
        }
    }
    
    public ArrayList<NQ11cpModel> getDataNQ11CP(String posCode, String reportDate, String program, String communeId, String groupId) {
        org.glassfish.jersey.client.ClientConfig config = new org.glassfish.jersey.client.ClientConfig();
        Client client = ClientBuilder.newClient(config);
        WebTarget target = client.target(getBaseURI()).path("int-subsidy-data")
                //                .queryParam("key", key)
                .queryParam("posCode", posCode)
                .queryParam("reportDate", reportDate)
                .queryParam("program", program)
                .queryParam("communeId", communeId.equals("000000") ? "" : communeId)
                .queryParam("groupId", groupId.equals("0000000") ? "" : groupId);

        Invocation.Builder invocationBuilder = target.request(MediaType.APPLICATION_XML);
        Response response = invocationBuilder.get();

        if (response.getStatus() == 200) {
            NQ11cpResp dulieuNTResp = response.readEntity(NQ11cpResp.class);
            ArrayList<NQ11cpModel> listOfRow = dulieuNTResp.result;
            return listOfRow;
        } else {
            return null;
        }
    }
    
    public ArrayList<DuLieuNTRow> getDataNQ11CP_01KH(String posCode, String reportDate, String flag) {
        org.glassfish.jersey.client.ClientConfig config = new org.glassfish.jersey.client.ClientConfig();
        Client client = ClientBuilder.newClient(config);
        WebTarget target = client.target(getBaseURI()).path("report-manual-data")
                //                .queryParam("key", key)
                .queryParam("key", "NQ11CP_01KH")
                .queryParam("posCode", posCode)
                .queryParam("posFlag", flag)
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
    
    public ArrayList<NQ11cpModel> getDataNQ11CP_02SK(String posCode, String reportDate) {
        org.glassfish.jersey.client.ClientConfig config = new org.glassfish.jersey.client.ClientConfig();
        Client client = ClientBuilder.newClient(config);
        WebTarget target = client.target(getBaseURI()).path("int-subsidy-data")
                //                .queryParam("key", key)
                .queryParam("posCode", posCode)
                .queryParam("reportDate", reportDate)
                .queryParam("program", "03")
                .queryParam("communeId", "060102")
                .queryParam("groupId", "0023222");

        Invocation.Builder invocationBuilder = target.request(MediaType.APPLICATION_XML);
        Response response = invocationBuilder.get();

        if (response.getStatus() == 200) {
            NQ11cpResp dulieuNTResp = response.readEntity(NQ11cpResp.class);
            ArrayList<NQ11cpModel> listOfRow = dulieuNTResp.result;
            return listOfRow;
        } else {
            return null;
        }
    }
    
     public static void main(String[] args) {
         DuLieuNTService service = new DuLieuNTService();
//        Date timeServer = service.getTimeServer();
        
//         Date date = Calendar.getInstance().getTime();  
//                DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");  
//                String strDate = dateFormat.format(timeServer);  
                System.out.println("Converted String: " + service.getTimeServer());  
//          String file = "NV_QT_000401_S_31122021_quyennv_6283";
//          
//          String[] array = file.split("_", -1);
//          String s1 = array[0];
//          String s2 = array[3];
//          String s3 = array[1];    
        
//        ArrayList<DuLieuNTRow> lstData = service.getData("COVID_03", "000401", "S", "20210630");
//        System.out.println(lstData.size());
//
//        ArrayList<DuLieuNTRow> lstUpdateDate = new ArrayList<>();
//        DuLieuNTRow testItem = new DuLieuNTRow();
//        testItem.setKey("COVID_03");
//        testItem.setCode("1004003452");
//        testItem.setReportDate("2021-06-30T00:00:00");
//        testItem.setPosCode("000401");
//        testItem.setPosFlag("S");
//        testItem.setD1("1004003452");
//        testItem.setD2("100");
//        testItem.setReportYear(2021);
//        lstUpdateDate.add(testItem );
//
//        int status = service.updateData("COVID_03", "000401", "S", "20210630", "trungnt", "", lstUpdateDate);
        List<PLNO_DULIEU> lstPLNo = new ArrayList<>();
        PLNO_DULIEU plno_dulieu = PLNO_DULIEU.newInstance();
        plno_dulieu.setsSoku("6600000715491945");
        plno_dulieu.setsSoku("6600000717477667");
        lstPLNo.add(plno_dulieu);
        
        List<DcplnModel> lstDulieuNt = new ArrayList<>();
        lstDulieuNt = new DaoDCPLNO().getDataPLN_Api("000401", "1", "31-dec-2021", lstPLNo);
        
        
////       
//        String kkk = sendDataNV_QTByApi(lstDulieuNt, "NV_QT_000703_S_20211231_HUYNQ01_3324");
//            try {
//             String kkk = sendDataPLN_ByApi("000401", "N", "20211231", lstDulieuNt,"quyennv");
//         } catch (Exception e) {
//                 System.err.println(e.getMessage());
//         }
        
//        System.out.println(status);
    }
}


