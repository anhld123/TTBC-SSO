/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.sbv;

import static com.opensymphony.xwork2.Action.ERROR;
import java.util.HashMap;
import vbsp.ims.log.CoreLogger;
/**
 *
 * NguyetLM
 */
public class SBV_162_TTGS extends actionMainSbv implements sbvInterface{
    private String ky_bc;

    public String getKy_bc() {
        return ky_bc;
    }

    public void setKy_bc(String ky_bc) {
        this.ky_bc = ky_bc;
    }
    
   public String load() {
        try {
            if (!getParaSession()) {
                return ERROR;
            }
            HashMap<String, Object> hmPara = getParameter();
            // Thực hiện chuyển ngày về đúng định dạng DD-MON-YYYY
            ngay_bc = hmPara.get("ngay_bc").toString();
            khoa_sbv = hmPara.get("khoa_sbv").toString();
            ky_bc=hmPara.get("ky_bc").toString();
            
            if (ngay_bc == null || khoa_sbv == null||ky_bc==null) {
                addActionError("Không thể lấy ra được tham số để load dữ liệu");
                return ERROR;
            }
//            if (poscd == null || poscd.isEmpty()) {
//                addActionError("Bạn phải chọn một đợn vị để tải dữ liệu");
//                return ERROR;
//            }
//            if (poscd.size() > 1) {
//                addActionError("Bạn chỉ chọn được một đợn vị để nhập");
//                return ERROR;
//            }
            String Mapgd=daoSbv.newInstance().getPosFromUsername(UserName);
            if(Mapgd.equals("999999")) Mapgd="000100";
            lstDulieuNt = daoSbv.newInstance().load162TTGS(khoa_sbv, ngay_bc, ky_bc, Mapgd);
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
             ky_bc=hmPara.get("ky_bc").toString();
            if (ngay_bc == null || khoa_sbv == null||ky_bc==null) {
                addActionError("Không thể lấy ra được tham số để load dữ liệu");
                return ERROR;
            }
//            if (poscd == null || poscd.isEmpty()) {
//                addActionError("Bạn phải chọn một đợn vị để tải dữ liệu");
//                return ERROR;
//            }
//            if (poscd.size() > 1) {
//                addActionError("Bạn chỉ chọn được một đợn vị để nhập");
//                return ERROR;
//            }
            if (!daoSbv.newInstance().save162TTGS(khoa_sbv, UserName, "000100", hmPara.get("ngay_bc").toString(),ky_bc , lstDulieuNt)) {
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
