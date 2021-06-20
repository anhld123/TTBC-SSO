/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vbsp.ims.model;

import java.util.List;
import vbsp.ims.dao.SysParamDao;

/**
 *
 * @author Trung
 */
public class SysParamManager {
    private static List<SysParamInfor> sysParams;
    public SysParamManager(){}
    //--------------------------------------------------------------------------
    public List<SysParamInfor> getSysParams(){
        SysParamDao sysParamDao = new SysParamDao();
        sysParams = sysParamDao.getSysParams();        
        return sysParams;
    }    
    public int updateTable(int objId,ErrorObject error_obj){
        for(SysParamInfor infor: sysParams){
            if (infor.getSeqno() == objId){
                if (infor.getDiffercount() > 0){
                    SysParamDao sysParamDao = new SysParamDao();
                    return sysParamDao.updateTable(objId,error_obj);
                } 
                break;
            }
        }        
        error_obj.setError_cd(-1);
        error_obj.setError_msg("Bảng dữ liệu không có sự khác biệt, bạn hãy kiểm tra lại");
        return 0;
    }
    public String getTableName(int objId){
        for(SysParamInfor infor: sysParams){
            if (infor.getSeqno() == objId){
                return infor.getTablename();
            }
        }        
        return "";
    }
    //--------------------------------------------------------------------------
}
