/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package obj;

import java.math.BigDecimal;
import java.sql.*;                                           // line 1
import oracle.jdbc.OracleResultSet;
import oracle.jdbc.OracleTypes;
import oracle.jdbc.driver.*;
import oracle.sql.*;
import vbsp.ims.dao.DaoConnect;


public class CustomDatumExample
{
  public static void main(String args []) throws Exception
  {

    // Connect
//    DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver ());
      DaoConnect dao = new DaoConnect();
    OracleConnection conn = (OracleConnection)dao.getConnect();
//      DriverManager.getConnection("jdbc:oracle:oci8:@", "scott", "tiger");

    // Create a Statement                                   // line 18
    Statement stmt = conn.createStatement ();
    try 
    {
      stmt.execute ("drop table EMPLOYEE_TABLE");
      stmt.execute ("drop type EMPLOYEE");
    }
    catch (SQLException e) 
    {      
      // An error is raised if the table/type does not exist. Just ignore it.
    }                                                       // line 28

    // Create and populate tables                           // line 30
    stmt.execute ("CREATE TYPE EMPLOYEE AS " +
          " OBJECT(EmpName VARCHAR2(50),EmpNo INTEGER)"); 
    stmt.execute ("CREATE TABLE EMPLOYEE_TABLE (ATTR1 EMPLOYEE)");
    stmt.execute ("INSERT INTO EMPLOYEE_TABLE " +
          " VALUES (EMPLOYEE('Susan Smith', 123))");          // line 35

    // Create a CustomDatum object                          // line 37
    Employee e = new Employee("George Jones", new BigDecimal("456"));

    // Insert the CustomDatum object                        // line 40
    PreparedStatement pstmt
      = conn.prepareStatement ("INSERT INTO employee_table VALUES (?)");
    
    pstmt.setObject(1, e, OracleTypes.STRUCT);
    pstmt.executeQuery();
    System.out.println("insert done");
    pstmt.close();                                          // line 47
                     
    // Select now                                           // line 49
    Statement s = conn.createStatement();
    OracleResultSet rs = (OracleResultSet)s.executeQuery("SELECT * FROM employee_table");

    while(rs.next())                                        // line 54
    {
       Employee ee = (Employee) rs.getCustomDatum(1, Employee.getFactory());
       System.out.println("EmpName: " + ee.empName + " EmpNo: " + ee.empNo);
    }                                                       // line 58
    rs.close();
    s.close();

    if (conn != null)
    {
      conn.close();
    }
  }
}