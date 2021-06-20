/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vbsp.ims.model;

import java.math.BigDecimal;

/**
 *
 * @author Trung
 */
public abstract class Account {
    /* Khai báo thuộc tính*/
    private String pos_code;
    private String account_code;
    private String account_descript;
    private BigDecimal open_debit;
    private BigDecimal open_credit;
    private BigDecimal turn_debit;
    private BigDecimal turn_credit;
    private BigDecimal close_debit;
    private BigDecimal close_credit;   
    
    public Account(){}
    
    public Account(String pos_code,String account_code,
            String account_descript) {
        this.pos_code = pos_code;
        this.account_code = account_code;
        this.account_descript = account_descript;
        this.open_debit = new BigDecimal("0");
        this.open_credit = new BigDecimal("0");
        this.turn_credit = new BigDecimal("0");
        this.turn_debit = new BigDecimal("0");
        this.close_credit = new BigDecimal("0");
        this.close_debit = new BigDecimal("0");
    }
    
    public Account(String pos_code,String account_code,
            String account_descript,BigDecimal open_debit,
            BigDecimal open_credit,BigDecimal turn_debit,BigDecimal turn_credit,
            BigDecimal close_debit,BigDecimal close_credit) {
        this.pos_code = pos_code;
        this.account_code = account_code;
        this.account_descript = account_descript;
        this.open_debit = open_debit;
        this.open_credit = open_credit;
        this.turn_credit = turn_credit;
        this.turn_debit = turn_debit;
        this.close_credit = close_credit;
        this.close_debit = close_debit;
    }

    /* Khai báo hàm*/
    public String getPos_code() {
        return pos_code;
    }

    public void setPos_code(String pos_code) {
        this.pos_code = pos_code;
    }

    public String getAccount_code() {
        return account_code;
    }

    public void setAccount_code(String account_code) {
        this.account_code = account_code;
    }

    public String getAccount_descript() {
        return account_descript;
    }

    public void setAccount_descript(String account_descript) {
        this.account_descript = account_descript;
    }

    public BigDecimal getOpen_debit() {
        return open_debit;
    }

    public void setOpen_debit(BigDecimal open_debit) {
        this.open_debit = open_debit;
    }

    public BigDecimal getOpen_credit() {
        return open_credit;
    }

    public void setOpen_credit(BigDecimal open_credit) {
        this.open_credit = open_credit;
    }

    public BigDecimal getTurn_debit() {
        return turn_debit;
    }

    public void setTurn_debit(BigDecimal turn_debit) {
        this.turn_debit = turn_debit;
    }

    public BigDecimal getTurn_credit() {
        return turn_credit;
    }

    public void setTurn_credit(BigDecimal turn_credit) {
        this.turn_credit = turn_credit;
    }

    public BigDecimal getClose_debit() {
        return close_debit;
    }

    public void setClose_debit(BigDecimal close_debit) {
        this.close_debit = close_debit;
    }

    public BigDecimal getClose_credit() {
        return close_credit;
    }

    public void setClose_credit(BigDecimal close_credit) {
        this.close_credit = close_credit;
    }        
    
    // Phuong thuc cong Abstract 
    public abstract void plus(Account plusAccount);
}
