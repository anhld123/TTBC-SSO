/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.restapi;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 *
 * @author HP
 */
@XmlAccessorType(XmlAccessType.FIELD)

public class CustCiResp {

    private boolean isSuccess;
    private float code;
    private String message;
    ArrayList<CustCicModel> result = new ArrayList<>();

    // Getter Methods 
    public boolean getIsSuccess() {
        return isSuccess;
    }

    public float getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    // Setter Methods 
    public void setIsSuccess(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public void setCode(float code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<CustCicModel> getResult() {
        return result;
    }

    public void setResult(ArrayList<CustCicModel> result) {
        this.result = result;
    }
    
    
}


