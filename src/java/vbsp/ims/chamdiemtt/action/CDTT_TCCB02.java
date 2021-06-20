/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.chamdiemtt.action;

import static com.opensymphony.xwork2.Action.ERROR;
import static com.opensymphony.xwork2.Action.SUCCESS;
import java.sql.Connection;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.derby.iapi.reference.ClassName;
import vbsp.ims.bcqt.model.QT_DULIEU_NT;
import vbsp.ims.chamdiemtt.dao.DaoChamdiemttMain;
import vbsp.ims.dao.DaoConnect;
import vbsp.ims.ktgs.action.*;
import vbsp.ims.log.CoreLogger;

/**
 *
 * @author QUYENNV
 */
public class CDTT_TCCB02 extends ActionChamdiemttMain implements CdttFunction {

    @Override
    public String load() {
        try {
            System.err.println("CDTT_TCCB02");
            if (!getParaSession()) {
                return ERROR;
            }
            HashMap hmParameter = getParameter();
            
            Connection conn = new DaoConnect().getConnect();
            DaoChamdiemttMain daoMain = new DaoChamdiemttMain();
            //khoi tao cho treeview cac pos  
            setLstDatKhong(daoMain.getLOV(UserName, "90", Grade,""));
            setLstDambao(daoMain.getLOV(UserName, "90", Grade,""));
            setLstPhongBan(daoMain.getLOV(UserName, "97", Grade,""));
            
            lstDulieuNt = daoMain.getDataCDTT_CDTT_TCCB02(conn, hmParameter.get("ngay_bc").toString(), UserName, Grade);
            return SUCCESS;                                                            
                                              
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> getDataCDTT_CDTT_TCCB02: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> getDataCDTT_CDTT_TCCB02: " + e.getMessage());
            addActionError("Có lỗi xảy ra: " + e.getMessage());
            return ERROR;
        }        
    }

    @Override
    public String save() {
        try {
            if (!getParaSession()) {
                return ERROR;
            }
            if (lstDulieuNt == null || lstDulieuNt.size() == 0) {
                addActionError("Bạn chưa lưu được báo cáo xin liên hệ với quản trị để khắc phục");;
                return ERROR;
            }
            DaoChamdiemttMain daoMain = DaoChamdiemttMain.newInstance();
            HashMap hmParameter = getParameter();
//            String err = daoMain.check_Info_Tccb02(lstDulieuNt, "CDTT_TCCB0", hmParameter.get("ngay_bc").toString(),UserName, Grade);
//            if(!err.equals("AAA"))
//            {
//                addActionError(err);
//                return ERROR;
//            }
            if (!daoMain.saveCDTT_CDTT_TCCB02( UserName, "", hmParameter.get("ngay_bc").toString(), lstDulieuNt,Grade)) {
                addActionError("Bạn chưa lưu được báo cáo xin liên hệ với quản trị để khắc phục");
                return ERROR;
            }

        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> save CDTT_TCCB02: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> save CDTT_TCCB02: " + e.getMessage());
            addActionError("Có lỗi xảy ra: " + e.getMessage());
            return ERROR;
        }
        addActionMessage("Bạn đã lưu dữ liệu thành công");
        return SUCCESS;
    }
    
    public String resetCDTT() {
        try {
            System.err.println("resetCDTT");
            if (!getParaSession()) {
                return ERROR;
            }
            HashMap hmParameter = getParameter();
            
            Connection conn = new DaoConnect().getConnect();
            DaoChamdiemttMain daoMain = new DaoChamdiemttMain();
            //khoi tao cho treeview cac pos  
            setLstDatKhong(daoMain.getLOV(UserName, "90", Grade,""));
            setLstDambao(daoMain.getLOV(UserName, "90", Grade,""));
            setLstPhongBan(daoMain.getLOV(UserName, "91", Grade,""));
            
            lstDulieuNt = daoMain.getDataCDTT_RESET_TCCB01(conn, hmParameter.get("ngay_bc").toString(), UserName, Grade);
            return SUCCESS;                                                            
                                              
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> getDataCDTT_CDTT_TCCB01: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> getDataCDTT_CDTT_TCCB01: " + e.getMessage());
            addActionError("Có lỗi xảy ra: " + e.getMessage());
            return ERROR;
        }        
    }
}
