/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.bcqt.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;
import vbsp.ims.bcqt.model.QT_DULIEU_NT;
import vbsp.ims.bcqt.model.ThuyetMinh;
import vbsp.ims.dao.DaoConnect;
import vbsp.ims.log.CoreLogger;

/**
 *
 * @author Trung
 */
public class TmDao {

    public static TmDao newInstance() {
        return new TmDao();
    }
    
    // HAM LUU DU LIEU CHUNG CHO BAO CAO NHAP TAY
    public boolean saveTM(String khoa, String username, String ngaybc,String Grade,
            ThuyetMinh tm, String update_type
    ){
        Connection connection = new DaoConnect().getConnect();
        CallableStatement cs = null;
        boolean bSuccess = false;
        try {
            cs = connection.prepareCall("{call BCQT_THUYETMINH.SP_SAVE_DATA_BCQT_TM(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}");
            cs.setString(1, khoa);
            cs.setString(2, username);
            cs.setString(3, ngaybc);
            cs.setString(4, Grade);
            cs.setString(5, tm.getCode());
            cs.setString(6, tm.getTitle());
            cs.setString(7, tm.getContent());
            cs.setString(8, tm.getPos_cd());
            cs.setString(9, update_type);
            cs.registerOutParameter(10, oracle.jdbc.OracleTypes.NUMBER);
            cs.registerOutParameter(11, oracle.jdbc.OracleTypes.VARCHAR);
            cs.execute();
            String ketqua = (String)cs.getObject(11);
            bSuccess = ketqua.equals("SUCCESS");
        } catch (SQLException e) {
            //e.printStackTrace();
            System.err.println("Loi trong ham saveTM " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " saveTM -> " + e.getMessage());
            bSuccess = false;
        } finally {            
            try {
                cs.close();
            } catch (SQLException ex) {
                Logger.getLogger(TmDao.class.getName()).log(Level.SEVERE, null, ex);
            }


            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(TmDao.class.getName()).log(Level.SEVERE, null, ex);
            }            
        }
        return bSuccess;
    }
    
    // HAM LAY MA THUYET MINH MOI
    public String getTMCODE(String khoa, String username, String ngaybc,String Grade
    ) throws SQLException {
        Connection connection = new DaoConnect().getConnect();
        CallableStatement cs = null;
        String ketqua="XXX";
        try {
            cs = connection.prepareCall("{call BCQT_THUYETMINH.SP_GET_NEW_TM_CODE(?, ?, ? , ?, ?)}");
            cs.setString(1, khoa);
            cs.setString(2, username);
            cs.setString(3, ngaybc);            
            cs.setString(4, Grade);            
            cs.registerOutParameter(5, oracle.jdbc.OracleTypes.VARCHAR);
            cs.execute();
            ketqua = (String)cs.getObject(5);            
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Loi trong ham getTMCODE " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " getTMCODE -> " + e.getMessage());            
        } finally {
            if (cs != null) {
                cs.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return ketqua;
    }
    
    // HAM LAY MA THUYET MINH MOI
    public String getCO_TM(String khoa) throws SQLException {
        Connection connection = new DaoConnect().getConnect();
        CallableStatement cs = null;
        String ketqua="N";
        try {
            cs = connection.prepareCall("{call BCQT_THUYETMINH.SP_GET_TM_FLAG(?, ?)}");
            cs.setString(1, khoa);            
            cs.registerOutParameter(2, oracle.jdbc.OracleTypes.VARCHAR);
            cs.execute();
            ketqua = (String)cs.getObject(2);            
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Loi trong ham getCO_TM " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " getCO_TM -> " + e.getMessage());            
        } finally {
            if (cs != null) {
                cs.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return ketqua;
    }
    
    // TRUNG BO SUNG PHAN NHAP THUYET MINH
    public List<QT_DULIEU_NT> getDataBCQT_TM(Connection conn, String sKhoa, String sNgaybc, String sUser,
            String sGrade, List<String> lstArrPoscd) {
        List<QT_DULIEU_NT> lstBcqt_NT = new ArrayList<>();
        try {
            ArrayDescriptor des = ArrayDescriptor.createDescriptor("POS_CD", conn);
            String[] arrayPoscd = lstArrPoscd.toArray(new String[0]);
            ARRAY oracle_arrayPoscd = new ARRAY(des, conn, arrayPoscd);

            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call BCQT_THUYETMINH.SP_GET_DATA_BCQT_TM(?,?,?,?,?,?,?,?)}";
            ResultSet reset;
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
                    // PHAN CHUNG
                    value.setKHOA(reset.getString("KHOA"));
                    value.setTHUTU(reset.getInt("TT_DONG"));
                    value.setTT_HIENTHI(reset.getString("TT_HIENTHI"));
                    value.setMA(reset.getString("MA"));
                    value.setTEN(reset.getString("TEN"));
                    value.setNGAYBC(reset.getDate("NGAYBC"));
                    value.setNAMBC(reset.getInt("NAMBC"));
                    value.setMAPGD(reset.getString("MAPGD"));
                    value.setCO_TONGHOP(reset.getString("CO_TONGHOP"));
                    value.setMACN(reset.getString("MACN"));                    
                    // PHAN RIENG
                    value.setD1(reset.getString("D1"));
                    value.setD2(reset.getString("D2"));
                    value.setD3(reset.getString("D3"));
                    value.setD4(reset.getString("D4"));
                    value.setD5(reset.getString("D5"));
                    value.setD6(reset.getString("D6"));
                    value.setD7(reset.getString("D7"));
                    value.setD8(reset.getString("D8"));
                    value.setD9(reset.getString("D9"));                   
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
                CoreLogger.error(this.getClass().getName() + " getDataBCQT_TM -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham getDataBCQT_NT " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " getDataBCQT_NT -> " + e.getMessage());
        }
        return lstBcqt_NT;
    }
    

}
