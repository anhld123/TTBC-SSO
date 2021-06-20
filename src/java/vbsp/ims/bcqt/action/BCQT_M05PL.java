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
public class BCQT_M05PL extends ActionBcqtMain
        implements BcqtFunction {

    public String load() {
        try {
            if (!getParaSession()) {
                return ERROR;
            }
            HashMap hmParameter = getParameter();
            Connection conn = new DaoConnect().getConnect();
            DaoBcqtMain daoMain = new DaoBcqtMain();
            //khoi tao cho treeview cac pos
            lstAllBcqt = daoMain.getDmKhac(conn, "56");
//            lstDulieuNt = daoMain.getDataBCQT_NT(conn, khoa_bcqt, "30-dec-2015","P0401", Grade,poscd);
            lstDulieuNt = daoMain.getData_M05PL(conn, khoa_bcqt, hmParameter.get("ngay_bc").toString(), poscd, Grade, UserName);
            if (conn != null) {
                conn.close();
            }
            System.err.println("Goi bao cao quyet toan BCQT_M05PL khoa_bcqt=" + khoa_bcqt);
            
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> BCQT_M05PL: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> BCQT_M05PL: " + e.getMessage());
        }
        return SUCCESS;
    }

    public String save() {
        try {
            if (!getParaSession()) {
                return ERROR;
            }
            if (lstDulieuNt == null || lstDulieuNt.size() == 0) {
                addActionError("Bạn chưa lưu được báo cáo xin liên hệ với quản trị để khắc phục");;
                return ERROR;
            }
//            lstDulieuNt.removeAll(null);
            List<QT_DULIEU_NT> lstData = new ArrayList<>();
            for (QT_DULIEU_NT value : lstDulieuNt) {
//                System.err.println("vuale=" + value.getMA());
                if (value != null) {
                    if( !value.getMA().equals("100001") && !value.getMA().equals("200001") && !value.getMA().equals("300001") && !value.getMA().equals("400001"))
                    {
                        if(value.getD2()==null ||value.getD2().isEmpty()||value.getD3()==null ||value.getD3().isEmpty()||value.getD4()==null ||value.getD4().isEmpty()
                                ||value.getD5()==null ||value.getD5().isEmpty()
                                ||value.getD6()==null ||value.getD6().isEmpty()
                                ||value.getD6().trim()=="0")
                        {
                            addActionError("Dữ liệu các cột 3, 4, 5 không được để trống. Cột 6 phải nhập giá trị >0");
                            return ERROR;
                        }
                    }
                    lstData.add(value);
                }
            }

            DaoBcqtMain daoMain = DaoBcqtMain.newInstance();
            HashMap hmParameter = getParameter();
            if (!daoMain.saveBcqtM05PL(khoa_bcqt, UserName, "", hmParameter.get("ngay_bc").toString(), lstData)) {
                addActionError("Bạn chưa lưu được báo cáo xin liên hệ với quản trị để khắc phục");
                return ERROR;
            }

        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> SAVE_BCQT_M05PL: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> SAVE_BCQT_M05PL: " + e.getMessage());
            addActionError("Bạn chưa lưu được báo cáo xin liên hệ với quản trị để khắc phục");
            return ERROR;
        }
        addActionMessage("Bạn đã lưu dữ liệu thành công");
        return SUCCESS;
    }

}
