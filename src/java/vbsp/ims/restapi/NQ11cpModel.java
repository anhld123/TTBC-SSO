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
import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

//@XmlRootElement(name = "dulieuNT")
@XmlAccessorType(XmlAccessType.FIELD)

public class NQ11cpModel {

    String mainPos;
    String posCode;
    String reportDate;
    String groupId;
    String customerId;
    String customerName;
    String loanId;
    double prinTotal;
    double normalAmt;
    double overdueAmt;
    double freezeAmt;
    double interestRate;
    String loanProgram;
    String specificProductCode;
    String decisionCode;
    String loanStatus;
    String capitalSourceCode;
    String investorCode;
    String casaAccount;
    float intTotalAmt;

    @XmlElement(defaultValue = "0")
    BigInteger intSubsidyTotalAmt;
    @XmlElement(defaultValue = "0")
    BigInteger intSubsidyM01Amt;
    @XmlElement(defaultValue = "0")
    BigInteger intSubsidyM02Amt;
    @XmlElement(defaultValue = "0")
    BigInteger intSubsidyM03Amt;
    @XmlElement(defaultValue = "0")
    BigInteger intSubsidyM04Amt;
    @XmlElement(defaultValue = "0")
    BigInteger intSubsidyM05Amt;
    @XmlElement(defaultValue = "0")
    BigInteger intSubsidyM06Amt;
    @XmlElement(defaultValue = "0")
    BigInteger intSubsidyM07Amt;

    @XmlElement(defaultValue = "0")
    BigInteger intSubsidyAdjustM01Amt;
    @XmlElement(defaultValue = "0")
    BigInteger intSubsidyAdjustM02Amt;
    @XmlElement(defaultValue = "0")
    BigInteger intSubsidyAdjustM03Amt;
    @XmlElement(defaultValue = "0")
    BigInteger intSubsidyAdjustM04Amt;
    @XmlElement(defaultValue = "0")
    BigInteger intSubsidyAdjustM05Amt;
    @XmlElement(defaultValue = "0")
    BigInteger intSubsidyAdjustM06Amt;
    @XmlElement(defaultValue = "0")
    BigInteger intSubsidyAdjustM07Amt;

    String paymentFlag;
    String intConfirmFlag;
    String subsidyTranRef;
    String subsidyTranDate;
    BigInteger accountingIntAmt;
    BigInteger rpaAmt;
    BigInteger casaAmt;
    BigInteger cashAmt;
    String posTranRef;
    String m01Status;
    String m02Status;
    String m03Status;
    String m04Status;
    String m05Status;
    String m06Status;
    
    String createdBy;
    String createdDate;
    String m01UpdateBy;
    String m01UpdateDate;
    String m02UpdateBy;
    String m02UpdateDate;
    String m03UpdateBy;
    String m03UpdateDate;
    String m04UpdateBy;
    String m04UpdateDate;
    String m05UpdateBy;
    String m05UpdateDate;
    String m06UpdateBy;
    String m06UpdateDate;

    double intTotalM01Amt;
    double intTotalM02Amt;
    double intTotalM03Amt;
    double intTotalM04Amt;
    double intTotalM05Amt;
    double intTotalM06Amt;
    double intTotalM07Amt;

    String communeId;
    String m01SubsidyTranRef;
    String m01SubsidyTranDate;
    double m10AccountingIntAmt;
    String m02SubsidyTranRef;
    String m02SubsidyTranDate;
    int m11AccountingIntAmt;
    String m03SubsidyTranRef;
    String m03SubsidyTranDate;
    double m12AccountingIntAmt;
    String disbursalDate;
    String editFlag;
    String rejectReason;
    String paymentMethod;

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

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getLoanId() {
        return loanId;
    }

    public void setLoanId(String loanId) {
        this.loanId = loanId;
    }

    public double getPrinTotal() {
        return prinTotal;
    }

    public void setPrinTotal(double prinTotal) {
        this.prinTotal = prinTotal;
    }

    public double getNormalAmt() {
        return normalAmt;
    }

    public void setNormalAmt(double normalAmt) {
        this.normalAmt = normalAmt;
    }

    public double getOverdueAmt() {
        return overdueAmt;
    }

    public void setOverdueAmt(double overdueAmt) {
        this.overdueAmt = overdueAmt;
    }

    public double getFreezeAmt() {
        return freezeAmt;
    }

    public void setFreezeAmt(double freezeAmt) {
        this.freezeAmt = freezeAmt;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
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
        return investorCode == null ? "-1" : investorCode;
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

    public BigInteger getIntSubsidyM01Amt() {
        return intSubsidyM01Amt == null ? new BigInteger("0") : intSubsidyM01Amt;
    }

    public void setIntSubsidyM01Amt(BigInteger intSubsidyM01Amt) {
        this.intSubsidyM01Amt = intSubsidyM01Amt;
    }

    public BigInteger getIntSubsidyM02Amt() {
        return intSubsidyM02Amt == null ? new BigInteger("0") : intSubsidyM02Amt;
    }

    public void setIntSubsidyM02Amt(BigInteger intSubsidyM02Amt) {
        this.intSubsidyM02Amt = intSubsidyM02Amt;
    }

    public BigInteger getIntSubsidyM03Amt() {
        return intSubsidyM03Amt == null ? new BigInteger("0") : intSubsidyM03Amt;
    }

    public void setIntSubsidyM03Amt(BigInteger intSubsidyM03Amt) {
        this.intSubsidyM03Amt = intSubsidyM03Amt;
    }

    public BigInteger getIntSubsidyAdjustM01Amt() {
        return intSubsidyAdjustM01Amt == null ? new BigInteger("0") : intSubsidyAdjustM01Amt;
    }

    public void setIntSubsidyAdjustM01Amt(BigInteger intSubsidyAdjustM01Amt) {
        this.intSubsidyAdjustM01Amt = intSubsidyAdjustM01Amt;
    }

    public BigInteger getIntSubsidyAdjustM02Amt() {
        return intSubsidyAdjustM02Amt == null ? new BigInteger("0") : intSubsidyAdjustM02Amt;
    }

    public void setIntSubsidyAdjustM02Amt(BigInteger intSubsidyAdjustM02Amt) {
        this.intSubsidyAdjustM02Amt = intSubsidyAdjustM02Amt;
    }

    public BigInteger getIntSubsidyAdjustM03Amt() {
        return intSubsidyAdjustM03Amt == null ? new BigInteger("0") : intSubsidyAdjustM03Amt;
    }

    public void setIntSubsidyAdjustM03Amt(BigInteger intSubsidyAdjustM03Amt) {
        this.intSubsidyAdjustM03Amt = intSubsidyAdjustM03Amt;
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

    public String getSubsidyTranRef() {
        return subsidyTranRef;
    }

    public void setSubsidyTranRef(String subsidyTranRef) {
        this.subsidyTranRef = subsidyTranRef;
    }

    public String getSubsidyTranDate() {
        return subsidyTranDate;
    }

    public void setSubsidyTranDate(String subsidyTranDate) {
        this.subsidyTranDate = subsidyTranDate;
    }

    public BigInteger getAccountingIntAmt() {
        return accountingIntAmt == null ? new BigInteger("0") : accountingIntAmt;
    }

    public void setAccountingIntAmt(BigInteger accountingIntAmt) {
        this.accountingIntAmt = accountingIntAmt;
    }

    public BigInteger getRpaAmt() {
        return rpaAmt == null ? new BigInteger("0") : rpaAmt;
    }

    public void setRpaAmt(BigInteger rpaAmt) {
        this.rpaAmt = rpaAmt;
    }

    public BigInteger getCasaAmt() {
        return casaAmt == null ? new BigInteger("0") : casaAmt;
    }

    public void setCasaAmt(BigInteger casaAmt) {
        this.casaAmt = casaAmt;
    }

    public BigInteger getCashAmt() {
        return cashAmt == null ? new BigInteger("0") : cashAmt;
    }

    public void setCashAmt(BigInteger cashAmt) {
        this.cashAmt = cashAmt;
    }

    public String getPosTranRef() {
        return posTranRef;
    }

    public void setPosTranRef(String posTranRef) {
        this.posTranRef = posTranRef;
    }

    public String getM01Status() {
        return m01Status;
    }

    public void setM01Status(String m01Status) {
        this.m01Status = m01Status;
    }

    public String getM02Status() {
        return m02Status;
    }

    public void setM02Status(String m02Status) {
        this.m02Status = m02Status;
    }

    public String getM03Status() {
        return m03Status;
    }

    public void setM03Status(String m03Status) {
        this.m03Status = m03Status;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getM01UpdateBy() {
        return m01UpdateBy;
    }

    public void setM01UpdateBy(String m01UpdateBy) {
        this.m01UpdateBy = m01UpdateBy;
    }

    public String getM01UpdateDate() {
        return m01UpdateDate;
    }

    public void setM01UpdateDate(String m01UpdateDate) {
        this.m01UpdateDate = m01UpdateDate;
    }

    public String getM02UpdateBy() {
        return m02UpdateBy;
    }

    public void setM02UpdateBy(String m02UpdateBy) {
        this.m02UpdateBy = m02UpdateBy;
    }

    public String getM02UpdateDate() {
        return m02UpdateDate;
    }

    public void setM02UpdateDate(String m02UpdateDate) {
        this.m02UpdateDate = m02UpdateDate;
    }

    public String getM03UpdateBy() {
        return m03UpdateBy;
    }

    public void setM03UpdateBy(String m03UpdateBy) {
        this.m03UpdateBy = m03UpdateBy;
    }

    public String getM03UpdateDate() {
        return m03UpdateDate;
    }

    public void setM03UpdateDate(String m03UpdateDate) {
        this.m03UpdateDate = m03UpdateDate;
    }

    public double getIntTotalM01Amt() {
        return intTotalM01Amt;
    }

    public void setIntTotalM01Amt(double intTotalM01Amt) {
        this.intTotalM01Amt = intTotalM01Amt;
    }

    public double getIntTotalM02Amt() {
        return intTotalM02Amt;
    }

    public void setIntTotalM02Amt(double intTotalM02Amt) {
        this.intTotalM02Amt = intTotalM02Amt;
    }

    public double getIntTotalM03Amt() {
        return intTotalM03Amt;
    }

    public void setIntTotalM03Amt(double intTotalM03Amt) {
        this.intTotalM03Amt = intTotalM03Amt;
    }

    public String getCommuneId() {
        return communeId;
    }

    public void setCommuneId(String communeId) {
        this.communeId = communeId;
    }

    public String getM01SubsidyTranRef() {
        return m01SubsidyTranRef;
    }

    public void setM01SubsidyTranRef(String m01SubsidyTranRef) {
        this.m01SubsidyTranRef = m01SubsidyTranRef;
    }

    public String getM01SubsidyTranDate() {
        return m01SubsidyTranDate;
    }

    public void setM01SubsidyTranDate(String m01SubsidyTranDate) {
        this.m01SubsidyTranDate = m01SubsidyTranDate;
    }

    public double getM10AccountingIntAmt() {
        return m10AccountingIntAmt;
    }

    public void setM10AccountingIntAmt(double m10AccountingIntAmt) {
        this.m10AccountingIntAmt = m10AccountingIntAmt;
    }

    public String getM02SubsidyTranRef() {
        return m02SubsidyTranRef;
    }

    public void setM02SubsidyTranRef(String m02SubsidyTranRef) {
        this.m02SubsidyTranRef = m02SubsidyTranRef;
    }

    public String getM02SubsidyTranDate() {
        return m02SubsidyTranDate;
    }

    public void setM02SubsidyTranDate(String m02SubsidyTranDate) {
        this.m02SubsidyTranDate = m02SubsidyTranDate;
    }

    public int getM11AccountingIntAmt() {
        return m11AccountingIntAmt;
    }

    public void setM11AccountingIntAmt(int m11AccountingIntAmt) {
        this.m11AccountingIntAmt = m11AccountingIntAmt;
    }

    public String getM03SubsidyTranRef() {
        return m03SubsidyTranRef;
    }

    public void setM03SubsidyTranRef(String m03SubsidyTranRef) {
        this.m03SubsidyTranRef = m03SubsidyTranRef;
    }

    public String getM03SubsidyTranDate() {
        return m03SubsidyTranDate;
    }

    public void setM03SubsidyTranDate(String m03SubsidyTranDate) {
        this.m03SubsidyTranDate = m03SubsidyTranDate;
    }

    public double getM12AccountingIntAmt() {
        return m12AccountingIntAmt;
    }

    public void setM12AccountingIntAmt(double m12AccountingIntAmt) {
        this.m12AccountingIntAmt = m12AccountingIntAmt;
    }

    public String getDisbursalDate() {
        return disbursalDate;
    }

    public void setDisbursalDate(String disbursalDate) {
        this.disbursalDate = disbursalDate;
    }

    public String getEditFlag() {
        return editFlag;
    }

    public void setEditFlag(String editFlag) {
        this.editFlag = editFlag;
    }

    public String getRejectReason() {
        return rejectReason;
    }

    public void setRejectReason(String rejectReason) {
        this.rejectReason = rejectReason;
    }

    public BigInteger getIntSubsidyTotalAmt() {
        return intSubsidyTotalAmt;
    }

    public void setIntSubsidyTotalAmt(BigInteger intSubsidyTotalAmt) {
        this.intSubsidyTotalAmt = intSubsidyTotalAmt;
    }

    public BigInteger getIntSubsidyM04Amt() {
        return intSubsidyM04Amt == null ? new BigInteger("0") : intSubsidyM04Amt;
    }

    public void setIntSubsidyM04Amt(BigInteger intSubsidyM04Amt) {
        this.intSubsidyM04Amt = intSubsidyM04Amt;
    }

    public BigInteger getIntSubsidyAdjustM04Amt() {
        return intSubsidyAdjustM04Amt == null ? new BigInteger("0") : intSubsidyAdjustM04Amt;
    }

    public void setIntSubsidyAdjustM04Amt(BigInteger intSubsidyAdjustM04Amt) {
        this.intSubsidyAdjustM04Amt = intSubsidyAdjustM04Amt;
    }

    public String getM04Status() {
        return m04Status;
    }

    public void setM04Status(String m04Status) {
        this.m04Status = m04Status;
    }

    public String getM04UpdateBy() {
        return m04UpdateBy;
    }

    public void setM04UpdateBy(String m04UpdateBy) {
        this.m04UpdateBy = m04UpdateBy;
    }

    public String getM04UpdateDate() {
        return m04UpdateDate;
    }

    public void setM04UpdateDate(String m04UpdateDate) {
        this.m04UpdateDate = m04UpdateDate;
    }

    public double getIntTotalM04Amt() {
        return intTotalM04Amt;
    }

    public void setIntTotalM04Amt(double intTotalM04Amt) {
        this.intTotalM04Amt = intTotalM04Amt;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public BigInteger getIntSubsidyM05Amt() {
        return intSubsidyM05Amt == null ? new BigInteger("0") : intSubsidyM05Amt;
    }

    public void setIntSubsidyM05Amt(BigInteger intSubsidyM05Amt) {
        this.intSubsidyM05Amt = intSubsidyM05Amt;
    }

    public BigInteger getIntSubsidyM06Amt() {
        return intSubsidyM06Amt == null ? new BigInteger("0") : intSubsidyM06Amt;
    }

    public void setIntSubsidyM06Amt(BigInteger intSubsidyM06Amt) {
        this.intSubsidyM06Amt = intSubsidyM06Amt;
    }

    public BigInteger getIntSubsidyAdjustM05Amt() {
        return intSubsidyAdjustM05Amt == null ? new BigInteger("0") : intSubsidyAdjustM05Amt;
    }

    public void setIntSubsidyAdjustM05Amt(BigInteger intSubsidyAdjustM05Amt) {
        this.intSubsidyAdjustM05Amt = intSubsidyAdjustM05Amt;
    }

    public BigInteger getIntSubsidyAdjustM06Amt() {
        return intSubsidyAdjustM06Amt == null ? new BigInteger("0") : intSubsidyAdjustM06Amt;
    }

    public void setIntSubsidyAdjustM06Amt(BigInteger intSubsidyAdjustM06Amt) {
        this.intSubsidyAdjustM06Amt = intSubsidyAdjustM06Amt;
    }

    public String getM05UpdateBy() {
        return m05UpdateBy;
    }

    public void setM05UpdateBy(String m05UpdateBy) {
        this.m05UpdateBy = m05UpdateBy;
    }

    public String getM05UpdateDate() {
        return m05UpdateDate;
    }

    public void setM05UpdateDate(String m05UpdateDate) {
        this.m05UpdateDate = m05UpdateDate;
    }

    public String getM06UpdateBy() {
        return m06UpdateBy;
    }

    public void setM06UpdateBy(String m06UpdateBy) {
        this.m06UpdateBy = m06UpdateBy;
    }

    public String getM06UpdateDate() {
        return m06UpdateDate;
    }

    public void setM06UpdateDate(String m06UpdateDate) {
        this.m06UpdateDate = m06UpdateDate;
    }

    public double getIntTotalM05Amt() {
        return intTotalM05Amt;
    }

    public void setIntTotalM05Amt(double intTotalM05Amt) {
        this.intTotalM05Amt = intTotalM05Amt;
    }

    public double getIntTotalM06Amt() {
        return intTotalM06Amt;
    }

    public void setIntTotalM06Amt(double intTotalM06Amt) {
        this.intTotalM06Amt = intTotalM06Amt;
    }

    public String getM05Status() {
        return m05Status;
    }

    public void setM05Status(String m05Status) {
        this.m05Status = m05Status;
    }

    public String getM06Status() {
        return m06Status;
    }

    public void setM06Status(String m06Status) {
        this.m06Status = m06Status;
    }

    public BigInteger getIntSubsidyM07Amt() {
        return intSubsidyM07Amt == null ? new BigInteger("0") : intSubsidyM07Amt;
    }
    
    public void setIntSubsidyM07Amt(BigInteger intSubsidyM07Amt) {
        this.intSubsidyM07Amt = intSubsidyM07Amt;
    }
    public BigInteger getIntSubsidyAdjustM07Amt() {
        return intSubsidyAdjustM07Amt == null ? new BigInteger("0") : intSubsidyAdjustM07Amt;
    }

    public void setIntSubsidyAdjustM07Amt(BigInteger intSubsidyAdjustM07Amt) {
        this.intSubsidyAdjustM07Amt = intSubsidyAdjustM07Amt;
    }

    public double getIntTotalM07Amt() {
        return intTotalM07Amt;
    }

    public void setIntTotalM07Amt(double intTotalM07Amt) {
        this.intTotalM07Amt = intTotalM07Amt;
    }

    
    
    
}
