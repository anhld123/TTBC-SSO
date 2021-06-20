/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.bcqt.model;

import java.math.BigDecimal;
import java.sql.Date;


/**
 *
 * @author LION
 */
public class QT_MS13SK {

    //<editor-fold defaultstate="collapsed" desc="Khai bao bien">
    public String KHOA;
    public String MACN;
    public String MAPGD;
    public Date NGAYBC;
    public BigDecimal NAMQT;
    public Date NGAY_NHAP;
    public String NGUOI_NHAP;
    public String SOKU;
    public String MA_TOT;
    public String TEN_TOT;
    public String TENKH;
    public BigDecimal SOTIEN_GLTTOAN;
    public BigDecimal SOTIEN_GLGNHAN;
    public BigDecimal LAIGIAM_TTOAN;
    public BigDecimal LAIGIAM_GHINHAN;
    public String TTMONVAY;
    public Date NG_CAPNHAT;
    public String TT_ROW;
    public String ROWID;
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Phuong thuc get/set">
     public static QT_MS13SK newInstance() {
        return new QT_MS13SK();
    }
    public String getKHOA() {
        return KHOA;
    }
    
    public void setKHOA(String KHOA) {
        this.KHOA = KHOA;
    }
    
    public String getMACN() {
        return MACN;
    }
    
    public void setMACN(String MACN) {
        this.MACN = MACN;
    }
    
    public String getMAPGD() {
        return MAPGD;
    }
    
    public void setMAPGD(String MAPGD) {
        this.MAPGD = MAPGD;
    }
    
    public Date getNGAYBC() {
        return NGAYBC;
    }
    
    public void setNGAYBC(Date NGAYBC) {
        this.NGAYBC = NGAYBC;
    }
    
    public BigDecimal getNAMQT() {
        return NAMQT;
    }
    
    public void setNAMQT(BigDecimal NAMQT) {
        this.NAMQT = NAMQT;
    }
    
    public Date getNGAY_NHAP() {
        return NGAY_NHAP;
    }
    
    public void setNGAY_NHAP(Date NGAY_NHAP) {
        this.NGAY_NHAP = NGAY_NHAP;
    }
    
    public String getNGUOI_NHAP() {
        return NGUOI_NHAP;
    }
    
    public void setNGUOI_NHAP(String NGUOI_NHAP) {
        this.NGUOI_NHAP = NGUOI_NHAP;
    }
    
    public String getSOKU() {
        return SOKU;
    }
    
    public void setSOKU(String SOKU) {
        this.SOKU = SOKU;
    }
    
    public String getMA_TOT() {
        return MA_TOT;
    }
    
    public void setMA_TOT(String MA_TOT) {
        this.MA_TOT = MA_TOT;
    }
    
    public String getTEN_TOT() {
        return TEN_TOT;
    }
    
    public void setTEN_TOT(String TEN_TOT) {
        this.TEN_TOT = TEN_TOT;
    }
    
    public String getTENKH() {
        return TENKH;
    }
    
    public void setTENKH(String TENKH) {
        this.TENKH = TENKH;
    }
    
    public BigDecimal getSOTIEN_GLTTOAN() {
        return SOTIEN_GLTTOAN;
    }
    
    public void setSOTIEN_GLTTOAN(BigDecimal SOTIEN_GLTTOAN) {
        this.SOTIEN_GLTTOAN = SOTIEN_GLTTOAN;
    }
    
    public BigDecimal getSOTIEN_GLGNHAN() {
        return SOTIEN_GLGNHAN;
    }
    
    public void setSOTIEN_GLGNHAN(BigDecimal SOTIEN_GLGNHAN) {
        this.SOTIEN_GLGNHAN = SOTIEN_GLGNHAN;
    }
    
    public BigDecimal getLAIGIAM_TTOAN() {
        return LAIGIAM_TTOAN;
    }
    
    public void setLAIGIAM_TTOAN(BigDecimal LAIGIAM_TTOAN) {
        this.LAIGIAM_TTOAN = LAIGIAM_TTOAN;
    }
    
    public BigDecimal getLAIGIAM_GHINHAN() {
        return LAIGIAM_GHINHAN;
    }
    
    public void setLAIGIAM_GHINHAN(BigDecimal LAIGIAM_GHINHAN) {
        this.LAIGIAM_GHINHAN = LAIGIAM_GHINHAN;
    }
    
    public String getTTMONVAY() {
        return TTMONVAY;
    }
    
    public void setTTMONVAY(String TTMONVAY) {
        this.TTMONVAY = TTMONVAY;
    }
    
    public Date getNG_CAPNHAT() {
        return NG_CAPNHAT;
    }
    
    public void setNG_CAPNHAT(Date NG_CAPNHAT) {
        this.NG_CAPNHAT = NG_CAPNHAT;
    }

    public String getTT_ROW() {
        return TT_ROW;
    }

    public void setTT_ROW(String TT_ROW) {
        this.TT_ROW = TT_ROW;
    }
    
    public String getROWID() {
        return ROWID;
    }

    public void setROWID(String ROWID) {
        this.ROWID = ROWID;
    }
    //</editor-fold>    
}
