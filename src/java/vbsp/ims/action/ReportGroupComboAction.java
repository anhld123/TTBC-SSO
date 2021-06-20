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
import vbsp.ims.model.ReportMenuManager;
import vbsp.ims.report.fast.ListValue;

/**
 *
 * @author Trung
 */
public class ReportGroupComboAction extends ActionSupport 
        implements ServletRequestAware {
    
    //--------------------------------------------------------------------------
    private List<ListValue> lstRptGroupObj;
    private List<ListValue> lstReportObj;    
    
    //--------------------------------------------------------------------------
    private String groupId;
    private String reportId;
    private HttpServletRequest request;
    
    //--------------------------------------------------------------------------
    public String builCombo() {
        int menuId = ReportMenuManager.getMenuId();
        ReportMenuManager.build(menuId,groupId);
        
        this.lstRptGroupObj = ReportMenuManager.getReportGroupList();
        this.lstReportObj = ReportMenuManager.getReportList();
        
        if (this.lstReportObj.size() > 0)
            this.reportId = this.lstReportObj.get(0).sKey;
        else {
            this.lstReportObj = new ArrayList<>();
//            this.lstReportObj.add(new ListValue(NONE, "--- Chọn báo cáo ---"));
        }
        return "success";
    }
    
    //--------------------------------------------------------------------------
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
    
    @Override
    public void setServletRequest(HttpServletRequest request) {
        this.request = request;
    }
    //--------------------------------------------------------------------------       
}
