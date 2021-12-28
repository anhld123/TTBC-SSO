/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.model;

import java.math.BigDecimal;
import java.util.Date;
import java.sql.SQLData;
import java.sql.SQLException;
import java.sql.SQLInput;
import java.sql.SQLOutput;

/**
 *
 * @author: Sr. Chữ (Date Created: 22/01/2016)
 */
public class DcplnModel {

    //<editor-fold defaultstate="collapsed" desc="Đối chiếu - Phân tích nợ -> Thông tin chi tiết danh sách">
    public int bStt;
    public String sSoku;
    public String sMakh;
    public String sTenkh;
    public String sMato;
    public String sDvut;
    public String sMadp;
    public String sNguonvon;
    public String sSprd_Cd;
    public String sChtrinh;
    public String sMaqd;
    public String sDnothan;
    public String sDnoqhan;
    public String sDnokhoanh;
    public String sLaithan_Tt;
    public String sLaiqhan_Tt;
    public String sTonglai_Tt;
    public String sLaitonthan;
    public String sLaitonqhan;
    public String sTonglaiton;
    public String sC_Kntn_Sodu;
    public String sK_Kntn_Sodu;
    public String sK_Kntn_Sd01;
    public String sK_Kntn_Sd02;
    public String sK_Kntn_Sd03;
    public String sK_Kntn_Sd04;
    public String sK_Kntn_Sd05;
    public String sK_Kntn_Sd06;
    public String sK_Kntn_Sd07;
    public String sK_Kntn_Sd08;
    public String sK_Kntn_Sd09;
    public String sK_Kntn_Sd10;
    public String sK_Kntn_Sd11;
    public String sK_Ngnhan_Kh;
    public String sQuanhe_Kh;
    public String sTrangthai;
    public String sNogoc_Clech;
    public String sNolai_Clech;
    public String sNgnhan_Clech;
    public String sTt_Monvay;
    public String sNgaybc;
    public String sNguoi_Pln;
    public String sNgay_Pln;
    public String sMapgd;
    public String sMacn;
    public String sNgaycn;
    public String sNguonvon_Ten;
    public String sChtrinh_Ten;
    public String sChtrinh_Tenvt;
    public String sNgnhan_Kckntn;
    public String sNgnhan_KckntnC2;    
    public String sNgnhan_Kckntn_Ten;
    public String sDvut_Ten;
    public String sMaxa;
    public String sMaxa_Ten;
    public String sTentt;
    public String sTrangthaino;
    public String sMatt;
    public String sLoaito;
    public String sTongDN;

    public String sSprd_Cd_Ten;
    public String sTrangthai_Ten;
    public String sTrangthaino_Ten;
    public String sTchat_No;
    public String sTchat_No_Ten;
    public String sLoaito_Ten;
    

    public String getsNgnhan_KckntnC2() {
        return sNgnhan_KckntnC2;
    }

    public void setsNgnhan_KckntnC2(String sNgnhan_KckntnC2) {
        this.sNgnhan_KckntnC2 = sNgnhan_KckntnC2;
    }

    public int getbStt() {
        return bStt;
    }

    public void setbStt(int bStt) {
        this.bStt = bStt;
    }

    public String getsSoku() {
        return sSoku;
    }

    public void setsSoku(String sSoku) {
        this.sSoku = sSoku;
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

    public String getsMato() {
        return sMato;
    }

    public void setsMato(String sMato) {
        this.sMato = sMato;
    }

    public String getsDvut() {
        return sDvut;
    }

    public void setsDvut(String sDvut) {
        this.sDvut = sDvut;
    }

    public String getsMadp() {
        return sMadp;
    }

    public void setsMadp(String sMadp) {
        this.sMadp = sMadp;
    }

    public String getsNguonvon() {
        return sNguonvon;
    }

    public void setsNguonvon(String sNguonvon) {
        this.sNguonvon = sNguonvon;
    }

    public String getsSprd_Cd() {
        return sSprd_Cd;
    }

    public void setsSprd_Cd(String sSprd_Cd) {
        this.sSprd_Cd = sSprd_Cd;
    }

    public String getsChtrinh() {
        return sChtrinh;
    }

    public void setsChtrinh(String sChtrinh) {
        this.sChtrinh = sChtrinh;
    }

    public String getsMaqd() {
        return sMaqd;
    }

    public void setsMaqd(String sMaqd) {
        this.sMaqd = sMaqd;
    }

    public String getsDnothan() {
        return sDnothan;
    }

    public void setsDnothan(String sDnothan) {
        this.sDnothan = sDnothan;
    }

    public String getsDnoqhan() {
        return sDnoqhan;
    }

    public void setsDnoqhan(String sDnoqhan) {
        this.sDnoqhan = sDnoqhan;
    }

    public String getsDnokhoanh() {
        return sDnokhoanh;
    }

    public void setsDnokhoanh(String sDnokhoanh) {
        this.sDnokhoanh = sDnokhoanh;
    }

    public String getsTongDN() {
        return sTongDN;
    }

    public void setsTongDN(String sTongDN) {
        this.sTongDN = sTongDN;
    }

    public String getsLaithan_Tt() {
        return sLaithan_Tt;
    }

    public void setsLaithan_Tt(String sLaithan_Tt) {
        this.sLaithan_Tt = sLaithan_Tt;
    }

    public String getsLaiqhan_Tt() {
        return sLaiqhan_Tt;
    }

    public void setsLaiqhan_Tt(String sLaiqhan_Tt) {
        this.sLaiqhan_Tt = sLaiqhan_Tt;
    }

    public String getsTonglai_Tt() {
        return sTonglai_Tt;
    }

    public void setsTonglai_Tt(String sTonglai_Tt) {
        this.sTonglai_Tt = sTonglai_Tt;
    }

    public String getsLaitonthan() {
        return sLaitonthan;
    }

    public void setsLaitonthan(String sLaitonthan) {
        this.sLaitonthan = sLaitonthan;
    }

    public String getsLaitonqhan() {
        return sLaitonqhan;
    }

    public void setsLaitonqhan(String sLaitonqhan) {
        this.sLaitonqhan = sLaitonqhan;
    }

    public String getsTonglaiton() {
        return sTonglaiton;
    }

    public void setsTonglaiton(String sTonglaiton) {
        this.sTonglaiton = sTonglaiton;
    }

    public String getsC_Kntn_Sodu() {
        return sC_Kntn_Sodu;
    }

    public void setsC_Kntn_Sodu(String sC_Kntn_Sodu) {
        this.sC_Kntn_Sodu = sC_Kntn_Sodu;
    }

    public String getsK_Kntn_Sodu() {
        return sK_Kntn_Sodu;
    }

    public void setsK_Kntn_Sodu(String sK_Kntn_Sodu) {
        this.sK_Kntn_Sodu = sK_Kntn_Sodu;
    }

    public String getsK_Kntn_Sd01() {
        return sK_Kntn_Sd01;
    }

    public void setsK_Kntn_Sd01(String sK_Kntn_Sd01) {
        this.sK_Kntn_Sd01 = sK_Kntn_Sd01;
    }

    public String getsK_Kntn_Sd02() {
        return sK_Kntn_Sd02;
    }

    public void setsK_Kntn_Sd02(String sK_Kntn_Sd02) {
        this.sK_Kntn_Sd02 = sK_Kntn_Sd02;
    }

    public String getsK_Kntn_Sd03() {
        return sK_Kntn_Sd03;
    }

    public void setsK_Kntn_Sd03(String sK_Kntn_Sd03) {
        this.sK_Kntn_Sd03 = sK_Kntn_Sd03;
    }

    public String getsK_Kntn_Sd04() {
        return sK_Kntn_Sd04;
    }

    public void setsK_Kntn_Sd04(String sK_Kntn_Sd04) {
        this.sK_Kntn_Sd04 = sK_Kntn_Sd04;
    }

    public String getsK_Kntn_Sd05() {
        return sK_Kntn_Sd05;
    }

    public void setsK_Kntn_Sd05(String sK_Kntn_Sd05) {
        this.sK_Kntn_Sd05 = sK_Kntn_Sd05;
    }

    public String getsK_Kntn_Sd06() {
        return sK_Kntn_Sd06;
    }

    public void setsK_Kntn_Sd06(String sK_Kntn_Sd06) {
        this.sK_Kntn_Sd06 = sK_Kntn_Sd06;
    }

    public String getsK_Kntn_Sd07() {
        return sK_Kntn_Sd07;
    }

    public void setsK_Kntn_Sd07(String sK_Kntn_Sd07) {
        this.sK_Kntn_Sd07 = sK_Kntn_Sd07;
    }

    public String getsK_Kntn_Sd08() {
        return sK_Kntn_Sd08;
    }

    public void setsK_Kntn_Sd08(String sK_Kntn_Sd08) {
        this.sK_Kntn_Sd08 = sK_Kntn_Sd08;
    }

    public String getsK_Kntn_Sd09() {
        return sK_Kntn_Sd09;
    }

    public void setsK_Kntn_Sd09(String sK_Kntn_Sd09) {
        this.sK_Kntn_Sd09 = sK_Kntn_Sd09;
    }

    public String getsK_Kntn_Sd10() {
        return sK_Kntn_Sd10;
    }

    public void setsK_Kntn_Sd10(String sK_Kntn_Sd10) {
        this.sK_Kntn_Sd10 = sK_Kntn_Sd10;
    }

    public String getsK_Kntn_Sd11() {
        return sK_Kntn_Sd11;
    }

    public void setsK_Kntn_Sd11(String sK_Kntn_Sd11) {
        this.sK_Kntn_Sd11 = sK_Kntn_Sd11;
    }

    public String getsK_Ngnhan_Kh() {
        return sK_Ngnhan_Kh;
    }

    public void setsK_Ngnhan_Kh(String sK_Ngnhan_Kh) {
        this.sK_Ngnhan_Kh = sK_Ngnhan_Kh;
    }

    public String getsQuanhe_Kh() {
        return sQuanhe_Kh;
    }

    public void setsQuanhe_Kh(String sQuanhe_Kh) {
        this.sQuanhe_Kh = sQuanhe_Kh;
    }

    public String getsTrangthai() {
        return sTrangthai;
    }

    public void setsTrangthai(String sTrangthai) {
        this.sTrangthai = sTrangthai;
    }

    public String getsNogoc_Clech() {
        return sNogoc_Clech;
    }

    public void setsNogoc_Clech(String sNogoc_Clech) {
        this.sNogoc_Clech = sNogoc_Clech;
    }

    public String getsNolai_Clech() {
        return sNolai_Clech;
    }

    public void setsNolai_Clech(String sNolai_Clech) {
        this.sNolai_Clech = sNolai_Clech;
    }

    public String getsNgnhan_Clech() {
        return sNgnhan_Clech;
    }

    public void setsNgnhan_Clech(String sNgnhan_Clech) {
        this.sNgnhan_Clech = sNgnhan_Clech;
    }

    public String getsTt_Monvay() {
        return sTt_Monvay;
    }

    public void setsTt_Monvay(String sTt_Monvay) {
        this.sTt_Monvay = sTt_Monvay;
    }

    public String getsNgaybc() {
        return sNgaybc;
    }

    public void setsNgaybc(String sNgaybc) {
        this.sNgaybc = sNgaybc;
    }

    public String getsNguoi_Pln() {
        return sNguoi_Pln;
    }

    public void setsNguoi_Pln(String sNguoi_Pln) {
        this.sNguoi_Pln = sNguoi_Pln;
    }

    public String getsNgay_Pln() {
        return sNgay_Pln;
    }

    public void setsNgay_Pln(String sNgay_Pln) {
        this.sNgay_Pln = sNgay_Pln;
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

    public String getsNgaycn() {
        return sNgaycn;
    }

    public void setsNgaycn(String sNgaycn) {
        this.sNgaycn = sNgaycn;
    }

    public String getsNguonvon_Ten() {
        return sNguonvon_Ten;
    }

    public void setsNguonvon_Ten(String sNguonvon_Ten) {
        this.sNguonvon_Ten = sNguonvon_Ten;
    }

    public String getsChtrinh_Ten() {
        return sChtrinh_Ten;
    }

    public void setsChtrinh_Ten(String sChtrinh_Ten) {
        this.sChtrinh_Ten = sChtrinh_Ten;
    }

    public String getsChtrinh_Tenvt() {
        return sChtrinh_Tenvt;
    }

    public void setsChtrinh_Tenvt(String sChtrinh_Tenvt) {
        this.sChtrinh_Tenvt = sChtrinh_Tenvt;
    }

    public String getsNgnhan_Kckntn() {
        return sNgnhan_Kckntn;
    }

    public void setsNgnhan_Kckntn(String sNgnhan_Kckntn) {
        this.sNgnhan_Kckntn = sNgnhan_Kckntn;
    }

    public String getsNgnhan_Kckntn_Ten() {
        return sNgnhan_Kckntn_Ten;
    }

    public void setsNgnhan_Kckntn_Ten(String sNgnhan_Kckntn_Ten) {
        this.sNgnhan_Kckntn_Ten = sNgnhan_Kckntn_Ten;
    }

    public String getsDvut_Ten() {
        return sDvut_Ten;
    }

    public void setsDvut_Ten(String sDvut_Ten) {
        this.sDvut_Ten = sDvut_Ten;
    }

    public String getsMaxa() {
        return sMaxa;
    }

    public void setsMaxa(String sMaxa) {
        this.sMaxa = sMaxa;
    }

    public String getsMaxa_Ten() {
        return sMaxa_Ten;
    }

    public void setsMaxa_Ten(String sMaxa_Ten) {
        this.sMaxa_Ten = sMaxa_Ten;
    }

    public String getsTentt() {
        return sTentt;
    }

    public void setsTentt(String sTentt) {
        this.sTentt = sTentt;
    }

    public String getsTrangthaino() {
        return sTrangthaino;
    }

    public void setsTrangthaino(String sTrangthaino) {
        this.sTrangthaino = sTrangthaino;
    }

    public String getsMatt() {
        return sMatt;
    }

    public void setsMatt(String sMatt) {
        this.sMatt = sMatt;
    }

    public String getsLoaito() {
        return sLoaito;
    }

    public void setsLoaito(String sLoaito) {
        this.sLoaito = sLoaito;
    }

    public String getsSprd_Cd_Ten() {
        return sSprd_Cd_Ten;
    }

    public void setsSprd_Cd_Ten(String sSprd_Cd_Ten) {
        this.sSprd_Cd_Ten = sSprd_Cd_Ten;
    }

    public String getsTrangthai_Ten() {
        return sTrangthai_Ten;
    }

    public void setsTrangthai_Ten(String sTrangthai_Ten) {
        this.sTrangthai_Ten = sTrangthai_Ten;
    }

    public String getsTrangthaino_Ten() {
        return sTrangthaino_Ten;
    }

    public void setsTrangthaino_Ten(String sTrangthaino_Ten) {
        this.sTrangthaino_Ten = sTrangthaino_Ten;
    }

    public String getsTchat_No() {
        return sTchat_No;
    }

    public void setsTchat_No(String sTchat_No) {
        this.sTchat_No = sTchat_No;
    }

    public String getsTchat_No_Ten() {
        return sTchat_No_Ten;
    }

    public void setsTchat_No_Ten(String sTchat_No_Ten) {
        this.sTchat_No_Ten = sTchat_No_Ten;
    }

    public String getsLoaito_Ten() {
        return sLoaito_Ten;
    }

    public void setsLoaito_Ten(String sLoaito_Ten) {
        this.sLoaito_Ten = sLoaito_Ten;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Đối chiếu - Phân loại nợ -> Hiển thị phần Tổng cộng">
    public static class ViewTotalLoan {

        public String sPoscd;
        public String sPosDesc;
        public String sSlg_KH;
        public String sSlg_KU;
        public String sTongDN;
        public String sDnothan;
        public String sDnoqhan;
        public String sDnokhoanh;
        public String sTonglaiton;

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

        public String getsSlg_KH() {
            return sSlg_KH;
        }

        public void setsSlg_KH(String sSlg_KH) {
            this.sSlg_KH = sSlg_KH;
        }

        public String getsSlg_KU() {
            return sSlg_KU;
        }

        public void setsSlg_KU(String sSlg_KU) {
            this.sSlg_KU = sSlg_KU;
        }

        public String getsTongDN() {
            return sTongDN;
        }

        public void setsTongDN(String sTongDN) {
            this.sTongDN = sTongDN;
        }

        public String getsDnothan() {
            return sDnothan;
        }

        public void setsDnothan(String sDnothan) {
            this.sDnothan = sDnothan;
        }

        public String getsDnoqhan() {
            return sDnoqhan;
        }

        public void setsDnoqhan(String sDnoqhan) {
            this.sDnoqhan = sDnoqhan;
        }

        public String getsDnokhoanh() {
            return sDnokhoanh;
        }

        public void setsDnokhoanh(String sDnokhoanh) {
            this.sDnokhoanh = sDnokhoanh;
        }

        public String getsTonglaiton() {
            return sTonglaiton;
        }

        public void setsTonglaiton(String sTonglaiton) {
            this.sTonglaiton = sTonglaiton;
        }
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Đối chiếu - Phân loại nợ -> Lưu thông tin Phân loại nợ">
    public static class SavePLNO {

        public String sSoku;
        public String sMato;
        public BigDecimal bC_Kntn_Sodu;
        public BigDecimal bK_Kntn_Sodu;
        public BigDecimal bK_Kntn_Sd01;
        public BigDecimal bK_Kntn_Sd02;
        public BigDecimal bK_Kntn_Sd03;
        public BigDecimal bK_Kntn_Sd04;
        public BigDecimal bK_Kntn_Sd05;
        public BigDecimal bK_Kntn_Sd06;
        public BigDecimal bK_Kntn_Sd07;
        public BigDecimal bK_Kntn_Sd08;
        public BigDecimal bK_Kntn_Sd09;
        public BigDecimal bK_Kntn_Sd10;
        public BigDecimal bK_Kntn_Sd11;
        public String sK_Ngnhan_Kh;
        public String sQuanhe_Kh;
        public String sTrangthai;
        public BigDecimal bNogoc_Clech;
        public BigDecimal bNolai_Clech;
        public String sNgnhan_Clech;
        public String sNguoi_Pln;
        private Date dNgay_Pln;

        public String getsSoku() {
            return sSoku;
        }

        public void setsSoku(String sSoku) {
            this.sSoku = sSoku;
        }

        public String getsMato() {
            return sMato;
        }

        public void setsMato(String sMato) {
            this.sMato = sMato;
        }

        public BigDecimal getbC_Kntn_Sodu() {
            return bC_Kntn_Sodu;
        }

        public void setbC_Kntn_Sodu(BigDecimal bC_Kntn_Sodu) {
            this.bC_Kntn_Sodu = bC_Kntn_Sodu;
        }

        public BigDecimal getbK_Kntn_Sodu() {
            return bK_Kntn_Sodu;
        }

        public void setbK_Kntn_Sodu(BigDecimal bK_Kntn_Sodu) {
            this.bK_Kntn_Sodu = bK_Kntn_Sodu;
        }

        public BigDecimal getbK_Kntn_Sd01() {
            return bK_Kntn_Sd01;
        }

        public void setbK_Kntn_Sd01(BigDecimal bK_Kntn_Sd01) {
            this.bK_Kntn_Sd01 = bK_Kntn_Sd01;
        }

        public BigDecimal getbK_Kntn_Sd02() {
            return bK_Kntn_Sd02;
        }

        public void setbK_Kntn_Sd02(BigDecimal bK_Kntn_Sd02) {
            this.bK_Kntn_Sd02 = bK_Kntn_Sd02;
        }

        public BigDecimal getbK_Kntn_Sd03() {
            return bK_Kntn_Sd03;
        }

        public void setbK_Kntn_Sd03(BigDecimal bK_Kntn_Sd03) {
            this.bK_Kntn_Sd03 = bK_Kntn_Sd03;
        }

        public BigDecimal getbK_Kntn_Sd04() {
            return bK_Kntn_Sd04;
        }

        public void setbK_Kntn_Sd04(BigDecimal bK_Kntn_Sd04) {
            this.bK_Kntn_Sd04 = bK_Kntn_Sd04;
        }

        public BigDecimal getbK_Kntn_Sd05() {
            return bK_Kntn_Sd05;
        }

        public void setbK_Kntn_Sd05(BigDecimal bK_Kntn_Sd05) {
            this.bK_Kntn_Sd05 = bK_Kntn_Sd05;
        }

        public BigDecimal getbK_Kntn_Sd06() {
            return bK_Kntn_Sd06;
        }

        public void setbK_Kntn_Sd06(BigDecimal bK_Kntn_Sd06) {
            this.bK_Kntn_Sd06 = bK_Kntn_Sd06;
        }

        public BigDecimal getbK_Kntn_Sd07() {
            return bK_Kntn_Sd07;
        }

        public void setbK_Kntn_Sd07(BigDecimal bK_Kntn_Sd07) {
            this.bK_Kntn_Sd07 = bK_Kntn_Sd07;
        }

        public BigDecimal getbK_Kntn_Sd08() {
            return bK_Kntn_Sd08;
        }

        public void setbK_Kntn_Sd08(BigDecimal bK_Kntn_Sd08) {
            this.bK_Kntn_Sd08 = bK_Kntn_Sd08;
        }

        public BigDecimal getbK_Kntn_Sd09() {
            return bK_Kntn_Sd09;
        }

        public void setbK_Kntn_Sd09(BigDecimal bK_Kntn_Sd09) {
            this.bK_Kntn_Sd09 = bK_Kntn_Sd09;
        }

        public BigDecimal getbK_Kntn_Sd10() {
            return bK_Kntn_Sd10;
        }

        public void setbK_Kntn_Sd10(BigDecimal bK_Kntn_Sd10) {
            this.bK_Kntn_Sd10 = bK_Kntn_Sd10;
        }

        public BigDecimal getbK_Kntn_Sd11() {
            return bK_Kntn_Sd11;
        }

        public void setbK_Kntn_Sd11(BigDecimal bK_Kntn_Sd11) {
            this.bK_Kntn_Sd11 = bK_Kntn_Sd11;
        }

        public String getsK_Ngnhan_Kh() {
            return sK_Ngnhan_Kh;
        }

        public void setsK_Ngnhan_Kh(String sK_Ngnhan_Kh) {
            this.sK_Ngnhan_Kh = sK_Ngnhan_Kh;
        }

        public String getsQuanhe_Kh() {
            return sQuanhe_Kh;
        }

        public void setsQuanhe_Kh(String sQuanhe_Kh) {
            this.sQuanhe_Kh = sQuanhe_Kh;
        }

        public String getsTrangthai() {
            return sTrangthai;
        }

        public void setsTrangthai(String sTrangthai) {
            this.sTrangthai = sTrangthai;
        }

        public BigDecimal getbNogoc_Clech() {
            return bNogoc_Clech;
        }

        public void setbNogoc_Clech(BigDecimal bNogoc_Clech) {
            this.bNogoc_Clech = bNogoc_Clech;
        }

        public BigDecimal getbNolai_Clech() {
            return bNolai_Clech;
        }

        public void setbNolai_Clech(BigDecimal bNolai_Clech) {
            this.bNolai_Clech = bNolai_Clech;
        }

        public String getsNgnhan_Clech() {
            return sNgnhan_Clech;
        }

        public void setsNgnhan_Clech(String sNgnhan_Clech) {
            this.sNgnhan_Clech = sNgnhan_Clech;
        }

        public String getsNguoi_Pln() {
            return sNguoi_Pln;
        }

        public void setsNguoi_Pln(String sNguoi_Pln) {
            this.sNguoi_Pln = sNguoi_Pln;
        }

        public Date getdNgay_Pln() {
            return dNgay_Pln;
        }

        public void setdNgay_Pln(Date dNgay_Pln) {
            this.dNgay_Pln = dNgay_Pln;
        }
    }
    //</editor-fold>
}
