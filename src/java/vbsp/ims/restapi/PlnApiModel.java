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

public class PlnApiModel {

    private String loanId;
    private String customerId;
    private String customerName;
    private String groupId;
    private String massOrgCode;
    private String communeCode;
    private String capitalSourceCode;
    private String specificProductCode;
    private String loanProgram;
    private String decisionCode;
    private BigInteger normalAmt;
    private BigInteger overdueAmt;
    private BigInteger freezeAmt;
    private BigInteger normal_Paid_Int;
    private BigInteger overdue_Paid_Int;
    private BigInteger total_Paid_Int;
    private BigInteger normal_Remaining_Int;
    private BigInteger overdue_Remaining_Int;
    private BigInteger total_Remaining_Int;
    
    
    private BigInteger able_ToPay_Amt;
    private BigInteger unAble_ToPay_Amt;
    private BigInteger unAble_ToPay_Amt_01;
    private BigInteger unAble_ToPay_Amt_02;
    private BigInteger unAble_ToPay_Amt_03;
    private BigInteger unAble_ToPay_Amt_04;
    private BigInteger unAble_ToPay_Amt_05;
    private BigInteger unAble_ToPay_Amt_06;
    private BigInteger unAble_ToPay_Amt_07;
    private BigInteger unAble_ToPay_Amt_08;
    private BigInteger unAble_ToPay_Amt_09;
    private BigInteger unAble_ToPay_Amt_10;
    private BigInteger unAble_ToPay_Amt_11;
    
    private String unAble_ToPay_Reason;
    private String custRelationship;
    private String status;
    
    private BigInteger deviant_Amt;
    private BigInteger deviant_Int;
    
    private String reason_Deviant;
    private String loanStatus;
    private String reportDate;
    private String updateBy;
    private String updateTime;
    private String posCode;
    private String mainPos;
    private String updateTimeByBranch;
    private String groupName;
    private String debtStatus;
    private String cifNoOfGroup;
    private String groupType;
    private String groupStatus;
    private String loanCloseDate;
    private String reason_Deviant02;

    public String getLoanId() {
        return loanId;
    }

    public void setLoanId(String loanId) {
        this.loanId = loanId;
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

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getMassOrgCode() {
        return massOrgCode;
    }

    public void setMassOrgCode(String massOrgCode) {
        this.massOrgCode = massOrgCode;
    }

    public String getCommuneCode() {
        return communeCode;
    }

    public void setCommuneCode(String communeCode) {
        this.communeCode = communeCode;
    }

    public String getCapitalSourceCode() {
        return capitalSourceCode;
    }

    public void setCapitalSourceCode(String capitalSourceCode) {
        this.capitalSourceCode = capitalSourceCode;
    }

    public String getSpecificProductCode() {
        return specificProductCode;
    }

    public void setSpecificProductCode(String specificProductCode) {
        this.specificProductCode = specificProductCode;
    }

    public String getLoanProgram() {
        return loanProgram;
    }

    public void setLoanProgram(String loanProgram) {
        this.loanProgram = loanProgram;
    }

    public String getDecisionCode() {
        return decisionCode;
    }

    public void setDecisionCode(String decisionCode) {
        this.decisionCode = decisionCode;
    }

    public BigInteger getNormalAmt() {
        return normalAmt;
    }

    public void setNormalAmt(BigInteger normalAmt) {
        this.normalAmt = normalAmt;
    }

    public BigInteger getOverdueAmt() {
        return overdueAmt;
    }

    public void setOverdueAmt(BigInteger overdueAmt) {
        this.overdueAmt = overdueAmt;
    }

    public BigInteger getFreezeAmt() {
        return freezeAmt;
    }

    public void setFreezeAmt(BigInteger freezeAmt) {
        this.freezeAmt = freezeAmt;
    }

    public BigInteger getNormal_Paid_Int() {
        return normal_Paid_Int;
    }

    public void setNormal_Paid_Int(BigInteger normal_Paid_Int) {
        this.normal_Paid_Int = normal_Paid_Int;
    }

    public BigInteger getOverdue_Paid_Int() {
        return overdue_Paid_Int;
    }

    public void setOverdue_Paid_Int(BigInteger overdue_Paid_Int) {
        this.overdue_Paid_Int = overdue_Paid_Int;
    }

    public BigInteger getTotal_Paid_Int() {
        return total_Paid_Int;
    }

    public void setTotal_Paid_Int(BigInteger total_Paid_Int) {
        this.total_Paid_Int = total_Paid_Int;
    }

    public BigInteger getNormal_Remaining_Int() {
        return normal_Remaining_Int;
    }

    public void setNormal_Remaining_Int(BigInteger normal_Remaining_Int) {
        this.normal_Remaining_Int = normal_Remaining_Int;
    }

    public BigInteger getOverdue_Remaining_Int() {
        return overdue_Remaining_Int;
    }

    public void setOverdue_Remaining_Int(BigInteger overdue_Remaining_Int) {
        this.overdue_Remaining_Int = overdue_Remaining_Int;
    }

    public BigInteger getTotal_Remaining_Int() {
        return total_Remaining_Int;
    }

    public void setTotal_Remaining_Int(BigInteger total_Remaining_Int) {
        this.total_Remaining_Int = total_Remaining_Int;
    }

    public BigInteger getAble_ToPay_Amt() {
        return able_ToPay_Amt;
    }

    public void setAble_ToPay_Amt(BigInteger able_ToPay_Amt) {
        this.able_ToPay_Amt = able_ToPay_Amt;
    }

    public BigInteger getUnAble_ToPay_Amt() {
        return unAble_ToPay_Amt;
    }

    public void setUnAble_ToPay_Amt(BigInteger unAble_ToPay_Amt) {
        this.unAble_ToPay_Amt = unAble_ToPay_Amt;
    }

    public BigInteger getUnAble_ToPay_Amt_01() {
        return unAble_ToPay_Amt_01;
    }

    public void setUnAble_ToPay_Amt_01(BigInteger unAble_ToPay_Amt_01) {
        this.unAble_ToPay_Amt_01 = unAble_ToPay_Amt_01;
    }

    public BigInteger getUnAble_ToPay_Amt_02() {
        return unAble_ToPay_Amt_02;
    }

    public void setUnAble_ToPay_Amt_02(BigInteger unAble_ToPay_Amt_02) {
        this.unAble_ToPay_Amt_02 = unAble_ToPay_Amt_02;
    }

    public BigInteger getUnAble_ToPay_Amt_03() {
        return unAble_ToPay_Amt_03;
    }

    public void setUnAble_ToPay_Amt_03(BigInteger unAble_ToPay_Amt_03) {
        this.unAble_ToPay_Amt_03 = unAble_ToPay_Amt_03;
    }

    public BigInteger getUnAble_ToPay_Amt_04() {
        return unAble_ToPay_Amt_04;
    }

    public void setUnAble_ToPay_Amt_04(BigInteger unAble_ToPay_Amt_04) {
        this.unAble_ToPay_Amt_04 = unAble_ToPay_Amt_04;
    }

    public BigInteger getUnAble_ToPay_Amt_05() {
        return unAble_ToPay_Amt_05;
    }

    public void setUnAble_ToPay_Amt_05(BigInteger unAble_ToPay_Amt_05) {
        this.unAble_ToPay_Amt_05 = unAble_ToPay_Amt_05;
    }

    public BigInteger getUnAble_ToPay_Amt_06() {
        return unAble_ToPay_Amt_06;
    }

    public void setUnAble_ToPay_Amt_06(BigInteger unAble_ToPay_Amt_06) {
        this.unAble_ToPay_Amt_06 = unAble_ToPay_Amt_06;
    }

    public BigInteger getUnAble_ToPay_Amt_07() {
        return unAble_ToPay_Amt_07;
    }

    public void setUnAble_ToPay_Amt_07(BigInteger unAble_ToPay_Amt_07) {
        this.unAble_ToPay_Amt_07 = unAble_ToPay_Amt_07;
    }

    public BigInteger getUnAble_ToPay_Amt_08() {
        return unAble_ToPay_Amt_08;
    }

    public void setUnAble_ToPay_Amt_08(BigInteger unAble_ToPay_Amt_08) {
        this.unAble_ToPay_Amt_08 = unAble_ToPay_Amt_08;
    }

    public BigInteger getUnAble_ToPay_Amt_09() {
        return unAble_ToPay_Amt_09;
    }

    public void setUnAble_ToPay_Amt_09(BigInteger unAble_ToPay_Amt_09) {
        this.unAble_ToPay_Amt_09 = unAble_ToPay_Amt_09;
    }

    public BigInteger getUnAble_ToPay_Amt_10() {
        return unAble_ToPay_Amt_10;
    }

    public void setUnAble_ToPay_Amt_10(BigInteger unAble_ToPay_Amt_10) {
        this.unAble_ToPay_Amt_10 = unAble_ToPay_Amt_10;
    }

    public BigInteger getUnAble_ToPay_Amt_11() {
        return unAble_ToPay_Amt_11;
    }

    public void setUnAble_ToPay_Amt_11(BigInteger unAble_ToPay_Amt_11) {
        this.unAble_ToPay_Amt_11 = unAble_ToPay_Amt_11;
    }

    public String getUnAble_ToPay_Reason() {
        return unAble_ToPay_Reason;
    }

    public void setUnAble_ToPay_Reason(String unAble_ToPay_Reason) {
        this.unAble_ToPay_Reason = unAble_ToPay_Reason;
    }

    public String getCustRelationship() {
        return custRelationship;
    }

    public void setCustRelationship(String custRelationship) {
        this.custRelationship = custRelationship;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigInteger getDeviant_Amt() {
        return deviant_Amt;
    }

    public void setDeviant_Amt(BigInteger deviant_Amt) {
        this.deviant_Amt = deviant_Amt;
    }

    public BigInteger getDeviant_Int() {
        return deviant_Int;
    }

    public void setDeviant_Int(BigInteger deviant_Int) {
        this.deviant_Int = deviant_Int;
    }

    public String getReason_Deviant() {
        return reason_Deviant;
    }

    public void setReason_Deviant(String reason_Deviant) {
        this.reason_Deviant = reason_Deviant;
    }

    public String getLoanStatus() {
        return loanStatus;
    }

    public void setLoanStatus(String loanStatus) {
        this.loanStatus = loanStatus;
    }

    public String getReportDate() {
        return reportDate;
    }

    public void setReportDate(String reportDate) {
        this.reportDate = reportDate;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getPosCode() {
        return posCode;
    }

    public void setPosCode(String posCode) {
        this.posCode = posCode;
    }

    public String getMainPos() {
        return mainPos;
    }

    public void setMainPos(String mainPos) {
        this.mainPos = mainPos;
    }

    public String getUpdateTimeByBranch() {
        return updateTimeByBranch;
    }

    public void setUpdateTimeByBranch(String updateTimeByBranch) {
        this.updateTimeByBranch = updateTimeByBranch;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getDebtStatus() {
        return debtStatus;
    }

    public void setDebtStatus(String debtStatus) {
        this.debtStatus = debtStatus;
    }

    public String getCifNoOfGroup() {
        return cifNoOfGroup;
    }

    public void setCifNoOfGroup(String cifNoOfGroup) {
        this.cifNoOfGroup = cifNoOfGroup;
    }

    public String getGroupType() {
        return groupType;
    }

    public void setGroupType(String groupType) {
        this.groupType = groupType;
    }

    public String getGroupStatus() {
        return groupStatus;
    }

    public void setGroupStatus(String groupStatus) {
        this.groupStatus = groupStatus;
    }

    public String getLoanCloseDate() {
        return loanCloseDate;
    }

    public void setLoanCloseDate(String loanCloseDate) {
        this.loanCloseDate = loanCloseDate;
    }

    public String getReason_Deviant02() {
        return reason_Deviant02;
    }

    public void setReason_Deviant02(String reason_Deviant02) {
        this.reason_Deviant02 = reason_Deviant02;
    }

    
}
