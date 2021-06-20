/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.branch;

import java.util.List;

/**
 *
 * @author BAOANH
 */
public class LoadEditMauView {
    private String tenmau;
    private String capbc;
    private String donvitinh;
    private boolean copydl;
    private boolean dongbo_dl;
    private String nhombc;
    
    private List<ModelParameter> lstParameter;

    public boolean isDongbo_dl() {
        return dongbo_dl;
    }

    public void setDongbo_dl(boolean dongbo_dl) {
        this.dongbo_dl = dongbo_dl;
    }

    
    public boolean isCopydl() {
        return copydl;
    }

    public void setCopydl(boolean copydl) {
        this.copydl = copydl;
    }
    
    public String getDonvitinh() {
        return donvitinh;
    }

    public void setDonvitinh(String donvitinh) {
        this.donvitinh = donvitinh;
    }

    
    public String getTenmau() {
        return tenmau;
    }

    public void setTenmau(String tenmau) {
        this.tenmau = tenmau;
    }

    public String getCapbc() {
        return capbc;
    }

    public void setCapbc(String capbc) {
        this.capbc = capbc;
    }

    public List<ModelParameter> getLstParameter() {
        return lstParameter;
    }

    public void setLstParameter(List<ModelParameter> lstParameter) {
        this.lstParameter = lstParameter;
    }

    public String getNhombc() {
        return nhombc;
    }

    public void setNhombc(String nhombc) {
        this.nhombc = nhombc;
    }
    
    
}
