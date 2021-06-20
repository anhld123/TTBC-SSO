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
public class SbvAccount extends Account{

    private String ccyCode;
    
    public SbvAccount(String pos_code,String account_code,
            String account_descript,String ccyCode,BigDecimal open_debit,
            BigDecimal open_credit,BigDecimal turn_debit,BigDecimal turn_credit,
            BigDecimal close_debit,BigDecimal close_credit){
        super(pos_code, account_code, account_descript, open_debit, 
                open_credit, turn_debit, turn_credit, close_debit, close_credit);
        this.ccyCode = ccyCode;
    }
    
    @Override
    public void plus(Account plusAccount) {
        this.setOpen_debit(this.getOpen_debit().add(plusAccount.getOpen_debit()));
        this.setOpen_credit(this.getOpen_credit().add(plusAccount.getOpen_credit()));
        this.setTurn_debit(this.getTurn_debit().add(plusAccount.getTurn_debit()));
        this.setTurn_credit(this.getTurn_credit().add(plusAccount.getTurn_credit()));
        this.setClose_debit(this.getClose_debit().add(plusAccount.getClose_debit()));
        this.setClose_credit(this.getClose_credit().add(plusAccount.getClose_credit()));
    }

    public String getCcyCode() {
        return ccyCode;
    }

    public void setCcyCode(String ccyCode) {
        this.ccyCode = ccyCode;
    }        
}
