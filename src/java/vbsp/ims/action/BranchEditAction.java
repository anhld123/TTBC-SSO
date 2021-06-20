/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.action;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;
import vbsp.ims.dao.BranchDao;
import vbsp.ims.dao.IMSRptDao;
import vbsp.ims.define.Define;
import vbsp.ims.log.CoreLogger;
import vbsp.ims.model.Branch;
import vbsp.ims.model.RefObject;
import vbsp.ims.syn.ProcessReportSyn;

/**
 *
 * @author Trung
 */
public class BranchEditAction extends ActionSupport
        implements ModelDriven, ServletRequestAware {

    private static final long serialVersionUID = 5078264277068533593L;
    //--------------------------------------------------------------------------   
    private HttpServletRequest request;
    //--------------------------------------------------------------------------

    private String message;
    private Branch branch = new Branch();
    private String disabled = "";

    static String session_id;

    @Override
    public Object getModel() {
        return branch;
    }

    @Override
    public String execute() {
        int reportGrade = Integer.parseInt(request.getSession().getAttribute("reportGrade").toString());
        String userName = request.getSession().getAttribute("username").toString();
        if (session_id == null || session_id.isEmpty()) {
            session_id = (String) request.getSession().getId();
        }
        String newsession_id = (String) request.getSession().getId();

        String action_type = request.getParameter("proc");
//        String permit = request.getParameter("permit");
//        
//        disable = isDisable(permit,2);
                
        BranchDao branchDao = new BranchDao();
        
//        if (session_id.equals(newsession_id)) {
            if (action_type.equals("view")) {  
                String permit = request.getParameter("permit");
                disabled = isDisable(permit,2);
                Branch branch_lc = branchDao.getBranchInfor(userName, reportGrade);
                branch.clone(branch_lc);
            }else {
                if (action_type.equals("update")){                    
                    boolean update_status_1st = branchDao.updateBranchInfor(userName,branch);
                    String pos_cd = IMSRptDao.getPosOfUser(userName);
                    vbsp.ims.model.RefObject refObj = new RefObject();
                    String sys_date = new SimpleDateFormat("dd-MMM-yyyy hh:mm:ss").format(new Date());
                    branch.setMaker_id(userName);
                    branch.setMaker_dt(sys_date);
                    boolean update_status_2st = sync_data(pos_cd,branch,refObj);
                    if (update_status_1st && update_status_2st)
                        message = "(*)Cập nhật dữ liệu thành công.";
                    else
                        message = "(*)Cập nhật dữ liệu thất bại.";
                }else {
                    System.err.println("NOTHING TO DO...");
                }
            }
//        }
        return SUCCESS;
    }

     //PHẦN ĐỒNG BỘ SỐ LIỆU VỀ HSC
    private boolean sync_data(String sync_pos_cd,Branch new_branch, vbsp.ims.model.RefObject refObj) {
        //DONG BO DU LIEU LEN TW
        try {
            int reportGrade = Integer.parseInt(request.getSession().getAttribute("reportGrade").toString());
            String reportDate = new SimpleDateFormat("ddMMyyyy").format(new Date());
            
            if (reportGrade != 3) {
                
                //Lay duong dan va ten xml se ghi ra
                String pathSave = !request.getRealPath("/").endsWith("/")
                        ? request.getRealPath("/") + "/" + Define.M_REPORT_XML
                        : request.getRealPath("/") + Define.M_REPORT_XML;
                pathSave += sync_pos_cd + "_dmpos_" + reportDate + ".xml";                                  
              
                //Tao file xml theo cau truc
                ProcessReportSyn clientWritexml = new ProcessReportSyn();
                boolean bSuccess = clientWritexml.SendFileXmlToWebServices_Dmpos(sync_pos_cd,new_branch, pathSave);
                if (!bSuccess) {
                    //message = "Dữ liệu đồng bộ về HSC thất bại...";
                    return false;
                }
            }
        } catch (NumberFormatException e) {
            CoreLogger.error(this.getClass().getName() + " Dữ liệu đồng bộ về HSC thất bại " + e.getMessage());
            return false;
        }
        return true;
    }
    
    @Override
    public void setServletRequest(HttpServletRequest request) {
        this.request = request;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public String getPermit(String permit, int permit_type){
        if (permit.substring(permit_type-1, permit_type).equals("1"))
            return "";
        else
            return "onclick='return false;' class='disabled'";
    }
    
     public String isDisable(String permit, int permit_type){
        if (permit.substring(permit_type-1, permit_type).equals("1"))
            return "";
        else
            return "style='display: none;'";
    }

    public String getDisabled() {
        return disabled;
    }

    public void setDisabled(String disabled) {
        this.disabled = disabled;
    }

  
    
}
