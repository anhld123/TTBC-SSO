/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.ktgs.action;

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
import vbsp.ims.dao.DaoConnect;
import vbsp.ims.ktgs.action.*;
import vbsp.ims.ktgs.dao.DaoKtgsMain;
import vbsp.ims.log.CoreLogger;

/**
 *
 * @author chudv
 */
public class BC00230033 extends ActionKtgsMain implements KtgsFunction {

    @Override
    public String load() {
        try {
//            System.err.println("KTGS 01/BDD");
            if (!getParaSession()) {
                return ERROR;
            }
            HashMap hmParameter = getParameter();
            Connection conn = new DaoConnect().getConnect();
            DaoKtgsMain daoMain = new DaoKtgsMain();

            //Kiểm tra ngày truyền vào có phải ngày cuối tháng không?
            String pattern = "dd-MMM-yyyy";
            String sNgayBC = hmParameter.get("ngay_bc").toString();
            if (!sNgayBC.toLowerCase().equals(vbsp.ims.define.DefineFun.getLastDayOfMonth(sNgayBC, pattern, pattern).toLowerCase())) // If last day of month
            {
                addActionError("Bạn phải chọn ngày cuối tháng");
                return ERROR;
            }
            
            int iCheckInput = daoMain.CheckInput03BDD(khoa_ktgs, UserName,  hmParameter.get("ngay_bc").toString(), Grade);
            if (iCheckInput == 0) {
                addActionError("Bạn cần nhập mẫu 03/BDD trước khi làm mẫu 02/BDD");
                return ERROR;
            } 
            
            //Khoi tao cho treeview cac pos
            lstDulieuNt = daoMain.get_data_BC00230033(conn, khoa_ktgs, hmParameter.get("ngay_bc").toString(), UserName, Grade, poscd);
            int result = daoMain.CheckChotKtgs(khoa_ktgs, UserName,  hmParameter.get("ngay_bc").toString(), Grade);
            if (result == 1)
                setTrangthaichotsl("Đã chốt số liệu");
            else
                setTrangthaichotsl("Chưa chốt số liệu");
                
            if (conn != null) {
                conn.close();
            }
            System.err.println("Goi bao cao Kết quả hoạt động BĐD HĐQT các cấp 01/BDD có khóa=" + khoa_ktgs);
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> KTGS 01/BDD: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> KTGS 01/BDD: " + e.getMessage());
        }
        return SUCCESS;
    }

    @Override
    public String save() {
//        System.err.println("Save - KTGS - 03 (01/BDD)");
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
            int result = daoMain.CheckChotKtgs(khoa_ktgs, UserName,  hmParameter.get("ngay_bc").toString(), Grade);
            if (result == 1) {
                addActionError("Bạn đã chốt số liệu nên không thể lưu");
                return ERROR;
            } 
            
            String iCheck = daoMain.checkTVIEN_Info02New(lstDulieuNt,khoa_ktgs,hmParameter.get("ngay_bc").toString(),UserName, Grade);
            if(!iCheck.equals("AAA"))
            {
                    addActionError(iCheck);
                    return ERROR;     
            }
            if (!daoMain.saveBC00230033(khoa_ktgs, UserName, Grade, "", hmParameter.get("ngay_bc").toString(), lstDulieuNt)) {
                addActionError("Bạn chưa lưu được báo cáo xin liên hệ với quản trị để khắc phục");
                return ERROR;
            }

        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> SAVE_KTGS_03: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> SAVE_KTGS_03: " + e.getMessage());
        }
        addActionMessage("Bạn đã lưu dữ liệu thành công");
        return SUCCESS;
    }
}
