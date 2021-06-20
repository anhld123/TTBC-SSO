/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vbsp.ims.eom_help;

/**
 *
 * @author Trung
 */
public class EOMTaskLog {
    
    private String pos_cd ;
    private String subtask_type;
    private String subtask_descript;
    private int record_total;
    private String error_msg;
    private String status;
    
    public EOMTaskLog(){}
    
    public EOMTaskLog(String pos_cd,String subtask_type,String subtask_descript,
            int record_total,String error_msg,String status){        
        this.pos_cd = pos_cd;
        this.subtask_type = subtask_type;
        this.subtask_descript = subtask_descript;
        this.record_total = record_total;
        this.error_msg = error_msg;
        this.status = status;
    }

    public String getPos_cd() {
        return pos_cd;
    }

    public void setPos_cd(String pos_cd) {
        this.pos_cd = pos_cd;
    }

    public int getRecord_total() {
        return record_total;
    }

    public void setRecord_total(int record_total) {
        this.record_total = record_total;
    }

    public String getError_msg() {
        return error_msg;
    }

    public void setError_msg(String error_msg) {
        this.error_msg = error_msg;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSubtask_type() {
        return subtask_type;
    }

    public void setSubtask_type(String subtask_type) {
        this.subtask_type = subtask_type;
    }

    public String getSubtask_descript() {
        return subtask_descript;
    }

    public void setSubtask_descript(String subtask_descript) {
        this.subtask_descript = subtask_descript;
    }
    
    
}
