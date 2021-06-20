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
public class DonViCT {
   private String MAPGD;
    private String TENPGD;

    public DonViCT() {
    }

    public DonViCT(String MAPGD, String TENPGD) {
        this.MAPGD = MAPGD;
        this.TENPGD = TENPGD;
    }

    public String getMAPGD() {
        return MAPGD;
    }

    public void setMAPGD(String MAPGD) {
        this.MAPGD = MAPGD;
    }

    public String getTENPGD() {
        return TENPGD;
    }

    public void setTENPGD(String TENPGD) {
        this.TENPGD = TENPGD;
    }

    
}
