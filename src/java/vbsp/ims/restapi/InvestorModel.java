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

public class InvestorModel {private String mainPos;
 private String posCode;
 private String reportDate;
 private String investorCode;
 private String investorName;
 private String specificProductCode;
 private String specificProductName;
 private String makerId;
 private String makerDate;
 private String updateId;
 private String updateDate;
 private String status;


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

 public String getInvestorCode() {
  return investorCode;
 }

 public String getInvestorName() {
  return investorName;
 }

 public String getSpecificProductCode() {
  return specificProductCode;
 }

 public String getSpecificProductName() {
  return specificProductName;
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

 public String getStatus() {
  return status;
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

 public void setInvestorCode(String investorCode) {
  this.investorCode = investorCode;
 }

 public void setInvestorName(String investorName) {
  this.investorName = investorName;
 }

 public void setSpecificProductCode(String specificProductCode) {
  this.specificProductCode = specificProductCode;
 }

 public void setSpecificProductName(String specificProductName) {
  this.specificProductName = specificProductName;
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

 public void setStatus(String status) {
  this.status = status;
 }
}
