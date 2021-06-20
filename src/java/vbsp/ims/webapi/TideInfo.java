/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.webapi;

import java.math.BigDecimal;

/**
 *
 * @author BAOANH
 */
public class TideInfo {
//<editor-fold defaultstate="collapsed" desc="khai bao bien">
    
    private String PosCode;
    private String MaKH;
    private String TenKH;
    private String DiaChi;
    private String So_TChieu;
    private String MaSP;
    private String TenSP;
    private String TienTe;
    private String GL_TK;
    private String SoTK;
    private String Ngay_Gui;
    private String Ngay_DH;
    private int KyHan;
    private String KyHan_DV;
    private BigDecimal SoDu_HT;
    private BigDecimal SoDu_HT_DenHan;
    private BigDecimal SoDu_BanDau;
    private BigDecimal SoDu_BanDau_DenHan;
    private BigDecimal LaiSuat;
    private BigDecimal LaiDuThu;
    private BigDecimal LaiDaTra;
    private BigDecimal St_PhongToa;
    private String TrangThai;
    private String TT_SoHuu;
    private String NgayDong_TK;
    private String MaPGD;
    private String TenPGD;
    private String DanhXung;
    private String NgaySinh;
    private String SoCMT;
    private String Eod_Date;
    private String Br_CD;
    private String Base_No;
    private String Dep_Ser_No;
    private String Auto_Ren_No;
    private String NgayGD;
    private String NgayGT;
    private BigDecimal SoTien_GD;
    private String NoiDung;
    private String Act_CD;
    private String Txn_Type;
//</editor-fold>
    
//<editor-fold defaultstate="collapsed" desc="get/set">
    
    public String getPosCode() {
        return PosCode;
    }
    
    public void setPosCode(String PosCode) {
        this.PosCode = PosCode;
    }
    
    public String getMaKH() {
        return MaKH;
    }
    
    public void setMaKH(String MaKH) {
        this.MaKH = MaKH;
    }
    
    public String getTenKH() {
        return TenKH;
    }
    
    public void setTenKH(String TenKH) {
        this.TenKH = TenKH;
    }
    
    public String getDiaChi() {
        return DiaChi;
    }
    
    public void setDiaChi(String DiaChi) {
        this.DiaChi = DiaChi;
    }
    
    public String getSo_TChieu() {
        return So_TChieu;
    }
    
    public void setSo_TChieu(String So_TChieu) {
        this.So_TChieu = So_TChieu;
    }
    
    public String getMaSP() {
        return MaSP;
    }
    
    public void setMaSP(String MaSP) {
        this.MaSP = MaSP;
    }
    
    public String getTenSP() {
        return TenSP;
    }
    
    public void setTenSP(String TenSP) {
        this.TenSP = TenSP;
    }
    
    public String getTienTe() {
        return TienTe;
    }
    
    public void setTienTe(String TienTe) {
        this.TienTe = TienTe;
    }
    
    public String getGL_TK() {
        return GL_TK;
    }
    
    public void setGL_TK(String GL_TK) {
        this.GL_TK = GL_TK;
    }
    
    public String getSoTK() {
        return SoTK;
    }
    
    public void setSoTK(String SoTK) {
        this.SoTK = SoTK;
    }
    
    public String getNgay_Gui() {
        return Ngay_Gui;
    }
    
    public void setNgay_Gui(String Ngay_Gui) {
        this.Ngay_Gui = Ngay_Gui;
    }
    
    public String getNgay_DH() {
        return Ngay_DH;
    }
    
    public void setNgay_DH(String Ngay_DH) {
        this.Ngay_DH = Ngay_DH;
    }
    
    public int getKyHan() {
        return KyHan;
    }
    
    public void setKyHan(int KyHan) {
        this.KyHan = KyHan;
    }
    
    public String getKyHan_DV() {
        return KyHan_DV;
    }
    
    public void setKyHan_DV(String KyHan_DV) {
        this.KyHan_DV = KyHan_DV;
    }
    
    public BigDecimal getSoDu_HT() {
        return SoDu_HT;
    }
    
    public void setSoDu_HT(BigDecimal SoDu_HT) {
        this.SoDu_HT = SoDu_HT;
    }
    
    public BigDecimal getSoDu_HT_DenHan() {
        return SoDu_HT_DenHan;
    }
    
    public void setSoDu_HT_DenHan(BigDecimal SoDu_HT_DenHan) {
        this.SoDu_HT_DenHan = SoDu_HT_DenHan;
    }
    
    public BigDecimal getSoDu_BanDau() {
        return SoDu_BanDau;
    }
    
    public void setSoDu_BanDau(BigDecimal SoDu_BanDau) {
        this.SoDu_BanDau = SoDu_BanDau;
    }
    
    public BigDecimal getSoDu_BanDau_DenHan() {
        return SoDu_BanDau_DenHan;
    }
    
    public void setSoDu_BanDau_DenHan(BigDecimal SoDu_BanDau_DenHan) {
        this.SoDu_BanDau_DenHan = SoDu_BanDau_DenHan;
    }
    
    public BigDecimal getLaiSuat() {
        return LaiSuat;
    }
    
    public void setLaiSuat(BigDecimal LaiSuat) {
        this.LaiSuat = LaiSuat;
    }
    
    public BigDecimal getLaiDuThu() {
        return LaiDuThu;
    }
    
    public void setLaiDuThu(BigDecimal LaiDuThu) {
        this.LaiDuThu = LaiDuThu;
    }
    
    public BigDecimal getLaiDaTra() {
        return LaiDaTra;
    }
    
    public void setLaiDaTra(BigDecimal LaiDaTra) {
        this.LaiDaTra = LaiDaTra;
    }
    
    public BigDecimal getSt_PhongToa() {
        return St_PhongToa;
    }
    
    public void setSt_PhongToa(BigDecimal St_PhongToa) {
        this.St_PhongToa = St_PhongToa;
    }
    
    public String getTrangThai() {
        return TrangThai;
    }
    
    public void setTrangThai(String TrangThai) {
        this.TrangThai = TrangThai;
    }
    
    public String getTT_SoHuu() {
        return TT_SoHuu;
    }
    
    public void setTT_SoHuu(String TT_SoHuu) {
        this.TT_SoHuu = TT_SoHuu;
    }
    
    public String getNgayDong_TK() {
        return NgayDong_TK;
    }
    
    public void setNgayDong_TK(String NgayDong_TK) {
        this.NgayDong_TK = NgayDong_TK;
    }
    
    public String getMaPGD() {
        return MaPGD;
    }
    
    public void setMaPGD(String MaPGD) {
        this.MaPGD = MaPGD;
    }
    
    public String getTenPGD() {
        return TenPGD;
    }
    
    public void setTenPGD(String TenPGD) {
        this.TenPGD = TenPGD;
    }
    
    public String getDanhXung() {
        return DanhXung;
    }
    
    public void setDanhXung(String DanhXung) {
        this.DanhXung = DanhXung;
    }
    
    public String getNgaySinh() {
        return NgaySinh;
    }
    
    public void setNgaySinh(String NgaySinh) {
        this.NgaySinh = NgaySinh;
    }
    
    public String getSoCMT() {
        return SoCMT;
    }
    
    public void setSoCMT(String SoCMT) {
        this.SoCMT = SoCMT;
    }
    
    public String getEod_Date() {
        return Eod_Date;
    }
    
    public void setEod_Date(String Eod_Date) {
        this.Eod_Date = Eod_Date;
    }
    
    public String getBr_CD() {
        return Br_CD;
    }
    
    public void setBr_CD(String Br_CD) {
        this.Br_CD = Br_CD;
    }
    
    public String getBase_No() {
        return Base_No;
    }
    
    public void setBase_No(String Base_No) {
        this.Base_No = Base_No;
    }
    
    public String getDep_Ser_No() {
        return Dep_Ser_No;
    }
    
    public void setDep_Ser_No(String Dep_Ser_No) {
        this.Dep_Ser_No = Dep_Ser_No;
    }
    
    public String getAuto_Ren_No() {
        return Auto_Ren_No;
    }
    
    public void setAuto_Ren_No(String Auto_Ren_No) {
        this.Auto_Ren_No = Auto_Ren_No;
    }
    
    public String getNgayGD() {
        return NgayGD;
    }
    
    public void setNgayGD(String NgayGD) {
        this.NgayGD = NgayGD;
    }
    
    public String getNgayGT() {
        return NgayGT;
    }
    
    public void setNgayGT(String NgayGT) {
        this.NgayGT = NgayGT;
    }
    
    public BigDecimal getSoTien_GD() {
        return SoTien_GD;
    }
    
    public void setSoTien_GD(BigDecimal SoTien_GD) {
        this.SoTien_GD = SoTien_GD;
    }
    
    public String getNoiDung() {
        return NoiDung;
    }
    
    public void setNoiDung(String NoiDung) {
        this.NoiDung = NoiDung;
    }
    
    public String getAct_CD() {
        return Act_CD;
    }
    
    public void setAct_CD(String Act_CD) {
        this.Act_CD = Act_CD;
    }
    
    public String getTxn_Type() {
        return Txn_Type;
    }
    
    public void setTxn_Type(String Txn_Type) {
        this.Txn_Type = Txn_Type;
    }
//</editor-fold>
}
