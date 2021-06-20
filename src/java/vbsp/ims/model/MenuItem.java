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
public class MenuItem implements Serializable{
    private int menuId;
    private String text;
    private String description;
    private int parentId;
    private String navigateUrl;
    private int childTotal;
    private int isDisplay = 1;
    
    public MenuItem(){}
    public MenuItem(int menuId,String text,String description, 
            int parentId, String navigateUrl, int childTotal){
        this.menuId = menuId;
        this.text = text;
        this.description = description;
        this.parentId = parentId;
        this.navigateUrl = navigateUrl;
        this.childTotal = childTotal;
    }

    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public String getNavigateUrl() {
        return navigateUrl;
    }

    public void setNavigateUrl(String navigateUrl) {
        this.navigateUrl = navigateUrl;
    }

    public int getChildTotal() {
        return childTotal;
    }

    public void setChildTotal(int childTotal) {
        this.childTotal = childTotal;
    }        

    public int getIsDisplay() {
        return isDisplay;
    }

    public void setIsDisplay(int isDisplay) {
        this.isDisplay = isDisplay;
    }

    
}
