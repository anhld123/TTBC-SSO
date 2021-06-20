/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.mobilebanking.action;


import static com.opensymphony.xwork2.Action.ERROR;
import static com.opensymphony.xwork2.Action.SUCCESS;
import java.io.File;
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
import vbsp.ims.define.Define;
import vbsp.ims.log.CoreLogger;
import vbsp.ims.mobilebanking.dao.DaoMobileBankingMain;

/**
 *
 * @author Trung
 */
public class MOBILE_004 extends ActionMobileBankingMain
        implements MobileFunction {

    @Override
    public String load() {
        try {
            System.err.println("MOBILE_004");
            if (!getParaSession()) {
                return ERROR;
            }
            HashMap hmParameter = getParameter();
          
            Connection conn = new DaoConnect().getConnect();
            DaoMobileBankingMain daoMain = new DaoMobileBankingMain();
            if(hmParameter.get("nghiepvu").toString().equals("1"))
            {
                lstDulieuNt = daoMain.getDataMobile_04(conn, khoa_mb, "", UserName, Grade, poscd, hmParameter.get("nghiepvu").toString(), hmParameter.get("xathon").toString(),hmParameter.get("maxathon").toString());
                
                setLstTinh(daoMain.getLOV(UserName, "DMTINH",Grade));
                setLstHuyen(daoMain.getLOV(UserName, "DMHUYEN",Grade));
                setLstPosCD(daoMain.getLOV(UserName, "POSCD",Grade));
                setLstTrangthai(daoMain.getLOV(UserName, "TRANGTHAI",Grade));  
                if (conn != null) {
                    conn.close();
                }
                return "themsua";
            }
            else
            {
                lstDulieuNt = daoMain.getDataMobile_04(conn, khoa_mb, "", UserName, Grade, poscd, hmParameter.get("nghiepvu").toString(), hmParameter.get("xathon").toString(),hmParameter.get("maxathon").toString());
                if (conn != null) {
                    conn.close();
                }
                return "truyvan";
            }

            

        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> MOBILE_004: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> MOBILE_004: " + e.getMessage());
        }
        return SUCCESS;
    }

    @Override
    public String save() {
        System.err.println("Save - MOBILE_004");
        try {
            if (!getParaSession()) {
                return ERROR;
            }
            if (lstDulieuNt == null || lstDulieuNt.size() == 0) {
                addActionError("Bạn chưa lưu được báo cáo xin liên hệ với quản trị để khắc phục");;
                return ERROR;
            }
            DaoNhaptaycnMain daoMain = new DaoNhaptaycnMain();
            HashMap hmParameter = getParameter();
           
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> MUASAMTS_001: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> MUASAMTS_001: " + e.getMessage());
            addActionError("Bạn chưa lưu được báo cáo xin liên hệ với quản trị để khắc phục");
            return ERROR;
        }
        addActionMessage("Bạn đã lưu dữ liệu thành công");
        return SUCCESS;
    }

    public String ChotSL() {
        System.err.println("Save - ChotSL");
        try {
            if (!getParaSession()) {
                return ERROR;
            }

            DaoNhaptaycnMain daoMain = new DaoNhaptaycnMain();
            HashMap hmParameter = getParameter();


        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> ChotSL: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> ChotSL: " + e.getMessage());
            addActionError("Bạn chưa lưu được báo cáo xin liên hệ với quản trị để khắc phục");
            return ERROR;
        }
        addActionMessage("Bạn đã chốt dữ liệu thành công");
        return SUCCESS;
    }

    public String MoChotSL() {
        System.err.println("Save - MoChotSL");
        try {
            if (!getParaSession()) {
                return ERROR;
            }

            DaoNhaptaycnMain daoMain = new DaoNhaptaycnMain();
            HashMap hmParameter = getParameter();
            
            

        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> MoChotSL: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> MoChotSL: " + e.getMessage());
            addActionError("Bạn chưa lưu được báo cáo xin liên hệ với quản trị để khắc phục");
            return ERROR;
        }
        addActionMessage("Bạn đã mở duyệt thành công");
        return SUCCESS;
    }
}
