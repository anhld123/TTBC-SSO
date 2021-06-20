/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vbsp.ims.menucnmanager;

/**
 *
 * @author Trung
 */
public class MenuGroup {
    private int menuid;
    private String group_id;
    private String descript;
    private String shortcut;
    private String privileage;
    
    public MenuGroup(){}
    
    public MenuGroup(int menuid, String group_id,String descript,String shortcut,String privileage){
        this.menuid = menuid;
        this.group_id = group_id;
        this.descript = descript;
        this.shortcut = shortcut;
        this.privileage = privileage;
    }

    public int getMenuid() {
        return menuid;
    }

    public void setMenuid(int menuid) {
        this.menuid = menuid;
    }

    public String getGroup_id() {
        return group_id;
    }

    public void setGroup_id(String group_id) {
        this.group_id = group_id;
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
