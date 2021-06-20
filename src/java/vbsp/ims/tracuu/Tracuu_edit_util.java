/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.tracuu;

import java.sql.SQLData;
import java.sql.SQLException;
import java.sql.SQLInput;
import java.sql.SQLOutput;

/**
 *
 * @author Administrator
 */
public class Tracuu_edit_util implements SQLData{
    private String DS_MAKH;
    private String DS_MATINH;
    private String DS_MAHUYEN;
    private String DS_MAXA;
    private String DS_MATHON;
    private String DS_TENKH;
    private String DS_GIOITINH;
    private String DS_NGAYSINH;
    private String DS_DANTOC;
    private String DS_SOCMT;
    private String DS_NGAYCAP;
    private String DS_NOICAP;
    private String DS_TD_HOCVAN;
    private String DS_FLAG_CH;
    private String DS_QUANHE;
    private String DS_DT_CHINHSACH;
    private String DS_TN_BINHQUAN;
    private String DS_LOAI_KH;
    private String DS_NGAYLOAI;
    private String DS_NAMSL;
    private String DS_NGUYENNHAN;
    private String DS_GHICHU;

    public Tracuu_edit_util() {
    }

    public Tracuu_edit_util(String DS_MAKH, String DS_MATINH, String DS_MAHUYEN, String DS_MAXA, String DS_MATHON, String DS_TENKH, String DS_GIOITINH, String DS_NGAYSINH, String DS_DANTOC, String DS_SOCMT, String DS_NGAYCAP, String DS_NOICAP, String DS_TD_HOCVAN, String DS_FLAG_CH, String DS_QUANHE, String DS_DT_CHINHSACH, String DS_TN_BINHQUAN, String DS_LOAI_KH, String DS_NGAYLOAI, String DS_NAMSL, String DS_NGUYENNHAN, String DS_GHICHU) {
        this.DS_MAKH = DS_MAKH;
        this.DS_MATINH = DS_MATINH;
        this.DS_MAHUYEN = DS_MAHUYEN;
        this.DS_MAXA = DS_MAXA;
        this.DS_MATHON = DS_MATHON;
        this.DS_TENKH = DS_TENKH;
        this.DS_GIOITINH = DS_GIOITINH;
        this.DS_NGAYSINH = DS_NGAYSINH;
        this.DS_DANTOC = DS_DANTOC;
        this.DS_SOCMT = DS_SOCMT;
        this.DS_NGAYCAP = DS_NGAYCAP;
        this.DS_NOICAP = DS_NOICAP;
        this.DS_TD_HOCVAN = DS_TD_HOCVAN;
        this.DS_FLAG_CH = DS_FLAG_CH;
        this.DS_QUANHE = DS_QUANHE;
        this.DS_DT_CHINHSACH = DS_DT_CHINHSACH;
        this.DS_TN_BINHQUAN = DS_TN_BINHQUAN;
        this.DS_LOAI_KH = DS_LOAI_KH;
        this.DS_NGAYLOAI = DS_NGAYLOAI;
        this.DS_NAMSL = DS_NAMSL;
        this.DS_NGUYENNHAN = DS_NGUYENNHAN;
        this.DS_GHICHU = DS_GHICHU;
    }

//<editor-fold defaultstate="collapsed" desc="Lớp GET SET">
    public String getDS_MAKH() {
        return DS_MAKH;
    }
    
    public void setDS_MAKH(String DS_MAKH) {
        this.DS_MAKH = DS_MAKH;
    }
    
    public String getDS_MATINH() {
        return DS_MATINH;
    }
    
    public void setDS_MATINH(String DS_MATINH) {
        this.DS_MATINH = DS_MATINH;
    }
    
    public String getDS_MAHUYEN() {
        return DS_MAHUYEN;
    }
    
    public void setDS_MAHUYEN(String DS_MAHUYEN) {
        this.DS_MAHUYEN = DS_MAHUYEN;
    }
    
    public String getDS_MAXA() {
        return DS_MAXA;
    }
    
    public void setDS_MAXA(String DS_MAXA) {
        this.DS_MAXA = DS_MAXA;
    }
    
    public String getDS_MATHON() {
        return DS_MATHON;
    }
    
    public void setDS_MATHON(String DS_MATHON) {
        this.DS_MATHON = DS_MATHON;
    }
    
    public String getDS_TENKH() {
        return DS_TENKH;
    }
    
    public void setDS_TENKH(String DS_TENKH) {
        this.DS_TENKH = DS_TENKH;
    }
    
    public String getDS_GIOITINH() {
        return DS_GIOITINH;
    }
    
    public void setDS_GIOITINH(String DS_GIOITINH) {
        this.DS_GIOITINH = DS_GIOITINH;
    }
    
    public String getDS_NGAYSINH() {
        return DS_NGAYSINH;
    }
    
    public void setDS_NGAYSINH(String DS_NGAYSINH) {
        this.DS_NGAYSINH = DS_NGAYSINH;
    }
    
    public String getDS_DANTOC() {
        return DS_DANTOC;
    }
    
    public void setDS_DANTOC(String DS_DANTOC) {
        this.DS_DANTOC = DS_DANTOC;
    }
    
    public String getDS_SOCMT() {
        return DS_SOCMT;
    }
    
    public void setDS_SOCMT(String DS_SOCMT) {
        this.DS_SOCMT = DS_SOCMT;
    }
    
    public String getDS_NGAYCAP() {
        return DS_NGAYCAP;
    }
    
    public void setDS_NGAYCAP(String DS_NGAYCAP) {
        this.DS_NGAYCAP = DS_NGAYCAP;
    }
    
    public String getDS_NOICAP() {
        return DS_NOICAP;
    }
    
    public void setDS_NOICAP(String DS_NOICAP) {
        this.DS_NOICAP = DS_NOICAP;
    }
    
    public String getDS_TD_HOCVAN() {
        return DS_TD_HOCVAN;
    }
    
    public void setDS_TD_HOCVAN(String DS_TD_HOCVAN) {
        this.DS_TD_HOCVAN = DS_TD_HOCVAN;
    }
    
    public String getDS_FLAG_CH() {
        return DS_FLAG_CH;
    }
    
    public void setDS_FLAG_CH(String DS_FLAG_CH) {
        this.DS_FLAG_CH = DS_FLAG_CH;
    }
    
    public String getDS_QUANHE() {
        return DS_QUANHE;
    }
    
    public void setDS_QUANHE(String DS_QUANHE) {
        this.DS_QUANHE = DS_QUANHE;
    }
    
    public String getDS_DT_CHINHSACH() {
        return DS_DT_CHINHSACH;
    }
    
    public void setDS_DT_CHINHSACH(String DS_DT_CHINHSACH) {
        this.DS_DT_CHINHSACH = DS_DT_CHINHSACH;
    }
    
    public String getDS_TN_BINHQUAN() {
        return DS_TN_BINHQUAN;
    }
    
    public void setDS_TN_BINHQUAN(String DS_TN_BINHQUAN) {
        this.DS_TN_BINHQUAN = DS_TN_BINHQUAN;
    }
    
    public String getDS_LOAI_KH() {
        return DS_LOAI_KH;
    }
    
    public void setDS_LOAI_KH(String DS_LOAI_KH) {
        this.DS_LOAI_KH = DS_LOAI_KH;
    }
    
    public String getDS_NGAYLOAI() {
        return DS_NGAYLOAI;
    }
    
    public void setDS_NGAYLOAI(String DS_NGAYLOAI) {
        this.DS_NGAYLOAI = DS_NGAYLOAI;
    }
    
    public String getDS_NAMSL() {
        return DS_NAMSL;
    }
    
    public void setDS_NAMSL(String DS_NAMSL) {
        this.DS_NAMSL = DS_NAMSL;
    }
    
    public String getDS_NGUYENNHAN() {
        return DS_NGUYENNHAN;
    }
    
    public void setDS_NGUYENNHAN(String DS_NGUYENNHAN) {
        this.DS_NGUYENNHAN = DS_NGUYENNHAN;
    }
    
    public String getDS_GHICHU() {
        return DS_GHICHU;
    }
    
    public void setDS_GHICHU(String DS_GHICHU) {
        this.DS_GHICHU = DS_GHICHU;
    }
//</editor-fold>

    @Override
    public String getSQLTypeName() throws SQLException {
        return "TYPE_DS_HONGHEO";
    }

    @Override
    public void readSQL(SQLInput stream, String typeName) throws SQLException {
        setDS_MAKH(stream.readString().trim());
        setDS_MATINH(stream.readString().trim());
        setDS_MAHUYEN(stream.readString().trim());
        setDS_MAXA(stream.readString().trim());
        setDS_MATHON(stream.readString().trim());
        setDS_TENKH(stream.readString().trim());
        setDS_GIOITINH(stream.readString().trim());
        setDS_NGAYSINH(stream.readString().trim());
        setDS_DANTOC(stream.readString().trim());
        setDS_SOCMT(stream.readString().trim());
        setDS_NGAYCAP(stream.readString().trim());
        setDS_NOICAP(stream.readString().trim());
        setDS_TD_HOCVAN(stream.readString().trim());
        setDS_FLAG_CH(stream.readString().trim());
        setDS_QUANHE(stream.readString().trim());
        setDS_DT_CHINHSACH(stream.readString().trim());
        setDS_TN_BINHQUAN(stream.readString().trim());
        setDS_LOAI_KH(stream.readString().trim());
        setDS_NGAYLOAI(stream.readString().trim());
        setDS_NAMSL(stream.readString().trim());
        setDS_NGUYENNHAN(stream.readString().trim());
        setDS_GHICHU(stream.readString().trim());
    }

    @Override
    public void writeSQL(SQLOutput stream) throws SQLException {
        stream.writeString(getDS_MAKH().trim());
        stream.writeString(getDS_MATINH().trim());
        stream.writeString(getDS_MAHUYEN().trim());
        stream.writeString(getDS_MAXA().trim());
        stream.writeString(getDS_MATHON().trim());
        stream.writeString(getDS_TENKH().trim());
        stream.writeString(getDS_GIOITINH().trim());
        stream.writeString(getDS_NGAYSINH().trim());
        stream.writeString(getDS_DANTOC().trim());
        stream.writeString(getDS_SOCMT().trim());
        stream.writeString(getDS_NGAYCAP().trim());
        stream.writeString(getDS_NOICAP().trim());
        stream.writeString(getDS_TD_HOCVAN().trim());
        stream.writeString(getDS_FLAG_CH().trim());
        stream.writeString(getDS_QUANHE().trim());
        stream.writeString(getDS_DT_CHINHSACH().trim());
        stream.writeString(getDS_TN_BINHQUAN().trim());
        stream.writeString(getDS_LOAI_KH().trim());
        stream.writeString(getDS_NGAYLOAI().trim());
        stream.writeString(getDS_NAMSL().trim());
        stream.writeString(getDS_NGUYENNHAN().trim());
        stream.writeString(getDS_GHICHU().trim());
    } 
}
