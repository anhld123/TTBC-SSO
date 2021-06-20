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
import vbsp.ims.bcqt.model.QT_MS13SK;
import vbsp.ims.dao.DaoConnect;
import vbsp.ims.log.CoreLogger;
import vbsp.ims.model.Pagination;

/**
 *
 * @author Trung
 */
public class BCQT_M13SK extends ActionBcqtMain
        implements BcqtFunction {

    private List<QT_MS13SK> lstms13sk = new ArrayList<>();
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
                    nTotalrow=daoMain.getTotalRowMS13SK(conn, khoa_bcqt, 
                            hmParameter.get("ngay_bc")==null?"":hmParameter.get("ngay_bc").toString(), 
                            poscd, Grade, UserName,hmParameter.get("so_ku")==null?"":hmParameter.get("so_ku").toString());
                    pagination.setPreperties(nTotalrow);
                }
                lstms13sk = daoMain.getDataMS13SK(conn, khoa_bcqt, hmParameter.get("ngay_bc").toString(), poscd, Grade, UserName,
                        pagination.getStart() + 1, pagination.getStart() + pagination.getEnd(),
                        hmParameter.get("so_ku")==null?"":hmParameter.get("so_ku").toString());
                pagination.setPage_records(lstms13sk.size());


            } catch (Exception e) {
                CoreLogger.error(this.getClass().getName() + " Exception ->  " + e.getMessage());
                System.err.println(this.getClass().getName() + " Exception -> " + e.getMessage());
            } finally {
                if (conn != null) {
                    conn.close();
                }
            }
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> " + e.getMessage());
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
            if (!daoMain.saveBcqt13SK(khoa_bcqt, UserName,"",  hmParameter.get("ngay_bc").toString(), lstData)) {
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

    public List<QT_MS13SK> getLstms13sk() {
        return lstms13sk;
    }

    public void setLstms13sk(List<QT_MS13SK> lstms13sk) {
        this.lstms13sk = lstms13sk;
    }

    public Pagination getPagination() {
        return pagination;
    }

    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }

}
