/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vbsp.ims.mssql.dataimport;

/**
 *
 * @author Trung
 */
public class DepartmentPosition {    
    private String  positionCode;
    private String  positionName;
    private String  departmentCode;
    private String  departmentName;
    private String  roleCode;
    private String  cashFlag;
    private String  atmFlag;
    
    public DepartmentPosition(){}
    
    public DepartmentPosition(        
        String  positionCode,
        String  positionName,
        String  departmentCode,
        String  departmentName,
        String  roleCode,
        String  cashFlag,
        String  atmFlag){
        this.positionCode = positionCode;
        this.positionName = positionName;
        this.departmentCode = departmentCode;
        this.departmentName = departmentName;
        this.roleCode = roleCode;
        this.cashFlag = cashFlag;
        this.atmFlag = atmFlag;
    }

    public String getPositionCode() {
        return positionCode;
    }

    public void setPositionCode(String positionCode) {
        this.positionCode = positionCode;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public String getDepartmentCode() {
        return departmentCode;
    }

    public void setDepartmentCode(String departmentCode) {
        this.departmentCode = departmentCode;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public String getCashFlag() {
        return cashFlag;
    }

    public void setCashFlag(String cashFlag) {
        this.cashFlag = cashFlag;
    }

    public String getAtmFlag() {
        return atmFlag;
    }

    public void setAtmFlag(String atmFlag) {
        this.atmFlag = atmFlag;
    }
    
    
}
