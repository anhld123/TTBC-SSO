/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vbsp.ims.action;

import com.opensymphony.xwork2.ActionSupport;
import vbsp.ims.dao.IMSRptDao;

/**
 *
 * @author Trung
 */
public class VbspNewsAction extends ActionSupport {
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    public String fetchMessage(){
        this.message = IMSRptDao.randomMessage();
        return "success";
    }
}
