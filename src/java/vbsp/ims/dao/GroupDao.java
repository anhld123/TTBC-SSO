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
import java.util.ArrayList;
import java.util.List;
import vbsp.ims.model.Commune;
import vbsp.ims.model.Group;

/**
 *
 * @author Trung
 */
public class GroupDao {

    //--------------------------------------------------------------------------    
    private static DaoConnect daoConnect;
    private static Connection conn;

    //--------------------------------------------------------------------------    
    public GroupDao() {
        daoConnect = new DaoConnect();
        conn =null;
    }

    //--------------------------------------------------------------------------    
    public List<Group> getGroupList(String user_name, int report_grade) {
        ArrayList<Group> groups = new ArrayList();
        CallableStatement calstatement;
        String strStoreproce
                = "{call app_group.getGroupList(?, ?, ?)}";
        ResultSet rs;
        String lv_group_id, lv_leader_group_name,lv_mass_org,
                lv_commune_id, lv_status, lv_standard_flg,lv_corrupt_flg,
                lv_pos_cd,lv_maker_id,lv_maker_dt;                
        try {
            if (conn == null)
                conn = daoConnect.getConnect();
            calstatement = conn.prepareCall(strStoreproce,
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            calstatement.setString(1, user_name);
            calstatement.setInt(2, report_grade);
            calstatement.registerOutParameter(3, oracle.jdbc.OracleTypes.CURSOR);
            calstatement.execute();
            rs = (ResultSet) calstatement.getObject(3);
            while (rs.next()) {
                lv_group_id = rs.getString("TO_MATO");
                lv_leader_group_name = rs.getString("TO_TENTT");
                lv_mass_org = rs.getString("TO_DVUT");
                lv_commune_id = rs.getString("TO_MADP");
                lv_status = rs.getString("TRANGTHAI");
                lv_standard_flg = rs.getString("TO_TLDUNGQD");
                lv_corrupt_flg = rs.getString("TO_THAMOCD");
                lv_pos_cd =  rs.getString("TO_MAPGD");
                lv_maker_id =  rs.getString("MAKER_ID");
                lv_maker_dt =  rs.getString("MAKER_DT");
                groups.add(new Group(lv_group_id, 
                        lv_leader_group_name, 
                        lv_mass_org, 
                        lv_commune_id, 
                        lv_status, 
                        lv_standard_flg, 
                        lv_corrupt_flg, 
                        lv_pos_cd, 
                        lv_maker_id, 
                        lv_maker_dt));
            }
            rs.close();
            calstatement.close();
        } catch (Exception ex) {
            System.err.println("Lay thong tin to nhom:  " + ex.getMessage());
//            System.err.println("GroupDao.getGroupList-->" + ex.getMessage());
        }
        return groups;
    }   
    
    
    public List<Group> getGroupList_sync(String pv_user_name,String sync_pos_cd) {
        ArrayList<Group> groups = new ArrayList();
        CallableStatement calstatement;
        String strStoreproce
                = "{call app_group.getGroupList_sync(?, ?, ?, ? , ? , ?)}";
        ResultSet rs;
        String lv_group_id, lv_leader_group_name,lv_mass_org,
                lv_commune_id, lv_status, lv_standard_flg,lv_corrupt_flg,
                lv_pos_cd,lv_maker_id,lv_maker_dt;                
        try {
            if (conn == null)
                conn = daoConnect.getConnect();
            calstatement = conn.prepareCall(strStoreproce,
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            calstatement.setString(1, pv_user_name);            
            calstatement.setString(2, sync_pos_cd);            
            calstatement.registerOutParameter(3, oracle.jdbc.OracleTypes.INTEGER);
            calstatement.registerOutParameter(4, oracle.jdbc.OracleTypes.VARCHAR);
            calstatement.registerOutParameter(5, oracle.jdbc.OracleTypes.CURSOR);
            calstatement.registerOutParameter(6, oracle.jdbc.OracleTypes.INTEGER);
            calstatement.execute();
            rs = (ResultSet) calstatement.getObject(5);
            while (rs.next()) {
                lv_group_id = rs.getString("TO_MATO");
                lv_leader_group_name = rs.getString("TO_TENTT");
                lv_mass_org = rs.getString("TO_DVUT");
                lv_commune_id = rs.getString("TO_MADP");
                lv_status = rs.getString("TRANGTHAI");
                lv_standard_flg = rs.getString("TO_TLDUNGQD");
                lv_corrupt_flg = rs.getString("TO_THAMOCD");
                lv_pos_cd =  rs.getString("TO_MAPGD");
                lv_maker_id =  rs.getString("MAKER_ID");
                lv_maker_dt =  rs.getString("MAKER_DT");
                groups.add(new Group(lv_group_id, 
                        lv_leader_group_name, 
                        lv_mass_org, 
                        lv_commune_id, 
                        lv_status, 
                        lv_standard_flg, 
                        lv_corrupt_flg, 
                        lv_pos_cd, 
                        lv_maker_id, 
                        lv_maker_dt));
            }
            rs.close();
            calstatement.close();
        } catch (SQLException ex) {
            System.err.println("GroupDao.getGroupList-->" + ex.getMessage());
        }
        return groups;
    }   
    
    public boolean updateGroup_tmp(String user_name, int report_grade, Group group) {
        CallableStatement calstatement;
        String strStoreproce
                = "{call app_group.updateGroup_tmp(?, ?, ?, ?, ?, ?)}";
        int updated_row = 0;
        try {
            if (conn == null)
                conn = daoConnect.getConnect();
            calstatement = conn.prepareCall(strStoreproce,
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);                        
            calstatement.setString(1, user_name);
            calstatement.setInt(2, report_grade);
            calstatement.setString(3, group.getGroup_id());
            calstatement.setString(4, group.getStandard_flg());
            calstatement.setString(5, group.getCorrupt_flg());
            calstatement.registerOutParameter(6, oracle.jdbc.OracleTypes.INTEGER);
            calstatement.executeUpdate();
            updated_row = (int) calstatement.getObject(6);
            calstatement.close();
        } catch (SQLException ex) {
            System.err.println("GroupDao.updateGroup_tmp-->" + ex.getMessage());
        }
        return updated_row > 0;
    }
    
    public int updateGroup_table(String user_name, int report_grade,String pos_cd){
        CallableStatement calstatement;
        String strStoreproce
                = "{call app_group.updateGroup_Table(?, ?, ?, ?, ?, ?)}";
        int updated_row = 0;
        try {
            if (conn == null)
                conn = daoConnect.getConnect();
            calstatement = conn.prepareCall(strStoreproce,
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);                        
            calstatement.setString(1, user_name);
            calstatement.setInt(2, report_grade);
            calstatement.setString(3, pos_cd);
            calstatement.registerOutParameter(4, oracle.jdbc.OracleTypes.INTEGER);
            calstatement.registerOutParameter(5, oracle.jdbc.OracleTypes.VARCHAR);
            calstatement.registerOutParameter(6, oracle.jdbc.OracleTypes.INTEGER);
            calstatement.executeUpdate();
            updated_row = (int) calstatement.getObject(6);
            calstatement.close();
        } catch (Exception ex) {
            System.err.println("GroupDao.updateGroup_tmp-->" + ex.getMessage());
        }
        return updated_row ;
    }

    public boolean updateGroupTable_HO(List<Group> groups) {
        try {
            String sql = "begin app_group.updateGroup_HO(? , ? , ? ,? ,? ,?); end;";
            if (conn == null)
                conn = daoConnect.getConnect();
            try (PreparedStatement ps = 
                    conn.prepareStatement(sql)) {
                for (Group group : groups) {                    
                    ps.setString(1, group.getPos_cd());
                    ps.setString(2, group.getGroup_id());
                    ps.setString(3, group.getStandard_flg());
                    ps.setString(4, group.getCorrupt_flg());
                    ps.setString(5, group.getMaker_id());
                    ps.setString(6, group.getMaker_dt());                                        
                    ps.addBatch();
                }
                ps.executeBatch();                
            }
            return true;
        } catch (SQLException ex) {
            System.err.println("GroupDao.updateGroupTable_HO-->" + ex.getMessage());
            return false;
        }
    }
}
