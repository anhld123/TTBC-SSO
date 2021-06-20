/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vbsp.ims.action;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import java.util.ArrayList;
import java.util.List;
import vbsp.ims.model.ReportGroup;
import vbsp.ims.model.ReportGroupManager;
import vbsp.ims.report.fast.ListValue;

/**
 *
 * @author Trung
 */
public class ReportGroupAction extends ActionSupport 
implements ModelDriven, Preparable{
    
    private ReportGroup reportGroup = new ReportGroup();
    private String reportGroupCode ="";
    private List<ReportGroup> reportGroups;
    private List<String> selectedGroups;    
    private List<ListValue> yesnoList = new ArrayList<>();
    
    @Override
    public void prepare() throws Exception{
        if (!reportGroupCode.isEmpty()){            
            reportGroup = ReportGroupManager.find(reportGroupCode);
        }
    }
    @Override
    public Object getModel(){
        //userGroup = new UserGroup();
        return reportGroup;
    }
    
    public List<ReportGroup> getReportGroups(){
        reportGroups = ReportGroupManager.getReportGroups();
        return reportGroups;
    }
    
    public ReportGroup getReportGroup(){
        return reportGroup;
    }

    public void setReportGroup(ReportGroup reportGroup) {
        this.reportGroup = reportGroup;
    }

    public void setReportGroups(List<ReportGroup> reportGroups) {
        this.reportGroups = reportGroups;
    }
    
    public String getReportGroupCode() {
        return reportGroupCode;
    }

    public void setReportGroupCode(String reportGroupCode) {
        this.reportGroupCode = reportGroupCode;
    }    
    
    public List<String> getSelectedGroups() {
        return selectedGroups;
    }

    public void setSelectedGroups(List<String> selectedGroups) {
        this.selectedGroups = selectedGroups;
    } 

    public List<ListValue> getYesnoList() {
        return yesnoList;
    }

    public void setYesnoList(List<ListValue> yesnoList) {
        this.yesnoList = yesnoList;
    }
    
    
        
    //--------------------------------------------------------------------------   
    public String list(){        
        this.reportGroups = ReportGroupManager.getReportGroups();
        yesnoList = new ArrayList<>();
        ListValue yesStatus = new ListValue("Y", "Y - Có");
        yesnoList.add(yesStatus);
        ListValue noStatus = new ListValue("N", "N - Không");
        yesnoList.add(noStatus);
        return "success";
    }
    public String create(){
        ReportGroupManager.create(reportGroup);        
        return "success";
    }
    public String edit(){       
        yesnoList = new ArrayList<>();
        ListValue yesStatus = new ListValue("Y", "Y - Có");
        yesnoList.add(yesStatus);
        ListValue noStatus = new ListValue("N", "N - Không");
        yesnoList.add(noStatus);
        return "success";
    }
    public String update(){        
        ReportGroupManager.update(reportGroup);
        return "success";
    }
    public String delete(){               
        ReportGroupManager.delete(reportGroupCode);                    
        return "success";
    }    
    //--------------------------------------------------------------------------
}
