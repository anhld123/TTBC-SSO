/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.gqvl_2023;

import static com.opensymphony.xwork2.Action.ERROR;
import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionContext;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import vbsp.ims.nhaptaycn.action.*;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import org.apache.struts2.ServletActionContext;
import vbsp.ims.action.ktktnb.DULIEU_NT_TQ;
import vbsp.ims.bcqt.model.QT_DULIEU_NT;
import vbsp.ims.dao.DaoConnect;
import vbsp.ims.define.GenericResult;
import vbsp.ims.log.CoreLogger;
import vbsp.ims.nghiquyet11cp.DaoNghiquyet11cp;
import vbsp.ims.restapi.DuLieuNTRow;
import vbsp.ims.restapi.DuLieuNTService;
import vbsp.ims.restapi.ListMainPos;
import vbsp.ims.restapi.ListPosCode;
import vbsp.ims.restapi.LockSendModel;
import vbsp.ims.util.DateUtil;

/**
 *
 * @author ducanh
 */
public class Thidua_khenthuong_2024 extends ActionNhaptaycnMain
        implements NhaptaycnFunction {

    Service_GQVL2023 _server;
    private List<QT_DULIEU_NT> lstData;
    private List<DULIEU_NT_TQ> lstData50;
    private List<DuLieuNTRow> lstData_Api;
    private List<ListPosCode> lstPGD_API;
    private List<ListMainPos> lstCN_API;
    protected String main_pos_username;
    private InputStream pageResult;
    DuLieuNTService _serverAPI = new DuLieuNTService();
    private String chotsl;
    private String txtGetData;
    private String status;
    private String message;
    private String check_Username;
    private String sngaybc;
//<editor-fold defaultstate="collapsed" desc="khai báo get,set">

    public List<DULIEU_NT_TQ> getLstData50() {
        return lstData50;
    }

    public void setLstData50(List<DULIEU_NT_TQ> lstData50) {
        this.lstData50 = lstData50;
    }

    public String getSngaybc() {
        return sngaybc;
    }

    public void setSngaybc(String sngaybc) {
        this.sngaybc = sngaybc;
    }

    public List<ListMainPos> getLstCN_API() {
        return lstCN_API;
    }

    public void setLstCN_API(List<ListMainPos> lstCN_API) {
        this.lstCN_API = lstCN_API;
    }

    public String getCheck_Username() {
        return check_Username;
    }

    public void setCheck_Username(String check_Username) {
        this.check_Username = check_Username;
    }

    public List<ListPosCode> getLstPGD_API() {
        return lstPGD_API;
    }

    public void setLstPGD_API(List<ListPosCode> lstPGD_API) {
        this.lstPGD_API = lstPGD_API;
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

    public String getTxtGetData() {
        return txtGetData;
    }

    public void setTxtGetData(String txtGetData) {
        this.txtGetData = txtGetData;
    }

    public String getChotsl() {
        return chotsl;
    }

    public void setChotsl(String chotsl) {
        this.chotsl = chotsl;
    }

    public DuLieuNTService getServerAPI() {
        return _serverAPI;
    }

    public void setServerAPI(DuLieuNTService _serverAPI) {
        this._serverAPI = _serverAPI;
    }

    public InputStream getPageResult() {
        return pageResult;
    }

    public void setPageResult(InputStream pageResult) {
        this.pageResult = pageResult;
    }

    public Service_GQVL2023 getServer() {
        return _server;
    }

    public void setServer(Service_GQVL2023 _server) {
        this._server = _server;
    }

    public List<QT_DULIEU_NT> getLstData() {
        return lstData;
    }

    public void setLstData(List<QT_DULIEU_NT> lstData) {
        this.lstData = lstData;
    }

    public String getMain_pos_username() {
        return main_pos_username;
    }

    public void setMain_pos_username(String main_pos_username) {
        this.main_pos_username = main_pos_username;
    }
//</editor-fold>

    @Override
    public String load() {
        try {
            if (!getParaSession()) {
                return ERROR;
            }

            HashMap<String, Object> hmParameter = getParameter();
            Connection conn = new DaoConnect().getConnect();
            posMainModel = listKTNBDA.get_pos_main_pos(UserName, Grade);
            pos_cd_username = posMainModel.getPosCd();
            main_pos_username = posMainModel.getMainPosCd();

            String dateStr = hmParameter.get("ngay_bc").toString();
            if (!Grade.equals("3")) {
                addActionError("Chương trình cấp TW");
                return ERROR;
            }
            setSngaybc(dateStr);
            final String _reportDate = new SimpleDateFormat("yyyyMMdd").format(new SimpleDateFormat("dd-MMM-yyyy").parse(dateStr));
            DaoNghiquyet11cp daoMain = new DaoNghiquyet11cp();
            lstData = daoMain.getCheck_user(conn, UserName);

            try {
                setCheck_Username(lstData.get(0).getD2());
            } catch (Exception e) {
                setCheck_Username("0");
            }
            ArrayList<LockSendModel> lstData_tmp = _serverAPI.getDataLockManual("TDKT_02", "000100", "H", _reportDate);
            try {
                setChotsl(lstData_tmp.get(0).getStatus());
            } catch (Exception e) {
                setChotsl("0");
            }
            if (lstData_tmp == null || lstData_tmp.isEmpty()) {
                int createChot = _serverAPI.updateChotSL("TDKT_02", "000100", "H", _reportDate, "0", "ANHLD", null);
            }
            ActionContext.getContext().getSession().put("check_Username", check_Username);
            if (Arrays.asList("USRGRP08", "USRGRP49", "USRGRP23", "USRGRP24", "USRGRP15", "USRGRP18", "USRGRP19").contains(check_Username)) {

                lstDulieuNt = daoMain.getTDKT_2024(conn, dateStr, "TDKT_02", "000100");

            } else {
                addActionError("User không có quyền sử dụng chương trình");
                return ERROR;
            }

            return "success";
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> load method: " + e.getMessage());
            return ERROR;
        }
    }

    @Override
    public String save() {
        System.out.println("vao váe");
        try {
            if (!getParaSession()) {
                return ERROR;
            }
            if (lstDulieuNt == null || lstDulieuNt.isEmpty()) {
                addActionError("Bạn chưa lưu được báo cáo xin liên hệ với quản trị để khắc phục");
                return ERROR;
            }
            check_Username = (String) ActionContext.getContext().getSession().get("check_Username");
            DaoNghiquyet11cp daoMain = new DaoNghiquyet11cp();
            HashMap hmParameter = getParameter();
            String dateStr = hmParameter.get("ngay_bc").toString();
            final String _reportDate = new SimpleDateFormat("yyyyMMdd").format(new SimpleDateFormat("dd-MMM-yyyy").parse(dateStr));
            final String _reportDate1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS").format(new Date());
            posMainModel = listKTNBDA.get_pos_main_pos(UserName, Grade);
            pos_cd_username = posMainModel.getPosCd();
            main_pos_username = posMainModel.getMainPosCd();
            ArrayList<DULIEU_NT_TQ> lstLocalDataUpdate = new ArrayList<>();
            for (QT_DULIEU_NT tmp : lstDulieuNt) {
                DULIEU_NT_TQ temlocal = new DULIEU_NT_TQ();
                temlocal.setKHOA("TDKT_02");
                temlocal.setTHUTU(tmp.getTHUTU());
                temlocal.setTT_HIENTHI(tmp.getTT_HIENTHI());
                temlocal.setTEN(tmp.getTEN());
                temlocal.setMA(tmp.getMA().equals("") ? tmp.getMAPGD() : tmp.getMA());
                temlocal.setNGUOI_DUYET(UserName);
                temlocal.setNGUOI_NHAP(UserName);
                temlocal.setNAMBC(tmp.getNAMBC());
                temlocal.setMAPGD(tmp.getMAPGD());
                temlocal.setCO_TONGHOP(tmp.getCO_TONGHOP());
                temlocal.setMACN(tmp.getMACN());
                temlocal.setD1(tmp.getD1());
                temlocal.setD2(tmp.getD2());
                temlocal.setD3(tmp.getD3());
                temlocal.setD4(tmp.getD4());
                temlocal.setD5(tmp.getD5());
                temlocal.setD6(tmp.getD6());
                temlocal.setD7(tmp.getD7());
                temlocal.setD8(tmp.getD8());
                temlocal.setD9(tmp.getD9());
                temlocal.setD10(tmp.getD10());
                temlocal.setD11(tmp.getD11());
                temlocal.setD12(tmp.getD12());
                temlocal.setD13(tmp.getD13());
                temlocal.setD14(tmp.getD14());
                temlocal.setD15(tmp.getD15());
                temlocal.setD16(tmp.getD16());
                temlocal.setD17(tmp.getD17());
                temlocal.setD18(tmp.getD18());
                temlocal.setD19(tmp.getD19());
                temlocal.setD20(tmp.getD20());
                if (check_Username.equals("USRGRP49")) {
                    temlocal.setD21(UserName);
                    temlocal.setD22(_reportDate1);
                } else {
                    temlocal.setD21(tmp.getD21());
                    temlocal.setD22(tmp.getD22());
                }
                if (check_Username.equals("USRGRP21")) {
                    temlocal.setD23(UserName);
                    temlocal.setD24(_reportDate1);
                } else {
                    temlocal.setD23(tmp.getD23());
                    temlocal.setD24(tmp.getD24());
                }
                if (check_Username.equals("USRGRP23")) {
                    temlocal.setD25(UserName);
                    temlocal.setD26(_reportDate1);
                } else {
                    temlocal.setD25(tmp.getD25());
                    temlocal.setD26(tmp.getD26());
                }
                if (check_Username.equals("USRGRP24")) {
                    temlocal.setD27(UserName);
                    temlocal.setD28(_reportDate1);
                } else {
                    temlocal.setD27(tmp.getD27());
                    temlocal.setD28(tmp.getD28());
                }
                if (check_Username.equals("USRGRP19")) {
                    temlocal.setD29(UserName);
                    temlocal.setD30(_reportDate1);
                } else {
                    temlocal.setD29(tmp.getD29());
                    temlocal.setD30(tmp.getD30());
                }
                if (check_Username.equals("USRGRP15")) {
                    temlocal.setD31(UserName);
                    temlocal.setD32(_reportDate1);
                } else {
                    temlocal.setD31(tmp.getD31());
                    temlocal.setD32(tmp.getD32());
                }
                if (check_Username.equals("USRGRP18")) {
                    temlocal.setD33(UserName);
                    temlocal.setD34(_reportDate1);
                } else {
                    temlocal.setD33(tmp.getD33());
                    temlocal.setD34(tmp.getD34());
                }
                temlocal.setD35(tmp.getD35());
                temlocal.setD36(tmp.getD36());
                temlocal.setD37(tmp.getD37());
                temlocal.setD38(tmp.getD38());
                temlocal.setD39(tmp.getD39());
                temlocal.setD40(tmp.getD40());
                temlocal.setD41(tmp.getD41());
                temlocal.setD42(tmp.getD42());
                temlocal.setD43(tmp.getD43());
                temlocal.setD44(tmp.getD44());
                temlocal.setD45(tmp.getD45());
                temlocal.setD46(tmp.getD46());
                temlocal.setD47(tmp.getD47());
                temlocal.setD48(tmp.getD48());
                temlocal.setD49(tmp.getD49());
                temlocal.setD50(tmp.getD50());
                lstLocalDataUpdate.add(temlocal);
            }
            int status = 200;
            if (status == 200) {
                if (!daoMain.save_TDTK_2024("TDKT_02", UserName, dateStr, lstLocalDataUpdate)) {
                    addActionError("Bạn chưa lưu được báo cáo tại chi nhánh vui lòng liên hệ quản trị viên!");
                    String code = String.valueOf(2);
                    this.pageResult = new ByteArrayInputStream(code.getBytes(StandardCharsets.UTF_8));
                    return ERROR;

                }
            }
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> muats2024: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> muats2024: " + e.getMessage());
        }
        addActionMessage("Bạn đã lưu dữ liệu thành công");
        String code = String.valueOf(200);
        this.pageResult = new ByteArrayInputStream(code.getBytes(StandardCharsets.UTF_8));
        return SUCCESS;
    }

    public String lock() {
        try {
            String D1 = ServletActionContext.getRequest().getParameter("ssngaybc");
            DaoNghiquyet11cp daoMain = new DaoNghiquyet11cp();
            GenericResult<String> _result = daoMain.lock_TDKT_2024("TDKT_02", "000100", "H", D1);

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
