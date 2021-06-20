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
import vbsp.ims.report.fast.ListValue;

/**
 *
 * @author Trung
 */
public class ManualDataInputAction 
extends ActionSupport
        implements ServletRequestAware
{
    private List<ManualInputObject> manualInputObjects;
    private List<ListValue> lstRptGroupObj;
    private List<ListValue> lstReportObj;    
    private String groupId;
    private String reportId;
    
    private HttpServletRequest request;
    
    @Override
    public void setServletRequest(HttpServletRequest request) {
        this.request = request;
    }
    
    public ManualDataInputAction(){}
    
    @Override
    public String execute(){
        System.err.println("Execute ... " + groupId);
        manualInputObjects = new ArrayList<>();   
        ManualReportInputDao mnReportInputDao = new ManualReportInputDao();       
        int report_grade = Integer.parseInt(request.getSession().getAttribute("reportGrade").toString());
        String userName = request.getSession().getAttribute("username").toString();
        if (groupId == null || groupId.isEmpty() || groupId.equals("ALL")){
            this.manualInputObjects = mnReportInputDao.getManualReportList(userName,report_grade);
        }
        else {
            this.manualInputObjects = mnReportInputDao.filterManualReportList(groupId,userName,report_grade);                    
        }            
        return SUCCESS;
    }

    public String buildCombo(){
        System.err.println("buildCombo ... " + groupId);
        ManualReportInputDao mnReportInputDao = new ManualReportInputDao();
        int report_grade = Integer.parseInt(request.getSession().getAttribute("reportGrade").toString());
        String userName = request.getSession().getAttribute("username").toString();
        if (groupId == null || groupId.isEmpty()|| groupId.equals("ALL")){
            this.lstRptGroupObj = mnReportInputDao.getManualGroupList_Combo(userName,report_grade);
            this.lstReportObj = mnReportInputDao.getManualReportList_Combo(userName,report_grade);
        }else {
            this.lstRptGroupObj = mnReportInputDao.getManualGroupList_Combo(userName,report_grade);
            this.lstReportObj = mnReportInputDao.filterManualReportList_Combo(groupId,userName,report_grade);
        }
        if (this.lstReportObj.size() > 0)
            this.reportId = this.lstReportObj.get(0).sKey;
        else {
            this.lstReportObj = new ArrayList<>();
        }
        return SUCCESS;
    }
    
    public String buildCombo_print(){
        System.err.println("buildCombo_prin()... " + groupId);
        ManualReportInputDao mnReportInputDao = new ManualReportInputDao();
        int report_grade = Integer.parseInt(request.getSession().getAttribute("reportGrade").toString());
        String userName = request.getSession().getAttribute("username").toString();
        if (groupId == null || groupId.isEmpty()|| groupId.equals("ALL")){
            this.lstRptGroupObj = mnReportInputDao.getManualGroupList_Combo(userName,report_grade);
            this.lstReportObj = mnReportInputDao.getManualReportList_Print_Combo(userName,report_grade);
        }else {
            this.lstRptGroupObj = mnReportInputDao.getManualGroupList_Combo(userName,report_grade);
            this.lstReportObj = mnReportInputDao.filterManualReportList_Combo_Print(groupId,userName,report_grade);
        }
        if (this.lstReportObj.size() > 0)
            this.reportId = this.lstReportObj.get(0).sKey;
        else {
            this.lstReportObj = new ArrayList<>();
        }
        return SUCCESS;
    }
    
    
    public List<ManualInputObject> getManualInputObjects() {
        return manualInputObjects;
    }

    public void setManualInputObjects(List<ManualInputObject> manualInputObjects) {
        this.manualInputObjects = manualInputObjects;
    }

    public List<ListValue> getLstRptGroupObj() {
        return lstRptGroupObj;
    }

    public void setLstRptGroupObj(List<ListValue> lstRptGroupObj) {
        this.lstRptGroupObj = lstRptGroupObj;
    }

    public List<ListValue> getLstReportObj() {
        return lstReportObj;
    }

    public void setLstReportObj(List<ListValue> lstReportObj) {
        this.lstReportObj = lstReportObj;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getReportId() {
        return reportId;
    }

    public void setReportId(String reportId) {
        this.reportId = reportId;
    }
        
    public String getPermit(String permit, int permit_type){
        if (permit.substring(permit_type-1, permit_type).equals("1"))
            return "";
        else
            return "onclick='return false;' class='disabled'";
    }
    
    
}
