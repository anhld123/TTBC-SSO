/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import vbsp.ims.model.Branch;

/**
 *
 * @author Trung
 */
public class BranchDao {

    //--------------------------------------------------------------------------    
    private static DaoConnect daoConnect;
    private static Connection conn;

    //--------------------------------------------------------------------------    
    public BranchDao() {
        daoConnect = new DaoConnect();
    }

    //--------------------------------------------------------------------------    
    public Branch getBranchInfor(String user_name, int report_grade) {
        Branch branch = new Branch();
        
        CallableStatement calstatement;
        String strStoreproce
                = "{call app_local_variable.sp_get_branch(?, ?, ?)}";
        ResultSet rs;
       String pos_cd, pos_name, pos_address, pos_fax, pos_mobile, pos_sbvcode, pos_flag, main_pos, status, maker_id, maker_dt;
        try {
            conn = daoConnect.getConnect();
            calstatement = conn.prepareCall(strStoreproce,
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            calstatement.setString(1, user_name);
            calstatement.setInt(2, report_grade);
            calstatement.registerOutParameter(3, oracle.jdbc.OracleTypes.CURSOR);
            calstatement.execute();
            rs = (ResultSet) calstatement.getObject(3);
            while (rs.next()) {
                pos_cd = rs.getString("PO_MA");
                pos_name = rs.getString("PO_TEN");
                pos_address = rs.getString("PO_DIACHI");
                pos_fax = rs.getString("PO_FAX");
                pos_mobile = rs.getString("PO_MOBILE");
                pos_sbvcode = rs.getString("PO_MASBV");
                pos_flag = rs.getString("PO_POSFLG");
                main_pos= rs.getString("PO_MACN");
                status= rs.getString("PO_STATUS");
                maker_id= rs.getString("MAKER_ID");
                maker_dt= rs.getString("MAKER_DT");                
                branch.setInfor(pos_cd, pos_name, pos_address, pos_fax, pos_mobile,
                        pos_sbvcode, pos_flag, main_pos, status, maker_id, maker_dt);
            }
            rs.close();
            calstatement.close();
        } catch (SQLException ex) {
            System.err.println("BranchDao.getBranchInfor-->" + ex.getMessage());
        }
        return branch;
    }
    
    public boolean updateBranchInfor(String user_name,Branch branch){
        int row_cnt = 0;
        String sys_date = new SimpleDateFormat("dd-MMM-yyyy hh:mm:ss").format(new Date());
        try {            
            Connection con;
            con = daoConnect.getConnect();
            String sql = "update dmpos set PO_DIACHI = ?, PO_FAX = ?, PO_MOBILE = ?, maker_id = ?, maker_dt = ?"
                    + " where po_ma =?";
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, branch.getPos_address());
                ps.setString(2, branch.getPos_fax());
                ps.setString(3, branch.getPos_mobile());
                ps.setString(4, user_name);
                ps.setString(5, sys_date);
                ps.setString(6, branch.getPos_cd());
                row_cnt = ps.executeUpdate();
            }
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(BranchDao.class.getName()).log(Level.SEVERE, null, ex);
        }
       return row_cnt > 0;
    }

    public boolean updateBranchTable(List<Branch> branches) {
        try {
            String sql = "update dmpos set po_ten = ?,po_diachi = ?,po_fax =?,po_mobile=?, maker_id = ?, maker_dt = ? "
                    + " where po_ma = ?";
            conn = daoConnect.getConnect();
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                for (Branch branch : branches) {
                    ps.setString(1, branch.getPos_name());
                    ps.setString(2, branch.getPos_address());
                    ps.setString(3, branch.getPos_fax());
                    ps.setString(4, branch.getPos_mobile());
                    ps.setString(5, branch.getMaker_id());
                    ps.setString(6, branch.getMaker_dt());
                    ps.setString(7, branch.getPos_cd());
                    ps.addBatch();
                }
                ps.executeBatch();
            }
            return true;
        } catch (SQLException ex) {
            System.err.println("BranchDao.updateBranchTable-->" + ex.getMessage());
            return false;
        }
    }
}
