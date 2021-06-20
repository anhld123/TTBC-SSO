/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.model;

/**
 *
 * @author Administrator
 */
public class DMQT {
    private String DM_MABC,DM_TENVT,DM_MOTA,DM_CAPBC,APPLY_FLG,DM_INPUT,DM_LINKBC;

    public DMQT(String DM_MABC, String DM_TENVT, String DM_MOTA, String DM_CAPBC, String APPLY_FLG, String DM_INPUT,String DM_LINKBC) {
        this.DM_MABC = DM_MABC;
        this.DM_TENVT = DM_TENVT;
        this.DM_MOTA = DM_MOTA;
        this.DM_CAPBC = DM_CAPBC;
        this.APPLY_FLG = APPLY_FLG;
        this.DM_INPUT = DM_INPUT;
        this.DM_LINKBC = DM_LINKBC;
    }

    public DMQT() {
    }

    public String getDM_MABC() {
        return DM_MABC;
    }

    public void setDM_MABC(String DM_MABC) {
        this.DM_MABC = DM_MABC;
    }

    public String getDM_TENVT() {
        return DM_TENVT;
    }

    public void setDM_TENVT(String DM_TENVT) {
        this.DM_TENVT = DM_TENVT;
    }

    public String getDM_MOTA() {
        return DM_MOTA;
    }

    public void setDM_MOTA(String DM_MOTA) {
        this.DM_MOTA = DM_MOTA;
    }

    public String getDM_CAPBC() {
        return DM_CAPBC;
    }

    public void setDM_CAPBC(String DM_CAPBC) {
        this.DM_CAPBC = DM_CAPBC;
    }

    public String getAPPLY_FLG() {
        return APPLY_FLG;
    }

    public void setAPPLY_FLG(String APPLY_FLG) {
        this.APPLY_FLG = APPLY_FLG;
    }

    public String getDM_INPUT() {
        return DM_INPUT;
    }

    public void setDM_INPUT(String DM_INPUT) {
        this.DM_INPUT = DM_INPUT;
    }

    public String getDM_LINKBC() {
        return DM_LINKBC;
    }

    public void setDM_LINKBC(String DM_LINKBC) {
        this.DM_LINKBC = DM_LINKBC;
    }
    
}
