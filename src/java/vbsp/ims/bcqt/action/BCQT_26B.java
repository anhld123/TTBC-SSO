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
public class BCQT_26B extends ActionBcqtMain 
implements BcqtFunction{
    
    @Override
    public String load(){
        try {
            System.err.println("getData26B");
            if (!getParaSession()) {
                return ERROR;
            }
            HashMap hmParameter = getParameter();
            Connection conn = new DaoConnect().getConnect();
            DaoBcqtMain daoMain = new DaoBcqtMain();
            //khoi tao cho treeview cac pos
            lstAllBcqt = daoMain.getDmKhac(conn, "56");
            if (poscd.size() >1)
            {
                addActionMessage("Bạn không thể chọn hơn 1 PGD để kiểm tra");
                return ERROR;
            }
//            lstDulieuNt = daoMain.getDataBCQT_NT(conn, khoa_bcqt, hmParameter.get("ngay_bc").toString(),UserName, Grade,poscd);
            lstDulieuNt = daoMain.getData26B(conn, khoa_bcqt, hmParameter.get("ngay_bc").toString(),UserName, Grade,poscd);
            if (conn != null) {
                conn.close();
            }
            System.err.println("Goi bao cao quyet toan getData26B khoa_bcqt=" + khoa_bcqt);

        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> getData26B: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> getData26B: " + e.getMessage());
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
            if(!daoMain.save26B(khoa_bcqt, UserName,Grade, "",hmParameter.get("ngay_bc").toString(), lstDulieuNt))                
//            if(!daoMain.saveBcqtM05(khoa_bcqt, UserName, "",hmParameter.get("ngay_bc").toString(), lstDulieuNt))
            {
                addActionError("Bạn chưa lưu được báo cáo xin liên hệ với quản trị để khắc phục");
                return ERROR;
            }

        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> save26B: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> save26B: " + e.getMessage());
            addActionError("Bạn chưa lưu được báo cáo xin liên hệ với quản trị để khắc phục");
            return ERROR;
        }
        addActionMessage("Bạn đã lưu dữ liệu thành công");
        return SUCCESS;
    }
}
