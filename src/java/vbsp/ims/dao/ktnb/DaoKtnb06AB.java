package vbsp.ims.dao.ktnb;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;
import vbsp.ims.dao.DaoConnect;
import vbsp.ims.dao.khnv.DaoXdkh;
import vbsp.ims.log.CoreLogger;
import vbsp.ims.model.ktnb.Ktnb01Model;
import vbsp.ims.model.ktnb.Ktnb06Model;

/**
 *
 * @author CuongBM0211
 */
public class DaoKtnb06AB {
    public ArrayList<Ktnb06Model> get_ktnb06(String posCD, int namBc, int quyBc) {
        ArrayList<Ktnb06Model> dataList = new ArrayList<Ktnb06Model>();
        boolean bSuccess = false;
        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call VBSP_RPT_KTKTNB.p_get_ktnb06(?, ?, ?, ?, ?, ?)}";
            ResultSet reset = null;

            try {
                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                //Tham so thu nhat module_id 
                calstatement.setString(1, posCD);
                calstatement.setInt(2, namBc);
                calstatement.setInt(3, quyBc);
        
                calstatement.registerOutParameter(4, oracle.jdbc.OracleTypes.NUMBER);
                calstatement.registerOutParameter(5, oracle.jdbc.OracleTypes.VARCHAR);
                calstatement.registerOutParameter(6, oracle.jdbc.OracleTypes.CURSOR);
                
                //Thuc hien execute lay du lieu
                calstatement.execute();
                
                //Lay cursor ra resultset
                reset = (ResultSet) calstatement.getObject(6);
                
                while (reset.next()) {
                    Ktnb06Model obj = new Ktnb06Model();
                    
                    obj.setKT_KHOA(reset.getString("KT_KHOA"));
                    obj.setKT_MAPGD(reset.getString("KT_MAPGD"));
                    obj.setKT_MACN(reset.getString("KT_MACN"));
                    obj.setKT_QUYBC(reset.getDouble("KT_QUYBC"));
                    obj.setKT_NAMBC(reset.getDouble("KT_NAMBC"));
                    obj.setKT_NGAY_NHAP(reset.getDate("KT_NGAY_NHAP"));
                    obj.setKT_NGUOI_NHAP(reset.getString("KT_NGUOI_NHAP"));
                    obj.setKT_STT_HT(reset.getString("KT_STT_HT"));
                    obj.setKT_DT(reset.getString("KT_DT"));
                    obj.setKT_TDDN_SV(reset.getDouble("KT_TDDN_SV"));
                    obj.setKT_TDDN_ST_G(reset.getDouble("KT_TDDN_ST_G"));
                    obj.setKT_TDDN_ST_L(reset.getDouble("KT_TDDN_ST_L"));
                    obj.setKT_TDDN_ST_TK(reset.getDouble("KT_TDDN_ST_TK"));
                    obj.setKT_PHTK_SV(reset.getDouble("KT_PHTK_SV"));
                    obj.setKT_PHTK_ST_G(reset.getDouble("KT_PHTK_ST_G"));
                    obj.setKT_PHTK_ST_L(reset.getDouble("KT_PHTK_ST_L"));
                    obj.setKT_PHTK_ST_TK(reset.getDouble("KT_PHTK_ST_TK"));
                    obj.setKT_DTH_SV(reset.getDouble("KT_DTH_SV"));
                    obj.setKT_DTH_ST_G(reset.getDouble("KT_DTH_ST_G"));
                    obj.setKT_DTH_ST_L(reset.getDouble("KT_DTH_ST_L"));
                    obj.setKT_DTH_ST_TK(reset.getDouble("KT_DTH_ST_TK"));
                    obj.setKT_TDCK_SV(reset.getDouble("KT_TDCK_SV"));
                    obj.setKT_TDCK_ST_G(reset.getDouble("KT_TDCK_ST_G"));
                    obj.setKT_TDCK_ST_L(reset.getDouble("KT_TDCK_ST_L"));
                    obj.setKT_TDCK_ST_TK(reset.getDouble("KT_TDCK_ST_TK"));
                    obj.setKT_GHICHU(reset.getString("KT_GHICHU"));
                    obj.setKT_DN(reset.getString("KT_DN"));
                    obj.setKT_CO_DINH(reset.getString("KT_CO_DINH"));
                    obj.setKT_THEM(reset.getString("KT_THEM"));
                    obj.setKT_XOA(reset.getString("KT_XOA"));
                    obj.setKT_FONTWEIGHT(reset.getString("KT_FONTWEIGHT"));
                    obj.setKT_CAPHT(reset.getDouble("KT_CAPHT"));
                    obj.setKT_STT(reset.getDouble("KT_STT"));
                    obj.setNG_CAPNHAT(reset.getString("NG_CAPNHAT"));


                    //Them vao list
                    dataList.add(obj);
                }
                
                //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
                int pn_err_cd = calstatement.getInt(4);
                //thu hien lay mo ta loi
                String strEdd_txt = calstatement.getString(5);
                //Lay cursor ra resultset

                if (calstatement != null) {
                    calstatement.close();
                }
                if (conn != null) {
                    conn.close();
                }
                bSuccess = true;
            } catch (SQLException e) {
                System.err.print(e.getMessage());
                CoreLogger.error(this.getClass().getName() + " get_ktnb06 -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham get_ktnb06 " + e.getMessage());
            CoreLogger.error(this.getClass().getName()+ " get_ktnb06 -> " + e.getMessage());
        }
        return dataList;
    }
    
    public ArrayList<Ktnb06Model> get_ktnb06_default(String posCD, int namBc, int quyBc) {
        ArrayList<Ktnb06Model> dataList = new ArrayList<Ktnb06Model>();
        boolean bSuccess = false;
        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call VBSP_RPT_KTKTNB.p_get_ktnb06_default(?, ?, ?, ?, ?, ?)}";
            ResultSet reset = null;

            try {
                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                //Tham so thu nhat module_id 
                calstatement.setString(1, posCD);
                calstatement.setInt(2, namBc);
                calstatement.setInt(3, quyBc);
        
                calstatement.registerOutParameter(4, oracle.jdbc.OracleTypes.NUMBER);
                calstatement.registerOutParameter(5, oracle.jdbc.OracleTypes.VARCHAR);
                calstatement.registerOutParameter(6, oracle.jdbc.OracleTypes.CURSOR);
                
                //Thuc hien execute lay du lieu
                calstatement.execute();
                
                //Lay cursor ra resultset
                reset = (ResultSet) calstatement.getObject(6);
                
                while (reset.next()) {
                    Ktnb06Model obj = new Ktnb06Model();
                    
                    obj.setKT_KHOA(reset.getString("KT_KHOA"));
                    obj.setKT_MAPGD(reset.getString("KT_MAPGD"));
                    obj.setKT_MACN(reset.getString("KT_MACN"));
                    obj.setKT_QUYBC(reset.getDouble("KT_QUYBC"));
                    obj.setKT_NAMBC(reset.getDouble("KT_NAMBC"));
                    obj.setKT_NGAY_NHAP(reset.getDate("KT_NGAY_NHAP"));
                    obj.setKT_NGUOI_NHAP(reset.getString("KT_NGUOI_NHAP"));
                    obj.setKT_STT_HT(reset.getString("KT_STT_HT"));
                    obj.setKT_DT(reset.getString("KT_DT"));
                    obj.setKT_TDDN_SV(reset.getDouble("KT_TDDN_SV"));
                    obj.setKT_TDDN_ST_G(reset.getDouble("KT_TDDN_ST_G"));
                    obj.setKT_TDDN_ST_L(reset.getDouble("KT_TDDN_ST_L"));
                    obj.setKT_TDDN_ST_TK(reset.getDouble("KT_TDDN_ST_TK"));
                    obj.setKT_PHTK_SV(reset.getDouble("KT_PHTK_SV"));
                    obj.setKT_PHTK_ST_G(reset.getDouble("KT_PHTK_ST_G"));
                    obj.setKT_PHTK_ST_L(reset.getDouble("KT_PHTK_ST_L"));
                    obj.setKT_PHTK_ST_TK(reset.getDouble("KT_PHTK_ST_TK"));
                    obj.setKT_DTH_SV(reset.getDouble("KT_DTH_SV"));
                    obj.setKT_DTH_ST_G(reset.getDouble("KT_DTH_ST_G"));
                    obj.setKT_DTH_ST_L(reset.getDouble("KT_DTH_ST_L"));
                    obj.setKT_DTH_ST_TK(reset.getDouble("KT_DTH_ST_TK"));
                    obj.setKT_TDCK_SV(reset.getDouble("KT_TDCK_SV"));
                    obj.setKT_TDCK_ST_G(reset.getDouble("KT_TDCK_ST_G"));
                    obj.setKT_TDCK_ST_L(reset.getDouble("KT_TDCK_ST_L"));
                    obj.setKT_TDCK_ST_TK(reset.getDouble("KT_TDCK_ST_TK"));
                    obj.setKT_GHICHU(reset.getString("KT_GHICHU"));
                    obj.setKT_DN(reset.getString("KT_DN"));
                    obj.setKT_CO_DINH(reset.getString("KT_CO_DINH"));
                    obj.setKT_THEM(reset.getString("KT_THEM"));
                    obj.setKT_XOA(reset.getString("KT_XOA"));
                    obj.setKT_FONTWEIGHT(reset.getString("KT_FONTWEIGHT"));
                    obj.setKT_CAPHT(reset.getDouble("KT_CAPHT"));
                    obj.setKT_STT(reset.getDouble("KT_STT"));
                    obj.setNG_CAPNHAT(reset.getString("NG_CAPNHAT"));


                    //Them vao list
                    dataList.add(obj);
                }
                
                //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
                int pn_err_cd = calstatement.getInt(4);
                //thu hien lay mo ta loi
                String strEdd_txt = calstatement.getString(5);
                //Lay cursor ra resultset

                if (calstatement != null) {
                    calstatement.close();
                }
                if (conn != null) {
                    conn.close();
                }
                bSuccess = true;
            } catch (SQLException e) {
                System.err.print(e.getMessage());
                CoreLogger.error(this.getClass().getName() + " get_ktnb06 -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham get_ktnb06 " + e.getMessage());
            CoreLogger.error(this.getClass().getName()+ " get_ktnb06 -> " + e.getMessage());
        }
        return dataList;
    }
    
    public boolean save_ktnb06(String posCD,String maCn,String quyBc,String namBc,String userId,
            List<String> KT_KHOA,List<String> p3, List<String> p4,List<String> p5,List<String> p6,List<String> p7,
            List<String> p8,List<String> p9,List<String> p10,List<String> p11,List<String> p12,List<String> p13,
            List<String> p14,List<String> p15,List<String> p16,List<String> p17,List<String> p18, String strAuth){
        boolean bSuccess = false;
        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            
            ArrayDescriptor des = ArrayDescriptor.createDescriptor("ARRAY_TABLE", conn);
            
                
            String[] arrayKhoa = KT_KHOA.toArray(new String[0]);   //Ma Chi Tieu
            
            String[] array3 = p3.toArray(new String[0]); //Uoc Thuc Hien
            String[] array4 = p4.toArray(new String[0]); //Uoc Thuc Hien
            String[] array5 = p5.toArray(new String[0]); //KH nam
            String[] array6 = p6.toArray(new String[0]); //Uoc Thuc Hien
            String[] array7 = p7.toArray(new String[0]); //KH nam
            String[] array8 = p8.toArray(new String[0]); //Uoc Thuc Hien
            String[] array9 = p9.toArray(new String[0]); //KH nam
            String[] array10 = p10.toArray(new String[0]); //Uoc Thuc Hien
            String[] array11 = p11.toArray(new String[0]); //KH nam
            String[] array12 = p12.toArray(new String[0]); //Uoc Thuc Hien
            String[] array13 = p13.toArray(new String[0]); //Uoc Thuc Hien
            String[] array14 = p14.toArray(new String[0]); //Uoc Thuc Hien
            String[] array15 = p15.toArray(new String[0]); //KH nam
            String[] array16 = p16.toArray(new String[0]); //Uoc Thuc Hien
            String[] array17 = p17.toArray(new String[0]); //Uoc Thuc Hien
            String[] array18 = p18.toArray(new String[0]); //Uoc Thuc Hien

            
            //CuongBM: Convert array --> array oracle su dung
            ARRAY oracle_arrayKhoa = new ARRAY(des,conn,arrayKhoa);
            ARRAY oracle_array3 = new ARRAY(des,conn,array3);
            ARRAY oracle_array4 = new ARRAY(des,conn,array4);
            ARRAY oracle_array5 = new ARRAY(des,conn,array5);
            ARRAY oracle_array6 = new ARRAY(des,conn,array6);
            ARRAY oracle_array7 = new ARRAY(des,conn,array7);
            ARRAY oracle_array8 = new ARRAY(des,conn,array8);
            ARRAY oracle_array9 = new ARRAY(des,conn,array9);
            ARRAY oracle_array10 = new ARRAY(des,conn,array10);
            ARRAY oracle_array11 = new ARRAY(des,conn,array11);
            ARRAY oracle_array12 = new ARRAY(des,conn,array12);
            ARRAY oracle_array13 = new ARRAY(des,conn,array13);
            ARRAY oracle_array14 = new ARRAY(des,conn,array14);
            ARRAY oracle_array15 = new ARRAY(des,conn,array15);
            ARRAY oracle_array16 = new ARRAY(des,conn,array16);
            ARRAY oracle_array17 = new ARRAY(des,conn,array17);
            ARRAY oracle_array18 = new ARRAY(des,conn,array18);
            
            //CuongBM: 06Nov14
            String strStoreproce = "{call VBSP_RPT_KTKTNB.p_save_ktnb06(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
//            ResultSet reset = null;

            try {
                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                
                calstatement.setString(1, posCD);
                calstatement.setString(2, maCn);
                calstatement.setString(3, namBc);
                calstatement.setString(4, quyBc);
                calstatement.setString(5, userId);
                calstatement.setArray(6, oracle_arrayKhoa);
                calstatement.setArray(7, oracle_array3);
                calstatement.setArray(8, oracle_array4);
                calstatement.setArray(9, oracle_array5);
                calstatement.setArray(10, oracle_array6);
                calstatement.setArray(11, oracle_array7);
                calstatement.setArray(12, oracle_array8);
                calstatement.setArray(13, oracle_array9);
                calstatement.setArray(14, oracle_array10);
                calstatement.setArray(15, oracle_array11);
                calstatement.setArray(16, oracle_array12);
                calstatement.setArray(17, oracle_array13);
                calstatement.setArray(18, oracle_array14);
                calstatement.setArray(19, oracle_array15);
                calstatement.setArray(20, oracle_array16);
                calstatement.setArray(21, oracle_array17);
                calstatement.setArray(22, oracle_array18);
                calstatement.registerOutParameter(23, oracle.jdbc.OracleTypes.NUMBER);
                calstatement.registerOutParameter(24, oracle.jdbc.OracleTypes.VARCHAR);
                calstatement.setString(25, strAuth);
                
                //Thuc hien execute lay du lieu
                calstatement.execute();
                
                //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
                int pn_err_cd = calstatement.getInt(23);
                
                //thu hien lay mo ta loi
                String strEdd_txt = calstatement.getString(24);
                //Lay cursor ra resultset
                System.err.print(strEdd_txt);
                if(pn_err_cd != 0)
                        return false;
                if (calstatement != null) {
                    calstatement.close();
                }
                if (conn != null) {
                    conn.close();
                }
                bSuccess = true;
            } catch (SQLException e) {
                System.err.print(e.getMessage());
                CoreLogger.error(this.getClass().getName() + " save_ktnb06 -> " + e.getMessage());
                return false;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DaoKtnb06AB.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Loi trong ham save_ktnb06_ "+posCD+" " + ex.getMessage());
            CoreLogger.error(this.getClass().getName()+ " save_ktnb06 -> "+posCD+" " + ex.getMessage());
            return false;
        }
        return true; 
    }
    
    public ArrayList<Ktnb06Model> get_ktnb06_auth(String posCD, int namBc, int quyBc,String auth) {
        ArrayList<Ktnb06Model> dataList = new ArrayList<Ktnb06Model>();
        boolean bSuccess = false;
        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call VBSP_RPT_KTKTNB.p_get_ktnb06_auth(?, ?, ?, ?, ?, ?, ?)}";
            ResultSet reset = null;

            try {
                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
//                calstatement = conn.prepareCall(strQuery, ResultSet.TYPE_FORWARD_ONLY,
//                    ResultSet.CONCUR_READ_ONLY);
                //Tham so thu nhat module_id 
                calstatement.setString(1, posCD);
                calstatement.setInt(2, namBc);
                calstatement.setInt(3, quyBc);
        
                calstatement.registerOutParameter(4, oracle.jdbc.OracleTypes.NUMBER);
                calstatement.registerOutParameter(5, oracle.jdbc.OracleTypes.VARCHAR);
                calstatement.registerOutParameter(6, oracle.jdbc.OracleTypes.CURSOR);
                calstatement.setString(7, auth);
                
                //Thuc hien execute lay du lieu
                calstatement.execute();
                
                //Lay cursor ra resultset
                reset = (ResultSet) calstatement.getObject(6);
                
                while (reset.next()) {
                    Ktnb06Model obj = new Ktnb06Model();
                    
                    obj.setKT_KHOA(reset.getString("KT_KHOA"));
                    obj.setKT_MAPGD(reset.getString("KT_MAPGD"));
                    obj.setKT_MACN(reset.getString("KT_MACN"));
                    obj.setKT_QUYBC(reset.getDouble("KT_QUYBC"));
                    obj.setKT_NAMBC(reset.getDouble("KT_NAMBC"));
                    obj.setKT_NGAY_NHAP(reset.getDate("KT_NGAY_NHAP"));
                    obj.setKT_NGUOI_NHAP(reset.getString("KT_NGUOI_NHAP"));
                    obj.setKT_STT_HT(reset.getString("KT_STT_HT"));
                    obj.setKT_DT(reset.getString("KT_DT"));
                    obj.setKT_TDDN_SV(reset.getDouble("KT_TDDN_SV"));
                    obj.setKT_TDDN_ST_G(reset.getDouble("KT_TDDN_ST_G"));
                    obj.setKT_TDDN_ST_L(reset.getDouble("KT_TDDN_ST_L"));
                    obj.setKT_TDDN_ST_TK(reset.getDouble("KT_TDDN_ST_TK"));
                    obj.setKT_PHTK_SV(reset.getDouble("KT_PHTK_SV"));
                    obj.setKT_PHTK_ST_G(reset.getDouble("KT_PHTK_ST_G"));
                    obj.setKT_PHTK_ST_L(reset.getDouble("KT_PHTK_ST_L"));
                    obj.setKT_PHTK_ST_TK(reset.getDouble("KT_PHTK_ST_TK"));
                    obj.setKT_DTH_SV(reset.getDouble("KT_DTH_SV"));
                    obj.setKT_DTH_ST_G(reset.getDouble("KT_DTH_ST_G"));
                    obj.setKT_DTH_ST_L(reset.getDouble("KT_DTH_ST_L"));
                    obj.setKT_DTH_ST_TK(reset.getDouble("KT_DTH_ST_TK"));
                    obj.setKT_TDCK_SV(reset.getDouble("KT_TDCK_SV"));
                    obj.setKT_TDCK_ST_G(reset.getDouble("KT_TDCK_ST_G"));
                    obj.setKT_TDCK_ST_L(reset.getDouble("KT_TDCK_ST_L"));
                    obj.setKT_TDCK_ST_TK(reset.getDouble("KT_TDCK_ST_TK"));
                    obj.setKT_GHICHU(reset.getString("KT_GHICHU"));
                    obj.setKT_DN(reset.getString("KT_DN"));
                    obj.setKT_CO_DINH(reset.getString("KT_CO_DINH"));
                    obj.setKT_THEM(reset.getString("KT_THEM"));
                    obj.setKT_XOA(reset.getString("KT_XOA"));
                    obj.setKT_FONTWEIGHT(reset.getString("KT_FONTWEIGHT"));
                    obj.setKT_CAPHT(reset.getDouble("KT_CAPHT"));
                    obj.setKT_STT(reset.getDouble("KT_STT"));
                    obj.setNG_CAPNHAT(reset.getString("NG_CAPNHAT"));
  

                    //Them vao list
                    dataList.add(obj);
                }
                
                //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
                int pn_err_cd = calstatement.getInt(4);
                //thu hien lay mo ta loi
                String strEdd_txt = calstatement.getString(5);
                //Lay cursor ra resultset

                if (calstatement != null) {
                    calstatement.close();
                }
                if (conn != null) {
                    conn.close();
                }
                bSuccess = true;
            } catch (SQLException e) {
                System.err.print(e.getMessage());
                CoreLogger.error(this.getClass().getName() + " get_ktnb06 -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham get_ktnb06 " + e.getMessage());
            CoreLogger.error(this.getClass().getName()+ " get_ktnb06 -> " + e.getMessage());
        }
        return dataList;
    }
    
    public boolean save_ktnb06_auth(String posCD ,String quyBc,String namBc){
    
        try {
            //Khai bao các biến để nhận dữ liệu đẩy về
            DaoConnect daoConnect = new DaoConnect();
            Connection con = daoConnect.getConnect();
            Statement stm = con.createStatement();

            //2. INSERT DU LIEU MOI
            String sqlInsert = "";
            sqlInsert = "INSERT INTO KTNB06 " +
                "                   SELECT KT_KHOA, KT_MAPGD, KT_MACN, KT_QUYBC, KT_NAMBC, \n" +
"    KT_NGAY_NHAP, KT_NGUOI_NHAP, KT_STT_HT, KT_DT, KT_TDDN_SV, \n" +
"    KT_TDDN_ST_G, KT_TDDN_ST_L, KT_TDDN_ST_TK, KT_PHTK_SV, KT_PHTK_ST_G, \n" +
"    KT_PHTK_ST_L, KT_PHTK_ST_TK, KT_DTH_SV, KT_DTH_ST_G, KT_DTH_ST_L, \n" +
"    KT_DTH_ST_TK, KT_TDCK_SV, KT_TDCK_ST_G, KT_TDCK_ST_L, KT_TDCK_ST_TK, \n" +
"    KT_GHICHU, KT_DN, KT_CO_DINH, KT_THEM, KT_XOA, \n" +
"    KT_FONTWEIGHT, KT_CAPHT, KT_STT,sysdate,'Y'          \n" +
"                   FROM KTNB06 A\n" +
"                   WHERE KT_MAPGD = '"+ posCD +
"' AND KT_NAMBC = " + namBc +
"                   AND KT_QUYBC = "+ quyBc +
"                   and KT_AUTH is null and NG_CAPNHAT = (select max(NG_CAPNHAT) FROM KTNB06\n" +
"                    WHERE KT_MAPGD = '" + posCD +
"'                    AND KT_NAMBC = " + namBc +
"                    AND KT_QUYBC = " + quyBc + " and KT_AUTH is null)"; 
                     
            stm.executeUpdate(sqlInsert);  
            
            
            if (stm != null) {
                    stm.close();
                }            
            
            if (con != null) {
                con.close();
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DaoKtnb06AB.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Loi trong ham save_ktnb06_auth "+posCD+" " + ex.getMessage());
            CoreLogger.error(this.getClass().getName()+ " save_ktnb06_auth ---------> "+posCD+" " + ex.getMessage());
            return false;
        }
        return true;        
    }
}
