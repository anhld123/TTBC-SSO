/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vbsp.ims.bcqt.action;

import static com.opensymphony.xwork2.Action.ERROR;
import static com.opensymphony.xwork2.Action.SUCCESS;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import vbsp.ims.bcqt.dao.DaoBcqtMain;
import vbsp.ims.bcqt.model.QT_DULIEU_NT;
import vbsp.ims.dao.DaoConnect;
import vbsp.ims.log.CoreLogger;

/**
 *
 * @author Trung
 */
public class BCQT_M06B extends ActionBcqtMain 
implements BcqtFunction{
    
    @Override
    public String load(){
        try {
            System.err.println("BCQT_M06B");
            if (!getParaSession()) {
                return ERROR;
            }
            HashMap hmParameter = getParameter();
            Connection conn = new DaoConnect().getConnect();
            DaoBcqtMain daoMain = new DaoBcqtMain();
            //khoi tao cho treeview cac pos
            lstAllBcqt = daoMain.getDmKhac(conn, "56");
            lstDulieuNt = daoMain.getDataBCQT_NT06AB(conn, khoa_bcqt, hmParameter.get("ngay_bc").toString(),UserName, Grade,poscd);
            if (conn != null) {
                conn.close();
            }
            System.err.println("Goi bao cao quyet toan BCQT_M06B khoa_bcqt=" + khoa_bcqt);

        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> BCQT_M06B: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> BCQT_M06B: " + e.getMessage());
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
            DaoBcqtMain daoMain = DaoBcqtMain.newInstance();
            HashMap hmParameter = getParameter();
            if(!daoMain.saveBcqtM06B(khoa_bcqt, UserName, "",hmParameter.get("ngay_bc").toString(), lstDulieuNt))
            {
                addActionError("Bạn chưa lưu được báo cáo xin liên hệ với quản trị để khắc phục");
                return ERROR;
            }

        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> SAVE_BCQT_M06B: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> SAVE_BCQT_M06B: " + e.getMessage());
        }
        addActionMessage("Bạn đã lưu dữ liệu thành công");
        return SUCCESS;
    }
    
    public String getBcqt06B_1() {
        try {
            if (!getParaSession()) {
                return ERROR;
            }
            HashMap hmParameter = getParameter();
            Connection conn = new DaoConnect().getConnect();
            DaoBcqtMain daoMain = new DaoBcqtMain();
            //khoi tao cho treeview cac pos
            lstAllBcqt = daoMain.getDmKhac(conn, "56");
            String sPos = poscd.get(0);
            List<String> myList = new ArrayList<String>(Arrays.asList(sPos.split(",")));
//            lstDulieuNt = daoMain.getDataBCQT_NT(conn, khoa_bcqt, "30-dec-2015","P0401", Grade,poscd);
            lstDulieuNt = daoMain.getData_M06A1(conn, khoa_bcqt, hmParameter.get("ngay_bc").toString(), myList, Grade, UserName);
            if (conn != null) {
                conn.close();
            }
            System.err.println("Goi bao cao quyet toan getBcqt06A_1 khoa_bcqt=" + khoa_bcqt);
            
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> getBcqt06A_1: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> getBcqt06A_1: " + e.getMessage());
        }
        return SUCCESS;
    }
    
    public String SAVE_BCQT_M06B1() {
        System.err.println("SAVE_BCQT_M06A1");
        try {
            if (!getParaSession()) {
                return ERROR;
            }
            if (lstDulieuNt == null || lstDulieuNt.size() == 0) {
                addActionError("Bạn chưa lưu được báo cáo xin liên hệ với quản trị để khắc phục");;
                return ERROR;
            }
//            lstDulieuNt.removeAll(null);
            List<QT_DULIEU_NT> lstData = new ArrayList<>();
            for (QT_DULIEU_NT value : lstDulieuNt) {
//                System.err.println("vuale=" + value.getMA());
                if (value != null) {
                    lstData.add(value);
                }
            }

            DaoBcqtMain daoMain = DaoBcqtMain.newInstance();
            HashMap hmParameter = getParameter();
            if (!daoMain.saveBcqtM06A1(khoa_bcqt, UserName, "000401", hmParameter.get("ngay_bc").toString(), lstData)) {
                addActionError("Bạn chưa lưu được báo cáo xin liên hệ với quản trị để khắc phục");
                return ERROR;
            }

        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> SAVE_BCQT_M06A1: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> SAVE_BCQT_M06A1: " + e.getMessage());
            addActionError("Bạn chưa lưu được báo cáo xin liên hệ với quản trị để khắc phục");
            return ERROR;
        }
        addActionMessage("Bạn đã lưu dữ liệu thành công");
        return SUCCESS;
    }
    
    public String getBcqt06B_2() {
        try {
            if (!getParaSession()) {
                return ERROR;
            }
            HashMap hmParameter = getParameter();
            Connection conn = new DaoConnect().getConnect();
            DaoBcqtMain daoMain = new DaoBcqtMain();
            //khoi tao cho treeview cac pos
            lstAllBcqt = daoMain.getDmKhac(conn, "56");
            String sPos = poscd.get(0);
            List<String> myList = new ArrayList<String>(Arrays.asList(sPos.split(",")));
//            lstDulieuNt = daoMain.getDataBCQT_NT(conn, khoa_bcqt, "30-dec-2015","P0401", Grade,poscd);
            lstDulieuNt = daoMain.getData_M06A2(conn, khoa_bcqt, hmParameter.get("ngay_bc").toString(), myList, Grade, UserName);
            if (conn != null) {
                conn.close();
            }
            System.err.println("Goi bao cao quyet toan getBcqt06A_1 khoa_bcqt=" + khoa_bcqt);
            
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> getBcqt06A_1: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> getBcqt06A_1: " + e.getMessage());
        }
        return SUCCESS;
    }
    
    public String SAVE_BCQT_M06B2() {
        System.err.println("SAVE_BCQT_M06A1");
        try {
            if (!getParaSession()) {
                return ERROR;
            }
            if (lstDulieuNt == null || lstDulieuNt.size() == 0) {
                addActionError("Bạn chưa lưu được báo cáo xin liên hệ với quản trị để khắc phục");;
                return ERROR;
            }
//            lstDulieuNt.removeAll(null);
            List<QT_DULIEU_NT> lstData = new ArrayList<>();
            for (QT_DULIEU_NT value : lstDulieuNt) {
//                System.err.println("vuale=" + value.getMA());
                if (value != null) {
                    lstData.add(value);
                }
            }

            DaoBcqtMain daoMain = DaoBcqtMain.newInstance();
            HashMap hmParameter = getParameter();
            if (!daoMain.saveBcqtM06A1(khoa_bcqt, UserName, "000401", hmParameter.get("ngay_bc").toString(), lstData)) {
                addActionError("Bạn chưa lưu được báo cáo xin liên hệ với quản trị để khắc phục");
                return ERROR;
            }

        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> SAVE_BCQT_M06A1: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> SAVE_BCQT_M06A1: " + e.getMessage());
            addActionError("Bạn chưa lưu được báo cáo xin liên hệ với quản trị để khắc phục");
            return ERROR;
        }
        addActionMessage("Bạn đã lưu dữ liệu thành công");
        return SUCCESS;
    }
    
    
}
