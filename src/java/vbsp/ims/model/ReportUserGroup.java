/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.model;

/**
 *
 * @author HP
 */
public class ReportUserGroup {
    
    private String NhomND;
    private String NhomBC;
    private String MaBC;
    private String TenBC;
    private Integer QuyenTC;
    
    public ReportUserGroup(){}

    public ReportUserGroup(String NhomND, String NhomBC, String MaBC, String TenBC, Integer QuyenTC) {
        this.NhomND = NhomND;
        this.NhomBC = NhomBC;
        this.MaBC = MaBC;
        this.TenBC = TenBC;
        this.QuyenTC = QuyenTC;
    }

    public String getNhomND() {
        return NhomND;
    }

    public void setNhomND(String NhomND) {
        this.NhomND = NhomND;
    }

    public String getNhomBC() {
        return NhomBC;
    }

    public void setNhomBC(String NhomBC) {
        this.NhomBC = NhomBC;
    }

    public String getMaBC() {
        return MaBC;
    }

    public void setMaBC(String MaBC) {
        this.MaBC = MaBC;
    }

    public String getTenBC() {
        return TenBC;
    }

    public void setTenBC(String TenBC) {
        this.TenBC = TenBC;
    }

    public Integer getQuyenTC() {
        return QuyenTC;
    }

    public void setQuyenTC(Integer QuyenTC) {
        this.QuyenTC = QuyenTC;
    }
    
    
    
}

