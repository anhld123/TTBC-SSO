/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.restapi;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 *
 * @author HP
 */
@XmlAccessorType(XmlAccessType.FIELD)

public class ListTranData {
  public String transactionPointCode;
  public String transactionPointName;
  public String transactionDate;
  public String status;
  public String posCode;
  public String communeCode;
  public String createdBy;
  public String createdDate;
  public String lastUpdatedDate;
  public String branchCode;

    public String getTransactionPointCode() {
        return transactionPointCode;
    }

    public void setTransactionPointCode(String transactionPointCode) {
        this.transactionPointCode = transactionPointCode;
    }

    public String getTransactionPointName() {
        return transactionPointName;
    }

    public void setTransactionPointName(String transactionPointName) {
        this.transactionPointName = transactionPointName;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPosCode() {
        return posCode;
    }

    public void setPosCode(String posCode) {
        this.posCode = posCode;
    }

    public String getCommuneCode() {
        return communeCode;
    }

    public void setCommuneCode(String communeCode) {
        this.communeCode = communeCode;
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

    public String getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public void setLastUpdatedDate(String lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public String getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

   

    public boolean isIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public float getCode() {
        return code;
    }

    public void setCode(float code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<ListTranData> getResult() {
        return result;
    }

    public void setResult(ArrayList<ListTranData> result) {
        this.result = result;
    }

   
    public boolean isSuccess;
    public float code;
    public String message;
    ArrayList<ListTranData> result = new ArrayList<>();
}