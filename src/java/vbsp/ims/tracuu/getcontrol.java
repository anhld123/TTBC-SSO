/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.tracuu;

import java.util.List;

/**
 *
 * @author Administrator
 */
public class getcontrol {
    private String LControl;
    private List<Tracuu_Utill> lscontrol;

    public getcontrol(String LControl, List<Tracuu_Utill> lscontrol) {
        this.LControl = LControl;
        this.lscontrol = lscontrol;
    }

    public getcontrol() {
    }

    public String getLControl() {
        return LControl;
    }

    public void setLControl(String LControl) {
        this.LControl = LControl;
    }

    public List<Tracuu_Utill> getLscontrol() {
        return lscontrol;
    }

    public void setLscontrol(List<Tracuu_Utill> lscontrol) {
        this.lscontrol = lscontrol;
    }
    
}
