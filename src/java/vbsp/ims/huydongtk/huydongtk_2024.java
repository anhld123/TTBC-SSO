/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.huydongtk;

import static com.opensymphony.xwork2.Action.ERROR;
import static com.opensymphony.xwork2.Action.SUCCESS;
import vbsp.ims.nhaptaycn.action.*;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import org.apache.struts2.ServletActionContext;
import vbsp.ims.bcqt.model.QT_DULIEU_NT;
import vbsp.ims.dao.DaoConnect;
import vbsp.ims.define.GenericResult;
import vbsp.ims.gqvl_2023.Service_GQVL2023;
import vbsp.ims.log.CoreLogger;
import vbsp.ims.nghiquyet11cp.DaoNghiquyet11cp;
import vbsp.ims.define.GenericResult; 

/**
 *
 * @author Trung
 */
public class huydongtk_2024 extends ActionNhaptaycnMain
        implements NhaptaycnFunction {

    Service_GQVL2023 _leaveHomeService;
    private String cboCanBo;
    private String flgFilter;
    private String searchKey;
    private ArrayList<String> chkChon = new ArrayList<>();
    private String status;
    private String message;

    public ArrayList<String> getChkChon() {
        return chkChon;
    }

    public void setChkChon(ArrayList<String> chkChon) {
        this.chkChon = chkChon;
    }

    public String getCboCanBo() {
        return cboCanBo;
    }

    public void setCboCanBo(String cboCanBo) {
        this.cboCanBo = cboCanBo;
    }

    public String getFlgFilter() {
        return flgFilter;
    }

    public void setFlgFilter(String flgFilter) {
        this.flgFilter = flgFilter;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String load() {
        try {
            if (!getParaSession()) {
                return ERROR;
            }
            HashMap hmParameter = getParameter();
            DaoNghiquyet11cp daoMain = new DaoNghiquyet11cp();
            Connection conn = new DaoConnect().getConnect();

            _leaveHomeService = new Service_GQVL2023();
            if (hmParameter.get("cboCanBo").toString().equals("") || hmParameter.get("cboCanBo").toString().equals("000000")) {
                addActionError("Bạn chưa chọn cán bộ");
                return ERROR;
            }
            if (hmParameter.get("txtChitieu").toString().equals("0") || hmParameter.get("txtChitieu").toString().equals("")) {
                addActionError("Bạn chưa nhập chỉ tiêu huy dộng");
                return ERROR;
            }
            lstDulieuNt = daoMain.getData_HuyDong_2023(conn, "CB_HUYDONGTK", hmParameter.get("ngay_bc").toString(), UserName, Grade, cboCanBo, flgFilter, poscd,
                    "0", "");
//            System.out.println("local: " );
            if (conn != null) {
                conn.close();
            }

        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> huydong: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> huydong: " + e.getMessage());
        }
        return SUCCESS;
    }

    public String getSearchKey() {
        return searchKey;
    }

    public void setSearchKey(String searchKey) {
        this.searchKey = searchKey;
    }

    public String search() {
        try {
            if (!getParaSession()) {
                return ERROR;
            }
            HashMap hmParameter = getParameter();
            DaoNghiquyet11cp daoMain = new DaoNghiquyet11cp();
            Connection conn = new DaoConnect().getConnect();

            _leaveHomeService = new Service_GQVL2023();
            if (hmParameter.get("cboCanBo").toString().equals("") || hmParameter.get("cboCanBo").toString().equals("000000")) {
                addActionError("Bạn chưa chọn cán bộ");
                return ERROR;
            }
            if (hmParameter.get("txtChitieu").toString().equals("0") || hmParameter.get("txtChitieu").toString().equals("")) {
                addActionError("Bạn chưa nhập chỉ tiêu huy dộng");
                return ERROR;
            }
            lstDulieuNt = daoMain.getData_HuyDong_2023(conn, "CB_HUYDONGTK", hmParameter.get("ngay_bc").toString(), UserName, Grade, cboCanBo, flgFilter, poscd,
                    "1", searchKey);
//            System.out.println("local: " );
            if (conn != null) {
                conn.close();
            }

        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> huydong: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> huydong: " + e.getMessage());
        }
        return SUCCESS;
    }

    @Override
    public String save() {
        try {
            if (!getParaSession()) {
                return ERROR;
            }
            if (lstDulieuNt == null || lstDulieuNt.isEmpty()) {
                addActionError("Bạn chưa lưu được báo cáo xin liên hệ với quản trị để khắc phục");
                return ERROR;
            }

            DaoNghiquyet11cp daoMain = new DaoNghiquyet11cp();
            HashMap hmParameter = getParameter();
            String ngayBaoCao = hmParameter.get("ngay_bc").toString();
            String canbo = hmParameter.get("cboCanBo").toString();
            String chitieu = hmParameter.get("txtChitieu").toString();
            ArrayList<QT_DULIEU_NT> lstLocalDataUpdate = new ArrayList<>();
            for (QT_DULIEU_NT tmp : lstDulieuNt) {
                if (chkChon.size() > 0 && chkChon.contains(tmp.getD3())) {
                    lstLocalDataUpdate.add(tmp);
                }
            }
            if (!daoMain.saveHUYDONG_2024("CB_HUYDONGTK", UserName, Grade, ngayBaoCao, lstLocalDataUpdate, poscd, chkChon, canbo, chitieu)) {
                addActionError("Bạn chưa lưu được báo cáo xin liên hệ với quản trị để khắc phục");
                return ERROR;
            }
            System.out.println("save :" + canbo + " " + chkChon);

        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> huy dong: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> huy dong: " + e.getMessage());
            addActionError("Bạn chưa lưu được báo cáo xin liên hệ với quản trị để khắc phục");
            return ERROR;
        }
        addActionMessage("Bạn đã lưu dữ liệu thành công");
        return SUCCESS;
    }

    public String cancelAssign() {
        try {
            String accountNo = ServletActionContext.getRequest().getParameter("accountNo");
            String staffCode = ServletActionContext.getRequest().getParameter("staffCode");
            String assignDate = ServletActionContext.getRequest().getParameter("assignDate");

            DaoNghiquyet11cp daoMain = new DaoNghiquyet11cp();
            GenericResult<String> _result = daoMain.cancelAssign("CB_HUYDONGTK", accountNo, staffCode, assignDate);

            if (_result.isIsSuccess()) {
                status = "1";
                message = "";
            } else {
                status = "0";
                message = _result.getMessage();
            }
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> cancelAssign: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> cancelAssign: " + e.getMessage());
            status = "0";
            message = e.getMessage();
        }
        return SUCCESS;
    }

}
