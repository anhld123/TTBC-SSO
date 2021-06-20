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
public class Loan {
    //<editor-fold defaultstate="collapsed" desc="variable">
    
    private String LoaiBG;
    private String PosCode;
    private String CustomerId;
    private String LoanId;
    private String MaPGD;
    private String TenPGD;
    private String MaTo;
    private String TenTT;
    private String DVUT;
    private String DVUT_Ten;
    private String SoKU;
    private String MaKH;
    private String DanhXung;
    private String TenKH;
    private String NgaySinh;
    private String Kh_Mobile;
    private String SoCMT;
    private String DiaChi;
    private String MaSP;
    private String TenSP;
    private String NgayVay;
    private String Ngay_DH_HD;
    private String Ngay_DH_GH;
    private String Ngay_DH_GDX;
    private String TTMonVay;
    private BigDecimal GiaiNgan;
    private BigDecimal DNTH;
    private BigDecimal DNQH;
    private BigDecimal DNKH;
    private BigDecimal TongDN;
    private BigDecimal ST_PhongToa;
    public int KyHan;
    private String KyHan_DV;
    private BigDecimal LaiSuat;
    private BigDecimal Lai_DT;
    private BigDecimal Lai_TT;
    private String TienTe;
    private BigDecimal LaiTon_TH;
    private BigDecimal LaiTon_QH;
    private String Sv_MaThe;
    private String Sv_HoTen;
    private String Sv_NgaySinh;
    private String Sv_GioiTinh;
    private String Sv_CMT;
    private String Sv_NgayCap;
    private String Sv_NoiCap;
    private String Sv_MaQH;
    private String Sv_QuanHe;
    private String Sv_MaTruong;
    private String Sv_TenTruong;
    private String Sv_MaLHDT;
    private String Sv_TenLHDT;
    private String Sv_MaLHCS;
    private String Sv_TenLHCS;
    private String Sv_MaHDT;
    private String Sv_TenHDT;
    private String Sv_MaNDT;
    private String Sv_tenNDT;
    private String Sv_LopHoc;
    private String Sv_KhoaHoc;
    private String Sv_Khoa;
    private String Sv_MaDTHPhi;
    private String Sv_TenDTHPhi;
    private String Sv_MaDTSV;
    private String Sv_TenDTSV;
    private String Sv_MaTTSV;
    private String Sv_TenTTSV;
    private String Sv_NgayNH;
    private String Sv_NgayRT;
    private String Sv_ATM_So;
    private String Sv_ATM_NoiCap;
    private String Sv_Ngay_NNgu;
    private String Sv_Ngay_XNgu;
    private String Sv_Ngay_BD_MGLai;
    private String Sv_ngay_KT_MGLai;
    private String Sv_NgayKTAH;
    private String Xk_SoTC;
    private String Xk_HoTen;
    private String Xk_NgaySinh;
    private String Xk_GioiTinh;
    private String Xk_CMT;
    private String Xk_NgayCap;
    private String Xk_NoiCap;
    private String Xk_MaQH;
    private String Xk_QuanHe;
    private String Xk_MaQG;
    private String Xk_TenQG;
    private String Xk_Ctylviec;
    private String Xk_Ctylviec_Diachi;
    private String Xk_Ctyduadixk;
    private String Xk_Ctyduadixk_Diachi;
    private String Xk_HD_So;
    private String Xk_HD_Ngky;
    private String Xk_HD_Ngkt;
    private String Xk_TgGH;
    private BigDecimal Xk_ThuNhap;
    private String Xk_Lvld;
    private String Xk_Tinhtrang;
    private String Vl_MaDA;
    private String Vl_LoaiDa;
    private String Vl_TenDa;
    private String Vl_Sold;
    private String Vl_Sold_Moi;
    private String Vl_GhiChu;
    private String Vl_TTrangDA;
    private String Udf_TenPnkt51;
    private String Udf_TenPnkt52;
    private String Udf_TenPnkt53;
    private String Udf_TenPnkt54;
    private String Udf_TenPnkt55;
    private String Udf_TenPnkt56;
    private String Udf_TenHqdt1;
    private String Udf_TenHqdt2;
    private String Udf_TenHqdt3;
    private String Udf_TenHqdt4;
    private String Udf_TenHqdt5;
    private String Udf_TenHqdt6;
    private BigDecimal Udf_GiaTri_Hqdt1;
    private BigDecimal Udf_giatri_hqdt2;
    private BigDecimal Udf_giatri_hqdt3;
    private BigDecimal Udf_giatri_hqdt4;
    private BigDecimal Udf_giatri_hqdt5;
    private BigDecimal Udf_giatri_hqdt6;
    public int Udf_SL_Hqdt1;
    private String Udf_SL_Hqdt2;
    private String Udf_SL_Hqdt3;
    private String Udf_SL_Hqdt4;
    private String Udf_SL_Hqdt5;
    private String Udf_SL_Hqdt6;
    private String Udf_Mdnha_Ten;
    private String Udf_Md30a_Ten;
    private String Udf_MaDA;
    private String Udf_Nguonvon_BS_Ten;
    private String Udf_Soldlapn;
    private String Udf_Soldlankt;
    private String Udf_Soldlants;
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="get/set">
    public String getLoaiBG() {
        return LoaiBG;
    }
    
    public void setLoaiBG(String LoaiBG) {
        this.LoaiBG = LoaiBG;
    }
    
    public String getPosCode() {
        return PosCode;
    }
    
    public void setPosCode(String PosCode) {
        this.PosCode = PosCode;
    }
    
    public String getCustomerId() {
        return CustomerId;
    }
    
    public void setCustomerId(String CustomerId) {
        this.CustomerId = CustomerId;
    }
    
    public String getLoanId() {
        return LoanId;
    }
    
    public void setLoanId(String LoanId) {
        this.LoanId = LoanId;
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
    
    public String getMaTo() {
        return MaTo;
    }
    
    public void setMaTo(String MaTo) {
        this.MaTo = MaTo;
    }
    
    public String getTenTT() {
        return TenTT;
    }
    
    public void setTenTT(String TenTT) {
        this.TenTT = TenTT;
    }
    
    public String getDVUT() {
        return DVUT;
    }
    
    public void setDVUT(String DVUT) {
        this.DVUT = DVUT;
    }
    
    public String getDVUT_Ten() {
        return DVUT_Ten;
    }
    
    public void setDVUT_Ten(String DVUT_Ten) {
        this.DVUT_Ten = DVUT_Ten;
    }
    
    public String getSoKU() {
        return SoKU;
    }
    
    public void setSoKU(String SoKU) {
        this.SoKU = SoKU;
    }
    
    public String getMaKH() {
        return MaKH;
    }
    
    public void setMaKH(String MaKH) {
        this.MaKH = MaKH;
    }
    
    public String getDanhXung() {
        return DanhXung;
    }
    
    public void setDanhXung(String DanhXung) {
        this.DanhXung = DanhXung;
    }
    
    public String getTenKH() {
        return TenKH;
    }
    
    public void setTenKH(String TenKH) {
        this.TenKH = TenKH;
    }
    
    public String getNgaySinh() {
        return NgaySinh;
    }
    
    public void setNgaySinh(String NgaySinh) {
        this.NgaySinh = NgaySinh;
    }
    
    public String getKh_Mobile() {
        return Kh_Mobile;
    }
    
    public void setKh_Mobile(String Kh_Mobile) {
        this.Kh_Mobile = Kh_Mobile;
    }
    
    public String getSoCMT() {
        return SoCMT;
    }
    
    public void setSoCMT(String SoCMT) {
        this.SoCMT = SoCMT;
    }
    
    public String getDiaChi() {
        return DiaChi;
    }
    
    public void setDiaChi(String DiaChi) {
        this.DiaChi = DiaChi;
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
    
    public String getNgayVay() {
        return NgayVay;
    }
    
    public void setNgayVay(String NgayVay) {
        this.NgayVay = NgayVay;
    }
    
    public String getNgay_DH_HD() {
        return Ngay_DH_HD;
    }
    
    public void setNgay_DH_HD(String Ngay_DH_HD) {
        this.Ngay_DH_HD = Ngay_DH_HD;
    }
    
    public String getNgay_DH_GH() {
        return Ngay_DH_GH;
    }
    
    public void setNgay_DH_GH(String Ngay_DH_GH) {
        this.Ngay_DH_GH = Ngay_DH_GH;
    }
    
    public String getNgay_DH_GDX() {
        return Ngay_DH_GDX;
    }
    
    public void setNgay_DH_GDX(String Ngay_DH_GDX) {
        this.Ngay_DH_GDX = Ngay_DH_GDX;
    }
    
    public String getTTMonVay() {
        return TTMonVay;
    }
    
    public void setTTMonVay(String TTMonVay) {
        this.TTMonVay = TTMonVay;
    }
    
    public BigDecimal getGiaiNgan() {
        return GiaiNgan;
    }
    
    public void setGiaiNgan(BigDecimal GiaiNgan) {
        this.GiaiNgan = GiaiNgan;
    }
    
    public BigDecimal getDNTH() {
        return DNTH;
    }
    
    public void setDNTH(BigDecimal DNTH) {
        this.DNTH = DNTH;
    }
    
    public BigDecimal getDNQH() {
        return DNQH;
    }
    
    public void setDNQH(BigDecimal DNQH) {
        this.DNQH = DNQH;
    }
    
    public BigDecimal getDNKH() {
        return DNKH;
    }
    
    public void setDNKH(BigDecimal DNKH) {
        this.DNKH = DNKH;
    }
    
    public BigDecimal getTongDN() {
        return TongDN;
    }
    
    public void setTongDN(BigDecimal TongDN) {
        this.TongDN = TongDN;
    }
    
    public BigDecimal getST_PhongToa() {
        return ST_PhongToa;
    }
    
    public void setST_PhongToa(BigDecimal ST_PhongToa) {
        this.ST_PhongToa = ST_PhongToa;
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
    
    public BigDecimal getLaiSuat() {
        return LaiSuat;
    }
    
    public void setLaiSuat(BigDecimal LaiSuat) {
        this.LaiSuat = LaiSuat;
    }
    
    public BigDecimal getLai_DT() {
        return Lai_DT;
    }
    
    public void setLai_DT(BigDecimal Lai_DT) {
        this.Lai_DT = Lai_DT;
    }
    
    public BigDecimal getLai_TT() {
        return Lai_TT;
    }
    
    public void setLai_TT(BigDecimal Lai_TT) {
        this.Lai_TT = Lai_TT;
    }
    
    public String getTienTe() {
        return TienTe;
    }
    
    public void setTienTe(String TienTe) {
        this.TienTe = TienTe;
    }
    
    public BigDecimal getLaiTon_TH() {
        return LaiTon_TH;
    }
    
    public void setLaiTon_TH(BigDecimal LaiTon_TH) {
        this.LaiTon_TH = LaiTon_TH;
    }
    
    public BigDecimal getLaiTon_QH() {
        return LaiTon_QH;
    }
    
    public void setLaiTon_QH(BigDecimal LaiTon_QH) {
        this.LaiTon_QH = LaiTon_QH;
    }
    
    public String getSv_MaThe() {
        return Sv_MaThe;
    }
    
    public void setSv_MaThe(String Sv_MaThe) {
        this.Sv_MaThe = Sv_MaThe;
    }
    
    public String getSv_HoTen() {
        return Sv_HoTen;
    }
    
    public void setSv_HoTen(String Sv_HoTen) {
        this.Sv_HoTen = Sv_HoTen;
    }
    
    public String getSv_NgaySinh() {
        return Sv_NgaySinh;
    }
    
    public void setSv_NgaySinh(String Sv_NgaySinh) {
        this.Sv_NgaySinh = Sv_NgaySinh;
    }
    
    public String getSv_GioiTinh() {
        return Sv_GioiTinh;
    }
    
    public void setSv_GioiTinh(String Sv_GioiTinh) {
        this.Sv_GioiTinh = Sv_GioiTinh;
    }
    
    public String getSv_CMT() {
        return Sv_CMT;
    }
    
    public void setSv_CMT(String Sv_CMT) {
        this.Sv_CMT = Sv_CMT;
    }
    
    public String getSv_NgayCap() {
        return Sv_NgayCap;
    }
    
    public void setSv_NgayCap(String Sv_NgayCap) {
        this.Sv_NgayCap = Sv_NgayCap;
    }
    
    public String getSv_NoiCap() {
        return Sv_NoiCap;
    }
    
    public void setSv_NoiCap(String Sv_NoiCap) {
        this.Sv_NoiCap = Sv_NoiCap;
    }
    
    public String getSv_MaQH() {
        return Sv_MaQH;
    }
    
    public void setSv_MaQH(String Sv_MaQH) {
        this.Sv_MaQH = Sv_MaQH;
    }
    
    public String getSv_QuanHe() {
        return Sv_QuanHe;
    }
    
    public void setSv_QuanHe(String Sv_QuanHe) {
        this.Sv_QuanHe = Sv_QuanHe;
    }
    
    public String getSv_MaTruong() {
        return Sv_MaTruong;
    }
    
    public void setSv_MaTruong(String Sv_MaTruong) {
        this.Sv_MaTruong = Sv_MaTruong;
    }
    
    public String getSv_TenTruong() {
        return Sv_TenTruong;
    }
    
    public void setSv_TenTruong(String Sv_TenTruong) {
        this.Sv_TenTruong = Sv_TenTruong;
    }
    
    public String getSv_MaLHDT() {
        return Sv_MaLHDT;
    }
    
    public void setSv_MaLHDT(String Sv_MaLHDT) {
        this.Sv_MaLHDT = Sv_MaLHDT;
    }
    
    public String getSv_TenLHDT() {
        return Sv_TenLHDT;
    }
    
    public void setSv_TenLHDT(String Sv_TenLHDT) {
        this.Sv_TenLHDT = Sv_TenLHDT;
    }
    
    public String getSv_MaLHCS() {
        return Sv_MaLHCS;
    }
    
    public void setSv_MaLHCS(String Sv_MaLHCS) {
        this.Sv_MaLHCS = Sv_MaLHCS;
    }
    
    public String getSv_TenLHCS() {
        return Sv_TenLHCS;
    }
    
    public void setSv_TenLHCS(String Sv_TenLHCS) {
        this.Sv_TenLHCS = Sv_TenLHCS;
    }
    
    public String getSv_MaHDT() {
        return Sv_MaHDT;
    }
    
    public void setSv_MaHDT(String Sv_MaHDT) {
        this.Sv_MaHDT = Sv_MaHDT;
    }
    
    public String getSv_TenHDT() {
        return Sv_TenHDT;
    }
    
    public void setSv_TenHDT(String Sv_TenHDT) {
        this.Sv_TenHDT = Sv_TenHDT;
    }
    
    public String getSv_MaNDT() {
        return Sv_MaNDT;
    }
    
    public void setSv_MaNDT(String Sv_MaNDT) {
        this.Sv_MaNDT = Sv_MaNDT;
    }
    
    public String getSv_tenNDT() {
        return Sv_tenNDT;
    }
    
    public void setSv_tenNDT(String Sv_tenNDT) {
        this.Sv_tenNDT = Sv_tenNDT;
    }
    
    public String getSv_LopHoc() {
        return Sv_LopHoc;
    }
    
    public void setSv_LopHoc(String Sv_LopHoc) {
        this.Sv_LopHoc = Sv_LopHoc;
    }
    
    public String getSv_KhoaHoc() {
        return Sv_KhoaHoc;
    }
    
    public void setSv_KhoaHoc(String Sv_KhoaHoc) {
        this.Sv_KhoaHoc = Sv_KhoaHoc;
    }
    
    public String getSv_Khoa() {
        return Sv_Khoa;
    }
    
    public void setSv_Khoa(String Sv_Khoa) {
        this.Sv_Khoa = Sv_Khoa;
    }
    
    public String getSv_MaDTHPhi() {
        return Sv_MaDTHPhi;
    }
    
    public void setSv_MaDTHPhi(String Sv_MaDTHPhi) {
        this.Sv_MaDTHPhi = Sv_MaDTHPhi;
    }
    
    public String getSv_TenDTHPhi() {
        return Sv_TenDTHPhi;
    }
    
    public void setSv_TenDTHPhi(String Sv_TenDTHPhi) {
        this.Sv_TenDTHPhi = Sv_TenDTHPhi;
    }
    
    public String getSv_MaDTSV() {
        return Sv_MaDTSV;
    }
    
    public void setSv_MaDTSV(String Sv_MaDTSV) {
        this.Sv_MaDTSV = Sv_MaDTSV;
    }
    
    public String getSv_TenDTSV() {
        return Sv_TenDTSV;
    }
    
    public void setSv_TenDTSV(String Sv_TenDTSV) {
        this.Sv_TenDTSV = Sv_TenDTSV;
    }
    
    public String getSv_MaTTSV() {
        return Sv_MaTTSV;
    }
    
    public void setSv_MaTTSV(String Sv_MaTTSV) {
        this.Sv_MaTTSV = Sv_MaTTSV;
    }
    
    public String getSv_TenTTSV() {
        return Sv_TenTTSV;
    }
    
    public void setSv_TenTTSV(String Sv_TenTTSV) {
        this.Sv_TenTTSV = Sv_TenTTSV;
    }
    
    public String getSv_NgayNH() {
        return Sv_NgayNH;
    }
    
    public void setSv_NgayNH(String Sv_NgayNH) {
        this.Sv_NgayNH = Sv_NgayNH;
    }
    
    public String getSv_NgayRT() {
        return Sv_NgayRT;
    }
    
    public void setSv_NgayRT(String Sv_NgayRT) {
        this.Sv_NgayRT = Sv_NgayRT;
    }
    
    public String getSv_ATM_So() {
        return Sv_ATM_So;
    }
    
    public void setSv_ATM_So(String Sv_ATM_So) {
        this.Sv_ATM_So = Sv_ATM_So;
    }
    
    public String getSv_ATM_NoiCap() {
        return Sv_ATM_NoiCap;
    }
    
    public void setSv_ATM_NoiCap(String Sv_ATM_NoiCap) {
        this.Sv_ATM_NoiCap = Sv_ATM_NoiCap;
    }
    
    public String getSv_Ngay_NNgu() {
        return Sv_Ngay_NNgu;
    }
    
    public void setSv_Ngay_NNgu(String Sv_Ngay_NNgu) {
        this.Sv_Ngay_NNgu = Sv_Ngay_NNgu;
    }
    
    public String getSv_Ngay_XNgu() {
        return Sv_Ngay_XNgu;
    }
    
    public void setSv_Ngay_XNgu(String Sv_Ngay_XNgu) {
        this.Sv_Ngay_XNgu = Sv_Ngay_XNgu;
    }
    
    public String getSv_Ngay_BD_MGLai() {
        return Sv_Ngay_BD_MGLai;
    }
    
    public void setSv_Ngay_BD_MGLai(String Sv_Ngay_BD_MGLai) {
        this.Sv_Ngay_BD_MGLai = Sv_Ngay_BD_MGLai;
    }
    
    public String getSv_ngay_KT_MGLai() {
        return Sv_ngay_KT_MGLai;
    }
    
    public void setSv_ngay_KT_MGLai(String Sv_ngay_KT_MGLai) {
        this.Sv_ngay_KT_MGLai = Sv_ngay_KT_MGLai;
    }
    
    public String getSv_NgayKTAH() {
        return Sv_NgayKTAH;
    }
    
    public void setSv_NgayKTAH(String Sv_NgayKTAH) {
        this.Sv_NgayKTAH = Sv_NgayKTAH;
    }
    
    public String getXk_SoTC() {
        return Xk_SoTC;
    }
    
    public void setXk_SoTC(String Xk_SoTC) {
        this.Xk_SoTC = Xk_SoTC;
    }
    
    public String getXk_HoTen() {
        return Xk_HoTen;
    }
    
    public void setXk_HoTen(String Xk_HoTen) {
        this.Xk_HoTen = Xk_HoTen;
    }
    
    public String getXk_NgaySinh() {
        return Xk_NgaySinh;
    }
    
    public void setXk_NgaySinh(String Xk_NgaySinh) {
        this.Xk_NgaySinh = Xk_NgaySinh;
    }
    
    public String getXk_GioiTinh() {
        return Xk_GioiTinh;
    }
    
    public void setXk_GioiTinh(String Xk_GioiTinh) {
        this.Xk_GioiTinh = Xk_GioiTinh;
    }
    
    public String getXk_CMT() {
        return Xk_CMT;
    }
    
    public void setXk_CMT(String Xk_CMT) {
        this.Xk_CMT = Xk_CMT;
    }
    
    public String getXk_NgayCap() {
        return Xk_NgayCap;
    }
    
    public void setXk_NgayCap(String Xk_NgayCap) {
        this.Xk_NgayCap = Xk_NgayCap;
    }
    
    public String getXk_NoiCap() {
        return Xk_NoiCap;
    }
    
    public void setXk_NoiCap(String Xk_NoiCap) {
        this.Xk_NoiCap = Xk_NoiCap;
    }
    
    public String getXk_MaQH() {
        return Xk_MaQH;
    }
    
    public void setXk_MaQH(String Xk_MaQH) {
        this.Xk_MaQH = Xk_MaQH;
    }
    
    public String getXk_QuanHe() {
        return Xk_QuanHe;
    }
    
    public void setXk_QuanHe(String Xk_QuanHe) {
        this.Xk_QuanHe = Xk_QuanHe;
    }
    
    public String getXk_MaQG() {
        return Xk_MaQG;
    }
    
    public void setXk_MaQG(String Xk_MaQG) {
        this.Xk_MaQG = Xk_MaQG;
    }
    
    public String getXk_TenQG() {
        return Xk_TenQG;
    }
    
    public void setXk_TenQG(String Xk_TenQG) {
        this.Xk_TenQG = Xk_TenQG;
    }
    
    public String getXk_Ctylviec() {
        return Xk_Ctylviec;
    }
    
    public void setXk_Ctylviec(String Xk_Ctylviec) {
        this.Xk_Ctylviec = Xk_Ctylviec;
    }
    
    public String getXk_Ctylviec_Diachi() {
        return Xk_Ctylviec_Diachi;
    }
    
    public void setXk_Ctylviec_Diachi(String Xk_Ctylviec_Diachi) {
        this.Xk_Ctylviec_Diachi = Xk_Ctylviec_Diachi;
    }
    
    public String getXk_Ctyduadixk() {
        return Xk_Ctyduadixk;
    }
    
    public void setXk_Ctyduadixk(String Xk_Ctyduadixk) {
        this.Xk_Ctyduadixk = Xk_Ctyduadixk;
    }
    
    public String getXk_Ctyduadixk_Diachi() {
        return Xk_Ctyduadixk_Diachi;
    }
    
    public void setXk_Ctyduadixk_Diachi(String Xk_Ctyduadixk_Diachi) {
        this.Xk_Ctyduadixk_Diachi = Xk_Ctyduadixk_Diachi;
    }
    
    public String getXk_HD_So() {
        return Xk_HD_So;
    }
    
    public void setXk_HD_So(String Xk_HD_So) {
        this.Xk_HD_So = Xk_HD_So;
    }
    
    public String getXk_HD_Ngky() {
        return Xk_HD_Ngky;
    }
    
    public void setXk_HD_Ngky(String Xk_HD_Ngky) {
        this.Xk_HD_Ngky = Xk_HD_Ngky;
    }
    
    public String getXk_HD_Ngkt() {
        return Xk_HD_Ngkt;
    }
    
    public void setXk_HD_Ngkt(String Xk_HD_Ngkt) {
        this.Xk_HD_Ngkt = Xk_HD_Ngkt;
    }
    
    public String getXk_TgGH() {
        return Xk_TgGH;
    }
    
    public void setXk_TgGH(String Xk_TgGH) {
        this.Xk_TgGH = Xk_TgGH;
    }
    
    public BigDecimal getXk_ThuNhap() {
        return Xk_ThuNhap;
    }
    
    public void setXk_ThuNhap(BigDecimal Xk_ThuNhap) {
        this.Xk_ThuNhap = Xk_ThuNhap;
    }
    
    public String getXk_Lvld() {
        return Xk_Lvld;
    }
    
    public void setXk_Lvld(String Xk_Lvld) {
        this.Xk_Lvld = Xk_Lvld;
    }
    
    public String getXk_Tinhtrang() {
        return Xk_Tinhtrang;
    }
    
    public void setXk_Tinhtrang(String Xk_Tinhtrang) {
        this.Xk_Tinhtrang = Xk_Tinhtrang;
    }
    
    public String getVl_MaDA() {
        return Vl_MaDA;
    }
    
    public void setVl_MaDA(String Vl_MaDA) {
        this.Vl_MaDA = Vl_MaDA;
    }
    
    public String getVl_LoaiDa() {
        return Vl_LoaiDa;
    }
    
    public void setVl_LoaiDa(String Vl_LoaiDa) {
        this.Vl_LoaiDa = Vl_LoaiDa;
    }
    
    public String getVl_TenDa() {
        return Vl_TenDa;
    }
    
    public void setVl_TenDa(String Vl_TenDa) {
        this.Vl_TenDa = Vl_TenDa;
    }
    
    public String getVl_Sold() {
        return Vl_Sold;
    }
    
    public void setVl_Sold(String Vl_Sold) {
        this.Vl_Sold = Vl_Sold;
    }
    
    public String getVl_Sold_Moi() {
        return Vl_Sold_Moi;
    }
    
    public void setVl_Sold_Moi(String Vl_Sold_Moi) {
        this.Vl_Sold_Moi = Vl_Sold_Moi;
    }
    
    public String getVl_GhiChu() {
        return Vl_GhiChu;
    }
    
    public void setVl_GhiChu(String Vl_GhiChu) {
        this.Vl_GhiChu = Vl_GhiChu;
    }
    
    public String getVl_TTrangDA() {
        return Vl_TTrangDA;
    }
    
    public void setVl_TTrangDA(String Vl_TTrangDA) {
        this.Vl_TTrangDA = Vl_TTrangDA;
    }
    
    public String getUdf_TenPnkt51() {
        return Udf_TenPnkt51;
    }
    
    public void setUdf_TenPnkt51(String Udf_TenPnkt51) {
        this.Udf_TenPnkt51 = Udf_TenPnkt51;
    }
    
    public String getUdf_TenPnkt52() {
        return Udf_TenPnkt52;
    }
    
    public void setUdf_TenPnkt52(String Udf_TenPnkt52) {
        this.Udf_TenPnkt52 = Udf_TenPnkt52;
    }
    
    public String getUdf_TenPnkt53() {
        return Udf_TenPnkt53;
    }
    
    public void setUdf_TenPnkt53(String Udf_TenPnkt53) {
        this.Udf_TenPnkt53 = Udf_TenPnkt53;
    }
    
    public String getUdf_TenPnkt54() {
        return Udf_TenPnkt54;
    }
    
    public void setUdf_TenPnkt54(String Udf_TenPnkt54) {
        this.Udf_TenPnkt54 = Udf_TenPnkt54;
    }
    
    public String getUdf_TenPnkt55() {
        return Udf_TenPnkt55;
    }
    
    public void setUdf_TenPnkt55(String Udf_TenPnkt55) {
        this.Udf_TenPnkt55 = Udf_TenPnkt55;
    }
    
    public String getUdf_TenPnkt56() {
        return Udf_TenPnkt56;
    }
    
    public void setUdf_TenPnkt56(String Udf_TenPnkt56) {
        this.Udf_TenPnkt56 = Udf_TenPnkt56;
    }
    
    public String getUdf_TenHqdt1() {
        return Udf_TenHqdt1;
    }
    
    public void setUdf_TenHqdt1(String Udf_TenHqdt1) {
        this.Udf_TenHqdt1 = Udf_TenHqdt1;
    }
    
    public String getUdf_TenHqdt2() {
        return Udf_TenHqdt2;
    }
    
    public void setUdf_TenHqdt2(String Udf_TenHqdt2) {
        this.Udf_TenHqdt2 = Udf_TenHqdt2;
    }
    
    public String getUdf_TenHqdt3() {
        return Udf_TenHqdt3;
    }
    
    public void setUdf_TenHqdt3(String Udf_TenHqdt3) {
        this.Udf_TenHqdt3 = Udf_TenHqdt3;
    }
    
    public String getUdf_TenHqdt4() {
        return Udf_TenHqdt4;
    }
    
    public void setUdf_TenHqdt4(String Udf_TenHqdt4) {
        this.Udf_TenHqdt4 = Udf_TenHqdt4;
    }
    
    public String getUdf_TenHqdt5() {
        return Udf_TenHqdt5;
    }
    
    public void setUdf_TenHqdt5(String Udf_TenHqdt5) {
        this.Udf_TenHqdt5 = Udf_TenHqdt5;
    }
    
    public String getUdf_TenHqdt6() {
        return Udf_TenHqdt6;
    }
    
    public void setUdf_TenHqdt6(String Udf_TenHqdt6) {
        this.Udf_TenHqdt6 = Udf_TenHqdt6;
    }
    
    public BigDecimal getUdf_GiaTri_Hqdt1() {
        return Udf_GiaTri_Hqdt1;
    }
    
    public void setUdf_GiaTri_Hqdt1(BigDecimal Udf_GiaTri_Hqdt1) {
        this.Udf_GiaTri_Hqdt1 = Udf_GiaTri_Hqdt1;
    }
    
    public BigDecimal getUdf_giatri_hqdt2() {
        return Udf_giatri_hqdt2;
    }
    
    public void setUdf_giatri_hqdt2(BigDecimal Udf_giatri_hqdt2) {
        this.Udf_giatri_hqdt2 = Udf_giatri_hqdt2;
    }
    
    public BigDecimal getUdf_giatri_hqdt3() {
        return Udf_giatri_hqdt3;
    }
    
    public void setUdf_giatri_hqdt3(BigDecimal Udf_giatri_hqdt3) {
        this.Udf_giatri_hqdt3 = Udf_giatri_hqdt3;
    }
    
    public BigDecimal getUdf_giatri_hqdt4() {
        return Udf_giatri_hqdt4;
    }
    
    public void setUdf_giatri_hqdt4(BigDecimal Udf_giatri_hqdt4) {
        this.Udf_giatri_hqdt4 = Udf_giatri_hqdt4;
    }
    
    public BigDecimal getUdf_giatri_hqdt5() {
        return Udf_giatri_hqdt5;
    }
    
    public void setUdf_giatri_hqdt5(BigDecimal Udf_giatri_hqdt5) {
        this.Udf_giatri_hqdt5 = Udf_giatri_hqdt5;
    }
    
    public BigDecimal getUdf_giatri_hqdt6() {
        return Udf_giatri_hqdt6;
    }
    
    public void setUdf_giatri_hqdt6(BigDecimal Udf_giatri_hqdt6) {
        this.Udf_giatri_hqdt6 = Udf_giatri_hqdt6;
    }
    
    public int getUdf_SL_Hqdt1() {
        return Udf_SL_Hqdt1;
    }
    
    public void setUdf_SL_Hqdt1(int Udf_SL_Hqdt1) {
        this.Udf_SL_Hqdt1 = Udf_SL_Hqdt1;
    }
    
    public String getUdf_SL_Hqdt2() {
        return Udf_SL_Hqdt2;
    }
    
    public void setUdf_SL_Hqdt2(String Udf_SL_Hqdt2) {
        this.Udf_SL_Hqdt2 = Udf_SL_Hqdt2;
    }
    
    public String getUdf_SL_Hqdt3() {
        return Udf_SL_Hqdt3;
    }
    
    public void setUdf_SL_Hqdt3(String Udf_SL_Hqdt3) {
        this.Udf_SL_Hqdt3 = Udf_SL_Hqdt3;
    }
    
    public String getUdf_SL_Hqdt4() {
        return Udf_SL_Hqdt4;
    }
    
    public void setUdf_SL_Hqdt4(String Udf_SL_Hqdt4) {
        this.Udf_SL_Hqdt4 = Udf_SL_Hqdt4;
    }
    
    public String getUdf_SL_Hqdt5() {
        return Udf_SL_Hqdt5;
    }
    
    public void setUdf_SL_Hqdt5(String Udf_SL_Hqdt5) {
        this.Udf_SL_Hqdt5 = Udf_SL_Hqdt5;
    }
    
    public String getUdf_SL_Hqdt6() {
        return Udf_SL_Hqdt6;
    }
    
    public void setUdf_SL_Hqdt6(String Udf_SL_Hqdt6) {
        this.Udf_SL_Hqdt6 = Udf_SL_Hqdt6;
    }
    
    public String getUdf_Mdnha_Ten() {
        return Udf_Mdnha_Ten;
    }
    
    public void setUdf_Mdnha_Ten(String Udf_Mdnha_Ten) {
        this.Udf_Mdnha_Ten = Udf_Mdnha_Ten;
    }
    
    public String getUdf_Md30a_Ten() {
        return Udf_Md30a_Ten;
    }
    
    public void setUdf_Md30a_Ten(String Udf_Md30a_Ten) {
        this.Udf_Md30a_Ten = Udf_Md30a_Ten;
    }
    
    public String getUdf_MaDA() {
        return Udf_MaDA;
    }
    
    public void setUdf_MaDA(String Udf_MaDA) {
        this.Udf_MaDA = Udf_MaDA;
    }
    
    public String getUdf_Nguonvon_BS_Ten() {
        return Udf_Nguonvon_BS_Ten;
    }
    
    public void setUdf_Nguonvon_BS_Ten(String Udf_Nguonvon_BS_Ten) {
        this.Udf_Nguonvon_BS_Ten = Udf_Nguonvon_BS_Ten;
    }
    
    public String getUdf_Soldlapn() {
        return Udf_Soldlapn;
    }
    
    public void setUdf_Soldlapn(String Udf_Soldlapn) {
        this.Udf_Soldlapn = Udf_Soldlapn;
    }
    
    public String getUdf_Soldlankt() {
        return Udf_Soldlankt;
    }
    
    public void setUdf_Soldlankt(String Udf_Soldlankt) {
        this.Udf_Soldlankt = Udf_Soldlankt;
    }
    
    public String getUdf_Soldlants() {
        return Udf_Soldlants;
    }
    
    public void setUdf_Soldlants(String Udf_Soldlants) {
        this.Udf_Soldlants = Udf_Soldlants;
    }
//</editor-fold>

}
