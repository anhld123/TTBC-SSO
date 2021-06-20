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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;
import vbsp.ims.define.DefineFun;
import vbsp.ims.export.excel.ExportExcelFile;
import vbsp.ims.loadparams.Combo;
import vbsp.ims.loadparams.ReportParam;
import vbsp.ims.log.CoreLogger;
import vbsp.ims.report.fast.ListValue;
import vbsp.ims.report.fast.ModelRptFastEdit;
import vbsp.ims.report.fast.paraReportFast;

/**
 *
 * @author LION
 */
public class DaoRptFastHstdct {

    public List<paraReportFast> getColumnParaReportFast(String strSave_id, String sUserName) {

        if (strSave_id.length() == 0 || strSave_id == null) {
            System.err.println("tra ra la null");
            return null;
        }

        List<paraReportFast> lstParaRptFast = new ArrayList<paraReportFast>();
        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call vbsp_ims_rpt.sp_get_para_report_fast(?,?,?,?)}";
            ResultSet reset = null;

            try {
                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                //Tham so thu nhat truyen vao la co lay theo pos hay main pos   
                calstatement.setString(1, strSave_id);
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
                    paraReportFast objPara = new paraReportFast();
                    String sDatatype = reset.getString("DATA_TYPE");
                    objPara.setsColumn_desc(reset.getString("COLUMN_DESC"));
                    objPara.setsColumn_name(reset.getString("COLUMN_NAME"));
                    objPara.setsData_type(sDatatype);
                    objPara.setsPara_where(reset.getString("PARA_WHERE"));
                    if (sDatatype.trim().equals("VARCHAR2_MAPGD")) {
                        objPara.setLstPoslist(get_pos_report_fast(conn, sUserName));
                    }

                    lstParaRptFast.add(objPara);
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
                CoreLogger.error(this.getClass().getCanonicalName() + " getColumnParaReportFast -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham getColumnParaReportFast " + e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " getColumnParaReportFast -> " + e.getMessage());
        }
        return lstParaRptFast;
    }

    public List<ListValue> get_pos_report_fast(Connection conn, String strUserName) {
        List<ListValue> lstPo = new ArrayList<ListValue>();
        try {

            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call vbsp_ims_rpt.sp_get_pos_report_fast(?,?,?,?)}";
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
                //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
                int pn_err_cd = calstatement.getInt(2);
                //thu hien lay mo ta loi
                String strEdd_txt = calstatement.getString(3);
                //Lay cursor ra resultset
                reset = (ResultSet) calstatement.getObject(4);
                //COLUMN_DESC
                while (reset.next()) {
                    lstPo.add(new ListValue(reset.getString("PO_KEY"), reset.getString("PO_DESC")));
                }

                if (reset != null) {
                    reset.close();
                }
                if (calstatement != null) {
                    calstatement.close();
                }
            } catch (SQLException e) {
                System.err.print(e.getMessage());
                CoreLogger.error(this.getClass().getCanonicalName() + " get_pos_report_fast -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham get_pos_report_fast " + e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " get_pos_report_fast -> " + e.getMessage());
        }
        return lstPo;

    }

    public List<ListValue> getModuleReportFast() {
        List<ListValue> lstModuleRptFast = new ArrayList<ListValue>();
        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call vbsp_ims_rpt.sp_get_module_report_fast(?, ?, ?)}";
            ResultSet reset = null;

            try {
                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                //Tham so thu nhat truyen vao la co lay theo pos hay main pos           
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
                while (reset.next()) {
                    lstModuleRptFast.add(new ListValue(reset.getString("MMRF_MODULE_ID"), reset.getString("MMRF_MODULE_DESC")));
                    //System.err.println(strPoscd_PosDesc);
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
                CoreLogger.error(this.getClass().getCanonicalName() + " getModuleReportFast -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham getModuleReportFast " + e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " getModuleReportFast -> " + e.getMessage());
        }
        return lstModuleRptFast;
    }

    public List<ListValue> getSaveReportFastFromModule(String strModule_id) {
        List<ListValue> lstSaveRptFast = new ArrayList<ListValue>();
        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call vbsp_ims_rpt.sp_get_save_report_fast(?, ?, ?, ?)}";
            ResultSet reset = null;

            try {
                //Khoi tao goi store STRF_SAVE_ID SAVE_ID, STRF_TITLE_NAME TITLE_NAME,STRF_USER_ID USER_ID,STRF_CREATE_DATE CREATE_DATE
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                //Tham so thu nhat truyen vao la co lay theo pos hay main pos  
                calstatement.setString(1, strModule_id);
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

                while (reset.next()) {
                    lstSaveRptFast.add(new ListValue(reset.getString("SAVE_ID"), reset.getString("TITLE_NAME")));
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
                CoreLogger.error(this.getClass().getCanonicalName() + " getSaveReportFastFromModule -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham getModuleReportFast " + e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " getSaveReportFastFromModule -> " + e.getMessage());
        }
        return lstSaveRptFast;
    }

    public String getQueryReportFastFromSaveID(String strSave_id, Connection connect) {
        if (strSave_id.length() == 0 || strSave_id == null) {
            return null;
        }
        String strQueryReportFast = null;

        // Connection connect = null;
        CallableStatement calstatement = null;
        //Khoi tao function se tra ra du lieu la kieu gi
        String strStoreproce = "{?=call vbsp_ims_rpt.f_get_query_report(?)}";

        try {
            //Khoi tao ket noi
            //DaoConnect oraconn = new DaoConnect();
            //connect = oraconn.getConnect();
            if (connect == null) {
                System.err.println("Khong the ket noi voi csdl ham ExportExcelFromQry");
                return strQueryReportFast;
            }
            //THuc hien goi ham trong oracle
            calstatement = connect.prepareCall(strStoreproce);
            //dang ky tham so tra du lieu ra la tham so thu nhat, kieu du lieu tra ra la number
            calstatement.registerOutParameter(1, oracle.jdbc.OracleTypes.CLOB);
            //Truyen tham so thu 2 vao la mang main_pos
            calstatement.setString(2, strSave_id);

            calstatement.execute();
            //Get du lieu tra ra tham so thu 1
//            strQueryReportFast = calstatement.getString(1);
            
            Clob clob = calstatement.getClob(1);
            //Lay cursor ra resultset
            //Get du lieu tra ra tham so thu 1
            if(clob!=null)
            {
                strQueryReportFast = clob.getSubString(1, (int)clob.length());
                clob.free();
                
            }
            if (calstatement != null) {
                calstatement.close();
            }
//            if (connect != null) {
//                connect.close();
//            }
        } catch (SQLException e) {
            System.err.print(e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " getQueryReportFastFromArrCol -> " + e.getMessage());
        }
        return strQueryReportFast;
    }

    public Map<String, String> get_Desc_column_report_fast(String strSave_id, Connection conn) {
        Map<String, String> mapColumnDesc = new HashMap<String, String>();

        try {
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call vbsp_ims_rpt.sp_get_Column_Desc_report_fast(?,?,?,?,?)}";
            ResultSet reset = null;

            try {
                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                //Tham so thu nhat truyen vao la co lay theo pos hay main pos   
                calstatement.setString(1, strSave_id);
                calstatement.registerOutParameter(2, oracle.jdbc.OracleTypes.VARCHAR);
                calstatement.registerOutParameter(3, oracle.jdbc.OracleTypes.NUMBER);
                calstatement.registerOutParameter(4, oracle.jdbc.OracleTypes.VARCHAR);
                calstatement.registerOutParameter(5, oracle.jdbc.OracleTypes.CURSOR);
                //Thuc hien execute lay du lieu
                calstatement.execute();
                String strTitle = calstatement.getString(2);
                //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
                int pn_err_cd = calstatement.getInt(3);
                //thu hien lay mo ta loi
                String strEdd_txt = calstatement.getString(4);
                mapColumnDesc.put("TITLE_HEADER", strTitle);
                //Lay cursor ra resultset
                reset = (ResultSet) calstatement.getObject(5);
                //COLUMN_DESC
                while (reset.next()) {
                    mapColumnDesc.put(reset.getString("COLUMN_NAME"), reset.getString("COLUMN_DESC"));
                }

                if (reset != null) {
                    reset.close();
                }
                if (calstatement != null) {
                    calstatement.close();
                }

            } catch (SQLException e) {
                System.err.print(e.getMessage());
                CoreLogger.error(this.getClass().getCanonicalName() + " get_Desc_column_report_fast -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham get_Desc_column_report_fast " + e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " get_Desc_column_report_fast -> " + e.getMessage());
        }
        return mapColumnDesc;

    }

    public String getReplaceFieldNull(String strSave_id, String strFieldName, Connection connect) {
        if (strSave_id.length() == 0 || strSave_id == null || strFieldName.length() == 0 || strFieldName == null) {
            return null;
        }
        String strReplaceField = null;

        //Connection connect = null;
        CallableStatement calstatement = null;
        //Khoi tao function se tra ra du lieu la kieu gi
        String strStoreproce = "{?=call vbsp_ims_rpt.f_get_replace_field_null(?,?,?,?)}";

        try {
            //Khoi tao ket noi
            // DaoConnect oraconn = new DaoConnect();
            //connect = oraconn.getConnect();
            if (connect == null) {
                System.err.println("Khong the ket noi voi csdl ham getReplaceFieldNull");
                return strReplaceField;
            }
            //THuc hien goi ham trong oracle
            calstatement = connect.prepareCall(strStoreproce);
            //dang ky tham so tra du lieu ra la tham so thu nhat, kieu du lieu tra ra la number
            calstatement.registerOutParameter(1, oracle.jdbc.OracleTypes.VARCHAR);
            //Truyen tham so thu 2 vao la mang main_pos
            calstatement.setString(2, strSave_id);
            calstatement.setString(3, strFieldName);
            calstatement.registerOutParameter(4, oracle.jdbc.OracleTypes.NUMBER);
            calstatement.registerOutParameter(5, oracle.jdbc.OracleTypes.VARCHAR);
            calstatement.execute();
            //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
            int pn_err_cd = calstatement.getInt(4);
//            //thu hien lay mo ta loi
            String strEdd_txt = calstatement.getString(5);
            //Lay cursor ra resultset
            //Get du lieu tra ra tham so thu 1
            strReplaceField = calstatement.getString(1);
            if (calstatement != null) {
                calstatement.close();
            }

        } catch (SQLException e) {
            System.err.print(e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " getReplaceFieldNull -> " + e.getMessage());
        }
        return strReplaceField;
    }

    public void ExportExcelFromQrySaveId(Map<String, String> mapinPara, String strSave_id, String strFileName) {
        if (strSave_id == null || strSave_id.length() < 1) {
            return;
        }
        DaoConnect daoConnect = new DaoConnect();
        Connection connect = null;
        try {
            connect = daoConnect.getConnect();
            String strQuery = getQueryReportFastFromSaveID(strSave_id, connect);
            //Lay ra column va desc cho column du lieu trong bao cao
            Map<String, String> mapColumnDesc = get_Desc_column_report_fast(strSave_id, connect);

            String strTitleHeader = mapColumnDesc.get("TITLE_HEADER");
            //replare tham so cho gia tri trong truy van
            Set<String> keys = mapinPara.keySet();
            String strReplace = "";
            for (String key : keys) {
                String value = mapinPara.get(key).trim();
                strQuery = strQuery.replaceAll(key, value);
                if (value.isEmpty() || value == null || value.length() < 1) {
                    strReplace = getReplaceFieldNull(strSave_id, key.substring(8), connect);
                    strReplace = strReplace.replaceAll(key, value);
                    strQuery = strQuery.replaceAll(strReplace, "");
                }
            }
            if (connect == null) {
                System.err.println("Khong the ket noi voi csdl ham ExportExcelFromQrySaveId");
                CoreLogger.error(this.getClass().getCanonicalName() + " Khong the ket noi voi csdl ham ExportExcelFromQrySaveId ");
                return;
            }
            Statement stm = null;
            stm = connect.createStatement();
            if (stm == null) {
                System.err.println("Khong tao duoc createStatement ham ExportExcelFromQrySaveId");
                CoreLogger.error(this.getClass().getCanonicalName() + " Khong tao duoc createStatement ham ExportExcelFromQrySaveId ");
                return;
            }
            ResultSet reset = null;
            reset = stm.executeQuery(strQuery);
            //System.err.println(strQuery);
            if (stm == null) {
                System.err.println("Khong tao duoc executeQuery ham ExportExcelFromQrySaveId");
                CoreLogger.error(this.getClass().getCanonicalName() + " Khong tao duoc executeQuery ham ExportExcelFromQrySaveId ");
                return;
            }
            ExportExcelFile exportExcel = new ExportExcelFile();
            exportExcel.ExportFileExcel(reset, mapColumnDesc, strTitleHeader, strFileName);

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
            System.err.println("Loi trong ham ExportExcelFromQry " + e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " ExportExcelFromQrySaveId -> " + e.getMessage());
            System.gc();
        }
    }

    public List<ListValue> getColumnReportFastArrObj(String strModuleID) {
        List<ListValue> lstColumnRptFast = new ArrayList<ListValue>();
        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call vbsp_ims_rpt.sp_get_column_report_fast(?, ?, ?, ?)}";
            ResultSet reset = null;

            try {
                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                //Tham so thu nhat truyen vao la co lay theo pos hay main pos    
                calstatement.setString(1, strModuleID);
                calstatement.registerOutParameter(2, oracle.jdbc.OracleTypes.NUMBER);
                calstatement.registerOutParameter(3, oracle.jdbc.OracleTypes.VARCHAR);
                calstatement.registerOutParameter(4, oracle.jdbc.OracleTypes.CURSOR);
                //Thuc hien execute lay du lieu
                calstatement.execute();
                //Lay cursor ra resultset
                reset = (ResultSet) calstatement.getObject(4);

                //Fill du lieu ra mang
                while (reset.next()) {

                    lstColumnRptFast.add(new ListValue(reset.getString("MCRF_COLUMN_NAME"),
                            reset.getString("MCRF_COLUMN_NAME") + " - " + reset.getString("MCRF_COLUMN_DESC")));
                    //System.err.println(reset.getString("MCRF_COLUMN_DESC"));
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
                System.err.println("Loi trong ham getColumnReportFast " + e.getMessage());
                CoreLogger.error(this.getClass().getCanonicalName() + " getColumnReportFastArrObj -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham getColumnReportFast " + e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " getColumnReportFastArrObj -> " + e.getMessage());
        }
        return lstColumnRptFast;
    }

    public List<ListValue> getColumnDateReportFast(String strModule_id) {

        if (strModule_id.length() == 0 || strModule_id == null) {
            return null;
        }

        List<ListValue> lstColumnDateRptFast = new ArrayList<ListValue>();
        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call vbsp_ims_rpt.sp_get_column_date_module(?,?,?,?)}";
            ResultSet reset = null;

            try {
                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                //Tham so thu nhat truyen vao la co lay theo pos hay main pos   
                calstatement.setString(1, strModule_id);
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

                while (reset.next()) {
                    lstColumnDateRptFast.add(new ListValue(reset.getString("COLUMN_NAME"),
                            reset.getString("COLUMN_NAME") + " - " + reset.getString("COLUMN_DESC")));
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
                CoreLogger.error(this.getClass().getCanonicalName() + " getColumnDateReportFast -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham getColumnDateReportFast " + e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " getColumnDateReportFast -> " + e.getMessage());
        }
        return lstColumnDateRptFast;
    }

    public List<String> viewReport(List<String> lstArrColumn, String strModule_id, String strAddwhere, String strNgayBC, String strPos_cd) {
        DaoConnect daoConnect = new DaoConnect();
        Connection connect = null;
        String strQuery = null;
        List<String> lstOutView = new ArrayList<String>();
        try {
            connect = daoConnect.getConnect();
            strQuery = getQueryReportFastFromArrCol(connect, lstArrColumn, strModule_id, strAddwhere);
            HashMap<String, String> hmColumnDesc = getColumnDescReportFast(connect, lstArrColumn, strModule_id);

            strQuery = strQuery.replaceAll("P_POS_CD", strPos_cd);
            strQuery = strQuery.replaceAll("PV_DATE_SYN", strNgayBC);
//            strQuery += " and rownum<11";

            //System.err.println("Truy van lay du lieu ra --------" + strQuery);
            if (connect == null) {
                System.err.println("Khong the ket noi voi csdl ham ExportExcelFromQry");
                return lstOutView;
            }
            Statement stm = null;
            stm = connect.createStatement();
            if (stm == null) {
                System.err.println("Khong tao duoc createStatement ham ExportExcelFromQry");
                return lstOutView;
            }
            ResultSet reset = null;
            reset = stm.executeQuery(strQuery);
            if (stm == null) {
                System.err.println("Khong tao duoc executeQuery ham ExportExcelFromQry");
                return lstOutView;
            }

            //Cho nay ghi ra phan header
            //Lấy metadata làm column header
            ResultSetMetaData rsmd = reset.getMetaData();

            int colNum = rsmd.getColumnCount();//số fields có trong bảng
            //duyệt qua các fields lấy làm column header
            String filltable = "";
            for (int i = 0; i < colNum; i++) {
                String col = rsmd.getColumnName(i + 1);
                String strColName = hmColumnDesc.get(col);
                filltable += "<td align=\"center\">" + strColName + "</td>";

            }
            lstOutView.add("<tr>" + filltable + "</tr>");
            //System.err.println("dong thu " + filltable);
            //điền giá trị vào các hàng
            while (reset.next()) {
                //tạo 1 hàng mới
                filltable = "";
                for (int i = 0; i < colNum; i++) {
                    String val = reset.getString(i + 1);
                    //tạo 1 ô mới

                    filltable += "<td align=\"center\">" + val + "</td>";
                }
                lstOutView.add("<tr>" + filltable + "</tr>");
//                System.err.println("dong thu " + filltable);
            }

            if (connect != null) {
                connect.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (reset != null) {
                reset.close();
            }
            System.gc();
        } catch (SQLException e) {
            System.err.println("Loi trong ham viewReport " + e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " viewReport -> " + e.getMessage());
            System.gc();
        }
        return lstOutView;
    }

    public HashMap<String, String> getColumnDescReportFast(Connection conn, List<String> lstArrColumn, String strModule_id) {

        if (lstArrColumn.size() == 0) {
            return null;
        }
        String[] array = (String[]) lstArrColumn.toArray(new String[lstArrColumn.size()]);

        HashMap<String, String> ColumnDescRptFast = new HashMap<String, String>();
        try {
//            DaoConnect daoconnect = new DaoConnect();
//            Connection conn = null;
//            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            ArrayDescriptor des = ArrayDescriptor
                    .createDescriptor("ARRAY_TABLE", conn);
            ARRAY array_to_pass = new ARRAY(des, conn, array);
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call vbsp_ims_rpt.sp_get_column_desc_from_array(?, ?, ?,?,?)}";
            ResultSet reset = null;

            try {
                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                //Tham so thu nhat truyen vao la co lay theo pos hay main pos   
                calstatement.setArray(1, array_to_pass);
                calstatement.setString(2, strModule_id);
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

                while (reset.next()) {
                    ColumnDescRptFast.put(reset.getString(1), reset.getString(2));
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
                CoreLogger.error(this.getClass().getCanonicalName() + " getColumnDescReportFast -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham getColumnDescReportFast " + e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " getColumnDescReportFast -> " + e.getMessage());
        }
        return ColumnDescRptFast;
    }

    public String getQueryReportFastFromArrCol(Connection connect, List<String> lstArrColumn, String strModule_id, String strAddwhere) {
        if (lstArrColumn.size() == 0) {
            return null;
        }
        String strQueryReportFast = null;

        // System.err.println(strColumnName);
//        Connection connect = null;
        CallableStatement calstatement = null;
        //Khoi tao function se tra ra du lieu la kieu gi
        String strStoreproce = "{?=call vbsp_ims_rpt.f_get_query_from_array_column(?, ?,?,?,?)}";
        String array[] = (String[]) lstArrColumn.toArray(new String[lstArrColumn.size()]);
        try {
            //Khoi tao ket noi
//            DaoConnect oraconn = new DaoConnect();
//            connect = oraconn.getConnect();
            ArrayDescriptor des = ArrayDescriptor.createDescriptor("ARRAY_TABLE", connect);
            ARRAY array_to_pass = new ARRAY(des, connect, array);
            if (connect == null) {
                System.err.println("Khong the ket noi voi csdl ham ExportExcelFromQry");
                return strQueryReportFast;
            }
            //THuc hien goi ham trong oracle
            calstatement = connect.prepareCall(strStoreproce);
            //dang ky tham so tra du lieu ra la tham so thu nhat, kieu du lieu tra ra la number
            calstatement.registerOutParameter(1, oracle.jdbc.OracleTypes.VARCHAR);
            //Truyen tham so thu 2 vao la mang main_pos
            calstatement.setArray(2, array_to_pass);
            calstatement.setString(3, strModule_id);
            calstatement.setString(4, strAddwhere);
            calstatement.registerOutParameter(5, oracle.jdbc.OracleTypes.NUMBER);
            calstatement.registerOutParameter(6, oracle.jdbc.OracleTypes.VARCHAR);
            calstatement.execute();
            //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
            int pn_err_cd = calstatement.getInt(5);
            //thu hien lay mo ta loi
            String strEdd_txt = calstatement.getString(6);
            //Lay cursor ra resultset
            //Get du lieu tra ra tham so thu 1
            strQueryReportFast = calstatement.getString(1);
//            System.err.println(calstatement.getString(1));
            if (calstatement != null) {
                calstatement.close();
            }
//            if (connect != null) {
//                connect.close();
//            }
        } catch (SQLException e) {
            System.err.print(e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " getQueryReportFastFromArrCol -> " + e.getMessage());
        }
        return strQueryReportFast;
    }

    public String getCheckSelectQuery( List<String> lstArrColumn, String strModule_id) {
        if (lstArrColumn.size() == 0) {
            return null;
        }
        String strQueryReportFast = null;

        // System.err.println(strColumnName);
        Connection connect = null;
        CallableStatement calstatement = null;
        //Khoi tao function se tra ra du lieu la kieu gi
        String strStoreproce = "{?=call vbsp_ims_rpt.F_CHECK_JOIN_REPORT(?,?,?,?)}";
        String array[] = (String[]) lstArrColumn.toArray(new String[lstArrColumn.size()]);
        try {
            //Khoi tao ket noi
            
            DaoConnect oraconn = new DaoConnect();
            connect = oraconn.getConnect();
            ArrayDescriptor des = ArrayDescriptor.createDescriptor("ARRAY_TABLE", connect);
            ARRAY array_to_pass = new ARRAY(des, connect, array);
            if (connect == null) {
                System.err.println("Khong the ket noi voi csdl ham ExportExcelFromQry");
                return strQueryReportFast;
            }
            //THuc hien goi ham trong oracle
            calstatement = connect.prepareCall(strStoreproce);
            //dang ky tham so tra du lieu ra la tham so thu nhat, kieu du lieu tra ra la number
            calstatement.registerOutParameter(1, oracle.jdbc.OracleTypes.VARCHAR);
            //Truyen tham so thu 2 vao la mang main_pos
            calstatement.setArray(2, array_to_pass);
            calstatement.setString(3, strModule_id);
            calstatement.registerOutParameter(4, oracle.jdbc.OracleTypes.NUMBER);
            calstatement.registerOutParameter(5, oracle.jdbc.OracleTypes.VARCHAR);
            calstatement.execute();
            //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
            int pn_err_cd = calstatement.getInt(4);
            //thu hien lay mo ta loi
            String strEdd_txt = calstatement.getString(5);
            //Lay cursor ra resultset
            //Get du lieu tra ra tham so thu 1
            strQueryReportFast = calstatement.getString(1);
//            System.err.println(calstatement.getString(1));
            if (calstatement != null) {
                calstatement.close();
            }
            if (connect != null) {
                connect.close();
            }
        } catch (SQLException e) {
            System.err.print(e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " getQueryReportFastFromArrCol -> " + e.getMessage());
        }
        return strQueryReportFast;
    }
    public String getCheckSelectQueryAddWhere( List<String> lstArrColumn, List<String> lstArrColumnWhere, String sAddWhere, String strModule_id) {
        if (lstArrColumn.size() == 0) {
            return null;
        }
        String strQueryReportFast = null;

        // System.err.println(strColumnName);
        Connection connect = null;
        CallableStatement calstatement = null;
        //Khoi tao function se tra ra du lieu la kieu gi
        String strStoreproce = "{?=call vbsp_ims_rpt.F_CHECK_REPORT_ADD_WHERE(?,?,?,?,?,?)}";
        String arrayCol[] = (String[]) lstArrColumn.toArray(new String[lstArrColumn.size()]);
        String arrayColWhere[] = (String[]) lstArrColumnWhere.toArray(new String[lstArrColumnWhere.size()]);
        try {
            //Khoi tao ket noi
            
            DaoConnect oraconn = new DaoConnect();
            connect = oraconn.getConnect();
            ArrayDescriptor des_col = ArrayDescriptor.createDescriptor("ARRAY_TABLE", connect);
            ARRAY array_to_Col = new ARRAY(des_col, connect, arrayCol);
            
            ArrayDescriptor des_colWhere = ArrayDescriptor.createDescriptor("ARRAY_TABLE", connect);
            ARRAY array_to_ColWhere = new ARRAY(des_colWhere, connect, arrayColWhere);
            if (connect == null) {
                System.err.println("Khong the ket noi voi csdl ham getCheckSelectQueryAddWhere");
                return strQueryReportFast;
            }
            //THuc hien goi ham trong oracle
            calstatement = connect.prepareCall(strStoreproce);
            //dang ky tham so tra du lieu ra la tham so thu nhat, kieu du lieu tra ra la number
            calstatement.registerOutParameter(1, oracle.jdbc.OracleTypes.VARCHAR);
            //Truyen tham so thu 2 vao la mang main_pos
            calstatement.setArray(2, array_to_Col);
            calstatement.setArray(3, array_to_ColWhere);
            calstatement.setString(4, sAddWhere);
            calstatement.setString(5, strModule_id);
            calstatement.registerOutParameter(6, oracle.jdbc.OracleTypes.NUMBER);
            calstatement.registerOutParameter(7, oracle.jdbc.OracleTypes.VARCHAR);
            calstatement.execute();
            //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
            int pn_err_cd = calstatement.getInt(6);
            //thu hien lay mo ta loi
            String strEdd_txt = calstatement.getString(7);
            //Lay cursor ra resultset
            //Get du lieu tra ra tham so thu 1
            strQueryReportFast = calstatement.getString(1);
//            System.err.println(calstatement.getString(1));
            if (calstatement != null) {
                calstatement.close();
            }
            if (connect != null) {
                connect.close();
            }
        } catch (SQLException e) {
            System.err.print(e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " getCheckSelectQueryAddWhere -> " + e.getMessage());
        }
        return strQueryReportFast;
    }
    public boolean saveReportFastArr(String strModule_id, List lstArrSelectCol, List lstArrWhereCol,
            String strTitle, String strAddwhere, String strUser_id) {
        boolean bSuccess = false;

        //neu mang cot da chon la null thi return
        if (lstArrSelectCol.size() == 0 || lstArrWhereCol.size() == 0) {
            return bSuccess;
        }

        //System.err.println("Mang cot du lieu "+strSelectCol);
        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call vbsp_ims_rpt.sp_save_report(?, ?, ?, ?, ?, ?, ?,?)}";
            ResultSet reset = null;

            try {
                String[] arrayColSelect = (String[]) lstArrSelectCol.toArray(new String[lstArrSelectCol.size()]);
                ArrayDescriptor desSelelect = ArrayDescriptor
                        .createDescriptor("ARRAY_TABLE", conn);
                ARRAY array_to_pass_col_sel = new ARRAY(desSelelect, conn, arrayColSelect);

                String[] arrayColWhere = (String[]) lstArrWhereCol.toArray(new String[lstArrWhereCol.size()]);
                ArrayDescriptor desWhere = ArrayDescriptor
                        .createDescriptor("ARRAY_TABLE", conn);
                ARRAY array_to_pass_col_Where = new ARRAY(desWhere, conn, arrayColWhere);

                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                //Tham so thu nhat module_id 
                calstatement.setString(1, strModule_id);
                //THam so thu 2 la mang cac cot da chon
                calstatement.setArray(2, array_to_pass_col_sel);
                //THam so thu 3 la mang cac cot sau dieu kien where
                calstatement.setArray(3, array_to_pass_col_Where);
                //THam so thu 4 la tieu de cua bao cao
                calstatement.setString(4, strTitle);

                calstatement.setString(5, strAddwhere);
                //User id tao bao cao
                calstatement.setString(6, strUser_id);
                calstatement.registerOutParameter(7, oracle.jdbc.OracleTypes.NUMBER);
                calstatement.registerOutParameter(8, oracle.jdbc.OracleTypes.VARCHAR);
                //calstatement.registerOutParameter(5, oracle.jdbc.OracleTypes.CURSOR);
                //Thuc hien execute lay du lieu
                calstatement.execute();
                //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
                int pn_err_cd = calstatement.getInt(7);
                //thu hien lay mo ta loi
                String strEdd_txt = calstatement.getString(8);
                if (calstatement != null) {
                    calstatement.close();
                }
                if (conn != null) {
                    conn.close();
                }
                bSuccess = true;
            } catch (SQLException e) {
                System.err.print(e.getMessage());
                CoreLogger.error(this.getClass().getCanonicalName() + " saveReportFastArr -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham saveReportFastArr " + e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " saveReportFastArr -> " + e.getMessage());
        }

        return bSuccess;
    }

    public List<ListValue> getAllFastRpt(String sUserName) {
        List<ListValue> lstAllRpt = new ArrayList<ListValue>();
        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call vbsp_ims_rpt.SP_LOAD_ALL_RPT_FAST( ?, ?, ?, ?)}";
            ResultSet reset = null;

            //Khoi tao goi store
            calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            calstatement.setString(1, sUserName);
            calstatement.registerOutParameter(2, oracle.jdbc.OracleTypes.NUMBER);
            calstatement.registerOutParameter(3, oracle.jdbc.OracleTypes.VARCHAR);
            calstatement.registerOutParameter(4, oracle.jdbc.OracleTypes.CURSOR);
            //Thuc hien execute lay du lieu
            calstatement.execute();
            //Lay cursor ra resultset
            reset = (ResultSet) calstatement.getObject(4);

            //Fill du lieu ra mang
            while (reset.next()) {
                lstAllRpt.add(new ListValue(reset.getString(2), reset.getString(3), reset.getString(1)));
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
        } catch (Exception e) {
            System.err.println("Loi trong ham getAllFastRpt " + e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " getAllFastRpt -> " + e.getMessage());
        }
        return lstAllRpt;
    }
    
    
    public String getUserCreateReport(String sSave_id) {
        String sUserName = "";
        try {
            Connection connect = null;
            CallableStatement calstatement = null;
            connect = new DaoConnect().getConnect();

            //Khoi tao function se tra ra du lieu la kieu gi
            String strStoreproce = "{?=call VBSP_IMS_RPT.F_GET_USER_CREATE_RPT_FAST(?,?,?)}";
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

            //Lay cursor ra resultset
            sUserName = calstatement.getString(1);
            //Get du lieu tra ra tham so thu 1

//            System.err.println(calstatement.getString(1));
            if (calstatement != null) {
                calstatement.close();
            }
            if (connect != null) {
                connect.close();
            }
        } catch (SQLException e) {
            System.err.print(e.getMessage());
            CoreLogger.error(this.getClass().getName() + " deleteRptFormula -> " + e.getMessage());
        }
        return sUserName;
    }
    
     public boolean deleteRptFast(String strSave_Id, String sUserName) {
        boolean bSuccess = false;
        //System.err.println("Mang cot du lieu "+strSelectCol);
        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call VBSP_IMS_RPT.SP_DELETE_RPT_FAST(?, ?, ?, ?)}";
            ResultSet reset = null;

            try {
                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                //Tham so thu nhat module_id 
                calstatement.setString(1, strSave_Id);
                calstatement.setString(2, sUserName);
                //THam so thu 2 la mang cac cot da chon

                calstatement.registerOutParameter(3, oracle.jdbc.OracleTypes.NUMBER);
                calstatement.registerOutParameter(4, oracle.jdbc.OracleTypes.VARCHAR);
                //calstatement.registerOutParameter(5, oracle.jdbc.OracleTypes.CURSOR);
                //Thuc hien execute lay du lieu
                calstatement.execute();
                //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
                int pn_err_cd = calstatement.getInt(3);
                //thu hien lay mo ta loi
                String strEdd_txt = calstatement.getString(4);
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
                CoreLogger.error(this.getClass().getName() + " deleteRptFast -> " + e.getMessage());
                return false;
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham deleteRptFast " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " deleteRptFast -> " + e.getMessage());
            return false;
        }

        return bSuccess;
    }
    public ModelRptFastEdit getRptFastEdit(String save_id)
    {
        ModelRptFastEdit objEdit = new ModelRptFastEdit();
        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call vbsp_ims_rpt.SP_GET_EDIT_RPT_FAST( ?, ?, ?,?, ?, ?,?, ?, ?, ?)}";
            //ResultSet reset = null;

            //Khoi tao goi store
            calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            calstatement.setString(1, save_id);
            calstatement.registerOutParameter(2, oracle.jdbc.OracleTypes.VARCHAR); //title
            calstatement.registerOutParameter(3, oracle.jdbc.OracleTypes.VARCHAR); //module_id
            calstatement.registerOutParameter(4, oracle.jdbc.OracleTypes.VARCHAR); //add_where
            calstatement.registerOutParameter(5, oracle.jdbc.OracleTypes.CURSOR);
            calstatement.registerOutParameter(6, oracle.jdbc.OracleTypes.CURSOR);
            calstatement.registerOutParameter(7, oracle.jdbc.OracleTypes.CURSOR);
            calstatement.registerOutParameter(8, oracle.jdbc.OracleTypes.CURSOR);
            calstatement.registerOutParameter(9, oracle.jdbc.OracleTypes.NUMBER);
            calstatement.registerOutParameter(10, oracle.jdbc.OracleTypes.VARCHAR);
            
            //Thuc hien execute lay du lieu
            calstatement.execute();
            //Lay cursor ra resultset
            objEdit.setSave_id(save_id);
            objEdit.setTitle(calstatement.getString(2));
            objEdit.setModule_id(calstatement.getString(3));
            objEdit.setAdd_where(calstatement.getString(4));
//            ResultSet reset_leftsel = (ResultSet) calstatement.getObject(5);

            //Fill du lieu ra mang
            objEdit.setLeftModuleColumnList(getListFromResultset((ResultSet) calstatement.getObject(5)));
            objEdit.setRightModuleColumnList(getListFromResultset((ResultSet) calstatement.getObject(6)));
            objEdit.setLeftModuleDateList(getListFromResultset((ResultSet) calstatement.getObject(7)));
            objEdit.setRightModuleDateList(getListFromResultset((ResultSet) calstatement.getObject(8)));
            
//            if (reset_leftsel != null) {
//                reset_leftsel.close();
//            }
            if (calstatement != null) {
                calstatement.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham getRptFastEdit " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " getRptFastEdit -> " + e.getMessage());
        }
        return objEdit;
    }
    private List<ListValue> getListFromResultset(ResultSet reset)
    {
        List<ListValue>  lstOut = new ArrayList<ListValue>();
        try {
            while(reset.next())
            {
                lstOut.add(new ListValue(reset.getString(2), reset.getString(2)+" - "+reset.getString(3)));
            }
            
             if (reset != null) {
                reset.close();
            }
        } catch (Exception e) {
             System.err.println("Loi trong ham getListFromResultset " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " getListFromResultset -> " + e.getMessage());
        }
        return lstOut;
    }
    
    public boolean saveEditReportFastArr(String save_id,String strModule_id, List lstArrSelectCol, List lstArrWhereCol,
            String strTitle, String strAddwhere, String strUser_id) {
        boolean bSuccess = false;

        //neu mang cot da chon la null thi return
        if (lstArrSelectCol.size() == 0 || lstArrWhereCol.size() == 0) {
            return bSuccess;
        }

        //System.err.println("Mang cot du lieu "+strSelectCol);
        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call vbsp_ims_rpt.SP_SAVE_EDIT_REPORT(?, ?, ?, ?, ?, ?, ?, ?, ?)}";
            ResultSet reset = null;

            try {
                String[] arrayColSelect = (String[]) lstArrSelectCol.toArray(new String[lstArrSelectCol.size()]);
                ArrayDescriptor desSelelect = ArrayDescriptor
                        .createDescriptor("ARRAY_TABLE", conn);
                ARRAY array_to_pass_col_sel = new ARRAY(desSelelect, conn, arrayColSelect);

                String[] arrayColWhere = (String[]) lstArrWhereCol.toArray(new String[lstArrWhereCol.size()]);
                ArrayDescriptor desWhere = ArrayDescriptor
                        .createDescriptor("ARRAY_TABLE", conn);
                ARRAY array_to_pass_col_Where = new ARRAY(desWhere, conn, arrayColWhere);

                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                calstatement.setString(1, save_id);
                //Tham so thu nhat module_id 
                calstatement.setString(2, strModule_id);
                //THam so thu 2 la mang cac cot da chon
                calstatement.setArray(3, array_to_pass_col_sel);
                //THam so thu 3 la mang cac cot sau dieu kien where
                calstatement.setArray(4, array_to_pass_col_Where);
                //THam so thu 4 la tieu de cua bao cao
                calstatement.setString(5, strTitle);

                calstatement.setString(6, strAddwhere);
                //User id tao bao cao
                calstatement.setString(7, strUser_id);
                calstatement.registerOutParameter(8, oracle.jdbc.OracleTypes.NUMBER);
                calstatement.registerOutParameter(9, oracle.jdbc.OracleTypes.VARCHAR);
                //calstatement.registerOutParameter(5, oracle.jdbc.OracleTypes.CURSOR);
                //Thuc hien execute lay du lieu
                calstatement.execute();
                //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
                int pn_err_cd = calstatement.getInt(8);
                //thu hien lay mo ta loi
                String strEdd_txt = calstatement.getString(9);
                if (calstatement != null) {
                    calstatement.close();
                }
                if (conn != null) {
                    conn.close();
                }
                bSuccess = true;
            } catch (SQLException e) {
                System.err.print(e.getMessage());
                CoreLogger.error(this.getClass().getCanonicalName() + " saveEditReportFastArr -> " + e.getMessage());
                return false;
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham saveEditReportFastArr " + e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " saveEditReportFastArr -> " + e.getMessage());
            return false;
        }

        return bSuccess;
    }
    
    public List<ReportParam> getReportParmams(String strSave_id, String strUsername) {
        ArrayList<ReportParam> report_param_list = new ArrayList<ReportParam>();

        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call vbsp_ims_rpt.SP_LOAD_PARA_HSTDCT(?, ?, ?, ?, ?, ?)}";
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
//                    rp.setAction(rscur_params.getString("PARA_ACTION"));
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
    public static void main(String[] args)
    {
        DaoRptFastHstdct dao = new DaoRptFastHstdct();
        
        List<ReportParam> lstPara = dao.getReportParmams("RPTFAST0000000070", "M2721");
        
        for (ReportParam rpt:lstPara)
        {
            System.err.println( "getLabel="+rpt.getLabel()+"  ->  getFieldName="+rpt.getFieldName()+"  ->  getType="+rpt.getType());
        }
//        List<ReportParam> lstPara1 = new DaoRptQuery().getReportParmams(null, null)
        //dao.getRptFastEdit("RPTFAST0000000073");
    }
}
