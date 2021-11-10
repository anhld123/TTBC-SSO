/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.huydongtk;

/**
 *
 * @author WELCOME
 */

public class clsCanBo {
   
    private String MaCB;
    private String TenCB;

    public String getMaCB() {
        return MaCB;
    }

    public void setMaCB(String MaCB) {
        this.MaCB = MaCB;
    }

    public String getTenCB() {
        return TenCB;
    }

    public void setTenCB(String TenCB) {
        this.TenCB = TenCB;
    }

    public clsCanBo() {
    }

    public clsCanBo(String MaCB, String TenCB) {
        this.MaCB = MaCB;
        this.TenCB = TenCB;
    }
    
    
}
