/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.model;

import com.opensymphony.xwork2.validator.annotations.DoubleRangeFieldValidator;
import java.math.BigDecimal;
import com.opensymphony.xwork2.validator.annotations.VisitorFieldValidator;

/**
 *
 * @author BAOANH
 */
public class ModelCommune {
    public String sPoscd;
    public String sCommuneid;
    public String sReportdt;
    public String sCode;

   
    public double bValue;
    public double bMark;
    public String  sMainpos;

    public String getsMainpos() {
        return sMainpos;
    }
    public String sSubcode;
    public String sUserid;
    public String sDescription;
//    MARK_STANDARD, C.VALUE, C.MARK MARK_VALUE
    public String sMarkStandard;
    public String sMarkValue;
    //<editor-fold defaultstate="collapsed" desc="Get set">
    
     public double getbValue() {
        return bValue;
    }

    public void setbValue(double bValue) {
        this.bValue = bValue;
    }
    
//    @DoubleRangeFieldValidator(minInclusive = "0", maxInclusive = "10",message = "Điểm không khợp lệ")
    public double getbMark() {
        return bMark;
    }

    public void setbMark(double bMark) {
        this.bMark = bMark;
    }
    public String getsDescription() {
        return sDescription;
    }

    public void setsDescription(String sDescription) {
        this.sDescription = sDescription;
    }

    public String getsMarkStandard() {
        return sMarkStandard;
    }

    public void setsMarkStandard(String sMarkStandard) {
        this.sMarkStandard = sMarkStandard;
    }

    public String getsMarkValue() {
        return sMarkValue;
    }

    public void setsMarkValue(String sMarkValue) {
        this.sMarkValue = sMarkValue;
    }
    
    
    public String getsPoscd() {
        return sPoscd;
    }
    
    public void setsPoscd(String sPoscd) {
        this.sPoscd = sPoscd;
    }
    
    public String getsCommuneid() {
        return sCommuneid;
    }
    
    public void setsCommuneid(String sCommuneid) {
        this.sCommuneid = sCommuneid;
    }
    
    public String getsReportdt() {
        return sReportdt;
    }
    
    public void setsReportdt(String sReportdt) {
        this.sReportdt = sReportdt;
    }
    
    public String getsCode() {
        return sCode;
    }
    
    public void setsCode(String sCode) {
        this.sCode = sCode;
    }
    
    
    
    public void setsMainpos(String sMainpos) {
        this.sMainpos = sMainpos;
    }
    
    public String getsSubcode() {
        return sSubcode;
    }
    
    public void setsSubcode(String sSubcode) {
        this.sSubcode = sSubcode;
    }
    
    public String getsUserid() {
        return sUserid;
    }
    
    public void setsUserid(String sUserid) {
        this.sUserid = sUserid;
    }
//</editor-fold>
    
}
