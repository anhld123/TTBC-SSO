/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vbsp.ims.model;

/**
 *
 * @author Trung
 */
public class CICLogRecord {
    private String pos_cd ;
    private String descript;
    private String report_dt;
    private Number error_cd;
    private String error_msg;
    private Number record_total;
    private String rec_st;
    
    public CICLogRecord(){}
    
    public CICLogRecord(String l_Pos_cd,String l_Descript,String l_report_dt,Number l_error_cd,
            String l_error_msg,Number l_record_total,String l_rec_st){
        this.pos_cd = l_Pos_cd;
        this.descript = l_Descript;
        this.report_dt = l_report_dt;
        this.error_cd = l_error_cd;
        this.error_msg = l_error_msg;
        this.record_total = l_record_total;
        this.rec_st = l_rec_st;
    }

    public String getPos_cd() {
        return pos_cd;
    }

    public void setPos_cd(String pos_cd) {
        this.pos_cd = pos_cd;
    }

    public String getDescript() {
        return descript;
    }

    public void setDescript(String descript) {
        this.descript = descript;
    }

    public String getReport_dt() {
        return report_dt;
    }

    public void setReport_dt(String report_dt) {
        this.report_dt = report_dt;
    }

    public Number getError_cd() {
        return error_cd;
    }

    public void setError_cd(Number error_cd) {
        this.error_cd = error_cd;
    }

    public String getError_msg() {
        return error_msg;
    }

    public void setError_msg(String error_msg) {
        this.error_msg = error_msg;
    }

    public Number getRecord_total() {
        return record_total;
    }

    public void setRecord_total(Number record_total) {
        this.record_total = record_total;
    }

    public String getRec_st() {
        return rec_st;
    }

    public void setRec_st(String rec_st) {
        this.rec_st = rec_st;
    }

    
        
    
}
