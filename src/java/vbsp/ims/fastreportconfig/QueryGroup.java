/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vbsp.ims.fastreportconfig;

/**
 *
 * @author Trung
 */
public class QueryGroup {
    private String groupQueryId;
    private String module;
    private String applyRegion;
    
    private String groupId;
    private String descript;
    private String shortcut;
    private String privileage;
    
    public QueryGroup(){}
    
    public QueryGroup(String groupQueryId,String module,String applyRegion, 
            String groupId,String descript,String shortcut,String privileage){
        this.groupQueryId = groupQueryId;
        this.module = module;
        this.applyRegion = applyRegion;
        this.groupId = groupId;
        this.descript = descript;
        this.shortcut = shortcut;
        this.privileage = privileage;
    }

    public String getGroupQueryId() {
        return groupQueryId;
    }

    public void setGroupQueryId(String groupQueryId) {
        this.groupQueryId = groupQueryId;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getApplyRegion() {
        return applyRegion;
    }

    public void setApplyRegion(String applyRegion) {
        this.applyRegion = applyRegion;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getDescript() {
        return descript;
    }

    public void setDescript(String descript) {
        this.descript = descript;
    }

    public String getShortcut() {
        return shortcut;
    }

    public void setShortcut(String shortcut) {
        this.shortcut = shortcut;
    }

    public String getPrivileage() {
        return privileage;
    }

    public void setPrivileage(String privileage) {
        this.privileage = privileage;
    }
    
    
}
