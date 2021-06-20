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
public class MoneyTransaction {

    private String pos_cd;
    private String name;
    private int amount;
    private int poor_total;
    private String val_dt;
    private String status;
    private String mkr_dt;
    private String mkr_id;

    public MoneyTransaction() {
    }

    public MoneyTransaction(String pos_cd,
            String name,
            int amount,
            int poor_total,
            String val_dt,
            String status,
            String mkr_dt,
            String mkr_id) {
        this.pos_cd = pos_cd;
        this.name = name;
        this.amount = amount;
        this.poor_total = poor_total;
        this.val_dt = val_dt;
        this.status = status;
        this.mkr_dt = mkr_dt;
        this.mkr_id = mkr_id;
    }

    public String getPos_cd() {
        return pos_cd;
    }

    public void setPos_cd(String pos_cd) {
        this.pos_cd = pos_cd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getPoor_total() {
        return poor_total;
    }

    public void setPoor_total(int poor_total) {
        this.poor_total = poor_total;
    }

    public String getVal_dt() {
        return val_dt;
    }

    public void setVal_dt(String val_dt) {
        this.val_dt = val_dt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMkr_dt() {
        return mkr_dt;
    }

    public void setMkr_dt(String mkr_dt) {
        this.mkr_dt = mkr_dt;
    }

    public String getMkr_id() {
        return mkr_id;
    }

    public void setMkr_id(String mkr_id) {
        this.mkr_id = mkr_id;
    }

    
    public String getStatus_desc() {
        if (status.equals("D"))
            return "Đã chuyển";
        else if (status.equals("N"))
            return "Chưa chuyển";
        else
            return "Đang xử lý";
    }
}
