/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tungnv.test.dao.object;

import java.sql.*;
import java.io.*;
import java.util.Date;
import oracle.sql.*;

/**
 *
 * @author LION
 */
public class KhachhangObj implements SQLData  {
    public static final String ORACLE_OBJECT_NAME = "KH_TYPE";
//    String sql_type = "kh_type";
    public String kh_makh;
    public String kh_tenkh;
    public Timestamp kh_ngaysinh;
    public String kh_cmt;
    public String kh_diachi;
    public String kh_madp;
    
    public KhachhangObj()
    {
        
    }
    public KhachhangObj(String sql_type, String KH_MAKH,String kh_tenkh,Timestamp kh_ngaysinh,String kh_cmt,
            String kh_diachi,String kh_madp)
    {
//        this.sql_type=sql_type;
        this.kh_makh=KH_MAKH;
        this.kh_tenkh=kh_tenkh;
        this.kh_ngaysinh=kh_ngaysinh;
        this.kh_cmt=kh_cmt;
        this.kh_diachi=kh_diachi;
        this.kh_madp=kh_madp;
    }
    public String getSQLTypeName() throws SQLException {
        return ORACLE_OBJECT_NAME;
    }
    public void readSQL(SQLInput stream, String typeName)
            throws SQLException {
//        sql_type=typeName;
        SQLInput istream = (SQLInput) stream;
        kh_makh = istream.readString();
        kh_tenkh = istream.readString();
        kh_ngaysinh=istream.readTimestamp();
        kh_cmt = istream.readString();
        kh_diachi = istream.readString();
        kh_madp = istream.readString();
    }

    public void writeSQL(SQLOutput stream)
            throws SQLException {
        SQLOutput ostream = (SQLOutput) stream;
        ostream.writeString(kh_makh);
        ostream.writeString(kh_tenkh);
        ostream.writeTimestamp(kh_ngaysinh);
        ostream.writeString(kh_cmt);
        ostream.writeString(kh_diachi);
        ostream.writeString(kh_madp);
    }
}
