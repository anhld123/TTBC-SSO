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
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

//@XmlRootElement(name = "dulieuNT")
@XmlAccessorType(XmlAccessType.FIELD)

public class CommissionMasterModel {

    private String mainPos;
    private String posCode;
    private String reportDate;
    private String groupId;
    private String customerId;
    private String customerName;
    private String loanId;
    private String prinTotal;
    private double normalAmt;
    private double overdueAmt;
    private double freezeAmt;
    private double interestRate;
    private String loanProgram;
    private String specificProductCode;
    private String decisionCode;
    private String loanStatus;
    private String capitalSourceCode;
    private String savingAccount;
    private String groupLeaderAccount;
    private double subsidyTotalAmount;
    private double commisionRate;
    private double commisionTotalAmount;
    private String investorCode;
    private String investorName;
    private double districtBenRate;
    private double provinceBenRate;
    private double commisionGroupAmount;
    private double commisionDistrictAmount;
    private double commisionProvinceAmount;
    private String confirmFlag;
    private String fpTransReference;
    private String fpTransDate;
    private double accountingIntAmount;
    private String communeId;
    private String disbursalDate;
    private String status;
    private String createdBy;
    private String createdDate;
    private String updateBy;
    private String updateDate;
    private String groupLeaderCif;
    private String groupLeaderName;
    
    
    ArrayList< CommissionDetailModel> benCommissionDetails = new ArrayList<>();
    
    private String D33;

    // Getter Methods 

    public String getD33() {
        return D33;
    }

    public void setD33(String D33) {
        this.D33 = D33;
    }
    

    public ArrayList<CommissionDetailModel> getBenCommissionDetails() {
        return benCommissionDetails;
    }

    public void setBenCommissionDetails(ArrayList<CommissionDetailModel> benCommissionDetails) {
        this.benCommissionDetails = benCommissionDetails;
    }

   
    
    public String getMainPos() {
        return mainPos;
    }

    public String getPosCode() {
        return posCode;
    }

    public String getReportDate() {
        return reportDate;
    }

    public String getGroupId() {
        return groupId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getLoanId() {
        return loanId;
    }


    public double getNormalAmt() {
        return normalAmt;
    }

    public double getOverdueAmt() {
        return overdueAmt;
    }

    public double getFreezeAmt() {
        return freezeAmt;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public String getLoanProgram() {
        return loanProgram;
    }

    public String getSpecificProductCode() {
        return specificProductCode;
    }

    public String getDecisionCode() {
        return decisionCode;
    }

    public String getLoanStatus() {
        return loanStatus;
    }

    public String getCapitalSourceCode() {
        return capitalSourceCode;
    }

    public String getSavingAccount() {
        return savingAccount;
    }

    public String getGroupLeaderAccount() {
        return groupLeaderAccount;
    }

    public double getSubsidyTotalAmount() {
        return subsidyTotalAmount;
    }

    public double getCommisionRate() {
        return commisionRate;
    }

    public double getCommisionTotalAmount() {
        return commisionTotalAmount;
    }

    public String getInvestorCode() {
        return investorCode;
    }

    public double getDistrictBenRate() {
        return districtBenRate;
    }

    public double getProvinceBenRate() {
        return provinceBenRate;
    }

    public double getCommisionGroupAmount() {
        return commisionGroupAmount;
    }

    public double getCommisionDistrictAmount() {
        return commisionDistrictAmount;
    }

    public double getCommisionProvinceAmount() {
        return commisionProvinceAmount;
    }

    public String getConfirmFlag() {
        return confirmFlag;
    }

    public String getFpTransReference() {
        return fpTransReference;
    }

    public String getFpTransDate() {
        return fpTransDate;
    }

    public double getAccountingIntAmount() {
        return accountingIntAmount;
    }

    public String getCommuneId() {
        return communeId;
    }

    public String getDisbursalDate() {
        return disbursalDate;
    }

    public String getStatus() {
        return status;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public String getGroupLeaderCif() {
        return groupLeaderCif;
    }

    public String getGroupLeaderName() {
        return groupLeaderName;
    }

    // Setter Methods 
    public void setMainPos(String mainPos) {
        this.mainPos = mainPos;
    }

    public void setPosCode(String posCode) {
        this.posCode = posCode;
    }

    public void setReportDate(String reportDate) {
        this.reportDate = reportDate;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setLoanId(String loanId) {
        this.loanId = loanId;
    }


    public void setNormalAmt(double normalAmt) {
        this.normalAmt = normalAmt;
    }

    public void setOverdueAmt(double overdueAmt) {
        this.overdueAmt = overdueAmt;
    }

    public void setFreezeAmt(double freezeAmt) {
        this.freezeAmt = freezeAmt;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public void setLoanProgram(String loanProgram) {
        this.loanProgram = loanProgram;
    }

    public void setSpecificProductCode(String specificProductCode) {
        this.specificProductCode = specificProductCode;
    }

    public void setDecisionCode(String decisionCode) {
        this.decisionCode = decisionCode;
    }

    public void setLoanStatus(String loanStatus) {
        this.loanStatus = loanStatus;
    }

    public void setCapitalSourceCode(String capitalSourceCode) {
        this.capitalSourceCode = capitalSourceCode;
    }

    public void setSavingAccount(String savingAccount) {
        this.savingAccount = savingAccount;
    }

    public void setGroupLeaderAccount(String groupLeaderAccount) {
        this.groupLeaderAccount = groupLeaderAccount;
    }

    public void setSubsidyTotalAmount(double subsidyTotalAmount) {
        this.subsidyTotalAmount = subsidyTotalAmount;
    }

    public void setCommisionRate(double commisionRate) {
        this.commisionRate = commisionRate;
    }

    public void setCommisionTotalAmount(double commisionTotalAmount) {
        this.commisionTotalAmount = commisionTotalAmount;
    }

    public void setInvestorCode(String investorCode) {
        this.investorCode = investorCode;
    }

    public void setDistrictBenRate(double districtBenRate) {
        this.districtBenRate = districtBenRate;
    }

    public void setProvinceBenRate(double provinceBenRate) {
        this.provinceBenRate = provinceBenRate;
    }

    public void setCommisionGroupAmount(double commisionGroupAmount) {
        this.commisionGroupAmount = commisionGroupAmount;
    }

    public void setCommisionDistrictAmount(double commisionDistrictAmount) {
        this.commisionDistrictAmount = commisionDistrictAmount;
    }

    public void setCommisionProvinceAmount(double commisionProvinceAmount) {
        this.commisionProvinceAmount = commisionProvinceAmount;
    }

    public void setConfirmFlag(String confirmFlag) {
        this.confirmFlag = confirmFlag;
    }

    public void setFpTransReference(String fpTransReference) {
        this.fpTransReference = fpTransReference;
    }

    public void setFpTransDate(String fpTransDate) {
        this.fpTransDate = fpTransDate;
    }

    public void setAccountingIntAmount(double accountingIntAmount) {
        this.accountingIntAmount = accountingIntAmount;
    }

    public void setCommuneId(String communeId) {
        this.communeId = communeId;
    }

    public void setDisbursalDate(String disbursalDate) {
        this.disbursalDate = disbursalDate;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public void setGroupLeaderCif(String groupLeaderCif) {
        this.groupLeaderCif = groupLeaderCif;
    }

    public void setGroupLeaderName(String groupLeaderName) {
        this.groupLeaderName = groupLeaderName;
    }

    public String getPrinTotal() {
        return prinTotal;
    }

    public void setPrinTotal(String prinTotal) {
        this.prinTotal = prinTotal;
    }

    public String getInvestorName() {
        return investorName;
    }

    public void setInvestorName(String investorName) {
        this.investorName = investorName;
    }

    
}
