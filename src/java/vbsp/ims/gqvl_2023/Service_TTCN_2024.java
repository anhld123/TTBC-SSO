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
public class Service_TTCN_2024 extends ActionNhaptaycnMain
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
            String txtGetData = hmParameter.get("txtGetData").toString();
            final String _reportDate = new SimpleDateFormat("yyyyMMdd").format(new SimpleDateFormat("dd-MMM-yyyy").parse(dateStr));
            ArrayList<LockSendModel> lstData_tmp = _serverAPI.getDataLockManual("TTCN_01_" + txtGetData, pos_cd_username, "S", _reportDate);
            try {
                setChotsl(lstData_tmp.get(0).getStatus());
            } catch (Exception e) {
                setChotsl("0");
            }
            
            if (pos_cd_username.equals("000101")) {
                lstData_Api = _serverAPI.getData_condition("TTCN_01", pos_cd_username, "S", _reportDate, "");
            } else {
                String smaxa = hmParameter.get("maxa").toString();
                String smato = hmParameter.get("mato").toString();
                String[] values = smato.split("\\_");
                String ssmato = values[1];

                if (smaxa.equals("000000")) {
                    addActionError("Vui lòng nhập mã xã để rà soát số liệu!");
                    return ERROR;
                }

                String condition1 = "D13=" + smaxa + "|D15=" + txtGetData + "|";
                String condition = "D13=" + smaxa + "|D14=" + ssmato + "|D15=" + txtGetData + "|";
                if (ssmato.equals("0000000")) {
                    lstData_Api = _serverAPI.getData_condition("TTCN_01", pos_cd_username, "S", _reportDate, condition1);
                    if (lstData_Api.size() > 150) {
                        addActionError("Dữ liệu của xã quá lớn, vui lòng chọn từng tổ để tải dữ liệu!");
                        return ERROR;
                    }
                } else {
                    lstData_Api = _serverAPI.getData_condition("TTCN_01", pos_cd_username, "S", _reportDate, condition);
                }
            }
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
                    row.setD15(txtGetData);
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
            String txtGetData = hmParameter.get("txtGetData").toString();
            final String _reportDate = new SimpleDateFormat("yyyyMMdd").format(new SimpleDateFormat("dd-MMM-yyyy").parse(dateStr));
            lstPGD_API = _serverAPI.getListPgd(main_pos_username, "");

            if (lstPGD_API == null || lstPGD_API.isEmpty()) {
                addActionError("Lỗi khi gọi API!");
                return ERROR;
            }

            for (ListPosCode item : lstPGD_API) {
                QT_DULIEU_NT row = new QT_DULIEU_NT();
                try {
                    row.setD1(item.getPosCode());
                    row.setD2(item.getPosName());
                    row.setD3(null);
                    row.setD4("0");
                    row.setD5(null);
                    row.setD6(null);
                    row.setD7(_reportDate);
                    row.setD8(null);
                    row.setD9("TTCN_01_" + txtGetData);
                    lstData_tmp = _serverAPI.getDataLockManual("TTCN_01_" + txtGetData, item.getPosCode(), "S", _reportDate);

                    // If there are data rows to process
                    if (!lstData_tmp.isEmpty()) {
                        for (LockSendModel item_tmp : lstData_tmp) {
                            QT_DULIEU_NT dataRow = new QT_DULIEU_NT();
                            try {
                                dataRow.setD1(item.getPosCode());
                                dataRow.setD2(item.getPosName());
                                if (item_tmp.getUpdateDate() != null) {
                                    DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
                                    LocalDateTime dateTime = LocalDateTime.parse(item_tmp.getUpdateDate(), inputFormatter);
                                    DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                                    String formattedDate = dateTime.format(outputFormatter);
                                    dataRow.setD3(formattedDate);
                                }
                                dataRow.setD4(item_tmp.getStatus());
                                dataRow.setD5(item_tmp.getReportDate());
                                dataRow.setD6(item_tmp.getPosFlag());
                                dataRow.setD7(_reportDate);
                                dataRow.setD8(item_tmp.getUpdateId());
                                dataRow.setD9("TTCN_01_" + txtGetData);
                            } catch (Exception e) {
                                System.err.println("gọi api chốt dữ liệu lỗi: " + e.getMessage());
                            }
                            lstDulieuNt.add(dataRow);
                        }
                    } else {
                        lstDulieuNt.add(row);
                    }
                } catch (Exception e) {
                    System.err.println("Error processing posCode " + item.getPosCode() + ": " + e.getMessage());
                }
            }

        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> tin dung 2024: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> tin dung 2024: " + e.getMessage());
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
            Connection conn = new DaoConnect().getConnect();
            DaoNghiquyet11cp daoMain = new DaoNghiquyet11cp();
            ActionContext.getContext().getSession().put("sUserName", UserName);
            ActionContext.getContext().getSession().put("skhoa", "TTCN_01_" + txtGetData);
            if (smacn.equals("000000")) {
                addActionError("Vui lòng chọn mã chi nhánh!");
                return ERROR;
            }
//            System.out.println("txtGetData= "+ txtGetData);
            lstDulieuNt = daoMain.getData_THTK_c3(conn, sngaybc, "TTCN_01_" + txtGetData, smacn, "S");
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
            String txtGetData = hmParameter.get("txtGetData").toString();
            String smaxa = hmParameter.get("maxa").toString();
            String smato = hmParameter.get("mato").toString();
            String[] values = smato.split("\\_");
            String ssmato = values[1];
            final String _reportDate = new SimpleDateFormat("yyyyMMdd").format(new SimpleDateFormat("dd-MMM-yyyy").parse(dateStr));
            final String _reportDate1 = new SimpleDateFormat("yyyy-MM-dd'T'00:00:00.000").format(new SimpleDateFormat("yyyyMMdd").parse(_reportDate));

            ArrayList<DuLieuNTRowX> lstUpdateDate = new ArrayList<>();
            for (QT_DULIEU_NT tmp : lstDulieuNt) {
                int Stt = 0;
                ++Stt;
                DuLieuNTRowX tempadd = new DuLieuNTRowX();
                tempadd.setKey("TTCN_01");
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
                tempadd.setD13(smaxa);
                tempadd.setD14(ssmato);
                tempadd.setD15(txtGetData);
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
                lstUpdateDate.add(tempadd);

            }

            _serverAPI = new DuLieuNTService();
            int status = _serverAPI.getGQVL2023("TTCN_01", pos_cd_username, "S", _reportDate, "", "", lstUpdateDate);
            if (status == 200) {
                String code = String.valueOf(status);
                this.pageResult = new ByteArrayInputStream(code.getBytes(StandardCharsets.UTF_8));
                return SUCCESS;
            }
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> thong tin ca nhan sms: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> thong tin ca nhan sms: " + e.getMessage());
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
            if (lstDulieuNt == null || lstDulieuNt.isEmpty()) {
                addActionError("Bạn chưa lưu được báo cáo xin liên hệ với quản trị để khắc phục");
                return ERROR;
            }
            HashMap hmParameter = getParameter();
            String dateStr = hmParameter.get("ngay_bc").toString();
            String[] values1 = dateStr.split("\\-");
            String snambc = values1[2];
            posMainModel = listKTNBDA.get_pos_main_pos(UserName, Grade);
            pos_cd_username = posMainModel.getPosCd();
            main_pos_username = posMainModel.getMainPosCd();
            String txtGetData = hmParameter.get("txtGetData").toString();
            String smaxa = hmParameter.get("maxa").toString();
            String smato = hmParameter.get("mato").toString();
            String[] values = smato.split("\\_");
            String ssmato = values[1];
            String condition = "D13=" + smaxa + "|D14=" + ssmato + "|D15=" + txtGetData + "|";
            final String _reportDate = new SimpleDateFormat("yyyyMMdd").format(new SimpleDateFormat("dd-MMM-yyyy").parse(dateStr));
            int table = lstDulieuNt.size();
            lstData_Api = _serverAPI.getData_condition("TTCN_01", pos_cd_username, "S", _reportDate, condition);
            int tableapi = lstData_Api.size();
            if (table != tableapi) {
                String code = "1";
                this.pageResult = new ByteArrayInputStream(code.getBytes(StandardCharsets.UTF_8));
                return SUCCESS;
            }
            int status = _serverAPI.updateChotSL("TTCN_01_" + txtGetData, pos_cd_username, "S", _reportDate, "1", UserName, null);
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

    public String unlock_c3() {
        try {
            String D1 = ServletActionContext.getRequest().getParameter("madiemgd");
            String D5 = ServletActionContext.getRequest().getParameter("ngaybc");
            String D6 = ServletActionContext.getRequest().getParameter("pos_flag");
            String D7 = ServletActionContext.getRequest().getParameter("key_lock");
            String D8 = ServletActionContext.getRequest().getParameter("skhoa");
            String UserName = (String) ActionContext.getContext().getSession().get("sUserName");
            String skhoa = (String) ActionContext.getContext().getSession().get("skhoa");

            SimpleDateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat outputFormat = new SimpleDateFormat("dd/MMM/yyyy");
            Date date = inputFormat.parse(D5);
            String formattedDate = outputFormat.format(date);
            DaoNghiquyet11cp daoMain = new DaoNghiquyet11cp();
            GenericResult<String> _result = daoMain.unlock_c3_THTK(skhoa, D1, UserName, formattedDate, D7);

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
