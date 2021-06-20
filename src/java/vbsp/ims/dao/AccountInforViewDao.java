/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vbsp.ims.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import vbsp.ims.model.AccountInforView;

/**
 *
 * @author Trung
 */
public class AccountInforViewDao {
    //--------------------------------------------------------------------------    
    private static final DaoConnect daoConnect;
    //--------------------------------------------------------------------------    
    static {
        daoConnect = new DaoConnect();
    }
    //--------------------------------------------------------------------------    
    public static ArrayList<AccountInforView> getAccountInforList(int type) {
        ArrayList<AccountInforView> lcAccountInforList = new ArrayList();
        String lcQuery;
        if (type == 1){
            lcQuery = "SELECT BANK_AC ACCOUNT,SBV_GL_SL,GL_SL,CCY_CD ,AC_DESC NAME, '' D_C_FLG "
                    + " FROM DMTKGL ORDER BY 2,1";
        }
        else {
            lcQuery = "SELECT SBV_TKCAP3 ACCOUNT,'' SBV_GL_SL,'' GL_SL,'' CCY_CD,SBV_TENTK NAME,"
                    + " SBV_NOCO D_C_FLG FROM DMTKSBV ORDER BY 1";
        }                    
        try {
            String lc_account, lc_sbv_gl_sl, lc_gl_sl, lc_ccy_cd, lc_name, lc_d_c_flg;
            Connection con = daoConnect.getConnect();
            Statement ps = con.createStatement();
            ResultSet rs = ps.executeQuery(lcQuery);
            while (rs.next()) {
                lc_account = rs.getString("ACCOUNT");
                lc_sbv_gl_sl = rs.getString("SBV_GL_SL");
                lc_gl_sl = rs.getString("GL_SL");
                lc_ccy_cd = rs.getString("CCY_CD");
                lc_name = rs.getString("NAME");
                lc_d_c_flg = rs.getString("D_C_FLG");
                lcAccountInforList.add(new AccountInforView(lc_account,lc_name,
                        lc_sbv_gl_sl,lc_gl_sl,lc_ccy_cd,lc_d_c_flg));                        
            }
            rs.close();
            ps.close();
        } catch (SQLException ex) {
            System.err.println("Loi --> Khong lay duoc danh tai khoan");
        }
        return lcAccountInforList;
    }    
    //--------------------------------------------------------------------------        
}
