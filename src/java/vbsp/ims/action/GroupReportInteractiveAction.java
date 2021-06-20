/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;
import vbsp.ims.model.ReportMenuManager;

/**
 *
 * @author Trung
 */
public class GroupReportInteractiveAction
        extends ActionSupport implements ServletRequestAware {
    
    HttpServletRequest request;       
    
    @Override
    public String execute() throws Exception {
        Map session = ActionContext.getContext().getSession();
        String username = (String) session.get("username");
        int reportGrade = 
                Integer.parseInt(request.getSession().getAttribute("reportGrade").toString());
        if (username == null || username.isEmpty()) {
            System.err.println("Error: chua dang nhap");
            return "error";
        } else {
            String menuId = getServletRequest().getParameter("menuId");
            if (menuId == null) {
                System.err.println("Error found menu id");
                return "error";
            } else {
                ReportMenuManager.setMenuId(Integer.parseInt(menuId));
                ReportMenuManager.setReportGrade(reportGrade);
                ReportMenuManager.setUserName(username);
            }            
            return "success";
        }
    }
    
    //--------------------------------------------------------------------------
    @Override
    public void setServletRequest(HttpServletRequest request) {
        this.request = request;
    }

    public HttpServletRequest getServletRequest() {
        return this.request;
    }      
    
}
