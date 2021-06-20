/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.dao;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Trung
 */
public class DaoConnectMSSQL {

    private String strUserName;
    private String strPassWord;
    private String strHost;
    private String strServerName;
    private String strDatabase;

    public DaoConnectMSSQL(){}
    
    public DaoConnectMSSQL(String strHost,
            String strServerName,
            String strDatabase,
            String strUserName,
            String strPassWord){
        this.strUserName = strUserName;
        this.strPassWord = strPassWord;
        this.strHost = strHost;
        this.strDatabase = strDatabase;
        this.strServerName = strServerName;
    }
    
    public Connection getConnect() {
        Connection connect = null;
        String db_connect_string = "jdbc:sqlserver://" 
                + strHost + "\\"
                + strServerName +";"
                + "databaseName="
                + strDatabase;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connect = DriverManager.getConnection(db_connect_string,
                    strUserName, strPassWord);            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connect;
    }
    
    public Connection getDefaultConnect(){
        this.strHost = "10.63.16.52";
        this.strServerName = "SQL2008R2";
        this.strDatabase = "EODManagement";
        this.strUserName = "readonly";
        this.strPassWord= "readonly321#";
        return getConnect();
    }

    public String getStrUserName() {
        return strUserName;
    }

    public void setStrUserName(String strUserName) {
        this.strUserName = strUserName;
    }

    public String getStrPassWord() {
        return strPassWord;
    }

    public void setStrPassWord(String strPassWord) {
        this.strPassWord = strPassWord;
    }

    public String getStrHost() {
        return strHost;
    }

    public void setStrHost(String strHost) {
        this.strHost = strHost;
    }

    public String getStrDatabase() {
        return strDatabase;
    }

    public void setStrDatabase(String strDatabase) {
        this.strDatabase = strDatabase;
    }          
}
