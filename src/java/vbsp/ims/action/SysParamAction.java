/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.action;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import java.util.List;
import vbsp.ims.model.ErrorObject;
import vbsp.ims.model.SysParamInfor;
import vbsp.ims.model.SysParamManager;

/**
 *
 * @author Trung
 */
public class SysParamAction extends ActionSupport
        implements ModelDriven {

    private SysParamInfor sysparam;
    private List<SysParamInfor> sysparams;
    private String updateTable;
    private int updateObjId;
    private String message;

    //--------------------------------------------------------------------------

    @Override
    public Object getModel() {
        return sysparam;
    }

    public String list() {
        SysParamManager manager = new SysParamManager();
        this.sysparams = manager.getSysParams();
        return "success";
    }

    public String update() {
        System.err.println("Update table -->" + updateObjId);
        SysParamManager manager = new SysParamManager();
        updateTable = manager.getTableName(updateObjId);        
        if (updateTable == null || updateTable.isEmpty()) {
            this.message = "(*)Cập nhật " + updateTable + " thất bại.";
        } else {            
            int updatedRow = 0;
            ErrorObject error_obj = new ErrorObject();
            updatedRow = manager.updateTable(updateObjId,error_obj);
            this.sysparams = manager.getSysParams();
            if (updatedRow > 0)
                this.message = "(*)Cập nhật " + updateTable 
                        + " thành công, số dòng cập nhật: " + updatedRow;
            else
                this.message = "(*)" + error_obj.getError_cd() + ":" + error_obj.getError_msg();
        }
        return "success";
    }

    //--------------------------------------------------------------------------

    public List<SysParamInfor> getSysparams() {
        return sysparams;
    }

    public void setSysparams(List<SysParamInfor> sysparams) {
        this.sysparams = sysparams;
    }

    public SysParamInfor getSysparam() {
        return sysparam;
    }

    public void setSysparam(SysParamInfor sysparam) {
        this.sysparam = sysparam;
    }

    public String getUpdateTable() {
        return updateTable;
    }

    public void setUpdateTable(String updateTable) {
        this.updateTable = updateTable;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getUpdateObjId() {
        return updateObjId;
    }

    public void setUpdateObjId(int updateObjId) {
        this.updateObjId = updateObjId;
    }        
}
