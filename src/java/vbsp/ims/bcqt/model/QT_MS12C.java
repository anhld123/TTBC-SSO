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
public class QT_MS12C {

    //<editor-fold defaultstate="collapsed" desc="Khai bao bien cho lop model">
    public String KHOA;
    public String MACN;
    public String MAPGD;
    public Date NGAYBC;
    public BigDecimal NAMBC;
    public Date NGAYNHAP;
    public String NGUOITAOLAP;
    public String TENKH;
    public String SOKU;
    public BigDecimal D1;
    public BigDecimal D2;
    public BigDecimal D3;
    public String CO_TONGHOP;
    public BigDecimal THUTU;
    public Date NGAYTAO;
    public String CHTRINH;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Phuong thuc get/set">
    public static QT_MS12C newInstance() {
        return new QT_MS12C();
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

    public BigDecimal getNAMBC() {
        return NAMBC;
    }

    public void setNAMBC(BigDecimal NAMBC) {
        this.NAMBC = NAMBC;
    }

    public Date getNGAYNHAP() {
        return NGAYNHAP;
    }

    public void setNGAYNHAP(Date NGAYNHAP) {
        this.NGAYNHAP = NGAYNHAP;
    }

    public String getNGUOITAOLAP() {
        return NGUOITAOLAP;
    }

    public void setNGUOITAOLAP(String NGUOITAOLAP) {
        this.NGUOITAOLAP = NGUOITAOLAP;
    }

    public String getTENKH() {
        return TENKH;
    }

    public void setTENKH(String TENKH) {
        this.TENKH = TENKH;
    }

    public String getSOKU() {
        return SOKU;
    }

    public void setSOKU(String SOKU) {
        this.SOKU = SOKU;
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

    public String getCO_TONGHOP() {
        return CO_TONGHOP;
    }

    public void setCO_TONGHOP(String CO_TONGHOP) {
        this.CO_TONGHOP = CO_TONGHOP;
    }

    public BigDecimal getTHUTU() {
        return THUTU;
    }

    public void setTHUTU(BigDecimal THUTU) {
        this.THUTU = THUTU;
    }

    public Date getNGAYTAO() {
        return NGAYTAO;
    }

    public void setNGAYTAO(Date NGAYTAO) {
        this.NGAYTAO = NGAYTAO;
    }

    public String getCHTRINH() {
        return CHTRINH;
    }

    public void setCHTRINH(String CHTRINH) {
        this.CHTRINH = CHTRINH;
    }
    //</editor-fold>
}
