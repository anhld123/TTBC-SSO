/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.chamdiemcn;

import vbsp.ims.chamdiemtt.dao.*;
import java.io.File;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import vbsp.ims.bcqt.dao.*;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import oracle.jdbc.OraclePreparedStatement;
import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;
import oracle.sql.STRUCT;
import oracle.sql.StructDescriptor;
import oracle.xdb.XMLType;
import org.w3c.dom.Document;
import vbsp.ims.action.ktktnb.DULIEU_NT_TQ;
import vbsp.ims.bcqt.model.QT_DULIEU_NT;
import vbsp.ims.bcqt.model.QT_MS12A;
import vbsp.ims.bcqt.model.QT_MS12C;
import vbsp.ims.bcqt.model.QT_MS13SK;
import vbsp.ims.chamdiemtt.action.ModelExcel;
import vbsp.ims.dao.DaoConnect;
import vbsp.ims.dao.DaoLoadReportParams;
import vbsp.ims.loadparams.Combo;
import vbsp.ims.loadparams.ReportParam;
import vbsp.ims.log.CoreLogger;
import vbsp.ims.model.ModelTreeNode;
import vbsp.ims.report.fast.ListValue;

/**
 *
 * @author LION
 */
public class DaoChamdiemcnMain {

    public static DaoChamdiemcnMain newInstance() {
        return new DaoChamdiemcnMain();
    }

    public Map<String, String> getPosByName(List<String> lstMaPGD) throws SQLException {
        Map<String, String> mapData = new HashMap<String, String>();
        DaoConnect daoconnect = new DaoConnect();
        Connection conn = null;
        conn = daoconnect.getConnect();
        CallableStatement calstatement = null;
        ResultSet reset = null;
//        try {
        //Khoi tao procedure cung voi tham so truyen vao la dau ?
        String strStoreproce = "{call VBSP_IMS_CHAMDIEMCN.SP_GET_POS_BY_NAME(?,?)}";
        ArrayDescriptor des = ArrayDescriptor.createDescriptor("POS_CD", conn);
        String[] arrayPoscd = lstMaPGD.toArray(new String[0]);
        ARRAY oracle_arrayPoscd = new ARRAY(des, conn, arrayPoscd);
        try {
            //Khoi tao goi store
            calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            calstatement.registerOutParameter(2, oracle.jdbc.OracleTypes.CURSOR);
            calstatement.setArray(1, oracle_arrayPoscd);
            //Thuc hien execute lay du lieu
            calstatement.execute();
            //Lay cursor ra resultset
            reset = (ResultSet) calstatement.getObject(2);
            while (reset.next()) {
                String mapgd = reset.getString(1);
                String tenpgd = reset.getString(2);
                if (!mapData.containsKey(mapgd)) {
                    mapData.put(mapgd, tenpgd);
                }
            }

        } catch (SQLException e) {
            System.err.print(e.getMessage());
            CoreLogger.error(this.getClass().getName() + " getPosByName -> " + e.getMessage());
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
        return mapData;
    }

    public List<ListValue> getAllBcqt(String sGrade, String sGroup) throws SQLException {
        List<ListValue> lstAllBcqt = new ArrayList<ListValue>();
        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call VBSP_IMS_CHAMDIEMCN.SP_LOAD_ALL_BCQT(?,?,?,?,?)}";
            ResultSet reset = null;

            try {
                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                calstatement.registerOutParameter(1, oracle.jdbc.OracleTypes.NUMBER);
                calstatement.registerOutParameter(2, oracle.jdbc.OracleTypes.VARCHAR);
                calstatement.registerOutParameter(3, oracle.jdbc.OracleTypes.CURSOR);
                calstatement.setString(4, sGrade);
                calstatement.setString(5, sGroup);
                //Thuc hien execute lay du lieu
                calstatement.execute();
                //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
                int pn_err_cd = calstatement.getInt(1);
                //thu hien lay mo ta loi
                String strEdd_txt = calstatement.getString(2);
                //Lay cursor ra resultset
                reset = (ResultSet) calstatement.getObject(3);
                while (reset.next()) {
                    String key = reset.getString(1);
                    String des = reset.getString(2);
//                    String stt = reset.getString("stt");

                    lstAllBcqt.add(new ListValue(key, des));
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
                CoreLogger.error(this.getClass().getName() + " getAllBcqt -> " + e.getMessage());
                throw new SQLException(e);
            }
        } catch (SQLException e) {
            System.err.println("Loi trong ham getAllBcqt " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " getAllBcqt -> " + e.getMessage());
            throw new SQLException(e);
        }
        return lstAllBcqt;
    }

    public List<ListValue> getAllBcqt_SUB() throws SQLException {
        List<ListValue> lstAllBcqt = new ArrayList<ListValue>();
        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call VBSP_IMS_CHAMDIEMCN.SP_LOAD_ALL_BCQT_SUB(?,?,?)}";
            ResultSet reset = null;

            try {
                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
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
                    String key = reset.getString(1);
                    String des = reset.getString(2);
//                    String stt = reset.getString("stt");

                    lstAllBcqt.add(new ListValue(key, des));
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
                CoreLogger.error(this.getClass().getName() + " getAllBcqt -> " + e.getMessage());
                throw new SQLException(e);
            }
        } catch (SQLException e) {
            System.err.println("Loi trong ham getAllBcqt " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " getAllBcqt -> " + e.getMessage());
            throw new SQLException(e);
        }
        return lstAllBcqt;
    }

    public List<ModelTreeNode> getDataPosTreeNode(Connection conn, String strUserName, String sGrade, String mabc) throws SQLException {
        List<ModelTreeNode> lstPo = new ArrayList<ModelTreeNode>();
        try {
//            DaoConnect daoconnect = new DaoConnect();
//            Connection conn = null;
//            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call VBSP_IMS_CHAMDIEMCN.SP_LOAD_POS_TREE_BCQT(?,?,?,?,?,?)}";
            ResultSet reset = null;

            try {
                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                //Tham so thu nhat truyen vao la co lay theo pos hay main pos
                calstatement.setString(1, strUserName);
                calstatement.setString(2, sGrade);
                calstatement.registerOutParameter(3, oracle.jdbc.OracleTypes.NUMBER);
                calstatement.registerOutParameter(4, oracle.jdbc.OracleTypes.VARCHAR);
                calstatement.registerOutParameter(5, oracle.jdbc.OracleTypes.CURSOR);
                calstatement.setString(6, mabc);
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
                    lstPo.add(new ModelTreeNode(reset.getString("PARENT_CD"), reset.getString("PARENT_DESC"),
                            reset.getString("CHILD_CD"), reset.getString("CHILD_DESC")));
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
                CoreLogger.error(this.getClass().getCanonicalName() + " getDataPosTreeNode -> " + e.getMessage());
                throw new SQLException(e);
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham getDataPosTreeNode " + e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " getDataPosTreeNode -> " + e.getMessage());
            throw new SQLException(e);
        }
        return lstPo;
    }

    public List<ReportParam> getReportParmams(Connection conn, String sKhoa, String sUserName, String sGrade) throws SQLException, Exception {
        ArrayList<ReportParam> report_param_list = new ArrayList<>();

        try {
//            DaoConnect daoconnect = new DaoConnect();
//            Connection conn ;
//            conn = daoconnect.getConnect();
            CallableStatement calstatement;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call VBSP_IMS_CHAMDIEMCN.SP_LOAD_PARA_BCQT(?, ?, ?, ?, ?,?,?)}";
            ResultSet rscur_params;
            ResultSet rs_combo;

            try {
                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce,
                        ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                //Tham so thu nhat truyen vao la co lay theo pos hay main pos    
                calstatement.setString(1, sKhoa);
                calstatement.setString(2, sUserName);
                calstatement.registerOutParameter(3, oracle.jdbc.OracleTypes.NUMBER);
                calstatement.registerOutParameter(4, oracle.jdbc.OracleTypes.VARCHAR);
                calstatement.registerOutParameter(5, oracle.jdbc.OracleTypes.CURSOR);
                calstatement.registerOutParameter(6, oracle.jdbc.OracleTypes.CURSOR);
                calstatement.setString(7, sGrade);

                //Thuc hien execute lay du lieu
                calstatement.execute();
                //Lay cursor ra resultset
                rscur_params = (ResultSet) calstatement.getObject(6);
                rs_combo = (ResultSet) calstatement.getObject(5);

                //Do toan bo du lieu tu Resultset vao array list
                List<Combo> combo_list_all = fillResultSetComboToArray(rs_combo);

                while (rscur_params.next()) {
                    ReportParam rp = new ReportParam();

                    rp.setType(rscur_params.getString("LOAITSO"));
                    rp.setFieldName(rscur_params.getString("THAMSO"));
                    rp.setLabel(rscur_params.getString("MOTA"));
                    rp.setOrderNumber(Integer.parseInt(rscur_params.getString("STT")));
                    rp.setAction(rscur_params.getString("DKSAPXEP"));
                    //Neu la kieu list
                    if (rp.getType().equalsIgnoreCase("L")) {
                        ArrayList<Combo> combo_list = new ArrayList<>(); //Loc cac cobo can thiet
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
//                if (conn != null) {
//                    conn.close();
//                }
            } catch (SQLException e) {
                System.err.println("Loi trong ham getReportParmams " + e.getMessage());
                CoreLogger.error(DaoLoadReportParams.class.getCanonicalName() + " getReportParmams  -> " + e.getMessage());
                throw new SQLException(e);
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham getColumnReportFast " + e.getMessage());
            CoreLogger.error(DaoLoadReportParams.class.getCanonicalName() + " getReportParmams  -> " + e.getMessage());
            throw new Exception(e);
        }
        return report_param_list;
    }

    //Desc: Chuyen du lieu combo ra Array de xu ly
    private List<Combo> fillResultSetComboToArray(ResultSet rs_combo) throws SQLException {

        ArrayList<Combo> combo_list = new ArrayList<Combo>();
        try {
            while (rs_combo.next()) {
                Combo cb = new Combo();

                cb.setKey(rs_combo.getString("PARA_KEY"));
                cb.setValue(rs_combo.getString("PARA_DESC"));
                cb.setFieldName(rs_combo.getString("PARA_FIELD_NAME"));

                combo_list.add(cb);
            }
        } catch (Exception e) {
            CoreLogger.error(DaoLoadReportParams.class.getCanonicalName() + " fillResultSetComboToArray  -> " + e.getMessage());
            throw new SQLException(e);
        }
        return combo_list;
    }

    public List<ListValue> getDmKhac(Connection conn, String Khoa) throws SQLException {
        List<ListValue> lstAllBcqt = new ArrayList<ListValue>();
        try {
//            DaoConnect daoconnect = new DaoConnect();
//            Connection conn = null;
//            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call VBSP_IMS_CHAMDIEMCN.SP_GET_DMKHAC(?,?,?,?)}";
            ResultSet reset = null;

            try {
                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                calstatement.registerOutParameter(2, oracle.jdbc.OracleTypes.NUMBER);
                calstatement.registerOutParameter(3, oracle.jdbc.OracleTypes.VARCHAR);
                calstatement.registerOutParameter(4, oracle.jdbc.OracleTypes.CURSOR);
                calstatement.setString(1, Khoa);
                //Thuc hien execute lay du lieu
                calstatement.execute();
                //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
                int pn_err_cd = calstatement.getInt(2);
                //thu hien lay mo ta loi
                String strEdd_txt = calstatement.getString(3);
                //Lay cursor ra resultset
                reset = (ResultSet) calstatement.getObject(4);
                while (reset.next()) {
                    String key = reset.getString(1);
                    String des = reset.getString(2);
//                    String stt = reset.getString("stt");

                    lstAllBcqt.add(new ListValue(key, key));
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
                CoreLogger.error(this.getClass().getName() + " getAllBcqt -> " + e.getMessage());
                throw new SQLException(e);
            }
        } catch (SQLException e) {
            System.err.println("Loi trong ham getAllBcqt " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " getAllBcqt -> " + e.getMessage());
            throw new SQLException(e);
        }
        return lstAllBcqt;
    }

    public int checkRuleUser_CN08AB(String UserName, String CapBC, String mabc) throws SQLException {
        int _retVal = 0;
        Connection conn = new DaoConnect().getConnect();
        CallableStatement calstatement = null;
        ResultSet reset = null;
        //Khoi tao procedure cung voi tham so truyen vao la dau ?
        String strStoreproce = "{ ? = call VBSP_IMS_CHAMDIEMCN.F_CHECK_RULE_USER_CN08AB(?, ? ,?) }";

        try {
            //Khoi tao goi Store
            calstatement = conn.prepareCall(strStoreproce,
                    ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            calstatement.registerOutParameter(1, oracle.jdbc.OracleTypes.NUMBER);
            calstatement.setString(2, UserName);
            calstatement.setString(3, CapBC);
            calstatement.setString(4, mabc);
            //Thuc hien execute lay du lieu
            calstatement.execute();
            //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
            _retVal = calstatement.getInt(1);

        } catch (SQLException e) {
            System.err.print(e.getMessage());
            CoreLogger.error(this.getClass().getName() + " checkUser -> " + e.getMessage());
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
        return _retVal;
    }

    public String getStatusInput_0607(String Capbc, String Khoa, String UserName, String ngaybc, String poscd, String phongcmnv) throws SQLException {
        String pos_cd = "";
        CallableStatement calstatement = null;
        ResultSet reset = null;
        DaoConnect daoconnect = new DaoConnect();
        Connection conn = null;
        conn = daoconnect.getConnect();
//        try {
        //Khoi tao procedure cung voi tham so truyen vao la dau ?
        String strStoreproce = "{?=call VBSP_IMS_CHAMDIEMCN.F_GET_STATUS_INPUT_0607(?,?,?,?,?,?)}";
        try {
            //Khoi tao goi store
            calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            calstatement.registerOutParameter(1, oracle.jdbc.OracleTypes.VARCHAR);
            calstatement.setString(2, Capbc);
            calstatement.setString(3, Khoa);
            calstatement.setString(4, UserName);
            calstatement.setString(5, ngaybc);
            calstatement.setString(6, poscd);
            calstatement.setString(7, phongcmnv);
            //Thuc hien execute lay du lieu
            calstatement.execute();
            //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
            pos_cd = calstatement.getString(1);

        } catch (SQLException e) {
            System.err.print(e.getMessage());
            CoreLogger.error(this.getClass().getName() + " isCheckPosSend -> " + e.getMessage());
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
        return pos_cd;
    }

    public List<QT_DULIEU_NT> getLANHDAODUYET_LOI_08AB(String sKhoa, String sNgaybc, String sUser,
            String sGrade, List<String> lstArrPoscd) throws SQLException {
        List<QT_DULIEU_NT> lstBcqt_NT = new ArrayList<QT_DULIEU_NT>();
        DaoConnect daoconnect = new DaoConnect();
        Connection conn = null;
        CallableStatement calstatement = null;
        //Khoi tao procedure cung voi tham so truyen vao la dau ?
        String strStoreproce = "{call VBSP_IMS_CHAMDIEMCN.SP_CHUADUYET_08AB(?,?,?,?,?,?)}";
        ResultSet reset = null;

        try {
            conn = daoconnect.getConnect();
            ArrayDescriptor des = ArrayDescriptor.createDescriptor("TREE_NT", conn);
            String[] arrayPoscd = lstArrPoscd.toArray(new String[0]);
            ARRAY oracle_arrayPoscd = new ARRAY(des, conn, arrayPoscd);
            //Khoi tao goi store
            calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            calstatement.registerOutParameter(6, oracle.jdbc.OracleTypes.CURSOR);
            calstatement.setString(1, sKhoa);
            calstatement.setString(2, sUser);
            calstatement.setString(3, sGrade);
            calstatement.setString(4, sNgaybc);
            calstatement.setArray(5, oracle_arrayPoscd);
            //Thuc hien execute lay du lieu
            calstatement.execute();
            //Lay cursor ra resultset
            reset = (ResultSet) calstatement.getObject(6);
            while (reset.next()) {

                QT_DULIEU_NT value = QT_DULIEU_NT.newInstance();
                value.setMACN(reset.getString(1));
                value.setTEN(reset.getString(2));
                value.setD1(reset.getString(3));
                value.setD2(reset.getString(4));
                lstBcqt_NT.add(value);
            }

        } catch (SQLException e) {
            System.err.print(e.getMessage());
            CoreLogger.error(this.getClass().getName() + " getHOIDONGCNChuaDuyet -> " + e.getMessage());
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

        return lstBcqt_NT;
    }

    public List<QT_DULIEU_NT> getDataCDTT_CN08TH(Connection conn, String sKhoa, String sNgaybc, String sUser,
            String sGrade, List<String> lstArrPoscd, String sTTCDTT) throws SQLException {
        List<QT_DULIEU_NT> lstBcqt_NT = new ArrayList<QT_DULIEU_NT>();
        try {
            ArrayDescriptor des = ArrayDescriptor.createDescriptor("TREE_NT", conn);
            String[] arrayPoscd = lstArrPoscd.toArray(new String[0]);
            ARRAY oracle_arrayPoscd = new ARRAY(des, conn, arrayPoscd);
//            DaoConnect daoconnect = new DaoConnect();
//            Connection conn = null;
//            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call VBSP_IMS_CHAMDIEMCN.SP_GET_DATA_CDTT_CN08TH(?,?,?,?,?,?,?,?,?)}";
            ResultSet reset = null;

            try {
                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                calstatement.registerOutParameter(5, oracle.jdbc.OracleTypes.NUMBER);
                calstatement.registerOutParameter(6, oracle.jdbc.OracleTypes.VARCHAR);
                calstatement.registerOutParameter(7, oracle.jdbc.OracleTypes.CURSOR);
                calstatement.setString(1, sKhoa);
                calstatement.setString(2, sUser);
                calstatement.setString(3, sGrade);
                calstatement.setString(4, sNgaybc);
                calstatement.setString(8, sTTCDTT);
                calstatement.setArray(9, oracle_arrayPoscd);
                //Thuc hien execute lay du lieu
                calstatement.execute();
                //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
                int pn_err_cd = calstatement.getInt(5);
                //thu hien lay mo ta loi
                String strEdd_txt = calstatement.getString(6);
                //Lay cursor ra resultset
                reset = (ResultSet) calstatement.getObject(7);
                while (reset.next()) {

                    QT_DULIEU_NT value = QT_DULIEU_NT.newInstance();
                    value.setKHOA(reset.getString(1));
                    value.setTHUTU(reset.getInt(2));
                    value.setTT_HIENTHI(reset.getString(3));
                    value.setMA(reset.getString(4));
                    value.setTEN(reset.getString(5));
                    value.setNGAYBC(reset.getDate(6));
                    value.setNAMBC(reset.getInt(7));
                    value.setMAPGD(reset.getString(8));
                    value.setCO_TONGHOP(reset.getString(9));
                    value.setMACN(reset.getString(10));
                    value.setNGUOI_NHAP(reset.getString(11));
                    value.setNGAY_NHAP(reset.getDate(12));
//                    value.setD1(reset.getString(14));
                    value.setD1(reset.getString(15));
                    value.setD2(reset.getString(16));
                    value.setD3(reset.getString(17));
                    value.setD4(reset.getString(18));
                    value.setD5(reset.getString(19));
                    value.setD6(reset.getString(20));
                    value.setD7(reset.getString(21));
                    value.setD8(reset.getString(22));
                    value.setD9(reset.getString(23));
                    value.setD10(reset.getString(24));
                    value.setD11(reset.getString(25));
                    value.setD12(reset.getString(26));
                    value.setD13(reset.getString(27));
                    value.setD14(reset.getString(28));
                    value.setD15(reset.getString(29));
                    value.setD16(reset.getString(30));
                    value.setD17(reset.getString(31));
                    value.setD18(reset.getString(32));

                    value.setD19(reset.getString(33));
                    value.setD20(reset.getString(34));
                    value.setD21(reset.getString(35));
                    value.setD22(reset.getString(36));
                    value.setD23(reset.getString(37));
                    value.setD24(reset.getString(38));
                    value.setD25(reset.getString(39));
                    value.setD26(reset.getString(40));
                    value.setD27(reset.getString(41));
                    value.setD28(reset.getString(42));
                    value.setD29(reset.getString(43));
                    value.setD30(reset.getString(44));
                    value.setNHAPTAY(reset.getString(45));
                    value.setFONTFORMAT(reset.getString(46));
                    value.setD31(reset.getString(47));
                    value.setD32(reset.getString(48));

                    lstBcqt_NT.add(value);
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
                CoreLogger.error(this.getClass().getName() + " getDataBCQT_NT -> " + e.getMessage());
                throw new SQLException(e);
            }
        } catch (SQLException e) {
            System.err.println("Loi trong ham getDataBCQT_NT " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " getDataBCQT_NT -> " + e.getMessage());
            throw new SQLException(e);
        }
        return lstBcqt_NT;
    }

    public boolean saveCDTT_CMNV08AB(String khoa, String username, String mapgd, String ngaybc, List<QT_DULIEU_NT> lstData, String capbc, String phongban, List<String> lstArrPoscd) throws SQLException {
        Connection connection = new DaoConnect().getConnect();
//        java.util.Dictionary map = (java.util.Dictionary) (connection.getTypeMap());
        Connection conn = new DaoConnect().getConnect();
        ArrayDescriptor despos = ArrayDescriptor.createDescriptor("TREE_NT", conn);
        String[] arrayPoscd = lstArrPoscd.toArray(new String[0]);
        ARRAY oracle_arrayPoscd = new ARRAY(despos, conn, arrayPoscd);

        Object array[] = lstData.toArray();
        ArrayDescriptor des = ArrayDescriptor
                .createDescriptor(QT_DULIEU_NT.ORACLE_TABLE_TYPE, connection);
        ARRAY array_to_pass = new ARRAY(des, connection, array);
        CallableStatement cs = null;
        try {
            cs = connection.prepareCall("{call VBSP_IMS_CHAMDIEMCN.SP_SAVE_CDCN_08TH(?, ?, ?, ?, ?, ?, ?, ?)}");
            cs.setString(1, khoa);
            cs.setString(2, username);
            cs.setString(3, mapgd);
            cs.setString(4, ngaybc);
            cs.setArray(5, array_to_pass);
            cs.setString(6, capbc);
            cs.setString(7, phongban);
            cs.setArray(8, oracle_arrayPoscd);
            cs.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Loi trong ham saveCDTT_CMNV08AB " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " saveCDTT_CMNV08AB -> " + e.getMessage());
            return false;
        } finally {
            if (cs != null) {
                cs.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return true;
    }

    public List<QT_DULIEU_NT> get_DetailCT(Connection conn, String sKhoa, String sNgaybc, String sUser,
            String sGrade, List<String> lstArrPoscd, String sID) throws SQLException {
        List<QT_DULIEU_NT> lstBcqt_NT = new ArrayList<QT_DULIEU_NT>();
        try {
            ArrayDescriptor des = ArrayDescriptor.createDescriptor("POS_CD", conn);
            String[] arrayPoscd = lstArrPoscd.toArray(new String[0]);
            ARRAY oracle_arrayPoscd = new ARRAY(des, conn, arrayPoscd);
//            DaoConnect daoconnect = new DaoConnect();
//            Connection conn = null;
//            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call VBSP_IMS_CHAMDIEMCN.SP_GET_DETAILCT(?,?,?,?,?,?,?,?,?)}";
            ResultSet reset = null;

            try {
                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                calstatement.registerOutParameter(6, oracle.jdbc.OracleTypes.NUMBER);
                calstatement.registerOutParameter(7, oracle.jdbc.OracleTypes.VARCHAR);
                calstatement.registerOutParameter(8, oracle.jdbc.OracleTypes.CURSOR);
                calstatement.setString(1, sKhoa);
                calstatement.setString(2, sUser);
                calstatement.setString(3, sGrade);
                calstatement.setString(4, sNgaybc);
                calstatement.setArray(5, oracle_arrayPoscd);
                calstatement.setString(9, sID);
                //Thuc hien execute lay du lieu
                calstatement.execute();
                //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
                int pn_err_cd = calstatement.getInt(6);
                //thu hien lay mo ta loi
                String strEdd_txt = calstatement.getString(7);
                //Lay cursor ra resultset
                reset = (ResultSet) calstatement.getObject(8);
                while (reset.next()) {

                    QT_DULIEU_NT value = QT_DULIEU_NT.newInstance();
                    value.setKHOA(reset.getString(1));
                    value.setTHUTU(reset.getInt(2));
                    value.setTT_HIENTHI(reset.getString(3));
                    value.setMA(reset.getString(4));
                    value.setTEN(reset.getString(5));
                    value.setNGAYBC(reset.getDate(6));
                    value.setNAMBC(reset.getInt(7));
                    value.setMAPGD(reset.getString(8));
                    value.setMACN(reset.getString(10));
                    value.setCO_TONGHOP(reset.getString(9));
//                    value.setD1(reset.getString(14));
                    value.setD1(reset.getString(15));
                    value.setD2(reset.getString(16));
                    value.setD3(reset.getString(17));
                    value.setD4(reset.getString(18));
                    value.setD5(reset.getString(19));
                    value.setD6(reset.getString(20));
                    value.setD7(reset.getString(21));
                    value.setD8(reset.getString(22));
                    value.setD9(reset.getString(23));
                    value.setD10(reset.getString(24));
                    value.setD11(reset.getString(25));
                    value.setD12(reset.getString(26));
                    value.setD13(reset.getString(27));
                    value.setD14(reset.getString(28));
                    value.setD15(reset.getString(29));
                    value.setD16(reset.getString(30));
                    value.setD17(reset.getString(31));
                    value.setD18(reset.getString(32));
                    value.setNHAPTAY(reset.getString(45));
                    value.setFONTFORMAT(reset.getString(46));

                    lstBcqt_NT.add(value);
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
                CoreLogger.error(this.getClass().getName() + " get_BC00230034 -> " + e.getMessage());
                throw new SQLException(e);
            }
        } catch (SQLException e) {
            System.err.println("Loi trong ham get_BC00230034 " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " get_BC00230034 -> " + e.getMessage());
            throw new SQLException(e);
        }
        return lstBcqt_NT;
    }

    public HashMap<String, String> getQueryTableDetail(String khoa, String ngaybc, String mapgd, String mact, String username, String Capbc, String macb) throws SQLException, Exception {
        String outtable = "", thuyetminh = "", tenchitieu = "";
        Connection conn = null;
        ResultSet reset = null;
        CallableStatement calstatement = null;
        HashMap<String, String> mapvalue = new HashMap<String, String>();
        try {
            conn = new DaoConnect().getConnect();

            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call VBSP_IMS_CHAMDIEMCN.sp_get_table_detail(?,?,?,?,?,?,?,?,?,?)}";
            ArrayDescriptor des = ArrayDescriptor.createDescriptor("POS_CD", conn);
            //Khoi tao goi store
            calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            calstatement.registerOutParameter(8, oracle.jdbc.OracleTypes.CURSOR);
            calstatement.registerOutParameter(9, oracle.jdbc.OracleTypes.CLOB);
            calstatement.registerOutParameter(10, oracle.jdbc.OracleTypes.VARCHAR);
            calstatement.setString(1, khoa);
            calstatement.setString(2, ngaybc);
            calstatement.setString(3, mapgd);
            calstatement.setString(4, mact);
            calstatement.setString(5, username);
            calstatement.setString(6, Capbc);
            calstatement.setString(7, macb);
            //Thuc hien execute lay du lieu
            calstatement.execute();
            reset = (ResultSet) calstatement.getObject(8);
            Clob clob = calstatement.getClob(9);
            tenchitieu = calstatement.getString(10);
            //Lay cursor ra resultset
            //Get du lieu tra ra tham so thu 1
            if (clob != null) {
                thuyetminh = clob.getSubString(1, (int) clob.length());
                clob.free();

            }
            ResultSetMetaData resetMetaData = reset.getMetaData();
            String Tieude = "<tr>";
            for (int i = 0; i < resetMetaData.getColumnCount(); i++) {
                Tieude += "<th data-f-bold=\"true\">" + resetMetaData.getColumnName(i + 1) + "</th>";
            }
            Tieude += "</tr>";
            String DetailData = "";
            while (reset.next()) {
                DetailData = DetailData + "<tr>";
                for (int i = 0; i < resetMetaData.getColumnCount(); i++) {
                    String value = reset.getString(i + 1);
                    String col = value == null ? "" : value;
                    String strDataType = resetMetaData.getColumnTypeName(i + 1);
                    if (strDataType.equals("NUMBER")) {
                        col = col.isEmpty() ? "0" : col;
                        DetailData = DetailData + "<td data-t=\"n\">" + col + "</td>";
                    } else {
                        DetailData = DetailData + "<td>" + col + "</td>";
                    }
                }
                DetailData = DetailData + "</tr>";
            }

            outtable = Tieude + DetailData;

            mapvalue.put("DETAILDATA", outtable);
            mapvalue.put("THUYETMINH", thuyetminh);
            mapvalue.put("TENCHITIEU", tenchitieu);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            System.err.println("Doi chieu, Phan loai no -> Loi trong ham getQueryTableDetail " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " getQueryTableDetail -> " + e.getMessage());
            throw new Exception(e);
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
        return mapvalue;
    }

    public List<QT_DULIEU_NT> get_chitieudiemtru(String sKhoa, String sUser, String sNgaybc,
            String macb) throws SQLException, Exception {
        List<QT_DULIEU_NT> lstBcqt_NT = new ArrayList<QT_DULIEU_NT>();
        Connection conn = null;
        ResultSet reset = null;
        CallableStatement calstatement = null;
        try {

            DaoConnect daoconnect = new DaoConnect();
            conn = daoconnect.getConnect();

            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call VBSP_IMS_CHAMDIEMCN.SP_GET_DATA_DIEMTRU(?,?,?,?,?)}";

            //Khoi tao goi store
            calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            calstatement.registerOutParameter(5, oracle.jdbc.OracleTypes.CURSOR);
            calstatement.setString(1, sKhoa);
            calstatement.setString(2, sUser);
            calstatement.setString(3, sNgaybc);
            calstatement.setString(4, macb);

            //Thuc hien execute lay du lieu
            calstatement.execute();

            //Lay cursor ra resultset
            reset = (ResultSet) calstatement.getObject(5);
            while (reset.next()) {

                QT_DULIEU_NT value = QT_DULIEU_NT.newInstance();
                value.setKHOA(reset.getString(1));
                value.setTHUTU(reset.getInt(2));
                value.setTT_HIENTHI(reset.getString(3));
                value.setMA(reset.getString(4));
                value.setTEN(reset.getString(5));
                value.setNGAYBC(reset.getDate(6));
                value.setNAMBC(reset.getInt(7));
                value.setMAPGD(reset.getString(8));
                value.setMACN(reset.getString(10));
                value.setCO_TONGHOP(reset.getString(9));
//                    value.setD1(reset.getString(14));
                value.setD1(reset.getString(15));
                value.setD2(reset.getString(16));
                value.setD3(reset.getString(17));
                value.setD4(reset.getString(18));
                value.setD5(reset.getString(19));
                value.setD6(reset.getString(20));
                value.setD7(reset.getString(21));
                value.setD8(reset.getString(22));
                value.setD9(reset.getString(23));
                value.setD10(reset.getString(24));
                value.setD11(reset.getString(25));
                value.setD12(reset.getString(26));
                value.setD13(reset.getString(27));
                value.setD14(reset.getString(28));
                value.setD15(reset.getString(29));
                value.setD16(reset.getString(30));
                value.setD17(reset.getString(31));
                value.setD18(reset.getString(32));

                value.setD19(reset.getString(33));
                value.setD20(reset.getString(34));
                value.setD21(reset.getString(35));
                value.setD22(reset.getString(36));
                value.setD23(reset.getString(37));
                value.setD24(reset.getString(38));
                value.setD25(reset.getString(39));
                value.setD26(reset.getString(40));
                value.setD27(reset.getString(41));
                value.setD28(reset.getString(42));
                value.setD29(reset.getString(43));
                value.setD30(reset.getString(44));
                value.setNHAPTAY(reset.getString(45));
                value.setFONTFORMAT(reset.getString(46));

                lstBcqt_NT.add(value);
            }

        } catch (SQLException e) {
            System.err.println("Loi trong ham get_BC00230034 " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " get_BC00230034 -> " + e.getMessage());
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
        return lstBcqt_NT;
    }

    public boolean saveCDTT_DIEMTRU(String khoa, String username, String mapgd, String ngaybc, List<QT_DULIEU_NT> lstData, String capbc, String phongban, List<String> lstArrPoscd) throws SQLException {
        Connection connection = new DaoConnect().getConnect();
//        java.util.Dictionary map = (java.util.Dictionary) (connection.getTypeMap());
        Connection conn = new DaoConnect().getConnect();
        ArrayDescriptor despos = ArrayDescriptor.createDescriptor("TREE_NT", conn);
        String[] arrayPoscd = lstArrPoscd.toArray(new String[0]);
        ARRAY oracle_arrayPoscd = new ARRAY(despos, conn, arrayPoscd);

        Object array[] = lstData.toArray();
        ArrayDescriptor des = ArrayDescriptor
                .createDescriptor(QT_DULIEU_NT.ORACLE_TABLE_TYPE, connection);
        ARRAY array_to_pass = new ARRAY(des, connection, array);
        CallableStatement cs = null;
        try {
            cs = connection.prepareCall("{call VBSP_IMS_CHAMDIEMCN.SP_SAVE_CDCN_DIEMTRU(?, ?, ?, ?, ?, ?, ?, ?)}");
            cs.setString(1, khoa);
            cs.setString(2, username);
            cs.setString(3, mapgd);
            cs.setString(4, ngaybc);
            cs.setArray(5, array_to_pass);
            cs.setString(6, capbc);
            cs.setString(7, phongban);
            cs.setArray(8, oracle_arrayPoscd);
            cs.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Loi trong ham SP_SAVE_BCQT_M06A " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " SP_SAVE_BCQT_M06 -> " + e.getMessage());
            return false;
        } finally {
            if (cs != null) {
                cs.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return true;
    }

    public List<QT_DULIEU_NT> viewAllStatus(String sKhoa, String sNgaybc, String sUser,
            String sGrade, List<String> lstArrPoscd) throws SQLException {
        List<QT_DULIEU_NT> lstBcqt_NT = new ArrayList<QT_DULIEU_NT>();
        DaoConnect daoconnect = new DaoConnect();
        Connection conn = null;
        CallableStatement calstatement = null;
        //Khoi tao procedure cung voi tham so truyen vao la dau ?
        String strStoreproce = "{call VBSP_IMS_CHAMDIEMCN.SP_ViewALLStatus(?,?,?,?,?,?)}";
        ResultSet reset = null;

        try {
            conn = daoconnect.getConnect();
            ArrayDescriptor des = ArrayDescriptor.createDescriptor("TREE_NT", conn);
            String[] arrayPoscd = lstArrPoscd.toArray(new String[0]);
            ARRAY oracle_arrayPoscd = new ARRAY(des, conn, arrayPoscd);
            //Khoi tao goi store
            calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            calstatement.registerOutParameter(6, oracle.jdbc.OracleTypes.CURSOR);
            calstatement.setString(1, sKhoa);
            calstatement.setString(2, sUser);
            calstatement.setString(3, sGrade);
            calstatement.setString(4, sNgaybc);
            calstatement.setArray(5, oracle_arrayPoscd);
            //Thuc hien execute lay du lieu
            calstatement.execute();
            //Lay cursor ra resultset
            reset = (ResultSet) calstatement.getObject(6);
            while (reset.next()) {

                QT_DULIEU_NT value = QT_DULIEU_NT.newInstance();
                value.setMACN(reset.getString(1));
                value.setTEN(reset.getString(2));
                value.setD1(reset.getString(3));
                value.setD2(reset.getString(4));
                value.setD3(reset.getString(5));
                value.setD4(reset.getString(6));
                value.setD5(reset.getString(7));
                lstBcqt_NT.add(value);
            }

        } catch (SQLException e) {
            System.err.print(e.getMessage());
            CoreLogger.error(this.getClass().getName() + " getHOIDONGCNChuaDuyet -> " + e.getMessage());
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

        return lstBcqt_NT;
    }

    public String getMabcByUser(String UserName, String CapBC) throws SQLException {
        String _retVal = "";
        Connection conn = new DaoConnect().getConnect();
        CallableStatement calstatement = null;
        ResultSet reset = null;
        //Khoi tao procedure cung voi tham so truyen vao la dau ?
        String strStoreproce = "{ ? = call VBSP_IMS_CHAMDIEMCN.F_GET_MACB(?, ? ) }";

        try {
            //Khoi tao goi Store
            calstatement = conn.prepareCall(strStoreproce,
                    ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            calstatement.registerOutParameter(1, oracle.jdbc.OracleTypes.VARCHAR);
            calstatement.setString(2, CapBC);
            calstatement.setString(3, UserName);
            //Thuc hien execute lay du lieu
            calstatement.execute();
            //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
            _retVal = calstatement.getString(1);

        } catch (SQLException e) {
            System.err.print(e.getMessage());
            CoreLogger.error(this.getClass().getName() + " checkUser -> " + e.getMessage());
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
        return _retVal;
    }

    public String getNhomNVByUser(String UserName, String CapBC) throws SQLException {
        String _retVal = "";
        Connection conn = new DaoConnect().getConnect();
        CallableStatement calstatement = null;
        ResultSet reset = null;
        //Khoi tao procedure cung voi tham so truyen vao la dau ?
        String strStoreproce = "{ ? = call VBSP_IMS_CHAMDIEMCN.F_GET_NHOMNV(?, ? ) }";

        try {
            //Khoi tao goi Store
            calstatement = conn.prepareCall(strStoreproce,
                    ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            calstatement.registerOutParameter(1, oracle.jdbc.OracleTypes.VARCHAR);
            calstatement.setString(2, CapBC);
            calstatement.setString(3, UserName);
            //Thuc hien execute lay du lieu
            calstatement.execute();
            //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
            _retVal = calstatement.getString(1);

        } catch (SQLException e) {
            System.err.print(e.getMessage());
            CoreLogger.error(this.getClass().getName() + " checkUser -> " + e.getMessage());
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
        return _retVal;
    }

    public String getNhomNVByUser_Duyet(String UserName, String CapBC, String maCB) throws SQLException {
        String _retVal = "";
        Connection conn = new DaoConnect().getConnect();
        CallableStatement calstatement = null;
        ResultSet reset = null;
        //Khoi tao procedure cung voi tham so truyen vao la dau ?
        String strStoreproce = "{ ? = call VBSP_IMS_CHAMDIEMCN.F_GET_NHOMNV_DUYET(?, ?, ? ) }";

        try {
            //Khoi tao goi Store
            calstatement = conn.prepareCall(strStoreproce,
                    ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            calstatement.registerOutParameter(1, oracle.jdbc.OracleTypes.VARCHAR);
            calstatement.setString(2, CapBC);
            calstatement.setString(3, UserName);
            calstatement.setString(4, maCB);
            //Thuc hien execute lay du lieu
            calstatement.execute();
            //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
            _retVal = calstatement.getString(1);

        } catch (SQLException e) {
            System.err.print(e.getMessage());
            CoreLogger.error(this.getClass().getName() + " checkUser -> " + e.getMessage());
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
        return _retVal;
    }

    public List<QT_DULIEU_NT> getDataPL01(Connection conn, String sKhoa, String sNgaybc, String sUser,
            String sGrade, List<String> lstArrPoscd, int iStart, int iEnd, String sTeler, String sotk) {
        List<QT_DULIEU_NT> lstBcqt_NT = new ArrayList<QT_DULIEU_NT>();
        try {
            ArrayDescriptor des = ArrayDescriptor.createDescriptor("POS_CD", conn);
            String[] arrayPoscd = lstArrPoscd.toArray(new String[0]);
            ARRAY oracle_arrayPoscd = new ARRAY(des, conn, arrayPoscd);
//            DaoConnect daoconnect = new DaoConnect();
//            Connection conn = null;
//            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call VBSP_IMS_CHAMDIEMCN.SP_GET_DATA_PL01(?,?,?,?,?,?,?,?,?,?,?,?)}";
            ResultSet reset = null;

            try {
                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                calstatement.registerOutParameter(9, oracle.jdbc.OracleTypes.NUMBER);
                calstatement.registerOutParameter(10, oracle.jdbc.OracleTypes.VARCHAR);
                calstatement.registerOutParameter(11, oracle.jdbc.OracleTypes.CURSOR);
                calstatement.setString(1, sKhoa);
                calstatement.setString(2, sUser);
                calstatement.setString(3, sGrade);
                calstatement.setString(4, sNgaybc);
                calstatement.setArray(5, oracle_arrayPoscd);
                calstatement.setInt(6, iStart);
                calstatement.setInt(7, iEnd);
                calstatement.setString(8, sTeler);
                calstatement.setString(12, sotk);
                //Thuc hien execute lay du lieu
                calstatement.execute();
                //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
                int pn_err_cd = calstatement.getInt(9);
                //thu hien lay mo ta loi
                String strEdd_txt = calstatement.getString(10);
                //Lay cursor ra resultset
                reset = (ResultSet) calstatement.getObject(11);
                while (reset.next()) {

                    QT_DULIEU_NT value = QT_DULIEU_NT.newInstance();
                    value.setKHOA(reset.getString("KHOA"));
//                    value.setTHUTU(reset.getInt("THUTU"));
//                    value.setTT_HIENTHI(reset.getString("TT_HIENTHI"));
//                    value.setMA(reset.getString("MA"));
//                    value.setTEN(reset.getString("TEN"));
//                    value.setNGAYBC(reset.getDate("NGAYBC"));
//                    value.setNAMBC(reset.getInt("NAMBC"));
                    value.setMAPGD(reset.getString("MAPGD"));
//                    value.setCO_TONGHOP(reset.getString("CO_TONGHOP"));
                    value.setMACN(reset.getString("MACN"));
//                    value.setNGUOI_NHAP(reset.getString("NGUOI_NHAP"));
//                    value.setNGAY_NHAP(reset.getDate("NGAY_NHAP"));
//                    value.setNGUOI_DUYET(reset.getString("NGUOI_DUYET"));
//                    value.setNGAY_DUYET(reset.getDate("NGAY_DUYET"));
                    value.setD1(reset.getString("D1"));
                    value.setD2(reset.getString("D2"));
                    value.setD3(reset.getString("D3"));
                    value.setD4(reset.getString("D4"));
                    value.setD5(reset.getString("D5"));
                    value.setD6(reset.getString("D6"));
                    value.setD7(reset.getString("D7"));
                    value.setD8(reset.getString("D8"));
                    value.setD9(reset.getString("D9"));
                    value.setD10(reset.getString("D10"));
                    value.setD11(reset.getString("D11"));
                    value.setD12(reset.getString("D12"));
                    value.setD13(reset.getString("D13"));
                    value.setD14(reset.getString("D14"));
                    value.setD15(reset.getString("D15"));
                    value.setD16(reset.getString("D16"));
                    value.setD17(reset.getString("D17"));
                    value.setD18(reset.getString("D18"));
                    value.setD19(reset.getString("D19"));
                    value.setD20(reset.getString("D20"));

                    lstBcqt_NT.add(value);
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
                CoreLogger.error(this.getClass().getName() + " getDataNhaptaycn_01 -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham getDataNhaptaycn_01 " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " getDataNhaptaycn_01 -> " + e.getMessage());
        }
        return lstBcqt_NT;
    }

    public boolean savePl01(String sUserName, String sNgaysl, List<QT_DULIEU_NT.saveDulieuNT> lstNt, String sTeler) {
        if (lstNt == null || lstNt.size() == 0) {
            return false;
        }

        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{ call VBSP_IMS_CHAMDIEMCN.SP_SAVE_PL01(?,?,?,?,?,?)}";
            ResultSet reset = null;
            StructDescriptor structDescriptor = StructDescriptor.createDescriptor("NHAPTAY_TYPE", conn);

            STRUCT[] structs = null;
            structs = new STRUCT[lstNt.size()];
            int index = 0;
            for (QT_DULIEU_NT.saveDulieuNT value : lstNt) {
                Object[] params = new Object[3];
                params[0] = value.getD2();
                params[1] = value.getD13();
                params[2] = value.getD14();
                STRUCT struct = new STRUCT(structDescriptor,
                        conn, params);
                structs[index] = struct;
                index++;
            }
            //Khoi tao goi store
            calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);

            ArrayDescriptor desc = ArrayDescriptor.createDescriptor(
                    "NHAPTAY_TAB", calstatement.getConnection());
            ARRAY oracleArray = new ARRAY(desc, calstatement.getConnection(), structs);

            //Tham so thu nhat truyen vao la co lay theo pos hay main pos
            calstatement.setString(1, sUserName);
            calstatement.setString(2, sNgaysl);
            calstatement.setArray(3, oracleArray);
            calstatement.setString(4, sTeler);

            calstatement.registerOutParameter(5, oracle.jdbc.OracleTypes.NUMBER);
            calstatement.registerOutParameter(6, oracle.jdbc.OracleTypes.VARCHAR);
            //Thuc hien execute lay du lieu
            calstatement.execute();
            int pn_err_cd = calstatement.getInt(5);
            //thu hien lay mo ta loi
            String strEdd_txt = calstatement.getString(6);
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
            System.err.println("Loi trong ham saveNhaptaycn01 " + e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " saveNhaptaycn01 -> " + e.getMessage());
            return false;
        }
        return true;
    }

    public List<ListValue> getLOV(String username, String type, String capbc, String sNgaybc) {
        List<ListValue> lstDMNgNhan = new ArrayList<ListValue>();
        try {

            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call VBSP_IMS_CHAMDIEMcn.SP_GET_LOV(?, ?, ?,?,?,?,?)}";
            ResultSet reset = null;
            try {
                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                calstatement.setString(1, username);
                calstatement.setString(2, type);
                calstatement.setString(6, capbc);
                calstatement.setString(7, sNgaybc);

                calstatement.registerOutParameter(3, oracle.jdbc.OracleTypes.NUMBER);
                calstatement.registerOutParameter(4, oracle.jdbc.OracleTypes.VARCHAR);
                calstatement.registerOutParameter(5, oracle.jdbc.OracleTypes.CURSOR);
                //Thuc hien execute lay du lieu
                calstatement.execute();
                //Lấy mã hiệu giá trị lỗi (Nếu có) của thủ tục (procedure)
                int iErr_CD = calstatement.getInt(3);
                //Lấy nội dung thông báo lỗi (Nếu có) của thủ tục (procedure)
                String sEdd_TXT = calstatement.getString(4);
                //Lay cursor ra resultset
                reset = (ResultSet) calstatement.getObject(5);
                while (reset.next()) {
                    String key = reset.getString(1);
                    String des = reset.getString(2);
                    lstDMNgNhan.add(new ListValue(key, des));
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
                CoreLogger.error(this.getClass().getName() + " getLOV -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Doi chieu, Phan loai no -> Loi trong ham getLOV " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " getLOV -> " + e.getMessage());
        }
        return lstDMNgNhan;
    }

    public String loadDataTotal(String Khoa, String teler, String username, List<String> lstMapgd) throws SQLException {
        List<QT_DULIEU_NT> lstBcqt_NT = new ArrayList<QT_DULIEU_NT>();
        DaoConnect daoconnect = new DaoConnect();
        Connection conn = null;
        conn = daoconnect.getConnect();
        String sCountTotalCust = "";
        try {
            ArrayDescriptor des = ArrayDescriptor.createDescriptor("POS_CD", conn);
            String[] arrayPoscd = lstMapgd.toArray(new String[0]);
            ARRAY oracle_arrayPoscd = new ARRAY(des, conn, arrayPoscd);
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{?=call vbsp_ims_chamdiemcn.f_get_casa_teler(?,?,?,?)}";
            ResultSet reset = null;

            calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            calstatement.registerOutParameter(1, oracle.jdbc.OracleTypes.VARCHAR);
            //Tham so thu nhat truyen vao la co lay theo pos hay main pos
            calstatement.setString(2, Khoa);
            calstatement.setString(3, teler);
            calstatement.setString(4, username);
            calstatement.setArray(5, oracle_arrayPoscd);
            calstatement.execute();
            //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
            sCountTotalCust = calstatement.getString(1);
            if (reset != null) {
                reset.close();
            }
            if (calstatement != null) {
                calstatement.close();
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham loadDataTotal " + e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " loadDataTotal -> " + e.getMessage());
        }
        return sCountTotalCust;
    }

    public List<ListValue> getAllBcConfig(String sGrade, String sGroup) {
        List<ListValue> lstAllBcqt = new ArrayList<ListValue>();
        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call VBSP_IMS_CHAMDIEMCN.SP_LOAD_ALL_BC_CONFIG(?,?,?,?,?)}";
            ResultSet reset = null;

            try {
                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                calstatement.registerOutParameter(1, oracle.jdbc.OracleTypes.NUMBER);
                calstatement.registerOutParameter(2, oracle.jdbc.OracleTypes.VARCHAR);
                calstatement.registerOutParameter(3, oracle.jdbc.OracleTypes.CURSOR);
                calstatement.setString(4, sGrade);
                calstatement.setString(5, sGroup);
                //Thuc hien execute lay du lieu
                calstatement.execute();
                //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
                int pn_err_cd = calstatement.getInt(1);
                //thu hien lay mo ta loi
                String strEdd_txt = calstatement.getString(2);
                //Lay cursor ra resultset
                reset = (ResultSet) calstatement.getObject(3);
                while (reset.next()) {
                    String key = reset.getString(1);
                    String des = reset.getString(2);
//                    String stt = reset.getString("stt");

                    lstAllBcqt.add(new ListValue(key, des));
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
                CoreLogger.error(this.getClass().getName() + " getAllBcqt -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham getAllBcqt " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " getAllBcqt -> " + e.getMessage());
        }
        return lstAllBcqt;
    }

    public List<ListValue> getAllBcUpload(String sGrade, String sGroup) {
        List<ListValue> lstAllBcqt = new ArrayList<ListValue>();
        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call VBSP_IMS_CHAMDIEMCN.SP_LOAD_ALL_BC_UPLOAD(?,?,?,?,?)}";
            ResultSet reset = null;

            try {
                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                calstatement.registerOutParameter(1, oracle.jdbc.OracleTypes.NUMBER);
                calstatement.registerOutParameter(2, oracle.jdbc.OracleTypes.VARCHAR);
                calstatement.registerOutParameter(3, oracle.jdbc.OracleTypes.CURSOR);
                calstatement.setString(4, sGrade);
                calstatement.setString(5, sGroup);
                //Thuc hien execute lay du lieu
                calstatement.execute();
                //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
                int pn_err_cd = calstatement.getInt(1);
                //thu hien lay mo ta loi
                String strEdd_txt = calstatement.getString(2);
                //Lay cursor ra resultset
                reset = (ResultSet) calstatement.getObject(3);
                while (reset.next()) {
                    String key = reset.getString(1);
                    String des = reset.getString(2);
//                    String stt = reset.getString("stt");

                    lstAllBcqt.add(new ListValue(key, des));
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
                CoreLogger.error(this.getClass().getName() + " getAllBcqt -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham getAllBcqt " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " getAllBcqt -> " + e.getMessage());
        }
        return lstAllBcqt;
    }

    public List<QT_DULIEU_NT> getDataFonfigChitieu(Connection conn, String sKhoa) {
        List<QT_DULIEU_NT> lstBcqt_NT = new ArrayList<QT_DULIEU_NT>();
        try {
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call VBSP_IMS_CHAMDIEMCN.SP_GET_DATA_CONFIG(?,?,?,?)}";
            ResultSet reset = null;

            try {
                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                calstatement.registerOutParameter(2, oracle.jdbc.OracleTypes.NUMBER);
                calstatement.registerOutParameter(3, oracle.jdbc.OracleTypes.VARCHAR);
                calstatement.registerOutParameter(4, oracle.jdbc.OracleTypes.CURSOR);
                calstatement.setString(1, sKhoa);
                //Thuc hien execute lay du lieu
                calstatement.execute();
                //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
                int pn_err_cd = calstatement.getInt(2);
                //thu hien lay mo ta loi
                String strEdd_txt = calstatement.getString(3);
                //Lay cursor ra resultset
                reset = (ResultSet) calstatement.getObject(4);
                while (reset.next()) {

                    QT_DULIEU_NT value = QT_DULIEU_NT.newInstance();
                    value.setKHOA(reset.getString(1));
                    value.setTHUTU(reset.getInt(2));
                    value.setTT_HIENTHI(reset.getString(3));
                    value.setMA(reset.getString(4));
                    value.setTEN(reset.getString(5));
                    value.setNGAYBC(reset.getDate(6));
                    value.setNAMBC(reset.getInt(7));
                    value.setMAPGD(reset.getString(8));
                    value.setCO_TONGHOP(reset.getString(9));
                    value.setMACN(reset.getString(10));
                    value.setNGUOI_NHAP(reset.getString(11));
                    value.setNGAY_NHAP(reset.getDate(12));
//                    value.setD1(reset.getString(14));
                    value.setD1(reset.getString(15));
                    value.setD2(reset.getString(16));
                    value.setD3(reset.getString(17));
                    value.setD4(reset.getString(18));
                    value.setD5(reset.getString(19));
                    value.setD6(reset.getString(20));
                    value.setD7(reset.getString(21));
                    value.setD8(reset.getString(22));
                    value.setD9(reset.getString(23));
                    value.setD10(reset.getString(24));
                    value.setD11(reset.getString(25));
                    value.setD12(reset.getString(26));
                    value.setD13(reset.getString(27));
                    value.setD14(reset.getString(28));
                    value.setD15(reset.getString(29));
                    value.setD16(reset.getString(30));
                    value.setD17(reset.getString(31));
                    value.setD18(reset.getString(32));

                    value.setD19(reset.getString(33));
                    value.setD20(reset.getString(34));
                    value.setD21(reset.getString(35));
                    value.setD22(reset.getString(36));
                    value.setD23(reset.getString(37));
                    value.setD24(reset.getString(38));
                    value.setD25(reset.getString(39));
                    value.setD26(reset.getString(40));
                    value.setD27(reset.getString(41));
                    value.setD28(reset.getString(42));
                    value.setD29(reset.getString(43));
                    value.setD30(reset.getString(44));
                    value.setNHAPTAY(reset.getString(45));
                    value.setFONTFORMAT(reset.getString(46));

                    lstBcqt_NT.add(value);
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
                CoreLogger.error(this.getClass().getName() + " getDataBCQT_NT -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham getDataBCQT_NT " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " getDataBCQT_NT -> " + e.getMessage());
        }
        return lstBcqt_NT;
    }

    public boolean saveCDTT_Config(String khoa, String username, List<QT_DULIEU_NT> lstData, String capbc) throws SQLException {
        Connection connection = new DaoConnect().getConnect();
//        java.util.Dictionary map = (java.util.Dictionary) (connection.getTypeMap());
        Connection conn = new DaoConnect().getConnect();

        Object array[] = lstData.toArray();
        ArrayDescriptor des = ArrayDescriptor
                .createDescriptor(QT_DULIEU_NT.ORACLE_TABLE_TYPE, connection);
        ARRAY array_to_pass = new ARRAY(des, connection, array);
        CallableStatement cs = null;
        try {
            cs = connection.prepareCall("{call VBSP_IMS_CHAMDIEMCN.SP_SAVE_CDCN_CONFIG(?, ?, ?, ?)}");
            cs.setString(1, khoa);
            cs.setString(2, username);
            cs.setArray(3, array_to_pass);
            cs.setString(4, capbc);
            cs.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Loi trong ham saveCDTT_Config " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " saveCDTT_Config -> " + e.getMessage());
            return false;
        } finally {
            if (cs != null) {
                cs.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return true;
    }

    public boolean insertCDCN_FROM_FILE(String mabc, String poscd, String fileName, Date ngaybc, String username, List<ModelExcelFile> lstExcel) throws Exception, SQLException {
        boolean bSuccess = true;
        DaoConnect daoconnect = new DaoConnect();
        Connection conn = null;

        PreparedStatement insert = null;
        CallableStatement statementDelete = null;
        CallableStatement statementUpdate = null;
        String sInsert = "";
        String Delete = "";
        String Update = "";
        try {
            conn = daoconnect.getConnect();
            switch (mabc) {
                case "GIAO_KHNV":
                    sInsert = "insert into dulieu_cdcn_imp(KHOA, STT, MAXA, TENXA, D1, D2, D3, D4, D5, mapgd,ngaybc, "
                            + " NGUOITAO, file_name ,D6,D7,D8,D9,D10)\n"
                            + "values(?, ?,?,?,?,?,?,?,?,?,?,?, ?,?,?,?,?,?)";
                    conn.setAutoCommit(false);
                    insert = conn.prepareStatement(sInsert);
                    Delete = "delete from dulieu_cdcn_imp where ngaybc=? and mapgd=? and khoa = ?";
                    statementDelete = conn.prepareCall(Delete);
                    statementDelete.setDate(1, new java.sql.Date(ngaybc.getTime()));
                    statementDelete.setString(2, poscd);
                    statementDelete.setString(3, mabc);

                    statementDelete.execute();
                    for (int i = 0; i < lstExcel.size(); i++) {
                        ModelExcelFile value = lstExcel.get(i);
                        if (value.getC1() == null || value.getC1().trim().isEmpty()) {
                            continue;
                        }
//                        insert.setString(1, fileName);
//                        insert.setDate(2, new java.sql.Date(ngaybc.getTime()));
                        insert.setString(1, value.getC1());
//                        insert.setString(4, poscd);
                        insert.setString(2, value.getC2());
                        insert.setString(3, value.getC3());
                        insert.setString(4, value.getN1());
                        insert.setString(5, value.getN2());
                        insert.setString(6, value.getN3());
                        insert.setString(7, value.getN4());
                        insert.setString(8, value.getN5());
                        insert.setString(9, value.getN6());
                        insert.setString(10, poscd);
                        insert.setDate(11, new java.sql.Date(ngaybc.getTime()));
                        insert.setString(12, username);
                        insert.setString(13, fileName);
                        insert.setString(14, value.getN8());
                        insert.setString(15, value.getN9());
                        insert.setString(16, value.getN10());
                        insert.setString(17, value.getN11());
                        insert.setString(18, value.getN12());
                        insert.execute();
                    }
                    Update = "update dulieu_cdcn_imp set MAXA = replace(MAXA,'.0',''), "
                            + "D1 = replace(D1,'.0',''),D8 = replace(D8,'.0',''),D9 = replace(D9,'.0',''),D10 = replace(D10,'.0','') where ngaybc=? and mapgd=? and khoa = ?";
                    statementUpdate = conn.prepareCall(Update);
                    statementUpdate.setDate(1, new java.sql.Date(ngaybc.getTime()));
                    statementUpdate.setString(2, poscd);
                    statementUpdate.setString(3, mabc);
                    statementUpdate.execute();

                    Delete = "delete from dulieu_cdcn_imp \n"
                            + "where trim(d1) is null and trim(d2) is null and ngaybc=? and mapgd=? and khoa = ?";
                    statementDelete = conn.prepareCall(Delete);
                    statementDelete.setDate(1, new java.sql.Date(ngaybc.getTime()));
                    statementDelete.setString(2, poscd);
                    statementDelete.setString(3, mabc);
                    statementDelete.execute();

                    conn.commit();
                    conn.setAutoCommit(true);

                    bSuccess = true;

                    break;
                case "GIAO_KHTK_DC":
                    sInsert = "insert into dulieu_cdcn_imp(KHOA, STT, D1, D2, D3, mapgd,ngaybc, "
                            + " NGUOITAO, file_name)\n"
                            + "values(?, ?,?,?,?,?,?,?,?)";
                    conn.setAutoCommit(false);
                    insert = conn.prepareStatement(sInsert);
                    Delete = "delete from dulieu_cdcn_imp where ngaybc=? and mapgd=? and khoa = ?";
                    statementDelete = conn.prepareCall(Delete);
                    statementDelete.setDate(1, new java.sql.Date(ngaybc.getTime()));
                    statementDelete.setString(2, poscd);
                    statementDelete.setString(3, mabc);

                    statementDelete.execute();
                    for (int i = 0; i < lstExcel.size(); i++) {
                        ModelExcelFile value = lstExcel.get(i);
//                        insert.setString(1, fileName);
//                        insert.setDate(2, new java.sql.Date(ngaybc.getTime()));
                        insert.setString(1, value.getC1());
//                        insert.setString(4, poscd);
                        insert.setString(2, value.getC2());
                        insert.setString(3, value.getC3());
                        insert.setString(4, value.getN1());
                        insert.setString(5, value.getN2());
                        insert.setString(6, poscd);
                        insert.setDate(7, new java.sql.Date(ngaybc.getTime()));
                        insert.setString(8, username);
                        insert.setString(9, fileName);
                        insert.execute();
                    }
                    Update = "update dulieu_cdcn_imp set  D1 = replace(D1,'.0','') where ngaybc=? and mapgd=? and khoa=?";
                    statementUpdate = conn.prepareCall(Update);
                    statementUpdate.setDate(1, new java.sql.Date(ngaybc.getTime()));
                    statementUpdate.setString(2, poscd);
                    statementUpdate.setString(3, mabc);
                    statementUpdate.execute();

                    Delete = "delete from dulieu_cdcn_imp where khoa ='GIAO_KHTK_DC' and d1 is null and d2 is null"
                            + " and ngaybc=? and mapgd=? and khoa = ?";
                    statementDelete = conn.prepareCall(Delete);
                    statementDelete.setDate(1, new java.sql.Date(ngaybc.getTime()));
                    statementDelete.setString(2, poscd);
                    statementDelete.setString(3, mabc);
                    statementDelete.execute();

                    conn.commit();
                    conn.setAutoCommit(true);
                    bSuccess = true;
                    break;
                case "GIAO_KHTK_TO":
                    sInsert = "insert into dulieu_cdcn_imp(KHOA, STT, MAXA, TENXA, D1, D2, D3, mapgd,ngaybc, "
                            + " NGUOITAO, file_name, D6,D7,D8,D9,D10)\n"
                            + "values(?, ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                    conn.setAutoCommit(false);
                    insert = conn.prepareStatement(sInsert);
                    Delete = "delete from dulieu_cdcn_imp where ngaybc=? and mapgd=? and khoa = ?";
                    statementDelete = conn.prepareCall(Delete);
                    statementDelete.setDate(1, new java.sql.Date(ngaybc.getTime()));
                    statementDelete.setString(2, poscd);
                    statementDelete.setString(3, mabc);

                    statementDelete.execute();
                    for (int i = 0; i < lstExcel.size(); i++) {
                        ModelExcelFile value = lstExcel.get(i);

                        // Nếu C1 rỗng hoặc null thì bỏ qua dòng này
                        if (value.getC1() == null || value.getC1().trim().isEmpty()) {
                            continue;
                        }

                        insert.setString(1, value.getC1());
                        insert.setString(2, value.getC2());
                        insert.setString(3, value.getC3());
                        insert.setString(4, value.getN1());
                        insert.setString(5, value.getN2());
                        insert.setString(6, value.getN3());
                        insert.setString(7, value.getN4());
                        insert.setString(8, poscd);
                        insert.setDate(9, new java.sql.Date(ngaybc.getTime()));
                        insert.setString(10, username);
                        insert.setString(11, fileName);
                        insert.setString(12, value.getN6());
                        insert.setString(13, value.getN7());
                        insert.setString(14, value.getN8());
                        insert.setString(15, value.getN9());
                        insert.setString(16, value.getN10());

                        insert.execute();
                    }

                    Update = "update dulieu_cdcn_imp set MAXA = replace(MAXA,'.0',''), D1 = replace(D1,'.0',''),D8 = replace(D8,'.0',''),D9 = replace(D9,'.0',''),D10 = replace(D10,'.0','') where ngaybc=? and mapgd=? and khoa=?";
                    statementUpdate = conn.prepareCall(Update);
                    statementUpdate.setDate(1, new java.sql.Date(ngaybc.getTime()));
                    statementUpdate.setString(2, poscd);
                    statementUpdate.setString(3, mabc);
                    statementUpdate.execute();

                    Delete = "delete from dulieu_cdcn_imp where khoa ='GIAO_KHTK_TO' and trim(d1) is null and trim(d2) is null"
                            + " and ngaybc=? and mapgd=? and khoa = ?";
                    statementDelete = conn.prepareCall(Delete);
                    statementDelete.setDate(1, new java.sql.Date(ngaybc.getTime()));
                    statementDelete.setString(2, poscd);
                    statementDelete.setString(3, mabc);
                    statementDelete.execute();

                    conn.commit();
                    conn.setAutoCommit(true);

                    bSuccess = true;

                    break;

                case "KT_CHUNGTU_KT":
                    sInsert = "insert into dulieu_cdcn_imp(KHOA, MAXA, TENXA, D1, D2, D3, D4, D5, mapgd,ngaybc, "
                            + " NGUOITAO, file_name)\n"
                            + "values(?, ?,?,?,?,?,?,?,?,?,?,?)";
                    conn.setAutoCommit(false);
                    insert = conn.prepareStatement(sInsert);
                    Delete = "delete from dulieu_cdcn_imp where ngaybc=? and mapgd=? and khoa = ?";
                    statementDelete = conn.prepareCall(Delete);
                    statementDelete.setDate(1, new java.sql.Date(ngaybc.getTime()));
                    statementDelete.setString(2, poscd);
                    statementDelete.setString(3, mabc);

                    statementDelete.execute();
                    for (int i = 0; i < lstExcel.size(); i++) {
                        ModelExcelFile value = lstExcel.get(i);
//                        insert.setString(1, fileName);
//                        insert.setDate(2, new java.sql.Date(ngaybc.getTime()));
                        insert.setString(1, value.getC1());
//                        insert.setString(4, poscd);
                        insert.setString(2, value.getC2());
                        insert.setString(3, value.getC3());
                        insert.setString(4, value.getN1());
                        insert.setString(5, value.getN2());
                        insert.setString(6, value.getN3());
                        insert.setString(7, value.getN4());
                        insert.setString(8, value.getN5());
//                        insert.setString(9, value.getN6());
                        insert.setString(9, poscd);
                        insert.setDate(10, new java.sql.Date(ngaybc.getTime()));
                        insert.setString(11, username);
                        insert.setString(12, fileName);
                        insert.execute();
                    }
                    Update = "update dulieu_cdcn_imp set MAXA = replace(MAXA,'.0',''), D1 = replace(D1,'.0','') where ngaybc=? and mapgd=? and khoa=?";
                    statementUpdate = conn.prepareCall(Update);
                    statementUpdate.setDate(1, new java.sql.Date(ngaybc.getTime()));
                    statementUpdate.setString(2, poscd);
                    statementUpdate.setString(3, mabc);
                    statementUpdate.execute();

                    Delete = "delete from dulieu_cdcn_imp where khoa ='KT_CHUNGTU_KT' and d1 is null and d2 is null"
                            + " and ngaybc=? and mapgd=? and khoa = ?";
                    statementDelete = conn.prepareCall(Delete);
                    statementDelete.setDate(1, new java.sql.Date(ngaybc.getTime()));
                    statementDelete.setString(2, poscd);
                    statementDelete.setString(3, mabc);
                    statementDelete.execute();

                    conn.commit();
                    conn.setAutoCommit(true);

                    bSuccess = true;

                    break;
                case "DT_KHNV_HST":  //Cán bộ phụ trách PGD
                    sInsert = "insert into dulieu_cdcn_imp(KHOA,STT, MAPGD, TENPGD, D1, D2, D3, D4, D5, D6, D7, D8, D9, D10, D11, D12, D13 , ngaybc, "
                            + " NGUOITAO, file_name)\n"
                            + "values(?, ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                    conn.setAutoCommit(false);
                    insert = conn.prepareStatement(sInsert);
                    Delete = "delete from dulieu_cdcn_imp where ngaybc=? and d13=? and khoa = ?";
                    statementDelete = conn.prepareCall(Delete);
                    statementDelete.setDate(1, new java.sql.Date(ngaybc.getTime()));
                    statementDelete.setString(2, poscd);
                    statementDelete.setString(3, mabc);

                    statementDelete.execute();
                    for (int i = 0; i < lstExcel.size(); i++) {
                        ModelExcelFile value = lstExcel.get(i);
//                        insert.setString(1, fileName);
//                        insert.setDate(2, new java.sql.Date(ngaybc.getTime()));
                        insert.setString(1, value.getC1());
//                        insert.setString(4, poscd);
                        insert.setString(2, value.getC2());
                        insert.setString(3, value.getC3());
                        insert.setString(4, value.getN1());
                        insert.setString(5, value.getN2());
                        insert.setString(6, value.getN3());
                        insert.setString(7, value.getN4());
                        insert.setString(8, value.getN5());
                        insert.setString(9, value.getN6());

                        insert.setString(10, value.getN7());
                        insert.setString(11, value.getN8());
                        insert.setString(12, value.getN9());
                        insert.setString(13, value.getN10());
                        insert.setString(14, value.getN11());
                        insert.setString(15, value.getN12());
                        insert.setString(16, value.getN13());

                        insert.setString(17, poscd);
                        insert.setDate(18, new java.sql.Date(ngaybc.getTime()));
                        insert.setString(19, username);
                        insert.setString(20, fileName);
                        insert.execute();
                    }
                    Update = "update dulieu_cdcn_imp set mapgd = replace(to_char(to_number(replace(MAPGD,'.0','')),'000000'),' ',''), D1 = replace(D1,'.0',''), D3 = replace(D3,'.0',''), D5 = replace(D5,'.0',''), D7 = replace(D7,'.0',''), "
                            + "D9 = replace(D9,'.0',''), D11 = replace(D11,'.0','') where ngaybc=? and D13=? and khoa=?";
                    statementUpdate = conn.prepareCall(Update);
                    statementUpdate.setDate(1, new java.sql.Date(ngaybc.getTime()));
                    statementUpdate.setString(2, poscd);
                    statementUpdate.setString(3, mabc);
                    statementUpdate.execute();

                    conn.commit();
                    conn.setAutoCommit(true);

                    bSuccess = true;

                    break;

                case "PHUTRACHXA_PGD":  //Cán bộ phụ trách PGD
                    sInsert = "insert into dulieu_cdcn_imp(KHOA,STT, maxa, TENXA, D1, D2, D3, D4, D5, D6, D7, D8, D9, D10, D11, D12, D13 , ngaybc, "
                            + " NGUOITAO, file_name)\n"
                            + "values(?, ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                    conn.setAutoCommit(false);
                    insert = conn.prepareStatement(sInsert);
                    Delete = "delete from dulieu_cdcn_imp where ngaybc=? and d13=? and khoa = ?";
                    statementDelete = conn.prepareCall(Delete);
                    statementDelete.setDate(1, new java.sql.Date(ngaybc.getTime()));
                    statementDelete.setString(2, poscd);
                    statementDelete.setString(3, mabc);

                    statementDelete.execute();
                    for (int i = 0; i < lstExcel.size(); i++) {
                        ModelExcelFile value = lstExcel.get(i);
//                        insert.setString(1, fileName);
//                        insert.setDate(2, new java.sql.Date(ngaybc.getTime()));
                        insert.setString(1, value.getC1());
//                        insert.setString(4, poscd);
                        insert.setString(2, value.getC2());
                        insert.setString(3, value.getC3());
                        insert.setString(4, value.getN1());
                        insert.setString(5, value.getN2());
                        insert.setString(6, value.getN3());
                        insert.setString(7, value.getN4());
                        insert.setString(8, value.getN5());
                        insert.setString(9, value.getN6());

                        insert.setString(10, value.getN7());
                        insert.setString(11, value.getN8());
                        insert.setString(12, value.getN9());
                        insert.setString(13, value.getN10());
                        insert.setString(14, value.getN11());
                        insert.setString(15, value.getN12());
                        insert.setString(16, value.getN13());

                        insert.setString(17, poscd);
                        insert.setDate(18, new java.sql.Date(ngaybc.getTime()));
                        insert.setString(19, username);
                        insert.setString(20, fileName);
                        insert.execute();
                    }
                    Update = "update dulieu_cdcn_imp set maxa = REGEXP_REPLACE(REPLACE(REPLACE(TO_CHAR(maxa), '.0', ''), '000000', ''), '[^a-zA-Z0-9]', ''), D1 = replace(D1,'.0',''), D3 = replace(D3,'.0',''), D5 = replace(D5,'.0',''), D7 = replace(D7,'.0',''), "
                            + "D9 = replace(D9,'.0',''), D11 = replace(D11,'.0','') where ngaybc=? and D13=? and khoa=?";
                    statementUpdate = conn.prepareCall(Update);
                    statementUpdate.setDate(1, new java.sql.Date(ngaybc.getTime()));
                    statementUpdate.setString(2, poscd);
                    statementUpdate.setString(3, mabc);
                    statementUpdate.execute();

                    conn.commit();
                    conn.setAutoCommit(true);

                    bSuccess = true;

                    break;

                case "TM_QATCT":  //Vượt quỹ an toàn chi trả
                    sInsert = "insert into dulieu_cdcn_imp(KHOA, STT, MAPGD, TENPGD, D1, D2, D3,ngaybc, "
                            + " NGUOITAO, file_name)\n"
                            + "values(?, ?,?,?,?,?,?,?,?,?)";
                    conn.setAutoCommit(false);
                    insert = conn.prepareStatement(sInsert);
                    Delete = "delete from dulieu_cdcn_imp where ngaybc=? and d3=? and khoa = ?";
                    statementDelete = conn.prepareCall(Delete);
                    statementDelete.setDate(1, new java.sql.Date(ngaybc.getTime()));
                    statementDelete.setString(2, poscd);
                    statementDelete.setString(3, mabc);

                    statementDelete.execute();
                    for (int i = 0; i < lstExcel.size(); i++) {
                        ModelExcelFile value = lstExcel.get(i);
//                        insert.setString(1, fileName);
//                        insert.setDate(2, new java.sql.Date(ngaybc.getTime()));
                        insert.setString(1, value.getC1());
//                        insert.setString(4, poscd);
                        insert.setString(2, value.getC2());
                        insert.setString(3, value.getC3());
                        insert.setString(4, value.getN1());
                        insert.setString(5, value.getN2());
                        insert.setString(6, value.getN3());

                        insert.setString(7, poscd);
                        insert.setDate(8, new java.sql.Date(ngaybc.getTime()));
                        insert.setString(9, username);
                        insert.setString(10, fileName);
                        insert.execute();
                    }
                    Update = "update dulieu_cdcn_imp set MAPGD = replace(to_char(to_number(replace(MAPGD,'.0','')),'000000'),' ','') where ngaybc=? and d3=? and khoa=?";
                    statementUpdate = conn.prepareCall(Update);
                    statementUpdate.setDate(1, new java.sql.Date(ngaybc.getTime()));
                    statementUpdate.setString(2, poscd);
                    statementUpdate.setString(3, mabc);
                    statementUpdate.execute();

                    Delete = "delete from dulieu_cdcn_imp where khoa ='TM_QATCT' and d1 is null and d2 is null and d3 is null"
                            + " and ngaybc=? and d3=? and khoa = ?";
                    statementDelete = conn.prepareCall(Delete);
                    statementDelete.setDate(1, new java.sql.Date(ngaybc.getTime()));
                    statementDelete.setString(2, poscd);
                    statementDelete.setString(3, mabc);
                    statementDelete.execute();

                    conn.commit();
                    conn.setAutoCommit(true);

                    bSuccess = true;

                    break;

                default:
                    System.out.println("");
            }

        } catch (SQLException e) {
            if (conn != null) {
                try {
                    System.err.print("Transaction is being rolled back");
                    conn.rollback();
                } catch (SQLException excep) {
                    CoreLogger.error(this.getClass().getCanonicalName() + " Exception-> insertCDCN_FROM_FILE " + e.getMessage());
                    System.err.println(" Exception-> insertCDCN_FROM_FILE " + e.getMessage());
                }
            }
            CoreLogger.error(this.getClass().getCanonicalName() + " Exception-> insertCDCN_FROM_FILE " + e.getMessage());
            System.err.println(" Exception-> insertCDCN_FROM_FILE " + e.getMessage());
            bSuccess = false;
            throw new Exception("Loi khi luu du lieu " + e.getMessage().replace("\n", "").replace("\r", ""));
        } finally {

            if (statementDelete != null) {
                statementDelete.close();
            }
            if (insert != null) {
                insert.close();
            }
            if (conn != null) {
                conn.close();
            }

        }
        return bSuccess;
    }

    public String getStartEndCel(String Khoa) throws SQLException {
        String pos_cd = "";
        CallableStatement calstatement = null;
        ResultSet reset = null;
        DaoConnect daoconnect = new DaoConnect();
        Connection conn = null;
        conn = daoconnect.getConnect();
//        try {
        //Khoi tao procedure cung voi tham so truyen vao la dau ?
        String strStoreproce = "{?=call VBSP_IMS_CHAMDIEMCN.F_GET_START_END_CELL(?)}";
        try {
            //Khoi tao goi store
            calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            calstatement.registerOutParameter(1, oracle.jdbc.OracleTypes.VARCHAR);

            calstatement.setString(2, Khoa);
            //Thuc hien execute lay du lieu
            calstatement.execute();
            //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
            pos_cd = calstatement.getString(1);

        } catch (SQLException e) {
            System.err.print(e.getMessage());
            CoreLogger.error(this.getClass().getName() + " getStartEndCel -> " + e.getMessage());
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
        return pos_cd;
    }

    public String getPosCd(String username) throws SQLException {
        String pos_cd = "";
        CallableStatement calstatement = null;
        ResultSet reset = null;
        DaoConnect daoconnect = new DaoConnect();
        Connection conn = null;
        conn = daoconnect.getConnect();
//        try {
        //Khoi tao procedure cung voi tham so truyen vao la dau ?
        String strStoreproce = "{?=call VBSP_IMS_CHAMDIEMCN.F_GET_POSCD(?)}";
        try {
            //Khoi tao goi store
            calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            calstatement.registerOutParameter(1, oracle.jdbc.OracleTypes.VARCHAR);

            calstatement.setString(2, username);
            //Thuc hien execute lay du lieu
            calstatement.execute();
            //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
            pos_cd = calstatement.getString(1);

        } catch (SQLException e) {
            System.err.print(e.getMessage());
            CoreLogger.error(this.getClass().getName() + " getPosCd -> " + e.getMessage());
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
        return pos_cd;
    }

    public List<ModelExcelFile> getDataAfterUpFile(Connection conn, String sKhoa, String sNgaybc, String poscd, String username, String grade) {
        List<ModelExcelFile> lstBcqt_NT = new ArrayList<ModelExcelFile>();
        try {
//            DaoConnect daoconnect = new DaoConnect();
//            Connection conn = null;
//            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call VBSP_IMS_CHAMDIEMCN.SP_GET_DATA_AFTER_UPFILE(?,?,?,?,?,?,?,?)}";
            ResultSet reset = null;

            try {
                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                calstatement.registerOutParameter(5, oracle.jdbc.OracleTypes.NUMBER);
                calstatement.registerOutParameter(6, oracle.jdbc.OracleTypes.VARCHAR);
                calstatement.registerOutParameter(7, oracle.jdbc.OracleTypes.CURSOR);
                calstatement.setString(1, sKhoa);
                calstatement.setString(2, username);
                calstatement.setString(3, grade);
                calstatement.setString(4, sNgaybc);
                calstatement.setString(8, poscd);
                //Thuc hien execute lay du lieu
                calstatement.execute();
                //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
                int pn_err_cd = calstatement.getInt(5);
                //thu hien lay mo ta loi
                String strEdd_txt = calstatement.getString(6);
                //Lay cursor ra resultset
                reset = (ResultSet) calstatement.getObject(7);
                while (reset.next()) {

                    ModelExcelFile value = ModelExcelFile.newInstance();
                    value.setC1(reset.getString(1));
                    value.setC2(reset.getString(2));
                    value.setC3(reset.getString(3));
                    value.setN1(reset.getString(4));
                    value.setN2(reset.getString(5));
                    value.setN3(reset.getString(6));
                    value.setN4(reset.getString(7));
                    value.setN5(reset.getString(8));
                    value.setN6(reset.getString(9));
                    value.setN7(reset.getString(10));
                    value.setN8(reset.getString(11));
                    value.setN9(reset.getString(12));
                    value.setN10(reset.getString(13));
                    value.setN11(reset.getString(14));
                    value.setN12(reset.getString(15));
                    value.setN13(reset.getString(16));
                    value.setN14(reset.getString(17));
                    lstBcqt_NT.add(value);
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
                CoreLogger.error(this.getClass().getName() + " getDataAfterUpFile -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham getDataAfterUpFile " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " getDataAfterUpFile -> " + e.getMessage());
        }
        return lstBcqt_NT;
    }

    public boolean UnlockCDCN(String khoa, String username, String ngaybc, List<String> lstMaPGD, String Trangthai, String capbc, String macanbo) throws SQLException {
        Connection connection = new DaoConnect().getConnect();
        CallableStatement cs = null;

        ArrayDescriptor des = ArrayDescriptor.createDescriptor("POS_CD", connection);
        String[] arrayPoscd = lstMaPGD.toArray(new String[0]);
        ARRAY oracle_arrayPoscd = new ARRAY(des, connection, arrayPoscd);
        try {
            cs = connection.prepareCall("{call VBSP_IMS_CHAMDIEMCN.SP_UNLOCK_CDCN(?, ?, ?, ?, ?, ?)}");
            cs.setString(1, khoa);
            cs.setString(2, username);
            cs.setString(3, ngaybc);
            cs.setString(4, Trangthai);
            cs.setString(5, capbc);
            cs.setString(6, macanbo);
            cs.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Loi trong ham UnlockCDCN " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " UnlockCDCN -> " + e.getMessage());
            return false;
        } finally {
            if (cs != null) {
                cs.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return true;
    }

    public String getCheck_Nhaplieu(String Khoa, String Capbc, String UserName, String ngaybc, String macb) throws SQLException {
        String pos_cd = "";
        CallableStatement calstatement = null;
        ResultSet reset = null;
        DaoConnect daoconnect = new DaoConnect();
        Connection conn = null;
        conn = daoconnect.getConnect();
//        try {
        //Khoi tao procedure cung voi tham so truyen vao la dau ?
        String strStoreproce = "{?=call VBSP_IMS_CHAMDIEMCN.F_CHECK_NHAPLIEU(?,?,?,?,?)}";
        try {
            //Khoi tao goi store
            calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            calstatement.registerOutParameter(1, oracle.jdbc.OracleTypes.VARCHAR);
            calstatement.setString(2, Khoa);
            calstatement.setString(3, UserName);
            calstatement.setString(4, Capbc);
            calstatement.setString(5, ngaybc);
            calstatement.setString(6, macb);
            //Thuc hien execute lay du lieu
            calstatement.execute();
            //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
            pos_cd = calstatement.getString(1);

        } catch (SQLException e) {
            System.err.print(e.getMessage());
            CoreLogger.error(this.getClass().getName() + " isCheckPosSend -> " + e.getMessage());
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
        return pos_cd;
    }

    public List<QT_DULIEU_NT> getDataPL01_2025(Connection conn, String sKhoa, String sUser, String sPos_Flag,
            String sNgaybc, String sPosCd, String sMaCb, String sSeach, String check, List<String> lstArrPoscd) {
        List<QT_DULIEU_NT> lstBcqt_NT = new ArrayList<QT_DULIEU_NT>();
        try {
            ArrayDescriptor des = ArrayDescriptor.createDescriptor("POS_CD", conn);
            String[] arrayPoscd = lstArrPoscd.toArray(new String[0]);
            ARRAY oracle_arrayPoscd = new ARRAY(des, conn, arrayPoscd);
            CallableStatement calstatement = null;
            String strStoreproce = "{call VBSP_IMS_CHAMDIEMCN.SP_GET_DATA_PL01_2025(?,?,?,?,?,?,?,?,?,?,?,?)}";
            ResultSet reset = null;

            try {

                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                calstatement.registerOutParameter(8, oracle.jdbc.OracleTypes.NUMBER);
                calstatement.registerOutParameter(9, oracle.jdbc.OracleTypes.VARCHAR);
                calstatement.registerOutParameter(10, oracle.jdbc.OracleTypes.CURSOR);
                calstatement.setString(1, sKhoa);
                calstatement.setString(2, sUser);
                calstatement.setString(3, sPos_Flag);
                calstatement.setString(4, sNgaybc);
                calstatement.setString(5, sPosCd);
                calstatement.setString(6, sMaCb);
                calstatement.setString(7, sSeach);
                calstatement.setString(11, check);
                calstatement.setArray(12, oracle_arrayPoscd);
                //Thuc hien execute lay du lieu
                calstatement.execute();
                //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
                int pn_err_cd = calstatement.getInt(8);
                //thu hien lay mo ta loi
                String strEdd_txt = calstatement.getString(9);
                //Lay cursor ra resultset
                reset = (ResultSet) calstatement.getObject(10);
                while (reset.next()) {

                    QT_DULIEU_NT value = QT_DULIEU_NT.newInstance();
                    value.setKHOA(reset.getString("KHOA"));
                    value.setTHUTU(reset.getInt("THUTU"));
                    value.setTT_HIENTHI(reset.getString("TT_HIENTHI"));
                    value.setMA(reset.getString("MA"));
                    value.setTEN(reset.getString("TEN"));
                    value.setNGAYBC(reset.getDate("NGAYBC"));
                    value.setNAMBC(reset.getInt("NAMBC"));
                    value.setMAPGD(reset.getString("MAPGD"));
                    value.setCO_TONGHOP(reset.getString("CO_TONGHOP"));
                    value.setMACN(reset.getString("MACN"));
                    value.setNGUOI_NHAP(reset.getString("NGUOI_NHAP"));
                    value.setNGAY_NHAP(reset.getDate("NGAY_NHAP"));
                    value.setNGUOI_DUYET(reset.getString("NGUOI_DUYET"));
                    value.setNGAY_DUYET(reset.getDate("NGAY_DUYET"));
                    value.setD1(reset.getString("D1"));
                    value.setD2(reset.getString("D2"));
                    value.setD3(reset.getString("D3"));
                    value.setD4(reset.getString("D4"));
                    value.setD5(reset.getString("D5"));
                    value.setD6(reset.getString("D6"));
                    value.setD7(reset.getString("D7"));
                    value.setD8(reset.getString("D8"));
                    value.setD9(reset.getString("D9"));
                    value.setD10(reset.getString("D10"));
                    value.setD11(reset.getString("D11"));
                    value.setD12(reset.getString("D12"));
                    value.setD13(reset.getString("D13"));
                    value.setD14(reset.getString("D14"));
                    value.setD15(reset.getString("D15"));
                    value.setD16(reset.getString("D16"));
                    value.setD17(reset.getString("D17"));
                    value.setD18(reset.getString("D18"));
                    value.setD19(reset.getString("D19"));
                    value.setD20(reset.getString("D20"));
                    lstBcqt_NT.add(value);
                }

                if (reset != null) {
                    reset.close();
                }
                if (calstatement != null) {
                    calstatement.close();
                }
            } catch (SQLException e) {
                System.err.print(e.getMessage());
                CoreLogger.error(this.getClass().getName() + " getDataKH04 -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham getDataKH04 " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " getDataKH04 -> " + e.getMessage());
        }
        return lstBcqt_NT;
    }

    public boolean save_PL01_2025(String khoa, String user, String sposcd, String scap, String sngay, String smacb, String check, List<DULIEU_NT_TQ> lstData) throws SQLException {
        Connection connection = new DaoConnect().getConnect();
//        java.util.Dictionary map = (java.util.Dictionary) (connection.getTypeMap());
        Object array[] = lstData.toArray();
        ArrayDescriptor des = ArrayDescriptor
                .createDescriptor(DULIEU_NT_TQ.ORACLE_TABLE_TYPE, connection);
        ARRAY array_to_pass = new ARRAY(des, connection, array);
        ArrayDescriptor des_ma = ArrayDescriptor.createDescriptor("POS_CD", connection);
        CallableStatement cs = null;
        try {
            cs = connection.prepareCall("{call VBSP_IMS_CHAMDIEMCN.SP_SAVE_DATA_PL01_2025(?, ?, ?, ? , ?, ?, ?, ?)}");
            cs.setString(1, khoa);
            cs.setString(2, user);
            cs.setString(3, sposcd);
            cs.setString(4, scap);
            cs.setString(5, sngay);
            cs.setString(6, smacb);
            cs.setString(7, check);
            cs.setArray(8, array_to_pass);
            cs.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Loi trong ham to vien " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " to vien -> " + e.getMessage());
            return false;
        } finally {
            if (cs != null) {
                cs.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return true;
    }

    public List<QT_DULIEU_NT> getDataPL02_2025(Connection conn, String sKhoa, String sUser, String sPos_Flag, String sNgaybc, String sPosCd) {
        List<QT_DULIEU_NT> lstBcqt_NT = new ArrayList<QT_DULIEU_NT>();
        try {
            CallableStatement calstatement = null;
            String strStoreproce = "{call VBSP_IMS_CHAMDIEMCN.SP_GET_DATA_PL02_2025(?,?,?,?,?,?,?,?)}";
            ResultSet reset = null;

            try {

                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                calstatement.registerOutParameter(6, oracle.jdbc.OracleTypes.NUMBER);
                calstatement.registerOutParameter(7, oracle.jdbc.OracleTypes.VARCHAR);
                calstatement.registerOutParameter(8, oracle.jdbc.OracleTypes.CURSOR);
                calstatement.setString(1, sKhoa);
                calstatement.setString(2, sUser);
                calstatement.setString(3, sPos_Flag);
                calstatement.setString(4, sNgaybc);
                calstatement.setString(5, sPosCd);
                calstatement.execute();
                int pn_err_cd = calstatement.getInt(6);
                //thu hien lay mo ta loi
                String strEdd_txt = calstatement.getString(7);
                //Lay cursor ra resultset
                reset = (ResultSet) calstatement.getObject(8);
                while (reset.next()) {
                    QT_DULIEU_NT value = QT_DULIEU_NT.newInstance();

                    for (int i = 1; i <= 20; i++) {
                        String field = "D" + i;
                        Method m = QT_DULIEU_NT.class.getMethod("set" + field, String.class);
                        m.invoke(value, reset.getString(field));
                    }

                    lstBcqt_NT.add(value);
                }

                if (reset != null) {
                    reset.close();
                }
                if (calstatement != null) {
                    calstatement.close();
                }
            } catch (SQLException e) {
                System.err.print(e.getMessage());
                CoreLogger.error(this.getClass().getName() + " getDataKH04 -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham getDataKH04 " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " getDataKH04 -> " + e.getMessage());
        }
        return lstBcqt_NT;
    }
}
