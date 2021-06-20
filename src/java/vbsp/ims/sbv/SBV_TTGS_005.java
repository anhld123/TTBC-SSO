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
import vbsp.ims.log.CoreLogger;

/**
 *
 * @author BAOANH
 */
public class SBV_TTGS_005 extends actionMainSbv implements sbvInterface {

    public String load() {
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
            lstDulieuNt = daoSbv.newInstance().load005TTGS(khoa_sbv, ngay_bc, UserName,Grade,poscd);
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
            for (QT_DULIEU_NT value : lstDulieuNt) {
//                System.err.println("vuale=" + value.getMA());
                if (value != null) {
                    lstData.add(value);
                }
            }
            if (!daoSbv.newInstance().save005TTGS(khoa_sbv, UserName, hmPara.get("ngay_bc").toString(), lstData)) {
                addActionError("Bạn chưa lưu được báo cáo xin liên hệ với quản trị để khắc phục");
                return ERROR;
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
