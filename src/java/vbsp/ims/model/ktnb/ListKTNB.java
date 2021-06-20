/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.model.ktnb;

/**
 *
 * @author Administrator
 */
public class ListKTNB {
    private String MABC;
    private String TENVT;
    private String MOTA;
    private String KYBC;
    private String LINKBC;

    public ListKTNB() {
    }

    public ListKTNB(String MABC, String TENVT, String MOTA, String KYBC, String LINKBC) {
        this.MABC = MABC;
        this.TENVT = TENVT;
        this.MOTA = MOTA;
        this.KYBC = KYBC;
        this.LINKBC = LINKBC;
    }

    public String getMABC() {
        return MABC;
    }

    public void setMABC(String MABC) {
        this.MABC = MABC;
    }

    public String getTENVT() {
        return TENVT;
    }

    public void setTENVT(String TENVT) {
        this.TENVT = TENVT;
    }

    public String getMOTA() {
        return MOTA;
    }

    public void setMOTA(String MOTA) {
        this.MOTA = MOTA;
    }

    public String getKYBC() {
        return KYBC;
    }

    public void setKYBC(String KYBC) {
        this.KYBC = KYBC;
    }

    public String getLINKBC() {
        return LINKBC;
    }

    public void setLINKBC(String LINKBC) {
        this.LINKBC = LINKBC;
    }
    
}
