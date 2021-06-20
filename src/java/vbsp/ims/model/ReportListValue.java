/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vbsp.ims.model;

import vbsp.ims.report.fast.ListValue;

/** 
 * @author Trung
 * lớp sử dụng để tạo danh sách report
 * dùng cho modul quản trị báo cáo
 */
public class ReportListValue {
    //--------------------------------------------------------------------------
    private String reportGroup;
    private ListValue reportListValue;
    private String reportGrade;    
    
    //--------------------------------------------------------------------------
    public ReportListValue(){}
    public ReportListValue(String reportGroup,ListValue listValue){
        this.reportGroup = reportGroup;
        this.reportListValue = listValue;
    }
    public ReportListValue(String reportGroup,ListValue listValue,String reportGrade){
        this.reportGroup = reportGroup;
        this.reportListValue = listValue;
        this.reportGrade = reportGrade;
    }
    //--------------------------------------------------------------------------
    public String getReportGroup() {
        return reportGroup;
    }
    public void setReportGroup(String reportGroup) {
        this.reportGroup = reportGroup;
    }
    public String getReportGrade() {
        return reportGrade;
    }
    public void setReportGrade(String reportGrade) {
        this.reportGrade = reportGrade;
    }    
    public ListValue getReportListValue() {
        return reportListValue;
    }
    public void setReportListValue(ListValue reportListValue) {
        this.reportListValue = reportListValue;
    }    
    //--------------------------------------------------------------------------
}
