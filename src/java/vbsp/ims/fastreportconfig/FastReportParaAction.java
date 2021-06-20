/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vbsp.ims.fastreportconfig;

import com.opensymphony.xwork2.ActionSupport;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;

/**
 *
 * @author Trung
 */
public class FastReportParaAction extends ActionSupport
        implements ServletRequestAware {
    
    private List<ParaRptQuery> paraRptQueries = new ArrayList<>();
    private ParaRptQuery paraRptQuery ;//= new ParaRptQuery();
    private String action_type;    
    HttpServletRequest request;
    private String message;
    
    @Override
    public void setServletRequest(HttpServletRequest request) {
        this.request = request;
    }
    
    public String listParaRptQueries(){
        int lLogGrade = Integer.parseInt(request.getSession().getAttribute("reportGrade").toString());
        String lUserName = request.getSession().getAttribute("username").toString();
        FastReportConfigDao frptcDao = new FastReportConfigDao();
        System.err.println("listParaRptQueries" + lUserName + "~" + lLogGrade + "~");
        paraRptQueries = frptcDao.listVariables(lUserName, lLogGrade);
        return SUCCESS;
    }
    
    public String update_ParaKey(){
        String userName = request.getSession().getAttribute("username").toString();
        System.err.println("update~"+userName+action_type+"~"
                +paraRptQuery.getParaKey()+"~"
                +paraRptQuery.getParaDesc()+ "~" 
                +paraRptQuery.getParaType());
        if (userName == null || userName.trim().equals(""))
            userName = "ADMIN";
        
        FastReportConfigDao frpDao = new FastReportConfigDao();        
        String actionMessage = 
                frpDao.update_Para(paraRptQuery, userName, action_type);                
        if (actionMessage.contains("ERROR"))
            message = "Cập nhật thất bại. " + actionMessage;
        else
            message = "Cập nhật thành công.";
        return SUCCESS;
    }

    public List<ParaRptQuery> getParaRptQueries() {
        return paraRptQueries;
    }

    public void setParaRptQueries(List<ParaRptQuery> paraRptQueries) {
        this.paraRptQueries = paraRptQueries;
    }

    public ParaRptQuery getParaRptQuery() {
        return paraRptQuery;
    }

    public void setParaRptQuery(ParaRptQuery paraRptQuery) {
        this.paraRptQuery = paraRptQuery;
    }

    public String getAction_type() {
        return action_type;
    }

    public void setAction_type(String action_type) {
        this.action_type = action_type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    
}
