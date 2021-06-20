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
public class USER_001 extends ActionNhaptaycnMain 
        
implements NhaptaycnFunction{
    
    @Override
    public String load(){
        try {
            System.err.println("USER_001");
            if (!getParaSession()) {
                return ERROR;
            }
            HashMap hmParameter = getParameter();
            Connection conn = new DaoConnect().getConnect();
            DaoNhaptaycnMain daoMain = new DaoNhaptaycnMain();
//            DaoTdnnMain daoMain1 = new DaoTdnnMain();
            //khoi tao cho treeview cac pos
//            lstAllTdnn = daoMain.getDmKhac(conn, "56");
            if(Grade.equals("3"))
            {
                lstDulieuNt = daoMain.getStatusSendCn("USER_001",
                    poscd, hmParameter.get("ngay_bc").toString(),"");
            }
            else
                lstDulieuNt = daoMain.getDataUser_01(conn, "USER_001", hmParameter.get("ngay_bc").toString(),UserName, Grade,poscd);
            if (conn != null) {
                conn.close();
            }            

        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> USER_001: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> USER_001: " + e.getMessage());
        }
        return SUCCESS;
    }
    
    public String reload(){        
        return SUCCESS;
    }

    @Override
    public String save() {
        System.err.println("Save - USER - 01");
        try {
            if (!getParaSession()) {
                return ERROR;
            }
            if (lstDulieuNt == null || lstDulieuNt.size() == 0) {
                addActionError("Bạn chưa lưu được báo cáo xin liên hệ với quản trị để khắc phục");;
                return ERROR;
            }
            
            DaoNhaptaycnMain daoMain = DaoNhaptaycnMain.newInstance();
            HashMap hmParameter = getParameter();            
            if(!daoMain.saveUser01("USER_001", UserName, "",hmParameter.get("ngay_bc").toString(), lstDulieuNt))
            {
                addActionError("Bạn chưa lưu được báo cáo xin liên hệ với quản trị để khắc phục");
                return ERROR;
            }

        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> USER: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> USER: " + e.getMessage());
            addActionError("Bạn chưa lưu được báo cáo xin liên hệ với quản trị để khắc phục");
                return ERROR;
        }
        addActionMessage("Bạn đã lưu dữ liệu thành công");
        return SUCCESS;
    }                            
}
