/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vbsp.ims.model;

import java.util.ArrayList;
import java.util.List;
import vbsp.ims.dao.ReportUpdateDao;
import vbsp.ims.jasper.JasperParam;
import vbsp.ims.report.fast.ListValue;

/**
 *
 * @author Trung
 */
public class ReportUpdateManager {
    
    private  ReportInfor reportInfor;
    private  List<ListValue> reportGroupList;
    private  List<ReportListValue> reportList;
    /* Tình trạng cập nhật: 01 - thêm mới, 02 - cập nhật */
    private  static String updateType;    
    private  final ReportUpdateDao reportUpdateDao;
    
    public ReportUpdateManager() {
        reportInfor = new ReportInfor();
        reportUpdateDao = new ReportUpdateDao();
        reportGroupList = reportUpdateDao.getReportGroupList();
        reportList = reportUpdateDao.getReportList("");
    }

    //--------------------------------------------------------------------------
    public ReportInfor getReportInfor() {
        return reportInfor;
    }
    
    public void updateReportInfor(String reportCode) {
        ReportInfor lcReportInfor = reportUpdateDao.getReportInfor(reportCode);
        reportInfor.clone(lcReportInfor);        
    }
    
    public void setReportInfor(ReportInfor reportInfor) {
        this.reportInfor = reportInfor;
    }    
    
    public boolean create() {
        if (updateType.equals("01")) {
            return reportUpdateDao.addReport(reportInfor);
        }else {
            return reportUpdateDao.updateReport(reportInfor);
        }
    }
    
    public boolean disableReport(String reportCode){
        return reportUpdateDao.updateReportStatus(reportCode, "N");
    }
    
    public boolean enableReport(String reportCode){
        return reportUpdateDao.updateReportStatus(reportCode, "Y");
    }
    
    public List<ListValue> getReportGroupList() {
        return reportGroupList;
    }
    
    public void setReportGroupList(List<ListValue> reportGroupList) {
        this.reportGroupList = reportGroupList;
    }
    
    public List<ReportListValue> getReportList() {
        return reportList;
    }
    
    public void setReportList(List<ReportListValue> reportList) {
        this.reportList = reportList;
    }    
    
    public String getUpdateType() {
        return updateType;
    }
    
    public void setUpdateType(String updateType) {
        this.updateType = updateType;
    }

    //--------------------------------------------------------------------------

    public List<ListValue> getReportList(String reportGroup) {
        ArrayList<ListValue> lcReportList = new ArrayList();
        if (reportGroup.isEmpty()) {
            for (ReportListValue listValue : reportList) {
                lcReportList.add(listValue.getReportListValue());
            }
        } else {
            for (ReportListValue listValue : reportList) {
                if (listValue.getReportGroup().trim().equals(reportGroup.trim())
                        || listValue.getReportGroup().trim().equals("ADDNEW")) {
                    lcReportList.add(listValue.getReportListValue());
                }
            }
        }
        return lcReportList;
    }
    
    public void refresh(){
        reportGroupList 
                = reportUpdateDao.getReportGroupList();
        reportList = reportUpdateDao.getReportList("");
    }
    
    public void suggest(List<JasperParam> param){
        reportUpdateDao.suggest(param);
    }
    //--------------------------------------------------------------------------
}
