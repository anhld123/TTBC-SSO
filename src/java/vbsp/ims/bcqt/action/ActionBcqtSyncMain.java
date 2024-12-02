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
import java.util.List;
import vbsp.ims.bcqt.dao.DaoBcqtMain;
import vbsp.ims.bcqt.dao.DaoSyncMain;
import vbsp.ims.bcqt.dao.TmDao;
import vbsp.ims.dao.DaoConnect;
import vbsp.ims.define.Define;
import vbsp.ims.leavelocal.LeaveHomeService;
import vbsp.ims.log.CoreLogger;
import vbsp.ims.restapi.ListMainPos;

/**
 *
 * @author BAOANH
 */
public class ActionBcqtSyncMain extends ActionBcqtMain {

    //<editor-fold defaultstate="collapsed" desc="Khai bao bien">
    private String macn;
    private String ngay_bc;
     private List<ListMainPos> lstCN_API;
    private String tt_khoa; //Trang thai khoa là OK: đã gửi số liệu, SEND: Đã xác nhận số liệu
//</editor-fold>

    public List<ListMainPos> getLstCN_API() {
        return lstCN_API;
    }

    public void setLstCN_API(List<ListMainPos> lstCN_API) {
        this.lstCN_API = lstCN_API;
    }

    //<editor-fold defaultstate="collapsed" desc="Phan xu ly chinh">
    public String LoadParaBcqt_unlock() {
        try {
            System.err.println("khoa_bcqt=" + khoa_bcqt);
            if (!getParaSession()) {
                return ERROR;
            }
            if (khoa_bcqt == null) {
                setKhoa_bcqt("ALL");
            } 
            if (khoa_bcqt.equals("BCQT_MS11C") || khoa_bcqt.equals("BCQT_MS18B")||khoa_bcqt.equals("BCQT_MS19A")) {
                _server_tmp = new LeaveHomeService();
                lstCN_API = _server_tmp.getListCn("");
                return "BCQT_MS11C";
            }else {
                if (khoa_bcqt.isEmpty()) {
                    setKhoa_bcqt("ALL");
                }
            }
            Connection conn = new DaoConnect().getConnect();
            DaoBcqtMain daoMain = new DaoBcqtMain();

            lstBcqtParams = daoMain.getReportParmams(conn, khoa_bcqt);
            lstParameters = DaoSyncMain.newInstance().getMainPosLock(conn);
            // BO SUNG PHAN KIEM TRA XEM CO THUYET MINH HAY KO
            TmDao tmDao = new TmDao();
            isDisplayTM = tmDao.getCO_TM(khoa_bcqt);
            if (conn != null) {
                conn.close();
            }
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> LoadParaBcqt_unlock: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> LoadParaBcqt_unlock: " + e.getMessage());
        }
        return SUCCESS;
    }

    public String LoadStatusSendCn() {
        try {
            if (!getParaSession()) {
                return ERROR;
            }
            DaoSyncMain daosync = DaoSyncMain.newInstance();
            //Kiem tra xem cac pgd da du du lieu chua neu du moi cho xac nhan so lieu

            HashMap hmParameter = getParameter();
            lstDulieuNt = daosync.getStatusSendCn(getType_bcqt(), hmParameter.get("khoa_bcqt").toString(),
                    macn, hmParameter.get("ngay_bc").toString(), 
                    hmParameter.get("khoa_bcqt").toString().equals("ALL")?tt_khoa:Define.WEB_SERVICES_STATUS_SEND);

        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> LoadStatusSendCn: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> LoadStatusSendCn: " + e.getMessage());
            addActionError("Bạn chưa tải được dữ liệu xin liên hệ với quản trị để được khắc phục");
            return ERROR;
        }
//        addActionMessage("Bạn gửi dữ liệu về trung ương thành công !");
        return SUCCESS;
    }

    public String OpenPgd() {
        try {
            if (!getParaSession()) {
                return ERROR;
            }
            DaoSyncMain daosync = DaoSyncMain.newInstance();
            //Kiem tra xem cac pgd da du du lieu chua neu du moi cho xac nhan so lieu

            HashMap hmParameter = getParameter();

            setKhoa_bcqt(hmParameter.get("khoa_bcqt").toString());
            setType_bcqt(hmParameter.get("type_bcqt").toString());
            setMacn(hmParameter.get("macn").toString());
            setNgay_bc(hmParameter.get("ngay_bc").toString());
//            (String type, String khoa,List<String> lstMapgd,  String ngaybc, String tt_khoa,  String username,  String grade)
            if (!daosync.setStatusLock(getType_bcqt(), khoa_bcqt, poscd, hmParameter.get("ngay_bc").toString(),
                    Define.WEB_SERVICES_STATUS_SEND, UserName, Grade)) {
                addActionError("Lỗi !, Mở khóa bị lỗi xin liên hệ với quản trị để được khắc phục");
                return ERROR;
            }
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> LoadStatusSendCn: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> LoadStatusSendCn: " + e.getMessage());
            addActionError("Mở khóa bị lỗi xin liên hệ với quản trị để được khắc phục");
            return ERROR;
        }
        addActionMessage("Đã mở khóa thành công. Trường hợp chi nhánh chưa chốt sô liệu không cần phải mở khóa!");
        return SUCCESS;
    }

    public String LockPgd() {
        try {
            if (!getParaSession()) {
                return ERROR;
            }
            DaoSyncMain daosync = DaoSyncMain.newInstance();
            //Kiem tra xem cac pgd da du du lieu chua neu du moi cho xac nhan so lieu

            HashMap hmParameter = getParameter();

            setKhoa_bcqt(hmParameter.get("khoa_bcqt").toString());
            setType_bcqt(hmParameter.get("type_bcqt").toString());
            setMacn(hmParameter.get("macn").toString());
            setNgay_bc(hmParameter.get("ngay_bc").toString());

            if (!daosync.setStatusLock(getType_bcqt(), khoa_bcqt, poscd, hmParameter.get("ngay_bc").toString(),
                    Define.WEB_SERVICES_STATUS_OK, UserName, Grade)) {
                addActionError("Lỗi !, Khóa PGD bị lỗi xin liên hệ với quản trị để được khắc phục");
                return ERROR;
            }
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> LoadStatusSendCn: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> LoadStatusSendCn: " + e.getMessage());
            addActionError("Khóa PGD bị lỗi xin liên hệ với quản trị để được khắc phục");
            return ERROR;
        }
        addActionMessage("Khóa đơn vị thành công !");
        return SUCCESS;
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Phuong thuc get/set bien">
    public String getMacn() {
        return macn;
    }

    public void setMacn(String macn) {
        this.macn = macn;
    }

    public String getNgay_bc() {
        return ngay_bc;
    }

    public void setNgay_bc(String ngay_bc) {
        this.ngay_bc = ngay_bc;
    }
    public String getTt_khoa() {
        return tt_khoa;
    }

    public void setTt_khoa(String tt_khoa) {
        this.tt_khoa = tt_khoa;
    }
    
//</editor-fold>
}
