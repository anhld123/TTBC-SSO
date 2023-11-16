/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.restapi;

import java.math.BigInteger;

/**
 *
 * @author HP
 */
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

//@XmlRootElement(name = "dulieuNT")
@XmlAccessorType(XmlAccessType.FIELD)

public class LockSendCiCModel {

    private String reportKey;
    private String mainPos;
    private String posCode;
    private String posFlag;
    private String posName;
    private String reportDate;
    private String status;
    private String makerId;
    private String makerDate;
    private String updateId;
    private String updateDate;
    private BigInteger customerTotal;
    private BigInteger customerNotReviewCount;
    private BigInteger intTotal;
    private BigInteger intDeductionTotal;
    
    private BigInteger customerNotReviewCount1;
    private BigInteger intTotal1;
    private BigInteger intDeductionTotal1;

    public BigInteger getCustomerNotReviewCount1() {
        return customerNotReviewCount1;
    }

    public void setCustomerNotReviewCount1(BigInteger customerNotReviewCount1) {
        this.customerNotReviewCount1 = customerNotReviewCount1;
    }

    public BigInteger getIntTotal1() {
        return intTotal1;
    }

    public void setIntTotal1(BigInteger intTotal1) {
        this.intTotal1 = intTotal1;
    }

    public BigInteger getIntDeductionTotal1() {
        return intDeductionTotal1;
    }

    public void setIntDeductionTotal1(BigInteger intDeductionTotal1) {
        this.intDeductionTotal1 = intDeductionTotal1;
    }
    
    

    public String getReportKey() {
        return reportKey;
    }

    public void setReportKey(String reportKey) {
        this.reportKey = reportKey;
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

    public String getPosFlag() {
        return posFlag;
    }

    public void setPosFlag(String posFlag) {
        this.posFlag = posFlag;
    }

    public String getPosName() {
        return posName;
    }

    public void setPosName(String posName) {
        this.posName = posName;
    }

    public String getReportDate() {
        return reportDate;
    }

    public void setReportDate(String reportDate) {
        this.reportDate = reportDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getUpdateId() {
        return updateId;
    }

    public void setUpdateId(String updateId) {
        this.updateId = updateId;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public BigInteger getCustomerTotal() {
        return customerTotal;
    }

    public void setCustomerTotal(BigInteger customerTotal) {
        this.customerTotal = customerTotal;
    }

    public BigInteger getCustomerNotReviewCount() {
        return customerNotReviewCount;
    }

    public void setCustomerNotReviewCount(BigInteger customerNotReviewCount) {
        this.customerNotReviewCount = customerNotReviewCount;
    }

    public BigInteger getIntTotal() {
        return intTotal;
    }

    public void setIntTotal(BigInteger intTotal) {
        this.intTotal = intTotal;
    }

    public BigInteger getIntDeductionTotal() {
        return intDeductionTotal;
    }

    public void setIntDeductionTotal(BigInteger intDeductionTotal) {
        this.intDeductionTotal = intDeductionTotal;
    }
    

}
