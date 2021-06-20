/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.prt;

import java.sql.*;
import java.io.*;
import java.util.Date;
import oracle.sql.*;

public class PersonObj implements SQLData {

    String sql_type = "person_t";
    public String name;
    public int age;
    public AddressObj addrObj;
// Constructors

    public PersonObj() {
    }

    public PersonObj(String sql_type, String name, int age, AddressObj addrObj) {
        this.sql_type = sql_type;
        this.name = name;
        this.age = age;
        this.addrObj = addrObj;
    }
// Methods implementing the SQLData interface

    public String getSQLTypeName() throws SQLException {
        return sql_type;
    }

    public void readSQL(SQLInput stream, String typeName)
            throws SQLException {
        sql_type = typeName;
        SQLInput istream = (SQLInput) stream;
        name = istream.readString();
        age = istream.readInt();
        addrObj = (AddressObj) istream.readObject();
    }

    public void writeSQL(SQLOutput stream)
            throws SQLException {
        SQLOutput ostream = (SQLOutput) stream;
        ostream.writeString(name);
        ostream.writeInt(age);
        ostream.writeObject(addrObj);
    }
}
