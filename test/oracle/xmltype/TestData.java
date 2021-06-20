/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oracle.xmltype;

import groovy.sql.Sql;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import vbsp.ims.dao.DaoConnect;

/**
 *
 * @author LION
 */
public class TestData {

    public static void main(String[] args) throws Exception {
        
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy hh:mm:ss");
	String dateInString = "28-Nov-2015 19:18:22";		
		
		Date date = formatter.parse(dateInString);
                
                System.err.println("date="+date.getTime());
                System.err.println(dateInString.substring(6));
        //Connection conn = new DaoConnect().getConnect();
//        PreparedStatement pre=conn.prepareStatement("abc");
       // System.err.println(conn.getMetaData().getDatabaseProductName().toLowerCase());
//        Statement str=null;
//        pre.setObject(1, pre, java.sql.Types.CHAR);
//                
//        System.err.println();
//                System.err.println(java.sql.Types.CHAR);
          
//                java.sql.Type.CHAR
//        
//        DatabaseMetaData metadata = conn.getMetaData();
//        
//        ResultSet reset = metadata.getTypeInfo();
//        
//        ResultSetMetaData rsmd = reset.getMetaData();
//        int numberOfColumns = rsmd.getColumnCount();
//
//        for (int i = 1; i <= numberOfColumns; i++) {
//            // label if defined else name of column
//            String columnLabel = rsmd.getColumnLabel(i);
//
//            // data type in database
//            String columnType = rsmd.getColumnTypeName(i);
//
//            // java equivalent class name
//            String columnClassName = rsmd.getColumnClassName(i);
//
//            System.out.println("Column Name : " + columnLabel);
//            System.out.println("Column Type : " + columnType);
//            System.out.println("Column Class: " + columnClassName);
//
//            System.out.println();
//        } // for count of columns
////        metadata.getCatalogs();
//        
//        if(reset!=null) reset.close();
       // if(conn!=null) conn.close();
    }
}
