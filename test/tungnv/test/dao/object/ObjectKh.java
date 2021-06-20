/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tungnv.test.dao.object;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLData;
import java.sql.SQLException;
import java.sql.SQLInput;
import java.sql.SQLOutput;
import java.sql.Statement;
import java.text.NumberFormat;
import java.util.Map;

/**
 *
 * @author LION
 */
public class ObjectKh implements SQLData {

    public static final String ORACLE_OBJECT_NAME = "KH_TYPE";
    String sMakh;
    String sTenkh;
    String sNgaysinh;
    String sSocmt;
    String sDiachi;
    String sMadp;
    
    public String getSQLTypeName() throws SQLException {
        return ORACLE_OBJECT_NAME;
    }
    
    public void readSQL(SQLInput stream, String typeName) throws SQLException {
        setsMakh(stream.readString());
        setsTenkh(stream.readString());
        setsNgaysinh(stream.readString());
        setsSocmt(stream.readString());
        setsDiachi(stream.readString());
        setsMadp(stream.readString());
    }
    
    public void writeSQL(SQLOutput stream) throws SQLException {
     
        stream.writeString(getsMakh());
        stream.writeString(getsTenkh());
        stream.writeString(getsNgaysinh());
        stream.writeString(getsSocmt());
        stream.writeString(getsDiachi());
        stream.writeString(getsMadp());
    }

    public String getsMakh() {
        return sMakh;
    }
    
    public void setsMakh(String sMakh) {
        this.sMakh = sMakh;
    }
    
    public String getsTenkh() {
        return sTenkh;
    }
    
    public void setsTenkh(String sTenkh) {
        this.sTenkh = sTenkh;
    }
    
    public String getsNgaysinh() {
        return sNgaysinh;
    }
    
    public void setsNgaysinh(String sNgaysinh) {
        this.sNgaysinh = sNgaysinh;
    }
    
    public String getsSocmt() {
        return sSocmt;
    }
    
    public void setsSocmt(String sSocmt) {
        this.sSocmt = sSocmt;
    }
    
    public String getsDiachi() {
        return sDiachi;
    }
    
    public void setsDiachi(String sDiachi) {
        this.sDiachi = sDiachi;
    }
    
    public String getsMadp() {
        return sMadp;
    }
    
    public void setsMadp(String sMadp) {
        this.sMadp = sMadp;
    }
    
}
