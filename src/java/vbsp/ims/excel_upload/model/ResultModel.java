/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.excel_upload.model;

/**
 *
 * @author HP
 */
public class ResultModel {
    public boolean status;
    public String message;

    public ResultModel() {
    }

    
    
    public ResultModel(boolean status, String message) {
        this.status = status;
        this.message = message;
    }
    
}
