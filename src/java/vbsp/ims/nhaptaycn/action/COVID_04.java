/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vbsp.ims.nhaptaycn.action;

import static com.opensymphony.xwork2.Action.ERROR;
import static com.opensymphony.xwork2.Action.SUCCESS;
import vbsp.ims.bcqt.action.*;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import vbsp.ims.bcqt.model.QT_DULIEU_NT;
import vbsp.ims.dao.DaoConnect;
import vbsp.ims.ktgs.dao.DaoKtgsMain;
import vbsp.ims.log.CoreLogger;
import vbsp.ims.nhaptaycn.dao.DaoNhaptaycnMain;

/**
 *
 * @author Trung
 */
public class COVID_04 extends ActionNhaptaycnMain
implements NhaptaycnFunction{
    public String load(){
       try {
            System.err.println("COVID_04");
            if (!getParaSession()) {
                return ERROR;
            }
            HashMap hmParameter = getParameter();
            Connection conn = new DaoConnect().getConnect();
            DaoNhaptaycnMain daoMain = new DaoNhaptaycnMain();            
            DaoKtgsMain daoMainKtgs = new DaoKtgsMain();            
            lstDulieuNt = daoMain.getDataCovid_04(conn, khoa_nhaptaycn, hmParameter.get("ngay_bc").toString(),UserName, Grade,poscd);            
//                setLstCBKetoan(daoMain.getCanBo(UserName,"LT"));
            setLstBDD(daoMainKtgs.getLOV(UserName, "7", Grade,hmParameter.get("ngay_bc").toString()));
            if (conn != null) {
                conn.close();
            }                                       
            
            if (daoMain.checkLienHuyen(Grade, UserName) ==0)
            {
                return SUCCESS;
            }
            else
            {
                return "lienhuyen";
            }
            
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> COVID_04: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> COVID_04: " + e.getMessage());
        }        
        return SUCCESS;
    }
    
    public String save(){
        try {
            if (!getParaSession()) {
                return ERROR;
            }
//            if (lstDulieuNt == null || lstDulieuNt.size() == 0) {
//                addActionError("Bạn chưa lưu được báo cáo xin liên hệ với quản trị để khắc phục");;
//                return ERROR;
//            }
//            lstDulieuNt.removeAll(null);
            List<QT_DULIEU_NT> lstData = new ArrayList<>();
            for (QT_DULIEU_NT value : lstDulieuNt) {
//                System.err.println("vuale=" + value.getMA());
                if (value != null) {                                        
                    lstData.add(value);
                }
            }
            

            DaoNhaptaycnMain daoMain = new DaoNhaptaycnMain();    
            HashMap hmParameter = getParameter();      
            
            String iCheck = daoMain.checkData_Info(lstData,khoa_nhaptaycn,hmParameter.get("ngay_bc").toString(),UserName, Grade, lstDat);
            if(!iCheck.equals("XXXAAA"))
            {
                addActionError("Lỗi! "+ iCheck);
                    return ERROR; 
            }                        
            
            
            if(!daoMain.saveCoVid04(khoa_nhaptaycn, UserName, "",hmParameter.get("ngay_bc").toString(), lstData))
            {
                addActionError("Bạn chưa lưu được báo cáo xin liên hệ với quản trị để khắc phục");
                return ERROR;
            } 

        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> SAVE_COVID_04: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> SAVE_COVID_04: " + e.getMessage());
            addActionError("Bạn chưa lưu được báo cáo xin liên hệ với quản trị để khắc phục");
            return ERROR;
        }
        addActionMessage("Bạn đã lưu dữ liệu thành công");
        return SUCCESS;
    }
}
