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
import vbsp.ims.model.ktnb.*;

/**
 *
 * @author CuongBM0211
 */
public class DaoKtnb01 {
    public ArrayList<Ktnb01Model> get_ktnb01(String posCD, int namBc, int quyBc) {
        ArrayList<Ktnb01Model> dataList = new ArrayList<Ktnb01Model>();
        boolean bSuccess = false;
        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call VBSP_RPT_KTKTNB.p_get_ktnb01(?, ?, ?, ?, ?, ?)}";
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
                
                //Thuc hien execute lay du lieu
                calstatement.execute();
                
                //Lay cursor ra resultset
                reset = (ResultSet) calstatement.getObject(6);
                
                while (reset.next()) {
                    Ktnb01Model obj = new Ktnb01Model();
                    
                    obj.setKT_KHOA(reset.getString("KT_KHOA"));
                    obj.setKT_MAPGD(reset.getString("KT_MAPGD"));
                    obj.setKT_MACN(reset.getString("KT_MACN"));
                    obj.setKT_QUYBC(reset.getDouble("KT_QUYBC"));
                    obj.setKT_NAMBC(reset.getDouble("KT_NAMBC"));
                    obj.setKT_NGAY_NHAP(reset.getDate("KT_NGAY_NHAP"));
                    obj.setKT_NGUOI_NHAP(reset.getString("KT_NGUOI_NHAP"));
                    obj.setKT_STT_HT(reset.getString("KT_STT_HT"));
                    obj.setKT_DKT(reset.getString("KT_DKT"));
                    obj.setKT_SLT(reset.getDouble("KT_SLT"));
                    obj.setKT_SLH(reset.getDouble("KT_SLH"));
                    obj.setKT_SL_DGD(reset.getDouble("KT_SL_DGD"));
                    obj.setKT_SL_TKVV(reset.getDouble("KT_SL_TKVV"));
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
    
    public ArrayList<Ktnb01Model> get_ktnb01_default(String posCD, int namBc, int quyBc) {
        ArrayList<Ktnb01Model> dataList = new ArrayList<Ktnb01Model>();
        boolean bSuccess = false;
        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call VBSP_RPT_KTKTNB.p_get_ktnb01_default(?, ?, ?, ?, ?, ?)}";
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
                
                //Thuc hien execute lay du lieu
                calstatement.execute();
                
                //Lay cursor ra resultset
                reset = (ResultSet) calstatement.getObject(6);
                
                while (reset.next()) {
                    Ktnb01Model obj = new Ktnb01Model();
                    
                    obj.setKT_KHOA(reset.getString("KT_KHOA"));
                    obj.setKT_MAPGD(reset.getString("KT_MAPGD"));
                    obj.setKT_MACN(reset.getString("KT_MACN"));
                    obj.setKT_QUYBC(reset.getDouble("KT_QUYBC"));
                    obj.setKT_NAMBC(reset.getDouble("KT_NAMBC"));
                    obj.setKT_NGAY_NHAP(reset.getDate("KT_NGAY_NHAP"));
                    obj.setKT_NGUOI_NHAP(reset.getString("KT_NGUOI_NHAP"));
                    obj.setKT_STT_HT(reset.getString("KT_STT_HT"));
                    obj.setKT_DKT(reset.getString("KT_DKT"));
                    obj.setKT_SLT(reset.getDouble("KT_SLT"));
                    obj.setKT_SLH(reset.getDouble("KT_SLH"));
                    obj.setKT_SL_DGD(reset.getDouble("KT_SL_DGD"));
                    obj.setKT_SL_TKVV(reset.getDouble("KT_SL_TKVV"));
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
    
    public ArrayList<Ktnb01Model> get_ktnb01_auth(String posCD, int namBc, int quyBc,String auth) {
        ArrayList<Ktnb01Model> dataList = new ArrayList<Ktnb01Model>();
        boolean bSuccess = false;
        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call VBSP_RPT_KTKTNB.p_get_ktnb01_auth(?, ?, ?, ?, ?, ?, ?)}";
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
                    Ktnb01Model obj = new Ktnb01Model();
                    
                    obj.setKT_KHOA(reset.getString("KT_KHOA"));
                    obj.setKT_MAPGD(reset.getString("KT_MAPGD"));
                    obj.setKT_MACN(reset.getString("KT_MACN"));
                    obj.setKT_QUYBC(reset.getDouble("KT_QUYBC"));
                    obj.setKT_NAMBC(reset.getDouble("KT_NAMBC"));
                    obj.setKT_NGAY_NHAP(reset.getDate("KT_NGAY_NHAP"));
                    obj.setKT_NGUOI_NHAP(reset.getString("KT_NGUOI_NHAP"));
                    obj.setKT_STT_HT(reset.getString("KT_STT_HT"));
                    obj.setKT_DKT(reset.getString("KT_DKT"));
                    obj.setKT_SLT(reset.getDouble("KT_SLT"));
                    obj.setKT_SLH(reset.getDouble("KT_SLH"));
                    obj.setKT_SL_DGD(reset.getDouble("KT_SL_DGD"));
                    obj.setKT_SL_TKVV(reset.getDouble("KT_SL_TKVV"));
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
    
    public boolean save_ktnb01(String posCD,String maCn,String quyBc,String namBc,String userId,
            List<String> KT_KHOA,List<String> KT_SLT,List<String> KT_SLH,
            List<String> KT_SL_DGD,List<String> KT_SL_TKVV, String strAuth){
    
        boolean bSuccess = false;
        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            
            ArrayDescriptor des = ArrayDescriptor.createDescriptor("ARRAY_TABLE", conn);
            
            //CuongBM: Convert List to array
            String[] arrayKhoa = KT_KHOA.toArray(new String[0]);   //Ma Chi Tieu
            String[] arrayKT_SLT = KT_SLT.toArray(new String[0]); //Uoc Thuc Hien
            String[] arrayKT_SLH = KT_SLH.toArray(new String[0]); //KH nam
            String[] arrayKT_SL_DGD = KT_SL_DGD.toArray(new String[0]); //Uoc Thuc Hien
            String[] arrayKT_SL_TKVV = KT_SL_TKVV.toArray(new String[0]); //KH nam
            
            //CuongBM: Convert array --> array oracle su dung
            ARRAY oracle_arrayKhoa = new ARRAY(des,conn,arrayKhoa);
            ARRAY oracle_arrayKT_SLT = new ARRAY(des,conn,arrayKT_SLT);
            ARRAY oracle_arrayKT_SLH = new ARRAY(des,conn,arrayKT_SLH);
            ARRAY oracle_arrayKT_SL_DGD = new ARRAY(des,conn,arrayKT_SL_DGD);
            ARRAY oracle_arrayKT_SL_TKVV = new ARRAY(des,conn,arrayKT_SL_TKVV);
            
            //CuongBM: 06Nov14
            String strStoreproce = "{call VBSP_RPT_KTKTNB.p_save_ktnb01(?,?,?,?,?,?,?,?,?,?,?,?,?)}";
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
                calstatement.setArray(7, oracle_arrayKT_SLT);
                calstatement.setArray(8, oracle_arrayKT_SLH);
                calstatement.setArray(9, oracle_arrayKT_SL_DGD);
                calstatement.setArray(10, oracle_arrayKT_SL_TKVV);
                calstatement.registerOutParameter(11, oracle.jdbc.OracleTypes.NUMBER);
                calstatement.registerOutParameter(12, oracle.jdbc.OracleTypes.VARCHAR);
                calstatement.setString(13, strAuth);
                
                //Thuc hien execute lay du lieu
                calstatement.execute();
                
                //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
                int pn_err_cd = calstatement.getInt(11);
                
                //thu hien lay mo ta loi
                String strEdd_txt = calstatement.getString(12);
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
                CoreLogger.error(this.getClass().getName() + " save_ktnb01 -> " + e.getMessage());
                return false;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DaoXdkh.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Loi trong ham save_ktnb01_ "+posCD+" " + ex.getMessage());
            CoreLogger.error(this.getClass().getName()+ " save_ktnb01 -> "+posCD+" " + ex.getMessage());
            return false;
        }
        return true; 
    }
    
    public boolean save_ktnb01_auth(String posCD ,String quyBc,String namBc){
    
        try {
            //Khai bao các biến để nhận dữ liệu đẩy về
            DaoConnect daoConnect = new DaoConnect();
            Connection con = daoConnect.getConnect();
            Statement stm = con.createStatement();

            //2. INSERT DU LIEU MOI
            String sqlInsert = "";
            sqlInsert = "INSERT INTO KTNB01(KT_KHOA,KT_MAPGD,KT_MACN,KT_QUYBC,KT_NAMBC,KT_NGAY_NHAP,KT_NGUOI_NHAP,KT_STT_HT,KT_DKT,KT_SLT,KT_SLH,KT_SL_DGD,KT_SL_TKVV,KT_DN,KT_CO_DINH,KT_THEM,KT_XOA,KT_FONTWEIGHT,KT_CAPHT,KT_STT,NG_CAPNHAT,KT_AUTH)\n" +
"                   SELECT KT_KHOA,KT_MAPGD,KT_MACN,KT_QUYBC,KT_NAMBC,KT_NGAY_NHAP,KT_NGUOI_NHAP,KT_STT_HT,KT_DKT,KT_SLT,KT_SLH,KT_SL_DGD,KT_SL_TKVV,KT_DN,KT_CO_DINH,KT_THEM,KT_XOA,KT_FONTWEIGHT,KT_CAPHT,KT_STT,sysdate,'Y'          \n" +
"                   FROM KTNB01 A\n" +
"                   WHERE KT_MAPGD = '"+ posCD +
"' AND KT_NAMBC = " + namBc +
"                   AND KT_QUYBC = "+ quyBc +
"                   and KT_AUTH is null and NG_CAPNHAT = (select max(NG_CAPNHAT) FROM KTNB01\n" +
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
            Logger.getLogger(DaoKtnb01.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Loi trong ham save_ktnb01_auth "+posCD+" " + ex.getMessage());
            CoreLogger.error(this.getClass().getName()+ " save_ktnb01_auth ---------> "+posCD+" " + ex.getMessage());
            return false;
        }
        return true;        
    }
}
