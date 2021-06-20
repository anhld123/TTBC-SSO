/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vbsp.ims.loveleaf;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.util.Map;

/**
 *
 * @author Trung
 */
public class DonatorEditAction extends ActionSupport{
    
    private Donator donator;
    Map parameters = null;
    private String message;
    
    public String load_donator(){
//        String maker_id = getParameterValue("username");
//        String maker_dt = getParameterValue("report_dt");
        String donator_id = getParameterValue("donator_id"); 
        
        LoveLeafDao loveleafDao = new LoveLeafDao();
        donator = loveleafDao.getDonator(donator_id);        
        return SUCCESS;
    }
    
    public String update_donator(){
        System.err.println("Update~"+donator.getId()+"~" +donator.getLl_name());
        
        LoveLeafDao loveleafDao = new LoveLeafDao();
        boolean status = loveleafDao.updateDonator(donator);
        
        if (status)
            message = "<xanh> (*)Cập nhật [" + donator.getId() + "] thành công. <xanh>";    
        else
            message = "<do> (*)Cập nhật [" + donator.getId() + "] thất bại. <do>";
        
        return SUCCESS;
    }
    
     public Donator getDonator() {
        return donator;
    }

    public void setDonator(Donator donator) {
        this.donator = donator;
    }
    
    public String getParameterValue(String param) {
        Object paramObj = getParameters().get(param);
        if (paramObj == null) {
            return null;
        }
        return ((String[]) paramObj)[0];
    }
    
     public final Map getParameters() {
        parameters = ActionContext.getContext().getParameters();
        return parameters;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
     
     
    
}
