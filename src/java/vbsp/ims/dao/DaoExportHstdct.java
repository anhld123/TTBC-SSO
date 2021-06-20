/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vbsp.ims.dao;


import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import vbsp.ims.export.excel.ExportFileHstdCt;
import vbsp.ims.log.CoreLogger;

/**
 *
 * @author LION
 */
public class DaoExportHstdct {
    //Lay ra pos dua vao user
    public HashMap<String, ArrayList<String>> getPosExportHstdct(String strUserName)
    {
        HashMap<String, ArrayList<String>> hmPosExp = new HashMap<String, ArrayList<String>>();
        
        DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call VBSP_IMS_EXPORT_HSTDCT.sp_get_pos_export(?, ?, ?, ?)}";
            ResultSet reset = null;

            try {
                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                //Tham so thu nhat truyen vao la co lay theo pos hay main pos    
                calstatement.setString(1, strUserName);
                calstatement.registerOutParameter(2, oracle.jdbc.OracleTypes.NUMBER);
                calstatement.registerOutParameter(3, oracle.jdbc.OracleTypes.VARCHAR);
                calstatement.registerOutParameter(4, oracle.jdbc.OracleTypes.CURSOR);
                //Thuc hien execute lay du lieu
                calstatement.execute();
                //Lay cursor ra resultset
                reset = (ResultSet) calstatement.getObject(4);

                //Fill du lieu ra mang
                ArrayList<String> ArrlstPosCD = new ArrayList<String>();
                ArrayList<String> ArrlstPosDesc = new ArrayList<String>();
               // ArrayList<Object> ArrlstColumnDesc = new ArrayList<Object>();
                while (reset.next()) {

                    ArrlstPosCD.add(reset.getString("PO_KEY"));
                    ArrlstPosDesc.add(reset.getString("PO_DESC"));
                    //ArrlstColumnDesc.add(reset.getString("MCRF_COLUMN_DESC"));

                    //System.err.println(reset.getString("MCRF_COLUMN_DESC"));
                }
                hmPosExp.put("POS_CD", ArrlstPosCD);
                hmPosExp.put("POS_DESC", ArrlstPosDesc);
                
                if (reset != null) {
                    reset.close();
                }
                if (calstatement != null) {
                    calstatement.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                System.err.println("Loi trong ham getPosExportHstdct " + e.getMessage());
                CoreLogger.error(DaoExportHstdct.class.getCanonicalName() + " getPosExportHstdct -> " + e.getMessage());
            }
        
        return hmPosExp;
    }
    public LinkedHashMap<String, String> getModuleExportHstdct()
    {
        LinkedHashMap<String, String> hmPosExp = new LinkedHashMap<String, String>();
        
        DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call VBSP_IMS_EXPORT_HSTDCT.sp_get_module_export( ?, ?, ?)}";
            ResultSet reset = null;

            try {
                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                //Tham so thu nhat truyen vao la co lay theo pos hay main pos    
//                calstatement.setString(1, strUserName);
                calstatement.registerOutParameter(1, oracle.jdbc.OracleTypes.NUMBER);
                calstatement.registerOutParameter(2, oracle.jdbc.OracleTypes.VARCHAR);
                calstatement.registerOutParameter(3, oracle.jdbc.OracleTypes.CURSOR);
                //Thuc hien execute lay du lieu
                calstatement.execute();
                //Lay cursor ra resultset
                reset = (ResultSet) calstatement.getObject(3);

                //Fill du lieu ra mang
                while (reset.next()) {

                      hmPosExp.put(reset.getString("TABLE_NAME"), reset.getString("TABLE_DESC"));
                }
               
                
                if (reset != null) {
                    reset.close();
                }
                if (calstatement != null) {
                    calstatement.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                System.err.println("Loi trong ham getPosExportHstdct " + e.getMessage());
                CoreLogger.error(DaoExportHstdct.class.getCanonicalName() + " getPosExportHstdct -> " + e.getMessage());
            }
        
        return hmPosExp;
    }
    public String getDataExportFile(String strPosCd, String strExpDate, String strTableId, String strPathFile)
    {
//        procedure sp_get_export_data(pv_pos_cd in varchar2, pv_date_export in date, PV_TABLE_NAME IN VARCHAR2,
//                                 pn_err_cd out number, pv_err_msg out varchar2,csrdata out sys_refcursor, pv_file_name out varchar2);
        String strFullName="";        
         if (!strPathFile.endsWith("/") || !strPathFile.endsWith("\\")) {
            strPathFile += "/";
        }
        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call VBSP_IMS_EXPORT_HSTDCT.sp_get_export_data(?, ?, ?, ?,?,?,?)}";
            ResultSet reset = null;

            try {
                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                //Tham so thu nhat truyen vao la co lay theo pos hay main pos    
                calstatement.setString(1, strPosCd);
                calstatement.setString(2, strExpDate);
                calstatement.setString(3, strTableId);
                calstatement.registerOutParameter(4, oracle.jdbc.OracleTypes.VARCHAR);
                calstatement.registerOutParameter(5, oracle.jdbc.OracleTypes.VARCHAR);
                calstatement.registerOutParameter(6, oracle.jdbc.OracleTypes.CURSOR);
                calstatement.registerOutParameter(7, oracle.jdbc.OracleTypes.VARCHAR);
                //Thuc hien execute lay du lieu
                calstatement.execute();
                //Lay cursor ra resultset
                reset = (ResultSet) calstatement.getObject(6);                
                
                //Lay du lieu ra file name
                strFullName=strPathFile+calstatement.getString(7);
                ExportFileHstdCt export = new ExportFileHstdCt();
                export.ExportFileHstdCt(strFullName, reset);
                
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
                catch (SQLException e) {
                System.err.println("Loi trong ham getDataExportFile " + e.getMessage());
                CoreLogger.error(DaoExportHstdct.class.getCanonicalName() + " getDataExportFile -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham getDataExportFile " + e.getMessage());
            CoreLogger.error(DaoExportHstdct.class.getCanonicalName() + " getDataExportFile -> " + e.getMessage());

        }
        return strFullName;
    }
    
    public String getDataExportFile_CRT(String flag_rep, String reportDate,String reportType,String strPathFile)
    {
//        procedure sp_get_export_data(pv_pos_cd in varchar2, pv_date_export in date, PV_TABLE_NAME IN VARCHAR2,
//                                 pn_err_cd out number, pv_err_msg out varchar2,csrdata out sys_refcursor, pv_file_name out varchar2);
        String strFullName="";
        String strRow = "";
         if (!strPathFile.endsWith("/") || !strPathFile.endsWith("\\")) {
            strPathFile += "/";
        }
        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call EXPORTTXT_CRT(?, ?, ?, ?,?,?,?)}";
            ResultSet reset = null;

            try {
                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                //Tham so thu nhat truyen vao la co lay theo pos hay main pos    
                calstatement.setString(1, flag_rep);
                calstatement.setString(2, reportDate);
                calstatement.setString(3, reportType);
                calstatement.registerOutParameter(4, oracle.jdbc.OracleTypes.VARCHAR);
                calstatement.registerOutParameter(5, oracle.jdbc.OracleTypes.VARCHAR);
                calstatement.registerOutParameter(6, oracle.jdbc.OracleTypes.VARCHAR);
                calstatement.registerOutParameter(7, oracle.jdbc.OracleTypes.CURSOR);
                //Thuc hien execute lay du lieu
                calstatement.execute();
                //Lay cursor ra resultset
                reset = (ResultSet) calstatement.getObject(7);
                strRow = calstatement.getString(6);
                
                //Lay du lieu ra file name
                strFullName=strPathFile+calstatement.getString(5);
                ExportFileHstdCt export = new ExportFileHstdCt();
                export.ExportFileHstdCt_CRT(reportDate,reportType,strRow,strFullName, reset);
                
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
                catch (SQLException e) {
                System.err.println("Loi trong ham getDataExportFile " + e.getMessage());
                CoreLogger.error(DaoExportHstdct.class.getCanonicalName() + " getDataExportFile -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham getDataExportFile " + e.getMessage());
            CoreLogger.error(DaoExportHstdct.class.getCanonicalName() + " getDataExportFile -> " + e.getMessage());

        }
        return strFullName;
    }
}
