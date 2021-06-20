/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package obj;

import java.math.BigDecimal;
import java.sql.*;
import java.util.Dictionary;
import oracle.jdbc.OracleResultSet;
import oracle.jdbc.OracleTypes;
import oracle.jdbc.driver.*;
import oracle.sql.*;
import vbsp.ims.dao.DaoConnect;


public class SQLDataExample
{

  public static void main(String args []) throws Exception
  {

    // Connect
    DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver ());
    OracleConnection conn = (OracleConnection) new DaoConnect().getConnect();

    Dictionary map = (Dictionary)conn.getTypeMap();
    map.put("EMPLOYEE", Class.forName("EmployeeObj"));

    // Create a Statement
    Statement stmt = conn.createStatement ();
    try 
    {
      stmt.execute ("drop table EMPLOYEE_TABLE");
      stmt.execute ("drop type EMPLOYEE");
    }
    catch (SQLException e) 
    {      
      // An error is raised if the table/type does not exist. Just ignore it.
    }

    // Create and populate tables
    stmt.execute ("CREATE TYPE EMPLOYEE AS OBJECT (EmpName VARCHAR2(50),EmpNo INTEGER)"); 
    stmt.execute ("CREATE TABLE EMPLOYEE_TABLE (ATTR1 EMPLOYEE)");
    stmt.execute ("INSERT INTO EMPLOYEE_TABLE VALUES (EMPLOYEE('Susan Smith', 123))");
    stmt.close();

    // Create a SQLData object
    EmployeeObj e = new EmployeeObj("SCOTT.EMPLOYEE", "George Jones", 456);

    // Insert the SQLData object
    PreparedStatement pstmt
      = conn.prepareStatement ("insert into employee_table values (?)");

    pstmt.setObject(1, e, OracleTypes.STRUCT);
    pstmt.executeQuery();
    System.out.println("insert done");
    pstmt.close();

    // Select now
    Statement s = conn.createStatement();
    OracleResultSet rs = (OracleResultSet) 
      s.executeQuery("select * from employee_table");

    while(rs.next())
    {
       EmployeeObj ee = (EmployeeObj) rs.getObject(1);
       System.out.println("EmpName: " + ee.empName + " EmpNo: " + ee.empNo);
    }
    rs.close();
    s.close();

    if (conn != null)
    {
      conn.close();
    }
  }
}