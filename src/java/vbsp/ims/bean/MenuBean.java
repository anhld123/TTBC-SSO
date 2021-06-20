/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.bean;

import vbsp.ims.model.MenuItem;
import java.util.List;
import vbsp.ims.model.MenuManager;
/**
 *
 * @author Trung Nguyen
 */
public class MenuBean {
    private List<MenuItem> menuItems ;

    public List<MenuItem> getMenuItems() {
        return MenuManager.getMenus();
    }

    public void setMenuItems(List<MenuItem> menuItems) {
        this.menuItems = menuItems;
    }
    
}
