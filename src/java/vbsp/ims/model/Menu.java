/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vbsp.ims.model;

import java.io.Serializable;
import java.util.ArrayList;
import vbsp.ims.dao.IMSRptDao;

/**
 *
 * @author Trung
 */
public class Menu implements Serializable{
    private ArrayList<MenuItem> menu;
    private String userId;
    public Menu(){}
    public Menu(String userId){
        this.userId = userId;
        this.menu = IMSRptDao.getMenu(userId);
    }

    public ArrayList<MenuItem> getMenu() {
        return menu;
    }    
}
