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
import vbsp.ims.model.ktnb.Ktnb02Model;

/**
 *
 * @author CuongBM0211
 */
public class DaoKtnb02 {

    public ArrayList<Ktnb02Model> get_ktnb02(String posCD, int namBc, int quyBc) {
        ArrayList<Ktnb02Model> dataList = new ArrayList<Ktnb02Model>();
        boolean bSuccess = false;
        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call VBSP_RPT_KTKTNB.p_get_ktnb02(?, ?, ?, ?, ?, ?)}";
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
                    Ktnb02Model obj = new Ktnb02Model();

                    obj.setKT_KHOA(reset.getString("KT_KHOA"));
                    obj.setKT_MAPGD(reset.getString("KT_MAPGD"));
                    obj.setKT_MACN(reset.getString("KT_MACN"));
                    obj.setKT_QUYBC(reset.getDouble("KT_QUYBC"));
                    obj.setKT_NAMBC(reset.getDouble("KT_NAMBC"));
                    obj.setKT_NGAY_NHAP(reset.getDate("KT_NGAY_NHAP"));
                    obj.setKT_NGUOI_NHAP(reset.getString("KT_NGUOI_NHAP"));
                    obj.setKT_STT_HT(reset.getString("KT_STT_HT"));
                    obj.setKT_DTVV(reset.getString("KT_DTVV"));
                    obj.setKT_HS_THS_SHS(reset.getString("KT_HS_THS_SHS")); 
                    obj.setKT_HS_THS_ST(reset.getString("KT_HS_THS_ST")); 
                    obj.setKT_HS_KT_TS_SHS(reset.getDouble("KT_HS_KT_TS_SHS"));
                    obj.setKT_HS_KT_TS_ST(reset.getDouble("KT_HS_KT_TS_ST"));
                    obj.setKT_HS_KT_TL_SHS(reset.getDouble("KT_HS_KT_TL_SHS"));
                    obj.setKT_HS_KT_TL_ST(reset.getDouble("KT_HS_KT_TL_ST"));
                    obj.setKT_HS_HSS_SHS(reset.getDouble("KT_HS_HSS_SHS"));
                    obj.setKT_HS_HSS_ST(reset.getDouble("KT_HS_HSS_ST"));
                    obj.setKT_HS_TLS_SHS(reset.getDouble("KT_HS_TLS_SHS"));
                    obj.setKT_HS_TLS_ST(reset.getDouble("KT_HS_TLS_ST"));
                    obj.setKT_NQH_CC_SHS(reset.getDouble("KT_NQH_CC_SHS"));
                    obj.setKT_NQH_CC_ST(reset.getDouble("KT_NQH_CC_ST"));
                    obj.setKT_NQH_CD(reset.getDouble("KT_NQH_CD"));
                    obj.setKT_DN(reset.getString("KT_DN"));
                    obj.setKT_CO_DINH(reset.getString("KT_CO_DINH"));
                    obj.setKT_THEM(reset.getString("KT_THEM"));
                    obj.setKT_XOA(reset.getString("KT_XOA"));
                    obj.setKT_FONTWEIGHT(reset.getString("KT_FONTWEIGHT"));
                    obj.setKT_CAPHT(reset.getDouble("KT_CAPHT"));
                    obj.setKT_STT(reset.getDouble("KT_STT"));
                    obj.setNG_CAPNHAT(reset.getString("NG_CAPNHAT"));
                    System.err.println(reset.getString("KT_HS_THS_ST"));
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
                CoreLogger.error(this.getClass().getName() + " get_ktnb02 -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham get_ktnb02 " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " get_ktnb02 -> " + e.getMessage());
        }
        return dataList;
    }
    
    public ArrayList<Ktnb02Model> get_ktnb02_default(String posCD, int namBc, int quyBc) {
        ArrayList<Ktnb02Model> dataList = new ArrayList<Ktnb02Model>();
        boolean bSuccess = false;
        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call VBSP_RPT_KTKTNB.p_get_ktnb02_default(?, ?, ?, ?, ?, ?)}";
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
                    Ktnb02Model obj = new Ktnb02Model();

                    obj.setKT_KHOA(reset.getString("KT_KHOA"));
                    obj.setKT_MAPGD(reset.getString("KT_MAPGD"));
                    obj.setKT_MACN(reset.getString("KT_MACN"));
                    obj.setKT_QUYBC(reset.getDouble("KT_QUYBC"));
                    obj.setKT_NAMBC(reset.getDouble("KT_NAMBC"));
                    obj.setKT_NGAY_NHAP(reset.getDate("KT_NGAY_NHAP"));
                    obj.setKT_NGUOI_NHAP(reset.getString("KT_NGUOI_NHAP"));
                    obj.setKT_STT_HT(reset.getString("KT_STT_HT"));
                    obj.setKT_DTVV(reset.getString("KT_DTVV"));
                    obj.setKT_HS_THS_SHS(reset.getString("KT_HS_THS_SHS")); 
                    obj.setKT_HS_THS_ST(reset.getString("KT_HS_THS_ST")); 
                    obj.setKT_HS_KT_TS_SHS(reset.getDouble("KT_HS_KT_TS_SHS"));
                    obj.setKT_HS_KT_TS_ST(reset.getDouble("KT_HS_KT_TS_ST"));
                    obj.setKT_HS_KT_TL_SHS(reset.getDouble("KT_HS_KT_TL_SHS"));
                    obj.setKT_HS_KT_TL_ST(reset.getDouble("KT_HS_KT_TL_ST"));
                    obj.setKT_HS_HSS_SHS(reset.getDouble("KT_HS_HSS_SHS"));
                    obj.setKT_HS_HSS_ST(reset.getDouble("KT_HS_HSS_ST"));
                    obj.setKT_HS_TLS_SHS(reset.getDouble("KT_HS_TLS_SHS"));
                    obj.setKT_HS_TLS_ST(reset.getDouble("KT_HS_TLS_ST"));
                    obj.setKT_NQH_CC_SHS(reset.getDouble("KT_NQH_CC_SHS"));
                    obj.setKT_NQH_CC_ST(reset.getDouble("KT_NQH_CC_ST"));
                    obj.setKT_NQH_CD(reset.getDouble("KT_NQH_CD"));
                    obj.setKT_DN(reset.getString("KT_DN"));
                    obj.setKT_CO_DINH(reset.getString("KT_CO_DINH"));
                    obj.setKT_THEM(reset.getString("KT_THEM"));
                    obj.setKT_XOA(reset.getString("KT_XOA"));
                    obj.setKT_FONTWEIGHT(reset.getString("KT_FONTWEIGHT"));
                    obj.setKT_CAPHT(reset.getDouble("KT_CAPHT"));
                    obj.setKT_STT(reset.getDouble("KT_STT"));
                    obj.setNG_CAPNHAT(reset.getString("NG_CAPNHAT"));
                    System.err.println(reset.getString("KT_HS_THS_ST"));
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
                CoreLogger.error(this.getClass().getName() + " get_ktnb02 -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham get_ktnb02 " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " get_ktnb02 -> " + e.getMessage());
        }
        return dataList;
    }
    
    public ArrayList<Ktnb02Model> get_ktnb02_auth(String posCD, int namBc, int quyBc, String auth) {
        ArrayList<Ktnb02Model> dataList = new ArrayList<Ktnb02Model>();
        boolean bSuccess = false;
        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call VBSP_RPT_KTKTNB.p_get_ktnb02_auth(?, ?, ?, ?, ?, ?,?)}";
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
                calstatement.setString(7, "N");

                //Thuc hien execute lay du lieu
                calstatement.execute();

                //Lay cursor ra resultset
                reset = (ResultSet) calstatement.getObject(6);

                while (reset.next()) {
                    Ktnb02Model obj = new Ktnb02Model();

                    obj.setKT_KHOA(reset.getString("KT_KHOA"));
                    obj.setKT_MAPGD(reset.getString("KT_MAPGD"));
                    obj.setKT_MACN(reset.getString("KT_MACN"));
                    obj.setKT_QUYBC(reset.getDouble("KT_QUYBC"));
                    obj.setKT_NAMBC(reset.getDouble("KT_NAMBC"));
                    obj.setKT_NGAY_NHAP(reset.getDate("KT_NGAY_NHAP"));
                    obj.setKT_NGUOI_NHAP(reset.getString("KT_NGUOI_NHAP"));
                    obj.setKT_STT_HT(reset.getString("KT_STT_HT"));
                    obj.setKT_DTVV(reset.getString("KT_DTVV"));
                    obj.setKT_HS_THS_SHS(reset.getString("KT_HS_THS_SHS")); 
                    obj.setKT_HS_THS_ST(reset.getString("KT_HS_THS_ST")); 
                    obj.setKT_HS_KT_TS_SHS(reset.getDouble("KT_HS_KT_TS_SHS"));
                    obj.setKT_HS_KT_TS_ST(reset.getDouble("KT_HS_KT_TS_ST"));
                    obj.setKT_HS_KT_TL_SHS(reset.getDouble("KT_HS_KT_TL_SHS"));
                    obj.setKT_HS_KT_TL_ST(reset.getDouble("KT_HS_KT_TL_ST"));
                    obj.setKT_HS_HSS_SHS(reset.getDouble("KT_HS_HSS_SHS"));
                    obj.setKT_HS_HSS_ST(reset.getDouble("KT_HS_HSS_ST"));
                    obj.setKT_HS_TLS_SHS(reset.getDouble("KT_HS_TLS_SHS"));
                    obj.setKT_HS_TLS_ST(reset.getDouble("KT_HS_TLS_ST"));
                    obj.setKT_NQH_CC_SHS(reset.getDouble("KT_NQH_CC_SHS"));
                    obj.setKT_NQH_CC_ST(reset.getDouble("KT_NQH_CC_ST"));
                    obj.setKT_NQH_CD(reset.getDouble("KT_NQH_CD"));
                    obj.setKT_DN(reset.getString("KT_DN"));
                    obj.setKT_CO_DINH(reset.getString("KT_CO_DINH"));
                    obj.setKT_THEM(reset.getString("KT_THEM"));
                    obj.setKT_XOA(reset.getString("KT_XOA"));
                    obj.setKT_FONTWEIGHT(reset.getString("KT_FONTWEIGHT"));
                    obj.setKT_CAPHT(reset.getDouble("KT_CAPHT"));
                    obj.setKT_STT(reset.getDouble("KT_STT"));
                    obj.setNG_CAPNHAT(reset.getString("NG_CAPNHAT"));
                    System.err.println(reset.getString("KT_HS_THS_ST"));
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
                CoreLogger.error(this.getClass().getName() + " get_ktnb02 -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham get_ktnb02 " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " get_ktnb02 -> " + e.getMessage());
        }
        return dataList;
    }

    public boolean save_ktnb02(String posCD, String maCn, String quyBc, String namBc, String userId,
            List<String> KT_KHOA,List<String> KT_HS_THS_SHS,List<String> KT_HS_THS_ST,List<String> KT_HS_KT_TS_SHS, 
            List<String> KT_HS_KT_TS_ST,List<String> KT_HS_KT_TL_SHS, 
	List<String> KT_HS_KT_TL_ST,List<String> KT_HS_HSS_SHS,List<String> KT_HS_HSS_ST,
        List<String> KT_HS_TLS_SHS,List<String> KT_HS_TLS_ST, String strAuth) {

        boolean bSuccess = false;
        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            
            ArrayDescriptor des = ArrayDescriptor.createDescriptor("ARRAY_TABLE", conn);
            
            //CuongBM: Convert List to array
            String[] arrayKhoa = KT_KHOA.toArray(new String[0]);   //Ma Chi Tieu
            String[] arrayKT_HS_THS_SHS = KT_HS_THS_SHS.toArray(new String[0]); //Uoc Thuc Hien
            String[] arrayKT_HS_THS_ST = KT_HS_THS_ST.toArray(new String[0]); //KH nam
            String[] arrayKT_HS_KT_TS_SHS = KT_HS_KT_TS_SHS.toArray(new String[0]); //Uoc Thuc Hien
            String[] arrayKT_HS_KT_TS_ST = KT_HS_KT_TS_ST.toArray(new String[0]); //KH nam
            String[] arrayKT_HS_KT_TL_SHS = KT_HS_KT_TL_SHS.toArray(new String[0]); //Uoc Thuc Hien
            String[] arrayKT_HS_KT_TL_ST = KT_HS_KT_TL_ST.toArray(new String[0]); //KH nam
            String[] arrayKT_HS_HSS_SHS = KT_HS_HSS_SHS.toArray(new String[0]); //Uoc Thuc Hien
            String[] arrayKT_HS_HSS_ST = KT_HS_HSS_ST.toArray(new String[0]); //KH nam
            String[] arrayKT_HS_TLS_SHS = KT_HS_TLS_SHS.toArray(new String[0]); //Uoc Thuc Hien
            String[] arrayKT_HS_TLS_ST = KT_HS_TLS_ST.toArray(new String[0]); //KH nam
            
            //CuongBM: Convert array --> array oracle su dung
            ARRAY oracle_arrayKhoa = new ARRAY(des,conn,arrayKhoa);
            ARRAY oracle_array3 = new ARRAY(des,conn,arrayKT_HS_THS_SHS);
            ARRAY oracle_array4 = new ARRAY(des,conn,arrayKT_HS_THS_ST);
            ARRAY oracle_array5 = new ARRAY(des,conn,arrayKT_HS_KT_TS_SHS);
            ARRAY oracle_array6 = new ARRAY(des,conn,arrayKT_HS_KT_TS_ST);
            ARRAY oracle_array7 = new ARRAY(des,conn,arrayKT_HS_KT_TL_SHS);
            ARRAY oracle_array8 = new ARRAY(des,conn,arrayKT_HS_KT_TL_ST);
            ARRAY oracle_array9 = new ARRAY(des,conn,arrayKT_HS_HSS_SHS);
            ARRAY oracle_array10 = new ARRAY(des,conn,arrayKT_HS_HSS_ST);
            ARRAY oracle_array11 = new ARRAY(des,conn,arrayKT_HS_TLS_SHS);
            ARRAY oracle_array12 = new ARRAY(des,conn,arrayKT_HS_TLS_ST);
            
            //CuongBM: 06Nov14
            String strStoreproce = "{call VBSP_RPT_KTKTNB.p_save_ktnb02(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
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
                CoreLogger.error(this.getClass().getName() + " save_ktnb02 -> " + e.getMessage());
                return false;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DaoXdkh.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Loi trong ham save_ktnb02_ "+posCD+" " + ex.getMessage());
            CoreLogger.error(this.getClass().getName()+ " save_ktnb02 -> "+posCD+" " + ex.getMessage());
            return false;
        }
        return true; 
    }
    public boolean save_ktnb02_auth(String posCD ,String quyBc,String namBc){
    
        try {
            //Khai bao các biến để nhận dữ liệu đẩy về
            DaoConnect daoConnect = new DaoConnect();
            Connection con = daoConnect.getConnect();
            Statement stm = con.createStatement();

            //2. INSERT DU LIEU MOI
            String sqlInsert = "";
            sqlInsert = "INSERT INTO KTNB02(KT_KHOA, KT_MAPGD, KT_MACN, KT_QUYBC, KT_NAMBC, \n" +
                "    KT_NGAY_NHAP, KT_NGUOI_NHAP, KT_STT_HT, KT_DTVV, KT_HS_THS_SHS, \n" +
                "    KT_HS_THS_ST, KT_HS_KT_TS_SHS, KT_HS_KT_TS_ST, KT_HS_KT_TL_SHS, KT_HS_KT_TL_ST, \n" +
                "    KT_HS_HSS_SHS, KT_HS_HSS_ST, KT_HS_TLS_SHS, KT_HS_TLS_ST, KT_NQH_CC_SHS, \n" +
                "    KT_NQH_CC_ST, KT_NQH_CD, KT_DN, KT_CO_DINH, KT_THEM, \n" +
                "    KT_XOA, KT_FONTWEIGHT, KT_CAPHT, KT_STT, NG_CAPNHAT, \n" +
                "    KT_AUTH)\n" +
                "    SELECT KT_KHOA, KT_MAPGD, KT_MACN, KT_QUYBC, KT_NAMBC, \n" +
                "    KT_NGAY_NHAP, KT_NGUOI_NHAP, KT_STT_HT, KT_DTVV, KT_HS_THS_SHS, \n" +
                "    KT_HS_THS_ST, KT_HS_KT_TS_SHS, KT_HS_KT_TS_ST, KT_HS_KT_TL_SHS, KT_HS_KT_TL_ST, \n" +
                "    KT_HS_HSS_SHS, KT_HS_HSS_ST, KT_HS_TLS_SHS, KT_HS_TLS_ST, KT_NQH_CC_SHS, \n" +
                "    KT_NQH_CC_ST, KT_NQH_CD, KT_DN, KT_CO_DINH, KT_THEM, \n" +
                "    KT_XOA, KT_FONTWEIGHT, KT_CAPHT, KT_STT \n" +
                "    ,sysdate,'Y'          \n" +
                "                   FROM KTNB02 A\n" +
                "                   WHERE KT_MAPGD = '"+ posCD +
                "' AND KT_NAMBC = " + namBc +
                "                   AND KT_QUYBC = "+ quyBc +
                "                   and KT_AUTH is null and NG_CAPNHAT = (select max(NG_CAPNHAT) FROM KTNB02\n" +
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
            Logger.getLogger(DaoKtnb02.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Loi trong ham save_ktnb02_auth "+posCD+" " + ex.getMessage());
            CoreLogger.error(this.getClass().getName()+ " save_ktnb02_auth ---------> "+posCD+" " + ex.getMessage());
            return false;
        }
        return true;        
    }
    
}
