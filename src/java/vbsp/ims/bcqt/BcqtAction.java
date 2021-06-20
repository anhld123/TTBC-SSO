/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.bcqt;

import com.opensymphony.xwork2.ActionSupport;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;
import vbsp.ims.bcqt.model.MSQT01Model;
//import vbsp.ims.report.fast.ListValue;

/**
 *
 * @author Trung
 */
public class BcqtAction extends ActionSupport
        implements ServletRequestAware {

    //--------------------------------------------------------------------------
    private static final long serialVersionUID = 5078264277068533593L;
    private HttpServletRequest request;
    
    private List<MSQT01Model> arl_qt01_data = new ArrayList<>();
    
    
    //--------------------------------------------------------------------------
    
    @Override
    public void setServletRequest(HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public String execute() {
        return SUCCESS;
    }
   
    public String Get_Date_QT01()
    {
        
        
        
        //arl_qt01_data
        return SUCCESS;
    }
}
