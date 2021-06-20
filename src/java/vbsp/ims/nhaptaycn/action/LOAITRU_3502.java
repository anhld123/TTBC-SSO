/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vbsp.ims.nhaptaycn.action;

import static com.opensymphony.xwork2.Action.ERROR;
import static com.opensymphony.xwork2.Action.SUCCESS;
import java.io.File;
import vbsp.ims.nhaptaycn.action.*;
import vbsp.ims.nhaptaycn.action.*;
import java.sql.Connection;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletContext;
import org.apache.struts2.ServletActionContext;
import vbsp.ims.nhaptaycn.dao.DaoNhaptaycnMain;
import vbsp.ims.bcqt.model.DULIEU_NT;
import vbsp.ims.bcqt.model.QT_DULIEU_NT;
import vbsp.ims.dao.DaoConnect;
import vbsp.ims.dao.DaoDCPLNO;
import vbsp.ims.define.Define;
import vbsp.ims.log.CoreLogger;
import vbsp.ims.syn.ProcessReportSyn;
import vbsp.ims.tdnn.DaoTdnnMain;
import vbsp.ims.xml.XmlKtgsSync;

/**
 *
 * @author Trung
 */
public class LOAITRU_3502 extends ActionNhaptaycnMain 
        
implements NhaptaycnFunction{
    
    @Override
    public String load(){
       try {
            System.err.println("LOAITRU_3502");
            if (!getParaSession()) {
                return ERROR;
            }
            HashMap hmParameter = getParameter();
            Connection conn = new DaoConnect().getConnect();
            DaoNhaptaycnMain daoMain = new DaoNhaptaycnMain();            
                lstDulieuNt = daoMain.getDataLoaitru3502(conn, khoa_nhaptaycn, hmParameter.get("ngay_bc").toString(),UserName, Grade,poscd, hmParameter.get("user_id").toString(), hmParameter.get("nha_dt").toString() );
                setLstCBKetoan(daoMain.getCanBo(UserName,"LT"));
            if (conn != null) {
                conn.close();
            }   
            
                return SUCCESS;            
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> LOAITRU_3502: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> LOAITRU_3502: " + e.getMessage());
        }        
        return SUCCESS;
    }
    

    @Override
    public String save() {
        System.err.println("Save - LOAITRU_3502");
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
                    if (!value.getD2().equals("false")) {
                        lstDat.add(value.getD2());
                    }
            }            
            
            DaoNhaptaycnMain daoMain = new DaoNhaptaycnMain();    
            HashMap hmParameter = getParameter();            
            
//            String iCheck = daoMain.checkData_Info(lstDulieuNt,khoa_nhaptaycn,hmParameter.get("ngay_bc").toString(),UserName, Grade, lstDat);
//            if(!iCheck.equals("XXXAAA"))
//            {
//                addActionError("Lỗi! "+ iCheck);
//                    return ERROR; 
//            }                        
            
             if(!daoMain.saveLoaiTru3502(khoa_nhaptaycn, UserName, "",hmParameter.get("ngay_bc").toString(), lstDulieuNt, lstDat, Grade))
            {
                addActionError("Bạn chưa lưu được báo cáo xin liên hệ với quản trị để khắc phục");
                return ERROR;
            }             

        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> LOAITRU_3502: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> LOAITRU_3502: " + e.getMessage());
            addActionError("Bạn chưa lưu được báo cáo xin liên hệ với quản trị để khắc phục");
                return ERROR;
        }
        addActionMessage("Bạn đã lưu dữ liệu thành công");
        return SUCCESS;
    }                 
}
