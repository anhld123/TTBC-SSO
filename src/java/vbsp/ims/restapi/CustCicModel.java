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
    
    private int id;
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
    private String statusString;
    private int type;
    private String createdBy;
    private String createdDate;
    private String updatedBy;
    private String updatedDate;
    private int intellectUpdateFlag;
    private String groupCode;
    private String mobileNumber;
    private String remark;

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

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
    
    private int profileCorrectConfirmFlag;
    private int profileMissingConfirmFlag;
    private int customerWrongIdNoConfirmFlag;
    private int customerWrongFullNameConfirmFlag;
    private int customerWrongBirthdayConfirmFlag;
    private int customerNotIdConfirmFlag;  
    private int customerReviewStatus;
    
    private String c14;
    private String c15;
    private String c16;
//    private String c17;
//    private String c18;
    private String c19;
    
     private String c20;
     private String c21;
     //xác nhận với khách hàng
     private String c28;
     private String c29;
     private String c30;
     private String c31;
     private String c32;
     private String c33;

    public String getC21() {
        return c21;
    }

    public void setC21(String c21) {
        this.c21 = c21;
    }

    public String getC28() {
        return c28;
    }

    public void setC28(String c28) {
        this.c28 = c28;
    }

    public String getC29() {
        return c29;
    }

    public void setC29(String c29) {
        this.c29 = c29;
    }

    public String getC30() {
        return c30;
    }

    public void setC30(String c30) {
        this.c30 = c30;
    }

    public String getC31() {
        return c31;
    }

    public void setC31(String c31) {
        this.c31 = c31;
    }

    public String getC32() {
        return c32;
    }

    public void setC32(String c32) {
        this.c32 = c32;
    }

    public String getC33() {
        return c33;
    }

    public void setC33(String c33) {
        this.c33 = c33;
    }
     
     

    public String getC14() {
        return c14;
    }

    public void setC14(String c14) {
        this.c14 = c14;
    }

    public String getC20() {
        return c20;
    }

    public void setC20(String c20) {
        this.c20 = c20;
    }
     
     

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    

    public String getStatusString() {
        return statusString;
    }

    public void setStatusString(String statusString) {
        this.statusString = statusString;
    }
    

    public String getC15() {
        return c15;
    }

    public void setC15(String c15) {
        this.c15 = c15;
    }

    public String getC16() {
        return c16;
    }

    public void setC16(String c16) {
        this.c16 = c16;
    }

    

    public String getC19() {
        return c19;
    }

    public void setC19(String c19) {
        this.c19 = c19;
    }
    
    

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

    public int getProfileCorrectConfirmFlag() {
        return profileCorrectConfirmFlag;
    }

    public void setProfileCorrectConfirmFlag(int profileCorrectConfirmFlag) {
        this.profileCorrectConfirmFlag = profileCorrectConfirmFlag;
    }

    public int getProfileMissingConfirmFlag() {
        return profileMissingConfirmFlag;
    }

    public void setProfileMissingConfirmFlag(int profileMissingConfirmFlag) {
        this.profileMissingConfirmFlag = profileMissingConfirmFlag;
    }

    public int getCustomerWrongIdNoConfirmFlag() {
        return customerWrongIdNoConfirmFlag;
    }

    public void setCustomerWrongIdNoConfirmFlag(int customerWrongIdNoConfirmFlag) {
        this.customerWrongIdNoConfirmFlag = customerWrongIdNoConfirmFlag;
    }

    public int getCustomerWrongFullNameConfirmFlag() {
        return customerWrongFullNameConfirmFlag;
    }

    public void setCustomerWrongFullNameConfirmFlag(int customerWrongFullNameConfirmFlag) {
        this.customerWrongFullNameConfirmFlag = customerWrongFullNameConfirmFlag;
    }

    public int getCustomerWrongBirthdayConfirmFlag() {
        return customerWrongBirthdayConfirmFlag;
    }

    public void setCustomerWrongBirthdayConfirmFlag(int customerWrongBirthdayConfirmFlag) {
        this.customerWrongBirthdayConfirmFlag = customerWrongBirthdayConfirmFlag;
    }

    public int getCustomerNotIdConfirmFlag() {
        return customerNotIdConfirmFlag;
    }

    public void setCustomerNotIdConfirmFlag(int customerNotIdConfirmFlag) {
        this.customerNotIdConfirmFlag = customerNotIdConfirmFlag;
    }

    public int getCustomerReviewStatus() {
        return customerReviewStatus;
    }

    public void setCustomerReviewStatus(int customerReviewStatus) {
        this.customerReviewStatus = customerReviewStatus;
    }
    
    
}
