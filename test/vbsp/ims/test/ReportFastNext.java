/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vbsp.ims.test;

import com.opensymphony.xwork2.ActionSupport;
import java.util.List;

/**
 *
 * @author LION
 */
public class ReportFastNext extends ActionSupport{
    
    private List<String> lstallcolumn;
    private List<String> lstselectcolumn;

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
    
    public String execute()
    {
        //lstselectcolumn.addAll(lstselectcolumn);
        return SUCCESS;
    }
}
