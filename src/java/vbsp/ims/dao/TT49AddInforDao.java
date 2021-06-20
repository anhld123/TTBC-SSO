/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vbsp.ims.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import vbsp.ims.model.TT49AddInforView;

/**
 *
 * @author Trung
 */
public class TT49AddInforDao {
    //--------------------------------------------------------------------------    
    private static DaoConnect daoConnect;    
    
    public TT49AddInforDao(){
        daoConnect = new DaoConnect();
    }
    
    public ArrayList<TT49AddInforView> list() {
        ArrayList<TT49AddInforView> laddInforList = new ArrayList();
        try {            
            String lprocedure;
            lprocedure = "{call APP_TT49.sp_view(?)}";
            String lcode, ldescript, ld2, ld3, ld4;
            int ld1;
            Connection connect = daoConnect.getConnect();
            CallableStatement calstatement = connect.prepareCall(lprocedure,
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            calstatement.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            calstatement.execute();
            ResultSet rs = (ResultSet)calstatement.getObject(1);
                    while (rs.next()) {
                        lcode = rs.getString("CODE");
                        ldescript = rs.getString("DESCRIPTION");
                        ld1 = rs.getInt("D1");
                        ld2 = rs.getString("D2");
                        ld3 = rs.getString("D3");
                        ld4 = rs.getString("D4");
                        laddInforList.add(new TT49AddInforView(lcode,ldescript,ld1,ld2,ld3,ld4));
                    }            
        } catch (SQLException ex) {
            Logger.getLogger(TT49AddInforDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return laddInforList;
    }
    
    public String update(TT49AddInforView addView){
        String status = "";    
        try {            
            String lprocedure = "{call APP_TT49.sp_update(?,?,?,?,?,?)}";
            Connection connect = daoConnect.getConnect();
            CallableStatement calstatement = connect.prepareCall(lprocedure);
            calstatement.setString(1, addView.getCode());
            calstatement.setInt(2, addView.getD1());
            calstatement.setString(3, addView.getD2());
            calstatement.setString(4, addView.getD3());
            calstatement.setString(5, addView.getD4());
            calstatement.registerOutParameter(6, oracle.jdbc.OracleTypes.VARCHAR);
            int rec_cnt = calstatement.executeUpdate();
            status = (String)calstatement.getObject(6);                     
        } catch (SQLException ex) {
            Logger.getLogger(TT49AddInforDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return status;
    }
}
