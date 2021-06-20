/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.genbcqt;

import com.opensymphony.xwork2.ActionSupport;
import java.util.List;

/**
 *
 * @author Administrator
 */
public class genmaubc_action extends ActionSupport {
    private List<genmaubc> lstviewcontrol;
    
    public genmaubc_action() {
    }
    
    public String execute() throws Exception {
        lstviewcontrol = new genmaubc_model().gentidebc();
        return "thanhcong";
    }

    public List<genmaubc> getLstviewcontrol() {
        return lstviewcontrol;
    }

    public void setLstviewcontrol(List<genmaubc> lstviewcontrol) {
        this.lstviewcontrol = lstviewcontrol;
    }
        
}
