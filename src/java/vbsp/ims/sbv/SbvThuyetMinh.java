/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.sbv;

import static com.opensymphony.xwork2.Action.ERROR;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import vbsp.ims.bcqt.model.QT_DULIEU_NT;
import vbsp.ims.define.Define;
import vbsp.ims.log.CoreLogger;

/**
 *
 * @author BAOANH
 */
public class SbvThuyetMinh extends actionMainSbv implements sbvInterface {
    
       public String load() {
        System.err.println("SbvThuyetMinh" +  ky_bc_LIST);
        try {
            
            if (!getParaSession()) {
                return ERROR;
            }
           
            HashMap<String, Object> hmPara = getParameter();
            // Thực hiện chuyển ngày về đúng định dạng DD-MON-YYYY
            ngay_bc = hmPara.get("ngay_bc").toString();
            khoa_sbv = hmPara.get("khoa_sbv").toString();
            if (ngay_bc == null || khoa_sbv == null) {
                addActionError("Không thể lấy ra được tham số để load dữ liệu");
                return ERROR;
            }
            if ((Grade.equals("3") && poscd == null) || (Grade.equals("3") && poscd.size() == 0)) {
                poscd.add("000100");
            }
//            System.err.println(poscd.get(0));
            String skybc = "";
            try {
                if(ky_bc_LIST.equals(""))
                    skybc = "99";
                else
                    skybc = ky_bc_LIST;
            } catch (Exception e) {
                skybc = "99"; 
            }
            
            lstDulieuNt = daoSbv.newInstance().loadThuyetminh1(khoa_sbv, ngay_bc, UserName,poscd.get(0),skybc);
            lstDulieuNt1 = daoSbv.newInstance().loadThuyetminh2(khoa_sbv, ngay_bc, UserName,poscd.get(0),skybc);
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> load: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> load: " + e.getMessage());
            addActionError("Lỗi khi tải dữ liệu " + e.getMessage());
            return ERROR;
        }
        return SUCCESS;
    }

    @Override
    public String save() {
        System.err.println("Save_SbvThuyetMinh");
        try {
            if (!getParaSession()) {
                return ERROR;
            }
            HashMap<String, Object> hmPara = getParameter();
            // Thực hiện chuyển ngày về đúng định dạng DD-MON-YYYY
            ngay_bc = hmPara.get("ngay_bc").toString();
            khoa_sbv = hmPara.get("khoa_sbv").toString();
            if (ngay_bc == null || khoa_sbv == null) {
                addActionError("Không thể lấy ra được tham số để load dữ liệu");
                return ERROR;
            }   
            
            List<QT_DULIEU_NT> lstData = new ArrayList<>();
            for (QT_DULIEU_NT value : lstDulieuNt1) {
//                System.err.println("vuale=" + value.getMA());
                if (value != null) {
                    lstData.add(value);
                }
            }
            if ((Grade.equals("3") && poscd == null) || (Grade.equals("3") && poscd.size() == 0)) {
                poscd.add("000100");
            }
            String skybc = "";
            try {
                if(ky_bc_LIST.equals(""))
                    skybc = "99";
                else
                    skybc = ky_bc_LIST;
            } catch (Exception e) {
                skybc = "99"; 
            }
            for (String value : poscd) {
                if (!daoSbv.newInstance().saveThuyetMinh(khoa_sbv, UserName, hmPara.get("ngay_bc").toString(),value, lstDulieuNt,lstData,skybc)) {
                addActionError("Bạn chưa lưu được báo cáo xin liên hệ với quản trị để khắc phục");
                return ERROR;
            }
            }
            
            

        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> save: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> save: " + e.getMessage());
            return ERROR;
        }
        addActionMessage("Bạn đã lưu dữ liệu thành công");
        return SUCCESS;
    }

   }
