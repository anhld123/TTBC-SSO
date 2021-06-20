/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vbsp.ims.model;

import java.io.Serializable;

/**
 *
 * @author Trung
 */
public class User implements Serializable{
    private String priUserCode;
    private String priUserName;
    private String priAddress;
    private String priMobile;
    private String priOffice;
    private String priPassword;
    private String priPubKey;
    private String priUserGroup;
    private String priRptGrade;
    private String priPosCode;
    private String priStatus;
    private String priMaCanBo;
    private String priNhomCongViec;
    private int priValidFlag;
    
    public User(){}
    public User(String paraUserCode,String paraUserName,String paraAddress,String paraMobile,String paraOffice,
    String paraPassword,String paraPubKey,String paraUserGroup,String paraRptGrade,String paraPosCode,String paraStatus,
    String paraMaCanBo, String paraNhomCongViec, int paraValidFlag){
        this.priUserCode = paraUserCode;
        this.priUserName = paraUserName;
        this.priAddress = paraAddress;
        this.priMobile = paraMobile;
        this.priOffice = paraOffice;
        this.priPassword = paraPassword;
        this.priPubKey = paraPubKey;
        this.priUserGroup = paraUserGroup;
        this.priRptGrade = paraRptGrade;
        this.priPosCode = paraPosCode;
        this.priStatus = paraStatus;
        this.priMaCanBo = paraMaCanBo;
        this.priNhomCongViec = paraNhomCongViec;
        this.priValidFlag = paraValidFlag;
    }

    public String getPriUserCode() {
        return priUserCode;
    }

    public void setPriUserCode(String priUserCode) {
        this.priUserCode = priUserCode;
    }

    public String getPriUserName() {
        return priUserName;
    }

    public void setPriUserName(String priUserName) {
        this.priUserName = priUserName;
    }

    public String getPriAddress() {
        return priAddress;
    }

    public void setPriAddress(String priAddress) {
        this.priAddress = priAddress;
    }

    public String getPriMobile() {
        return priMobile;
    }

    public void setPriMobile(String priMobile) {
        this.priMobile = priMobile;
    }

    public String getPriOffice() {
        return priOffice;
    }

    public void setPriOffice(String priOffice) {
        this.priOffice = priOffice;
    }

    public String getPriPassword() {
        return priPassword;
    }

    public void setPriPassword(String priPassword) {
        this.priPassword = priPassword;
    }

    public String getPriPubKey() {
        return priPubKey;
    }

    public void setPriPubKey(String priPubKey) {
        this.priPubKey = priPubKey;
    }

    public String getPriUserGroup() {
        return priUserGroup;
    }

    public void setPriUserGroup(String priUserGroup) {
        this.priUserGroup = priUserGroup;
    }

    public String getPriRptGrade() {
        return priRptGrade;
    }

    public void setPriRptGrade(String priRptGrade) {
        this.priRptGrade = priRptGrade;
    }

    public String getPriPosCode() {
        return priPosCode;
    }

    public void setPriPosCode(String priPosCode) {
        this.priPosCode = priPosCode;
    }

    public String getPriStatus() {
        return priStatus;
    }

    public void setPriStatus(String priStatus) {
        this.priStatus = priStatus;
    }

    public String getPriMaCanBo() {
        return priMaCanBo;
    }

    public void setPriMaCanBo(String priMaCanBo) {
        this.priMaCanBo = priMaCanBo;
    }

    public String getPriNhomCongViec() {
        return priNhomCongViec;
    }

    public void setPriNhomCongViec(String priNhomCongViec) {
        this.priNhomCongViec = priNhomCongViec;
    }

    public int getPriValidFlag() {
        return priValidFlag;
    }

    public void setPriValidFlag(int priValidFlag) {
        this.priValidFlag = priValidFlag;
    }
    
    
    
     
    public User clone() {
        User user = new User(
                this.getPriUserCode(),
                this.getPriUserName(),
                this.getPriAddress()
                ,this.getPriMobile(),
                this.getPriOffice(),
                this.getPriPassword(),
                this.getPriPubKey(),
                this.getPriUserGroup(),
                this.getPriRptGrade(),
                this.getPriPosCode(),
                this.getPriStatus(),
                this.getPriMaCanBo(),
                this.getPriNhomCongViec(),
                this.getPriValidFlag()
        );
        return user;
    }
}
