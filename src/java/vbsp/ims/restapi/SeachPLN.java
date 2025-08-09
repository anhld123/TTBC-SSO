/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.restapi;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 *
 * @author HP
 */
@XmlAccessorType(XmlAccessType.FIELD)

public class SeachPLN {

     public String posCode;
    public String posName;
    public String mainPos;
    public String status;
    public String address;
    public String faxNumber;
    public String phoneNo;
    public String codeSBV;
    public String posType;
    public String groupPos;
    public String makerId;
    public String makerDT;

    public String getPosCode() {
        return posCode;
    }

    public void setPosCode(String posCode) {
        this.posCode = posCode;
    }

    public String getPosName() {
        return posName;
    }

    public void setPosName(String posName) {
        this.posName = posName;
    }

    public String getMainPos() {
        return mainPos;
    }

    public void setMainPos(String mainPos) {
        this.mainPos = mainPos;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFaxNumber() {
        return faxNumber;
    }

    public void setFaxNumber(String faxNumber) {
        this.faxNumber = faxNumber;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getCodeSBV() {
        return codeSBV;
    }

    public void setCodeSBV(String codeSBV) {
        this.codeSBV = codeSBV;
    }

    public String getPosType() {
        return posType;
    }

    public void setPosType(String posType) {
        this.posType = posType;
    }

    public String getGroupPos() {
        return groupPos;
    }

    public void setGroupPos(String groupPos) {
        this.groupPos = groupPos;
    }

    public String getMakerId() {
        return makerId;
    }

    public void setMakerId(String makerId) {
        this.makerId = makerId;
    }

    public String getMakerDT() {
        return makerDT;
    }

    public void setMakerDT(String makerDT) {
        this.makerDT = makerDT;
    }
    
    
}
