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
 * @author BAOANH
 */
public class QT_MS11A {

    private String KHOA;
    private String MACN;
    private String MAPGD;
    private Date NGAYBC;
    private BigDecimal NAMQT;
    private Date NGAY_NHAP;
    private String NGUOI_NHAP;
    private String MAKH;
    private String TENKH;
    private String DIACHI;
    private String SOKU;
    private String CHTRINH;
    private Date NGAY_HT;
    private String MASP;
    private BigDecimal SOTIEN_GOC;
    private BigDecimal SOTIEN_LAI;
    private BigDecimal GOC_HACHTOAN;
    private BigDecimal LAI_HACHTOAN;
    private BigDecimal SOTIEN_GOC_NT;
    private BigDecimal SOTIEN_LAI_NT;
    private BigDecimal GOC_HACHTOAN_NT;
    private BigDecimal LAI_HACHTOAN_NT;
    private String TTMONVAY;
    private Date NG_CAPNHAT;
    private String TT_ROW;
   public String ROWID;
   private String MAXA;
   private String MATO;
   private String TENTT;

    public String getROWID() {
        return ROWID;
    }

    public void setROWID(String ROWID) {
        this.ROWID = ROWID;
    }
   
   
    public static QT_MS11A newInstance() {
        return new QT_MS11A();
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

    public String getMAKH() {
        return MAKH;
    }

    public void setMAKH(String MAKH) {
        this.MAKH = MAKH;
    }

    public String getTENKH() {
        return TENKH;
    }

    public void setTENKH(String TENKH) {
        this.TENKH = TENKH;
    }

    public String getDIACHI() {
        return DIACHI;
    }

    public void setDIACHI(String DIACHI) {
        this.DIACHI = DIACHI;
    }

    public String getSOKU() {
        return SOKU;
    }

    public void setSOKU(String SOKU) {
        this.SOKU = SOKU;
    }

    public String getCHTRINH() {
        return CHTRINH;
    }

    public void setCHTRINH(String CHTRINH) {
        this.CHTRINH = CHTRINH;
    }

    public Date getNGAY_HT() {
        return NGAY_HT;
    }

    public void setNGAY_HT(Date NGAY_HT) {
        this.NGAY_HT = NGAY_HT;
    }

    public String getMASP() {
        return MASP;
    }

    public void setMASP(String MASP) {
        this.MASP = MASP;
    }

    public BigDecimal getSOTIEN_GOC() {
        return SOTIEN_GOC;
    }

    public void setSOTIEN_GOC(BigDecimal SOTIEN_GOC) {
        this.SOTIEN_GOC = SOTIEN_GOC;
    }

    public BigDecimal getSOTIEN_LAI() {
        return SOTIEN_LAI;
    }

    public void setSOTIEN_LAI(BigDecimal SOTIEN_LAI) {
        this.SOTIEN_LAI = SOTIEN_LAI;
    }

    public BigDecimal getGOC_HACHTOAN() {
        return GOC_HACHTOAN;
    }

    public void setGOC_HACHTOAN(BigDecimal GOC_HACHTOAN) {
        this.GOC_HACHTOAN = GOC_HACHTOAN;
    }

    public BigDecimal getLAI_HACHTOAN() {
        return LAI_HACHTOAN;
    }

    public void setLAI_HACHTOAN(BigDecimal LAI_HACHTOAN) {
        this.LAI_HACHTOAN = LAI_HACHTOAN;
    }

    public BigDecimal getSOTIEN_GOC_NT() {
        return SOTIEN_GOC_NT;
    }

    public void setSOTIEN_GOC_NT(BigDecimal SOTIEN_GOC_NT) {
        this.SOTIEN_GOC_NT = SOTIEN_GOC_NT;
    }

    public BigDecimal getSOTIEN_LAI_NT() {
        return SOTIEN_LAI_NT;
    }

    public void setSOTIEN_LAI_NT(BigDecimal SOTIEN_LAI_NT) {
        this.SOTIEN_LAI_NT = SOTIEN_LAI_NT;
    }

    public BigDecimal getGOC_HACHTOAN_NT() {
        return GOC_HACHTOAN_NT;
    }

    public void setGOC_HACHTOAN_NT(BigDecimal GOC_HACHTOAN_NT) {
        this.GOC_HACHTOAN_NT = GOC_HACHTOAN_NT;
    }

    public BigDecimal getLAI_HACHTOAN_NT() {
        return LAI_HACHTOAN_NT;
    }

    public void setLAI_HACHTOAN_NT(BigDecimal LAI_HACHTOAN_NT) {
        this.LAI_HACHTOAN_NT = LAI_HACHTOAN_NT;
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

    public String getMAXA() {
        return MAXA;
    }

    public void setMAXA(String MAXA) {
        this.MAXA = MAXA;
    }

    public String getMATO() {
        return MATO;
    }

    public void setMATO(String MATO) {
        this.MATO = MATO;
    }

    public String getTENTT() {
        return TENTT;
    }

    public void setTENTT(String TENTT) {
        this.TENTT = TENTT;
    }
    

}
