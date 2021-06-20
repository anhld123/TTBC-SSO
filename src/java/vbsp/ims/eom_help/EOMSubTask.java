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
public class EOMSubTask {
    private int seq_no;
    private String task;
    private String subtask;
    private String short_descript;
    private String descript;
    private String server;
    private String execute_script;
    private String check_script;
    private String expected_time;
    private String status;
    private String comment;
    private String period;
    private int expected_row_total;
    private String script_type;
    private String remark;
    private String execute_permission;
    
    private Link attachFile;
    
    public EOMSubTask(){}
    
    public EOMSubTask(
            int seq_no,
            String task,
            String subtask,
            String short_descript,
            String descript,
            String server,
            String execute_script,
            String check_script,
            String expected_time,
            String status,
            String comment,
            String period,
            Link attachFile,
            int expected_row_total,
            String script_type,
            String remark,
            String execute_permission
    ){
        this.seq_no = seq_no;
        this.task = task;
        this.subtask = subtask;
        this.short_descript = short_descript;
        this.descript = descript;
        this.server = server;
        this.execute_script = execute_script;
        this.check_script = check_script;
        this.expected_time = expected_time;
        this.status = status;        
        this.comment = comment;
        this.period = period;
        this.attachFile = attachFile;
        this.expected_row_total = expected_row_total;
        this.script_type = script_type;
        this.remark = remark;
        this.execute_permission = execute_permission;
    }

    public int getSeq_no() {
        return seq_no;
    }

    public void setSeq_no(int seq_no) {
        this.seq_no = seq_no;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getSubtask() {
        return subtask;
    }

    public void setSubtask(String subtask) {
        this.subtask = subtask;
    }

    public String getShort_descript() {
        return short_descript;
    }

    public void setShort_descript(String short_descript) {
        this.short_descript = short_descript;
    }

    public String getDescript() {
        return descript;
    }

    public void setDescript(String descript) {
        this.descript = descript;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public String getExecute_script() {
        return execute_script;
    }

    public void setExecute_script(String execute_script) {
        this.execute_script = execute_script;
    }

    public String getCheck_script() {
        return check_script;
    }

    public void setCheck_script(String check_script) {
        this.check_script = check_script;
    }

    public String getExpected_time() {
        return expected_time;
    }

    public void setExpected_time(String expected_time) {
        this.expected_time = expected_time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public Link getAttachFile() {
        return attachFile;
    }

    public void setAttachFile(Link attachFile) {
        this.attachFile = attachFile;
    }

    public int getExpected_row_total() {
        return expected_row_total;
    }

    public void setExpected_row_total(int expected_row_total) {
        this.expected_row_total = expected_row_total;
    }

    public String getScript_type() {
//        String l_descript;
//        switch (script_type){
//            case "JOB": 
//                l_descript = "";//"Thực hiện chạy JOB(s).";
//                break;
//            case "SCRIPT": 
//                l_descript = "Thực hiện bằng TOAD sau đó cập nhật lại trạng thái.";
//                break;    
//            case "SCRIPT_2": 
//                l_descript = "";//Thực hiện chạy câu lệnh trực tiếp(s).";
//                break;        
//            default:
//                l_descript = "Không xác định.";
//                break;        
//        }        
//        return l_descript;       
        return script_type;
    }

    public void setScript_type(String script_type) {
        this.script_type = script_type;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getExecute_permission() {
        return execute_permission;
    }

    public void setExecute_permission(String execute_permission) {
        this.execute_permission = execute_permission;
    }

    
    
    
}
