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
public class BCQT_PL01A extends ActionBcqtMain
        implements BcqtFunction {

    private String nambc;

    public String load() {
        try {
            if (!getParaSession()) {
                return ERROR;
            }
            HashMap hmParameter = getParameter();
            Connection conn = new DaoConnect().getConnect();
            DaoBcqtMain daoMain = new DaoBcqtMain();
            //khoi tao cho treeview cac pos
            String ngaybc = hmParameter.get("ngay_bc").toString();
            lstAllBcqt = daoMain.getDmKhac(conn, "56");
            lstDulieuNt = daoMain.getDataPL01TL_A(conn, khoa_bcqt,
                    ngaybc, poscd, Grade, UserName);
            setNambc(ngaybc.substring(7, ngaybc.length()));
            if (conn != null) {
                conn.close();
            }
            System.err.println("Goi bao cao quyet toan pl_01A khoa_bcqt=" + khoa_bcqt);

        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> BCQT_PL01A: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> BCQT_PL01A: " + e.getMessage());
            addActionError("Lỗi khi thực hiện tải dữ liệu " + e.getMessage());
            return ERROR;
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
                    lstData.add(value);
                }
            }

            DaoBcqtMain daoMain = DaoBcqtMain.newInstance();
            HashMap hmParameter = getParameter();
            if (!daoMain.saveBcqtPl01A(khoa_bcqt, UserName, "", hmParameter.get("ngay_bc").toString(), lstData)) {
                addActionError("Bạn chưa lưu được báo cáo xin liên hệ với quản trị để khắc phục");
                return ERROR;
            }

        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> SAVE_BCQT_PL01A: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> SAVE_BCQT_PL01A: " + e.getMessage());
            addActionError("Bạn chưa lưu được báo cáo xin liên hệ với quản trị để khắc phục");
            return ERROR;
        }
        addActionMessage("Bạn đã lưu dữ liệu thành công");
        return SUCCESS;
    }

    public String getNambc() {
        return nambc;
    }

    public void setNambc(String nambc) {
        this.nambc = nambc;
    }

    public static void main(String[] args) {
        String ngaybc = "31-dec-2017";
        System.err.println(ngaybc.substring(7, ngaybc.length()));
    }

}
