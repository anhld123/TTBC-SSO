/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.nhaptaycn.dao;

import vbsp.ims.ktgs.dao.*;
import vbsp.ims.bcqt.dao.*;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;
import oracle.sql.STRUCT;
import oracle.sql.StructDescriptor;
import vbsp.ims.bcqt.model.DULIEU_NT;
import vbsp.ims.bcqt.model.QT_DULIEU_NT;
import vbsp.ims.chamdiemcn.ModelExcelFile;
import vbsp.ims.dao.DaoConnect;
import vbsp.ims.dao.DaoLoadReportParams;
import vbsp.ims.dao.khnv.DaoDieuchinhkh;
import vbsp.ims.export.excel.ExportExcelFile;
import vbsp.ims.loadparams.Combo;
import vbsp.ims.loadparams.ReportParam;
import vbsp.ims.log.CoreLogger;
import vbsp.ims.model.DcptNoModel;
import vbsp.ims.model.ModelTreeNode;
import vbsp.ims.model.khnv.POSModel;
import vbsp.ims.nhaptaycn.action.QT_DULIEU_NT_50;
import vbsp.ims.query.ImsFillParaMeter;
import vbsp.ims.query.ImsPlSqlQuery;
import vbsp.ims.report.fast.ListValue;

/**
 *
 * @author LION
 */
public class DaoNhaptaycnMain {

    public static DaoNhaptaycnMain newInstance() {
        return new DaoNhaptaycnMain();
    }

    public List<ListValue> getAllNhaptaycn(String capbc) {
        List<ListValue> lstAllBcqt = new ArrayList<ListValue>();
        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call VBSP_IMS_NHAPTAYCN.SP_LOAD_ALL_BCQT(?,?,?, ?)}";
            ResultSet reset = null;

            try {
                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                calstatement.registerOutParameter(1, oracle.jdbc.OracleTypes.NUMBER);
                calstatement.registerOutParameter(2, oracle.jdbc.OracleTypes.VARCHAR);
                calstatement.registerOutParameter(3, oracle.jdbc.OracleTypes.CURSOR);
                calstatement.setString(4, capbc);
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
                CoreLogger.error(this.getClass().getName() + " getAllNhaptaycn -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham getAllNhaptaycn " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " getAllNhaptaycn -> " + e.getMessage());
        }
        return lstAllBcqt;
    }

    public List<ListValue> getAllMuasamts(String capbc) {
        List<ListValue> lstAllBcqt = new ArrayList<ListValue>();
        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call VBSP_IMS_NHAPTAYCN.SP_LOAD_ALL_MUASAMTS(?,?,?, ?)}";
            ResultSet reset = null;

            try {
                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                calstatement.registerOutParameter(1, oracle.jdbc.OracleTypes.NUMBER);
                calstatement.registerOutParameter(2, oracle.jdbc.OracleTypes.VARCHAR);
                calstatement.registerOutParameter(3, oracle.jdbc.OracleTypes.CURSOR);
                calstatement.setString(4, capbc);
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
                CoreLogger.error(this.getClass().getName() + " getAllNhaptaycn -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham getAllNhaptaycn " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " getAllNhaptaycn -> " + e.getMessage());
        }
        return lstAllBcqt;
    }

    public List<ListValue> getAllNhaptaycn_SUB() {
        List<ListValue> lstAllBcqt = new ArrayList<ListValue>();
        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call VBSP_IMS_NHAPTAYCN.SP_LOAD_ALL_BCQT_SUB(?,?,?)}";
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
                CoreLogger.error(this.getClass().getName() + " getAllNhaptaycn_SUB -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham getAllNhaptaycn_SUB " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " getAllNhaptaycn_SUB -> " + e.getMessage());
        }
        return lstAllBcqt;
    }

    public List<ModelTreeNode> getDataPosTreeNode(Connection conn, String strUserName, String sGrade, String sKhoa) {
        List<ModelTreeNode> lstPo = new ArrayList<ModelTreeNode>();
        try {
//            DaoConnect daoconnect = new DaoConnect();
//            Connection conn = null;
//            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call VBSP_IMS_NHAPTAYCN.SP_LOAD_POS_TREE_BCQT(?,?,?,?,?,?)}";
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
                calstatement.setString(6, sKhoa);
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

    public List<String> getDataSendNhaptaycn(String type, String khoa, String mapgd, String ngay_bc) throws SQLException {
        List<String> lstData = new ArrayList<>();
        DaoConnect daoconnect = new DaoConnect();
        Connection conn = null;
        conn = daoconnect.getConnect();
        CallableStatement calstatement = null;
        ResultSet reset = null;
//        try {
        //Khoi tao procedure cung voi tham so truyen vao la dau ?
        String strStoreproce = "{call VBSP_IMS_NHAPTAYCN.SP_GET_DATA_Nhaptaycn_SYNC(?,?,?,?,?,?,?)}";
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
            String strStoreproce = "{call VBSP_IMS_NHAPTAYCN.SP_GET_DMKHAC(?,?,?,?)}";
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
                CoreLogger.error(this.getClass().getName() + " getDmKhacNhaptaycn -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham getDmKhacNhaptaycn " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " getDmKhacNhaptaycn -> " + e.getMessage());
        }
        return lstAllBcqt;
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
            String strStoreproce = "{?=call vbsp_ims_nhaptaycn.f_get_casa_teler(?,?,?,?)}";
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

    public List<QT_DULIEU_NT> getDataNhaptaycn_01(Connection conn, String sKhoa, String sNgaybc, String sUser,
            String sGrade, List<String> lstArrPoscd, int iStart, int iEnd, String sTeler) {
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
            String strStoreproce = "{call VBSP_IMS_NHAPTAYCN.SP_GET_DATA_Nhaptaycn01(?,?,?,?,?,?,?,?,?,?,?)}";
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
                    value.setKHOA(reset.getString(2));
//                    value.setTHUTU(reset.getInt(3));
//                    value.setTT_HIENTHI(reset.getString(3));
//                    value.setMA(reset.getString(4));
//                    value.setTEN(reset.getString(5));
//                    value.setNGAYBC(reset.getDate(6));
//                    value.setNAMBC(reset.getInt(7));
                    value.setMAPGD(reset.getString(9));
                    value.setMACN(reset.getString(11));
//                    value.setD1(reset.getString(14));
//                    value.setD1(reset.getString(15));
                    value.setD1(reset.getString(16));
                    value.setD2(reset.getString(17));
                    value.setD3(reset.getString(18));
                    value.setD4(reset.getString(19));
                    value.setD5(reset.getString(20));
                    value.setD6(reset.getString(21));
                    value.setD7(reset.getString(22));
                    value.setD8(reset.getString(23));
                    value.setD9(reset.getString(24));
                    value.setD10(reset.getString(25));
                    value.setD11(reset.getString(26));
                    value.setD12(reset.getString(27));
                    value.setD13(reset.getString(28));
                    value.setD14(reset.getString(29));
                    value.setD15(reset.getString(30));
                    value.setD16(reset.getString(31));
                    value.setD20(reset.getString(1));
//                    value.setFONTFORMAT(reset.getString(46));

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

    public List<QT_DULIEU_NT> getDataNhaptaycn_01_re(Connection conn, String sKhoa, String sNgaybc, String sUser,
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
            String strStoreproce = "{call VBSP_IMS_NHAPTAYCN.SP_GET_DATA_Nhaptaycn01_RE(?,?,?,?,?,?,?,?)}";
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
                CoreLogger.error(this.getClass().getName() + " getDataNhaptaycn_01 -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham getDataNhaptaycn_01 " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " getDataNhaptaycn_01 -> " + e.getMessage());
        }
        return lstBcqt_NT;
    }

    public List<QT_DULIEU_NT> getDataNhaptaycn_02(Connection conn, String sKhoa, String sNgaybc, String sUser,
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
            String strStoreproce = "{call VBSP_IMS_NHAPTAYCN.SP_GET_DATA_Nhaptaycn02(?,?,?,?,?,?,?,?)}";
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
                CoreLogger.error(this.getClass().getName() + " getDataNhaptaycn_02 -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham getDataNhaptaycn_02 " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " getDataNhaptaycn_02 -> " + e.getMessage());
        }
        return lstBcqt_NT;
    }

    public List<QT_DULIEU_NT> getDataNhaptaycn_02_re(Connection conn, String sKhoa, String sNgaybc, String sUser,
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
            String strStoreproce = "{call VBSP_IMS_NHAPTAYCN.SP_GET_DATA_Nhaptaycn02_RE(?,?,?,?,?,?,?,?)}";
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
                CoreLogger.error(this.getClass().getName() + " getDataNhaptaycn_02 -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham getDataNhaptaycn_02 " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " getDataNhaptaycn_02 -> " + e.getMessage());
        }
        return lstBcqt_NT;
    }

//    public boolean saveNhaptaycn01(String khoa, String username, String mapgd, String ngaybc, List<QT_DULIEU_NT> lstData) throws SQLException {
    public boolean saveNhaptaycn01(String sUserName, String sNgaysl, List<QT_DULIEU_NT.saveDulieuNT> lstNt, String sTeler) {
        if (lstNt == null || lstNt.size() == 0) {
            return false;
        }

        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{ call VBSP_IMS_NHAPTAYCN.SP_SAVE_NHAPTAYCN_01(?,?,?,?,?,?)}";
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

    public boolean saveNhaptaycn02(String khoa, String username, String mapgd, String ngaybc, List<QT_DULIEU_NT> lstData) throws SQLException {
        Connection connection = new DaoConnect().getConnect();
//        java.util.Dictionary map = (java.util.Dictionary) (connection.getTypeMap());
        Object array[] = lstData.toArray();
        ArrayDescriptor des = ArrayDescriptor
                .createDescriptor(QT_DULIEU_NT.ORACLE_TABLE_TYPE, connection);
        ARRAY array_to_pass = new ARRAY(des, connection, array);
        CallableStatement cs = null;
        try {
            cs = connection.prepareCall("{call VBSP_IMS_NHAPTAYCN.SP_SAVE_Nhaptaycn_02(?, ?, ?, ?, ?)}");
            cs.setString(1, khoa);
            cs.setString(2, username);
            cs.setString(3, mapgd);
            cs.setString(4, ngaybc);
            cs.setArray(5, array_to_pass);
            cs.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Loi trong ham saveNhaptaycn02 " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " saveNhaptaycn02 -> " + e.getMessage());
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

    public int checkUser(String UserName) throws SQLException {
        int _retVal = 0;
        Connection conn = new DaoConnect().getConnect();
        CallableStatement calstatement = null;
        ResultSet reset = null;
        //Khoi tao procedure cung voi tham so truyen vao la dau ?
        String strStoreproce = "{ ? = call VBSP_IMS_NHAPTAYCN.F_CHECK_USER(?) }";

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
            CoreLogger.error(this.getClass().getName() + " checkUserNhaptaycn -> " + e.getMessage());
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

    public int checkUserMainPos(String UserName, String capbc) throws SQLException {
        int _retVal = 0;
        Connection conn = new DaoConnect().getConnect();
        CallableStatement calstatement = null;
        ResultSet reset = null;
        //Khoi tao procedure cung voi tham so truyen vao la dau ?
        String strStoreproce = "{ ? = call VBSP_IMS_NHAPTAYCN.F_CHECK_USER_MAINPOS(?,?) }";

        try {
            //Khoi tao goi Store
            calstatement = conn.prepareCall(strStoreproce,
                    ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            calstatement.registerOutParameter(1, oracle.jdbc.OracleTypes.NUMBER);
            calstatement.setString(2, UserName);
            calstatement.setString(3, capbc);
            //Thuc hien execute lay du lieu
            calstatement.execute();
            //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
            _retVal = calstatement.getInt(1);

        } catch (SQLException e) {
            System.err.print(e.getMessage());
            CoreLogger.error(this.getClass().getName() + " checkUserNhaptaycn -> " + e.getMessage());
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

    public List<String> getDataSendBcqt(String type, String khoa, String mapgd, String ngay_bc) throws SQLException {
        List<String> lstData = new ArrayList<>();
        DaoConnect daoconnect = new DaoConnect();
        Connection conn = null;
        conn = daoconnect.getConnect();
        CallableStatement calstatement = null;
        ResultSet reset = null;
//        try {
        //Khoi tao procedure cung voi tham so truyen vao la dau ?
        String strStoreproce = "{call VBSP_IMS_NHAPTAYCN.SP_GET_DATA_BCQT_SYNC(?,?,?,?,?,?,?)}";
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
            CoreLogger.error(this.getClass().getName() + " getDataSendNhaptaycn -> " + e.getMessage());
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

    public Map<String, String> getPosByName(List<String> lstMaPGD) throws SQLException {
        Map<String, String> mapData = new HashMap<String, String>();
        DaoConnect daoconnect = new DaoConnect();
        Connection conn = null;
        conn = daoconnect.getConnect();
        CallableStatement calstatement = null;
        ResultSet reset = null;
//        try {
        //Khoi tao procedure cung voi tham so truyen vao la dau ?
        String strStoreproce = "{call VBSP_IMS_NHAPTAYCN.SP_GET_POS_BY_NAME(?,?)}";
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

    public String isCheckPosSend(String type, String Khoa, String UserName, String ngaybc) throws SQLException {
        String pos_cd = "";
        CallableStatement calstatement = null;
        ResultSet reset = null;
        DaoConnect daoconnect = new DaoConnect();
        Connection conn = null;
        conn = daoconnect.getConnect();
//        try {
        //Khoi tao procedure cung voi tham so truyen vao la dau ?
        String strStoreproce = "{?=call VBSP_IMS_NHAPTAYCN.F_CHECK_POS_DATA(?,?,?,?)}";
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
    //--------------------------------------------------------------------------

    public static void main(String[] args) throws Exception {

        List<String> pos = new ArrayList();

        pos.add("001002");
        pos.add("001005");
        pos.add("001003");
        pos.add("001002");
        pos.add("001004");

        DaoNhaptaycnMain.newInstance().getPosByName(pos);
        Connection conn = new DaoConnect().getConnect();
        new DaoNhaptaycnMain().getReportParmamsNhaptaycn(conn, "PHIUT_001", "P0631", "1");
        if (conn != null) {
            conn.close();
        }
    }

    public List<QT_DULIEU_NT> getDataTCTD_B05(Connection conn, String sKhoa, String sNgaybc, String sUser,
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
            String strStoreproce = "{call VBSP_IMS_NHAPTAYCN.SP_GET_DATA_TCTD_B05(?,?,?,?,?,?,?,?)}";
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
                CoreLogger.error(this.getClass().getName() + " SP_GET_DATA_TCTD_B05 -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham SP_GET_DATA_TCTD_B05 " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " SP_GET_DATA_TCTD_B05 -> " + e.getMessage());
        }
        return lstBcqt_NT;
    }

    public List<QT_DULIEU_NT> getDataTCTD_B06(Connection conn, String sKhoa, String sNgaybc, String sUser,
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
            String strStoreproce = "{call VBSP_IMS_NHAPTAYCN.SP_GET_DATA_TCTD_B06(?,?,?,?,?,?,?,?)}";
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

    public List<ReportParam> getReportParmamsNhaptaycn(Connection conn, String sKhoa, String sUserName, String sGrade) {
        ArrayList<ReportParam> report_param_list = new ArrayList<>();

        try {
//            DaoConnect daoconnect = new DaoConnect();
//            Connection conn ;
//            conn = daoconnect.getConnect();
            CallableStatement calstatement;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call VBSP_IMS_NHAPTAYCN.SP_LOAD_PARA_Nhaptaycn(?, ?, ?, ?, ?,?,?)}";
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
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham getColumnReportFast " + e.getMessage());
            CoreLogger.error(DaoLoadReportParams.class.getCanonicalName() + " getReportParmams  -> " + e.getMessage());
        }
        return report_param_list;
    }

    public int getPosSendDataLockNhaptaycn(String type, String khoa, String mapgd, String ngay_bc, String tt_khoa) throws SQLException {
        int nPos = 0;
        DaoConnect daoconnect = new DaoConnect();
        Connection conn = null;

        CallableStatement calstatement = null;
        ResultSet reset = null;
//        try {
        //Khoi tao procedure cung voi tham so truyen vao la dau ?
        String strStoreproce = "{?=call VBSP_IMS_NHAPTAYCN.F_CHECK_POS_LOCK(?,?,?,?,?)}";
        try {
            conn = daoconnect.getConnect();
            //Khoi tao goi store
            calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            calstatement.registerOutParameter(1, oracle.jdbc.OracleTypes.INTEGER);
            calstatement.setString(2, type);
            calstatement.setString(3, khoa);
            calstatement.setString(4, mapgd);
            calstatement.setString(5, ngay_bc);
            calstatement.setString(6, tt_khoa);
            //Thuc hien execute lay du lieu
            calstatement.execute();
            //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
            //Lay cursor ra resultset
            nPos = calstatement.getInt(1);

        } catch (SQLException e) {
            System.err.print(e.getMessage());
            CoreLogger.error(this.getClass().getName() + " getPosSendDataLock -> " + e.getMessage());
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
        return nPos;
    }

    public boolean setStatusLock(String type, String khoa, List<String> lstMapgd, String ngaybc, String tt_khoa, String username, String grade) throws SQLException {
        Connection connection = new DaoConnect().getConnect();
//        java.util.Dictionary map = (java.util.Dictionary) (connection.getTypeMap());
        Object array[] = lstMapgd.toArray();
        ArrayDescriptor des = ArrayDescriptor.createDescriptor("POS_CD", connection);
        ARRAY array_to_pass = new ARRAY(des, connection, array);
        CallableStatement cs = null;
        boolean bSuccess = false;
        try {
            cs = connection.prepareCall("{call VBSP_IMS_NHAPTAYCN.SP_OPEN_PGD(?, ?, ?, ?, ?, ?, ?)}");
            cs.setString(1, type);
            cs.setString(2, khoa);
            cs.setArray(3, array_to_pass);
            cs.setString(4, ngaybc);
            cs.setString(5, tt_khoa);
            cs.setString(6, username);
            cs.setString(7, grade);
            cs.execute();
            bSuccess = true;
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Loi trong ham setStatusLock " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " setStatusLock -> " + e.getMessage());
            bSuccess = false;
        } finally {
            if (cs != null) {
                cs.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return bSuccess;
    }

    //CHUDV: Hàm thực hiện Bind dữ liệu ra màn hình nhập liệu báo cáo Kết quả hoạt động của Ban Đại diện HĐQT 01/BDD
    public List<QT_DULIEU_NT> get_data_ktnb32(Connection conn, String sKhoa, String sNgaybc, String sUser,
            String sGrade, List<String> lstArrPoscd) {
        List<QT_DULIEU_NT> lstBcqt_NT = new ArrayList<QT_DULIEU_NT>();
        try {
            ArrayDescriptor des = ArrayDescriptor.createDescriptor("POS_CD", conn);
            String[] arrayPoscd = lstArrPoscd.toArray(new String[0]);
            ARRAY oracle_arrayPoscd = new ARRAY(des, conn, arrayPoscd);
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call VBSP_IMS_NHAPTAYCN.SP_GET_Nhaptaycn_BDD01(?,?,?,?,?,?,?,?)}";
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
                String strEdd_txt = calstatement.getString(7);      //thu hien lay mo ta loi
                reset = (ResultSet) calstatement.getObject(8);      //Lay cursor ra resultset
                while (reset.next()) {
                    QT_DULIEU_NT value = QT_DULIEU_NT.newInstance();
                    value.setNHAPTAY(reset.getString("NHAPTAY"));
                    value.setCO_TONGHOP(reset.getString("CO_TONGHOP"));
                    value.setD22(reset.getString("D22"));
                    value.setNGUOI_NHAP(reset.getString("NGUOI_NHAP"));
                    value.setNGAY_NHAP(reset.getDate("NGAY_NHAP"));
                    value.setTT_HIENTHI(reset.getString("TT_HIENTHI"));
                    value.setTHUTU(reset.getInt("THUTU"));
                    value.setMA(reset.getString("MA"));
                    value.setTEN(reset.getString("TEN"));
                    value.setD20(reset.getString("D20"));
                    value.setD21(reset.getString("D21"));
                    value.setNGAYBC(reset.getDate("NGAYBC"));
                    value.setKHOA(reset.getString("KHOA"));
                    value.setMAPGD(reset.getString("MAPGD"));
                    value.setMACN(reset.getString("MACN"));
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
                    value.setD15(reset.getString("D15"));
                    value.setD16(reset.getString("D16"));
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
                CoreLogger.error(this.getClass().getName() + " get_data_ktnb32 -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham get_data_ktnb32 " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " get_data_ktnb32 -> " + e.getMessage());
        }
        return lstBcqt_NT;
    }

    public boolean saveNhaptaycn03(String Khoa, String UserName, String sGrade, String MaPGD, String NgayBC, List<QT_DULIEU_NT> lstData) throws SQLException {
        Connection connection = new DaoConnect().getConnect();
//        java.util.Dictionary map = (java.util.Dictionary) (connection.getTypeMap());
        Object array[] = lstData.toArray();
        ArrayDescriptor des = ArrayDescriptor
                .createDescriptor(QT_DULIEU_NT.ORACLE_TABLE_TYPE, connection);
        ARRAY array_to_pass = new ARRAY(des, connection, array);
        CallableStatement cs = null;
        try {
            cs = connection.prepareCall("{call VBSP_IMS_NHAPTAYCN.SP_SAVE_Nhaptaycn_03(?, ?, ?, ?, ?, ?)}");
            cs.setString(1, Khoa);
            cs.setString(2, UserName);
            cs.setString(3, sGrade);
            cs.setString(4, MaPGD);
            cs.setString(5, NgayBC);
            cs.setArray(6, array_to_pass);
            cs.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Loi trong ham saveNhaptaycn03 " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " saveNhaptaycn03 -> " + e.getMessage());
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

    public List<String> getAllPosUser(String username, String khoa) throws SQLException {
        List<String> lstData = new ArrayList<>();
        DaoConnect daoconnect = new DaoConnect();
        Connection conn = null;
        conn = daoconnect.getConnect();
        CallableStatement calstatement = null;
        ResultSet reset = null;
//        try {
        //Khoi tao procedure cung voi tham so truyen vao la dau ?
        String strStoreproce = "{call VBSP_IMS_NHAPTAYCN.SP_LOAD_ALL_POS(?,?,?,?,?)}";
        try {
            //Khoi tao goi store
            calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            calstatement.registerOutParameter(2, oracle.jdbc.OracleTypes.NUMBER);
            calstatement.registerOutParameter(3, oracle.jdbc.OracleTypes.VARCHAR);
            calstatement.registerOutParameter(4, oracle.jdbc.OracleTypes.CURSOR);
            calstatement.setString(1, username);
            calstatement.setString(5, khoa);
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

    public List<ListValue> getNguyennhanDm() {
        List<ListValue> lstNNDM = new ArrayList<ListValue>();
        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call VBSP_IMS_NHAPTAYCN.SP_GET_NGUYENNHANDM(?,?,?)}";
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

                    lstNNDM.add(new ListValue(key, des));
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
                CoreLogger.error(this.getClass().getName() + " getNguyennhanDm -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham getNguyennhanDm " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " getNguyennhanDm -> " + e.getMessage());
        }
        return lstNNDM;
    }

    public List<QT_DULIEU_NT> getDataPhiUT_SP(Connection conn, String sKhoa, String sNgaybc, String sUser,
            String sGrade, List<String> lstArrPoscd, String sNhaDT) {
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
            String strStoreproce = "{call VBSP_IMS_NHAPTAYCN.SP_GET_DATA_PHIUT_SANPHAM(?,?,?,?,?,?,?,?,?)}";
            ResultSet reset = null;

            try {
                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                calstatement.registerOutParameter(7, oracle.jdbc.OracleTypes.NUMBER);
                calstatement.registerOutParameter(8, oracle.jdbc.OracleTypes.VARCHAR);
                calstatement.registerOutParameter(9, oracle.jdbc.OracleTypes.CURSOR);
                calstatement.setString(1, sKhoa);
                calstatement.setString(2, sUser);
                calstatement.setString(3, sGrade);
                calstatement.setString(4, sNgaybc);
                calstatement.setArray(5, oracle_arrayPoscd);
                calstatement.setString(6, sNhaDT);
                //Thuc hien execute lay du lieu
                calstatement.execute();
                //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
                int pn_err_cd = calstatement.getInt(7);
                //thu hien lay mo ta loi
                String strEdd_txt = calstatement.getString(8);
                //Lay cursor ra resultset
                reset = (ResultSet) calstatement.getObject(9);
                while (reset.next()) {

                    QT_DULIEU_NT value = QT_DULIEU_NT.newInstance();
                    value.setKHOA(reset.getString(2));
//                    value.setTHUTU(reset.getInt(3));
//                    value.setTT_HIENTHI(reset.getString(3));
                    value.setMA(reset.getString(4));
                    value.setTEN(reset.getString(5));
//                    value.setNGAYBC(reset.getDate(6));
//                    value.setNAMBC(reset.getInt(7));
                    value.setMAPGD(reset.getString(9));
                    value.setMACN(reset.getString(11));
//                    value.setD1(reset.getString(14));
//                    value.setD1(reset.getString(15));
                    value.setD1(reset.getString(16));
                    value.setD2(reset.getString(17));
                    value.setD3(reset.getString(18));
                    value.setD4(reset.getString(19));
                    value.setD5(reset.getString(20));
                    value.setD6(reset.getString(21));
                    value.setD7(reset.getString(22));
                    value.setD8(reset.getString(23));
                    value.setD9(reset.getString(24));
                    value.setD10(reset.getString(25));
                    value.setD11(reset.getString(26));
                    value.setD12(reset.getString(27));
                    value.setD13(reset.getString(28));
                    value.setD14(reset.getString(29));
                    value.setD15(reset.getString(30));
                    value.setD16(reset.getString(31));
                    value.setD20(reset.getString(1));
//                    value.setFONTFORMAT(reset.getString(46));

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

    public List<QT_DULIEU_NT> getDataPhiUT_PGD(Connection conn, String sUser, String sGrade, String sNhadt) {
        List<QT_DULIEU_NT> lstBcqt_NT = new ArrayList<QT_DULIEU_NT>();
        try {
//            DaoConnect daoconnect = new DaoConnect();
//            Connection conn = null;
//            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call VBSP_IMS_NHAPTAYCN.SP_GET_DATA_PHIUT_PGD(?,?,?,?,?,?)}";
            ResultSet reset = null;

            try {
                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                calstatement.registerOutParameter(4, oracle.jdbc.OracleTypes.NUMBER);
                calstatement.registerOutParameter(5, oracle.jdbc.OracleTypes.VARCHAR);
                calstatement.registerOutParameter(6, oracle.jdbc.OracleTypes.CURSOR);
                calstatement.setString(1, sUser);
                calstatement.setString(2, sGrade);
                calstatement.setString(3, sNhadt);
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
                    value.setKHOA(reset.getString(2));
//                    value.setTHUTU(reset.getInt(3));
//                    value.setTT_HIENTHI(reset.getString(3));
                    value.setMA(reset.getString(4));
                    value.setTEN(reset.getString(5));
//                    value.setNGAYBC(reset.getDate(6));
//                    value.setNAMBC(reset.getInt(7));
                    value.setMAPGD(reset.getString(9));
                    value.setMACN(reset.getString(11));
//                    value.setD1(reset.getString(14));
//                    value.setD1(reset.getString(15));
                    value.setD1(reset.getString(16));
                    value.setD2(reset.getString(17));
                    value.setD3(reset.getString(18));
                    value.setD4(reset.getString(19));
                    value.setD5(reset.getString(20));
                    value.setD6(reset.getString(21));
                    value.setD7(reset.getString(22));
                    value.setD8(reset.getString(23));
                    value.setD9(reset.getString(24));
                    value.setD10(reset.getString(25));
                    value.setD11(reset.getString(26));
                    value.setD12(reset.getString(27));
                    value.setD13(reset.getString(28));
                    value.setD14(reset.getString(29));
                    value.setD15(reset.getString(30));
                    value.setD16(reset.getString(31));
                    value.setD20(reset.getString(1));
//                    value.setFONTFORMAT(reset.getString(46));

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

    public List<QT_DULIEU_NT> getDataPhiUT_PHANBO(Connection conn, String sKhoa, String sNgaybc, String sUser,
            String sGrade, List<String> lstArrPoscd, String sNhaDT) {
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
            String strStoreproce = "{call VBSP_IMS_NHAPTAYCN.SP_GET_DATA_PHIUT_PHANBO(?,?,?,?,?,?,?,?,?)}";
            ResultSet reset = null;

            try {
                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                calstatement.registerOutParameter(7, oracle.jdbc.OracleTypes.NUMBER);
                calstatement.registerOutParameter(8, oracle.jdbc.OracleTypes.VARCHAR);
                calstatement.registerOutParameter(9, oracle.jdbc.OracleTypes.CURSOR);
                calstatement.setString(1, sKhoa);
                calstatement.setString(2, sUser);
                calstatement.setString(3, sGrade);
                calstatement.setString(4, sNgaybc);
                calstatement.setArray(5, oracle_arrayPoscd);
                calstatement.setString(6, sNhaDT);
                //Thuc hien execute lay du lieu
                calstatement.execute();
                //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
                int pn_err_cd = calstatement.getInt(7);
                //thu hien lay mo ta loi
                String strEdd_txt = calstatement.getString(8);
                //Lay cursor ra resultset
                reset = (ResultSet) calstatement.getObject(9);
                while (reset.next()) {

                    QT_DULIEU_NT value = QT_DULIEU_NT.newInstance();
                    value.setKHOA(reset.getString(2));
//                    value.setTHUTU(reset.getInt(3));
//                    value.setTT_HIENTHI(reset.getString(3));
                    value.setMA(reset.getString(4));
                    value.setTEN(reset.getString(5));
//                    value.setNGAYBC(reset.getDate(6));
//                    value.setNAMBC(reset.getInt(7));
                    value.setMAPGD(reset.getString(9));
                    value.setMACN(reset.getString(11));
//                    value.setD1(reset.getString(14));
//                    value.setD1(reset.getString(15));
                    value.setD1(reset.getString(16));
                    value.setD2(reset.getString(17));
                    value.setD3(reset.getString(18));
                    value.setD4(reset.getString(19));
                    value.setD5(reset.getString(20));
                    value.setD6(reset.getString(21));
                    value.setD7(reset.getString(22));
                    value.setD8(reset.getString(23));
                    value.setD9(reset.getString(24));
                    value.setD10(reset.getString(25));
                    value.setD11(reset.getString(26));
                    value.setD12(reset.getString(27));
                    value.setD13(reset.getString(28));
                    value.setD14(reset.getString(29));
                    value.setD15(reset.getString(30));
                    value.setD16(reset.getString(31));
                    value.setD20(reset.getString(1));
//                    value.setFONTFORMAT(reset.getString(46));

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

    public List<QT_DULIEU_NT> getDataPhiUT_DETAIL(Connection conn, String sKhoa, String sNgaybc, String sUser,
            String sGrade, List<String> lstArrPoscd, String sNhaDT, List<String> lstArrSp, List<String> lstArrPb) {
        List<QT_DULIEU_NT> lstBcqt_NT = new ArrayList<QT_DULIEU_NT>();
        try {
            ArrayDescriptor des = ArrayDescriptor.createDescriptor("POS_CD", conn);

            ArrayDescriptor desSp = ArrayDescriptor.createDescriptor("POS_CD", conn);
            ArrayDescriptor desPb = ArrayDescriptor.createDescriptor("POS_CD", conn);
            String[] arrayPoscd = lstArrPoscd.toArray(new String[0]);
            ARRAY oracle_arrayPoscd = new ARRAY(des, conn, arrayPoscd);

            String[] arraySP = lstArrSp.toArray(new String[0]);
            String[] arrayPB = lstArrPb.toArray(new String[0]);

            ARRAY oracle_arraySP = new ARRAY(desSp, conn, arraySP);
            ARRAY oracle_arrayPb = new ARRAY(desPb, conn, arrayPB);
//            DaoConnect daoconnect = new DaoConnect();
//            Connection conn = null;
//            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call VBSP_IMS_NHAPTAYCN.SP_GET_DATA_PHIUT_DETAIL(?,?,?,?,?,?,?,?,?,?,?)}";
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
                calstatement.setString(6, sNhaDT);
                calstatement.setArray(7, oracle_arraySP);
                calstatement.setArray(8, oracle_arrayPb);
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
                    value.setKHOA(reset.getString(2));
//                    value.setTHUTU(reset.getInt(3));
//                    value.setTT_HIENTHI(reset.getString(3));
                    value.setMA(reset.getString(4));
                    value.setTEN(reset.getString(5));
//                    value.setNGAYBC(reset.getDate(6));
//                    value.setNAMBC(reset.getInt(7));
                    value.setMAPGD(reset.getString(9));
                    value.setMACN(reset.getString(11));
//                    value.setD1(reset.getString(14));
//                    value.setD1(reset.getString(15));
                    value.setD1(reset.getString(16));
                    value.setD2(reset.getString(17));
                    value.setD3(reset.getString(18));
                    value.setD4(reset.getString(19));
                    value.setD5(reset.getString(20));
                    value.setD6(reset.getString(21));
                    value.setD7(reset.getString(22));
                    value.setD8(reset.getString(23));
                    value.setD9(reset.getString(24));
                    value.setD10(reset.getString(25));
                    value.setD11(reset.getString(26));
                    value.setD12(reset.getString(27));
                    value.setD13(reset.getString(28));
                    value.setD14(reset.getString(29));
                    value.setD15(reset.getString(30));
                    value.setD16(reset.getString(31));
                    value.setD20(reset.getString(1));
//                    value.setFONTFORMAT(reset.getString(46));

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

    public boolean savePhanBoPhi(String khoa, String username, String mapgd, String ngaybc, List<QT_DULIEU_NT> lstData, String sGrade) throws SQLException {
        Connection connection = new DaoConnect().getConnect();
//        java.util.Dictionary map = (java.util.Dictionary) (connection.getTypeMap());
        Object array[] = lstData.toArray();
        ArrayDescriptor des = ArrayDescriptor
                .createDescriptor(QT_DULIEU_NT.ORACLE_TABLE_TYPE, connection);
        ARRAY array_to_pass = new ARRAY(des, connection, array);
        CallableStatement cs = null;
        try {
            cs = connection.prepareCall("{call VBSP_IMS_NHAPTAYCN.SP_SAVE_DATA_PHIUT(?, ?, ?, ?, ?, ?)}");
            cs.setString(1, khoa);
            cs.setString(2, username);
            cs.setString(3, mapgd);
            cs.setString(4, ngaybc);
            cs.setArray(5, array_to_pass);
            cs.setString(6, sGrade);
            cs.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Loi trong ham savePhanBoPhi " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " savePhanBoPhi -> " + e.getMessage());
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

    public List<String> getDataSendPhiut(String type, String khoa, String mapgd, String ngay_bc) throws SQLException {
        List<String> lstData = new ArrayList<>();
        DaoConnect daoconnect = new DaoConnect();
        Connection conn = null;
        conn = daoconnect.getConnect();
        CallableStatement calstatement = null;
        ResultSet reset = null;
//        try {
        //Khoi tao procedure cung voi tham so truyen vao la dau ?
        String strStoreproce = "{call VBSP_IMS_NHAPTAYCN.SP_GET_DATA_NHAPTAYCN_SYNC(?,?,?,?,?,?,?)}";
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

    public List<String> getDataSendSms(String type, String khoa, String mapgd, String ngay_bc, List<String> lstArrPoscd) throws SQLException {
        List<String> lstData = new ArrayList<>();
        DaoConnect daoconnect = new DaoConnect();
        Connection conn = null;
        conn = daoconnect.getConnect();
        CallableStatement calstatement = null;
        ResultSet reset = null;

        ArrayDescriptor des_ma = ArrayDescriptor.createDescriptor("INDICATOR", conn);
        String[] arrayPoscd = lstArrPoscd.toArray(new String[0]);
        ARRAY oracle_arrayPos = new ARRAY(des_ma, conn, arrayPoscd);

//        try {
        //Khoi tao procedure cung voi tham so truyen vao la dau ?
        String strStoreproce = "{call VBSP_IMS_NHAPTAYCN.SP_GET_DATA_NHAPTAYCN_SYNC_SMS(?,?,?,?,?,?,?,?)}";
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
            calstatement.setArray(8, oracle_arrayPos);
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

    public List<String> getDataSendNhaptayCN(String type, String khoa, String mapgd, String ngay_bc) throws SQLException {
        List<String> lstData = new ArrayList<>();
        DaoConnect daoconnect = new DaoConnect();
        Connection conn = null;
        conn = daoconnect.getConnect();
        CallableStatement calstatement = null;
        ResultSet reset = null;
//        try {
        //Khoi tao procedure cung voi tham so truyen vao la dau ?
        String strStoreproce = "{call VBSP_IMS_TDNN.SP_GET_DATA_TDNN_SYNC(?,?,?,?,?,?,?)}";
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

    public List<QT_DULIEU_NT> getDataNTMOI_01(Connection conn, String sKhoa, String sNgaybc, String sUser,
            String sGrade, List<String> lstArrPoscd, String sKySL) {
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
            String strStoreproce = "{call VBSP_IMS_NHAPTAYCN.SP_GET_DATA_NTMOI01(?,?,?,?,?,?,?,?,?)}";
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
                calstatement.setString(9, sKySL);
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
                CoreLogger.error(this.getClass().getName() + " getDataTDNN_01 -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham getDataTDNN_01 " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " getDataTDNN_01 -> " + e.getMessage());
        }
        return lstBcqt_NT;
    }

    public List<QT_DULIEU_NT> getDataCN23_01(Connection conn, String sKhoa, String sNgaybc, String sUser,
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
            String strStoreproce = "{call VBSP_IMS_NHAPTAYCN.SP_GET_DATA_CN23_01(?,?,?,?,?,?,?,?)}";
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
                CoreLogger.error(this.getClass().getName() + " getDataTDNN_01 -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham getDataTDNN_01 " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " getDataTDNN_01 -> " + e.getMessage());
        }
        return lstBcqt_NT;
    }

    public List<QT_DULIEU_NT> getDataCN23_EDIT(Connection conn, String sKhoa, String sNgaybc, String sUser,
            String sGrade, List<String> lstArrPoscd, String soku) {
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
            String strStoreproce = "{call VBSP_IMS_NHAPTAYCN.SP_GET_DATA_CN23_EDIT(?,?,?,?,?,?,?,?,?)}";
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
                calstatement.setString(9, soku);
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
                CoreLogger.error(this.getClass().getName() + " getDataTDNN_01 -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham getDataTDNN_01 " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " getDataTDNN_01 -> " + e.getMessage());
        }
        return lstBcqt_NT;
    }

    public boolean saveNtmoi01(String khoa, String username, String mapgd, String ngaybc, List<QT_DULIEU_NT> lstData,
            List<QT_DULIEU_NT> lstDataCombo) throws SQLException {
        Connection connection = new DaoConnect().getConnect();
//        java.util.Dictionary map = (java.util.Dictionary) (connection.getTypeMap());

        Object array[] = lstData.toArray();
        ArrayDescriptor des = ArrayDescriptor
                .createDescriptor(QT_DULIEU_NT.ORACLE_TABLE_TYPE, connection);
        ARRAY array_to_pass = new ARRAY(des, connection, array);

        Object arrayCB[] = lstDataCombo.toArray();
        ArrayDescriptor des1 = ArrayDescriptor
                .createDescriptor(QT_DULIEU_NT.ORACLE_TABLE_TYPE, connection);
        ARRAY array_to_pass1 = new ARRAY(des1, connection, arrayCB);

        CallableStatement cs = null;
        try {
            cs = connection.prepareCall("{call VBSP_IMS_NHAPTAYCN.SP_SAVE_NTMOI_01(?, ?, ?, ?, ?, ? )}");
            cs.setString(1, khoa);
            cs.setString(2, username);
            cs.setString(3, mapgd);
            cs.setString(4, ngaybc);
            cs.setArray(5, array_to_pass);
            cs.setArray(6, array_to_pass1);
            cs.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Loi trong ham saveNtmoi01 " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " saveNtmoi01 -> " + e.getMessage());
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

    public boolean saveCN23_1(String khoa, String username, String mapgd, String ngaybc, List<QT_DULIEU_NT> lstData) throws SQLException {
        Connection connection = new DaoConnect().getConnect();
//        java.util.Dictionary map = (java.util.Dictionary) (connection.getTypeMap());

        Object array[] = lstData.toArray();
        ArrayDescriptor des = ArrayDescriptor
                .createDescriptor(QT_DULIEU_NT.ORACLE_TABLE_TYPE, connection);
        ARRAY array_to_pass = new ARRAY(des, connection, array);

        CallableStatement cs = null;
        try {
            cs = connection.prepareCall("{call VBSP_IMS_NHAPTAYCN.SP_SAVE_CN23_01(?, ?, ?, ?, ?)}");
            cs.setString(1, khoa);
            cs.setString(2, username);
            cs.setString(3, mapgd);
            cs.setString(4, ngaybc);
            cs.setArray(5, array_to_pass);
            cs.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Loi trong ham saveNtmoi01 " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " saveNtmoi01 -> " + e.getMessage());
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

    public List<QT_DULIEU_NT> getDataHANOI_01(Connection conn, String sKhoa, String sNgaybc, String sUser,
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
            String strStoreproce = "{call VBSP_IMS_NHAPTAYCN.SP_GET_DATA_HANOI01(?,?,?,?,?,?,?,?)}";
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
                CoreLogger.error(this.getClass().getName() + " getDataTDNN_01 -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham getDataTDNN_01 " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " getDataTDNN_01 -> " + e.getMessage());
        }
        return lstBcqt_NT;
    }

    public boolean saveHaNoi01(String khoa, String username, String mapgd, String ngaybc, List<QT_DULIEU_NT> lstData) throws SQLException {
        Connection connection = new DaoConnect().getConnect();
//        java.util.Dictionary map = (java.util.Dictionary) (connection.getTypeMap());
        Object array[] = lstData.toArray();
        ArrayDescriptor des = ArrayDescriptor
                .createDescriptor(QT_DULIEU_NT.ORACLE_TABLE_TYPE, connection);
        ARRAY array_to_pass = new ARRAY(des, connection, array);
        CallableStatement cs = null;
        try {
            cs = connection.prepareCall("{call VBSP_IMS_NHAPTAYCN.SP_SAVE_HANOI_01(?, ?, ?, ?, ? )}");
            cs.setString(1, khoa);
            cs.setString(2, username);
            cs.setString(3, mapgd);
            cs.setString(4, ngaybc);
            cs.setArray(5, array_to_pass);
            cs.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Loi trong ham saveTdnn01 " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " saveTdnn01 -> " + e.getMessage());
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

    public List<QT_DULIEU_NT> getDataHANOI_02(Connection conn, String sKhoa, String sNgaybc, String sUser,
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
            String strStoreproce = "{call VBSP_IMS_NHAPTAYCN.SP_GET_DATA_HANOI02(?,?,?,?,?,?,?,?)}";
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
                CoreLogger.error(this.getClass().getName() + " getDataTDNN_01 -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham getDataTDNN_01 " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " getDataTDNN_01 -> " + e.getMessage());
        }
        return lstBcqt_NT;
    }

    public List<QT_DULIEU_NT> getDataHANOI_03(Connection conn, String sKhoa, String sNgaybc, String sUser,
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
            String strStoreproce = "{call VBSP_IMS_NHAPTAYCN.SP_GET_DATA_HANOI03(?,?,?,?,?,?,?,?)}";
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
                CoreLogger.error(this.getClass().getName() + " getDataTDNN_01 -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham getDataTDNN_01 " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " getDataTDNN_01 -> " + e.getMessage());
        }
        return lstBcqt_NT;
    }

    public boolean saveHaNoi02(String khoa, String username, String mapgd, String ngaybc, List<QT_DULIEU_NT> lstData) throws SQLException {
        Connection connection = new DaoConnect().getConnect();
//        java.util.Dictionary map = (java.util.Dictionary) (connection.getTypeMap());
        Object array[] = lstData.toArray();
        ArrayDescriptor des = ArrayDescriptor
                .createDescriptor(QT_DULIEU_NT.ORACLE_TABLE_TYPE, connection);
        ARRAY array_to_pass = new ARRAY(des, connection, array);
        CallableStatement cs = null;
        try {
            cs = connection.prepareCall("{call VBSP_IMS_NHAPTAYCN.SP_SAVE_HANOI_02(?, ?, ?, ?, ? )}");
            cs.setString(1, khoa);
            cs.setString(2, username);
            cs.setString(3, mapgd);
            cs.setString(4, ngaybc);
            cs.setArray(5, array_to_pass);
            cs.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Loi trong ham saveTdnn01 " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " saveTdnn01 -> " + e.getMessage());
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

    public List<QT_DULIEU_NT> getDataNhaptay_02(Connection conn, String sKhoa, String sNgaybc, String sUser,
            String sGrade, List<String> lstArrPoscd, String sDvut) {
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
            String strStoreproce = "{call VBSP_IMS_NHAPTAYCN.SP_GET_DATA_NHAPTAY02(?,?,?,?,?,?,?,?,?)}";
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
                calstatement.setString(9, sDvut);
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
                CoreLogger.error(this.getClass().getName() + " getDataTDNN_01 -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham getDataTDNN_01 " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " getDataTDNN_01 -> " + e.getMessage());
        }
        return lstBcqt_NT;
    }

    public boolean saveNhaptay02(String khoa, String username, String mapgd, String ngaybc, List<QT_DULIEU_NT> lstData, String sDvut) throws SQLException {
        Connection connection = new DaoConnect().getConnect();
//        java.util.Dictionary map = (java.util.Dictionary) (connection.getTypeMap());
        Object array[] = lstData.toArray();
        ArrayDescriptor des = ArrayDescriptor
                .createDescriptor(QT_DULIEU_NT.ORACLE_TABLE_TYPE, connection);
        ARRAY array_to_pass = new ARRAY(des, connection, array);
        CallableStatement cs = null;
        try {
            cs = connection.prepareCall("{call VBSP_IMS_NHAPTAYCN.SP_SAVE_NHAPTAY02(?, ?, ?, ?, ? ,?)}");
            cs.setString(1, khoa);
            cs.setString(2, username);
            cs.setString(3, mapgd);
            cs.setString(4, ngaybc);
            cs.setArray(5, array_to_pass);
            cs.setString(6, sDvut);
            cs.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Loi trong ham saveNhaptay02 " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " saveNhaptay02 -> " + e.getMessage());
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

    public List<QT_DULIEU_NT> getStatusSendCnNhaptay(String Khoa, List<String> lstArrPoscd, String ngaybc, String capbc) {
        List<QT_DULIEU_NT> lstStatusSendcn = new ArrayList<QT_DULIEU_NT>();
        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            ArrayDescriptor des = ArrayDescriptor.createDescriptor("POS_CD", conn);
            String[] arrayPoscd = lstArrPoscd.toArray(new String[0]);
            ARRAY oracle_arrayPoscd = new ARRAY(des, conn, arrayPoscd);
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call VBSP_IMS_NHAPTAYCN.SP_GET_STATUS_SEND_CN(?,?,?,?,?)}";
            ResultSet reset = null;
            try {
                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                calstatement.registerOutParameter(5, oracle.jdbc.OracleTypes.CURSOR);
                calstatement.setString(1, Khoa);
                calstatement.setArray(2, oracle_arrayPoscd);
                calstatement.setString(3, ngaybc);
                calstatement.setString(4, capbc);
                //Thuc hien execute lay du lieu
                calstatement.execute();
                //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
                //Lay cursor ra resultset
                reset = (ResultSet) calstatement.getObject(5);
                //<editor-fold defaultstate="collapsed" desc="Tieu de cho cot">

                ResultSetMetaData resetMeta = reset.getMetaData();
                if (resetMeta.getColumnCount() <= 29 && Khoa.equals("ALL")) {
                    QT_DULIEU_NT value = QT_DULIEU_NT.newInstance();
                    value.setD1(resetMeta.getColumnName(1));
                    value.setD2(resetMeta.getColumnName(2));
                    value.setD3(resetMeta.getColumnName(3));
                    value.setD4(resetMeta.getColumnName(4));
                    value.setD5(resetMeta.getColumnName(5));
                    value.setD6(resetMeta.getColumnName(6));
                    value.setD7(resetMeta.getColumnName(7));
                    value.setD8(resetMeta.getColumnName(8));
                    value.setD9(resetMeta.getColumnName(9));
                    value.setD10(resetMeta.getColumnName(10));
                    value.setD11(resetMeta.getColumnName(11));
                    value.setD12(resetMeta.getColumnName(12));
                    value.setD13(resetMeta.getColumnName(13));
                    value.setD14(resetMeta.getColumnName(14));
                    value.setD15(resetMeta.getColumnName(15));
                    value.setD16(resetMeta.getColumnName(16));
                    value.setD17(resetMeta.getColumnName(17));
                    value.setD18(resetMeta.getColumnName(18));
                    value.setD19(resetMeta.getColumnName(19));
                    value.setD20(resetMeta.getColumnName(20));
                    value.setD21(resetMeta.getColumnName(21));
                    value.setD22(resetMeta.getColumnName(22));
                    value.setD23(resetMeta.getColumnName(23));
                    value.setD24(resetMeta.getColumnName(24));
                    value.setD25(resetMeta.getColumnName(25));
                    value.setD26(resetMeta.getColumnName(26));
//                    value.setD27(resetMeta.getColumnName(27));
//                    value.setD28(resetMeta.getColumnName(28));
//                    value.setD29(resetMeta.getColumnName(29));
                    lstStatusSendcn.add(value);
                }
//</editor-fold>
                while (reset.next()) {
                    QT_DULIEU_NT value = QT_DULIEU_NT.newInstance();
                    if (Khoa.equals("ALL")) {
                        value.setD1(reset.getString(1));
                        value.setD2(reset.getString(2));
                        value.setD3(reset.getString(3));
                        value.setD4(reset.getString(4).equals("1") ? "X" : "");
                        value.setD5(reset.getString(5).equals("1") ? "X" : "");
                        value.setD6(reset.getString(6).equals("1") ? "X" : "");
                        value.setD7(reset.getString(7).equals("1") ? "X" : "");
                        value.setD8(reset.getString(8).equals("1") ? "X" : "");
                        value.setD9(reset.getString(9).equals("1") ? "X" : "");
                        value.setD10(reset.getString(10).equals("1") ? "X" : "");
                        value.setD11(reset.getString(11).equals("1") ? "X" : "");
                        value.setD12(reset.getString(12).equals("1") ? "X" : "");
                        value.setD13(reset.getString(13).equals("1") ? "X" : "");
                        value.setD14(reset.getString(14).equals("1") ? "X" : "");
                        value.setD15(reset.getString(15).equals("1") ? "X" : "");
                        value.setD16(reset.getString(16).equals("1") ? "X" : "");
                        value.setD17(reset.getString(17).equals("1") ? "X" : "");
                        value.setD18(reset.getString(18).equals("1") ? "X" : "");
                        value.setD19(reset.getString(19).equals("1") ? "X" : "");
                        value.setD20(reset.getString(20).equals("1") ? "X" : "");
                        value.setD21(reset.getString(21).equals("1") ? "X" : "");
                        value.setD22(reset.getString(22).equals("1") ? "X" : "");
                        value.setD23(reset.getString(23).equals("1") ? "X" : "");
                        value.setD24(reset.getString(24).equals("1") ? "X" : "");
                        value.setD25(reset.getString(25).equals("1") ? "X" : "");
                        value.setD26(reset.getString(26).equals("1") ? "X" : "");
//                        value.setD27(reset.getString(27).equals("1") ? "X" : "");
//                        value.setD28(reset.getString(28).equals("1") ? "X" : "");
//                        value.setD29(reset.getString(29).equals("1") ? "X" : "");

                    } else {
                        value.setD1(reset.getString(1));
                        value.setD2(reset.getString(2));
                        value.setD3(reset.getString(3));
                        value.setD4(reset.getString(4));
                        value.setD5(reset.getString(5));
                        value.setD6(reset.getString(6));
                        value.setD7(reset.getString(7));
                        value.setD8(reset.getString(8));
                        value.setD9(reset.getString(9));
                        value.setD10(reset.getString(10));
                        value.setD11(reset.getString(11));
                    }
                    lstStatusSendcn.add(value);
                }

            } catch (SQLException e) {
                System.err.print(e.getMessage());
                CoreLogger.error(this.getClass().getName() + " getDataPL01TL -> " + e.getMessage());
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
        } catch (Exception e) {
            System.err.println("Loi trong ham getAllBcqt " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " getDataPL01TL -> " + e.getMessage());
        }
        return lstStatusSendcn;
    }

    public List<QT_DULIEU_NT> getStatusSendCn(String Khoa, List<String> lstArrPoscd, String ngaybc, String sCapKT) {
        List<QT_DULIEU_NT> lstStatusSendcn = new ArrayList<QT_DULIEU_NT>();
        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            ArrayDescriptor des = ArrayDescriptor.createDescriptor("POS_CD", conn);
            String[] arrayPoscd = lstArrPoscd.toArray(new String[0]);
            ARRAY oracle_arrayPoscd = new ARRAY(des, conn, arrayPoscd);
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call VBSP_IMS_TDNN.SP_GET_STATUS_SEND_CN(?,?,?,?,?)}";
            ResultSet reset = null;
            try {
                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                calstatement.registerOutParameter(5, oracle.jdbc.OracleTypes.CURSOR);
                calstatement.setString(1, Khoa);
                calstatement.setArray(2, oracle_arrayPoscd);
                calstatement.setString(3, ngaybc);
                calstatement.setString(4, sCapKT);
                //Thuc hien execute lay du lieu
                calstatement.execute();
                //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
                //Lay cursor ra resultset
                reset = (ResultSet) calstatement.getObject(5);
                //<editor-fold defaultstate="collapsed" desc="Tieu de cho cot">

                ResultSetMetaData resetMeta = reset.getMetaData();
                if (resetMeta.getColumnCount() <= 29 && Khoa.equals("ALL")) {
                    QT_DULIEU_NT value = QT_DULIEU_NT.newInstance();
                    value.setD1(resetMeta.getColumnName(1));
                    value.setD2(resetMeta.getColumnName(2));
                    value.setD3(resetMeta.getColumnName(3));
                    value.setD4(resetMeta.getColumnName(4));
                    value.setD5(resetMeta.getColumnName(5));
                    value.setD6(resetMeta.getColumnName(6));
                    value.setD7(resetMeta.getColumnName(7));
                    value.setD8(resetMeta.getColumnName(8));
                    value.setD9(resetMeta.getColumnName(9));
                    value.setD10(resetMeta.getColumnName(10));
                    value.setD11(resetMeta.getColumnName(11));
                    value.setD12(resetMeta.getColumnName(12));
                    value.setD13(resetMeta.getColumnName(13));
                    value.setD14(resetMeta.getColumnName(14));
                    value.setD15(resetMeta.getColumnName(15));
                    value.setD16(resetMeta.getColumnName(16));
                    value.setD17(resetMeta.getColumnName(17));
                    value.setD18(resetMeta.getColumnName(18));
                    value.setD19(resetMeta.getColumnName(19));
                    value.setD20(resetMeta.getColumnName(20));
                    value.setD21(resetMeta.getColumnName(21));
                    value.setD22(resetMeta.getColumnName(22));
                    value.setD23(resetMeta.getColumnName(23));
                    value.setD24(resetMeta.getColumnName(24));
                    value.setD25(resetMeta.getColumnName(25));
                    value.setD26(resetMeta.getColumnName(26));
//                    value.setD27(resetMeta.getColumnName(27));
//                    value.setD28(resetMeta.getColumnName(28));
//                    value.setD29(resetMeta.getColumnName(29));
                    lstStatusSendcn.add(value);
                }
//</editor-fold>
                while (reset.next()) {
                    QT_DULIEU_NT value = QT_DULIEU_NT.newInstance();
                    if (Khoa.equals("ALL")) {
                        value.setD1(reset.getString(1));
                        value.setD2(reset.getString(2));
                        value.setD3(reset.getString(3));
                        value.setD4(reset.getString(4).equals("1") ? "X" : "");
                        value.setD5(reset.getString(5).equals("1") ? "X" : "");
                        value.setD6(reset.getString(6).equals("1") ? "X" : "");
                        value.setD7(reset.getString(7).equals("1") ? "X" : "");
                        value.setD8(reset.getString(8).equals("1") ? "X" : "");
                        value.setD9(reset.getString(9).equals("1") ? "X" : "");
                        value.setD10(reset.getString(10).equals("1") ? "X" : "");
                        value.setD11(reset.getString(11).equals("1") ? "X" : "");
                        value.setD12(reset.getString(12).equals("1") ? "X" : "");
                        value.setD13(reset.getString(13).equals("1") ? "X" : "");
                        value.setD14(reset.getString(14).equals("1") ? "X" : "");
                        value.setD15(reset.getString(15).equals("1") ? "X" : "");
                        value.setD16(reset.getString(16).equals("1") ? "X" : "");
                        value.setD17(reset.getString(17).equals("1") ? "X" : "");
                        value.setD18(reset.getString(18).equals("1") ? "X" : "");
                        value.setD19(reset.getString(19).equals("1") ? "X" : "");
                        value.setD20(reset.getString(20).equals("1") ? "X" : "");
                        value.setD21(reset.getString(21).equals("1") ? "X" : "");
                        value.setD22(reset.getString(22).equals("1") ? "X" : "");
                        value.setD23(reset.getString(23).equals("1") ? "X" : "");
                        value.setD24(reset.getString(24).equals("1") ? "X" : "");
                        value.setD25(reset.getString(25).equals("1") ? "X" : "");
                        value.setD26(reset.getString(26).equals("1") ? "X" : "");
//                        value.setD27(reset.getString(27).equals("1") ? "X" : "");
//                        value.setD28(reset.getString(28).equals("1") ? "X" : "");
//                        value.setD29(reset.getString(29).equals("1") ? "X" : "");

                    } else {
                        value.setD1(reset.getString(1));
                        value.setD2(reset.getString(2));
                        value.setD3(reset.getString(3));
                        value.setD4(reset.getString(4));
                        value.setD5(reset.getString(5));
                        value.setD6(reset.getString(6));
                        value.setD7(reset.getString(7));
                        value.setD8(reset.getString(8));
                        value.setD9(reset.getString(9));
                        value.setD10(reset.getString(10));
                        value.setD11(reset.getString(11));
                    }
                    lstStatusSendcn.add(value);
                }

            } catch (SQLException e) {
                System.err.print(e.getMessage());
                CoreLogger.error(this.getClass().getName() + " getDataPL01TL -> " + e.getMessage());
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
        } catch (Exception e) {
            System.err.println("Loi trong ham getAllBcqt " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " getDataPL01TL -> " + e.getMessage());
        }
        return lstStatusSendcn;
    }

    public List<QT_DULIEU_NT> getData_HSSV_HTLS(Connection conn, String Khoa, String ngaybc, List<String> lstMapgd, String grade, String UserName) {
        List<QT_DULIEU_NT> lstBcqtPl01 = new ArrayList<QT_DULIEU_NT>();
        try {
//            DaoConnect daoconnect = new DaoConnect();
//            Connection conn = null;
//            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call VBSP_IMS_NHAPTAYCN.SP_LOAD_DATA_HSSV_HTLS(?,?,?,?,?,?,?,?)}";
            ResultSet reset = null;
            ArrayDescriptor des = ArrayDescriptor.createDescriptor("POS_CD", conn);
            String[] arrayPoscd = lstMapgd.toArray(new String[0]);
            ARRAY oracle_arrayPoscd = new ARRAY(des, conn, arrayPoscd);
            try {
                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                calstatement.registerOutParameter(6, oracle.jdbc.OracleTypes.NUMBER);
                calstatement.registerOutParameter(7, oracle.jdbc.OracleTypes.VARCHAR);
                calstatement.registerOutParameter(8, oracle.jdbc.OracleTypes.CURSOR);
                calstatement.setString(1, Khoa);
                calstatement.setString(2, ngaybc);
                calstatement.setArray(3, oracle_arrayPoscd);
                calstatement.setString(4, grade);
                calstatement.setString(5, UserName);
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
                    String Ma = reset.getString(2);
                    value.setKHOA(reset.getString(1));
                    value.setMA(Ma);
                    value.setTT_HIENTHI(Ma.substring(Ma.length() - 1));
                    value.setTEN(reset.getString(3));
                    value.setNGAYBC(reset.getDate(4));
                    value.setNAMBC(reset.getInt(5));
                    value.setMAPGD(reset.getString(6));
                    value.setMACN(reset.getString(7));
                    value.setD1(reset.getString(8));
                    value.setD2(reset.getString(9));
                    value.setD3(reset.getString(10));
                    value.setD4(reset.getString(11));
                    value.setD5(reset.getString(12));
                    value.setD6(reset.getString(13));
                    value.setD7(reset.getString(14));
                    value.setD8(reset.getString(15));
                    value.setD9(reset.getString(16));
                    value.setD10(reset.getString(17));
                    value.setD11(reset.getString(18));
                    value.setD12(reset.getString(19));
                    value.setD13(reset.getString(20));
                    value.setD14(reset.getString(21));
                    value.setNHAPTAY(reset.getString(22));

                    lstBcqtPl01.add(value);
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
                CoreLogger.error(this.getClass().getName() + " getData_M07 -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham getData_M07 " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " getData_M07 -> " + e.getMessage());
        }
        return lstBcqtPl01;
    }

    public String checkApdungPhiUT(String sKhoa, String sNgaybc, String sUser,
            String sGrade, List<String> lstArrPoscd, String sNhaDT, List<String> lstArrSp) throws SQLException {
        DaoConnect daoconnect = new DaoConnect();
        Connection conn = null;
        conn = daoconnect.getConnect();
        String sReturn = "";
        try {
            ArrayDescriptor des_pos = ArrayDescriptor.createDescriptor("POS_CD", conn);
            ArrayDescriptor des_sp = ArrayDescriptor.createDescriptor("POS_CD", conn);

            String[] arrayPoscd = lstArrPoscd.toArray(new String[0]);
            String[] arraySanpham = lstArrSp.toArray(new String[0]);

            ARRAY oracle_arrayPos = new ARRAY(des_pos, conn, arrayPoscd);
            ARRAY oracle_arrayAp = new ARRAY(des_sp, conn, arraySanpham);

            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{?=call vbsp_ims_nhaptaycn.F_CHECK_APDUNG(?,?,?,?,?,?,?)}";
            ResultSet reset = null;

            calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            calstatement.registerOutParameter(1, oracle.jdbc.OracleTypes.VARCHAR);
            //Tham so thu nhat truyen vao la co lay theo pos hay main pos
            calstatement.setString(2, sKhoa);
            calstatement.setString(3, sUser);
            calstatement.setString(4, sGrade);
            calstatement.setString(5, sNgaybc);
            calstatement.setArray(6, oracle_arrayPos);
            calstatement.setString(7, sNhaDT);
            calstatement.setArray(8, oracle_arrayAp);

            calstatement.execute();
            //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
            sReturn = calstatement.getString(1);
            if (reset != null) {
                reset.close();
            }
            if (calstatement != null) {
                calstatement.close();
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham checkApdungPhiUT " + e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " checkApdungPhiUT -> " + e.getMessage());
        }
        return sReturn;
    }

    public boolean saveHssv_001(String khoa, String username, String mapgd, String ngaybc,
            List<QT_DULIEU_NT> lstData, List<QT_DULIEU_NT> lstDataAll) throws SQLException {
        Connection connection = new DaoConnect().getConnect();
//        java.util.Dictionary map = (java.util.Dictionary) (connection.getTypeMap());
        Object array[] = lstData.toArray();
        ArrayDescriptor des = ArrayDescriptor
                .createDescriptor(QT_DULIEU_NT.ORACLE_TABLE_TYPE, connection);
        ARRAY array_to_pass = new ARRAY(des, connection, array);

        Object arrayAll[] = lstDataAll.toArray();
        ArrayDescriptor desAll = ArrayDescriptor
                .createDescriptor(QT_DULIEU_NT.ORACLE_TABLE_TYPE, connection);
        ARRAY array_to_passAll = new ARRAY(desAll, connection, arrayAll);

        CallableStatement cs = null;
        try {
            cs = connection.prepareCall("{call VBSP_IMS_NHAPTAYCN.SP_SAVE_HSSV_01(?, ?, ?, ?, ? ,?)}");
            cs.setString(1, khoa);
            cs.setString(2, username);
            cs.setString(3, mapgd);
            cs.setString(4, ngaybc);
            cs.setArray(5, array_to_pass);
            cs.setArray(6, array_to_passAll);
            cs.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Loi trong ham saveHssv_001 " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " saveHssv_001 -> " + e.getMessage());
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

    public List<QT_DULIEU_NT> getDataUser_01(Connection conn, String sKhoa, String sNgaybc, String sUser,
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
            String strStoreproce = "{call VBSP_IMS_NHAPTAYCN.SP_GET_DATA_USER01(?,?,?,?,?,?,?,?)}";
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
                CoreLogger.error(this.getClass().getName() + " getDataUser_01 -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham getDataUser_01 " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " getDataUser_01 -> " + e.getMessage());
        }
        return lstBcqt_NT;
    }

    public boolean saveUser01(String khoa, String username, String mapgd, String ngaybc, List<QT_DULIEU_NT> lstData) throws SQLException {
        Connection connection = new DaoConnect().getConnect();
//        java.util.Dictionary map = (java.util.Dictionary) (connection.getTypeMap());
        Object array[] = lstData.toArray();
        ArrayDescriptor des = ArrayDescriptor
                .createDescriptor(QT_DULIEU_NT.ORACLE_TABLE_TYPE, connection);
        ARRAY array_to_pass = new ARRAY(des, connection, array);
        CallableStatement cs = null;
        try {
            cs = connection.prepareCall("{call VBSP_IMS_NHAPTAYCN.SP_SAVE_USER_01(?, ?, ?, ?, ? )}");
            cs.setString(1, khoa);
            cs.setString(2, username);
            cs.setString(3, mapgd);
            cs.setString(4, ngaybc);
            cs.setArray(5, array_to_pass);
            cs.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Loi trong ham saveUser01 " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " saveUser01 -> " + e.getMessage());
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

    public List<QT_DULIEU_NT> getDataTraiPhieu_01(Connection conn, String sKhoa, String sNgaybc, String sUser,
            String sGrade, List<String> lstArrPoscd, String sNhap) {
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
            String strStoreproce = "{call VBSP_IMS_NHAPTAYCN.SP_GET_DATA_TRAIPHIEU01(?,?,?,?,?,?,?,?,?)}";
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
                calstatement.setString(9, sNhap);
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
                CoreLogger.error(this.getClass().getName() + " getDataTraiPhieu_01 -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham getDataTraiPhieu_01 " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " getDataTraiPhieu_01 -> " + e.getMessage());
        }
        return lstBcqt_NT;
    }

    public boolean saveTraiPhieu01(String khoa, String username, String mapgd, String ngaybc, List<QT_DULIEU_NT> lstData, String sNhap) throws SQLException {
        Connection connection = new DaoConnect().getConnect();
//        java.util.Dictionary map = (java.util.Dictionary) (connection.getTypeMap());
        Object array[] = lstData.toArray();
        ArrayDescriptor des = ArrayDescriptor
                .createDescriptor(QT_DULIEU_NT.ORACLE_TABLE_TYPE, connection);
        ARRAY array_to_pass = new ARRAY(des, connection, array);
        CallableStatement cs = null;
        try {
            cs = connection.prepareCall("{call VBSP_IMS_NHAPTAYCN.SP_SAVE_TPLS_01(?, ?, ?, ?, ?,? )}");
            cs.setString(1, khoa);
            cs.setString(2, username);
            cs.setString(3, mapgd);
            cs.setString(4, ngaybc);
            cs.setArray(5, array_to_pass);
            cs.setString(6, sNhap);
            cs.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Loi trong ham saveTdnn01 " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " saveTdnn01 -> " + e.getMessage());
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

    public int checkgRP(String sType, String UserName) throws SQLException {
        int _retVal = 0;
        Connection conn = new DaoConnect().getConnect();
        CallableStatement calstatement = null;
        ResultSet reset = null;
        //Khoi tao procedure cung voi tham so truyen vao la dau ?
        String strStoreproce = "{ ? = call VBSP_IMS_NHAPTAYCN.F_CHECK_GRP(?,?) }";

        try {
            //Khoi tao goi Store
            calstatement = conn.prepareCall(strStoreproce,
                    ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            calstatement.registerOutParameter(1, oracle.jdbc.OracleTypes.NUMBER);
            calstatement.setString(2, sType);
            calstatement.setString(3, UserName);
            //Thuc hien execute lay du lieu
            calstatement.execute();
            //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
            _retVal = calstatement.getInt(1);

        } catch (SQLException e) {
            System.err.print(e.getMessage());
            CoreLogger.error(this.getClass().getName() + " checkUserNhaptaycn -> " + e.getMessage());
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

    public List<QT_DULIEU_NT> getDataDMBC(Connection conn, String sUser, String sGrade, String sNhadt) {
        List<QT_DULIEU_NT> lstBcqt_NT = new ArrayList<QT_DULIEU_NT>();
        try {
//            DaoConnect daoconnect = new DaoConnect();
//            Connection conn = null;
//            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call VBSP_IMS_NHAPTAYCN.SP_GET_DATA_BC_DETAI(?,?,?,?,?,?)}";
            ResultSet reset = null;

            try {
                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                calstatement.registerOutParameter(4, oracle.jdbc.OracleTypes.NUMBER);
                calstatement.registerOutParameter(5, oracle.jdbc.OracleTypes.VARCHAR);
                calstatement.registerOutParameter(6, oracle.jdbc.OracleTypes.CURSOR);
                calstatement.setString(1, sUser);
                calstatement.setString(2, sGrade);
                calstatement.setString(3, sNhadt);
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
                    value.setKHOA(reset.getString(2));
//                    value.setTHUTU(reset.getInt(3));
//                    value.setTT_HIENTHI(reset.getString(3));
                    value.setMA(reset.getString(4));
                    value.setTEN(reset.getString(5));
//                    value.setNGAYBC(reset.getDate(6));
//                    value.setNAMBC(reset.getInt(7));
                    value.setMAPGD(reset.getString(9));
                    value.setMACN(reset.getString(11));
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
                    value.setD20(reset.getString(1));
//                    value.setFONTFORMAT(reset.getString(46));

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

    public List<QT_DULIEU_NT> getDataNhaptay_03(Connection conn, String sKhoa, String sNgaybc, String sUser,
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
            String strStoreproce = "{call VBSP_IMS_NHAPTAYCN.SP_GET_DATA_TB02(?,?,?,?,?,?,?,?)}";
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
                CoreLogger.error(this.getClass().getName() + " getDataTDNN_01 -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham getDataTDNN_01 " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " getDataTDNN_01 -> " + e.getMessage());
        }
        return lstBcqt_NT;
    }

    public boolean saveNhaptay03(String khoa, String username, String mapgd, String ngaybc, List<QT_DULIEU_NT> lstData) throws SQLException {
        Connection connection = new DaoConnect().getConnect();
//        java.util.Dictionary map = (java.util.Dictionary) (connection.getTypeMap());
        Object array[] = lstData.toArray();
        ArrayDescriptor des = ArrayDescriptor
                .createDescriptor(QT_DULIEU_NT.ORACLE_TABLE_TYPE, connection);
        ARRAY array_to_pass = new ARRAY(des, connection, array);
        CallableStatement cs = null;
        try {
            cs = connection.prepareCall("{call VBSP_IMS_NHAPTAYCN.SP_SAVE_TB02(?, ?, ?, ?, ? )}");
            cs.setString(1, khoa);
            cs.setString(2, username);
            cs.setString(3, mapgd);
            cs.setString(4, ngaybc);
            cs.setArray(5, array_to_pass);
            cs.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Loi trong ham saveNhaptay02 " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " saveNhaptay02 -> " + e.getMessage());
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

    public List<DULIEU_NT> getDataBdp01(Connection conn, String sKhoa, String sNgaybc, String sUser,
            String sGrade, List<String> lstArrPoscd, String sDvut) {
        List<DULIEU_NT> lstBcqt_NT = new ArrayList<DULIEU_NT>();
        try {
            ArrayDescriptor des = ArrayDescriptor.createDescriptor("POS_CD", conn);
            String[] arrayPoscd = lstArrPoscd.toArray(new String[0]);
            ARRAY oracle_arrayPoscd = new ARRAY(des, conn, arrayPoscd);
//            DaoConnect daoconnect = new DaoConnect();
//            Connection conn = null;
//            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call VBSP_IMS_NHAPTAYCN.SP_GET_DATA_BDP01(?,?,?,?,?,?,?,?,?)}";
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
                calstatement.setString(9, sDvut);
                //Thuc hien execute lay du lieu
                calstatement.execute();
                //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
                int pn_err_cd = calstatement.getInt(6);
                //thu hien lay mo ta loi
                String strEdd_txt = calstatement.getString(7);
                //Lay cursor ra resultset
                reset = (ResultSet) calstatement.getObject(8);
                while (reset.next()) {

                    DULIEU_NT value = DULIEU_NT.newInstance();
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
                    value.setD33(reset.getString(49));
                    value.setD34(reset.getString(50));
                    value.setD35(reset.getString(51));
                    value.setD36(reset.getString(52));
                    value.setD37(reset.getString(53));
                    value.setD38(reset.getString(54));
                    value.setD39(reset.getString(55));

                    value.setD40(reset.getString(56));
                    value.setD41(reset.getString(57));
                    value.setD42(reset.getString(58));
                    value.setD43(reset.getString(59));
                    value.setD44(reset.getString(60));
                    value.setD45(reset.getString(61));
                    value.setD46(reset.getString(62));
                    value.setD47(reset.getString(63));
                    value.setD48(reset.getString(64));
                    value.setD49(reset.getString(65));
                    value.setD50(reset.getString(66));

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
                CoreLogger.error(this.getClass().getName() + " getDataTDNN_01 -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham getDataTDNN_01 " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " getDataTDNN_01 -> " + e.getMessage());
        }
        return lstBcqt_NT;
    }

    public boolean saveBdp01(String khoa, String username, String mapgd, String ngaybc, List<DULIEU_NT> lstData, String sDvut) throws SQLException {
        Connection connection = new DaoConnect().getConnect();
//        java.util.Dictionary map = (java.util.Dictionary) (connection.getTypeMap());
        Object array[] = lstData.toArray();
        ArrayDescriptor des = ArrayDescriptor
                .createDescriptor(DULIEU_NT.ORACLE_TABLE_TYPE, connection);
        ARRAY array_to_pass = new ARRAY(des, connection, array);
        CallableStatement cs = null;
        try {
            cs = connection.prepareCall("{call VBSP_IMS_NHAPTAYCN.SP_SAVE_BDP01(?, ?, ?, ?, ? ,?)}");
            cs.setString(1, khoa);
            cs.setString(2, username);
            cs.setString(3, mapgd);
            cs.setString(4, ngaybc);
            cs.setArray(5, array_to_pass);
            cs.setString(6, sDvut);
            cs.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Loi trong ham saveNhaptay02 " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " saveNhaptay02 -> " + e.getMessage());
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

    public List<QT_DULIEU_NT> getDataSms01(Connection conn, String sKhoa, String sNgaybc, String sUser,
            String sGrade, List<String> lstArrPoscd, String sDvut, String tenkh) {
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
            String strStoreproce = "{call VBSP_IMS_NHAPTAYCN.SP_GET_DATA_SMS01(?,?,?,?,?,?,?,?,?,?)}";
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
                calstatement.setString(9, sDvut);
                calstatement.setString(10, tenkh);
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
                CoreLogger.error(this.getClass().getName() + " getDataTDNN_01 -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham getDataTDNN_01 " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " getDataTDNN_01 -> " + e.getMessage());
        }
        return lstBcqt_NT;
    }

    public boolean saveSms02(String khoa, String username, String mapgd, String ngaybc, List<QT_DULIEU_NT> lstData, String sDvut, List<String> lstArrPoscd) throws SQLException {
        Connection connection = new DaoConnect().getConnect();
//        java.util.Dictionary map = (java.util.Dictionary) (connection.getTypeMap());
        Object array[] = lstData.toArray();
        ArrayDescriptor des = ArrayDescriptor
                .createDescriptor(QT_DULIEU_NT.ORACLE_TABLE_TYPE, connection);
        ARRAY array_to_pass = new ARRAY(des, connection, array);

        ArrayDescriptor des_ma = ArrayDescriptor.createDescriptor("INDICATOR", connection);
        String[] arrayPoscd = lstArrPoscd.toArray(new String[0]);
        ARRAY oracle_arrayPos = new ARRAY(des_ma, connection, arrayPoscd);

        CallableStatement cs = null;
        try {
            cs = connection.prepareCall("{call VBSP_IMS_NHAPTAYCN.SP_SAVE_Sms02(?, ?, ?, ?, ? ,?, ?)}");
            cs.setString(1, khoa);
            cs.setString(2, username);
            cs.setString(3, mapgd);
            cs.setString(4, ngaybc);
            cs.setArray(5, array_to_pass);
            cs.setString(6, sDvut);
            cs.setArray(7, oracle_arrayPos);
            cs.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Loi trong ham saveNhaptay02 " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " saveNhaptay02 -> " + e.getMessage());
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

    public boolean saveSms03(String khoa, String username, String mapgd, String ngaybc, List<QT_DULIEU_NT> lstData, String sDvut, List<String> lstArrPoscd) throws SQLException {
        Connection connection = new DaoConnect().getConnect();
//        java.util.Dictionary map = (java.util.Dictionary) (connection.getTypeMap());
        Object array[] = lstData.toArray();
        ArrayDescriptor des = ArrayDescriptor
                .createDescriptor(QT_DULIEU_NT.ORACLE_TABLE_TYPE, connection);
        ARRAY array_to_pass = new ARRAY(des, connection, array);

        ArrayDescriptor des_ma = ArrayDescriptor.createDescriptor("INDICATOR", connection);
        String[] arrayPoscd = lstArrPoscd.toArray(new String[0]);
        ARRAY oracle_arrayPos = new ARRAY(des_ma, connection, arrayPoscd);

        CallableStatement cs = null;
        try {
            cs = connection.prepareCall("{call VBSP_IMS_NHAPTAYCN.SP_SAVE_Sms03(?, ?, ?, ?, ? ,?, ?)}");
            cs.setString(1, khoa);
            cs.setString(2, username);
            cs.setString(3, mapgd);
            cs.setString(4, ngaybc);
            cs.setArray(5, array_to_pass);
            cs.setString(6, sDvut);
            cs.setArray(7, oracle_arrayPos);
            cs.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Loi trong ham saveNhaptay02 " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " saveNhaptay02 -> " + e.getMessage());
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

    public boolean saveSms01(String khoa, String username, String mapgd, String ngaybc, List<QT_DULIEU_NT> lstData, String sDvut) throws SQLException {
        Connection connection = new DaoConnect().getConnect();
//        java.util.Dictionary map = (java.util.Dictionary) (connection.getTypeMap());
        Object array[] = lstData.toArray();
        ArrayDescriptor des = ArrayDescriptor
                .createDescriptor(QT_DULIEU_NT.ORACLE_TABLE_TYPE, connection);
        ARRAY array_to_pass = new ARRAY(des, connection, array);

        CallableStatement cs = null;
        try {
            cs = connection.prepareCall("{call VBSP_IMS_NHAPTAYCN.SP_SAVE_Sms01(?, ?, ?, ?, ? ,?)}");
            cs.setString(1, khoa);
            cs.setString(2, username);
            cs.setString(3, mapgd);
            cs.setString(4, ngaybc);
            cs.setArray(5, array_to_pass);
            cs.setString(6, sDvut);
            cs.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Loi trong ham saveNhaptay02 " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " saveNhaptay02 -> " + e.getMessage());
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

    public boolean saveHaNoi03(String khoa, String username, String mapgd, String ngaybc, List<QT_DULIEU_NT> lstData, String sCapKT, List<String> lstArrPoscd) throws SQLException {
        Connection connection = new DaoConnect().getConnect();
//        java.util.Dictionary map = (java.util.Dictionary) (connection.getTypeMap());
        Object array[] = lstData.toArray();
        ArrayDescriptor des = ArrayDescriptor
                .createDescriptor(QT_DULIEU_NT.ORACLE_TABLE_TYPE, connection);
        ARRAY array_to_pass = new ARRAY(des, connection, array);

        ArrayDescriptor des_ma = ArrayDescriptor.createDescriptor("INDICATOR", connection);
        String[] arrayPoscd = lstArrPoscd.toArray(new String[0]);
        ARRAY oracle_arrayPos = new ARRAY(des_ma, connection, arrayPoscd);

        CallableStatement cs = null;
        try {
            cs = connection.prepareCall("{call VBSP_IMS_NHAPTAYCN.SP_SAVE_HANOI03(?, ?, ?, ?, ? ,?, ?)}");
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

    public List<QT_DULIEU_NT> getDataQLDB01(Connection conn, String sKhoa, String sNgaybc, String sUser,
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
            String strStoreproce = "{call VBSP_IMS_NHAPTAYCN.SP_GET_DATA_QLDB01(?,?,?,?,?,?,?,?)}";
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
                CoreLogger.error(this.getClass().getName() + " getDataQLDB_01 -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham getDataQLDB_01 " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " getDataQLDB_01 -> " + e.getMessage());
        }
        return lstBcqt_NT;
    }

    public boolean saveQLDB01(String khoa, String username, String mapgd, String ngaybc, List<QT_DULIEU_NT> lstData, String sCapKT, List<String> lstArrPoscd) throws SQLException {
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
            cs = connection.prepareCall("{call VBSP_IMS_NHAPTAYCN.SP_SAVE_QLDB_01(?, ?, ?, ?, ? ,?, ?)}");
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

    public List<ListValue> getCanBo(String username, String type) {
        List<ListValue> lstDMNgNhan = new ArrayList<ListValue>();
        try {

            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call VBSP_IMS_NHAPTAYCN.SP_GET_CANBO(?, ?, ?,?,?)}";
            ResultSet reset = null;
            try {
                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                calstatement.setString(1, username);
                calstatement.setString(2, type);

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
                CoreLogger.error(this.getClass().getName() + " getNgNhan_KCKNTN -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Doi chieu, Phan loai no -> Loi trong ham getNgNhan_KCKNTN " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " getNgNhan_KCKNTN -> " + e.getMessage());
        }
        return lstDMNgNhan;
    }

    public List<QT_DULIEU_NT> getDataCN25_01(Connection conn, String sKhoa, String sNgaybc, String sUser,
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
            String strStoreproce = "{call VBSP_IMS_NHAPTAYCN.SP_GET_DATA_CN25(?,?,?,?,?,?,?,?)}";
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
                    value.setD18(reset.getString(32));
                    value.setD19(reset.getString(33));
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
                CoreLogger.error(this.getClass().getName() + " getDataTDNN_01 -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham getDataTDNN_01 " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " getDataTDNN_01 -> " + e.getMessage());
        }
        return lstBcqt_NT;
    }

    public boolean saveCN25_01(String khoa, String username, String mapgd, String ngaybc, List<QT_DULIEU_NT> lstData) throws SQLException {
        Connection connection = new DaoConnect().getConnect();
//        java.util.Dictionary map = (java.util.Dictionary) (connection.getTypeMap());
        Object array[] = lstData.toArray();
        ArrayDescriptor des = ArrayDescriptor
                .createDescriptor(QT_DULIEU_NT.ORACLE_TABLE_TYPE, connection);
        ARRAY array_to_pass = new ARRAY(des, connection, array);
        CallableStatement cs = null;
        try {
            cs = connection.prepareCall("{call VBSP_IMS_NHAPTAYCN.SP_SAVE_CN25_01(?, ?, ?, ?, ? )}");
            cs.setString(1, khoa);
            cs.setString(2, username);
            cs.setString(3, mapgd);
            cs.setString(4, ngaybc);
            cs.setArray(5, array_to_pass);
            cs.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Loi trong ham saveTdnn01 " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " saveTdnn01 -> " + e.getMessage());
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

    public List<QT_DULIEU_NT> getDataDieuChuyenTo01(Connection conn, String sKhoa, String sNgaybc, String sUser,
            String sGrade, String FromTo, String ToTo) {
        List<QT_DULIEU_NT> lstBcqt_NT = new ArrayList<QT_DULIEU_NT>();
        try {
//            DaoConnect daoconnect = new DaoConnect();
//            Connection conn = null;
//            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call VBSP_IMS_NHAPTAYCN.SP_GET_DATA_DIEUCHUYENTO_01(?,?,?,?,?,?,?,?,?)}";
            ResultSet reset = null;

            try {
                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                calstatement.registerOutParameter(7, oracle.jdbc.OracleTypes.NUMBER);
                calstatement.registerOutParameter(8, oracle.jdbc.OracleTypes.VARCHAR);
                calstatement.registerOutParameter(9, oracle.jdbc.OracleTypes.CURSOR);
                calstatement.setString(1, sKhoa);
                calstatement.setString(2, sUser);
                calstatement.setString(3, sGrade);
                calstatement.setString(4, sNgaybc);
                calstatement.setString(5, FromTo);
                calstatement.setString(6, ToTo);
                //Thuc hien execute lay du lieu
                calstatement.execute();
                //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
                int pn_err_cd = calstatement.getInt(7);
                //thu hien lay mo ta loi
                String strEdd_txt = calstatement.getString(8);
                //Lay cursor ra resultset
                reset = (ResultSet) calstatement.getObject(9);
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
                CoreLogger.error(this.getClass().getName() + " getDataQLDB_01 -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham getDataQLDB_01 " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " getDataQLDB_01 -> " + e.getMessage());
        }
        return lstBcqt_NT;
    }

    public boolean saveDieuChuyenTo01(String khoa, String username, String mapgd, String ngaybc, List<QT_DULIEU_NT> lstData, List<String> lstArrPoscd, String gr_from, String gr_to) throws SQLException {
        Connection connection = new DaoConnect().getConnect();
//        java.util.Dictionary map = (java.util.Dictionary) (connection.getTypeMap());
        Object array[] = lstData.toArray();
        ArrayDescriptor des = ArrayDescriptor
                .createDescriptor(QT_DULIEU_NT.ORACLE_TABLE_TYPE, connection);
        ARRAY array_to_pass = new ARRAY(des, connection, array);

        ArrayDescriptor des_ma = ArrayDescriptor.createDescriptor("INDICATOR", connection);

        String[] arrayPoscd = lstArrPoscd.toArray(new String[0]);

        ARRAY oracle_arrayPos = new ARRAY(des_ma, connection, arrayPoscd);

        CallableStatement cs = null;
        try {
            cs = connection.prepareCall("{call VBSP_IMS_NHAPTAYCN.SP_SAVE_DIEUCHUYENTO_01(?, ?, ?, ?, ?, ?, ?, ?)}");
            cs.setString(1, khoa);
            cs.setString(2, username);
            cs.setString(3, mapgd);
            cs.setString(4, ngaybc);
            cs.setArray(5, array_to_pass);
            cs.setArray(6, oracle_arrayPos);
            cs.setString(7, gr_from);
            cs.setString(8, gr_to);
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

    public List<QT_DULIEU_NT> getDataKyQuy04(Connection conn, String sKhoa, String sNgaybc, String sUser,
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
            String strStoreproce = "{call VBSP_IMS_NHAPTAYCN.SP_GET_DATA_KYQUY04(?,?,?,?,?,?,?,?)}";
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
                CoreLogger.error(this.getClass().getName() + " getDataQLDB_01 -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham getDataQLDB_01 " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " getDataQLDB_01 -> " + e.getMessage());
        }
        return lstBcqt_NT;
    }

    public boolean saveKyquy04(String khoa, String username, String mapgd, String ngaybc, List<QT_DULIEU_NT> lstData, List<String> lstArrPoscd) throws SQLException {
        Connection connection = new DaoConnect().getConnect();
//        java.util.Dictionary map = (java.util.Dictionary) (connection.getTypeMap());
        Object array[] = lstData.toArray();
        ArrayDescriptor des = ArrayDescriptor
                .createDescriptor(QT_DULIEU_NT.ORACLE_TABLE_TYPE, connection);
        ARRAY array_to_pass = new ARRAY(des, connection, array);

        ArrayDescriptor des_ma = ArrayDescriptor.createDescriptor("INDICATOR", connection);

        String[] arrayPoscd = lstArrPoscd.toArray(new String[0]);

        ARRAY oracle_arrayPos = new ARRAY(des_ma, connection, arrayPoscd);

        CallableStatement cs = null;
        try {
            cs = connection.prepareCall("{call VBSP_IMS_NHAPTAYCN.SP_SAVE_KYQUY_04(?, ?, ?, ?, ? ,?)}");
            cs.setString(1, khoa);
            cs.setString(2, username);
            cs.setString(3, mapgd);
            cs.setString(4, ngaybc);
            cs.setArray(5, array_to_pass);
            cs.setArray(6, oracle_arrayPos);
            cs.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Loi trong ham saveKyquy04 " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " saveKyquy04 -> " + e.getMessage());
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

    public String checkData_Info(List<QT_DULIEU_NT> lstData, String skhoa, String sNgaybc, String sUser, String sCapbc, List<String> lstArrPoscd) throws SQLException {
        String _retVal = "";
        Connection conn = new DaoConnect().getConnect();
        CallableStatement calstatement = null;
        ResultSet reset = null;

        Object array[] = lstData.toArray();
        ArrayDescriptor des = ArrayDescriptor
                .createDescriptor(QT_DULIEU_NT.ORACLE_TABLE_TYPE, conn);
        ARRAY array_to_pass = new ARRAY(des, conn, array);

        ArrayDescriptor des_ma = ArrayDescriptor.createDescriptor("INDICATOR", conn);

        String[] arrayPoscd = lstArrPoscd.toArray(new String[0]);

        ARRAY oracle_arrayPos = new ARRAY(des_ma, conn, arrayPoscd);

        //Khoi tao procedure cung voi tham so truyen vao la dau ?
        String strStoreproce = "{ ? = call VBSP_IMS_NHAPTAYCN.F_CHECK_DATA_INFO(?,?,?,?,?,?) }";

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
            calstatement.setArray(7, oracle_arrayPos);
            //Thuc hien execute lay du lieu
            calstatement.execute();
            //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
            _retVal = calstatement.getString(1);

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
    
     public String checkData_Info_50(List<QT_DULIEU_NT_50> lstData, String skhoa, String sNgaybc, String sUser, String sCapbc, List<String> lstArrPoscd) throws SQLException {
        String _retVal = "";
        Connection conn = new DaoConnect().getConnect();
        CallableStatement calstatement = null;
        ResultSet reset = null;

        Object array[] = lstData.toArray();
        ArrayDescriptor des = ArrayDescriptor
                .createDescriptor(QT_DULIEU_NT_50.ORACLE_TABLE_TYPE, conn);
        ARRAY array_to_pass = new ARRAY(des, conn, array);

        ArrayDescriptor des_ma = ArrayDescriptor.createDescriptor("INDICATOR", conn);

        String[] arrayPoscd = lstArrPoscd.toArray(new String[0]);

        ARRAY oracle_arrayPos = new ARRAY(des_ma, conn, arrayPoscd);

        //Khoi tao procedure cung voi tham so truyen vao la dau ?
        String strStoreproce = "{ ? = call VBSP_IMS_NHAPTAYCN.F_CHECK_DATA_INFO_50(?,?,?,?,?,?) }";

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
            calstatement.setArray(7, oracle_arrayPos);
            //Thuc hien execute lay du lieu
            calstatement.execute();
            //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
            _retVal = calstatement.getString(1);

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

    public String checkData_MuasamTS(List<QT_DULIEU_NT> lstData, String skhoa, String sNgaybc, String sUser, String sCapbc, String nambc,
            String trangthaims, String dotms, String nghiepvums) throws SQLException {
        String _retVal = "";
        Connection conn = new DaoConnect().getConnect();
        CallableStatement calstatement = null;
        ResultSet reset = null;

        Object array[] = lstData.toArray();
        ArrayDescriptor des = ArrayDescriptor
                .createDescriptor(QT_DULIEU_NT.ORACLE_TABLE_TYPE, conn);
        ARRAY array_to_pass = new ARRAY(des, conn, array);

        //Khoi tao procedure cung voi tham so truyen vao la dau ?
        String strStoreproce = "{ ? = call VBSP_IMS_NHAPTAYCN.F_CHECK_DATA_MUASAMTS(?,?,?,?,?,?,?,?,?) }";

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

            calstatement.setString(7, nambc);
            calstatement.setString(8, trangthaims);
            calstatement.setString(9, dotms);
            calstatement.setString(10, nghiepvums);

            //Thuc hien execute lay du lieu
            calstatement.execute();
            //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
            _retVal = calstatement.getString(1);

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

    public List<QT_DULIEU_NT> getDataKyQuy05(Connection conn, String sKhoa, String sNgaybc, String sUser,
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
            String strStoreproce = "{call VBSP_IMS_NHAPTAYCN.SP_GET_DATA_KYQUY05(?,?,?,?,?,?,?,?)}";
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
                CoreLogger.error(this.getClass().getName() + " getDataQLDB_01 -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham getDataQLDB_01 " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " getDataQLDB_01 -> " + e.getMessage());
        }
        return lstBcqt_NT;
    }

    public boolean saveKyquy05(String khoa, String username, String mapgd, String ngaybc, List<QT_DULIEU_NT> lstData, List<String> lstArrPoscd) throws SQLException {
        Connection connection = new DaoConnect().getConnect();
//        java.util.Dictionary map = (java.util.Dictionary) (connection.getTypeMap());
        Object array[] = lstData.toArray();
        ArrayDescriptor des = ArrayDescriptor
                .createDescriptor(QT_DULIEU_NT.ORACLE_TABLE_TYPE, connection);
        ARRAY array_to_pass = new ARRAY(des, connection, array);

        ArrayDescriptor des_ma = ArrayDescriptor.createDescriptor("INDICATOR", connection);

        String[] arrayPoscd = lstArrPoscd.toArray(new String[0]);

        ARRAY oracle_arrayPos = new ARRAY(des_ma, connection, arrayPoscd);

        CallableStatement cs = null;
        try {
            cs = connection.prepareCall("{call VBSP_IMS_NHAPTAYCN.SP_SAVE_KYQUY_05(?, ?, ?, ?, ? ,?)}");
            cs.setString(1, khoa);
            cs.setString(2, username);
            cs.setString(3, mapgd);
            cs.setString(4, ngaybc);
            cs.setArray(5, array_to_pass);
            cs.setArray(6, oracle_arrayPos);
            cs.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Loi trong ham saveKyquy05 " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " saveKyquy05 -> " + e.getMessage());
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

    public List<QT_DULIEU_NT> getDataLoaitru01(Connection conn, String sKhoa, String sNgaybc, String sUser,
            String sGrade, List<String> lstArrPoscd, String type_loaitru, String txn_group) {
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
            String strStoreproce = "{call VBSP_IMS_NHAPTAYCN.SP_GET_DATA_LOAITRU01(?,?,?,?,?,?,?,?,?,?)}";
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
                calstatement.setString(9, type_loaitru);
                calstatement.setString(10, txn_group);
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
                CoreLogger.error(this.getClass().getName() + " getDataQLDB_01 -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham getDataQLDB_01 " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " getDataQLDB_01 -> " + e.getMessage());
        }
        return lstBcqt_NT;
    }

    public boolean saveLoaiTru01(String khoa, String username, String mapgd, String ngaybc, List<QT_DULIEU_NT> lstData, List<String> lstArrPoscd, String capbc) throws SQLException {
        Connection connection = new DaoConnect().getConnect();
//        java.util.Dictionary map = (java.util.Dictionary) (connection.getTypeMap());
        Object array[] = lstData.toArray();
        ArrayDescriptor des = ArrayDescriptor
                .createDescriptor(QT_DULIEU_NT.ORACLE_TABLE_TYPE, connection);
        ARRAY array_to_pass = new ARRAY(des, connection, array);

        ArrayDescriptor des_ma = ArrayDescriptor.createDescriptor("INDICATOR", connection);

        String[] arrayPoscd = lstArrPoscd.toArray(new String[0]);

        ARRAY oracle_arrayPos = new ARRAY(des_ma, connection, arrayPoscd);

        CallableStatement cs = null;
        try {
            cs = connection.prepareCall("{call VBSP_IMS_NHAPTAYCN.SP_SAVE_LOAITRU_01(?, ?, ?, ?, ? ,?, ?)}");
            cs.setString(1, khoa);
            cs.setString(2, username);
            cs.setString(3, mapgd);
            cs.setString(4, ngaybc);
            cs.setArray(5, array_to_pass);
            cs.setArray(6, oracle_arrayPos);
            cs.setString(7, capbc);
            cs.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Loi trong ham saveLoaiTru01 " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " saveLoaiTru01 -> " + e.getMessage());
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

    public List<QT_DULIEU_NT> getDataCovid_03(Connection conn, String sKhoa, String sNgaybc, String sUser,
            String sGrade, List<String> lstArrPoscd, String type_loaitru, String txn_group) {
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
            String strStoreproce = "{call VBSP_IMS_NHAPTAYCN.SP_GET_DATA_COVID_03(?,?,?,?,?,?,?,?,?,?)}";
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
                calstatement.setString(9, type_loaitru);
                calstatement.setString(10, txn_group);
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
                CoreLogger.error(this.getClass().getName() + " getDataQLDB_01 -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham getDataQLDB_01 " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " getDataQLDB_01 -> " + e.getMessage());
        }
        return lstBcqt_NT;
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
        String strStoreproce = "{?=call VBSP_IMS_NHAPTAYCN.F_GET_START_END_CELL(?)}";
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
        String strStoreproce = "{?=call VBSP_IMS_NHAPTAYCN.F_GET_POSCD(?)}";
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

    public boolean insert_PL02_FILE(String mabc, String poscd, String fileName, Date ngaybc, String username, List<ModelExcelFile> lstExcel, String masothue) throws Exception, SQLException {
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
            sInsert = "insert into dulieu_nt(KHOA,TT_HIENTHI, MAPGD, D1, D2, D3, D4, D5, D6, D7, D8, D9, D10, D11, D12, D15 , ngaybc, "
                    + " NGUOI_NHAP)\n"
                    + "values(?, ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            conn.setAutoCommit(false);
            insert = conn.prepareStatement(sInsert);
            Delete = "delete from dulieu_nt where MAPGD=? and khoa = ? and D15 = ? ";
            statementDelete = conn.prepareCall(Delete);
//                    statementDelete.setDate(1, new java.sql.Date(ngaybc.getTime()));
            statementDelete.setString(1, poscd);
            statementDelete.setString(2, mabc);
            statementDelete.setString(3, masothue);

            statementDelete.execute();
            for (int i = 0; i < lstExcel.size(); i++) {
                ModelExcelFile value = lstExcel.get(i);
//                        insert.setString(1, fileName);
//                        insert.setDate(2, new java.sql.Date(ngaybc.getTime()));
                insert.setString(1, mabc);
//                        insert.setString(4, poscd);
                insert.setString(2, value.getC1());
                insert.setString(3, poscd);
                insert.setString(4, value.getC2());
                insert.setString(5, value.getC3());
                insert.setString(6, value.getN1());
                insert.setString(7, value.getN2());
                insert.setString(8, value.getN3());

                insert.setString(9, value.getN4());
                insert.setString(10, value.getN5());
                insert.setString(11, value.getN6());
                insert.setString(12, value.getN7());
                insert.setString(13, value.getN8());
                insert.setString(14, value.getN9());
                insert.setString(15, value.getN10());
                insert.setString(16, masothue);

                insert.setDate(17, new java.sql.Date(ngaybc.getTime()));
                insert.setString(18, username);
                insert.execute();
            }
            Delete = "delete from dulieu_nt where D5 is null and khoa = ? and D15 = ?";
            statementDelete = conn.prepareCall(Delete);
//                    statementDelete.setDate(1, new java.sql.Date(ngaybc.getTime()));                    
            statementDelete.setString(1, mabc);
            statementDelete.setString(2, masothue);

            statementDelete.execute();

//                    Update = "update dulieu_cdcn_imp set mapgd = replace(to_char(to_number(replace(MAPGD,'.0','')),'000000'),' ',''), D1 = replace(D1,'.0',''), D3 = replace(D3,'.0',''), D5 = replace(D5,'.0',''), D7 = replace(D7,'.0',''), "
//                            + "D9 = replace(D9,'.0',''), D11 = replace(D11,'.0','') where ngaybc=? and D13=? and khoa=?";
//                    statementUpdate = conn.prepareCall(Update);
//                    statementUpdate.setDate(1, new java.sql.Date(ngaybc.getTime()));
//                    statementUpdate.setString(2, poscd);
//                    statementUpdate.setString(3, mabc);
//                    statementUpdate.execute();
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

    public boolean insert_COV_GAINGAN(String mabc, String poscd, String fileName, Date ngaybc, String username, List<ModelExcelFile> lstExcel, String masothue, String lanGN) throws Exception, SQLException {
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
            sInsert = "insert into dulieu_nt(KHOA,TT_HIENTHI, MAPGD, D1, D2, D3, D4, D5, D6, D7, D8, D9, D10, D11, D12, D15 , ngaybc, "
                    + " NGUOI_NHAP, D14, D18, D19)\n"
                    + "values(?, ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            conn.setAutoCommit(false);
            insert = conn.prepareStatement(sInsert);
            Delete = "delete from dulieu_nt where MAPGD=? and khoa = ? and D14 = ? and d15 = ?";
            statementDelete = conn.prepareCall(Delete);
//                    statementDelete.setDate(1, new java.sql.Date(ngaybc.getTime()));
            statementDelete.setString(1, poscd);
            statementDelete.setString(2, mabc);
            statementDelete.setString(3, lanGN);
            statementDelete.setString(4, masothue);

            statementDelete.execute();
            for (int i = 0; i < lstExcel.size(); i++) {
                ModelExcelFile value = lstExcel.get(i);
//                        insert.setString(1, fileName);
//                        insert.setDate(2, new java.sql.Date(ngaybc.getTime()));
                insert.setString(1, mabc);
//                        insert.setString(4, poscd);
                insert.setString(2, value.getC1());
                insert.setString(3, poscd);
                insert.setString(4, value.getC2());
                insert.setString(5, value.getC3());
                insert.setString(6, value.getN1());
                insert.setString(7, value.getN2());
                insert.setString(8, value.getN3());

                insert.setString(9, value.getN4());
                insert.setString(10, value.getN5());
                insert.setString(11, value.getN6());
                insert.setString(12, value.getN7());
                insert.setString(13, value.getN8());
                insert.setString(14, value.getN9());
                insert.setString(15, value.getN10());
                insert.setString(16, masothue);

                insert.setDate(17, new java.sql.Date(ngaybc.getTime()));
                insert.setString(18, username);

                insert.setString(19, lanGN);
                insert.setString(20, value.getN12());
                insert.setString(21, value.getN13());
                insert.execute();
            }
            Delete = "delete from dulieu_nt where (D2 is null or D4 is null)  and khoa = ?";
            statementDelete = conn.prepareCall(Delete);
//                    statementDelete.setDate(1, new java.sql.Date(ngaybc.getTime()));                    
            statementDelete.setString(1, mabc);

            statementDelete.execute();

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

    public boolean insert_HTLS_CN23(String mabc, String poscd, String fileName, Date ngaybc, String username, List<ModelExcelFile> lstExcel, String masothue, String lanGN) throws Exception, SQLException {
        boolean bSuccess = true;
        DaoConnect daoconnect = new DaoConnect();
        Connection conn = null;

        PreparedStatement insert = null;
        CallableStatement statementDelete = null;
        CallableStatement statementDelete2 = null;
        CallableStatement statementUpdate = null;
        String sInsert = "";
        String Delete = "";
        String Delete2 = "";
        try {
            conn = daoconnect.getConnect();
            sInsert = "insert into dulieu_nt(KHOA, D1, D2, D3, "
                    + " NGUOI_NHAP, NGAY_NHAP, ngaybc, mapgd, D4)\n"
                    + "values(?, ?,?,?,?,sysdate,trunc(?),?, ?)";
            conn.setAutoCommit(false);
            insert = conn.prepareStatement(sInsert);
//                    Delete = "delete from dulieu_nt where MAPGD=? and khoa = ? and D14 = ? and d15 = ?";
//                    statementDelete = conn.prepareCall(Delete);
//                    statementDelete.setDate(1, new java.sql.Date(ngaybc.getTime()));
//                    statementDelete.setString(1, poscd);
//                    statementDelete.setString(2, mabc);
//                    statementDelete.setString(3, lanGN);
//                    statementDelete.setString(4, masothue);
//
//                    statementDelete.execute();
            for (int i = 0; i < lstExcel.size(); i++) {
                ModelExcelFile value = lstExcel.get(i);
//                        insert.setString(1, fileName);
//                        insert.setDate(2, new java.sql.Date(ngaybc.getTime()));
                insert.setString(1, mabc);
//                        insert.setString(4, poscd);
                insert.setString(2, value.getC1());
//                        insert.setString(3, poscd);                        
                insert.setString(3, value.getC2());
                insert.setString(4, value.getC3());

//                        insert.setDate(6, new java.sql.Date(ngaybc.getTime()));
                insert.setString(5, username);
                insert.setDate(6, new java.sql.Date(ngaybc.getTime()));
                insert.setString(7, poscd);
                insert.setString(8, value.getN1());
                insert.execute();
            }
            Delete = "delete (select * from dulieu_nt where khoa = 'CN23_01' and mapgd = ?) A\n"
                    + "WHERE  a.rowid >  ANY (SELECT B.rowid FROM  (select * from dulieu_nt where khoa = 'CN23_01' and mapgd = ?) B WHERE A.D2 = B.D2)";
            statementDelete = conn.prepareCall(Delete);
            statementDelete.setString(1, poscd);
            statementDelete.setString(2, poscd);
            statementDelete.execute();

            Delete2 = "delete  dulieu_nt where khoa = 'CN23_01' and D2 is null";
            statementDelete2 = conn.prepareCall(Delete2);
            statementDelete2.execute();

            conn.commit();
            conn.setAutoCommit(true);

            bSuccess = true;

        } catch (SQLException e) {
            if (conn != null) {
                try {
                    System.err.print("Transaction is being rolled back");
                    conn.rollback();
                } catch (SQLException excep) {
                    CoreLogger.error(this.getClass().getCanonicalName() + " Exception-> insert_HTLS_CN23 " + e.getMessage());
                    System.err.println(" Exception-> saveDsHongheo " + e.getMessage());
                }
            }
            CoreLogger.error(this.getClass().getCanonicalName() + " Exception-> insert_HTLS_CN23 " + e.getMessage());
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

    public List<QT_DULIEU_NT> getDataAfterUpFile(Connection conn, String sKhoa, String sNgaybc, String poscd, String username, String grade, String langiaingan, String masothue) {
        List<QT_DULIEU_NT> lstBcqt_NT = new ArrayList<QT_DULIEU_NT>();
        try {
//            DaoConnect daoconnect = new DaoConnect();
//            Connection conn = null;
//            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call VBSP_IMS_NHAPTAYCN.SP_GET_DATA_AFTER_UPFILE(?,?,?,?,?,?,?,?,?, ?)}";
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
                calstatement.setString(9, langiaingan);
                calstatement.setString(10, masothue);
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
                    value.setD18(reset.getString(32));
                    value.setD19(reset.getString(33));
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
                CoreLogger.error(this.getClass().getName() + " getDataTDNN_01 -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham getDataTDNN_01 " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " getDataTDNN_01 -> " + e.getMessage());
        }
        return lstBcqt_NT;
    }

    public List<QT_DULIEU_NT> getDataC0Vid02(Connection conn, String sKhoa, String sNgaybc, String sUser,
            String sGrade, List<String> lstArrPoscd, String type_loaitru, String txn_group) {
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
            String strStoreproce = "{call VBSP_IMS_NHAPTAYCN.SP_GET_DATA_COVID_02(?,?,?,?,?,?,?,?,?,?)}";
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
                calstatement.setString(9, type_loaitru);
                calstatement.setString(10, txn_group);
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
                CoreLogger.error(this.getClass().getName() + " getDataQLDB_01 -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham getDataQLDB_01 " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " getDataQLDB_01 -> " + e.getMessage());
        }
        return lstBcqt_NT;
    }

    public boolean saveCoVid03(String khoa, String username, String mapgd, String ngaybc, List<QT_DULIEU_NT> lstData) throws SQLException {
        Connection connection = new DaoConnect().getConnect();
//        java.util.Dictionary map = (java.util.Dictionary) (connection.getTypeMap());
        Object array[] = lstData.toArray();
        ArrayDescriptor des = ArrayDescriptor
                .createDescriptor(QT_DULIEU_NT.ORACLE_TABLE_TYPE, connection);
        ARRAY array_to_pass = new ARRAY(des, connection, array);

        CallableStatement cs = null;
        try {
            cs = connection.prepareCall("{call VBSP_IMS_NHAPTAYCN.SP_SAVE_COVID_03(?, ?, ?, ?, ?)}");
            cs.setString(1, khoa);
            cs.setString(2, username);
            cs.setString(3, mapgd);
            cs.setString(4, ngaybc);
            cs.setArray(5, array_to_pass);
            cs.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Loi trong ham saveCoVid03 " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " saveCoVid03 -> " + e.getMessage());
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

    public List<QT_DULIEU_NT> getDataThaydoiDGHC(Connection conn, String sKhoa, String sNgaybc, String sUser,
            String sGrade, List<String> lstArrPoscd, String exten1, String exten2, String exten3, String exten4) {
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
            String strStoreproce = "{call VBSP_IMS_NHAPTAYCN.SP_GET_DATA_DGHC_01(?,?,?,?,?,?,?,?,?,?,?,?)}";
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
                calstatement.setString(9, exten1);
                calstatement.setString(10, exten2);
                calstatement.setString(11, exten3);
                calstatement.setString(12, exten4);
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
                CoreLogger.error(this.getClass().getName() + " getDataThaydoiDGHC -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham getDataThaydoiDGHC " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " getDataThaydoiDGHC -> " + e.getMessage());
        }
        return lstBcqt_NT;
    }

    public List<ListValue> getDANHMUC(String username, String type, String exten1, String exten2) {
        List<ListValue> lstDMNgNhan = new ArrayList<ListValue>();
        try {

            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call VBSP_IMS_NHAPTAYCN.SP_GET_DANHMUC(?, ?, ?,?,?,?,?)}";
            ResultSet reset = null;
            try {
                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                calstatement.setString(1, username);
                calstatement.setString(2, type);

                calstatement.registerOutParameter(3, oracle.jdbc.OracleTypes.NUMBER);
                calstatement.registerOutParameter(4, oracle.jdbc.OracleTypes.VARCHAR);
                calstatement.registerOutParameter(5, oracle.jdbc.OracleTypes.CURSOR);
                calstatement.setString(6, exten1);
                calstatement.setString(7, exten2);
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
                CoreLogger.error(this.getClass().getName() + " getNgNhan_KCKNTN -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Doi chieu, Phan loai no -> Loi trong ham getNgNhan_KCKNTN " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " getNgNhan_KCKNTN -> " + e.getMessage());
        }
        return lstDMNgNhan;
    }

    public boolean saveThaydoiDGHC(String khoa, String username, String mapgd, String ngaybc, List<QT_DULIEU_NT> lstData, List<String> lstArrPoscd) throws SQLException {
        Connection connection = new DaoConnect().getConnect();
//        java.util.Dictionary map = (java.util.Dictionary) (connection.getTypeMap());
        Object array[] = lstData.toArray();
        ArrayDescriptor des = ArrayDescriptor
                .createDescriptor(QT_DULIEU_NT.ORACLE_TABLE_TYPE, connection);
        ARRAY array_to_pass = new ARRAY(des, connection, array);

        ArrayDescriptor des_ma = ArrayDescriptor.createDescriptor("INDICATOR", connection);

        String[] arrayPoscd = lstArrPoscd.toArray(new String[0]);

        ARRAY oracle_arrayPos = new ARRAY(des_ma, connection, arrayPoscd);

        CallableStatement cs = null;
        try {
            cs = connection.prepareCall("{call VBSP_IMS_NHAPTAYCN.SP_SAVE_DGHC_01(?, ?, ?, ?, ? ,?)}");
            cs.setString(1, khoa);
            cs.setString(2, username);
            cs.setString(3, mapgd);
            cs.setString(4, ngaybc);
            cs.setArray(5, array_to_pass);
            cs.setArray(6, oracle_arrayPos);
            cs.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Loi trong ham saveKyquy04 " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " saveKyquy04 -> " + e.getMessage());
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

    public boolean saveCoVid02(String khoa, String username, String mapgd, String ngaybc, List<QT_DULIEU_NT> lstData, String langn) throws SQLException {
        Connection connection = new DaoConnect().getConnect();
//        java.util.Dictionary map = (java.util.Dictionary) (connection.getTypeMap());
        Object array[] = lstData.toArray();
        ArrayDescriptor des = ArrayDescriptor
                .createDescriptor(QT_DULIEU_NT.ORACLE_TABLE_TYPE, connection);
        ARRAY array_to_pass = new ARRAY(des, connection, array);

        CallableStatement cs = null;
        try {
            cs = connection.prepareCall("{call VBSP_IMS_NHAPTAYCN.SP_SAVE_COVID_02(?, ?, ?, ?, ?,?)}");
            cs.setString(1, khoa);
            cs.setString(2, username);
            cs.setString(3, mapgd);
            cs.setString(4, ngaybc);
            cs.setArray(5, array_to_pass);
            cs.setString(6, langn);
            cs.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Loi trong ham saveCoVid02 " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " saveCoVid02 -> " + e.getMessage());
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

    public String getQuery(String strSave_id, Connection connect) {
        String strQuery = "";
        if (strSave_id.length() == 0 || strSave_id == null) {
            return null;
        }

        // Connection connect = null;
        CallableStatement calstatement = null;
        //Khoi tao function se tra ra du lieu la kieu gi
        String strStoreproce = "{?=call VBSP_IMS_NHAPTAYCN.f_get_query(?,?,?)}";

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
            String strTitle = "Danh sách người lao động được gải ngân, nhận tiền và chưa nhận tiền";
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
            String strTitle = "Danh sách người lao động giải ngân, nhận tiền, chưa nhận tiền";
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
            String strTitle = "Danh sách người lao động giải ngân, nhận tiền, chưa nhận tiền";
            //Lay ra truy van cho bao cao
            String strQuery = getQuery(sSave_id, connect);

            //Lay ra danh sach pos
            List<ListValue> lstPostCd = null;//getPosGeneralReport(connect, sPos_cd, sPos_Flag);

            Integer nValue = 0;
            Integer nCountData = 0;
            //Hashmap luu tru du lieu
            HashMap<Integer, List<Object>> hmData = new HashMap<Integer, List<Object>>();
            //Hashmap luu tru lieu du lieu
            HashMap<Integer, List<Object>> hmMetaTable = new HashMap<Integer, List<Object>>();

            //Hashmap luu tru ten cot du lieu
            HashMap<Integer, List<Object>> hmNameCol = new HashMap<Integer, List<Object>>();

            for (ListValue valuePos : lstPostCd) {
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
            lstDataExp.clear();
            System.gc();
        } catch (SQLException e) {
            System.err.println("Loi trong ham getDataExp " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " getDataExp -> " + e.getMessage());
            System.gc();
            return false;
        }
        return true;
    }

    public List<QT_DULIEU_NT> getDataCovid_04(Connection conn, String sKhoa, String sNgaybc, String sUser,
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
            String strStoreproce = "{call VBSP_IMS_NHAPTAYCN.SP_GET_DATA_COVID_04(?,?,?,?,?,?,?,?)}";
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
                CoreLogger.error(this.getClass().getName() + " getDataCovid_04 -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham getDataCovid_04 " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " getDataCovid_04 -> " + e.getMessage());
        }
        return lstBcqt_NT;
    }

    public boolean saveCoVid04(String khoa, String username, String mapgd, String ngaybc, List<QT_DULIEU_NT> lstData) throws SQLException {
        Connection connection = new DaoConnect().getConnect();
//        java.util.Dictionary map = (java.util.Dictionary) (connection.getTypeMap());
        Object array[] = lstData.toArray();
        ArrayDescriptor des = ArrayDescriptor
                .createDescriptor(QT_DULIEU_NT.ORACLE_TABLE_TYPE, connection);
        ARRAY array_to_pass = new ARRAY(des, connection, array);

        CallableStatement cs = null;
        try {
            cs = connection.prepareCall("{call VBSP_IMS_NHAPTAYCN.SP_SAVE_COVID_04(?, ?, ?, ?, ?)}");
            cs.setString(1, khoa);
            cs.setString(2, username);
            cs.setString(3, mapgd);
            cs.setString(4, ngaybc);
            cs.setArray(5, array_to_pass);
            cs.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Loi trong ham saveCoVid04 " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " saveCoVid04 -> " + e.getMessage());
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

    public int checkLienHuyen(String Capbc, String UserName) throws SQLException {
        int _retVal = 0;
        Connection conn = new DaoConnect().getConnect();
        CallableStatement calstatement = null;
        ResultSet reset = null;
        //Khoi tao procedure cung voi tham so truyen vao la dau ?
        String strStoreproce = "{ ? = call VBSP_IMS_NHAPTAYCN.F_CHECK_LIENHUYEN(?, ?) }";

        try {
            //Khoi tao goi Store
            calstatement = conn.prepareCall(strStoreproce,
                    ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            calstatement.registerOutParameter(1, oracle.jdbc.OracleTypes.NUMBER);
            calstatement.setString(2, Capbc);
            calstatement.setString(3, UserName);
            //Thuc hien execute lay du lieu
            calstatement.execute();
            //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
            _retVal = calstatement.getInt(1);

        } catch (SQLException e) {
            System.err.print(e.getMessage());
            CoreLogger.error(this.getClass().getName() + " checkUserNhaptaycn -> " + e.getMessage());
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

    public List<QT_DULIEU_NT> getDataTracuuSMS(Connection conn, String sKhoa, String sUser,
            String sGrade, List<String> lstArrPoscd,
            String tungay, String denngay, String sodt, String makh, String trangthai) {
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
//            APP_TRACUU_TT.SP_SEARCH_SMS_MOBILE('000101','','','15-JAN-2020','29-MAY-2020','0',:B)
//            String strStoreproce = "{call VBSP_IMS_NHAPTAYCN.SP_GET_DATA_COVID_03(?,?,?,?,?,?,?,?,?,?)}";
            String strStoreproce = "{call APP_TRACUU_TT.SP_SEARCH_SMS_MOBILE(?,?,?,?,?,?,?,?,?)}";
            ResultSet reset = null;

            try {
                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
//                calstatement.registerOutParameter(6, oracle.jdbc.OracleTypes.NUMBER);
//                calstatement.registerOutParameter(7, oracle.jdbc.OracleTypes.VARCHAR);
                calstatement.registerOutParameter(7, oracle.jdbc.OracleTypes.CURSOR);
                calstatement.setArray(1, oracle_arrayPoscd);
                calstatement.setString(2, sodt);
                calstatement.setString(3, makh);
                calstatement.setString(4, tungay);
                calstatement.setString(5, denngay);
                calstatement.setString(6, trangthai);
                calstatement.setString(8, sGrade);
                calstatement.setString(9, sUser);
//                calstatement.setArray(7, oracle_arrayPoscd);      
//                calstatement.setString(9, type_loaitru);
//                calstatement.setString(10, txn_group);
                //Thuc hien execute lay du lieu
                calstatement.execute();
                //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
//                int pn_err_cd = calstatement.getInt(6);
                //thu hien lay mo ta loi
//                String strEdd_txt = calstatement.getString(7);
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
                CoreLogger.error(this.getClass().getName() + " getDataQLDB_01 -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham getDataQLDB_01 " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " getDataQLDB_01 -> " + e.getMessage());
        }
        return lstBcqt_NT;
    }

    public List<QT_DULIEU_NT> getDataTracuuKyQuy(Connection conn, String sKhoa, String sUser,
            String sGrade, List<String> lstArrPoscd,
            String tungay, String denngay, String sodt, String makh, String trangthai) {
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
//            APP_TRACUU_TT.SP_SEARCH_SMS_MOBILE('000101','','','15-JAN-2020','29-MAY-2020','0',:B)
//            String strStoreproce = "{call VBSP_IMS_NHAPTAYCN.SP_GET_DATA_COVID_03(?,?,?,?,?,?,?,?,?,?)}";
            String strStoreproce = "{call APP_TRACUU_TT.SP_SEARCH_SMS_MOBILE(?,?,?,?,?,?,?,?,?)}";
            ResultSet reset = null;

            try {
                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
//                calstatement.registerOutParameter(6, oracle.jdbc.OracleTypes.NUMBER);
//                calstatement.registerOutParameter(7, oracle.jdbc.OracleTypes.VARCHAR);
                calstatement.registerOutParameter(7, oracle.jdbc.OracleTypes.CURSOR);
                calstatement.setArray(1, oracle_arrayPoscd);
                calstatement.setString(2, sodt);
                calstatement.setString(3, makh);
                calstatement.setString(4, tungay);
                calstatement.setString(5, denngay);
                calstatement.setString(6, trangthai);
                calstatement.setString(8, sGrade);
                calstatement.setString(9, sUser);
//                calstatement.setArray(7, oracle_arrayPoscd);      
//                calstatement.setString(9, type_loaitru);
//                calstatement.setString(10, txn_group);
                //Thuc hien execute lay du lieu
                calstatement.execute();
                //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
//                int pn_err_cd = calstatement.getInt(6);
                //thu hien lay mo ta loi
//                String strEdd_txt = calstatement.getString(7);
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
                CoreLogger.error(this.getClass().getName() + " getDataQLDB_01 -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham getDataQLDB_01 " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " getDataQLDB_01 -> " + e.getMessage());
        }
        return lstBcqt_NT;
    }

    public List<QT_DULIEU_NT> getDataLoaitru3502(Connection conn, String sKhoa, String sNgaybc, String sUser,
            String sGrade, List<String> lstArrPoscd, String type_loaitru, String txn_group) {
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
            String strStoreproce = "{call VBSP_IMS_NHAPTAYCN.SP_GET_DATA_LOAITRU3502(?,?,?,?,?,?,?,?,?,?)}";
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
                calstatement.setString(9, type_loaitru);
                calstatement.setString(10, txn_group);
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
                CoreLogger.error(this.getClass().getName() + " getDataQLDB_01 -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham getDataQLDB_01 " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " getDataQLDB_01 -> " + e.getMessage());
        }
        return lstBcqt_NT;
    }

    public boolean saveLoaiTru3502(String khoa, String username, String mapgd, String ngaybc, List<QT_DULIEU_NT> lstData, List<String> lstArrPoscd, String capbc) throws SQLException {
        Connection connection = new DaoConnect().getConnect();
//        java.util.Dictionary map = (java.util.Dictionary) (connection.getTypeMap());
        Object array[] = lstData.toArray();
        ArrayDescriptor des = ArrayDescriptor
                .createDescriptor(QT_DULIEU_NT.ORACLE_TABLE_TYPE, connection);
        ARRAY array_to_pass = new ARRAY(des, connection, array);

        ArrayDescriptor des_ma = ArrayDescriptor.createDescriptor("INDICATOR", connection);

        String[] arrayPoscd = lstArrPoscd.toArray(new String[0]);

        ARRAY oracle_arrayPos = new ARRAY(des_ma, connection, arrayPoscd);

        CallableStatement cs = null;
        try {
            cs = connection.prepareCall("{call VBSP_IMS_NHAPTAYCN.SP_SAVE_LOAITRU_3502(?, ?, ?, ?, ? ,?, ?)}");
            cs.setString(1, khoa);
            cs.setString(2, username);
            cs.setString(3, mapgd);
            cs.setString(4, ngaybc);
            cs.setArray(5, array_to_pass);
            cs.setArray(6, oracle_arrayPos);
            cs.setString(7, capbc);
            cs.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Loi trong ham saveLoaiTru3502 " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " saveLoaiTru3502 -> " + e.getMessage());
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

    public List<QT_DULIEU_NT> getDataXaKK(Connection conn, String sKhoa, String sNgaybc, String sUser,
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
            String strStoreproce = "{call VBSP_IMS_NHAPTAYCN.SP_GET_DATA_XAKK(?,?,?,?,?,?,?,?)}";
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
                CoreLogger.error(this.getClass().getName() + " getDataXaKK -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham getDataXaKK " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " getDataXaKK -> " + e.getMessage());
        }
        return lstBcqt_NT;
    }

    public List<QT_DULIEU_NT> getDataMuasamTS_01(Connection conn, String sKhoa, String sNgaybc, String sUser,
            String sGrade, List<String> lstArrPoscd, String nam_bc, String trangthaims, String dotms, String nghiepvums) {
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
            String strStoreproce = "{call VBSP_IMS_NHAPTAYCN.SP_GET_DATA_MUASAMTS_01(?,?,?,?,?,?,?,?,?,?, ?, ?)}";
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
                calstatement.setString(9, nam_bc);
                calstatement.setString(10, trangthaims);
                calstatement.setString(11, dotms);
                calstatement.setString(12, nghiepvums);
//                calstatement.setString(11, nghiepvu);
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
                CoreLogger.error(this.getClass().getName() + " getDataThaydoiDGHC -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham getDataThaydoiDGHC " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " getDataThaydoiDGHC -> " + e.getMessage());
        }
        return lstBcqt_NT;
    }

    public List<QT_DULIEU_NT> getStatusPosLock(Connection conn, String sKhoa, String sNgaybc, String sUser,
            String sGrade, List<String> lstArrPoscd, String nam_bc, String trangthai, String dot_ms, String nghiepvu) {
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
            String strStoreproce = "{call VBSP_IMS_NHAPTAYCN.SP_GET_STATUS_POS_LOCK(?,?,?,?,?,?,?,?,?,?,?, ?)}";
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
                calstatement.setString(9, nam_bc);
                calstatement.setString(10, trangthai);
                calstatement.setString(11, dot_ms);
                calstatement.setString(12, nghiepvu);
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
                CoreLogger.error(this.getClass().getName() + " getDataThaydoiDGHC -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham getDataThaydoiDGHC " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " getDataThaydoiDGHC -> " + e.getMessage());
        }
        return lstBcqt_NT;
    }

    public boolean saveMuasamTS_001(String khoa, String username, String mapgd, String ngaybc, List<QT_DULIEU_NT> lstData, String nambc,
            String trangthaims, String dotms, String nghiepvums) throws SQLException {
        Connection connection = new DaoConnect().getConnect();
//        java.util.Dictionary map = (java.util.Dictionary) (connection.getTypeMap());
        Object array[] = lstData.toArray();
        ArrayDescriptor des = ArrayDescriptor
                .createDescriptor(QT_DULIEU_NT.ORACLE_TABLE_TYPE, connection);
        ARRAY array_to_pass = new ARRAY(des, connection, array);

        ArrayDescriptor des_ma = ArrayDescriptor.createDescriptor("INDICATOR", connection);

        CallableStatement cs = null;
        try {
            cs = connection.prepareCall("{call VBSP_IMS_NHAPTAYCN.SP_SAVE_MUASAMTS_01(?, ?, ?, ?, ? ,?, ?, ?, ?)}");
            cs.setString(1, khoa);
            cs.setString(2, username);
            cs.setString(3, mapgd);
            cs.setString(4, ngaybc);
            cs.setArray(5, array_to_pass);
            cs.setString(6, nambc);
            cs.setString(7, trangthaims);
            cs.setString(8, dotms);
            cs.setString(9, nghiepvums);
            cs.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Loi trong ham saveKyquy04 " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " saveKyquy04 -> " + e.getMessage());
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

//    public boolean ChotMuasamTS(String khoa, String username, String mapgd, String ngaybc, String nambc, String dotms) throws SQLException {
    public int ChotMuasamTS(String khoa, String username, String ngaybc, String nambc, String trangthaims, String dotms, String nghiepvums) throws SQLException {
        int _retVal = 0;
        Connection conn = new DaoConnect().getConnect();
        CallableStatement calstatement = null;
        ResultSet reset = null;
        //Khoi tao procedure cung voi tham so truyen vao la dau ?
        String strStoreproce = "{ ? = call VBSP_IMS_NHAPTAYCN.F_CHOTMUASAMTS(?, ?, ?, ?, ?, ?, ?) }";

        try {
            //Khoi tao goi Store
            calstatement = conn.prepareCall(strStoreproce,
                    ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            calstatement.registerOutParameter(1, oracle.jdbc.OracleTypes.NUMBER);
            calstatement.setString(2, khoa);
            calstatement.setString(3, username);
            calstatement.setString(4, ngaybc);
            calstatement.setString(5, nambc);
            calstatement.setString(6, trangthaims);
            calstatement.setString(7, dotms);
            calstatement.setString(8, nghiepvums);
            //Thuc hien execute lay du lieu
            calstatement.execute();
            //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
            _retVal = calstatement.getInt(1);

        } catch (SQLException e) {
            System.err.print(e.getMessage());
            CoreLogger.error(this.getClass().getName() + " ChotMuasamTS -> " + e.getMessage());
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

    public String Get_status_input(String khoa, String username, String ngaybc, String nambc, String trangthaims, String typeInput, String dotms, String nghiepvums) throws SQLException {
        String _retVal = "";
        Connection conn = new DaoConnect().getConnect();
        CallableStatement calstatement = null;
        ResultSet reset = null;
        //Khoi tao procedure cung voi tham so truyen vao la dau ?
        String strStoreproce = "{ ? = call VBSP_IMS_NHAPTAYCN.F_GET_TT_CHOTMUASAMTS(?, ?, ?, ?, ?, ?, ?, ?) }";

        try {
            //Khoi tao goi Store
            calstatement = conn.prepareCall(strStoreproce,
                    ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            calstatement.registerOutParameter(1, oracle.jdbc.OracleTypes.VARCHAR);
            calstatement.setString(2, khoa);
            calstatement.setString(3, username);
            calstatement.setString(4, ngaybc);
            calstatement.setString(5, nambc);
            calstatement.setString(6, trangthaims);
            calstatement.setString(7, typeInput);
            calstatement.setString(8, dotms);
            calstatement.setString(9, nghiepvums);
            //Thuc hien execute lay du lieu
            calstatement.execute();
            //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
            _retVal = calstatement.getString(1);

        } catch (SQLException e) {
            System.err.print(e.getMessage());
            CoreLogger.error(this.getClass().getName() + " ChotMuasamTS -> " + e.getMessage());
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

    public List<ListValue> getLOV_Muasamts(String username, String type, String nambc, String dotms, String nghiepvu) {
        List<ListValue> lstDMNgNhan = new ArrayList<ListValue>();
        try {

            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call VBSP_IMS_NHAPTAYCN.SP_GET_LOV_MUASAMTS(?, ?, ?,?,?, ?, ?, ?)}";
            ResultSet reset = null;
            try {
                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                calstatement.setString(1, username);
                calstatement.setString(2, type);

                calstatement.registerOutParameter(3, oracle.jdbc.OracleTypes.NUMBER);
                calstatement.registerOutParameter(4, oracle.jdbc.OracleTypes.VARCHAR);
                calstatement.registerOutParameter(5, oracle.jdbc.OracleTypes.CURSOR);
                calstatement.setString(6, nambc);
                calstatement.setString(7, dotms);
                calstatement.setString(8, nghiepvu);
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
                CoreLogger.error(this.getClass().getName() + " getNgNhan_KCKNTN -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Doi chieu, Phan loai no -> Loi trong ham getNgNhan_KCKNTN " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " getNgNhan_KCKNTN -> " + e.getMessage());
        }
        return lstDMNgNhan;
    }

    public int MoChotMuasamTS(String khoa, String username, String ngaybc, String nambc, String dotms, List<String> lstArrPoscd, String nghiepvu, String trangthaims) throws SQLException {
        int _retVal = 0;
        Connection conn = new DaoConnect().getConnect();
        CallableStatement calstatement = null;
        ResultSet reset = null;
        ArrayDescriptor des = ArrayDescriptor.createDescriptor("POS_CD", conn);
        String[] arrayPoscd = lstArrPoscd.toArray(new String[0]);
        ARRAY oracle_arrayPoscd = new ARRAY(des, conn, arrayPoscd);
        //Khoi tao procedure cung voi tham so truyen vao la dau ?
        String strStoreproce = "{ ? = call VBSP_IMS_NHAPTAYCN.F_MOCHOTMUASAMTS(?, ?, ?, ?, ?, ?, ?, ?) }";

        try {
            //Khoi tao goi Store
            calstatement = conn.prepareCall(strStoreproce,
                    ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            calstatement.registerOutParameter(1, oracle.jdbc.OracleTypes.NUMBER);
            calstatement.setString(2, khoa);
            calstatement.setString(3, username);
            calstatement.setString(4, ngaybc);
            calstatement.setString(5, nambc);
            calstatement.setString(6, trangthaims);
            calstatement.setArray(7, oracle_arrayPoscd);
            calstatement.setString(8, dotms);
            calstatement.setString(9, nghiepvu);
            //Thuc hien execute lay du lieu
            calstatement.execute();
            //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
            _retVal = calstatement.getInt(1);

        } catch (SQLException e) {
            System.err.print(e.getMessage());
            CoreLogger.error(this.getClass().getName() + " ChotMuasamTS -> " + e.getMessage());
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

    public List<String> getDataSendMuasamts(String type, String khoa, String mapgd, String ngay_bc, String nghiepvu, String nambc, String dot_ms, String trangthai) throws SQLException {
        List<String> lstData = new ArrayList<>();
        DaoConnect daoconnect = new DaoConnect();
        Connection conn = null;
        conn = daoconnect.getConnect();
        CallableStatement calstatement = null;
        ResultSet reset = null;
//        try {
        //Khoi tao procedure cung voi tham so truyen vao la dau ?
        String strStoreproce = "{call VBSP_IMS_NHAPTAYCN.SP_GET_DATA_MUASAMTS_SYNC(?,?,?,?,?,?,?,?,?,?,?)}";
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
            calstatement.setString(8, nghiepvu);
            calstatement.setString(9, nambc);
            calstatement.setString(10, dot_ms);
            calstatement.setString(11, trangthai);
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
            CoreLogger.error(this.getClass().getName() + " getDataSendMuasamts -> " + e.getMessage());
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

    public int getCheckSendData(String filename) {
        int nRowTotal = 0;
        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{?=call VBSP_IMS_NHAPTAYCN.F_CHECK_SEND_DATA(?)}";
            ResultSet reset = null;

            try {
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                //Tham so thu nhat truyen vao la co lay theo pos hay main pos  
                calstatement.registerOutParameter(1, oracle.jdbc.OracleTypes.NUMBER);
                calstatement.setString(2, filename);
                calstatement.execute();
                //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
                nRowTotal = calstatement.getInt(1);
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
                CoreLogger.error(" getCountTotalRow -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham getCountTotalRow " + e.getMessage());
            CoreLogger.error(" getCountTotalRow -> " + e.getMessage());
        }

        return nRowTotal;
    }

    public List<QT_DULIEU_NT> getStatusSendCnMuasamTS(String Khoa, String tt_khoa, List<String> lstArrPoscd,
            String nam_bc, String trangthai, String dot_ms, String nghiepvu, String macnAll) {
        List<QT_DULIEU_NT> lstStatusSendcn = new ArrayList<QT_DULIEU_NT>();
        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            ArrayDescriptor des = ArrayDescriptor.createDescriptor("POS_CD", conn);
            String[] arrayPoscd = lstArrPoscd.toArray(new String[0]);
            ARRAY oracle_arrayPoscd = new ARRAY(des, conn, arrayPoscd);

            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call VBSP_IMS_NHAPTAYCN.SP_GET_STATUS_SENDMUASAMTS_CN(?,?,?,?,?,?, ?, ?, ?)}";
            ResultSet reset = null;
            try {
                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                calstatement.registerOutParameter(3, oracle.jdbc.OracleTypes.CURSOR);
                calstatement.setString(1, Khoa);
                calstatement.setString(2, tt_khoa);
                calstatement.setArray(4, oracle_arrayPoscd);
                calstatement.setString(5, nghiepvu);
                calstatement.setString(6, nam_bc);
                calstatement.setString(7, dot_ms);
                calstatement.setString(8, trangthai);
                calstatement.setString(9, macnAll);

                //Thuc hien execute lay du lieu
                calstatement.execute();
                //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
                //Lay cursor ra resultset
                reset = (ResultSet) calstatement.getObject(3);
                //<editor-fold defaultstate="collapsed" desc="Tieu de cho cot">

                ResultSetMetaData resetMeta = reset.getMetaData();
                if (resetMeta.getColumnCount() <= 29 && Khoa.equals("ALL")) {
                    QT_DULIEU_NT value = QT_DULIEU_NT.newInstance();
                    value.setD1(resetMeta.getColumnName(1));
                    value.setD2(resetMeta.getColumnName(2));
                    value.setD3(resetMeta.getColumnName(3));
                    value.setD4(resetMeta.getColumnName(4));
                    value.setD5(resetMeta.getColumnName(5));
                    value.setD6(resetMeta.getColumnName(6));
                    value.setD7(resetMeta.getColumnName(7));
                    value.setD8(resetMeta.getColumnName(8));
                    value.setD9(resetMeta.getColumnName(9));
                    value.setD10(resetMeta.getColumnName(10));
                    value.setD11(resetMeta.getColumnName(11));
                    value.setD12(resetMeta.getColumnName(12));
                    value.setD13(resetMeta.getColumnName(13));
                    value.setD14(resetMeta.getColumnName(14));
                    value.setD15(resetMeta.getColumnName(15));
                    value.setD16(resetMeta.getColumnName(16));
                    value.setD17(resetMeta.getColumnName(17));
                    value.setD18(resetMeta.getColumnName(18));
                    value.setD19(resetMeta.getColumnName(19));
                    value.setD20(resetMeta.getColumnName(20));
                    value.setD21(resetMeta.getColumnName(21));
                    value.setD22(resetMeta.getColumnName(22));
                    value.setD23(resetMeta.getColumnName(23));
                    value.setD24(resetMeta.getColumnName(24));
                    value.setD25(resetMeta.getColumnName(25));
                    value.setD26(resetMeta.getColumnName(26));
//                    value.setD27(resetMeta.getColumnName(27));
//                    value.setD28(resetMeta.getColumnName(28));
//                    value.setD29(resetMeta.getColumnName(29));
                    lstStatusSendcn.add(value);
                }
//</editor-fold>
                while (reset.next()) {
                    QT_DULIEU_NT value = QT_DULIEU_NT.newInstance();
                    if (Khoa.equals("ALL")) {
                        value.setD1(reset.getString(1));
                        value.setD2(reset.getString(2));
                        value.setD3(reset.getString(3));
                        value.setD4(reset.getString(4).equals("1") ? "X" : "");
                        value.setD5(reset.getString(5).equals("1") ? "X" : "");
                        value.setD6(reset.getString(6).equals("1") ? "X" : "");
                        value.setD7(reset.getString(7).equals("1") ? "X" : "");
                        value.setD8(reset.getString(8).equals("1") ? "X" : "");
                        value.setD9(reset.getString(9).equals("1") ? "X" : "");
                        value.setD10(reset.getString(10).equals("1") ? "X" : "");
                        value.setD11(reset.getString(11).equals("1") ? "X" : "");
                        value.setD12(reset.getString(12).equals("1") ? "X" : "");
                        value.setD13(reset.getString(13).equals("1") ? "X" : "");
                        value.setD14(reset.getString(14).equals("1") ? "X" : "");
                        value.setD15(reset.getString(15).equals("1") ? "X" : "");
                        value.setD16(reset.getString(16).equals("1") ? "X" : "");
                        value.setD17(reset.getString(17).equals("1") ? "X" : "");
                        value.setD18(reset.getString(18).equals("1") ? "X" : "");
                        value.setD19(reset.getString(19).equals("1") ? "X" : "");
                        value.setD20(reset.getString(20).equals("1") ? "X" : "");
                        value.setD21(reset.getString(21).equals("1") ? "X" : "");
                        value.setD22(reset.getString(22).equals("1") ? "X" : "");
                        value.setD23(reset.getString(23).equals("1") ? "X" : "");
                        value.setD24(reset.getString(24).equals("1") ? "X" : "");
                        value.setD25(reset.getString(25).equals("1") ? "X" : "");
                        value.setD26(reset.getString(26).equals("1") ? "X" : "");
//                        value.setD27(reset.getString(27).equals("1") ? "X" : "");
//                        value.setD28(reset.getString(28).equals("1") ? "X" : "");
//                        value.setD29(reset.getString(29).equals("1") ? "X" : "");

                    } else {
                        value.setD1(reset.getString(1));
                        value.setD2(reset.getString(2));
                        value.setD3(reset.getString(3));
                        value.setD4(reset.getString(4));
                        value.setD5(reset.getString(5));
                        value.setD6(reset.getString(6));
                        value.setD7(reset.getString(7));
                        value.setD8(reset.getString(8));
                        value.setD9(reset.getString(9));
                        value.setD10(reset.getString(10));
                        value.setD11(reset.getString(11));
                    }
                    lstStatusSendcn.add(value);
                }

            } catch (SQLException e) {
                System.err.print(e.getMessage());
                CoreLogger.error(this.getClass().getName() + " getDataPL01TL -> " + e.getMessage());
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
        } catch (Exception e) {
            System.err.println("Loi trong ham getAllBcqt " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " getDataPL01TL -> " + e.getMessage());
        }
        return lstStatusSendcn;
    }

    public boolean setStatusLockTw(String khoa, List<String> lstMapgd, String tt_khoa, String username, String grade,
            String nam_bc, String trangthai, String dot_ms, String nghiepvu) throws SQLException {
        Connection connection = new DaoConnect().getConnect();
//        java.util.Dictionary map = (java.util.Dictionary) (connection.getTypeMap());
        Object array[] = lstMapgd.toArray();
        ArrayDescriptor des = ArrayDescriptor.createDescriptor("POS_CD", connection);
        ARRAY array_to_pass = new ARRAY(des, connection, array);
        CallableStatement cs = null;
        boolean bSuccess = false;
        try {
            cs = connection.prepareCall("{call VBSP_IMS_NHAPTAYCN.SP_OPEN_PGD_TW(?, ?, ?, ?, ?, ?, ?, ?, ?)}");
            cs.setString(1, khoa);
            cs.setArray(2, array_to_pass);
            cs.setString(3, tt_khoa);
            cs.setString(4, username);
            cs.setString(5, grade);
            cs.setString(6, nghiepvu);
            cs.setString(7, nam_bc);
            cs.setString(8, dot_ms);
            cs.setString(9, trangthai);

            cs.execute();
            bSuccess = true;
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Loi trong ham setStatusLock " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " setStatusLock -> " + e.getMessage());
            bSuccess = false;
        } finally {
            if (cs != null) {
                cs.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return bSuccess;
    }

    public List<QT_DULIEU_NT_50> getDataQd23_001(Connection conn, String sKhoa, String sNgaybc, String sUser,
            String sGrade, List<String> lstArrPoscd, String maDN, String loaiNV) {
        List<QT_DULIEU_NT_50> lstBcqt_NT = new ArrayList<QT_DULIEU_NT_50>();
        try {
            ArrayDescriptor des = ArrayDescriptor.createDescriptor("POS_CD", conn);
            String[] arrayPoscd = lstArrPoscd.toArray(new String[0]);
            ARRAY oracle_arrayPoscd = new ARRAY(des, conn, arrayPoscd);
//            DaoConnect daoconnect = new DaoConnect();
//            Connection conn = null;
//            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call VBSP_IMS_NHAPTAYCN.SP_GET_DATA_QD23_01(?,?,?,?,?,?,?,?,?,?)}";
            ResultSet reset = null;

            try {
                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                calstatement.registerOutParameter(8, oracle.jdbc.OracleTypes.NUMBER);
                calstatement.registerOutParameter(9, oracle.jdbc.OracleTypes.VARCHAR);
                calstatement.registerOutParameter(10, oracle.jdbc.OracleTypes.CURSOR);
                calstatement.setString(1, sKhoa);
                calstatement.setString(2, sUser);
                calstatement.setString(3, sGrade);
                calstatement.setString(4, sNgaybc);
                calstatement.setArray(5, oracle_arrayPoscd);
                calstatement.setString(6, maDN);
                calstatement.setString(7, loaiNV);
                //Thuc hien execute lay du lieu
                calstatement.execute();
                //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
                int pn_err_cd = calstatement.getInt(8);
                //thu hien lay mo ta loi
                String strEdd_txt = calstatement.getString(9);
                //Lay cursor ra resultset
                reset = (ResultSet) calstatement.getObject(10);
                while (reset.next()) {

                    QT_DULIEU_NT_50 value = QT_DULIEU_NT_50.newInstance();
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
                    value.setKIEUIN(reset.getInt(47));
                    
                    value.setD31(reset.getString(48));
                    value.setD32(reset.getString(49));
                    value.setD33(reset.getString(50));
                    value.setD34(reset.getString(51));
                    value.setD35(reset.getString(52));
                    value.setD36(reset.getString(53));
                    value.setD37(reset.getString(54));
                    value.setD38(reset.getString(55));
                    value.setD39(reset.getString(56));
                    value.setD40(reset.getString(57));
                    value.setD41(reset.getString(58));
                    value.setD42(reset.getString(59));
                    value.setD43(reset.getString(60));
                    value.setD44(reset.getString(61));
                    value.setD45(reset.getString(62));
                    value.setD46(reset.getString(63));
                    value.setD47(reset.getString(64));
                    value.setD48(reset.getString(65));
                    value.setD49(reset.getString(66));
                    

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
                CoreLogger.error(this.getClass().getName() + " getDataQd23_001 -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham getDataQd23_001 " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " getDataQd23_001 -> " + e.getMessage());
        }
        return lstBcqt_NT;
    }

    public boolean saveQD23_001(String khoa, String username, String mapgd, String ngaybc, String capBc, List<QT_DULIEU_NT_50> lstData, String loaiNV) throws SQLException {
        Connection connection = new DaoConnect().getConnect();
//        java.util.Dictionary map = (java.util.Dictionary) (connection.getTypeMap());
        Object array[] = lstData.toArray();
        ArrayDescriptor des = ArrayDescriptor
                .createDescriptor(QT_DULIEU_NT_50.ORACLE_TABLE_TYPE, connection);
        ARRAY array_to_pass = new ARRAY(des, connection, array);

        CallableStatement cs = null;
        try {
            cs = connection.prepareCall("{call VBSP_IMS_NHAPTAYCN.SP_SAVE_QD23_001(?, ?, ?, ?, ?, ?, ?)}");
            cs.setString(1, khoa);
            cs.setString(2, username);
            cs.setString(3, mapgd);
            cs.setString(4, ngaybc);
            cs.setString(5, capBc);
            cs.setArray(6, array_to_pass);
            cs.setString(7, loaiNV);
            cs.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Loi trong ham saveQD23_001 " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " saveQD23_001 -> " + e.getMessage());
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

    public List<QT_DULIEU_NT> getDataQd2368(Connection conn, String sKhoa, String sNgaybc, String sUser,
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
            String strStoreproce = "{call VBSP_IMS_NHAPTAYCN.SP_GET_DATA_QD2368(?,?,?,?,?,?,?,?)}";
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
                int iErrorCode = calstatement.getInt(6);
                //thu hien lay mo ta loi
                String strErrorMessage = calstatement.getString(7);
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
                    value.setKIEUIN(reset.getInt(47));

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
                CoreLogger.error(this.getClass().getName() + " getDataQd2368 -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham getDataQd2368 " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " getDataQd2368 -> " + e.getMessage());
        }
        return lstBcqt_NT;
    }

    public boolean saveQD2368(String khoa, String username, String mapgd, String ngaybc, String capBc, List<QT_DULIEU_NT> lstData) throws SQLException {
        Connection connection = new DaoConnect().getConnect();
//        java.util.Dictionary map = (java.util.Dictionary) (connection.getTypeMap());
        Object array[] = lstData.toArray();
        ArrayDescriptor des = ArrayDescriptor
                .createDescriptor(QT_DULIEU_NT.ORACLE_TABLE_TYPE, connection);
        ARRAY array_to_pass = new ARRAY(des, connection, array);

        CallableStatement cs = null;
        try {
            cs = connection.prepareCall("{call VBSP_IMS_NHAPTAYCN.SP_SAVE_QD2368(?, ?, ?, ?, ?, ?)}");
            cs.setString(1, khoa);
            cs.setString(2, username);
            cs.setString(3, mapgd);
            cs.setString(4, ngaybc);
            cs.setString(5, capBc);
            cs.setArray(6, array_to_pass);
            cs.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Loi trong ham saveQD2368 " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " saveQD2368 -> " + e.getMessage());
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

    public List<QT_DULIEU_NT_50> getDataQd23_001_Dieuchinh(Connection conn, String sKhoa, String sNgaybc, String sUser,
            String sGrade, String maDN, String thangdc) {
        List<QT_DULIEU_NT_50> lstBcqt_NT = new ArrayList<QT_DULIEU_NT_50>();
        try {
//            ArrayDescriptor des = ArrayDescriptor.createDescriptor("POS_CD", conn);
//            String[] arrayPoscd = lstArrPoscd.toArray(new String[0]);
//            ARRAY oracle_arrayPoscd = new ARRAY(des, conn, arrayPoscd);
//            DaoConnect daoconnect = new DaoConnect();
//            Connection conn = null;
//            conn = daoconnect.getConnect();
            Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse(sNgaybc);
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
            String dateStr = sdf.format(date1);
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call VBSP_IMS_NHAPTAYCN.SP_GET_DATA_QD23_01_DIEUCHINH(?,?,?,?,?,?,?,?,?)}";
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
                calstatement.setString(4, dateStr);
                calstatement.setString(5, maDN);
                calstatement.setString(9, thangdc);
                //Thuc hien execute lay du lieu
                calstatement.execute();
                //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
                int pn_err_cd = calstatement.getInt(6);
                //thu hien lay mo ta loi
                String strEdd_txt = calstatement.getString(7);
                //Lay cursor ra resultset
                reset = (ResultSet) calstatement.getObject(8);
                while (reset.next()) {

                    QT_DULIEU_NT_50 value = QT_DULIEU_NT_50.newInstance();
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
                CoreLogger.error(this.getClass().getName() + " getDataQd23_001 -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham getDataQd23_001 " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " getDataQd23_001 -> " + e.getMessage());
        }
        return lstBcqt_NT;
    }
    
    public List<QT_DULIEU_NT_50> getDataQd23_001_Dc_pheduyet(Connection conn, String sKhoa, String sNgaybc, String sUser,
            String sGrade, String maDN, String thangdc) {
        List<QT_DULIEU_NT_50> lstBcqt_NT = new ArrayList<QT_DULIEU_NT_50>();
        try {
//            ArrayDescriptor des = ArrayDescriptor.createDescriptor("POS_CD", conn);
//            String[] arrayPoscd = lstArrPoscd.toArray(new String[0]);
//            ARRAY oracle_arrayPoscd = new ARRAY(des, conn, arrayPoscd);
//            DaoConnect daoconnect = new DaoConnect();
//            Connection conn = null;
//            conn = daoconnect.getConnect();
            Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse(sNgaybc);
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
            String dateStr = sdf.format(date1);
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call VBSP_IMS_NHAPTAYCN.SP_GET_DATA_QD2301_DCPHEDUYET(?,?,?,?,?,?,?,?,?)}";
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
                calstatement.setString(4, dateStr);
                calstatement.setString(5, maDN);
                calstatement.setString(9, thangdc);
                //Thuc hien execute lay du lieu
                calstatement.execute();
                //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
                int pn_err_cd = calstatement.getInt(6);
                //thu hien lay mo ta loi
                String strEdd_txt = calstatement.getString(7);
                //Lay cursor ra resultset
                reset = (ResultSet) calstatement.getObject(8);
                while (reset.next()) {

                    QT_DULIEU_NT_50 value = QT_DULIEU_NT_50.newInstance();
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
                    
                    value.setD31(reset.getString(48));
                    value.setD32(reset.getString(49));
                    value.setD33(reset.getString(50));
                    value.setD34(reset.getString(51));
                    value.setD35(reset.getString(52));
                    value.setD36(reset.getString(53));
                    value.setD37(reset.getString(54));
                    value.setD38(reset.getString(55));
                    value.setD39(reset.getString(56));
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
                CoreLogger.error(this.getClass().getName() + " getDataQd23_001 -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham getDataQd23_001 " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " getDataQd23_001 -> " + e.getMessage());
        }
        return lstBcqt_NT;
    }

    public boolean saveQD23_001_Dieuchinh(String khoa, String username, String mapgd, String ngaybc, String capBc, List<QT_DULIEU_NT_50> lstData, String thangbc) throws SQLException, ParseException {
        Connection connection = new DaoConnect().getConnect();
//        java.util.Dictionary map = (java.util.Dictionary) (connection.getTypeMap());
        Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse(ngaybc);
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
            String dateStr = sdf.format(date1);
        Object array[] = lstData.toArray();
        ArrayDescriptor des = ArrayDescriptor
                .createDescriptor(QT_DULIEU_NT_50.ORACLE_TABLE_TYPE, connection);
        ARRAY array_to_pass = new ARRAY(des, connection, array);

        CallableStatement cs = null;
        try {
            cs = connection.prepareCall("{call VBSP_IMS_NHAPTAYCN.SP_SAVE_QD23_001_DIEUCHINH(?, ?, ?, ?, ?, ?, ?)}");
            cs.setString(1, khoa);
            cs.setString(2, username);
            cs.setString(3, mapgd);
            cs.setString(4, dateStr);
            cs.setString(5, capBc);
            cs.setArray(6, array_to_pass);
            cs.setString(7, thangbc);
            cs.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Loi trong ham saveQD23_001 " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " saveQD23_001 -> " + e.getMessage());
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

    public boolean insert_DS_NGUOILD_QD23(String mabc, String poscd, String fileName, Date ngaybc, String username, List<ModelExcelFile> lstExcel, String masothue, String lanGN) throws Exception, SQLException {
        boolean bSuccess = true;
        DaoConnect daoconnect = new DaoConnect();
        Connection conn = null;

        PreparedStatement insert = null;
        CallableStatement statementDelete = null;
        CallableStatement statementUpdate = null;
        SimpleDateFormat sdf = new SimpleDateFormat("MM/YYYY");
        String dateStr = sdf.format(ngaybc);
        String sInsert = "";
        String Delete = "";
        String Update = "";
        try {
            conn = daoconnect.getConnect();
            sInsert = "insert into dulieu_nt(KHOA,TT_HIENTHI, MAPGD, D1, D2, D3, D4, D5, D6, D7, D8, D9, D10, D11, D12, D15 , ngaybc, "
                    + " NGUOI_NHAP)\n"
                    + "values(?, ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            conn.setAutoCommit(false);
            insert = conn.prepareStatement(sInsert);
            Delete = "delete from dulieu_nt where MAPGD=? and khoa = ? and D7 = ? and d15 = ?";
            statementDelete = conn.prepareCall(Delete);
//                    statementDelete.setDate(1, new java.sql.Date(ngaybc.getTime()));
            statementDelete.setString(1, poscd);
            statementDelete.setString(2, mabc);
            statementDelete.setString(3, dateStr);
            statementDelete.setString(4, masothue);

            statementDelete.execute();
            for (int i = 0; i < lstExcel.size(); i++) {
                ModelExcelFile value = lstExcel.get(i);
//                        insert.setString(1, fileName);
//                        insert.setDate(2, new java.sql.Date(ngaybc.getTime()));
                insert.setString(1, mabc);
//                        insert.setString(4, poscd);
                insert.setString(2, value.getC1());
                insert.setString(3, poscd);
                insert.setString(4, value.getC2());
                insert.setString(5, value.getC3());
                insert.setString(6, value.getN1());
                insert.setString(7, value.getN2());
                insert.setString(8, value.getN3());

                insert.setString(9, value.getN4());
                insert.setString(10, dateStr);
                insert.setString(11, value.getN6());
                insert.setString(12, value.getN7());
                insert.setString(13, value.getN8());
                insert.setString(14, value.getN9());
                insert.setString(15, value.getN10());
                insert.setString(16, masothue);

                insert.setDate(17, new java.sql.Date(ngaybc.getTime()));
                insert.setString(18, username);
                insert.execute();
            }
            Delete = "delete from dulieu_nt where (D2 is null or D4 is null)  and khoa = ?";
            statementDelete = conn.prepareCall(Delete);
//                    statementDelete.setDate(1, new java.sql.Date(ngaybc.getTime()));                    
            statementDelete.setString(1, mabc);

            statementDelete.execute();

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

    public int getLockStatus(String key, String reportDate, String userName, String reportGrade) {
        try {

            Connection conn = new DaoConnect().getConnect();
            String dateStr = "";
            if (!reportDate.isEmpty()) {
                Date dateValue = new SimpleDateFormat("yyyyMMdd").parse(reportDate);
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
                dateStr = sdf.format(dateValue);
            }
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call VBSP_IMS_NHAPTAYCN.SP_GET_LOCK_STATUS(?,?,?,?,?)}";
            ResultSet reset = null;

            //Khoi tao goi store
            calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            calstatement.registerOutParameter(5, oracle.jdbc.OracleTypes.NUMBER);

            calstatement.setString(1, key);
            calstatement.setString(2, userName);
            calstatement.setString(3, reportGrade);
            calstatement.setString(4, dateStr);

            //Thuc hien execute lay du lieu
            calstatement.execute();
            //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
            int lockStatus = calstatement.getInt(5);

            if (calstatement != null) {
                calstatement.close();
            }
            if (conn != null) {
                conn.close();
            }

            return lockStatus;

        } catch (Exception e) {
            System.err.println("Loi trong ham getLockStatus " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " getLockStatus -> " + e.getMessage());

        }
        return 0;
    }
    
    public ArrayList<POSModel> getPosExportFile(String posCode, String KHOA){
       List<String> lstData = new ArrayList<>();
       ArrayList<POSModel> posList = new ArrayList<>();
       try{
        DaoConnect daoconnect = new DaoConnect();
        Connection conn = null;
        conn = daoconnect.getConnect();
        CallableStatement calstatement = null;
        ResultSet reset = null;
//        try {
        //Khoi tao procedure cung voi tham so truyen vao la dau ?
        String strStoreproce = "{call VBSP_IMS_NHAPTAYCN.SP_LOAD_ALL_POS(?,?,?,?,?)}";
        try {
            //Khoi tao goi store
            calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            calstatement.registerOutParameter(2, oracle.jdbc.OracleTypes.NUMBER);
            calstatement.registerOutParameter(3, oracle.jdbc.OracleTypes.VARCHAR);
            calstatement.registerOutParameter(4, oracle.jdbc.OracleTypes.CURSOR);
            calstatement.setString(1, posCode);
            calstatement.setString(5, KHOA);
            //Thuc hien execute lay du lieu
            calstatement.execute();
            //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
            int pn_err_cd = calstatement.getInt(2);
            //thu hien lay mo ta loi
            String strEdd_txt = calstatement.getString(3);
            //Lay cursor ra resultset
            reset = (ResultSet) calstatement.getObject(4);
//                reset = (ResultSet) calstatement.getObject(2);

                while (reset.next()) {
                    POSModel p = new POSModel();
                    p.setId(reset.getString("MA"));
                    p.setDesc(reset.getString("TEN"));

                    posList.add(p);
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
                System.err.println("Loi trong ham getCommuneList " + e.getMessage());
                CoreLogger.error(POSModel.class.getCanonicalName() + " getPosList  -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham getCommuneList " + e.getMessage());
            CoreLogger.error(DaoDieuchinhkh.class.getCanonicalName() + " getPosList  -> " + e.getMessage());
        }
        return posList;
    }
    
    public List<QT_DULIEU_NT_50> getDataEportElxQD23001(String maBC, String poscd,String sGrade, String sNgaybc) {
        List<QT_DULIEU_NT_50> lstBcqt_NT = new ArrayList<QT_DULIEU_NT_50>();
        try {
//            ArrayDescriptor des = ArrayDescriptor.createDescriptor("POS_CD", conn);
//            String[] arrayPoscd = lstArrPoscd.toArray(new String[0]);
//            ARRAY oracle_arrayPoscd = new ARRAY(des, conn, arrayPoscd);
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
    
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call VBSP_IMS_NHAPTAYCN.SP_GET_DATA_EXP_QD23001(?,?,?,?,?,?,?)}";
            ResultSet reset = null;

            try {
                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                calstatement.registerOutParameter(5, oracle.jdbc.OracleTypes.NUMBER);
                calstatement.registerOutParameter(6, oracle.jdbc.OracleTypes.VARCHAR);
                calstatement.registerOutParameter(7, oracle.jdbc.OracleTypes.CURSOR);
                calstatement.setString(1, maBC);
                calstatement.setString(2, poscd);
                calstatement.setString(3, sGrade);
                calstatement.setString(4, sNgaybc);
                //Thuc hien execute lay du lieu
                calstatement.execute();
                //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
                int pn_err_cd = calstatement.getInt(5);
                //thu hien lay mo ta loi
                String strEdd_txt = calstatement.getString(6);
                //Lay cursor ra resultset
                reset = (ResultSet) calstatement.getObject(7);
                while (reset.next()) {

                    QT_DULIEU_NT_50 value = QT_DULIEU_NT_50.newInstance();
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
                CoreLogger.error(this.getClass().getName() + " getDataQd23_001 -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham getDataQd23_001 " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " getDataQd23_001 -> " + e.getMessage());
        }
        return lstBcqt_NT;
    }
}
