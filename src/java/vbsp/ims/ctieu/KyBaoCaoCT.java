package vbsp.ims.ctieu;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Admin
 */
public class KyBaoCaoCT {
    private String MAKY;
    private String TENKY;

    public KyBaoCaoCT() {
    }

    public KyBaoCaoCT(String MAKY, String TENKY) {
        this.MAKY = MAKY;
        this.TENKY = TENKY;
    }

    public String getMAKY() {
        return MAKY;
    }

    public void setMAKY(String MAKY) {
        this.MAKY = MAKY;
    }

    public String getTENKY() {
        return TENKY;
    }

    public void setTENKY(String TENKY) {
        this.TENKY = TENKY;
    }
}
