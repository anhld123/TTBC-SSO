/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vbsp.ims.bcqt.action;

import static com.opensymphony.xwork2.Action.ERROR;
import static com.opensymphony.xwork2.Action.SUCCESS;
import java.sql.Connection;
import java.util.HashMap;
import vbsp.ims.bcqt.dao.DaoBcqtMain;
import vbsp.ims.dao.DaoConnect;
import vbsp.ims.log.CoreLogger;

/**
 *
 * @author Trung
 */
public class BCQT_M05B extends ActionBcqtMain 
implements BcqtFunction{
    
    @Override
    public String load(){
        try {
            System.err.println("BCQT_M05");
            if (!getParaSession()) {
                return ERROR;
            }
            HashMap hmParameter = getParameter();
            Connection conn = new DaoConnect().getConnect();
            DaoBcqtMain daoMain = new DaoBcqtMain();
            //khoi tao cho treeview cac pos
            lstAllBcqt = daoMain.getDmKhac(conn, "56");
//            lstDulieuNt = daoMain.getDataBCQT_NT(conn, khoa_bcqt, hmParameter.get("ngay_bc").toString(),UserName, Grade,poscd);
            lstDulieuNt = daoMain.getDataBCQT_05(conn, khoa_bcqt, hmParameter.get("ngay_bc").toString(),UserName, Grade,poscd);
            if (conn != null) {
                conn.close();
            }
            System.err.println("Goi bao cao quyet toan ms_05 khoa_bcqt=" + khoa_bcqt);

        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> BCQT_M05: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> BCQT_M05: " + e.getMessage());
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
            if(!daoMain.saveBcqtM05(khoa_bcqt, UserName, "",hmParameter.get("ngay_bc").toString(), lstDulieuNt))
            {
                addActionError("Bạn chưa lưu được báo cáo xin liên hệ với quản trị để khắc phục");
                return ERROR;
            }

        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> SAVE_BCQT_M05: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> SAVE_BCQT_M05: " + e.getMessage());
        }
        addActionMessage("Bạn đã lưu dữ liệu thành công");
        return SUCCESS;
    }
    
    
    
}
