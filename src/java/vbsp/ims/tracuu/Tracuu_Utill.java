/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.tracuu;

/**
 *
 * @author Administrator
 */
public class Tracuu_Utill {
    private String TENTRUONG;
    private String GIATRI;

    public Tracuu_Utill() {
    }

    public Tracuu_Utill(String TENTRUONG, String GIATRI) {
        this.TENTRUONG = TENTRUONG;
        this.GIATRI = GIATRI;
    }

    public String getTENTRUONG() {
        return TENTRUONG;
    }

    public void setTENTRUONG(String TENTRUONG) {
        this.TENTRUONG = TENTRUONG;
    }

    public String getGIATRI() {
        return GIATRI;
    }

    public void setGIATRI(String GIATRI) {
        this.GIATRI = GIATRI;
    }
    
}
