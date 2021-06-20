/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vbsp.ims.model;

import java.util.List;
import vbsp.ims.jasper.JasperParam;

/** 
 * @author Trung
 */
public class ReportInfor {
    /* Phương thức */
    private String reportCode;
    private String reportGroupCode;
    private String shortcutName;
    private String descript;
    private int reportUnit;
    private String reportTerm;
    private String reportType;
    private String reportGrade;
    private String jaserFileName;
    private String generatedName;
    private List<JasperParam> param;
    
    //--------------------------------------------------------------------------
    public ReportInfor(){ 
        reportUnit = 1;
        reportTerm = "/1D/";
        reportType = "01";
        reportGrade = "/1/2/3/";
        jaserFileName = "UNKNOWN";
        generatedName = "UNKNOWN";
    }
    /* Hàm thực hiện chức năng cập nhật thông tin report */
    public void clone(ReportInfor reportInfor){
        this.reportCode = reportInfor.getReportCode();
        this.descript = reportInfor.getDescript();
        this.reportGroupCode = reportInfor.getReportGroupCode();
        this.reportGrade = reportInfor.getReportGrade();
        this.reportTerm = reportInfor.getReportTerm();
        this.reportType = reportInfor.getReportType();
        this.reportUnit = reportInfor.getReportUnit();
        this.shortcutName = reportInfor.getShortcutName();
        this.jaserFileName = reportInfor.getJaserFileName();
        this.generatedName = reportInfor.getGeneratedName();
        this.setParam(reportInfor.getParam());
    }
    
    //--------------------------------------------------------------------------
    public String getReportCode() {
        return reportCode;
    }

    public void setReportCode(String reportCode) {
        this.reportCode = reportCode;
    }

    public String getReportGroupCode() {
        return reportGroupCode;
    }

    public void setReportGroupCode(String reportGroupCode) {
        this.reportGroupCode = reportGroupCode;
    }

    public String getShortcutName() {
        return shortcutName;
    }

    public void setShortcutName(String shortcutName) {
        this.shortcutName = shortcutName;
    }

    public String getDescript() {
        return descript;
    }

    public void setDescript(String descript) {
        this.descript = descript;
    }

    public int getReportUnit() {
        return reportUnit;
    }

    public void setReportUnit(int reportUnit) {
        this.reportUnit = reportUnit;
    }

    public String getReportTerm() {
        return reportTerm;
    }

    public void setReportTerm(String reportTerm) {
        this.reportTerm = reportTerm;
    }

    public String getReportType() {
        return reportType;
    }

    public void setReportType(String reportType) {
        this.reportType = reportType;
    }

    public String getReportGrade() {
        return reportGrade;
    }

    public void setReportGrade(String reportGrade) {
        this.reportGrade = reportGrade;
    }

    public String getJaserFileName() {
        return jaserFileName;
    }

    public void setJaserFileName(String jaserFileName) {
        this.jaserFileName = jaserFileName;
    }

    public String getGeneratedName() {
        return generatedName;
    }

    public void setGeneratedName(String generatedName) {
        this.generatedName = generatedName;
    }

    public List<JasperParam> getParam() {
        return param;
    }

    public void setParam(List<JasperParam> param) {
        this.param = param;
    }        
    //--------------------------------------------------------------------------
}
