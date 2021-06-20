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

public class AddressObj implements SQLData {

    String sql_type = "address_t";
    public String street1;
    public String street2;
    public String city;
    public int zip;
// Constructors

    public AddressObj() {
    }

    public AddressObj(String sql_type, String street1,
            String street2, String city, int zip) {
        this.sql_type = sql_type;
        this.street1 = street1;
        this.street2 = street2;
        this.city = city;
        this.zip = zip;
    }
// Methods implementing the SQLData interface

    public String getSQLTypeName() throws SQLException {
        return sql_type;
    }
    
    public void readSQL(SQLInput stream, String typeName)
            throws SQLException {
        sql_type = typeName;
        SQLInput istream = (SQLInput) stream;
        street1 = istream.readString();
        street2 = istream.readString();
        city = istream.readString();
        zip = istream.readInt();
    }

    public void writeSQL(SQLOutput stream)
            throws SQLException {
        SQLOutput ostream = (SQLOutput) stream;
        ostream.writeString(street1);
        ostream.writeString(street2);
        ostream.writeString(city);
        ostream.writeInt(zip);
    }
}
