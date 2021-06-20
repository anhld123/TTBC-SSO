/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.sbv;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;
import vbsp.ims.bcqt.model.QT_DULIEU_NT;
import vbsp.ims.dao.DaoConnect;
import vbsp.ims.dao.DaoLoadReportParams;
import vbsp.ims.loadparams.Combo;
import vbsp.ims.loadparams.ReportParam;
import vbsp.ims.log.CoreLogger;
import vbsp.ims.model.ModelTreeNode;
import vbsp.ims.report.fast.ListValue;

/**
 *
 * @author BAOANH
 */
public class daoSbv {

    public static daoSbv newInstance() {
        return new daoSbv();
    }

    public List<ListValue> getNhombc() throws SQLException {
        List<ListValue> lstAllGroup = new ArrayList<ListValue>();
        Connection conn = null;
        conn = new DaoConnect().getConnect();
        CallableStatement calstatement = null;
        //Khoi tao procedure cung voi tham so truyen vao la dau ?
        String strStoreproce = "{call IMS_SBV.SP_GET_LOAIBC(?)}";
        ResultSet reset = null;

        try {
            //Khoi tao goi store
            calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            //Tham so thu nhat truyen vao la co lay theo pos hay main pos   
            calstatement.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            //Thuc hien execute lay du lieu
            calstatement.execute();

            //Lay cursor ra resultset
            reset = (ResultSet) calstatement.getObject(1);
            //COLUMN_DESC
            while (reset.next()) {
                lstAllGroup.add(new ListValue(reset.getString("SKEY"), reset.getString("SDESC"), reset.getString("GROUP_ORDER")));
            }

        } catch (SQLException e) {
            System.err.print(e.getMessage());
            CoreLogger.error(this.getClass().getName() + " getNhombc -> " + e.getMessage());
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

    public List<ListValue> getAllReportSbv(String capbc, String loaibc) throws SQLException {
        List<ListValue> lstAllReport = new ArrayList<ListValue>();
        Connection conn = null;
        conn = new DaoConnect().getConnect();
        CallableStatement calstatement = null;
        //Khoi tao procedure cung voi tham so truyen vao la dau ?
        String strStoreproce = "{call IMS_SBV.SP_LOAD_ALL_SBV(?,?,?)}";
        ResultSet reset = null;

        try {
            //Khoi tao goi store
            calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            //Tham so thu nhat truyen vao la co lay theo pos hay main pos   
            calstatement.setString(1, capbc);
            calstatement.setString(2, loaibc == null ? "" : loaibc);
            calstatement.registerOutParameter(3, oracle.jdbc.OracleTypes.CURSOR);
            //Thuc hien execute lay du lieu
            calstatement.execute();

            //Lay cursor ra resultset
            reset = (ResultSet) calstatement.getObject(3);
            //COLUMN_DESC
            while (reset.next()) {
                lstAllReport.add(new ListValue(reset.getString("SKEY"), reset.getString("SDESC"), reset.getString("GROUP_ORDER")));
            }

        } catch (SQLException e) {
            System.err.print(e.getMessage());
            CoreLogger.error(this.getClass().getName() + " getNhombc -> " + e.getMessage());
            throw new SQLException();
        } finally {
            if (reset != null) {
                reset.close();
            }
            if (calstatement != null) {
                calstatement.close();
            }

        }
        return lstAllReport;
    }

    public List<ModelTreeNode> getDataPosTreeNode(Connection conn, String strUserName, String sGrade) {
        List<ModelTreeNode> lstPo = new ArrayList<ModelTreeNode>();
        try {
//            DaoConnect daoconnect = new DaoConnect();
//            Connection conn = null;
//            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call IMS_SBV.SP_LOAD_POS_TREE_SBV(?,?,?)}";
            ResultSet reset = null;

            try {
                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                //Tham so thu nhat truyen vao la co lay theo pos hay main pos
                calstatement.setString(1, strUserName);
                calstatement.setString(2, sGrade);
                calstatement.registerOutParameter(3, oracle.jdbc.OracleTypes.CURSOR);
                //Thuc hien execute lay du lieu
                calstatement.execute();
                //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
                //Lay cursor ra resultset
                reset = (ResultSet) calstatement.getObject(3);
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

    public List<ReportParam> getReportParmams(Connection conn, String sKhoa) {
        ArrayList<ReportParam> report_param_list = new ArrayList<>();

        try {
//            DaoConnect daoconnect = new DaoConnect();
//            Connection conn ;
//            conn = daoconnect.getConnect();
            CallableStatement calstatement;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call IMS_SBV.SP_LOAD_PARA_SBV(?,  ?, ?)}";
            ResultSet rscur_params;
            ResultSet rs_combo;

            try {
                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce,
                        ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                //Tham so thu nhat truyen vao la co lay theo pos hay main pos    
                calstatement.setString(1, sKhoa);
                calstatement.registerOutParameter(2, oracle.jdbc.OracleTypes.CURSOR);
                calstatement.registerOutParameter(3, oracle.jdbc.OracleTypes.CURSOR);

                //Thuc hien execute lay du lieu
                calstatement.execute();
                //Lay cursor ra resultset
                rscur_params = (ResultSet) calstatement.getObject(3);
                rs_combo = (ResultSet) calstatement.getObject(2);

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
                CoreLogger.error(this.getClass().getCanonicalName() + " getReportParmams  -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham getColumnReportFast " + e.getMessage());
            CoreLogger.error(DaoLoadReportParams.class.getCanonicalName() + " getReportParmams  -> " + e.getMessage());
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

                combo_list.add(cb);
            }
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getCanonicalName() + " fillResultSetComboToArray  -> " + e.getMessage());
        }
        return combo_list;
    }

    public List<QT_DULIEU_NT> load170TTGS(String Khoa, String Ngaybc, String ky_bc, String Mapgd) throws SQLException {
        List<QT_DULIEU_NT> lstBcqt_NT = new ArrayList<QT_DULIEU_NT>();

        DaoConnect daoconnect = new DaoConnect();
        Connection conn = null;
        conn = daoconnect.getConnect();
        CallableStatement calstatement = null;
        //Khoi tao procedure cung voi tham so truyen vao la dau ?
        String strStoreproce = "{call IMS_SBV.SP_LOAD_SBV_170_TTGS(?,?,?,?,?)}";
        ResultSet reset = null;

        try {
            //Khoi tao goi store
            calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            calstatement.registerOutParameter(5, oracle.jdbc.OracleTypes.CURSOR);
            calstatement.setString(1, Khoa);
            calstatement.setString(2, Ngaybc);
            calstatement.setString(3, ky_bc);
            calstatement.setString(4, Mapgd);
            //Thuc hien execute lay du lieu
            calstatement.execute();
                //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
            //Lay cursor ra resultset
            reset = (ResultSet) calstatement.getObject(5);
            while (reset.next()) {

                QT_DULIEU_NT value = QT_DULIEU_NT.newInstance();
                value.setKHOA(reset.getString(1));
                value.setTHUTU(reset.getInt(2));
                value.setTT_HIENTHI(reset.getString(3) == null ? "" : reset.getString(3));
                value.setMA(reset.getString(4));
                value.setTEN(reset.getString(5));
                value.setNGAYBC(reset.getDate(6));
                value.setNAMBC(reset.getInt(7));
                value.setMAPGD(reset.getString(8));
                value.setD1(reset.getString(9));
                value.setD2(reset.getString(10));

                lstBcqt_NT.add(value);
            }

        } catch (SQLException e) {
            System.err.print(e.getMessage());
            CoreLogger.error(this.getClass().getName() + " load170TTGS -> " + e.getMessage());
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
    
    public List<QT_DULIEU_NT> load1283TTGS(String Khoa, String Ngaybc, String UserName,String sGrade, List<String> lstArrPoscd) throws SQLException {
        List<QT_DULIEU_NT> lstBcqt_NT = new ArrayList<QT_DULIEU_NT>();

        DaoConnect daoconnect = new DaoConnect();                        
        Connection conn = null;
        conn = daoconnect.getConnect();
        CallableStatement calstatement = null;
        
        ArrayDescriptor des = ArrayDescriptor.createDescriptor("POS_CD", conn);
            String[] arrayPoscd = lstArrPoscd.toArray(new String[0]);
            ARRAY oracle_arrayPoscd = new ARRAY(des, conn, arrayPoscd);
            
        //Khoi tao procedure cung voi tham so truyen vao la dau ?
        String strStoreproce = "{call IMS_SBV.SP_LOAD_SBV_1283_TTGS(?,?,?,?,?,?)}";
        ResultSet reset = null;

        try {
            //Khoi tao goi store
            calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            calstatement.registerOutParameter(6, oracle.jdbc.OracleTypes.CURSOR);
            calstatement.setString(1, Khoa);
            calstatement.setString(2, Ngaybc);
            calstatement.setString(3, UserName);
            calstatement.setString(4, sGrade);            
            calstatement.setArray(5, oracle_arrayPoscd);
            //Thuc hien execute lay du lieu
            calstatement.execute();
                //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
            //Lay cursor ra resultset
            reset = (ResultSet) calstatement.getObject(6);
            while (reset.next()) {

                QT_DULIEU_NT value = QT_DULIEU_NT.newInstance();
                value.setKHOA(reset.getString(1));
                value.setD1(reset.getString(4));
                value.setD2(reset.getString(5));
                value.setD3(reset.getString(6));
                value.setD4(reset.getString(7));
                value.setD5(reset.getString(8));
                value.setD6(reset.getString(9));
                value.setD7(reset.getString(10));
                value.setD8(reset.getString(11));
                value.setD9(reset.getString(12));
                value.setD10(reset.getString(13));
                value.setD11(reset.getString(14));
                value.setD12(reset.getString(15));
                lstBcqt_NT.add(value);
            }

        } catch (SQLException e) {
            System.err.print(e.getMessage());
            CoreLogger.error(this.getClass().getName() + " load1283TTGS -> " + e.getMessage());
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
    
    public List<QT_DULIEU_NT> loadThuyetminh2(String Khoa, String Ngaybc, String UserName,String pos, String sKybc) throws SQLException {
        List<QT_DULIEU_NT> lstBcqt_NT = new ArrayList<QT_DULIEU_NT>();

        DaoConnect daoconnect = new DaoConnect();
        Connection conn = null;
        conn = daoconnect.getConnect();
        CallableStatement calstatement = null;
        //Khoi tao procedure cung voi tham so truyen vao la dau ?
        String strStoreproce = "{call IMS_SBV.SP_LOAD_THUYETMINH2(?,?,?,?,?,?)}";
        ResultSet reset = null;

        try {
            //Khoi tao goi store
            calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            calstatement.registerOutParameter(5, oracle.jdbc.OracleTypes.CURSOR);
            calstatement.setString(1, Khoa);
            calstatement.setString(2, Ngaybc);
            calstatement.setString(3, UserName);
            calstatement.setString(4, pos);
            calstatement.setString(6, sKybc);
            //Thuc hien execute lay du lieu
            calstatement.execute();
                //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
            //Lay cursor ra resultset
            reset = (ResultSet) calstatement.getObject(5);
            while (reset.next()) {

                QT_DULIEU_NT value = QT_DULIEU_NT.newInstance();
                value.setKHOA(reset.getString(1));
                value.setD1(reset.getString(4));
                value.setD2(reset.getString(5));
                value.setD3(reset.getString(6));
                value.setD4(reset.getString(7));
                value.setD5(reset.getString(8));
                value.setD6(reset.getString(9));
                value.setD7(reset.getString(10));
                value.setD8(reset.getString(11));
                value.setD9(reset.getString(12));
                lstBcqt_NT.add(value);
            }

        } catch (SQLException e) {
            System.err.print(e.getMessage());
            CoreLogger.error(this.getClass().getName() + " loadThuyetminh2 -> " + e.getMessage());
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
    
    public List<QT_DULIEU_NT> loadThuyetminh1(String Khoa, String Ngaybc, String UserName, String pos ,String sKybc) throws SQLException {
        List<QT_DULIEU_NT> lstBcqt_NT = new ArrayList<QT_DULIEU_NT>();

        DaoConnect daoconnect = new DaoConnect();
        Connection conn = null;
        conn = daoconnect.getConnect();
        CallableStatement calstatement = null;
        //Khoi tao procedure cung voi tham so truyen vao la dau ?
        String strStoreproce = "{call IMS_SBV.SP_LOAD_THUYETMINH1(?,?,?,?,?,?)}";
        ResultSet reset = null;

        try {
            //Khoi tao goi store
            calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            calstatement.registerOutParameter(5, oracle.jdbc.OracleTypes.CURSOR);
            calstatement.setString(1, Khoa);
            calstatement.setString(2, Ngaybc);
            calstatement.setString(3, UserName);
            calstatement.setString(4, pos);
            calstatement.setString(6, sKybc);
            //Thuc hien execute lay du lieu
            calstatement.execute();
                //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
            //Lay cursor ra resultset
            reset = (ResultSet) calstatement.getObject(5);
            while (reset.next()) {

                QT_DULIEU_NT value = QT_DULIEU_NT.newInstance();
                value.setKHOA(reset.getString(1));
                value.setD1(reset.getString(4));
                value.setD2(reset.getString(5));
                value.setD3(reset.getString(6));
                value.setD4(reset.getString(7));
                value.setD5(reset.getString(8));
                value.setD6(reset.getString(9));
                value.setD7(reset.getString(10));
                value.setD8(reset.getString(11));
                value.setD9(reset.getString(12));
                lstBcqt_NT.add(value);
            }

        } catch (SQLException e) {
            System.err.print(e.getMessage());
            CoreLogger.error(this.getClass().getName() + " loadThuyetminh1 -> " + e.getMessage());
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

    public boolean save170TTGS(String khoa, String username, String mapgd, String ngaybc, String ky_bc, List<QT_DULIEU_NT> lstData) throws SQLException {
        Connection connection = new DaoConnect().getConnect();
        Object array[] = lstData.toArray();
        ArrayDescriptor des = ArrayDescriptor
                .createDescriptor(QT_DULIEU_NT.ORACLE_TABLE_TYPE, connection);
        ARRAY array_to_pass = new ARRAY(des, connection, array);
        CallableStatement cs = null;
        try {
            cs = connection.prepareCall("{call IMS_SBV.SP_SAVE_170TTGS(?, ?, ?, ?, ?, ?)}");
            cs.setString(1, khoa);
            cs.setString(2, username);
            cs.setString(3, mapgd);
            cs.setString(4, ngaybc);
            cs.setString(5, ky_bc);
            cs.setArray(6, array_to_pass);
            cs.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Loi trong ham save170TTGS " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " save170TTGS -> " + e.getMessage());
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

    public List<QT_DULIEU_NT> load162TTGS(String Khoa, String Ngaybc, String ky_bc, String Mapgd) throws SQLException {
        List<QT_DULIEU_NT> lstBcqt_NT = new ArrayList<QT_DULIEU_NT>();

        DaoConnect daoconnect = new DaoConnect();
        Connection conn = null;
        conn = daoconnect.getConnect();
        CallableStatement calstatement = null;
        //Khoi tao procedure cung voi tham so truyen vao la dau ?
        String strStoreproce = "{call IMS_SBV.SP_LOAD_SBV_162_TTGS(?,?,?,?,?)}";
        ResultSet reset = null;

        try {
            //Khoi tao goi store
            calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            calstatement.registerOutParameter(5, oracle.jdbc.OracleTypes.CURSOR);
            calstatement.setString(1, Khoa);
            calstatement.setString(2, Ngaybc);
            calstatement.setString(3, ky_bc);
            calstatement.setString(4, Mapgd);
            //Thuc hien execute lay du lieu
            calstatement.execute();
                //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
            //Lay cursor ra resultset
            reset = (ResultSet) calstatement.getObject(5);
            while (reset.next()) {

                QT_DULIEU_NT value = QT_DULIEU_NT.newInstance();
                value.setKHOA(reset.getString(1));
                value.setTHUTU(reset.getInt(2));
                value.setTT_HIENTHI(reset.getString(3) == null ? "" : reset.getString(3));
                value.setMA(reset.getString(4));
                value.setTEN(reset.getString(5));
                value.setNGAYBC(reset.getDate(6));
                value.setNAMBC(reset.getInt(7));
                value.setMAPGD(reset.getString(8));
                value.setD1(reset.getString(9));
                value.setD2(reset.getString(10));

                lstBcqt_NT.add(value);
            }

        } catch (SQLException e) {
            System.err.print(e.getMessage());
            CoreLogger.error(this.getClass().getName() + " load162TTGS -> " + e.getMessage());
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

    public boolean save162TTGS(String khoa, String username, String mapgd, String ngaybc, String ky_bc, List<QT_DULIEU_NT> lstData) throws SQLException {
        Connection connection = new DaoConnect().getConnect();
        Object array[] = lstData.toArray();
        ArrayDescriptor des = ArrayDescriptor
                .createDescriptor(QT_DULIEU_NT.ORACLE_TABLE_TYPE, connection);
        ARRAY array_to_pass = new ARRAY(des, connection, array);
        CallableStatement cs = null;
        try {
            cs = connection.prepareCall("{call IMS_SBV.SP_SAVE_162TTGS(?, ?, ?, ?, ?,?)}");
            cs.setString(1, khoa);
            cs.setString(2, username);
            cs.setString(3, mapgd);
            cs.setString(4, ngaybc);
            cs.setString(5, ky_bc);
            cs.setArray(6, array_to_pass);
            cs.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Loi trong ham save162TTGS " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " save162TTGS -> " + e.getMessage());
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
    
    public List<QT_DULIEU_NT> load165TTGS(String Khoa, String Ngaybc, String ky_bc, String Mapgd) throws SQLException {
        List<QT_DULIEU_NT> lstBcqt_NT = new ArrayList<QT_DULIEU_NT>();

        DaoConnect daoconnect = new DaoConnect();
        Connection conn = null;
        conn = daoconnect.getConnect();
        CallableStatement calstatement = null;
        //Khoi tao procedure cung voi tham so truyen vao la dau ?
        String strStoreproce = "{call IMS_SBV.SP_LOAD_SBV_165_TTGS(?,?,?,?,?)}";
        ResultSet reset = null;

        try {
            //Khoi tao goi store
            calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            calstatement.registerOutParameter(5, oracle.jdbc.OracleTypes.CURSOR);
            calstatement.setString(1, Khoa);
            calstatement.setString(2, Ngaybc);
            calstatement.setString(3, ky_bc);
            calstatement.setString(4, Mapgd);
            //Thuc hien execute lay du lieu
            calstatement.execute();
                //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
            //Lay cursor ra resultset
            reset = (ResultSet) calstatement.getObject(5);
            while (reset.next()) {

                QT_DULIEU_NT value = QT_DULIEU_NT.newInstance();
                value.setKHOA(reset.getString(1));
                value.setTHUTU(reset.getInt(2));
                value.setTT_HIENTHI(reset.getString(3) == null ? "" : reset.getString(3));
                value.setMA(reset.getString(4));
                value.setTEN(reset.getString(5));
                value.setNGAYBC(reset.getDate(6));
                value.setNAMBC(reset.getInt(7));
                value.setMAPGD(reset.getString(8));
                value.setD1(reset.getString(9));
                value.setD2(reset.getString(10));
                value.setD3(reset.getString(11));
                value.setD4(reset.getString(12));

                lstBcqt_NT.add(value);
            }

        } catch (SQLException e) {
            System.err.print(e.getMessage());
            CoreLogger.error(this.getClass().getName() + " load165TTGS -> " + e.getMessage());
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

    public boolean save165TTGS(String khoa, String username, String mapgd, String ngaybc, String ky_bc, List<QT_DULIEU_NT> lstData) throws SQLException {
        Connection connection = new DaoConnect().getConnect();
        Object array[] = lstData.toArray();
        ArrayDescriptor des = ArrayDescriptor
                .createDescriptor(QT_DULIEU_NT.ORACLE_TABLE_TYPE, connection);
        ARRAY array_to_pass = new ARRAY(des, connection, array);
        CallableStatement cs = null;
        try {
            cs = connection.prepareCall("{call IMS_SBV.SP_SAVE_165TTGS(?, ?, ?, ?, ?, ?)}");
            cs.setString(1, khoa);
            cs.setString(2, username);
            cs.setString(3, mapgd);
            cs.setString(4, ngaybc);
            cs.setString(5, ky_bc);
            cs.setArray(6, array_to_pass);
            cs.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Loi trong ham save165TTGS " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " save165TTGS -> " + e.getMessage());
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
    
     public List<QT_DULIEU_NT> load168TTGS(String Khoa, String Ngaybc, String ky_bc, String Mapgd) throws SQLException {
        List<QT_DULIEU_NT> lstBcqt_NT = new ArrayList<QT_DULIEU_NT>();

        DaoConnect daoconnect = new DaoConnect();
        Connection conn = null;
        conn = daoconnect.getConnect();
        CallableStatement calstatement = null;
        //Khoi tao procedure cung voi tham so truyen vao la dau ?
        String strStoreproce = "{call IMS_SBV.SP_LOAD_SBV_168_TTGS(?,?,?,?,?)}";
        ResultSet reset = null;

        try {
            //Khoi tao goi store
            calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            calstatement.registerOutParameter(5, oracle.jdbc.OracleTypes.CURSOR);
            calstatement.setString(1, Khoa);
            calstatement.setString(2, Ngaybc);
            calstatement.setString(3, ky_bc);
            calstatement.setString(4, Mapgd);
            //Thuc hien execute lay du lieu
            calstatement.execute();
                //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
            //Lay cursor ra resultset
            reset = (ResultSet) calstatement.getObject(5);
            while (reset.next()) {

                QT_DULIEU_NT value = QT_DULIEU_NT.newInstance();
                value.setKHOA(reset.getString(1));
                value.setTHUTU(reset.getInt(2));
                value.setTT_HIENTHI(reset.getString(3) == null ? "" : reset.getString(3));
                value.setMA(reset.getString(4));
                value.setTEN(reset.getString(5));
                value.setNGAYBC(reset.getDate(6));
                value.setNAMBC(reset.getInt(7));
                value.setMAPGD(reset.getString(8));
                value.setD1(reset.getString(9));
                value.setD2(reset.getString(10));

                lstBcqt_NT.add(value);
            }

        } catch (SQLException e) {
            System.err.print(e.getMessage());
            CoreLogger.error(this.getClass().getName() + " load168TTGS -> " + e.getMessage());
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

    public boolean save168TTGS(String khoa, String username, String mapgd, String ngaybc, String ky_bc, List<QT_DULIEU_NT> lstData) throws SQLException {
        Connection connection = new DaoConnect().getConnect();
        Object array[] = lstData.toArray();
        ArrayDescriptor des = ArrayDescriptor
                .createDescriptor(QT_DULIEU_NT.ORACLE_TABLE_TYPE, connection);
        ARRAY array_to_pass = new ARRAY(des, connection, array);
        CallableStatement cs = null;
        try {
            cs = connection.prepareCall("{call IMS_SBV.SP_SAVE_168TTGS(?, ?, ?, ?, ?, ?)}");
            cs.setString(1, khoa);
            cs.setString(2, username);
            cs.setString(3, mapgd);
            cs.setString(4, ngaybc);
            cs.setString(5, ky_bc);
            cs.setArray(6, array_to_pass);
            cs.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Loi trong ham save168TTGS " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " save168TTGS -> " + e.getMessage());
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
    
    public boolean save1283TTGS(String khoa, String username, String ngaybc, List<QT_DULIEU_NT> lstData) throws SQLException {
        Connection connection = new DaoConnect().getConnect();
        Object array[] = lstData.toArray();
        ArrayDescriptor des = ArrayDescriptor
                .createDescriptor(QT_DULIEU_NT.ORACLE_TABLE_TYPE, connection);
        ARRAY array_to_pass = new ARRAY(des, connection, array);
        CallableStatement cs = null;
        try {
            cs = connection.prepareCall("{call IMS_SBV.SP_SAVE_1283TTGS(?, ?, ?,  ?)}");
            cs.setString(1, khoa);
            cs.setString(2, username);
            cs.setString(3, ngaybc);
            cs.setArray(4, array_to_pass);
            cs.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Loi trong ham save1283TTGS " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " save1283TTGS -> " + e.getMessage());
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
    
    public boolean saveThuyetMinh(String khoa, String username, String ngaybc,String pos, List<QT_DULIEU_NT> lstData1,List<QT_DULIEU_NT> lstData2,String sKybc) throws SQLException {
        Connection connection = new DaoConnect().getConnect();
        Object array1[] = lstData1.toArray();
        ArrayDescriptor des1 = ArrayDescriptor
                .createDescriptor(QT_DULIEU_NT.ORACLE_TABLE_TYPE, connection);
        ARRAY array_to_pass1 = new ARRAY(des1, connection, array1);
        
        Object array2[] = lstData2.toArray();
        ArrayDescriptor des2 = ArrayDescriptor
                .createDescriptor(QT_DULIEU_NT.ORACLE_TABLE_TYPE, connection);
        ARRAY array_to_pass2 = new ARRAY(des2, connection, array2);
        
        CallableStatement cs = null;
        try {
            cs = connection.prepareCall("{call IMS_SBV.SP_SAVE_THUYETMINH(?, ?,?, ?,?,?,?)}");
            cs.setString(1, khoa);
            cs.setString(2, username);
            cs.setString(3, ngaybc);
            cs.setString(4, pos);
            cs.setArray(5, array_to_pass1);
            cs.setArray(6, array_to_pass2);
            cs.setString(7, sKybc);
            cs.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Loi trong ham saveThuyetMinh " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " saveThuyetMinh -> " + e.getMessage());
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
    
    public String getPosFromUsername(String userName) throws SQLException
    {
        String Mapgd="";
         DaoConnect daoconnect = new DaoConnect();
        Connection conn = null;
        conn = daoconnect.getConnect();
        CallableStatement calstatement = null;
        //Khoi tao procedure cung voi tham so truyen vao la dau ?
        String strStoreproce = "{?=call IMS_SBV.SP_GET_POS_FROM_USER(?)}";
        ResultSet reset = null;

        try {
            //Khoi tao goi store
            calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            calstatement.registerOutParameter(1, oracle.jdbc.OracleTypes.VARCHAR);
            calstatement.setString(2, userName);
            //Thuc hien execute lay du lieu
            calstatement.execute();
                //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
            //Lay cursor ra resultset
            Mapgd=calstatement.getString(1);

        } catch (SQLException e) {
            System.err.print(e.getMessage());
            CoreLogger.error(this.getClass().getName() + " getPosFromUsername -> " + e.getMessage());
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
        return  Mapgd;
    }
    
    public List<String> getDataSendsBV(String type,String khoa, String mapgd, String ngay_bc) throws SQLException {
        List<String> lstData = new ArrayList<>();
        DaoConnect daoconnect = new DaoConnect();
        Connection conn = null;
        conn = daoconnect.getConnect();
        CallableStatement calstatement = null;
        ResultSet reset = null;
//        try {
        //Khoi tao procedure cung voi tham so truyen vao la dau ?
        String strStoreproce = "{call  IMS_SBV.SP_GET_DATA_SBV_SYNC(?,?,?,?,?,?,?)}";
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
    
    public Map<String, String> getPosByName(List<String> lstMaPGD) throws SQLException {
        Map<String, String> mapData = new HashMap<String, String>();
        DaoConnect daoconnect = new DaoConnect();
        Connection conn = null;
        conn = daoconnect.getConnect();
        CallableStatement calstatement = null;
        ResultSet reset = null;
//        try {
        //Khoi tao procedure cung voi tham so truyen vao la dau ?
        String strStoreproce = "{call VBSP_IMS_BCQT.SP_GET_POS_BY_NAME(?,?)}";
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
    
    public List<QT_DULIEU_NT> load004TTGS(String Khoa, String Ngaybc, String UserName,String sGrade, List<String> lstArrPoscd) throws SQLException {
        List<QT_DULIEU_NT> lstBcqt_NT = new ArrayList<QT_DULIEU_NT>();

        DaoConnect daoconnect = new DaoConnect();                        
        Connection conn = null;
        conn = daoconnect.getConnect();
        CallableStatement calstatement = null;
        
        ArrayDescriptor des = ArrayDescriptor.createDescriptor("POS_CD", conn);
            String[] arrayPoscd = lstArrPoscd.toArray(new String[0]);
            ARRAY oracle_arrayPoscd = new ARRAY(des, conn, arrayPoscd);
            
        //Khoi tao procedure cung voi tham so truyen vao la dau ?
        String strStoreproce = "{call IMS_SBV.SP_LOAD_VB8338_TTGS04(?,?,?,?,?,?)}";
        ResultSet reset = null;

        try {
            //Khoi tao goi store
            calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            calstatement.registerOutParameter(6, oracle.jdbc.OracleTypes.CURSOR);
            calstatement.setString(1, Khoa);
            calstatement.setString(2, Ngaybc);
            calstatement.setString(3, UserName);
            calstatement.setString(4, sGrade);            
            calstatement.setArray(5, oracle_arrayPoscd);
            //Thuc hien execute lay du lieu
            calstatement.execute();
                //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
            //Lay cursor ra resultset
            reset = (ResultSet) calstatement.getObject(6);
            while (reset.next()) {
//                KHOA, MA, TT_HIENTHI, TEN, NGAYBC, NAMBC, MAPGD, MACN, 
//                    D2, D4, D5, D7, D13, D19, D20, D21, D22, NHAPTAY
                QT_DULIEU_NT value = QT_DULIEU_NT.newInstance();
                
                    String Ma = reset.getString(2);
                    value.setKHOA(reset.getString(1));
                    value.setMA(Ma);
                    value.setTT_HIENTHI(reset.getString(3));
                    value.setTEN(reset.getString(4));
                    value.setNGAYBC(reset.getDate(5));
                    value.setNAMBC(reset.getInt(6));
                    value.setMAPGD(reset.getString(7));
                    value.setMACN(reset.getString(8));
                    value.setD2(reset.getString(9));
                    value.setD4(reset.getString(10));
                    value.setD5(reset.getString(11));
                    value.setD7(reset.getString(12));
                    value.setD13(reset.getString(13));
                    value.setD19(reset.getString(14));
                    value.setD20(reset.getString(15));
                    value.setD21(reset.getString(16));
                    value.setD22(reset.getString(17));                    
                    value.setNHAPTAY(reset.getString(18));
                    value.setD26(reset.getString(19));
                lstBcqt_NT.add(value);
            }

        } catch (SQLException e) {
            System.err.print(e.getMessage());
            CoreLogger.error(this.getClass().getName() + " load004TTGS -> " + e.getMessage());
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
    
    public boolean save004TTGS(String khoa, String username, String ngaybc, List<QT_DULIEU_NT> lstData) throws SQLException {
        Connection connection = new DaoConnect().getConnect();
        Object array[] = lstData.toArray();
        ArrayDescriptor des = ArrayDescriptor
                .createDescriptor(QT_DULIEU_NT.ORACLE_TABLE_TYPE, connection);
        ARRAY array_to_pass = new ARRAY(des, connection, array);
        CallableStatement cs = null;
        try {
            cs = connection.prepareCall("{call IMS_SBV.SP_SAVE_004TTGS(?, ?, ?,  ?)}");
            cs.setString(1, khoa);
            cs.setString(2, username);
            cs.setString(3, ngaybc);
            cs.setArray(4, array_to_pass);
            cs.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Loi trong ham save004TTGS " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " save004TTGS -> " + e.getMessage());
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
    
    public List<QT_DULIEU_NT> load005TTGS(String Khoa, String Ngaybc, String UserName,String sGrade, List<String> lstArrPoscd) throws SQLException {
        List<QT_DULIEU_NT> lstBcqt_NT = new ArrayList<QT_DULIEU_NT>();

        DaoConnect daoconnect = new DaoConnect();                        
        Connection conn = null;
        conn = daoconnect.getConnect();
        CallableStatement calstatement = null;
        
        ArrayDescriptor des = ArrayDescriptor.createDescriptor("POS_CD", conn);
            String[] arrayPoscd = lstArrPoscd.toArray(new String[0]);
            ARRAY oracle_arrayPoscd = new ARRAY(des, conn, arrayPoscd);
            
        //Khoi tao procedure cung voi tham so truyen vao la dau ?
        String strStoreproce = "{call IMS_SBV.SP_LOAD_VB8338_TTGS05(?,?,?,?,?,?)}";
        ResultSet reset = null;

        try {
            //Khoi tao goi store
            calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            calstatement.registerOutParameter(6, oracle.jdbc.OracleTypes.CURSOR);
            calstatement.setString(1, Khoa);
            calstatement.setString(2, Ngaybc);
            calstatement.setString(3, UserName);
            calstatement.setString(4, sGrade);            
            calstatement.setArray(5, oracle_arrayPoscd);
            //Thuc hien execute lay du lieu
            calstatement.execute();
                //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
            //Lay cursor ra resultset
            reset = (ResultSet) calstatement.getObject(6);
            while (reset.next()) {
//                KHOA, MA, TT_HIENTHI, TEN, NGAYBC, NAMBC, MAPGD, MACN, 
//                    D2, D4, D5, D7, D13, D19, D20, D21, D22, NHAPTAY
                QT_DULIEU_NT value = QT_DULIEU_NT.newInstance();
//                KHOA, THUTU, TT_HIENTHI, MA, TEN, NGAYBC, NAMBC, MAPGD, CO_TONGHOP, MACN, NGUOI_NHAP, NGAY_NHAP, NGUOI_DUYET, 
//                NGAY_DUYET, D1, D2, D3, D4, D5, D6, D7, D8, D9, D10, D11, D12, D13, D14, D15, D16, D17, D18, D19,
//                    D20, D21, D22, D23, D24, D25, D26, D27, D28, D29, D30, NHAPTAY, FONTFORMAT
                    String Ma = reset.getString(4);
                    value.setKHOA(reset.getString(1));
                    value.setMA(Ma);
                    value.setTHUTU(reset.getInt(2));
                    value.setTT_HIENTHI(reset.getString(3));
                    value.setTEN(reset.getString(5));
                    value.setNGAYBC(reset.getDate(6));
                    value.setNAMBC(reset.getInt(7));
                    value.setMAPGD(reset.getString(8));
                    value.setMACN(reset.getString(10));
                    value.setD3(reset.getString(17));
//                    value.setD4(reset.getString(10));
//                    value.setD5(reset.getString(11));
//                    value.setD7(reset.getString(12));
//                    value.setD13(reset.getString(13));
//                    value.setD19(reset.getString(14));
//                    value.setD20(reset.getString(15));
//                    value.setD21(reset.getString(16));
//                    value.setD22(reset.getString(17));                    
                    value.setNHAPTAY(reset.getString(45));
                lstBcqt_NT.add(value);
            }

        } catch (SQLException e) {
            System.err.print(e.getMessage());
            CoreLogger.error(this.getClass().getName() + " load005TTGS -> " + e.getMessage());
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
    
    public boolean save005TTGS(String khoa, String username, String ngaybc, List<QT_DULIEU_NT> lstData) throws SQLException {
        Connection connection = new DaoConnect().getConnect();
        Object array[] = lstData.toArray();
        ArrayDescriptor des = ArrayDescriptor
                .createDescriptor(QT_DULIEU_NT.ORACLE_TABLE_TYPE, connection);
        ARRAY array_to_pass = new ARRAY(des, connection, array);
        CallableStatement cs = null;
        try {
            cs = connection.prepareCall("{call IMS_SBV.SP_SAVE_005TTGS(?, ?, ?,  ?)}");
            cs.setString(1, khoa);
            cs.setString(2, username);
            cs.setString(3, ngaybc);
            cs.setArray(4, array_to_pass);
            cs.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Loi trong ham save005TTGS " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " save005TTGS -> " + e.getMessage());
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
    
    public List<QT_DULIEU_NT> loadTestChar(String Khoa, String Ngaybc, String UserName,String sGrade, List<String> lstArrPoscd) throws SQLException {
        List<QT_DULIEU_NT> lstBcqt_NT = new ArrayList<QT_DULIEU_NT>();

        DaoConnect daoconnect = new DaoConnect();                        
        Connection conn = null;
        conn = daoconnect.getConnect();
        CallableStatement calstatement = null;
        
        ArrayDescriptor des = ArrayDescriptor.createDescriptor("POS_CD", conn);
            String[] arrayPoscd = lstArrPoscd.toArray(new String[0]);
            ARRAY oracle_arrayPoscd = new ARRAY(des, conn, arrayPoscd);
            
        //Khoi tao procedure cung voi tham so truyen vao la dau ?
        String strStoreproce = "{call IMS_SBV.SP_LOAD_TESTCHART(?,?,?,?,?,?)}";
        ResultSet reset = null;

        try {
            //Khoi tao goi store
            calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            calstatement.registerOutParameter(6, oracle.jdbc.OracleTypes.CURSOR);
            calstatement.setString(1, Khoa);
            calstatement.setString(2, Ngaybc);
            calstatement.setString(3, UserName);
            calstatement.setString(4, sGrade);            
            calstatement.setArray(5, oracle_arrayPoscd);
            //Thuc hien execute lay du lieu
            calstatement.execute();
                //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
            //Lay cursor ra resultset
            reset = (ResultSet) calstatement.getObject(6);
            while (reset.next()) {
//                KHOA, MA, TT_HIENTHI, TEN, NGAYBC, NAMBC, MAPGD, MACN, 
//                    D2, D4, D5, D7, D13, D19, D20, D21, D22, NHAPTAY
                QT_DULIEU_NT value = QT_DULIEU_NT.newInstance();
                
                    String Ma = reset.getString(2);
                    value.setKHOA(reset.getString(1));
                    value.setMA(Ma);
                    value.setTT_HIENTHI(reset.getString(3));
                    value.setTEN(reset.getString(4));
                    value.setNGAYBC(reset.getDate(5));
                    value.setNAMBC(reset.getInt(6));
                    value.setMAPGD(reset.getString(7));
                    value.setMACN(reset.getString(8));
                    value.setD2(reset.getString(9));
                    value.setD4(reset.getString(10));
                    value.setD5(reset.getString(11));
                    value.setD7(reset.getString(12));
                    value.setD13(reset.getString(13));
                    value.setD19(reset.getString(14));
                    value.setD20(reset.getString(15));
                    value.setD21(reset.getString(16));
                    value.setD22(reset.getString(17));                    
                    value.setNHAPTAY(reset.getString(18));
                lstBcqt_NT.add(value);
            }

        } catch (SQLException e) {
            System.err.print(e.getMessage());
            CoreLogger.error(this.getClass().getName() + " load004TTGS -> " + e.getMessage());
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
}
