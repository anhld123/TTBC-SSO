/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.model;

import java.math.BigDecimal;
import java.sql.SQLData;
import java.sql.SQLException;
import java.sql.SQLInput;
import java.sql.SQLOutput;

/**
 *
 * @author chudv
 */
public class PLNO_DULIEU implements SQLData {

    public static final String ORACLE_OBJECT_TYPE = "PLNO_TYPE";
    public static final String ORACLE_TABLE_TYPE = "PLNO_TAB";

    public String sSoku;
    public BigDecimal bC_Kntn_Sodu;
    public BigDecimal bK_Kntn_Sodu;
    public String sK_Ma_Ngnhan;
    public String sK_Ma_NgnhanC2;
    public String sK_Ngnhan_Kh;
    public String sQuanhe_Kh;
    public String sTrangthai;
    public BigDecimal bNogoc_Clech;
    public BigDecimal bNolai_Clech;
    public String sNgnhan_Clech;

    public static PLNO_DULIEU newInstance() {
        return new PLNO_DULIEU();
    }

    public String getSQLTypeName() throws SQLException {
        return ORACLE_OBJECT_TYPE;
    }

    public String getsSoku() {
        return sSoku;
    }

    public void setsSoku(String sSoku) {
        this.sSoku = sSoku;
    }

    public BigDecimal getbC_Kntn_Sodu() {
        return bC_Kntn_Sodu;
    }

    public void setbC_Kntn_Sodu(BigDecimal bC_Kntn_Sodu) {
        this.bC_Kntn_Sodu = bC_Kntn_Sodu;
    }

    public BigDecimal getbK_Kntn_Sodu() {
        return bK_Kntn_Sodu;
    }

    public String getsK_Ma_Ngnhan() {
        return sK_Ma_Ngnhan;
    }

    public void setsK_Ma_Ngnhan(String sK_Ma_Ngnhan) {
        this.sK_Ma_Ngnhan = sK_Ma_Ngnhan;
    }

    public void setbK_Kntn_Sodu(BigDecimal bK_Kntn_Sodu) {
        this.bK_Kntn_Sodu = bK_Kntn_Sodu;
    }

    public String getsK_Ma_NgnhanC2() {
        return sK_Ma_NgnhanC2;
    }

    public void setsK_Ma_NgnhanC2(String sK_Ma_NgnhanC2) {
        this.sK_Ma_NgnhanC2 = sK_Ma_NgnhanC2;
    }
    
    

    public String getsK_Ngnhan_Kh() {
        return sK_Ngnhan_Kh;
    }

    public void setsK_Ngnhan_Kh(String sK_Ngnhan_Kh) {
        this.sK_Ngnhan_Kh = sK_Ngnhan_Kh;
    }

    public String getsQuanhe_Kh() {
        return sQuanhe_Kh;
    }

    public void setsQuanhe_Kh(String sQuanhe_Kh) {
        this.sQuanhe_Kh = sQuanhe_Kh;
    }

    public String getsTrangthai() {
        return sTrangthai;
    }

    public void setsTrangthai(String sTrangthai) {
        this.sTrangthai = sTrangthai;
    }

    public BigDecimal getbNogoc_Clech() {
        return bNogoc_Clech;
    }

    public void setbNogoc_Clech(BigDecimal bNogoc_Clech) {
        this.bNogoc_Clech = bNogoc_Clech;
    }

    public BigDecimal getbNolai_Clech() {
        return bNolai_Clech;
    }

    public void setbNolai_Clech(BigDecimal bNolai_Clech) {
        this.bNolai_Clech = bNolai_Clech;
    }

    public String getsNgnhan_Clech() {
        return sNgnhan_Clech;
    }

    public void setsNgnhan_Clech(String sNgnhan_Clech) {
        this.sNgnhan_Clech = sNgnhan_Clech;
    }

    @Override
    public void readSQL(SQLInput stream, String typeName) throws SQLException {
        setsSoku(stream.readString());
        setbC_Kntn_Sodu(stream.readBigDecimal());
        setbK_Kntn_Sodu(stream.readBigDecimal());
        setsK_Ma_Ngnhan(stream.readString());
        setsK_Ma_NgnhanC2(stream.readString());
        setsK_Ngnhan_Kh(stream.readString());
        setsQuanhe_Kh(stream.readString());
        setsTrangthai(stream.readString());
        setbNogoc_Clech(stream.readBigDecimal());
        setbNolai_Clech(stream.readBigDecimal());
        setsNgnhan_Clech(stream.readString());
    }

    @Override
    public void writeSQL(SQLOutput stream) throws SQLException {
        stream.writeString(getsSoku());
        stream.writeBigDecimal(getbC_Kntn_Sodu());
        stream.writeBigDecimal(getbK_Kntn_Sodu());
        stream.writeString(getsK_Ma_Ngnhan());
        stream.writeString(getsK_Ma_NgnhanC2());
        stream.writeString(getsK_Ngnhan_Kh());
        stream.writeString(getsQuanhe_Kh());
        stream.writeString(getsTrangthai());
        stream.writeBigDecimal(getbNogoc_Clech());
        stream.writeBigDecimal(getbNolai_Clech());
        stream.writeString(getsNgnhan_Clech());
    }
}