/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vbsp.ims.ketquaKTDC;

import vbsp.ims.khnv2021.dao.*;
import vbsp.ims.dao.khnv.*;
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
import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;
import vbsp.ims.bcqt.model.QT_DULIEU_NT;
import vbsp.ims.dao.DaoConnect;
import vbsp.ims.export.excel.ExportExcelFile;
import vbsp.ims.log.CoreLogger;
import vbsp.ims.model.khnv.POSModel;
import vbsp.ims.model.khnv.XdkhModel;
import vbsp.ims.report.fast.ListValue;
import vbsp.ims.nhaptaycn.action.QT_DULIEU_NT_50;

/**
 *
 * @author CuongBM0211
 */
public class KetQuaKtdcDao {
    
    public List<ListValue> getLOV(String username, String type, String capbc) {
        List<ListValue> lstDMNgNhan = new ArrayList<ListValue>();
        try {
            
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call VBSP_IMS_KETQUA_KTDC.SP_GET_LOV(?, ?, ?, ?, ?, ?)}";
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
                CoreLogger.error(this.getClass().getName() + " VBSP_IMS_KETQUA_KTDC.SP_GET_LOV -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Doi chieu, Phan loai no -> Loi trong ham VBSP_IMS_KETQUA_KTDC.SP_GET_LOV " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " VBSP_IMS_KETQUA_KTDC.SP_GET_LOV -> " + e.getMessage());
        }
        return lstDMNgNhan;
    }
    
   public ArrayList<POSModel> getPosList(String posCD, String maCn, String reportGrade){
        ArrayList<POSModel> posList = new ArrayList<>();
        
        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call VBSP_IMS_KHNV2021.p_get_pos_list(?, ?, ?, ?, ?, ?)}";
            ResultSet rsPosList = null;

            try {
                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                
                //Truyen vao username
                calstatement.setString(1, posCD);          
                calstatement.setString(2, maCn);          
                calstatement.setString(3, reportGrade);          
                
                calstatement.registerOutParameter(4, oracle.jdbc.OracleTypes.VARCHAR);
                calstatement.registerOutParameter(5, oracle.jdbc.OracleTypes.NUMBER);
                calstatement.registerOutParameter(6, oracle.jdbc.OracleTypes.CURSOR);

                //Thuc hien execute lay du lieu
                calstatement.execute();
                //Lay cursor ra resultset
                rsPosList = (ResultSet) calstatement.getObject(6);

                while (rsPosList.next()) {
                    POSModel p = new POSModel();
                    p.setId(rsPosList.getString("PO_MA"));
                    p.setDesc(rsPosList.getString("PO_MA") + " - " + rsPosList.getString("PO_TEN"));

                    posList.add(p);
                }

                if (rsPosList != null) {
                    rsPosList.close();
                }
                if (calstatement != null) {
                    calstatement.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                System.err.println("Loi trong ham getPosList " + e.getMessage());
                CoreLogger.error(POSModel.class.getCanonicalName() + " getPosList  -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham getPosList " + e.getMessage());
            CoreLogger.error(DaoDieuchinhkh.class.getCanonicalName() + " getPosList  -> " + e.getMessage());
        }
        return posList;
    }
   
   public ArrayList<POSModel> getListCombobox(String username, String type, String capbc){
        ArrayList<POSModel> posList = new ArrayList<>();
                
        try {
            
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call VBSP_IMS_KETQUA_KTDC.SP_GET_LOV(?, ?, ?, ?, ?, ?)}";
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
                    POSModel p = new POSModel();
                    p.setId(reset.getString(1));
                    p.setDesc(reset.getString(2));
                    
                  
                    posList.add(p);
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
                CoreLogger.error(this.getClass().getName() + " VBSP_IMS_KETQUA_KTDC.SP_GET_LOV -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Doi chieu, Phan loai no -> Loi trong ham VBSP_IMS_KETQUA_KTDC.SP_GET_LOV " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " VBSP_IMS_KETQUA_KTDC.SP_GET_LOV -> " + e.getMessage());
        }        
        return posList;
    }
   
   public ArrayList<POSModel> getListTo(String maxa, String dvut){
        ArrayList<POSModel> posList = new ArrayList<>();
                
        try {
            
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call VBSP_IMS_KETQUA_KTDC.SP_GET_MATO(?, ?, ?, ?, ?)}";
            ResultSet reset = null;
            try {
                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                calstatement.setString(1, maxa);
                calstatement.setString(2, dvut);
               
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
                    POSModel p = new POSModel();
                    p.setId(reset.getString(1));
                    p.setDesc(reset.getString(2));
                    
                  
                    posList.add(p);
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
                CoreLogger.error(this.getClass().getName() + " VBSP_IMS_KETQUA_KTDC.SP_GET_LOV -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Doi chieu, Phan loai no -> Loi trong ham VBSP_IMS_KETQUA_KTDC.SP_GET_LOV " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " VBSP_IMS_KETQUA_KTDC.SP_GET_LOV -> " + e.getMessage());
        }        
        return posList;
    }
   
   public ArrayList<POSModel> getSubCommuneList(String posCD, String commuuneId, String reportGrade){
        ArrayList<POSModel> posList = new ArrayList<POSModel>();
        
        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call VBSP_IMS_KHNV2021.P_GET_SUBCOMMUNE_LIST(?, ?, ?, ?, ?, ?)}";
            ResultSet rsPosList = null;

            try {
                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                
                //Truyen vao username
                calstatement.setString(1, posCD);          
                calstatement.setString(2, commuuneId);          
                calstatement.setString(3, reportGrade);          
                
                calstatement.registerOutParameter(4, oracle.jdbc.OracleTypes.VARCHAR);
                calstatement.registerOutParameter(5, oracle.jdbc.OracleTypes.NUMBER);
                calstatement.registerOutParameter(6, oracle.jdbc.OracleTypes.CURSOR);

                //Thuc hien execute lay du lieu
                calstatement.execute();
                //Lay cursor ra resultset
                rsPosList = (ResultSet) calstatement.getObject(6);

                while (rsPosList.next()) {
                    POSModel p = new POSModel();
                    p.setId(rsPosList.getString("PO_MA"));
                    p.setDesc(rsPosList.getString("PO_MA") + " - " + rsPosList.getString("PO_TEN"));

                    posList.add(p);
                }

                if (rsPosList != null) {
                    rsPosList.close();
                }
                if (calstatement != null) {
                    calstatement.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                System.err.println("Loi trong ham getPosList " + e.getMessage());
                CoreLogger.error(POSModel.class.getCanonicalName() + " getPosList  -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham getPosList " + e.getMessage());
            CoreLogger.error(DaoDieuchinhkh.class.getCanonicalName() + " getPosList  -> " + e.getMessage());
        }
        return posList;
    }
   
   public List<QT_DULIEU_NT_50> getDataKiemtraLoan( String username,
            String sGrade, String ngaybc, String ngaykt, String doituongKT, String hinhthucKT, String canboKT, String maxa, String dvut, String mato, String textSeach, String mabc) {
        List<QT_DULIEU_NT_50> lstBcqt_NT = new ArrayList<QT_DULIEU_NT_50>();
        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call VBSP_IMS_KETQUA_KTDC.SP_LOAD_KIEMTRA_MONVAY(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
            ResultSet reset = null;

            try {
                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                calstatement.registerOutParameter(12, oracle.jdbc.OracleTypes.NUMBER);
                calstatement.registerOutParameter(13, oracle.jdbc.OracleTypes.VARCHAR);
                calstatement.registerOutParameter(14, oracle.jdbc.OracleTypes.CURSOR);
                calstatement.setString(1, username);
                calstatement.setString(2, sGrade);
                calstatement.setString(3, ngaybc);
                calstatement.setString(4, ngaykt);
                calstatement.setString(5, doituongKT);
                calstatement.setString(6, hinhthucKT);
                calstatement.setString(7, canboKT);
                calstatement.setString(8, maxa);
                calstatement.setString(9, dvut);
                calstatement.setString(10, mato);
                calstatement.setString(11, textSeach);
                calstatement.setString(15, mabc);
                        
                //Thuc hien execute lay du lieu
                calstatement.execute();
                //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
                int pn_err_cd = calstatement.getInt(12);
                //thu hien lay mo ta loi
                String strEdd_txt = calstatement.getString(13);
                //Lay cursor ra resultset
                reset = (ResultSet) calstatement.getObject(14);
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
   
   public boolean saveKTDC(String mabc, String username, String capbc,
           String ngaybc, String ngaykt, String doituongKT, String hinhthucKT, String canboKT, String maxa, String dvut, String mato
          , List<QT_DULIEU_NT_50> lstData,  List<String> lstArrPoscd) throws SQLException {
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
            cs = connection.prepareCall("{call VBSP_IMS_KETQUA_KTDC.SP_SAVE_CHECKLOAN(?, ?, ?, ?, ? ,?, ?, ?, ?,?,?,?,?)}");
            cs.setString(1, mabc);
            cs.setString(2, username);
            cs.setString(3, capbc);
            cs.setString(4, ngaybc);
            cs.setString(5, ngaykt);
            
            cs.setString(6, doituongKT);
            cs.setString(7, hinhthucKT);
            cs.setString(8, canboKT);
            cs.setString(9, maxa);
            cs.setString(10, dvut);
            cs.setString(11, mato);
            cs.setArray(12, array_to_pass);
            
            cs.setArray(13, oracle_arrayPos);
            
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
   
   public List<QT_DULIEU_NT_50> getDuyetDataKiemtraLoan( String username,
            String sGrade,  String ngaykt,  String maxa, String mabc) {
        List<QT_DULIEU_NT_50> lstBcqt_NT = new ArrayList<QT_DULIEU_NT_50>();
        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call VBSP_IMS_KETQUA_KTDC.SP_LOAD_DUYET_KTDC_MONVAY(?,?,?,?,?,?,?,?)}";
            ResultSet reset = null;

            try {
                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                calstatement.registerOutParameter(5, oracle.jdbc.OracleTypes.NUMBER);
                calstatement.registerOutParameter(6, oracle.jdbc.OracleTypes.VARCHAR);
                calstatement.registerOutParameter(7, oracle.jdbc.OracleTypes.CURSOR);
                calstatement.setString(1, username);
                calstatement.setString(2, sGrade);                
                calstatement.setString(3, ngaykt);
                calstatement.setString(4, maxa);
                calstatement.setString(8, mabc);
                        
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
                CoreLogger.error(this.getClass().getName() + " getDataTDNN_01 -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham getDataTDNN_01 " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " getDataTDNN_01 -> " + e.getMessage());
        }
        return lstBcqt_NT;
    }
}
