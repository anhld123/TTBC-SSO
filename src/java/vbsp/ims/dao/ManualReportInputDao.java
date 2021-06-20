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
import java.util.List;
import vbsp.ims.log.CoreLogger;
import vbsp.ims.model.ManualInputObject;
import vbsp.ims.report.fast.ListValue;

/**
 *
 * @author Trung
 */
public class ManualReportInputDao {
    private static DaoConnect daoConnect;
    private static Connection conn;

    //--------------------------------------------------------------------------
    public ManualReportInputDao() {
        daoConnect = new DaoConnect();
        conn = daoConnect.getConnect();
    }

    public List<ManualInputObject> getManualReportList(String user_id,int report_grade) {
        List<ManualInputObject> reportList = new ArrayList<>();
        CallableStatement calstatement;
        String strStoreproce = "{call app_input_manual_rpt.sp_get_report_list(?,?,?,?)}";
        ResultSet reset;
        try {
            //Khoi tao goi store
            calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY,
                    ResultSet.CONCUR_READ_ONLY);
            calstatement.setString(1, user_id);
            calstatement.setInt(2, report_grade);
            calstatement.registerOutParameter(3, oracle.jdbc.OracleTypes.CURSOR);
            calstatement.registerOutParameter(4, oracle.jdbc.OracleTypes.CURSOR);
            calstatement.execute();
            reset = (ResultSet) calstatement.getObject(4);
            //Lay du lieu ra file name
            String code, groupCode,
             shortDesc, fullDesc, period, link, permit;            
            while (reset.next()) {
                code = reset.getString("DM_MABC");
                groupCode = reset.getString("DM_NHOMBC");
                shortDesc = reset.getString("DM_TENVT");
                fullDesc = reset.getString("DM_MOTA");
                period = reset.getString("DM_KYBC");
                link = reset.getString("DM_LINKBC");
                permit = reset.getString("PERMIT");
                reportList.add(new ManualInputObject(code, groupCode, shortDesc, fullDesc, period, 
                        link,permit));
            }
            calstatement.close();
        } catch (SQLException e) {
            System.err.println("Loi trong ham ManualReportInputDao.getManualReportList " + e.getMessage());
            CoreLogger.error(SysParamDao.class.getCanonicalName() + " getSysParams -> " + e.getMessage());
        }
        return reportList;
    }
    
    public List<ManualInputObject> filterManualReportList(String group_id,String user_id,int report_grade) {
        List<ManualInputObject> reportList = new ArrayList<>();
        CallableStatement calstatement;
        String strStoreproce = "{call app_input_manual_rpt.sp_filter_report_list(?,?,?,?)}";
        ResultSet reset;
        try {
            //Khoi tao goi store
            calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY,
                    ResultSet.CONCUR_READ_ONLY);
            calstatement.setString(1, group_id);
            calstatement.setString(2, user_id);
            calstatement.setInt(3, report_grade);
            calstatement.registerOutParameter(4, oracle.jdbc.OracleTypes.CURSOR);
            calstatement.execute();
            reset = (ResultSet) calstatement.getObject(4);
            //Lay du lieu ra file name
            String code, groupCode,
             shortDesc, fullDesc, period, link, permit;            
            while (reset.next()) {
                code = reset.getString("DM_MABC");
                groupCode = reset.getString("DM_NHOMBC");
                shortDesc = reset.getString("DM_TENVT");
                fullDesc = reset.getString("DM_MOTA");
                period = reset.getString("DM_KYBC");
                link = reset.getString("DM_LINKBC");
                permit = reset.getString("PERMIT");
                reportList.add(new ManualInputObject(code, groupCode, shortDesc, fullDesc, period, 
                        link,permit));
            }
            calstatement.close();
        } catch (SQLException e) {
            System.err.println("Loi trong ham ManualReportInputDao.filterManualReportList_Combo " + e.getMessage());
            CoreLogger.error(SysParamDao.class.getCanonicalName() + " filterManualReportList_Combo -> " + e.getMessage());
        }
        return reportList;
    }
    
    public List<ListValue> getManualGroupList_Combo(String user_id,int report_grade) {
        List<ListValue> groupList = new ArrayList<>();
        CallableStatement calstatement;
        String strStoreproce = "{call app_input_manual_rpt.sp_get_report_list(?,?,?,?)}";
        ResultSet reset;
        try {
            //Khoi tao goi store
            calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY,
                    ResultSet.CONCUR_READ_ONLY);
            calstatement.setString(1, user_id);
            calstatement.setInt(2, report_grade);
            calstatement.registerOutParameter(3, oracle.jdbc.OracleTypes.CURSOR);
            calstatement.registerOutParameter(4, oracle.jdbc.OracleTypes.CURSOR);
            calstatement.execute();
            reset = (ResultSet) calstatement.getObject(3);
            //Lay du lieu ra file name
            String code,shortDesc, fullDesc;            
            while (reset.next()) {
                code = reset.getString("DM_NHOMBC");                
                shortDesc = reset.getString("DM_TENVT");
                fullDesc = reset.getString("DM_MOTA");                
                groupList.add(new ListValue(code, shortDesc + "-" + fullDesc));
            }
            calstatement.close();
        } catch (SQLException e) {
            System.err.println("Loi trong ham ManualReportInputDao.getManualGroupList_Combo " + e.getMessage());
            CoreLogger.error(SysParamDao.class.getCanonicalName() + " getManualGroupList_Combo -> " + e.getMessage());
        }
        return groupList;
    }
    
    public List<ListValue> getManualReportList_Combo(String user_id,int report_grade) {
        List<ListValue> reportList = new ArrayList<>();
        CallableStatement calstatement;
        String strStoreproce = "{call app_input_manual_rpt.sp_get_report_list(?,?,?,?)}";
        ResultSet reset;
        try {
            //Khoi tao goi store
            calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY,
                    ResultSet.CONCUR_READ_ONLY);
            calstatement.setString(1, user_id);
            calstatement.setInt(2, report_grade);
            calstatement.registerOutParameter(3, oracle.jdbc.OracleTypes.CURSOR);
            calstatement.registerOutParameter(4, oracle.jdbc.OracleTypes.CURSOR);
            calstatement.execute();
            reset = (ResultSet) calstatement.getObject(4);
            //Lay du lieu ra file name
            String code,shortDesc, fullDesc;            
            while (reset.next()) {
                code = reset.getString("DM_MABC");                
                shortDesc = reset.getString("DM_TENVT");
                fullDesc = reset.getString("DM_MOTA");                
                reportList.add(new ListValue(code, shortDesc + "-" + fullDesc));
            }
            calstatement.close();
        } catch (SQLException e) {
            System.err.println("Loi trong ham ManualReportInputDao.getManualReportList_Combo " + e.getMessage());
            CoreLogger.error(SysParamDao.class.getCanonicalName() + " getManualReportList_Combo -> " + e.getMessage());
        }
        return reportList;
    }
    
    public List<ListValue> filterManualReportList_Combo(String group_id,String user_id,int report_grade) {
        List<ListValue> reportList = new ArrayList<>();
        CallableStatement calstatement;
        String strStoreproce = "{call app_input_manual_rpt.sp_filter_report_list(?,?,?,?)}";
        ResultSet reset;
        try {
            //Khoi tao goi store
            calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY,
                    ResultSet.CONCUR_READ_ONLY);
            calstatement.setString(1, group_id);
            calstatement.setString(2, user_id);
            calstatement.setInt(3, report_grade);
            calstatement.registerOutParameter(4, oracle.jdbc.OracleTypes.CURSOR);
            calstatement.execute();
            reset = (ResultSet) calstatement.getObject(4);
            //Lay du lieu ra file name
            String code,shortDesc, fullDesc;            
            while (reset.next()) {
                code = reset.getString("DM_MABC");                
                shortDesc = reset.getString("DM_TENVT");
                fullDesc = reset.getString("DM_MOTA");                
                reportList.add(new ListValue(code, shortDesc + "-" + fullDesc));
            }
            calstatement.close();
        } catch (SQLException e) {
            System.err.println("Loi trong ham ManualReportInputDao.filterManualReportList_Combo " 
                    + e.getMessage());
            CoreLogger.error(SysParamDao.class.getCanonicalName() 
                    + " filterManualReportList_Combo -> " + e.getMessage());
        }
        return reportList;
    }

    public List<ListValue> getManualReportList_Print_Combo(String userName, int report_grade) {
        List<ListValue> reportList = new ArrayList<>();
        CallableStatement calstatement;
        String strStoreproce = "{call app_input_manual_rpt.sp_get_report_list_print(?,?,?,?)}";
        ResultSet reset;
        try {
            //Khoi tao goi store
            calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY,
                    ResultSet.CONCUR_READ_ONLY);
            calstatement.setString(1, userName);
            calstatement.setInt(2, report_grade);
            calstatement.registerOutParameter(3, oracle.jdbc.OracleTypes.CURSOR);
            calstatement.registerOutParameter(4, oracle.jdbc.OracleTypes.CURSOR);
            calstatement.execute();
            reset = (ResultSet) calstatement.getObject(4);
            //Lay du lieu ra file name
            String code,shortDesc, fullDesc;            
            while (reset.next()) {
                code = reset.getString("DM_MABC");                
                shortDesc = reset.getString("DM_TENVT");
                fullDesc = reset.getString("DM_MOTA");                
                reportList.add(new ListValue(code, shortDesc + "-" + fullDesc));
            }
            calstatement.close();
        } catch (SQLException e) {
            System.err.println("Loi trong ham ManualReportInputDao.getManualReportList_Combo " 
                    + e.getMessage());
            CoreLogger.error(SysParamDao.class.getCanonicalName() 
                    + " getManualReportList_Combo -> " + e.getMessage());
        }
        return reportList;
    }

    public List<ListValue> filterManualReportList_Combo_Print(String groupId, String userName, int report_grade) {
        List<ListValue> reportList = new ArrayList<>();
        CallableStatement calstatement;
        String strStoreproce = "{call app_input_manual_rpt.sp_filter_report_list_print(?,?,?,?)}";
        ResultSet reset;
        try {
            //Khoi tao goi store
            calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY,
                    ResultSet.CONCUR_READ_ONLY);
            calstatement.setString(1, groupId);
            calstatement.setString(2, userName);
            calstatement.setInt(3, report_grade);
            calstatement.registerOutParameter(4, oracle.jdbc.OracleTypes.CURSOR);
            calstatement.execute();
            reset = (ResultSet) calstatement.getObject(4);
            //Lay du lieu ra file name
            String code,shortDesc, fullDesc;            
            while (reset.next()) {
                code = reset.getString("DM_MABC");                
                shortDesc = reset.getString("DM_TENVT");
                fullDesc = reset.getString("DM_MOTA");                
                reportList.add(new ListValue(code, shortDesc + "-" + fullDesc));
            }
            calstatement.close();
        } catch (SQLException e) {
            System.err.println("Loi trong ham ManualReportInputDao.filterManualReportList_Combo " + e.getMessage());
            CoreLogger.error(SysParamDao.class.getCanonicalName() + " filterManualReportList_Combo -> " + e.getMessage());
        }
        return reportList;
    }
    
    
    
    //PHAN DANH CHO CAP NHAT THONG TIN BO SUNG 
    public List<ManualInputObject> getAddInforManualList(String user_id,int report_grade) {
        List<ManualInputObject> reportList = new ArrayList<>();
        CallableStatement calstatement;
        String strStoreproce = "{call app_input_manual_rpt.sp_get_add_infor_by_manual(?,?,?)}";
        ResultSet reset;
        try {
            //Khoi tao goi store
            calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY,
                    ResultSet.CONCUR_READ_ONLY);
            calstatement.setString(1, user_id);
            calstatement.setInt(2, report_grade);
            calstatement.registerOutParameter(3, oracle.jdbc.OracleTypes.CURSOR);            
            calstatement.execute();
            reset = (ResultSet) calstatement.getObject(3);
            //Lay du lieu ra file name
            String code, groupCode,
             shortDesc, fullDesc, period, link, permit;            
            while (reset.next()) {
                code = reset.getString("CODE");
                groupCode = "";
                shortDesc = reset.getString("SHORTCUT");
                fullDesc = reset.getString("DESCRIPTION");
                period = "";
                link = reset.getString("ACTION_LINK");
                permit = reset.getString("PERMIT");
                reportList.add(new ManualInputObject(code, groupCode, shortDesc, fullDesc, period, 
                        link,permit));
            }
            calstatement.close();
        } catch (SQLException e) {
            System.err.println("Loi trong ham ManualReportInputDao.getAddInforManualList " + e.getMessage());
            CoreLogger.error(SysParamDao.class.getCanonicalName() + " getSysParams -> " + e.getMessage());
        }
        return reportList;
    }
    
}
