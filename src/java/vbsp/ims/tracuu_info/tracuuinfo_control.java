/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.tracuu_info;

/**
 *
 * @author Administrator
 */
public class tracuuinfo_control {
    private String GIATRI;
    private String HIENTHI;

    public tracuuinfo_control() {
    }

    public tracuuinfo_control(String GIATRI, String HIENTHI) {
        this.GIATRI = GIATRI;
        this.HIENTHI = HIENTHI;
    }

    public String getGIATRI() {
        return GIATRI;
    }

    public void setGIATRI(String GIATRI) {
        this.GIATRI = GIATRI;
    }

    public String getHIENTHI() {
        return HIENTHI;
    }

    public void setHIENTHI(String HIENTHI) {
        this.HIENTHI = HIENTHI;
    }

   
}
