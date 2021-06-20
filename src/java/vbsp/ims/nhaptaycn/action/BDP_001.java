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
import vbsp.ims.bcqt.model.DULIEU_NT;
import vbsp.ims.bcqt.model.QT_DULIEU_NT;
import vbsp.ims.dao.DaoConnect;
import vbsp.ims.log.CoreLogger;
import vbsp.ims.tdnn.DaoTdnnMain;

/**
 *
 * @author Trung
 */
public class BDP_001 extends ActionNhaptaycnMain 
        
implements NhaptaycnFunction{
    
    @Override
    public String load(){
        try {
//            System.err.println("NTMOI - 01");
            if (!getParaSession()) {
                return ERROR;
            }
            HashMap hmParameter = getParameter();
            Connection conn = new DaoConnect().getConnect();
            DaoNhaptaycnMain daoMain = new DaoNhaptaycnMain();
//            DaoTdnnMain daoMain1 = new DaoTdnnMain();
            //khoi tao cho treeview cac pos
//            lstAllTdnn = daoMain.getDmKhac(conn, "56");   
//            String s = hmParameter.get("sotien_qu").toString();
            lstNt = daoMain.getDataBdp01(conn, "BDP_001", hmParameter.get("ngay_bc").toString(),UserName, Grade,poscd,hmParameter.get("soku").toString());
            if (conn != null) {
                conn.close();
            }                

        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> BDP_001: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> BDP_001: " + e.getMessage());
        }
        return SUCCESS;
    }
    
    public String reload(){        
        return SUCCESS;
    }

    @Override
    public String save() {
//        System.err.println("Save - NTMOI - 01");
        try {
            if (!getParaSession()) {
                return ERROR;
            }
            if (lstNt == null || lstNt.size() == 0) {
                addActionError("Bạn chưa lưu được báo cáo xin liên hệ với quản trị để khắc phục");;
                return ERROR;
            }
            String iCV = "0";
            if(lstsaveNT_DAT.size()>0)
            {
                iCV = "1";
            }
            
            if (Integer.parseInt(lstNt.get(0).getD23()) >0 && lstNt.get(0).getD25().trim().length()<1) {
                addActionError("Bạn cần nhập nguyên nhân chưa thu được!");;
                return ERROR;
            }
            if (Integer.parseInt(lstNt.get(0).getD8()) ==0) {
                addActionError("Bạn cần phải chọn nhóm địa chỉ của khách hàng!");;
                return ERROR;
            }
            
            if ((Integer.parseInt(lstNt.get(0).getD8()) ==1 || Integer.parseInt(lstNt.get(0).getD8()) ==2) && lstNt.get(0).getD9().trim().length()<1) {
                addActionError("Bạn cần phải nhập địa chỉ chi tiết của hộ vay với nhóm địa chỉ 01 và 02!");;
                return ERROR;
            }
            
            if (Integer.parseInt(lstNt.get(0).getD13()) + Integer.parseInt(lstNt.get(0).getD14()) 
                    + Integer.parseInt(lstNt.get(0).getD15()) + Integer.parseInt(lstNt.get(0).getD16())
                    + Integer.parseInt(lstNt.get(0).getD17()) + Integer.parseInt(lstNt.get(0).getD18())
                    + Integer.parseInt(lstNt.get(0).getD19()) + Integer.parseInt(lstNt.get(0).getD20()) >0 && Integer.parseInt(lstNt.get(0).getD11())  == 0) {
                addActionError("Bạn cần phải chọn nhóm nhận nợ khi có thu hoặc bàn giao nợ!");;
                return ERROR;
            }
            
            if (Integer.parseInt(lstNt.get(0).getD13()) + Integer.parseInt(lstNt.get(0).getD14()) 
                    + Integer.parseInt(lstNt.get(0).getD15()) + Integer.parseInt(lstNt.get(0).getD16())
                    + Integer.parseInt(lstNt.get(0).getD17()) + Integer.parseInt(lstNt.get(0).getD18())
                    + Integer.parseInt(lstNt.get(0).getD19()) + Integer.parseInt(lstNt.get(0).getD20()) >0 && lstNt.get(0).getD12().trim().length() !=6) {
                addActionError("PGD chuyển đến phải đủ 6 ký tự!");;
                return ERROR;
            }
            
            DaoNhaptaycnMain daoMain = DaoNhaptaycnMain.newInstance();
            HashMap hmParameter = getParameter();            
            if(!daoMain.saveBdp01("BDP_001", UserName, iCV,hmParameter.get("ngay_bc").toString(), lstNt,hmParameter.get("soku").toString()))
            {
                addActionError("Bạn chưa lưu được báo cáo xin liên hệ với quản trị để khắc phục");
                return ERROR;
            }

        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> BDP_001: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> BDP_001: " + e.getMessage());
            addActionError("Bạn chưa lưu được báo cáo xin liên hệ với quản trị để khắc phục");
                return ERROR;
        }
        addActionMessage("Bạn đã lưu dữ liệu thành công");
        return SUCCESS;
    }     
    
    public String saveBDPCO() {
//        System.err.println("Save - NTMOI - 01");
        try {
            if (!getParaSession()) {
                return ERROR;
            }
            if (lstNt == null || lstNt.size() == 0) {
                addActionError("Bạn chưa lưu được báo cáo xin liên hệ với quản trị để khắc phục");;
                return ERROR;
            }
            
            if (Integer.parseInt(lstNt.get(0).getD23()) >0 && lstNt.get(0).getD25().trim().length()<1) {
                addActionError("Bạn cần nhập nguyên nhân chưa thu được!");;
                return ERROR;
            }
            if (Integer.parseInt(lstNt.get(0).getD8()) ==0) {
                addActionError("Bạn cần phải chọn nhóm địa chỉ của khách hàng!");;
                return ERROR;
            }
            
            if ((Integer.parseInt(lstNt.get(0).getD8()) ==1 || Integer.parseInt(lstNt.get(0).getD8()) ==2) && lstNt.get(0).getD9().trim().length()<1) {
                addActionError("Bạn cần phải nhập địa chỉ chi tiết của hộ vay với nhóm địa chỉ 01 và 02!");;
                return ERROR;
            }
            
            if (Integer.parseInt(lstNt.get(0).getD13()) + Integer.parseInt(lstNt.get(0).getD14()) 
                    + Integer.parseInt(lstNt.get(0).getD15()) + Integer.parseInt(lstNt.get(0).getD16())
                    + Integer.parseInt(lstNt.get(0).getD17()) + Integer.parseInt(lstNt.get(0).getD18())
                    + Integer.parseInt(lstNt.get(0).getD19()) + Integer.parseInt(lstNt.get(0).getD20()) >0 && Integer.parseInt(lstNt.get(0).getD11())  == 0) {
                addActionError("Bạn cần phải chọn nhóm nhận nợ khi có thu hoặc bàn giao nợ!");;
                return ERROR;
            }
            
            if (Integer.parseInt(lstNt.get(0).getD13()) + Integer.parseInt(lstNt.get(0).getD14()) 
                    + Integer.parseInt(lstNt.get(0).getD15()) + Integer.parseInt(lstNt.get(0).getD16())
                    + Integer.parseInt(lstNt.get(0).getD17()) + Integer.parseInt(lstNt.get(0).getD18())
                    + Integer.parseInt(lstNt.get(0).getD19()) + Integer.parseInt(lstNt.get(0).getD20()) >0 && lstNt.get(0).getD12().trim().length() !=6) {
                addActionError("PGD chuyển đến phải đủ 6 ký tự!");;
                return ERROR;
            }
            
            DaoNhaptaycnMain daoMain = DaoNhaptaycnMain.newInstance();
            HashMap hmParameter = getParameter();            
            if(!daoMain.saveBdp01("BDP_001", UserName, "",hmParameter.get("ngay_bc").toString(), lstNt,hmParameter.get("soku").toString()))
            {
                addActionError("Bạn chưa lưu được báo cáo xin liên hệ với quản trị để khắc phục");
                return ERROR;
            }

        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> BDP_001: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> BDP_001: " + e.getMessage());
            addActionError("Bạn chưa lưu được báo cáo xin liên hệ với quản trị để khắc phục");
                return ERROR;
        }
        addActionMessage("Bạn đã lưu dữ liệu thành công");
        return SUCCESS;
    }  
    
    public String getDetailBDPCo(){
        try {
//            System.err.println("NTMOI - 01");
            if (!getParaSession()) {
                return ERROR;
            }
            HashMap hmParameter = getParameter();
            Connection conn = new DaoConnect().getConnect();
            DaoNhaptaycnMain daoMain = new DaoNhaptaycnMain();
//            DaoTdnnMain daoMain1 = new DaoTdnnMain();
            //khoi tao cho treeview cac pos
//            lstAllTdnn = daoMain.getDmKhac(conn, "56");   
//            String s = hmParameter.get("sotien_qu").toString();
            lstNt = daoMain.getDataBdp01(conn, "BDP_001", hmParameter.get("ngay_bc").toString(),UserName, Grade,poscd,hmParameter.get("soku").toString()+"A");
            if (conn != null) {
                conn.close();
            }            

        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> BDP_001: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> BDP_001: " + e.getMessage());
        }
        return SUCCESS;
    }
    
    public String getDetailBDPKo(){
        try {
//            System.err.println("NTMOI - 01");
            if (!getParaSession()) {
                return ERROR;
            }
            HashMap hmParameter = getParameter();
            Connection conn = new DaoConnect().getConnect();
            DaoNhaptaycnMain daoMain = new DaoNhaptaycnMain();
//            DaoTdnnMain daoMain1 = new DaoTdnnMain();
            //khoi tao cho treeview cac pos
//            lstAllTdnn = daoMain.getDmKhac(conn, "56");   
//            String s = hmParameter.get("sotien_qu").toString();
            lstNt = daoMain.getDataBdp01(conn, "BDP_001", hmParameter.get("ngay_bc").toString(),UserName, Grade,poscd,hmParameter.get("soku").toString()+"B");
            if (conn != null) {
                conn.close();
            }            

        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> BDP_001: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> BDP_001: " + e.getMessage());
        }
        return SUCCESS;
    }
}
