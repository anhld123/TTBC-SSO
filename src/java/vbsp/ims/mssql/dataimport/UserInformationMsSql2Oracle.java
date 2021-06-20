/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vbsp.ims.mssql.dataimport;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import vbsp.ims.dao.DaoConnect;
import vbsp.ims.dao.DaoConnectMSSQL;

/**
 *
 * @author Trung
 */
public class UserInformationMsSql2Oracle {
    
    public UserInformationMsSql2Oracle(){}
    
    public int syncTable(String tableName,String pv_from_dt,String pv_to_dt){
        int rowCnt = 0;
        switch(tableName){
            case "DepartmentPosition" :
                rowCnt = dumpDepartmentPositionTable();
                break;
            case "UserRoleHist":
                rowCnt = dumpUserRoleHistTable(pv_from_dt,pv_to_dt);
                break;
        }
        return rowCnt;
    }
    
    
    public int dumpDepartmentPositionTable(){
        List<DepartmentPosition> data = getDepartmentPositionData();
        int rowInsertedCount = setDepartmentPositionData(data); 
        System.err.println(rowInsertedCount);
        return rowInsertedCount;
    }
    
    public int dumpUserRoleHistTable(String pv_from_dt,String pv_to_dt){
        List<UserRole> data = getUserRoleHistData(pv_from_dt, pv_to_dt);
        int rowInsertedCount = setUserRoleHistData(data,pv_from_dt, pv_to_dt); 
        //System.err.println(rowInsertedCount);
        return rowInsertedCount;
    }
    
    private List<UserRole> getUserRoleHistData(String pv_from_dt,String pv_to_dt){
        ArrayList<UserRole> data = new ArrayList<>();
        try {            
            DaoConnectMSSQL daoConnectMSSQL 
                    = new DaoConnectMSSQL();
            Connection msConnection = 
                    daoConnectMSSQL.getDefaultConnect();            
            String queryString = 
                            "SELECT  [TransType]\n" +
                            "      ,[PosCode]\n" +
                            "      ,[UserName]\n" +
                            "      ,[FullName]\n" +
                            "      ,[IdNo]\n" +
                            "      ,[IdIssueDate]\n" +
                            "      ,[IdIssuePlace]\n" +
                            "      ,[Department]\n" +
                            "      ,[Position]\n" +
                            "      ,CONVERT(varchar,CreateTime,103) AS CreateTime\n" +
                            "      ,[CreateBy]\n" +
                            "      ,CONVERT(varchar,UpdateTime,103) AS UpdateTime \n" +
                            "      ,[UpdateBy]\n" +
                            "      ,[NewDepartment]\n" +
                            "      ,[NewPosition]\n" +
                            "      ,[PosNew]      \n" +
                            "      ,[ATM]\n" +
                            "      ,[ATMUser]\n" +
                            "      ,[ATMFullName]\n" +
                            "      ,[FromDate]\n" +
                            "      ,[ToDate]      \n" +
                            "      ,[Note]\n" +
                            "  FROM [EODManagement].[dbo].[UserRole] \n" +
                            "WHERE [Status] = '2' AND CONVERT(date,CreateTime,103) >= CONVERT(DATE,?,103)\n" +
                            "AND CONVERT(date,CreateTime,103) <= CONVERT(DATE,?,103)\n" +
                            "UNION ALL\n" +
                            "SELECT  [TransType]\n" +
                            "      ,[PosCode]\n" +
                            "      ,[UserName]\n" +
                            "      ,[FullName]\n" +
                            "      ,[IdNo]\n" +
                            "      ,[IdIssueDate]\n" +
                            "      ,[IdIssuePlace]\n" +
                            "      ,[Department]\n" +
                            "      ,[Position]\n" +
                            "      ,CONVERT(varchar,CreateTime,103) AS CreateTime\n" +
                            "      ,[CreateBy]\n" +
                            "      ,CONVERT(varchar,UpdateTime,103) AS UpdateTime \n" +
                            "      ,[UpdateBy]\n" +
                            "      ,[NewDepartment]\n" +
                            "      ,[NewPosition]\n" +
                            "      ,[PosNew]      \n" +
                            "      ,[ATM]\n" +
                            "      ,[ATMUser]\n" +
                            "      ,[ATMFullName]\n" +
                            "      ,[FromDate]\n" +
                            "      ,[ToDate]      \n" +
                            "      ,[Note]\n" +
                            "  FROM [EODManagement].[dbo].[UserRoleHist] \n" +
                            "WHERE [Status] = '2' AND CONVERT(date,CreateTime,103) >= CONVERT(DATE,?,103)\n" +
                            "AND CONVERT(date,CreateTime,103) <= CONVERT(DATE,?,103)";
            
            PreparedStatement preparedStament = msConnection.prepareStatement(queryString);
            
            preparedStament.setString(1, pv_from_dt);
            preparedStament.setString(2, pv_to_dt);
            preparedStament.setString(3, pv_from_dt);
            preparedStament.setString(4, pv_to_dt);
            
            ResultSet rs = preparedStament.executeQuery();
            
            while (rs.next()) {    
                //System.out.println(rs.getString(3));                
                UserRole userRole = new UserRole();
                
                userRole.setTransType(rs.getString(1));                
                userRole.setPosCode(rs.getString(2));
                userRole.setUserName(rs.getString(3));
                userRole.setFullName(rs.getString(4));
                userRole.setIdNo(rs.getString(5));
                userRole.setIdIssueDate(rs.getString(6));
                userRole.setIdIssuePlace(rs.getString(7));
                userRole.setDepartment(rs.getString(8));
                userRole.setPosition(rs.getString(9));
                userRole.setCreateTime(rs.getString(10));
                userRole.setCreateBy(rs.getString(11));
                userRole.setUpdateTime(rs.getString(12));
                userRole.setUpdateBy(rs.getString(13));
                userRole.setNewDepartment(rs.getString(14));
                userRole.setNewPosition(rs.getString(15));
                userRole.setPosNew(rs.getString(16));
                userRole.setATM(rs.getString(17));
                userRole.setATMUser(rs.getString(18));
                userRole.setATMFullName(rs.getString(19));
                userRole.setFromDate(rs.getString(20));
                userRole.setToDate(rs.getString(21));
                userRole.setNote(rs.getString(22));
                
                data.add(userRole);
            }            
        } catch (SQLException ex) {
            Logger.getLogger(UserInformationMsSql2Oracle.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;
    }
    
    
    private int setUserRoleHistData(List<UserRole> data,String pv_from_dt,String pv_to_dt){
        int rowCount = 0;
        try {
            DaoConnect daoConnect = new DaoConnect();
            try (Connection oracleConnection = daoConnect.getConnect()) {
                
                // Xoá dữ liệu
                String deleteQuery = "delete from QTVH_UserRoleHist "
                        + "where CreateTime >= to_date(?,'dd/mm/yyyy') "
                        + "and CreateTime <= to_date(?,'dd/mm/yyyy')";
                oracleConnection.setAutoCommit(true);
                PreparedStatement preparedStament
                        = oracleConnection.prepareStatement(deleteQuery);
                preparedStament.setString(1, pv_from_dt);
                preparedStament.setString(2, pv_to_dt);
                preparedStament.executeUpdate();
                
                //System.err.println("Hoan thanh xoa bang ");
                
                // insert dữ liệu
                oracleConnection.setAutoCommit(false);
                String insertQuery = 
                                "insert into QTVH_UserRoleHist("+
                                "       TransType ,\n" +
                                "	PosCode ,\n" +
                                "	UserName ,\n" +
                                "	FullName ,\n" +
                                "	IdNo ,\n" +
                                "	IdIssueDate ,\n" +
                                "	IdIssuePlace ,\n" +
                                "	Department ,\n" +
                                "	Position ,\n" +
                                "	CreateTime ,\n" +
                                "	CreateBy ,\n" +
                                "	UpdateTime ,\n" +
                                "	UpdateBy ,\n" +
                                "	NewDepartment ,\n" +
                                "	NewPosition ,\n" +
                                "	PosNew       ,\n" +
                                "	ATM ,\n" +
                                "	ATMUser ,\n" +
                                "	ATMFullName ,\n" +
                                "	FromDate ,\n" +
                                "	ToDate       ,\n" +
                                "	Note\n" +
                                ")\n" +
                                "values (\n" +
                                "	?,\n" +
                                "	?,\n" +
                                "	?,\n" +
                                "	?,\n" +
                                "	?,\n" +
                                "	?,\n" +
                                "	?,\n" +
                                "	?,\n" +
                                "	?,\n" +
                                "	to_date(?,'dd/mm/yyyy'),\n" +
                                "	?,\n" +
                                "	to_date(?,'dd/mm/yyyy'),\n" +
                                "	?,\n" +
                                "	?,\n" +
                                "	?,\n" +
                                "	?,\n" +
                                "	?,\n" +
                                "	?,\n" +
                                "	?,\n" +
                                "	?,\n" +
                                "	?,\n" +
                                "	?\n" +
                                ")";
                preparedStament
                        = oracleConnection.prepareStatement(insertQuery,
                                ResultSet.TYPE_SCROLL_SENSITIVE,
                                ResultSet.CONCUR_READ_ONLY);
                for (UserRole userRole : data){                   
//                    System.err.println(
//                    nvl(userRole.getPosCode (),"X")+
//                    nvl(userRole.getUserName (),"X")+
//                    nvl(userRole.getFullName (),"X")+
//                    nvl(userRole.getIdNo (),"X")+
//                    nvl(userRole.getIdIssueDate (),"X")+
//                    nvl(userRole.getIdIssuePlace (),"X")+
//                    nvl(userRole.getDepartment (),"X")+
//                    nvl(userRole.getPosition (),"X")+
//                    nvl(userRole.getCreateTime (),"X")+
//                    nvl(userRole.getCreateBy (),"X")+
//                    nvl(userRole.getUpdateTime (),"X")+
//                    nvl(userRole.getUpdateBy (),"X")+
//                    nvl(userRole.getNewDepartment (),"X")+
//                    nvl(userRole.getNewPosition (),"X")+
//                    nvl(userRole.getPosNew       (),"X")+
//                    nvl(userRole.getATM (),"X")+
//                    nvl(userRole.getATMUser (),"X")+
//                    nvl(userRole.getATMFullName (),"X")+
//                    nvl(userRole.getFromDate (),"X")+
//                    nvl(userRole.getToDate       (),"X")+
//                    nvl(userRole.getNote(),"X"));
                    preparedStament.setString(1, nvl(userRole.getTransType(),""));
                    preparedStament.setString(2,nvl(userRole.getPosCode(),""));
                    preparedStament.setString(3,nvl(userRole.getUserName(),""));
                    preparedStament.setString(4,nvl(userRole.getFullName(),""));
                    preparedStament.setString(5,nvl(userRole.getIdNo(),""));
                    preparedStament.setString(6,nvl(userRole.getIdIssueDate(),""));
                    preparedStament.setString(7,nvl(userRole.getIdIssuePlace(),""));
                    preparedStament.setString(8,nvl(userRole.getDepartment(),""));
                    preparedStament.setString(9,nvl(userRole.getPosition(),""));
                    preparedStament.setString(10,nvl(userRole.getCreateTime(),"''"));
                    preparedStament.setString(11,nvl(userRole.getCreateBy(),""));
                    preparedStament.setString(12,nvl(userRole.getUpdateTime(),""));
                    preparedStament.setString(13,nvl(userRole.getUpdateBy(),""));
                    preparedStament.setString(14,nvl(userRole.getNewDepartment(),""));
                    preparedStament.setString(15,nvl(userRole.getNewPosition(),""));
                    preparedStament.setString(16,nvl(userRole.getPosNew(),""));
                    preparedStament.setString(17,nvl(userRole.getATM(),""));
                    preparedStament.setString(18,nvl(userRole.getATMUser(),""));
                    preparedStament.setString(19,nvl(userRole.getATMFullName(),""));
                    preparedStament.setString(20,nvl(userRole.getFromDate(),""));
                    preparedStament.setString(21,nvl(userRole.getToDate(),""));
                    preparedStament.setString(22,nvl(userRole.getNote(),""));                  
                    preparedStament.addBatch();
                    rowCount++;
                }
                
                preparedStament.executeBatch();
                oracleConnection.commit();
                
                return rowCount;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserInformationMsSql2Oracle.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
        
    }
    
    private String nvl(String str,String alterStr){
        if (str == null)
            return alterStr;
        else
            return str;
    }
    
    private List<DepartmentPosition> getDepartmentPositionData(){
        ArrayList<DepartmentPosition> data = new ArrayList<>();
        try {            
            DaoConnectMSSQL daoConnectMSSQL = new DaoConnectMSSQL();
            Connection msConnection = daoConnectMSSQL.getDefaultConnect();
            Statement statement = msConnection.createStatement();
            String queryString = "SELECT [PositionCode]\n" +
                                "      ,[PositionName]\n" +
                                "      ,[DepartmentCode]\n" +
                                "      ,[DepartmentName]\n" +
                                "      ,[RoleCode]\n" +
                                "      ,[CashFlag]\n" +
                                "      ,[ATMFlag]\n" +
                                "  FROM [dbo].[DepartmentPosition];";
            ResultSet rs = statement.executeQuery(queryString);
            while (rs.next()) {    
                //System.out.println(rs.getString(3));
                data.add(new DepartmentPosition(
                        rs.getString(1), 
                        rs.getString(2), 
                        rs.getString(3), 
                        rs.getString(4), 
                        rs.getString(5), 
                        rs.getString(6), 
                        rs.getString(7)));
            }            
        } catch (SQLException ex) {
            Logger.getLogger(UserInformationMsSql2Oracle.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;
    }
    
    private int setDepartmentPositionData(List<DepartmentPosition> data){
        int rowCount = 0;
        try {
            DaoConnect daoConnect = new DaoConnect();
            try (Connection oracleConnection = daoConnect.getConnect()) {
                
                // Xoá dữ liệu
                String deleteQuery = "delete from QTVH_DepartmentPosition";
                oracleConnection.setAutoCommit(true);
                PreparedStatement preparedStament
                        = oracleConnection.prepareStatement(deleteQuery);
                preparedStament.executeUpdate();
                
                // insert dữ liệu
                oracleConnection.setAutoCommit(false);
                String insertQuery = "insert into QTVH_DepartmentPosition "+
                        "      (PositionCode\n" +
                        "      ,PositionName\n" +
                        "      ,DepartmentCode\n" +
                        "      ,DepartmentName\n" +
                        "      ,RoleCode\n" +
                        "      ,CashFlag\n" +
                        "      ,ATMFlag)\n" +
                        "	  values (?,?,?,?,?,?,?)";
                preparedStament
                        = oracleConnection.prepareStatement(insertQuery,
                                ResultSet.TYPE_SCROLL_SENSITIVE,
                                ResultSet.CONCUR_READ_ONLY);
                for (DepartmentPosition dp : data){
                    preparedStament.setString(1, dp.getPositionCode());
                    preparedStament.setString(2, dp.getPositionName());
                    preparedStament.setString(3, dp.getDepartmentCode());
                    preparedStament.setString(4, dp.getDepartmentName());
                    preparedStament.setString(5, dp.getRoleCode());
                    preparedStament.setString(6, dp.getCashFlag());
                    preparedStament.setString(7, dp.getAtmFlag());
                    preparedStament.addBatch();
                    rowCount++;
                }
                
                preparedStament.executeBatch();
                oracleConnection.commit();
                
                return rowCount;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserInformationMsSql2Oracle.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
        
    }
    
    public static void main(String[] args) {
        try {
            UserInformationMsSql2Oracle usrInforMssql2Oracle = new UserInformationMsSql2Oracle();
            usrInforMssql2Oracle.dumpDepartmentPositionTable();
        } catch (Exception ex) {
            Logger.getLogger(DaoConnectMSSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
