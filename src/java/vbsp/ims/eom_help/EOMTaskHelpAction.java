/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.eom_help;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;
import vbsp.ims.define.DefineFun;
import vbsp.ims.report.fast.ListValue;

/**
 *
 * @author Trung
 */
public class EOMTaskHelpAction
        extends ActionSupport
        implements ServletRequestAware{

    private List<ListValue> modules;
    private List<ListValue> periods;
    private List<EOMTask> eomTasks;
    private List<EOMSubTask> eomSubTasks;
    private HttpServletRequest request;
    
    private String reportDate;
    private String reportPeriod;
    private String module_id;
    private String para_task;
    private String para_report_date;
    private String para_period;
    private String para_subtask;
    private String reload_data_flag;
    private String username;
    
//    private EOMSubTask detail_subtask ;
    
    @Override
    public void setServletRequest(HttpServletRequest request) {
        this.request = request;
    }
    
//     @Override
//    public Object getModel(){        
//        return detail_subtask;
//    }

    
    Map parameters = null;
   
    public String list_all_task() {
        System.err.println("list_all_task()" + module_id + reportDate + "~" + reportPeriod
        + "~" + reload_data_flag);
        String lv_reload_flg ;
        if (reload_data_flag != null && 
                reload_data_flag.toLowerCase().equals("on"))
            lv_reload_flg = "Y";
        else
            lv_reload_flg = "N";
        EOMTaskHelpDao eomTaskDao = new EOMTaskHelpDao();
        if (module_id == null || module_id.isEmpty() || module_id.equals("ALL")) {
            if (reportDate == null || reportDate.isEmpty())
               eomTasks = eomTaskDao.list_all_task("ALL","",reportPeriod,lv_reload_flg);
            else
                eomTasks = eomTaskDao.list_all_task("ALL",
                    DefineFun.convert2OracleDateFormat(reportDate),reportPeriod,lv_reload_flg);
        } else {
            eomTasks = eomTaskDao.list_all_task(module_id,
                    DefineFun.convert2OracleDateFormat(reportDate),reportPeriod,lv_reload_flg);
        }       
        return SUCCESS;
    }

    public String build_module_combo() {
        EOMTaskHelpDao eomTaskDao = new EOMTaskHelpDao();
        modules = eomTaskDao.list_all_module();
        if (module_id == null || module_id.isEmpty()){
            periods = eomTaskDao.list_all_period("ALL");    
        }else {
            periods = eomTaskDao.list_all_period(module_id);
        }
        return SUCCESS;
    }

//    public String build_period_combo() {
//        EOMTaskHelpDao eomTaskDao = new EOMTaskHelpDao();
//        periods = eomTaskDao.list_all_period();
//        return SUCCESS;
//    }

    /// Phần dành cho view sub task
    public String list_all_sub_task() {
        

        EOMTaskHelpDao eomTaskDao = new EOMTaskHelpDao();
        
        para_task = getParameterValue("task");
        para_report_date = getParameterValue("report_dt");
        para_period = getParameterValue("period");       
        reload_data_flag = getParameterValue("reload_data_flg");        
        username = getParameterValue("username");;
        
        System.err.println(
                "list_all_sub_task~" + para_task + "~" + para_report_date + "~" + para_period
        + "~" + reload_data_flag);
        
        eomSubTasks = eomTaskDao.list_all_subtask(para_task, 
                DefineFun.convert2OracleDateFormat(para_report_date), 
                para_period,
                reload_data_flag);
                     
        return SUCCESS;
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

    public List<EOMTask> getEomTasks() {
        return eomTasks;
    }

    public void setEomTasks(List<EOMTask> eomTasks) {
        this.eomTasks = eomTasks;
    }

    public List<ListValue> getModules() {
        return modules;
    }

    public void setModules(List<ListValue> modules) {
        this.modules = modules;
    }

    public String getModule_id() {
        return module_id;
    }

    public void setModule_id(String module_id) {
        this.module_id = module_id;
    }

    public List<ListValue> getPeriods() {
        return periods;
    }

    public void setPeriods(List<ListValue> periods) {
        this.periods = periods;
    }

    public List<EOMSubTask> getEomSubTasks() {
        return eomSubTasks;
    }

    public void setEomSubTasks(List<EOMSubTask> eomSubTasks) {
        this.eomSubTasks = eomSubTasks;
    }

    public String getPara_task() {
        return para_task;
    }

    public void setPara_task(String para_task) {
        this.para_task = para_task;
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

    public String getReportDate() {
        return reportDate;
    }

    public void setReportDate(String reportDate) {
        this.reportDate = reportDate;
    }

    public String getReportPeriod() {
        return reportPeriod;
    }

    public void setReportPeriod(String reportPeriod) {
        this.reportPeriod = reportPeriod;
    }

    public String getReload_data_flag() {
        return reload_data_flag;
    }

    public void setReload_data_flag(String reload_data_flag) {
        this.reload_data_flag = reload_data_flag;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    public String get_status_descript(String status) {
        String l_descript;
        switch (status) {
            case "N":
                l_descript = "Chưa thực hiện";
                break;
            case "D":
                l_descript = "Hoàn thành";
                break;
            case "E":
                l_descript = "Lỗi xử lý";
                break;
            case "W":
                l_descript = "Đang chờ";
                break;
            case "P":
                l_descript = "Đang xử lý";
                break;
            default:
                l_descript = "Không xác định";
                break;
        }
        return l_descript;
    }

}
