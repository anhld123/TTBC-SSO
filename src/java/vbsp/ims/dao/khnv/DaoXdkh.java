/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vbsp.ims.dao.khnv;

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
import vbsp.ims.log.CoreLogger;
import vbsp.ims.model.khnv.XdkhModel;

/**
 *
 * @author CuongBM0211
 */
public class DaoXdkh {
    public ArrayList<XdkhModel> get_data_xdkh(String posCD, String capbc,String xa_pgd, int namBc) {
        ArrayList<XdkhModel> dataList = new ArrayList<XdkhModel>();
        boolean bSuccess = false;
        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call VBSP_RPT_KHNV.p_get_data_xdkh(?, ?, ?, ?, ?,?,?)}";
            ResultSet reset = null;

            try {
                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                //Tham so thu nhat module_id 
                calstatement.setString(1, posCD);
                calstatement.setString(2, capbc);
                calstatement.setString(3, xa_pgd);
                calstatement.setInt(4, namBc);
        
                calstatement.registerOutParameter(5, oracle.jdbc.OracleTypes.NUMBER);
                calstatement.registerOutParameter(6, oracle.jdbc.OracleTypes.VARCHAR);
                calstatement.registerOutParameter(7, oracle.jdbc.OracleTypes.CURSOR);
                
                //Thuc hien execute lay du lieu
                calstatement.execute();
                
                //Lay cursor ra resultset
                reset = (ResultSet) calstatement.getObject(7);
                
                while (reset.next()) {
                    XdkhModel obj = new XdkhModel();
                    
                    obj.setKH_MA_CT(reset.getString("KH_MA_CT"));
                    obj.setKH_STT_HT(reset.getString("KH_STT_HT"));
                    obj.setKH_CHI_TIEU(reset.getString("KH_CHI_TIEU"));
                    obj.setKH_UOC_TH(reset.getDouble("KH_UOC_TH"));
                    obj.setKH_KH_NAM(reset.getDouble("KH_KH_NAM"));
                    obj.setKH_DN(reset.getString("KH_DN"));
                    obj.setKH_FONTWEIGHT(reset.getString("KH_FONTWEIGHT"));
                    obj.setKH_CAPHT(reset.getDouble("KH_CAPHT"));
                    obj.setKH_STT(reset.getDouble("KH_STT"));
                    obj.setKH_CONGTHUC(reset.getString("KH_CONGTHUC"));
                    obj.setKH_CHITIEU_CHAR(reset.getString("KH_CHITIEU_CHA"));
                    //Them vao list
                    dataList.add(obj);
                }
                
//                //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
//                int pn_err_cd = calstatement.getInt(4);
//                //thu hien lay mo ta loi
//                String strEdd_txt = calstatement.getString(5);
//                //Lay cursor ra resultset

                if (calstatement != null) {
                    calstatement.close();
                }
                if (conn != null) {
                    conn.close();
                }
                bSuccess = true;
            } catch (SQLException e) {
                System.err.print(e.getMessage());
                CoreLogger.error(this.getClass().getName() + " get_data_xdkh -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham get_data_xdkh " + e.getMessage());
            CoreLogger.error(this.getClass().getName()+ " get_data_xdkh -> " + e.getMessage());
        }
        return dataList;
    }
    
    public boolean save_xdkh(String posCD,String maCn,String namBc,String userId, String capbc,String xa_pgd, List<String> KH_MA_CT,
            List<String> KH_STT_HT,List<String> KH_CHI_TIEU,List<String> KH_UOC_TH,List<String> KH_KH_NAM,
            List<String> KH_DN,List<String> KH_FONTWEIGHT,List<String> KH_CAPHT,List<String> KH_STT) throws SQLException{
    
        boolean bSuccess = false;
        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            
            ArrayDescriptor des = ArrayDescriptor.createDescriptor("ARRAY_TABLE", conn);
            
            //CuongBM: Convert List to array
            String[] arrayMaCT = KH_MA_CT.toArray(new String[0]);   //Ma Chi Tieu
            String[] arrayUocTH = KH_UOC_TH.toArray(new String[0]); //Uoc Thuc Hien
            String[] arrayKhNam = KH_KH_NAM.toArray(new String[0]); //KH nam
            
            //CuongBM: Convert array --> array oracle su dung
            ARRAY oracle_arrayMaCT = new ARRAY(des,conn,arrayMaCT);
            ARRAY oracle_arrayUocTH = new ARRAY(des,conn,arrayUocTH);
            ARRAY oracle_arrayKhNam = new ARRAY(des,conn,arrayKhNam);
            
            //CuongBM: 06Nov14
            String strStoreproce = "{call VBSP_RPT_KHNV.save_xdkh(?,?,?,?,?,?,?,?,?,?,?)}";
//            ResultSet reset = null;

            try {
                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                
                calstatement.setString(1, posCD);
                calstatement.setString(2, maCn);
                calstatement.setString(3, namBc);
                calstatement.setString(4, userId);
                  calstatement.setString(5, capbc);
                calstatement.setString(6, xa_pgd);
                calstatement.setArray(7, oracle_arrayMaCT);
                calstatement.setArray(8, oracle_arrayUocTH);
                calstatement.setArray(9, oracle_arrayKhNam);
                calstatement.registerOutParameter(10, oracle.jdbc.OracleTypes.NUMBER);
                calstatement.registerOutParameter(11, oracle.jdbc.OracleTypes.VARCHAR);
                
                //Thuc hien execute lay du lieu
                calstatement.execute();
                
                //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
                int pn_err_cd = calstatement.getInt(10);
                //thu hien lay mo ta loi
                String strEdd_txt = calstatement.getString(11);
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
                CoreLogger.error(this.getClass().getName() + " save_xdkh -> " + e.getMessage());
                throw new SQLException(e);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DaoXdkh.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Loi trong ham save_xdkh "+posCD+" " + ex.getMessage());
            CoreLogger.error(this.getClass().getName()+ " save_xdkh -> "+posCD+" " + ex.getMessage());
            throw new SQLException(ex);
        }
        return true;
    }
    public boolean save_xdkh(String posCD,String maCn,String namBc,String userId,  List<String> KH_MA_CT,
            List<String> KH_STT_HT,List<String> KH_CHI_TIEU,List<String> KH_UOC_TH,List<String> KH_KH_NAM,
            List<String> KH_DN,List<String> KH_FONTWEIGHT,List<String> KH_CAPHT,List<String> KH_STT){
    
        boolean bSuccess = false;
        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            
            ArrayDescriptor des = ArrayDescriptor.createDescriptor("ARRAY_TABLE", conn);
            
            //CuongBM: Convert List to array
            String[] arrayMaCT = KH_MA_CT.toArray(new String[0]);   //Ma Chi Tieu
            String[] arrayUocTH = KH_UOC_TH.toArray(new String[0]); //Uoc Thuc Hien
            String[] arrayKhNam = KH_KH_NAM.toArray(new String[0]); //KH nam
            
            //CuongBM: Convert array --> array oracle su dung
            ARRAY oracle_arrayMaCT = new ARRAY(des,conn,arrayMaCT);
            ARRAY oracle_arrayUocTH = new ARRAY(des,conn,arrayUocTH);
            ARRAY oracle_arrayKhNam = new ARRAY(des,conn,arrayKhNam);
            
            //CuongBM: 06Nov14
            String strStoreproce = "{call VBSP_RPT_KHNV.save_xdkh(?,?,?,?,?,?,?,?,?)}";
//            ResultSet reset = null;

            try {
                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                
                calstatement.setString(1, posCD);
                calstatement.setString(2, maCn);
                calstatement.setString(3, namBc);
                calstatement.setString(4, userId);
                calstatement.setArray(5, oracle_arrayMaCT);
                calstatement.setArray(6, oracle_arrayUocTH);
                calstatement.setArray(7, oracle_arrayKhNam);
                calstatement.registerOutParameter(8, oracle.jdbc.OracleTypes.NUMBER);
                calstatement.registerOutParameter(9, oracle.jdbc.OracleTypes.VARCHAR);
                
                //Thuc hien execute lay du lieu
                calstatement.execute();
                
                //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
                int pn_err_cd = calstatement.getInt(8);
                //thu hien lay mo ta loi
                String strEdd_txt = calstatement.getString(9);
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
                CoreLogger.error(this.getClass().getName() + " save_xdkh -> " + e.getMessage());
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DaoXdkh.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Loi trong ham save_xdkh "+posCD+" " + ex.getMessage());
            CoreLogger.error(this.getClass().getName()+ " save_xdkh -> "+posCD+" " + ex.getMessage());
            return false;
        }
        return true;
    }
}
