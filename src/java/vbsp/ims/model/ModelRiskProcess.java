/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.model;

import java.math.BigDecimal;
import java.sql.Date;

/**
 *
 * @author LION
 */
public class ModelRiskProcess {
    
    //<editor-fold defaultstate="collapsed" desc="Khai bao bien cho lop">
    public String sSoku;
    public String sMakh;
    public String sTenkh;
    public String sDiachi;
    public String sChtrinh;
    public String sSprd_Cd;
    public String sDq_Stat_Cd;
    public BigDecimal dbDngoc;
    public BigDecimal dbLaith;
    public BigDecimal dbLaiqh;
    public BigDecimal dbDnghi_Dno;
    public BigDecimal dbDnghi_Lai;
    public BigDecimal dbXl_Duno;
    public BigDecimal dbXl_Lai;
    public BigDecimal dbHt_Dno;
    public BigDecimal dbHt_Lai;
    public String sNgayvay;
    public String sNgaydh;
    public BigDecimal dbThoihanvay;
    public BigDecimal dbMdthiethai;
    public String sNgayrr;
    public BigDecimal dbDnghi_Tg;
    public BigDecimal dbPduyet_Tg;
    public String sNguyennhan;
    public String sMotann;
    public String sTrangthai;
    public String sPduyet_Ngay_Cn;
    public String sPduyet_Nguoi_Cn;
    public String sPduyet_Ngay_Tw;
    public String sPduyet_Nguoi_Tw;
    public String sTaolap_Nguoi;
    public String sTaolap_Ngay;
    public String sMaqd;
    public String sTenqd;
    public String sNhomrr;
    public String sMapgd;
    public String sMacn;
    public String sNgaybc;
    public String sCapnhat;
    public String sNgaytao;
    public String sNguoitao;
    public String sPduyet_Cap;
    public String sNgayhl;
    public String sHt_Tkxoano;
    public String sNguonvon;
    public BigDecimal dbDotrr;
    public String sMadp;
    public String sMato;
    public BigDecimal dbSolanxl;
    public String sNguoi_pduyet_pgd;
    public String sNgay_pduyet_pgd;
    public String sNguyennhan_tuchoi;
    public String sInt_pduyet_ngay_cn;
    public String sInt_pduyet_nguoi_cn;
    public String sNguyennhan_tc_cn;
    
    public String sTrangthai_duyet;
    public String sNguyennhan_01;
    public String sNguyennhan_02;
    public String sNguyennhan_03;
    public String sNguyennhan_04;
    public BigDecimal dDuno_hientai;
    public BigDecimal dLaiton_hientai;
    public String sNgaycn_dn_lt;
    
    public String sNguoiduyet_01;
    public String sNgayduyet_01;
    public String sNguoiduyet_02;
    public String sNgayduyet_02;
    public String sNguoiduyet_03;
    public String sNgayduyet_03;
    public String sNguoiduyet_04;
    public String sNgayduyet_04;
    
    public String sNgay_giahan;
    public BigDecimal dSotien_giahan;
    public String sNgay_giaodichgn;
    public BigDecimal dSodu_Casa105;
    public BigDecimal dRPA;
    
    public String sTenHSSV;
    public String sMucdicVV;
    
    
    
//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Get set cho method">
    public String getsSoku() {
        return sSoku;
    }
    
    public void setsSoku(String sSoku) {
        this.sSoku = sSoku;
    }

    public String getsNgay_giaodichgn() {
        return sNgay_giaodichgn;
    }

    public void setsNgay_giaodichgn(String sNgay_giaodichgn) {
        this.sNgay_giaodichgn = sNgay_giaodichgn;
    }
    
    
    public String getsMakh() {
        return sMakh;
    }
    
    public void setsMakh(String sMakh) {
        this.sMakh = sMakh;
    }
    
    public String getsTenkh() {
        return sTenkh;
    }
    
    public void setsTenkh(String sTenkh) {
        this.sTenkh = sTenkh;
    }
    
    public String getsDiachi() {
        return sDiachi;
    }
    
    public void setsDiachi(String sDiachi) {
        this.sDiachi = sDiachi;
    }
    
    public String getsChtrinh() {
        return sChtrinh;
    }
    
    public void setsChtrinh(String sChtrinh) {
        this.sChtrinh = sChtrinh;
    }
    
    public String getsSprd_Cd() {
        return sSprd_Cd;
    }
    
    public void setsSprd_Cd(String sSprd_Cd) {
        this.sSprd_Cd = sSprd_Cd;
    }
    
    public String getsDq_Stat_Cd() {
        return sDq_Stat_Cd;
    }
    
    public void setsDq_Stat_Cd(String sDq_Stat_Cd) {
        this.sDq_Stat_Cd = sDq_Stat_Cd;
    }
    
    public BigDecimal getDbDngoc() {
        return dbDngoc;
    }
    
    public void setDbDngoc(BigDecimal dbDngoc) {
        this.dbDngoc = dbDngoc;
    }
    
    public BigDecimal getDbLaith() {
        return dbLaith;
    }
    
    public void setDbLaith(BigDecimal dbLaith) {
        this.dbLaith = dbLaith;
    }
    
    public BigDecimal getDbLaiqh() {
        return dbLaiqh;
    }
    
    public void setDbLaiqh(BigDecimal dbLaiqh) {
        this.dbLaiqh = dbLaiqh;
    }
    
    public BigDecimal getDbDnghi_Dno() {
        return dbDnghi_Dno;
    }
    
    public void setDbDnghi_Dno(BigDecimal dbDnghi_Dno) {
        this.dbDnghi_Dno = dbDnghi_Dno;
    }
    
    public BigDecimal getDbDnghi_Lai() {
        return dbDnghi_Lai;
    }
    
    public void setDbDnghi_Lai(BigDecimal dbDnghi_Lai) {
        this.dbDnghi_Lai = dbDnghi_Lai;
    }
    
    public BigDecimal getDbXl_Duno() {
        return dbXl_Duno;
    }
    
    public void setDbXl_Duno(BigDecimal dbXl_Duno) {
        this.dbXl_Duno = dbXl_Duno;
    }
    
    public BigDecimal getDbXl_Lai() {
        return dbXl_Lai;
    }
    
    public void setDbXl_Lai(BigDecimal dbXl_Lai) {
        this.dbXl_Lai = dbXl_Lai;
    }
    
    public BigDecimal getDbHt_Dno() {
        return dbHt_Dno;
    }
    
    public void setDbHt_Dno(BigDecimal dbHt_Dno) {
        this.dbHt_Dno = dbHt_Dno;
    }
    
    public BigDecimal getDbHt_Lai() {
        return dbHt_Lai;
    }
    
    public void setDbHt_Lai(BigDecimal dbHt_Lai) {
        this.dbHt_Lai = dbHt_Lai;
    }
    
    public String getsNgayvay() {
        return sNgayvay;
    }
    
    public void setsNgayvay(String sNgayvay) {
        this.sNgayvay = sNgayvay;
    }
    
    public String getsNgaydh() {
        return sNgaydh;
    }
    
    public void setsNgaydh(String sNgaydh) {
        this.sNgaydh = sNgaydh;
    }
    
    public BigDecimal getDbThoihanvay() {
        return dbThoihanvay;
    }
    
    public void setDbThoihanvay(BigDecimal dbThoihanvay) {
        this.dbThoihanvay = dbThoihanvay;
    }
    
    public BigDecimal getDbMdthiethai() {
        return dbMdthiethai;
    }
    
    public void setDbMdthiethai(BigDecimal dbMdthiethai) {
        this.dbMdthiethai = dbMdthiethai;
    }

    public String getsTenHSSV() {
        return sTenHSSV;
    }

    public void setsTenHSSV(String sTenHSSV) {
        this.sTenHSSV = sTenHSSV;
    }

    public String getsMucdicVV() {
        return sMucdicVV;
    }

    public void setsMucdicVV(String sMucdicVV) {
        this.sMucdicVV = sMucdicVV;
    }
    
    
    public String getsNgayrr() {
        return sNgayrr;
    }
    
    public void setsNgayrr(String sNgayrr) {
        this.sNgayrr = sNgayrr;
    }
    
    public BigDecimal getDbDnghi_Tg() {
        return dbDnghi_Tg;
    }
    
    public void setDbDnghi_Tg(BigDecimal dbDnghi_Tg) {
        this.dbDnghi_Tg = dbDnghi_Tg;
    }
    
    public BigDecimal getDbPduyet_Tg() {
        return dbPduyet_Tg;
    }
    
    public void setDbPduyet_Tg(BigDecimal dbPduyet_Tg) {
        this.dbPduyet_Tg = dbPduyet_Tg;
    }
    
    public String getsNguyennhan() {
        return sNguyennhan;
    }
    
    public void setsNguyennhan(String sNguyennhan) {
        this.sNguyennhan = sNguyennhan;
    }
    
    public String getsMotann() {
        return sMotann;
    }
    
    public void setsMotann(String sMotann) {
        this.sMotann = sMotann;
    }
    
    public String getsTrangthai() {
        return sTrangthai;
    }
    
    public void setsTrangthai(String sTrangthai) {
        this.sTrangthai = sTrangthai;
    }
    
    public String getsPduyet_Ngay_Cn() {
        return sPduyet_Ngay_Cn;
    }
    
    public void setsPduyet_Ngay_Cn(String sPduyet_Ngay_Cn) {
        this.sPduyet_Ngay_Cn = sPduyet_Ngay_Cn;
    }
    
    public String getsPduyet_Nguoi_Cn() {
        return sPduyet_Nguoi_Cn;
    }
    
    public void setsPduyet_Nguoi_Cn(String sPduyet_Nguoi_Cn) {
        this.sPduyet_Nguoi_Cn = sPduyet_Nguoi_Cn;
    }
    
    public String getsPduyet_Ngay_Tw() {
        return sPduyet_Ngay_Tw;
    }
    
    public void setsPduyet_Ngay_Tw(String sPduyet_Ngay_Tw) {
        this.sPduyet_Ngay_Tw = sPduyet_Ngay_Tw;
    }
    
    public String getsPduyet_Nguoi_Tw() {
        return sPduyet_Nguoi_Tw;
    }
    
    public void setsPduyet_Nguoi_Tw(String sPduyet_Nguoi_Tw) {
        this.sPduyet_Nguoi_Tw = sPduyet_Nguoi_Tw;
    }
    
    public String getsTaolap_Nguoi() {
        return sTaolap_Nguoi;
    }
    
    public void setsTaolap_Nguoi(String sTaolap_Nguoi) {
        this.sTaolap_Nguoi = sTaolap_Nguoi;
    }
    
    public String getsTaolap_Ngay() {
        return sTaolap_Ngay;
    }
    
    public void setsTaolap_Ngay(String sTaolap_Ngay) {
        this.sTaolap_Ngay = sTaolap_Ngay;
    }
    
    public String getsMaqd() {
        return sMaqd;
    }
    
    public void setsMaqd(String sMaqd) {
        this.sMaqd = sMaqd;
    }
    
    public String getsTenqd() {
        return sTenqd;
    }
    
    public void setsTenqd(String sTenqd) {
        this.sTenqd = sTenqd;
    }
    
    public String getsNhomrr() {
        return sNhomrr;
    }
    
    public void setsNhomrr(String sNhomrr) {
        this.sNhomrr = sNhomrr;
    }
    
    public String getsMapgd() {
        return sMapgd;
    }
    
    public void setsMapgd(String sMapgd) {
        this.sMapgd = sMapgd;
    }
    
    public String getsMacn() {
        return sMacn;
    }
    
    public void setsMacn(String sMacn) {
        this.sMacn = sMacn;
    }
    
    public String getsNgaybc() {
        return sNgaybc;
    }
    
    public void setsNgaybc(String sNgaybc) {
        this.sNgaybc = sNgaybc;
    }
    
    public String getsCapnhat() {
        return sCapnhat;
    }
    
    public void setsCapnhat(String sCapnhat) {
        this.sCapnhat = sCapnhat;
    }
    
    public String getsNgaytao() {
        return sNgaytao;
    }
    
    public void setsNgaytao(String sNgaytao) {
        this.sNgaytao = sNgaytao;
    }
    
    public String getsNguoitao() {
        return sNguoitao;
    }
    
    public void setsNguoitao(String sNguoitao) {
        this.sNguoitao = sNguoitao;
    }
    
    public String getsPduyet_Cap() {
        return sPduyet_Cap;
    }
    
    public void setsPduyet_Cap(String sPduyet_Cap) {
        this.sPduyet_Cap = sPduyet_Cap;
    }
    
    public String getsNgayhl() {
        return sNgayhl;
    }
    
    public void setsNgayhl(String sNgayhl) {
        this.sNgayhl = sNgayhl;
    }
    
    public String getsHt_Tkxoano() {
        return sHt_Tkxoano;
    }
    
    public void setsHt_Tkxoano(String sHt_Tkxoano) {
        this.sHt_Tkxoano = sHt_Tkxoano;
    }
    
    public String getsNguonvon() {
        return sNguonvon;
    }
    
    public void setsNguonvon(String sNguonvon) {
        this.sNguonvon = sNguonvon;
    }
    
    public BigDecimal getDbDotrr() {
        return dbDotrr;
    }
    
    public void setDbDotrr(BigDecimal dbDotrr) {
        this.dbDotrr = dbDotrr;
    }
    
    public String getsMadp() {
        return sMadp;
    }
    
    public void setsMadp(String sMadp) {
        this.sMadp = sMadp;
    }
    
    public String getsMato() {
        return sMato;
    }
    
    public void setsMato(String sMato) {
        this.sMato = sMato;
    }
    
    public BigDecimal getDbSolanxl() {
        return dbSolanxl;
    }
    
    public void setDbSolanxl(BigDecimal dbSolanxl) {
        this.dbSolanxl = dbSolanxl;
    }

    public String getsNguoi_pduyet_pgd() {
        return sNguoi_pduyet_pgd;
    }

    public void setsNguoi_pduyet_pgd(String sNguoi_pduyet_pgd) {
        this.sNguoi_pduyet_pgd = sNguoi_pduyet_pgd;
    }

    public String getsNgay_pduyet_pgd() {
        return sNgay_pduyet_pgd;
    }

    public void setsNgay_pduyet_pgd(String sNgay_pduyet_pgd) {
        this.sNgay_pduyet_pgd = sNgay_pduyet_pgd;
    }

    public String getsNguyennhan_tuchoi() {
        return sNguyennhan_tuchoi;
    }

    public void setsNguyennhan_tuchoi(String sNguyennhan_tuchoi) {
        this.sNguyennhan_tuchoi = sNguyennhan_tuchoi;
    }

    public String getsInt_pduyet_ngay_cn() {
        return sInt_pduyet_ngay_cn;
    }

    public void setsInt_pduyet_ngay_cn(String sInt_pduyet_ngay_cn) {
        this.sInt_pduyet_ngay_cn = sInt_pduyet_ngay_cn;
    }

    public String getsInt_pduyet_nguoi_cn() {
        return sInt_pduyet_nguoi_cn;
    }

    public void setsInt_pduyet_nguoi_cn(String sInt_pduyet_nguoi_cn) {
        this.sInt_pduyet_nguoi_cn = sInt_pduyet_nguoi_cn;
    }

    public String getsNguyennhan_tc_cn() {
        return sNguyennhan_tc_cn;
    }

    public void setsNguyennhan_tc_cn(String sNguyennhan_tc_cn) {
        this.sNguyennhan_tc_cn = sNguyennhan_tc_cn;
    }

    public String getsTrangthai_duyet() {
        return sTrangthai_duyet;
    }

    public void setsTrangthai_duyet(String sTrangthai_duyet) {
        this.sTrangthai_duyet = sTrangthai_duyet;
    }

    public String getsNguyennhan_01() {
        return sNguyennhan_01;
    }

    public void setsNguyennhan_01(String sNguyennhan_01) {
        this.sNguyennhan_01 = sNguyennhan_01;
    }

    public String getsNguyennhan_02() {
        return sNguyennhan_02;
    }

    public void setsNguyennhan_02(String sNguyennhan_02) {
        this.sNguyennhan_02 = sNguyennhan_02;
    }

    public String getsNguyennhan_03() {
        return sNguyennhan_03;
    }

    public void setsNguyennhan_03(String sNguyennhan_03) {
        this.sNguyennhan_03 = sNguyennhan_03;
    }

    public String getsNguyennhan_04() {
        return sNguyennhan_04;
    }

    public void setsNguyennhan_04(String sNguyennhan_04) {
        this.sNguyennhan_04 = sNguyennhan_04;
    }

    public BigDecimal getdDuno_hientai() {
        return dDuno_hientai;
    }

    public void setdDuno_hientai(BigDecimal dDuno_hientai) {
        this.dDuno_hientai = dDuno_hientai;
    }

    public BigDecimal getdLaiton_hientai() {
        return dLaiton_hientai;
    }

    public void setdLaiton_hientai(BigDecimal dLaiton_hientai) {
        this.dLaiton_hientai = dLaiton_hientai;
    }

    public String getsNgaycn_dn_lt() {
        return sNgaycn_dn_lt;
    }

    public void setsNgaycn_dn_lt(String sNgaycn_dn_lt) {
        this.sNgaycn_dn_lt = sNgaycn_dn_lt;
    }

    public String getsNguoiduyet_01() {
        return sNguoiduyet_01;
    }

    public void setsNguoiduyet_01(String sNguoiduyet_01) {
        this.sNguoiduyet_01 = sNguoiduyet_01;
    }

    public String getsNgayduyet_01() {
        return sNgayduyet_01;
    }

    public void setsNgayduyet_01(String sNgayduyet_01) {
        this.sNgayduyet_01 = sNgayduyet_01;
    }

    public String getsNguoiduyet_02() {
        return sNguoiduyet_02;
    }

    public void setsNguoiduyet_02(String sNguoiduyet_02) {
        this.sNguoiduyet_02 = sNguoiduyet_02;
    }

    public String getsNgayduyet_02() {
        return sNgayduyet_02;
    }

    public void setsNgayduyet_02(String sNgayduyet_02) {
        this.sNgayduyet_02 = sNgayduyet_02;
    }

    public String getsNguoiduyet_03() {
        return sNguoiduyet_03;
    }

    public void setsNguoiduyet_03(String sNguoiduyet_03) {
        this.sNguoiduyet_03 = sNguoiduyet_03;
    }

    public String getsNgayduyet_03() {
        return sNgayduyet_03;
    }

    public void setsNgayduyet_03(String sNgayduyet_03) {
        this.sNgayduyet_03 = sNgayduyet_03;
    }

    public String getsNguoiduyet_04() {
        return sNguoiduyet_04;
    }

    public void setsNguoiduyet_04(String sNguoiduyet_04) {
        this.sNguoiduyet_04 = sNguoiduyet_04;
    }

    public String getsNgayduyet_04() {
        return sNgayduyet_04;
    }

    public void setsNgayduyet_04(String sNgayduyet_04) {
        this.sNgayduyet_04 = sNgayduyet_04;
    }

    public String getsNgay_giahan() {
        return sNgay_giahan;
    }

    public void setsNgay_giahan(String sNgay_giahan) {
        this.sNgay_giahan = sNgay_giahan;
    }

    public BigDecimal getdSotien_giahan() {
        return dSotien_giahan;
    }

    public void setdSotien_giahan(BigDecimal dSotien_giahan) {
        this.dSotien_giahan = dSotien_giahan;
    }


    public BigDecimal getdSodu_Casa105() {
        return dSodu_Casa105;
    }

    public void setdSodu_Casa105(BigDecimal dSodu_Casa105) {
        this.dSodu_Casa105 = dSodu_Casa105;
    }

    public BigDecimal getdRPA() {
        return dRPA;
    }

    public void setdRPA(BigDecimal dRPA) {
        this.dRPA = dRPA;
    }

    
  

//</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Khai bao lop tinh cho phan luu du lieu gui tu sua dl tren web">
    public static class ListRisk {

        public String check_legacyid;
        public String sTenkh;
        public String duno_rr;
        public String lai_rr;
        public String sNgayvay;
        public String sNgaydh;
        public String sNgayrr;
        public String sThiethai;
        public String thang;
        public String sNguyennhan;
        
        public String getCheck_legacyid() {
            return check_legacyid;
        }

        public void setCheck_legacyid(String check_legacyid) {
            this.check_legacyid = check_legacyid;
        }

        public String getDuno_rr() {
            return duno_rr;
        }

        public void setDuno_rr(String duno_rr) {
            this.duno_rr = duno_rr;
        }

        public String getLai_rr() {
            return lai_rr;
        }

        public void setLai_rr(String lai_rr) {
            this.lai_rr = lai_rr;
        }

        public String getThang() {
            return thang;
        }

        public void setThang(String thang) {
            this.thang = thang;
        }

        public String getsTenkh() {
            return sTenkh;
        }

        public void setsTenkh(String sTenkh) {
            this.sTenkh = sTenkh;
        }

      

        public String getsNgayvay() {
            return sNgayvay;
        }

        public void setsNgayvay(String sNgayvay) {
            this.sNgayvay = sNgayvay;
        }

        public String getsNgaydh() {
            return sNgaydh;
        }

        public void setsNgaydh(String sNgaydh) {
            this.sNgaydh = sNgaydh;
        }

        public String getsNgayrr() {
            return sNgayrr;
        }

        public void setsNgayrr(String sNgayrr) {
            this.sNgayrr = sNgayrr;
        }

        public String getsThiethai() {
            return sThiethai;
        }

        public void setsThiethai(String sThiethai) {
            this.sThiethai = sThiethai;
        }

        public String getsNguyennhan() {
            return sNguyennhan;
        }

        public void setsNguyennhan(String sNguyennhan) {
            this.sNguyennhan = sNguyennhan;
        }
        
    }
//</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Khai bao lop tinh cho phan luu du lieu gui tu cn -> tw">
    public static class ListRiskSync {
        public String svbxlrr;
        public String sStt;
        public String sSoku;
        public String sTenkh;
        public String sDnghi_Dno;
        public String sDnghi_Lai;
        public String sNgayvay;
        public String sNgaydh;
        public String sMdthiethai;
        public String sNgayrr;
        public String sDnghi_Tg;
        public String sNguyennhan;
        public String sTrangthai;
        public String sPduyet_Ngay_Cn;
        public String sPduyet_Nguoi_Cn;
        public String sPduyet_Cap;
        public String sNguoi_pduyet_pgd;
        public String sNgay_pduyet_pgd;
        public String sNguyennhan_tuchoi;

        public String getsStt() {
            return sStt;
        }

        public void setsStt(String sStt) {
            this.sStt = sStt;
        }

        public String getsSoku() {
            return sSoku;
        }

        public void setsSoku(String sSoku) {
            this.sSoku = sSoku;
        }

        public String getsTenkh() {
            return sTenkh;
        }

        public void setsTenkh(String sTenkh) {
            this.sTenkh = sTenkh;
        }

        public String getsDnghi_Dno() {
            return sDnghi_Dno;
        }

        public void setsDnghi_Dno(String sDnghi_Dno) {
            this.sDnghi_Dno = sDnghi_Dno;
        }

        public String getsDnghi_Lai() {
            return sDnghi_Lai;
        }

        public void setsDnghi_Lai(String sDnghi_Lai) {
            this.sDnghi_Lai = sDnghi_Lai;
        }

        public String getsNgayvay() {
            return sNgayvay;
        }

        public void setsNgayvay(String sNgayvay) {
            this.sNgayvay = sNgayvay;
        }

        public String getsNgaydh() {
            return sNgaydh;
        }

        public void setsNgaydh(String sNgaydh) {
            this.sNgaydh = sNgaydh;
        }

        public String getsMdthiethai() {
            return sMdthiethai;
        }

        public void setsMdthiethai(String sMdthiethai) {
            this.sMdthiethai = sMdthiethai;
        }

        public String getsNgayrr() {
            return sNgayrr;
        }

        public void setsNgayrr(String sNgayrr) {
            this.sNgayrr = sNgayrr;
        }

        public String getsDnghi_Tg() {
            return sDnghi_Tg;
        }

        public void setsDnghi_Tg(String sDnghi_Tg) {
            this.sDnghi_Tg = sDnghi_Tg;
        }

        public String getsNguyennhan() {
            return sNguyennhan;
        }

        public void setsNguyennhan(String sNguyennhan) {
            this.sNguyennhan = sNguyennhan;
        }

        public String getsTrangthai() {
            return sTrangthai;
        }

        public void setsTrangthai(String sTrangthai) {
            this.sTrangthai = sTrangthai;
        }

        public String getsPduyet_Ngay_Cn() {
            return sPduyet_Ngay_Cn;
        }

        public void setsPduyet_Ngay_Cn(String sPduyet_Ngay_Cn) {
            this.sPduyet_Ngay_Cn = sPduyet_Ngay_Cn;
        }

        public String getsPduyet_Nguoi_Cn() {
            return sPduyet_Nguoi_Cn;
        }

        public void setsPduyet_Nguoi_Cn(String sPduyet_Nguoi_Cn) {
            this.sPduyet_Nguoi_Cn = sPduyet_Nguoi_Cn;
        }

        public String getsPduyet_Cap() {
            return sPduyet_Cap;
        }

        public void setsPduyet_Cap(String sPduyet_Cap) {
            this.sPduyet_Cap = sPduyet_Cap;
        }

        public String getsNguoi_pduyet_pgd() {
            return sNguoi_pduyet_pgd;
        }

        public void setsNguoi_pduyet_pgd(String sNguoi_pduyet_pgd) {
            this.sNguoi_pduyet_pgd = sNguoi_pduyet_pgd;
        }

        public String getsNgay_pduyet_pgd() {
            return sNgay_pduyet_pgd;
        }

        public void setsNgay_pduyet_pgd(String sNgay_pduyet_pgd) {
            this.sNgay_pduyet_pgd = sNgay_pduyet_pgd;
        }

        public String getsNguyennhan_tuchoi() {
            return sNguyennhan_tuchoi;
        }

        public void setsNguyennhan_tuchoi(String sNguyennhan_tuchoi) {
            this.sNguyennhan_tuchoi = sNguyennhan_tuchoi;
        }

        public String getSvbxlrr() {
            return svbxlrr;
        }

        public void setSvbxlrr(String svbxlrr) {
            this.svbxlrr = svbxlrr;
        }        
    }
//</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Lop tinh cho phe duyet theo chi nhanh">
    public static class DescTableBrower
    {
        public int nStt;
        public String sPoscd;
        public String sPosDesc;
        public String sSoKh;
        public String sTongtien;
        public String sTongDuno;
        public String sTongLai;
        public String sStatus;
        public boolean bSuccess;
        public String sTongtienDn;
        public String sTongDunoDn;
        public String sTongLaiDn;
        public String sNguyennhan_tc_cn;
        
        public String sSoKh_1;
        public String sTongtien_1;
        public String sTongDuno_1;
        public String sTongLai_1;
        
        public int getnStt() {
            return nStt;
        }

        public void setnStt(int nStt) {
            this.nStt = nStt;
        }
        public String getsPoscd() {
            return sPoscd;
        }
        
        public void setsPoscd(String sPoscd) {
            this.sPoscd = sPoscd;
        }
        
        public String getsPosDesc() {
            return sPosDesc;
        }
        
        public void setsPosDesc(String sPosDesc) {
            this.sPosDesc = sPosDesc;
        }

        public String getsSoKh() {
            return sSoKh;
        }

        public void setsSoKh(String sSoKh) {
            this.sSoKh = sSoKh;
        }

        public String getsTongtien() {
            return sTongtien;
        }

        public void setsTongtien(String sTongtien) {
            this.sTongtien = sTongtien;
        }
        
        public String getsTongDuno() {
            return sTongDuno;
        }

        public void setsTongDuno(String sTongDuno) {
            this.sTongDuno = sTongDuno;
        }

        public String getsTongLai() {
            return sTongLai;
        }

        public void setsTongLai(String sTongLai) {
            this.sTongLai = sTongLai;
        }
        
        public boolean isbSuccess() {
            return bSuccess;
        }

        public void setbSuccess(boolean bSuccess) {
            this.bSuccess = bSuccess;
        }
        
        public String getsStatus() {
            return sStatus;
        }
        
        public void setsStatus(String sStatus) {
            this.sStatus = sStatus;
        }

        public String getsTongtienDn() {
            return sTongtienDn;
        }

        public void setsTongtienDn(String sTongtienDn) {
            this.sTongtienDn = sTongtienDn;
        }

        public String getsTongDunoDn() {
            return sTongDunoDn;
        }

        public void setsTongDunoDn(String sTongDunoDn) {
            this.sTongDunoDn = sTongDunoDn;
        }

        public String getsTongLaiDn() {
            return sTongLaiDn;
        }

        public void setsTongLaiDn(String sTongLaiDn) {
            this.sTongLaiDn = sTongLaiDn;
        }

        public String getsNguyennhan_tc_cn() {
            return sNguyennhan_tc_cn;
        }

        public void setsNguyennhan_tc_cn(String sNguyennhan_tc_cn) {
            this.sNguyennhan_tc_cn = sNguyennhan_tc_cn;
        }

        public String getsSoKh_1() {
            return sSoKh_1;
        }

        public void setsSoKh_1(String sSoKh_1) {
            this.sSoKh_1 = sSoKh_1;
        }

        public String getsTongtien_1() {
            return sTongtien_1;
        }

        public void setsTongtien_1(String sTongtien_1) {
            this.sTongtien_1 = sTongtien_1;
        }

        public String getsTongDuno_1() {
            return sTongDuno_1;
        }

        public void setsTongDuno_1(String sTongDuno_1) {
            this.sTongDuno_1 = sTongDuno_1;
        }

        public String getsTongLai_1() {
            return sTongLai_1;
        }

        public void setsTongLai_1(String sTongLai_1) {
            this.sTongLai_1 = sTongLai_1;
        }
        
        
        
    }
//</editor-fold>
    
    
    //<editor-fold defaultstate="collapsed" desc="Lop tinh cho lich su gui du lieu">
    public static class HistorySendData
    {
        public String sMapgd;
        public String sMacn;
        public String sUserid;
        public String sGrade;
        public String sCapbc;
        public String sNamrr;
        public String sDotrr;
        public String sNhomrr;
        public String sNgaytao;
        public String sSomon;
        public String sTenpgd;
        
        public String getsMapgd() {
            return sMapgd;
        }
        
        public void setsMapgd(String sMapgd) {
            this.sMapgd = sMapgd;
        }
        
        public String getsMacn() {
            return sMacn;
        }
        
        public void setsMacn(String sMacn) {
            this.sMacn = sMacn;
        }
        
        public String getsUserid() {
            return sUserid;
        }
        
        public void setsUserid(String sUserid) {
            this.sUserid = sUserid;
        }
        
        public String getsGrade() {
            return sGrade;
        }
        
        public void setsGrade(String sGrade) {
            this.sGrade = sGrade;
        }
        
        public String getsCapbc() {
            return sCapbc;
        }
        
        public void setsCapbc(String sCapbc) {
            this.sCapbc = sCapbc;
        }
        
        public String getsNamrr() {
            return sNamrr;
        }
        
        public void setsNamrr(String sNamrr) {
            this.sNamrr = sNamrr;
        }
        
        public String getsDotrr() {
            return sDotrr;
        }
        
        public void setsDotrr(String sDotrr) {
            this.sDotrr = sDotrr;
        }
        
        public String getsNhomrr() {
            return sNhomrr;
        }
        
        public void setsNhomrr(String sNhomrr) {
            this.sNhomrr = sNhomrr;
        }
        
        public String getsNgaytao() {
            return sNgaytao;
        }
        
        public void setsNgaytao(String sNgaytao) {
            this.sNgaytao = sNgaytao;
        }
        
        public String getsSomon() {
            return sSomon;
        }
        
        public void setsSomon(String sSomon) {
            this.sSomon = sSomon;
        }

        public String getsTenpgd() {
            return sTenpgd;
        }

        public void setsTenpgd(String sTenpgd) {
            this.sTenpgd = sTenpgd;
        }
        
    }
//</editor-fold>
    
    
    //<editor-fold defaultstate="collapsed" desc="Lop tinh de set cho chi nhanh gui du lieu">
    public static class StatusHistorySend
    {
        public int nStt;
        public String sMacn;
        public String sTencn;
        public String sSend;
        public String sStatus;
        public String vb_xlrr;
        
        public int getnStt() {
            return nStt;
        }

        public void setnStt(int nStt) {
            this.nStt = nStt;
        }
        
        
        public String getsMacn() {
            return sMacn;
        }
        
        public void setsMacn(String sMacn) {
            this.sMacn = sMacn;
        }
        
        public String getsTencn() {
            return sTencn;
        }
        
        public void setsTencn(String sTencn) {
            this.sTencn = sTencn;
        }
        
        public String getsSend() {
            return sSend;
        }
        
        public void setsSend(String sSend) {
            this.sSend = sSend;
        }
        
        public String getsStatus() {
            return sStatus;
        }
        
        public void setsStatus(String sStatus) {
            this.sStatus = sStatus;
        }

        public String getVb_xlrr() {
            return vb_xlrr;
        }

        public void setVb_xlrr(String vb_xlrr) {
            this.vb_xlrr = vb_xlrr;
        }
        
    }
//</editor-fold>
    
}

