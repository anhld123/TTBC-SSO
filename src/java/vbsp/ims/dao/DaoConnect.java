/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.dao;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import vbsp.ims.define.*;
import vbsp.ims.encrypt.*;
import vbsp.ims.log.CoreLogger;

/**
 *
 * @author LION
 */
public class DaoConnect {

    private String strUserName;
    private String strPassWord;
    private String strUrl;
    private String strDriver;
    private String strKey;
    private DES m_clsDes;

    public DaoConnect() {
        this.strKey = DefineFun.getKeyDes("1");
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

    public String getStrUrl() {
        return strUrl;
    }

    public void setStrUrl(String strUrl) {
        this.strUrl = strUrl;
    }

    public String getStrDriver() {
        return strDriver;
    }

    public void setStrDriver(String strDriver) {
        this.strDriver = strDriver;
    }
    public Connection getSMSBankingConnect(){
        
          try {
              return getConnect("jdbc/smsbanking");
        } catch (Exception ex) {
             Logger.getLogger(DaoConnect.class.getName()).log(Level.SEVERE, null, ex);
            CoreLogger.error(DaoConnect.class.getCanonicalName() 
                    + " Cannot connect oracle database  -> " + ex.getMessage());
            return null;
        }
    }
    
    public Connection getConnect(){
        try {
//              return getConnect("jdbc/intellect");
        //return getConnect("10.129.0.56", "imsreport", 1521, "intellect", "intellect");
        //return getConnect("10.63.8.63", "VBSPIMS1", 1521, "intellect", "intellect");
//        return getConnect("10.27.0.56", "imsreport", 1521, "intellect", "intellect");
//        return getConnect("10.18.0.56", "imsreport", 1521, "intellect", "intellect");
  //      return getConnect("10.67.0.56", "imsreport", 1521, "intellect", "intellect");
      //  return getConnect("10.0.19.12", "VBSPBKA", 1521, "intellect", "intellect");
        return getConnect("10.63.48.70", "IMSDEV", 1521, "intellect", "intellect");
  //      return getConnect("10.134.0.56", "imsreport", 1521, "intellect", "intellect");
//        return getConnect("10.63.8.78", "VBSPIMS", 1521, "intellect", "intellect");
        } catch (Exception ex) {
             Logger.getLogger(DaoConnect.class.getName()).log(Level.SEVERE, null, ex);
            CoreLogger.error(DaoConnect.class.getCanonicalName() 
                    + " Cannot connect oracle database  -> " + ex.getMessage());
            return null;
        }
    }
    
    public Connection getConnect1()
    {
    try {
              return getConnect("jdbc/intellect");
        //return getConnect("10.0.19.12", "VBSPBKA", 1521, "intellect", "intellect");
        //return getConnect("10.69.0.56", "imsreport", 1521, "intellect", "intellect");
//        return getConnect("10.63.8.78", "VBSPIMS", 1521, "intellect", "intellect");
        } catch (Exception ex) {
             Logger.getLogger(DaoConnect.class.getName()).log(Level.SEVERE, null, ex);
            CoreLogger.error(DaoConnect.class.getCanonicalName() 
                    + " Cannot connect oracle database  -> " + ex.getMessage());
            return null;
        }
    }
    
    public Connection getConnect(String databaseName) throws NamingException, SQLException  {
        //goi lop ma hoa rsa
        //System.err.println("Bat dau duyet ham connect");
        //m_clsDes = new DES(strKey);
        Connection connect = null;
        InitialContext initialContext = null;
        Context context = null;
        //DataSource ds = null;
        BasicDataSource basicDatasource = null;
        try {
            initialContext = new InitialContext();

            if (initialContext == null) {
                System.err.println("Loi khi khoi tao initialContext");
                CoreLogger.error(DaoConnect.class.getCanonicalName() 
                        + " Loi khi khoi tao initialContext getConnect  -> ");
                throw new NamingException("Not Init initialContext");
                //return connect;
            }
            context = (Context) initialContext.lookup("java:comp/env");
        } catch (NamingException ex) {

            Logger.getLogger(DaoConnect.class.getName()).log(Level.SEVERE, null, ex);
            CoreLogger.error(DaoConnect.class.getCanonicalName() 
                    + " Loi khi khoi tao initialContext getConnect  -> " + ex.getMessage());
            throw ex;
        }
        try {
            basicDatasource = (BasicDataSource) context.lookup(databaseName); //"jdbc/intellect");
            
            if (basicDatasource == null) {
                System.err.println("Loi khi khoi tao basicDatasource");
                CoreLogger.error(DaoConnect.class.getCanonicalName() 
                        + " Loi khi khoi tao basicDatasource getConnect  -> ");
                //return connect;
                throw new NamingException("Not Init basicDatasource");
            }
            try {
//                m_clsDes = new DES(strKey + basicDatasource.getUsername());
//                setStrPassWord(m_clsDes.decrypt(basicDatasource.getPassword()));
                setStrPassWord(basicDatasource.getPassword());
                setStrUserName(basicDatasource.getUsername());
                setStrUrl(basicDatasource.getUrl());
                setStrDriver(basicDatasource.getDriverClassName());
            } catch (Exception e) {
                CoreLogger.error(DaoConnect.class.getCanonicalName() + " getConnect  -> " + e.getMessage());
            }

            try {
                System.err.println("<<< Bat dau ket noi csdl >>> ");

                //connect=basicDatasource.getConnection();
                

                connect = getConnect(strDriver, strUrl, strUserName, strPassWord);
                basicDatasource.close();

                initialContext.close();
                //context.close();
                System.err.println("<<< Da ket noi csdl thanh cong >>> ");
            } catch (SQLException ex) {
                Logger.getLogger(DaoConnect.class.getName()).log(Level.SEVERE, null, ex);
                CoreLogger.error(DaoConnect.class.getCanonicalName() + " getConnect  -> " + ex.getMessage());
                 System.err.println("<<< Lỗi kết nối csdl >>> "+ex.getMessage());
                //return connect;
                throw ex;
            }
        } catch (NamingException ex) {
            Logger.getLogger(DaoConnect.class.getName()).log(Level.SEVERE, null, ex);
            System.err.printf(ex.getMessage());
            CoreLogger.error(DaoConnect.class.getCanonicalName() 
                    + " Loi khi khoi tao getConnect  -> " + ex.getMessage());
            
            System.err.println("<<< Lỗi kết nối csdl >>> "+ex.getMessage());
            throw ex;
        }

        return connect;
    }

    public Connection getConnect(String strDriver, String strUrl, String sUserName, String sPassword) throws SQLException {
        Connection connect = null;
        try {
            Class.forName(strDriver);
        } catch (ClassNotFoundException e) {
            System.err.println("Khong tim thay lop ket noi csdl oracle");
            //e.printStackTrace();
            CoreLogger.error(DaoConnect.class.getCanonicalName() 
                    + " Loi khi khoi tao getConnect  -> " + e.getMessage());
            return connect;
        }
        //jdbc:oracle:thin:@10.0.19.9:1521:vbspiut
        //String sUrl="jdbc:oracle:thin:@"+sServerHost+":"+Integer.toString(nPort)+":"+sSid;
        try {
            connect = DriverManager.getConnection(strUrl, sUserName, sPassword);

        } catch (SQLException e) {
            System.err.println("Khong the ket noi voi csdl ban xem lai thong tin het noi");
            //e.printStackTrace();
            CoreLogger.error(DaoConnect.class.getCanonicalName() 
                    + " Loi khi khoi tao getConnect  -> " + e.getMessage());
            throw e;
        }
        return connect;

    }

    public Connection getConnect(String sServerHost, String sSid, int nPort, 
            String sUserName, String sPassword) {
        Connection connect = null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException e) {
            System.err.println("Khong tim thay lop ket noi csdl oracle");
            e.printStackTrace();;
            return connect;
        }
        //jdbc:oracle:thin:@10.0.19.9:1521:vbspiut
        String sUrl = "jdbc:oracle:thin:@" + sServerHost + ":" + Integer.toString(nPort) + ":" + sSid;
        try {
            connect = DriverManager.getConnection(sUrl, sUserName, sPassword);

        } catch (SQLException e) {
            System.err.println("Khong the ket noi voi csdl ban xem lai thong tin het noi");
            e.printStackTrace();
            return null;
        }
        return connect;

    }

}
