/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.ktksnb2024;

import static com.opensymphony.xwork2.Action.ERROR;
import static com.opensymphony.xwork2.Action.SUCCESS;
import vbsp.ims.gqvl_2023.*;
import com.opensymphony.xwork2.ActionContext;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import vbsp.ims.nhaptaycn.action.*;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.struts2.ServletActionContext;
import vbsp.ims.bcqt.model.QT_DULIEU_NT;
import vbsp.ims.chtrinh_cn.ActionChtrinhcnMain;
import vbsp.ims.dao.DaoConnect;
import vbsp.ims.define.GenericResult;
import vbsp.ims.log.CoreLogger;
import vbsp.ims.chtrinh_cn.DaoChtrinhcnMain;
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
public class KTKSNB_04 extends ActionChtrinhcnMain
        implements NhaptaycnFunction {

    Service_GQVL2023 _server;
    private List<QT_DULIEU_NT> lstData;
    private List<DuLieuNTRow> lstData_Api;
    private List<ListPosCode> lstPGD_API;
    private List<LockSendModel> lstData_tmp;
    private List<ListCommune> lstXa_API;
    protected String main_pos_username;
    private InputStream pageResult;
    DuLieuNTService _serverAPI = new DuLieuNTService();
    private String chotsl;
    private String chotsl_tw;
    private String chotsl_th;
    private String check_kehoach;
    private String check_dieuchinh;
    private String txtGetData;
    private String status;
    private String message;
    private String check_cn;
    private String title1;
    private String tento;
    private String tenxa;
    private String skhoa;
//<editor-fold defaultstate="collapsed" desc="khai báo get,set">

    public String getCheck_kehoach() {
        return check_kehoach;
    }

    public void setCheck_kehoach(String check_kehoach) {
        this.check_kehoach = check_kehoach;
    }

    public String getCheck_dieuchinh() {
        return check_dieuchinh;
    }

    public void setCheck_dieuchinh(String check_dieuchinh) {
        this.check_dieuchinh = check_dieuchinh;
    }

    public String getChotsl_th() {
        return chotsl_th;
    }

    public void setChotsl_th(String chotsl_th) {
        this.chotsl_th = chotsl_th;
    }

    public String getSkhoa() {
        return skhoa;
    }

    public void setSkhoa(String skhoa) {
        this.skhoa = skhoa;
    }

    public List<DuLieuNTRow> getLstData_Api() {
        return lstData_Api;
    }

    public void setLstData_Api(List<DuLieuNTRow> lstData_Api) {
        this.lstData_Api = lstData_Api;
    }

    public List<ListCommune> getLstXa_API() {
        return lstXa_API;
    }

    public void setLstXa_API(List<ListCommune> lstXa_API) {
        this.lstXa_API = lstXa_API;
    }

    public List<LockSendModel> getLstData_tmp() {
        return lstData_tmp;
    }

    public void setLstData_tmp(List<LockSendModel> lstData_tmp) {
        this.lstData_tmp = lstData_tmp;
    }

    public String getChotsl_tw() {
        return chotsl_tw;
    }

    public void setChotsl_tw(String chotsl_tw) {
        this.chotsl_tw = chotsl_tw;
    }

    public String getTento() {
        return tento;
    }

    public void setTento(String tento) {
        this.tento = tento;
    }

    public String getTenxa() {
        return tenxa;
    }

    public void setTenxa(String tenxa) {
        this.tenxa = tenxa;
    }

    public String getTitle1() {
        return title1;
    }

    public void setTitle1(String title1) {
        this.title1 = title1;
    }

    public String getCheck_cn() {
        return check_cn;
    }

    public void setCheck_cn(String check_cn) {
        this.check_cn = check_cn;
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

            HashMap<String, Object> hmParameter = getParameter();
            posMainModel = listKTNBDA.get_pos_main_pos(UserName, Grade);
            pos_cd_username = posMainModel.getPosCd();
            String smonth = hmParameter.get("monthSelect").toString();
            String syear = hmParameter.get("yearSelect").toString();
            String sKehoach = hmParameter.get("txtKehoach").toString();
            String ssMaxa = hmParameter.get("lstXa").toString();
            if (smonth.equals("0")) {
                addActionError("Bạn chưa chọn tháng kiểm tra");
                return ERROR;
            }
            String sMaxa;
            String sTenxa;
            if (ssMaxa.equals("000000")) {
                addActionError("Bạn chưa chọn xã kiểm tra");
                return ERROR;
            } else {
                String[] values = ssMaxa.split("\\|");
                sMaxa = values[0];  // giá trị posCode
                sTenxa = values[1];   // giá trị posName
            }

            int year = Integer.parseInt(syear);
            int month = Integer.parseInt(smonth);
            LocalDate firstDayOfMonth = LocalDate.of(year, month, 1);
            int lastDay = firstDayOfMonth.lengthOfMonth();
            final String _reportDate3 = String.format("%04d%02d%02d", year, month, lastDay);
            ArrayList<LockSendModel> lstData_tmp11 = _serverAPI.getDataLockManual("KH_HUYEN_TH", pos_cd_username, "S", _reportDate3);
            try {
                setChotsl_th(lstData_tmp11.get(0).getStatus());
            } catch (Exception e) {
                setChotsl_th("0");
            }
            if (chotsl_th.equals("1")) {
                addActionError("Kế hoạch tháng " + smonth + " đã được thực hiện, không thể bổ sung!");
                return ERROR;
            }
            ArrayList<LockSendModel> lstData_tmp = _serverAPI.getDataLockManual("KH_HUYEN_BS", pos_cd_username, "S", _reportDate3);
            try {
                setChotsl(lstData_tmp.get(0).getStatus());
            } catch (Exception e) {
                setChotsl("0");
            }
            String sCanbo;
            if (sKehoach.equals("1")) {
                sCanbo = "99999";
            } else {
                sCanbo = hmParameter.get("txtCanbo").toString();
            }
            if (sCanbo.equals("00000")) {
                addActionError("Bạn chưa chọn cán bộ kiểm tra");
                return ERROR;
            }
            String conditions = "D3=" + sMaxa + "|D4=" + sCanbo + "|D5=" + smonth + "|";
//                System.out.println(conditions);
            String year1 = String.valueOf(year - 1);
            final String _reportDate1 = year1 + "1231";
            List<DuLieuNTRow> lstData_Api11 = _serverAPI.getDataKTKSNB_2024("KH_HUYEN", pos_cd_username, "S", _reportDate1, conditions, "0");
            setCheck_kehoach((lstData_Api11 != null && !lstData_Api11.isEmpty()) ? "1" : "0");
            List<DuLieuNTRow> lstData_Api22 = _serverAPI.getDataKTKSNB_2024("KH_HUYEN_DC", pos_cd_username, "S", _reportDate3, conditions, "0");
            setCheck_dieuchinh((lstData_Api22 != null && !lstData_Api22.isEmpty()) ? "1" : "0");
            List<DuLieuNTRow> lstData_Api33 = _serverAPI.getDataKTKSNB_2024("KH_HUYEN_BS", pos_cd_username, "S", _reportDate3, conditions, "0");
            try {
                setChotsl(lstData_Api33.get(0).getD7());
            } catch (Exception e) {
                setChotsl("0");
            }

            lstData_Api = _serverAPI.getDataKTKSNB_2024("KH_HUYEN_BS", pos_cd_username, "S", _reportDate3, conditions, "0");
            if (lstData_Api == null || lstData_Api.isEmpty()) {
                setChotsl_tw("0");
                lstData_Api = _serverAPI.getDataKTKSNB_2024("KH_HUYEN", pos_cd_username, "S", _reportDate3, conditions, "1");
            } else {
                setChotsl_tw("1");
            }

            setTitle1("Bổ sung kiểm tra " + sTenxa + " - Tháng " + smonth);
            for (DuLieuNTRow item : lstData_Api) {
                QT_DULIEU_NT row = new QT_DULIEU_NT();
                try {
                    row.setKHOA(item.getKey());
                    row.setTHUTU(Integer.parseInt(item.getOrderValue()));
                    row.setTT_HIENTHI(item.getOrderDescription());
                    if (chotsl_tw.equals("0")) {
                        row.setMA("BS_" + item.getCode() + sMaxa + sCanbo + smonth + _reportDate3);
                    } else {
                        row.setMA(item.getCode());
                    }
                    row.setTEN(item.getName());
                    row.setCO_TONGHOP(item.getPosFlag());
                    row.setNGUOI_NHAP(item.getMakerId());
                    Date reportDate = DateUtil.toDate(item.getReportDate());
                    row.setNGAYBC(reportDate);
                    row.setNAMBC(item.getReportYear());
                    row.setMAPGD(pos_cd_username);
                    row.setMACN(main_pos_username);
                    row.setD1(item.getD1());
                    row.setD2(item.getD2());
                    row.setD3(sMaxa);
                    row.setD4(sCanbo);
                    row.setD5(smonth);
                    row.setD6(item.getD6());
                    row.setD7(item.getD7());
                    row.setD8(item.getD8());
                    row.setD9(item.getD9());
                    row.setD10(item.getD10());
                    row.setD11(item.getD11());
                    row.setD12(item.getD12());
                    row.setNHAPTAY(item.getManualFlag());
                    row.setKIEUIN(item.getStyle());
                    lstDulieuNt.add(row);
                } catch (Exception e) {
                }
            }
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> ktksnb2024: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> ktksnb2024: " + e.getMessage());
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
            String smonth = hmParameter.get("monthSelect").toString();
            String syear = hmParameter.get("yearSelect").toString();
            if (smonth.equals("0")) {
                addActionError("Bạn chưa chọn tháng kiểm tra");
                return ERROR;
            }
            int year = Integer.parseInt(syear);
            int month = Integer.parseInt(smonth);
            LocalDate firstDayOfMonth = LocalDate.of(year, month, 1);
            int lastDay = firstDayOfMonth.lengthOfMonth();
            final String _reportDate3 = String.format("%04d%02d%02d", year, month, lastDay);
            ArrayList<LockSendModel> lstData_tmp11 = _serverAPI.getDataLockManual("KH_TINH_TH", main_pos_username, "S", _reportDate3);
            try {
                setChotsl_th(lstData_tmp11.get(0).getStatus());
            } catch (Exception e) {
                setChotsl_th("0");
            }

            String sMapgd;
            String sTenpgd;
            String ssMapgd = hmParameter.get("lstPGD").toString();
            if (ssMapgd.equals("000000")) {
                addActionError("Bạn chưa chọn PGD kiểm tra");
                return ERROR;
            } else {
                String[] values = ssMapgd.split("\\|");
                sMapgd = values[0];  // giá trị posCode
                sTenpgd = values[1];   // giá trị posName
            }

            String year1 = String.valueOf(year - 1);
            final String _reportDate1 = year1 + "1231";
            String conditions = "D3=" + sMapgd + "|D4=99999" + "|D5=" + smonth + "|";
            List<DuLieuNTRow> lstData_Api11 = _serverAPI.getDataKTKSNB_2024("KH_TINH", sMapgd, "S", _reportDate1, conditions, "0");
            setCheck_kehoach((lstData_Api11 != null && !lstData_Api11.isEmpty()) ? "1" : "0");
            List<DuLieuNTRow> lstData_Api22 = _serverAPI.getDataKTKSNB_2024("KH_TINH_DC", sMapgd, "S", _reportDate3, conditions, "0");
            setCheck_dieuchinh((lstData_Api22 != null && !lstData_Api22.isEmpty()) ? "1" : "0");
            List<DuLieuNTRow> lstData_Api33 = _serverAPI.getDataKTKSNB_2024("KH_TINH_BS", sMapgd, "S", _reportDate3, conditions, "0");
            try {
                setChotsl(lstData_Api33.get(0).getD7());
            } catch (Exception e) {
                setChotsl("0");
            }
            if (smonth.equals("0")) {
                addActionError("Bạn chưa chọn tháng kiểm tra");
                return ERROR;
            }
            setTitle1("Bổ sung kiểm tra " + sTenpgd + " - Tháng " + smonth);
            lstData_Api = _serverAPI.getDataKTKSNB_2024("KH_TINH_BS", sMapgd, "S", _reportDate3, conditions, "0");
            if (lstData_Api == null || lstData_Api.isEmpty()) {
                setChotsl_tw("0");
                lstData_Api = _serverAPI.getDataKTKSNB_2024("KH_TINH", sMapgd, "S", _reportDate3, conditions, "1");
            } else {
                setChotsl_tw("1");
            }
            for (DuLieuNTRow item : lstData_Api) {
                QT_DULIEU_NT row = new QT_DULIEU_NT();
                try {
                    row.setKHOA(item.getKey());
                    row.setTHUTU(Integer.parseInt(item.getOrderValue()));
                    row.setTT_HIENTHI(item.getOrderDescription());
                    if (chotsl_tw.equals("0")) {
                        row.setMA("BS_" + item.getCode() + sMapgd + smonth + _reportDate3);
                    } else {
                        row.setMA(item.getCode());
                    }
                    row.setTEN(item.getName());
                    row.setCO_TONGHOP(item.getPosFlag());
                    row.setNGUOI_NHAP(item.getMakerId());
                    Date reportDate = DateUtil.toDate(item.getReportDate());
                    row.setNGAYBC(reportDate);
                    row.setNAMBC(item.getReportYear());
                    row.setMAPGD(sMapgd);
                    row.setMACN(main_pos_username);
                    row.setD1(item.getD1());
                    row.setD2(item.getD2());
                    row.setD3(sMapgd);
                    row.setD4("99999");
                    row.setD5(smonth);
                    row.setD6(item.getD6());
                    row.setD7(item.getD7());
                    row.setD8(item.getD8());
                    row.setD9(item.getD9());
                    row.setD10(item.getD10());
                    row.setD11(item.getD11());
                    row.setD12(item.getD12());
                    row.setNHAPTAY(item.getManualFlag());
                    row.setKIEUIN(item.getStyle());
                    lstDulieuNt.add(row);
                } catch (Exception e) {
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
            String lastFourChars = "31-dec-" + sngaybc.substring(sngaybc.length() - 4);
            String ssNghiepvu = "2";
//                    hmParameter.get("txtGetData3").toString();
            Connection conn = new DaoConnect().getConnect();
            ActionContext.getContext().getSession().put("sUserName", UserName);
            DaoChtrinhcnMain daoMain = new DaoChtrinhcnMain();
            DaoNghiquyet11cp daoMain_tmp = new DaoNghiquyet11cp();
            lstData = daoMain_tmp.getCheck_user(conn, UserName);

            try {
                setCheck_cn(lstData.get(0).getD2());
            } catch (Exception e) {
                setCheck_cn("0");
            }
//            System.out.println("check_cn= " + check_cn);
            if (!check_cn.equals("USRGRP16")) {
                addActionError("User không có quyền sử dụng chương trình");
                return ERROR;
            }
            String ssskhoa;
            if (ssNghiepvu.equals("1")) {
                ssskhoa = "KH_HUYEN";
            } else {
                ssskhoa = "KH_TINH";
            }
            lstDulieuNt = daoMain.getData_Ktksnb_c3(conn, lastFourChars, ssskhoa, smacn, "S");
            if (conn != null) {
                conn.close();
            }

        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> gdx: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> gdx: " + e.getMessage());
        }
        return "success_3";
    }

    public String save() {
        System.out.println("vao váe");
        try {
            if (!getParaSession()) {
                return ERROR;
            }
            HashMap hmParameter = getParameter();
            String smonth = hmParameter.get("monthSelect").toString();
            String syear = hmParameter.get("yearSelect").toString();
            int year = Integer.parseInt(syear);
            int month = Integer.parseInt(smonth);
            LocalDate firstDayOfMonth = LocalDate.of(year, month, 1);
            int lastDay = firstDayOfMonth.lengthOfMonth();
            final String _reportDate3 = String.format("%04d%02d%02d", year, month, lastDay);
            final String _reportDate1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS").format(new Date());
            posMainModel = listKTNBDA.get_pos_main_pos(UserName, Grade);
            pos_cd_username = posMainModel.getPosCd();
            main_pos_username = posMainModel.getMainPosCd();
            String smapgd;
            if (Grade.equals("1")) {
                skhoa = "KH_HUYEN_BS";
                smapgd = pos_cd_username;
            } else {
                skhoa = "KH_TINH_BS";
                String ssMapgd = hmParameter.get("lstPGD").toString();
                String[] values = ssMapgd.split("\\|");
                smapgd = values[0];  // giá trị posCode
            }
            System.out.println("skhoa== " + skhoa);
            ArrayList<DuLieuNTRowX> lstUpdateDate = new ArrayList<>();
            for (QT_DULIEU_NT tmp : lstDulieuNt) {
                DuLieuNTRowX tempadd = new DuLieuNTRowX();
                tempadd.setKey(skhoa);
                tempadd.setOrderValue(tmp.getTHUTU());
                tempadd.setOrderDescription(tmp.getTT_HIENTHI());
                tempadd.setName(tmp.getTEN());
                tempadd.setCode(tmp.getMA());
                tempadd.setMakerId(UserName);
                tempadd.setMakerDate(_reportDate1);
                tempadd.setAuthoriseId(UserName);
                tempadd.setAuthoriseDate(_reportDate1);
                tempadd.setReportDate(_reportDate1);
                tempadd.setReportYear(tmp.getNAMBC());
                if (Grade.equals("1")) {
                    tempadd.setPosCode(smapgd);
                } else {
                    tempadd.setPosCode(tmp.getD3());
                };
                tempadd.setPosFlag(tmp.getCO_TONGHOP());
                tempadd.setBranchCode(main_pos_username);
                tempadd.setD1(tmp.getD1());
                tempadd.setD2(tmp.getD2());
                tempadd.setD3(tmp.getD3());
                tempadd.setD4(tmp.getD4());
                tempadd.setD5(tmp.getD5());
                tempadd.setD7("3");
                tempadd.setD8(tmp.getD8());
                tempadd.setD9(tmp.getD9());
                tempadd.setManualFlag(tmp.getNHAPTAY());
                tempadd.setStyle(tmp.getKIEUIN());
                lstUpdateDate.add(tempadd);
            }

            _serverAPI = new DuLieuNTService();
            int status = _serverAPI.updateKTKSNB(skhoa, smapgd, "S", _reportDate3, "", "", lstUpdateDate);
            if (status == 200) {
                String code = String.valueOf(status);
                this.pageResult = new ByteArrayInputStream(code.getBytes(StandardCharsets.UTF_8));
                return SUCCESS;
            }
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> save ktksnb bs: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> save ktksnb bs: " + e.getMessage());
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
            String smonth = hmParameter.get("monthSelect").toString();
            String syear = hmParameter.get("yearSelect").toString();
            int year = Integer.parseInt(syear);
            int month = Integer.parseInt(smonth);
            LocalDate firstDayOfMonth = LocalDate.of(year, month, 1);
            int lastDay = firstDayOfMonth.lengthOfMonth();
            final String _reportDate2 = String.format("%04d%02d%02d", year, month, lastDay);
            posMainModel = listKTNBDA.get_pos_main_pos(UserName, Grade);
            pos_cd_username = posMainModel.getPosCd();
            main_pos_username = posMainModel.getMainPosCd();
            String smapgd;
            if (Grade.equals("1")) {
                skhoa = "KH_HUYEN_BS";
                smapgd = pos_cd_username;
            } else {
                skhoa = "KH_TINH_BS";
                smapgd = main_pos_username;
            }
            int status = _serverAPI.updateChotSL(skhoa, smapgd, "S", _reportDate2, Grade, UserName, null);

            if (status != 200) {
                String code = String.valueOf(status);
                this.pageResult = new ByteArrayInputStream(code.getBytes(StandardCharsets.UTF_8));
                return SUCCESS;
            }
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> send ktksnb 2024: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> send ktksnb 2024: " + e.getMessage());
        }
        addActionMessage("Bạn gửi lưu dữ liệu thành công");
        String code = String.valueOf(200);
        this.pageResult = new ByteArrayInputStream(code.getBytes(StandardCharsets.UTF_8));
        return SUCCESS;
    }

    public String status() {
        try {
            String D1 = ServletActionContext.getRequest().getParameter("madiemgd");
            String D7 = ServletActionContext.getRequest().getParameter("ngaybc");
            String D8 = ServletActionContext.getRequest().getParameter("type");
            int status;
            if (D8.equals("1")) {
                status = _serverAPI.updateChotSL("KH_HUYEN", D1, "S", D7, "0", UserName, null);
            } else {
                status = _serverAPI.updateChotSL("KH_HUYEN", D1, "S", D7, "2", UserName, null);
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

    public String popupTablePos() throws Exception {
        try {
            String D1 = ServletActionContext.getRequest().getParameter("madiemgd");

            String D7 = ServletActionContext.getRequest().getParameter("ngaybc");
            String type = ServletActionContext.getRequest().getParameter("type");
            ArrayList<LockSendModel> lstData_tmp = _serverAPI.getDataLockManual("KH_HUYEN", D1, "S", D7);
            try {
                setChotsl(lstData_tmp.get(0).getStatus());
            } catch (Exception e) {
                setChotsl("0");
            }
            lstData_Api = _serverAPI.getDataKTKSNB("KH_HUYEN", D1, "S", D7, "", "0");
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
                    row.setMAPGD(pos_cd_username);
                    row.setMACN(main_pos_username);
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
                    row.setNHAPTAY(item.getManualFlag());
                    row.setKIEUIN(item.getStyle());
                    lstDulieuNt.add(row);
                } catch (Exception e) {
                }
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham table pos " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " table pos -> " + e.getMessage());
        }
        return "success";
    }

    public String unlock_c3() {
        try {
            String D1 = ServletActionContext.getRequest().getParameter("madiemgd");
            String D5 = ServletActionContext.getRequest().getParameter("ngaybc");
            String D6 = ServletActionContext.getRequest().getParameter("pos_flag");
            String D7 = ServletActionContext.getRequest().getParameter("key_lock");
            String D10 = ServletActionContext.getRequest().getParameter("sc3khoa");
            SimpleDateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat outputFormat = new SimpleDateFormat("dd/MMM/yyyy");
            Date date = inputFormat.parse(D5);
            String formattedDate = outputFormat.format(date);
            DaoChtrinhcnMain daoMain = new DaoChtrinhcnMain();
            UserName = (String) ActionContext.getContext().getSession().get("sUserName");
            GenericResult<String> _result = daoMain.unlock_c3_ktksnb(D10, D1, D6, formattedDate, D7, UserName);
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

    public String delete() {
        System.out.println("vao váe");
        try {
            if (!getParaSession()) {
                return ERROR;
            }
            posMainModel = listKTNBDA.get_pos_main_pos(UserName, Grade);
            pos_cd_username = posMainModel.getPosCd();
            main_pos_username = posMainModel.getMainPosCd();
            HashMap<String, Object> hmParameter = getParameter();
            String smonth = hmParameter.get("monthSelect").toString();
            String syear = hmParameter.get("yearSelect").toString();
            int year = Integer.parseInt(syear);
            int month = Integer.parseInt(smonth);
            LocalDate firstDayOfMonth = LocalDate.of(year, month, 1);
            int lastDay = firstDayOfMonth.lengthOfMonth();
            final String _reportDate3 = String.format("%04d%02d%02d", year, month, lastDay);
            String sKehoach = hmParameter.get("txtKehoach").toString();
            String smapgd;
            String sMaxa;
            String sskhoa_th;
            if (Grade.equals("1")) {
                skhoa = "KH_HUYEN_BS";
                sskhoa_th = "KH_HUYEN_TH_BS";
                smapgd = pos_cd_username;
                String ssMaxa = hmParameter.get("lstXa").toString();
                String[] values = ssMaxa.split("\\|");
                sMaxa = values[0];
                if (sMaxa.equals("000000")) {
                    String code = "1";
                    this.pageResult = new ByteArrayInputStream(code.getBytes(StandardCharsets.UTF_8));
                    return SUCCESS;
                }
            } else {
                skhoa = "KH_TINH_BS";
                sskhoa_th = "KH_TINH_TH_BS";
                String ssMapgd = hmParameter.get("lstPGD").toString();
                String[] values = ssMapgd.split("\\|");
                smapgd = values[0];  // giá trị posCode
                sMaxa = smapgd;
            }
            String sCanbo;
            if (sKehoach.equals("1")) {
                sCanbo = "99999";
            } else {
                sCanbo = hmParameter.get("txtCanbo").toString();
            }
            String conditions = "D3=" + sMaxa + "|D4=" + sCanbo + "|D5=" + smonth + "|";
            if (smonth.equals("0")) {
                String code = "2";
                this.pageResult = new ByteArrayInputStream(code.getBytes(StandardCharsets.UTF_8));
                return SUCCESS;
            }
            lstData_Api = _serverAPI.getDataKTKSNB_2024(skhoa, smapgd, "S", _reportDate3, conditions, "0");

            ArrayList<DuLieuNTRowX> lstUpdateDate = new ArrayList<>();
            for (DuLieuNTRow tmp : lstData_Api) {
                DuLieuNTRowX tempadd = new DuLieuNTRowX();
                tempadd.setKey(skhoa);
                tempadd.setOrderValue(Integer.parseInt(tmp.getOrderValue()));
                tempadd.setOrderDescription(tmp.getOrderDescription());
                tempadd.setName(tmp.getName());
                tempadd.setCode(tmp.getCode());
                tempadd.setMakerId(tmp.getMakerId());
                tempadd.setMakerDate(tmp.getMakerDate());
                tempadd.setAuthoriseId(tmp.getAuthoriseId());
                tempadd.setAuthoriseDate(tmp.getAuthoriseDate());
                tempadd.setReportDate(tmp.getReportDate());
                tempadd.setReportYear(tmp.getReportYear());
                tempadd.setPosCode(tmp.getPosCode());
                tempadd.setPosFlag(tmp.getPosFlag());
                tempadd.setBranchCode(tmp.getBranchCode());
                tempadd.setD1(tmp.getD1());
                tempadd.setD2(tmp.getD2());
                tempadd.setD3(tmp.getD3());
                tempadd.setD4(tmp.getD4());
                tempadd.setD5(tmp.getD5());
                tempadd.setD6(tmp.getD6());
                tempadd.setD7(tmp.getD7());
                tempadd.setD8(tmp.getD8());
                tempadd.setD9(tmp.getD9());
                lstUpdateDate.add(tempadd);
            }
            int status = _serverAPI.deleteKTKSNB(skhoa, smapgd, "S", _reportDate3, "", "", lstUpdateDate);
            _serverAPI.deleteKTKSNB(sskhoa_th, smapgd, "S", _reportDate3, "", "", lstUpdateDate);
            if (status != 200) {
                String code = String.valueOf(status);
                this.pageResult = new ByteArrayInputStream(code.getBytes(StandardCharsets.UTF_8));
                return SUCCESS;
            }
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> muats2024: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> muats2024: " + e.getMessage());
        }
        addActionMessage("Bạn đã xóa dữ liệu thành công");
        String code = String.valueOf(200);
        this.pageResult = new ByteArrayInputStream(code.getBytes(StandardCharsets.UTF_8));
        return SUCCESS;
    }

    public String seach() {
        try {
            if (!getParaSession()) {
                return ERROR;
            }
            HashMap hmParameter = getParameter();
            String syear = hmParameter.get("yearSelect").toString();
            int year = Integer.parseInt(syear);

            posMainModel = listKTNBDA.get_pos_main_pos(UserName, Grade);
            pos_cd_username = posMainModel.getPosCd();
            main_pos_username = posMainModel.getMainPosCd();

            if (Grade.equals("1")) {
                for (int month = 1; month <= 12; month++) {
                    String sThangkt = String.valueOf(month);
                    LocalDate firstDayOfMonth = LocalDate.of(year, month, 1);
                    int lastDay = firstDayOfMonth.lengthOfMonth();
                    final String _reportDate = String.format("%04d%02d%02d", year, month, lastDay);
                    lstData_Api = _serverAPI.getListKTKSNB_2024("KH_HUYEN_BS", pos_cd_username, "S", _reportDate, sThangkt);

                    if (lstData_Api == null || lstData_Api.isEmpty()) {
                        CoreLogger.info("No data found for month: " + sThangkt);
                        continue;
                    }

                    for (DuLieuNTRow dataItem : lstData_Api) {
                        try {
                            QT_DULIEU_NT row = new QT_DULIEU_NT();
                            row.setMAPGD(dataItem.getPosCode());
                            row.setD1(dataItem.getD1());
                            row.setD2(dataItem.getD2());
                            row.setD3(dataItem.getD3());
                            row.setD4(dataItem.getD4());
                            row.setD5(dataItem.getD5());
                            row.setD6(dataItem.getD6());
                            row.setD7(dataItem.getD7());
                            row.setD8(dataItem.getD8());
                            row.setD9(dataItem.getD9());
                            row.setD10(dataItem.getD10());
                            lstDulieuNt_tong.add(row);
                        } catch (Exception e) {
                            CoreLogger.error("Error processing dataItem for month " + sThangkt + ": " + e.getMessage());
                        }
                    }
                }
            } else {
                lstPGD_API = _serverAPI.getListPgd(main_pos_username, "");
                for (ListPosCode item : lstPGD_API) {
                    for (int month = 1; month <= 12; month++) {
                        String sThangkt = String.valueOf(month);
                        LocalDate firstDayOfMonth = LocalDate.of(year, month, 1);
                        int lastDay = firstDayOfMonth.lengthOfMonth();
                        final String _reportDate = String.format("%04d%02d%02d", year, month, lastDay);
                        try {
                            lstData_Api = _serverAPI.getListKTKSNB_2024("KH_TINH_BS", item.getPosCode(), "S", _reportDate, sThangkt);
                            for (DuLieuNTRow dataItem : lstData_Api) {
                                try {
                                    QT_DULIEU_NT row = new QT_DULIEU_NT();
                                    row.setMAPGD(dataItem.getPosCode());
                                    for (ListPosCode posRow : lstPGD_API) {
                                        if (posRow.getPosCode() != null && posRow.getPosCode().trim().equals(dataItem.getPosCode().trim())) {
                                            row.setD1(dataItem.getD1() + " " + posRow.getPosName());
                                        }
                                    }
                                    row.setD2(dataItem.getD2());
                                    row.setD3(dataItem.getD3());
                                    row.setD4(dataItem.getD4());
                                    row.setD5(dataItem.getD5());
                                    row.setD6(dataItem.getD6());
                                    row.setD7(dataItem.getD7());
                                    row.setD8(dataItem.getD8());
                                    row.setD9(dataItem.getD9());
                                    row.setD10(dataItem.getD10());
                                    lstDulieuNt_tong.add(row);
                                } catch (Exception e) {
                                    CoreLogger.error("Error processing dataItem for posCode " + item.getPosCode() + ", month " + sThangkt + ": " + e.getMessage());
                                }
                            }
                        } catch (Exception e) {
                            CoreLogger.error("Exception in processing posCode " + item.getPosCode() + ", month " + sThangkt + ": " + e.getMessage());
                        }
                    }
                }
            }
            lstDulieuNt_tong.sort(Comparator.comparingInt((QT_DULIEU_NT obj) -> Integer.parseInt(obj.getMAPGD()))
                    .thenComparingInt(obj -> Integer.parseInt(obj.getD4())));
//            System.out.println("datazzz: lstData == " + lstDulieuNt_tong.size());
            return SUCCESS;
        } catch (Exception e) {
            CoreLogger.error("Exception in seach 2024: " + e.getMessage());
            return ERROR;
        }
    }

}
