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

public class IntDeductionModel {
private String mainPos;
 private String posCode;
 private String reportDate;
 private String groupId;
 private String customerId;
 private String loanId;
 private float prinTotal;
 private float normalAmt;
 private float overdueAmt;
 private float freezeAmt;
 private float interestRate;
 private String loanProgram;
 private String specificProductCode;
 private String decisionCode;
 private String loanStatus;
 private String capitalSourceCode;
 private String investorCode;
 private String casaAccount;
 private float intTotalAmt;
 private float intDeductionTotalAmt;
 private float intDeductionM10Amt;
 private float intDeductionM11Amt;
 private float intDeductionM12Amt;
 private float intDeductionAdjustM10Amt;
 private float intDeductionAdjustM11Amt;
 private float intDeductionAdjustM12Amt;
 private String paymentFlag;
 private String intConfirmFlag;
 private String deductionTranRef;
 private String deductionTranDate;
 private float accountingIntAmt;
 private float rpaAmt;
 private float casaAmt;
 private float cashAmt;
 private String posTranRef;
 private String m10Status;
 private String m11Status;
 private String m12Status;
 private String makerId;
 private String makerDate;
 private String m10UpdateId;
 private String m10UpdateDate;
 private String m11UpdateId;
 private String m11UpdateDate;
 private String m12UpdateId;
 private String m12UpdateDate;
 private float intTotalM10Amt;
 private float intTotalM11Amt;
 private float intTotalM12Amt;
 private String communeId;
 private String customerName;

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
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

    public String getReportDate() {
        return reportDate;
    }

    public void setReportDate(String reportDate) {
        this.reportDate = reportDate;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getLoanId() {
        return loanId;
    }

    public void setLoanId(String loanId) {
        this.loanId = loanId;
    }

    public float getPrinTotal() {
        return prinTotal;
    }

    public void setPrinTotal(float prinTotal) {
        this.prinTotal = prinTotal;
    }

    public float getNormalAmt() {
        return normalAmt;
    }

    public void setNormalAmt(float normalAmt) {
        this.normalAmt = normalAmt;
    }

    public float getOverdueAmt() {
        return overdueAmt;
    }

    public void setOverdueAmt(float overdueAmt) {
        this.overdueAmt = overdueAmt;
    }

    public float getFreezeAmt() {
        return freezeAmt;
    }

    public void setFreezeAmt(float freezeAmt) {
        this.freezeAmt = freezeAmt;
    }

    public float getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(float interestRate) {
        this.interestRate = interestRate;
    }

    public String getLoanProgram() {
        return loanProgram;
    }

    public void setLoanProgram(String loanProgram) {
        this.loanProgram = loanProgram;
    }

    public String getSpecificProductCode() {
        return specificProductCode;
    }

    public void setSpecificProductCode(String specificProductCode) {
        this.specificProductCode = specificProductCode;
    }

    public String getDecisionCode() {
        return decisionCode;
    }

    public void setDecisionCode(String decisionCode) {
        this.decisionCode = decisionCode;
    }

    public String getLoanStatus() {
        return loanStatus;
    }

    public void setLoanStatus(String loanStatus) {
        this.loanStatus = loanStatus;
    }

    public String getCapitalSourceCode() {
        return capitalSourceCode;
    }

    public void setCapitalSourceCode(String capitalSourceCode) {
        this.capitalSourceCode = capitalSourceCode;
    }

    public String getInvestorCode() {
        return investorCode;
    }

    public void setInvestorCode(String investorCode) {
        this.investorCode = investorCode;
    }

    public String getCasaAccount() {
        return casaAccount;
    }

    public void setCasaAccount(String casaAccount) {
        this.casaAccount = casaAccount;
    }

    public float getIntTotalAmt() {
        return intTotalAmt;
    }

    public void setIntTotalAmt(float intTotalAmt) {
        this.intTotalAmt = intTotalAmt;
    }

    public float getIntDeductionTotalAmt() {
        return intDeductionTotalAmt;
    }

    public void setIntDeductionTotalAmt(float intDeductionTotalAmt) {
        this.intDeductionTotalAmt = intDeductionTotalAmt;
    }

    public float getIntDeductionM10Amt() {
        return intDeductionM10Amt;
    }

    public void setIntDeductionM10Amt(float intDeductionM10Amt) {
        this.intDeductionM10Amt = intDeductionM10Amt;
    }

    public float getIntDeductionM11Amt() {
        return intDeductionM11Amt;
    }

    public void setIntDeductionM11Amt(float intDeductionM11Amt) {
        this.intDeductionM11Amt = intDeductionM11Amt;
    }

    public float getIntDeductionM12Amt() {
        return intDeductionM12Amt;
    }

    public void setIntDeductionM12Amt(float intDeductionM12Amt) {
        this.intDeductionM12Amt = intDeductionM12Amt;
    }

    public float getIntDeductionAdjustM10Amt() {
        return intDeductionAdjustM10Amt;
    }

    public void setIntDeductionAdjustM10Amt(float intDeductionAdjustM10Amt) {
        this.intDeductionAdjustM10Amt = intDeductionAdjustM10Amt;
    }

    public float getIntDeductionAdjustM11Amt() {
        return intDeductionAdjustM11Amt;
    }

    public void setIntDeductionAdjustM11Amt(float intDeductionAdjustM11Amt) {
        this.intDeductionAdjustM11Amt = intDeductionAdjustM11Amt;
    }

    public float getIntDeductionAdjustM12Amt() {
        return intDeductionAdjustM12Amt;
    }

    public void setIntDeductionAdjustM12Amt(float intDeductionAdjustM12Amt) {
        this.intDeductionAdjustM12Amt = intDeductionAdjustM12Amt;
    }

    public String getPaymentFlag() {
        return paymentFlag;
    }

    public void setPaymentFlag(String paymentFlag) {
        this.paymentFlag = paymentFlag;
    }

    public String getIntConfirmFlag() {
        return intConfirmFlag;
    }

    public void setIntConfirmFlag(String intConfirmFlag) {
        this.intConfirmFlag = intConfirmFlag;
    }

    public String getDeductionTranRef() {
        return deductionTranRef;
    }

    public void setDeductionTranRef(String deductionTranRef) {
        this.deductionTranRef = deductionTranRef;
    }

    public String getDeductionTranDate() {
        return deductionTranDate;
    }

    public void setDeductionTranDate(String deductionTranDate) {
        this.deductionTranDate = deductionTranDate;
    }

    public float getAccountingIntAmt() {
        return accountingIntAmt;
    }

    public void setAccountingIntAmt(float accountingIntAmt) {
        this.accountingIntAmt = accountingIntAmt;
    }

    public float getRpaAmt() {
        return rpaAmt;
    }

    public void setRpaAmt(float rpaAmt) {
        this.rpaAmt = rpaAmt;
    }

    public float getCasaAmt() {
        return casaAmt;
    }

    public void setCasaAmt(float casaAmt) {
        this.casaAmt = casaAmt;
    }

    public float getCashAmt() {
        return cashAmt;
    }

    public void setCashAmt(float cashAmt) {
        this.cashAmt = cashAmt;
    }

    public String getPosTranRef() {
        return posTranRef;
    }

    public void setPosTranRef(String posTranRef) {
        this.posTranRef = posTranRef;
    }

    public String getM10Status() {
        return m10Status;
    }

    public void setM10Status(String m10Status) {
        this.m10Status = m10Status;
    }

    public String getM11Status() {
        return m11Status;
    }

    public void setM11Status(String m11Status) {
        this.m11Status = m11Status;
    }

    public String getM12Status() {
        return m12Status;
    }

    public void setM12Status(String m12Status) {
        this.m12Status = m12Status;
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

    public String getM10UpdateId() {
        return m10UpdateId;
    }

    public void setM10UpdateId(String m10UpdateId) {
        this.m10UpdateId = m10UpdateId;
    }

    public String getM10UpdateDate() {
        return m10UpdateDate;
    }

    public void setM10UpdateDate(String m10UpdateDate) {
        this.m10UpdateDate = m10UpdateDate;
    }

    public String getM11UpdateId() {
        return m11UpdateId;
    }

    public void setM11UpdateId(String m11UpdateId) {
        this.m11UpdateId = m11UpdateId;
    }

    public String getM11UpdateDate() {
        return m11UpdateDate;
    }

    public void setM11UpdateDate(String m11UpdateDate) {
        this.m11UpdateDate = m11UpdateDate;
    }

    public String getM12UpdateId() {
        return m12UpdateId;
    }

    public void setM12UpdateId(String m12UpdateId) {
        this.m12UpdateId = m12UpdateId;
    }

    public String getM12UpdateDate() {
        return m12UpdateDate;
    }

    public void setM12UpdateDate(String m12UpdateDate) {
        this.m12UpdateDate = m12UpdateDate;
    }

    public float getIntTotalM10Amt() {
        return intTotalM10Amt;
    }

    public void setIntTotalM10Amt(float intTotalM10Amt) {
        this.intTotalM10Amt = intTotalM10Amt;
    }

    public float getIntTotalM11Amt() {
        return intTotalM11Amt;
    }

    public void setIntTotalM11Amt(float intTotalM11Amt) {
        this.intTotalM11Amt = intTotalM11Amt;
    }

    public float getIntTotalM12Amt() {
        return intTotalM12Amt;
    }

    public void setIntTotalM12Amt(float intTotalM12Amt) {
        this.intTotalM12Amt = intTotalM12Amt;
    }

    public String getCommuneId() {
        return communeId;
    }

    public void setCommuneId(String communeId) {
        this.communeId = communeId;
    }
 
 
 
}
