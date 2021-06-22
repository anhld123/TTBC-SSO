/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.khnv2021;

public class PosClass {
    private String PosCode;
    private String PosName;

    public PosClass() {
    }

    public PosClass(String PosCode, String PosName) {
        this.PosCode = PosCode;
        this.PosName = PosName;
    }

    public String getPosCode() {
        return PosCode;
    }

    public void setPosCode(String PosCode) {
        this.PosCode = PosCode;
    }

    public String getPosName() {
        return PosName;
    }

    public void setPosName(String PosName) {
        this.PosName = PosName;
    }  
}
