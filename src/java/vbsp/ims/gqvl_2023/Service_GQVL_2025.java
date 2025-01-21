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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import org.apache.struts2.ServletActionContext;
import vbsp.ims.bcqt.model.QT_DULIEU_NT;
import vbsp.ims.dao.DaoConnect;
import vbsp.ims.define.GenericResult;
import vbsp.ims.log.CoreLogger;
import vbsp.ims.nghiquyet11cp.DaoNghiquyet11cp;
import vbsp.ims.restapi.DuLieuNTRow;
import vbsp.ims.restapi.DuLieuNTRowX;
import vbsp.ims.restapi.DuLieuNTService;
import vbsp.ims.restapi.ListPosCode;
import vbsp.ims.restapi.LockSendModel;
import vbsp.ims.util.DateUtil;

/**
 *
 * @author ducanh
 */
public class Service_GQVL_2025 extends ActionNhaptaycnMain
        implements NhaptaycnFunction {

    Service_GQVL2023 _server;
    DaoNghiquyet11cp _serverlocal;
    private List<QT_DULIEU_NT> lstData;
    private List<DuLieuNTRow> lstData_Api;
    private List<ListPosCode> lstPGD_API;
    private List<LockSendModel> lstData_tmp;
    protected String main_pos_username;
    private InputStream pageResult;
    DuLieuNTService _serverAPI = new DuLieuNTService();
    private String chotsl;
    private String txtGetData;
    private String status;
    private String message;
    private String sCode;
    private String stype;
//<editor-fold defaultstate="collapsed" desc="khai báo get,set">

    public Service_GQVL2023 getServer() {
        return _server;
    }

    public void setServer(Service_GQVL2023 _server) {
        this._server = _server;
    }

    public DaoNghiquyet11cp getServerlocal() {
        return _serverlocal;
    }

    public void setServerlocal(DaoNghiquyet11cp _serverlocal) {
        this._serverlocal = _serverlocal;
    }

    public List<QT_DULIEU_NT> getLstData() {
        return lstData;
    }

    public void setLstData(List<QT_DULIEU_NT> lstData) {
        this.lstData = lstData;
    }

    public List<DuLieuNTRow> getLstData_Api() {
        return lstData_Api;
    }

    public void setLstData_Api(List<DuLieuNTRow> lstData_Api) {
        this.lstData_Api = lstData_Api;
    }

    public List<ListPosCode> getLstPGD_API() {
        return lstPGD_API;
    }

    public void setLstPGD_API(List<ListPosCode> lstPGD_API) {
        this.lstPGD_API = lstPGD_API;
    }

    public List<LockSendModel> getLstData_tmp() {
        return lstData_tmp;
    }

    public void setLstData_tmp(List<LockSendModel> lstData_tmp) {
        this.lstData_tmp = lstData_tmp;
    }

    public String getMain_pos_username() {
        return main_pos_username;
    }

    public void setMain_pos_username(String main_pos_username) {
        this.main_pos_username = main_pos_username;
    }

    public InputStream getPageResult() {
        return pageResult;
    }

    public void setPageResult(InputStream pageResult) {
        this.pageResult = pageResult;
    }

    public DuLieuNTService getServerAPI() {
        return _serverAPI;
    }

    public void setServerAPI(DuLieuNTService _serverAPI) {
        this._serverAPI = _serverAPI;
    }

    public String getChotsl() {
        return chotsl;
    }

    public void setChotsl(String chotsl) {
        this.chotsl = chotsl;
    }

    public String getTxtGetData() {
        return txtGetData;
    }

    public void setTxtGetData(String txtGetData) {
        this.txtGetData = txtGetData;
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

    public String getsCode() {
        return sCode;
    }

    public void setsCode(String sCode) {
        this.sCode = sCode;
    }

    public String getStype() {
        return stype;
    }

    public void setStype(String stype) {
        this.stype = stype;
    }

//</editor-fold>
    @Override
    public String load() {
        switch (Grade) {
            case "1":
                return load_c1();
            case "2":
                return load_c2();
            default:
                return load_c3();
        }
    }

    public String load_c1() {
        try {
            if (!getParaSession()) {
                return ERROR;
            }
            HashMap hmParameter = getParameter();
            posMainModel = listKTNBDA.get_pos_main_pos(UserName, Grade);
            pos_cd_username = posMainModel.getPosCd();
            main_pos_username = posMainModel.getMainPosCd();
            String dateStr = hmParameter.get("ngay_bc").toString();
//            String txtGetData = hmParameter.get("txtGetData").toString();
            final String _reportDate = new SimpleDateFormat("yyyyMMdd").format(new SimpleDateFormat("dd-MMM-yyyy").parse(dateStr));
            ArrayList<LockSendModel> lstData_tmp = _serverAPI.getDataLockManual("GQVL_2024", pos_cd_username, "S", _reportDate);
            try {
                setChotsl(lstData_tmp.get(0).getStatus());
            } catch (Exception e) {
                setChotsl("0");
            }

            lstData_Api = _serverAPI.getData_condition("GQVL_2024", pos_cd_username, "S", _reportDate, "");

            if (lstData_Api == null || lstData_Api.isEmpty()) {
                addActionError("Chưa có dữ liệu!");
                return ERROR;
            }
            for (DuLieuNTRow item : lstData_Api) {
                QT_DULIEU_NT row = new QT_DULIEU_NT();
                try {
                    row.setKHOA(item.getKey());
                    row.setTHUTU(Integer.parseInt(item.getOrderValue()));
                    row.setTT_HIENTHI(item.getOrderDescription());
                    row.setMA(item.getCode());
                    row.setTEN(item.getName());
                    row.setCO_TONGHOP(item.getPosFlag());
                    row.setNGUOI_NHAP(item.getMakerId());
                    Date reportDate = DateUtil.toDate(item.getReportDate());
                    row.setNGAYBC(reportDate);
                    row.setNAMBC(item.getReportYear());
                    row.setMAPGD(item.getPosCode());
                    row.setMACN(item.getBranchCode());
                    row.setD1(item.getD1());
                    row.setD2(item.getD2());
                    row.setD3(item.getD3());
                    row.setD4(item.getD4());
                    row.setD5(item.getD5());
                    row.setD6(item.getD6());
                    row.setD7(item.getD7());
                    row.setD8(item.getD8());
                    row.setD9(item.getD9());
                    row.setD10(item.getD10());
                    row.setD11(item.getD11());
                    row.setD12(item.getD12());
                    row.setD13(item.getD13());
                    row.setD14(item.getD14());
                    row.setD15(item.getD15());
                    row.setD16(item.getD16());
                    row.setD17(item.getD17());
                    row.setD18(item.getD18());
                    row.setD19(item.getD19());
                    row.setD20(item.getD20());
                    row.setD21(item.getD21());
                    row.setD22(item.getD22());
                    row.setD23(item.getD23());
                    row.setD24(item.getD24());
                    row.setD25(item.getD25());
                    row.setD26(item.getD26());
                    row.setD27(item.getD27());
                    row.setD28(item.getD28());
                    row.setD29(item.getD29());
                    row.setD30(item.getD30());
                    row.setD31(item.getD31());
                    row.setD32(item.getD32());
                    row.setD33(item.getD33());
                    row.setD34(item.getD34());
                    row.setD35(item.getD35());
                    lstDulieuNt.add(row);
                } catch (Exception e) {
                }
            }
//            lstDulieuNt.sort(Comparator.comparingInt((QT_DULIEU_NT obj) -> Integer.parseInt(obj.getD1().toString())));
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> thong tin ca nhan sms : " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> thong tin ca nhan sms: " + e.getMessage());
        }
        return "success_1";
    }

    public String load_c2() {
        try {
            if (!getParaSession()) {
                return ERROR;
            }
            HashMap hmParameter = getParameter();
            posMainModel = listKTNBDA.get_pos_main_pos(UserName, Grade);
            pos_cd_username = posMainModel.getPosCd();
            main_pos_username = posMainModel.getMainPosCd();
            String dateStr = hmParameter.get("ngay_bc").toString();
            final String _reportDate = new SimpleDateFormat("yyyyMMdd").format(new SimpleDateFormat("dd-MMM-yyyy").parse(dateStr));
            ActionContext.getContext().getSession().put("sUserName", UserName);
            ActionContext.getContext().getSession().put("_reportDate", _reportDate);
            ArrayList<LockSendModel> lstData_tmp = _serverAPI.getDataLockManual("GQVL_2024", main_pos_username, "M", _reportDate);
            try {
                setChotsl(lstData_tmp.get(0).getStatus());
            } catch (Exception e) {
                setChotsl("0");
            }
            lstPGD_API = _serverAPI.getListPgd(main_pos_username, "");
            for (ListPosCode item : lstPGD_API) {
                lstData_Api = _serverAPI.getData_condition("GQVL_2024", item.getPosCode(), "S", _reportDate, "");

                if (lstData_Api == null || lstData_Api.isEmpty()) {
                    continue;
                }
                for (DuLieuNTRow itemApi : lstData_Api) {
                    QT_DULIEU_NT row = new QT_DULIEU_NT();
                    try {
                        row.setKHOA(itemApi.getKey());
                        row.setTHUTU(Integer.parseInt(itemApi.getOrderValue()));
                        row.setTT_HIENTHI(itemApi.getOrderDescription());
                        row.setMA(itemApi.getCode());
                        row.setTEN(itemApi.getName());
                        row.setCO_TONGHOP(itemApi.getPosFlag());
                        row.setNGUOI_NHAP(itemApi.getMakerId());

                        Date reportDate = DateUtil.toDate(itemApi.getReportDate());
                        row.setNGAYBC(reportDate);
                        row.setNAMBC(itemApi.getReportYear());
                        row.setMAPGD(itemApi.getPosCode());
                        row.setMACN(itemApi.getBranchCode());

                        // Set các giá trị D1 - D35
                        row.setD1(itemApi.getD1());
                        row.setD2(itemApi.getD2());
                        row.setD3(itemApi.getD3());
                        row.setD4(itemApi.getD4());
                        row.setD5(itemApi.getD5());
                        row.setD6(itemApi.getD6());
                        row.setD7(itemApi.getD7());
                        row.setD8(itemApi.getD8());
                        row.setD9(itemApi.getD9());
                        row.setD10(itemApi.getD10());
                        row.setD11(itemApi.getD11());
                        row.setD12(itemApi.getD12());
                        row.setD13(itemApi.getD13());
                        row.setD14(itemApi.getD14());
                        row.setD15(itemApi.getD15());
                        row.setD16(itemApi.getD16());
                        row.setD17(itemApi.getD17());
                        row.setD18(itemApi.getD18());
                        row.setD19(itemApi.getD19());
                        row.setD20(itemApi.getD20());
                        row.setD21(itemApi.getD21());
                        row.setD22(itemApi.getD22());
                        row.setD23(itemApi.getD23());
                        row.setD24(itemApi.getD24());
                        row.setD25(itemApi.getD25());
                        row.setD26(itemApi.getD26());
                        row.setD27(itemApi.getD27());
                        row.setD28(itemApi.getD28());
                        row.setD29(itemApi.getD29());
                        row.setD30(itemApi.getD30());
                        row.setD31(itemApi.getD31());
                        row.setD32(itemApi.getD32());
                        row.setD33(itemApi.getD33());
                        row.setD34(itemApi.getD34());
                        ArrayList<LockSendModel> lstData_tmp1 = _serverAPI.getDataLockManual("GQVL_2024", itemApi.getPosCode(), "S", _reportDate);
                        try {
                            row.setD35(lstData_tmp1.get(0).getStatus());
                        } catch (Exception e) {
                            row.setD35("0");
                        }

                        lstDulieuNt.add(row);
                    } catch (Exception e) {
                        System.err.println("Lỗi khi xử lý mục API: " + e.getMessage());
                    }
                }
            }

            if (lstDulieuNt.isEmpty()) {
                addActionError("Chưa có dữ liệu hợp lệ!");
                return ERROR;
            }

        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> thong tin ca nhan sms : " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> thong tin ca nhan sms: " + e.getMessage());
        }
        return "success_2";
    }

    public String load_c3() {
        try {
            if (!getParaSession()) {
                return ERROR;
            }
            HashMap hmParameter = getParameter();
            String sngaybc = hmParameter.get("ngay_bc").toString();
            String smacn = hmParameter.get("lstCN").toString();
            setMacn(smacn);
            Connection conn = new DaoConnect().getConnect();
            DaoNghiquyet11cp daoMain = new DaoNghiquyet11cp();
            ActionContext.getContext().getSession().put("sUserName", UserName);
            ActionContext.getContext().getSession().put("sngaybc", sngaybc);
            lstDulieuNt = daoMain.getData_GQVL_2023(conn, sngaybc, "GQVL_2024", UserName, smacn, "S");

//            System.out.println(sngaybc + " " + smacn +" " +Pos_Flag);
            if (conn != null) {
                conn.close();
            }

        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> gdx: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> gdx: " + e.getMessage());
        }
        return "success_3";
    }

    @Override
    public String save() {
        System.out.println("vao váe");
        try {
            if (!getParaSession()) {
                return ERROR;
            }
            HashMap hmParameter = getParameter();
            String dateStr = hmParameter.get("ngay_bc").toString();
            String[] values1 = dateStr.split("\\-");
            String snambc = values1[2];
            posMainModel = listKTNBDA.get_pos_main_pos(UserName, Grade);
            pos_cd_username = posMainModel.getPosCd();
            main_pos_username = posMainModel.getMainPosCd();
            final String _reportDate = new SimpleDateFormat("yyyyMMdd").format(new SimpleDateFormat("dd-MMM-yyyy").parse(dateStr));
            final String _reportDate1 = new SimpleDateFormat("yyyy-MM-dd'T'00:00:00.000").format(new SimpleDateFormat("yyyyMMdd").parse(_reportDate));

            ArrayList<DuLieuNTRowX> lstUpdateDate = new ArrayList<>();
            for (QT_DULIEU_NT tmp : lstDulieuNt) {
                int Stt = 0;
                ++Stt;
                DuLieuNTRowX tempadd = new DuLieuNTRowX();
                tempadd.setKey("GQVL_2024");
                tempadd.setOrderValue(tmp.getTHUTU());
                tempadd.setOrderDescription(tmp.getTT_HIENTHI());
                tempadd.setName(tmp.getTEN());
                tempadd.setCode(tmp.getMA());
                tempadd.setMakerId(UserName);
                tempadd.setMakerDate(_reportDate1);
                tempadd.setAuthoriseId(UserName);
                tempadd.setAuthoriseDate(_reportDate1);
                tempadd.setReportDate(_reportDate1);
                tempadd.setReportYear(Integer.valueOf(snambc));
                tempadd.setPosCode(pos_cd_username);
                tempadd.setPosFlag("S");
                tempadd.setBranchCode(main_pos_username);
                tempadd.setD1(tmp.getD1());
                tempadd.setD2(tmp.getD2());
                tempadd.setD3(tmp.getD3());
                tempadd.setD4(tmp.getD4());
                tempadd.setD5(tmp.getD5());
                tempadd.setD6(tmp.getD6());
                tempadd.setD7(tmp.getD7());
                tempadd.setD8(tmp.getD8());
                tempadd.setD9(tmp.getD9());
                tempadd.setD10(tmp.getD10());
                tempadd.setD11(tmp.getD11());
                tempadd.setD12(tmp.getD12());
                tempadd.setD13(tmp.getD13());
                tempadd.setD14(tmp.getD14());
                tempadd.setD15(tmp.getD15());
                tempadd.setD16(tmp.getD16());
                tempadd.setD17(tmp.getD17());
                tempadd.setD18(tmp.getD18());
                tempadd.setD19(tmp.getD19());
                tempadd.setD20(tmp.getD20());
                tempadd.setD21(tmp.getD21());
                tempadd.setD22(tmp.getD22());
                tempadd.setD23(tmp.getD23());
                tempadd.setD24(tmp.getD24());
                tempadd.setD25(tmp.getD25());
                tempadd.setD26(tmp.getD26());
                tempadd.setD27(tmp.getD27());
                tempadd.setD28(tmp.getD28());
                tempadd.setD29(tmp.getD29());
                tempadd.setD30(tmp.getD30());
                tempadd.setD31(tmp.getD31());
                tempadd.setD32(tmp.getD32());
                tempadd.setD33(tmp.getD33());
                tempadd.setD34(tmp.getD34());
                tempadd.setD35(tmp.getD35());

                lstUpdateDate.add(tempadd);

            }

            _serverAPI = new DuLieuNTService();
            int status = _serverAPI.getGQVL2023("GQVL_2024", pos_cd_username, "S", _reportDate, "", "", lstUpdateDate);
            if (status == 200) {
                String code = String.valueOf(status);
                this.pageResult = new ByteArrayInputStream(code.getBytes(StandardCharsets.UTF_8));
                return SUCCESS;
            }
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> giai quyet viec lam: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> giai quyet viec lam: " + e.getMessage());
        }
        String code = String.valueOf(200);
        this.pageResult = new ByteArrayInputStream(code.getBytes(StandardCharsets.UTF_8));
        return SUCCESS;
    }

    public String send() {
        try {
            if (!getParaSession()) {
                return ERROR;
            }
            HashMap hmParameter = getParameter();
            String dateStr = hmParameter.get("ngay_bc").toString();
            String[] values1 = dateStr.split("\\-");
            String snambc = values1[2];
            posMainModel = listKTNBDA.get_pos_main_pos(UserName, Grade);
            pos_cd_username = posMainModel.getPosCd();
            main_pos_username = posMainModel.getMainPosCd();
            final String _reportDate = new SimpleDateFormat("yyyyMMdd").format(new SimpleDateFormat("dd-MMM-yyyy").parse(dateStr));
            int table = lstDulieuNt.size();
            String mapgd;
            String co_tonghop;
            if (Grade.equals("1")) {
                mapgd = pos_cd_username;
                co_tonghop = "S";
                lstData_Api = _serverAPI.getData_condition("GQVL_2024", mapgd, co_tonghop, _reportDate, "");
                int tableapi = lstData_Api.size();
                if (table != tableapi) {
                    String code = "1";
                    this.pageResult = new ByteArrayInputStream(code.getBytes(StandardCharsets.UTF_8));
                    return SUCCESS;
                }
            } else {
                mapgd = main_pos_username;
                co_tonghop = "M";
            }
            int status = _serverAPI.updateChotSL("GQVL_2024", mapgd, co_tonghop, _reportDate, Grade, UserName, null);
            if (status == 200) {
                String code = String.valueOf(status);
                this.pageResult = new ByteArrayInputStream(code.getBytes(StandardCharsets.UTF_8));
                return SUCCESS;
            }
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> thongtincanhan: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> thongtincanhan: " + e.getMessage());
            String code = String.valueOf(status);
            this.pageResult = new ByteArrayInputStream(code.getBytes(StandardCharsets.UTF_8));
            return SUCCESS;
        }
        String code = String.valueOf(200);
        this.pageResult = new ByteArrayInputStream(code.getBytes(StandardCharsets.UTF_8));
        return SUCCESS;
    }

    public String unlock_c2() {
        try {
            String D1 = ServletActionContext.getRequest().getParameter("madiemgd");
            String D5 = ServletActionContext.getRequest().getParameter("ngaybc");
            String D6 = ServletActionContext.getRequest().getParameter("pos_flag");
            UserName = (String) ActionContext.getContext().getSession().get("sUserName");
            String _reportDate = (String) ActionContext.getContext().getSession().get("_reportDate");
            int status = _serverAPI.updateChotSL("GQVL_2024", D1, "S", _reportDate, "0", UserName, null);;
            if (status != 200) {
                String code = String.valueOf(1);
                this.pageResult = new ByteArrayInputStream(code.getBytes(StandardCharsets.UTF_8));
                return ERROR;
            }
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> unlock_c2: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> unlock_c2: " + e.getMessage());
        }

        addActionMessage("Mở dữ liệu thành công");
        String code = String.valueOf(200);
        this.pageResult = new ByteArrayInputStream(code.getBytes(StandardCharsets.UTF_8));
        return SUCCESS;
    }

    public String unlock_c3() {
        try {
            String D1 = ServletActionContext.getRequest().getParameter("madiemgd");
            String UserName = (String) ActionContext.getContext().getSession().get("sUserName");
            String D5 = (String) ActionContext.getContext().getSession().get("sngaybc");
            DaoNghiquyet11cp daoMain = new DaoNghiquyet11cp();
//            System.out.println("D1== " + D1 + " = " + D5 + " = " + UserName);
            GenericResult<String> _result = daoMain.unlock_c3_THTK("GQVL_2024", D1, "M", D5, UserName);

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

    public String status() {
        try {
            System.out.println("vbsp.ims.gqvl_2023.Service_TTCN_2024.status()");
            String D1 = ServletActionContext.getRequest().getParameter("madiemgd");
            String D7 = ServletActionContext.getRequest().getParameter("ngaybc");
            String D8 = ServletActionContext.getRequest().getParameter("type");
            String D9 = ServletActionContext.getRequest().getParameter("skhoa");
//            System.out.println("D1= " + D1 + " D7= " + D7 + " D8= " + D8 + " D9= " + D9);
            int status;
            if (D8.equals("1")) {
                status = _serverAPI.updateChotSL(D9, D1, "S", D7, "0", UserName, null);
            } else {
                status = _serverAPI.updateChotSL(D9, D1, "S", D7, "2", UserName, null);
            }
            if (status != 200) {
                String code = String.valueOf(1);
                this.pageResult = new ByteArrayInputStream(code.getBytes(StandardCharsets.UTF_8));
                return ERROR;
            }
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> unlock_c2: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> unlock_c2: " + e.getMessage());
        }
        String code = String.valueOf(200);
        this.pageResult = new ByteArrayInputStream(code.getBytes(StandardCharsets.UTF_8));
        return SUCCESS;
    }
}
