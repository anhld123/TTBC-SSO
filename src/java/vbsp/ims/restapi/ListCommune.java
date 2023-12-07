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

public class ListCommune {

  public String communeCode;
  public String communeName;
  public String posCode;
  public String status;
  public String isInCommune;
  public String is135Commune;
  public String visitDate;
  public String isNewCountryside;
  public String txnPointCode;
  public String txnPointName;
  public String txnPointDate;
  public String provinceCode;
  public String provinceName;
  public String districtCode;
  public String districtName;

    public String getCommuneCode() {
        return communeCode;
    }

    public void setCommuneCode(String communeCode) {
        this.communeCode = communeCode;
    }

    public String getCommuneName() {
        return communeName;
    }

    public void setCommuneName(String communeName) {
        this.communeName = communeName;
    }

    public String getPosCode() {
        return posCode;
    }

    public void setPosCode(String posCode) {
        this.posCode = posCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIsInCommune() {
        return isInCommune;
    }

    public void setIsInCommune(String isInCommune) {
        this.isInCommune = isInCommune;
    }

    public String getIs135Commune() {
        return is135Commune;
    }

    public void setIs135Commune(String is135Commune) {
        this.is135Commune = is135Commune;
    }

    public String getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(String visitDate) {
        this.visitDate = visitDate;
    }

    public String getIsNewCountryside() {
        return isNewCountryside;
    }

    public void setIsNewCountryside(String isNewCountryside) {
        this.isNewCountryside = isNewCountryside;
    }

    public String getTxnPointCode() {
        return txnPointCode;
    }

    public void setTxnPointCode(String txnPointCode) {
        this.txnPointCode = txnPointCode;
    }

    public String getTxnPointName() {
        return txnPointName;
    }

    public void setTxnPointName(String txnPointName) {
        this.txnPointName = txnPointName;
    }

    public String getTxnPointDate() {
        return txnPointDate;
    }

    public void setTxnPointDate(String txnPointDate) {
        this.txnPointDate = txnPointDate;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getDistrictCode() {
        return districtCode;
    }

    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
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
    public ArrayList<ListCommune> getResult() {
        return result;
    }

    public void setResult(ArrayList<ListCommune> result) {
        this.result = result;
    }
    public boolean isSuccess;
    public float code;
    public String message;
    ArrayList<ListCommune> result = new ArrayList<>();
}