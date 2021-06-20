/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vbsp.ims.tlsl;

/**
 *
 * @author Trung
 */
public abstract class ReportExporter {
    
    protected String report_type;
    protected String eco_area;
    
    public abstract boolean export();
    public abstract String getPath();
    public abstract String getName();

    public String getReport_type() {
        return report_type;
    }

    public void setReport_type(String report_type) {
        this.report_type = report_type;
    }

    public String getEco_area() {
        return eco_area;
    }

    public void setEco_area(String eco_area) {
        this.eco_area = eco_area;
    }

    
        
    
}
