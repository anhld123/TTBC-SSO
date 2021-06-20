/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * CREATE TYPE array_table AS TABLE OF VARCHAR2 (50); CREATE TYPE array_table AS
 * VARRAY(5) OF VARCHAR2(50);
 *
 * CREATE OR REPLACE PROCEDURE procarrayinout (p_array IN array_table, len OUT
 * NUMBER, p_arr_out OUT array_table) AS BEGIN len := p_array.COUNT; p_arr_out
 * := NEW array_table (); p_arr_out.EXTEND (len); FOR i IN 1 .. p_array.COUNT
 * LOOP DBMS_OUTPUT.put_line (p_array (i)); p_arr_out (i) := p_array (i); END
 * LOOP; END;
 *
 */
package obj;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Types;
import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.internal.OracleTypes;
import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;

public class JDBCArraySP {

    public static void passAndGetArray() {
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            Connection con = DriverManager.getConnection(
                    "jdbc:oracle:thin:@10.60.28.83:1521:ORA11", "hej01", "hej01");
            String array[] = {"one", "two", "three", "four"};
            ArrayDescriptor des = ArrayDescriptor
                    .createDescriptor("ARRAY_TABLE", con);
            ARRAY array_to_pass = new ARRAY(des, con, array);
            CallableStatement st = con.prepareCall("call procarrayinout(?,?,?)");

            // Passing an array to the stored procedure
            st.setArray(1, array_to_pass);
            st.registerOutParameter(2, Types.INTEGER);
            st.registerOutParameter(3, OracleTypes.ARRAY, "ARRAY_TABLE");
            st.execute();
            System.out.println("Output array size : " + st.getInt(2));
            if (st.getInt(2) > 0) {
                System.out.println("Array elements:");
            }
      // Retrieving array from the resultset after executing the stored
            // procedure
            ARRAY arr = ((OracleCallableStatement) st).getARRAY(3);
            String[] recievedArray = (String[]) arr.getArray();
            for (int i = 0; i < recievedArray.length; i++) {
                System.out.println(recievedArray[i]);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void main(String args[]) {

        passAndGetArray();

    }

}
