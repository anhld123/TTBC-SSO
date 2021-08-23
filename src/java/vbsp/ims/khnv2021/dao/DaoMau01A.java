/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.khnv2021.dao;


import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import vbsp.ims.dao.DaoConnect;
import vbsp.ims.khnv2021.model.Mau01AModel;
import vbsp.ims.log.CoreLogger;

/**
 *
 * @author HP
 */
public class DaoMau01A {
    
    public List<Mau01AModel> getExportData(String posCode, String communeCode, String subCommuneCode, String reportDate){
        List<Mau01AModel> lstData = new ArrayList<>();
        
        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call PK_KHNV_DATA_EXPORT.Export_01A(?, ?, ?, ?, ?)}";
            ResultSet cursor = null;

            try {
                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);                
                //Truyen vao username
                calstatement.setString(1, posCode);          
                calstatement.setString(2, communeCode);          
                calstatement.setString(3, subCommuneCode);          
                calstatement.setString(4, reportDate);                                         
                calstatement.registerOutParameter(5, oracle.jdbc.OracleTypes.CURSOR);
                //Thuc hien execute lay du lieu
                calstatement.execute();
                //Lay cursor ra resultset
                cursor = (ResultSet) calstatement.getObject(5);

                while (cursor.next()) {
                    Mau01AModel item = new Mau01AModel();
                    //item.reportDate = cursor.getString("TEN_THON");
                    item.subCommuneCode = cursor.getString("MA_THON");
                    item.subCommuneName = cursor.getString("TEN_THON");
                    item.order = cursor.getString("THUTU");
                    item.orderDisplay = cursor.getString("TT_HIENTHI");
                    item.code = cursor.getString("MACHITIEU");
                    item.name = cursor.getString("TENCHITIEU");
                    item.editFlag = cursor.getInt("THUCONG");
                    item.level = cursor.getInt("CAPCT");
                    item.levelCode = cursor.getString("CAPCT_MA");
                    item.printType = cursor.getInt("KIEUIN");
                    item.totalFlag = cursor.getInt("CONGCAP");                    
                    item.d1 = Double.parseDouble(getNumberValueString(cursor.getString("D1")));
                    item.d2 = Double.parseDouble(getNumberValueString(cursor.getString("D2")));
                    item.d3 = Double.parseDouble(getNumberValueString(cursor.getString("D3")));
                    item.d4 = Double.parseDouble(getNumberValueString(cursor.getString("D4")));
                    item.d5 = Double.parseDouble(getNumberValueString(cursor.getString("D5")));
                    item.d6 = Double.parseDouble(getNumberValueString(cursor.getString("D6")));
                    
                    lstData.add(item);
                }

                if (cursor != null) {
                    cursor.close();
                }
                
                if (calstatement != null) {
                    calstatement.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                System.err.println("Loi trong ham DaoMau01A.getExportData " + e.getMessage());
                CoreLogger.error(DaoMau01A.class.getCanonicalName() + " getExportData  -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham DaoMau01A.getExportData " + e.getMessage());
            CoreLogger.error(DaoMau01A.class.getCanonicalName() + " getExportData  -> " + e.getMessage());
        }
        return lstData;
    }
    
    public List<Mau01AModel> getExportData01(String posCode, String communeCode,  String reportDate){
        List<Mau01AModel> lstData = new ArrayList<>();
        
        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call PK_KHNV_DATA_EXPORT.EXPORT_01(?, ?, ?, ?)}";
            ResultSet cursor = null;

            try {
                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);                
                //Truyen vao username
                calstatement.setString(1, posCode);          
                calstatement.setString(2, communeCode);                                
                calstatement.setString(3, reportDate);                                         
                calstatement.registerOutParameter(4, oracle.jdbc.OracleTypes.CURSOR);
                //Thuc hien execute lay du lieu
                calstatement.execute();
                //Lay cursor ra resultset
                cursor = (ResultSet) calstatement.getObject(4);

                while (cursor.next()) {
                    Mau01AModel item = new Mau01AModel();
                    //item.reportDate = cursor.getString("TEN_THON");
                    item.subCommuneCode = cursor.getString("MA_THON");
                    item.subCommuneName = cursor.getString("TEN_THON");
                    item.order = cursor.getString("THUTU");
                    item.orderDisplay = cursor.getString("TT_HIENTHI");
                    item.code = cursor.getString("MACHITIEU");
                    item.name = cursor.getString("TENCHITIEU");
                    item.d1 = Double.parseDouble(getNumberValueString(cursor.getString("D1")));
                    item.d2 = Double.parseDouble(getNumberValueString(cursor.getString("D2")));
                    item.d3 = Double.parseDouble(getNumberValueString(cursor.getString("D3")));
                    item.d4 = Double.parseDouble(getNumberValueString(cursor.getString("D4")));
                    item.d5 = Double.parseDouble(getNumberValueString(cursor.getString("D5")));
                    item.d6 = Double.parseDouble(getNumberValueString(cursor.getString("D6")));
                    
                    lstData.add(item);
                }

                if (cursor != null) {
                    cursor.close();
                }
                
                if (calstatement != null) {
                    calstatement.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                System.err.println("Loi trong ham getExportData01.getExportData " + e.getMessage());
                CoreLogger.error(DaoMau01A.class.getCanonicalName() + " getExportData  -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham getExportData01.getExportData " + e.getMessage());
            CoreLogger.error(DaoMau01A.class.getCanonicalName() + " getExportData  -> " + e.getMessage());
        }
        return lstData;
    }
    
    
    public List<String> getDataSendKhnv(String type, String khoa, String mapgd, String nambc, String dotbc) throws SQLException {
        List<String> lstData = new ArrayList<>();
        DaoConnect daoconnect = new DaoConnect();
        Connection conn = null;
        conn = daoconnect.getConnect();
        CallableStatement calstatement = null;
        ResultSet reset = null;
//        try {
        //Khoi tao procedure cung voi tham so truyen vao la dau ?
        String strStoreproce = "{call VBSP_IMS_KTGS.SP_GET_DATA_KTGS_SYNC(?,?,?,?,?,?,?)}";
        try {
            //Khoi tao goi store
            calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            calstatement.registerOutParameter(5, oracle.jdbc.OracleTypes.NUMBER);
            calstatement.registerOutParameter(6, oracle.jdbc.OracleTypes.VARCHAR);
            calstatement.registerOutParameter(7, oracle.jdbc.OracleTypes.CURSOR);
            calstatement.setString(1, type);
            calstatement.setString(2, khoa);
            calstatement.setString(3, mapgd);
            calstatement.setString(4, dotbc);
            //Thuc hien execute lay du lieu
            calstatement.execute();
            //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
            int pn_err_cd = calstatement.getInt(5);
            //thu hien lay mo ta loi
            String strEdd_txt = calstatement.getString(6);
            //Lay cursor ra resultset
            reset = (ResultSet) calstatement.getObject(7);
            while (reset.next()) {

                lstData.add(reset.getString(1));
            }

        } catch (SQLException e) {
            System.err.print(e.getMessage());
            CoreLogger.error(this.getClass().getName() + " getDataSendKhnv -> " + e.getMessage());
            throw new SQLException(e);
        } finally {
            if (reset != null) {
                reset.close();
            }
            if (calstatement != null) {
                calstatement.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return lstData;
    }
    
    public List<String> getAllPosUser(String username) throws SQLException {
        List<String> lstData = new ArrayList<>();
        DaoConnect daoconnect = new DaoConnect();
        Connection conn = null;
        conn = daoconnect.getConnect();
        CallableStatement calstatement = null;
        ResultSet reset = null;
//        try {
        //Khoi tao procedure cung voi tham so truyen vao la dau ?
        String strStoreproce = "{call VBSP_IMS_KHNV2021.SP_LOAD_ALL_POS(?,?,?,?)}";
        try {
            //Khoi tao goi store
            calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            calstatement.registerOutParameter(2, oracle.jdbc.OracleTypes.NUMBER);
            calstatement.registerOutParameter(3, oracle.jdbc.OracleTypes.VARCHAR);
            calstatement.registerOutParameter(4, oracle.jdbc.OracleTypes.CURSOR);
            calstatement.setString(1, username);
            //Thuc hien execute lay du lieu
            calstatement.execute();
            //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
            int pn_err_cd = calstatement.getInt(2);
            //thu hien lay mo ta loi
            String strEdd_txt = calstatement.getString(3);
            //Lay cursor ra resultset
            reset = (ResultSet) calstatement.getObject(4);
            while (reset.next()) {

                lstData.add(reset.getString(1));
            }

        } catch (SQLException e) {
            System.err.print(e.getMessage());
            CoreLogger.error(this.getClass().getName() + " getAllPosUser -> " + e.getMessage());
            throw new SQLException(e);
        } finally {
            if (reset != null) {
                reset.close();
            }
            if (calstatement != null) {
                calstatement.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return lstData;
    }
        
    private String getNumberValueString(String value) {
        if (value == null || value.isEmpty()){
            return "0";
        } else {
            return value;
        }
    }
    
    public List<String> getDataSendKhnv(String type, String khoa, String mapgd, String ngaybc) throws SQLException {
        List<String> lstData = new ArrayList<>();
        DaoConnect daoconnect = new DaoConnect();
        Connection conn = null;
        conn = daoconnect.getConnect();
        CallableStatement calstatement = null;
        ResultSet reset = null;
//        try {
        //Khoi tao procedure cung voi tham so truyen vao la dau ?
        String strStoreproce = "{call VBSP_IMS_KHNV2021.SP_GET_DATA_KHNV_SYNC(?,?,?,?,?,?,?)}";
        try {
            //Khoi tao goi store
            calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            calstatement.registerOutParameter(5, oracle.jdbc.OracleTypes.NUMBER);
            calstatement.registerOutParameter(6, oracle.jdbc.OracleTypes.VARCHAR);
            calstatement.registerOutParameter(7, oracle.jdbc.OracleTypes.CURSOR);
            calstatement.setString(1, type);
            calstatement.setString(2, khoa);
            calstatement.setString(3, mapgd);
            calstatement.setString(4, ngaybc);
            
            //Thuc hien execute lay du lieu
            calstatement.execute();
            //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
            int pn_err_cd = calstatement.getInt(5);
            //thu hien lay mo ta loi
            String strEdd_txt = calstatement.getString(6);
            //Lay cursor ra resultset
            reset = (ResultSet) calstatement.getObject(7);
            while (reset.next()) {

                lstData.add(reset.getString(1));
            }

        } catch (SQLException e) {
            System.err.print(e.getMessage());
            CoreLogger.error(this.getClass().getName() + " getDataSendKhnv -> " + e.getMessage());
            throw new SQLException(e);
        } finally {
            if (reset != null) {
                reset.close();
            }
            if (calstatement != null) {
                calstatement.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return lstData;
    }
    
    public String getNgaybc(String nambc, String dotbc) {
       DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            
        String strQuery = "";

        // Connection connect = null;
        CallableStatement calstatement = null;
        //Khoi tao function se tra ra du lieu la kieu gi
        String strStoreproce = "{?=call VBSP_IMS_KHNV2021.F_GET_NGAYBC(?,?)}";

        try {
            //Khoi tao ket noi
            if (conn == null) {
                System.err.println("Khong the ket noi voi csdl ham getNgaybc");
                return strQuery;
            }
            //THuc hien goi ham trong oracle
            calstatement = conn.prepareCall(strStoreproce);
            //dang ky tham so tra du lieu ra la tham so thu nhat, kieu du lieu tra ra la number
            calstatement.registerOutParameter(1, oracle.jdbc.OracleTypes.CLOB);
            //Truyen tham so thu 2 vao la mang main_pos
            
            calstatement.setString(2, nambc);
            calstatement.setString(3, dotbc);
//            calstatement.setString(3, strModule_id);
//            calstatement.registerOutParameter(3, oracle.jdbc.OracleTypes.NUMBER);
//            calstatement.registerOutParameter(4, oracle.jdbc.OracleTypes.VARCHAR);
            calstatement.execute();
            //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
//            int pn_err_cd = calstatement.getInt(3);
////            //thu hien lay mo ta loi
//            String strEdd_txt = calstatement.getString(4);

            Clob clob = calstatement.getClob(1);
            //Lay cursor ra resultset
            //Get du lieu tra ra tham so thu 1
            if (clob != null) {
                strQuery = clob.getSubString(1, (int) clob.length());
                clob.free();

            }
//            System.err.println(calstatement.getString(1));
            if (calstatement != null) {
                calstatement.close();
            }

//            if (connect != null) {
//                connect.close();
//            }
        } catch (SQLException e) {
            System.err.print(e.getMessage());
            CoreLogger.error(this.getClass().getName() + " getNgaybc -> " + e.getMessage());
        }
        return strQuery;

    }
        
}
