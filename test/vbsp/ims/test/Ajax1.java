/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vbsp.ims.test;

import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionSupport;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Administrator
 */
public class Ajax1 extends ActionSupport {
      private String allcolumn;
    private String selectcolumn;
    private List<String> lstallcolumn;
    private List<String> lstselectcolumn ;
   // private static final long serialVersionUID = -7895258309088641394L;

    
    //@Action(value = "/ajax1", results = { @Result(location = "ajax1.jsp", name = "success") })
    public String execute(){
        //if(selectcolumn.length()<=0)
            System.err.println("Ban chua chon du lieu "+getSelectcolumn());
	return SUCCESS;
    }

    public String getAllcolumn() {
        return allcolumn;
    }

    public void setAllcolumn(String allcolumn) {
        this.allcolumn = allcolumn;
    }

    public String getSelectcolumn() {
        return selectcolumn;
    }

    public void setSelectcolumn(String selectcolumn) {
        this.selectcolumn = selectcolumn;
    }

    public List<String> getLstallcolumn() {
        return lstallcolumn;
    }

    public void setLstallcolumn(List<String> lstallcolumn) {
        this.lstallcolumn = lstallcolumn;
    }

    public List<String> getLstselectcolumn() {
        return lstselectcolumn;
    }

    public void setLstselectcolumn(List<String> lstselectcolumn) {
        this.lstselectcolumn = lstselectcolumn;
    }
    
    
}
