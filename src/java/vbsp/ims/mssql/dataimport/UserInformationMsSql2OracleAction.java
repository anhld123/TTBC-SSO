/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vbsp.ims.mssql.dataimport;

import com.opensymphony.xwork2.ActionSupport;
import java.util.ArrayList;
import java.util.List;
import vbsp.ims.report.fast.ListValue;

/**
 *
 * @author Trung
 */
public class UserInformationMsSql2OracleAction extends ActionSupport{
    
    private String hostAddress;
    private String databaseName;
    private String tableName;
    private String fromDate;
    private String toDate;
    private String message;
    
    private List<ListValue> serverAddresses = new ArrayList<>();
    private List<ListValue> databaseNames = new ArrayList<>();
    private List<ListValue> tableNames  = new ArrayList<>();
    
    public UserInformationMsSql2OracleAction(){}
    
    public String popupLink(){
        return SUCCESS;
    }

    public String syncData(){
        UserInformationMsSql2Oracle msSql2Oracle 
                = new UserInformationMsSql2Oracle();
        
        System.err.println("Infor: " + tableName + "~" + fromDate + "~" + toDate);
        
        int rowTotal = msSql2Oracle.syncTable(tableName,fromDate,toDate);
        
        message = "<xanh> (*)Thành công [Số dòng: " + rowTotal +"] <xanh>";
        return SUCCESS;
    }
    
    public String generateServerAddresses(){
        serverAddresses.add(
                new ListValue("10.63.16.52", "Máy chủ QTVH (10.63.16.52)")
        );
        databaseNames.add(
                new ListValue("EODManagement", "EODManagement")
        );
        tableNames.add(
                new ListValue("DepartmentPosition", "DepartmentPosition"));
        tableNames.add(
                new ListValue("UserRoleHist", "UserRoleHist"));
        return SUCCESS;
    }
    
    public String getHostAddress() {
        return hostAddress;
    }

    public void setHostAddress(String hostAddress) {
        this.hostAddress = hostAddress;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<ListValue> getServerAddresses() {
        return serverAddresses;
    }

    public void setServerAddresses(List<ListValue> serverAddresses) {
        this.serverAddresses = serverAddresses;
    }

    public List<ListValue> getDatabaseNames() {
        return databaseNames;
    }

    public void setDatabaseNames(List<ListValue> databaseNames) {
        this.databaseNames = databaseNames;
    }

    public List<ListValue> getTableNames() {
        return tableNames;
    }

    public void setTableNames(List<ListValue> tableNames) {
        this.tableNames = tableNames;
    }
    
    
}
