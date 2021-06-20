/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vbsp.ims.eom_help;

import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionSupport;
import java.util.ArrayList;
import java.util.List;
import vbsp.ims.define.DefineFun;
import vbsp.ims.report.fast.ListValue;

/**
 *
 * @author TrungNT88
 */
public class EOMBuildCombo extends ActionSupport
{
     private List<ListValue> status ;     
     private String new_status_ID;
     int type = 2;
     
     public String build_status_combo(){
        status = new ArrayList<>();
        if (type == 1){
            status.add(new ListValue("N", "N" + "~"  + DefineFun.get_status_descript("N")));
            status.add(new ListValue("D", "D" + "~"  + DefineFun.get_status_descript("D")));
            status.add(new ListValue("E", "E" + "~"  + DefineFun.get_status_descript("E")));
            status.add(new ListValue("P", "P" + "~"  + DefineFun.get_status_descript("P")));
        }else {
            status.add(new ListValue("N",DefineFun.get_status_descript("N")));
            status.add(new ListValue("D",DefineFun.get_status_descript("D")));
            status.add(new ListValue("E",DefineFun.get_status_descript("E")));
            status.add(new ListValue("P",DefineFun.get_status_descript("P")));
        }
        return SUCCESS;
    }

    public List<ListValue> getStatus() {
        return status;
    }

    public void setStatus(List<ListValue> status) {
        this.status = status;
    }
              

   

    public String getNew_status_ID() {
        return new_status_ID;
    }

    public void setNew_status_ID(String new_status_ID) {
        this.new_status_ID = new_status_ID;
    }
}
