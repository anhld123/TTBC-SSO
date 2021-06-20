/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vbsp.ims.model;

/**
 *
 * @author Trung
 */
public class AccountInforView {
    private String account;
    private String name;
    private String sbv_gl;
    private String gl_sl;
    private String ccy_cd;
    private String d_c_flg;

    public AccountInforView(){}
    
    public AccountInforView(String account, String name,String sbv_gl,
            String gl_sl,String ccy_cd,String d_c_flg){
        this.account = account;
        this.name = name;
        this.sbv_gl = sbv_gl;
        this.gl_sl = gl_sl;
        this.ccy_cd = ccy_cd;
        this.d_c_flg = d_c_flg;
    }
    
    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSbv_gl() {
        return sbv_gl;
    }

    public void setSbv_gl(String sbv_gl) {
        this.sbv_gl = sbv_gl;
    }

    public String getGl_sl() {
        return gl_sl;
    }

    public void setGl_sl(String gl_sl) {
        this.gl_sl = gl_sl;
    }

    public String getCcy_cd() {
        return ccy_cd;
    }

    public void setCcy_cd(String ccy_cd) {
        this.ccy_cd = ccy_cd;
    }

    public String getD_c_flg() {
        return d_c_flg;
    }

    public void setD_c_flg(String d_c_flg) {
        this.d_c_flg = d_c_flg;
    }        
    
}
