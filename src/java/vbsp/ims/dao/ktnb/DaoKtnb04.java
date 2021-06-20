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
import vbsp.ims.model.ktnb.Ktnb04Model;

/**
 *
 * @author CuongBM0211
 */
public class DaoKtnb04 {
    public ArrayList<Ktnb04Model> get_ktnb04(String posCD, int namBc, int quyBc) {
        ArrayList<Ktnb04Model> dataList = new ArrayList<Ktnb04Model>();
        boolean bSuccess = false;
        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call VBSP_RPT_KTKTNB.p_get_ktnb04(?, ?, ?, ?, ?, ?)}";
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
                    Ktnb04Model obj = new Ktnb04Model();
                    
                    obj.setKT_KHOA(reset.getString("KT_KHOA"));
                    obj.setKT_MAPGD(reset.getString("KT_MAPGD"));
                    obj.setKT_MACN(reset.getString("KT_MACN"));
                    obj.setKT_QUYBC(reset.getDouble("KT_QUYBC"));
                    obj.setKT_NAMBC(reset.getDouble("KT_NAMBC"));
                    obj.setKT_NGAY_NHAP(reset.getDate("KT_NGAY_NHAP"));
                    obj.setKT_NGUOI_NHAP(reset.getString("KT_NGUOI_NHAP"));
                    obj.setKT_STT_HT(reset.getString("KT_STT_HT"));
                    obj.setKT_LOAI_CT(reset.getString("KT_LOAI_CT"));
                    obj.setKT_KT_SCT(reset.getDouble("KT_KT_SCT"));
                    obj.setKT_KT_ST(reset.getDouble("KT_KT_ST"));
                    obj.setKT_SS_TS_SCT(reset.getDouble("KT_SS_TS_SCT"));
                    obj.setKT_SS_TS_ST(reset.getDouble("KT_SS_TS_ST"));
                    obj.setKT_SS_TS_TLCT(reset.getDouble("KT_SS_TS_TLCT"));
                    obj.setKT_SS_TS_TLST(reset.getDouble("KT_SS_TS_TLST"));
                    obj.setKT_TD_SPL_SCT(reset.getDouble("KT_TD_SPL_SCT"));
                    obj.setKT_TD_SPL_ST(reset.getDouble("KT_TD_SPL_ST"));
                    obj.setKT_TD_KTNV_SL(reset.getDouble("KT_TD_KTNV_SL"));
                    obj.setKT_TD_KTNV_ST(reset.getDouble("KT_TD_KTNV_ST"));
                    obj.setKT_TD_SK_SL(reset.getDouble("KT_TD_SK_SL"));
                    obj.setKT_TD_SK_ST(reset.getDouble("KT_TD_SK_ST"));
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
                CoreLogger.error(this.getClass().getName() + " get_ktnb04 -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham get_ktnb04 " + e.getMessage());
            CoreLogger.error(this.getClass().getName()+ " get_ktnb04 -> " + e.getMessage());
        }
        return dataList;
    }
    
    public ArrayList<Ktnb04Model> get_ktnb04_default(String posCD, int namBc, int quyBc) {
        ArrayList<Ktnb04Model> dataList = new ArrayList<Ktnb04Model>();
        boolean bSuccess = false;
        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call VBSP_RPT_KTKTNB.p_get_ktnb04_default(?, ?, ?, ?, ?, ?)}";
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
                    Ktnb04Model obj = new Ktnb04Model();
                    
                    obj.setKT_KHOA(reset.getString("KT_KHOA"));
                    obj.setKT_MAPGD(reset.getString("KT_MAPGD"));
                    obj.setKT_MACN(reset.getString("KT_MACN"));
                    obj.setKT_QUYBC(reset.getDouble("KT_QUYBC"));
                    obj.setKT_NAMBC(reset.getDouble("KT_NAMBC"));
                    obj.setKT_NGAY_NHAP(reset.getDate("KT_NGAY_NHAP"));
                    obj.setKT_NGUOI_NHAP(reset.getString("KT_NGUOI_NHAP"));
                    obj.setKT_STT_HT(reset.getString("KT_STT_HT"));
                    obj.setKT_LOAI_CT(reset.getString("KT_LOAI_CT"));
                    obj.setKT_KT_SCT(reset.getDouble("KT_KT_SCT"));
                    obj.setKT_KT_ST(reset.getDouble("KT_KT_ST"));
                    obj.setKT_SS_TS_SCT(reset.getDouble("KT_SS_TS_SCT"));
                    obj.setKT_SS_TS_ST(reset.getDouble("KT_SS_TS_ST"));
                    obj.setKT_SS_TS_TLCT(reset.getDouble("KT_SS_TS_TLCT"));
                    obj.setKT_SS_TS_TLST(reset.getDouble("KT_SS_TS_TLST"));
                    obj.setKT_TD_SPL_SCT(reset.getDouble("KT_TD_SPL_SCT"));
                    obj.setKT_TD_SPL_ST(reset.getDouble("KT_TD_SPL_ST"));
                    obj.setKT_TD_KTNV_SL(reset.getDouble("KT_TD_KTNV_SL"));
                    obj.setKT_TD_KTNV_ST(reset.getDouble("KT_TD_KTNV_ST"));
                    obj.setKT_TD_SK_SL(reset.getDouble("KT_TD_SK_SL"));
                    obj.setKT_TD_SK_ST(reset.getDouble("KT_TD_SK_ST"));
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
                CoreLogger.error(this.getClass().getName() + " get_ktnb04 -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham get_ktnb04 " + e.getMessage());
            CoreLogger.error(this.getClass().getName()+ " get_ktnb04 -> " + e.getMessage());
        }
        return dataList;
    }
    
    public boolean save_ktnb04(String posCD,String maCn,String quyBc,String namBc,String userId,
            List<String> KT_KHOA,List<String> p3,List<String>  p4,List<String>  p5, 
            List<String> p6, List<String> p7, List<String> p8, 
                List<String> p9, List<String> p10, List<String> p11, 
                List<String> p12, List<String> p13, List<String> p14, String strAuth ){
    
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
            
            //CuongBM: 06Nov14
            String strStoreproce = "{call VBSP_RPT_KTKTNB.p_save_ktnb04(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
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
                calstatement.registerOutParameter(19, oracle.jdbc.OracleTypes.NUMBER);
                calstatement.registerOutParameter(20, oracle.jdbc.OracleTypes.VARCHAR);
                calstatement.setString(21, strAuth);
                
                //Thuc hien execute lay du lieu
                calstatement.execute();
                
                //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
                int pn_err_cd = calstatement.getInt(19);
                
                //thu hien lay mo ta loi
                String strEdd_txt = calstatement.getString(20);
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
                CoreLogger.error(this.getClass().getName() + " save_ktnb04 -> " + e.getMessage());
                return false;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DaoXdkh.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Loi trong ham save_ktnb04_ "+posCD+" " + ex.getMessage());
            CoreLogger.error(this.getClass().getName()+ " save_ktnb04 -> "+posCD+" " + ex.getMessage());
            return false;
        }
        return true; 
    }
    
    
    public ArrayList<Ktnb04Model> get_ktnb04_auth(String posCD, int namBc, int quyBc,String auth) {
        ArrayList<Ktnb04Model> dataList = new ArrayList<Ktnb04Model>();
        boolean bSuccess = false;
        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call VBSP_RPT_KTKTNB.p_get_ktnb04_auth(?, ?, ?, ?, ?, ?, ?)}";
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
                    Ktnb04Model obj = new Ktnb04Model();
                    
                    obj.setKT_KHOA(reset.getString("KT_KHOA"));
                    obj.setKT_MAPGD(reset.getString("KT_MAPGD"));
                    obj.setKT_MACN(reset.getString("KT_MACN"));
                    obj.setKT_QUYBC(reset.getDouble("KT_QUYBC"));
                    obj.setKT_NAMBC(reset.getDouble("KT_NAMBC"));
                    obj.setKT_NGAY_NHAP(reset.getDate("KT_NGAY_NHAP"));
                    obj.setKT_NGUOI_NHAP(reset.getString("KT_NGUOI_NHAP"));
                    obj.setKT_STT_HT(reset.getString("KT_STT_HT"));
                    obj.setKT_LOAI_CT(reset.getString("KT_LOAI_CT"));
                    obj.setKT_KT_SCT(reset.getDouble("KT_KT_SCT"));
                    obj.setKT_KT_ST(reset.getDouble("KT_KT_ST"));
                    obj.setKT_SS_TS_SCT(reset.getDouble("KT_SS_TS_SCT"));
                    obj.setKT_SS_TS_ST(reset.getDouble("KT_SS_TS_ST"));
                    obj.setKT_SS_TS_TLCT(reset.getDouble("KT_SS_TS_TLCT"));
                    obj.setKT_SS_TS_TLST(reset.getDouble("KT_SS_TS_TLST"));
                    obj.setKT_TD_SPL_SCT(reset.getDouble("KT_TD_SPL_SCT"));
                    obj.setKT_TD_SPL_ST(reset.getDouble("KT_TD_SPL_ST"));
                    obj.setKT_TD_KTNV_SL(reset.getDouble("KT_TD_KTNV_SL"));
                    obj.setKT_TD_KTNV_ST(reset.getDouble("KT_TD_KTNV_ST"));
                    obj.setKT_TD_SK_SL(reset.getDouble("KT_TD_SK_SL"));
                    obj.setKT_TD_SK_ST(reset.getDouble("KT_TD_SK_ST"));
                    obj.setKT_DN(reset.getString("KT_DN"));
                    obj.setKT_CO_DINH(reset.getString("KT_CO_DINH"));
                    obj.setKT_THEM(reset.getString("KT_THEM"));
                    obj.setKT_XOA(reset.getString("KT_XOA"));
                    obj.setKT_FONTWEIGHT(reset.getString("KT_FONTWEIGHT"));
                    obj.setKT_CAPHT(reset.getDouble("KT_CAPHT"));
                    obj.setKT_STT(reset.getDouble("KT_STT"));
                    obj.setNG_CAPNHAT(reset.getString("NG_CAPNHAT"));
                    obj.setNG_CAPNHAT(reset.getString("KT_KHOA"));
  

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
                CoreLogger.error(this.getClass().getName() + " get_ktnb04 -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham get_ktnb04 " + e.getMessage());
            CoreLogger.error(this.getClass().getName()+ " get_ktnb04 -> " + e.getMessage());
        }
        return dataList;
    }
    
    public boolean save_ktnb04_auth(String posCD ,String quyBc,String namBc){
    
        try {
            //Khai bao các biến để nhận dữ liệu đẩy về
            DaoConnect daoConnect = new DaoConnect();
            Connection con = daoConnect.getConnect();
            Statement stm = con.createStatement();

            //2. INSERT DU LIEU MOI
            String sqlInsert = "";
            sqlInsert = "INSERT INTO KTNB04 " +
                "                   SELECT KT_KHOA, KT_MAPGD, KT_MACN, KT_QUYBC, KT_NAMBC, \n" +
"    KT_NGAY_NHAP, KT_NGUOI_NHAP, KT_STT_HT, KT_LOAI_CT, KT_KT_SCT, \n" +
"    KT_KT_ST, KT_SS_TS_SCT, KT_SS_TS_ST, KT_SS_TS_TLCT, KT_SS_TS_TLST, \n" +
"    KT_TD_SPL_SCT, KT_TD_SPL_ST, KT_TD_KTNV_SL, KT_TD_KTNV_ST, KT_TD_SK_SL, \n" +
"    KT_TD_SK_ST, KT_DN, KT_CO_DINH, KT_THEM, KT_XOA, \n" +
"    KT_FONTWEIGHT, KT_CAPHT, KT_STT,sysdate,'Y'          \n" +
"                   FROM KTNB04 A\n" +
"                   WHERE KT_MAPGD = '"+ posCD +
"' AND KT_NAMBC = " + namBc +
"                   AND KT_QUYBC = "+ quyBc +
"                   and KT_AUTH is null  and NG_CAPNHAT = (select max(NG_CAPNHAT) FROM KTNB04\n" +
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
            Logger.getLogger(DaoKtnb04.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Loi trong ham save_ktnb04_auth "+posCD+" " + ex.getMessage());
            CoreLogger.error(this.getClass().getName()+ " save_ktnb04_auth ---------> "+posCD+" " + ex.getMessage());
            return false;
        }
        return true;        
    }
}
