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
public class ExportInfor {
    private String jasper_name;
    private String program ;
    
    public ExportInfor(){}
    
    public ExportInfor(String jasper_name,String program){
        this.jasper_name = jasper_name;
        this.program = program;
    }

    public String getJasper_name() {
        return jasper_name;
    }

    public void setJasper_name(String jasper_name) {
        this.jasper_name = jasper_name;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }
    
    
}
