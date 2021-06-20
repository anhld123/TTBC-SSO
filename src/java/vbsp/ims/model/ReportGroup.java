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
public class ReportGroup implements Serializable{
    
    private String priGroupCode;
    private String priGroupDesc;
    private String priGroupAlias;
    private String priGroupType;
    private String priGroupStatus;
    private int priMenuId;

    public ReportGroup(){}
    public ReportGroup(String paraGroupCode,String paraGroupDesc,String paraGroupAlias,
            String paraGroupType,String paraGroupStatus, int paraMenuId){
        this.priGroupCode = paraGroupCode;
        this.priGroupDesc = paraGroupDesc;        
        this.priGroupAlias = paraGroupAlias;
        this.priGroupType = paraGroupType;
        this.priGroupStatus = paraGroupStatus;
        this.priMenuId = paraMenuId;
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
   
    public String getPriGroupStatus() {
        return priGroupStatus;
    }

    public void setPriGroupStatus(String priGroupStatus) {
        this.priGroupStatus = priGroupStatus;
    }

    public String getPriGroupType() {
        return priGroupType;
    }

    public void setPriGroupType(String priGroupType) {
        this.priGroupType = priGroupType;
    }            

    public int getPriMenuId() {
        return priMenuId;
    }

    public void setPriMenuId(int priMenuId) {
        this.priMenuId = priMenuId;
    }    
}
