/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vbsp.ims.eom_help;

import com.opensymphony.xwork2.ActionSupport;
import vbsp.ims.define.DefineFun;

/**
 *
 * @author TrungNT88
 */
public class EOMEditSubtaskAction
 extends ActionSupport{        
 
    private String para_report_date;
    private String para_period;
    private String para_subtask;
    private String new_status_ID;
    private String para_comment;
    private String para_username;
    private String para_expected_time;
    private int para_expected_row_total;
    
     private String message ;
     
    public String update_subtask(){
//        int reportGrade = Integer.parseInt(request.getSession().getAttribute("reportGrade").toString());
        
        
        System.err.println("userName" + para_username);
        
        EOMTaskHelpDao eomTaskDao = new EOMTaskHelpDao();
//        EOMSubTask subtask = eomTaskDao.get_subtask_detail(para_subtask, 
//                DefineFun.convert2OracleDateFormat(para_report_date), para_period);
        String lv_message = eomTaskDao.update_subtask(
                para_username, 
                para_subtask, 
                DefineFun.convert2OracleDateFormat(para_report_date), 
                para_period, 
                new_status_ID, 
                para_comment,
                para_expected_time,
                para_expected_row_total);
        
            message = "(*) Ghi nhận thông tin [" 
                +  para_subtask + "~" + para_report_date + "~" + para_period + "]"
                    + lv_message;        
        return SUCCESS;                
    }

    public String getNew_status_ID() {
        return new_status_ID;
    }

    public void setNew_status_ID(String new_status_ID) {
        this.new_status_ID = new_status_ID;
    }

    public String getPara_comment() {
        return para_comment;
    }

    public void setPara_comment(String para_comment) {
        this.para_comment = para_comment;
    }


    
    
    public String getPara_report_date() {
        return para_report_date;
    }

    public void setPara_report_date(String para_report_date) {
        this.para_report_date = para_report_date;
    }

    public String getPara_period() {
        return para_period;
    }

    public void setPara_period(String para_period) {
        this.para_period = para_period;
    }

    public String getPara_subtask() {
        return para_subtask;
    }

    public void setPara_subtask(String para_subtask) {
        this.para_subtask = para_subtask;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPara_username() {
        return para_username;
    }

    public void setPara_username(String para_username) {
        this.para_username = para_username;
    }

    public String getPara_expected_time() {
        return para_expected_time;
    }

    public void setPara_expected_time(String para_expected_time) {
        this.para_expected_time = para_expected_time;
    }

    public int getPara_expected_row_total() {
        return para_expected_row_total;
    }

    public void setPara_expected_row_total(int para_expected_row_total) {
        this.para_expected_row_total = para_expected_row_total;
    }

   
    
    
    
}
