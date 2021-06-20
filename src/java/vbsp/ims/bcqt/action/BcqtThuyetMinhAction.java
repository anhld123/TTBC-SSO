/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.bcqt.action;


import java.sql.Connection;
import java.util.HashMap;
import vbsp.ims.bcqt.dao.TmDao;
import vbsp.ims.bcqt.model.ThuyetMinh;
import vbsp.ims.dao.DaoConnect;
import vbsp.ims.log.CoreLogger;

/**
 *
 * @author Trung
 */
public class BcqtThuyetMinhAction extends ActionBcqtMain implements BcqtFunction {

    protected String ngay_nhaplieu;
    private ThuyetMinh tm = new ThuyetMinh();
    private String action_type;          

    @Override
    public String load() {
        try {
            System.err.println("BCQT_TM" + khoa_bcqt);
            if (!getParaSession()) {
                return ERROR;
            }
            HashMap hmParameter = getParameter();
            Connection conn = new DaoConnect().getConnect();
            TmDao daoMain = new TmDao();
            //khoi tao cho treeview cac pos            
            ngay_nhaplieu = hmParameter.get("ngay_bc").toString();
            lstDulieuNt = daoMain.getDataBCQT_TM(conn, khoa_bcqt,
                    ngay_nhaplieu, UserName, Grade, poscd);
            if (conn != null) {
                conn.close();
            }
            System.err.println("Goi bao THUYET MINH khoa_bcqt=" + khoa_bcqt);

        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> THUYET MINH: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> THUYET MINH: " + e.getMessage());
        }
        Message = "test";
        return SUCCESS;
    }

    @Override
    public String save() {
        try {
            TmDao daoMain = TmDao.newInstance();
            HashMap hmParameter = getParameter();
//            System.err.println(khoa_bcqt+UserName+hmParameter.get("ngay_bc").toString()+action_type);
            if (!daoMain.saveTM(khoa_bcqt, UserName,hmParameter.get("ngay_bc").toString(),Grade,tm,action_type)) {
                addActionError("Bạn chưa lưu được báo cáo xin liên hệ với quản trị để khắc phục");                
                Message="Bạn chưa lưu được báo cáo xin liên hệ với quản trị để khắc phục";
                return ERROR;
            }
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> SAVE_BCQT_M17: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> SAVE_BCQT_M17: " + e.getMessage());
            addActionError("Bạn chưa lưu được báo cáo xin liên hệ với quản trị để khắc phục");            
            return ERROR;
        }
        addActionMessage("Bạn đã lưu dữ liệu thành công");     
        Message = "Bạn đã lưu dữ liệu thành công";
        return SUCCESS;
    }

    
    public String check(){
        try {
            TmDao daoMain = TmDao.newInstance();
            HashMap hmParameter = getParameter();
            System.err.println(khoa_bcqt+UserName+hmParameter.get("ngay_bc").toString()+action_type);
            String ketqua = daoMain.getTMCODE(khoa_bcqt, UserName,hmParameter.get("ngay_bc").toString(),Grade);
            tm.setCode(ketqua);
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> SAVE_BCQT_M17: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> SAVE_BCQT_M17: " + e.getMessage());
            addActionError("Bạn chưa lưu được báo cáo xin liên hệ với quản trị để khắc phục");            
            return ERROR;
        }
        addActionMessage("Bạn đã lưu dữ liệu thành công");     
        Message = "Bạn đã lưu dữ liệu thành công";
        return SUCCESS;        
    }
    
    public String getNgay_nhaplieu() {
        return ngay_nhaplieu;
    }

    public void setNgay_nhaplieu(String ngay_nhaplieu) {
        this.ngay_nhaplieu = ngay_nhaplieu;
    }

    // PHAN BO SUNG THEM MO TA TRANG THAI
    public String getStatusDescript(String code) {
        if (code.equals("Y")) {
            return "Đã gửi";
        } else {
            return "Chưa gửi";
        }
    }

    public ThuyetMinh getTm() {
        return tm;
    }

    public void setTm(ThuyetMinh tm) {
        this.tm = tm;
    }

    public String getAction_type() {
        return action_type;
    }

    public void setAction_type(String action_type) {
        this.action_type = action_type;
    }
   
    
    
}
