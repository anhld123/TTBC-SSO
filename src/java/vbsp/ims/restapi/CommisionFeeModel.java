/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.restapi;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 *
 * @author HP
 */

@XmlAccessorType (XmlAccessType.FIELD)
public class CommisionFeeModel {
    private String posCode;
    private String refNo;
    private String valDate;
    private String legacyAc;
    private String accountPosCode;
    private String flagDRCR;
    private int amount;
    private String reason;
    private String currency;

    public String getPosCode() {
        return posCode;
    }

    public void setPosCode(String posCode) {
        this.posCode = posCode;
    }

    public String getRefNo() {
        return refNo;
    }

    public void setRefNo(String refNo) {
        this.refNo = refNo;
    }

    public String getValDate() {
        return valDate;
    }

    public void setValDate(String valDate) {
        this.valDate = valDate;
    }

    public String getLegacyAc() {
        return legacyAc;
    }

    public void setLegacyAc(String legacyAc) {
        this.legacyAc = legacyAc;
    }

    public String getAccountPosCode() {
        return accountPosCode;
    }

    public void setAccountPosCode(String accountPosCode) {
        this.accountPosCode = accountPosCode;
    }

    public String getFlagDRCR() {
        return flagDRCR;
    }

    public void setFlagDRCR(String flagDRCR) {
        this.flagDRCR = flagDRCR;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
    
    @Override
    public String toString()
    {
        return posCode + "~" + refNo + "~" + valDate + "~" + legacyAc + "~" + accountPosCode + "~" + flagDRCR +"~" +  String.valueOf(amount) +"~" +  reason +"~" +  currency + "~" ;
    }
    
}

