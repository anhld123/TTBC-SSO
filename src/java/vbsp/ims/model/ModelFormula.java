/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.model;

/**
 *
 * @author lion
 */
public class ModelFormula {
    private String sData;
    private int nID;
    private String sName;
    private String sColor;
    
    public ModelFormula()
    {
        
    }
    public ModelFormula(String sData,int nID, String sName)
    {
        this.sData=sData;
        this.nID=nID;
        this.sName=sName;
    }
    public String getsData() {
        return sData;
    }

    public void setsData(String sData) {
        this.sData = sData;
    }

    public int getnID() {
        return nID;
    }

    public void setnID(int nID) {
        this.nID = nID;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public String getsColor() {
        return sColor;
    }

    public void setsColor(String sColor) {
        this.sColor = sColor;
    }
    
   
    
}
