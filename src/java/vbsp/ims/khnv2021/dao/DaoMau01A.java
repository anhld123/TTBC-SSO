/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.khnv2021.dao;


import java.sql.CallableStatement;
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
        
    private String getNumberValueString(String value) {
        if (value == null || value.isEmpty()){
            return "0";
        } else {
            return value;
        }
    }
}
