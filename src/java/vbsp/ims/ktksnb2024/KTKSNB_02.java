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
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.struts2.ServletActionContext;
import vbsp.ims.bcqt.model.QT_DULIEU_NT;
import vbsp.ims.chtrinh_cn.ActionChtrinhcnMain;
import vbsp.ims.dao.DaoConnect;
import vbsp.ims.define.GenericResult;
import vbsp.ims.log.CoreLogger;
import vbsp.ims.chtrinh_cn.DaoChtrinhcnMain;
import vbsp.ims.huydongtk.clsHuyDongTK;
import vbsp.ims.restapi.DuLieuNTRow;
import vbsp.ims.restapi.DuLieuNTRowX;
import vbsp.ims.restapi.DuLieuNTService;
import vbsp.ims.restapi.ListCommune;
import vbsp.ims.restapi.ListOfValue;
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
    private String title2;
    private String title3;
    private String title4;
    private String tento;
    private String tenxa;
    private String skhoa;
    private String ssmapgd;
    private String ssnam;
    private String ssthang;
    private String sngay_sys;
    private String scapbc;
    private List<ListOfValue> lstDmKhac;
//<editor-fold defaultstate="collapsed" desc="khai báo get,set">

    public String getSngay_sys() {
        return sngay_sys;
    }

    public void setSngay_sys(String sngay_sys) {
        this.sngay_sys = sngay_sys;
    }

    public String getScapbc() {
        return scapbc;
    }

    public void setScapbc(String scapbc) {
        this.scapbc = scapbc;
    }

    public List<ListOfValue> getLstDmKhac() {
        return lstDmKhac;
    }

    public void setLstDmKhac(List<ListOfValue> lstDmKhac) {
        this.lstDmKhac = lstDmKhac;
    }

    public String getSsmapgd() {
        return ssmapgd;
    }

    public void setSsmapgd(String ssmapgd) {
        this.ssmapgd = ssmapgd;
    }

    public String getSsnam() {
        return ssnam;
    }

    public void setSsnam(String ssnam) {
        this.ssnam = ssnam;
    }

    public String getSsthang() {
        return ssthang;
    }

    public void setSsthang(String ssthang) {
        this.ssthang = ssthang;
    }

    public String getTitle2() {
        return title2;
    }

    public void setTitle2(String title2) {
        this.title2 = title2;
    }

    public String getTitle3() {
        return title3;
    }

    public void setTitle3(String title3) {
        this.title3 = title3;
    }

    public String getTitle4() {
        return title4;
    }

    public void setTitle4(String title4) {
        this.title4 = title4;
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
            main_pos_username = posMainModel.getMainPosCd();

            String sNam = String.valueOf(Integer.parseInt(hmParameter.get("yearSelect").toString()) - 1);
            String ssNam = hmParameter.get("yearSelect").toString();
            String sThangkt = hmParameter.get("monthSelect").toString();
            final String _reportDate1 = sNam + "1231";

            int year = Integer.parseInt(hmParameter.get("yearSelect").toString());
            int month = Integer.parseInt(sThangkt);
            LocalDate firstDayOfMonth = LocalDate.of(year, month, 1);
            int lastDay = firstDayOfMonth.lengthOfMonth();
            final String _reportDate3 = String.format("%04d%02d%02d", year, month, lastDay);

            ArrayList<LockSendModel> lstData_dky = _serverAPI.getDataLockManual("KH_HUYEN", pos_cd_username, "S", _reportDate1);
            try {
                setChotCic(lstData_dky.get(0).getStatus());
            } catch (Exception e) {
                setChotCic("0");
            }

            if (!chotCic.equals("1")) {
                addActionError("Đơn vị chưa gửi kế hoạch kiểm tra!");
                return ERROR;
            }

            ArrayList<LockSendModel> lstData_tmp = _serverAPI.getDataLockManual("KH_HUYEN_DC", pos_cd_username, "S", _reportDate3);
            try {
                setChotsl(lstData_tmp.get(0).getStatus());
            } catch (Exception e) {
                setChotsl("0");
            }

            List<DuLieuNTRow> lstDataApi1 = _serverAPI.getListKTKSNB_2024("KH_HUYEN", pos_cd_username, "S", _reportDate1, sThangkt);
            List<DuLieuNTRow> lstDataApi2 = _serverAPI.getListKTKSNB_2024("KH_HUYEN_DC", pos_cd_username, "S", _reportDate3, sThangkt);

            Set<String> d3d4SetApi2 = new HashSet<>();

            for (DuLieuNTRow item : lstDataApi2) {
                d3d4SetApi2.add(item.getD3() + "|" + item.getD4());
            }

            if (lstDataApi1 != null) {
                for (DuLieuNTRow item : lstDataApi1) {
                    String d3d4Key = item.getD3() + "|" + item.getD4();
                    if (!d3d4SetApi2.contains(d3d4Key)) {
                        QT_DULIEU_NT row = cvQT_DULIEU_NT(item, pos_cd_username, main_pos_username, sThangkt, sNam, _reportDate1, Grade);
                        lstDulieuNt.add(row);
                    }
                }
            }

            for (DuLieuNTRow item : lstDataApi2) {
                QT_DULIEU_NT row = cvQT_DULIEU_NT(item, pos_cd_username, main_pos_username, sThangkt, ssNam, _reportDate3, Grade);
                lstDulieuNt.add(row);
            }
            if (lstDulieuNt == null || lstDulieuNt.isEmpty()) {
                addActionError("Chưa có dữ liệu kiểm tra!");
                return ERROR;
            }
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> ktksnb2024: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> ktksnb2024: " + e.getMessage());
        }

        return "success_1";
    }

    private QT_DULIEU_NT cvQT_DULIEU_NT(DuLieuNTRow item, String pos_cd_username, String main_pos_username, String sThangkt, String sNam, String _reportDate, String Grade) {
        QT_DULIEU_NT row = new QT_DULIEU_NT();
        try {
            // Populate data fields
            row.setKHOA(item.getKey());

            // Safely parse and set values with null checks
            if (item.getOrderValue() != null) {
                row.setTHUTU(Integer.parseInt(item.getOrderValue()));
            }
            row.setTT_HIENTHI(item.getOrderDescription());
            row.setMA(item.getCode());
            row.setTEN(item.getName());
            row.setCO_TONGHOP(item.getPosFlag());
            row.setNGUOI_NHAP(item.getMakerId());

            // Convert and set report date
            Date reportDate = DateUtil.toDate(item.getReportDate());
            row.setNGAYBC(reportDate);
            row.setNAMBC(item.getReportYear());
            row.setMAPGD(pos_cd_username);
            row.setMACN(main_pos_username);

            // Conditional logic based on Grade
            if ("1".equals(Grade)) {
                row.setD1(item.getD1());
            } else {
                List<ListPosCode> lstPGD_API = _serverAPI.getListPgd(main_pos_username, "");
                for (ListPosCode posRow : lstPGD_API) {
                    if (posRow.getPosCode() != null && posRow.getPosCode().equals(pos_cd_username)) {
                        row.setD1(item.getD1() + " " + posRow.getPosName());
                        break;
                    }
                }
            }
            row.setD2(item.getD2());
            row.setD3(item.getD3());
            row.setD4(item.getD4());
            row.setD5(item.getD5());
            row.setD6(sThangkt);
            row.setD7(sNam);
            row.setD8(_reportDate);
            row.setD9(Grade);
            row.setD10(item.getD7());
            row.setNHAPTAY(item.getManualFlag());
            row.setKIEUIN(item.getStyle());

        } catch (Exception e) {
            // Log the exception for debugging
            System.err.println("Error in cvQT_DULIEU_NT: " + e.getMessage());
            e.printStackTrace();
        }
        return row;
    }

    public String load_c2() {
        try {
            if (!getParaSession()) {
                return ERROR;
            }
            HashMap<String, Object> hmParameter = getParameter();
            posMainModel = listKTNBDA.get_pos_main_pos(UserName, Grade);
            pos_cd_username = posMainModel.getPosCd();
            main_pos_username = posMainModel.getMainPosCd();
            String sNam = String.valueOf(Integer.parseInt(hmParameter.get("yearSelect").toString()) - 1);
            String ssNam = hmParameter.get("yearSelect").toString();
            String sThangkt = hmParameter.get("monthSelect").toString();
            final String _reportDate1 = sNam + "1231";
            int year = Integer.parseInt(hmParameter.get("yearSelect").toString());
            int month = Integer.parseInt(sThangkt);
            LocalDate firstDayOfMonth = LocalDate.of(year, month, 1);
            int lastDay = firstDayOfMonth.lengthOfMonth();
            final String _reportDate3 = String.format("%04d%02d%02d", year, month, lastDay);
            String nghiepvu = hmParameter.get("txtGetData").toString();
            setChotsl_tw(nghiepvu);
            if (chotsl_tw.equals("1")) {
                ArrayList<LockSendModel> lstData_dky = _serverAPI.getDataLockManual("KH_TINH", main_pos_username, "S", _reportDate1);
                try {
                    setChotCic(lstData_dky.get(0).getStatus());
                } catch (Exception e) {
                    setChotCic("0");
                }
                if (!chotCic.equals("2")) {
                    addActionError("Đơn vị chưa gửi kế hoạch kiểm tra!");
                    return ERROR;
                }
                ArrayList<LockSendModel> lstData_tmp = _serverAPI.getDataLockManual("KH_TINH_DC", main_pos_username, "S", _reportDate3);
                try {
                    setChotsl(lstData_tmp.get(0).getStatus());
                } catch (Exception e) {
                    setChotsl("0");
                }
                lstPGD_API = _serverAPI.getListPgd(main_pos_username, "");
                for (ListPosCode item : lstPGD_API) {
                    try {
                        List<DuLieuNTRow> lstDataApi1 = _serverAPI.getListKTKSNB_2024("KH_TINH", item.getPosCode(), "S", _reportDate1, sThangkt);
                        List<DuLieuNTRow> lstDataApi2 = _serverAPI.getListKTKSNB_2024("KH_TINH_DC", item.getPosCode(), "S", _reportDate3, sThangkt);

                        // Set for d3 and d4 combinations from API 2
                        Set<String> d3d4SetApi2 = new HashSet<>();
                        for (DuLieuNTRow api2Item : lstDataApi2) {
                            d3d4SetApi2.add(api2Item.getD3() + "|" + api2Item.getD4());
                        }

                        // Add unique rows from API 1
                        if (lstDataApi1 != null) {
                            for (DuLieuNTRow api1Item : lstDataApi1) {
                                String d3d4Key = api1Item.getD3() + "|" + api1Item.getD4();
                                if (!d3d4SetApi2.contains(d3d4Key)) {
                                    QT_DULIEU_NT row = cvQT_DULIEU_NT(api1Item, item.getPosCode(), main_pos_username, sThangkt, sNam, _reportDate1, Grade);
                                    lstDulieuNt.add(row);
                                }
                            }
                        }

                        // Thêm tất cả dữ liệu từ API 2
                        for (DuLieuNTRow api2Item : lstDataApi2) {
                            QT_DULIEU_NT row = cvQT_DULIEU_NT(api2Item, item.getPosCode(), main_pos_username, sThangkt, ssNam, _reportDate3, Grade);
                            lstDulieuNt.add(row);
                        }

                        // Kiểm tra dữ liệu đầu ra
                        if (lstDulieuNt == null || lstDulieuNt.isEmpty()) {
                            addActionError("Chưa có dữ liệu kiểm tra!");
                            return ERROR;
                        }
                    } catch (Exception e) {
                        CoreLogger.error(this.getClass().getName() + " Exception -> Processing item: " + e.getMessage());
                        System.err.println(this.getClass().getName() + " Exception -> Processing item: " + e.getMessage());
                    }
                }

            } else {
                lstPGD_API = _serverAPI.getListPgd(main_pos_username, "");
                if (lstPGD_API == null || lstPGD_API.isEmpty()) {
                    addActionError("Lỗi khi gọi API!");
                    return ERROR;
                }
                String sKye = "KH_HUYEN_DC";
//                if (nghiepvu.equals("2")) {
//                    sKye = "KH_HUYEN_DC";
//                } else {
//                    sKye = "KH_TINH_DC";
//                }
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
            CoreLogger.error(this.getClass().getName() + " Exception -> ktksnb2024: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> ktksnb2024: " + e.getMessage());
        }
        return chotsl_tw.equals("1") ? "success_1" : "success_2";
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
            String ssNghiepvu = "2";
//                    hmParameter.get("txtGetData3").toString();
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

            String sskhoa = (String) ActionContext.getContext().getSession().get("dc_khoa");
            String smapgd = (String) ActionContext.getContext().getSession().get("dc_mapgd");
            String sthang = (String) ActionContext.getContext().getSession().get("dc_thang");
            String snam;
            if (sskhoa.equals("KH_HUYEN") || sskhoa.equals("KH_TINH")) {
                snam = String.valueOf(Integer.parseInt((String) ActionContext.getContext().getSession().get("dc_nam")) + 1);
            } else {
                snam = (String) ActionContext.getContext().getSession().get("dc_nam");
            }
            if (Grade.equals("1")) {
                setSkhoa("KH_HUYEN_DC");
            } else {
                setSkhoa("KH_TINH_DC");
            }
//            System.out.println("skhoa= " + skhoa + " smapgd= " + smapgd + " sthang= " + sthang + " snam= " + snam);
            int year = Integer.parseInt(snam);
            int month = Integer.parseInt(sthang);
            LocalDate firstDayOfMonth = LocalDate.of(year, month, 1);
            int lastDay = firstDayOfMonth.lengthOfMonth();
            final String _reportDate2 = String.format("%04d%02d%02d", year, month, lastDay);
            final String _reportDate1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS").format(new Date());
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
                tempadd.setPosCode(tmp.getMAPGD());
                tempadd.setPosFlag(tmp.getCO_TONGHOP());
                tempadd.setBranchCode(tmp.getMACN());
                tempadd.setD1(tmp.getD1());
                tempadd.setD2(tmp.getD2());
                tempadd.setD3(tmp.getD3());
                tempadd.setD4(tmp.getD4());
                tempadd.setD5(tmp.getD5());
                tempadd.setD6(tmp.getD6());
                tempadd.setD7("1");
                tempadd.setManualFlag(tmp.getNHAPTAY());
                tempadd.setStyle(tmp.getKIEUIN());
                lstUpdateDate.add(tempadd);
            }
            _serverAPI = new DuLieuNTService();
            int status = _serverAPI.updateKTKSNB(skhoa, smapgd, "S", _reportDate2, "", "", lstUpdateDate);
            if (status == 200) {
                String code = String.valueOf(status);
                this.pageResult = new ByteArrayInputStream(code.getBytes(StandardCharsets.UTF_8));
                return SUCCESS;
            }
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> save ktksnb 2024: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> save ktksnb 2024: " + e.getMessage());
            String code = String.valueOf(status);
            this.pageResult = new ByteArrayInputStream(code.getBytes(StandardCharsets.UTF_8));
            return SUCCESS;
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
            System.out.println("vào send");
            HashMap hmParameter = getParameter();
            String sthang = hmParameter.get("monthSelect").toString();
            String snam = hmParameter.get("yearSelect").toString();
            String smapgd;
            String sNam = String.valueOf(Integer.parseInt(snam) - 1);
            int year = Integer.parseInt(hmParameter.get("yearSelect").toString());
            int month = Integer.parseInt(hmParameter.get("monthSelect").toString());
            LocalDate firstDayOfMonth = LocalDate.of(year, month, 1);
            int lastDay = firstDayOfMonth.lengthOfMonth();
            final String _reportDate2 = String.format("%04d%02d%02d", year, month, lastDay);
            posMainModel = listKTNBDA.get_pos_main_pos(UserName, Grade);
            pos_cd_username = posMainModel.getPosCd();
            main_pos_username = posMainModel.getMainPosCd();
            List<DuLieuNTRow> lstDataApi1 = new ArrayList<>();
            List<DuLieuNTRow> lstDataApi2 = new ArrayList<>();
            if (Grade.equals("1")) {
                skhoa = "KH_HUYEN_DC";
                smapgd = pos_cd_username;
                lstDataApi1 = _serverAPI.getListKTKSNB_2024("KH_HUYEN", pos_cd_username, "S", sNam + "1231", sthang);
                lstDataApi2 = _serverAPI.getListKTKSNB_2024("KH_HUYEN_DC", pos_cd_username, "S", _reportDate2, sthang);

            } else {
                skhoa = "KH_TINH_DC";
                smapgd = main_pos_username;
                lstPGD_API = _serverAPI.getListPgd(main_pos_username, "");
                for (ListPosCode item : lstPGD_API) {
                    try {
                        List<DuLieuNTRow> tempData = _serverAPI.getListKTKSNB_2024("KH_TINH", item.getPosCode(), "S", sNam + "1231", sthang);
                        if (tempData != null && !tempData.isEmpty()) {
                            lstDataApi1.addAll(tempData);
                        }
                    } catch (Exception e) {
                        System.err.println("Lỗi khi xử lý PGD: " + item.getPosCode());
                        e.printStackTrace();
                    }
                }
                for (ListPosCode item : lstPGD_API) {
                    try {
                        List<DuLieuNTRow> tempData = _serverAPI.getListKTKSNB_2024("KH_TINH_DC", item.getPosCode(), "S", _reportDate2, sthang);
                        if (tempData != null && !tempData.isEmpty()) {
                            lstDataApi2.addAll(tempData);
                        }
                    } catch (Exception e) {
                        System.err.println("Lỗi khi xử lý PGD: " + item.getPosCode());
                        e.printStackTrace();
                    }
                }
            }
            ArrayList<LockSendModel> lstData_tmp = _serverAPI.getDataLockManual(skhoa, smapgd, "S", _reportDate2);

            try {
                setChotsl(lstData_tmp.get(0).getStatus());
            } catch (Exception e) {
                setChotsl("0");
            }
            int status;

            if (!lstDataApi1.isEmpty() || !lstDataApi2.isEmpty()) {
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
            String ssThang;
            String stype = ServletActionContext.getRequest().getParameter("type");
            String sNgaybc = ServletActionContext.getRequest().getParameter("sNam");
            String sNam = sNgaybc.substring(sNgaybc.length() - 4);
            String ssKhoa;
            String ssCanbo22 = "";
            String ssDieuchinh = "";
            if (stype.equals("1") || stype.equals("2")) {
                ssThang = ServletActionContext.getRequest().getParameter("ssThang");
                ssKhoa = ServletActionContext.getRequest().getParameter("skhoa");
            } else {
                String ssKhoa22 = ServletActionContext.getRequest().getParameter("skhoa");
//                System.out.println("ssKhoa22= " + ssKhoa22);
                String[] values = ssKhoa22.split("\\-");
                ssKhoa = values[0];
                ssThang = values[1];
                if (stype.equals("3")) {
                    ssCanbo22 = ServletActionContext.getRequest().getParameter("ssThang");
                } else {
                    ssDieuchinh = ServletActionContext.getRequest().getParameter("ssThang");
                }
            }
//            System.out.println("ssKhoa= " + ssKhoa + " ssThang= " + ssThang + " ssCanbo22= " + ssCanbo22);
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
            if (schotsl.equals("1") || schotsl.equals("2")) {
                status = 100;
                this.pageResult = new ByteArrayInputStream(String.valueOf(status).getBytes(StandardCharsets.UTF_8));
                return SUCCESS;
            } else {
                switch (stype) {
                    case "2":
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
                            this.pageResult = new ByteArrayInputStream(String.valueOf(status).getBytes(StandardCharsets.UTF_8));
                            return SUCCESS;
                        }
                    case "1":
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
                    case "3":
                    case "4":
                        ArrayList<DuLieuNTRowX> lstUpdateDate = new ArrayList<>();
                        for (QT_DULIEU_NT tmp : lstDulieuNt) {
                            DuLieuNTRowX tempUpdate = new DuLieuNTRowX();
                            tempUpdate.setKey(ssKhoa);
                            tempUpdate.setOrderValue(tmp.getTHUTU());
                            tempUpdate.setOrderDescription(tmp.getTT_HIENTHI());
                            tempUpdate.setName(tmp.getTEN());
                            tempUpdate.setCode(tmp.getMA());
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
                            if (!stype.equals("3")) {
                                tempUpdate.setD3(ssDieuchinh);
                            } else {
                                tempUpdate.setD3(tmp.getD3());
                            }
                            if (stype.equals("3")) {
                                tempUpdate.setD4(ssCanbo22);
                            } else {
                                tempUpdate.setD4(tmp.getD4());
                            }
                            tempUpdate.setD5(sThang);
                            tempUpdate.setD6(tmp.getD6());
                            tempUpdate.setManualFlag(tmp.getNHAPTAY());
                            tempUpdate.setStyle(tmp.getKIEUIN());
                            lstUpdateDate.add(tempUpdate);
                        }
                        _serverAPI = new DuLieuNTService();
                        status = _serverAPI.updateKTKSNB(ssKhoa, sMaPgd, "S", _reportDate3, "", "", lstUpdateDate);
                        this.pageResult = new ByteArrayInputStream(String.valueOf(status).getBytes(StandardCharsets.UTF_8));
                        return SUCCESS;
                    case "5":
                        ArrayList<DuLieuNTRowX> lstUpdateDate1 = new ArrayList<>();
                        ArrayList<DuLieuNTRowX> lstDelete1 = new ArrayList<>();
                        for (QT_DULIEU_NT tmp : lstDulieuNt) {
                            DuLieuNTRowX tempUpdate = new DuLieuNTRowX();
                            tempUpdate.setKey(ssKhoa);
                            tempUpdate.setOrderValue(tmp.getTHUTU());
                            tempUpdate.setOrderDescription(tmp.getTT_HIENTHI());
                            tempUpdate.setName(tmp.getTEN());
                            tempUpdate.setCode(tmp.getMA());
                            tempUpdate.setMakerId(UserName);
                            tempUpdate.setMakerDate(_reportDate11);
                            tempUpdate.setAuthoriseId(UserName);
                            tempUpdate.setAuthoriseDate(_reportDate11);
                            tempUpdate.setReportDate(_reportDate11);
                            tempUpdate.setReportYear(tmp.getNAMBC());
                            tempUpdate.setPosCode(ssDieuchinh);
                            tempUpdate.setPosFlag(tmp.getCO_TONGHOP());
                            tempUpdate.setBranchCode(tmp.getMACN());
                            tempUpdate.setD1(tmp.getD1());
                            tempUpdate.setD2(tmp.getD2());
                            tempUpdate.setD3(ssDieuchinh);
                            tempUpdate.setD4(tmp.getD4());
                            tempUpdate.setD5(sThang);
                            tempUpdate.setD6(tmp.getD6());
                            tempUpdate.setManualFlag(tmp.getNHAPTAY());
                            tempUpdate.setStyle(tmp.getKIEUIN());
                            lstUpdateDate1.add(tempUpdate);

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
                            lstDelete1.add(tempDelete);
                        }
                        _serverAPI = new DuLieuNTService();
                        status = _serverAPI.updateKTKSNB(ssKhoa, ssDieuchinh, "S", _reportDate3, "", "", lstUpdateDate1);
                        _serverAPI.deleteKTKSNB(ssKhoa, sMaPgd, "S", _reportDate3, "", "", lstDelete1);
                        this.pageResult = new ByteArrayInputStream(String.valueOf(status).getBytes(StandardCharsets.UTF_8));
                        return SUCCESS;
                    default:
                        break;
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
            int status;
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

    public String popupTable() throws Exception {
        try {
            String dc_khoa = ServletActionContext.getRequest().getParameter("dc_khoa");
            String dc_maxa = ServletActionContext.getRequest().getParameter("dc_maxa");
            String dc_macb = ServletActionContext.getRequest().getParameter("dc_macb");
            String dc_thang = ServletActionContext.getRequest().getParameter("dc_thang");
            String dc_bank = ServletActionContext.getRequest().getParameter("dc_mapgd");
            String[] values = dc_bank.split("\\-");
            String dc_mapgd = values[0];
            String dc_macn = values[1];
            String dc_nam = ServletActionContext.getRequest().getParameter("dc_nam");
            String dc_cap = ServletActionContext.getRequest().getParameter("dc_cap");
            String dc_chot = ServletActionContext.getRequest().getParameter("dc_chot");
            setGrade(dc_cap);
            setScapbc(dc_cap);
            clsHuyDongTK Canbo2024 = new clsHuyDongTK();
            setLstCanBo(Canbo2024.getCanBo(dc_mapgd + "KTKSNB", UserName));
            int year = Integer.parseInt(dc_nam);
            int month = Integer.parseInt(dc_thang);
            LocalDate firstDayOfMonth = LocalDate.of(year, month, 1);
            int lastDay = firstDayOfMonth.lengthOfMonth();
            final String _reportDate3 = String.format("%04d%02d%02d", year, month, lastDay);
            String conditions = "D3=" + dc_maxa + "|D4=" + dc_macb + "|D5=" + dc_thang + "|";
            String ssnam;
            String _reportDate;
            if (dc_khoa.equals("KH_HUYEN") || dc_khoa.equals("KH_TINH")) {
                setTitle4(String.valueOf(Integer.parseInt(dc_nam) + 1));
                ssnam = dc_nam;
                setCheck_cn("1");
                _reportDate = dc_nam + "1231";
            } else {
                setTitle4(dc_nam);
                ssnam = String.valueOf(Integer.parseInt(dc_nam) - 1);
                setCheck_cn("0");
                _reportDate = _reportDate3;
            }
            lstDmKhac = _serverAPI.getListOfValue("199", dc_macn);
            try {
                setSngay_sys(lstDmKhac.get(0).getValue());
            } catch (Exception e) {
                setSngay_sys("0");
            }
            setSsthang(dc_thang);
            String sskhoas;
            if (dc_cap.equals("1")) {
                sskhoas = "KH_HUYEN_DC";
                lstXa_API = _serverAPI.getListXa("", "", "", dc_mapgd);
                for (ListCommune row : lstXa_API) {
                    if (row.getCommuneCode() != null && row.getCommuneCode().equals(dc_maxa)) {
                        String communeCode = (String) row.getCommuneCode();
                        String communeName = (String) row.getCommuneName();
                        setTitle1(communeCode + " - " + communeName);
                        break;
                    }
                }
                if (!dc_macb.equals("99999")) {
                    List<DuLieuNTRow> lstDataApi1 = _serverAPI.getDataKTKSNB_2024("CB_KTKSNB", dc_mapgd, "S", ssnam + "1231", "", "0");
                    if (lstDataApi1 != null && !lstDataApi1.isEmpty()) {
                        for (DuLieuNTRow item : lstDataApi1) {
                            if (item.getD1() != null && item.getD1().equals(dc_macb)) {
                                String sD2 = item.getD2() != null ? item.getD2() : "";
                                String sD3 = item.getD3() != null ? item.getD3() : "";
                                setTitle2("Cán bộ kiểm tra " + sD2 + " - " + sD3);
                                break;
                            }
                        }
                    }
                } else {
                    setTitle2("Đoàn kiểm tra của NHCSXH cấp huyện đối với cấp xã");
                }
                setTitle3("Xã kiểm tra " + title1 + " -/- " + title2 + " -/- Kiểm tra tháng " + dc_thang + " năm " + title4);
            } else {
                sskhoas = "KH_TINH_DC";
                lstPGD_API = _serverAPI.getListPgd(dc_macn, "");
                for (ListPosCode row : lstPGD_API) {
                    if (row.getPosCode() != null && row.getPosCode().equals(dc_mapgd)) {
                        String posCode = (String) row.getPosCode();
                        String posName = (String) row.getPosName();
                        setTitle1(posCode + " - " + posName);
                        break;
                    }
                }
                setTitle3(title1 + " -/- Kiểm tra tháng " + dc_thang + " năm " + title4);
            }
            setChotsl(dc_chot);
            ActionContext.getContext().getSession().put("dc_khoa", dc_khoa);
            ActionContext.getContext().getSession().put("dc_mapgd", dc_mapgd);
            ActionContext.getContext().getSession().put("dc_thang", dc_thang);
            ActionContext.getContext().getSession().put("dc_nam", dc_nam);
            lstData_Api = _serverAPI.getDataKTKSNB_2024(dc_khoa, dc_mapgd, "S", _reportDate, conditions, "0");
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
                    row.setD3(dc_maxa);
                    row.setD4(dc_macb);
                    row.setD5(dc_thang);
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

    public String delete() {
        try {
            String dc_khoa = ServletActionContext.getRequest().getParameter("dc_khoa");
            String dc_maxa = ServletActionContext.getRequest().getParameter("dc_maxa");
            String dc_macb = ServletActionContext.getRequest().getParameter("dc_macb");
            String dc_thang = ServletActionContext.getRequest().getParameter("dc_thang");
            String dc_mapgd = ServletActionContext.getRequest().getParameter("dc_mapgd");
            String dc_nam = ServletActionContext.getRequest().getParameter("dc_nam");
            String dc_chot = ServletActionContext.getRequest().getParameter("dc_chot");
            String stype = ServletActionContext.getRequest().getParameter("type");;
            int year = Integer.parseInt(dc_nam);
            int month = Integer.parseInt(dc_thang);
            LocalDate firstDayOfMonth = LocalDate.of(year, month, 1);
            int lastDay = firstDayOfMonth.lengthOfMonth();
            final String _reportDate3 = String.format("%04d%02d%02d", year, month, lastDay);
            String conditions = "D3=" + dc_maxa + "|D4=" + dc_macb + "|D5=" + dc_thang + "|";
            if (stype.equals("1")) {
                lstData_Api = _serverAPI.getDataKTKSNB_2024(dc_khoa, dc_mapgd, "S", _reportDate3, conditions, "0");
                if (!dc_chot.equals("0") || null == dc_chot) {
                    String code = String.valueOf(100);
                    this.pageResult = new ByteArrayInputStream(code.getBytes(StandardCharsets.UTF_8));
                    return SUCCESS;
                }
                ArrayList<DuLieuNTRowX> lstDelete = new ArrayList<>();
                for (DuLieuNTRow tmp : lstData_Api) {
                    DuLieuNTRowX tempDelete = new DuLieuNTRowX();
                    tempDelete.setKey(tmp.getKey());
                    tempDelete.setOrderValue(Integer.valueOf(tmp.getOrderValue()));
                    tempDelete.setOrderDescription(tmp.getOrderDescription());
                    tempDelete.setName(tmp.getName());
                    tempDelete.setCode(tmp.getCode());
                    tempDelete.setMakerId(tmp.getMakerId());
                    tempDelete.setMakerDate(tmp.getMakerDate());
                    tempDelete.setAuthoriseId(tmp.getAuthoriseId());
                    tempDelete.setAuthoriseDate(tmp.getAuthoriseDate());
                    tempDelete.setReportDate(tmp.getReportDate());
                    tempDelete.setReportYear(tmp.getReportYear());
                    tempDelete.setPosCode(tmp.getPosCode());
                    tempDelete.setPosFlag(tmp.getPosFlag());
                    tempDelete.setBranchCode(tmp.getBranchCode());
                    tempDelete.setD1(tmp.getD1());
                    tempDelete.setD2(tmp.getD2());
                    tempDelete.setD3(tmp.getD3());
                    tempDelete.setD4(tmp.getD4());
                    tempDelete.setD5(tmp.getD5());
                    tempDelete.setD6(tmp.getD6());
                    tempDelete.setD7(tmp.getD7());
                    tempDelete.setD8(tmp.getD8());
                    tempDelete.setD9(tmp.getD9());
                    tempDelete.setD10(tmp.getD10());
                    tempDelete.setD11(tmp.getD11());

                    lstDelete.add(tempDelete);
                }
                _serverAPI = new DuLieuNTService();
                int status = _serverAPI.deleteKTKSNB(dc_khoa, dc_mapgd, "S", _reportDate3, "", "", lstDelete);
                if (status != 200) {
                    String code = String.valueOf(1);
                    this.pageResult = new ByteArrayInputStream(code.getBytes(StandardCharsets.UTF_8));
                    return SUCCESS;
                }
            } else {
                lstData_Api = _serverAPI.getDataKTKSNB_2024(dc_khoa, dc_mapgd, "S", dc_nam + "1231", conditions, "0");
                if (!dc_chot.equals("0") || null == dc_chot) {
                    String code = String.valueOf(100);
                    this.pageResult = new ByteArrayInputStream(code.getBytes(StandardCharsets.UTF_8));
                    return SUCCESS;
                }
                ArrayList<DuLieuNTRowX> lstUpdate = new ArrayList<>();
                for (DuLieuNTRow tmp : lstData_Api) {
                    DuLieuNTRowX tempDelete = new DuLieuNTRowX();
                    tempDelete.setKey(tmp.getKey());
                    tempDelete.setOrderValue(Integer.valueOf(tmp.getOrderValue()));
                    tempDelete.setOrderDescription(tmp.getOrderDescription());
                    tempDelete.setName(tmp.getName());
                    tempDelete.setCode(tmp.getCode());
                    tempDelete.setMakerId(tmp.getMakerId());
                    tempDelete.setMakerDate(tmp.getMakerDate());
                    tempDelete.setAuthoriseId(tmp.getAuthoriseId());
                    tempDelete.setAuthoriseDate(tmp.getAuthoriseDate());
                    tempDelete.setReportDate(tmp.getReportDate());
                    tempDelete.setReportYear(tmp.getReportYear());
                    tempDelete.setPosCode(tmp.getPosCode());
                    tempDelete.setPosFlag(tmp.getPosFlag());
                    tempDelete.setBranchCode(tmp.getBranchCode());
                    tempDelete.setD1(tmp.getD1());
                    tempDelete.setD2(tmp.getD2());
                    tempDelete.setD3(tmp.getD3());
                    tempDelete.setD4(tmp.getD4());
                    tempDelete.setD5(tmp.getD5());
                    tempDelete.setD6(tmp.getD6());
                    if (tmp.getD7().equals("2")) {
                        tempDelete.setD7("1");
                    } else {
                        tempDelete.setD7("2");
                    }
                    tempDelete.setD8(tmp.getD8());
                    tempDelete.setD9(tmp.getD9());
                    tempDelete.setD10(tmp.getD10());
                    tempDelete.setD11(tmp.getD11());

                    lstUpdate.add(tempDelete);
                }
                _serverAPI = new DuLieuNTService();
                int status = _serverAPI.updateKTKSNB(dc_khoa, dc_mapgd, "S", dc_nam + "1231", "", "", lstUpdate);
                if (status != 200) {
                    String code = String.valueOf(1);
                    this.pageResult = new ByteArrayInputStream(code.getBytes(StandardCharsets.UTF_8));
                    return SUCCESS;
                }
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
