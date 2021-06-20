/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vbsp.ims.action;

import com.opensymphony.xwork2.ActionSupport;
import java.util.ArrayList;
import java.util.List;
import vbsp.ims.dao.IMSRptDao;
import vbsp.ims.report.fast.ListValue;

/**
 *
 * @author Trung
 */
public class BuildMenuIdAction extends ActionSupport {
    
    private List<ListValue> menuIdList = new ArrayList<>();
    
    public List<ListValue> getMenuIdList() {
        return menuIdList;
    }

    public void setMenuIdList(List<ListValue> menuIdList) {
        this.menuIdList = menuIdList;
    }

    public String generateMenuIdCombo() {        
        menuIdList = IMSRptDao.getMenuIdList();
        return "success";
    }
    
}
