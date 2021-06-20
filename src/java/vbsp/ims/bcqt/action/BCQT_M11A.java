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
import vbsp.ims.bcqt.model.QT_MS11A;
import vbsp.ims.dao.DaoConnect;
import vbsp.ims.log.CoreLogger;
import vbsp.ims.model.Pagination;

/**
 *
 * @author Trung
 */
public class BCQT_M11A extends ActionBcqtMain
        implements BcqtFunction {

    private List<QT_MS11A> lstMs11a = new ArrayList<>();
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
                int nTotalrow = 0;
                if (Grade.equals("1")) {
                    nTotalrow = daoMain.getTotalRowMS11A(conn, khoa_bcqt,
                            hmParameter.get("ngay_bc") == null ? "" : hmParameter.get("ngay_bc").toString(),
                            poscd, Grade, UserName, hmParameter.get("so_ku") == null ? "" : hmParameter.get("so_ku").toString());
                    pagination.setPreperties(nTotalrow);
                }
                lstMs11a = daoMain.getDataMS11A(conn, khoa_bcqt, hmParameter.get("ngay_bc").toString(), poscd, Grade, UserName,
                        pagination.getStart() + 1, pagination.getStart() + pagination.getEnd(),
                        hmParameter.get("so_ku") == null ? "" : hmParameter.get("so_ku").toString());
                setLstCBChuongtrinh(daoMain.getLov(Grade, "CHUONGTRINH"));
                pagination.setPage_records(lstMs11a.size());

            } catch (Exception e) {
                CoreLogger.error(this.getClass().getName() + " Exception ->  " + e.getMessage());
                System.err.println(this.getClass().getName() + " Exception -> " + e.getMessage());
                setMessage("Lỗi xin liên hệ với quản trị để được khắc phục: " + e.getMessage());
                addActionError("Lỗi xin liên hệ với quản trị để được khắc phục: " + e.getMessage());
                return ERROR;
            } finally {
                if (conn != null) {
                    conn.close();
                }
            }
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> " + e.getMessage());
            setMessage("Lỗi xin liên hệ với quản trị để được khắc phục: " + e.getMessage());
            addActionError("Lỗi xin liên hệ với quản trị để được khắc phục: " + e.getMessage());
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

            List<QT_DULIEU_NT> lstData = new ArrayList<>();
            for (QT_DULIEU_NT value : lstDulieuNt) {
//                System.err.println("vuale=" + value.getMA());
                if (value != null) {
                    if (!isCheckData(value)) {
                        addActionError("Bạn chưa Nhập đầy đủ dữ liệu, xin nhập đầy đủ dữ liệu");
                        return ERROR;
                    } else {
                        lstData.add(value);
                    }
                }
            }
//
            DaoBcqtMain daoMain = DaoBcqtMain.newInstance();
            HashMap hmParameter = getParameter();
            if (!daoMain.saveBcqt11A(khoa_bcqt, UserName, "", hmParameter.get("ngay_bc").toString(), lstData)) {
                addActionError("Bạn chưa lưu được báo cáo xin liên hệ với quản trị để khắc phục");
                return ERROR;
            }

        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> SAVE_BCQT_MS11A: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> SAVE_BCQT_MS11A: " + e.getMessage());
            addActionError("Bạn chưa lưu được báo cáo xin liên hệ với quản trị để khắc phục");
            return ERROR;
        }
        addActionMessage("Bạn đã lưu dữ liệu thành công");
        return SUCCESS;
    }

    private boolean EmptyOrNull(final String s) {
        return s == null || s.trim().isEmpty();
    }

    private boolean isCheckData(QT_DULIEU_NT dulieunt) {
        if (dulieunt == null) {
            return false;
        }
         if (dulieunt.getD1().equals("DELETE")) {
            return true;
        }
        
        if (EmptyOrNull(dulieunt.getMA()) || EmptyOrNull(dulieunt.getTEN()) || EmptyOrNull(dulieunt.getD1())
                || EmptyOrNull(dulieunt.getD2()) ) {
            return false;
        }
        if(dulieunt.getD1().equals("88888888")&&dulieunt.getD12().equals("-1"))  return false;
        return true;
    }

    public List<QT_MS11A> getLstMs11a() {
        return lstMs11a;
    }

    public void setLstMs11a(List<QT_MS11A> lstMs11a) {
        this.lstMs11a = lstMs11a;
    }

    public Pagination getPagination() {
        return pagination;
    }

    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }

}
