/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.model;

import java.math.BigDecimal;

/**
 *
 * @author Tom
 */
public class AntiMoney {

    private String ma_gd;
    private String ma_pgd;
    private String lenh_ct;
    private BigDecimal sotien;
    private BigDecimal sotien_quydoi;
    private String noidung_ct;
    private String ten_ta;
    private String diachi;
    private String kieukh;
    private String cmt;
    private String sohc;
    private String sotk;
    private String loai_tk;
    private String tt_tk;
    private String ma_nh;
    private String sotk_th;
    private String mst;
    private String ngaygd;
    private String loaibc;
    private String manv;
    private String tenkh_th;

    private String tenkh;
    private String loaigt;
    private String sogt;
    private String kenhct;

    public String getTenkh() {
        return tenkh;
    }

    public void setTenkh(String tenkh) {
        this.tenkh = tenkh;
    }

    public String getLoaigt() {
        return loaigt;
    }

    public void setLoaigt(String loaigt) {
        this.loaigt = loaigt;
    }

    public String getSogt() {
        return sogt;
    }

    public void setSogt(String sogt) {
        this.sogt = sogt;
    }

    public String getKenhct() {
        return kenhct;
    }

    public void setKenhct(String kenhct) {
        this.kenhct = kenhct;
    }

    public String getManv() {
        return manv;
    }

    public void setManv(String manv) {
        this.manv = manv;
    }

    public String getTenkh_th() {
        return tenkh_th;
    }

    public void setTenkh_th(String tenkh_th) {
        this.tenkh_th = tenkh_th;
    }

    public String getLoaibc() {
        return loaibc;
    }

    public void setLoaibc(String loaibc) {
        this.loaibc = loaibc;
    }

    public String getNgaygd() {
        return ngaygd;
    }

    public void setNgaygd(String ngaygd) {
        this.ngaygd = ngaygd;
    }

    public String getMst() {
        return mst;
    }

    public void setMst(String mst) {
        this.mst = mst;
    }

    public String getNoidung_ct() {
        return noidung_ct;
    }

    public void setNoidung_ct(String noidung_ct) {
        this.noidung_ct = noidung_ct;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public String getKieukh() {
        return kieukh;
    }

    public void setKieukh(String kieukh) {
        this.kieukh = kieukh;
    }

    public String getCmt() {
        return cmt;
    }

    public void setCmt(String cmt) {
        this.cmt = cmt;
    }

    public String getSohc() {
        return sohc;
    }

    public void setSohc(String sohc) {
        this.sohc = sohc;
    }

    public String getSotk() {
        return sotk;
    }

    public void setSotk(String sotk) {
        this.sotk = sotk;
    }

    public String getTt_tk() {
        return tt_tk;
    }

    public void setTt_tk(String tt_tk) {
        this.tt_tk = tt_tk;
    }

    public AntiMoney() {
    }

    public AntiMoney(String ma_dg, String ma_pgd) {
        this.ma_gd = ma_dg;
        this.ma_pgd = ma_pgd;
        //  this.lenh_ct = lenh_ct;
        this.sotien = new BigDecimal("0");
        this.sotien_quydoi = new BigDecimal("0");
    }

    public AntiMoney(String ma_gd, String ma_pgd, String lenh_ct, BigDecimal sotien, BigDecimal sotien_quydoi,
            String noidung_ct, String ten_ta, String diachi, String kieukh, String cmt, String sohc,
            String sotk, String loai_tk, String tt_tk, String ma_nh, String sotk_th, String mst, String ngaygd,
            String loaibc, String manv, String tenkh_th, String tenkh, String loaigt, String sogt, String kenhct) {
        this.ma_gd = ma_gd;
        this.ma_pgd = ma_pgd;
        this.lenh_ct = lenh_ct;
        this.sotien = sotien;
        this.sotien_quydoi = sotien_quydoi;
        this.noidung_ct = noidung_ct;
        this.ten_ta = ten_ta;
        this.diachi = diachi;
        this.kieukh = kieukh;
        this.cmt = cmt;
        this.sohc = sohc;
        this.sotk = sotk;
        this.loai_tk = loai_tk;
        this.tt_tk = tt_tk;
        this.ma_nh = ma_nh;
        this.sotk_th = sotk_th;
        this.mst = mst;
        this.ngaygd = ngaygd;
        this.loaibc = loaibc;
        this.manv = manv;
        this.tenkh_th = tenkh_th;
        this.tenkh = tenkh;
        this.loaigt = loaigt;
        this.sogt = sogt;
        this.kenhct = kenhct;
    }

    public String getSotk_th() {
        return sotk_th;
    }

    public void setSotk_th(String sotk_th) {
        this.sotk_th = sotk_th;
    }

    public String getTen_ta() {
        return ten_ta;
    }

    public void setTen_ta(String ten_ta) {
        this.ten_ta = ten_ta;
    }

    public String getLoai_tk() {
        return loai_tk;
    }

    public void setLoai_tk(String loai_tk) {
        this.loai_tk = loai_tk;
    }

    public String getMa_nh() {
        return ma_nh;
    }

    public void setMa_nh(String ma_nh) {
        this.ma_nh = ma_nh;
    }

    public String getLenh_ct() {
        return lenh_ct;
    }

    public void setLenh_ct(String lenh_ct) {
        this.lenh_ct = lenh_ct;
    }

    public String getMa_gd() {
        return ma_gd;
    }

    public void setMa_gd(String ma_gd) {
        this.ma_gd = ma_gd;
    }

    public String getMa_pgd() {
        return ma_pgd;
    }

    public void setMa_pgd(String ma_pgd) {
        this.ma_pgd = ma_pgd;
    }

    public BigDecimal getSotien() {
        return sotien;
    }

    public void setSotien(BigDecimal sotien) {
        this.sotien = sotien;
    }

    public BigDecimal getSotien_quydoi() {
        return sotien_quydoi;
    }

    public void setSotien_quydoi(BigDecimal sotien_quydoi) {
        this.sotien_quydoi = sotien_quydoi;
    }

}
