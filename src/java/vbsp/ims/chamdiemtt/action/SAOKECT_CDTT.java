/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.chamdiemtt.action;

import java.sql.SQLData;
import java.sql.SQLException;
import java.sql.SQLInput;
import java.sql.SQLOutput;
import java.util.Date;

/**
 *
 * @author HP
 */
public class SAOKECT_CDTT implements SQLData {
    
    public static final String ORACLE_OBJECT_TYPE = "TYPE_SAOKECT_CDTT";
    public static final String ORACLE_TABLE_TYPE = "TAB_SAOKECT_CDTT";

    public static SAOKECT_CDTT newInstance() {
        return new SAOKECT_CDTT();
    }
    
    private String CDTT_KHOA ;
    private String CDTT_MAPGD  ;
    private Date CDTT_NGAYBC  ;
    private int CDTT_CN_DENGHI_LOAI_TRU;
    private String CDTT_NGUOI_DE_NGHI   ;
    private Date CDTT_NGAY_DE_NGHI ;
    private String CDTT_CN_LYDO;
    private int CDTT_TW_DUYET_LOAI_TRU;
    private String CDTT_NGUOI_DUYET ;
    private Date CDTT_NGAY_DUYET ;
    private String CDTT_TW_LYDO_TUCHOI;
    private int CDTT_TRANG_THAI;
    private String CDTT_MA;

    @Override
    public String getSQLTypeName() throws SQLException {
        return ORACLE_OBJECT_TYPE;
    }
    
    @Override
    public void readSQL(SQLInput stream, String typeName) throws SQLException {
        setCDTT_KHOA(stream.readString());
        setCDTT_MAPGD(stream.readString());
        setCDTT_NGAYBC(stream.readDate());
        setCDTT_CN_DENGHI_LOAI_TRU(stream.readInt());
        setCDTT_NGUOI_DE_NGHI(stream.readString());
        setCDTT_NGAY_DE_NGHI(stream.readDate());
        setCDTT_CN_LYDO(stream.readString());
        setCDTT_TW_DUYET_LOAI_TRU(stream.readInt());
        setCDTT_NGUOI_DUYET(stream.readString());
        setCDTT_NGAY_DUYET(stream.readDate());
        setCDTT_TW_LYDO_TUCHOI(stream.readString());
        setCDTT_TRANG_THAI(stream.readInt());
        setCDTT_MA(stream.readString());
    }
    
    @Override
    public void writeSQL(SQLOutput stream) throws SQLException {
        stream.writeString(getCDTT_KHOA());
        stream.writeString(getCDTT_MAPGD());
        stream.writeDate(getCDTT_NGAYBC()!= null ? new java.sql.Date(getCDTT_NGAYBC().getTime()) : null);
        stream.writeInt(getCDTT_CN_DENGHI_LOAI_TRU());
        stream.writeString(getCDTT_NGUOI_DE_NGHI());
        stream.writeDate(getCDTT_NGAY_DE_NGHI()!= null ? new java.sql.Date(getCDTT_NGAY_DE_NGHI().getTime()) : null);
        stream.writeString(getCDTT_CN_LYDO());
        stream.writeInt(getCDTT_TW_DUYET_LOAI_TRU());
        stream.writeString(getCDTT_NGUOI_DUYET());
        stream.writeDate(getCDTT_NGAY_DUYET()!= null ? new java.sql.Date(getCDTT_NGAY_DUYET().getTime()) : null);
        stream.writeString(getCDTT_TW_LYDO_TUCHOI());       
        stream.writeInt(getCDTT_TRANG_THAI());        
        stream.writeString(getCDTT_MA());
    }
    
    public String getCDTT_KHOA() {
        return CDTT_KHOA;
    }

    public void setCDTT_KHOA(String CDTT_KHOA) {
        this.CDTT_KHOA = CDTT_KHOA;
    }

    public String getCDTT_MAPGD() {
        return CDTT_MAPGD;
    }

    public void setCDTT_MAPGD(String CDTT_MAPGD) {
        this.CDTT_MAPGD = CDTT_MAPGD;
    }

    public Date getCDTT_NGAYBC() {
        return CDTT_NGAYBC;
    }

    public void setCDTT_NGAYBC(Date CDTT_NGAYBC) {
        this.CDTT_NGAYBC = CDTT_NGAYBC;
    }

    public int getCDTT_CN_DENGHI_LOAI_TRU() {
        return CDTT_CN_DENGHI_LOAI_TRU;
    }

    public void setCDTT_CN_DENGHI_LOAI_TRU(int CDTT_CN_DENGHI_LOAI_TRU) {
        this.CDTT_CN_DENGHI_LOAI_TRU = CDTT_CN_DENGHI_LOAI_TRU;
    }

    public String getCDTT_NGUOI_DE_NGHI() {
        return CDTT_NGUOI_DE_NGHI;
    }

    public void setCDTT_NGUOI_DE_NGHI(String CDTT_NGUOI_DE_NGHI) {
        this.CDTT_NGUOI_DE_NGHI = CDTT_NGUOI_DE_NGHI;
    }

    public Date getCDTT_NGAY_DE_NGHI() {
        return CDTT_NGAY_DE_NGHI;
    }

    public void setCDTT_NGAY_DE_NGHI(Date CDTT_NGAY_DE_NGHI) {
        this.CDTT_NGAY_DE_NGHI = CDTT_NGAY_DE_NGHI;
    }

    public String getCDTT_CN_LYDO() {
        return CDTT_CN_LYDO;
    }

    public void setCDTT_CN_LYDO(String CDTT_CN_LYDO) {
        this.CDTT_CN_LYDO = CDTT_CN_LYDO;
    }

    public int getCDTT_TW_DUYET_LOAI_TRU() {
        return CDTT_TW_DUYET_LOAI_TRU;
    }

    public void setCDTT_TW_DUYET_LOAI_TRU(int CDTT_TW_DUYET_LOAI_TRU) {
        this.CDTT_TW_DUYET_LOAI_TRU = CDTT_TW_DUYET_LOAI_TRU;
    }

    public String getCDTT_NGUOI_DUYET() {
        return CDTT_NGUOI_DUYET;
    }

    public void setCDTT_NGUOI_DUYET(String CDTT_NGUOI_DUYET) {
        this.CDTT_NGUOI_DUYET = CDTT_NGUOI_DUYET;
    }

    public Date getCDTT_NGAY_DUYET() {
        return CDTT_NGAY_DUYET;
    }

    public void setCDTT_NGAY_DUYET(Date CDTT_NGAY_DUYET) {
        this.CDTT_NGAY_DUYET = CDTT_NGAY_DUYET;
    }

    public String getCDTT_TW_LYDO_TUCHOI() {
        return CDTT_TW_LYDO_TUCHOI;
    }

    public void setCDTT_TW_LYDO_TUCHOI(String CDTT_TW_LYDO_TUCHOI) {
        this.CDTT_TW_LYDO_TUCHOI = CDTT_TW_LYDO_TUCHOI;
    }

    public int getCDTT_TRANG_THAI() {
        return CDTT_TRANG_THAI;
    }

    public void setCDTT_TRANG_THAI(int CDTT_TRANG_THAI) {
        this.CDTT_TRANG_THAI = CDTT_TRANG_THAI;
    }

    public String getCDTT_MA() {
        return CDTT_MA;
    }

    public void setCDTT_MA(String CDTT_MA) {
        this.CDTT_MA = CDTT_MA;
    }
    
    
}
