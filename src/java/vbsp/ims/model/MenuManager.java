/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.model;

import vbsp.ims.dao.IMSRptDao;
import java.util.List;
/**
 *
 * @author Trung Nguyen
 */
public class MenuManager {
    private static List<MenuItem> menus;
    private static String userName;
    
    public static void initMenu() 
    {
        menus = IMSRptDao.getMenu(userName);
    }

    public static List<MenuItem> getMenus() {
        return menus;
    }

    public static void setMenus(List<MenuItem> menus) {
        MenuManager.menus = menus;
    }
    
    
    
}
