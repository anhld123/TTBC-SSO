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
public class GLAccount extends Account{
    private String gl_Sl;
    private String gl_Ccy;
    private String gl_Sbv;
    
    public String getGl_Sl() {
        return gl_Sl;
    }
    public void setGl_Sl(String gl_Sl) {
        this.gl_Sl = gl_Sl;
    }

    public String getGl_Ccy() {
        return gl_Ccy;
    }

    public void setGl_Ccy(String gl_Ccy) {
        this.gl_Ccy = gl_Ccy;
    }

    public String getGl_Sbv() {
        return gl_Sbv;
    }

    public void setGl_Sbv(String gl_Sbv) {
        this.gl_Sbv = gl_Sbv;
    }        
    
    public GLAccount(String pos_code,String account_code,
            String account_descript,String gl_Sl,String gl_Ccy,String gl_Sbv) {
        super(pos_code,account_code,account_descript);
        this.gl_Sl = gl_Sl;
        this.gl_Ccy = gl_Ccy;
        this.gl_Sbv = gl_Sbv;
    }
    
    public GLAccount(String pos_code,String account_code,
            String account_descript,String gl_Sl,String gl_Ccy,String gl_Sbv,
            BigDecimal open_debit,
            BigDecimal open_credit,BigDecimal turn_debit,BigDecimal turn_credit,
            BigDecimal close_debit,BigDecimal close_credit) {
        super(pos_code,account_code,account_descript,open_debit,open_credit,turn_debit,
                turn_credit,close_debit,close_credit);
        this.gl_Sl = gl_Sl;
        this.gl_Ccy = gl_Ccy;
        this.gl_Sbv = gl_Sbv;
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
}
