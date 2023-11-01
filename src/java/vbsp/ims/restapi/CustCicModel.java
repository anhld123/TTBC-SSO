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

public class CustCicModel {

    private String mainPos;
    private String posCode;
    private String customerCode;
    private String cicCode;
    private String customerName;
    private String isValidCustomerName;
    private String birthDay;
    private String idNo;
    private String newIdNo;
    private String isValidNewIdNo;
    private String oldIdNo;
    private String isValidOldIdNo;
    private String c06OldIdNo;
    private String c06NewIdNo;
    private String communeCode;
    private String communeName;
    private String subCommuneCode;
    private String subCommuneName;
    private String status;
    private String type;
    private String createdBy;
    private String createdDate;
    private String updatedBy;
    private String updatedDate;
    private String intellectUpdateFlag;
    private String groupCode;
    private String mobileNumber;

    @XmlElement(defaultValue = "0")
    private double principleBalance;
    @XmlElement(defaultValue = "0")
    private double savingBalance;
    @XmlElement(defaultValue = "0")
    private double remainIntAmount;

    private String idExpiredDate;
    private String wrongFullNameConfirmFlag;
    private String wrongIdNoConfirmFlag;
    private String wrongIssueDateConfirmFlag;
    private String wrongIssuePlaceConfirmFlag;
    private String wrongBirthdayConfirmFlag;
    private String customerStatus;
    private String coreBankingIdNo;
    private String coreBankingIssuePlace;
    private String coreBankingIssueDate;
    private String coreBankingCustomerName;
    private String coreBankingBirthday;

    // Getter Methods 
    public String getMainPos() {
        return mainPos;
    }

    public String getPosCode() {
        return posCode;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public String getCicCode() {
        return cicCode;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getIsValidCustomerName() {
        return isValidCustomerName;
    }

    public String getBirthDay() {
        return birthDay;
    }

    public String getIdNo() {
        return idNo;
    }

    public String getNewIdNo() {
        return newIdNo;
    }

    public String getIsValidNewIdNo() {
        return isValidNewIdNo;
    }

    public String getOldIdNo() {
        return oldIdNo;
    }

    public String getIsValidOldIdNo() {
        return isValidOldIdNo;
    }

    public String getC06OldIdNo() {
        return c06OldIdNo;
    }

    public String getC06NewIdNo() {
        return c06NewIdNo;
    }

    public String getCommuneCode() {
        return communeCode;
    }

    public String getCommuneName() {
        return communeName;
    }

    public String getSubCommuneCode() {
        return subCommuneCode;
    }

    public String getSubCommuneName() {
        return subCommuneName;
    }

    public String getStatus() {
        return status;
    }

    public String getType() {
        return type;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public String getUpdatedDate() {
        return updatedDate;
    }

    public String getIntellectUpdateFlag() {
        return intellectUpdateFlag;
    }

    public String getGroupCode() {
        return groupCode;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public double getPrincipleBalance() {
        return principleBalance;
    }

    public double getSavingBalance() {
        return savingBalance;
    }

    public double getRemainIntAmount() {
        return remainIntAmount;
    }

    public String getIdExpiredDate() {
        return idExpiredDate;
    }

    public String getWrongFullNameConfirmFlag() {
        return wrongFullNameConfirmFlag;
    }

    public String getWrongIdNoConfirmFlag() {
        return wrongIdNoConfirmFlag;
    }

    public String getWrongIssueDateConfirmFlag() {
        return wrongIssueDateConfirmFlag;
    }

    public String getWrongIssuePlaceConfirmFlag() {
        return wrongIssuePlaceConfirmFlag;
    }

    public String getWrongBirthdayConfirmFlag() {
        return wrongBirthdayConfirmFlag;
    }

    public String getCustomerStatus() {
        return customerStatus;
    }

    public String getCoreBankingIdNo() {
        return coreBankingIdNo;
    }

    public String getCoreBankingIssuePlace() {
        return coreBankingIssuePlace;
    }

    public String getCoreBankingIssueDate() {
        return coreBankingIssueDate;
    }

    public String getCoreBankingCustomerName() {
        return coreBankingCustomerName;
    }

    public String getCoreBankingBirthday() {
        return coreBankingBirthday;
    }

    // Setter Methods 
    public void setMainPos(String mainPos) {
        this.mainPos = mainPos;
    }

    public void setPosCode(String posCode) {
        this.posCode = posCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public void setCicCode(String cicCode) {
        this.cicCode = cicCode;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setIsValidCustomerName(String isValidCustomerName) {
        this.isValidCustomerName = isValidCustomerName;
    }

    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public void setNewIdNo(String newIdNo) {
        this.newIdNo = newIdNo;
    }

    public void setIsValidNewIdNo(String isValidNewIdNo) {
        this.isValidNewIdNo = isValidNewIdNo;
    }

    public void setOldIdNo(String oldIdNo) {
        this.oldIdNo = oldIdNo;
    }

    public void setIsValidOldIdNo(String isValidOldIdNo) {
        this.isValidOldIdNo = isValidOldIdNo;
    }

    public void setC06OldIdNo(String c06OldIdNo) {
        this.c06OldIdNo = c06OldIdNo;
    }

    public void setC06NewIdNo(String c06NewIdNo) {
        this.c06NewIdNo = c06NewIdNo;
    }

    public void setCommuneCode(String communeCode) {
        this.communeCode = communeCode;
    }

    public void setCommuneName(String communeName) {
        this.communeName = communeName;
    }

    public void setSubCommuneCode(String subCommuneCode) {
        this.subCommuneCode = subCommuneCode;
    }

    public void setSubCommuneName(String subCommuneName) {
        this.subCommuneName = subCommuneName;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public void setUpdatedDate(String updatedDate) {
        this.updatedDate = updatedDate;
    }

    public void setIntellectUpdateFlag(String intellectUpdateFlag) {
        this.intellectUpdateFlag = intellectUpdateFlag;
    }

    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public void setPrincipleBalance(double principleBalance) {
        this.principleBalance = principleBalance;
    }

    public void setSavingBalance(double savingBalance) {
        this.savingBalance = savingBalance;
    }

    public void setRemainIntAmount(double remainIntAmount) {
        this.remainIntAmount = remainIntAmount;
    }

    public void setIdExpiredDate(String idExpiredDate) {
        this.idExpiredDate = idExpiredDate;
    }

    public void setWrongFullNameConfirmFlag(String wrongFullNameConfirmFlag) {
        this.wrongFullNameConfirmFlag = wrongFullNameConfirmFlag;
    }

    public void setWrongIdNoConfirmFlag(String wrongIdNoConfirmFlag) {
        this.wrongIdNoConfirmFlag = wrongIdNoConfirmFlag;
    }

    public void setWrongIssueDateConfirmFlag(String wrongIssueDateConfirmFlag) {
        this.wrongIssueDateConfirmFlag = wrongIssueDateConfirmFlag;
    }

    public void setWrongIssuePlaceConfirmFlag(String wrongIssuePlaceConfirmFlag) {
        this.wrongIssuePlaceConfirmFlag = wrongIssuePlaceConfirmFlag;
    }

    public void setWrongBirthdayConfirmFlag(String wrongBirthdayConfirmFlag) {
        this.wrongBirthdayConfirmFlag = wrongBirthdayConfirmFlag;
    }

    public void setCustomerStatus(String customerStatus) {
        this.customerStatus = customerStatus;
    }

    public void setCoreBankingIdNo(String coreBankingIdNo) {
        this.coreBankingIdNo = coreBankingIdNo;
    }

    public void setCoreBankingIssuePlace(String coreBankingIssuePlace) {
        this.coreBankingIssuePlace = coreBankingIssuePlace;
    }

    public void setCoreBankingIssueDate(String coreBankingIssueDate) {
        this.coreBankingIssueDate = coreBankingIssueDate;
    }

    public void setCoreBankingCustomerName(String coreBankingCustomerName) {
        this.coreBankingCustomerName = coreBankingCustomerName;
    }

    public void setCoreBankingBirthday(String coreBankingBirthday) {
        this.coreBankingBirthday = coreBankingBirthday;
    }
}
