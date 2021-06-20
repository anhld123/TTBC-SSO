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
public class ReportPrivilege {
    //--------------------------------------------------------------------------
    private String reportGroup;
    private String userGroup;
    private String privilege; //Chuỗi phân quyền có định dạng [XXXX]
    private String exception; //Nếu có trong ngoại lệ thì sẽ không chịu ảnh hưởng của phân quyền
    //--------------------------------------------------------------------------
    public ReportPrivilege(){}
    public ReportPrivilege(String reportGroup,String userGroup,
           String privilege,String exception){
        this.reportGroup = reportGroup;
        this.userGroup = userGroup;
        this.exception = exception;
        this.privilege = privilege;
    }
    //--------------------------------------------------------------------------
    public String getReportGroup() {
        return reportGroup;
    }
    public void setReportGroup(String reportGroup) {
        this.reportGroup = reportGroup;
    }
    public String getUserGroup() {
        return userGroup;
    }
    public void setUserGroup(String userGroup) {
        this.userGroup = userGroup;
    }
    public String getException() {
        return exception;
    }
    public void setException(String exception) {
        this.exception = exception;
    }   
    public String getPrivilege() {
        return privilege;
    }
    public void setPrivilege(String privilege) {
        this.privilege = privilege;
    }
    //--------------------------------------------------------------------------
}
