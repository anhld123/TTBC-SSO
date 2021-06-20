/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.dao.implement;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import oracle.jdbc.OracleResultSet;
import oracle.jdbc.OracleTypes;
import vbsp.ims.dao.base.DaoParamsI;
import vbsp.ims.dao.base.DaoBase;

/**
 *
 * @author Le Duc Hung
 */
public class DaoParamsImp extends DaoBase implements DaoParamsI {

    @Override
    public Map<String, String> getBranchInfo(String procedureName) {
        Map<String, String> list = new HashMap<String, String>();
        try{
            openConnection();            
            sql = String.format("{call %s(?)}", (procedureName == null || procedureName.isEmpty()) ? "SP_GET_BRANCH_INFO" : procedureName);            
            pstmt = conn.prepareCall(sql);
            pstmt.registerOutParameter(1, OracleTypes.CURSOR);
            pstmt.executeQuery();
            rs=(OracleResultSet)pstmt.getObject(1);
            while(rs.next()){                                
                list.put(rs.getString(1), rs.getString(2));                
            }            
        }
        catch(Exception ex){
            Logger.getLogger(DaoParamsImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            closeConnection();
        }
        return list;
    }   

    @Override
    public Map<String, String> getPosInfo(String branchCode, String procedureName) {
        Map<String, String> list = new HashMap<String, String>();
        try{
            openConnection();            
            sql = String.format("{call %s(?,?)}", (procedureName == null || procedureName.isEmpty()) ? "SP_GET_POS_INFO" : procedureName);
            pstmt = conn.prepareCall(sql);
            pstmt.registerOutParameter(1, OracleTypes.CURSOR);
            pstmt.setString(2, branchCode);
            pstmt.executeQuery();
            rs = (OracleResultSet) pstmt.getObject(1);
            while (rs.next()) {
                list.put(rs.getString(1), rs.getString(2));
            }
        }
        catch(Exception ex){
            Logger.getLogger(DaoParamsImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            closeConnection();
        }
        return list;
    }    

    @Override
    public Map<String, String> getCommuneInfo(String posCode, String procedureName) {
        Map<String, String> list = new HashMap<String, String>();
        try{
            openConnection();            
            sql = String.format("{call %s(?,?)}", (procedureName == null || procedureName.isEmpty()) ? "SP_GET_COMMUNE_INFO" : procedureName);
            pstmt = conn.prepareCall(sql);
            pstmt.registerOutParameter(1, OracleTypes.CURSOR);
            pstmt.setString(2, posCode);
            pstmt.executeQuery();
            rs = (OracleResultSet) pstmt.getObject(1);
            while (rs.next()) {
                list.put(rs.getString(1), rs.getString(2));
            }
        }
        catch(Exception ex){
            Logger.getLogger(DaoParamsImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            closeConnection();
        }
        return list;
    }
    
    @Override
    public Map<String, String> getGroupInfo(String communeCode, String procedureName) {
        Map<String, String> list = new HashMap<String, String>();
        try{
            openConnection();            
            sql = String.format("{call %s(?,?)}", (procedureName == null || procedureName.isEmpty()) ? "SP_GET_GROUP_INFO" : procedureName);
            pstmt = conn.prepareCall(sql);
            pstmt.registerOutParameter(1, OracleTypes.CURSOR);
            pstmt.setString(2, communeCode);
            pstmt.executeQuery();
            rs = (OracleResultSet) pstmt.getObject(1);
            while (rs.next()) {
                list.put(rs.getString(1), rs.getString(4) + "-" + rs.getString(3) + "-" + rs.getString(2));
            }
        }
        catch(Exception ex){
            Logger.getLogger(DaoParamsImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            closeConnection();
        }
        return list;
    }
    
    @Override
    public Map<String, String> getGroupByPos(String posCode, String procedureName) {
        Map<String, String> list = new HashMap<String, String>();
        try{
            openConnection();            
            sql = String.format("{call %s(?,?)}", (procedureName == null || procedureName.isEmpty()) ? "SP_GET_GROUP_BY_POS" : procedureName);
            pstmt = conn.prepareCall(sql);
            pstmt.registerOutParameter(1, OracleTypes.CURSOR);
            pstmt.setString(2, posCode);
            pstmt.executeQuery();
            rs = (OracleResultSet) pstmt.getObject(1);
            while (rs.next()) {
                list.put(rs.getString(1), rs.getString(4) + "-" + rs.getString(3) + "-" + rs.getString(2));
            }
        }
        catch(Exception ex){
            Logger.getLogger(DaoParamsImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            closeConnection();
        }
        return list;
    }
}
