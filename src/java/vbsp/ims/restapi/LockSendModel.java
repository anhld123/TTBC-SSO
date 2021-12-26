/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.restapi;

import java.math.BigInteger;


/**
 *
 * @author HP
 */
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

//@XmlRootElement(name = "dulieuNT")
@XmlAccessorType(XmlAccessType.FIELD)

public class LockSendModel {
   private String reportKey; 
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
    private BigInteger loanTotal;
    private BigInteger prinTotal;
    private double intTotal;
    private BigInteger intDeductionTotal;
    private BigInteger deductionLoanTotal;
    private BigInteger deductionIntTotal;
    private BigInteger noDeductionLoanTotal;
    private BigInteger noDeductionIntTotal;

    public String getReportKey() {
        return reportKey;
    }

    public void setReportKey(String reportKey) {
        this.reportKey = reportKey;
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

    public String getPosName() {
        return posName;
    }

    public void setPosName(String posName) {
        this.posName = posName;
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

    public BigInteger getLoanTotal() {
        return loanTotal;
    }

    public void setLoanTotal(BigInteger loanTotal) {
        this.loanTotal = loanTotal;
    }

    public BigInteger getPrinTotal() {
        return prinTotal;
    }

    public void setPrinTotal(BigInteger prinTotal) {
        this.prinTotal = prinTotal;
    }

    public double getIntTotal() {
        return intTotal;
    }

    public void setIntTotal(double intTotal) {
        this.intTotal = intTotal;
    }

    public BigInteger getIntDeductionTotal() {
        return intDeductionTotal;
    }

    public void setIntDeductionTotal(BigInteger intDeductionTotal) {
        this.intDeductionTotal = intDeductionTotal;
    }

    public BigInteger getDeductionLoanTotal() {
        return deductionLoanTotal;
    }

    public void setDeductionLoanTotal(BigInteger deductionLoanTotal) {
        this.deductionLoanTotal = deductionLoanTotal;
    }

    public BigInteger getDeductionIntTotal() {
        return deductionIntTotal;
    }

    public void setDeductionIntTotal(BigInteger deductionIntTotal) {
        this.deductionIntTotal = deductionIntTotal;
    }

    public BigInteger getNoDeductionLoanTotal() {
        return noDeductionLoanTotal;
    }

    public void setNoDeductionLoanTotal(BigInteger noDeductionLoanTotal) {
        this.noDeductionLoanTotal = noDeductionLoanTotal;
    }

    public BigInteger getNoDeductionIntTotal() {
        return noDeductionIntTotal;
    }

    public void setNoDeductionIntTotal(BigInteger noDeductionIntTotal) {
        this.noDeductionIntTotal = noDeductionIntTotal;
    }

    
   
}
