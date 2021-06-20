/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vbsp.ims.ctieu;

/**
 *
 * @author Administrator
 */
public class ChiTieu {
    private String CHITIEU;
    private String NAME;

    public ChiTieu() {
    }

    public ChiTieu(String CHITIEU, String NAME) {
        this.CHITIEU = CHITIEU;
        this.NAME = NAME;
    }

    public String getCHITIEU() {
        return CHITIEU;
    }

    public void setCHITIEU(String CHITIEU) {
        this.CHITIEU = CHITIEU;
    }

    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }
    
    
}
