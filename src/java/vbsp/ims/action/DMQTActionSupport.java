/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.action;

import com.opensymphony.xwork2.ActionSupport;
import java.util.List;
import vbsp.ims.model.DMQT;
import vbsp.ims.model.DMQTDA;


/**
 *
 * @author Administrator
 */
public class DMQTActionSupport extends ActionSupport {
    
    private List<DMQT> danhmucqt;
    
    public DMQTActionSupport() {
    }
    
    public String execute() throws Exception {
       danhmucqt = new DMQTDA().getDMQT();
       return SUCCESS;
    }

    public List<DMQT> getDanhmucqt() {
        return danhmucqt;
    }

    public void setDanhmucqt(List<DMQT> danhmucqt) {
        this.danhmucqt = danhmucqt;
    }

}
