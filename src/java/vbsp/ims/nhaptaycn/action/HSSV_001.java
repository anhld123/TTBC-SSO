/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vbsp.ims.nhaptaycn.action;

import vbsp.ims.nhaptaycn.action.*;
import vbsp.ims.nhaptaycn.action.*;
import static com.opensymphony.xwork2.Action.ERROR;
import static com.opensymphony.xwork2.Action.SUCCESS;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import vbsp.ims.nhaptaycn.dao.DaoNhaptaycnMain;
import vbsp.ims.bcqt.model.QT_DULIEU_NT;
import vbsp.ims.dao.DaoConnect;
import vbsp.ims.log.CoreLogger;
import vbsp.ims.tdnn.DaoTdnnMain;

/**
 *
 * @author Trung
 */
public class HSSV_001 extends ActionNhaptaycnMain 
        
implements NhaptaycnFunction{
    
    @Override
    public String load(){
        try {
            System.err.println("HSSV_001");
            if (!getParaSession()) {
                return ERROR;
            }
            HashMap hmParameter = getParameter();
            Connection conn = new DaoConnect().getConnect();
            DaoNhaptaycnMain daoMain = new DaoNhaptaycnMain();
//            DaoTdnnMain daoMain1 = new DaoTdnnMain();
            //khoi tao cho treeview cac pos
//            lstAllTdnn = daoMain.getDmKhac(conn, "56");            
//            if((poscd.size() == 1 && Grade.equals("1")) || 
//                    (poscd.size() == 2 && Grade.equals("1") && poscd.get(0).equals("999999")) 
//                    || Grade.equals("2") || Grade.equals("3"))
//            {
//                
//            }
//            else
//            {
//                addActionError("Bạn chỉ có thể chính sửa theo 1 xã.");
//                return ERROR;
//            }
            if(Grade.equals("3"))
            {
                lstDulieuNt = daoMain.getStatusSendCn("HSSV_001",
                    poscd, hmParameter.get("ngay_bc").toString(),"");
            }
            else
//                lstDulieuNt = daoMain.getDataNTMOI_01(conn, "NTMOI_001", hmParameter.get("ngay_bc").toString(),UserName, Grade,poscd);
                lstDulieuNt = daoMain.getData_HSSV_HTLS(conn, "HSSV_001", hmParameter.get("ngay_bc").toString(), poscd, Grade, UserName);
            if (conn != null) {
                conn.close();
            }            

        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> HSSV_001: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> HSSV_001: " + e.getMessage());
        }
        return SUCCESS;
    }
    
    public String reload(){        
        return SUCCESS;
    }

    @Override
    public String save() {
        System.err.println("Save - HSSV_001 - 01");
        try {
            if (!getParaSession()) {
                return ERROR;
            }
            if (lstDulieuNt == null || lstDulieuNt.size() == 0) {
                addActionError("Bạn chưa lưu được báo cáo xin liên hệ với quản trị để khắc phục");;
                return ERROR;
            }
            List<QT_DULIEU_NT> lstDulieuNt_test = new ArrayList<>();

                for (QT_DULIEU_NT.saveDulieuNT_Phi value : lstsaveNT_PGD) {
                    if(value != null)                    
                        if (!value.getD1().equals("false")) {
                            for (QT_DULIEU_NT valueNt : lstDulieuNt) {
                                if(valueNt != null)                    
                                    if (valueNt.getD1().equals(value.getD1())) {                                        
                                        lstDulieuNt_test.add(valueNt);
                                    }                            
                            }
                        }                    
                }            
                for (QT_DULIEU_NT valueNt : lstDulieuNt) {
                    if(valueNt != null)                    
                        if (valueNt.getMA().equals("100999")) {                              
                            lstDulieuNt_test.add(valueNt);
                        }                            
                }
                
                for (QT_DULIEU_NT valueNt_test : lstDulieuNt_test) {
                    if(valueNt_test.getD2().equals("") || valueNt_test.getD3().equals("") ||
                                    valueNt_test.getD4().equals("") || valueNt_test.getD5().equals("") || valueNt_test.getD6().equals("")||
                                    valueNt_test.getD7().equals("") || valueNt_test.getD8().equals("") ||
                                    valueNt_test.getD8().equals("") || valueNt_test.getD9().equals(""))
                            {
                                addActionError("Bạn cần điền đầy đủ thông tin liên quan đến khách hàng và hssv");;
                                return ERROR;
                            }    
                }
                
            
            DaoNhaptaycnMain daoMain = DaoNhaptaycnMain.newInstance();
            HashMap hmParameter = getParameter();            
            if(!daoMain.saveHssv_001("HSSV_001", UserName, "",hmParameter.get("ngay_bc").toString(), lstDulieuNt_test,lstDulieuNt))
            {
                addActionError("Bạn chưa lưu được báo cáo xin liên hệ với quản trị để khắc phục");
                return ERROR;
            }

        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> HSSV_001: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> HSSV_001: " + e.getMessage());
            addActionError("Bạn chưa lưu được báo cáo xin liên hệ với quản trị để khắc phục");
                return ERROR;
        }
        addActionMessage("Bạn đã lưu dữ liệu thành công");
        return SUCCESS;
    }                            
}
