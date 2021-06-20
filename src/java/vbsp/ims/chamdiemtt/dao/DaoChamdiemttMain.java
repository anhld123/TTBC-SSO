/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.chamdiemtt.dao;

import java.io.File;
import java.math.BigDecimal;
import vbsp.ims.bcqt.dao.*;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import oracle.jdbc.OraclePreparedStatement;
import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;
import oracle.xdb.XMLType;
import org.w3c.dom.Document;
import vbsp.ims.bcqt.model.QT_DULIEU_NT;
import vbsp.ims.bcqt.model.QT_MS12A;
import vbsp.ims.bcqt.model.QT_MS12C;
import vbsp.ims.bcqt.model.QT_MS13SK;
import vbsp.ims.chamdiemtt.action.CHUONGTRINH_LOAITRU;
import vbsp.ims.chamdiemtt.action.DeNghiLoaiTru;
import vbsp.ims.chamdiemtt.action.ModelExcel;
import vbsp.ims.chamdiemtt.action.SAOKECT_CDTT;
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
public class DaoChamdiemttMain {

    public static DaoChamdiemttMain newInstance() {
        return new DaoChamdiemttMain();
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
        String strStoreproce = "{call VBSP_IMS_CHAMDIEMTT.SP_GET_POS_BY_NAME(?,?)}";
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

    public List<ListValue> getAllBcqt(String sGrade, String sGroup) {
        List<ListValue> lstAllBcqt = new ArrayList<ListValue>();
        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call VBSP_IMS_CHAMDIEMTT.SP_LOAD_ALL_BCQT(?,?,?,?,?)}";
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

    public List<ListValue> getAllBcqt_SUB() {
        List<ListValue> lstAllBcqt = new ArrayList<ListValue>();
        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call VBSP_IMS_CHAMDIEMTT.SP_LOAD_ALL_BCQT_SUB(?,?,?)}";
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
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham getAllBcqt " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " getAllBcqt -> " + e.getMessage());
        }
        return lstAllBcqt;
    }

    public List<ModelTreeNode> getDataPosTreeNode(Connection conn, String strUserName, String sGrade, String mabc) {
        List<ModelTreeNode> lstPo = new ArrayList<ModelTreeNode>();
        try {
//            DaoConnect daoconnect = new DaoConnect();
//            Connection conn = null;
//            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call VBSP_IMS_CHAMDIEMTT.SP_LOAD_POS_TREE_BCQT(?,?,?,?,?,?)}";
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
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham getDataPosTreeNode " + e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " getDataPosTreeNode -> " + e.getMessage());
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
            String strStoreproce = "{call VBSP_IMS_CHAMDIEMTT.SP_LOAD_PARA_BCQT(?, ?, ?, ?, ?,?,?)}";
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
        }
        return combo_list;
    }

    public List<ListValue> getDmKhac(Connection conn, String Khoa) {
        List<ListValue> lstAllBcqt = new ArrayList<ListValue>();
        try {
//            DaoConnect daoconnect = new DaoConnect();
//            Connection conn = null;
//            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call VBSP_IMS_CHAMDIEMTT.SP_GET_DMKHAC(?,?,?,?)}";
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
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham getAllBcqt " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " getAllBcqt -> " + e.getMessage());
        }
        return lstAllBcqt;
    }

//    public List<QT_DULIEU_NT> getDataCDTT_01(Connection conn, String sKhoa, String sNgaybc, String sUser,
//            String sGrade, List<String> lstArrPoscd, String sTTCDTT) {
//        List<QT_DULIEU_NT> lstBcqt_NT = new ArrayList<QT_DULIEU_NT>();
//        try {
//            ArrayDescriptor des = ArrayDescriptor.createDescriptor("POS_CD", conn);
//            String[] arrayPoscd = lstArrPoscd.toArray(new String[0]);
//            ARRAY oracle_arrayPoscd = new ARRAY(des, conn, arrayPoscd);
////            DaoConnect daoconnect = new DaoConnect();
////            Connection conn = null;
////            conn = daoconnect.getConnect();
//            CallableStatement calstatement = null;
//            //Khoi tao procedure cung voi tham so truyen vao la dau ?
//            String strStoreproce = "{call VBSP_IMS_CHAMDIEMTT.SP_GET_DATA_CDTT_01(?,?,?,?,?,?,?,?,?)}";
//            ResultSet reset = null;
//
//            try {
//                //Khoi tao goi store
//                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
//                calstatement.registerOutParameter(6, oracle.jdbc.OracleTypes.NUMBER);
//                calstatement.registerOutParameter(7, oracle.jdbc.OracleTypes.VARCHAR);
//                calstatement.registerOutParameter(8, oracle.jdbc.OracleTypes.CURSOR);
//                calstatement.setString(1, sKhoa);
//                calstatement.setString(2, sUser);
//                calstatement.setString(3, sGrade);
//                calstatement.setString(4, sNgaybc);
//                calstatement.setArray(5, oracle_arrayPoscd);
//                calstatement.setString(9, sTTCDTT);
//                //Thuc hien execute lay du lieu
//                calstatement.execute();
//                //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
//                int pn_err_cd = calstatement.getInt(6);
//                //thu hien lay mo ta loi
//                String strEdd_txt = calstatement.getString(7);
//                //Lay cursor ra resultset
//                reset = (ResultSet) calstatement.getObject(8);
//                while (reset.next()) {
//
//                    QT_DULIEU_NT value = QT_DULIEU_NT.newInstance();
//                    value.setKHOA(reset.getString(1));
//                    value.setTHUTU(reset.getInt(2));
//                    value.setTT_HIENTHI(reset.getString(3));
//                    value.setMA(reset.getString(4));
//                    value.setTEN(reset.getString(5));
//                    value.setNGAYBC(reset.getDate(6));
//                    value.setNAMBC(reset.getInt(7));
//                    value.setMAPGD(reset.getString(8));
//                    value.setCO_TONGHOP(reset.getString(9));
//                    value.setMACN(reset.getString(10));
//                    value.setNGUOI_NHAP(reset.getString(11));
//                    value.setNGAY_NHAP(reset.getDate(12));
////                    value.setD1(reset.getString(14));
//                    value.setD1(reset.getString(15));
//                    value.setD2(reset.getString(16));
//                    value.setD3(reset.getString(17));
//                    value.setD4(reset.getString(18));
//                    value.setD5(reset.getString(19));
//                    value.setD6(reset.getString(20));
//                    value.setD7(reset.getString(21));
//                    value.setD8(reset.getString(22));
//                    value.setD9(reset.getString(23));
//                    value.setD10(reset.getString(24));
//                    value.setD11(reset.getString(25));
//                    value.setD12(reset.getString(26));
//                    value.setD13(reset.getString(27));
//                    value.setD14(reset.getString(28));
//                    value.setD15(reset.getString(29));
//                    value.setD16(reset.getString(30));
//                    value.setD17(reset.getString(31));
//                    value.setNHAPTAY(reset.getString(45));
//                    value.setFONTFORMAT(reset.getString(46));
//
//                    lstBcqt_NT.add(value);
//                }
//
//                if (reset != null) {
//                    reset.close();
//                }
//                if (calstatement != null) {
//                    calstatement.close();
//                }
////                if (conn != null) {
////                    conn.close();
////                }
//            } catch (SQLException e) {
//                System.err.print(e.getMessage());
//                CoreLogger.error(this.getClass().getName() + " getDataBCQT_NT -> " + e.getMessage());
//            }
//        } catch (Exception e) {
//            System.err.println("Loi trong ham getDataBCQT_NT " + e.getMessage());
//            CoreLogger.error(this.getClass().getName() + " getDataBCQT_NT -> " + e.getMessage());
//        }
//        return lstBcqt_NT;
//    }
//    
    public List<QT_DULIEU_NT> getDataCDTT_PGD(Connection conn, String sKhoa, String sNgaybc, String sUser,
            String sGrade, List<String> lstArrPoscd, String sTTCDTT) {
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
            String strStoreproce = "{call VBSP_IMS_CHAMDIEMTT.SP_GET_DATA_CDTT_05(?,?,?,?,?,?,?,?,?)}";
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
                calstatement.setString(9, sTTCDTT);
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

    public List<QT_DULIEU_NT> getDataCDTT_SGD(Connection conn, String sKhoa, String sNgaybc, String sUser,
            String sGrade, List<String> lstArrPoscd, String sTTCDTT) {
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
            String strStoreproce = "{call VBSP_IMS_CHAMDIEMTT.SP_GET_DATA_CDTT_SGD(?,?,?,?,?,?,?,?,?)}";
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
                calstatement.setString(9, sTTCDTT);
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

    public List<QT_DULIEU_NT> getDataCDTT_CNTT(Connection conn, String sKhoa, String sNgaybc, String sUser,
            String sGrade, List<String> lstArrPoscd, String sTTCDTT) {
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
            String strStoreproce = "{call VBSP_IMS_CHAMDIEMTT.SP_GET_DATA_CDTT_CNTT(?,?,?,?,?,?,?,?,?)}";
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
                calstatement.setString(9, sTTCDTT);
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

    public List<QT_DULIEU_NT> getDataCDTT_TTDT(Connection conn, String sKhoa, String sNgaybc, String sUser,
            String sGrade, List<String> lstArrPoscd, String sTTCDTT) {
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
            String strStoreproce = "{call VBSP_IMS_CHAMDIEMTT.SP_GET_DATA_CDTT_TTDT(?,?,?,?,?,?,?,?,?)}";
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
                calstatement.setString(9, sTTCDTT);
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

    public List<QT_DULIEU_NT> getDataCDTT_CMNV06(Connection conn, String sKhoa, String sNgaybc, String sUser,
            String sGrade, List<String> lstArrPoscd, String sTTCDTT) {
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
            String strStoreproce = "{call VBSP_IMS_CHAMDIEMTT.SP_GET_DATA_CDTT_CMNV06(?,?,?,?,?,?,?,?,?)}";
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
                calstatement.setString(9, sTTCDTT);
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

    public List<QT_DULIEU_NT> getDataCDTT_CMNV07(Connection conn, String sKhoa, String sNgaybc, String sUser,
            String sGrade, List<String> lstArrPoscd, String sTTCDTT) {
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
            String strStoreproce = "{call VBSP_IMS_CHAMDIEMTT.SP_GET_DATA_CDTT_CMNV07(?,?,?,?,?,?,?,?,?)}";
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
                calstatement.setString(9, sTTCDTT);
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

    public List<QT_DULIEU_NT> getDataCDTT_CN(Connection conn, String sKhoa, String sNgaybc, String sUser,
            String sGrade, List<String> lstArrPoscd, String sTTCDTT) {
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
            String strStoreproce = "{call VBSP_IMS_CHAMDIEMTT.SP_GET_DATA_CDTT_CN(?,?,?,?,?,?,?,?,?)}";
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
                calstatement.setString(9, sTTCDTT);
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

                    value.setD22(reset.getString(36));
                    value.setD23(reset.getString(37));
                    value.setD24(reset.getString(38));
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

    public List<QT_DULIEU_NT> getDataCDTT_CN_TUNGNV(String sKhoa, String sNgaybc, String sUser,
            String sGrade, List<String> lstArrPoscd) throws SQLException {
        List<QT_DULIEU_NT> lstBcqt_NT = new ArrayList<QT_DULIEU_NT>();

        Connection conn = null;
//            conn = daoconnect.getConnect();
        CallableStatement calstatement = null;
        //Khoi tao procedure cung voi tham so truyen vao la dau ?
        String strStoreproce = "{call VBSP_IMS_CHAMDIEMTT.SP_GET_DATA_CDTT_CNNHAP(?,?,?,?,?,?)}";
        ResultSet reset = null;

        try {
            conn = new DaoConnect().getConnect();
            ArrayDescriptor des = ArrayDescriptor.createDescriptor("POS_CD", conn);
            String[] arrayPoscd = lstArrPoscd.toArray(new String[0]);
            ARRAY oracle_arrayPoscd = new ARRAY(des, conn, arrayPoscd);
//            DaoConnect daoconnect = new DaoConnect();
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
            //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)

            //Lay cursor ra resultset
            reset = (ResultSet) calstatement.getObject(6);
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

                value.setD22(reset.getString(36));
                value.setD23(reset.getString(37));
                value.setD24(reset.getString(38));
                value.setD30(reset.getString(44));
                value.setNHAPTAY(reset.getString(45));
                value.setFONTFORMAT(reset.getString(46));

                lstBcqt_NT.add(value);
            }

        } catch (SQLException e) {
            System.err.print(e.getMessage());
            CoreLogger.error(this.getClass().getName() + " getDataBCQT_NT_TUNGNV -> " + e.getMessage());
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

    public List<QT_DULIEU_NT> getDataCDTT_Status(Connection conn, String sKhoa, String sNgaybc, String sGrade) {
        List<QT_DULIEU_NT> lstBcqt_NT = new ArrayList<QT_DULIEU_NT>();
        try {
            ArrayDescriptor des = ArrayDescriptor.createDescriptor("POS_CD", conn);
//            String[] arrayPoscd = lstArrPoscd.toArray(new String[0]);
//            ARRAY oracle_arrayPoscd = new ARRAY(des, conn, arrayPoscd);
//            DaoConnect daoconnect = new DaoConnect();
//            Connection conn = null;
//            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call VBSP_IMS_CHAMDIEMTT.SP_GET_STATUS_INPUT(?,?,?,?,?,?)}";
            ResultSet reset = null;

            try {
                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                calstatement.registerOutParameter(4, oracle.jdbc.OracleTypes.NUMBER);
                calstatement.registerOutParameter(5, oracle.jdbc.OracleTypes.VARCHAR);
                calstatement.registerOutParameter(6, oracle.jdbc.OracleTypes.CURSOR);
                calstatement.setString(1, sKhoa);
                calstatement.setString(2, sGrade);
                calstatement.setString(3, sNgaybc);
                //Thuc hien execute lay du lieu
                calstatement.execute();
                //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
                int pn_err_cd = calstatement.getInt(4);
                //thu hien lay mo ta loi
                String strEdd_txt = calstatement.getString(5);
                //Lay cursor ra resultset
                reset = (ResultSet) calstatement.getObject(6);
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

    public boolean saveCDTT01(String khoa, String username, String mapgd, String ngaybc, List<QT_DULIEU_NT> lstData) throws SQLException {
        Connection connection = new DaoConnect().getConnect();
//        java.util.Dictionary map = (java.util.Dictionary) (connection.getTypeMap());
        Object array[] = lstData.toArray();
        ArrayDescriptor des = ArrayDescriptor
                .createDescriptor(QT_DULIEU_NT.ORACLE_TABLE_TYPE, connection);
        ARRAY array_to_pass = new ARRAY(des, connection, array);
        CallableStatement cs = null;
        try {
            cs = connection.prepareCall("{call VBSP_IMS_CHAMDIEMTT.SP_SAVE_CDTT_01(?, ?, ?, ?, ?)}");
            cs.setString(1, khoa);
            cs.setString(2, username);
            cs.setString(3, mapgd);
            cs.setString(4, ngaybc);
            cs.setArray(5, array_to_pass);
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

    public boolean saveCDTT_CNNHAP(String khoa, String username, String ngaybc, List<QT_DULIEU_NT> lstData, String capbc) throws SQLException {
        Connection connection = new DaoConnect().getConnect();
//        java.util.Dictionary map = (java.util.Dictionary) (connection.getTypeMap());
        Object array[] = lstData.toArray();
        ArrayDescriptor des = ArrayDescriptor
                .createDescriptor(QT_DULIEU_NT.ORACLE_TABLE_TYPE, connection);
        ARRAY array_to_pass = new ARRAY(des, connection, array);
        CallableStatement cs = null;
        try {
            cs = connection.prepareCall("{call VBSP_IMS_CHAMDIEMTT.SP_SAVE_CDTT_CNNHAP(?, ?, ?, ?, ?)}");
            cs.setString(1, khoa);
            cs.setString(2, username);
            cs.setString(3, ngaybc);
            cs.setArray(4, array_to_pass);
            cs.setString(5, capbc);
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

    public boolean saveCDTT_CN(String khoa, String username, String mapgd, String ngaybc, List<QT_DULIEU_NT> lstData, String capbc) throws SQLException {
        Connection connection = new DaoConnect().getConnect();
//        java.util.Dictionary map = (java.util.Dictionary) (connection.getTypeMap());
        Object array[] = lstData.toArray();
        ArrayDescriptor des = ArrayDescriptor
                .createDescriptor(QT_DULIEU_NT.ORACLE_TABLE_TYPE, connection);
        ARRAY array_to_pass = new ARRAY(des, connection, array);
        CallableStatement cs = null;
        try {
            cs = connection.prepareCall("{call VBSP_IMS_CHAMDIEMTT.SP_SAVE_CDTT_CN(?, ?, ?, ?, ?, ?)}");
            cs.setString(1, khoa);
            cs.setString(2, username);
            cs.setString(3, mapgd);
            cs.setString(4, ngaybc);
            cs.setArray(5, array_to_pass);
            cs.setString(6, capbc);
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

    public boolean saveCMNVTW(String khoa, String username, String mapgd, String ngaybc, List<QT_DULIEU_NT> lstData, String capbc) throws SQLException {
        Connection connection = new DaoConnect().getConnect();
//        java.util.Dictionary map = (java.util.Dictionary) (connection.getTypeMap());
        Object array[] = lstData.toArray();
        ArrayDescriptor des = ArrayDescriptor
                .createDescriptor(QT_DULIEU_NT.ORACLE_TABLE_TYPE, connection);
        ARRAY array_to_pass = new ARRAY(des, connection, array);
        CallableStatement cs = null;
        try {
            cs = connection.prepareCall("{call VBSP_IMS_CHAMDIEMTT.SP_SAVE_CDTT_CN_CMNV_TW(?, ?, ?, ?, ?, ?)}");
            cs.setString(1, khoa);
            cs.setString(2, username);
            cs.setString(3, mapgd);
            cs.setString(4, ngaybc);
            cs.setArray(5, array_to_pass);
            cs.setString(6, capbc);
            cs.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Loi trong ham saveCMNVTW " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " saveCMNVTW -> " + e.getMessage());
            throw new SQLException(e);
            //return false;
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

    public boolean saveHOIDONGCN(String khoa, String username, String mapgd, String ngaybc, List<QT_DULIEU_NT> lstData, String capbc) throws SQLException {
        Connection connection = new DaoConnect().getConnect();
//        java.util.Dictionary map = (java.util.Dictionary) (connection.getTypeMap());
        Object array[] = lstData.toArray();
        ArrayDescriptor des = ArrayDescriptor
                .createDescriptor(QT_DULIEU_NT.ORACLE_TABLE_TYPE, connection);
        ARRAY array_to_pass = new ARRAY(des, connection, array);
        CallableStatement cs = null;
        try {
            cs = connection.prepareCall("{call VBSP_IMS_CHAMDIEMTT.SP_HOIDONG_CN_DUYET(?, ?, ?, ?, ?, ?)}");
            cs.setString(1, khoa);
            cs.setString(2, username);
            cs.setString(3, mapgd);
            cs.setString(4, ngaybc);
            cs.setArray(5, array_to_pass);
            cs.setString(6, capbc);
            cs.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Loi trong ham saveHOIDONGCN " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " saveHOIDONGCN -> " + e.getMessage());
            throw new SQLException(e);
            //return false;
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

    public boolean saveHOIDONGTW(String khoa, String username, String mapgd, String ngaybc, List<QT_DULIEU_NT> lstData, String capbc) throws SQLException {
        Connection connection = new DaoConnect().getConnect();
//        java.util.Dictionary map = (java.util.Dictionary) (connection.getTypeMap());
        Object array[] = lstData.toArray();
        ArrayDescriptor des = ArrayDescriptor
                .createDescriptor(QT_DULIEU_NT.ORACLE_TABLE_TYPE, connection);
        ARRAY array_to_pass = new ARRAY(des, connection, array);
        CallableStatement cs = null;
        try {
            cs = connection.prepareCall("{call VBSP_IMS_CHAMDIEMTT.SP_SAVE_CDTT_CN_HOIDONG_TW(?, ?, ?, ?, ?, ?)}");
            cs.setString(1, khoa);
            cs.setString(2, username);
            cs.setString(3, mapgd);
            cs.setString(4, ngaybc);
            cs.setArray(5, array_to_pass);
            cs.setString(6, capbc);
            cs.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Loi trong ham saveHOIDONGTW " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " saveHOIDONGTW -> " + e.getMessage());
            throw new SQLException(e);
            //return false;
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

    public boolean saveCDTT_TTCNTT(String khoa, String username, String mapgd, String ngaybc, List<QT_DULIEU_NT> lstData, String capbc) throws SQLException {
        Connection connection = new DaoConnect().getConnect();
//        java.util.Dictionary map = (java.util.Dictionary) (connection.getTypeMap());
        Object array[] = lstData.toArray();
        ArrayDescriptor des = ArrayDescriptor
                .createDescriptor(QT_DULIEU_NT.ORACLE_TABLE_TYPE, connection);
        ARRAY array_to_pass = new ARRAY(des, connection, array);
        CallableStatement cs = null;
        try {
            cs = connection.prepareCall("{call VBSP_IMS_CHAMDIEMTT.SP_SAVE_CDTT_CNTT(?, ?, ?, ?, ?, ?)}");
            cs.setString(1, khoa);
            cs.setString(2, username);
            cs.setString(3, mapgd);
            cs.setString(4, ngaybc);
            cs.setArray(5, array_to_pass);
            cs.setString(6, capbc);
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

    public boolean saveCDTT05(String khoa, String username, String mapgd, String ngaybc, List<QT_DULIEU_NT> lstData, String capbc) throws SQLException {
        Connection connection = new DaoConnect().getConnect();
//        java.util.Dictionary map = (java.util.Dictionary) (connection.getTypeMap());
        Object array[] = lstData.toArray();
        ArrayDescriptor des = ArrayDescriptor
                .createDescriptor(QT_DULIEU_NT.ORACLE_TABLE_TYPE, connection);
        ARRAY array_to_pass = new ARRAY(des, connection, array);
        CallableStatement cs = null;
        try {
            cs = connection.prepareCall("{call VBSP_IMS_CHAMDIEMTT.SP_SAVE_CDTT_05(?, ?, ?, ?, ?, ?)}");
            cs.setString(1, khoa);
            cs.setString(2, username);
            cs.setString(3, mapgd);
            cs.setString(4, ngaybc);
            cs.setArray(5, array_to_pass);
            cs.setString(6, capbc);
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

    public boolean saveCDTT_CMNV06(String khoa, String username, String mapgd, String ngaybc, List<QT_DULIEU_NT> lstData, String capbc, String phongban) throws SQLException {
        Connection connection = new DaoConnect().getConnect();
//        java.util.Dictionary map = (java.util.Dictionary) (connection.getTypeMap());
        Object array[] = lstData.toArray();
        ArrayDescriptor des = ArrayDescriptor
                .createDescriptor(QT_DULIEU_NT.ORACLE_TABLE_TYPE, connection);
        ARRAY array_to_pass = new ARRAY(des, connection, array);
        CallableStatement cs = null;
        try {
            cs = connection.prepareCall("{call VBSP_IMS_CHAMDIEMTT.SP_SAVE_CDTT_CMNV06(?, ?, ?, ?, ?, ?, ?)}");
            cs.setString(1, khoa);
            cs.setString(2, username);
            cs.setString(3, mapgd);
            cs.setString(4, ngaybc);
            cs.setArray(5, array_to_pass);
            cs.setString(6, capbc);
            cs.setString(7, phongban);
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

    public boolean saveCDTT_CMNV06A(String khoa, String username, String mapgd, String ngaybc, List<QT_DULIEU_NT> lstData, String capbc, String phongban) throws SQLException {
        Connection connection = new DaoConnect().getConnect();
//        java.util.Dictionary map = (java.util.Dictionary) (connection.getTypeMap());
        Object array[] = lstData.toArray();
        ArrayDescriptor des = ArrayDescriptor
                .createDescriptor(QT_DULIEU_NT.ORACLE_TABLE_TYPE, connection);
        ARRAY array_to_pass = new ARRAY(des, connection, array);
        CallableStatement cs = null;
        try {
            cs = connection.prepareCall("{call VBSP_IMS_CHAMDIEMTT.SP_SAVE_CDTT_CMNV06(?, ?, ?, ?, ?, ?, ?)}");
            cs.setString(1, khoa);
            cs.setString(2, username);
            cs.setString(3, mapgd);
            cs.setString(4, ngaybc);
            cs.setArray(5, array_to_pass);
            cs.setString(6, capbc);
            cs.setString(7, capbc);
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

    public boolean lockCDTT_pgd(String khoa, String username, String ngaybc, List<String> lstMaPGD, String Trangthai, String capbc) throws SQLException {
        Connection connection = new DaoConnect().getConnect();
        CallableStatement cs = null;

        ArrayDescriptor des = ArrayDescriptor.createDescriptor("POS_CD", connection);
        String[] arrayPoscd = lstMaPGD.toArray(new String[0]);
        ARRAY oracle_arrayPoscd = new ARRAY(des, connection, arrayPoscd);
        try {
            cs = connection.prepareCall("{call VBSP_IMS_CHAMDIEMTT.SP_LOCK_CDTT_05(?, ?, ?, ?, ?, ?)}");
            cs.setString(1, khoa);
            cs.setString(2, username);
            cs.setString(3, ngaybc);
            cs.setArray(4, oracle_arrayPoscd);
            cs.setString(5, Trangthai);
            cs.setString(6, capbc);
            cs.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Loi trong ham lockCDTT05 " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " lockCDTT05 -> " + e.getMessage());
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
    
    public boolean lockCDTT_pgd_phongban(String khoa, String username, String ngaybc, List<String> lstMaPGD, String Trangthai, String capbc, String phongban) throws SQLException {
        Connection connection = new DaoConnect().getConnect();
        CallableStatement cs = null;

        ArrayDescriptor des = ArrayDescriptor.createDescriptor("POS_CD", connection);
        String[] arrayPoscd = lstMaPGD.toArray(new String[0]);
        ARRAY oracle_arrayPoscd = new ARRAY(des, connection, arrayPoscd);
        try {
            cs = connection.prepareCall("{call VBSP_IMS_CHAMDIEMTT.SP_LOCK_CDTT_05_PHONGBAN(?, ?, ?, ?, ?, ?, ?)}");
            cs.setString(1, khoa);
            cs.setString(2, username);
            cs.setString(3, ngaybc);
            cs.setArray(4, oracle_arrayPoscd);
            cs.setString(5, Trangthai);
            cs.setString(6, capbc);
            cs.setString(7, phongban);
            cs.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Loi trong ham lockCDTT05 " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " lockCDTT05 -> " + e.getMessage());
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
    
    public boolean lockCDTT_0607(String khoa, String username, String ngaybc, List<String> lstMaPGD, String Trangthai, String capbc, String phongban) throws SQLException {
        Connection connection = new DaoConnect().getConnect();
        CallableStatement cs = null;

        ArrayDescriptor des = ArrayDescriptor.createDescriptor("POS_CD", connection);
        String[] arrayPoscd = lstMaPGD.toArray(new String[0]);
        ARRAY oracle_arrayPoscd = new ARRAY(des, connection, arrayPoscd);
        try {
            cs = connection.prepareCall("{call VBSP_IMS_CHAMDIEMTT.SP_LOCK_CDTT_0607(?, ?, ?, ?, ?, ?, ?)}");
            cs.setString(1, khoa);
            cs.setString(2, username);
            cs.setString(3, ngaybc);
            cs.setArray(4, oracle_arrayPoscd);
            cs.setString(5, Trangthai);
            cs.setString(6, capbc);
            cs.setString(7, phongban);
            cs.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Loi trong ham lockCDTT_0607 " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " lockCDTT_0607 -> " + e.getMessage());
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

    public List<QT_DULIEU_NT> getDataCDTT_02(Connection conn, String sKhoa, String sNgaybc, String sUser,
            String sGrade, List<String> lstArrPoscd) {
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
            String strStoreproce = "{call VBSP_IMS_CHAMDIEMTT.SP_GET_DATA_CDTT_02(?,?,?,?,?,?,?,?)}";
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

    public int checkUser(String UserName) throws SQLException {
        int _retVal = 0;
        Connection conn = new DaoConnect().getConnect();
        CallableStatement calstatement = null;
        ResultSet reset = null;
        //Khoi tao procedure cung voi tham so truyen vao la dau ?
        String strStoreproce = "{ ? = call VBSP_IMS_CHAMDIEMTT.F_CHECK_USER(?) }";

        try {
            //Khoi tao goi Store
            calstatement = conn.prepareCall(strStoreproce,
                    ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            calstatement.registerOutParameter(1, oracle.jdbc.OracleTypes.NUMBER);
            calstatement.setString(2, UserName);
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

    public int checkRuleUser(String UserName, String CapBC) throws SQLException {
        int _retVal = 0;
        Connection conn = new DaoConnect().getConnect();
        CallableStatement calstatement = null;
        ResultSet reset = null;
        //Khoi tao procedure cung voi tham so truyen vao la dau ?
        String strStoreproce = "{ ? = call VBSP_IMS_CHAMDIEMTT.F_CHECK_RULE_USER(?, ?) }";

        try {
            //Khoi tao goi Store
            calstatement = conn.prepareCall(strStoreproce,
                    ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            calstatement.registerOutParameter(1, oracle.jdbc.OracleTypes.NUMBER);
            calstatement.setString(2, UserName);
            calstatement.setString(3, CapBC);
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

    public List<String> getDataSendCdtt(String type, String khoa, String mapgd, String ngay_bc) throws SQLException {
        List<String> lstData = new ArrayList<>();
        DaoConnect daoconnect = new DaoConnect();
        Connection conn = null;
        conn = daoconnect.getConnect();
        CallableStatement calstatement = null;
        ResultSet reset = null;
//        try {
        //Khoi tao procedure cung voi tham so truyen vao la dau ?
        String strStoreproce = "{call VBSP_IMS_CHAMDIEMTT.SP_GET_DATA_CDTT_SYNC(?,?,?,?,?,?,?)}";
        try {
            //Khoi tao goi store
            calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            calstatement.registerOutParameter(5, oracle.jdbc.OracleTypes.NUMBER);
            calstatement.registerOutParameter(6, oracle.jdbc.OracleTypes.VARCHAR);
            calstatement.registerOutParameter(7, oracle.jdbc.OracleTypes.CURSOR);
            calstatement.setString(1, type);
            calstatement.setString(2, khoa);
            calstatement.setString(3, mapgd);
            calstatement.setString(4, ngay_bc);
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
            CoreLogger.error(this.getClass().getName() + " getDataSendBcqt -> " + e.getMessage());
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

    public boolean putXmlFileCDTT(String fileXml, String khoa, String type_bcqt, String mapgd, String ngay_bc,
            String grade, String username, String ngay_gui, String tt_khoa) throws SQLException {
        boolean bSuccess = false;
//        String qry = "INSERT INTO BCQT_XML_SYNC (KHOA, MAPGD, NGAY_BC, GRADE, NGUOI_GUI, NGAY_GUI,\n"
//                + " FILE_SIZE, FILE_NAME, XML_DATA, NGAY_TAO) VALUES(?,?,?,?,?,?,?,?,?,SYSDATE)";
        String qry = "{call VBSP_IMS_CHAMDIEMTT.SP_PUT_FILEXML_CDTT(?,?,?,?,?,?,?,?,?,?,?)}";//SP_PUT_FILEXML
        DaoConnect daoconnect = new DaoConnect();
        Connection conn = null;
        OraclePreparedStatement sqlStatement = null;
        XMLType xml = null;
        try {
            //convert date cho ngay_gui
            java.sql.Timestamp date_ngay_gui = new java.sql.Timestamp(new SimpleDateFormat("dd-MMM-yyyy hh:mm:ss").parse(ngay_gui).getTime());
            //format date cho ngay bao cao
            java.sql.Date date_ngay_bc = new java.sql.Date(new SimpleDateFormat("dd-MMM-yyyy").parse(ngay_bc).getTime());
            File xmlFile = new File(fileXml);
            if (!xmlFile.exists()) {
                CoreLogger.error(this.getClass().getName() + " putXmlFileKtgs -> Khong tim thay filexml " + fileXml);
                return false;
            }
            BigDecimal value = new BigDecimal(xmlFile.length());
            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();

            Document document = documentBuilder.parse(xmlFile);
            conn = daoconnect.getConnect();

            xml = XMLType.createXML(conn, document);

            sqlStatement = (OraclePreparedStatement) conn.prepareStatement(qry);
            sqlStatement.setString(1, khoa);
            sqlStatement.setString(2, type_bcqt);
            sqlStatement.setString(3, mapgd);
            sqlStatement.setDate(4, date_ngay_bc);
            sqlStatement.setString(5, grade);
            sqlStatement.setString(6, username);
//            sqlStatement.setString(6, ngay_gui);
            sqlStatement.setTimestamp(7, date_ngay_gui);
            sqlStatement.setBigDecimal(8, value);
            sqlStatement.setString(9, xmlFile.getName());
            sqlStatement.setObject(10, xml);
            sqlStatement.setString(11, tt_khoa);
            sqlStatement.execute();

            bSuccess = true;
        } catch (Exception e) {
            System.err.println("Loi trong ham putXmlFileKtgs " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " putXmlFileKtgs -> " + e.getMessage());
            bSuccess = false;
        } finally {

            if (sqlStatement != null) {
                sqlStatement.close();
            }
            if (xml != null) {
                xml.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return bSuccess;
    }

    public String isCheckPosSend(String type, String Khoa, String UserName, String ngaybc) throws SQLException {
        String pos_cd = "";
        CallableStatement calstatement = null;
        ResultSet reset = null;
        DaoConnect daoconnect = new DaoConnect();
        Connection conn = null;
        conn = daoconnect.getConnect();
//        try {
        //Khoi tao procedure cung voi tham so truyen vao la dau ?
        String strStoreproce = "{?=call VBSP_IMS_CHAMDIEMTT.F_CHECK_POS_DATA(?,?,?,?)}";
        try {
            //Khoi tao goi store
            calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            calstatement.registerOutParameter(1, oracle.jdbc.OracleTypes.VARCHAR);
            calstatement.setString(2, type);
            calstatement.setString(3, Khoa);
            calstatement.setString(4, UserName);
            calstatement.setString(5, ngaybc);
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

    public int isCheckPGDInput(String Khoa, String maPGD, String ngaybc, String capbc, String ploai, String UserName) throws SQLException {
        int iReturn = 0;
        CallableStatement calstatement = null;
        ResultSet reset = null;
        DaoConnect daoconnect = new DaoConnect();
        Connection conn = null;
        conn = daoconnect.getConnect();
//        try {
        //Khoi tao procedure cung voi tham so truyen vao la dau ?
        String strStoreproce = "{?=call VBSP_IMS_CHAMDIEMTT.F_CHECK_BLOCK_DATA(?,?,?,?,?,?)}";
        try {
            //Khoi tao goi store
            calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            calstatement.registerOutParameter(1, oracle.jdbc.OracleTypes.VARCHAR);
            calstatement.setString(2, Khoa);
            calstatement.setString(3, ngaybc);
            calstatement.setString(4, capbc);
            calstatement.setString(5, maPGD);
            calstatement.setString(6, ploai);
            calstatement.setString(7, UserName);
            //Thuc hien execute lay du lieu
            calstatement.execute();
            //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
            iReturn = calstatement.getInt(1);

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
        return iReturn;
    }
//    PV_KHOA  IN VARCHAR2,PV_NGAYBC  IN VARCHAR2,PV_USERNAME   IN VARCHAR2,  PV_CAPBC      IN VARCHAR2

    public String isChitieuchuaduyet(String Khoa, String ngaybc, String capbc, String UserName) throws SQLException {
        String iReturn = "";
        CallableStatement calstatement = null;
        ResultSet reset = null;
        DaoConnect daoconnect = new DaoConnect();
        Connection conn = null;
        conn = daoconnect.getConnect();
//        try {
        //Khoi tao procedure cung voi tham so truyen vao la dau ?
        String strStoreproce = "{?=call VBSP_IMS_CHAMDIEMTT.f_CMNV_KIEMTRA_01DGXL(?,?,?,?)}";
        try {
            //Khoi tao goi store
            calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            calstatement.registerOutParameter(1, oracle.jdbc.OracleTypes.VARCHAR);
            calstatement.setString(2, Khoa);
            calstatement.setString(3, ngaybc);
            calstatement.setString(4, UserName);
            calstatement.setString(5, capbc);
            //Thuc hien execute lay du lieu
            calstatement.execute();
            //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
            iReturn = calstatement.getString(1);

        } catch (SQLException e) {
            System.err.print(e.getMessage());
            CoreLogger.error(this.getClass().getName() + " isChitieuchuaduyet -> " + e.getMessage());
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
        return iReturn;
    }

    public String kiemtranhaplieu(String Khoa, String ngaybc, String capbc, String UserName) throws SQLException {
        String iReturn = "";
        CallableStatement calstatement = null;
        ResultSet reset = null;
        DaoConnect daoconnect = new DaoConnect();
        Connection conn = null;
        conn = daoconnect.getConnect();
//        try {
        //Khoi tao procedure cung voi tham so truyen vao la dau ?
        String strStoreproce = "{?=call VBSP_IMS_CHAMDIEMTT.F_KIEMTRA_NHAPLIEU_CN(?,?,?,?)}";
        try {
            //Khoi tao goi store
            calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            calstatement.registerOutParameter(1, oracle.jdbc.OracleTypes.VARCHAR);
            calstatement.setString(2, Khoa);
            calstatement.setString(3, ngaybc);
            calstatement.setString(4, UserName);
            calstatement.setString(5, capbc);
            //Thuc hien execute lay du lieu
            calstatement.execute();
            //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
            iReturn = calstatement.getString(1);

        } catch (SQLException e) {
            System.err.print(e.getMessage());
            CoreLogger.error(this.getClass().getName() + " kiemtranhaplieu -> " + e.getMessage());
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
        return iReturn;
    }

    public String getStatusInput(String Capbc, String Khoa, String UserName, String ngaybc, String poscd) throws SQLException {
        String pos_cd = "";
        CallableStatement calstatement = null;
        ResultSet reset = null;
        DaoConnect daoconnect = new DaoConnect();
        Connection conn = null;
        conn = daoconnect.getConnect();
//        try {
        //Khoi tao procedure cung voi tham so truyen vao la dau ?
        String strStoreproce = "{?=call VBSP_IMS_CHAMDIEMTT.F_GET_STATUS_INPUT(?,?,?,?,?)}";
        try {
            //Khoi tao goi store
            calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            calstatement.registerOutParameter(1, oracle.jdbc.OracleTypes.VARCHAR);
            calstatement.setString(2, Capbc);
            calstatement.setString(3, Khoa);
            calstatement.setString(4, UserName);
            calstatement.setString(5, ngaybc);
            calstatement.setString(6, poscd);
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

    public String getStatusInput_0607(String Capbc, String Khoa, String UserName, String ngaybc, String poscd, String phongcmnv) throws SQLException {
        String pos_cd = "";
        CallableStatement calstatement = null;
        ResultSet reset = null;
        DaoConnect daoconnect = new DaoConnect();
        Connection conn = null;
        conn = daoconnect.getConnect();
//        try {
        //Khoi tao procedure cung voi tham so truyen vao la dau ?
        String strStoreproce = "{?=call VBSP_IMS_CHAMDIEMTT.F_GET_STATUS_INPUT_0607(?,?,?,?,?,?)}";
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

    public List<String> getAllPosUser(String username, String mabc) throws SQLException {
        List<String> lstData = new ArrayList<>();
        DaoConnect daoconnect = new DaoConnect();
        Connection conn = null;
        conn = daoconnect.getConnect();
        CallableStatement calstatement = null;
        ResultSet reset = null;
//        try {
        //Khoi tao procedure cung voi tham so truyen vao la dau ?
        String strStoreproce = "{call VBSP_IMS_CHAMDIEMTT.SP_LOAD_ALL_POS(?,?,?,?,?)}";
        try {
            //Khoi tao goi store
            calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            calstatement.registerOutParameter(2, oracle.jdbc.OracleTypes.NUMBER);
            calstatement.registerOutParameter(3, oracle.jdbc.OracleTypes.VARCHAR);
            calstatement.registerOutParameter(4, oracle.jdbc.OracleTypes.CURSOR);
            calstatement.setString(1, username);
            calstatement.setString(5, mabc);
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

    public List<QT_DULIEU_NT> get_DetailCT(Connection conn, String sKhoa, String sNgaybc, String sUser,
            String sGrade, List<String> lstArrPoscd, String sID) {
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
            String strStoreproce = "{call VBSP_IMS_CHAMDIEMTT.SP_GET_DETAILCT(?,?,?,?,?,?,?,?,?)}";
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
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham get_BC00230034 " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " get_BC00230034 -> " + e.getMessage());
        }
        return lstBcqt_NT;
    }

    public boolean saveChitiet01(String khoa, String username, String mapgd, String ngaybc, List<QT_DULIEU_NT> lstData, String sCapKT, List<String> lstArrPoscd) throws SQLException {
        Connection connection = new DaoConnect().getConnect();
//        java.util.Dictionary map = (java.util.Dictionary) (connection.getTypeMap());
        Object array[] = lstData.toArray();
        ArrayDescriptor des = ArrayDescriptor
                .createDescriptor(QT_DULIEU_NT.ORACLE_TABLE_TYPE, connection);
        ARRAY array_to_pass = new ARRAY(des, connection, array);

        ArrayDescriptor des_ma = ArrayDescriptor.createDescriptor("POS_CD", connection);

        String[] arrayPoscd = lstArrPoscd.toArray(new String[0]);

        ARRAY oracle_arrayPos = new ARRAY(des_ma, connection, arrayPoscd);

        CallableStatement cs = null;
        try {
            cs = connection.prepareCall("{call VBSP_IMS_CHAMDIEMTT.SP_SAVE_CHITIET01(?, ?, ?, ?, ? ,?, ?)}");
            cs.setString(1, khoa);
            cs.setString(2, username);
            cs.setString(3, mapgd);
            cs.setString(4, ngaybc);
            cs.setArray(5, array_to_pass);
            cs.setString(6, sCapKT);
            cs.setArray(7, oracle_arrayPos);
            cs.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Loi trong ham saveGscmr01 " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " saveGscmr01 -> " + e.getMessage());
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

    public List<ListValue> getLOV(String username, String type, String capbc, String sNgaybc) {
        List<ListValue> lstDMNgNhan = new ArrayList<ListValue>();
        try {

            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call VBSP_IMS_CHAMDIEMTT.SP_GET_LOV(?, ?, ?,?,?,?,?)}";
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

    public String getQueryTableDetail1(String khoa, String mact, String ngaybc, String mapgd, String username, String Capbc, List<String> lstArrPoscd) throws SQLException, Exception {
        String outtable = "";
        Connection conn = null;
        ResultSet reset = null;
        CallableStatement calstatement = null;
        try {
            conn = new DaoConnect().getConnect();

            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call VBSP_IMS_CHAMDIEMTT.sp_get_table_detail(?,?,?,?,?,?,?,?)}";
            ArrayDescriptor des = ArrayDescriptor.createDescriptor("POS_CD", conn);
            String[] arrayPoscd = lstArrPoscd.toArray(new String[0]);
            ARRAY oracle_arrayPoscd = new ARRAY(des, conn, arrayPoscd);
            //Khoi tao goi store
            calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            calstatement.registerOutParameter(8, oracle.jdbc.OracleTypes.CURSOR);
            calstatement.setString(1, khoa);
            calstatement.setString(2, mact);
            calstatement.setString(3, ngaybc);
            calstatement.setString(4, mapgd);
            calstatement.setString(5, username);
            calstatement.setString(6, Capbc);
            calstatement.setArray(7, oracle_arrayPoscd);
            //Thuc hien execute lay du lieu
            calstatement.execute();
            reset = (ResultSet) calstatement.getObject(8);

            ResultSetMetaData resetMetaData = reset.getMetaData();
            String Tieude = "<tr>";
            for (int i = 0; i < resetMetaData.getColumnCount(); i++) {
                Tieude += "<th>" + resetMetaData.getColumnName(i + 1) + "</th>";
            }
            Tieude += "</tr>";
            String DetailData = "";
            while (reset.next()) {
                DetailData = DetailData + "<tr>";
                for (int i = 0; i < resetMetaData.getColumnCount(); i++) {
                    String value = reset.getString(i + 1);
                    String col = value.equals("null") ? "" : value;

                    DetailData = DetailData + "<td>" + col + "</td>";
                }
                DetailData = DetailData + "</tr>";
            }

            outtable = Tieude + DetailData;
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
        return outtable;
    }

    public HashMap<String, String> getQueryTableDetail(String khoa, String mact, String ngaybc, String mapgd, String username, String Capbc, List<String> lstArrPoscd) throws SQLException, Exception {
        String outtable = "", thuyetminh = "", tenchitieu = "", exceltable = "";
        Connection conn = null;
        ResultSet reset = null;
        CallableStatement calstatement = null;
        HashMap<String, String> mapvalue = new HashMap<>();
        try {
            conn = new DaoConnect().getConnect();

            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call VBSP_IMS_CHAMDIEMTT.sp_get_table_detail(?,?,?,?,?,?,?,?,?,?,?)}";
            ArrayDescriptor des = ArrayDescriptor.createDescriptor("POS_CD", conn);
            String[] arrayPoscd = lstArrPoscd.toArray(new String[0]);
            ARRAY oracle_arrayPoscd = new ARRAY(des, conn, arrayPoscd);
            //Khoi tao goi store
            calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            calstatement.registerOutParameter(8, oracle.jdbc.OracleTypes.CURSOR);
            calstatement.registerOutParameter(9, oracle.jdbc.OracleTypes.CLOB);
            calstatement.registerOutParameter(10, oracle.jdbc.OracleTypes.VARCHAR);
            calstatement.registerOutParameter(11, oracle.jdbc.OracleTypes.INTEGER);
            calstatement.setString(1, khoa);
            calstatement.setString(2, mact);
            calstatement.setString(3, ngaybc);
            calstatement.setString(4, mapgd);
            calstatement.setString(5, username);
            calstatement.setString(6, Capbc);
            calstatement.setArray(7, oracle_arrayPoscd);
            //Thuc hien execute lay du lieu
            calstatement.execute();
            reset = (ResultSet) calstatement.getObject(8);
            Clob clob = calstatement.getClob(9);
            tenchitieu = calstatement.getString(10);
            int duocLoaiTru =  calstatement.getString(11) != null ? Integer.valueOf(calstatement.getString(11)) : 0; 
            //Lay cursor ra resultset
            //Get du lieu tra ra tham so thu 1
            if (clob != null) {
                thuyetminh = clob.getSubString(1, (int) clob.length());
                clob.free();
            }
            ResultSetMetaData resetMetaData = reset.getMetaData();
            String Tieude = "<tr>";
            for (int i = 0; i < resetMetaData.getColumnCount(); i++) {
                String colName = resetMetaData.getColumnName(i + 1);
                switch (colName) {
                    case "Key":
                        Tieude += "<th style='display:none;'>" + resetMetaData.getColumnName(i + 1) + "</th>";
                        break;                    
                    case "Code":
                        Tieude += "<th style='display:none;'>" + resetMetaData.getColumnName(i + 1) + "</th>";
                        break;     
                    case "CN Đề nghị":
                        if(duocLoaiTru == 1) {
                            Tieude += "<th data-f-bold='true'>" + "CN ĐN loại trừ" + "</th>";
                        }
                        break;
                    case "CN Lý do":
                        if(duocLoaiTru == 1) {
                            Tieude += "<th data-f-bold='true'>" + "Lý do loại trừ" + "</th>";
                        }
                        break;
                    case "TW Duyệt":
                        if(duocLoaiTru == 1) {
                            Tieude += "<th data-f-bold='true'>" + "TW Phê duyệt" + "</th>";
                        }
                        break;
                    case "TW Lý do":
                        if(duocLoaiTru == 1) {
                            Tieude += "<th data-f-bold='true'>" + "Lý do từ chối" + "</th>";
                        }
                        break;
                    default:
                        Tieude += "<th data-f-bold='true'>" + resetMetaData.getColumnName(i + 1) + "</th>";       
                        break;
                }                                
            }            
            Tieude += "</tr>";
            String DetailData = "", ExcelData = "";
            int j = 0;
            while (reset.next()) {
                DetailData = DetailData + "<tr>";
                ExcelData = ExcelData + "<tr>";
                for (int i = 0; i < resetMetaData.getColumnCount(); i++) {
                    String value = reset.getString(i + 1);
                    String col = value == null ? "" : value;
                    String strDataType = resetMetaData.getColumnTypeName(i + 1);
                    String colName = resetMetaData.getColumnName(i + 1);
                    switch (colName) {
                        case "Key":
                            DetailData = DetailData + "<td style='display:none;'> <input type='hidden' value='" + col + "' name='lstDeNghiLoaiTru["+j+"].Key'/> </td>";
                            ExcelData = ExcelData + "<td style='display:none;'> " + col + "</td>";
                            break;
                        case "Code":
                            DetailData = DetailData + "<td style='display:none;'> <input type='hidden' value='" + col + "' name='lstDeNghiLoaiTru["+j+"].Code'/> </td>";
                            ExcelData = ExcelData  + "<td style='display:none;'> " + col + " </td>";
                            break;
                        case "Mã PGD":
                            DetailData = DetailData + "<td> <input type='text' value='" + col + "' name='lstDeNghiLoaiTru["+j+"].MaPGD' readonly=true title='" + col + "'/> </td>";
                            ExcelData = ExcelData + "<td> " + col + "</td>";
                            break;
                        case "Ngày báo cáo":
                            DetailData = DetailData + "<td> <input type='text' value='" + col + "' name='lstDeNghiLoaiTru["+j+"].NgayBC' readonly=true title='" + col + "'/> </td>";
                            ExcelData = ExcelData + "<td> " + col + "</td>";
                            break;
                        case "CN Đề nghị":
                            if(duocLoaiTru == 1) {
                                if (Integer.valueOf(Capbc) == 2) {
                                    if (!"".equals(col) && Integer.valueOf(col) == 1) {
                                        DetailData = DetailData + "<td> <input type='checkbox' class='dynamic_checkbox_cn' name='lstDeNghiLoaiTru["+j+"].CN_DeNghi_LoaiTru' value='true' checked /></td>";
                                        ExcelData = ExcelData + "<td> X </td>";
                                    } else {
                                        DetailData = DetailData + "<td> <input type='checkbox' class='dynamic_checkbox_cn' name='lstDeNghiLoaiTru["+j+"].CN_DeNghi_LoaiTru' /></td>";
                                        ExcelData = ExcelData + "<td>  </td>";
                                    }
                                } else {
                                    if (Integer.valueOf(Capbc) == 3) {
                                        if (!"".equals(col) && Integer.valueOf(col) == 1) {
                                            DetailData = DetailData + "<td> <input type='checkbox' class='dynamic_checkbox_cn' name='lstDeNghiLoaiTru["+j+"].CN_DeNghi_LoaiTru' value='true' checked /></td>";
                                            ExcelData = ExcelData + "<td> X </td>";
                                        } else {
                                            DetailData = DetailData + "<td> <input type='checkbox' class='dynamic_checkbox_cn' name='lstDeNghiLoaiTru["+j+"].CN_DeNghi_LoaiTru'/></td>";
                                            ExcelData = ExcelData + "<td>  </td>";
                                        }
                                    } else {
                                        if (!"".equals(col) && Integer.valueOf(col) == 1) {
                                            DetailData = DetailData + "<td> <input type='checkbox' class='dynamic_checkbox_cn' name='lstDeNghiLoaiTru["+j+"].CN_DeNghi_LoaiTru' value='true' checked disabled='true'/></td>";
                                            ExcelData = ExcelData + "<td> X </td>";
                                        } else {
                                            DetailData = DetailData + "<td> <input type='checkbox' class='dynamic_checkbox_cn' name='lstDeNghiLoaiTru["+j+"].CN_DeNghi_LoaiTru' disabled='true'/></td>";
                                            ExcelData = ExcelData + "<td>  </td>";
                                        }
                                    }
                                }
                            }
                            break;
                        case "CN Lý do":
                            if(duocLoaiTru == 1) {
                                if (Integer.valueOf(Capbc) == 2) {
                                    DetailData = DetailData + "<td width='200px'> <input type='text' width='100%' name='lstDeNghiLoaiTru["+j+"].CN_LyDo' value='"+ col + "' /></td>";                                    
                                } else {
                                    DetailData = DetailData + "<td width='200px'> <input type='text' width='100%' name='lstDeNghiLoaiTru["+j+"].CN_LyDo' value='"+ col + "' title='" + col + "' disabled='true'/></td>";
                                }
                                ExcelData = ExcelData + "<td> " + col + " </td>";
                            }
                            break;
                        case "TW Duyệt":
                            if(duocLoaiTru == 1) {
                                if (Integer.valueOf(Capbc) != 3) {
                                    if (!"".equals(col) && Integer.valueOf(col) == 1) {
                                        DetailData = DetailData + "<td> <input type='checkbox' class='dynamic_checkbox_tw' name='lstDeNghiLoaiTru[" + j+"].TW_Duyet_DeNghi' value='true' checked disabled='true' /></td>";
                                        ExcelData = ExcelData + "<td> X </td>";
                                    } else {
                                        DetailData = DetailData + "<td> <input type='checkbox' class='dynamic_checkbox_tw'  name='lstDeNghiLoaiTru[" + j+"].TW_Duyet_DeNghi' disabled='true'/></td>";
                                        ExcelData = ExcelData + "<td> </td>";
                                    }                                
                                } else {
                                    if (!"".equals(col) && Integer.valueOf(col) == 1) {
                                        DetailData = DetailData + "<td> <input type='checkbox' class='dynamic_checkbox_tw' name='lstDeNghiLoaiTru[" + j+"].TW_Duyet_DeNghi' value='true' checked /></td>";
                                        ExcelData = ExcelData + "<td> X </td>";
                                    } else {
                                        DetailData = DetailData + "<td> <input type='checkbox' class='dynamic_checkbox_tw'  name='lstDeNghiLoaiTru[" + j+"].TW_Duyet_DeNghi' /></td>";
                                        ExcelData = ExcelData + "<td> </td>";
                                    }    
                                }
                            }
                            break;
                        case "TW Lý do":
                            if(duocLoaiTru == 1) {
                                if (Integer.valueOf(Capbc) != 3) {
                                    DetailData = DetailData + "<td width='200px'> <input type='text' width='100%' name='lstDeNghiLoaiTru["+j+"].TW_LyDo' value='" + col + "' title='" + col + "' disabled='true'/></td>";
                                } else {
                                    DetailData = DetailData + "<td width='200px'> <input type='text' width='100%' name='lstDeNghiLoaiTru["+j+"].TW_LyDo' value='" + col + "' /></td>";
                                }
                                ExcelData = ExcelData + "<td> " + col +" </td>";
                            }
                            break;
                        case "Mô tả lỗi":
                            DetailData = DetailData + "<td width='200px'> <input type='text' value='" + col + "' readonly=true title='" + col + "'/> </td>";
                            ExcelData = ExcelData + "<td> " + col +" </td>";
                            break;
                        default:
                            if (strDataType.equals("NUMBER")) {
                                col = col.isEmpty() ? "0" : col;                                
                                double colVal = Double.valueOf(col);
                                DecimalFormat numberFormat;
                                if (colVal == Math.floor(colVal)) {
                                    numberFormat = new DecimalFormat("##,###");
                                    String colStr = numberFormat.format(colVal);                                 
                                    DetailData = DetailData + "<td> <input type='text' value='" + col + "' readonly=true class=\"number \" title='" + colStr + "'/> </td>";                                
                                } else {
                                    numberFormat = new DecimalFormat("##,###.##");
                                    String colStr = numberFormat.format(colVal);                                 
                                    DetailData = DetailData + "<td> <input type='text' value='" + col + "' readonly=true class=\"number2 \" title='" + colStr + "'/> </td>";                                
                                }                                
                                ExcelData = ExcelData + "<td data-t=\"n\">" + col + "</td>";
                            } else {
                                DetailData = DetailData + "<td> <input type='text' value='" + col + "' readonly=true title='" + col + "'/> </td>";
                                ExcelData = ExcelData + "<td> " + col +" </td>";
                            }       
                            
                            break;
                    }
                }

                DetailData = DetailData + "</tr>";
                ExcelData = ExcelData + "</tr>";
                j++;
            }
            outtable = Tieude + DetailData;
            exceltable = Tieude + ExcelData;

            mapvalue.put("DETAILDATA", outtable);
            mapvalue.put("THUYETMINH", thuyetminh);
            mapvalue.put("TENCHITIEU", tenchitieu);
            mapvalue.put("EXCELDATA", exceltable);
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

    public boolean insertCDTTKHNV(String fileName, Date ngaybc, String username, List<ModelExcel> lstExcel) throws Exception, SQLException {
        boolean bSuccess = true;
        DaoConnect daoconnect = new DaoConnect();
        Connection conn = null;

        PreparedStatement insert = null;
        CallableStatement statementDelete = null;
        try {
            conn = daoconnect.getConnect();
            String sInsert = "insert into cdtt_khnv(FILENAME, NGAYBC, STT, MACN, TENCN, N1, N2, N3, N4, N5, N6, N7, N8,"
                    + " N9, N10, N11, N12, N13, N14, N15, N16,N17,N18,N19,N20,N21,N22,N23,N24,N25,N26,N27,N28,N29,N30,N31,N32,N33,N34,N35,nguoinhap)\n"
                    + "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            conn.setAutoCommit(false);
            insert = conn.prepareStatement(sInsert);
            String Delete = "delete from cdtt_khnv where ngaybc=?";
            statementDelete = conn.prepareCall(Delete);
            statementDelete.setDate(1, new java.sql.Date(ngaybc.getTime()));

            statementDelete.execute();
            for (int i = 0; i < lstExcel.size(); i++) {
                ModelExcel value = lstExcel.get(i);
                insert.setString(1, fileName);
                insert.setDate(2, new java.sql.Date(ngaybc.getTime()));
                insert.setString(3, value.getC1());
                insert.setString(4, value.getC2());
                insert.setString(5, value.getC3());
                insert.setDouble(6, value.getN1());
                insert.setDouble(7, value.getN2());
                insert.setDouble(8, value.getN3());
                insert.setDouble(9, value.getN4());
                insert.setDouble(10, value.getN5());
                insert.setDouble(11, value.getN6());
                insert.setDouble(12, value.getN7());
                insert.setDouble(13, value.getN8());
                insert.setDouble(14, value.getN9());
                insert.setDouble(15, value.getN10());
                insert.setDouble(16, value.getN11());
                insert.setDouble(17, value.getN12());
                insert.setDouble(18, value.getN13());
                insert.setDouble(19, value.getN14());
                insert.setDouble(20, value.getN15());
                insert.setDouble(21, value.getN16());
                insert.setDouble(22, value.getN17());
                insert.setDouble(23, value.getN18());
                insert.setDouble(24, value.getN19());
                insert.setDouble(25, value.getN20());
                insert.setDouble(26, value.getN21());
                insert.setDouble(27, value.getN22());
                insert.setDouble(28, value.getN23());
                insert.setDouble(29, value.getN24());
                insert.setDouble(30, value.getN25());
                insert.setDouble(31, value.getN26());
                insert.setDouble(32, value.getN27());
                insert.setDouble(33, value.getN28());
                insert.setDouble(34, value.getN29());
                insert.setDouble(35, value.getN30());
                insert.setDouble(36, value.getN31());
                insert.setDouble(37, value.getN32());
                insert.setDouble(38, value.getN33());
                insert.setDouble(39, value.getN34());
                insert.setDouble(40, value.getN35());

                insert.setString(41, username);
                insert.execute();
                //lay ra so lon nhat cua ma khach hang
                //Lay ra ma khach hang

            }
            conn.commit();
            conn.setAutoCommit(true);
            bSuccess = true;

        } catch (SQLException e) {
            if (conn != null) {
                try {
                    System.err.print("Transaction is being rolled back");
                    conn.rollback();
                } catch (SQLException excep) {
                    CoreLogger.error(this.getClass().getCanonicalName() + " Exception-> saveDsHongheo " + e.getMessage());
                    System.err.println(" Exception-> saveDsHongheo " + e.getMessage());
                }
            }
            CoreLogger.error(this.getClass().getCanonicalName() + " Exception-> saveDsHongheo " + e.getMessage());
            System.err.println(" Exception-> saveDsHongheo " + e.getMessage());
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

//    SP_DANHSACH_CMNV_CHUADUYET(PV_KHOA       IN     VARCHAR2,
//                                      PV_USERNAME   IN     VARCHAR2,
//                                      PV_GRADE      IN     VARCHAR,
//                                      PV_NGAYBC     IN     VARCHAR2,
//                                      PV_ARR_POS    IN     POS_CD,
//                                      CRSDATA          OUT SYS_REFCURSOR)
    public List<QT_DULIEU_NT> getCMNVTWChuaDuyet(String sKhoa, String sNgaybc, String sUser,
            String sGrade, List<String> lstArrPoscd) throws SQLException {
        List<QT_DULIEU_NT> lstBcqt_NT = new ArrayList<QT_DULIEU_NT>();
        DaoConnect daoconnect = new DaoConnect();
        Connection conn = null;
        CallableStatement calstatement = null;
        //Khoi tao procedure cung voi tham so truyen vao la dau ?
        String strStoreproce = "{call VBSP_IMS_CHAMDIEMTT.SP_DANHSACH_CMNV_CHUADUYET(?,?,?,?,?,?)}";
        ResultSet reset = null;

        try {
            conn = daoconnect.getConnect();
            ArrayDescriptor des = ArrayDescriptor.createDescriptor("POS_CD", conn);
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
            CoreLogger.error(this.getClass().getName() + " getCMNVTWChuaDuyet -> " + e.getMessage());
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

    public List<QT_DULIEU_NT> getHOIDONGCNChuaDuyet(String sKhoa, String sNgaybc, String sUser,
            String sGrade, List<String> lstArrPoscd) throws SQLException {
        List<QT_DULIEU_NT> lstBcqt_NT = new ArrayList<QT_DULIEU_NT>();
        DaoConnect daoconnect = new DaoConnect();
        Connection conn = null;
        CallableStatement calstatement = null;
        //Khoi tao procedure cung voi tham so truyen vao la dau ?
        String strStoreproce = "{call VBSP_IMS_CHAMDIEMTT.SP_HOIDONGCN_CHUADUYET(?,?,?,?,?,?)}";
        ResultSet reset = null;

        try {
            conn = daoconnect.getConnect();
            ArrayDescriptor des = ArrayDescriptor.createDescriptor("POS_CD", conn);
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

    public int checkTVIEN_Info(List<QT_DULIEU_NT> lstData, String skhoa, String sNgaybc, String sUser, String sCapbc) throws SQLException {
        int _retVal = 0;
        Connection conn = new DaoConnect().getConnect();
        CallableStatement calstatement = null;
        ResultSet reset = null;

        Object array[] = lstData.toArray();
        ArrayDescriptor des = ArrayDescriptor
                .createDescriptor(QT_DULIEU_NT.ORACLE_TABLE_TYPE, conn);
        ARRAY array_to_pass = new ARRAY(des, conn, array);

        //Khoi tao procedure cung voi tham so truyen vao la dau ?
        String strStoreproce = "{ ? = call VBSP_IMS_CHAMDIEMTT.F_CHECK_TVIEN_INFO(?,?,?,?,?) }";

        try {
            //Khoi tao goi Store
            calstatement = conn.prepareCall(strStoreproce,
                    ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            calstatement.registerOutParameter(1, oracle.jdbc.OracleTypes.NUMBER);
            calstatement.setArray(2, array_to_pass);

            calstatement.setString(3, skhoa);
            calstatement.setString(4, sNgaybc);
            calstatement.setString(5, sUser);
            calstatement.setString(6, sCapbc);
            //Thuc hien execute lay du lieu
            calstatement.execute();
            //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
            _retVal = calstatement.getInt(1);

        } catch (SQLException e) {
            System.err.print(e.getMessage());
            CoreLogger.error(this.getClass().getName() + " checkTVIEN_Info -> " + e.getMessage());
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
    
    public int checkInputAfter(String mabc, String ngaybc, String capbc, String phongban) throws SQLException {
        int _retVal = 0;
        Connection conn = new DaoConnect().getConnect();
        CallableStatement calstatement = null;
        ResultSet reset = null;
        //Khoi tao procedure cung voi tham so truyen vao la dau ?
        String strStoreproce = "{ ? = call VBSP_IMS_CHAMDIEMTT.F_CHECK_INPUT_AFTER(?, ?, ?, ?) }";

        try {
            //Khoi tao goi Store
            calstatement = conn.prepareCall(strStoreproce,
                    ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            calstatement.registerOutParameter(1, oracle.jdbc.OracleTypes.NUMBER);
            calstatement.setString(2, mabc);
            calstatement.setString(3, ngaybc);
            calstatement.setString(4, capbc);
            calstatement.setString(5, phongban);
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
    
    
    public List<QT_DULIEU_NT> getDataCDTT_CMNV08A(Connection conn, String sKhoa, String sNgaybc, String sUser,
            String sGrade, List<String> lstArrPoscd, String sTTCDTT) {
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
            String strStoreproce = "{call VBSP_IMS_CHAMDIEMTT.SP_GET_DATA_CDTT_CMNV08AB(?,?,?,?,?,?,?,?,?)}";
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
    
    public int checkRuleUser_CN08AB(String UserName, String CapBC, String mabc) throws SQLException {
        int _retVal = 0;
        Connection conn = new DaoConnect().getConnect();
        CallableStatement calstatement = null;
        ResultSet reset = null;
        //Khoi tao procedure cung voi tham so truyen vao la dau ?
        String strStoreproce = "{ ? = call VBSP_IMS_CHAMDIEMTT.F_CHECK_RULE_USER_CN08AB(?, ? ,?) }";

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
    
    public boolean saveCDTT_CMNV08AB(String khoa, String username, String mapgd, String ngaybc, List<QT_DULIEU_NT> lstData, String capbc, String phongban) throws SQLException {
        Connection connection = new DaoConnect().getConnect();
//        java.util.Dictionary map = (java.util.Dictionary) (connection.getTypeMap());
        Object array[] = lstData.toArray();
        ArrayDescriptor des = ArrayDescriptor
                .createDescriptor(QT_DULIEU_NT.ORACLE_TABLE_TYPE, connection);
        ARRAY array_to_pass = new ARRAY(des, connection, array);
        CallableStatement cs = null;
        try {
            cs = connection.prepareCall("{call VBSP_IMS_CHAMDIEMTT.SP_SAVE_CDTT_CMNV08AB(?, ?, ?, ?, ?, ?, ?)}");
            cs.setString(1, khoa);
            cs.setString(2, username);
            cs.setString(3, mapgd);
            cs.setString(4, ngaybc);
            cs.setArray(5, array_to_pass);
            cs.setString(6, capbc);
            cs.setString(7, phongban);
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
    
    
    public List<QT_DULIEU_NT> getLANHDAODUYET_LOI_08AB(String sKhoa, String sNgaybc, String sUser,
            String sGrade, List<String> lstArrPoscd) throws SQLException {
        List<QT_DULIEU_NT> lstBcqt_NT = new ArrayList<QT_DULIEU_NT>();
        DaoConnect daoconnect = new DaoConnect();
        Connection conn = null;
        CallableStatement calstatement = null;
        //Khoi tao procedure cung voi tham so truyen vao la dau ?
        String strStoreproce = "{call VBSP_IMS_CHAMDIEMTT.SP_CHUADUYET_08AB(?,?,?,?,?,?)}";
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
    
    public List<QT_DULIEU_NT> getDataCDTT_CN99(Connection conn, String sKhoa, String sNgaybc, String sUser,
            String sGrade, List<String> lstArrPoscd, String sTTCDTT) {
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
            String strStoreproce = "{call VBSP_IMS_CHAMDIEMTT.SP_GET_DATA_CDTT_CN99(?,?,?,?,?,?,?,?)}";
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
//                calstatement.setArray(5, oracle_arrayPoscd);
                calstatement.setString(8, sTTCDTT);
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
    
    public boolean saveCDTT_CN99(String khoa, String username,  String ngaybc, List<QT_DULIEU_NT> lstData, String capbc) throws SQLException {
        Connection connection = new DaoConnect().getConnect();
//        java.util.Dictionary map = (java.util.Dictionary) (connection.getTypeMap());
        Object array[] = lstData.toArray();
        ArrayDescriptor des = ArrayDescriptor
                .createDescriptor(QT_DULIEU_NT.ORACLE_TABLE_TYPE, connection);
        ARRAY array_to_pass = new ARRAY(des, connection, array);
        CallableStatement cs = null;
        try {
            cs = connection.prepareCall("{call VBSP_IMS_CHAMDIEMTT.SP_SAVE_CDTT_CN99(?, ?, ?, ?, ?)}");
            cs.setString(1, khoa);
            cs.setString(2, username);           
            cs.setString(3, ngaybc);
            cs.setArray(4, array_to_pass);
            cs.setString(5, capbc);            
            cs.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Loi trong ham saveCDTT_CN99 " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " saveCDTT_CN99 -> " + e.getMessage());
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
    
    public List<QT_DULIEU_NT> getDataCDTT_CN08TH(Connection conn, String sKhoa, String sNgaybc, String sUser,
            String sGrade, List<String> lstArrPoscd, String sTTCDTT) {
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
            String strStoreproce = "{call VBSP_IMS_CHAMDIEMTT.SP_GET_DATA_CDTT_CN08TH(?,?,?,?,?,?,?,?,?)}";
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
    
    public String check_CN99_Info(List<QT_DULIEU_NT> lstData, String skhoa, String sNgaybc, String sUser, String sCapbc) throws SQLException {
        String _retVal = "";
        Connection conn = new DaoConnect().getConnect();
        CallableStatement calstatement = null;
        ResultSet reset = null;

        Object array[] = lstData.toArray();
        ArrayDescriptor des = ArrayDescriptor
                .createDescriptor(QT_DULIEU_NT.ORACLE_TABLE_TYPE, conn);
        ARRAY array_to_pass = new ARRAY(des, conn, array);

        //Khoi tao procedure cung voi tham so truyen vao la dau ?
        String strStoreproce = "{ ? = call VBSP_IMS_CHAMDIEMTT.F_CHECK_INFO_CN99(?,?,?,?,?) }";

        try {
            //Khoi tao goi Store
            calstatement = conn.prepareCall(strStoreproce,
                    ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            calstatement.registerOutParameter(1, oracle.jdbc.OracleTypes.VARCHAR);
            calstatement.setArray(2, array_to_pass);

            calstatement.setString(3, skhoa);
            calstatement.setString(4, sNgaybc);
            calstatement.setString(5, sUser);
            calstatement.setString(6, sCapbc);
            //Thuc hien execute lay du lieu
            calstatement.execute();
            //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
            _retVal = calstatement.getString(1);

        } catch (SQLException e) {
            System.err.print(e.getMessage());
            CoreLogger.error(this.getClass().getName() + " check_CN99_Info -> " + e.getMessage());
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
    
    // Trung bo sung cho phan luu de nghi loai tru, ly do
    public boolean save_LOAI_TRU_CDTT_CN(String khoa, String username, String ngaybc, int capbc, List<DeNghiLoaiTru> lstData) throws SQLException {
        Connection connection = new DaoConnect().getConnect();
        List<SAOKECT_CDTT> convertData = new ArrayList<>();
        for(int i = 0 ; i < lstData.size(); i++) {
            SAOKECT_CDTT item = new SAOKECT_CDTT();
            item.setCDTT_KHOA(lstData.get(i).getKey());
            item.setCDTT_MAPGD(lstData.get(i).getMaPGD());
            item.setCDTT_NGAYBC(new Date());
            item.setCDTT_CN_DENGHI_LOAI_TRU(lstData.get(i).getCN_DeNghi_LoaiTru()!= null&&lstData.get(i).getCN_DeNghi_LoaiTru()==true?1:0);
            item.setCDTT_NGUOI_DE_NGHI(username);
            item.setCDTT_NGAY_DE_NGHI( new Date());
            item.setCDTT_CN_LYDO(lstData.get(i).getCN_LyDo());
            item.setCDTT_TW_DUYET_LOAI_TRU(lstData.get(i).getTW_Duyet_DeNghi() != null && lstData.get(i).getTW_Duyet_DeNghi()==true?1:0);
            item.setCDTT_NGUOI_DUYET(username);
            item.setCDTT_NGAY_DUYET(new Date());
            item.setCDTT_TW_LYDO_TUCHOI(lstData.get(i).getTW_LyDo());
            item.setCDTT_TRANG_THAI(1);
            item.setCDTT_MA(lstData.get(i).getCode());
            convertData.add(item);
        }        
        Object array[] = convertData.toArray();
        ArrayDescriptor des = ArrayDescriptor.createDescriptor(SAOKECT_CDTT.ORACLE_TABLE_TYPE, connection);
        ARRAY array_to_pass = new ARRAY(des, connection, array);
        try (CallableStatement cs = connection.prepareCall("{call VBSP_IMS_CHAMDIEMTT.SP_SAVE_DE_NGHI_LT_CDTT_CN(?, ?, ?, ?, ?)}")) {
            cs.setString(1, khoa);
            cs.setString(2, username);            
            cs.setString(3, ngaybc);            
            cs.setInt(4, capbc);
            cs.setArray(5, array_to_pass);
            cs.execute();
        } catch (SQLException e) {
            System.err.println("Loi trong ham saveCDTT_CN " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " saveCDTT_CN -> " + e.getMessage());
            return false;
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
        return true;
    }
    
    
    public List<QT_DULIEU_NT> getDataCDTT_CDTT_TCCB01(Connection conn, String sNgaybc, String sUser,
            String sGrade) {
        List<QT_DULIEU_NT> lstBcqt_NT = new ArrayList<QT_DULIEU_NT>();
        try {
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call VBSP_IMS_CHAMDIEMTT.SP_GET_DATA_CDTT_TCCB01(?,?,?,?,?,?)}";
            ResultSet reset = null;

            try {
                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                calstatement.registerOutParameter(4, oracle.jdbc.OracleTypes.NUMBER);
                calstatement.registerOutParameter(5, oracle.jdbc.OracleTypes.VARCHAR);
                calstatement.registerOutParameter(6, oracle.jdbc.OracleTypes.CURSOR);                
                calstatement.setString(1, sUser);
                calstatement.setString(2, sGrade);
                calstatement.setString(3, sNgaybc);
                //Thuc hien execute lay du lieu
                calstatement.execute();
                //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
                int pn_err_cd = calstatement.getInt(4);
                //thu hien lay mo ta loi
                String strEdd_txt = calstatement.getString(5);
                //Lay cursor ra resultset
                reset = (ResultSet) calstatement.getObject(6);
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
    
    public boolean saveCDTT_CDTT_TCCB01( String username, String mapgd, String ngaybc, List<QT_DULIEU_NT> lstData, String capbc) throws SQLException {
        Connection connection = new DaoConnect().getConnect();
//        java.util.Dictionary map = (java.util.Dictionary) (connection.getTypeMap());
        Object array[] = lstData.toArray();
        ArrayDescriptor des = ArrayDescriptor
                .createDescriptor(QT_DULIEU_NT.ORACLE_TABLE_TYPE, connection);
        ARRAY array_to_pass = new ARRAY(des, connection, array);
        CallableStatement cs = null;
        try {
            cs = connection.prepareCall("{call VBSP_IMS_CHAMDIEMTT.SP_SAVE_CDTT_CDTT_TCCB01(?, ?, ?, ?, ?)}");            
            cs.setString(1, username);
            cs.setString(2, mapgd);
            cs.setString(3, ngaybc);
            cs.setArray(4, array_to_pass);
            cs.setString(5, capbc);            
            cs.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Loi trong ham saveCDTT_CDTT_TCCB01 " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " saveCDTT_CDTT_TCCB01 -> " + e.getMessage());
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
    
    public String check_Info_Tccb01(List<QT_DULIEU_NT> lstData, String skhoa, String sNgaybc, String sUser, String sCapbc) throws SQLException {
        String _retVal = "AAA";
        Connection conn = new DaoConnect().getConnect();
        CallableStatement calstatement = null;
        ResultSet reset = null;

        Object array[] = lstData.toArray();
        ArrayDescriptor des = ArrayDescriptor
                .createDescriptor(QT_DULIEU_NT.ORACLE_TABLE_TYPE, conn);
        ARRAY array_to_pass = new ARRAY(des, conn, array);

        //Khoi tao procedure cung voi tham so truyen vao la dau ?
        String strStoreproce = "{ ? = call VBSP_IMS_CHAMDIEMTT.F_CHECK_INFO_TCCB01(?,?,?,?,?) }";

        try {
            //Khoi tao goi Store
            calstatement = conn.prepareCall(strStoreproce,
                    ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            calstatement.registerOutParameter(1, oracle.jdbc.OracleTypes.VARCHAR);
            calstatement.setArray(2, array_to_pass);

            calstatement.setString(3, skhoa);
            calstatement.setString(4, sNgaybc);
            calstatement.setString(5, sUser);
            calstatement.setString(6, sCapbc);
            //Thuc hien execute lay du lieu
            calstatement.execute();
            //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
            _retVal = calstatement.getString(1);

        } catch (SQLException e) {
            System.err.print(e.getMessage());
            CoreLogger.error(this.getClass().getName() + " check_Info_Tccb01 -> " + e.getMessage());
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
    
    public List<QT_DULIEU_NT> getDataCDTT_RESET_TCCB01(Connection conn, String sNgaybc, String sUser,
            String sGrade) {
        List<QT_DULIEU_NT> lstBcqt_NT = new ArrayList<QT_DULIEU_NT>();
        try {
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call VBSP_IMS_CHAMDIEMTT.SP_GET_DATA_RESET_TCCB01(?,?,?,?,?,?)}";
            ResultSet reset = null;

            try {
                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                calstatement.registerOutParameter(4, oracle.jdbc.OracleTypes.NUMBER);
                calstatement.registerOutParameter(5, oracle.jdbc.OracleTypes.VARCHAR);
                calstatement.registerOutParameter(6, oracle.jdbc.OracleTypes.CURSOR);                
                calstatement.setString(1, sUser);
                calstatement.setString(2, sGrade);
                calstatement.setString(3, sNgaybc);
                //Thuc hien execute lay du lieu
                calstatement.execute();
                //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
                int pn_err_cd = calstatement.getInt(4);
                //thu hien lay mo ta loi
                String strEdd_txt = calstatement.getString(5);
                //Lay cursor ra resultset
                reset = (ResultSet) calstatement.getObject(6);
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
    
    public List<QT_DULIEU_NT> getDataCDTT_CDTT_TCCB02(Connection conn, String sNgaybc, String sUser,
            String sGrade) {
        List<QT_DULIEU_NT> lstBcqt_NT = new ArrayList<QT_DULIEU_NT>();
        try {
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call VBSP_IMS_CHAMDIEMTT.SP_GET_DATA_CDTT_TCCB02(?,?,?,?,?,?)}";
            ResultSet reset = null;

            try {
                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                calstatement.registerOutParameter(4, oracle.jdbc.OracleTypes.NUMBER);
                calstatement.registerOutParameter(5, oracle.jdbc.OracleTypes.VARCHAR);
                calstatement.registerOutParameter(6, oracle.jdbc.OracleTypes.CURSOR);                
                calstatement.setString(1, sUser);
                calstatement.setString(2, sGrade);
                calstatement.setString(3, sNgaybc);
                //Thuc hien execute lay du lieu
                calstatement.execute();
                //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
                int pn_err_cd = calstatement.getInt(4);
                //thu hien lay mo ta loi
                String strEdd_txt = calstatement.getString(5);
                //Lay cursor ra resultset
                reset = (ResultSet) calstatement.getObject(6);
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
    
    public boolean saveCDTT_CDTT_TCCB02( String username, String mapgd, String ngaybc, List<QT_DULIEU_NT> lstData, String capbc) throws SQLException {
        Connection connection = new DaoConnect().getConnect();
//        java.util.Dictionary map = (java.util.Dictionary) (connection.getTypeMap());
        Object array[] = lstData.toArray();
        ArrayDescriptor des = ArrayDescriptor
                .createDescriptor(QT_DULIEU_NT.ORACLE_TABLE_TYPE, connection);
        ARRAY array_to_pass = new ARRAY(des, connection, array);
        CallableStatement cs = null;
        try {
            cs = connection.prepareCall("{call VBSP_IMS_CHAMDIEMTT.SP_SAVE_CDTT_CDTT_TCCB02(?, ?, ?, ?, ?)}");            
            cs.setString(1, username);
            cs.setString(2, mapgd);
            cs.setString(3, ngaybc);
            cs.setArray(4, array_to_pass);
            cs.setString(5, capbc);            
            cs.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Loi trong ham saveCDTT_CDTT_TCCB01 " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " saveCDTT_CDTT_TCCB01 -> " + e.getMessage());
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
    
    
    public List<CHUONGTRINH_LOAITRU> getDataChuongtrinhLoaitru(String mapgd, String ngaybc, String capbc, String username,
             List<String> lstArrPoscd) throws SQLException {
        List<CHUONGTRINH_LOAITRU> lstChuongtrinhLT = new ArrayList<CHUONGTRINH_LOAITRU>();

        Connection conn = null;
//            conn = daoconnect.getConnect();
        CallableStatement calstatement = null;
        //Khoi tao procedure cung voi tham so truyen vao la dau ?
        String strStoreproce = "{call VBSP_IMS_CHAMDIEMTT.SP_GET_CHUONGTRINH_LOAITRU(?,?,?,?,?,?)}";
        ResultSet reset = null;

        try {
            conn = new DaoConnect().getConnect();
            ArrayDescriptor des = ArrayDescriptor.createDescriptor("POS_CD", conn);
            String[] arrayPoscd = lstArrPoscd.toArray(new String[0]);
            ARRAY oracle_arrayPoscd = new ARRAY(des, conn, arrayPoscd);
//            DaoConnect daoconnect = new DaoConnect();
            //Khoi tao goi store
            calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            calstatement.registerOutParameter(6, oracle.jdbc.OracleTypes.CURSOR);
            calstatement.setString(1, mapgd);
            calstatement.setString(2, ngaybc);
            calstatement.setString(3, capbc);
            calstatement.setString(4, username);
            calstatement.setArray(5, oracle_arrayPoscd);
            //Thuc hien execute lay du lieu
            calstatement.execute();
            //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)

            //Lay cursor ra resultset
            reset = (ResultSet) calstatement.getObject(6);
            while (reset.next()) {

                CHUONGTRINH_LOAITRU value = CHUONGTRINH_LOAITRU.newInstance();
                value.setMacn(reset.getString(1));
                value.setMapgd(reset.getString(2));
                value.setMact(reset.getString(3));
                value.setTenct(reset.getString(4));
                value.setMonthDefault(reset.getString(5));
                value.setMonthList(reset.getString(5));               
                value.setThangMacdinh(reset.getString(5));
                value.setTenPGD(reset.getString(7));
                lstChuongtrinhLT.add(value);
            }

        } catch (SQLException e) {
            System.err.print(e.getMessage());
            CoreLogger.error(this.getClass().getName() + " getDataChuongtrinhLoaitru -> " + e.getMessage());
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
        return lstChuongtrinhLT;
    }
    
    public boolean saveChuongtrinhLoaitru(String username,String mapgd, String ngaybc, List<QT_DULIEU_NT> lstData, String capbc) throws SQLException {
        Connection connection = new DaoConnect().getConnect();
//        java.util.Dictionary map = (java.util.Dictionary) (connection.getTypeMap());
        Object array[] = lstData.toArray();
        ArrayDescriptor des = ArrayDescriptor
                .createDescriptor(QT_DULIEU_NT.ORACLE_TABLE_TYPE, connection);
        ARRAY array_to_pass = new ARRAY(des, connection, array);
        CallableStatement cs = null;
        try {
            cs = connection.prepareCall("{call VBSP_IMS_CHAMDIEMTT.SP_SAVE_CHUONGTRINH_LOAITRU(?, ?, ?, ?, ?)}");
            cs.setString(1, username);
            cs.setString(2, mapgd);
            cs.setString(3, ngaybc);
            cs.setArray(4, array_to_pass);
            cs.setString(5, capbc);
            cs.execute();
        } catch (SQLException e) {
//            e.printStackTrace();
            System.err.println("Loi trong ham saveChuongtrinhLoaitru " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " saveChuongtrinhLoaitru -> " + e.getMessage());
            throw new SQLException(e);
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
    public List<String> getDataSendCTLT(String mapgd, String ngay_bc, String capbc) throws SQLException {
        List<String> lstData = new ArrayList<>();
        DaoConnect daoconnect = new DaoConnect();
        Connection conn = null;
        conn = daoconnect.getConnect();
        CallableStatement calstatement = null;
        ResultSet reset = null;
//        try {
        //Khoi tao procedure cung voi tham so truyen vao la dau ?
        String strStoreproce = "{call VBSP_IMS_CHAMDIEMTT.SP_GET_DATA_CTLT_SYNC(?,?,?,?)}";
        try {
            //Khoi tao goi store
            calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            calstatement.registerOutParameter(4, oracle.jdbc.OracleTypes.CURSOR);
            calstatement.setString(1, mapgd);
            calstatement.setString(2, ngay_bc);
            calstatement.setString(3, capbc);
            //Thuc hien execute lay du lieu
            calstatement.execute();
            //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
            reset = (ResultSet) calstatement.getObject(4);
            while (reset.next()) {

                lstData.add(reset.getString(1));
            }

        } catch (SQLException e) {
            System.err.print(e.getMessage());
            CoreLogger.error(this.getClass().getName() + " getDataSendCTLT -> " + e.getMessage());
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
    
    public boolean putXmlFileCTLT(String fileXml, String mapgd, String ngay_bc,
            String grade, String username, String ngay_gui, String tt_khoa) throws SQLException {
        boolean bSuccess = false;
//        PV_MAPGD IN VARCHAR2, PV_NGAY_BC IN VARCHAR2, PV_GRADE IN VARCHAR2,
//                 PV_NGUOI_GUI IN VARCHAR2, PV_NGAY_GUI IN DATE, PV_FILE_SIZE IN NUMBER, PV_FILE_NAME IN VARCHAR2,
//                 PV_XML_DATA IN XMLTYPE, PV_STATUS_LOCK IN VARCHAR2
        String qry = "{call VBSP_IMS_CHAMDIEMTT.SP_PUT_FILEXML_CTLT(?,?,?,?,?,?,?,?,?)}";//SP_PUT_FILEXML
        DaoConnect daoconnect = new DaoConnect();
        Connection conn = null;
        OraclePreparedStatement sqlStatement = null;
        XMLType xml = null;
        try {
            //convert date cho ngay_gui
            java.sql.Timestamp date_ngay_gui = new java.sql.Timestamp(new SimpleDateFormat("dd-MMM-yyyy hh:mm:ss").parse(ngay_gui).getTime());
            //format date cho ngay bao cao
//            java.sql.Date date_ngay_bc = new java.sql.Date(new SimpleDateFormat("dd-MMM-yyyy").parse(ngay_bc).getTime());
            File xmlFile = new File(fileXml);
            if (!xmlFile.exists()) {
                CoreLogger.error(this.getClass().getName() + " putXmlFileCIC -> Khong tim thay filexml " + fileXml);
                return false;
            }
            BigDecimal value = new BigDecimal(xmlFile.length());
            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();

            Document document = documentBuilder.parse(xmlFile);
            conn = daoconnect.getConnect();

            xml = XMLType.createXML(conn, document);

            sqlStatement = (OraclePreparedStatement) conn.prepareStatement(qry);
            sqlStatement.setString(1, mapgd);
            sqlStatement.setString(2, ngay_bc);
            sqlStatement.setString(3, grade);
            sqlStatement.setString(4, username);
//            sqlStatement.setString(6, ngay_gui);
            sqlStatement.setTimestamp(5, date_ngay_gui);
            sqlStatement.setBigDecimal(6, value);
            sqlStatement.setString(7, xmlFile.getName());
            sqlStatement.setObject(8, xml);
            sqlStatement.setString(9, tt_khoa);
            sqlStatement.execute();

            bSuccess = true;
        } catch (Exception e) {
            System.err.println("Loi trong ham putXmlFileCTLT " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " putXmlFileCTLT -> " + e.getMessage());
            bSuccess = false;
        } finally {

            if (sqlStatement != null) {
                sqlStatement.close();
            }
            if (xml != null) {
                xml.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return bSuccess;
    }
     public String getMsgSendata(String capbc, String ngaybc) throws SQLException {
        String msg = "";
        CallableStatement calstatement = null;
        ResultSet reset = null;
        DaoConnect daoconnect = new DaoConnect();
        Connection conn = null;
        conn = daoconnect.getConnect();
//        try {
        //Khoi tao procedure cung voi tham so truyen vao la dau ?
        String strStoreproce = "{?=call VBSP_IMS_CHAMDIEMTT.F_GET_MSG_SEND_DATA(?,?)}";
        try {
            //Khoi tao goi store
            calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            calstatement.registerOutParameter(1, oracle.jdbc.OracleTypes.VARCHAR);
            calstatement.setString(2, capbc);
            calstatement.setString(3, ngaybc);
            //Thuc hien execute lay du lieu
            calstatement.execute();
            //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
            msg = calstatement.getString(1);

        } catch (SQLException e) {
            System.err.print(e.getMessage());
            CoreLogger.error(this.getClass().getName() + " getMsgSendata -> " + e.getMessage());
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
        return msg;
    }
    public static void main(String[] args)
    {
        String month ="/1/2/3/4/5/6/7/8/9/10/11/12/";
        String str[] = month.split("/");
	List<String> al = new ArrayList<String>();
	al = Arrays.asList(str);
	for(String s: al){
	   System.out.println(s);
	}
    }
}
