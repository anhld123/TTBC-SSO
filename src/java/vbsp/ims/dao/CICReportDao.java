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
import vbsp.ims.model.CICLogRecord;
import vbsp.ims.model.CICRecord;

/**
 *
 * @author Trung
 */
public class CICReportDao {
    
    private final DaoConnect daoConnect;   
    
    public CICReportDao() {       
        daoConnect = new DaoConnect();
    }
    
    public List<CICRecord> getViewList(String p_report_dt){
        ArrayList<CICRecord> viewLst = new ArrayList<>();
        Connection conn = daoConnect.getConnect();        
        CallableStatement calstatement;
        String strStoreproce = "{call VBSP_RPT_CIC.p_app_view(?,?)}";
        ResultSet reset;
        try {            
            calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY,
                    ResultSet.CONCUR_READ_ONLY);            
            calstatement.setString(1, p_report_dt);                       
            calstatement.registerOutParameter(2, oracle.jdbc.OracleTypes.CURSOR);                       
            calstatement.execute();            
            reset = (ResultSet) calstatement.getObject(2);
            String l_pos_cd, l_descript, l_report_dt;
            Number l_cust_total,l_loan_total,l_prin_amt, l_col_total, l_file_total;
            while(reset.next()){
                l_pos_cd = reset.getString("POS_CD");
                l_descript = reset.getString("DESCRIPT");
                l_report_dt = reset.getString("REPORT_DT");
                l_cust_total = reset.getLong("CUST_TOTAL");
                l_loan_total = reset.getLong("LOAN_TOTAL");
                l_prin_amt = reset.getDouble("PRIN_AMT");
                l_col_total = reset.getLong("COL_TOTAL");
                l_file_total = reset.getLong("FILE_TOTAL");
                viewLst.add(new CICRecord(l_pos_cd,l_descript, l_report_dt,
                        l_cust_total,l_loan_total,l_prin_amt, l_col_total,l_file_total));
            }
            calstatement.close();
        } catch (SQLException e) {            
            CoreLogger.error("CICReportDao.getViewList()" + e.getMessage());
        }
        return viewLst;
    }
    
    public List<CICLogRecord> getViewLogList(String p_report_dt){
        ArrayList<CICLogRecord> viewLogLst = new ArrayList<>();
        Connection conn = daoConnect.getConnect();        
        CallableStatement calstatement;
        String strStoreproce = "{call VBSP_RPT_CIC.p_app_log_view(?,?)}";
        ResultSet reset;
        try {            
            calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY,
                    ResultSet.CONCUR_READ_ONLY);            
            calstatement.setString(1, p_report_dt);                       
            calstatement.registerOutParameter(2, oracle.jdbc.OracleTypes.CURSOR);                       
            calstatement.execute();            
            reset = (ResultSet) calstatement.getObject(2);
            String l_pos_cd, l_descript, l_report_dt,l_error_msg,l_rec_st;            
            Number l_record_total,l_error_cd ;
            while(reset.next()){
                l_pos_cd = reset.getString("POS_CD");
                l_descript = reset.getString("DESCRIPT");
                l_report_dt = reset.getString("REPORT_DT");
                l_error_cd = reset.getInt("ERROR_CD");
                l_error_msg = reset.getString("ERROR_MSG");
                l_record_total = reset.getInt("RECORD_TOTAL");
                l_rec_st = reset.getString("REC_ST");
                viewLogLst.add(new CICLogRecord(l_pos_cd,l_descript, l_report_dt,
                        l_error_cd,l_error_msg,l_record_total, l_rec_st));
            }
            calstatement.close();
        } catch (SQLException e) {            
            CoreLogger.error("CICReportDao.getViewLogList()" + e.getMessage());
        }
        return viewLogLst;
    }
    
    public List<CICRecord> getSearchList(String p_report_dt,String p_search_key,String p_search_type){
        ArrayList<CICRecord> viewLst = new ArrayList<>();
        Connection conn = daoConnect.getConnect();        
        CallableStatement calstatement;
        String strStoreproce = "{call VBSP_RPT_CIC.p_app_search(?,?,?,?,?)}";
        ResultSet reset;
        try {            
            calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY,
                    ResultSet.CONCUR_READ_ONLY);            
            calstatement.setString(1, p_report_dt);                       
            calstatement.setString(2, p_search_key);                       
            calstatement.setString(3, p_search_type);                       
            calstatement.setString(4, "02");                       
            calstatement.registerOutParameter(5, oracle.jdbc.OracleTypes.CURSOR);                       
            calstatement.execute();            
            reset = (ResultSet) calstatement.getObject(5);
            String l_pos_cd, l_descript, l_report_dt;
            Number l_cust_total,l_loan_total,l_prin_amt, l_col_total, l_file_total;
            while(reset.next()){
                l_pos_cd = reset.getString("POS_CD");
                l_descript = reset.getString("DESCRIPT");
                l_report_dt = reset.getString("REPORT_DT");
                l_cust_total = reset.getLong("CUST_TOTAL");
                l_loan_total = reset.getLong("LOAN_TOTAL");
                l_prin_amt = reset.getDouble("PRIN_AMT");
                l_col_total = reset.getLong("COL_TOTAL");
                l_file_total = reset.getLong("FILE_TOTAL");
                viewLst.add(new CICRecord(l_pos_cd,l_descript, l_report_dt,
                        l_cust_total,l_loan_total,l_prin_amt, l_col_total,l_file_total));
            }
            calstatement.close();
        } catch (SQLException e) {            
            CoreLogger.error("CICReportDao.getSearchList()" + e.getMessage());
        }
        return viewLst;
    }
    
    public List<CICLogRecord> getLogSearchList(String p_report_dt,String p_search_key,String p_search_type){
        ArrayList<CICLogRecord> viewLogLst = new ArrayList<>();
        Connection conn = daoConnect.getConnect();        
        CallableStatement calstatement;
        String strStoreproce = "{call VBSP_RPT_CIC.p_app_search(?,?,?,?,?)}";
        ResultSet reset;
        try {            
            calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY,
                    ResultSet.CONCUR_READ_ONLY);            
            calstatement.setString(1, p_report_dt);                       
            calstatement.setString(2, p_search_key);                       
            calstatement.setString(3, p_search_type);                       
            calstatement.setString(4, "01");                       
            calstatement.registerOutParameter(5, oracle.jdbc.OracleTypes.CURSOR);                       
            calstatement.execute();            
            reset = (ResultSet) calstatement.getObject(5);
            String l_pos_cd, l_descript, l_report_dt,l_error_msg,l_rec_st;            
            Number l_record_total,l_error_cd ;
            while(reset.next()){
                l_pos_cd = reset.getString("POS_CD");
                l_descript = reset.getString("DESCRIPT");
                l_report_dt = reset.getString("REPORT_DT");
                l_error_cd = reset.getInt("ERROR_CD");
                l_error_msg = reset.getString("ERROR_MSG");
                l_record_total = reset.getInt("RECORD_TOTAL");
                l_rec_st = reset.getString("REC_ST");
                viewLogLst.add(new CICLogRecord(l_pos_cd,l_descript, l_report_dt,
                        l_error_cd,l_error_msg,l_record_total, l_rec_st));
            }
            calstatement.close();
        } catch (SQLException e) {            
            CoreLogger.error("CICReportDao.getSearchList()" + e.getMessage());
        }
        return viewLogLst;
    }
    
    public String createJobs(String p_report_dt,String p_user_name){
        String error_msg;
        Connection conn = daoConnect.getConnect();        
        CallableStatement calstatement;
        String strStoreproce = "{call VBSP_RPT_CIC.p_app_cic_gen_job(?,?,?)}";
        try {            
            calstatement = conn.prepareCall(strStoreproce);            
            calstatement.setString(1, p_report_dt);     
            calstatement.setString(2, p_user_name);
            calstatement.registerOutParameter(3, oracle.jdbc.OracleTypes.VARCHAR);                       
            calstatement.executeUpdate();            
            error_msg = (String) calstatement.getObject(3);            
            calstatement.close();
        } catch (SQLException e) {  
            error_msg = "CICReportDao.createJobs()" + e.getMessage();
            CoreLogger.error("CICReportDao.createJobs()" + e.getMessage());
        }
        return error_msg;
    }
}
