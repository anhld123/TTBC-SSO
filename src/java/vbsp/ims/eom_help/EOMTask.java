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
public class EOMTask extends Task {
    
    private int seq_no;
    private String task_no;
    private String descript;
    private String comment;
    private String status;    
    
    public EOMTask(){}
    
    public EOMTask(int seq_no,String task_no,String descript,String comment,String status){
        this.seq_no = seq_no;
        this.task_no = task_no;
        this.descript = descript;
        this.comment = comment;
        this.status = status;
    }

    public int getSeq_no() {
        return seq_no;
    }

    public void setSeq_no(int seq_no) {
        this.seq_no = seq_no;
    }

    public String getTask_no() {
        return task_no;
    }

    public void setTask_no(String task_no) {
        this.task_no = task_no;
    }

    public String getDescript() {
        return descript;
    }

    public void setDescript(String descript) {
        this.descript = descript;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    
}
