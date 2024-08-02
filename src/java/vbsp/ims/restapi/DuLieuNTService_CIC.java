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
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HP
 */
public class DuLieuNTService_CIC extends ReportService_ApiFileCic {

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

// quyennv 31/07/2024
    public ApiFileCic_Tmp getFileCic(String id) {
        org.glassfish.jersey.client.ClientConfig config = new org.glassfish.jersey.client.ClientConfig();
        Client client = ClientBuilder.newClient(config);
        WebTarget target = client.target(getBaseURI()).path("file/" + id)
                .queryParam("id", id);
//        System.out.println("Request URL: " + target.getUri());
        Invocation.Builder invocationBuilder = target.request(MediaType.APPLICATION_XML);
        Response response = invocationBuilder.get();

        if (response.getStatus() == 200) {
            ApiFileCic listDistrict = response.readEntity(ApiFileCic.class);
            ApiFileCic_Tmp listOfRow = listDistrict.result;
            return listOfRow;
        } else {
            return null;
        }
    }
    
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Ví dụ">
    public static void main(String[] args) {
        DuLieuNTService_CIC service = new DuLieuNTService_CIC();
        List<DuLieuNTRowX> data = new ArrayList<>();

    }

}
