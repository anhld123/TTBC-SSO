/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.restapi;

import java.util.ArrayList;
import java.util.Date;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 *
 * @author HP
 */
@XmlAccessorType(XmlAccessType.FIELD)

public class CustomerBlackList {

    public int order;
    public String fullName;
    public int birthDate;
    public int birthMonth;
    public int birthYear;
    public String permanentAddress;
    public String currentAddress;
    public String idNumber;
    public Date issueDate;
    public String issuePlace;
    public String passportId;
    public Date passportIssueDate;
    public String passportIssuePlace;
    public String criminalOffense;
    public String fatherName;
    public String motherName;
    public String decisionNo;
    public String decisionDate;
    public String decisionPlace;
    public String offenseType;
    public String fullNameNoAccent;

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(int birthDate) {
        this.birthDate = birthDate;
    }

    public int getBirthMonth() {
        return birthMonth;
    }

    public void setBirthMonth(int birthMonth) {
        this.birthMonth = birthMonth;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }

    public String getPermanentAddress() {
        return permanentAddress;
    }

    public void setPermanentAddress(String permanentAddress) {
        this.permanentAddress = permanentAddress;
    }

    public String getCurrentAddress() {
        return currentAddress;
    }

    public void setCurrentAddress(String currentAddress) {
        this.currentAddress = currentAddress;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public String getIssuePlace() {
        return issuePlace;
    }

    public void setIssuePlace(String issuePlace) {
        this.issuePlace = issuePlace;
    }

    public String getPassportId() {
        return passportId;
    }

    public void setPassportId(String passportId) {
        this.passportId = passportId;
    }

    public Date getPassportIssueDate() {
        return passportIssueDate;
    }

    public void setPassportIssueDate(Date passportIssueDate) {
        this.passportIssueDate = passportIssueDate;
    }

    public String getPassportIssuePlace() {
        return passportIssuePlace;
    }

    public void setPassportIssuePlace(String passportIssuePlace) {
        this.passportIssuePlace = passportIssuePlace;
    }

    public String getCriminalOffense() {
        return criminalOffense;
    }

    public void setCriminalOffense(String criminalOffense) {
        this.criminalOffense = criminalOffense;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getMotherName() {
        return motherName;
    }

    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }

    public String getDecisionNo() {
        return decisionNo;
    }

    public void setDecisionNo(String decisionNo) {
        this.decisionNo = decisionNo;
    }

    public String getDecisionDate() {
        return decisionDate;
    }

    public void setDecisionDate(String decisionDate) {
        this.decisionDate = decisionDate;
    }

    public String getDecisionPlace() {
        return decisionPlace;
    }

    public void setDecisionPlace(String decisionPlace) {
        this.decisionPlace = decisionPlace;
    }

    public String getOffenseType() {
        return offenseType;
    }

    public void setOffenseType(String offenseType) {
        this.offenseType = offenseType;
    }

    public String getFullNameNoAccent() {
        return fullNameNoAccent;
    }

    public void setFullNameNoAccent(String fullNameNoAccent) {
        this.fullNameNoAccent = fullNameNoAccent;
    }

    private boolean isSuccess;
    private float code;
    private String message;
    ArrayList<CustomerBlackList> result = new ArrayList<>();

    // Getter Methods 
    public boolean getIsSuccess() {
        return isSuccess;
    }

    public float getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    // Setter Methods 
    public void setIsSuccess(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public void setCode(float code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<CustomerBlackList> getResult() {
        return result;
    }

    public void setResult(ArrayList<CustomerBlackList> result) {
        this.result = result;
    }
}
