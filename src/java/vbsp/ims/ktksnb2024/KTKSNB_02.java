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
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import org.apache.struts2.ServletActionContext;
import vbsp.ims.bcqt.model.QT_DULIEU_NT;
import vbsp.ims.chtrinh_cn.ActionChtrinhcnMain;
import vbsp.ims.dao.DaoConnect;
import vbsp.ims.define.GenericResult;
import vbsp.ims.log.CoreLogger;
import vbsp.ims.chtrinh_cn.DaoChtrinhcnMain;
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
public class KTKSNB_02 extends ActionChtrinhcnMain
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
    private String txtGetData;
    private String status;
    private String message;
    private String check_cn;
    private String title1;
    private String tento;
    private String tenxa;
    private String skhoa;
//<editor-fold defaultstate="collapsed" desc="khai báo get,set">

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
            Connection conn = new DaoConnect().getConnect();
            posMainModel = listKTNBDA.get_pos_main_pos(UserName, Grade);
            pos_cd_username = posMainModel.getPosCd();
            main_pos_username = posMainModel.getMainPosCd();

            String sNam = String.valueOf(Integer.parseInt(hmParameter.get("yearSelect").toString()) - 1);
            String sKehoach = hmParameter.get("txtKehoach").toString();
            String ssMaxa = hmParameter.get("lstXa").toString();
            String[] values = ssMaxa.split("\\|");
            String sMaxa = values[0];  // giá trị posCode
            String sTenxa = values[1];   // giá trị posName
            String sThangkt = hmParameter.get("monthSelect").toString();
            final String _reportDate1 = sNam + "1231";

            // Chỉnh sửa định dạng để phân tích cú pháp ngày
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
            LocalDate _reportDate2 = LocalDate.parse(_reportDate1, formatter);
            LocalDate minDate = LocalDate.of(Integer.parseInt(sNam), 12, 10);
            String sskhoa = hmParameter.get("khoa_nhaptaycn").toString();

            if (sskhoa.equals("KTKSNB_02") && Grade.equals("1")) {
                setSkhoa("KH_HUYEN");
            }
            int year = Integer.parseInt(hmParameter.get("yearSelect").toString());
            int month = Integer.parseInt(hmParameter.get("monthSelect").toString());
            LocalDate firstDayOfMonth = LocalDate.of(year, month, 1);
            int lastDay = firstDayOfMonth.lengthOfMonth();
            final String _reportDate3 = String.format("%04d%02d%02d", year, month, lastDay);

            ActionContext.getContext().getSession().put("skhoa", skhoa);
            ArrayList<LockSendModel> lstData_dky = _serverAPI.getDataLockManual("KH_HUYEN", pos_cd_username, "S", _reportDate1);
            try {
                setChotCic(lstData_dky.get(0).getStatus());
            } catch (Exception e) {
                setChotCic("0");
            }

            if (sThangkt.equals("0")) {
                addActionError("Bạn chưa chọn tháng kiểm tra");
                return ERROR;
            }
            if (sMaxa.equals("000000")) {
                addActionError("Bạn chưa chọn xã kiểm tra");
                return ERROR;
            }
            if (!chotCic.equals("2")) {
                addActionError("Đơn vị chưa gửi kế hoạch lên TW");
                return ERROR;
            }
            ArrayList<LockSendModel> lstData_tmp = _serverAPI.getDataLockManual("KH_HUYEN_DC", pos_cd_username, "S", _reportDate3);

            try {
                setChotsl(lstData_tmp.get(0).getStatus());
            } catch (Exception e) {
                setChotsl("0");
            }
            String sCanbo = "";
            SimpleDateFormat sdf2 = new SimpleDateFormat("dd-MM-yyyy");
            String ngay1 = "";
            boolean dataFound = false; // Flag to track if data was found

            if (sKehoach.equals("1")) {
                sCanbo = "99999";
            } else {
                sCanbo = hmParameter.get("txtCanbo").toString();
            }
            String conditions = "D3=" + sMaxa + "|D4=" + sCanbo + "|D5=" + sThangkt + "|";
            lstData_Api = _serverAPI.getDataKTKSNB_2024("KH_HUYEN_DC", pos_cd_username, "S", _reportDate3, conditions, "0");
            if (lstData_Api == null || lstData_Api.isEmpty()) {
                while (!_reportDate2.isBefore(minDate)) {
                    String currentDateStr = _reportDate2.format(formatter);
                    lstData_Api = _serverAPI.getDataKTKSNB_2024(skhoa, pos_cd_username, "S", currentDateStr, conditions, "0");
                    if (lstData_Api != null && !lstData_Api.isEmpty()) {
                        dataFound = true; // Set flag to true if data is found
                        break;
                    }

                    _reportDate2 = _reportDate2.minusDays(1);
                    ngay1 = sdf2.format(java.sql.Date.valueOf(_reportDate2.toString()));
                }
            } else {
                dataFound = true;
                ngay1 = "";
            }
            if (!dataFound) {
                addActionError("Chưa đăng ký kế hoạch kiểm tra!");
                return ERROR;
            }
            if (ngay1.equals("")) {
                ngay1 = " - Đã điều chỉnh";
                setCheck_cn("0");
            } else {
                ngay1 = " - Ngày nhập kế hoạch cuối " + ngay1;
                setCheck_cn("1");
            }
            setTitle1("Kiểm tra " + sTenxa + " - Tháng " + sThangkt + ngay1);
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
                    row.setD3(sMaxa);
                    row.setD4(sCanbo);
                    row.setD5(sThangkt);
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
                    // Log error if needed
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
            String sNam = String.valueOf(Integer.parseInt(hmParameter.get("yearSelect").toString()) - 1);
            final String _reportDate1 = sNam + "1231";
            String nghiepvu = hmParameter.get("txtGetData").toString();
            String sskhoa = hmParameter.get("khoa_nhaptaycn").toString();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
            LocalDate _reportDate2 = LocalDate.parse(_reportDate1, formatter);
            LocalDate minDate = LocalDate.of(Integer.parseInt(sNam), 12, 10);
            int year = Integer.parseInt(hmParameter.get("yearSelect").toString());
            int month = Integer.parseInt(hmParameter.get("monthSelect").toString());
            LocalDate firstDayOfMonth = LocalDate.of(year, month, 1);
            int lastDay = firstDayOfMonth.lengthOfMonth();
            final String _reportDate3 = String.format("%04d%02d%02d", year, month, lastDay);
            if (sskhoa.equals("KTKSNB_02") && Grade.equals("2")) {
                setSkhoa("KH_TINH");
            }
            ActionContext.getContext().getSession().put("skhoa", skhoa);
            setTxtGetData(nghiepvu);
            if (nghiepvu.equals("1")) {
                String ssMapgd = hmParameter.get("lstPGD").toString();
                String[] values = ssMapgd.split("\\|");
                String sMapgd = values[0];  // giá trị posCode
                String sTenpgd = values[1];   // giá trị posName
                ArrayList<LockSendModel> lstData_tmp = _serverAPI.getDataLockManual("KH_TINH_DC", sMapgd, "S", _reportDate3);
                try {
                    setChotsl(lstData_tmp.get(0).getStatus());
                } catch (Exception e) {
                    setChotsl("0");
                }
                String sThangkt = hmParameter.get("monthSelect").toString();
                String conditions = "D3=" + sMapgd + "|D4=99999" + "|D5=" + sThangkt + "|";
                if (sMapgd.equals("000000")) {
                    addActionError("Bạn chưa chọn PGD kiểm tra");
                    return ERROR;
                }
                if (sThangkt.equals("0")) {
                    addActionError("Bạn chưa chọn tháng kiểm tra");
                    return ERROR;
                }
                ArrayList<LockSendModel> lstData_dky = _serverAPI.getDataLockManual("KH_TINH", sMapgd, "S", _reportDate1);
                try {
                    setChotCic(lstData_dky.get(0).getStatus());
                } catch (Exception e) {
                    setChotCic("0");
                }
                if (!chotCic.equals("2")) {
                    addActionError("Đơn vị chưa gửi kế hoạch lên TW");
                    return ERROR;
                }
                SimpleDateFormat sdf2 = new SimpleDateFormat("dd-MM-yyyy");
                String ngay1 = "";
                boolean dataFound = false;
                lstData_Api = _serverAPI.getDataKTKSNB_2024("KH_TINH_DC", sMapgd, "S", _reportDate3, conditions, "0");
                if (lstData_Api == null || lstData_Api.isEmpty()) {
                    while (!_reportDate2.isBefore(minDate)) {
                        String currentDateStr = _reportDate2.format(formatter);
                        lstData_Api = _serverAPI.getDataKTKSNB_2024(skhoa, sMapgd, "S", currentDateStr, conditions, "0");
                        if (lstData_Api != null && !lstData_Api.isEmpty()) {
                            dataFound = true;
                            break;
                        }

                        _reportDate2 = _reportDate2.minusDays(1);
                        ngay1 = sdf2.format(java.sql.Date.valueOf(_reportDate2.toString()));
                    }
                } else {
                    dataFound = true;
                    ngay1 = "";
                }
                if (!dataFound) {
                    addActionError("Chưa đăng ký kế hoạch kiểm tra!");
                    return ERROR;
                }
                if (ngay1.equals("")) {
                    ngay1 = " - Đã điều chỉnh";
                    setCheck_cn("0");
                } else {
                    ngay1 = " - Ngày nhập kế hoạch cuối " + ngay1;
                    setCheck_cn("1");
                }
                setTitle1("Kiểm tra " + sTenpgd + " - Tháng " + sThangkt + ngay1);
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
                        row.setMAPGD(sMapgd);
                        row.setMACN(main_pos_username);
                        row.setD1(item.getD1());
                        row.setD2(item.getD2());
                        row.setD3(sMapgd);
                        row.setD4("99999");
                        row.setD5(sThangkt);
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
            } else {
                lstPGD_API = _serverAPI.getListPgd(main_pos_username, "");
                if (lstPGD_API == null || lstPGD_API.isEmpty()) {
                    addActionError("Lỗi khi gọi API!");
                    return ERROR;
                }
                String sKye;
                if (nghiepvu.equals("2")) {
                    sKye = "KH_HUYEN_DC";
                } else {
                    sKye = "KH_TINH_DC";
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
                        row.setD7(_reportDate3);
                        row.setD8(null);
                        lstData_tmp = _serverAPI.getDataLockManual(sKye, item.getPosCode(), "S", _reportDate3);
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
                                    dataRow.setD7(_reportDate3);
                                    dataRow.setD8(item_tmp.getUpdateId());
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
            String month = hmParameter.get("monthSelect").toString();
            String year = hmParameter.get("yearSelect").toString();
            int monthValue = Integer.parseInt(month);
            LocalDate lastDayOfMonth = LocalDate.of(Integer.parseInt(year), monthValue, 1)
                    .withDayOfMonth(LocalDate.of(Integer.parseInt(year), monthValue, 1).lengthOfMonth());
            String lastFourChars = lastDayOfMonth.format(DateTimeFormatter.ofPattern("dd-MMM-yyyy")).toLowerCase();
            String smacn = hmParameter.get("lstCN").toString();
            String ssNghiepvu = hmParameter.get("txtGetData3").toString();
            Connection conn = new DaoConnect().getConnect();
            ActionContext.getContext().getSession().put("sUserName", UserName);
            DaoChtrinhcnMain daoMain = new DaoChtrinhcnMain();
            String ssskhoa;
            if (ssNghiepvu.equals("1")) {
                ssskhoa = "KH_HUYEN_DC";
            } else {
                ssskhoa = "KH_TINH_DC";
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
            int year = Integer.parseInt(hmParameter.get("yearSelect").toString());
            int month = Integer.parseInt(hmParameter.get("monthSelect").toString());
            LocalDate firstDayOfMonth = LocalDate.of(year, month, 1);
            int lastDay = firstDayOfMonth.lengthOfMonth();
            final String _reportDate2 = String.format("%04d%02d%02d", year, month, lastDay);
            final String _reportDate1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS").format(new Date());
            posMainModel = listKTNBDA.get_pos_main_pos(UserName, Grade);
            pos_cd_username = posMainModel.getPosCd();
            main_pos_username = posMainModel.getMainPosCd();
            String smapgd;
            if (Grade.equals("1")) {
                skhoa = "KH_HUYEN_DC";
                smapgd = pos_cd_username;
            } else {
                skhoa = "KH_TINH_DC";
                String ssMapgd = hmParameter.get("lstPGD").toString();
                String[] values = ssMapgd.split("\\|");
                smapgd = values[0];  // giá trị posCode
            }
            ArrayList<DuLieuNTRowX> lstUpdateDate = new ArrayList<>();
            for (QT_DULIEU_NT tmp : lstDulieuNt) {
                DuLieuNTRowX tempadd = new DuLieuNTRowX();
                tempadd.setKey(skhoa);
                tempadd.setOrderValue(tmp.getTHUTU());
                tempadd.setOrderDescription(tmp.getTT_HIENTHI());
                tempadd.setName(tmp.getTEN());
                tempadd.setCode(tmp.getMA().substring(0, tmp.getMA().length() - 8) + _reportDate2);
                tempadd.setMakerId(UserName);
                tempadd.setMakerDate(_reportDate1);
                tempadd.setAuthoriseId(UserName);
                tempadd.setAuthoriseDate(_reportDate1);
                tempadd.setReportDate(_reportDate1);
                tempadd.setReportYear(tmp.getNAMBC());
                tempadd.setPosCode(smapgd);
                tempadd.setPosFlag(tmp.getCO_TONGHOP());
                tempadd.setBranchCode(main_pos_username);
                tempadd.setD1(tmp.getD1());
                tempadd.setD2(tmp.getD2());
                tempadd.setD3(tmp.getD3());
                tempadd.setD4(tmp.getD4());
                tempadd.setD5(tmp.getD5());
                tempadd.setD6(tmp.getD6());
                tempadd.setManualFlag(tmp.getNHAPTAY());
                tempadd.setStyle(tmp.getKIEUIN());
                lstUpdateDate.add(tempadd);
            }

            _serverAPI = new DuLieuNTService();
            int status = _serverAPI.updateKTKSNB(skhoa, smapgd, "S", _reportDate2, "", "", lstUpdateDate);
            if (status == 200) {
                _serverAPI.updateChotSL(skhoa, smapgd, "S", _reportDate2, "0", UserName, null);
                String code = String.valueOf(status);
                this.pageResult = new ByteArrayInputStream(code.getBytes(StandardCharsets.UTF_8));
                return SUCCESS;
            }
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> save ktksnb 2024: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> save ktksnb 2024: " + e.getMessage());
            String code = String.valueOf(status);
            this.pageResult = new ByteArrayInputStream(code.getBytes(StandardCharsets.UTF_8));
            return ERROR;
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
            String smapgd;
            String sCanbo;
            if (Grade.equals("1")) {
                skhoa = "KH_HUYEN_DC";
                smapgd = pos_cd_username;
                sCanbo = hmParameter.get("sCanbo").toString();
            } else {
                skhoa = "KH_TINH_DC";
                String ssMapgd = hmParameter.get("lstPGD").toString();
                String[] values = ssMapgd.split("\\|");
                smapgd = values[0];  // giá trị posCode
                sCanbo = "99999";
            }

            int year = Integer.parseInt(hmParameter.get("yearSelect").toString());
            int month = Integer.parseInt(hmParameter.get("monthSelect").toString());
            LocalDate firstDayOfMonth = LocalDate.of(year, month, 1);
            int lastDay = firstDayOfMonth.lengthOfMonth();
            final String _reportDate2 = String.format("%04d%02d%02d", year, month, lastDay);
            posMainModel = listKTNBDA.get_pos_main_pos(UserName, Grade);
            pos_cd_username = posMainModel.getPosCd();
            main_pos_username = posMainModel.getMainPosCd();
            String conditions = "D3=" + smapgd + "|D4=" + sCanbo + "|D5=" + month + "|";
            int status;
            lstData_Api = _serverAPI.getDataKTKSNB_2024(skhoa, smapgd, "S", _reportDate2, conditions, "0");
            if (lstData_Api != null && !lstData_Api.isEmpty()) {
                status = _serverAPI.updateChotSL(skhoa, smapgd, "S", _reportDate2, Grade, UserName, null);
                this.pageResult = new ByteArrayInputStream(String.valueOf(status).getBytes(StandardCharsets.UTF_8));
                return SUCCESS;
            } else {
                status = 1;
                this.pageResult = new ByteArrayInputStream(String.valueOf(status).getBytes(StandardCharsets.UTF_8));
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
            String sMaPgd = ServletActionContext.getRequest().getParameter("madiemgd");
            String smaxa = ServletActionContext.getRequest().getParameter("smaxa");
            String sCanbo = ServletActionContext.getRequest().getParameter("sCanbo");
            String sThang = ServletActionContext.getRequest().getParameter("sThang");
            String ssThang = ServletActionContext.getRequest().getParameter("ssThang");
            String stype = ServletActionContext.getRequest().getParameter("type");
            String sNgaybc = ServletActionContext.getRequest().getParameter("sNam");
            String sNam = sNgaybc.substring(sNgaybc.length() - 4);
            String ssKhoa = ServletActionContext.getRequest().getParameter("skhoa");

            String schotsl = ServletActionContext.getRequest().getParameter("chotsl");
            String conditions = "D3=" + smaxa + "|D4=" + sCanbo + "|D5=" + sThang + "|";
//            System.out.println("ssKhoa " + ssKhoa + " zzz: " + conditions);
            final String _reportDate1 = (Integer.parseInt(sNam) - 1) + "1231";
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
            LocalDate _reportDate2 = LocalDate.parse(_reportDate1, formatter);
            final String _reportDate11 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS").format(new Date());
            LocalDate minDate = LocalDate.of((Integer.parseInt(sNam) - 1), 12, 10);
            int year = Integer.parseInt(sNam);
            int month = Integer.parseInt(sThang);
            int month1 = Integer.parseInt(ssThang);
            LocalDate firstDayOfMonth = LocalDate.of(year, month, 1);
            LocalDate firstDayOfMonth1 = LocalDate.of(year, month1, 1);
            int lastDay = firstDayOfMonth.lengthOfMonth();
            int lastDay1 = firstDayOfMonth1.lengthOfMonth();
            final String _reportDate3 = String.format("%04d%02d%02d", year, month, lastDay);
            final String _reportDate4 = String.format("%04d%02d%02d", year, month1, lastDay1);
//            System.out.println(_reportDate4);
            boolean dataFound = false;
            if (ssKhoa.equals("KH_HUYEN_DC")) {
                setChotCic("KH_HUYEN");
            } else {
                setChotCic("KH_TINH");
            }
            int status1;
            while (!_reportDate2.isBefore(minDate)) {
                String currentDateStr = _reportDate2.format(formatter);
                lstData_Api = _serverAPI.getDataKTKSNB_2024(chotCic, sMaPgd, "S", currentDateStr, conditions, "0");
                if (lstData_Api != null && !lstData_Api.isEmpty()) {
                    dataFound = true;
                    status1 = 200;
                    break;
                }
                _reportDate2 = _reportDate2.minusDays(1);
            }
            int status2;
            lstData_Api = _serverAPI.getDataKTKSNB_2024(ssKhoa, sMaPgd, "S", _reportDate3, conditions, "0");
            if (lstData_Api != null && !lstData_Api.isEmpty()) {
                status2 = 200;
            } else {
                status2 = 0;
            }
//            System.out.println(lstData_Api.size() + " " + ssKhoa + " " + sMaPgd + " " + _reportDate3 + " " + conditions + " " + status2);
            int status;
//            System.out.println(status1);
            if (schotsl.equals("1") || schotsl.equals("2")) {
                status = 100;
                this.pageResult = new ByteArrayInputStream(String.valueOf(status).getBytes(StandardCharsets.UTF_8));
                return SUCCESS;
            } else {
                if (stype.equals("2")) {
                    if (status2 == 200) {
                        status = 1;
                        this.pageResult = new ByteArrayInputStream(String.valueOf(status).getBytes(StandardCharsets.UTF_8));
                        return SUCCESS;
                    } else {
                        ArrayList<DuLieuNTRowX> lstUpdateDate = new ArrayList<>();
                        ArrayList<DuLieuNTRowX> lstDelete = new ArrayList<>();
                        for (QT_DULIEU_NT tmp : lstDulieuNt) {
                            // Create a new instance for update
                            DuLieuNTRowX tempUpdate = new DuLieuNTRowX();
                            tempUpdate.setKey(ssKhoa);
                            tempUpdate.setOrderValue(tmp.getTHUTU());
                            tempUpdate.setOrderDescription(tmp.getTT_HIENTHI());
                            tempUpdate.setName(tmp.getTEN());
                            tempUpdate.setCode(tmp.getMA().substring(0, tmp.getMA().length() - 8) + _reportDate3); // For update
                            tempUpdate.setMakerId(UserName);
                            tempUpdate.setMakerDate(_reportDate11);
                            tempUpdate.setAuthoriseId(UserName);
                            tempUpdate.setAuthoriseDate(_reportDate11);
                            tempUpdate.setReportDate(_reportDate11);
                            tempUpdate.setReportYear(tmp.getNAMBC());
                            tempUpdate.setPosCode(sMaPgd);
                            tempUpdate.setPosFlag(tmp.getCO_TONGHOP());
                            tempUpdate.setBranchCode(tmp.getMACN());
                            tempUpdate.setD1(tmp.getD1());
                            tempUpdate.setD2(tmp.getD2());
                            tempUpdate.setD3(tmp.getD3());
                            tempUpdate.setD4(tmp.getD4());
                            tempUpdate.setD5(sThang);
                            tempUpdate.setD6(tmp.getD6());
                            tempUpdate.setManualFlag(tmp.getNHAPTAY());
                            tempUpdate.setStyle(tmp.getKIEUIN());

                            lstUpdateDate.add(tempUpdate);

                            DuLieuNTRowX tempDelete = new DuLieuNTRowX();
                            tempDelete.setKey(ssKhoa);
                            tempDelete.setOrderValue(tmp.getTHUTU());
                            tempDelete.setOrderDescription(tmp.getTT_HIENTHI());
                            tempDelete.setName(tmp.getTEN());
                            tempDelete.setCode(tmp.getMA());
                            tempDelete.setMakerId(UserName);
                            tempDelete.setMakerDate(_reportDate11);
                            tempDelete.setAuthoriseId(UserName);
                            tempDelete.setAuthoriseDate(_reportDate11);
                            tempDelete.setReportDate(_reportDate11);
                            tempDelete.setReportYear(tmp.getNAMBC());
                            tempDelete.setPosCode(sMaPgd);
                            tempDelete.setPosFlag(tmp.getCO_TONGHOP());
                            tempDelete.setBranchCode(tmp.getMACN());
                            tempDelete.setD1(tmp.getD1());
                            tempDelete.setD2(tmp.getD2());
                            tempDelete.setD3(tmp.getD3());
                            tempDelete.setD4(tmp.getD4());
                            tempDelete.setD5(tmp.getD5());
                            tempDelete.setD6(tmp.getD6());
                            tempDelete.setManualFlag(tmp.getNHAPTAY());
                            tempDelete.setStyle(tmp.getKIEUIN());
                            lstDelete.add(tempDelete);
                        }
                        _serverAPI = new DuLieuNTService();
                        status = _serverAPI.updateKTKSNB(ssKhoa, sMaPgd, "S", _reportDate3, "", "", lstUpdateDate);
                        _serverAPI.deleteKTKSNB(ssKhoa, sMaPgd, "S", _reportDate4, "", "", lstDelete);
                    }

                } else if (stype.equals("1")) {
                    ArrayList<DuLieuNTRowX> lstDelete = new ArrayList<>();
                    for (QT_DULIEU_NT tmp : lstDulieuNt) {
                        DuLieuNTRowX tempDelete = new DuLieuNTRowX();
                        tempDelete.setKey(ssKhoa);
                        tempDelete.setOrderValue(tmp.getTHUTU());
                        tempDelete.setOrderDescription(tmp.getTT_HIENTHI());
                        tempDelete.setName(tmp.getTEN());
                        tempDelete.setCode(tmp.getMA());
                        tempDelete.setMakerId(UserName);
                        tempDelete.setMakerDate(_reportDate11);
                        tempDelete.setAuthoriseId(UserName);
                        tempDelete.setAuthoriseDate(_reportDate11);
                        tempDelete.setReportDate(_reportDate11);
                        tempDelete.setReportYear(tmp.getNAMBC());
                        tempDelete.setPosCode(sMaPgd);
                        tempDelete.setPosFlag(tmp.getCO_TONGHOP());
                        tempDelete.setBranchCode(tmp.getMACN());
                        tempDelete.setD1(tmp.getD1());
                        tempDelete.setD2(tmp.getD2());
                        tempDelete.setD3(tmp.getD3());
                        tempDelete.setD4(tmp.getD4());
                        tempDelete.setD5(tmp.getD5());
                        tempDelete.setD6(tmp.getD6());
                        tempDelete.setManualFlag(tmp.getNHAPTAY());
                        tempDelete.setStyle(tmp.getKIEUIN());
                        lstDelete.add(tempDelete);
                    }
                    _serverAPI = new DuLieuNTService();
                    status = _serverAPI.deleteKTKSNB(ssKhoa, sMaPgd, "S", _reportDate4, "", "", lstDelete);
                    this.pageResult = new ByteArrayInputStream(String.valueOf(status).getBytes(StandardCharsets.UTF_8));
                    return SUCCESS;
                }
            }
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> unlock_c2: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> unlock_c2: " + e.getMessage());
            String code = String.valueOf(3);
            this.pageResult = new ByteArrayInputStream(code.getBytes(StandardCharsets.UTF_8));
            return SUCCESS;
        }
        String code = String.valueOf(200);
        this.pageResult = new ByteArrayInputStream(code.getBytes(StandardCharsets.UTF_8));
        return SUCCESS;
    }

    public String status1() {
        try {
            String D1 = ServletActionContext.getRequest().getParameter("madiemgd");
            String D7 = ServletActionContext.getRequest().getParameter("ngaybc");
            String D8 = ServletActionContext.getRequest().getParameter("type");
            int status = 0;
            if (D8.equals("1")) {
                status = _serverAPI.updateChotSL("KH_HUYEN_DC", D1, "S", D7, "0", UserName, null);
            } else {
                status = _serverAPI.updateChotSL("KH_HUYEN_DC", D1, "S", D7, "2", UserName, null);
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
}
