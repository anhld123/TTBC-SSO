/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vbsp.ims.action;

import com.opensymphony.xwork2.ActionSupport;
/**
 *
 * @author Trung
 */
public class MenuAction extends ActionSupport {
    
    private String menuUrl;
    private String menuId;        
    private String userName;
    
    
    @Override
    public String execute() throws Exception {                
        return SUCCESS;
    }

    public String getMenu() {        
        return SUCCESS;
    }
   
    
    public String getMenuUrl() {
        return menuUrl;
    }

    public void setMenuUrl(String url) {
        this.menuUrl = url;
    }        

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }             
    
}
