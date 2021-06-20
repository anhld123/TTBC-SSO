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
import vbsp.ims.nhaptaycn.dao.DaoNhaptaycnMain;

/**
 *
 * @author chudv
 */
public class BC00230034 extends ActionKtgsMain implements KtgsFunction {

    @Override
    public String load() {
        try {
            System.err.println("KTGS 01/BDD");
            if (!getParaSession()) {
                return ERROR;
            }
            HashMap hmParameter = getParameter();
            Connection conn = new DaoConnect().getConnect();
            DaoKtgsMain daoMain = new DaoKtgsMain();
            DaoNhaptaycnMain daoMain1 = new DaoNhaptaycnMain();

            //Kiểm tra ngày truyền vào có phải ngày cuối tháng không?
            String pattern = "dd-MMM-yyyy";
            String sNgayBC = hmParameter.get("ngay_bc").toString();
            if (!sNgayBC.toLowerCase().equals(vbsp.ims.define.DefineFun.getLastDayOfMonth(sNgayBC, pattern, pattern).toLowerCase())) // If last day of month
            {
                addActionError("Bạn phải chọn ngày cuối tháng");
                return ERROR;
            }
            //Khoi tao cho treeview cac pos
            lstDulieuNt = daoMain.get_BC00230034(conn, khoa_ktgs, hmParameter.get("ngay_bc").toString(), UserName, Grade, poscd,"ALL");
            setLstCBTindung(daoMain1.getCanBo(UserName,"T"));
            setLstCBKetoan(daoMain1.getCanBo(UserName,"K"));
            int result = daoMain.CheckChotKtgs(khoa_ktgs, UserName,  hmParameter.get("ngay_bc").toString(), Grade);
            if (result == 1)
                setTrangthaichotsl("Đã chốt số liệu");
            else
                setTrangthaichotsl("Chưa chốt số liệu");
                
            if (conn != null) {
                conn.close();
            }
            System.err.println("Goi bao cao Kết quả hoạt động BĐD HĐQT các cấp 02/BDD có khóa=" + khoa_ktgs);
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> KTGS 02/BDD: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> KTGS 02/BDD: " + e.getMessage());
        }
        return SUCCESS;
    }
    
    public String reload() {
        try {
            System.err.println("KTGS 01/BDD");
            if (!getParaSession()) {
                return ERROR;
            }
            HashMap hmParameter = getParameter();
            Connection conn = new DaoConnect().getConnect();
            DaoKtgsMain daoMain = new DaoKtgsMain();
            DaoNhaptaycnMain daoMain1 = new DaoNhaptaycnMain();

            //Kiểm tra ngày truyền vào có phải ngày cuối tháng không?
            String pattern = "dd-MMM-yyyy";
            String sNgayBC = hmParameter.get("ngay_bc").toString();
            if (!sNgayBC.toLowerCase().equals(vbsp.ims.define.DefineFun.getLastDayOfMonth(sNgayBC, pattern, pattern).toLowerCase())) // If last day of month
            {
                addActionError("Bạn phải chọn ngày cuối tháng");
                return ERROR;
            }
            //Khoi tao cho treeview cac pos
            lstDulieuNt = daoMain.get_BC00230034_re(conn, khoa_ktgs, hmParameter.get("ngay_bc").toString(), UserName, Grade, poscd,"ALL");
            setLstCBTindung(daoMain1.getCanBo(UserName,"T"));
            setLstCBKetoan(daoMain1.getCanBo(UserName,"K"));
            if (conn != null) {
                conn.close();
            }
            System.err.println("Goi bao cao Kết quả hoạt động BĐD HĐQT các cấp 02/BDD có khóa=" + khoa_ktgs);
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> KTGS 02/BDD: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> KTGS 02/BDD: " + e.getMessage());
        }
        return SUCCESS;
    }
    
    public String addTVBDD002() {
        try {
            System.err.println("KTGS 01/BDD");
            if (!getParaSession()) {
                return ERROR;
            }
            HashMap hmParameter = getParameter();
            Connection conn = new DaoConnect().getConnect();
            DaoKtgsMain daoMain = new DaoKtgsMain();
            DaoNhaptaycnMain daoMain1 = new DaoNhaptaycnMain();

            //Kiểm tra ngày truyền vào có phải ngày cuối tháng không?
            String pattern = "dd-MMM-yyyy";
            String sNgayBC = hmParameter.get("ngay_bc").toString();
            
            Date sdf = new SimpleDateFormat("dd/MM/yyyy").parse(sNgayBC);
            sNgayBC = new SimpleDateFormat("dd-MMM-yyyy").format(sdf);
            
            int result = daoMain.CheckChotKtgs(khoa_ktgs, UserName,  hmParameter.get("ngay_bc").toString(), Grade);
            if (result == 1) {
                addActionError("Bạn đã chốt số liệu nên không thể thêm mới/chỉnh sửa");
                return ERROR;
            } 
                    
            if (!sNgayBC.toLowerCase().equals(vbsp.ims.define.DefineFun.getLastDayOfMonth(sNgayBC, pattern, pattern).toLowerCase())) // If last day of month
            {
                return ERROR;
            }
            //Khoi tao cho treeview cac pos
            lstDulieuNt = daoMain.get_BC00230034(conn, khoa_ktgs, sNgayBC, UserName, Grade, poscd,"NO");
            setLstGioiTinh(daoMain.getLOV(UserName, "1", Grade,sNgayBC));
            setLstDanToc(daoMain.getLOV(UserName, "2", Grade,sNgayBC));
            setLstDonVi(daoMain.getLOV(UserName, "3", Grade,sNgayBC));
            setLstChucVu(daoMain.getLOV(UserName, "4", Grade,sNgayBC));
            setLstTrangThai(daoMain.getLOV(UserName, "5", Grade,sNgayBC));
            setLstThanhVien(daoMain.getLOV(UserName, "6", Grade,sNgayBC));
            setLstBDD(daoMain.getLOV(UserName, "7", Grade,sNgayBC));
            
  
            if (conn != null) {
                conn.close();
            }
            System.err.println("Goi bao cao Kết quả hoạt động BĐD HĐQT các cấp 02/BDD có khóa=" + khoa_ktgs);
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> KTGS 02/BDD: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> KTGS 02/BDD: " + e.getMessage());
        }
        return SUCCESS;
    }
    
    public String addTVBDD003() {
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
            
            Date sdf = new SimpleDateFormat("dd/MM/yyyy").parse(sNgayBC);
            sNgayBC = new SimpleDateFormat("dd-MMM-yyyy").format(sdf);
                    
                    
            if (!sNgayBC.toLowerCase().equals(vbsp.ims.define.DefineFun.getLastDayOfMonth(sNgayBC, pattern, pattern).toLowerCase())) // If last day of month
            {
                return ERROR;
            }
            int result = daoMain.CheckChotKtgs(khoa_ktgs, UserName,  hmParameter.get("ngay_bc").toString(), Grade);
            if (result == 1) {
                addActionError("Bạn đã chốt số liệu nên không thể thêm mới/chỉnh sửa");
                return ERROR;
            } 
                                                    
            //Khoi tao cho treeview cac pos
            lstDulieuNt = daoMain.get_BC00230034(conn, khoa_ktgs, sNgayBC, UserName, Grade, poscd,MATV);
            setLstGioiTinh(daoMain.getLOV(UserName, "1", Grade,sNgayBC));
            setLstDanToc(daoMain.getLOV(UserName, "2", Grade,sNgayBC));
            setLstDonVi(daoMain.getLOV(UserName, "3", Grade,sNgayBC));
            setLstChucVu(daoMain.getLOV(UserName, "4", Grade,sNgayBC));
            setLstTrangThai(daoMain.getLOV(UserName, "5", Grade,sNgayBC));
            setLstThanhVien(daoMain.getLOV(UserName, "6", Grade,sNgayBC));
            setLstBDD(daoMain.getLOV(UserName, "7", Grade,sNgayBC));
            
  
            if (conn != null) {
                conn.close();
            }
//            System.err.println("Goi bao cao Kết quả hoạt động BĐD HĐQT các cấp 02/BDD có khóa=" + khoa_ktgs);
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> KTGS 02/BDD: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> KTGS 02/BDD: " + e.getMessage());
        }
        return SUCCESS;
    }

    @Override
    public String save() {
//        System.err.println("Save - KTGS - 03 (02/BDD)");
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
            
            int iCheck = daoMain.checkTVIEN_Info(lstDulieuNt,khoa_ktgs,hmParameter.get("ngay_bc").toString(),UserName, Grade);
            switch  (iCheck) {
                case 1:
                    addActionError("Lỗi! Tên thành viên không được để trống");
                    return ERROR; 
                case 2:
                    addActionError("Lỗi! Ngày sinh hoặc ngày hiệu lực sai định dạng ngày, tháng, năm dd/MM/yyyy");
                    return ERROR;    
                case 3:
                    addActionError("Lỗi! Bạn chưa chọn giới tính");
                    return ERROR;
                case 4:
                    addActionError("Lỗi! Bạn chưa chọn dân tộc");
                    return ERROR;
                case 5:
                    addActionError("Lỗi! Bạn chưa chọn đơn vị công tác");
                    return ERROR;  
                case 6:
                    addActionError("Lỗi! Bạn chưa chọn chức vụ");
                    return ERROR; 
                case 7:
                    addActionError("Lỗi! Bạn chưa thành viên thay thế với trạng thái là 'Thay thế'");
                    return ERROR; 
                case 8:
                    addActionError("Lỗi!");
                    return ERROR; 
                case 9:
                    addActionError("Lỗi! CMND phải đủ 9 hoặc 12 số");
                    return ERROR; 
                case 10:
                    addActionError("Lỗi! Điện thoại phải đủ 10 số");
                    return ERROR; 
                case 11:
                    addActionError("Lỗi! Địa chỉ mail không đúng");
                    return ERROR;     
            }
            
            if (!daoMain.saveBC00230034(khoa_ktgs, UserName, Grade, "", hmParameter.get("ngay_bc").toString(), lstDulieuNt)) {
                addActionError("Bạn chưa lưu được báo cáo xin liên hệ với quản trị để khắc phục");
                return ERROR;
            }

        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> SAVE_KTGS_02: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> SAVE_KTGS_02: " + e.getMessage());
        }
        addActionMessage("Bạn đã lưu dữ liệu thành công");
        return SUCCESS;
    }
    
    public String saveaddTVBDD002() {
        System.err.println("Save - KTGS - 03 (02/BDD)");
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
            String sNgayBC =hmParameter.get("ngay_bc").toString();
            
            Date sdf = new SimpleDateFormat("dd/MM/yyyy").parse(sNgayBC);
            sNgayBC = new SimpleDateFormat("dd-MMM-yyyy").format(sdf);
            
            int iCheck = daoMain.checkTVIEN_Info(lstDulieuNt,khoa_ktgs,hmParameter.get("ngay_bc").toString(),UserName, Grade);
            switch  (iCheck) {
                case 1:
                    addActionError("Lỗi! Tên thành viên không được để trống");
                    return ERROR; 
                case 2:
                    addActionError("Lỗi! Ngày sinh hoặc ngày hiệu lực sai định dạng ngày, tháng, năm dd/MM/yyyy");
                    return ERROR;    
                case 3:
                    addActionError("Lỗi! Bạn chưa chọn giới tính");
                    return ERROR;
                case 4:
                    addActionError("Lỗi! Bạn chưa chọn dân tộc");
                    return ERROR;
                case 5:
                    addActionError("Lỗi! Bạn chưa chọn đơn vị công tác");
                    return ERROR;  
                case 6:
                    addActionError("Lỗi! Bạn chưa chọn chức vụ");
                    return ERROR; 
                case 7:
                    addActionError("Lỗi! Bạn chưa thành viên thay thế với trạng thái là 'Thay thế'");
                    return ERROR; 
                case 8:
                    addActionError("Lỗi!");
                    return ERROR; 
                case 9:
                    addActionError("Lỗi! CMND phải đủ 9 hoặc 12 số");
                    return ERROR; 
                case 10:
                    addActionError("Lỗi! Điện thoại phải đủ 10 số");
                    return ERROR; 
                case 11:
                    addActionError("Lỗi! Địa chỉ mail không đúng");
                    return ERROR;     
            }
            if (!daoMain.saveBC00230034("BC00230034", UserName, Grade, "", sNgayBC, lstDulieuNt)) {
                addActionError("Bạn chưa lưu được báo cáo xin liên hệ với quản trị để khắc phục");
                return ERROR;
            }

        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> SAVE_KTGS_02: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> SAVE_KTGS_02: " + e.getMessage());
        }
        addActionMessage("Bạn đã lưu dữ liệu thành công");
        return SUCCESS;
    }
}
