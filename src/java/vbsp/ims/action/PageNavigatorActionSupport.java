/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vbsp.ims.action;

import com.opensymphony.xwork2.ActionSupport;

/**
 *
 * @author Home
 */
public class PageNavigatorActionSupport extends ActionSupport {
    
    public PageNavigatorActionSupport() {
    }
    
    public String execute() throws Exception {
        return "success";
    }
    
    public String redirectMainPage(){
        return "success";
    }
    
}
