/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vbsp.ims.action;

import com.opensymphony.xwork2.ActionSupport;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;
import vbsp.ims.dao.ManualReportInputDao;
import vbsp.ims.model.ManualInputObject;

/**
 *
 * @author Trung
 */
public class ManualAddInforAction 
extends ActionSupport
        implements ServletRequestAware
{
    private List<ManualInputObject> manualInputObjects;        
    
    private HttpServletRequest request;
    
    @Override
    public void setServletRequest(HttpServletRequest request) {
        this.request = request;
    }
    
    public ManualAddInforAction(){}
    
    @Override
    public String execute(){
        
        manualInputObjects = new ArrayList<>();   
        ManualReportInputDao mnReportInputDao = new ManualReportInputDao();       
        int report_grade = Integer.parseInt(request.getSession().getAttribute("reportGrade").toString());
        String userName = request.getSession().getAttribute("username").toString();
        
            this.manualInputObjects = mnReportInputDao.getAddInforManualList(userName,report_grade);                    
        return SUCCESS;
    }

  
    
    public List<ManualInputObject> getManualInputObjects() {
        return manualInputObjects;
    }

    public void setManualInputObjects(List<ManualInputObject> manualInputObjects) {
        this.manualInputObjects = manualInputObjects;
    }

    
        
    public String getPermit(String permit, int permit_type){
        if (permit.substring(permit_type-1, permit_type).equals("1"))
            return "";
        else
            return "onclick='return false;' class='disabled'";
    }
}
