/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.chamdiemcn;

import static com.opensymphony.xwork2.Action.ERROR;
import static com.opensymphony.xwork2.Action.SUCCESS;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import vbsp.ims.chamdiemcn.ActionChamdiemcnMain;
import vbsp.ims.bcqt.model.QT_DULIEU_NT;
import vbsp.ims.dao.DaoConnect;
import vbsp.ims.log.CoreLogger;

/**
 *
 * @author Trung
 */
public class CDTT_CN01PL extends ActionChamdiemcnMain implements CdttFunction {

    public List<String> Sotk = new ArrayList<String>();
    public List<String> selected = new ArrayList<String>();

    public List<String> getSotk() {
        return Sotk;
    }

    public void setSotk(List<String> Sotk) {
        this.Sotk = Sotk;
    }

    public List<String> getSelected() {
        return selected;
    }

    public void setSelected(List<String> selected) {
        this.selected = selected;
    }

    @Override
    public String load() {
        Connection conn = null;
        try {
            System.err.println("CDTT_CN01PL");
            if (!getParaSession()) {
                return ERROR;
            }
            HashMap hmParameter = getParameter();
            conn = new DaoConnect().getConnect();
            DaoChamdiemcnMain daoMain = new DaoChamdiemcnMain();

            String sotk = hmParameter.get("soku").toString();
            if (poscd.size() > 6) {
                addActionError("Bạn chỉ được phép chọn tối đa 6 xã!");
                return ERROR;
            }
            //khoi tao cho treeview cac pos                      
            if (pagination.getStart() == 0) {
                int nCountCust = 1000;
                int size = (int) Math.ceil((double) nCountCust / 10);
                pagination.setPage_size(size * 10);
                pagination.setPreperties(nCountCust);

            }
            setMacb_old(hmParameter.get("user_id").toString());

            String ngay_bc = hmParameter.get("ngay_bc").toString();

            totalDataView = daoMain.loadDataTotal(khoa_cdtt, hmParameter.get("user_id").toString(), UserName, poscd);
            setLstUser(daoMain.getLOV(UserName, "22", Grade, ""));
            lstDulieuNt = daoMain.getDataPL01(conn, khoa_cdtt, ngay_bc, UserName, Grade, poscd, pagination.getStart() + 1, pagination.getStart() + pagination.getEnd(), hmParameter.get("user_id").toString(), hmParameter.get("soku").toString());

            pagination.setPage_records(lstDulieuNt.size());

        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> CDTT_CN01PL: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> CDTT_CN01PL: " + e.getMessage());
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CDTT_CN01PL.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return SUCCESS;
    }

    public String reload() {
        return SUCCESS;
    }

    @Override
    public String save() {
        System.err.println("Save - Nhaptaycn - 01");
        try {
            if (!getParaSession()) {
                return ERROR;
            }
            if (selected == null || selected.size() == 0) {
                addActionError("Bạn chưa chọn tài khoản cần lưu xin chọn tài khoản selected.size()=0");;
                return ERROR;
            }
            DaoChamdiemcnMain daoMain = DaoChamdiemcnMain.newInstance();
            HashMap hmParameter = getParameter();

            List<QT_DULIEU_NT.saveDulieuNT> lstdctmp = new ArrayList<QT_DULIEU_NT.saveDulieuNT>();

//            for (QT_DULIEU_NT value : lstDulieuNt) {
//                if (!value.getD21().equals("false")) {
//                    QT_DULIEU_NT.saveDulieuNT luu = new QT_DULIEU_NT.saveDulieuNT();
//                    luu.setD2(value.getD2());
//                    lstdctmp.add(luu); 
//                }
//            }
            for (int index = 0; index < this.selected.size(); index++) {
                String sd = (String) this.selected.get(index);
                if (!techmasterIsNullOrEmpty((String) this.selected.get(index))) {
                    QT_DULIEU_NT.saveDulieuNT luu = new QT_DULIEU_NT.saveDulieuNT();
                    luu.setD2((String) this.Sotk.get(index));
                    lstdctmp.add(luu);
                }
            }
            if (!daoMain.savePl01(UserName, hmParameter.get("ngay_bc").toString(), lstdctmp, hmParameter.get("user_id").toString())) {
                addActionError("Bạn chưa lưu được báo cáo xin liên hệ với quản trị để khắc phục");
                return ERROR;
            }

        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> SAVE_Nhaptaycn_01: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> SAVE_Nhaptaycn_01: " + e.getMessage());
            addActionError("Lỗi khi lưu dữ liệu: " + e.getMessage());
            return ERROR;
        }
        addActionMessage("Bạn đã lưu dữ liệu thành công");
        return SUCCESS;
    }

    public static boolean techmasterIsNullOrEmpty(String techmasterString) {
        if (techmasterString == null) {
            return true;
        }
        if (techmasterString.trim().equals("")) {
            return true;
        }
        return false;
    }
}
