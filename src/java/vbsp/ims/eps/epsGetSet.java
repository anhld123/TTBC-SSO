/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.eps;

/**
 *
 * @author ITCVBSP56
 */
public class epsGetSet {

    private String KHOA;
    private String MACN;
    private String MAPGD;
    private String NGAYBC;
    private String NGUOI_NHAP;
    private String NGAY_NHAP;
    private String D1;
    private String D2;
    private String D3;

    public epsGetSet() {
    }

    public epsGetSet(String KHOA, String MACN, String MAPGD, String NGAYBC, String NGUOI_NHAP, String NGAY_NHAP, String D1, String D2, String D3) {
        this.KHOA = KHOA;
        this.MACN = MACN;
        this.MAPGD = MAPGD;
        this.NGAYBC = NGAYBC;
        this.NGUOI_NHAP = NGUOI_NHAP;
        this.NGAY_NHAP = NGAY_NHAP;
        this.D1 = D1;
        this.D2 = D2;
        this.D3 = D3;
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

    public String getNGAYBC() {
        return NGAYBC;
    }

    public void setNGAYBC(String NGAYBC) {
        this.NGAYBC = NGAYBC;
    }

    public String getNGUOI_NHAP() {
        return NGUOI_NHAP;
    }

    public void setNGUOI_NHAP(String NGUOI_NHAP) {
        this.NGUOI_NHAP = NGUOI_NHAP;
    }

    public String getNGAY_NHAP() {
        return NGAY_NHAP;
    }

    public void setNGAY_NHAP(String NGAY_NHAP) {
        this.NGAY_NHAP = NGAY_NHAP;
    }

    public String getD1() {
        return D1;
    }

    public void setD1(String D1) {
        this.D1 = D1;
    }

    public String getD2() {
        return D2;
    }

    public void setD2(String D2) {
        this.D2 = D2;
    }

    public String getD3() {
        return D3;
    }

    public void setD3(String D3) {
        this.D3 = D3;
    }
    
    
}
