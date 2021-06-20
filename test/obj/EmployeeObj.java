/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obj;

import java.sql.*;
import oracle.jdbc.*;

public class EmployeeObj implements SQLData {

    private String sql_type;

    public String empName;
    public int empNo;

    public EmployeeObj() {
    }

    public EmployeeObj(String sql_type, String empName, int empNo) {
        this.sql_type = sql_type;
        this.empName = empName;
        this.empNo = empNo;
    }

  ////// implements SQLData //////
    public String getSQLTypeName() throws SQLException {
        return sql_type;
    }

    public void readSQL(SQLInput stream, String typeName)
            throws SQLException {
        sql_type = typeName;

        empName = stream.readString();
        empNo = stream.readInt();
    }

    public void writeSQL(SQLOutput stream)
            throws SQLException {
        stream.writeString(empName);
        stream.writeInt(empNo);
    }
}
