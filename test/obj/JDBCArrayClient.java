/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package obj;

import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import vbsp.ims.dao.DaoConnect;

public class JDBCArrayClient {
  public static void passArray() {
    try {
      Class.forName("oracle.jdbc.OracleDriver");
      // Need to modify conn string, username and password as per your oracle installation.
      Connection con = new DaoConnect().getConnect();
//              DriverManager.getConnection(
//          "jdbc:oracle:thin:@<hostname>:<port>:<sid>", "username", "password");
      Statement stmt = con.createStatement();
      ResultSet rs = stmt
          .executeQuery("SELECT emp_id, emp_skills from empnested");
      while (rs.next()) {
        Array e = rs.getArray("emp_skills");
        String[] skills = (String[]) e.getArray();
        System.out.println("Skills");
        for (int i = 0; i < skills.length; i++) {
          System.out.println(skills[i]);
        }
      }
    } catch (Exception e) {
      System.out.println(e);
    }
  }
            public static void main(String args[]) {
                        passArray();
            }
}
