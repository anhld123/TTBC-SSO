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
import vbsp.ims.model.Variable;

/**
 *
 * @author Trung
 */
public class VariableDao {

    //--------------------------------------------------------------------------    
    private static DaoConnect daoConnect;
    private static Connection conn;
    //--------------------------------------------------------------------------    

    public VariableDao() {
//        daoConnect = new DaoConnect();
//        conn = daoConnect.getConnect();
    }

    //--------------------------------------------------------------------------    
    public ArrayList<Variable> getVariableList(String pos_cd, String pos_flag) {
        
        ArrayList<Variable> lcVariableList = new ArrayList();
        
        CallableStatement calstatement;
        String strStoreproce
                = "{call app_local_variable.sp_getvariable(?, ?, ?)}";
        ResultSet variableResult;
        String varName, varDesc, varValue, varType, updQry, status;
        try {
//            Connection conn = daoConnect.getConnect();   
            daoConnect = new DaoConnect();
            conn = daoConnect.getConnect();
            calstatement = conn.prepareCall(strStoreproce,
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            calstatement.setString(1, pos_cd.trim());
            calstatement.setString(2, pos_flag.trim());
            calstatement.registerOutParameter(3, oracle.jdbc.OracleTypes.CURSOR);
            calstatement.execute();
            variableResult = (ResultSet) calstatement.getObject(3);
            while (variableResult.next()) {
                varName = variableResult.getString("VARNAME");
                varDesc = variableResult.getString("DESCRIPT");
                varValue = variableResult.getString("VALUE");
                varType = variableResult.getString("VARTYPE");
                updQry = variableResult.getString("UPDQRY");
                status = variableResult.getString("REC_ST");
                lcVariableList.add(new Variable(varName, varDesc, varValue, varType, updQry, status, pos_cd, pos_flag));
            }
            variableResult.close();
            calstatement.close();
            conn.close();
        } catch (SQLException e) {
            System.err.println("Loi --> Khong lay duoc danh muc bien" 
                    + e.getMessage());
        }
        return lcVariableList;
    }

    //--------------------------------------------------------------------------        

    public boolean updateVariable(Variable var,String mkr_id) {
        int updatedRows = 0;
        try {
//            Connection conn = daoConnect.getConnect();
            daoConnect = new DaoConnect();
            conn = daoConnect.getConnect();
            CallableStatement calstatement;
            String strStoreproce
                    = "{call app_local_variable.sp_upvariable(?,?,?,?,?,?,?)}";
            calstatement = conn.prepareCall(strStoreproce,
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            calstatement.setString(1, var.getVarDesc());
            calstatement.setString(2, var.getVarValue());
            calstatement.setString(3, var.getVarType());
            calstatement.setString(4, var.getVarName());
            calstatement.setString(5, var.getPos_cd());
            calstatement.setString(6, var.getPos_flag());
            calstatement.setString(7, mkr_id);
            updatedRows = calstatement.executeUpdate();
            calstatement.close();
            conn.close();
        } catch (SQLException ex) {
            System.err.println("Loi --> Khong cap nhat duoc gia bien"
            + ex.getMessage());
        }
        return updatedRows > 0;
    }
    //--------------------------------------------------------------------------      
    
    public void initTempTable(){        
        try {
//            Connection conn = daoConnect.getConnect();
            daoConnect = new DaoConnect();
            conn = daoConnect.getConnect();
            CallableStatement calstatement;
            String strStoreproce
                    = "{call app_local_variable.sp_inittemp()}";
            calstatement = conn.prepareCall(strStoreproce,
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            
//            calstatement.setString(1, mkr_id);
            calstatement.executeUpdate();
            calstatement.close();
            conn.close();
        } catch (SQLException ex) {
            System.err.println("Loi --> Khong khoi tao duoc bang temp"
            + ex.getMessage());
        }        
    }
    
    //--------------------------------------------------------------------------      
//    public void initTempTable(String mkr_id){        
//        try {
////            Connection conn = daoConnect.getConnect();
//            daoConnect = new DaoConnect();
//            conn = daoConnect.getConnect();
//            CallableStatement calstatement;
//            String strStoreproce
//                    = "{call app_local_variable.sp_inittemp(?)}";
//            calstatement = conn.prepareCall(strStoreproce,
//                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
//            
//            calstatement.setString(1, mkr_id);
//            calstatement.executeUpdate();
//            calstatement.close();
//            conn.close();
//        } catch (SQLException ex) {
//            System.err.println("Loi --> Khong khoi tao duoc bang temp"
//            + ex.getMessage());
//        }        
//    }
    
    //--------------------------------------------------------------------------      
    public String updateMainTable(String pos_cd,String pos_flg,String effect_dt){
        String message = "(*)";
        try {
//            Connection conn = daoConnect.getConnect();
            daoConnect = new DaoConnect();
            conn = daoConnect.getConnect();
            CallableStatement calstatement;
            String strStoreproce
                    = "{call app_local_variable.sp_updateMainTable(?,?,?,?)}";
            calstatement = conn.prepareCall(strStoreproce,
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            calstatement.setString(1, pos_cd);
            calstatement.setString(2, pos_flg);
            calstatement.setString(3, effect_dt);
             calstatement.registerOutParameter(4, oracle.jdbc.OracleTypes.VARCHAR);
            calstatement.execute();
            message = (String)calstatement.getObject(4);
            calstatement.close();
            conn.close();
        } catch (SQLException ex) {
            System.err.println("Loi --> Khong cap nhat duoc vao bang chinh"
            + ex.getMessage());
        }        
        return message;
    }
}
