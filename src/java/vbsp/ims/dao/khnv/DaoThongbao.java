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
import java.util.ArrayList;
import vbsp.ims.dao.DaoConnect;
import vbsp.ims.log.CoreLogger;
import vbsp.ims.model.khnv.CTChuaKhopModel;
import vbsp.ims.model.khnv.POSModel;

/**
 *
 * @author CuongBM0211
 */
public class DaoThongbao {
    public ArrayList<POSModel> getDataChuaXDKH(String maPGD, String maCn, String reportGrade, int namBc){
        ArrayList<POSModel> posModelList = new ArrayList<POSModel>();
        boolean bSuccess = false;
        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            
            //CuongBM: 06Nov14
            String strStoreproce = "{call VBSP_RPT_KHNV.get_data_chua_xd(?,?,?,?,?)}";
            ResultSet reset = null;

            try {
                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                
                calstatement.setString(1, maPGD);
                calstatement.setString(2, maCn);
                calstatement.setString(3, reportGrade);
                calstatement.setInt(4, namBc);
                
                calstatement.registerOutParameter(5, oracle.jdbc.OracleTypes.CURSOR);
                
                //Thuc hien execute lay du lieu
                calstatement.execute();
                
                //Lay cursor ra resultset
                //Lay cursor ra resultset
                reset = (ResultSet) calstatement.getObject(5);
                
                while (reset.next()) {
                    POSModel obj = new POSModel();
                    
                    obj.setId(reset.getString("PO_MA"));
                    obj.setDesc(reset.getString("PO_TEN"));
                    
                    //Them vao list
                    posModelList.add(obj);
                }                

                if (calstatement != null) {
                    calstatement.close();
                }
                if (conn != null) {
                    conn.close();
                }
                
            } catch (SQLException e) {
                System.err.print(e.getMessage());
                CoreLogger.error(this.getClass().getName() + " getDataChuaXDKH -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham getDataChuaXDKH " + e.getMessage());
            CoreLogger.error(this.getClass().getName()+ " getDataChuaXDKH -> " + e.getMessage());
        }
        return posModelList;
    }
    
    //Lay danh sach chi tieu chua khop
    public ArrayList<CTChuaKhopModel> getDataCTCK(String maPGD, String maCn, String reportGrade, int namBc){
        ArrayList<CTChuaKhopModel> posModelList = new ArrayList<CTChuaKhopModel>();
        boolean bSuccess = false;
        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            
            //CuongBM: 06Nov14
            String strStoreproce = "{call VBSP_RPT_KHNV.get_data_ctck(?,?,?,?,?)}";
            ResultSet reset = null;

            try {
                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                
                calstatement.setString(1, maPGD);
                calstatement.setString(2, maCn);
                calstatement.setString(3, reportGrade);
                calstatement.setInt(4, namBc);
                
                calstatement.registerOutParameter(5, oracle.jdbc.OracleTypes.CURSOR);
                
                //Thuc hien execute lay du lieu
                calstatement.execute();
                
                //Lay cursor ra resultset
                reset = (ResultSet) calstatement.getObject(5);
                
                while (reset.next()) {
                    CTChuaKhopModel obj = new CTChuaKhopModel();
                    
                    obj.setMaPOS(reset.getString("PO_MA"));
                    obj.setTenPOS(reset.getString("PO_TEN"));
                    obj.setMaCT(reset.getString("KH_MA_CT"));
                    obj.setTenCT(reset.getString("KH_CHI_TIEU"));
                    obj.setKhGiaoTW(reset.getDouble("GIAO_DC_TW"));
                    obj.setKhGiaoCN(reset.getDouble("GIAO_DC_CN"));
                    
                    //Them vao list
                    posModelList.add(obj);
                }                

                if (calstatement != null) {
                    calstatement.close();
                }
                if (conn != null) {
                    conn.close();
                }
                
            } catch (SQLException e) {
                System.err.print(e.getMessage());
                CoreLogger.error(this.getClass().getName() + " getDataCTCK -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham getDataCTCK " + e.getMessage());
            CoreLogger.error(this.getClass().getName()+ " getDataCTCK -> " + e.getMessage());
        }
        return posModelList;
    }
}
