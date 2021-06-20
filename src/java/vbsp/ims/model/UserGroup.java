/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vbsp.ims.model;

import java.io.Serializable;

/**
 *
 * @author Trung
 */
public class UserGroup implements Serializable{
    
    private String priGroupCode;
    private String priGroupDesc;
    private String priGroupAlias;
    private String priPrivilege;
    private String priGroupStatus;
    private String priChildGroup;
    private String priviewType;

    public UserGroup(){}
    public UserGroup(String paraGroupCode,String paraGroupDesc,String paraGroupAlias,
            String paraPrivilege,String paraGroupStatus,String paraViewType){
        this.priGroupCode = paraGroupCode;
        this.priGroupDesc = paraGroupDesc;        
        this.priGroupAlias = paraGroupAlias;
        this.priPrivilege = paraPrivilege;
        this.priGroupStatus = paraGroupStatus;
        this.priviewType = paraViewType;
    }
    
    public UserGroup(String paraGroupCode,String paraGroupDesc,String paraGroupAlias,
            String paraPrivilege,String paraGroupStatus,String paraChildGroup,String paraViewType){
        this.priGroupCode = paraGroupCode;
        this.priGroupDesc = paraGroupDesc;        
        this.priGroupAlias = paraGroupAlias;
        this.priPrivilege = paraPrivilege;
        this.priGroupStatus = paraGroupStatus;
        this.priChildGroup = paraChildGroup;
        this.priviewType = paraViewType;
    }
    
    public String getPriGroupCode() {
        return priGroupCode;
    }

    public void setPriGroupCode(String priGroupCode) {
        this.priGroupCode = priGroupCode;
    }

    public String getPriGroupDesc() {
        return priGroupDesc;
    }

    public void setPriGroupDesc(String priGroupDesc) {
        this.priGroupDesc = priGroupDesc;
    }

    public String getPriGroupAlias() {
        return priGroupAlias;
    }

    public void setPriGroupAlias(String priGroupAlias) {
        this.priGroupAlias = priGroupAlias;
    }

    public String getPriPrivilege() {
        return priPrivilege;
    }

    public void setPriPrivilege(String priPrivilege) {
        this.priPrivilege = priPrivilege;
    }

    public String getPriGroupStatus() {
        return priGroupStatus;
    }

    public void setPriGroupStatus(String priGroupStatus) {
        this.priGroupStatus = priGroupStatus;
    }

    public String getPriChildGroup() {
        return priChildGroup;
    }

    public void setPriChildGroup(String priChildGroup) {
        this.priChildGroup = priChildGroup;
    }

    public String getPriviewType() {
        return priviewType;
    }

    public void setPriviewType(String priviewType) {
        this.priviewType = priviewType;
    }                
}
