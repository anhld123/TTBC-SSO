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
public class QT_MS11B {

    private String KHOA;
    private String MACN;
    private String MAPGD;
    private String MAXA;
    private Date NGAYBC;
    private BigDecimal NAMQT;
    private Date NGAY_NHAP;
    private String NGUOI_NHAP;
    private String MAKH;
    private String TENKH;
    private String DIACHI;
    private String SOKU;
    private String MATO;
    private String TENTT;
    private String NG_CHIEMDUNG;
    private String MA_CHIEMDUNG;
    private String CHTRINH;
    private String SANPHAM;
    private Date NGAY_HT;
    private String SBT;
    private BigDecimal SOTIEN_GOC;
    private BigDecimal SOTIEN_LAI;
    private BigDecimal GOC_CANDOI;
    private BigDecimal LAI_CANDOI;
    private BigDecimal SOTIEN_GOC_NT;
    private BigDecimal SOTIEN_LAI_NT;
    private BigDecimal GOC_CANDOI_NT;
    private BigDecimal LAI_CANDOI_NT;
    private String TTMONVAY;
    private Date NG_CAPNHAT;
    private String TT_ROW;
    public String ROWID;

    public String getSANPHAM() {
        return SANPHAM;
    }

    public void setSANPHAM(String SANPHAM) {
        this.SANPHAM = SANPHAM;
    }
    
    
    
     public static QT_MS11B newInstance() {
        return new QT_MS11B();
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

    public String getMAXA() {
        return MAXA;
    }

    public void setMAXA(String MAXA) {
        this.MAXA = MAXA;
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

    public String getNG_CHIEMDUNG() {
        return NG_CHIEMDUNG;
    }

    public void setNG_CHIEMDUNG(String NG_CHIEMDUNG) {
        this.NG_CHIEMDUNG = NG_CHIEMDUNG;
    }

    public String getMA_CHIEMDUNG() {
        return MA_CHIEMDUNG;
    }

    public void setMA_CHIEMDUNG(String MA_CHIEMDUNG) {
        this.MA_CHIEMDUNG = MA_CHIEMDUNG;
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

    public String getSBT() {
        return SBT;
    }

    public void setSBT(String SBT) {
        this.SBT = SBT;
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

    public BigDecimal getGOC_CANDOI() {
        return GOC_CANDOI;
    }

    public void setGOC_CANDOI(BigDecimal GOC_CANDOI) {
        this.GOC_CANDOI = GOC_CANDOI;
    }

    public BigDecimal getLAI_CANDOI() {
        return LAI_CANDOI;
    }

    public void setLAI_CANDOI(BigDecimal LAI_CANDOI) {
        this.LAI_CANDOI = LAI_CANDOI;
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

    public BigDecimal getGOC_CANDOI_NT() {
        return GOC_CANDOI_NT;
    }

    public void setGOC_CANDOI_NT(BigDecimal GOC_CANDOI_NT) {
        this.GOC_CANDOI_NT = GOC_CANDOI_NT;
    }

    public BigDecimal getLAI_CANDOI_NT() {
        return LAI_CANDOI_NT;
    }

    public void setLAI_CANDOI_NT(BigDecimal LAI_CANDOI_NT) {
        this.LAI_CANDOI_NT = LAI_CANDOI_NT;
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
         
}
