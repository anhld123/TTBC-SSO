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
public class QT_MS12A {

    //<editor-fold defaultstate="collapsed" desc="Khai bao bien cho lop model">
    public String KHOA;
    public String MACN;
    public String MAPGD;
    public Date NGAYBC;
    public String TENKH;
    public String SOKU;
    public String SOKU_FOX;
    public BigDecimal D1;
    public BigDecimal D2;
    public BigDecimal D3;
    public BigDecimal D4;
    public BigDecimal D5;
    public BigDecimal D6;
    public String TRANGTHAI;
    public String MAKH;
    public BigDecimal NAMBC;
    public Date NGAYTAO;
    public String NGUOI_NHAP;
    public Date NGAY_NHAP;
    public String ROWID;
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Phuong thuc get/set">
    public static QT_MS12A newInstance() {
        return new QT_MS12A();
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

    public String getSOKU() {
        return SOKU;
    }

    public void setSOKU(String SOKU) {
        this.SOKU = SOKU;
    }

    public String getTENKH() {
        return TENKH;
    }

    public void setTENKH(String TENKH) {
        this.TENKH = TENKH;
    }

    public String getSOKU_FOX() {
        return SOKU_FOX;
    }

    public void setSOKU_FOX(String SOKU_FOX) {
        this.SOKU_FOX = SOKU_FOX;
    }

    public BigDecimal getD1() {
        return D1;
    }

    public void setD1(BigDecimal D1) {
        this.D1 = D1;
    }

    public BigDecimal getD2() {
        return D2;
    }

    public void setD2(BigDecimal D2) {
        this.D2 = D2;
    }

    public BigDecimal getD3() {
        return D3;
    }

    public void setD3(BigDecimal D3) {
        this.D3 = D3;
    }

    public BigDecimal getD4() {
        return D4;
    }

    public void setD4(BigDecimal D4) {
        this.D4 = D4;
    }

    public BigDecimal getD5() {
        return D5;
    }

    public void setD5(BigDecimal D5) {
        this.D5 = D5;
    }

    public BigDecimal getD6() {
        return D6;
    }

    public void setD6(BigDecimal D6) {
        this.D6 = D6;
    }

    public String getTRANGTHAI() {
        return TRANGTHAI;
    }

    public void setTRANGTHAI(String TRANGTHAI) {
        this.TRANGTHAI = TRANGTHAI;
    }

    public String getMAKH() {
        return MAKH;
    }

    public void setMAKH(String MAKH) {
        this.MAKH = MAKH;
    }

    public BigDecimal getNAMBC() {
        return NAMBC;
    }

    public void setNAMBC(BigDecimal NAMBC) {
        this.NAMBC = NAMBC;
    }

    public Date getNGAYTAO() {
        return NGAYTAO;
    }

    public void setNGAYTAO(Date NGAYTAO) {
        this.NGAYTAO = NGAYTAO;
    }

    public String getNGUOI_NHAP() {
        return NGUOI_NHAP;
    }

    public void setNGUOI_NHAP(String NGUOI_NHAP) {
        this.NGUOI_NHAP = NGUOI_NHAP;
    }

    public Date getNGAY_NHAP() {
        return NGAY_NHAP;
    }

    public void setNGAY_NHAP(Date NGAY_NHAP) {
        this.NGAY_NHAP = NGAY_NHAP;
    }

    public String getROWID() {
        return ROWID;
    }

    public void setROWID(String ROWID) {
        this.ROWID = ROWID;
    }
//</editor-fold>

}
