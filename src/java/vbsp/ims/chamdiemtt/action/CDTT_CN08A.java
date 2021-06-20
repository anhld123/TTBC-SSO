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
public class CDTT_CN08A extends ActionChamdiemttMain implements CdttFunction {

    @Override
    public String load() {
        try {
            System.err.println("CDTT_PGD");
            if (!getParaSession()) {
                return ERROR;
            }
            HashMap hmParameter = getParameter();
            
            Connection conn = new DaoConnect().getConnect();
            DaoChamdiemttMain daoMain = new DaoChamdiemttMain();
            //khoi tao cho treeview cac pos  
            setTt_cdtt(hmParameter.get("tt_cdtt").toString());
            if(!RULEUSER.equals("9") || (RULEUSER.equals("9") && poscd.size() == 0))
            {
                setTT_DUYET(daoMain.getStatusInput_0607(Grade, khoa_cdtt, UserName, hmParameter.get("ngay_bc").toString(), poscd.size() > 0 ? poscd.get(0).toString() : "",hmParameter.get("tt_cdtt").toString()));
                lstDulieuNt = daoMain.getDataCDTT_CMNV08A(conn, khoa_cdtt, hmParameter.get("ngay_bc").toString(), UserName, Grade, poscd, hmParameter.get("tt_cdtt").toString());
                    if (conn != null) {
                    conn.close();
                }
                return SUCCESS;
            }
            else
            {         
                List<QT_DULIEU_NT> listChuaDuyet = daoMain.getLANHDAODUYET_LOI_08AB(khoa_cdtt, hmParameter.get("ngay_bc").toString(), UserName, Grade, poscd);
                if (listChuaDuyet.isEmpty() || listChuaDuyet.size() == 0) {
                    lstDulieuNt = daoMain.getDataCDTT_CMNV08A(conn, khoa_cdtt, hmParameter.get("ngay_bc").toString(), UserName, Grade, poscd, hmParameter.get("tt_cdtt").toString());
                    if (conn != null) {
                        conn.close();
                    }
                    return "LANHDAO_DUYET";
                } else {
                    setLstDulieuNt(listChuaDuyet);
                    return "LANHDAO_DUYET_ERROR";
                }                                                    
            }                                  
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> CDTT_PGD: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> CDTT_PGD: " + e.getMessage());
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
                        
            if (!daoMain.saveCDTT_CMNV08AB(khoa_cdtt, UserName, "", hmParameter.get("ngay_bc").toString(), lstDulieuNt,Grade, hmParameter.get("tt_cdtt").toString())) {
                addActionError("Bạn chưa lưu được báo cáo xin liên hệ với quản trị để khắc phục");
                return ERROR;
            }

        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> save CDTT_PGD: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> save CDTT_PGD: " + e.getMessage());
            addActionError("Có lỗi xảy ra: " + e.getMessage());
            return ERROR;
        }
        addActionMessage("Bạn đã lưu dữ liệu thành công");
        return SUCCESS;
    }

    public String ChitietChamdiem() {
        try {
//            System.err.println("KTGS 01/BDD");
            if (!getParaSession()) {
                return ERROR;
            }
            HashMap hmParameter = getParameter();
            Connection conn = new DaoConnect().getConnect();
            DaoChamdiemttMain daoMain = new DaoChamdiemttMain();

            //Kiểm tra ngày truyền vào có phải ngày cuối tháng không?
            String pattern = "dd-MMM-yyyy";
            String sNgayBC = hmParameter.get("ngay_bc").toString();                    

            Date sdf = new SimpleDateFormat("dd/MM/yyyy").parse(sNgayBC);
            sNgayBC = new SimpleDateFormat("dd-MMM-yyyy").format(sdf);

            if (!sNgayBC.toLowerCase().equals(vbsp.ims.define.DefineFun.getLastDayOfMonth(sNgayBC, pattern, pattern).toLowerCase())) // If last day of month
            {
                return ERROR;
            }

            //Khoi tao cho treeview cac pos
            lstDulieuNt = daoMain.get_DetailCT(conn, khoa_cdtt, sNgayBC, UserName, Grade, poscd, MACT);
            if (conn != null) {
                conn.close();
            }
//            System.err.println("Goi bao cao Kết quả hoạt động BĐD HĐQT các cấp 02/BDD có khóa=" + khoa_ktgs);
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> KTGS 02/BDD: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> KTGS 02/BDD: " + e.getMessage());
            addActionError("Có lỗi xảy ra: " + e.getMessage());
            return ERROR;
        }
        return SUCCESS;
    }

    public String saveChitiet01() {
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
                if (value != null) {
                    if (!value.getMA().equals("false")) {
                        lstDat.add(value.getMA());
                    }
                }
            }

            DaoChamdiemttMain daoMain = new DaoChamdiemttMain();
            HashMap hmParameter = getParameter();
            if (!daoMain.saveChitiet01(khoa_cdtt, UserName, "", hmParameter.get("ngay_bc").toString(), lstDulieuNt, "", lstDat)) {
                addActionError("Bạn chưa lưu được báo cáo xin liên hệ với quản trị để khắc phục");
                return ERROR;
            }

        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> QLDB_001: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> QLDB_001: " + e.getMessage());
            addActionError("Bạn chưa lưu được báo cáo xin liên hệ với quản trị để khắc phục");
            return ERROR;
        }
        addActionMessage("Bạn đã lưu dữ liệu thành công");
        return SUCCESS;
    }

    public String ChotCDTT() {
        try {
            if (!getParaSession()) {
                return ERROR;
            }
            DaoChamdiemttMain daoMain = DaoChamdiemttMain.newInstance();
            HashMap hmParameter = getParameter();
            int input = daoMain.isCheckPGDInput(khoa_cdtt, UserName, hmParameter.get("ngay_bc").toString(), Grade, "1", UserName);
            if (input == 0) {
                addActionError("Bạn chưa nhập số liệu cho đơn vị nên không thể chốt số liệu");
                return ERROR;
            }
            List<String> lpos = new ArrayList<String>();
            
            if(!daoMain.lockCDTT_pgd(khoa_cdtt, UserName, hmParameter.get("ngay_bc").toString(),Grade.equals("1") ? lpos:poscd,"1",Grade)) {
                addActionError("Bạn chưa chốt được báo cáo xin liên hệ với quản trị để khắc phục");
                return ERROR;
            }

        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> save CDTT_PGD: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> save CDTT_PGD: " + e.getMessage());
            addActionError("Có lỗi xảy ra: " + e.getMessage());
            return ERROR;
        }
        addActionMessage("Bạn đã lưu dữ liệu thành công");
        return SUCCESS;
    }

}
