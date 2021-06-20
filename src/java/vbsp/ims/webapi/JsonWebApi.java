/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.webapi;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import vbsp.ims.xml.ImsException;

/**
 *
 * @author BAOANH
 */
public class JsonWebApi {

    private String url;

    public JsonWebApi(String url) {
        String urltmp = url;

        if (urltmp.toLowerCase().endsWith("api") || urltmp.toLowerCase().endsWith("api/")) {
            if (url.endsWith("/")) {
                this.url = url;
            } else {
                this.url = url + "/";
            }
//            System.out.println("vbsp.ims.webapi.JsonWebApi.<init>() " + this.url);
        } else {
            if (url.endsWith("/")) {
                this.url = url + "api/";
            } else {
                this.url = url + "/api/";
            }
//            System.out.println("vbsp.ims.webapi.JsonWebApi.<init>() " + this.url);
        }
    }

    //<editor-fold defaultstate="collapsed" desc="cho webapi">
    /*
    Người tạo: Tungnv
    Ngày tạo: 10/4/2017
    Hàm này thực hiện lấy chuỗi json từ web api
     */
    /**
     *
     * @param urlpath tham số đường dẫn web api
     * @return trả về kiểu string của json
     * @throws Exception
     */
    public static String getJsonfromUrl(String urlpath) throws Exception {
        String jsonString = "";
        try {

            URL url = new URL(urlpath);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept-Charset", "UTF-8");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }
            Charset charset = Charset.forName("UTF8");
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(),charset));

            String output;
            while ((output = br.readLine()) != null) {
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

    /*
    hàm này đưa chuối json về kiểu list object
    kiểu Type truyền vào dưới dạng  Type type = new TypeToken<List<custInfo>>() {}.getType();
    với custInfo là object
     */
    /**
     *
     * @param jsonString chuối string json
     * @param type = new TypeToken<List<custInfo>>() {}.getType(); với custInfo
     * là object
     * @return List<Object>
     * @throws Exception
     */
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

//</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Xu lý goi web api">
    
    /**
     * Truy vấn tài khoản tide
     * @param posCode mã pgd
     * @param pTideAccount tài khoản galecy id tide của khách hàng
     * @return
     * @throws Exception
     * @since
     * http://localhost:28585/api/Loans/GetTranHisTide?posCode=000101&pTideAccount=4000001001000035
     */
    public List<TideInfo> GetTranHisTide(String posCode, String pTideAccount) throws Exception {
        List<TideInfo> tideInfoList = new ArrayList<TideInfo>();
        String urlapi = "";
        try {
            urlapi = url + "Loans/GetTranHisTide?posCode=" + posCode + "&pTideAccount=" + pTideAccount;
            
            String jsonString = getJsonfromUrl(urlapi);
            Type type = new TypeToken<List<TideInfo>>() {
            }.getType();
            
            List<Object> objList = convertJsonToObject(jsonString, type);
            
            for (Object obj : objList) {
                TideInfo value = (TideInfo) obj;
                tideInfoList.add(value);
            }
            
        } catch (Exception e) {
            throw new ImsException("GetTranHisTide " + urlapi, e);
        }
        return tideInfoList;
    }
    
    /**
     * Truy vấn thông tin tổ
     * @param posCode mã pgd
     * @param groupId mã tổ
     * @return
     * @throws Exception
     * @since
     * http://localhost:28585/api/Loans/GetGroupInfo?posCode=000301&groupId=0052754
     */
    public List<GroupInfo> GetGroupInfo(String posCode, String groupId) throws Exception {
        List<GroupInfo> groupInfoList = new ArrayList<GroupInfo>();
        String urlapi = "";
        try {
            urlapi = url + "Loans/GetGroupInfo?posCode=" + posCode + "&groupId=" + groupId;
            
            String jsonString = getJsonfromUrl(urlapi);
            Type type = new TypeToken<List<GroupInfo>>() {
            }.getType();
            
            List<Object> objList = convertJsonToObject(jsonString, type);
            
            for (Object obj : objList) {
                GroupInfo value = (GroupInfo) obj;
                groupInfoList.add(value);
            }
        } catch (Exception e) {
            throw new ImsException("GetGroupInfo " + urlapi, e);
        }
        return groupInfoList;
    }
    
    /**
     * Truy vấn thông tin tổ
     * @param posCode
     * @return
     * @throws Exception
     * @since http://localhost:28585/api/Loans/GetGroupInfoList?posCode=000301
     */
    public List<GroupInfo> GetGroupInfoList(String posCode) throws Exception {
        List<GroupInfo> groupInfoList = new ArrayList<GroupInfo>();
        String urlapi = "";
        try {
            urlapi = url + "Loans/GetGroupInfoList?posCode=" + posCode;
            String jsonString = getJsonfromUrl(urlapi);
            Type type = new TypeToken<List<GroupInfo>>() {
            }.getType();
            
            List<Object> objList = convertJsonToObject(jsonString, type);
            
            for (Object obj : objList) {
                GroupInfo value = (GroupInfo) obj;
                groupInfoList.add(value);
            }
        } catch (Exception e) {
            throw new ImsException("GetGroupInfoList " + urlapi, e);
        }
        return groupInfoList;
    }
    
    /**
     * Truy vấn thông tin khoản vay khách hàng
     * @param posCode
     * @param custId
     * @param typeFind co gia tri 1 truy vấn thông tin theo mã khách hàng ,2
     * truy vấn khách hàng theo PASS_NO (chứng minh thư) 3 truy vấn khách hàng
     * theo số điện thoại
     * @return
     * @throws Exception
     * @since
     * http://localhost:28585/api/Loans/GetCustInfoByCustId?posCode=000301&custId=7076879954&typeFind=1
     * @see
     * http://localhost:28585/api/Loans/GetCustInfoByCustId?posCode=000301&custId=030135403&typeFind=2
     * @see
     * http://localhost:28585/api/Loans/GetCustInfoByCustId?posCode=000301&custId=0936428410&typeFind=3
     */
    public List<Loan> GetCustInfoByCustId(String posCode, String custId, int typeFind) throws Exception {
        List<Loan> loanList = new ArrayList<Loan>();
        String urlapi = "";
        try {
            urlapi = url + "Loans/GetCustInfoByCustId?posCode=" + posCode + "&custId=" + custId + "&typeFind=" + typeFind;
            
            String jsonString = getJsonfromUrl(urlapi);
            Type type = new TypeToken<List<Loan>>() {
            }.getType();
            
            List<Object> objList = convertJsonToObject(jsonString, type);
            
            for (Object obj : objList) {
                Loan value = (Loan) obj;
                loanList.add(value);
            }
        } catch (Exception e) {
            throw new ImsException("GetCustInfoByCustId " + urlapi, e);
        }
        return loanList;
    }
    
    /**
     * Truy vấn lịch sử giao dịch của khoản vay
     * @param posCode
     * @param loanId
     * @return
     * @throws Exception
     * @since
     * http://localhost:28585/api/Loans/GetTranHisTide?posCode=000301&loanId=6600000708750068
     */
    public List<LoanTransaction> GetLoanTransaction(String posCode, String loanId) throws Exception {
        List<LoanTransaction> loanTransactionList = new ArrayList<LoanTransaction>();
        String urlapi = "";
        try {
            urlapi = url + "Loans/GetLoanTransaction?posCode=" + posCode + "&loanId=" + loanId;
            String jsonString = getJsonfromUrl(urlapi);
            Type type = new TypeToken<List<LoanTransaction>>() {
            }.getType();
            
            List<Object> objList = convertJsonToObject(jsonString, type);
            
            for (Object obj : objList) {
                LoanTransaction value = (LoanTransaction) obj;
                loanTransactionList.add(value);
            }
        } catch (Exception e) {
            throw new ImsException("GetLoanTransaction " + urlapi, e);
        }
        return loanTransactionList;
    }
    
    /**
     * Truy vấn thông tin điểm giao dịch xã
     * @param pTinhId
     * @param pHuyenId
     * @param pTenDGD
     * @param pNgayGD
     * @param pGioBD
     * @param pGioKT
     * @return
     * @throws Exception 
     */
    public List<TranPoint> GetTranPointInfors(int pTinhId, int pHuyenId, String pTenDGD, String pNgayGD, String pGioBD, String pGioKT)
            throws Exception {
        List<TranPoint> tranPointList = new ArrayList<TranPoint>();
        try {
            
        } catch (Exception e) {
            throw new ImsException("GetTranPointInfors ", e);
        }
        return tranPointList;
    }
//</editor-fold>

    public static void main(String[] args) {
        JsonWebApi json = new JsonWebApi("http://localhost:28585/api/");
        try {
//            List<TideInfo> tide = json.GetTranHisTide("000101", "4000001001000035");
//            for (TideInfo val : tide) {
//                System.err.println(val.getDiaChi());
//            }

            /*
            List<GroupInfo> groupList=json.GetGroupInfoList("000301");
            
             for (GroupInfo val : groupList) {
                System.err.println(val.getAc_No()+" -> "+val.getLeader_Cif_Name());
            }*/

 /*
//            List<Loan> groupList = json.GetCustInfoByCustId("000301", "7076879954", 1);

//            List<Loan> groupList = json.GetCustInfoByCustId("000301", "030135403", 2);
            List<Loan> groupList = json.GetCustInfoByCustId("000301", "0936428410", 3);
            for (Loan val : groupList) {
                System.err.println(val.getTenKH() + " -> " + val.getSoKU());
            }
             */
            List<LoanTransaction> groupList = json.GetLoanTransaction("000301", "6600000708750068");
            for (LoanTransaction val : groupList) {
                System.err.println(val.getCustName() + " -> " + val.getPrinOS());
            }
        } catch (Exception ex) {
            Logger.getLogger(JsonWebApi.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
