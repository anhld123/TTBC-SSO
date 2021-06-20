/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vbsp.ims.tdnn;

import vbsp.ims.tdnn.*;
import static com.opensymphony.xwork2.Action.ERROR;
import static com.opensymphony.xwork2.Action.SUCCESS;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import vbsp.ims.bcqt.model.QT_DULIEU_NT;
import vbsp.ims.dao.DaoConnect;
import vbsp.ims.log.CoreLogger;

/**
 *
 * @author Trung
 */
public class GSCMR_001 extends ActionTdnnMain 
implements TdnnFunction{
    
    @Override
    public String load(){
        try {            
//            System.err.println("GSCMR_001");
            if (!getParaSession()) {
                return ERROR;
            }
            HashMap hmParameter = getParameter();
            Connection conn = new DaoConnect().getConnect();
            DaoTdnnMain daoMain = new DaoTdnnMain();
            //khoi tao cho treeview cac pos
            lstAllTdnn = daoMain.getDmKhac(conn, "56");
//            if(Grade.equals("3"))
//            {
//                lstDulieuNt = daoMain.getStatusSendCn(khoa_tdnn,
//                    poscd, hmParameter.get("ngay_bc").toString(),hmParameter.get("txn_cam").toString());
//            }
//            else
//            {
                txn_detail = daoMain.getTXNDetail(hmParameter.get("txn_cam").toString(), hmParameter.get("ngay_bc").toString());
                lstDulieuNt = daoMain.getDataGSCMR_01(conn, khoa_tdnn, hmParameter.get("ngay_bc").toString(),UserName, Grade,poscd,hmParameter.get("txn_cam").toString());
                
//            }                
            if (conn != null) {
                conn.close();
            }            

        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> GSCMR_001: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> GSCMR_001: " + e.getMessage());
        }
        return SUCCESS;
    }
    
    public String reload(){        
        return SUCCESS;
    }

    @Override
    public String save() {
        System.err.println("Save - GSCMR_001");
        try {
            if (!getParaSession()) {
                return ERROR;
            }
            if (lstDulieuNt == null || lstDulieuNt.size() == 0) {
                addActionError("Bạn chưa lưu được báo cáo xin liên hệ với quản trị để khắc phục");;
                return ERROR;
            }
            for (QT_DULIEU_NT.saveDulieuNT_Phi value : lstsaveNT_DAT) {
                if(value != null)                    
                    if (!value.getMA().equals("false")) {
                        lstDat.add(value.getMA());
                    }
            }
            
            DaoTdnnMain daoMain = DaoTdnnMain.newInstance();
            HashMap hmParameter = getParameter();            
            if(!daoMain.saveGscmr01(khoa_tdnn, UserName, "",hmParameter.get("ngay_bc").toString(), lstDulieuNt, hmParameter.get("txn_cam").toString(),lstDat))
            {
                addActionError("Bạn chưa lưu được báo cáo xin liên hệ với quản trị để khắc phục");
                return ERROR;
            }

        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> SAVE_GSCMR_001: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> SAVE_GSCMR_001: " + e.getMessage());
            addActionError("Bạn chưa lưu được báo cáo xin liên hệ với quản trị để khắc phục");
                return ERROR;
        }
        addActionMessage("Bạn đã lưu dữ liệu thành công");
        return SUCCESS;
    }       
    
    public String TongHopTDNN(){
        try {            
//            System.err.println("GSCMR_001");
            if (!getParaSession()) {
                return ERROR;
            }
            HashMap hmParameter = getParameter();
            Connection conn = new DaoConnect().getConnect();
            DaoTdnnMain daoMain = new DaoTdnnMain();
            //khoi tao cho treeview cac pos
            lstAllTdnn = daoMain.getDmKhac(conn, "56");
            if(Grade.equals("3"))
            {
                lstDulieuNt = daoMain.getStatusSendCn(khoa_tdnn,
                    poscd, hmParameter.get("ngay_bc").toString(),hmParameter.get("txn_cam").toString());
                return "guinhan";
            }  
            lstDulieuNt = daoMain.getDataGSCMR_01_TH(conn, khoa_tdnn, hmParameter.get("ngay_bc").toString(),UserName, Grade,poscd,hmParameter.get("txn_cam").toString());
                             
            if (conn != null) {
                conn.close();
            }            

        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> GSCMR_001: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> GSCMR_001: " + e.getMessage());
        }
        return SUCCESS;
    }
}
