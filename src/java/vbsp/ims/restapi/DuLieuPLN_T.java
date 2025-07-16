/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.restapi;

import java.util.ArrayList;
import java.util.Date;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 *
 * @author HP
 */
@XmlAccessorType(XmlAccessType.FIELD)

public class DuLieuPLN_T {

    private int stt;
    private String plnMacn;
    private String plnMapgd;
    private String plnMaxa;
    private String plnNguonvon;
    private String plnNguonvonTen;
    private String plnDvut;
    private String plnDvutTen;
    private String plnChtrinh;
    private String plnChtrinhTen;
    private String plnChtrinhTenvt;
    private String plnMato;
    private String plnTentt;
    private String plnMakh;
    private String plnTenkh;
    private String plnSoku;
    private int plnDnothan;
    private int plnDnoqhan;
    private int plnDnokhoanh;
    private int plnTonglaiton;
    private int plnCKntnSodu;
    private int plnKKntnSodu;
    private String ngnhanKckntn;
    private String kNgnhanKh;
    private String plnQuanheKh;
    private String plnTrangthai;
    private int plnNogocClech;
    private int plnNolaiClech;
    private String plnNgnhanClech;
    private String plnTtMonvay;
    private String plnNgaybc;
    private String plnNguoiPln;
    private String plnNgayPln;
    private String plnNguyennhanC2;
    private String kKntnSodu01;
    private String kKntnSodu02;
    private String kKntnSodu03;
    private String kKntnSodu04;
    private String kKntnSodu05;
    private String kKntnSodu06;
    private String kKntnSodu07;
    private String kKntnSodu08;
    private String kKntnSodu09;
    private String kKntnSodu10;
    private String kKntnSodu11;
    private String ngnhanKntn;

    public String getNgnhanKntn() {
        return ngnhanKntn;
    }

    public void setNgnhanKntn(String ngnhanKntn) {
        this.ngnhanKntn = ngnhanKntn;
    }

    public String getkKntnSodu01() {
        return kKntnSodu01;
    }

    public void setkKntnSodu01(String kKntnSodu01) {
        this.kKntnSodu01 = kKntnSodu01;
    }

    public String getkKntnSodu02() {
        return kKntnSodu02;
    }

    public void setkKntnSodu02(String kKntnSodu02) {
        this.kKntnSodu02 = kKntnSodu02;
    }

    public String getkKntnSodu03() {
        return kKntnSodu03;
    }

    public void setkKntnSodu03(String kKntnSodu03) {
        this.kKntnSodu03 = kKntnSodu03;
    }

    public String getkKntnSodu04() {
        return kKntnSodu04;
    }

    public void setkKntnSodu04(String kKntnSodu04) {
        this.kKntnSodu04 = kKntnSodu04;
    }

    public String getkKntnSodu05() {
        return kKntnSodu05;
    }

    public void setkKntnSodu05(String kKntnSodu05) {
        this.kKntnSodu05 = kKntnSodu05;
    }

    public String getkKntnSodu06() {
        return kKntnSodu06;
    }

    public void setkKntnSodu06(String kKntnSodu06) {
        this.kKntnSodu06 = kKntnSodu06;
    }

    public String getkKntnSodu07() {
        return kKntnSodu07;
    }

    public void setkKntnSodu07(String kKntnSodu07) {
        this.kKntnSodu07 = kKntnSodu07;
    }

    public String getkKntnSodu08() {
        return kKntnSodu08;
    }

    public void setkKntnSodu08(String kKntnSodu08) {
        this.kKntnSodu08 = kKntnSodu08;
    }

    public String getkKntnSodu09() {
        return kKntnSodu09;
    }

    public void setkKntnSodu09(String kKntnSodu09) {
        this.kKntnSodu09 = kKntnSodu09;
    }

    public String getkKntnSodu10() {
        return kKntnSodu10;
    }

    public void setkKntnSodu10(String kKntnSodu10) {
        this.kKntnSodu10 = kKntnSodu10;
    }

    public String getkKntnSodu11() {
        return kKntnSodu11;
    }

    public void setkKntnSodu11(String kKntnSodu11) {
        this.kKntnSodu11 = kKntnSodu11;
    }

    private int plnTongDno;
    // Getter và Setter đầy đủ

    public int getStt() {
        return stt;
    }

    public void setStt(int stt) {
        this.stt = stt;
    }

    public String getPlnMacn() {
        return plnMacn;
    }

    public void setPlnMacn(String plnMacn) {
        this.plnMacn = plnMacn;
    }

    public String getPlnMapgd() {
        return plnMapgd;
    }

    public void setPlnMapgd(String plnMapgd) {
        this.plnMapgd = plnMapgd;
    }

    public String getPlnMaxa() {
        return plnMaxa;
    }

    public void setPlnMaxa(String plnMaxa) {
        this.plnMaxa = plnMaxa;
    }

    public String getPlnNguonvon() {
        return plnNguonvon;
    }

    public void setPlnNguonvon(String plnNguonvon) {
        this.plnNguonvon = plnNguonvon;
    }

    public String getPlnNguonvonTen() {
        return plnNguonvonTen;
    }

    public void setPlnNguonvonTen(String plnNguonvonTen) {
        this.plnNguonvonTen = plnNguonvonTen;
    }

    public String getPlnDvut() {
        return plnDvut;
    }

    public void setPlnDvut(String plnDvut) {
        this.plnDvut = plnDvut;
    }

    public String getPlnDvutTen() {
        return plnDvutTen;
    }

    public void setPlnDvutTen(String plnDvutTen) {
        this.plnDvutTen = plnDvutTen;
    }

    public String getPlnChtrinh() {
        return plnChtrinh;
    }

    public void setPlnChtrinh(String plnChtrinh) {
        this.plnChtrinh = plnChtrinh;
    }

    public String getPlnChtrinhTen() {
        return plnChtrinhTen;
    }

    public void setPlnChtrinhTen(String plnChtrinhTen) {
        this.plnChtrinhTen = plnChtrinhTen;
    }

    public String getPlnChtrinhTenvt() {
        return plnChtrinhTenvt;
    }

    public void setPlnChtrinhTenvt(String plnChtrinhTenvt) {
        this.plnChtrinhTenvt = plnChtrinhTenvt;
    }

    public String getPlnMato() {
        return plnMato;
    }

    public void setPlnMato(String plnMato) {
        this.plnMato = plnMato;
    }

    public String getPlnTentt() {
        return plnTentt;
    }

    public void setPlnTentt(String plnTentt) {
        this.plnTentt = plnTentt;
    }

    public String getPlnMakh() {
        return plnMakh;
    }

    public void setPlnMakh(String plnMakh) {
        this.plnMakh = plnMakh;
    }

    public String getPlnTenkh() {
        return plnTenkh;
    }

    public void setPlnTenkh(String plnTenkh) {
        this.plnTenkh = plnTenkh;
    }

    public String getPlnSoku() {
        return plnSoku;
    }

    public void setPlnSoku(String plnSoku) {
        this.plnSoku = plnSoku;
    }

    public int getPlnDnothan() {
        return plnDnothan;
    }

    public void setPlnDnothan(int plnDnothan) {
        this.plnDnothan = plnDnothan;
    }

    public int getPlnDnoqhan() {
        return plnDnoqhan;
    }

    public void setPlnDnoqhan(int plnDnoqhan) {
        this.plnDnoqhan = plnDnoqhan;
    }

    public int getPlnDnokhoanh() {
        return plnDnokhoanh;
    }

    public void setPlnDnokhoanh(int plnDnokhoanh) {
        this.plnDnokhoanh = plnDnokhoanh;
    }

    public int getPlnTonglaiton() {
        return plnTonglaiton;
    }

    public void setPlnTonglaiton(int plnTonglaiton) {
        this.plnTonglaiton = plnTonglaiton;
    }

    public int getPlnCKntnSodu() {
        return plnCKntnSodu;
    }

    public void setPlnCKntnSodu(int plnCKntnSodu) {
        this.plnCKntnSodu = plnCKntnSodu;
    }

    public int getPlnKKntnSodu() {
        return plnKKntnSodu;
    }

    public void setPlnKKntnSodu(int plnKKntnSodu) {
        this.plnKKntnSodu = plnKKntnSodu;
    }

    public String getNgnhanKckntn() {
        return ngnhanKckntn;
    }

    public void setNgnhanKckntn(String ngnhanKckntn) {
        this.ngnhanKckntn = ngnhanKckntn;
    }

    public String getkNgnhanKh() {
        return kNgnhanKh;
    }

    public void setkNgnhanKh(String kNgnhanKh) {
        this.kNgnhanKh = kNgnhanKh;
    }

    public String getPlnQuanheKh() {
        return plnQuanheKh;
    }

    public void setPlnQuanheKh(String plnQuanheKh) {
        this.plnQuanheKh = plnQuanheKh;
    }

    public String getPlnTrangthai() {
        return plnTrangthai;
    }

    public void setPlnTrangthai(String plnTrangthai) {
        this.plnTrangthai = plnTrangthai;
    }

    public int getPlnNogocClech() {
        return plnNogocClech;
    }

    public void setPlnNogocClech(int plnNogocClech) {
        this.plnNogocClech = plnNogocClech;
    }

    public int getPlnNolaiClech() {
        return plnNolaiClech;
    }

    public void setPlnNolaiClech(int plnNolaiClech) {
        this.plnNolaiClech = plnNolaiClech;
    }

    public String getPlnNgnhanClech() {
        return plnNgnhanClech;
    }

    public void setPlnNgnhanClech(String plnNgnhanClech) {
        this.plnNgnhanClech = plnNgnhanClech;
    }

    public String getPlnTtMonvay() {
        return plnTtMonvay;
    }

    public void setPlnTtMonvay(String plnTtMonvay) {
        this.plnTtMonvay = plnTtMonvay;
    }

    public String getPlnNgaybc() {
        return plnNgaybc;
    }

    public void setPlnNgaybc(String plnNgaybc) {
        this.plnNgaybc = plnNgaybc;
    }

    public String getPlnNguoiPln() {
        return plnNguoiPln;
    }

    public void setPlnNguoiPln(String plnNguoiPln) {
        this.plnNguoiPln = plnNguoiPln;
    }

    public String getPlnNgayPln() {
        return plnNgayPln;
    }

    public void setPlnNgayPln(String plnNgayPln) {
        this.plnNgayPln = plnNgayPln;
    }

    public String getPlnNguyennhanC2() {
        return plnNguyennhanC2;
    }

    public void setPlnNguyennhanC2(String plnNguyennhanC2) {
        this.plnNguyennhanC2 = plnNguyennhanC2;
    }

    public int getPlnTongDno() {
        return plnTongDno;
    }

    public void setPlnTongDno(int plnTongDno) {
        this.plnTongDno = plnTongDno;
    }

}
