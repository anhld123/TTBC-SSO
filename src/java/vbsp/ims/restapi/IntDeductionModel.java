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

public class IntDeductionModel {

    private String mainPos;
    private String posCode;
    private String reportDate;
    private String groupId;
    private String customerId;
    private String loanId;
    private double prinTotal;
    private double normalAmt;
    private double overdueAmt;
    private double freezeAmt;
    private double interestRate;
    private String loanProgram;
    private String specificProductCode;
    private String decisionCode;
    private String loanStatus;
    private String capitalSourceCode;
    private String investorCode;
    private String casaAccount;
    private float intTotalAmt;

    @XmlElement(defaultValue = "0")
    private BigInteger intDeductionTotalAmt;

    @XmlElement(defaultValue = "0")
    private BigInteger intDeductionM09Amt;

    @XmlElement(defaultValue = "0")
    private BigInteger intDeductionM10Amt;

    @XmlElement(defaultValue = "0")
    private BigInteger intDeductionM11Amt;

    @XmlElement(defaultValue = "0")
    private BigInteger intDeductionM12Amt;

    @XmlElement(defaultValue = "0")
    private BigInteger intDeductionAdjustM09Amt;

    @XmlElement(defaultValue = "0")
    private BigInteger intDeductionAdjustM10Amt;

    @XmlElement(defaultValue = "0")
    private BigInteger intDeductionAdjustM11Amt;

    @XmlElement(defaultValue = "0")
    private BigInteger intDeductionAdjustM12Amt;

    private String paymentFlag;
    private String intConfirmFlag;
    private String deductionTranRef;
    private String deductionTranDate;
//    14
    @XmlElement(defaultValue = "0")
    private BigInteger accountingIntAmt;
    @XmlElement(defaultValue = "0")
    private BigInteger rpaAmt;
    @XmlElement(defaultValue = "0")
    private BigInteger casaAmt;
    // 19
    @XmlElement(defaultValue = "0")
    private BigInteger cashAmt;
    // 20
    private String posTranRef;
    private String m09Status;
    private String m10Status;
    private String m11Status;
    private String m12Status;
    private String makerId;
    private String makerDate;
    private String m09UpdateId;
    private String m09UpdateDate;
    private String m10UpdateId;
    private String m10UpdateDate;
    private String m11UpdateId;
    private String m11UpdateDate;
    private String m12UpdateId;
    private String m12UpdateDate;
    private double intTotalM09Amt;
    private double intTotalM10Amt;
    private double intTotalM11Amt;
    private double intTotalM12Amt;
    private String communeId;
    private String customerName;
    private String disbursalDate;

//    2025
    private BigInteger glAdjustAmt;
    @XmlElement(defaultValue = "0")
    private BigInteger accountingCasaAmt;
    @XmlElement(defaultValue = "0")
    private BigInteger accountingGLAmt;
    @XmlElement(defaultValue = "0")
    private BigInteger rpaAddAmt;

    public BigInteger getRpaAddAmt() {
        return rpaAddAmt;
    }

    public void setRpaAddAmt(BigInteger rpaAddAmt) {
        this.rpaAddAmt = rpaAddAmt;
    }

    public BigInteger getGlAdjustAmt() {
        return glAdjustAmt;
    }

    public void setGlAdjustAmt(BigInteger glAdjustAmt) {
        this.glAdjustAmt = glAdjustAmt;
    }

    public BigInteger getAccountingCasaAmt() {
        return accountingCasaAmt;
    }

    public void setAccountingCasaAmt(BigInteger accountingCasaAmt) {
        this.accountingCasaAmt = accountingCasaAmt;
    }

    public BigInteger getAccountingGLAmt() {
        return accountingGLAmt;
    }

    public void setAccountingGLAmt(BigInteger accountingGLAmt) {
        this.accountingGLAmt = accountingGLAmt;
    }

    public String getDisbursalDate() {
//        return disbursalDate;
        return disbursalDate; //==null? "19000101" : disbursalDate;
    }

    public void setDisbursalDate(String disbursalDate) {
        this.disbursalDate = disbursalDate;
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

    public BigInteger getIntDeductionTotalAmt() {
        return intDeductionTotalAmt == null ? new BigInteger("0") : intDeductionTotalAmt;
    }

    public void setIntDeductionTotalAmt(BigInteger intDeductionTotalAmt) {
        this.intDeductionTotalAmt = intDeductionTotalAmt;
    }

    public BigInteger getIntDeductionM10Amt() {
        return intDeductionM10Amt == null ? new BigInteger("0") : intDeductionM10Amt;
    }

    public void setIntDeductionM10Amt(BigInteger intDeductionM10Amt) {
        this.intDeductionM10Amt = intDeductionM10Amt;
    }

    public BigInteger getIntDeductionM11Amt() {
        return intDeductionM11Amt == null ? new BigInteger("0") : intDeductionM11Amt;
    }

    public void setIntDeductionM11Amt(BigInteger intDeductionM11Amt) {
        this.intDeductionM11Amt = intDeductionM11Amt;
    }

    public BigInteger getIntDeductionM12Amt() {
        return intDeductionM12Amt == null ? new BigInteger("0") : intDeductionM12Amt;
    }

    public void setIntDeductionM12Amt(BigInteger intDeductionM12Amt) {
        this.intDeductionM12Amt = intDeductionM12Amt;
    }

    public BigInteger getIntDeductionAdjustM10Amt() {
        return intDeductionAdjustM10Amt == null ? new BigInteger("0") : intDeductionAdjustM10Amt;
    }

    public void setIntDeductionAdjustM10Amt(BigInteger intDeductionAdjustM10Amt) {
        this.intDeductionAdjustM10Amt = intDeductionAdjustM10Amt;
    }

    public BigInteger getIntDeductionAdjustM11Amt() {
        return intDeductionAdjustM11Amt == null ? new BigInteger("0") : intDeductionAdjustM11Amt;
    }

    public void setIntDeductionAdjustM11Amt(BigInteger intDeductionAdjustM11Amt) {
        this.intDeductionAdjustM11Amt = intDeductionAdjustM11Amt;
    }

    public BigInteger getIntDeductionAdjustM12Amt() {
        return intDeductionAdjustM12Amt == null ? new BigInteger("0") : intDeductionAdjustM12Amt;
    }

    public void setIntDeductionAdjustM12Amt(BigInteger intDeductionAdjustM12Amt) {
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

    public double getIntTotalM10Amt() {
        return intTotalM10Amt;
    }

    public void setIntTotalM10Amt(double intTotalM10Amt) {
        this.intTotalM10Amt = intTotalM10Amt;
    }

    public double getIntTotalM11Amt() {
        return intTotalM11Amt;
    }

    public void setIntTotalM11Amt(double intTotalM11Amt) {
        this.intTotalM11Amt = intTotalM11Amt;
    }

    public double getIntTotalM12Amt() {
        return intTotalM12Amt;
    }

    public void setIntTotalM12Amt(double intTotalM12Amt) {
        this.intTotalM12Amt = intTotalM12Amt;
    }

    public String getCommuneId() {
        return communeId;
    }

    public void setCommuneId(String communeId) {
        this.communeId = communeId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public BigInteger getIntDeductionM09Amt() {
        return intDeductionM09Amt == null ? new BigInteger("0") : intDeductionM09Amt;
    }

    public void setIntDeductionM09Amt(BigInteger intDeductionM09Amt) {
        this.intDeductionM09Amt = intDeductionM09Amt;
    }

    public BigInteger getIntDeductionAdjustM09Amt() {
        return intDeductionAdjustM09Amt == null ? new BigInteger("0") : intDeductionAdjustM09Amt;
    }

    public void setIntDeductionAdjustM09Amt(BigInteger intDeductionAdjustM09Amt) {
        this.intDeductionAdjustM09Amt = intDeductionAdjustM09Amt;
    }

    public String getM09UpdateId() {
        return m09UpdateId;
    }

    public void setM09UpdateId(String m09UpdateId) {
        this.m09UpdateId = m09UpdateId;
    }

    public String getM09UpdateDate() {
        return m09UpdateDate;
    }

    public void setM09UpdateDate(String m09UpdateDate) {
        this.m09UpdateDate = m09UpdateDate;
    }

    public double getIntTotalM09Amt() {
        return intTotalM09Amt;
    }

    public void setIntTotalM09Amt(double intTotalM09Amt) {
        this.intTotalM09Amt = intTotalM09Amt;
    }

    public String getM09Status() {
        return m09Status;
    }

    public void setM09Status(String m09Status) {
        this.m09Status = m09Status;
    }

}
