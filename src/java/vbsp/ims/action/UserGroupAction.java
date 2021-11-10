/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.action;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;
import vbsp.ims.dao.IMSRptDao;
import vbsp.ims.model.MenuItem;
import vbsp.ims.model.ReportUserGroup;
import vbsp.ims.model.UserGroup;
import vbsp.ims.model.UserGroupManager;
import vbsp.ims.report.fast.ListValue;

/**
 *
 * @author Trung
 */
public class UserGroupAction extends ActionSupport
        implements ModelDriven, Preparable, ServletRequestAware {

    private UserGroupManager userGroupManager = new UserGroupManager();
    private UserGroup userGroup = new UserGroup();
    private String userGroupCode = "";
    private List<UserGroup> userGroups;
    private List<String> selectedGroups;
    private List<MenuItem> menuItems = new ArrayList<>();
    private String privileageStr;
    private String error_msg = "";
    private String success_msg = "";
    private String suggess_val = "";
    private String administrator_pri = "";
    private String[] reports;
    HttpServletRequest request;
    private List<ListValue> statusList = new ArrayList<>();
    private List<ListValue> yesnoList = new ArrayList<>();
    private List<ReportUserGroup> ownerReports = new ArrayList<>();

    @Override
    public void prepare() throws Exception {
        if (!userGroupCode.isEmpty()) {
            userGroup = userGroupManager.find(userGroupCode);
        }
    }

    @Override
    public Object getModel() {
        //userGroup = new UserGroup();
        return userGroup;
    }

    @Override
    public void setServletRequest(HttpServletRequest request) {
        this.request = request;
    }

    public List<UserGroup> getUserGroups() {
        //userGroups = UserGroupManager.getUserGroups();
        return userGroups;
    }

    public UserGroup getUserGroup() {
        return userGroup;
    }

    public void setUserGroup(UserGroup userGroup) {
        this.userGroup = userGroup;
    }

    public void setUserGroups(List<UserGroup> userGroups) {
        this.userGroups = userGroups;
    }

    public String getUserGroupCode() {
        return userGroupCode;
    }

    public void setUserGroupCode(String userGroupCode) {
        this.userGroupCode = userGroupCode;
    }

    public List<String> getSelectedGroups() {
        return selectedGroups;
    }

    public List<MenuItem> getMenuItems() {
        return menuItems;
    }

    public String getPrivileageStr() {
        return privileageStr;
    }

    public void setPrivileageStr(String privileageStr) {
        this.privileageStr = privileageStr;
    }

    public void setMenuItems(List<MenuItem> menuItems) {
        this.menuItems = menuItems;
    }

    public void setSelectedGroups(List<String> selectedGroups) {
        this.selectedGroups = selectedGroups;
    }

    public String list() {
        String userName = request.getSession().getAttribute("username").toString();
        System.err.println("UserGroupAction~" + userName);
        userGroup.setPriPrivilege(IMSRptDao.getMenuString(userName));
        System.err.println("UserGroupAction~" + IMSRptDao.getMenuString(userName));
        userGroups = userGroupManager.getUserGroups(userName);
        // Khoi tao status List
        statusList = new ArrayList<>();
        ListValue activeStatus = new ListValue("A", "Hoạt động");
        statusList.add(activeStatus);
        ListValue closeStatus = new ListValue("C", "Đóng");
        statusList.add(closeStatus);
        
        yesnoList = new ArrayList<>();
        ListValue yesStatus = new ListValue("Y", "Y - Có");
        yesnoList.add(yesStatus);
        ListValue noStatus = new ListValue("N", "N - Không");
        yesnoList.add(noStatus);
        return "success";
    }

    public String create() {
        String maker = request.getSession().getAttribute("username").toString();
        userGroupManager.create(userGroup, maker);
        return "success";
    }

    public String edit() {
        // Khoi tao status List
        statusList = new ArrayList<>();
        ListValue activeStatus = new ListValue("A", "Hoạt động");
        statusList.add(activeStatus);
        ListValue closeStatus = new ListValue("C", "Đóng");
        statusList.add(closeStatus);
        
        yesnoList = new ArrayList<>();
        ListValue yesStatus = new ListValue("Y", "Y - Có");
        yesnoList.add(yesStatus);
        ListValue noStatus = new ListValue("N", "N - Không");
        yesnoList.add(noStatus);
        return "success";
    }

    public String update() {
        String maker = request.getSession().getAttribute("username").toString();
        userGroupManager.update(userGroup, maker);
        return "success";
    }

    public String delete() {
        userGroupManager.delete(userGroupCode);
        return "success";
    }

    public String checkNewGroup() {
        String js_checkmsg[] = IMSRptDao.checkUserGroup(userGroup.getPriGroupCode());
        if (js_checkmsg[0].equals("E")) {
            userGroup.setPriGroupCode(js_checkmsg[2]);
            error_msg = js_checkmsg[1];
            suggess_val = js_checkmsg[2];
        } else {
            success_msg = js_checkmsg[1];
            suggess_val = js_checkmsg[2];
        }
        return "success";
    }

    public String selectPrivileage() {
        
        List<MenuItem> jl_mnItem = userGroupManager.getListOfMenuItem();
        
        String userName = request.getSession().getAttribute("username").toString();
        administrator_pri = IMSRptDao.getMenuString(userName);
        
        if (privileageStr == null || privileageStr.trim().isEmpty()) {
            privileageStr = IMSRptDao.getMenuString(userName);
        }
        
        for (MenuItem jo_mnItem : jl_mnItem) {
            int ji_menuid = jo_mnItem.getMenuId();
            String c = privileageStr.substring(ji_menuid-1, ji_menuid);
            jo_mnItem.setIsDisplay(Integer.parseInt(c));
        }
        
        for (MenuItem jl_mnItem1 : jl_mnItem) {
            if (jl_mnItem1.getParentId() == -1) {
                menuItems.add(jl_mnItem1);
                for (MenuItem jl_mnItem2 : jl_mnItem) {
                    if (jl_mnItem2.getParentId() == jl_mnItem1.getMenuId()) {
                        String js_mndesc = "      " + jl_mnItem2.getText();
                        jl_mnItem2.setText(js_mndesc);
                        menuItems.add(jl_mnItem2);
                        for (MenuItem jl_mnItem3 : jl_mnItem) {
                            if (jl_mnItem3.getParentId() == jl_mnItem2.getMenuId()) {
                                js_mndesc = "            " + jl_mnItem3.getText();
                                jl_mnItem3.setText(js_mndesc);
                                menuItems.add(jl_mnItem3);
                            }
                        }
                    }
                }
            }
        }
        return "success";
    }
    
    
    public String saveReports() {
        
        IMSRptDao dao = new IMSRptDao();
        ArrayList<String> reportList = null;
        if (reports != null && reports.length > 0){
            reportList = new ArrayList<>(Arrays.asList(reports));        
        }
        error_msg = dao.updateGroupOwnerListReport(userGroupCode, reportList);
        
        return SUCCESS;
    }
    
    public String selectOwnerReports() {
        IMSRptDao dao = new IMSRptDao();
        ownerReports = dao.getListOfReportByUserGroup(userGroupCode);
        return SUCCESS;
    }

    public String getStatus(int i) {
        if (i == 0) {
            return "";
        } else {
            return "checked";
        }
    }

    public String getError_msg() {
        return error_msg;
    }

    public void setError_msg(String error_msg) {
        this.error_msg = error_msg;
    }

    public String getSuccess_msg() {
        return success_msg;
    }

    public void setSuccess_msg(String success_msg) {
        this.success_msg = success_msg;
    }

    public String getSuggess_val() {
        return suggess_val;
    }

    public void setSuggess_val(String suggess_val) {
        this.suggess_val = suggess_val;
    }

    public String getAdministrator_pri() {
        return administrator_pri;
    }

    public void setAdministrator_pri(String administrator_pri) {
        this.administrator_pri = administrator_pri;
    }   

    public List<ListValue> getStatusList() {
        return statusList;
    }

    public void setStatusList(List<ListValue> statusList) {
        this.statusList = statusList;
    }

    public List<ListValue> getYesnoList() {
        return yesnoList;
    }

    public void setYesnoList(List<ListValue> yesnoList) {
        this.yesnoList = yesnoList;
    }

    public List<ReportUserGroup> getOwnerReports() {
        return ownerReports;
    }

    public void setOwnerReports(List<ReportUserGroup> ownerReports) {
        this.ownerReports = ownerReports;
    }            

    public String[] getReports() {
        return reports;
    }

    public void setReports(String[] reports) {
        this.reports = reports;
    }
    
    
}
