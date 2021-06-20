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
public class ErrorObject {
    private int error_cd;
    private String error_msg;
    public ErrorObject(){}
    public ErrorObject(int error_cd,String error_msg){
        this.error_cd = error_cd;
        this.error_msg = error_msg;
    }
    public int getError_cd() {
        return error_cd;
    }
    public void setError_cd(int error_cd) {
        this.error_cd = error_cd;
    }
    public String getError_msg() {
        return error_msg;
    }
    public void setError_msg(String error_msg) {
        this.error_msg = error_msg;
    }    
}
