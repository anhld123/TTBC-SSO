
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
import vbsp.ims.dao.DaoConnect;
import vbsp.ims.log.CoreLogger;
import vbsp.ims.model.ktnb.Ktnb11Model;
import vbsp.ims.model.ktnb.Ktnb12Model;

/**
 *
 * @author CuongBM0211
 */
public class DaoKtnb12 {
    public ArrayList<Ktnb12Model> get_ktnb12(String posCD, int namBc, int quyBc) {
        ArrayList<Ktnb12Model> dataList = new ArrayList<Ktnb12Model>();
        boolean bSuccess = false;
        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call VBSP_RPT_KTKTNB.p_get_ktnb12(?, ?, ?, ?, ?, ?)}";
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
                    Ktnb12Model obj = new Ktnb12Model();
                    
                    obj.setKT_KHOA(reset.getString("KT_KHOA"));
                    obj.setKT_MAPGD(reset.getString("KT_MAPGD"));
                    obj.setKT_MACN(reset.getString("KT_MACN"));
                    obj.setKT_QUYBC(reset.getDouble("KT_QUYBC"));
                    obj.setKT_NAMBC(reset.getDouble("KT_NAMBC"));
                    obj.setKT_NGAY_NHAP(reset.getDate("KT_NGAY_NHAP"));
                    obj.setKT_NGUOI_NHAP(reset.getString("KT_NGUOI_NHAP"));
                    
                    //CuongBM: Truong hop nay se hardcode la ma PGD
                    obj.setKT_DV(posCD);
                    obj.setKT_SO_VB_NEW(reset.getDouble("KT_SO_VB_NEW"));
                    obj.setKT_SO_VB_BS(reset.getDouble("KT_SO_VB_BS"));
                    obj.setKT_SO_LOP_TH(reset.getDouble("KT_SO_LOP_TH"));
                    obj.setKT_SO_NG_TH(reset.getDouble("KT_SO_NG_TH"));
                    obj.setKT_SO_CUOC(reset.getDouble("KT_SO_CUOC"));
                    obj.setKT_SO_DV(reset.getDouble("KT_SO_DV"));
                    obj.setKT_SO_DV_VP(reset.getDouble("KT_SO_DV_VP"));
                    obj.setKT_SO_TOCHUC1(reset.getDouble("KT_SO_TOCHUC1"));
                    obj.setKT_SO_CANHAN1(reset.getDouble("KT_SO_CANHAN1"));
                    obj.setKT_SO_TOCHUC2(reset.getDouble("KT_SO_TOCHUC2"));
                    obj.setKT_SO_CANHAN2(reset.getDouble("KT_SO_CANHAN2"));
                    obj.setKT_TONG_KLTT(reset.getDouble("KT_TONG_KLTT"));
                    obj.setKT_SO_TOCHUC3(reset.getDouble("KT_SO_TOCHUC3"));
                    obj.setKT_SO_CANHAN3(reset.getDouble("KT_SO_CANHAN3"));
                    obj.setKT_SO_TOCHUC4(reset.getDouble("KT_SO_TOCHUC4"));
                    obj.setKT_SO_CANHAN4(reset.getDouble("KT_SO_CANHAN4"));
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
                CoreLogger.error(this.getClass().getName() + " get_ktnb12 -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham get_ktnb11 " + e.getMessage());
            CoreLogger.error(this.getClass().getName()+ " get_ktnb12 -> " + e.getMessage());
        }
        return dataList;
    }
    
    public ArrayList<Ktnb12Model> get_ktnb12_default(String posCD, int namBc, int quyBc) {
        ArrayList<Ktnb12Model> dataList = new ArrayList<Ktnb12Model>();
        boolean bSuccess = false;
        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call VBSP_RPT_KTKTNB.p_get_ktnb12_default(?, ?, ?, ?, ?, ?)}";
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
                    Ktnb12Model obj = new Ktnb12Model();
                    
                    obj.setKT_KHOA(reset.getString("KT_KHOA"));
                    obj.setKT_MAPGD(reset.getString("KT_MAPGD"));
                    obj.setKT_MACN(reset.getString("KT_MACN"));
                    obj.setKT_QUYBC(reset.getDouble("KT_QUYBC"));
                    obj.setKT_NAMBC(reset.getDouble("KT_NAMBC"));
                    obj.setKT_NGAY_NHAP(reset.getDate("KT_NGAY_NHAP"));
                    obj.setKT_NGUOI_NHAP(reset.getString("KT_NGUOI_NHAP"));
                    
                    //CuongBM: Truong hop nay se hardcode la ma PGD
                    obj.setKT_DV(posCD);
                    obj.setKT_SO_VB_NEW(reset.getDouble("KT_SO_VB_NEW"));
                    obj.setKT_SO_VB_BS(reset.getDouble("KT_SO_VB_BS"));
                    obj.setKT_SO_LOP_TH(reset.getDouble("KT_SO_LOP_TH"));
                    obj.setKT_SO_NG_TH(reset.getDouble("KT_SO_NG_TH"));
                    obj.setKT_SO_CUOC(reset.getDouble("KT_SO_CUOC"));
                    obj.setKT_SO_DV(reset.getDouble("KT_SO_DV"));
                    obj.setKT_SO_DV_VP(reset.getDouble("KT_SO_DV_VP"));
                    obj.setKT_SO_TOCHUC1(reset.getDouble("KT_SO_TOCHUC1"));
                    obj.setKT_SO_CANHAN1(reset.getDouble("KT_SO_CANHAN1"));
                    obj.setKT_SO_TOCHUC2(reset.getDouble("KT_SO_TOCHUC2"));
                    obj.setKT_SO_CANHAN2(reset.getDouble("KT_SO_CANHAN2"));
                    obj.setKT_TONG_KLTT(reset.getDouble("KT_TONG_KLTT"));
                    obj.setKT_SO_TOCHUC3(reset.getDouble("KT_SO_TOCHUC3"));
                    obj.setKT_SO_CANHAN3(reset.getDouble("KT_SO_CANHAN3"));
                    obj.setKT_SO_TOCHUC4(reset.getDouble("KT_SO_TOCHUC4"));
                    obj.setKT_SO_CANHAN4(reset.getDouble("KT_SO_CANHAN4"));
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
                CoreLogger.error(this.getClass().getName() + " get_ktnb12 -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham get_ktnb11 " + e.getMessage());
            CoreLogger.error(this.getClass().getName()+ " get_ktnb12 -> " + e.getMessage());
        }
        return dataList;
    }
    
    public boolean save_ktnb12(String posCD,String maCn,String quyBc,String namBc,String userId,
            List<String> KT_DV,List<String> KT_SO_VB_NEW,List<String> KT_SO_VB_BS,List<String> KT_SO_LOP_TH,
            List<String> KT_SO_NG_TH,List<String> KT_SO_CUOC,List<String> KT_SO_DV,List<String> KT_SO_DV_VP,
            List<String> KT_SO_TOCHUC1,List<String> KT_SO_CANHAN1,List<String> KT_SO_TOCHUC2,List<String> KT_SO_CANHAN2,
            List<String> KT_TONG_KLTT,List<String> KT_SO_TOCHUC3,List<String> KT_SO_CANHAN3,List<String> KT_SO_TOCHUC4,
            List<String> KT_SO_CANHAN4,List<String> KT_GHICHU,List<String> KT_DN,List<String> KT_CO_DINH,
            List<String> KT_THEM,List<String> KT_XOA,List<String> KT_FONTWEIGHT,List<String> KT_CAPHT,
            List<String> KT_STT,List<String> NG_CAPNHAT, String strAuth){
    
        try {
            if(strAuth == "")
                strAuth = "''";
            else strAuth = "'"+strAuth+"'";
            //1. XOA DU LIEU CU
//            String sqlDelete = "delete ktnb12 " +
//                    "WHERE kt_mapgd = '" + posCD + "'" +
//                    "    AND kt_quybc = " + quyBc +
//                    "    AND kt_nambc = " + namBc;
            
            //Khai bao các biến để nhận dữ liệu đẩy về
            DaoConnect daoConnect = new DaoConnect();
            Connection con = daoConnect.getConnect();
            Statement stm = con.createStatement();
            
            //Thuc hien xoa du lieu
//            stm.executeUpdate(sqlDelete);            
            
            //2. INSERT DU LIEU MOI
            String sqlInsert = "";
                    
            for (int i = 0; i < KT_DV.size(); i++) {
                if(KT_GHICHU.size() == 0)
                {
                    sqlInsert = "INSERT INTO KTNB12(KT_KHOA,KT_MAPGD,KT_MACN,KT_QUYBC,KT_NAMBC,KT_NGAY_NHAP,KT_NGUOI_NHAP,KT_DV,KT_SO_VB_NEW,KT_SO_VB_BS,KT_SO_LOP_TH,KT_SO_NG_TH,KT_SO_CUOC,KT_SO_DV,KT_SO_DV_VP,KT_SO_TOCHUC1,KT_SO_CANHAN1,KT_SO_TOCHUC2,KT_SO_CANHAN2,KT_TONG_KLTT,KT_SO_TOCHUC3,KT_SO_CANHAN3,KT_SO_TOCHUC4,KT_SO_CANHAN4,KT_GHICHU,KT_DN,KT_CO_DINH,KT_THEM,KT_XOA,KT_FONTWEIGHT,KT_CAPHT,KT_STT,NG_CAPNHAT, KT_AUTH) "+
                        "VALUES(" + 
                        i + ",'" + 
                        posCD + "','" + 
                        maCn + "'," + 
                        quyBc + "," + 
                        namBc + 
                        "," + 
                        "sysdate" + ",'" + 
                        userId + "'," +                //Cac truong co dinh
                        "'"+KT_DV.get(i)+"',"+ 
                        KT_SO_VB_NEW.get(i)+","+ 
                        KT_SO_VB_BS.get(i)+","+ 
                        KT_SO_LOP_TH.get(i)+","+ 
                        KT_SO_NG_TH.get(i)+","+ 
                        KT_SO_CUOC.get(i)+","+ 
                        KT_SO_DV.get(i)+","+ 
                        KT_SO_DV_VP.get(i)+","+ 
                        KT_SO_TOCHUC1.get(i)+","+ 
                        KT_SO_CANHAN1.get(i)+","+ 
                        KT_SO_TOCHUC2.get(i)+","+ 
                        KT_SO_CANHAN2.get(i)+","+ 
                        KT_TONG_KLTT.get(i)+","+ 
                        KT_SO_TOCHUC3.get(i)+","+ 
                        KT_SO_CANHAN3.get(i)+","+ 
                        KT_SO_TOCHUC4.get(i)+","+ 
                        KT_SO_CANHAN4.get(i)+","+ 
                        "'"+' '+"',"+ 
                        "'"+KT_DN.get(i)+"',"+ 
                        "'"+KT_CO_DINH.get(i)+"',"+ 
                        "'"+KT_THEM.get(i)+"',"+ 
                        "'"+KT_XOA.get(i)+"',"+ 
                        "'"+KT_FONTWEIGHT.get(i)+"',"+ 
                        KT_CAPHT.get(i)+","+ 
                        i+","+ 
                        "sysdate," + strAuth +")";
                }
                else
                {
                    sqlInsert = "INSERT INTO KTNB12(KT_KHOA,KT_MAPGD,KT_MACN,KT_QUYBC,KT_NAMBC,KT_NGAY_NHAP,KT_NGUOI_NHAP,KT_DV,KT_SO_VB_NEW,KT_SO_VB_BS,KT_SO_LOP_TH,KT_SO_NG_TH,KT_SO_CUOC,KT_SO_DV,KT_SO_DV_VP,KT_SO_TOCHUC1,KT_SO_CANHAN1,KT_SO_TOCHUC2,KT_SO_CANHAN2,KT_TONG_KLTT,KT_SO_TOCHUC3,KT_SO_CANHAN3,KT_SO_TOCHUC4,KT_SO_CANHAN4,KT_GHICHU,KT_DN,KT_CO_DINH,KT_THEM,KT_XOA,KT_FONTWEIGHT,KT_CAPHT,KT_STT,NG_CAPNHAT, KT_AUTH) "+
                        "VALUES(" + 
                        i + ",'" + 
                        posCD + "','" + 
                        maCn + "'," + 
                        quyBc + "," + 
                        namBc + 
                        "," + 
                        "sysdate" + ",'" + 
                        userId + "'," +                //Cac truong co dinh
                        "'"+KT_DV.get(i)+"',"+ 
                        KT_SO_VB_NEW.get(i)+","+ 
                        KT_SO_VB_BS.get(i)+","+ 
                        KT_SO_LOP_TH.get(i)+","+ 
                        KT_SO_NG_TH.get(i)+","+ 
                        KT_SO_CUOC.get(i)+","+ 
                        KT_SO_DV.get(i)+","+ 
                        KT_SO_DV_VP.get(i)+","+ 
                        KT_SO_TOCHUC1.get(i)+","+ 
                        KT_SO_CANHAN1.get(i)+","+ 
                        KT_SO_TOCHUC2.get(i)+","+ 
                        KT_SO_CANHAN2.get(i)+","+ 
                        KT_TONG_KLTT.get(i)+","+ 
                        KT_SO_TOCHUC3.get(i)+","+ 
                        KT_SO_CANHAN3.get(i)+","+ 
                        KT_SO_TOCHUC4.get(i)+","+ 
                        KT_SO_CANHAN4.get(i)+","+ 
                        "'"+KT_GHICHU.get(i)+"',"+ 
                        "'"+KT_DN.get(i)+"',"+ 
                        "'"+KT_CO_DINH.get(i)+"',"+ 
                        "'"+KT_THEM.get(i)+"',"+ 
                        "'"+KT_XOA.get(i)+"',"+ 
                        "'"+KT_FONTWEIGHT.get(i)+"',"+ 
                        KT_CAPHT.get(i)+","+ 
                        i+","+ 
                        "sysdate," + strAuth +")";
                }
                

                stm.executeUpdate(sqlInsert);  
            }
            
            if (stm != null) {
                    stm.close();
                }            
            
            if (con != null) {
                con.close();
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DaoKtnb12.class.getName()).log(Level.SEVERE, null, ex);
//            CoreLogger.error(this.getClass().getName()+ " get_ktnb12 -> " + ex.getMessage());
            
            System.err.println("Loi trong ham save_ktnb12 " + posCD + " " + ex.getMessage());
            CoreLogger.error(this.getClass().getName() + " save_ktnb12 ---------> " + posCD + " " + ex.getMessage());
      
            return false;
        }
        
        return true;
                
    }
    
       
       public ArrayList<Ktnb12Model> get_ktnb12_auth(String posCD, int namBc, int quyBc,String auth) {
        ArrayList<Ktnb12Model> dataList = new ArrayList<Ktnb12Model>();
        boolean bSuccess = false;
        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call VBSP_RPT_KTKTNB.p_get_ktnb12_auth(?, ?, ?, ?, ?, ?, ?)}";
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
                    Ktnb12Model obj = new Ktnb12Model();
                    
                    obj.setKT_KHOA(reset.getString("KT_KHOA"));
                    obj.setKT_MAPGD(reset.getString("KT_MAPGD"));
                    obj.setKT_MACN(reset.getString("KT_MACN"));
                    obj.setKT_QUYBC(reset.getDouble("KT_QUYBC"));
                    obj.setKT_NAMBC(reset.getDouble("KT_NAMBC"));
                    obj.setKT_NGAY_NHAP(reset.getDate("KT_NGAY_NHAP"));
                    obj.setKT_NGUOI_NHAP(reset.getString("KT_NGUOI_NHAP"));
                    
                    //CuongBM: Truong hop nay se hardcode la ma PGD
                    obj.setKT_DV(posCD);
                    obj.setKT_SO_VB_NEW(reset.getDouble("KT_SO_VB_NEW"));
                    obj.setKT_SO_VB_BS(reset.getDouble("KT_SO_VB_BS"));
                    obj.setKT_SO_LOP_TH(reset.getDouble("KT_SO_LOP_TH"));
                    obj.setKT_SO_NG_TH(reset.getDouble("KT_SO_NG_TH"));
                    obj.setKT_SO_CUOC(reset.getDouble("KT_SO_CUOC"));
                    obj.setKT_SO_DV(reset.getDouble("KT_SO_DV"));
                    obj.setKT_SO_DV_VP(reset.getDouble("KT_SO_DV_VP"));
                    obj.setKT_SO_TOCHUC1(reset.getDouble("KT_SO_TOCHUC1"));
                    obj.setKT_SO_CANHAN1(reset.getDouble("KT_SO_CANHAN1"));
                    obj.setKT_SO_TOCHUC2(reset.getDouble("KT_SO_TOCHUC2"));
                    obj.setKT_SO_CANHAN2(reset.getDouble("KT_SO_CANHAN2"));
                    obj.setKT_TONG_KLTT(reset.getDouble("KT_TONG_KLTT"));
                    obj.setKT_SO_TOCHUC3(reset.getDouble("KT_SO_TOCHUC3"));
                    obj.setKT_SO_CANHAN3(reset.getDouble("KT_SO_CANHAN3"));
                    obj.setKT_SO_TOCHUC4(reset.getDouble("KT_SO_TOCHUC4"));
                    obj.setKT_SO_CANHAN4(reset.getDouble("KT_SO_CANHAN4"));
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
                CoreLogger.error(this.getClass().getName() + " get_ktnb12 -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham get_ktnb12 " + e.getMessage());
            CoreLogger.error(this.getClass().getName()+ " get_ktnb12 -> " + e.getMessage());
        }
        return dataList;
    }
    
    public boolean save_ktnb12_auth(String posCD ,String quyBc,String namBc){
    
        try {
            //Khai bao các biến để nhận dữ liệu đẩy về
            DaoConnect daoConnect = new DaoConnect();
            Connection con = daoConnect.getConnect();
            Statement stm = con.createStatement();

            //2. INSERT DU LIEU MOI
            String sqlInsert = "";
            sqlInsert = "INSERT INTO KTNB12 " +
                "                   SELECT KT_KHOA, KT_MAPGD, KT_MACN, KT_QUYBC, KT_NAMBC, \n" +
"    KT_NGAY_NHAP, KT_NGUOI_NHAP, KT_DV, KT_SO_VB_NEW, KT_SO_VB_BS, \n" +
"    KT_SO_LOP_TH, KT_SO_NG_TH, KT_SO_CUOC, KT_SO_DV, KT_SO_DV_VP, \n" +
"    KT_SO_TOCHUC1, KT_SO_CANHAN1, KT_SO_TOCHUC2, KT_SO_CANHAN2, KT_TONG_KLTT, \n" +
"    KT_SO_TOCHUC3, KT_SO_CANHAN3, KT_SO_TOCHUC4, KT_SO_CANHAN4, KT_GHICHU, \n" +
"    KT_DN, KT_CO_DINH, KT_THEM, KT_XOA, KT_FONTWEIGHT, \n" +
"    KT_CAPHT, KT_STT,sysdate,'Y'          \n" +
"                   FROM KTNB12 A\n" +
"                   WHERE KT_MAPGD = '"+ posCD +
"' AND KT_NAMBC = " + namBc +
"                   AND KT_QUYBC = "+ quyBc +
"                   and NG_CAPNHAT = (select max(NG_CAPNHAT) FROM KTNB12\n" +
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
            Logger.getLogger(DaoKtnb11.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Loi trong ham save_ktnb11_auth "+posCD+" " + ex.getMessage());
            CoreLogger.error(this.getClass().getName()+ " save_ktnb11_auth ---------> "+posCD+" " + ex.getMessage());
            return false;
        }
        return true;        
    }
}
