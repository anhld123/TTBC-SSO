/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.bcqt.model;

/**
 *
 * @author HP
 */
public class CommisionFP {
    private String posCode ;
    private String refNo;
    private String valDate;
    private String accountNo;
    private String accountPos;
    private String flag;
    private int amount;
    private String remark; 

    public CommisionFP(String posCode, String refNo, String valDate, String accountNo, String accountPos, String flag, int amount, String remark) {
        this.posCode = posCode;
        this.refNo = refNo;
        this.valDate = valDate;
        this.accountNo = accountNo;
        this.accountPos = accountPos;
        this.flag = flag;
        this.amount = amount;
        this.remark = remark;
    }
    
    public String getPosCode() {
        return posCode;
    }

    public void setPosCode(String posCode) {
        this.posCode = posCode;
    }

    public String getRefNo() {
        return refNo;
    }

    public void setRefNo(String refNo) {
        this.refNo = refNo;
    }

    public String getValDate() {
        return valDate;
    }

    public void setValDate(String valDate) {
        this.valDate = valDate;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getAccountPos() {
        return accountPos;
    }

    public void setAccountPos(String accountPos) {
        this.accountPos = accountPos;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
    
    
}
