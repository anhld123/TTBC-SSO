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
public class kiemtract {
    private String POS_CD,DESCRIPT;
    private Double VALUE_1,VALUE_2,DIFFER_AMT;

    public kiemtract() {
    }

    public kiemtract(String POS_CD, String DESCRIPT, Double VALUE_1, Double VALUE_2, Double DIFFER_AMT) {
        this.POS_CD = POS_CD;
        this.DESCRIPT = DESCRIPT;
        this.VALUE_1 = VALUE_1;
        this.VALUE_2 = VALUE_2;
        this.DIFFER_AMT = DIFFER_AMT;
    }

    public String getPOS_CD() {
        return POS_CD;
    }

    public void setPOS_CD(String POS_CD) {
        this.POS_CD = POS_CD;
    }

    public String getDESCRIPT() {
        return DESCRIPT;
    }

    public void setDESCRIPT(String DESCRIPT) {
        this.DESCRIPT = DESCRIPT;
    }

    public Double getVALUE_1() {
        return VALUE_1;
    }

    public void setVALUE_1(Double VALUE_1) {
        this.VALUE_1 = VALUE_1;
    }

    public Double getVALUE_2() {
        return VALUE_2;
    }

    public void setVALUE_2(Double VALUE_2) {
        this.VALUE_2 = VALUE_2;
    }

    public Double getDIFFER_AMT() {
        return DIFFER_AMT;
    }

    public void setDIFFER_AMT(Double DIFFER_AMT) {
        this.DIFFER_AMT = DIFFER_AMT;
    }
    
    
}
