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
import vbsp.ims.model.ktnb.Ktnb03Model;

/**
 *
 * @author CuongBM0211
 */
public class DaoKtnb03 {

    public ArrayList<Ktnb03Model> get_ktnb03(String posCD, int namBc, int quyBc) {
        ArrayList<Ktnb03Model> dataList = new ArrayList<Ktnb03Model>();
        boolean bSuccess = false;
        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call VBSP_RPT_KTKTNB.p_get_ktnb03(?, ?, ?, ?, ?, ?)}";
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
                    Ktnb03Model obj = new Ktnb03Model();

                    obj.setKT_KHOA(reset.getString("KT_KHOA"));
                    obj.setKT_MAPGD(reset.getString("KT_MAPGD"));
                    obj.setKT_MACN(reset.getString("KT_MACN"));
                    obj.setKT_QUYBC(reset.getDouble("KT_QUYBC"));
                    obj.setKT_NAMBC(reset.getDouble("KT_NAMBC"));
                    obj.setKT_NGAY_NHAP(reset.getDate("KT_NGAY_NHAP"));
                    obj.setKT_NGUOI_NHAP(reset.getString("KT_NGUOI_NHAP"));
                    obj.setKT_STT_HT(reset.getString("KT_STT_HT"));
                    obj.setKT_TIEUCHI(reset.getString("KT_TIEUCHI"));
                    obj.setKT_SDCC_SHVV(reset.getDouble("KT_SDCC_SHVV"));
                    obj.setKT_SDCC_ST(reset.getDouble("KT_SDCC_ST"));
                    obj.setKT_SDDC_SHVV(reset.getDouble("KT_SDDC_SHVV"));
                    obj.setKT_SDDC_ST(reset.getDouble("KT_SDDC_ST"));
                    obj.setKT_TLDC_SHVV(reset.getDouble("KT_TLDC_SHVV"));
                    obj.setKT_TLDC_ST(reset.getDouble("KT_TLDC_ST"));
                    obj.setKT_SSDC_SKH(reset.getDouble("KT_SSDC_SKH"));
                    obj.setKT_SSDC_ST(reset.getDouble("KT_SSDC_ST"));
                    obj.setKT_SSDC_TLST(reset.getDouble("KT_SSDC_TLST"));
                    obj.setKT_SSDC_GC(reset.getString("KT_SSDC_GC"));
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
                CoreLogger.error(this.getClass().getName() + " get_ktnb03 -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham get_ktnb03 " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " get_ktnb03 -> " + e.getMessage());
        }
        return dataList;
    }
    
    public ArrayList<Ktnb03Model> get_ktnb03_default(String posCD, int namBc, int quyBc) {
        ArrayList<Ktnb03Model> dataList = new ArrayList<Ktnb03Model>();
        boolean bSuccess = false;
        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call VBSP_RPT_KTKTNB.p_get_ktnb03_default(?, ?, ?, ?, ?, ?)}";
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
                    Ktnb03Model obj = new Ktnb03Model();

                    obj.setKT_KHOA(reset.getString("KT_KHOA"));
                    obj.setKT_MAPGD(reset.getString("KT_MAPGD"));
                    obj.setKT_MACN(reset.getString("KT_MACN"));
                    obj.setKT_QUYBC(reset.getDouble("KT_QUYBC"));
                    obj.setKT_NAMBC(reset.getDouble("KT_NAMBC"));
                    obj.setKT_NGAY_NHAP(reset.getDate("KT_NGAY_NHAP"));
                    obj.setKT_NGUOI_NHAP(reset.getString("KT_NGUOI_NHAP"));
                    obj.setKT_STT_HT(reset.getString("KT_STT_HT"));
                    obj.setKT_TIEUCHI(reset.getString("KT_TIEUCHI"));
                    obj.setKT_SDCC_SHVV(reset.getDouble("KT_SDCC_SHVV"));
                    obj.setKT_SDCC_ST(reset.getDouble("KT_SDCC_ST"));
                    obj.setKT_SDDC_SHVV(reset.getDouble("KT_SDDC_SHVV"));
                    obj.setKT_SDDC_ST(reset.getDouble("KT_SDDC_ST"));
                    obj.setKT_TLDC_SHVV(reset.getDouble("KT_TLDC_SHVV"));
                    obj.setKT_TLDC_ST(reset.getDouble("KT_TLDC_ST"));
                    obj.setKT_SSDC_SKH(reset.getDouble("KT_SSDC_SKH"));
                    obj.setKT_SSDC_ST(reset.getDouble("KT_SSDC_ST"));
                    obj.setKT_SSDC_TLST(reset.getDouble("KT_SSDC_TLST"));
                    obj.setKT_SSDC_GC(reset.getString("KT_SSDC_GC"));
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
                CoreLogger.error(this.getClass().getName() + " get_ktnb03 -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham get_ktnb03 " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " get_ktnb03 -> " + e.getMessage());
        }
        return dataList;
    }
    
    public ArrayList<Ktnb03Model> get_ktnb03_auth(String posCD, int namBc, int quyBc,String auth) {
        ArrayList<Ktnb03Model> dataList = new ArrayList<Ktnb03Model>();
        boolean bSuccess = false;
        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call VBSP_RPT_KTKTNB.p_get_ktnb03_auth(?, ?, ?, ?, ?, ?, ?)}";
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
                    Ktnb03Model obj = new Ktnb03Model();
                    
                    obj.setKT_KHOA(reset.getString("KT_KHOA"));
                    obj.setKT_MAPGD(reset.getString("KT_MAPGD"));
                    obj.setKT_MACN(reset.getString("KT_MACN"));
                    obj.setKT_QUYBC(reset.getDouble("KT_QUYBC"));
                    obj.setKT_NAMBC(reset.getDouble("KT_NAMBC"));
                    obj.setKT_NGAY_NHAP(reset.getDate("KT_NGAY_NHAP"));
                    obj.setKT_NGUOI_NHAP(reset.getString("KT_NGUOI_NHAP"));
                    obj.setKT_STT_HT(reset.getString("KT_STT_HT"));
                    obj.setKT_TIEUCHI(reset.getString("KT_TIEUCHI"));
                    obj.setKT_SDCC_SHVV(reset.getDouble("KT_SDCC_SHVV"));
                    obj.setKT_SDCC_ST(reset.getDouble("KT_SDCC_ST"));
                    obj.setKT_SDDC_SHVV(reset.getDouble("KT_SDDC_SHVV"));
                    obj.setKT_SDDC_ST(reset.getDouble("KT_SDDC_ST"));
                    obj.setKT_TLDC_SHVV(reset.getDouble("KT_TLDC_SHVV"));
                    obj.setKT_TLDC_ST(reset.getDouble("KT_TLDC_ST"));
                    obj.setKT_SSDC_SKH(reset.getDouble("KT_SSDC_SKH"));
                    obj.setKT_SSDC_ST(reset.getDouble("KT_SSDC_ST"));
                    obj.setKT_SSDC_TLST(reset.getDouble("KT_SSDC_TLST"));
                    obj.setKT_SSDC_GC(reset.getString("KT_SSDC_GC"));
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
                CoreLogger.error(this.getClass().getName() + " get_ktnb01 -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham get_ktnb01 " + e.getMessage());
            CoreLogger.error(this.getClass().getName()+ " get_ktnb01 -> " + e.getMessage());
        }
        return dataList;
    }

    public boolean save_ktnb03(String posCD, String maCn, String quyBc, String namBc, String userId,
            List<String> KT_KHOA,
            List<String> KT_SDCC_SHVV,List<String>  KT_SDCC_ST, List<String> KT_SDDC_SHVV,List<String>  KT_SDDC_ST,
            List<String> KT_TLDC_SHVV, List<String> KT_TLDC_ST,List<String>  KT_SSDC_SKH, 
            List<String> KT_SSDC_ST, List<String> KT_SSDC_TLST,List<String>  KT_SSDC_GC, String strAuth) {

        boolean bSuccess = false;
        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            
            ArrayDescriptor des = ArrayDescriptor.createDescriptor("ARRAY_TABLE", conn);
            
                
            String[] arrayKhoa = KT_KHOA.toArray(new String[0]);   //Ma Chi Tieu
            
            String[] arrayKT_SDCC_SHVV3 = KT_SDCC_SHVV.toArray(new String[0]); //Uoc Thuc Hien
            String[] arrayKT_SDCC_ST4 = KT_SDCC_ST.toArray(new String[0]); //Uoc Thuc Hien
            String[] arrayKT_SDDC_SHVV5 = KT_SDDC_SHVV.toArray(new String[0]); //KH nam
            String[] arrayKT_SDDC_ST6 = KT_SDDC_ST.toArray(new String[0]); //Uoc Thuc Hien
            String[] arrayKT_TLDC_SHVV7 = KT_TLDC_SHVV.toArray(new String[0]); //KH nam
            String[] arrayKT_TLDC_ST8 = KT_TLDC_ST.toArray(new String[0]); //Uoc Thuc Hien
            String[] arrayKT_SSDC_SKH9 = KT_SSDC_SKH.toArray(new String[0]); //KH nam
            String[] arrayKT_SSDC_ST10 = KT_SSDC_ST.toArray(new String[0]); //Uoc Thuc Hien
            String[] arrayKT_SSDC_TLST11 = KT_SSDC_TLST.toArray(new String[0]); //KH nam
            String[] arrayKT_SSDC_GC12 = KT_SSDC_GC.toArray(new String[0]); //Uoc Thuc Hien

            
            //CuongBM: Convert array --> array oracle su dung
            ARRAY oracle_arrayKhoa = new ARRAY(des,conn,arrayKhoa);
            ARRAY oracle_array3 = new ARRAY(des,conn,arrayKT_SDCC_SHVV3);
            ARRAY oracle_array4 = new ARRAY(des,conn,arrayKT_SDCC_ST4);
            ARRAY oracle_array5 = new ARRAY(des,conn,arrayKT_SDDC_SHVV5);
            ARRAY oracle_array6 = new ARRAY(des,conn,arrayKT_SDDC_ST6);
            ARRAY oracle_array7 = new ARRAY(des,conn,arrayKT_TLDC_SHVV7);
            ARRAY oracle_array8 = new ARRAY(des,conn,arrayKT_TLDC_ST8);
            ARRAY oracle_array9 = new ARRAY(des,conn,arrayKT_SSDC_SKH9);
            ARRAY oracle_array10 = new ARRAY(des,conn,arrayKT_SSDC_ST10);
            ARRAY oracle_array11 = new ARRAY(des,conn,arrayKT_SSDC_TLST11);
            ARRAY oracle_array12 = new ARRAY(des,conn,arrayKT_SSDC_GC12);
            
            //CuongBM: 06Nov14
            String strStoreproce = "{call VBSP_RPT_KTKTNB.p_save_ktnb03(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
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
                calstatement.registerOutParameter(17, oracle.jdbc.OracleTypes.NUMBER);
                calstatement.registerOutParameter(18, oracle.jdbc.OracleTypes.VARCHAR);
                calstatement.setString(19, strAuth);
                
                //Thuc hien execute lay du lieu
                calstatement.execute();
                
                //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
                int pn_err_cd = calstatement.getInt(17);
                
                //thu hien lay mo ta loi
                String strEdd_txt = calstatement.getString(18);
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
                CoreLogger.error(this.getClass().getName() + " save_ktnb03 -> " + e.getMessage());
                return false;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DaoXdkh.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Loi trong ham save_ktnb03_ "+posCD+" " + ex.getMessage());
            CoreLogger.error(this.getClass().getName()+ " save_ktnb03 -> "+posCD+" " + ex.getMessage());
            return false;
        }
        return true; 
    }
    
    public boolean save_ktnb03_auth(String posCD ,String quyBc,String namBc){
    
        try {
            //Khai bao các biến để nhận dữ liệu đẩy về
            DaoConnect daoConnect = new DaoConnect();
            Connection con = daoConnect.getConnect();
            Statement stm = con.createStatement();

            //2. INSERT DU LIEU MOI
            String sqlInsert = "";
            sqlInsert = "INSERT INTO KTNB03 " +
                "                   SELECT KT_KHOA, KT_MAPGD, KT_MACN, KT_QUYBC, KT_NAMBC, \n" +
"    KT_NGAY_NHAP, KT_NGUOI_NHAP, KT_STT_HT, KT_TIEUCHI, KT_SDCC_SHVV, \n" +
"    KT_SDCC_ST, KT_SDDC_SHVV, KT_SDDC_ST, KT_TLDC_SHVV, KT_TLDC_ST, \n" +
"    KT_SSDC_SKH, KT_SSDC_ST, KT_SSDC_TLST, KT_SSDC_GC, KT_DN, \n" +
"    KT_CO_DINH, KT_THEM, KT_XOA, KT_FONTWEIGHT, KT_CAPHT, \n" +
"    KT_STT,sysdate,'Y'          \n" +
"                   FROM KTNB03 A\n" +
"                   WHERE KT_MAPGD = '"+ posCD +
"' AND KT_NAMBC = " + namBc +
"                   AND KT_QUYBC = "+ quyBc +
"                   and KT_AUTH is null and NG_CAPNHAT = (select max(NG_CAPNHAT) FROM KTNB03\n" +
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
            Logger.getLogger(DaoKtnb03.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Loi trong ham save_ktnb03_auth "+posCD+" " + ex.getMessage());
            CoreLogger.error(this.getClass().getName()+ " save_ktnb03_auth ---------> "+posCD+" " + ex.getMessage());
            return false;
        }
        return true;        
    }
}
