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

/**
 *
 * @author Trung
 */
public class PHIUT_001 extends ActionNhaptaycnMain 
        
implements NhaptaycnFunction{
    
    @Override
//    public String load(){
//        try {
//            System.err.println("PHIUT_001");
//            if (!getParaSession()) {
//                return ERROR;
//            }
//            HashMap hmParameter = getParameter();
//            Connection conn = new DaoConnect().getConnect();
//            DaoNhaptaycnMain daoMain = new DaoNhaptaycnMain();
//            //khoi tao cho treeview cac pos
////            lstAllNhaptaycn = daoMain.getDmKhac(conn, "56");
////            lstDulieuNt = daoMain.getDataNhaptaycn_01(conn, khoa_nhaptaycn, "",UserName, Grade,poscd);
//
//            String s = hmParameter.get("nha_dt").toString();
//            
////            totalDataView = daoMain.loadDataTotal(khoa_nhaptaycn,  hmParameter.get("nha_dt").toString(), UserName, poscd);
//            lstDulieuNt_pgd = daoMain.getDataPhiUT_PGD(conn,UserName, Grade);            
//            lstDulieuNt_spham = daoMain.getDataPhiUT_SP(conn, khoa_nhaptaycn, "",UserName, Grade,poscd,hmParameter.get("nha_dt").toString());
//            lstDulieuNt_phanbo = daoMain.getDataPhiUT_PHANBO(conn, khoa_nhaptaycn, "",UserName, Grade,poscd,hmParameter.get("nha_dt").toString());
//            if (conn != null) {
//                conn.close();
//            }
//            System.err.println("Goi bao cao quyet toan Nhaptaycn - 01 khoa_nhaptaycn=" + khoa_nhaptaycn);
//
//        } catch (Exception e) {
//            CoreLogger.error(this.getClass().getName() + " Exception -> Nhaptaycn_01: " + e.getMessage());
//            System.err.println(this.getClass().getName() + " Exception -> Nhaptaycn_01: " + e.getMessage());
//        }
//        return SUCCESS;
//    }
    
    public String load(){
        try {
            System.err.println("phanbophi");
            if (!getParaSession()) {
                return ERROR;
            }
            HashMap hmParameter = getParameter();
            Connection conn = new DaoConnect().getConnect();
            DaoNhaptaycnMain daoMain = new DaoNhaptaycnMain();
            //khoi tao cho treeview cac pos
//            lstAllNhaptaycn = daoMain.getDmKhac(conn, "56");
            
            for (QT_DULIEU_NT.saveDulieuNT_Phi value : lstsaveNT_SP) {
                if(value != null)    
                if (!value.getMA().equals("false")) {
                    sanpham.add(value.getMA());
                }
            }
            
            for (QT_DULIEU_NT.saveDulieuNT_Phi value : lstsaveNT_PGD) {
                if(value != null)                    
                    if (!value.getMA().equals("false")) {
                        pgd.add(value.getMA());
                    }
            }
            
            for (QT_DULIEU_NT.saveDulieuNT_Phi value : lstsaveNT_PB) {
                if(value != null)    
                if (!value.getMA().equals("false")) {
                    phanbo.add("1" + lstDulieuNt_phanbo.get(0).getD1());
                    phanbo.add("2" + lstDulieuNt_phanbo.get(0).getD2());
                    phanbo.add("3" + lstDulieuNt_phanbo.get(0).getD3());
                    phanbo.add("4" + lstDulieuNt_phanbo.get(0).getD4());
                }
            }
//            String s1 = hmParameter.get("nha_dt").toString();
            if(Grade.equals("3"))
            {
                lstDulieuNt = daoMain.getStatusSendCn("PHIUT_001",
                    poscd, hmParameter.get("ngay_bc").toString(),"");
                return SUCCESS;
            }
                    
            String sCheck = "";
            sCheck = daoMain.checkApdungPhiUT("PHIUT_001", hmParameter.get("ngay_bc").toString(),UserName, Grade,pgd,hmParameter.get("nha_dt").toString(),sanpham);            
            try {
                if(!sCheck.equals(""))
                {
                    addActionError("PGD và sản phẩm không được áp dụng: " + sCheck);
                    return ERROR;
                }
            } catch (Exception e) {
            }                       
            lstDulieuNt_chitiet = daoMain.getDataPhiUT_DETAIL(conn, "PHIUT_001", hmParameter.get("ngay_bc").toString(),UserName, Grade,pgd,hmParameter.get("nha_dt").toString(),sanpham, phanbo);            
            if (conn != null) {
                conn.close();
            } 
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> phanbophi: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> phanbophi: " + e.getMessage());
        }
        return SUCCESS;
    }
    
    public String reload(){        
        return SUCCESS;
    }

    @Override
     public String save() {
        System.err.println("Save - KTGS - 01");
        try {
            if (!getParaSession()) {
                return ERROR;
            }
            if (lstDulieuNt_chitiet == null || lstDulieuNt_chitiet.size() == 0) {
                addActionError("Bạn chưa lưu được báo cáo xin liên hệ với quản trị để khắc phục");;
                return ERROR;
            }
            DaoNhaptaycnMain daoMain = DaoNhaptaycnMain.newInstance();
            HashMap hmParameter = getParameter();
            if(!daoMain.savePhanBoPhi("PHIUT_001", UserName, "",hmParameter.get("ngay_bc").toString(), lstDulieuNt_chitiet,Grade))
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
    
    public String phanbophi(){
        try {
            System.err.println("phanbophi");
            if (!getParaSession()) {
                return ERROR;
            }
            HashMap hmParameter = getParameter();
            Connection conn = new DaoConnect().getConnect();
            DaoNhaptaycnMain daoMain = new DaoNhaptaycnMain();
            //khoi tao cho treeview cac pos
//            lstAllNhaptaycn = daoMain.getDmKhac(conn, "56");
            
            for (QT_DULIEU_NT.saveDulieuNT_Phi value : lstsaveNT_SP) {
                if (!value.getMA().equals("false")) {
                    sanpham.add(value.getMA());
                }
            }
            
            for (QT_DULIEU_NT.saveDulieuNT_Phi value : lstsaveNT_PGD) {
                if (!value.getMA().equals("false")) {
                    pgd.add(value.getMA());
                }
            }
            
            for (QT_DULIEU_NT.saveDulieuNT_Phi value : lstsaveNT_PB) {
                if (!value.getMA().equals("false")) {
                    phanbo.add("1_" + lstDulieuNt_phanbo.get(0).getD1());
                    phanbo.add("2_" + lstDulieuNt_phanbo.get(0).getD2());
                    phanbo.add("3_" + lstDulieuNt_phanbo.get(0).getD3());
                    phanbo.add("4_" + lstDulieuNt_phanbo.get(0).getD4());
                }
            }
            String s1 = hmParameter.get("nha_dt").toString();
                              
            
            
            lstDulieuNt_chitiet = daoMain.getDataPhiUT_DETAIL(conn, "PHIUT_001", "",UserName, Grade,pgd,hmParameter.get("nha_dt").toString(),sanpham, phanbo);            
            if (conn != null) {
                conn.close();
            }
            System.err.println("Goi bao cao quyet toan phanbophi - 01 khoa_nhaptaycn=" + khoa_nhaptaycn);

        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> phanbophi: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> phanbophi: " + e.getMessage());
        }
        return SUCCESS;
    }
    
}
