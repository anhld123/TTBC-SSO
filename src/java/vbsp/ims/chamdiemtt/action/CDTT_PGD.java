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
public class CDTT_PGD extends ActionChamdiemttMain implements CdttFunction {

    @Override
    public String load() {
        try {
            System.err.println("CDTT_PGD");

            if (!getParaSession()) {
                return ERROR;
            }
            if (!Grade.equals("1") && (poscd == null || poscd.isEmpty())) {
                addActionError("Bạn phải chọn đơn vị để xem dữ liệu và duyệt!");
                return ERROR;
            }
            
            HashMap hmParameter = getParameter();
//            if (poscd.size() > 1) {
//                addActionError("Chỉ được phép chọn 1 đơn vị để kiểm tra!");
//                return ERROR;
//            }

            Connection conn = new DaoConnect().getConnect();
            DaoChamdiemttMain daoMain = new DaoChamdiemttMain();
            //khoi tao cho treeview cac pos  
            setTt_cdtt(hmParameter.get("tt_cdtt").toString());
            if (Grade.equals("1")) {
                setTT_DUYET(daoMain.getStatusInput(Grade, khoa_cdtt, UserName, hmParameter.get("ngay_bc").toString(), poscd.size() > 0 ? poscd.get(0).toString() : ""));
                lstDulieuNt = daoMain.getDataCDTT_PGD(conn, khoa_cdtt, hmParameter.get("ngay_bc").toString(), UserName, Grade, poscd, hmParameter.get("tt_cdtt").toString());
                return SUCCESS;
            } else if (Grade.equals("2") && !RULEUSER.equals("9")) {
                int input =0;
                for (int i = 0; i < poscd.size(); i++) {
                    if(!poscd.get(i).equals("999999"))
                    {                        
                        input = daoMain.isCheckPGDInput(khoa_cdtt,poscd.get(i) , hmParameter.get("ngay_bc").toString(), Grade, "1", UserName);
                        if (input == 8 && Grade.equals("2")) {
                            addActionError("Phòng giao dịch " + poscd.get(i) +" chưa duyệt số liệu nên phòng CMNV chưa thể chấm điểm");
                            setTT_DUYET(daoMain.getStatusInput(Grade, khoa_cdtt, UserName, hmParameter.get("ngay_bc").toString(), poscd.size() > 0 ? poscd.get(0).toString() : ""));
                            return ERROR;
                        }
                    }
                }
                setTT_DUYET(daoMain.getStatusInput(Grade, khoa_cdtt, UserName, hmParameter.get("ngay_bc").toString(), poscd.size() > 0 ? poscd.get(0).toString() : ""));
                lstDulieuNt = daoMain.getDataCDTT_PGD(conn, khoa_cdtt, hmParameter.get("ngay_bc").toString(), UserName, Grade, poscd, hmParameter.get("tt_cdtt").toString());
                return SUCCESS;
            } //            Ban chuyên môn nghiệp vụ tại hội sở chính duyệt
            else if (Grade.equals("2") && RULEUSER.equals("9")) {
                List<QT_DULIEU_NT> listChuaDuyet = daoMain.getCMNVTWChuaDuyet(khoa_cdtt, hmParameter.get("ngay_bc").toString(), UserName, Grade, poscd);
                if (listChuaDuyet.isEmpty() || listChuaDuyet.size() == 0) {
                    lstDulieuNt = daoMain.getDataCDTT_PGD(conn, khoa_cdtt, hmParameter.get("ngay_bc").toString(), UserName, Grade, poscd, hmParameter.get("tt_cdtt").toString());
                    return SUCCESS;
                } else {
                    setLstDulieuNt(listChuaDuyet);
                    return "CMNV_CN_CHUADUYET";
                }
//                List<QT_DULIEU_NT> listChuaDuyet = daoMain.getCMNVTWChuaDuyet(khoa_cdtt, hmParameter.get("ngay_bc").toString(), UserName, Grade, poscd);
//                if (listChuaDuyet.isEmpty() || listChuaDuyet.size() == 0) {
//                    setTT_DUYET(daoMain.getStatusInput(Grade, khoa_cdtt, UserName, hmParameter.get("ngay_bc").toString(), poscd.size() > 0 ? poscd.get(0).toString() : ""));
//                    lstDulieuNt = daoMain.getDataCDTT_PGD(conn, khoa_cdtt, hmParameter.get("ngay_bc").toString(), UserName, Grade, poscd, hmParameter.get("tt_cdtt").toString());
//                    return SUCCESS;
//                } else {
//                    setLstDulieuNt(listChuaDuyet);
//                    return "CMNV_CN_CHUADUYET";
//                }
            }
            
//            int input = daoMain.isCheckPGDInput(khoa_cdtt, poscd.size() > 0 ? poscd.get(0).toString() : "", hmParameter.get("ngay_bc").toString(), Grade, "1", UserName);
//            if (input == 2 && Grade.equals("2")) {
//                String CMNV_ERR  = daoMain.getStatusInput(Grade, khoa_cdtt, UserName, hmParameter.get("ngay_bc").toString(), poscd.size() > 0 ? poscd.get(0).toString() : "");
//                addActionError("Phòng CMNV chưa duyệt hết số liệu " + CMNV_ERR);
//                
//                return ERROR;
//            }
//            if (input == 8 && Grade.equals("2")) {
//                addActionError("Phòng giao dịch chưa duyệt số liệu nên phòng CMNV chưa thể chấm điểm");
//                setTT_DUYET(daoMain.getStatusInput(Grade, khoa_cdtt, UserName, hmParameter.get("ngay_bc").toString(), poscd.size() > 0 ? poscd.get(0).toString() : ""));
//                return ERROR;
//            }
//
            
//            lstDulieuNt = daoMain.getDataCDTT_PGD(conn, khoa_cdtt, hmParameter.get("ngay_bc").toString(), UserName, Grade, poscd, hmParameter.get("tt_cdtt").toString());
////            lstDulieuNt_TH = daoMain.getDataCDTT_Status(conn, khoa_cdtt, hmParameter.get("ngay_bc").toString(), Grade);
            if (conn != null) {
                conn.close();
            }
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> CDTT_PGD: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> CDTT_PGD: " + e.getMessage());
            addActionError("Có lỗi xảy ra: " + e.getMessage());
            return ERROR;
        }
        return SUCCESS;
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
            Connection conn = new DaoConnect().getConnect();
            DaoChamdiemttMain daoMain = DaoChamdiemttMain.newInstance();
            HashMap hmParameter = getParameter();
            int input = daoMain.isCheckPGDInput(khoa_cdtt, poscd.size() > 0 ? poscd.get(0).toString() : "", hmParameter.get("ngay_bc").toString(), Grade, "1", UserName);
            if (input == 2 && Grade.equals("2")) {
                addActionError("Phòng CMNV chưa duyệt hết số liệu");
                return ERROR;
            }
            if (input == 1 && Grade.equals("1")) {
                addActionError("Bạn đã chốt số liệu nên ko thể lưu thêm");
                return ERROR;
            }
            
            if (Grade.equals("2") && RULEUSER.equals("9")) {
//                List<QT_DULIEU_NT> listChuaDuyet = daoMain.getCMNVTWChuaDuyet(khoa_cdtt, hmParameter.get("ngay_bc").toString(), UserName, Grade, poscd);
//                if (listChuaDuyet.isEmpty() || listChuaDuyet.size() == 0) {
                    if (!daoMain.saveCDTT05(khoa_cdtt, UserName, "", hmParameter.get("ngay_bc").toString(), lstDulieuNt, Grade)) {
                        addActionError("Bạn chưa lưu được báo cáo xin liên hệ với quản trị để khắc phục");
                        return ERROR;
                    }
//                } else {
//                    setLstDulieuNt(listChuaDuyet);
//                    return "CMNV_CN_CHUADUYET";
//                }
            }
            else
            {
                if (!daoMain.saveCDTT05(khoa_cdtt, UserName, "", hmParameter.get("ngay_bc").toString(), lstDulieuNt, Grade)) {
                    addActionError("Bạn chưa lưu được báo cáo xin liên hệ với quản trị để khắc phục");
                    return ERROR;
                }
            }
            if (conn != null) {
                conn.close();
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

            if (!daoMain.lockCDTT_pgd(khoa_cdtt, UserName, hmParameter.get("ngay_bc").toString(), Grade.equals("1") ? lpos : poscd, "1", Grade)) {
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
    
    public String unlockPL05() {
        try {
            if (!getParaSession()) {
                return ERROR;
            }
            DaoChamdiemttMain daoMain = DaoChamdiemttMain.newInstance();
            HashMap hmParameter = getParameter();
            
            List<String> lpos = new ArrayList<String>();
            if (poscd.size() > 1) {
                addActionError("Chỉ được phép chọn 1 đơn vị để mở duyệt!");
                return ERROR;
            }
            int input = daoMain.isCheckPGDInput(khoa_cdtt, poscd.size() > 0 ? poscd.get(0).toString() : "", hmParameter.get("ngay_bc").toString(), Grade, "1", UserName);
            if (input == 2 && Grade.equals("2")) {
                addActionError("Phòng CMNV đã duyệt bạn không thể mở duyệt lại");
                return ERROR;
            }
            if(khoa_cdtt.equals("CDTT_PGD"))
            {
                 System.err.println("Mo duyet theo phong " + hmParameter.get("tt_cdtt").toString());
                if (!daoMain.lockCDTT_pgd_phongban(khoa_cdtt, UserName, hmParameter.get("ngay_bc").toString(), Grade.equals("1") ? lpos : poscd, "0", Grade, hmParameter.get("tt_cdtt").toString())) {
                    addActionError("Bạn chưa mở duyệt được báo cáo xin liên hệ với quản trị để khắc phục");
                    return ERROR;
                }
            }
            else
            {
                if (!daoMain.lockCDTT_0607(khoa_cdtt, UserName, hmParameter.get("ngay_bc").toString(), Grade.equals("1") ? lpos : poscd, "0", Grade, hmParameter.get("tt_cdtt").toString())) {
                    addActionError("Bạn chưa mở duyệt được báo cáo xin liên hệ với quản trị để khắc phục");
                    return ERROR;
                }
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

    
    public String HOIHONG_DUYET_CDTT() {
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

            if (!daoMain.lockCDTT_pgd(khoa_cdtt, UserName, hmParameter.get("ngay_bc").toString(), Grade.equals("1") ? lpos : poscd, "1", Grade)) {
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
