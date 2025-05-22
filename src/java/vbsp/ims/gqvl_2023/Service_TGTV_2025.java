/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.gqvl_2023;

import static com.opensymphony.xwork2.Action.ERROR;
import static com.opensymphony.xwork2.Action.SUCCESS;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import vbsp.ims.nhaptaycn.action.*;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import org.apache.struts2.ServletActionContext;
import vbsp.ims.action.ktktnb.DULIEU_NT_TQ;
import vbsp.ims.bcqt.model.QT_DULIEU_NT;
import vbsp.ims.dao.DaoConnect;
import vbsp.ims.log.CoreLogger;
import vbsp.ims.nghiquyet11cp.DaoNghiquyet11cp;
import vbsp.ims.restapi.DuLieuNTRow;
import vbsp.ims.restapi.DuLieuNTRowX;
import vbsp.ims.restapi.DuLieuNTService;
import vbsp.ims.restapi.ListCommune;
import vbsp.ims.restapi.ListPosCode;
import vbsp.ims.restapi.LockSendModel;
import vbsp.ims.util.DateUtil;

/**
 *
 * @author ducanh
 */
public class Service_TGTV_2025 extends ActionNhaptaycnMain
        implements NhaptaycnFunction {

    Service_GQVL2023 _server;
    DaoNghiquyet11cp _serverlocal;
    private List<QT_DULIEU_NT> lstData;
    private List<DuLieuNTRow> lstData_Api;
    private List<ListPosCode> lstPGD_API;
    private List<ListCommune> lstXa_API;
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

    public List<ListCommune> getLstXa_API() {
        return lstXa_API;
    }

    public void setLstXa_API(List<ListCommune> lstXa_API) {
        this.lstXa_API = lstXa_API;
    }

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
            ArrayList<LockSendModel> lstData_tmp = _serverAPI.getDataLockManual("TGTV_2025", main_pos_username, "M", _reportDate);
            try {
                setChotsl(lstData_tmp.get(0).getStatus());
            } catch (Exception e) {
                setChotsl("0");
            }
            ArrayList<LockSendModel> lstData_tmp1 = _serverAPI.getDataLockManual("TGTV_2025", pos_cd_username, "S", _reportDate);
            try {
                setChotCic(lstData_tmp1.get(0).getStatus());
            } catch (Exception e) {
                setChotCic("0");
            }
            if (pos_cd_username.equals("000101")) {
                lstData_Api = _serverAPI.getData_condition("TGTV_2025", pos_cd_username, "S", _reportDate, "");
            } else {
                String smaxa = hmParameter.get("maxa").toString();
                String smato = hmParameter.get("mato").toString();
                String[] values = smato.split("\\_");
                String ssmato = values[1];

                String condition1 = "D15=" + smaxa + "|D9=" + txtGetData + "|";
                String condition = "D15=" + smaxa + "|D7=" + ssmato + "|D9=" + txtGetData + "|";
                if (smaxa.equals("000000")) {
                    // Tải dữ liệu toàn bộ xã
                    lstData_Api = _serverAPI.getData_condition("TGTV_2025", pos_cd_username, "S", _reportDate, "");

                    // Kiểm tra nếu dữ liệu quá lớn
                    if (lstData_Api.size() > 2000) {
                        addActionError("Dữ liệu quá lớn, vui lòng chọn từng xã để tải dữ liệu!");
                        return ERROR;
                    }
                } else if (ssmato.equals("0000000")) {
                    // Tải dữ liệu toàn bộ tổ trong xã
                    lstData_Api = _serverAPI.getData_condition("TGTV_2025", pos_cd_username, "S", _reportDate, condition1);

                    // Kiểm tra nếu dữ liệu của xã quá lớn
                    if (lstData_Api.size() > 2000) {
                        addActionError("Dữ liệu của xã quá lớn, vui lòng chọn từng tổ để tải dữ liệu!");
                        return ERROR;
                    }
                } else {
                    // Tải dữ liệu của tổ cụ thể
                    lstData_Api = _serverAPI.getData_condition("TGTV_2025", pos_cd_username, "S", _reportDate, condition);
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
            lstPGD_API = _serverAPI.getListPgd(main_pos_username, "");
            ArrayList<LockSendModel> lstData_tmp = _serverAPI.getDataLockManual("TGTV_2025", main_pos_username, "M", _reportDate);
            try {
                setChotsl(lstData_tmp.get(0).getStatus());
            } catch (Exception e) {
                setChotsl("0");
            }
            if (lstPGD_API == null || lstPGD_API.isEmpty()) {
                addActionError("Lỗi khi gọi API!");
                return ERROR;
            }

            for (ListPosCode item : lstPGD_API) {
                QT_DULIEU_NT row = new QT_DULIEU_NT();
                try {
                    row.setD1(item.getPosCode());
                    row.setD2(item.getPosName());
                    row.setD3(item.getMainPos());
                    row.setD4("0");
                    row.setD5(dateStr);
                    row.setD6(null);
                    row.setD7(_reportDate);
//                    row.setD8(null);
//                    row.setD9("TTCN_01_" + txtGetData);
                    lstData_Api = _serverAPI.getData_condition("TGTV_2025", item.getPosCode(), "S", _reportDate, "");
                    int countD9_1 = 0;
                    int countD9_0 = 0;
                    if (lstData_Api != null) {
                        for (DuLieuNTRow apiRow : lstData_Api) {
                            if ("1".equals(apiRow.getD9())) {
                                countD9_1++;
                            } else if ("0".equals(apiRow.getD9())) {
                                countD9_0++;
                            }
                        }
                    }
                    ArrayList<LockSendModel> lstData_tmp1 = _serverAPI.getDataLockManual("TGTV_2025", item.getPosCode(), "S", _reportDate);
                    try {
                        setChotCic(lstData_tmp1.get(0).getStatus());
                    } catch (Exception e) {
                        setChotCic("0");
                    }
                    row.setD8(chotCic);
                    int countD9 = countD9_1 + countD9_0;
                    row.setD10(String.valueOf(countD9_1));
                    row.setD11(String.valueOf(countD9_0));
                    row.setD12(String.valueOf(countD9));
                    lstDulieuNt.add(row);
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
            DaoNghiquyet11cp daoMain = new DaoNghiquyet11cp();
            Connection conn = new DaoConnect().getConnect();
            HashMap hmParameter = getParameter();
            posMainModel = listKTNBDA.get_pos_main_pos(UserName, Grade);
            pos_cd_username = posMainModel.getPosCd();
            String mainpos = hmParameter.get("lstCN").toString();
            String dateStr = hmParameter.get("ngay_bc").toString();
            final String _reportDate = new SimpleDateFormat("yyyyMMdd").format(new SimpleDateFormat("dd-MMM-yyyy").parse(dateStr));
            ArrayList<LockSendModel> lstData_tmp = _serverAPI.getDataLockManual("TGTV_2025", mainpos, "M", _reportDate);
            try {
                setChotsl(lstData_tmp.get(0).getStatus());
            } catch (Exception e) {
                setChotsl("0");
            }

            lstDulieuNt = daoMain.getTGTV_2025(conn, "TGTV_2025", mainpos, dateStr, "S", "");
            if (lstDulieuNt == null || lstDulieuNt.isEmpty()) {
                addActionError("Chưa có dữ liệu!");
                return ERROR;
            }
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> tin dung 2024: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> tin dung 2024: " + e.getMessage());
        }
        return "success_2";
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
                tempadd.setKey("TGTV_2025");
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
                tempadd.setD9(tmp.getD9() == null ? "0" : tmp.getD9());
                tempadd.setD10(tmp.getD10());
                tempadd.setD11(tmp.getD11() == null ? "0" : tmp.getD11());
                tempadd.setD12(tmp.getD12() == null ? "0" : tmp.getD12());
                tempadd.setD13(tmp.getD13() == null ? "0" : tmp.getD13());
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
            int status = _serverAPI.getGQVL2023("TGTV_2025", pos_cd_username, "S", _reportDate, "", "", lstUpdateDate);
            if (status == 200) {
                String code = String.valueOf(status);
                this.pageResult = new ByteArrayInputStream(code.getBytes(StandardCharsets.UTF_8));
                return SUCCESS;
            }
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> tien gui to vien 2025: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> tien gui to vien 2025: " + e.getMessage());
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
            String macn = ServletActionContext.getRequest().getParameter("macn");
            String dateStr = ServletActionContext.getRequest().getParameter("ngayss");
            String schotsl = ServletActionContext.getRequest().getParameter("schotsl");
            String sstype = ServletActionContext.getRequest().getParameter("sstype");
//            System.out.println(macn + dateStr);
            String[] values = dateStr.split("\\/");
            String sngay = values[0];
            String sthang = values[1];
            String snam = values[2];
            String _reportDate = snam + sthang + sngay;
            String stype = schotsl.equals("2") ? "0" : "2";
            String posfl = sstype.equals("2") ? "S" : "M";
//            System.out.println("macn= " + macn + "posfl= " + posfl + " stype= " + stype);
            int status = _serverAPI.updateChotSL("TGTV_2025", macn, posfl, _reportDate, stype, UserName, null);
            if (status != 200) {
                this.pageResult = new ByteArrayInputStream(String.valueOf(status).getBytes(StandardCharsets.UTF_8));
                return ERROR;
            }
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> send 23: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> send 23: " + e.getMessage());
            this.pageResult = new ByteArrayInputStream("500".getBytes(StandardCharsets.UTF_8));
            return ERROR;
        }
        String code = String.valueOf(200);
        this.pageResult = new ByteArrayInputStream(code.getBytes(StandardCharsets.UTF_8));
        return SUCCESS;
    }

    public String popupTablePos() throws Exception {
        try {
            String D1 = ServletActionContext.getRequest().getParameter("madiemgd");
            String D7 = ServletActionContext.getRequest().getParameter("ngaybc");
            String type = ServletActionContext.getRequest().getParameter("type");
            setTxtGetData(type);
            String condition = "D9=" + txtGetData + "|";
            lstData_Api = _serverAPI.getData_condition("TGTV_2025", D1, "S", D7, condition);
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
                    lstDulieuNt.add(row);
                } catch (Exception e) {
                }
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham saveDataaa " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " saveDataaa -> " + e.getMessage());
        }
        return "success";

    }

    public String sendCn() {
        try {
            if (!getParaSession()) {
                return ERROR;
            }

            DaoNghiquyet11cp daoMain = new DaoNghiquyet11cp();
            Connection conn = new DaoConnect().getConnect();

            String D1 = ServletActionContext.getRequest().getParameter("mapgd");
            String D3 = ServletActionContext.getRequest().getParameter("macn");
            String D5 = ServletActionContext.getRequest().getParameter("ngayss");
            String D7 = ServletActionContext.getRequest().getParameter("ngaybc");

            String _reportDate = new SimpleDateFormat("yyyyMMdd").format(new SimpleDateFormat("dd-MMM-yyyy").parse(D5));
            String _reportDate1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS").format(new Date());
            int status = _serverAPI.updateChotSL("TGTV_2025", D1, "S", _reportDate, "2", UserName, null);
            if (status != 200) {
                this.pageResult = new ByteArrayInputStream(String.valueOf(status).getBytes(StandardCharsets.UTF_8));
                return ERROR;
            }

            lstXa_API = _serverAPI.getListXa("", "", "", D1);
            for (ListCommune item : lstXa_API) {
                try {
                    String condition = "D15=" + item.communeCode + "|";
                    lstData_Api = _serverAPI.getData_condition("TGTV_2025", D1, "S", _reportDate, condition);
                    ArrayList<DULIEU_NT_TQ> lstLocalDataUpdate = new ArrayList<>();
                    for (DuLieuNTRow tmp : lstData_Api) {
                        DULIEU_NT_TQ tempadd = new DULIEU_NT_TQ();
                        // Gán dữ liệu
                        tempadd.setKHOA("TGTV_2025");
                        tempadd.setTHUTU(Integer.valueOf(tmp.getOrderValue()));
                        tempadd.setTT_HIENTHI(tmp.getOrderDescription());
                        tempadd.setTEN(tmp.getName());
                        tempadd.setMA(tmp.getCode());
                        tempadd.setNGUOI_NHAP(tmp.getMakerId());
                        tempadd.setNGUOI_DUYET(tmp.getAuthoriseId());
                        Date reportDate2 = DateUtil.toDate(D5);
                        tempadd.setNGAYBC(reportDate2);
                        tempadd.setNAMBC(tmp.getReportYear());
                        tempadd.setMAPGD(tmp.getPosCode());
                        tempadd.setCO_TONGHOP(tmp.getPosFlag());
                        tempadd.setMACN(tmp.getBranchCode());

                        // Gán D1 -> D20
                        for (int i = 1; i <= 20; i++) {
                            Method getter = tmp.getClass().getMethod("getD" + i);
                            Method setter = tempadd.getClass().getMethod("setD" + i, String.class);
                            setter.invoke(tempadd, (String) getter.invoke(tmp));
                        }

                        lstLocalDataUpdate.add(tempadd);
                    }
                    int batchSize = 500;
                    int totalSize = lstLocalDataUpdate.size();
                    int batches = (int) Math.ceil((double) totalSize / batchSize);

                    for (int i = 0; i < batches; i++) {
                        int fromIndex = i * batchSize;
                        int toIndex = Math.min(fromIndex + batchSize, totalSize);

                        List<DULIEU_NT_TQ> subList = lstLocalDataUpdate.subList(fromIndex, toIndex);

                        // Chỉ truyền "Y" cho batch đầu tiên
                        String isFirstBatch = (i == 0) ? "Y" : "N";

                        daoMain.save_TGTV_2025("TGTV_2025", D5, D1, item.communeCode, "S", new ArrayList<>(subList), isFirstBatch);

                        System.out.println(isFirstBatch + " batch " + (i + 1) + "/" + batches + " cho maxa: " + item.communeCode + " to " + fromIndex + " from " + (toIndex - 1));
                    }

                } catch (Exception e) {
                    CoreLogger.error(this.getClass().getName() + " Exception -> commune loop: " + e.getMessage());
                    System.err.println(this.getClass().getName() + " Exception -> commune loop: " + e.getMessage());
                }
            }

            this.pageResult = new ByteArrayInputStream(String.valueOf(status).getBytes(StandardCharsets.UTF_8));
            return SUCCESS;

        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> sendCn: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> sendCn: " + e.getMessage());
            this.pageResult = new ByteArrayInputStream("500".getBytes(StandardCharsets.UTF_8));
            return ERROR;
        }
    }

    public String send_c1() {
        try {
            if (!getParaSession()) {
                return ERROR;
            }
            HashMap hmParameter = getParameter();
            String dateStr = hmParameter.get("ngay_bc").toString();
            final String _reportDate = new SimpleDateFormat("yyyyMMdd").format(new SimpleDateFormat("dd-MMM-yyyy").parse(dateStr));
            posMainModel = listKTNBDA.get_pos_main_pos(UserName, Grade);
            pos_cd_username = posMainModel.getPosCd();
            main_pos_username = posMainModel.getMainPosCd();
            int status = _serverAPI.updateChotSL("TGTV_2025", pos_cd_username, "S", _reportDate, "1", UserName, null);
            if (status != 200) {
                this.pageResult = new ByteArrayInputStream(String.valueOf(status).getBytes(StandardCharsets.UTF_8));
                return ERROR;
            }
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> sendCn: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> sendCn: " + e.getMessage());
            this.pageResult = new ByteArrayInputStream("500".getBytes(StandardCharsets.UTF_8));
            return ERROR;
        }
        String code = String.valueOf(200);
        this.pageResult = new ByteArrayInputStream(code.getBytes(StandardCharsets.UTF_8));
        return SUCCESS;
    }

    public String unlock_c1() {
        try {
            if (!getParaSession()) {
                return ERROR;
            }
            String macn = ServletActionContext.getRequest().getParameter("macn");
            String dateStr = ServletActionContext.getRequest().getParameter("ngayss");
//            System.out.println(macn + dateStr);
            String[] values = dateStr.split("\\/");
            String sngay = values[0];
            String sthang = values[1];
            String snam = values[2];
            String _reportDate = snam + sthang + sngay;
            int status = _serverAPI.updateChotSL("TGTV_2025", macn, "S", _reportDate, "0", UserName, null);
            if (status != 200) {
                this.pageResult = new ByteArrayInputStream(String.valueOf(status).getBytes(StandardCharsets.UTF_8));
                return ERROR;
            }
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> send 23: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> send 23: " + e.getMessage());
            this.pageResult = new ByteArrayInputStream("500".getBytes(StandardCharsets.UTF_8));
            return ERROR;
        }
        String code = String.valueOf(200);
        this.pageResult = new ByteArrayInputStream(code.getBytes(StandardCharsets.UTF_8));
        return SUCCESS;
    }
}
