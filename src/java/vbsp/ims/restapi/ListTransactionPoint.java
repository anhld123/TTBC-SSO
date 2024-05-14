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

public class ListTransactionPoint {

  public String communeId;
  public String communeName;
  public String transDate;
  public String transTime;
  public String inCommuneFlag;
  public String inBranch;
  public String interWardFlag;
  public String interWardName;
  public String effectDate;
  public String status;
  public String transactionPoint;
  public String posCode;

    public String getCommuneId() {
        return communeId;
    }

    public void setCommuneId(String communeId) {
        this.communeId = communeId;
    }

    public String getCommuneName() {
        return communeName;
    }

    public void setCommuneName(String communeName) {
        this.communeName = communeName;
    }

    public String getTransDate() {
        return transDate;
    }

    public void setTransDate(String transDate) {
        this.transDate = transDate;
    }

    public String getTransTime() {
        return transTime;
    }

    public void setTransTime(String transTime) {
        this.transTime = transTime;
    }

    public String getInCommuneFlag() {
        return inCommuneFlag;
    }

    public void setInCommuneFlag(String inCommuneFlag) {
        this.inCommuneFlag = inCommuneFlag;
    }

    public String getInBranch() {
        return inBranch;
    }

    public void setInBranch(String inBranch) {
        this.inBranch = inBranch;
    }

    public String getInterWardFlag() {
        return interWardFlag;
    }

    public void setInterWardFlag(String interWardFlag) {
        this.interWardFlag = interWardFlag;
    }

    public String getInterWardName() {
        return interWardName;
    }

    public void setInterWardName(String interWardName) {
        this.interWardName = interWardName;
    }

    public String getEffectDate() {
        return effectDate;
    }

    public void setEffectDate(String effectDate) {
        this.effectDate = effectDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTransactionPoint() {
        return transactionPoint;
    }

    public void setTransactionPoint(String transactionPoint) {
        this.transactionPoint = transactionPoint;
    }

    public String getPosCode() {
        return posCode;
    }

    public void setPosCode(String posCode) {
        this.posCode = posCode;
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
    public ArrayList<ListTransactionPoint> getResult() {
        return result;
    }

    public void setResult(ArrayList<ListTransactionPoint> result) {
        this.result = result;
    }
    public boolean isSuccess;
    public float code;
    public String message;
    ArrayList<ListTransactionPoint> result = new ArrayList<>();
}