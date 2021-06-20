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
public class BaoCaoCT {
    private String MABC;
    private String TENBC;

    public BaoCaoCT() {
    }

    public BaoCaoCT(String MABC, String TENBC) {
        this.MABC = MABC;
        this.TENBC = TENBC;
    }

    public String getMABC() {
        return MABC;
    }

    public void setMABC(String MABC) {
        this.MABC = MABC;
    }

    public String getTENBC() {
        return TENBC;
    }

    public void setTENBC(String TENBC) {
        this.TENBC = TENBC;
    }

      
}
