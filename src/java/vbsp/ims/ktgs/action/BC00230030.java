/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vbsp.ims.ktgs.action;

import vbsp.ims.ktgs.action.*;
import static com.opensymphony.xwork2.Action.ERROR;
import static com.opensymphony.xwork2.Action.SUCCESS;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import vbsp.ims.ktgs.dao.DaoKtgsMain;
import vbsp.ims.bcqt.model.QT_DULIEU_NT;
import vbsp.ims.dao.DaoConnect;
import vbsp.ims.log.CoreLogger;

/**
 *
 * @author Trung
 */
public class BC00230030 extends ActionKtgsMain 
implements KtgsFunction{
    
    @Override
    public String load(){
        try {
            System.err.println("KTGS - 01");
            if (!getParaSession()) {
                return ERROR;
            }
            HashMap hmParameter = getParameter();
            Connection conn = new DaoConnect().getConnect();
            DaoKtgsMain daoMain = new DaoKtgsMain();
            //khoi tao cho treeview cac pos
            lstAllKtgs = daoMain.getDmKhac(conn, "56");
            lstDulieuNt = daoMain.getDataKTGS_01(conn, khoa_ktgs, hmParameter.get("ngay_bc").toString(),UserName, Grade,poscd);
            if (conn != null) {
                conn.close();
            }
            System.err.println("Goi bao cao quyet toan KTGS - 01 khoa_ktgs=" + khoa_ktgs);

        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> KTGS_01: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> KTGS_01: " + e.getMessage());
        }
        return SUCCESS;
    }
    
    public String reload(){
        try {
            System.err.println("RE_KTGS - 01");
            if (!getParaSession()) {
                return ERROR;
            }
            HashMap hmParameter = getParameter();
            Connection conn = new DaoConnect().getConnect();
            DaoKtgsMain daoMain = new DaoKtgsMain();
            //khoi tao cho treeview cac pos
            lstAllKtgs = daoMain.getDmKhac(conn, "56");
            
            lstDulieuNt = daoMain.getDataKTGS_01_re(conn, "BC00230030", hmParameter.get("ngay_bc").toString(),UserName, Grade,poscd);
            if (conn != null) {
                conn.close();
            }
            System.err.println("Goi bao cao quyet toan KTGS - 01 khoa_ktgs=" + khoa_ktgs);

        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> reload - KTGS_01: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> reload - KTGS_01: " + e.getMessage());
        }
        return SUCCESS;
    }

    @Override
    public String save() {
        System.err.println("Save - KTGS - 01");
        try {
            if (!getParaSession()) {
                return ERROR;
            }
            if (lstDulieuNt == null || lstDulieuNt.size() == 0) {
                addActionError("Bạn chưa lưu được báo cáo xin liên hệ với quản trị để khắc phục");;
                return ERROR;
            }
            DaoKtgsMain daoMain = DaoKtgsMain.newInstance();
            HashMap hmParameter = getParameter();
            if(!daoMain.saveKtgs01(khoa_ktgs, UserName, "",hmParameter.get("ngay_bc").toString(), lstDulieuNt))
            {
                addActionError("Bạn chưa lưu được báo cáo xin liên hệ với quản trị để khắc phục");
                return ERROR;
            }

        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> SAVE_KTGS_01: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> SAVE_KTGS_01: " + e.getMessage());
        }
        addActionMessage("Bạn đã lưu dữ liệu thành công");
        return SUCCESS;
    }                            
}
