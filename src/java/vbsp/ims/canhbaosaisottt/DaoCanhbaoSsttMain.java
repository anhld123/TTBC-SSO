/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.canhbaosaisottt;

import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;
import vbsp.ims.bcqt.model.QT_DULIEU_NT;
import vbsp.ims.chamdiemcn.ModelExcelFile;
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
public class DaoCanhbaoSsttMain {


    public static DaoCanhbaoSsttMain newInstance() {
        return new DaoCanhbaoSsttMain();
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
        String strStoreproce = "{call VBSP_IMS_CANHBAOSS.SP_GET_POS_BY_NAME(?,?)}";
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
            String strStoreproce = "{call VBSP_IMS_CANHBAOSS.SP_LOAD_ALL_BAOCAO(?,?,?,?,?)}";
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



    public List<ModelTreeNode> getDataPosTreeNode(Connection conn, String strUserName, String sGrade, String mabc) throws SQLException {
        List<ModelTreeNode> lstPo = new ArrayList<ModelTreeNode>();
        try {
//            DaoConnect daoconnect = new DaoConnect();
//            Connection conn = null;
//            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call VBSP_IMS_CANHBAOSS.SP_LOAD_POS_TREE(?,?,?,?,?,?)}";
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
            String strStoreproce = "{call VBSP_IMS_CANHBAOSS.SP_LOAD_PARA_BAOCAO(?, ?, ?, ?, ?,?,?)}";
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
            String strStoreproce = "{call VBSP_IMS_CANHBAOSS.SP_GET_DMKHAC(?,?,?,?)}";
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

    public HashMap<String, String> getQueryTableDetail(String khoa,  String ngaybc, String mapgd,String mact, String username, String Capbc, String macb) throws SQLException, Exception {
        String outtable = "", thuyetminh = "", tenchitieu = "";
        Connection conn = null;
        ResultSet reset = null;
        CallableStatement calstatement = null;
        HashMap<String, String> mapvalue = new HashMap<String, String>();
        try {
            conn = new DaoConnect().getConnect();

            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call VBSP_IMS_CANHBAOSS.sp_get_table_detail(?,?,?,?,?,?,?,?,?,?)}";
            ArrayDescriptor des = ArrayDescriptor.createDescriptor("POS_CD", conn);
            //Khoi tao goi store
            calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            calstatement.registerOutParameter(8, oracle.jdbc.OracleTypes.CURSOR);
            calstatement.registerOutParameter(9, oracle.jdbc.OracleTypes.CLOB);
            calstatement.registerOutParameter(10, oracle.jdbc.OracleTypes.VARCHAR);
            calstatement.setString(1, khoa);
            calstatement.setString(2,ngaybc );
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

   public List<QT_DULIEU_NT> getDataCBSS01_Main(Connection conn, String sKhoa, String sNgaybc, String sUser,
            String sGrade, List<String> lstArrPoscd, String poscd) {
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
            String strStoreproce = "{call VBSP_IMS_CANHBAOSS.SP_GET_DATA_CBSS01_MAIN(?,?,?,?,?,?,?,?,?)}";
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
                calstatement.setString(9, poscd);
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

   public List<QT_DULIEU_NT> getDataCBSS01_Detail(Connection conn, String sKhoa, String sNgaybc, String sUser,
            String sGrade, List<String> lstArrPoscd, String poscd) {
        List<QT_DULIEU_NT> lstBcqt_NT = new ArrayList<QT_DULIEU_NT>();
        try {
            ArrayDescriptor des = ArrayDescriptor.createDescriptor("POS_CD", conn);
            String[] arrayPoscd = lstArrPoscd.toArray(new String[0]);
            ARRAY oracle_arrayPoscd = new ARRAY(des, conn, arrayPoscd);
            CallableStatement calstatement = null;
            // Thực hiện phân trang - Fix 50 (trong DB) dòng tránh gọi DB nhiều lần
            // Thực hiện lọc giá trị
            String strStoreproce = "{call VBSP_IMS_CANHBAOSS.SP_GET_DATA_CBSS01_DETAIL(?,?,?,?,?,?,?,?,?)}";
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
                calstatement.setString(9, poscd);
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
                    value.setD15(reset.getString(35)); //Nội dung mắc phải
                    value.setD16(reset.getString(36)); // Giải trình
                    value.setD17(reset.getString(37)); // Khoá để cập nhật
                    value.setNHAPTAY(reset.getString(45));
                    value.setFONTFORMAT(reset.getString(52)); //Mẫu biểu
                    value.setD29(reset.getString(53)); // Readonly
                    value.setD30(reset.getString(54)); // Nút duyệt
                    lstBcqt_NT.add(value);
                }
                reset.getRow();

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

   public boolean saveP001(String khoa, String username, String mapgd, String ngaybc, List<QT_DULIEU_NT> lstData) throws SQLException {
        Connection connection = new DaoConnect().getConnect(); // Kết nối dữ liệu
        //------Chuyển rạng mảng thành Object của Oracle
        Object array[] = lstData.toArray();
        ArrayDescriptor des = ArrayDescriptor
                .createDescriptor(QT_DULIEU_NT.ORACLE_TABLE_TYPE, connection);
        ARRAY array_to_pass = new ARRAY(des, connection, array);
        //---------------------------------------------------------------------------
        CallableStatement cs = null;
        try {
            cs = connection.prepareCall("{call VBSP_IMS_CANHBAOSS.SP_SAVE_P001(?, ?, ?, ?, ? )}");
            cs.setString(1, khoa);
            cs.setString(2, username);
            cs.setString(3, mapgd);
            cs.setString(4, ngaybc);
            cs.setArray(5, array_to_pass);
            cs.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Loi trong ham saveP001 " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " saveP001 -> " + e.getMessage());
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
   
   public boolean saveP001_tmp(String khoa, String username, String mapgd, String ngaybc, List<QT_DULIEU_NT> lstData) throws SQLException {
        Connection connection = new DaoConnect().getConnect(); // Kết nối dữ liệu
        //------Chuyển rạng mảng thành Object của Oracle
        Object array[] = lstData.toArray();
        ArrayDescriptor des = ArrayDescriptor
                .createDescriptor(QT_DULIEU_NT.ORACLE_TABLE_TYPE, connection);
        ARRAY array_to_pass = new ARRAY(des, connection, array);
        //---------------------------------------------------------------------------
        CallableStatement cs = null;
        try {
            cs = connection.prepareCall("{call VBSP_IMS_CANHBAOSS.SP_SAVE_P001_TMP(?, ?, ?, ?, ? )}");
            cs.setString(1, khoa);
            cs.setString(2, username);
            cs.setString(3, mapgd);
            cs.setString(4, ngaybc);
            cs.setArray(5, array_to_pass);
            cs.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Loi trong ham saveP001 " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " saveP001 -> " + e.getMessage());
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

   public String authDetail(String Khoa,  String username, String capbc, String ngaybc,  String poscd) throws SQLException {
        List<QT_DULIEU_NT> lstBcqt_NT = new ArrayList<QT_DULIEU_NT>();
        DaoConnect daoconnect = new DaoConnect();
        Connection conn = null;
        conn = daoconnect.getConnect();
        String sCountTotalCust = "0";
        try {
            ArrayDescriptor des = ArrayDescriptor.createDescriptor("POS_CD", conn);
//            String[] arrayPoscd = lstMapgd.toArray(new String[0]);
//            ARRAY oracle_arrayPoscd = new ARRAY(des, conn, arrayPoscd);
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{?=call VBSP_IMS_CANHBAOSS.F_AUTH_DETAIL(?,?,?,?, ?,?,?)}";
            ResultSet reset = null;

            calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            calstatement.registerOutParameter(1, oracle.jdbc.OracleTypes.VARCHAR);
                calstatement.registerOutParameter(7, oracle.jdbc.OracleTypes.NUMBER);
                calstatement.registerOutParameter(8, oracle.jdbc.OracleTypes.VARCHAR);
            //Tham so thu nhat truyen vao la co lay theo pos hay main pos
             calstatement.setString(2, Khoa);
             calstatement.setString(3, username);
             calstatement.setString(4, capbc);
             calstatement.setString(5, ngaybc);
             calstatement.setString(6, poscd);

//            calstatement.setArray(5, oracle_arrayPoscd);
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
            System.err.println("Loi trong ham authDetail " + e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " authDetail -> " + e.getMessage());
        }
        return sCountTotalCust;
    }

   public List<String> getDataSendCBSS(String type, String khoa, String mapgd, String ngay_bc) throws SQLException {
        List<String> lstData = new ArrayList<>();
        DaoConnect daoconnect = new DaoConnect();
        Connection conn = null;
        conn = daoconnect.getConnect();
        CallableStatement calstatement = null;
        ResultSet reset = null;
//        try {
        //Khoi tao procedure cung voi tham so truyen vao la dau ?
        String strStoreproce = "{call VBSP_IMS_CANHBAOSS.SP_GET_DATA_CBSS_SYNC(?,?,?,?,?,?,?)}";
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

   public boolean insert_xlx_giaitrinh(String mabc, String poscd, String fileName, Date ngaybc, String username, List<ModelExcelFile> lstExcel, String ma) throws Exception, SQLException {
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
                sInsert = "insert into CBSS_DULIEU_TMP(KHOA_1, KHOA_2, D1, D2, D3, D4, D5, D6, D7, D8, D9, D10, D11, D12, D13, D14, D15, D16, D17, D18, D19, D20, D21, D22, D23, D24, D25, MAPGD, MAKH)\n"
                            + "values(?,? ,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?, ?)";
                    conn.setAutoCommit(false);
                    insert = conn.prepareStatement(sInsert);
                    Delete = "delete from CBSS_DULIEU_TMP where KHOA_1||KHOA_2 = ?";
                    statementDelete = conn.prepareCall(Delete);
//                    statementDelete.setDate(1, new java.sql.Date(ngaybc.getTime()));
                    statementDelete.setString(1, mabc);

                    statementDelete.execute();
                    for (int i = 0; i < lstExcel.size(); i++) {
                        ModelExcelFile value = lstExcel.get(i);
//                        insert.setString(1, fileName);
//                        insert.setDate(2, new java.sql.Date(ngaybc.getTime()));
                        insert.setString(1, mabc.substring(0, 5));
//                        insert.setString(4, poscd);
                        insert.setString(2, mabc.substring(5,10));

                        insert.setString(3, value.getC2());
                        insert.setString(4, value.getC3());
                        insert.setString(5, value.getN1());
                        insert.setString(6, value.getN2());
                        insert.setString(7, value.getN3());
                        insert.setString(8, value.getN4());

                        insert.setString(9, value.getN5());
                        insert.setString(10, value.getN6());
                        insert.setString(11, value.getN7());
                        insert.setString(12, value.getN8());
                        insert.setString(13, value.getN9());
                        insert.setString(14, value.getN10());
                        insert.setString(15, value.getN11());
                        insert.setString(16,value.getN12());

                        insert.setString(17, value.getN13());
                        insert.setString(18, value.getN14());
                        insert.setString(19, value.getN15());
                        insert.setString(20, value.getN16());
                        insert.setString(21, value.getN17());
                        insert.setString(22, value.getN18());
                        insert.setString(23, value.getN19());
                        insert.setString(24, value.getN20());
                        insert.setString(25, value.getN21());
                        insert.setString(26, value.getN22());
                        insert.setString(27, value.getN23());
                        insert.setString(27, value.getN23());
                        insert.setString(28, poscd);
                        insert.setString(29, ma);

                        insert.execute();
                    }
//                    Delete = "delete from dulieu_nt where (D2 is null or D4 is null)  and khoa = ?";
//                    statementDelete = conn.prepareCall(Delete);
////                    statementDelete.setDate(1, new java.sql.Date(ngaybc.getTime()));
//                    statementDelete.setString(1, mabc);
//
//                    statementDelete.execute();

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

   public boolean mergeAfterUpFile(String khoa, String username, String mapgd, String ngaybc, String maUpload, String capbc) throws SQLException {
        Connection connection = new DaoConnect().getConnect(); // Kết nối dữ liệu

        //---------------------------------------------------------------------------
        CallableStatement cs = null;
        try {
            cs = connection.prepareCall("{call VBSP_IMS_CANHBAOSS.SP_MERGER_AFTER_UPFILE(?, ?, ?, ?, ?, ? )}");
            cs.setString(1, khoa);
            cs.setString(2, username);
            cs.setString(3, mapgd);
            cs.setString(4, ngaybc);
            cs.setString(5, maUpload);
            cs.setString(6, capbc);

            cs.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Loi trong ham mergeAfterUpFile " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " mergeAfterUpFile -> " + e.getMessage());
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
}
