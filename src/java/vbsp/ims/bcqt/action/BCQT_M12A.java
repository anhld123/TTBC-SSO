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
import vbsp.ims.bcqt.model.QT_MS12A;
import vbsp.ims.dao.DaoConnect;
import vbsp.ims.log.CoreLogger;
import vbsp.ims.model.Pagination;

/**
 *
 * @author Trung
 */
public class BCQT_M12A extends ActionBcqtMain
        implements BcqtFunction {

    private List<QT_MS12A> lstms12a = new ArrayList<>();
    private Pagination pagination = new Pagination(50, 1);

    public String load() {
        try {
            Connection conn = new DaoConnect().getConnect();
            try {
                if (!getParaSession()) {
                    return ERROR;
                }
                HashMap hmParameter = getParameter();

                DaoBcqtMain daoMain = new DaoBcqtMain();
                int nTotalrow=0;
                if(Grade.equals("1"))
                {
                    nTotalrow=daoMain.getTotalRowMS12A(conn, khoa_bcqt, hmParameter.get("ngay_bc").toString(), poscd, Grade, UserName);
                    pagination.setPreperties(nTotalrow);
                }
                lstms12a = daoMain.getDataMS12A(conn, khoa_bcqt, hmParameter.get("ngay_bc").toString(), poscd, Grade, UserName,
                        pagination.getStart() + 1, pagination.getStart() + pagination.getEnd());
                pagination.setPage_records(lstms12a.size());


            } catch (Exception e) {
                CoreLogger.error(this.getClass().getName() + " Exception -> BCQT_M12A: " + e.getMessage());
                System.err.println(this.getClass().getName() + " Exception -> BCQT_M12A: " + e.getMessage());
            } finally {
                if (conn != null) {
                    conn.close();
                }
            }
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> BCQT_M12A: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> BCQT_M12A: " + e.getMessage());
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

            List<QT_DULIEU_NT> lstData = new ArrayList<>();
            for (QT_DULIEU_NT value : lstDulieuNt) {
//                System.err.println("vuale=" + value.getMA());
                if (value != null) {
                    lstData.add(value);
                }
            }
//
            DaoBcqtMain daoMain = DaoBcqtMain.newInstance();
            HashMap hmParameter = getParameter();
            if (!daoMain.saveBcqt12A(khoa_bcqt, UserName,"",  hmParameter.get("ngay_bc").toString(), lstData)) {
                addActionError("Bạn chưa lưu được báo cáo xin liên hệ với quản trị để khắc phục");
                return ERROR;
            }

        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> SAVE_BCQT_M12A: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> SAVE_BCQT_M12A: " + e.getMessage());
            addActionError("Bạn chưa lưu được báo cáo xin liên hệ với quản trị để khắc phục");
            return ERROR;
        }
        addActionMessage("Bạn đã lưu dữ liệu thành công");
        return SUCCESS;
    }

    public List<QT_MS12A> getLstms12a() {
        return lstms12a;
    }

    public void setLstms12a(List<QT_MS12A> lstms12a) {
        this.lstms12a = lstms12a;
    }

    public Pagination getPagination() {
        return pagination;
    }

    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }

}
