/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.restapi;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.Date;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 *
 * @author HP
 */
@XmlAccessorType(XmlAccessType.FIELD)

@JsonInclude(JsonInclude.Include.NON_NULL) // Exclude null fields from JSON
public class DuLieuPLN_Save {

    public String loanId;
    public String customerId;
    public String customerName;
    public String groupId;
    public String massOrgCode;
    public String communeCode;
    public String capitalSourceCode;
    public String specificProductCode;
    public String loanProgram;
    public String decisionCode;
    public Integer normalAmt;
    public Integer overdueAmt;
    public Integer freezeAmt;
    public Integer normal_Paid_Int;
    public Integer overdue_Paid_Int;
    public Integer total_Paid_Int;
    public Integer normal_Remaining_Int;
    public Integer overdue_Remaining_Int;
    public Integer total_Remaining_Int;
    public Integer able_ToPay_Amt;
    public Integer unAble_ToPay_Amt;
    public Integer unAble_ToPay_Amt_01;
    public Integer unAble_ToPay_Amt_02;
    public Integer unAble_ToPay_Amt_03;
    public Integer unAble_ToPay_Amt_04;
    public Integer unAble_ToPay_Amt_05;
    public Integer unAble_ToPay_Amt_06;
    public Integer unAble_ToPay_Amt_07;
    public Integer unAble_ToPay_Amt_08;
    public Integer unAble_ToPay_Amt_09;
    public Integer unAble_ToPay_Amt_10;
    public Integer unAble_ToPay_Amt_11;
    public String unAble_ToPay_Reason;
    public String custRelationship;
    public String status;
    public Integer deviant_Amt;
    public Integer deviant_Int;
    public String reason_Deviant;
    public String loanStatus;
    public String reportDate;
    public String updateBy;
    public String updateTime;
    public String posCode;
    public String mainPos;
    public String updateTimeByBranch;
    public String groupName;
    public String debtStatus;
    public String cifNoOfGroup;
    public String groupType;
    public String groupStatus;
    public Date loanCloseDate;
    public String reason_Deviant02;

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

    public Integer getNormalAmt() {
        return normalAmt;
    }

    public void setNormalAmt(Integer normalAmt) {
        this.normalAmt = normalAmt;
    }

    public Integer getOverdueAmt() {
        return overdueAmt;
    }

    public void setOverdueAmt(Integer overdueAmt) {
        this.overdueAmt = overdueAmt;
    }

    public Integer getFreezeAmt() {
        return freezeAmt;
    }

    public void setFreezeAmt(Integer freezeAmt) {
        this.freezeAmt = freezeAmt;
    }

    public Integer getNormal_Paid_Int() {
        return normal_Paid_Int;
    }

    public void setNormal_Paid_Int(Integer normal_Paid_Int) {
        this.normal_Paid_Int = normal_Paid_Int;
    }

    public Integer getOverdue_Paid_Int() {
        return overdue_Paid_Int;
    }

    public void setOverdue_Paid_Int(Integer overdue_Paid_Int) {
        this.overdue_Paid_Int = overdue_Paid_Int;
    }

    public Integer getTotal_Paid_Int() {
        return total_Paid_Int;
    }

    public void setTotal_Paid_Int(Integer total_Paid_Int) {
        this.total_Paid_Int = total_Paid_Int;
    }

    public Integer getNormal_Remaining_Int() {
        return normal_Remaining_Int;
    }

    public void setNormal_Remaining_Int(Integer normal_Remaining_Int) {
        this.normal_Remaining_Int = normal_Remaining_Int;
    }

    public Integer getOverdue_Remaining_Int() {
        return overdue_Remaining_Int;
    }

    public void setOverdue_Remaining_Int(Integer overdue_Remaining_Int) {
        this.overdue_Remaining_Int = overdue_Remaining_Int;
    }

    public Integer getTotal_Remaining_Int() {
        return total_Remaining_Int;
    }

    public void setTotal_Remaining_Int(Integer total_Remaining_Int) {
        this.total_Remaining_Int = total_Remaining_Int;
    }

    public Integer getAble_ToPay_Amt() {
        return able_ToPay_Amt;
    }

    public void setAble_ToPay_Amt(Integer able_ToPay_Amt) {
        this.able_ToPay_Amt = able_ToPay_Amt;
    }

    public Integer getUnAble_ToPay_Amt() {
        return unAble_ToPay_Amt;
    }

    public void setUnAble_ToPay_Amt(Integer unAble_ToPay_Amt) {
        this.unAble_ToPay_Amt = unAble_ToPay_Amt;
    }

    public Integer getUnAble_ToPay_Amt_01() {
        return unAble_ToPay_Amt_01;
    }

    public void setUnAble_ToPay_Amt_01(Integer unAble_ToPay_Amt_01) {
        this.unAble_ToPay_Amt_01 = unAble_ToPay_Amt_01;
    }

    public Integer getUnAble_ToPay_Amt_02() {
        return unAble_ToPay_Amt_02;
    }

    public void setUnAble_ToPay_Amt_02(Integer unAble_ToPay_Amt_02) {
        this.unAble_ToPay_Amt_02 = unAble_ToPay_Amt_02;
    }

    public Integer getUnAble_ToPay_Amt_03() {
        return unAble_ToPay_Amt_03;
    }

    public void setUnAble_ToPay_Amt_03(Integer unAble_ToPay_Amt_03) {
        this.unAble_ToPay_Amt_03 = unAble_ToPay_Amt_03;
    }

    public Integer getUnAble_ToPay_Amt_04() {
        return unAble_ToPay_Amt_04;
    }

    public void setUnAble_ToPay_Amt_04(Integer unAble_ToPay_Amt_04) {
        this.unAble_ToPay_Amt_04 = unAble_ToPay_Amt_04;
    }

    public Integer getUnAble_ToPay_Amt_05() {
        return unAble_ToPay_Amt_05;
    }

    public void setUnAble_ToPay_Amt_05(Integer unAble_ToPay_Amt_05) {
        this.unAble_ToPay_Amt_05 = unAble_ToPay_Amt_05;
    }

    public Integer getUnAble_ToPay_Amt_06() {
        return unAble_ToPay_Amt_06;
    }

    public void setUnAble_ToPay_Amt_06(Integer unAble_ToPay_Amt_06) {
        this.unAble_ToPay_Amt_06 = unAble_ToPay_Amt_06;
    }

    public Integer getUnAble_ToPay_Amt_07() {
        return unAble_ToPay_Amt_07;
    }

    public void setUnAble_ToPay_Amt_07(Integer unAble_ToPay_Amt_07) {
        this.unAble_ToPay_Amt_07 = unAble_ToPay_Amt_07;
    }

    public Integer getUnAble_ToPay_Amt_08() {
        return unAble_ToPay_Amt_08;
    }

    public void setUnAble_ToPay_Amt_08(Integer unAble_ToPay_Amt_08) {
        this.unAble_ToPay_Amt_08 = unAble_ToPay_Amt_08;
    }

    public Integer getUnAble_ToPay_Amt_09() {
        return unAble_ToPay_Amt_09;
    }

    public void setUnAble_ToPay_Amt_09(Integer unAble_ToPay_Amt_09) {
        this.unAble_ToPay_Amt_09 = unAble_ToPay_Amt_09;
    }

    public Integer getUnAble_ToPay_Amt_10() {
        return unAble_ToPay_Amt_10;
    }

    public void setUnAble_ToPay_Amt_10(Integer unAble_ToPay_Amt_10) {
        this.unAble_ToPay_Amt_10 = unAble_ToPay_Amt_10;
    }

    public Integer getUnAble_ToPay_Amt_11() {
        return unAble_ToPay_Amt_11;
    }

    public void setUnAble_ToPay_Amt_11(Integer unAble_ToPay_Amt_11) {
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

    public Integer getDeviant_Amt() {
        return deviant_Amt;
    }

    public void setDeviant_Amt(Integer deviant_Amt) {
        this.deviant_Amt = deviant_Amt;
    }

    public Integer getDeviant_Int() {
        return deviant_Int;
    }

    public void setDeviant_Int(Integer deviant_Int) {
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

    public Date getLoanCloseDate() {
        return loanCloseDate;
    }

    public void setLoanCloseDate(Date loanCloseDate) {
        this.loanCloseDate = loanCloseDate;
    }

    public String getReason_Deviant02() {
        return reason_Deviant02;
    }

    public void setReason_Deviant02(String reason_Deviant02) {
        this.reason_Deviant02 = reason_Deviant02;
    }

}
