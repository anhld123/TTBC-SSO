/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.eom_help;

import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import vbsp.ims.define.Define;
//import javax.servlet.http.HttpServletRequest;
//import org.apache.struts2.interceptor.ServletRequestAware;
import vbsp.ims.define.DefineFun;
import vbsp.ims.model.RefObject;

/**
 *
 * @author Trung
 */
public class EOMSubTaskHelpAction extends ActionSupport
        implements  ModelDriven<EOMSubTask> {

//    private HttpServletRequest request;

    private EOMSubTask detail_subtask = new EOMSubTask();

    private String para_report_date;
    private String para_period;
    private String para_subtask;
    
    
    private String para_execute_script;
    private String para_check_script;
    private String para_status;
    private String para_comment;
    private String para_expected_time;
    private int para_expected_row_total;
    
    private Link attachFile;
    
    private String attachFile_name;
    private String attachFile_path;
    
    
    private List<EOMTaskLog> tasklogs = new ArrayList();
    
    private String message;
       
    
    private String search_key;
    private String search_pos;
    private String search_status;
    private String search_type;     
    
    
    private String para_username;
    
     Map parameters = null;
     
    
    
//     @Override
//    public void setServletRequest(HttpServletRequest request) {
//        this.request = request;
//    }
   

    public String view_sub_task_detail() {
        
//        String  userName = request.getSession().getAttribute(
//                "username"
//                ).toString();
        
        EOMTaskHelpDao eomTaskDao = new EOMTaskHelpDao();
        
        para_subtask = getParameterValue("subtask");
        para_report_date = getParameterValue("report_dt");
        para_period = getParameterValue("period");

        System.err.println(
                "view_sub_task_detail~" + para_subtask + "~" + para_report_date + "~" + para_period);

        detail_subtask = eomTaskDao.get_subtask_detail(para_subtask, 
                DefineFun.convert2OracleDateFormat(para_report_date), para_period);

        para_execute_script = detail_subtask.getExecute_script();
        para_check_script = detail_subtask.getCheck_script();
        para_status = detail_subtask.getStatus();
        para_expected_row_total = detail_subtask.getExpected_row_total();
        
        return SUCCESS;
    }

    
    public String execute_sub_task(){
//        message = "(*) Chức năng này chưa hỗ trợ." + para_subtask + para_report_date + para_period;
        para_subtask = getParameterValue("subtask");
        para_report_date = getParameterValue("report_dt");
        para_period = getParameterValue("period");
        para_username = getParameterValue("username");
        
        RefObject outmsg = new RefObject();
        EOMTaskHelpDao eomTaskDao = new EOMTaskHelpDao();
        eomTaskDao.execute_script(para_subtask, 
                DefineFun.convert2OracleDateFormat(para_report_date), 
                para_period, 
                "1",
                para_username,
                outmsg,tasklogs);
        
//        EOMSubTaskManager.setTasklogs(tasklogs);
        
        message = outmsg.getString01();
        return SUCCESS;
    }
    
    public String view_sub_task_progress(){
//        message = "(*) Chức năng này chưa hỗ trợ." + para_subtask + para_report_date + para_period;
        para_subtask = getParameterValue("subtask");
        para_report_date = getParameterValue("report_dt");
        para_period = getParameterValue("period");
        para_username = getParameterValue("username");
        
        RefObject outmsg = new RefObject();
        EOMTaskHelpDao eomTaskDao = new EOMTaskHelpDao();
        eomTaskDao.execute_script(para_subtask, 
                DefineFun.convert2OracleDateFormat(para_report_date), 
                para_period, 
                "2",
                para_username,
                outmsg,tasklogs);
        
        EOMSubTaskManager.setTasklogs(tasklogs);
        message = outmsg.getString01();
        return SUCCESS;
    }
    
    public String search_subtask(){
        para_subtask = getParameterValue("subtask");
        para_report_date = getParameterValue("report_dt");
        para_period = getParameterValue("period");        
                      
//        request.getSession(false).setAttribute("username", username);                  
        if (search_key == null ||
                search_key.isEmpty())
            tasklogs = EOMSubTaskManager.getTasklogs();                                
        else
            tasklogs = EOMSubTaskManager.search(search_key, search_type);                                        
        message = "[Hiển thị log:" + para_subtask + "~" + para_report_date + "~" + para_period
                + "~" + search_key + "~" + search_type + "~" + search_pos + "~" +  search_status +  "]";
        return SUCCESS;
    }
    
    public String edit_subtask(){        
        
        para_subtask = getParameterValue("subtask");
        para_report_date = getParameterValue("report_dt");
        para_period = getParameterValue("period");
        para_username = getParameterValue("username");  
        
        EOMTaskHelpDao eomTaskDao = new EOMTaskHelpDao();
        EOMSubTask subtask = eomTaskDao.get_subtask_detail(para_subtask, 
                DefineFun.convert2OracleDateFormat(para_report_date), para_period);
        para_status = subtask.getStatus();
        para_comment = subtask.getComment();
        para_expected_time = subtask.getExpected_time();
        attachFile = subtask.getAttachFile();
        attachFile_name = attachFile.getFileName();
        attachFile_path = attachFile.getFullPath();
        para_expected_row_total = subtask.getExpected_row_total();
        
        return SUCCESS;
    }
    
//    public String update_subtask(){
//        message = "(*) Ghi nhận thông tin thành công [" +  para_subtask + "~" + para_report_date + "~" + para_period + "]" ;
//        return SUCCESS;                
//    }
    
//    public String view_server_infor(){
//        para_subtask = getParameterValue("subtask");
//        String lv_server = getParameterValue("server");
//        EOMTaskHelpDao eomTaskDao = new EOMTaskHelpDao();
//        ServerInfor serverInfor = eomTaskDao.getServerInfor(lv_server);
//        message = serverInfor.getServerName();
//    }
    
    public EOMSubTask getDetail_subtask() {
        return detail_subtask;
    }

    public void setDetail_subtask(EOMSubTask detail_subtask) {
        this.detail_subtask = detail_subtask;
    }

    @Override
    public EOMSubTask getModel() {
        return detail_subtask;
    }
    
    public final Map getParameters() {
        parameters = ActionContext.getContext().getParameters();
        return parameters;
    }

    public String getParameterValue(String param) {
        Object paramObj = getParameters().get(param);
        if (paramObj == null) {
            return null;
        }
        return ((String[]) paramObj)[0];
    }

    public String getPara_execute_script() {
        return para_execute_script;
    }

    public void setPara_execute_script(String para_execute_script) {
        this.para_execute_script = para_execute_script;
    }

    public String getPara_check_script() {
        return para_check_script;
    }

    public void setPara_check_script(String para_check_script) {
        this.para_check_script = para_check_script;
    }

    public String getPara_status() {
        return para_status;
    }

    public void setPara_status(String para_status) {
        this.para_status = para_status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPara_report_date() {
        return para_report_date;
    }

    public String getPara_comment() {
        return para_comment;
    }

    public void setPara_comment(String para_comment) {
        this.para_comment = para_comment;
    }

    public Link getAttachFile() {
        return attachFile;
    }

    public void setAttachFile(Link attachFile) {
        this.attachFile = attachFile;
    }

    public String getAttachFile_name() {
        return attachFile_name;
    }

    public void setAttachFile_name(String attachFile_name) {
        this.attachFile_name = attachFile_name;
    }

    public String getAttachFile_path() {
        return attachFile_path;
    }

    public void setAttachFile_path(String attachFile_path) {
        this.attachFile_path = attachFile_path;
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

    public List<EOMTaskLog> getTasklogs() {
        return tasklogs;
    }

    public void setTasklogs(List<EOMTaskLog> tasklogs) {
        this.tasklogs = tasklogs;
    }

    public String getSearch_key() {
        return search_key;
    }

    public void setSearch_key(String search_key) {
        this.search_key = search_key;
    }

    public String getSearch_pos() {
        return search_pos;
    }

    public void setSearch_pos(String search_pos) {
        this.search_pos = search_pos;
    }

    public String getSearch_status() {
        return search_status;
    }

    public void setSearch_status(String search_status) {
        this.search_status = search_status;
    }

    public String getSearch_type() {
        return search_type;
    }

    public void setSearch_type(String search_type) {
        this.search_type = search_type;
    }

    
    public String get_status_descript(String status) {
        return DefineFun.get_status_descript(status);
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
