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
    private int isValidCustomerName;
    private String birthDay;
    private String idNo;
    private String newIdNo;
    private int isValidNewIdNo;
    private String oldIdNo;
    private String isValidOldIdNo;
    private String c06OldIdNo;
    private String c06NewIdNo;
    private String communeCode;
    private String communeName;
    private String subCommuneCode;
    private String subCommuneName;
    private int status;
    private int type;
    private String createdBy;
    private String createdDate;
    private String updatedBy;
    private String updatedDate;
    private int intellectUpdateFlag;
    private String groupCode;
    private String mobileNumber;


    @XmlElement(defaultValue = "0")
    BigInteger principleBalance;
    @XmlElement(defaultValue = "0")
    BigInteger savingBalance;
    @XmlElement(defaultValue = "0")
    BigInteger remainIntAmount;

    public BigInteger getPrincipleBalance() {
//        return principleBalance;
        return principleBalance == null ? new BigInteger("0") : principleBalance;
    }

    public void setPrincipleBalance(BigInteger principleBalance) {
        this.principleBalance = principleBalance;
    }

    public BigInteger getSavingBalance() {
//        return savingBalance;
        return savingBalance == null ? new BigInteger("0") : savingBalance;
    }

    public void setSavingBalance(BigInteger savingBalance) {
        this.savingBalance = savingBalance;
    }

    public BigInteger getRemainIntAmount() {
        return remainIntAmount == null ? new BigInteger("0") : remainIntAmount;
//        return remainIntAmount;
    }

    public void setRemainIntAmount(BigInteger remainIntAmount) {
        this.remainIntAmount = remainIntAmount;
    }
    
    private String idExpiredDate;
    private int wrongFullNameConfirmFlag;
    private int wrongIdNoConfirmFlag;
    private int wrongIssueDateConfirmFlag;
    private int wrongIssuePlaceConfirmFlag;
    private int wrongBirthdayConfirmFlag;
    private int customerStatus;
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

    public String getBirthDay() {
        return birthDay;
    }

    public String getIdNo() {
        return idNo;
    }

    public String getNewIdNo() {
        return newIdNo;
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

    public String getGroupCode() {
        return groupCode;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    

    public String getIdExpiredDate() {
        return idExpiredDate;
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

    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public void setNewIdNo(String newIdNo) {
        this.newIdNo = newIdNo;
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

    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    
    public void setIdExpiredDate(String idExpiredDate) {
        this.idExpiredDate = idExpiredDate;
    }

    public void setCoreBankingIdNo(String coreBankingIdNo) {
        this.coreBankingIdNo = coreBankingIdNo;
    }

    public int getIsValidCustomerName() {
        return isValidCustomerName;
    }

    public void setIsValidCustomerName(int isValidCustomerName) {
        this.isValidCustomerName = isValidCustomerName;
    }

    public int getIsValidNewIdNo() {
        return isValidNewIdNo;
    }

    public void setIsValidNewIdNo(int isValidNewIdNo) {
        this.isValidNewIdNo = isValidNewIdNo;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getIntellectUpdateFlag() {
        return intellectUpdateFlag;
    }

    public void setIntellectUpdateFlag(int intellectUpdateFlag) {
        this.intellectUpdateFlag = intellectUpdateFlag;
    }

    public int getWrongFullNameConfirmFlag() {
        return wrongFullNameConfirmFlag;
    }

    public void setWrongFullNameConfirmFlag(int wrongFullNameConfirmFlag) {
        this.wrongFullNameConfirmFlag = wrongFullNameConfirmFlag;
    }

    public int getWrongIdNoConfirmFlag() {
        return wrongIdNoConfirmFlag;
    }

    public void setWrongIdNoConfirmFlag(int wrongIdNoConfirmFlag) {
        this.wrongIdNoConfirmFlag = wrongIdNoConfirmFlag;
    }

    public int getWrongIssueDateConfirmFlag() {
        return wrongIssueDateConfirmFlag;
    }

    public void setWrongIssueDateConfirmFlag(int wrongIssueDateConfirmFlag) {
        this.wrongIssueDateConfirmFlag = wrongIssueDateConfirmFlag;
    }

    public int getWrongIssuePlaceConfirmFlag() {
        return wrongIssuePlaceConfirmFlag;
    }

    public void setWrongIssuePlaceConfirmFlag(int wrongIssuePlaceConfirmFlag) {
        this.wrongIssuePlaceConfirmFlag = wrongIssuePlaceConfirmFlag;
    }

    public int getWrongBirthdayConfirmFlag() {
        return wrongBirthdayConfirmFlag;
    }

    public void setWrongBirthdayConfirmFlag(int wrongBirthdayConfirmFlag) {
        this.wrongBirthdayConfirmFlag = wrongBirthdayConfirmFlag;
    }

    public int getCustomerStatus() {
        return customerStatus;
    }

    public void setCustomerStatus(int customerStatus) {
        this.customerStatus = customerStatus;
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
