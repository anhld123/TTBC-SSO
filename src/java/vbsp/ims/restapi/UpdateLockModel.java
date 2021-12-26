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

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
 
//@XmlRootElement(name = "dulieuNT")
@XmlAccessorType (XmlAccessType.FIELD)

public class UpdateLockModel {
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

 
 public String getReportKey() {
  return reportKey;
 }

    // Getter Methods
    public void setReportKey(String reportKey) {
        this.reportKey = reportKey;
    }

    public String getMainPos() {
        return mainPos;
    }

 public String getPosCode() {
  return posCode;
 }

 public String getPosFlag() {
  return posFlag;
 }

 public String getPosName() {
  return posName;
 }

 public String getReportDate() {
  return reportDate;
 }

 public String getStatus() {
  return status;
 }

 public String getMakerId() {
  return makerId;
 }

 public String getMakerDate() {
  return makerDate;
 }

 public String getUpdateId() {
  return updateId;
 }

 public String getUpdateDate() {
  return updateDate;
 }

 // Setter Methods 

 public void setMainPos(String mainPos) {
  this.mainPos = mainPos;
 }

 public void setPosCode(String posCode) {
  this.posCode = posCode;
 }

 public void setPosFlag(String posFlag) {
  this.posFlag = posFlag;
 }

 public void setPosName(String posName) {
  this.posName = posName;
 }

 public void setReportDate(String reportDate) {
  this.reportDate = reportDate;
 }

 public void setStatus(String status) {
  this.status = status;
 }

 public void setMakerId(String makerId) {
  this.makerId = makerId;
 }

 public void setMakerDate(String makerDate) {
  this.makerDate = makerDate;
 }

 public void setUpdateId(String updateId) {
  this.updateId = updateId;
 }

 public void setUpdateDate(String updateDate) {
  this.updateDate = updateDate;
 }
 
}
