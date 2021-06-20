/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vbsp.ims.loveleaf;

/**
 *
 * @author Trung
 */
public class DonateTransaction {
    private String ref_no;
    private String donator_id;
    private String donate_dt;
    private int  amount;
    private String donator_ac;
    private String donator_bank;
    private String cust_remark;
    private String donator_name;
    private String status;    
    
    
    public DonateTransaction(){}
    
    public DonateTransaction(String ref_no,
    String donator_id,
    String donate_dt,
    int  amount,
    String donator_ac,
    String donator_bank,
    String cust_remark,
    String donator_name,
    String status){
        this.ref_no = ref_no;
        this.donator_id = donator_id;
        this.donate_dt = donate_dt;
        this.amount = amount;
        this.donator_ac = donator_ac;
        this.cust_remark = cust_remark;
        this.donator_name = donator_name;
        this.status = status;
        this.donator_bank = donator_bank;
    }

    public String getRef_no() {
        return ref_no;
    }

    public void setRef_no(String ref_no) {
        this.ref_no = ref_no;
    }

    public String getDonator_id() {
        return donator_id;
    }

    public void setDonator_id(String donator_id) {
        this.donator_id = donator_id;
    }

    public String getDonate_dt() {
        return donate_dt;
    }

    public void setDonate_dt(String donate_dt) {
        this.donate_dt = donate_dt;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getDonator_ac() {
        return donator_ac;
    }

    public void setDonator_ac(String donator_ac) {
        this.donator_ac = donator_ac;
    }

    public String getCust_remark() {
        return cust_remark;
    }

    public void setCust_remark(String cust_remark) {
        this.cust_remark = cust_remark;
    }

    public String getDonator_name() {
        return donator_name;
    }

    public void setDonator_name(String donator_name) {
        this.donator_name = donator_name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus_desc() {
        if (status.equals("D"))
            return "Đã chuyển";
        else if (status.equals("N"))
            return "Chưa chuyển";
        else
            return "Đang xử lý";
    }

    public String getDonator_bank() {
        return donator_bank;
    }

    public void setDonator_bank(String donator_bank) {
        this.donator_bank = donator_bank;
    }
    
    
    
}
