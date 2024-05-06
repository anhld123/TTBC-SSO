/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.tdnn;

import static com.opensymphony.xwork2.Action.ERROR;
import static com.opensymphony.xwork2.Action.SUCCESS;
import java.sql.Connection;
import vbsp.ims.dao.DaoConnect;
import vbsp.ims.log.CoreLogger;
import java.util.Date;
import java.util.HashMap;


/**
 *
 * @author HP
 */
public class KTGS_01GDX extends ActionTdnnMain implements TdnnFunction{
    @Override
    public String load(){
        try {
            System.err.println("Load KTGS_01GDX");
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
                    poscd, hmParameter.get("ngay_bc").toString(),hmParameter.get("cap_kt").toString());
            }
            else
                lstDulieuNt = daoMain.getDataTDNN_01(conn, khoa_tdnn, hmParameter.get("ngay_bc").toString(),UserName, Grade,poscd,hmParameter.get("cap_kt").toString());
            if (conn != null) {
                conn.close();
            }            

        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> TDNN_01: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> TDNN_01: " + e.getMessage());
        }
        return SUCCESS;
    }

    @Override
    public String save() {
        System.err.println("Save - KTGS_01GDX");
        try {
            if (!getParaSession()) {
                return ERROR;
            }
            if (lstDulieuNt == null || lstDulieuNt.size() == 0) {
                addActionError("Bạn chưa lưu được báo cáo xin liên hệ với quản trị để khắc phục");;
                return ERROR;
            }
            
            DaoTdnnMain daoMain = DaoTdnnMain.newInstance();
            HashMap hmParameter = getParameter();            
            if(!daoMain.saveTdnn01(khoa_tdnn, UserName, "",hmParameter.get("ngay_bc").toString(), lstDulieuNt, hmParameter.get("cap_kt").toString()))
            {
                addActionError("Bạn chưa lưu được báo cáo xin liên hệ với quản trị để khắc phục");
                return ERROR;
            }

        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> SAVE_TDNN_01: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> SAVE_TDNN_01: " + e.getMessage());
            addActionError("Bạn chưa lưu được báo cáo xin liên hệ với quản trị để khắc phục");
                return ERROR;
        }
        addActionMessage("Bạn đã lưu dữ liệu thành công");
        return SUCCESS;
    }    
}
