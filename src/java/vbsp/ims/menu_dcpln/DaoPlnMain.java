/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.menu_dcpln;

import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
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
import vbsp.ims.define.GenericResult;
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
public class DaoPlnMain {

    public static DaoPlnMain newInstance() {
        return new DaoPlnMain();
    }

    public List<ListValue> getAllNhaptaycn(String capbc) {
        List<ListValue> lstAllBcqt = new ArrayList<ListValue>();
        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call VBSP_IMS_DCPHANLOAINO.SP_LOAD_ALL_BCQT(?,?,?, ?)}";
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
            String strStoreproce = "{call VBSP_IMS_CHTRINHCN.SP_LOAD_ALL_BCQT_SUB(?,?,?)}";
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
            String strStoreproce = "{call VBSP_IMS_DCPHANLOAINO.SP_LOAD_POS_TREE_BCQT(?,?,?,?,?,?)}";
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
        String strStoreproce = "{call VBSP_IMS_DCPHANLOAINO.SP_GET_DATA_Nhaptaycn_SYNC(?,?,?,?,?,?,?)}";
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
            String strStoreproce = "{call VBSP_IMS_DCPHANLOAINO.SP_GET_DMKHAC(?,?,?,?)}";
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
            String strStoreproce = "{?=call VBSP_IMS_DCPHANLOAINO.f_get_casa_teler(?,?,?,?)}";
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

    public int checkUser(String UserName) throws SQLException {
        int _retVal = 0;
        Connection conn = new DaoConnect().getConnect();
        CallableStatement calstatement = null;
        ResultSet reset = null;
        //Khoi tao procedure cung voi tham so truyen vao la dau ?
        String strStoreproce = "{ ? = call VBSP_IMS_DCPHANLOAINO.F_CHECK_USER(?) }";

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
        String strStoreproce = "{ ? = call VBSP_IMS_DCPHANLOAINO.F_CHECK_USER_MAINPOS(?,?) }";

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

    public Map<String, String> getPosByName(List<String> lstMaPGD) throws SQLException {
        Map<String, String> mapData = new HashMap<String, String>();
        DaoConnect daoconnect = new DaoConnect();
        Connection conn = null;
        conn = daoconnect.getConnect();
        CallableStatement calstatement = null;
        ResultSet reset = null;
//        try {
        //Khoi tao procedure cung voi tham so truyen vao la dau ?
        String strStoreproce = "{call VBSP_IMS_DCPHANLOAINO.SP_GET_POS_BY_NAME(?,?)}";
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
        String strStoreproce = "{?=call VBSP_IMS_DCPHANLOAINO.F_CHECK_POS_DATA(?,?,?,?)}";
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

        DaoPlnMain.newInstance().getPosByName(pos);
        Connection conn = new DaoConnect().getConnect();
        new DaoPlnMain().getReportParmamsNhaptaycn(conn, "PHIUT_001", "P0631", "1");
        if (conn != null) {
            conn.close();
        }
    }

    public List<ReportParam> getReportParmamsNhaptaycn(Connection conn, String sKhoa, String sUserName, String sGrade) {
        ArrayList<ReportParam> report_param_list = new ArrayList<>();

        try {
//            DaoConnect daoconnect = new DaoConnect();
//            Connection conn ;
//            conn = daoconnect.getConnect();
            CallableStatement calstatement;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call VBSP_IMS_DCPHANLOAINO.SP_LOAD_PARA_Nhaptaycn(?, ?, ?, ?, ?,?,?)}";
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
        String strStoreproce = "{?=call VBSP_IMS_DCPHANLOAINO.F_CHECK_POS_LOCK(?,?,?,?,?)}";
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
            cs = connection.prepareCall("{call VBSP_IMS_DCPHANLOAINO.SP_OPEN_PGD(?, ?, ?, ?, ?, ?, ?)}");
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

    public List<String> getAllPosUser(String username, String khoa) throws SQLException {
        List<String> lstData = new ArrayList<>();
        DaoConnect daoconnect = new DaoConnect();
        Connection conn = null;
        conn = daoconnect.getConnect();
        CallableStatement calstatement = null;
        ResultSet reset = null;
//        try {
        //Khoi tao procedure cung voi tham so truyen vao la dau ?
        String strStoreproce = "{call VBSP_IMS_DCPHANLOAINO.SP_LOAD_ALL_POS(?,?,?,?,?)}";
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
            String strStoreproce = "{call VBSP_IMS_DCPHANLOAINO.SP_GET_NGUYENNHANDM(?,?,?)}";
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
    
    public List<QT_DULIEU_NT> getDataDMBC(Connection conn, String sUser, String sGrade, String sNhadt) {
        List<QT_DULIEU_NT> lstBcqt_NT = new ArrayList<QT_DULIEU_NT>();
        try {
//            DaoConnect daoconnect = new DaoConnect();
//            Connection conn = null;
//            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call VBSP_IMS_DCPHANLOAINO.SP_GET_DATA_BC_DETAI(?,?,?,?,?,?)}";
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

    public String getPosCd(String username) throws SQLException {
        String pos_cd = "";
        CallableStatement calstatement = null;
        ResultSet reset = null;
        DaoConnect daoconnect = new DaoConnect();
        Connection conn = null;
        conn = daoconnect.getConnect();
//        try {
        //Khoi tao procedure cung voi tham so truyen vao la dau ?
        String strStoreproce = "{?=call VBSP_IMS_DCPHANLOAINO.F_GET_POSCD(?)}";
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

    public String getQuery(String strSave_id, Connection connect) {
        String strQuery = "";
        if (strSave_id.length() == 0 || strSave_id == null) {
            return null;
        }

        // Connection connect = null;
        CallableStatement calstatement = null;
        //Khoi tao function se tra ra du lieu la kieu gi
        String strStoreproce = "{?=call VBSP_IMS_DCPHANLOAINO.f_get_query(?,?,?)}";

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
            String strStoreproce = "{call VBSP_IMS_DCPHANLOAINO.SP_GET_DATA_COVID_04(?,?,?,?,?,?,?,?)}";
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
            String strStoreproce = "{call VBSP_IMS_DCPHANLOAINO.SP_GET_LOCK_STATUS(?,?,?,?,?)}";
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

    public ArrayList<POSModel> getPosExportFile(String posCode, String KHOA) {
        List<String> lstData = new ArrayList<>();
        ArrayList<POSModel> posList = new ArrayList<>();
        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            ResultSet reset = null;
//        try {
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call VBSP_IMS_DCPHANLOAINO.SP_LOAD_ALL_POS(?,?,?,?,?)}";
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

}
