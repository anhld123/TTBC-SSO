/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.nghiquyet11cp;

import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;
import vbsp.ims.action.ktktnb.DULIEU_NT_TQ;
import vbsp.ims.bcqt.model.QT_DULIEU_NT;
import vbsp.ims.dao.DaoConnect;
import vbsp.ims.dao.DaoLoadReportParams;
import vbsp.ims.loadparams.Combo;
import vbsp.ims.loadparams.ReportParam;
import vbsp.ims.log.CoreLogger;
import vbsp.ims.model.ModelTreeNode;
import vbsp.ims.report.fast.ListValue;
import vbsp.ims.define.GenericResult;
import vbsp.ims.model.khnv.POSModel;
import vbsp.ims.restapi.ListPosCode;

/**
 *
 * @author LION
 */
public class DaoNghiquyet11cp {

    public static DaoNghiquyet11cp newInstance() {
        return new DaoNghiquyet11cp();
    }

    public List<ListValue> getAllBaocao(String capbc, String module) {
        List<ListValue> lstAllBcqt = new ArrayList<>();
        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call VBSP_IMS_NGHIQUYET11CP.SP_LOAD_ALL_BC(?,?,?, ?, ?)}";
            ResultSet reset = null;

            try {
                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                calstatement.registerOutParameter(1, oracle.jdbc.OracleTypes.NUMBER);
                calstatement.registerOutParameter(2, oracle.jdbc.OracleTypes.VARCHAR);
                calstatement.registerOutParameter(3, oracle.jdbc.OracleTypes.CURSOR);
                calstatement.setString(4, capbc);
                calstatement.setString(5, module);
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

    public List<QT_DULIEU_NT> getDataKH04(Connection conn, String sKhoa, String sNgaybc, String sUser,
            String sGrade, List<String> lstArrPoscd, String nghiepvu) {
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
            String strStoreproce = "{call VBSP_IMS_NGHIQUYET11CP.SP_GET_DATA_KH04(?,?,?,?,?,?,?,?, ?)}";
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
                calstatement.setString(9, nghiepvu);
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
                CoreLogger.error(this.getClass().getName() + " getDataKH04 -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham getDataKH04 " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " getDataKH04 -> " + e.getMessage());
        }
        return lstBcqt_NT;
    }

    public List<QT_DULIEU_NT> getData_Traiphieu_001(Connection conn, String sKhoa, String sNgaybc, String sUser,
            String sGrade, List<String> lstArrPoscd, String sanphan, String kyhan) {
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
            String strStoreproce = "{call VBSP_IMS_NGHIQUYET11CP.SP_GET_DATA_TRAIPHIEU_001(?,?,?,?,?,?,?,?, ?, ?)}";
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
                calstatement.setString(9, sanphan);
                calstatement.setString(10, kyhan);
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
                CoreLogger.error(this.getClass().getName() + " getDataKH04 -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham getDataKH04 " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " getDataKH04 -> " + e.getMessage());
        }
        return lstBcqt_NT;
    }

    public GenericResult<String> cancelAssign(String skhoa, String smadgx, String sngaybc) throws SQLException {
        Connection connection = new DaoConnect().getConnect();
        CallableStatement cs = null;
        try {
            cs = connection.prepareCall("{call VBSP_IMS_NGHIQUYET11CP.SP_UNLOCK_MSTS(?, ?, ? ,?, ? )}");
            cs.setString(1, skhoa);
            cs.setString(2, smadgx);
            cs.setString(3, sngaybc);
            cs.registerOutParameter(4, oracle.jdbc.OracleTypes.NUMBER);
            cs.registerOutParameter(5, oracle.jdbc.OracleTypes.VARCHAR);
            cs.execute();

            //Lay ma loi neu co
            int errorCode = cs.getInt(4);
            String errorMessage = cs.getString(5);

            if (errorCode == 0) {
                return (new GenericResult<String>()).Success("Success");
            } else {
                return (new GenericResult<String>()).Fail(errorMessage, errorCode);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Loi trong ham cancelAssign " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " cancelAssign -> " + e.getMessage());
            return (new GenericResult<String>()).Fail(e.getMessage(), e.getErrorCode());
        } finally {
            if (cs != null) {
                cs.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    public GenericResult<String> unlock_c3_THTK(String skhoa, String smadgx, String spos_flag, String sngaybc, String skye) throws SQLException {
        Connection connection = new DaoConnect().getConnect();
        CallableStatement cs = null;
        try {
            cs = connection.prepareCall("{call VBSP_IMS_NGHIQUYET11CP.SP_UNLOCK_THTK_C3(?, ?, ?, ? ,?, ?, ? )}");
            cs.setString(1, skhoa);
            cs.setString(2, smadgx);
            cs.setString(4, sngaybc);
            cs.setString(3, spos_flag);
            cs.setString(5, skye);
            cs.registerOutParameter(6, oracle.jdbc.OracleTypes.NUMBER);
            cs.registerOutParameter(7, oracle.jdbc.OracleTypes.VARCHAR);
            cs.execute();

            //Lay ma loi neu co
            int errorCode = cs.getInt(6);
            String errorMessage = cs.getString(7);

            if (errorCode == 0) {
                return (new GenericResult<String>()).Success("Success");
            } else {
                return (new GenericResult<String>()).Fail(errorMessage, errorCode);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Loi trong ham cancelAssign " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " cancelAssign -> " + e.getMessage());
            return (new GenericResult<String>()).Fail(e.getMessage(), e.getErrorCode());
        } finally {
            if (cs != null) {
                cs.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    public GenericResult<String> unlock_baoso3_c2(String skhoa, String smaxa, String smato, String sngaybc) throws SQLException {
        Connection connection = new DaoConnect().getConnect();
        CallableStatement cs = null;
        try {
            cs = connection.prepareCall("{call VBSP_IMS_NGHIQUYET11CP.SP_UNLOCK_BAOSO3_C2(?, ?, ?, ? ,?, ?)}");
            cs.setString(1, skhoa);
            cs.setString(2, smaxa);
            cs.setString(3, smato);
            cs.setString(4, sngaybc);
            cs.registerOutParameter(5, oracle.jdbc.OracleTypes.NUMBER);
            cs.registerOutParameter(6, oracle.jdbc.OracleTypes.VARCHAR);
            cs.execute();

            //Lay ma loi neu co
            int errorCode = cs.getInt(5);
            String errorMessage = cs.getString(6);

            if (errorCode == 0) {
                return (new GenericResult<String>()).Success("Success");
            } else {
                return (new GenericResult<String>()).Fail(errorMessage, errorCode);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Loi trong ham cancelAssign " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " cancelAssign -> " + e.getMessage());
            return (new GenericResult<String>()).Fail(e.getMessage(), e.getErrorCode());
        } finally {
            if (cs != null) {
                cs.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    public GenericResult<String> lock_TDKT_2024(String skhoa, String smacn, String spos_flag, String sngaybc) throws SQLException {
        Connection connection = new DaoConnect().getConnect();
        CallableStatement cs = null;
        try {
            cs = connection.prepareCall("{call VBSP_IMS_NGHIQUYET11CP.SP_LOCK_TDKT_2024(?, ?, ?, ? ,?, ?)}");
            cs.setString(1, skhoa);
            cs.setString(2, smacn);
            cs.setString(3, spos_flag);
            cs.setString(4, sngaybc);
            cs.registerOutParameter(5, oracle.jdbc.OracleTypes.NUMBER);
            cs.registerOutParameter(6, oracle.jdbc.OracleTypes.VARCHAR);
            cs.execute();

            //Lay ma loi neu co
            int errorCode = cs.getInt(5);
            String errorMessage = cs.getString(6);

            if (errorCode == 0) {
                return (new GenericResult<String>()).Success("Success");
            } else {
                return (new GenericResult<String>()).Fail(errorMessage, errorCode);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Loi trong ham cancelAssign " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " cancelAssign -> " + e.getMessage());
            return (new GenericResult<String>()).Fail(e.getMessage(), e.getErrorCode());
        } finally {
            if (cs != null) {
                cs.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    public List<QT_DULIEU_NT> getData_load_c3(Connection conn, String sNgaybc, String sKhoa, String sMacn) {
        List<QT_DULIEU_NT> lstBcqt_NT = new ArrayList<QT_DULIEU_NT>();
        try {
            CallableStatement calstatement = null;
            String strStoreproce = "{call VBSP_IMS_NGHIQUYET11CP.SP_GET_DATA_MSTS_C3(?,?,?,?,?,?)}";
            ResultSet reset = null;

            try {
                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                calstatement.registerOutParameter(4, oracle.jdbc.OracleTypes.NUMBER);
                calstatement.registerOutParameter(5, oracle.jdbc.OracleTypes.VARCHAR);
                calstatement.registerOutParameter(6, oracle.jdbc.OracleTypes.CURSOR);
                calstatement.setString(2, sKhoa);
                calstatement.setString(3, sMacn);
                calstatement.setString(1, sNgaybc);
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
                    value.setD1(reset.getString("D1"));
                    value.setD2(reset.getString("D2"));
                    value.setD3(reset.getString("D3"));
                    value.setD4(reset.getString("D4"));
                    value.setD5(reset.getString("D5"));
                    value.setD6(reset.getString("D6"));
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

    public List<QT_DULIEU_NT> getData_THTK_c3(Connection conn, String sNgaybc, String sKhoa, String sMacn, String sPod_Flag) {
        List<QT_DULIEU_NT> lstBcqt_NT = new ArrayList<QT_DULIEU_NT>();
        try {
            CallableStatement calstatement = null;
            String strStoreproce = "{call VBSP_IMS_NGHIQUYET11CP.SP_GET_DATA_THTK_C3(?,?,?,?,?,?,?)}";
            ResultSet reset = null;

            try {
                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                calstatement.registerOutParameter(5, oracle.jdbc.OracleTypes.NUMBER);
                calstatement.registerOutParameter(6, oracle.jdbc.OracleTypes.VARCHAR);
                calstatement.registerOutParameter(7, oracle.jdbc.OracleTypes.CURSOR);
                calstatement.setString(2, sKhoa);
                calstatement.setString(3, sMacn);
                calstatement.setString(1, sNgaybc);
                calstatement.setString(4, sPod_Flag);
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
                    value.setD1(reset.getString("D1"));
                    value.setD2(reset.getString("D2"));
                    value.setD3(reset.getString("D3"));
                    value.setD4(reset.getString("D4"));
                    value.setD5(reset.getString("D5"));
                    value.setD6(reset.getString("D6"));
                    value.setD7(reset.getString("D7"));
                    value.setD8(reset.getString("D8"));
                    value.setD9(reset.getString("D9"));
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
    
    public List<QT_DULIEU_NT> getDataPlnTW(Connection conn, String sNgaybc, String sKhoa, String sMacn, String sPod_Flag) {
        List<QT_DULIEU_NT> lstBcqt_NT = new ArrayList<QT_DULIEU_NT>();
        try {
            CallableStatement calstatement = null;
            String strStoreproce = "{call VBSP_IMS_DCPHANLOAINO.SP_GET_DATA_PLN_TW(?,?,?,?,?,?,?)}";
            ResultSet reset = null;

            try {
                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                calstatement.registerOutParameter(5, oracle.jdbc.OracleTypes.NUMBER);
                calstatement.registerOutParameter(6, oracle.jdbc.OracleTypes.VARCHAR);
                calstatement.registerOutParameter(7, oracle.jdbc.OracleTypes.CURSOR);
                calstatement.setString(2, sKhoa);
                calstatement.setString(3, sMacn);
                calstatement.setString(1, sNgaybc);
                calstatement.setString(4, sPod_Flag);
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
    public List<QT_DULIEU_NT> getCheck_user(Connection conn, String sUsername) {
        List<QT_DULIEU_NT> lstBcqt_NT = new ArrayList<QT_DULIEU_NT>();
        try {
            CallableStatement calstatement = null;
            String strStoreproce = "{call VBSP_IMS_NGHIQUYET11CP.SP_CHECK_USERNAME(?,?,?,?)}";
            ResultSet reset = null;

            try {
                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                calstatement.registerOutParameter(2, oracle.jdbc.OracleTypes.NUMBER);
                calstatement.registerOutParameter(3, oracle.jdbc.OracleTypes.VARCHAR);
                calstatement.registerOutParameter(4, oracle.jdbc.OracleTypes.CURSOR);
                calstatement.setString(1, sUsername);
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
                    value.setD1(reset.getString("D1"));
                    value.setD2(reset.getString("D2"));
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

    public List<QT_DULIEU_NT> getTDKT_2024(Connection conn, String sNgaybc, String sKhoa, String sPosCd) {
        List<QT_DULIEU_NT> lstBcqt_NT = new ArrayList<QT_DULIEU_NT>();
        try {
            CallableStatement calstatement = null;
            String strStoreproce = "{call VBSP_IMS_NGHIQUYET11CP.SP_GET_DATA_TDKT2024(?,?,?,?,?,?)}";
            ResultSet reset = null;

            try {
                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                calstatement.registerOutParameter(4, oracle.jdbc.OracleTypes.NUMBER);
                calstatement.registerOutParameter(5, oracle.jdbc.OracleTypes.VARCHAR);
                calstatement.registerOutParameter(6, oracle.jdbc.OracleTypes.CURSOR);
                calstatement.setString(1, sNgaybc);
                calstatement.setString(2, sKhoa);
                calstatement.setString(3, sPosCd);
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
                    value.setD21(reset.getString("D21"));
                    value.setD22(reset.getString("D22"));
                    value.setD23(reset.getString("D23"));
                    value.setD24(reset.getString("D24"));
                    value.setD25(reset.getString("D25"));
                    value.setD26(reset.getString("D26"));
                    value.setD27(reset.getString("D27"));
                    value.setD28(reset.getString("D28"));
                    value.setD29(reset.getString("D29"));
                    value.setD30(reset.getString("D30"));
                    value.setD31(reset.getString("D31"));
                    value.setD32(reset.getString("D32"));
                    value.setD33(reset.getString("D33"));
                    value.setD34(reset.getString("D34"));
                    value.setD35(reset.getString("D35"));
                    value.setD36(reset.getString("D36"));
                    value.setD37(reset.getString("D37"));
                    value.setD38(reset.getString("D38"));
                    value.setD39(reset.getString("D39"));
                    value.setD40(reset.getString("D40"));
                    value.setD41(reset.getString("D41"));
                    value.setD42(reset.getString("D42"));
                    value.setD43(reset.getString("D43"));
                    value.setD44(reset.getString("D44"));
                    value.setD45(reset.getString("D45"));
                    value.setD46(reset.getString("D46"));
                    value.setD47(reset.getString("D47"));
                    value.setD48(reset.getString("D48"));
                    value.setD49(reset.getString("D49"));
                    value.setD50(reset.getString("D50"));
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

    public List<QT_DULIEU_NT> getData_baoso3(Connection conn, String sNgaybc, String sKhoa, String sUser, String sPosCd, String sMaxa, String sPos_Flag, String sMato) {
        List<QT_DULIEU_NT> lstBcqt_NT = new ArrayList<QT_DULIEU_NT>();
        try {
            CallableStatement calstatement = null;
            String strStoreproce = "{call VBSP_IMS_NGHIQUYET11CP.SP_GET_DATA_BAOSO3(?,?,?,?,?,?,?,?,?,?)}";
            ResultSet reset = null;

            try {
                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                calstatement.registerOutParameter(8, oracle.jdbc.OracleTypes.NUMBER);
                calstatement.registerOutParameter(9, oracle.jdbc.OracleTypes.VARCHAR);
                calstatement.registerOutParameter(10, oracle.jdbc.OracleTypes.CURSOR);
                calstatement.setString(1, sNgaybc);
                calstatement.setString(2, sKhoa);
                calstatement.setString(3, sUser);
                calstatement.setString(4, sPosCd);
                calstatement.setString(5, sMaxa);
                calstatement.setString(6, sPos_Flag);
                calstatement.setString(7, sMato);
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
//                    value.setD21(reset.getString("D21"));
//                    value.setD22(reset.getString("D22"));
//                    value.setD23(reset.getString("D23"));
//                    value.setD24(reset.getString("D24"));
//                    value.setD25(reset.getString("D25"));
//                    value.setD26(reset.getString("D26"));
//                    value.setD27(reset.getString("D27"));
//                    value.setD28(reset.getString("D28"));
//                    value.setD29(reset.getString("D29"));
//                    value.setD30(reset.getString("D30"));
//                    value.setD31(reset.getString("D31"));
//                    value.setD32(reset.getString("D32"));
//                    value.setD33(reset.getString("D33"));
//                    value.setD34(reset.getString("D34"));
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

    public List<QT_DULIEU_NT> getLock_Baoso3_2(Connection conn, String khoa, String ngaybc, String sUser, String sPoscd, String smaxa, String smato) {
        List<QT_DULIEU_NT> lstBcqt_NT = new ArrayList<QT_DULIEU_NT>();
        try {
            CallableStatement calstatement = null;
            String strStoreproce = "{call VBSP_IMS_NGHIQUYET11CP.GET_DATA_LOCK_BAOSO3_C2(?,?,?,?,?,?,?,?,?)}";
            ResultSet reset = null;

            try {
                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                calstatement.registerOutParameter(7, oracle.jdbc.OracleTypes.NUMBER);
                calstatement.registerOutParameter(8, oracle.jdbc.OracleTypes.VARCHAR);
                calstatement.registerOutParameter(9, oracle.jdbc.OracleTypes.CURSOR);
                calstatement.setString(1, khoa);
                calstatement.setString(2, ngaybc);
                calstatement.setString(3, sUser);
                calstatement.setString(4, sPoscd);
                calstatement.setString(5, smaxa);
                calstatement.setString(6, smato);

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
                    value.setD1(reset.getString("D1"));
                    value.setD2(reset.getString("D2"));
                    value.setD3(reset.getString("D3"));
                    value.setD4(reset.getString("D4"));
                    value.setD5(reset.getString("D5"));
                    value.setD6(reset.getString("D6"));
                    value.setD7(reset.getString("D7"));
                    value.setD8(reset.getString("D8"));
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

    public List<QT_DULIEU_NT> seach_StatusMSTS(Connection conn, String sNgaybc, String sKhoa, String sMacn) {
        List<QT_DULIEU_NT> lstBcqt_NT = new ArrayList<QT_DULIEU_NT>();
        try {
            CallableStatement calstatement = null;
            String strStoreproce = "{call VBSP_IMS_NGHIQUYET11CP.SP_GET_DATA_SEACH_STATUS_MSTS(?,?,?,?,?,?)}";
            ResultSet reset = null;

            try {
                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                calstatement.registerOutParameter(4, oracle.jdbc.OracleTypes.NUMBER);
                calstatement.registerOutParameter(5, oracle.jdbc.OracleTypes.VARCHAR);
                calstatement.registerOutParameter(6, oracle.jdbc.OracleTypes.CURSOR);
                calstatement.setString(2, sKhoa);
                calstatement.setString(3, sMacn);
                calstatement.setString(1, sNgaybc);
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
                    value.setD1(reset.getString("D1"));
                    value.setD2(reset.getString("D2"));
                    value.setD3(reset.getString("D3"));
                    value.setD4(reset.getString("D4"));
                    value.setD5(reset.getString("D5"));
                    value.setD6(reset.getString("D6"));
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

    public List<QT_DULIEU_NT> getData_Muasam_2024(Connection conn, String sKhoa, String sNgaybc, String sUser,
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
            String strStoreproce = "{call VBSP_IMS_NGHIQUYET11CP.SP_GET_DATA_MSTS_2024(?,?,?,?,?,?,?,?)}";
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
                    value.setKHOA(reset.getString("KHOA"));
                    value.setTHUTU(reset.getInt("THUTU"));
                    value.setTT_HIENTHI(reset.getString("TT_HIENTHI"));
                    value.setMA(reset.getString("MA"));
                    value.setTEN(reset.getString("TEN"));
                    value.setNGAYBC(reset.getDate("NGAYBC"));
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

    public List<ListPosCode> getData_Taisan_2024(String sKhoa, String sTrangthai) {
        List<ListPosCode> lstBcqt_NT = new ArrayList<ListPosCode>();
        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call VBSP_IMS_NGHIQUYET11CP.SP_GET_DATA_TAISAN(?,?,?,?,?)}";
            ResultSet reset = null;

            try {
                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                calstatement.setString(1, sKhoa);
                calstatement.setString(2, sTrangthai);
                calstatement.registerOutParameter(3, oracle.jdbc.OracleTypes.VARCHAR);
                calstatement.registerOutParameter(4, oracle.jdbc.OracleTypes.NUMBER);
                calstatement.registerOutParameter(5, oracle.jdbc.OracleTypes.CURSOR);
                calstatement.execute();
                reset = (ResultSet) calstatement.getObject(5);

                while (reset.next()) {
                    ListPosCode value = new ListPosCode();
                    value.setAddress(reset.getString("THUTU"));
                    value.setPosCode(reset.getString("MOTA"));
                    value.setPosName(reset.getString("GIATRI"));
                    lstBcqt_NT.add(value);
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
                CoreLogger.error(this.getClass().getName() + " getDataKH04 -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham getDataKH04 " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " getDataKH04 -> " + e.getMessage());
        }
        return lstBcqt_NT;
    }

    public boolean saveNQ11CP_KH04(String khoa, String username, String capbc, String ngaybc, List<QT_DULIEU_NT> lstData, List<String> lstArrPoscd, String nghiepvu) throws SQLException {
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
            cs = connection.prepareCall("{call VBSP_IMS_NGHIQUYET11CP.SP_SAVE_KH04(?, ?, ?, ?, ? ,?)}");
            cs.setString(1, khoa);
            cs.setString(2, username);
            cs.setString(3, capbc);
            cs.setString(4, ngaybc);
            cs.setArray(5, array_to_pass);
            cs.setArray(6, oracle_arrayPos);
            cs.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Loi trong ham saveNQ11CP_KH04 " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " saveNQ11CP_KH04 -> " + e.getMessage());
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

    public boolean saveNQ11CP_01KEHOACH(String username, String mapgd, String ngaybc, List<QT_DULIEU_NT> lstData)
            throws SQLException {
        Connection connection = new DaoConnect().getConnect();
        Object array[] = lstData.toArray();
        ArrayDescriptor des = ArrayDescriptor
                .createDescriptor(QT_DULIEU_NT.ORACLE_TABLE_TYPE, connection);
        ARRAY array_to_pass = new ARRAY(des, connection, array);
        CallableStatement cs = null;
        try {
            cs = connection.prepareCall("{call VBSP_IMS_NGHIQUYET11CP.SP_SAVE_01KEHOACH_NQ11CP(?, ?, ?, ?)}");
            cs.setString(1, username);
            cs.setString(2, mapgd);
            cs.setString(3, ngaybc);
            cs.setArray(4, array_to_pass);
            cs.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Loi trong ham saveNQ11CP_01KEHOACH " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " saveNQ11CP_01KEHOACH -> " + e.getMessage());
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

    public boolean saveCIC_Local(String username, String mapgd, String ngaybc, List<QT_DULIEU_NT> lstData)
            throws SQLException {
        Connection connection = new DaoConnect().getConnect();
        Object array[] = lstData.toArray();
        ArrayDescriptor des = ArrayDescriptor
                .createDescriptor(QT_DULIEU_NT.ORACLE_TABLE_TYPE, connection);
        ARRAY array_to_pass = new ARRAY(des, connection, array);
        CallableStatement cs = null;
        try {
            cs = connection.prepareCall("{call VBSP_IMS_NGHIQUYET11CP.SP_SAVE_CIC_LOCAL(?, ?, ?, ?)}");
            cs.setString(1, username);
            cs.setString(2, mapgd);
            cs.setString(3, ngaybc);
            cs.setArray(4, array_to_pass);
            cs.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Loi trong ham saveCIC_Local " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " saveCIC_Local -> " + e.getMessage());
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

    public boolean saveMs13aKhoanh_Local(String username, String mapgd, String ngaybc, List<QT_DULIEU_NT> lstData)
            throws SQLException {
        Connection connection = new DaoConnect().getConnect();
        Object array[] = lstData.toArray();
        ArrayDescriptor des = ArrayDescriptor
                .createDescriptor(QT_DULIEU_NT.ORACLE_TABLE_TYPE, connection);
        ARRAY array_to_pass = new ARRAY(des, connection, array);
        CallableStatement cs = null;
        try {
            cs = connection.prepareCall("{call VBSP_IMS_NGHIQUYET11CP.SP_SAVE_MS13A_KHOANH(?, ?, ?, ?)}");
            cs.setString(1, username);
            cs.setString(2, mapgd);
            cs.setString(3, ngaybc);
            cs.setArray(4, array_to_pass);
            cs.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Loi trong ham SP_SAVE_MS13A_KHOANH " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " SP_SAVE_MS13A_KHOANH -> " + e.getMessage());
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

    public boolean saveNQ11CP_04KEHOACH(String username, String mapgd, String ngaybc, List<QT_DULIEU_NT> lstData, String nghiepvu)
            throws SQLException {
        Connection connection = new DaoConnect().getConnect();
        Object array[] = lstData.toArray();
        ArrayDescriptor des = ArrayDescriptor
                .createDescriptor(QT_DULIEU_NT.ORACLE_TABLE_TYPE, connection);
        ARRAY array_to_pass = new ARRAY(des, connection, array);
        CallableStatement cs = null;
        try {
            cs = connection.prepareCall("{call VBSP_IMS_NGHIQUYET11CP.SP_SAVE_04KEHOACH_NQ11CP(?, ?, ?, ?, ?)}");
            cs.setString(1, username);
            cs.setString(2, mapgd);
            cs.setString(3, ngaybc);
            cs.setArray(4, array_to_pass);
            cs.setString(5, nghiepvu);
            cs.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Loi trong ham saveNQ11CP_04KEHOACH " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " saveNQ11CP_04KEHOACH -> " + e.getMessage());
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

    public boolean saveNQ11CP_02SK(String username, String mapgd, String ngaybc, List<QT_DULIEU_NT> lstData)
            throws SQLException {
        Connection connection = new DaoConnect().getConnect();
        Object array[] = lstData.toArray();
        ArrayDescriptor des = ArrayDescriptor
                .createDescriptor(QT_DULIEU_NT.ORACLE_TABLE_TYPE, connection);
        ARRAY array_to_pass = new ARRAY(des, connection, array);
        CallableStatement cs = null;
        try {
            cs = connection.prepareCall("{call VBSP_IMS_NGHIQUYET11CP.SP_SAVE_02SK(?, ?, ?, ?)}");
            cs.setString(1, username);
            cs.setString(2, mapgd);
            cs.setString(3, ngaybc);
            cs.setArray(4, array_to_pass);
            cs.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Loi trong ham saveNQ11CP_02SK " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " saveNQ11CP_02SK -> " + e.getMessage());
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

    public boolean saveTraiphieu_001(String khoa, String username, String capbc, String ngaybc, List<QT_DULIEU_NT> lstData, List<String> lstArrPoscd, String nghiepvu) throws SQLException {
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
            cs = connection.prepareCall("{call VBSP_IMS_NGHIQUYET11CP.SP_SAVE_TRAIPHIEU_001(?, ?, ?, ?, ? ,?)}");
            cs.setString(1, khoa);
            cs.setString(2, username);
            cs.setString(3, capbc);
            cs.setString(4, ngaybc);
            cs.setArray(5, array_to_pass);
            cs.setArray(6, oracle_arrayPos);
            cs.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Loi trong ham saveNQ11CP_KH04 " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " saveNQ11CP_KH04 -> " + e.getMessage());
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

    public List<QT_DULIEU_NT> getDataSendApiDKKH(String sMaBC, String sFileName) {
        List<QT_DULIEU_NT> lstBcqt_NT = new ArrayList<QT_DULIEU_NT>();
        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call VBSP_IMS_NGHIQUYET11CP.SP_VIEW_DATA_DKKH_SEND_API(?,?,?,?,?)}";
            ResultSet reset = null;

            try {
                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                calstatement.registerOutParameter(3, oracle.jdbc.OracleTypes.NUMBER);
                calstatement.registerOutParameter(4, oracle.jdbc.OracleTypes.VARCHAR);
                calstatement.registerOutParameter(5, oracle.jdbc.OracleTypes.CURSOR);
                calstatement.setString(1, sMaBC);
                calstatement.setString(2, sFileName);
//                calstatement.setString(3, sGrade);
//                calstatement.setString(4, namBC);

                //Thuc hien execute lay du lieu
                calstatement.execute();
                //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
                int pn_err_cd = calstatement.getInt(3);
                //thu hien lay mo ta loi
                String strEdd_txt = calstatement.getString(4);
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

    public List<QT_DULIEU_NT> getData_Traiphieu_002(Connection conn, String sKhoa, String sNgaybc, String sUser,
            String sGrade, List<String> lstArrPoscd, String sanphan, String kyhan) {
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
            String strStoreproce = "{call VBSP_IMS_NGHIQUYET11CP.SP_GET_DATA_TRAIPHIEU_002(?,?,?,?,?,?,?,?, ?, ?)}";
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
                calstatement.setString(9, sanphan);
                calstatement.setString(10, kyhan);
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
                CoreLogger.error(this.getClass().getName() + " getDataKH04 -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham getDataKH04 " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " getDataKH04 -> " + e.getMessage());
        }
        return lstBcqt_NT;
    }

    public boolean saveTraiphieu_002(String khoa, String username, String capbc, String ngaybc, List<QT_DULIEU_NT> lstData, List<String> lstArrPoscd, String nghiepvu) throws SQLException {
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
            cs = connection.prepareCall("{call VBSP_IMS_NGHIQUYET11CP.SP_SAVE_TRAIPHIEU_002(?, ?, ?, ?, ? ,?)}");
            cs.setString(1, khoa);
            cs.setString(2, username);
            cs.setString(3, capbc);
            cs.setString(4, ngaybc);
            cs.setArray(5, array_to_pass);
            cs.setArray(6, oracle_arrayPos);
            cs.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Loi trong ham saveNQ11CP_KH04 " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " saveNQ11CP_KH04 -> " + e.getMessage());
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

    public boolean saveNQ11CP_01_DKKH(String khoa, String username, String capbc, String mapgd, String ngaybc, List<QT_DULIEU_NT> lstData)
            throws SQLException {
        Connection connection = new DaoConnect().getConnect();
        Object array[] = lstData.toArray();
        ArrayDescriptor des = ArrayDescriptor
                .createDescriptor(QT_DULIEU_NT.ORACLE_TABLE_TYPE, connection);
        ARRAY array_to_pass = new ARRAY(des, connection, array);
        CallableStatement cs = null;
        try {
            cs = connection.prepareCall("{call VBSP_IMS_NGHIQUYET11CP.SP_SAVE_01_DKKH(?, ?, ?, ?, ?)}");
            cs.setString(1, khoa);
            cs.setString(2, username);
            cs.setString(3, capbc);
            cs.setString(4, ngaybc);
            cs.setArray(5, array_to_pass);
            cs.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Loi trong ham saveNQ11CP_01_DKKH " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " saveNQ11CP_01_DKKH -> " + e.getMessage());
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

    public int checkSyncData(String khoa, String mapgd, String capbc, String ngaybc) throws SQLException {
        int _retVal = 0;
        Connection conn = new DaoConnect().getConnect();
        CallableStatement calstatement = null;
        ResultSet reset = null;
        //Khoi tao procedure cung voi tham so truyen vao la dau ?
        String strStoreproce = "{ ? = call VBSP_IMS_NGHIQUYET11CP.F_CHECK_SYNC_DATA(?, ?, ?, ?) }";

        try {
            //Khoi tao goi Store
            calstatement = conn.prepareCall(strStoreproce,
                    ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            calstatement.registerOutParameter(1, oracle.jdbc.OracleTypes.NUMBER);
            calstatement.setString(2, khoa);
            calstatement.setString(3, mapgd);
            calstatement.setString(4, capbc);
            calstatement.setString(5, ngaybc);
            //Thuc hien execute lay du lieu
            calstatement.execute();
            //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
            _retVal = calstatement.getInt(1);

        } catch (SQLException e) {
            System.err.print(e.getMessage());
            CoreLogger.error(this.getClass().getName() + " checkSyncData -> " + e.getMessage());
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

    public int checkDateInput(String khoa, String ngaybc) throws SQLException {
        int _retVal = 0;
        Connection conn = new DaoConnect().getConnect();
        CallableStatement calstatement = null;
        ResultSet reset = null;
        //Khoi tao procedure cung voi tham so truyen vao la dau ?
        String strStoreproce = "{ ? = call VBSP_IMS_NGHIQUYET11CP.F_CHECK_DATE(?, ?) }";

        try {
            //Khoi tao goi Store
            calstatement = conn.prepareCall(strStoreproce,
                    ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            calstatement.registerOutParameter(1, oracle.jdbc.OracleTypes.NUMBER);
            calstatement.setString(2, khoa);
            calstatement.setString(3, ngaybc);
            //Thuc hien execute lay du lieu
            calstatement.execute();
            //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
            _retVal = calstatement.getInt(1);

        } catch (SQLException e) {
            System.err.print(e.getMessage());
            CoreLogger.error(this.getClass().getName() + " checkSyncData -> " + e.getMessage());
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

    public boolean saveDUCANH_001(String khoa, String username, String capbc, String ngaybc, List<QT_DULIEU_NT> lstData, List<String> lstArrPoscd, String nghiepvu) throws SQLException {
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
            cs = connection.prepareCall("{call VBSP_IMS_NGHIQUYET11CP.SP_SAVE_DUCANH_001(?, ?, ?, ?, ? ,?)}");
            cs.setString(1, khoa);
            cs.setString(2, username);
            cs.setString(3, capbc);
            cs.setString(4, ngaybc);
            cs.setArray(5, array_to_pass);
            cs.setArray(6, oracle_arrayPos);
            cs.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Loi trong ham saveNQ11CP_KH04 " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " saveNQ11CP_KH04 -> " + e.getMessage());
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

    public List<QT_DULIEU_NT> getData_GQVL_2023(Connection conn, String sNgaybc, String sKhoa, String sUser, String sPosCd, String sPos_Flag) {
        List<QT_DULIEU_NT> lstBcqt_NT = new ArrayList<QT_DULIEU_NT>();
        try {
            CallableStatement calstatement = null;
            String strStoreproce = "{call VBSP_IMS_NGHIQUYET11CP.SP_GET_DATA_GQVL_2023(?,?,?,?,?,?,?,?)}";
            ResultSet reset = null;

            try {
                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                calstatement.registerOutParameter(6, oracle.jdbc.OracleTypes.NUMBER);
                calstatement.registerOutParameter(7, oracle.jdbc.OracleTypes.VARCHAR);
                calstatement.registerOutParameter(8, oracle.jdbc.OracleTypes.CURSOR);
                calstatement.setString(1, sNgaybc);
                calstatement.setString(2, sKhoa);
                calstatement.setString(3, sUser);
                calstatement.setString(4, sPosCd);
                calstatement.setString(5, sPos_Flag);
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
                    value.setD21(reset.getString("D21"));
                    value.setD22(reset.getString("D22"));
                    value.setD23(reset.getString("D23"));
                    value.setD24(reset.getString("D24"));
                    value.setD25(reset.getString("D25"));
                    value.setD26(reset.getString("D26"));
                    value.setD27(reset.getString("D27"));
                    value.setD28(reset.getString("D28"));
                    value.setD29(reset.getString("D29"));
                    value.setD30(reset.getString("D30"));
                    value.setD31(reset.getString("D31"));
                    value.setD32(reset.getString("D32"));
                    value.setD35(reset.getString("D35"));
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

    public List<QT_DULIEU_NT> getData_HuyDong_2023(Connection conn, String sKhoa, String sNgaybc, String sUser,
            String sGrade, String cboCanBo, String flgFilter, List<String> lstArrPoscd, String fetchType, String searchKey) {
        List<QT_DULIEU_NT> lstData = new ArrayList<QT_DULIEU_NT>();
        try {
            ArrayDescriptor des = ArrayDescriptor.createDescriptor("POS_CD", conn);
            String[] arrayPoscd = lstArrPoscd.toArray(new String[0]);
            ARRAY oracle_arrayPoscd = new ARRAY(des, conn, arrayPoscd);
//            DaoConnect daoconnect = new DaoConnect();
//            Connection conn = null;
//            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call VBSP_IMS_NGHIQUYET11CP.SP_GET_DATA_HUYDONG_2024(?,?,?,?,?,?,?,?,?,?,?,?)}";
            ResultSet reset;
            try {
                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                calstatement.registerOutParameter(10, oracle.jdbc.OracleTypes.NUMBER);
                calstatement.registerOutParameter(11, oracle.jdbc.OracleTypes.VARCHAR);
                calstatement.registerOutParameter(12, oracle.jdbc.OracleTypes.CURSOR);
                calstatement.setString(1, sKhoa);
                calstatement.setString(2, sUser);
                calstatement.setString(3, sGrade);
                calstatement.setString(4, sNgaybc);
                calstatement.setString(5, cboCanBo);
                calstatement.setString(6, flgFilter);
                calstatement.setArray(7, oracle_arrayPoscd);
                calstatement.setString(8, fetchType);
                calstatement.setString(9, searchKey);
                //Thuc hien execute lay du lieu
                calstatement.execute();
                //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
                int pn_err_cd = calstatement.getInt(10);
                //thu hien lay mo ta loi
                String strEdd_txt = calstatement.getString(11);
                //Lay cursor ra resultset
                reset = (ResultSet) calstatement.getObject(12);
                while (reset.next()) {

                    QT_DULIEU_NT value = QT_DULIEU_NT.newInstance();
                    value.setD1(reset.getString("GL_TK"));
                    value.setD2(reset.getString("SO_SERI"));
                    value.setD3(reset.getString("SOTK"));
                    value.setD4(reset.getString("MAKH"));
                    value.setD5(reset.getString("TENKH"));
                    value.setD6(reset.getString("MASP"));
                    value.setD7(reset.getString("SODU_SK"));
                    value.setD8(reset.getString("SODU_HD"));
                    value.setD9(reset.getString("KYHAN"));
                    value.setD10(reset.getString("MACB"));
                    value.setD11(reset.getString("NGAYGANSO"));
                    lstData.add(value);

                }
//                System.out.println("dao : "+conn+ "  " +sKhoa+ "  " +sNgaybc+ "  " +sUser+ "  " + sGrade+ "  " + cboCanBo+ "  " +flgFilter+ "  " + lstArrPoscd);
                if (reset != null) {
                    reset.close();
                }
                if (calstatement != null) {
                    calstatement.close();
                }
            } catch (SQLException e) {
                System.err.print(e.getMessage());
                CoreLogger.error(this.getClass().getName() + " loi get 1 -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham get 2 " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " loi get 1  -> " + e.getMessage());
        }
        return lstData;
    }

    public boolean saveGQVL2023(String khoa, String username, String ngaybc, List<QT_DULIEU_NT> lstData) throws SQLException {
        Connection connection = new DaoConnect().getConnect();
//        java.util.Dictionary map = (java.util.Dictionary) (connection.getTypeMap());
        Object array[] = lstData.toArray();
        ArrayDescriptor des = ArrayDescriptor
                .createDescriptor(QT_DULIEU_NT.ORACLE_TABLE_TYPE, connection);
        ARRAY array_to_pass = new ARRAY(des, connection, array);
        ArrayDescriptor des_ma = ArrayDescriptor.createDescriptor("POS_CD", connection);
        CallableStatement cs = null;
        try {
            cs = connection.prepareCall("{call VBSP_IMS_NGHIQUYET11CP.SP_SAVE_GQVL_2023(?, ?, ?, ? )}");
            cs.setString(1, khoa);
            cs.setString(2, username);
            cs.setString(3, ngaybc);
            cs.setArray(4, array_to_pass);
            cs.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Loi trong ham huy dong " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " huy dong -> " + e.getMessage());
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

    public boolean saveHUYDONG_2024(String khoa, String username, String capbc, String ngaybc, List<QT_DULIEU_NT> lstData, List<String> lstArrPoscd, ArrayList<String> chkChon,
            String sCanbo, String sChitieu) throws SQLException {
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
            cs = connection.prepareCall("{call VBSP_IMS_NGHIQUYET11CP.SP_SAVE_HUYDONG_2024(?, ?, ?, ?, ? ,?,?,?,?)}");
            cs.setString(1, khoa);
            cs.setString(2, username);
            cs.setString(3, capbc);
            cs.setString(4, ngaybc);
            cs.setArray(5, array_to_pass);
            cs.setArray(6, oracle_arrayPos);
            cs.setString(7, chkChon.toString());
            cs.setString(8, sCanbo);
            cs.setString(9, sChitieu);
            cs.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Loi trong ham huy dong " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " huy dong -> " + e.getMessage());
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

    //HUYDONG_2024
    public GenericResult<String> cancelAssign(String khoa, String taikhoan, String maCanBo, String ngayGanSo) throws SQLException {
        Connection connection = new DaoConnect().getConnect();
        CallableStatement cs = null;
        try {
            cs = connection.prepareCall("{call VBSP_IMS_NGHIQUYET11CP.SP_CANCEL_HUYDONG_2024(?, ?, ?, ?, ? )}");
            cs.setString(1, taikhoan);
            cs.setString(2, maCanBo);
            cs.setString(3, ngayGanSo);
            cs.registerOutParameter(4, oracle.jdbc.OracleTypes.NUMBER);
            cs.registerOutParameter(5, oracle.jdbc.OracleTypes.VARCHAR);
            cs.execute();

            //Lay ma loi neu co
            int errorCode = cs.getInt(4);
            String errorMessage = cs.getString(5);

            if (errorCode == 0) {
                return (new GenericResult<String>()).Success("Success");
            } else {
                return (new GenericResult<String>()).Fail(errorMessage, errorCode);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Loi trong ham cancelAssign " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " cancelAssign -> " + e.getMessage());
            return (new GenericResult<String>()).Fail(e.getMessage(), e.getErrorCode());
        } finally {
            if (cs != null) {
                cs.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    public boolean deleteQLNK2023(String khoa, String username, String capbc, String ngaybc, List<QT_DULIEU_NT> lstData, String mapgd, String makh) throws SQLException {
        Connection connection = new DaoConnect().getConnect();
//        java.util.Dictionary map = (java.util.Dictionary) (connection.getTypeMap());
        Object array[] = lstData.toArray();
        ArrayDescriptor des = ArrayDescriptor
                .createDescriptor(QT_DULIEU_NT.ORACLE_TABLE_TYPE, connection);
        ARRAY array_to_pass = new ARRAY(des, connection, array);

        ArrayDescriptor des_ma = ArrayDescriptor.createDescriptor("POS_CD", connection);

        CallableStatement cs = null;
        try {
            cs = connection.prepareCall("{call VBSP_IMS_NGHIQUYET11CP.P_DELETE_QLNK_2023(?, ?, ?, ?, ? ,?,?)}");
            cs.setString(1, khoa);
            cs.setString(2, username);
            cs.setString(3, capbc);
            cs.setString(4, ngaybc);
            cs.setArray(5, array_to_pass);
            cs.setString(6, mapgd);
            cs.setString(7, makh);
            cs.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Loi trong ham P_SAVE_QLNK_2023 " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " P_SAVE_QLNK_2023 -> " + e.getMessage());
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

    public boolean deleteMSTS_2024(String khoa, String ngaybc, String mapgd) throws SQLException {
        Connection connection = new DaoConnect().getConnect();
        ArrayDescriptor des = ArrayDescriptor
                .createDescriptor(QT_DULIEU_NT.ORACLE_TABLE_TYPE, connection);

        ArrayDescriptor des_ma = ArrayDescriptor.createDescriptor("POS_CD", connection);

        CallableStatement cs = null;
        try {
            cs = connection.prepareCall("{call VBSP_IMS_NGHIQUYET11CP.P_DELETE_MSTS_2024(? ,?,?)}");
            cs.setString(1, khoa);
            cs.setString(2, ngaybc);
            cs.setString(3, mapgd);
            cs.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Loi trong ham P_SAVE_QLNK_2023 " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " P_SAVE_QLNK_2023 -> " + e.getMessage());
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

    public boolean saveQLNK2023_1(String khoa, String username, String capbc, String ngaybc, List<QT_DULIEU_NT> lstData, String mapgd, String maxa, String mato) throws SQLException {
        Connection connection = new DaoConnect().getConnect();
//        java.util.Dictionary map = (java.util.Dictionary) (connection.getTypeMap());
        Object array[] = lstData.toArray();
        ArrayDescriptor des = ArrayDescriptor
                .createDescriptor(QT_DULIEU_NT.ORACLE_TABLE_TYPE, connection);
        ARRAY array_to_pass = new ARRAY(des, connection, array);

        ArrayDescriptor des_ma = ArrayDescriptor.createDescriptor("POS_CD", connection);

        CallableStatement cs = null;
        try {
            cs = connection.prepareCall("{call VBSP_IMS_NGHIQUYET11CP.P_SAVE_QLNK_2023_1(?, ?, ?, ?, ? ,?,?,?)}");
            cs.setString(1, khoa);
            cs.setString(2, username);
            cs.setString(3, capbc);
            cs.setString(4, ngaybc);
            cs.setArray(5, array_to_pass);
            cs.setString(6, mapgd);
            cs.setString(7, maxa);
            cs.setString(8, mato);
            cs.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Loi trong ham P_SAVE_QLNK_2023 " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " P_SAVE_QLNK_2023 -> " + e.getMessage());
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

    public boolean saveMSTS_2024(String khoa, String username, String capbc, String ngaybc, List<QT_DULIEU_NT> lstData, String mapgd) throws SQLException {
        Connection connection = new DaoConnect().getConnect();
//        java.util.Dictionary map = (java.util.Dictionary) (connection.getTypeMap());
        Object array[] = lstData.toArray();
        ArrayDescriptor des = ArrayDescriptor
                .createDescriptor(QT_DULIEU_NT.ORACLE_TABLE_TYPE, connection);
        ARRAY array_to_pass = new ARRAY(des, connection, array);

        ArrayDescriptor des_ma = ArrayDescriptor.createDescriptor("POS_CD", connection);

        CallableStatement cs = null;
        try {
            cs = connection.prepareCall("{call VBSP_IMS_NGHIQUYET11CP.P_SAVE_MSTS_2024(?, ?, ?, ?, ? ,?)}");
            cs.setString(1, khoa);
            cs.setString(2, username);
            cs.setString(3, capbc);
            cs.setString(4, ngaybc);
            cs.setArray(5, array_to_pass);
            cs.setString(6, mapgd);
            cs.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Loi trong ham ms2024 " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " ms2024 -> " + e.getMessage());
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

    public boolean save_THTK_2024(String khoa, String username, String capbc, String ngaybc, List<QT_DULIEU_NT> lstData, String mapgd) throws SQLException {
        Connection connection = new DaoConnect().getConnect();
//        java.util.Dictionary map = (java.util.Dictionary) (connection.getTypeMap());
        Object array[] = lstData.toArray();
        ArrayDescriptor des = ArrayDescriptor
                .createDescriptor(QT_DULIEU_NT.ORACLE_TABLE_TYPE, connection);
        ARRAY array_to_pass = new ARRAY(des, connection, array);

        ArrayDescriptor des_ma = ArrayDescriptor.createDescriptor("POS_CD", connection);

        CallableStatement cs = null;
        try {
            cs = connection.prepareCall("{call VBSP_IMS_NGHIQUYET11CP.P_SAVE_THTK_2024(?, ?, ?, ?, ? ,?)}");
            cs.setString(1, khoa);
            cs.setString(2, username);
            cs.setString(3, capbc);
            cs.setString(4, ngaybc);
            cs.setArray(5, array_to_pass);
            cs.setString(6, mapgd);
            cs.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Loi trong ham ms2024 " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " ms2024 -> " + e.getMessage());
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

    public boolean save_TDTK_2024(String khoa, String username, String ngaybc, List<DULIEU_NT_TQ> lstData) throws SQLException {
        Connection connection = new DaoConnect().getConnect();
        Object array[] = lstData.toArray();
        ArrayDescriptor des = ArrayDescriptor
                .createDescriptor(DULIEU_NT_TQ.ORACLE_TABLE_TYPE, connection);
        ARRAY array_to_pass = new ARRAY(des, connection, array);

        CallableStatement cs = null;
        try {
            cs = connection.prepareCall("{call VBSP_IMS_NGHIQUYET11CP.P_SAVE_TDKT_2024( ?, ?, ? ,?)}");
            cs.setString(1, khoa);
            cs.setString(2, username);
            cs.setString(3, ngaybc);
            cs.setArray(4, array_to_pass);
            cs.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Loi trong ham ms2024 " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " ms2024 -> " + e.getMessage());
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

    public boolean save_KPBL_2024(String khoa, String ngaybc, String sUser, String sPoscd, String smaxa, String smato, List<QT_DULIEU_NT> lstData) throws SQLException {
        Connection connection = new DaoConnect().getConnect();
//        java.util.Dictionary map = (java.util.Dictionary) (connection.getTypeMap());
        Object array[] = lstData.toArray();
        ArrayDescriptor des = ArrayDescriptor
                .createDescriptor(DULIEU_NT_TQ.ORACLE_TABLE_TYPE, connection);
        ARRAY array_to_pass = new ARRAY(des, connection, array);

        CallableStatement cs = null;
        try {
            cs = connection.prepareCall("{call VBSP_IMS_NGHIQUYET11CP.P_SAVE_BAOSO3(?, ?, ? , ?, ?, ? ,?)}");
            cs.setString(1, khoa);
            cs.setString(2, ngaybc);
            cs.setString(3, sUser);
            cs.setString(4, sPoscd);
            cs.setString(5, smaxa);
            cs.setString(6, smato);
            cs.setArray(7, array_to_pass);
            cs.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Loi trong ham bao so 3 " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " bao so 3 -> " + e.getMessage());
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

    public boolean send_KPBL_2024_C1(String khoa, String ngaybc, String sUser, String sPoscd, String smaxa, String smato) throws SQLException {
        Connection connection = new DaoConnect().getConnect();
        CallableStatement cs = null;
        try {
            cs = connection.prepareCall("{call VBSP_IMS_NGHIQUYET11CP.SP_LOCK_BAOSO3_C1( ?, ? , ?, ?, ? ,?)}");
            cs.setString(1, khoa);
            cs.setString(2, ngaybc);
            cs.setString(3, sUser);
            cs.setString(4, sPoscd);
            cs.setString(5, smaxa);
            cs.setString(6, smato);
            cs.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Loi trong ham bao so 3 " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " bao so 3 -> " + e.getMessage());
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

    public List<QT_DULIEU_NT> getLock_Baoso3(Connection conn, String khoa, String ngaybc, String sUser, String sPoscd, String smaxa, String smato) {
        List<QT_DULIEU_NT> lstBcqt_NT = new ArrayList<QT_DULIEU_NT>();
        try {
            CallableStatement calstatement = null;
            String strStoreproce = "{call VBSP_IMS_NGHIQUYET11CP.GET_DATA_LOCK_BAOSO3_C1(?,?,?,?,?,?,?,?,?)}";
            ResultSet reset = null;

            try {
                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                calstatement.registerOutParameter(7, oracle.jdbc.OracleTypes.NUMBER);
                calstatement.registerOutParameter(8, oracle.jdbc.OracleTypes.VARCHAR);
                calstatement.registerOutParameter(9, oracle.jdbc.OracleTypes.CURSOR);
                calstatement.setString(1, khoa);
                calstatement.setString(2, ngaybc);
                calstatement.setString(3, sUser);
                calstatement.setString(4, sPoscd);
                calstatement.setString(5, smaxa);
                calstatement.setString(6, smato);

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
                    value.setD2(reset.getString("D2"));
                    value.setD3(reset.getString("D3"));
                    value.setD4(reset.getString("D4"));
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

    public boolean saveHTLS2023(String khoa, String username, String capbc, String ngaybc, List<QT_DULIEU_NT> lstData, List<String> lstArrPoscd) throws SQLException {
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
            cs = connection.prepareCall("{call VBSP_IMS_NGHIQUYET11CP.SP_SAVE_HTLS_2023_1(?, ?, ?, ?, ? ,?)}");
            cs.setString(1, khoa);
            cs.setString(2, username);
            cs.setString(3, capbc);
            cs.setString(4, ngaybc);
            cs.setArray(5, array_to_pass);
            cs.setArray(6, oracle_arrayPos);
            cs.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Loi trong ham SP_SAVE_HTLS_2023 " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " SP_SAVE_HTLS_2023 -> " + e.getMessage());
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

    public List<QT_DULIEU_NT> getData_DUCANH_001(Connection conn, String sKhoa, String sNgaybc, String sUser,
            String sGrade, List<String> lstArrPoscd, String sanphan, String kyhan) {
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
            String strStoreproce = "{call VBSP_IMS_NGHIQUYET11CP.SP_GET_DATA_DUCANH_001(?,?,?,?,?,?,?,?, ?, ?)}";
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
                calstatement.setString(9, sanphan);
                calstatement.setString(10, kyhan);
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
                CoreLogger.error(this.getClass().getName() + " getDataKH04 -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham getDataKH04 " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " getDataKH04 -> " + e.getMessage());
        }
        return lstBcqt_NT;
    }

    public boolean saveKKTS2024(String khoa, String username, String sposcd, String sGrade, String ngaybc, String stype, List<DULIEU_NT_TQ> lstData) throws SQLException {
        Connection connection = new DaoConnect().getConnect();
//        java.util.Dictionary map = (java.util.Dictionary) (connection.getTypeMap());
        Object array[] = lstData.toArray();
        ArrayDescriptor des = ArrayDescriptor
                .createDescriptor(DULIEU_NT_TQ.ORACLE_TABLE_TYPE, connection);
        ARRAY array_to_pass = new ARRAY(des, connection, array);
        ArrayDescriptor des_ma = ArrayDescriptor.createDescriptor("POS_CD", connection);
        CallableStatement cs = null;
        try {
            cs = connection.prepareCall("{call VBSP_IMS_NGHIQUYET11CP.SP_SAVE_KKTS_2024(?, ?, ?, ? ,?,?,?)}");
            cs.setString(1, khoa);
            cs.setString(2, username);
            cs.setString(3, sposcd);
            cs.setString(4, sGrade);
            cs.setString(5, ngaybc);
            cs.setString(6, stype);
            cs.setArray(7, array_to_pass);
            cs.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Loi trong ham kkts " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " kkts -> " + e.getMessage());
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

    public boolean saveKKTS2024_HDKT(String khoa, String username, String sposcd, String sGrade, String ngaybc, String stype, List<DULIEU_NT_TQ> lstData) throws SQLException {
        Connection connection = new DaoConnect().getConnect();
//        java.util.Dictionary map = (java.util.Dictionary) (connection.getTypeMap());
        Object array[] = lstData.toArray();
        ArrayDescriptor des = ArrayDescriptor
                .createDescriptor(DULIEU_NT_TQ.ORACLE_TABLE_TYPE, connection);
        ARRAY array_to_pass = new ARRAY(des, connection, array);
        ArrayDescriptor des_ma = ArrayDescriptor.createDescriptor("POS_CD", connection);
        CallableStatement cs = null;
        try {
            cs = connection.prepareCall("{call VBSP_IMS_NGHIQUYET11CP.SP_SAVE_KKTS_HDKT_2024(?, ?, ?, ? ,?,?,?)}");
            cs.setString(1, khoa);
            cs.setString(2, username);
            cs.setString(3, sposcd);
            cs.setString(4, sGrade);
            cs.setString(5, ngaybc);
            cs.setString(6, stype);
            cs.setArray(7, array_to_pass);
            cs.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Loi trong ham kkts " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " kkts -> " + e.getMessage());
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

    public boolean save_TGTV_2025(String khoa, String ngaybc, String sposcd, String smaxa, String sposfl, List<DULIEU_NT_TQ> lstData, String stype) throws SQLException {
        Connection connection = new DaoConnect().getConnect();
//        java.util.Dictionary map = (java.util.Dictionary) (connection.getTypeMap());
        Object array[] = lstData.toArray();
        ArrayDescriptor des = ArrayDescriptor
                .createDescriptor(DULIEU_NT_TQ.ORACLE_TABLE_TYPE, connection);
        ARRAY array_to_pass = new ARRAY(des, connection, array);
        ArrayDescriptor des_ma = ArrayDescriptor.createDescriptor("POS_CD", connection);
        CallableStatement cs = null;
        try {
            cs = connection.prepareCall("{call VBSP_IMS_TDNN.P_SAVE_TGTV_2025(?, ?, ?, ? ,?,?,?)}");
            cs.setString(1, khoa);
            cs.setString(2, ngaybc);
            cs.setString(3, sposcd);
            cs.setString(4, smaxa);
            cs.setString(5, sposfl);
            cs.setArray(6, array_to_pass);
            cs.setString(7, stype);
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

    public List<QT_DULIEU_NT> getTGTV_2025(Connection conn, String khoa, String sPoscd, String ngaybc, String posfl, String type) {
        List<QT_DULIEU_NT> lstBcqt_NT = new ArrayList<QT_DULIEU_NT>();
        try {
            CallableStatement calstatement = null;
            String strStoreproce = "{call VBSP_IMS_TDNN.SP_GET_DATA_TGTV_2025(?,?,?,?,?,?,?,?)}";
            ResultSet reset = null;

            try {
                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                calstatement.registerOutParameter(6, oracle.jdbc.OracleTypes.NUMBER);
                calstatement.registerOutParameter(7, oracle.jdbc.OracleTypes.VARCHAR);
                calstatement.registerOutParameter(8, oracle.jdbc.OracleTypes.CURSOR);
                calstatement.setString(1, khoa);
                calstatement.setString(2, sPoscd);
                calstatement.setString(3, ngaybc);
                calstatement.setString(4, posfl);
                calstatement.setString(5, type);

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

    public static void main(String[] args) throws Exception {
        String s = "30-APR-2022";
        Date date_ngay_bc = new SimpleDateFormat("dd-MMM-yyyy").parse(s);

        String k = new SimpleDateFormat("yyyyMMdd").format(date_ngay_bc);

//        DuLieuNTService service = new DuLieuNTService();
//        ArrayList<NQ11cpModel> lstData = service.getDataNQ11CP("000601", "20220228", "03",
//                "060102", "0000000");
//        new NQ11CP_001().margerData(lstData,"0","0000","1");
//        
//        String s= "000000_1234567";
//        String k = s.split("_")[1];
//
//        List<String> pos = new ArrayList();
//
//        pos.add("001002");
//        pos.add("001005");
//        pos.add("001003");
//        pos.add("001002");
//        pos.add("001004");
//
////        DaoNghiquyet11cp.newInstance().getPosByName(pos);
//        Connection conn = new DaoConnect().getConnect();
//        new DaoNghiquyet11cp().getReportParmams(conn, "NQ11CP_001", "P0631", "1");
//        if (conn != null) {
//            conn.close();
//        }
    }
}
