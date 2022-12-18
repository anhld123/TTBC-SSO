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

public class UpdateCommBenModel {
 private String mainPos;
 private String posCode;
 private String reportDate;
 private String loanId;
 private double commissionTotalAmount;
 private String investorCode;
 private String investorName;
 private String levelFlag;
 private String benefitName;
 private double benefitRate;
 private String creditAccount;
 private String debitAccount;
 private String status;
 private double benefitAmount;
 private String createdBy;
 private String createdDate;
 private String updateBy;
 private String updateDate;
 private double benefitRateAdjust;
 private double benefitAmountAdjust;
 private String benKey;


 // Getter Methods 

 public String getMainPos() {
  return mainPos;
 }

 public String getPosCode() {
  return posCode;
 }

 public String getReportDate() {
  return reportDate;
 }

 public String getLoanId() {
  return loanId;
 }

 public double getCommissionTotalAmount() {
  return commissionTotalAmount;
 }

 public String getInvestorCode() {
  return investorCode;
 }

 public String getInvestorName() {
  return investorName;
 }

 public String getLevelFlag() {
  return levelFlag;
 }

 public String getBenefitName() {
  return benefitName;
 }

 public double getBenefitRate() {
  return benefitRate;
 }

 public String getCreditAccount() {
  return creditAccount;
 }

 public String getDebitAccount() {
  return debitAccount;
 }

 public String getStatus() {
  return status;
 }

 public double getBenefitAmount() {
  return benefitAmount;
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

 public double getBenefitRateAdjust() {
  return benefitRateAdjust;
 }

 public double getBenefitAmountAdjust() {
  return benefitAmountAdjust;
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

 public void setLoanId(String loanId) {
  this.loanId = loanId;
 }

 public void setCommissionTotalAmount(double commissionTotalAmount) {
  this.commissionTotalAmount = commissionTotalAmount;
 }

 public void setInvestorCode(String investorCode) {
  this.investorCode = investorCode;
 }

 public void setInvestorName(String investorName) {
  this.investorName = investorName;
 }

 public void setLevelFlag(String levelFlag) {
  this.levelFlag = levelFlag;
 }

 public void setBenefitName(String benefitName) {
  this.benefitName = benefitName;
 }

 public void setBenefitRate(double benefitRate) {
  this.benefitRate = benefitRate;
 }

 public void setCreditAccount(String creditAccount) {
  this.creditAccount = creditAccount;
 }

 public void setDebitAccount(String debitAccount) {
  this.debitAccount = debitAccount;
 }

 public void setStatus(String status) {
  this.status = status;
 }

 public void setBenefitAmount(double benefitAmount) {
  this.benefitAmount = benefitAmount;
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

 public void setBenefitRateAdjust(double benefitRateAdjust) {
  this.benefitRateAdjust = benefitRateAdjust;
 }

 public void setBenefitAmountAdjust(double benefitAmountAdjust) {
  this.benefitAmountAdjust = benefitAmountAdjust;
 }

    public String getBenKey() {
        return benKey;
    }

    public void setBenKey(String benKey) {
        this.benKey = benKey;
    }
 
 
}
