/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.restapi;

/**
 *
 * @author HP
 */

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
 
//@XmlRootElement(name = "dulieuNT")
@XmlAccessorType (XmlAccessType.FIELD)

public class LockSendModel {
 private String mainPos;
 private String posCode;
 private String posName;
 private String posFlag;
 private String reportDate;
 private String status;
 private String makerId;
 private String makerDate;
 private String updateId;
 private String updateDate;
 private float loanTotal;
 private float prinTotal;
 private float intTotal;
 private float intDeductionTotal;
 private float deductionLoanTotal;
 private float deductionIntTotal;
 private float noDeductionLoanTotal;
 private float noDeductionIntTotal;

    public String getPosName() {
        return posName;
    }

    public void setPosName(String posName) {
        this.posName = posName;
    }
 
 

    public String getMainPos() {
        return mainPos;
    }

    public void setMainPos(String mainPos) {
        this.mainPos = mainPos;
    }

    public String getPosCode() {
        return posCode;
    }

    public void setPosCode(String posCode) {
        this.posCode = posCode;
    }

    public String getPosFlag() {
        return posFlag;
    }

    public void setPosFlag(String posFlag) {
        this.posFlag = posFlag;
    }

    public String getReportDate() {
        return reportDate;
    }

    public void setReportDate(String reportDate) {
        this.reportDate = reportDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMakerId() {
        return makerId;
    }

    public void setMakerId(String makerId) {
        this.makerId = makerId;
    }

    public String getMakerDate() {
        return makerDate;
    }

    public void setMakerDate(String makerDate) {
        this.makerDate = makerDate;
    }

    public String getUpdateId() {
        return updateId;
    }

    public void setUpdateId(String updateId) {
        this.updateId = updateId;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public float getLoanTotal() {
        return loanTotal;
    }

    public void setLoanTotal(float loanTotal) {
        this.loanTotal = loanTotal;
    }

    public float getPrinTotal() {
        return prinTotal;
    }

    public void setPrinTotal(float prinTotal) {
        this.prinTotal = prinTotal;
    }

    public float getIntTotal() {
        return intTotal;
    }

    public void setIntTotal(float intTotal) {
        this.intTotal = intTotal;
    }

    public float getIntDeductionTotal() {
        return intDeductionTotal;
    }

    public void setIntDeductionTotal(float intDeductionTotal) {
        this.intDeductionTotal = intDeductionTotal;
    }

    public float getDeductionLoanTotal() {
        return deductionLoanTotal;
    }

    public void setDeductionLoanTotal(float deductionLoanTotal) {
        this.deductionLoanTotal = deductionLoanTotal;
    }

    public float getDeductionIntTotal() {
        return deductionIntTotal;
    }

    public void setDeductionIntTotal(float deductionIntTotal) {
        this.deductionIntTotal = deductionIntTotal;
    }

    public float getNoDeductionLoanTotal() {
        return noDeductionLoanTotal;
    }

    public void setNoDeductionLoanTotal(float noDeductionLoanTotal) {
        this.noDeductionLoanTotal = noDeductionLoanTotal;
    }

    public float getNoDeductionIntTotal() {
        return noDeductionIntTotal;
    }

    public void setNoDeductionIntTotal(float noDeductionIntTotal) {
        this.noDeductionIntTotal = noDeductionIntTotal;
    }
 
 
}
