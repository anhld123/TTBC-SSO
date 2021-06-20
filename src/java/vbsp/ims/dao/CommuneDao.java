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
import java.util.logging.Level;
import java.util.logging.Logger;
import vbsp.ims.model.Commune;
import vbsp.ims.report.fast.ListValue;

/**
 *
 * @author Trung
 */
public class CommuneDao {

    //--------------------------------------------------------------------------    
    private static DaoConnect daoConnect;
    private static Connection conn;

    //--------------------------------------------------------------------------    
    public CommuneDao() {
//        daoConnect = new DaoConnect();
    }

    //--------------------------------------------------------------------------    
    public List<Commune> getCommuneList(String user_name, int report_grade) {
        ArrayList<Commune> communes = new ArrayList();
        CallableStatement calstatement;
        String strStoreproce
                = "{call app_commune.getCommuneList(?, ?, ?)}";
        ResultSet rs;
        String commune_id, commune_name, status, pgd_ql;
        String gdx_flg, xa135_flg, nongthonmoi_flg, sCanbotdPt;
        int ngaygdx;
        try {

            daoConnect = new DaoConnect();
            conn = daoConnect.getConnect();

            calstatement = conn.prepareCall(strStoreproce,
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            calstatement.setString(1, user_name);
            calstatement.setInt(2, report_grade);
            calstatement.registerOutParameter(3, oracle.jdbc.OracleTypes.CURSOR);
            calstatement.execute();
            rs = (ResultSet) calstatement.getObject(3);
            while (rs.next()) {
                commune_id = rs.getString("COMMUNE_ID");
                commune_name = rs.getString("COMMUNE_NAME");
                status = rs.getString("TRANGTHAI");
                pgd_ql = rs.getString("PGD_QL");
                gdx_flg = rs.getString("GDXFLG");
                xa135_flg = rs.getString("XA135FLG");
                nongthonmoi_flg = rs.getString("NONGTHONMOI_FLG");
                sCanbotdPt = rs.getString("CANBOTDPT");
                ngaygdx = rs.getInt("NGAYGDX");
                communes.add(new Commune(commune_id, commune_name, gdx_flg, xa135_flg,
                        status, ngaygdx, pgd_ql, nongthonmoi_flg, sCanbotdPt));
            }
            rs.close();
            calstatement.close();
        } catch (SQLException ex) {
            System.err.println("CommuneDao.getCommuneList-->" + ex.getMessage());
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(CommuneDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return communes;
    }

    public boolean updateCommune(String user_name, int report_grade, Commune commune) {
        CallableStatement calstatement;
        String strStoreproce
                = "{call app_commune.updateCommune(?, ?, ?, ?, ?, ?)}";
        int updated_row = 0;
        try {

            daoConnect = new DaoConnect();
            conn = daoConnect.getConnect();
            calstatement = conn.prepareCall(strStoreproce,
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String newcommune_flag;
            newcommune_flag = commune.getNongthonmoi_flg();
            calstatement.setString(1, commune.getCommune_id());
            calstatement.setString(2, commune.getCommune_name());
            calstatement.setString(3, newcommune_flag);
            calstatement.registerOutParameter(4, oracle.jdbc.OracleTypes.INTEGER);
            calstatement.executeUpdate();
            updated_row = (int) calstatement.getObject(4);
            calstatement.close();
        } catch (SQLException ex) {
            System.err.println("CommuneDao.updated_row-->" + ex.getMessage());
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(CommuneDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return updated_row > 0;
    }

    public boolean updateCommune_tmp(String user_name, int report_grade, Commune commune) {
        CallableStatement calstatement;
        String strStoreproce
                = "{call app_commune.updateCommune_tmp(?, ?, ?, ?, ?, ?, ?)}";
        int updated_row = 0;
        try {

            daoConnect = new DaoConnect();
            conn = daoConnect.getConnect();
            calstatement = conn.prepareCall(strStoreproce,
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String newcommune_flag;
            newcommune_flag = commune.getNongthonmoi_flg();
            calstatement.setString(1, user_name);
            calstatement.setInt(2, report_grade);
            calstatement.setString(3, commune.getCommune_id());
            calstatement.setString(4, commune.getCommune_name());
            calstatement.setString(5, newcommune_flag);
            calstatement.setString(6, commune.getCanbotdpt());
            calstatement.registerOutParameter(7, oracle.jdbc.OracleTypes.INTEGER);
            calstatement.executeUpdate();
            updated_row = (int) calstatement.getObject(7);
            calstatement.close();
        } catch (SQLException ex) {
            System.err.println("CommuneDao.updated_row-->" + ex.getMessage());
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(CommuneDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return updated_row > 0;
    }

    /**
     *
     * @param sync_pos_cd
     * @return List
     */
    public List<Commune> getCommuneList_sync(String sync_pos_cd) {
        ArrayList<Commune> communes = new ArrayList();
        CallableStatement calstatement;
        String strStoreproce
                = "{call app_commune.getCommuneList_sync(?, ?, ?, ?, ?)}";
        ResultSet rs;
        String commune_id, commune_name, status, pgd_ql;
        String gdx_flg, xa135_flg, nongthonmoi_flg, maker_id, maker_dt, sCanbotdPt;
        int ngaygdx;
        try {
            daoConnect = new DaoConnect();
            conn = daoConnect.getConnect();
            calstatement = conn.prepareCall(strStoreproce,
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            calstatement.setString(1, sync_pos_cd);
            calstatement.registerOutParameter(2, oracle.jdbc.OracleTypes.NUMBER);
            calstatement.registerOutParameter(3, oracle.jdbc.OracleTypes.VARCHAR);
            calstatement.registerOutParameter(4, oracle.jdbc.OracleTypes.CURSOR);
            calstatement.registerOutParameter(5, oracle.jdbc.OracleTypes.NUMBER);
            calstatement.execute();
            rs = (ResultSet) calstatement.getObject(4);
            while (rs.next()) {
                commune_id = rs.getString("COMMUNE_ID");
                commune_name = rs.getString("COMMUNE_NAME");
                status = rs.getString("TRANGTHAI");
                pgd_ql = rs.getString("PGD_QL");
                gdx_flg = rs.getString("GDXFLG");
                xa135_flg = rs.getString("XA135FLG");
                nongthonmoi_flg = rs.getString("NONGTHONMOI_FLG");
                sCanbotdPt = rs.getString("CANBOTDPT");
                ngaygdx = rs.getInt("NGAYGDX");
                maker_id = rs.getString("MAKER_ID");
                maker_dt = rs.getString("MAKER_DT");
                communes.add(new Commune(commune_id, commune_name, gdx_flg, xa135_flg,
                        status, ngaygdx, pgd_ql, nongthonmoi_flg, sCanbotdPt, maker_id, maker_dt));
            }
            rs.close();
            calstatement.close();
        } catch (SQLException ex) {
            System.err.println("CommuneDao.getCommuneList_sync-->" + ex.getMessage());
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(CommuneDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return communes;
    }

    public boolean updateCommuneTable(List<Commune> communes) {
        
        boolean returnValue = false;
        
        try {
            String sql = "update dmxa set nongthonmoi_flg = ?,canbotdpt =?, maker_id = ?, maker_dt = ? "
                    + " where ma = ? and pgd_ql = ?";
            daoConnect = new DaoConnect();
            conn = daoConnect.getConnect();
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                for (Commune commune : communes) {
                    ps.setString(1, commune.getNongthonmoi_flg());
                    ps.setString(2, commune.getCanbotdpt());
                    ps.setString(3, commune.getMaker_id());
                    ps.setString(4, commune.getMaker_dt());
                    ps.setString(5, commune.getCommune_id());
                    ps.setString(6, commune.getPgd_ql());
                    ps.addBatch();
                }
                ps.executeBatch();
            }
            returnValue = true;
        } catch (SQLException ex) {
            System.err.println("CommuneDao.updateCommuneTable-->" + ex.getMessage());
            returnValue = false;
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(CommuneDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return returnValue;
    }

    public List<ListValue> getSeller(String pv_pos_cd) {
        ArrayList<ListValue> sellers = new ArrayList<ListValue>();
        CallableStatement calstatement;
        String strStoreproce
                = "{call app_commune.getSellerList(?, ?, ?)}";
        ResultSet rs;
        try {
            daoConnect = new DaoConnect();
            conn = daoConnect.getConnect();
            calstatement = conn.prepareCall(strStoreproce,
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            calstatement.setString(1, pv_pos_cd);
            calstatement.setString(2, "T");
            calstatement.registerOutParameter(3, oracle.jdbc.OracleTypes.CURSOR);
            calstatement.execute();
            rs = (ResultSet) calstatement.getObject(3);
            String sKey, sDesc;
            while (rs.next()) {
                sKey = rs.getString("MA");
                sDesc = rs.getString("TEN");
                sellers.add(new ListValue(sKey, sDesc));
            }
            rs.close();
            calstatement.close();
        } catch (SQLException ex) {
            System.err.println("CommuneDao.getSeller-->" + ex.getMessage());
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(CommuneDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return sellers;
    }
}
