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
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import vbsp.ims.dao.IMSRptDao;
import vbsp.ims.model.User;
import vbsp.ims.model.UserManager;
import vbsp.ims.report.fast.ListValue;

/**
 *
 * @author Trung
 */
public class UserAction extends ActionSupport
    implements ModelDriven, Preparable, ServletRequestAware{
    private User user = new User();
    private String userCode ="";        
    private List<User> users;
    private String updateType="";
    private String updatePassword="";
    HttpServletRequest request;
    private List<String> grades = new ArrayList<>();       
    private String error_msg="";
    private String success_msg="";
    private String suggess_val="";
    private List<ListValue> statusList = new ArrayList<>();
    UserManager userManager = new UserManager();
    //private int ktMaCanBo = 0;
    
    @Override
    public void prepare() throws Exception{
        if (!userCode.isEmpty()){     
            User findUser = userManager.find(userCode);
            user = findUser.clone();            
        }
    }
    @Override
    public Object getModel(){
        //userGroup = new UserGroup();
        return user;
    }
    @Override
    public void setServletRequest(HttpServletRequest request) {
        this.request = request;
    }
    public HttpServletRequest getServletRequest() {
        return this.request;
    }
    public String list(){        
        String lcUserName = request.getSession().getAttribute("username").toString();      
        String lcmanagerPosCode = IMSRptDao.getPosOfUser(lcUserName);
        // Khoi tao status List
        statusList = new ArrayList<>();
        ListValue activeStatus = new ListValue("A", "Hoạt động");
        statusList.add(activeStatus);
        ListValue closeStatus = new ListValue("C", "Đóng");
        statusList.add(closeStatus);
        this.users = userManager.getUsers(lcUserName,lcmanagerPosCode);
        userCode = lcUserName;
        return "success";
    }
    public String create(){
        userManager.create(user);
        return "success";
    }
    public String edit(){       
        statusList = new ArrayList<>();
        ListValue activeStatus = new ListValue("A", "Hoạt động");
        statusList.add(activeStatus);
        ListValue closeStatus = new ListValue("C", "Đóng");
        statusList.add(closeStatus);
        return "success";
    }
    public String userClone(){
        if (user != null)
            user.setPriUserCode(user.getPriUserCode() + "_1");
        return "success";
    }
    public String profile(){
        return "success";
    }
    public String update(){        
        if (updateType.isEmpty()){
            System.err.println("Update Type is null");        
        }
        else {
            userManager.update(user,updateType,updatePassword);
        }
        return "success";
    }
    public String delete(){               
        userManager.delete(userCode);                    
        return "success";
    }

    public List<ListValue> getStatusList() {
        return statusList;
    }

    public void setStatusList(List<ListValue> statusList) {
        this.statusList = statusList;
    }
    
    public String checkUser(){
        String lcUserName = request.getSession().getAttribute("username").toString();      
        String js_checkmsg[] = IMSRptDao.checkUser(user.getPriUserCode(),lcUserName);
        if (js_checkmsg[0].equals("E")){
            user.setPriUserCode(js_checkmsg[2]);
            error_msg = js_checkmsg[1];
            suggess_val = js_checkmsg[2];
        }
        else{
            success_msg = js_checkmsg[1];            
            suggess_val = js_checkmsg[2];
        }
        return "success";        
    }    
    
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }       

    public String getUpdateType() {
        return updateType;
    }

    public void setUpdateType(String updateType) {
        this.updateType = updateType;
    }

    public List<String> getGrades() {
        grades.add("1");
        grades.add("2");
        grades.add("3");
        return grades;
    }

    public void setGrades(List<String> grades) {
        this.grades = grades;
    }    

    public String getUpdatePassword() {
        return updatePassword;
    }

    public void setUpdatePassword(String updatePassword) {
        this.updatePassword = updatePassword;
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
}
