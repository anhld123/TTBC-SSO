/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.nghiquyet11cp;

import vbsp.ims.nhaptaycn.dao.*;
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
import vbsp.ims.restapi.DuLieuNTService;
import vbsp.ims.restapi.NQ11cpModel;

/**
 *
 * @author LION
 */
public class DaoNghiquyet11cp {

    public static DaoNghiquyet11cp newInstance() {
        return new DaoNghiquyet11cp();
    }

    public List<ListValue> getAllBaocao(String capbc) {
        List<ListValue> lstAllBcqt = new ArrayList<ListValue>();
        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call VBSP_IMS_NGHIQUYET11CP.SP_LOAD_ALL_BC(?,?,?, ?)}";
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
    
    public List<String> getAllPosUser(String username, String khoa) throws SQLException {
        List<String> lstData = new ArrayList<>();
        DaoConnect daoconnect = new DaoConnect();
        Connection conn = null;
        conn = daoconnect.getConnect();
        CallableStatement calstatement = null;
        ResultSet reset = null;
//        try {
        //Khoi tao procedure cung voi tham so truyen vao la dau ?
        String strStoreproce = "{call VBSP_IMS_NGHIQUYET11CP.SP_LOAD_ALL_POS(?,?,?,?,?)}";
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
    
    public List<ReportParam> getReportParmams(Connection conn, String sKhoa, String sUserName, String sGrade) {
        ArrayList<ReportParam> report_param_list = new ArrayList<>();

        try {
//            DaoConnect daoconnect = new DaoConnect();
//            Connection conn ;
//            conn = daoconnect.getConnect();
            CallableStatement calstatement;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call VBSP_IMS_NGHIQUYET11CP.SP_LOAD_PARA_BC(?, ?, ?, ?, ?,?,?)}";
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

    public List<ModelTreeNode> getDataPosTreeNode(Connection conn, String strUserName, String sGrade, String sKhoa) {
        List<ModelTreeNode> lstPo = new ArrayList<ModelTreeNode>();
        try {
//            DaoConnect daoconnect = new DaoConnect();
//            Connection conn = null;
//            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call VBSP_IMS_NGHIQUYET11CP.SP_LOAD_POS_TREE(?,?,?,?,?,?)}";
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
        String strStoreproce = "{call VBSP_IMS_NGHIQUYET11CP.SP_GET_DATA_Nhaptaycn_SYNC(?,?,?,?,?,?,?)}";
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
            String strStoreproce = "{call VBSP_IMS_NGHIQUYET11CP.SP_GET_DMKHAC(?,?,?,?)}";
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
            String strStoreproce = "{call VBSP_IMS_NGHIQUYET11CP.SP_GET_DATA_Nhaptaycn01(?,?,?,?,?,?,?,?,?,?,?)}";
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
            String strStoreproce = "{call VBSP_IMS_NGHIQUYET11CP.SP_GET_DATA_Nhaptaycn01_RE(?,?,?,?,?,?,?,?)}";
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
            String strStoreproce = "{call VBSP_IMS_NGHIQUYET11CP.SP_GET_DATA_Nhaptaycn02(?,?,?,?,?,?,?,?)}";
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
            String strStoreproce = "{call VBSP_IMS_NGHIQUYET11CP.SP_GET_DATA_Nhaptaycn02_RE(?,?,?,?,?,?,?,?)}";
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
            String strStoreproce = "{ call VBSP_IMS_NGHIQUYET11CP.SP_SAVE_NHAPTAYCN_01(?,?,?,?,?,?)}";
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
            cs = connection.prepareCall("{call VBSP_IMS_NGHIQUYET11CP.SP_SAVE_Nhaptaycn_02(?, ?, ?, ?, ?)}");
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
        String strStoreproce = "{ ? = call VBSP_IMS_NGHIQUYET11CP.F_CHECK_USER(?) }";

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
        String strStoreproce = "{ ? = call VBSP_IMS_NGHIQUYET11CP.F_CHECK_USER_MAINPOS(?,?) }";

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
        String strStoreproce = "{call VBSP_IMS_NGHIQUYET11CP.SP_GET_DATA_BCQT_SYNC(?,?,?,?,?,?,?)}";
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
    

    public String isCheckPosSend(String type, String Khoa, String UserName, String ngaybc) throws SQLException {
        String pos_cd = "";
        CallableStatement calstatement = null;
        ResultSet reset = null;
        DaoConnect daoconnect = new DaoConnect();
        Connection conn = null;
        conn = daoconnect.getConnect();
//        try {
        //Khoi tao procedure cung voi tham so truyen vao la dau ?
        String strStoreproce = "{?=call VBSP_IMS_NGHIQUYET11CP.F_CHECK_POS_DATA(?,?,?,?)}";
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
            String strStoreproce = "{call VBSP_IMS_NGHIQUYET11CP.SP_GET_DATA_TCTD_B05(?,?,?,?,?,?,?,?)}";
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
            String strStoreproce = "{call VBSP_IMS_NGHIQUYET11CP.SP_GET_DATA_TCTD_B06(?,?,?,?,?,?,?,?)}";
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

    

    public int getPosSendDataLockNhaptaycn(String type, String khoa, String mapgd, String ngay_bc, String tt_khoa) throws SQLException {
        int nPos = 0;
        DaoConnect daoconnect = new DaoConnect();
        Connection conn = null;

        CallableStatement calstatement = null;
        ResultSet reset = null;
//        try {
        //Khoi tao procedure cung voi tham so truyen vao la dau ?
        String strStoreproce = "{?=call VBSP_IMS_NGHIQUYET11CP.F_CHECK_POS_LOCK(?,?,?,?,?)}";
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
            cs = connection.prepareCall("{call VBSP_IMS_NGHIQUYET11CP.SP_OPEN_PGD(?, ?, ?, ?, ?, ?, ?)}");
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
            String strStoreproce = "{call VBSP_IMS_NGHIQUYET11CP.SP_GET_Nhaptaycn_BDD01(?,?,?,?,?,?,?,?)}";
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
            cs = connection.prepareCall("{call VBSP_IMS_NGHIQUYET11CP.SP_SAVE_Nhaptaycn_03(?, ?, ?, ?, ?, ?)}");
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

    

    public List<ListValue> getNguyennhanDm() {
        List<ListValue> lstNNDM = new ArrayList<ListValue>();
        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call VBSP_IMS_NGHIQUYET11CP.SP_GET_NGUYENNHANDM(?,?,?)}";
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
            String strStoreproce = "{call VBSP_IMS_NGHIQUYET11CP.SP_GET_DATA_PHIUT_SANPHAM(?,?,?,?,?,?,?,?,?)}";
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
            String strStoreproce = "{call VBSP_IMS_NGHIQUYET11CP.SP_GET_DATA_PHIUT_PGD(?,?,?,?,?,?)}";
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
            String strStoreproce = "{call VBSP_IMS_NGHIQUYET11CP.SP_GET_DATA_PHIUT_PHANBO(?,?,?,?,?,?,?,?,?)}";
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
            String strStoreproce = "{call VBSP_IMS_NGHIQUYET11CP.SP_GET_DATA_PHIUT_DETAIL(?,?,?,?,?,?,?,?,?,?,?)}";
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
            cs = connection.prepareCall("{call VBSP_IMS_NGHIQUYET11CP.SP_SAVE_DATA_PHIUT(?, ?, ?, ?, ?, ?)}");
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
        String strStoreproce = "{call VBSP_IMS_NGHIQUYET11CP.SP_GET_DATA_NHAPTAYCN_SYNC(?,?,?,?,?,?,?)}";
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
        String strStoreproce = "{call VBSP_IMS_NGHIQUYET11CP.SP_GET_DATA_NHAPTAYCN_SYNC_SMS(?,?,?,?,?,?,?,?)}";
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
            String strStoreproce = "{call VBSP_IMS_NGHIQUYET11CP.SP_GET_DATA_NTMOI01(?,?,?,?,?,?,?,?,?)}";
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
            String strStoreproce = "{call VBSP_IMS_NGHIQUYET11CP.SP_GET_DATA_CN23_01(?,?,?,?,?,?,?,?)}";
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
            String strStoreproce = "{call VBSP_IMS_NGHIQUYET11CP.SP_GET_DATA_CN23_EDIT(?,?,?,?,?,?,?,?,?)}";
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
            cs = connection.prepareCall("{call VBSP_IMS_NGHIQUYET11CP.SP_SAVE_NTMOI_01(?, ?, ?, ?, ?, ? )}");
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
            cs = connection.prepareCall("{call VBSP_IMS_NGHIQUYET11CP.SP_SAVE_CN23_01(?, ?, ?, ?, ?)}");
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
            String strStoreproce = "{call VBSP_IMS_NGHIQUYET11CP.SP_GET_DATA_HANOI01(?,?,?,?,?,?,?,?)}";
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
            cs = connection.prepareCall("{call VBSP_IMS_NGHIQUYET11CP.SP_SAVE_HANOI_01(?, ?, ?, ?, ? )}");
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
            String strStoreproce = "{call VBSP_IMS_NGHIQUYET11CP.SP_GET_DATA_HANOI02(?,?,?,?,?,?,?,?)}";
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
            String strStoreproce = "{call VBSP_IMS_NGHIQUYET11CP.SP_GET_DATA_HANOI03(?,?,?,?,?,?,?,?)}";
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
            cs = connection.prepareCall("{call VBSP_IMS_NGHIQUYET11CP.SP_SAVE_HANOI_02(?, ?, ?, ?, ? )}");
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
            String strStoreproce = "{call VBSP_IMS_NGHIQUYET11CP.SP_GET_DATA_NHAPTAY02(?,?,?,?,?,?,?,?,?)}";
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
            cs = connection.prepareCall("{call VBSP_IMS_NGHIQUYET11CP.SP_SAVE_NHAPTAY02(?, ?, ?, ?, ? ,?)}");
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
            String strStoreproce = "{call VBSP_IMS_NGHIQUYET11CP.SP_GET_STATUS_SEND_CN(?,?,?,?,?)}";
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
            String strStoreproce = "{call VBSP_IMS_NGHIQUYET11CP.SP_LOAD_DATA_HSSV_HTLS(?,?,?,?,?,?,?,?)}";
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
            cs = connection.prepareCall("{call VBSP_IMS_NGHIQUYET11CP.SP_SAVE_HSSV_01(?, ?, ?, ?, ? ,?)}");
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
            String strStoreproce = "{call VBSP_IMS_NGHIQUYET11CP.SP_GET_DATA_USER01(?,?,?,?,?,?,?,?)}";
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
            cs = connection.prepareCall("{call VBSP_IMS_NGHIQUYET11CP.SP_SAVE_USER_01(?, ?, ?, ?, ? )}");
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
            String strStoreproce = "{call VBSP_IMS_NGHIQUYET11CP.SP_GET_DATA_TRAIPHIEU01(?,?,?,?,?,?,?,?,?)}";
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
            cs = connection.prepareCall("{call VBSP_IMS_NGHIQUYET11CP.SP_SAVE_TPLS_01(?, ?, ?, ?, ?,? )}");
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
        String strStoreproce = "{ ? = call VBSP_IMS_NGHIQUYET11CP.F_CHECK_GRP(?,?) }";

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
            String strStoreproce = "{call VBSP_IMS_NGHIQUYET11CP.SP_GET_DATA_BC_DETAI(?,?,?,?,?,?)}";
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
            String strStoreproce = "{call VBSP_IMS_NGHIQUYET11CP.SP_GET_DATA_TB02(?,?,?,?,?,?,?,?)}";
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
            cs = connection.prepareCall("{call VBSP_IMS_NGHIQUYET11CP.SP_SAVE_TB02(?, ?, ?, ?, ? )}");
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
            String strStoreproce = "{call VBSP_IMS_NGHIQUYET11CP.SP_GET_DATA_BDP01(?,?,?,?,?,?,?,?,?)}";
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
            cs = connection.prepareCall("{call VBSP_IMS_NGHIQUYET11CP.SP_SAVE_BDP01(?, ?, ?, ?, ? ,?)}");
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
            String strStoreproce = "{call VBSP_IMS_NGHIQUYET11CP.SP_GET_DATA_SMS01(?,?,?,?,?,?,?,?,?,?)}";
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
            cs = connection.prepareCall("{call VBSP_IMS_NGHIQUYET11CP.SP_SAVE_Sms02(?, ?, ?, ?, ? ,?, ?)}");
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
            cs = connection.prepareCall("{call VBSP_IMS_NGHIQUYET11CP.SP_SAVE_Sms03(?, ?, ?, ?, ? ,?, ?)}");
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
            cs = connection.prepareCall("{call VBSP_IMS_NGHIQUYET11CP.SP_SAVE_Sms01(?, ?, ?, ?, ? ,?)}");
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
            cs = connection.prepareCall("{call VBSP_IMS_NGHIQUYET11CP.SP_SAVE_HANOI03(?, ?, ?, ?, ? ,?, ?)}");
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
            String strStoreproce = "{call VBSP_IMS_NGHIQUYET11CP.SP_GET_DATA_QLDB01(?,?,?,?,?,?,?,?)}";
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
            cs = connection.prepareCall("{call VBSP_IMS_NGHIQUYET11CP.SP_SAVE_QLDB_01(?, ?, ?, ?, ? ,?, ?)}");
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

    public List<ListValue> getDanhMuc(String username, String type, String capbc) {
        List<ListValue> lstDMNgNhan = new ArrayList<ListValue>();
        try {

            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call VBSP_IMS_NGHIQUYET11CP.SP_GET_LOV(?, ?, ?,?,?, ?)}";
            ResultSet reset = null;
            try {
                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                calstatement.setString(1, username);
                calstatement.setString(2, type);

                calstatement.registerOutParameter(3, oracle.jdbc.OracleTypes.NUMBER);
                calstatement.registerOutParameter(4, oracle.jdbc.OracleTypes.VARCHAR);
                calstatement.registerOutParameter(5, oracle.jdbc.OracleTypes.CURSOR);
                calstatement.setString(6, capbc);
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

    //     1: lưu xác nhận lại; 2: lưu phân loại ht
    public boolean saveNQ11CP_001(String username, String mapgd, String ngaybc, List<QT_DULIEU_NT> lstData, String phanloai) 
            throws SQLException {
        Connection connection = new DaoConnect().getConnect();
        Object array[] = lstData.toArray();
        ArrayDescriptor des = ArrayDescriptor
                .createDescriptor(QT_DULIEU_NT.ORACLE_TABLE_TYPE, connection);
        ARRAY array_to_pass = new ARRAY(des, connection, array);
        CallableStatement cs = null;
        try {
            cs = connection.prepareCall("{call VBSP_IMS_NGHIQUYET11CP.SP_SAVE_GIAM_LAI_NQ11CP_001(?, ?, ?, ?, ?)}");
            cs.setString(1, username);
            cs.setString(2, mapgd);
            cs.setString(3, ngaybc);
            cs.setArray(4, array_to_pass);
            cs.setString(5, phanloai);
            cs.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Loi trong ham saveNQ11CP_001 " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " saveNQ11CP_001 -> " + e.getMessage());
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
    
    public static void main(String[] args) throws Exception {
        DuLieuNTService service = new DuLieuNTService();
        ArrayList<NQ11cpModel> lstData = service.getDataNQ11CP("000601", "20220228", "03",
                "060102", "0000000");
        new NQ11CP_001().margerData(lstData,"0","0000","1");
        
        String s= "000000_1234567";
        String k = s.split("_")[1];

        List<String> pos = new ArrayList();

        pos.add("001002");
        pos.add("001005");
        pos.add("001003");
        pos.add("001002");
        pos.add("001004");

//        DaoNghiquyet11cp.newInstance().getPosByName(pos);
        Connection conn = new DaoConnect().getConnect();
        new DaoNghiquyet11cp().getReportParmams(conn, "NQ11CP_001", "P0631", "1");
        if (conn != null) {
            conn.close();
        }
    }
}
