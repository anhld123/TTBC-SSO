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
public class LSTP_001 extends ActionNhaptaycnMain 
        
implements NhaptaycnFunction{
    
    @Override
    public String load(){
        try {
            System.err.println("LSTP_001");
            if (!getParaSession()) {
                return ERROR;
            }
            HashMap hmParameter = getParameter();
            Connection conn = new DaoConnect().getConnect();
            DaoNhaptaycnMain daoMain = new DaoNhaptaycnMain();
//            DaoTdnnMain daoMain1 = new DaoTdnnMain();
            //khoi tao cho treeview cac pos
//            lstAllTdnn = daoMain.getDmKhac(conn, "56");
             String s = hmParameter.get("cap_kt").toString();
            if(!Grade.equals("3"))
            {
                addActionError("Báo cáo này chỉ nhập liệu cấp Trung ương.");
                return ERROR;
            }
            if(daoMain.checkgRP("1", UserName) ==0 )
            {
                addActionError("Người dùng này không có quyền nhập thông tin cho báo cáo này.");;
                return ERROR;
            }
            
                lstDulieuNt = daoMain.getDataTraiPhieu_01(conn, "LSTP_001", hmParameter.get("ngay_bc").toString(),UserName, Grade,poscd,hmParameter.get("cap_kt").toString());
            if (conn != null) {
                conn.close();
            }            

        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> LSTP_001: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> LSTP_001: " + e.getMessage());
        }
        return SUCCESS;
    }
    
    public String reload(){        
        return SUCCESS;
    }

    @Override
    public String save() {
        System.err.println("Save - LSTP_001");
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
            if(!daoMain.saveTraiPhieu01("LSTP_001", UserName, "",hmParameter.get("ngay_bc").toString(), lstDulieuNt,hmParameter.get("cap_kt").toString()))
            {
                addActionError("Bạn chưa lưu được báo cáo xin liên hệ với quản trị để khắc phục");
                return ERROR;
            }

        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> NTMOI: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> NTMOI: " + e.getMessage());
            addActionError("Bạn chưa lưu được báo cáo xin liên hệ với quản trị để khắc phục");
                return ERROR;
        }
        addActionMessage("Bạn đã lưu dữ liệu thành công");
        return SUCCESS;
    }                            
}
