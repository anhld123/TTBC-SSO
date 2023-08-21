/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.dao;

import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import vbsp.ims.export.excel.ExportExcelFile;
import vbsp.ims.loadparams.Combo;
import vbsp.ims.loadparams.ReportParam;
import vbsp.ims.log.CoreLogger;
import vbsp.ims.query.ImsFillParaMeter;
import vbsp.ims.query.ImsPlSqlQuery;
import vbsp.ims.report.fast.ListValue;

/**
 *
 * @author LION
 */
public class DaoKt740 {

    public boolean saveReportQuery(String strUser_id, String strSave_id, String group_id, String strTitle, String strQuery, String sGradeReport) {
        boolean bSuccess = false;
        //System.err.println("Mang cot du lieu "+strSelectCol);
        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call vbsp_ims_rpt_kt740.sp_save_rpt_query(?, ?, ?, ?, ?, ?,?,?)}";
            ResultSet reset = null;

            try {
                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                //Tham so thu nhat module_id 
                calstatement.setString(1, strUser_id);
                calstatement.setString(2, strSave_id);
                calstatement.setString(3, group_id);
                //THam so thu 2 la mang cac cot da chon
                calstatement.setString(4, strTitle);
                //THam so thu 3 la mang cac cot sau dieu kien where
                calstatement.setString(5, strQuery);
                calstatement.setString(6, sGradeReport);
                calstatement.registerOutParameter(7, oracle.jdbc.OracleTypes.NUMBER);
                calstatement.registerOutParameter(8, oracle.jdbc.OracleTypes.VARCHAR);
                //calstatement.registerOutParameter(5, oracle.jdbc.OracleTypes.CURSOR);
                //Thuc hien execute lay du lieu
                calstatement.execute();
                //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
                int pn_err_cd = calstatement.getInt(7);
                //thu hien lay mo ta loi
                String strEdd_txt = calstatement.getString(8);
                //Lay cursor ra resultset

                if (calstatement != null) {
                    calstatement.close();
                }
                if (conn != null) {
                    conn.close();
                }
                bSuccess = true;
            } catch (SQLException e) {
                System.err.print(e.getMessage());
                CoreLogger.error(this.getClass().getName() + " saveReportQuery -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham saveReportQuery " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " saveReportQuery -> " + e.getMessage());
        }

        return bSuccess;
    }

    public void exportExcelQueryPlSql(Map parameters, String strSave_id, String strFileName) throws Exception {
        if (strSave_id == null || strSave_id.length() < 1) {
            return;
        }
        Map mapParameter = new HashMap();
        mapParameter.putAll(parameters);

        DaoConnect daoConnect = new DaoConnect();
        Connection connect = null;
        try {
            connect = daoConnect.getConnect();
            //connect=daoConnect.getConnect("10.63.8.59", "vbsprepo", 1521, "intellect", "intellect321#");
            //Lay ra title cho bao cao
            String strTitle = getTitleQuery(strSave_id, connect);
            //Lay ra truy van cho bao cao
            String strQuery = getQuery(strSave_id, connect);

            //neu co dau $ thi remove het di
            strQuery = strQuery.replace('$', ' ');

            ImsPlSqlQuery plsql = new ImsPlSqlQuery();

            //Lay ra tat ca cac tham so trong procedure
            List<String> lstPara = plsql.splitParaProcedure(strQuery);

            for (String strPara : lstPara) {
                if (!parameters.containsKey(strPara) || strPara.toUpperCase().contains("CURSOR")) {
                    mapParameter.put(strPara, ImsFillParaMeter.newInstance(strPara, null));
                }
            }
            if (connect == null) {
                System.err.println("Khong the ket noi voi csdl ham exportExcelQuery");
                CoreLogger.error(this.getClass().getName() + " Khong the ket noi voi csdl ham exportExcelQuery ");
                return;
            }

            plsql.setCollectedParameterNames(lstPara);
            plsql.setParameters(mapParameter);
            plsql.setQueryString(plsql.removeParaProcedure(strQuery));
            plsql.setConnection(connect);

            ResultSet reset = null;
            reset = plsql.createDatasource();

            ExportExcelFile exportExcel = new ExportExcelFile();
            exportExcel.ExportFileExcelQuery(reset, strTitle, strFileName);

            if (reset != null) {
                reset.close();
            }

            if (connect != null) {
                connect.close();
            }

            System.gc();
        } catch (SQLException e) {
            System.err.println("Loi trong ham exportExcelQuery " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " exportExcelQuery -> " + e.getMessage());
            System.gc();
        }
    }

    public List<ListValue> loadDescPara() {
        List<ListValue> lstDescPara = new ArrayList<ListValue>();

        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call vbsp_ims_rpt_kt740.sp_load_desc_para(?,?,?)}";
            ResultSet reset = null;

            try {
                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                //Tham so thu nhat truyen vao la co lay theo pos hay main pos   
//                calstatement.setString(1, strSaveId);
                calstatement.registerOutParameter(1, oracle.jdbc.OracleTypes.NUMBER);
                calstatement.registerOutParameter(2, oracle.jdbc.OracleTypes.VARCHAR);
                calstatement.registerOutParameter(3, oracle.jdbc.OracleTypes.CURSOR);
                //Thuc hien execute lay du lieu
                calstatement.execute();
                //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
                int pn_err_cd = calstatement.getInt(1);
                //thu hien lay mo ta loi
                String strEdd_txt = calstatement.getString(2);
                //Lay cursor ra resultset
                reset = (ResultSet) calstatement.getObject(3);
                //COLUMN_DESC
                while (reset.next()) {
                    lstDescPara.add(new ListValue(reset.getString("PARA_KEY"), reset.getString("PARA_DESC")));
//                    lstQuery.put("SRQ_QUERY",reset.getString("SRQ_QUERY"));   
//                     lstQuery.put("STRF_TITLE_NAME",reset.getString("STRF_TITLE_NAME"));  
//                     hmQuery.put("SRQ_QUERY",reset.getBlob("SRQ_QUERY").toString());   
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
                System.err.print(e.getMessage());
                CoreLogger.error(this.getClass().getName() + " getLoadAllQuery -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham getLoadAllQuery " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " getLoadAllQuery -> " + e.getMessage());
        }
        return lstDescPara;
    }

    public List<ListValue> getPosGeneralReport(Connection conn, String sPos_cd, String sPos_flag) {
        List<ListValue> lstPoscd = new ArrayList<ListValue>();

        try {
            // DaoConnect daoconnect = new DaoConnect();
            //Connection conn = null;
//            conn = daoconnect.getConnect();
            //conn=daoconnect.getConnect("10.63.8.59", "vbsprepo", 1521, "intellect", "intellect321#");
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call vbsp_ims_rpt_kt740.sp_get_pos_general_report(?,?,?,?,?)}";
            ResultSet reset = null;

            try {
                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                //Tham so thu nhat truyen vao la co lay theo pos hay main pos   
                calstatement.setString(1, sPos_cd);
                calstatement.setString(2, sPos_flag);
                calstatement.registerOutParameter(3, oracle.jdbc.OracleTypes.NUMBER);
                calstatement.registerOutParameter(4, oracle.jdbc.OracleTypes.VARCHAR);
                calstatement.registerOutParameter(5, oracle.jdbc.OracleTypes.CURSOR);
                //Thuc hien execute lay du lieu
                calstatement.execute();
                //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
                int pn_err_cd = calstatement.getInt(3);
                //thu hien lay mo ta loi
                String strEdd_txt = calstatement.getString(4);
                //Lay cursor ra resultset
                reset = (ResultSet) calstatement.getObject(5);
                //COLUMN_DESC
                while (reset.next()) {
                    lstPoscd.add(new ListValue(reset.getString("POS_CD"), reset.getString("POS_DESC")));
                }

                if (reset != null) {
                    reset.close();
                }
                if (calstatement != null) {
                    calstatement.close();
                }
//                if (conn != null) {
//                    conn.close();
//                }
            } catch (SQLException e) {
                System.err.print(e.getMessage());
                CoreLogger.error(this.getClass().getName() + " getLoadAllQuery -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham getLoadAllQuery " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " getLoadAllQuery -> " + e.getMessage());
        }
        return lstPoscd;
    }

    public boolean deleteReportQuery(String strSave_Id) {
        boolean bSuccess = false;
        //System.err.println("Mang cot du lieu "+strSelectCol);
        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call vbsp_ims_rpt_kt740.sp_delete_rpt(?, ?, ?)}";
            ResultSet reset = null;

            try {
                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                //Tham so thu nhat module_id 
                calstatement.setString(1, strSave_Id);
                //THam so thu 2 la mang cac cot da chon

                calstatement.registerOutParameter(2, oracle.jdbc.OracleTypes.NUMBER);
                calstatement.registerOutParameter(3, oracle.jdbc.OracleTypes.VARCHAR);
                //calstatement.registerOutParameter(5, oracle.jdbc.OracleTypes.CURSOR);
                //Thuc hien execute lay du lieu
                calstatement.execute();
                //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
                int pn_err_cd = calstatement.getInt(2);
                //thu hien lay mo ta loi
                String strEdd_txt = calstatement.getString(3);
                //Lay cursor ra resultset

                if (calstatement != null) {
                    calstatement.close();
                }
                if (conn != null) {
                    conn.close();
                }
                bSuccess = true;
            } catch (SQLException e) {
                System.err.print(e.getMessage());
                CoreLogger.error(this.getClass().getName() + " deleteReportQuery -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham deleteReportQuery " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " deleteReportQuery -> " + e.getMessage());
        }

        return bSuccess;
    }

    public HashMap<String, String> getEditQuery(String strSaveId) {
        HashMap<String, String> hmQuery = new HashMap<String, String>();
        if (strSaveId == null || strSaveId.isEmpty()) {
            return null;
        }
        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call vbsp_ims_rpt_kt740.sp_edit_rpt(?,?,?,?)}";
            ResultSet reset = null;

            try {
                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                //Tham so thu nhat truyen vao la co lay theo pos hay main pos   
                calstatement.setString(1, strSaveId);
                calstatement.registerOutParameter(2, oracle.jdbc.OracleTypes.NUMBER);
                calstatement.registerOutParameter(3, oracle.jdbc.OracleTypes.VARCHAR);
                calstatement.registerOutParameter(4, oracle.jdbc.OracleTypes.CURSOR);
                //Thuc hien execute lay du lieu
                calstatement.execute();
                //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
                int pn_err_cd = calstatement.getInt(2);
                //thu hien lay mo ta loi
                String strEdd_txt = calstatement.getString(3);
                //Lay cursor ra resultset
                reset = (ResultSet) calstatement.getObject(4);
                //COLUMN_DESC
                while (reset.next()) {
                    hmQuery.put("SRQ_SAVE_ID", reset.getString("SRQ_SAVE_ID"));
                    hmQuery.put("SRQ_QUERY", reset.getString("SRQ_QUERY"));
                    hmQuery.put("STRF_TITLE_NAME", reset.getString("STRF_TITLE_NAME"));
                    hmQuery.put("GRADE_REPORT", reset.getString("GRADE_REPORT"));
                    hmQuery.put("GROUP_ID", reset.getString("GROUP_ID").toString());
//                     hmQuery.put("SRQ_QUERY",reset.getBlob("SRQ_QUERY").toString());   
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
                System.err.print(e.getMessage());
                CoreLogger.error(this.getClass().getName() + " getColumnParaReportFast -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham getColumnParaReportFast " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " getColumnParaReportFast -> " + e.getMessage());
        }
        return hmQuery;
    }

    public List<ListValue> getLoadAllQuery(String sUserid, String sGroup_id, String sGrade) {
        List<ListValue> lstQuery = new ArrayList<ListValue>();

        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call vbsp_ims_rpt_kt740.sp_load_all_rpt_query(?,?,?,?,?,?)}";
            ResultSet reset = null;

            try {
                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                //Tham so thu nhat truyen vao la co lay theo pos hay main pos   
                calstatement.setString(1, sUserid);
                calstatement.setString(2, sGroup_id);
                calstatement.setString(3, sGrade);
                calstatement.registerOutParameter(4, oracle.jdbc.OracleTypes.NUMBER);
                calstatement.registerOutParameter(5, oracle.jdbc.OracleTypes.VARCHAR);
                calstatement.registerOutParameter(6, oracle.jdbc.OracleTypes.CURSOR);
                //Thuc hien execute lay du lieu
                calstatement.execute();
                //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
                int pn_err_cd = calstatement.getInt(4);
                //thu hien lay mo ta loi
                String strEdd_txt = calstatement.getString(5);
                //Lay cursor ra resultset
                reset = (ResultSet) calstatement.getObject(6);
                //COLUMN_DESC
                while (reset.next()) {
                    lstQuery.add(new ListValue(reset.getString("SRQ_SAVE_ID"), reset.getString("STRF_TITLE_NAME"), reset.getString("STT")));
//                    lstQuery.put("SRQ_QUERY",reset.getString("SRQ_QUERY"));   
//                     lstQuery.put("STRF_TITLE_NAME",reset.getString("STRF_TITLE_NAME"));  
//                     hmQuery.put("SRQ_QUERY",reset.getBlob("SRQ_QUERY").toString());   
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
                System.err.print(e.getMessage());
                CoreLogger.error(this.getClass().getName() + " getLoadAllQuery -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham getLoadAllQuery " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " getLoadAllQuery -> " + e.getMessage());
        }
        return lstQuery;
    }

    public HashMap<String, String> getQuerySave(String strSaveId) {
        HashMap<String, String> hmQuery = new HashMap<String, String>();
        if (strSaveId == null || strSaveId.isEmpty()) {
            return null;
        }

        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call vbsp_ims_rpt_kt740.sp_get_rpt_save(?,?,?,?)}";
            ResultSet reset = null;

            try {
                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                //Tham so thu nhat truyen vao la co lay theo pos hay main pos   
                calstatement.setString(1, strSaveId);
                calstatement.registerOutParameter(2, oracle.jdbc.OracleTypes.NUMBER);
                calstatement.registerOutParameter(3, oracle.jdbc.OracleTypes.VARCHAR);
                calstatement.registerOutParameter(4, oracle.jdbc.OracleTypes.CURSOR);
                //Thuc hien execute lay du lieu
                calstatement.execute();
                //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
                int pn_err_cd = calstatement.getInt(2);
                //thu hien lay mo ta loi
                String strEdd_txt = calstatement.getString(3);
                //Lay cursor ra resultset
                reset = (ResultSet) calstatement.getObject(4);
                //COLUMN_DESC
                while (reset.next()) {
                    hmQuery.put("SRQ_SAVE_ID", reset.getString("SRQ_SAVE_ID"));
                    hmQuery.put("SRQ_QUERY", reset.getString("SRQ_QUERY"));
//                     hmQuery.put("SRQ_QUERY",reset.getBlob("SRQ_QUERY").toString());   
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
                System.err.print(e.getMessage());
                CoreLogger.error(this.getClass().getName() + " getColumnParaReportFast -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham getColumnParaReportFast " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " getColumnParaReportFast -> " + e.getMessage());
        }
        return hmQuery;
    }

    public List<ReportParam> getReportParmams(String strSave_id, String strUsername) {
        ArrayList<ReportParam> report_param_list = new ArrayList<ReportParam>();

        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call vbsp_ims_rpt_kt740.sp_load_para_rpt(?, ?, ?, ?, ?, ?)}";
            ResultSet rscur_params = null;
            ResultSet rs_combo = null;

            try {
                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                //Tham so thu nhat truyen vao la co lay theo pos hay main pos    
                calstatement.setString(1, strSave_id);
                calstatement.setString(2, strUsername);
                calstatement.registerOutParameter(3, oracle.jdbc.OracleTypes.NUMBER);
                calstatement.registerOutParameter(4, oracle.jdbc.OracleTypes.VARCHAR);
                calstatement.registerOutParameter(5, oracle.jdbc.OracleTypes.CURSOR);
                calstatement.registerOutParameter(6, oracle.jdbc.OracleTypes.CURSOR);

                //Thuc hien execute lay du lieu
                calstatement.execute();
                //Lay cursor ra resultset
                rscur_params = (ResultSet) calstatement.getObject(5);
                rs_combo = (ResultSet) calstatement.getObject(6);

                //Do toan bo du lieu tu Resultset vao array list
                List<Combo> combo_list_all = fillResultSetComboToArray(rs_combo);

                while (rscur_params.next()) {
                    ReportParam rp = new ReportParam();

                    rp.setType(rscur_params.getString("PARA_TYPE"));
                    rp.setFieldName(rscur_params.getString("PARA_KEY"));
                    rp.setLabel(rscur_params.getString("PARA_DESC"));
                    rp.setOrderNumber(Integer.parseInt(rscur_params.getString("PARA_ORDER")));
//                    System.err.println(rscur_params.getString("PARA_DESC"));
                    //Neu la kieu list
                    if (rp.getType().equalsIgnoreCase("L")) {
                        ArrayList<Combo> combo_list = new ArrayList<Combo>(); //Loc cac cobo can thiet

                        for (Combo cb : combo_list_all) {
                            if (cb.getFieldName().equalsIgnoreCase(rp.getFieldName())) {
                                combo_list.add(cb);
                            }
                        }

                        rp.setComboList(combo_list);
                    }

                    report_param_list.add(rp);
                }

                if (rscur_params != null) {
                    rscur_params.close();
                }
                if (rs_combo != null) {
                    rs_combo.close();
                }
                if (calstatement != null) {
                    calstatement.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                System.err.println("Loi trong ham getReportParmams " + e.getMessage());
                CoreLogger.error(this.getClass().getName() + " getReportParmams  -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham getColumnReportFast " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " getReportParmams  -> " + e.getMessage());
        }
        return report_param_list;
    }

    public List<ReportParam> getReportViewParmams(String strQuery, String strUsername) {
        ArrayList<ReportParam> report_param_list = new ArrayList<ReportParam>();

        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call vbsp_ims_rpt_kt740.SP_LOAD_PARA_VIEW_RPT(?, ?, ?, ?, ?, ?)}";
            ResultSet rscur_params = null;
            ResultSet rs_combo = null;

            try {
                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                //Tham so thu nhat truyen vao la co lay theo pos hay main pos    
                calstatement.setString(1, strQuery);
                calstatement.setString(2, strUsername);
                calstatement.registerOutParameter(3, oracle.jdbc.OracleTypes.NUMBER);
                calstatement.registerOutParameter(4, oracle.jdbc.OracleTypes.VARCHAR);
                calstatement.registerOutParameter(5, oracle.jdbc.OracleTypes.CURSOR);
                calstatement.registerOutParameter(6, oracle.jdbc.OracleTypes.CURSOR);

                //Thuc hien execute lay du lieu
                calstatement.execute();
                //Lay cursor ra resultset
                rscur_params = (ResultSet) calstatement.getObject(5);
                rs_combo = (ResultSet) calstatement.getObject(6);

                //Do toan bo du lieu tu Resultset vao array list
                List<Combo> combo_list_all = fillResultSetComboToArray(rs_combo);

                while (rscur_params.next()) {
                    ReportParam rp = new ReportParam();

                    rp.setType(rscur_params.getString("PARA_TYPE"));
                    rp.setFieldName(rscur_params.getString("PARA_KEY"));
                    rp.setLabel(rscur_params.getString("PARA_DESC"));
                    rp.setOrderNumber(Integer.parseInt(rscur_params.getString("PARA_ORDER")));
//                    System.err.println(rscur_params.getString("PARA_DESC"));
                    //Neu la kieu list
                    if (rp.getType().equalsIgnoreCase("L")) {
                        ArrayList<Combo> combo_list = new ArrayList<Combo>(); //Loc cac cobo can thiet

                        for (Combo cb : combo_list_all) {
                            if (cb.getFieldName().equalsIgnoreCase(rp.getFieldName())) {
                                combo_list.add(cb);
                            }
                        }

                        rp.setComboList(combo_list);
                    }

                    report_param_list.add(rp);
                }

                if (rscur_params != null) {
                    rscur_params.close();
                }
                if (rs_combo != null) {
                    rs_combo.close();
                }
                if (calstatement != null) {
                    calstatement.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                System.err.println("Loi trong ham getReportParmams " + e.getMessage());
                CoreLogger.error(this.getClass().getName() + " getReportParmams  -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham getColumnReportFast " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " getReportParmams  -> " + e.getMessage());
        }
        return report_param_list;
    }

    //CuongBM: 21-Apr-14
    //Desc: Chuyen du lieu combo ra Array de xu ly
    private List<Combo> fillResultSetComboToArray(ResultSet rs_combo) throws SQLException {

        ArrayList<Combo> combo_list = new ArrayList<Combo>();
        try {
            while (rs_combo.next()) {
                Combo cb = new Combo();

                cb.setKey(rs_combo.getString("PARA_KEY"));
                cb.setValue(rs_combo.getString("PARA_DESC"));
                cb.setFieldName(rs_combo.getString("PARA_FIELD_NAME"));
//                System.err.println(rs_combo.getString("PARA_DESC"));
                combo_list.add(cb);
            }
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " fillResultSetComboToArray  -> " + e.getMessage());
        }
        return combo_list;
    }

    public String getTitleQuery(String strSave_id, Connection connect) {
        String strTitle = "";
        if (strSave_id.length() == 0 || strSave_id == null) {
            return null;
        }

        // Connection connect = null;
        CallableStatement calstatement = null;
        //Khoi tao function se tra ra du lieu la kieu gi
        String strStoreproce = "{?=call vbsp_ims_rpt_kt740.f_get_title_query(?,?,?)}";

        try {
            //Khoi tao ket noi
            if (connect == null) {
                System.err.println("Khong the ket noi voi csdl ham getTitleQuery");
                return strTitle;
            }
            //THuc hien goi ham trong oracle
            calstatement = connect.prepareCall(strStoreproce);
            //dang ky tham so tra du lieu ra la tham so thu nhat, kieu du lieu tra ra la number
            calstatement.registerOutParameter(1, oracle.jdbc.OracleTypes.VARCHAR);
            //Truyen tham so thu 2 vao la mang main_pos
            calstatement.setString(2, strSave_id);
//            calstatement.setString(3, strModule_id);
            calstatement.registerOutParameter(3, oracle.jdbc.OracleTypes.NUMBER);
            calstatement.registerOutParameter(4, oracle.jdbc.OracleTypes.VARCHAR);
            calstatement.execute();
            //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
            int pn_err_cd = calstatement.getInt(3);
//            //thu hien lay mo ta loi
            String strEdd_txt = calstatement.getString(4);
            //Lay cursor ra resultset
            //Get du lieu tra ra tham so thu 1
            strTitle = calstatement.getString(1);
//            System.err.println(calstatement.getString(1));
            if (calstatement != null) {
                calstatement.close();
            }
//            if (connect != null) {
//                connect.close();
//            }
        } catch (SQLException e) {
            System.err.print(e.getMessage());
            CoreLogger.error(this.getClass().getName() + " getTitleQuery -> " + e.getMessage());
        }
        return strTitle;

    }

    public String getQuery(String strSave_id, Connection connect) {
        String strQuery = "";
        if (strSave_id.length() == 0 || strSave_id == null) {
            return null;
        }

        // Connection connect = null;
        CallableStatement calstatement = null;
        //Khoi tao function se tra ra du lieu la kieu gi
        String strStoreproce = "{?=call vbsp_ims_rpt_kt740.f_get_query(?,?,?)}";

        try {
            //Khoi tao ket noi
            if (connect == null) {
                System.err.println("Khong the ket noi voi csdl ham getQuery");
                return strQuery;
            }
            //THuc hien goi ham trong oracle
            calstatement = connect.prepareCall(strStoreproce);
            //dang ky tham so tra du lieu ra la tham so thu nhat, kieu du lieu tra ra la number
            calstatement.registerOutParameter(1, oracle.jdbc.OracleTypes.CLOB);
            //Truyen tham so thu 2 vao la mang main_pos
            calstatement.setString(2, strSave_id);
//            calstatement.setString(3, strModule_id);
            calstatement.registerOutParameter(3, oracle.jdbc.OracleTypes.NUMBER);
            calstatement.registerOutParameter(4, oracle.jdbc.OracleTypes.VARCHAR);
            calstatement.execute();
            //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
            int pn_err_cd = calstatement.getInt(3);
//            //thu hien lay mo ta loi
            String strEdd_txt = calstatement.getString(4);

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
            CoreLogger.error(this.getClass().getName() + " getQuery -> " + e.getMessage());
        }
        return strQuery;

    }
    //Ham nay get data khi export du lieu. voi list la hashmap.
    //List nay chi chua 2 phan tu. phan tu 0 la kieu du lieu cua cot, 
    //phan tu thu 2 la du lieu cua cot.

    public boolean getDataExp(String sSave_id, Map<String, String> mapinPara,
            String sPos_cd, String stringPara_Poscd, String sPos_Flag, String strFileName) {
        List<HashMap<Integer, List<Object>>> lstDataExp = new ArrayList<HashMap<Integer, List<Object>>>();
        if (sSave_id == null || sSave_id.length() < 1) {
            return false;
        }
        DaoConnect daoConnect = new DaoConnect();
        Connection connect = null;
        try {
            connect = daoConnect.getConnect();
            //connect=daoConnect.getConnect("10.63.8.59", "vbsprepo", 1521, "intellect", "intellect321#");
            if (connect == null) {
                System.err.println("Khong the ket noi voi csdl ham exportExcelQuery");
                CoreLogger.error(this.getClass().getName() + " Khong the ket noi voi csdl ham exportExcelQuery ");
                return false;
            }

            //Lay ra title cho bao cao
            String strTitle = getTitleQuery(sSave_id, connect);
            //Lay ra truy van cho bao cao
            String strQuery = getQuery(sSave_id, connect);

            //Lay ra danh sach pos
            List<ListValue> lstPostCd = getPosGeneralReport(connect, sPos_cd, sPos_Flag);

            Integer nValue = 0;
            Integer nCountData = 0;
            //Hashmap luu tru du lieu
            HashMap<Integer, List<Object>> hmData = new HashMap<Integer, List<Object>>();
            //Hashmap luu tru lieu du lieu
            HashMap<Integer, List<Object>> hmMetaTable = new HashMap<Integer, List<Object>>();

            //Hashmap luu tru ten cot du lieu
            HashMap<Integer, List<Object>> hmNameCol = new HashMap<Integer, List<Object>>();

            for (ListValue valuePos : lstPostCd) {
                try {
                    Map<String, String> mapParaInTmp = new HashMap<String, String>();
                    //Dua tham so truyen vao
                    mapParaInTmp.putAll(mapinPara);
                    mapParaInTmp.put(stringPara_Poscd, valuePos.getsKey());
                    String sQueryTmp = strQuery;
                    //replare tham so cho gia tri trong truy van
                    for (String key : mapParaInTmp.keySet()) {
                        String value = mapParaInTmp.get(key).trim();
                        sQueryTmp = sQueryTmp.replaceAll(key, value);
                        //System.err.println("Value "+value+" Key "+key);
                    }

                    Statement stm = null;
                    stm = connect.createStatement();
                    if (stm == null) {
                        System.err.println("Khong tao duoc createStatement ham exportExcelQuery");
                        CoreLogger.error(this.getClass().getName() + " Khong tao duoc createStatement ham exportExcelQuery ");
                        return false;
                    }

                    ResultSet reset = null;
                    reset = stm.executeQuery(sQueryTmp);
                    //System.err.println(strQuery);
                    if (stm == null) {
                        System.err.println("Khong tao duoc executeQuery ham exportExcelQuery");
                        CoreLogger.error(this.getClass().getName() + " Khong tao duoc executeQuery ham exportExcelQuery ");
                        return false;
                    }
                    ResultSetMetaData resetMetaData = reset.getMetaData();
                    int nCountCol = resetMetaData.getColumnCount();
                    if (nValue == 0) {

                        List<Object> lsMetaData = new ArrayList<Object>();
                        List<Object> lstColName = new ArrayList<Object>();
                        for (int i = 1; i <= nCountCol; i++) {
                            lsMetaData.add(resetMetaData.getColumnTypeName(i));
                            lstColName.add(resetMetaData.getColumnName(i));
                        }

                        hmMetaTable.put(9999, lsMetaData);
                        hmNameCol.put(9999, lstColName);
                    }

                    while (reset.next()) {
                        List<Object> lstData = new ArrayList<Object>();
                        for (int i = 1; i <= nCountCol; i++) {
                            Object sValue = reset.getString(i);
                            if (sValue == null) {
                                lstData.add("");
                            } else {
                                lstData.add(reset.getString(i));
                            }
                        }
                        hmData.put(nCountData, lstData);
                        nCountData++;
                    }

                    if (reset != null) {
                        reset.close();
                    }
                    if (stm != null) {
                        stm.close();
                    }
                    nValue++;
                } catch (Exception e) {
                    System.err.println("Loi trong ham getDataExp " + valuePos.getsKey() + "~" + e.getMessage());
                    CoreLogger.error(this.getClass().getName() + " getDataExp -> " + valuePos.getsKey() + "~" + e.getMessage());
                }

            }
            lstDataExp.add(hmNameCol);
            lstDataExp.add(hmMetaTable);
            lstDataExp.add(hmData);
            //Lay ra column va desc cho column du lieu trong bao cao

            ExportExcelFile exportExcel = new ExportExcelFile();
            exportExcel.ExportFileExcelQueryFromList(lstDataExp, strTitle, strFileName);
            if (connect != null) {
                connect.close();
            }

            System.gc();
        } catch (SQLException e) {
            System.err.println("Loi trong ham getDataExp " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " getDataExp -> " + e.getMessage());
            System.gc();
            return false;
        }
        return true;
    }

    public void exportExcelQuery(Map<String, String> mapinPara, String strSave_id, String strFileName) {
        if (strSave_id == null || strSave_id.length() < 1) {
            return;
        }
        DaoConnect daoConnect = new DaoConnect();
        Connection connect = null;
        try {
            connect = daoConnect.getConnect();
            //connect=daoConnect.getConnect("10.63.8.59", "vbsprepo", 1521, "intellect", "intellect321#");
            //Lay ra title cho bao cao
            String strTitle = getTitleQuery(strSave_id, connect);
            //Lay ra truy van cho bao cao
            String strQuery = getQuery(strSave_id, connect);

            //Lay ra column va desc cho column du lieu trong bao cao
            //replare tham so cho gia tri trong truy van
            Set<String> keys = mapinPara.keySet();
            for (String key : keys) {
                String value = mapinPara.get(key).trim();
                strQuery = strQuery.replaceAll(key, value);
            }
            if (connect == null) {
                System.err.println("Khong the ket noi voi csdl ham exportExcelQuery");
                CoreLogger.error(this.getClass().getName() + " Khong the ket noi voi csdl ham exportExcelQuery ");
                return;
            }
            Statement stm = null;
            stm = connect.createStatement();
            if (stm == null) {
                System.err.println("Khong tao duoc createStatement ham exportExcelQuery");
                CoreLogger.error(this.getClass().getName() + " Khong tao duoc createStatement ham exportExcelQuery ");
                return;
            }
            ResultSet reset = null;
            reset = stm.executeQuery(strQuery);
            //System.err.println(strQuery);
            if (stm == null) {
                System.err.println("Khong tao duoc executeQuery ham exportExcelQuery");
                CoreLogger.error(this.getClass().getName() + " Khong tao duoc executeQuery ham exportExcelQuery ");
                return;
            }
            ExportExcelFile exportExcel = new ExportExcelFile();
            exportExcel.ExportFileExcelQuery(reset, strTitle, strFileName);

            if (reset != null) {
                reset.close();
            }
            if (stm != null) {
                stm.close();
            }

            if (connect != null) {
                connect.close();
            }

            System.gc();
        } catch (SQLException e) {
            System.err.println("Loi trong ham exportExcelQuery " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " exportExcelQuery -> " + e.getMessage());
            System.gc();
        }
    }

    public List<ListValue> ViewReportQuery(Map<String, String> mapinPara, String strTitle, String strQuery) throws SQLException, ParseException {
        if (strQuery == null || strQuery.length() < 1) {
            return null;
        }

        strQuery = "select * from (" + strQuery + ") where rownum<11";
        List<ListValue> lstTableData = new ArrayList<ListValue>();

        DaoConnect daoConnect = new DaoConnect();
        Connection connect = null;
//        try {
        connect = daoConnect.getConnect();

        //Lay ra column va desc cho column du lieu trong bao cao
        //replare tham so cho gia tri trong truy van
        Set<String> keys = mapinPara.keySet();
        for (String key : keys) {
            String value = mapinPara.get(key).trim();
            strQuery = strQuery.replaceAll(key, value);
        }
        if (connect == null) {
            System.err.println("Khong the ket noi voi csdl ham exportExcelQuery");
            CoreLogger.error(this.getClass().getName() + " Khong the ket noi voi csdl ham exportExcelQuery ");
            return null;
        }
        Statement stm = null;
        stm = connect.createStatement();
        if (stm == null) {
            System.err.println("Khong tao duoc createStatement ham exportExcelQuery");
            CoreLogger.error(this.getClass().getName() + " Khong tao duoc createStatement ham exportExcelQuery ");
            return null;
        }
        ResultSet reset = null;
        reset = stm.executeQuery(strQuery);
        //System.err.println(strQuery);
        if (stm == null) {
            System.err.println("Khong tao duoc executeQuery ham exportExcelQuery");
            CoreLogger.error(this.getClass().getName() + " Khong tao duoc executeQuery ham exportExcelQuery ");
            return null;
        }

        ResultSetMetaData resetMetaData = reset.getMetaData();
        int ncolNum = resetMetaData.getColumnCount();
        String strTableColName = "<tr>";
        for (int col = 0; col < ncolNum; col++) {
            strTableColName = strTableColName + "<th>" + resetMetaData.getColumnName(col + 1) + "</th>";
//                lstTableData.add(new ListValue(resetMetaData.getColumnName(col + 1)));
        }
        lstTableData.add(new ListValue(strTableColName + "</tr>"));

        while (reset.next()) {
            String strTableData = "<tr>";
            for (int i = 0; i < ncolNum; i++) {
                String strDataType = resetMetaData.getColumnTypeName(i + 1);
                String strValue = reset.getString(i + 1);
                if (strDataType.equals("DATE")) {
                    //dd/MM/yyyy 
                    //SimpleDateFormat datetemp = new SimpleDateFormat("yyyy-MM-dd");
                    if (strValue == null || strValue.isEmpty()) {
                        strTableData = strTableData + "<td>" + strValue + "</td>";
                        continue;
                    }

                    Date sdf = new SimpleDateFormat("yyyy-MM-dd").parse(strValue);
                    //Date cellValue = datetemp.parse(strValue);
                    strValue = new SimpleDateFormat("dd/MM/yyyy").format(sdf);
                }
                strTableData = strTableData + "<td>" + strValue + "</td>";
            }
            strTableData = strTableData.replaceAll("null", "");
            lstTableData.add(new ListValue(strTableData + "</tr>"));
        }
//            ExportExcelFile exportExcel = new ExportExcelFile();
//            exportExcel.ExportFileExcelQuery(reset, strTitle, strFileName);

        if (reset != null) {
            reset.close();
        }
        if (stm != null) {
            stm.close();
        }

        if (connect != null) {
            connect.close();
        }

        System.gc();
//        } catch (SQLException e) {
//            System.err.println("Loi trong ham exportExcelQuery " + e.getMessage());
//            CoreLogger.error(this.getClass().getName() + " exportExcelQuery -> " + e.getMessage());
//            System.gc();
//        }
        return lstTableData;
    }

    public String getUserCreateReport(String sSave_id) {
        String sUserName = "";
        try {
            Connection connect = null;
            CallableStatement calstatement = null;
            connect = new DaoConnect().getConnect();

            //Khoi tao function se tra ra du lieu la kieu gi
            String strStoreproce = "{?=call vbsp_ims_rpt_kt740.f_get_user_create_report(?,?,?)}";
            //Khoi tao ket noi
            if (connect == null) {
                System.err.println("Khong the ket noi voi csdl ham getQuery");
                return sUserName;
            }
            //THuc hien goi ham trong oracle
            calstatement = connect.prepareCall(strStoreproce);
            //dang ky tham so tra du lieu ra la tham so thu nhat, kieu du lieu tra ra la number
            calstatement.registerOutParameter(1, oracle.jdbc.OracleTypes.CLOB);
            //Truyen tham so thu 2 vao la mang main_pos
            calstatement.setString(2, sSave_id);
//            calstatement.setString(3, strModule_id);
            calstatement.registerOutParameter(3, oracle.jdbc.OracleTypes.NUMBER);
            calstatement.registerOutParameter(4, oracle.jdbc.OracleTypes.VARCHAR);
            calstatement.execute();
            //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
            int pn_err_cd = calstatement.getInt(3);
//            //thu hien lay mo ta loi
            String strEdd_txt = calstatement.getString(4);

            Clob clob = calstatement.getClob(1);
            //Lay cursor ra resultset
            sUserName = calstatement.getString(1);
            //Get du lieu tra ra tham so thu 1

//            System.err.println(calstatement.getString(1));
            if (calstatement != null) {
                calstatement.close();
            }
        } catch (SQLException e) {
            System.err.print(e.getMessage());
            CoreLogger.error(this.getClass().getName() + " deleteRptFormula -> " + e.getMessage());
        }
        return sUserName;
    }

    public List<ListValue> getGroupQuery(String UserName) {
        List<ListValue> lstQuery = new ArrayList<ListValue>();

        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call VBSP_IMS_RPT_KT740.sp_get_group(?, ?,?,?)}";
            ResultSet reset = null;

            try {
                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                //Tham so thu nhat truyen vao la co lay theo pos hay main pos   
                calstatement.setString(1, UserName == null ? "" : UserName);
                calstatement.registerOutParameter(2, oracle.jdbc.OracleTypes.NUMBER);
                calstatement.registerOutParameter(3, oracle.jdbc.OracleTypes.VARCHAR);
                calstatement.registerOutParameter(4, oracle.jdbc.OracleTypes.CURSOR);
                //Thuc hien execute lay du lieu
                calstatement.execute();
                //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
                int pn_err_cd = calstatement.getInt(2);
                //thu hien lay mo ta loi
                String strEdd_txt = calstatement.getString(3);
                //Lay cursor ra resultset
                reset = (ResultSet) calstatement.getObject(4);
                //COLUMN_DESC
                while (reset.next()) {
                    lstQuery.add(new ListValue(reset.getString("GROUP_ID"), reset.getString("GROUP_DESC"), reset.getString("GROUP_ORDER")));
//                    lstQuery.put("SRQ_QUERY",reset.getString("SRQ_QUERY"));   
//                     lstQuery.put("STRF_TITLE_NAME",reset.getString("STRF_TITLE_NAME"));  
//                     hmQuery.put("SRQ_QUERY",reset.getBlob("SRQ_QUERY").toString());   
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
                System.err.print(e.getMessage());
                CoreLogger.error(this.getClass().getName() + " getGroupQuery -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham getGroupQuery " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " getLoadAllQuery -> " + e.getMessage());
        }
        return lstQuery;
    }
}
