/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.excel.template;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;
import vbsp.ims.core.Node;
import vbsp.ims.dao.DaoConnect;
import vbsp.ims.loadparams.Combo;
import vbsp.ims.loadparams.ReportParam;
import vbsp.ims.log.CoreLogger;
import vbsp.ims.query.ImsFillParaMeter;
import vbsp.ims.query.PlSqlQueryExecuterIms;
import vbsp.ims.report.fast.ListValue;
import vbsp.ims.xml.ImsException;
import vbsp.ims.xml.imsQueryConfig;

/**
 *
 * @author BAOANH
 */
public class DaoExcelTemplate {

    public static DaoExcelTemplate newInstance() {
        return new DaoExcelTemplate();
    }

    public List<ListValue> getGroupQuery(Connection conn, String UserName) throws SQLException {
        List<ListValue> lstAllGroup = new ArrayList<ListValue>();
        if (conn == null || conn.isClosed()) {
            conn = new DaoConnect().getConnect();
        }
        CallableStatement calstatement = null;
        //Khoi tao procedure cung voi tham so truyen vao la dau ?
        String strStoreproce = "{call IMS_EXCEL_TEMPLATE.sp_get_group(?, ?,?,?)}";
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
                lstAllGroup.add(new ListValue(reset.getString("GROUP_ID"), reset.getString("GROUP_DESC"), reset.getString("GROUP_ORDER")));
            }

        } catch (SQLException e) {
            System.err.print(e.getMessage());
            CoreLogger.error(this.getClass().getName() + " getLoadAllQuery -> " + e.getMessage());
            throw new SQLException();
        } finally {
            if (reset != null) {
                reset.close();
            }
            if (calstatement != null) {
                calstatement.close();
            }

        }
        return lstAllGroup;
    }

    public List<ListValue> getbcSbv(Connection conn) throws SQLException {
        List<ListValue> lstAllRptSbv = new ArrayList<ListValue>();
        if (conn == null || conn.isClosed()) {
            conn = new DaoConnect().getConnect();
        }
        CallableStatement calstatement = null;
        //Khoi tao procedure cung voi tham so truyen vao la dau ?
        String strStoreproce = "{call IMS_EXCEL_TEMPLATE.SP_LOAD_BC_SBV(?)}";
        ResultSet reset = null;

        try {
            //Khoi tao goi store
            calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            //Tham so thu nhat truyen vao la co lay theo pos hay main pos   
//            calstatement.setString(1, UserName == null ? "" : UserName);
//            calstatement.registerOutParameter(2, oracle.jdbc.OracleTypes.NUMBER);
//            calstatement.registerOutParameter(3, oracle.jdbc.OracleTypes.VARCHAR);
            calstatement.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            //Thuc hien execute lay du lieu
            calstatement.execute();
            //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
            //Lay cursor ra resultset
            reset = (ResultSet) calstatement.getObject(1);
            //COLUMN_DESC
            while (reset.next()) {
                lstAllRptSbv.add(new ListValue(reset.getString("SKEY"), reset.getString("SDESC"), reset.getString("STT")));
            }

        } catch (SQLException e) {
            System.err.print(e.getMessage());
            CoreLogger.error(this.getClass().getName() + " getbcSbv -> " + e.getMessage());
            throw new SQLException();
        } finally {
            if (reset != null) {
                reset.close();
            }
            if (calstatement != null) {
                calstatement.close();
            }

        }
        return lstAllRptSbv;
    }
    
    private boolean isCheckColumnName(String columnName, String start) {
        boolean found = false;
        try {
            String end = columnName.substring(start.length(), columnName.length());
            int stt = Integer.valueOf(end);
            if (stt > 0) {
                found = true;
            }

        } catch (Exception e) {
//            System.err.println(e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " isCheckColumnName -> " + e.getMessage());
//            addActionError("Lỗi khi lấy dữ liệu, Cột dữ liệu " + e.getMessage() + " Không đúng tên quy định là STRn hoặc Dn");
            return false;
        }
        return found;
    }

    public List<ListValue> getAllFieldQuery(String query, String Username, List<Node> lstNodePara) throws Exception {
        List<ListValue> lstAllField = new ArrayList<ListValue>();

        //khai bao lop query muc dich khai bao lop nay de lay ra cac tham so
        imsQueryConfig querycfg = imsQueryConfig.newInstance();
        querycfg.setQueryString(query);

        //Lay ra tat ca cac tham so trong query
        List<String> lstParaInQuery = querycfg.splitParaProcedure();
        //lay ra tat ca cac du lieu, kieu tham so cua cac tham so trong truy van
        List<ReportParam> lstRptPara = getReportParmams(lstParaInQuery, Username);

        //khoi tao ket noi csdl
        ResultSet reset = null;
        Connection conn = new DaoConnect().getConnect();
        try {
            Map parameters = new HashMap();
            for (Node node : lstNodePara) {
                //khoi tao lop cho tham so
                String value = "";
                for (ReportParam data_type_para : lstRptPara) {
                    //kiem tra neu tham so la giong nhau va kieu tham so la date
                    if (data_type_para.getFieldName().equals(node.getKey())) {
                        if (data_type_para.getType().equals("D")) { //neu kieu du lieu la kieu date
                            value = new SimpleDateFormat("dd-MMM-yyyy").format(new Date());
                        }//neu kieu du lieu la kieu LIST
                        else if (data_type_para.getType().equals("L") && data_type_para.getComboList() != null) {
                            value = data_type_para.getComboList().get(0).getKey();
                        } else { //cac kieu con lai
                            value = null;
                        }
                    }
                }
                //tao lop tham so cung voi truyen vao la kieu du lieu vi du node.getValue()=java.lang.String
                ImsFillParaMeter clspara = ImsFillParaMeter.newInstance(node.getValue(), value);
                parameters.put(node.getKey(), clspara);
            }
            //Lay ra truy van khi da remove cac tham so bang dau ?
            String qryRemovePara = querycfg.removeParaProcedure();

            PlSqlQueryExecuterIms execute = new PlSqlQueryExecuterIms(conn, lstParaInQuery, parameters, qryRemovePara);
            //COLUMN_DESC
            reset = execute.createDatasource();
            ResultSetMetaData resultMeta = reset.getMetaData();
            for (int i = 1; i <= resultMeta.getColumnCount(); i++) {
                String columnname = resultMeta.getColumnName(i);
                if (!isCheckColumnName(columnname, "STR") && !isCheckColumnName(columnname, "D")) {
                    throw new ImsException("Lỗi truy vấn bạn điền có cột " + columnname + " không đúng định dạng đã quy định STRn hoặc Dn");
                }
                lstAllField.add(new ListValue(resultMeta.getColumnName(i), resultMeta.getColumnName(i)));
            }

        } catch (SQLException e) {
            System.err.print(e.getMessage());
            CoreLogger.error(this.getClass().getName() + " getAllFieldQuery -> " + e.getMessage());
            throw new SQLException();
        } finally {
            if (reset != null) {
                reset.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return lstAllField;
    }

    public Map<String, String> getAllColumnNameQuery(String query, String Username, List<Node> lstNodePara) throws Exception {
        Map<String, String> lstAllField = new LinkedHashMap<String, String>();

        //khai bao lop query muc dich khai bao lop nay de lay ra cac tham so
        imsQueryConfig querycfg = imsQueryConfig.newInstance();
        querycfg.setQueryString(query);

        //Lay ra tat ca cac tham so trong query
        List<String> lstParaInQuery = querycfg.splitParaProcedure();
        System.err.println(lstParaInQuery.toString());
        //lay ra tat ca cac du lieu, kieu tham so cua cac tham so trong truy van
        List<ReportParam> lstRptPara = getReportParmams(lstParaInQuery, Username);

        //khoi tao ket noi csdl
        ResultSet reset = null;
        Connection conn = new DaoConnect().getConnect();
        try {
            Map parameters = new HashMap();
            for (Node node : lstNodePara) {
                //khoi tao lop cho tham so
                String value = "";
                for (ReportParam data_type_para : lstRptPara) {
                    //kiem tra neu tham so la giong nhau va kieu tham so la date
                    if (data_type_para.getFieldName().equals(node.getKey())) {
                        if (data_type_para.getType().equals("D")) { //neu kieu du lieu la kieu date
                            value = new SimpleDateFormat("dd-MMM-yyyy").format(new Date());
                        }//neu kieu du lieu la kieu LIST
                        else if (data_type_para.getType().equals("L") && data_type_para.getComboList() != null) {
                            value = data_type_para.getComboList().get(0).getKey();
                        } else { //cac kieu con lai
                            value = null;
                        }
                    }
                }
                //tao lop tham so cung voi truyen vao la kieu du lieu vi du node.getValue()=java.lang.String
                ImsFillParaMeter clspara = ImsFillParaMeter.newInstance(node.getValue(), value);
                parameters.put(node.getKey(), clspara);
            }
            //Lay ra truy van khi da remove cac tham so bang dau ?
            String qryRemovePara = querycfg.removeParaProcedure();

            PlSqlQueryExecuterIms execute = new PlSqlQueryExecuterIms(conn, lstParaInQuery, parameters, qryRemovePara);
            //COLUMN_DESC
            reset = execute.createDatasource();
            ResultSetMetaData resultMeta = reset.getMetaData();
            for (int i = 1; i <= resultMeta.getColumnCount(); i++) {
                String columnname = resultMeta.getColumnName(i);
                if (!isCheckColumnName(columnname, "STR") && !isCheckColumnName(columnname, "D")) {
                    throw new ImsException("Lỗi truy vấn bạn điền có cột " + columnname + " không đúng định dạng đã quy định STRn hoặc Dn");
                }
                lstAllField.put(resultMeta.getColumnName(i), resultMeta.getColumnName(i));
            }

        } catch (SQLException e) {
            System.err.print(e.getMessage());
            CoreLogger.error(this.getClass().getName() + " getAllFieldQuery -> " + e.getMessage());
            throw new SQLException();
        } finally {
            if (reset != null) {
                reset.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return lstAllField;
    }

    public List<ListValue> getLoadAllQuery(Connection conn, String sUserid, String sGroup_id, String sGraderpt) throws SQLException {
        List<ListValue> lstAllRpt = new ArrayList<ListValue>();
        if (conn == null || conn.isClosed()) {
            conn = new DaoConnect().getConnect();
        }
        CallableStatement calstatement = null;
        //Khoi tao procedure cung voi tham so truyen vao la dau ?
        String strStoreproce = "{call IMS_EXCEL_TEMPLATE.sp_load_all_rpt_query(?,?,?,?,?,?)}";
        ResultSet reset = null;

        try {
            //Khoi tao goi store
            calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            //Tham so thu nhat truyen vao la co lay theo pos hay main pos   
            calstatement.setString(1, sUserid);
            calstatement.setString(2, sGroup_id);
            calstatement.setString(3, sGraderpt);
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
                lstAllRpt.add(new ListValue(reset.getString(2), reset.getString(3), reset.getString(1)));
            }

        } catch (SQLException e) {
            System.err.print(e.getMessage());
            CoreLogger.error(this.getClass().getName() + " getLoadAllQuery -> " + e.getMessage());
            throw new SQLException();
        } finally {
            if (reset != null) {
                reset.close();
            }
            if (calstatement != null) {
                calstatement.close();
            }

        }
        return lstAllRpt;
    }

    public int saveExcelTemplate(String strUser_id, String sFile_Excel, String sGroup_id, String Description, String sGradeReport, String flagInsert, String IdBc) throws SQLException {
        int bSuccess = 0;
        //System.err.println("Mang cot du lieu "+strSelectCol);
        DaoConnect daoconnect = new DaoConnect();
        Connection conn = null;
        conn = daoconnect.getConnect();
        CallableStatement calstatement = null;
        //Khoi tao procedure cung voi tham so truyen vao la dau ?
        String strStoreproce = "{call ims_excel_template.SP_SAVE_RPT_EXCEL(?, ?, ?, ?, ?, ?, ?, ?, ?,?)}";
        ResultSet reset = null;

        try {

            //Clob clob = CLOB.createTemporary(conn, false, oracle.sql.CLOB.DURATION_SESSION);
            //Khoi tao goi store
            calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            //Tham so thu nhat module_id 
            calstatement.setString(1, strUser_id);
            calstatement.setString(2, sFile_Excel);
            calstatement.setString(3, sGroup_id);
            //THam so thu 2 la mang cac cot da chon
            calstatement.setString(4, Description);
            //THam so thu 3 la mang cac cot sau dieu kien where

            calstatement.setString(5, sGradeReport);
            calstatement.setString(6, flagInsert);
            calstatement.setString(7, IdBc==null?"":IdBc);
            calstatement.registerOutParameter(8, oracle.jdbc.OracleTypes.INTEGER);
            calstatement.registerOutParameter(9, oracle.jdbc.OracleTypes.NUMBER);
            calstatement.registerOutParameter(10, oracle.jdbc.OracleTypes.VARCHAR);
            //calstatement.registerOutParameter(5, oracle.jdbc.OracleTypes.CURSOR);
            //Thuc hien execute lay du lieu
            calstatement.execute();
            //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
            bSuccess = calstatement.getInt(8);
            int pn_err_cd = calstatement.getInt(9);
            //thu hien lay mo ta loi
            String strEdd_txt = calstatement.getString(10);
            //Lay cursor ra resultset

        } catch (SQLException e) {
            System.err.print(e.getMessage());
            CoreLogger.error(this.getClass().getName() + " saveExcelTemplate -> " + e.getMessage());
            throw new ImsException(sFile_Excel, e);
        } finally {
            if (calstatement != null) {
                calstatement.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return bSuccess;
    }

    public boolean deleteExcelTemplate(String fileName) throws SQLException {
        boolean bSuccess = false;
        //System.err.println("Mang cot du lieu "+strSelectCol);

        DaoConnect daoconnect = new DaoConnect();
        Connection conn = null;
        conn = daoconnect.getConnect();
        CallableStatement calstatement = null;
        //Khoi tao procedure cung voi tham so truyen vao la dau ?
        String strStoreproce = "{call ims_excel_template.SP_DELETE_TEMPLATE(?)}";
        ResultSet reset = null;

        try {
            //Khoi tao goi store
            calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            //Tham so thu nhat module_id 
            calstatement.setString(1, fileName);

            //calstatement.registerOutParameter(5, oracle.jdbc.OracleTypes.CURSOR);
            //Thuc hien execute lay du lieu
            calstatement.execute();

            bSuccess = true;
        } catch (SQLException e) {
            System.err.print(e.getMessage());
            CoreLogger.error(this.getClass().getName() + " deleteExcelTemplate -> " + e.getMessage());
            throw new ImsException("Loi khi xoa " + fileName, e);
        } finally {
            if (calstatement != null) {
                calstatement.close();
            }
            if (conn != null) {
                conn.close();
            }
        }

        return bSuccess;
    }

    public HashMap<String, String> getEditTemplate(String fileName) throws SQLException {
        HashMap<String, String> hmQuery = new HashMap<String, String>();
        if (fileName == null || fileName.isEmpty()) {
            return null;
        }

        DaoConnect daoconnect = new DaoConnect();
        Connection conn = null;
        conn = daoconnect.getConnect();
        CallableStatement calstatement = null;
        //Khoi tao procedure cung voi tham so truyen vao la dau ?
        String strStoreproce = "{call IMS_EXCEL_TEMPLATE.sp_load_edit_template(?,?)}";
        ResultSet reset = null;

        try {
            //Khoi tao goi store
            calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            //Tham so thu nhat truyen vao la co lay theo pos hay main pos   
            calstatement.setString(1, fileName);

            calstatement.registerOutParameter(2, oracle.jdbc.OracleTypes.CURSOR);
            //Thuc hien execute lay du lieu
            calstatement.execute();

            //Lay cursor ra resultset
            reset = (ResultSet) calstatement.getObject(2);
            //COLUMN_DESC
            while (reset.next()) {
                hmQuery.put("FILE_NAME", reset.getString("STRF_SAVE_ID"));
                hmQuery.put("MOTA", reset.getString("STRF_TITLE_NAME"));
                hmQuery.put("GROUP_ID", reset.getString("GROUP_ID").toString());
                hmQuery.put("GRADE_REPORT", reset.getString("GRADE_REPORT"));
                hmQuery.put("ID_BC", reset.getString("STRF_ADD_WHERE")==null?"":reset.getString("STRF_ADD_WHERE"));
            }

        } catch (SQLException e) {
            System.err.print(e.getMessage());
            CoreLogger.error(this.getClass().getName() + " getColumnParaReportFast -> " + e.getMessage());
            throw new ImsException("Loi khi load data " + fileName, e);
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
        return hmQuery;
    }

    public List<ReportParam> getReportParmams(List<String> lstParaIn, String strUsername) throws SQLException {
        ArrayList<ReportParam> report_param_list = new ArrayList<ReportParam>();

        DaoConnect daoconnect = new DaoConnect();
        Connection conn = null;
        conn = daoconnect.getConnect();

        ArrayDescriptor des = ArrayDescriptor.createDescriptor("ARRAY_TABLE", conn);
        String[] arrayPara = lstParaIn.toArray(new String[0]);
        ARRAY oracle_arrayPara = new ARRAY(des, conn, arrayPara);

        CallableStatement calstatement = null;
        //Khoi tao procedure cung voi tham so truyen vao la dau ?
        String strStoreproce = "{call IMS_EXCEL_TEMPLATE.SP_LOAD_PARA_RPT(?, ?, ?, ?, ?, ?)}";
        ResultSet rscur_params = null;
        ResultSet rs_combo = null;

        try {
            //Khoi tao goi store
            calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            //Tham so thu nhat truyen vao la co lay theo pos hay main pos    
            calstatement.setArray(1, oracle_arrayPara);
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

        } catch (SQLException e) {
            System.err.println("Loi trong ham getReportParmams " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " getReportParmams  -> " + e.getMessage());
            throw new ImsException("loi khi get tham so ", e);
        } finally {
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
            throw new ImsException("Loi trong ham fillResultSetComboToArray", e);
        }
        return combo_list;
    }
}
