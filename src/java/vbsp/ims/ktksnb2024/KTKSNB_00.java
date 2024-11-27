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
import vbsp.ims.chtrinh_cn.ActionChtrinhcnMain;
import vbsp.ims.dao.DaoConnect;
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
public class KTKSNB_00 extends ActionChtrinhcnMain
        implements NhaptaycnFunction {
//<editor-fold defaultstate="collapsed" desc="khai biến">

    Service_GQVL2023 _server;
    DaoChtrinhcnMain _serverlocal;
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
    private String sCode;
    //</editor-fold>
//<editor-fold defaultstate="collapsed" desc="khai báo get,set">

    public String getsCode() {
        return sCode;
    }

    public void setsCode(String sCode) {
        this.sCode = sCode;
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

    public DaoChtrinhcnMain getServerlocal() {
        return _serverlocal;
    }

    public void setServerlocal(DaoChtrinhcnMain _serverlocal) {
        this._serverlocal = _serverlocal;
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
//            if (pos_cd_username.equals(main_pos_username)) {
//                addActionError("Chú ý: Hội sở tỉnh không nhập tại cấp (1) PGD!");
//                return ERROR;
//            }
            String dateStr = hmParameter.get("ngay_bc").toString();
            final String _reportDate = new SimpleDateFormat("yyyyMMdd").format(new SimpleDateFormat("dd-MMM-yyyy").parse(dateStr));
            ArrayList<LockSendModel> lstData_tmp = _serverAPI.getDataLockManual("CB_KTKSNB", pos_cd_username, "S", _reportDate);
            try {
                setChotsl(lstData_tmp.get(0).getStatus());
            } catch (Exception e) {
                setChotsl("0");
            }
            DaoChtrinhcnMain daoMain = new DaoChtrinhcnMain();
            lstData = daoMain.getCanbo_Ktksnb(conn, "AAA1", dateStr, UserName, pos_cd_username, Grade);
            if (lstData == null || lstData.isEmpty()) {
                setChotsl_tw("0");
            } else {
                setChotsl_tw("1");
            }
            lstDulieuNt = daoMain.getCanbo_Ktksnb(conn, "AAA", dateStr, UserName, pos_cd_username, Grade);
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

            HashMap<String, Object> hmParameter = getParameter();
            String dateStr = hmParameter.get("ngay_bc").toString();
            posMainModel = listKTNBDA.get_pos_main_pos(UserName, Grade);
            pos_cd_username = posMainModel.getPosCd();
            main_pos_username = posMainModel.getMainPosCd();

            String _reportDate = new SimpleDateFormat("yyyyMMdd").format(new SimpleDateFormat("dd-MMM-yyyy").parse(dateStr));

            // Retrieve PGD list
            lstPGD_API = _serverAPI.getListPgd(main_pos_username, "");
            if (lstPGD_API == null || lstPGD_API.isEmpty()) {
                addActionError("Lỗi khi gọi API!");
                return ERROR;
            }

            for (ListPosCode item : lstPGD_API) {
                lstData_Api = _serverAPI.getDataKTKSNB("CB_KTKSNB", item.getPosCode(), "S", _reportDate, "", "0");

                for (DuLieuNTRow dataItem : lstData_Api) {
                    try {
                        if ("1".equals(dataItem.getD14())) {
                            QT_DULIEU_NT row = new QT_DULIEU_NT();
                            row.setKHOA(dataItem.getKey());
                            row.setTHUTU(Integer.parseInt(dataItem.getOrderValue()));
                            row.setTT_HIENTHI(dataItem.getOrderDescription());
                            row.setMA(dataItem.getCode());
                            row.setTEN(dataItem.getName());
                            row.setCO_TONGHOP(dataItem.getPosFlag());
                            row.setNGUOI_NHAP(dataItem.getMakerId());
                            Date reportDate = DateUtil.toDate(dataItem.getReportDate());
                            row.setNGAYBC(reportDate);
                            row.setNAMBC(dataItem.getReportYear());
                            row.setMAPGD(pos_cd_username);
                            row.setMACN(main_pos_username);
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
                            row.setD11(dataItem.getD11());
                            row.setD12(dataItem.getD12());
                            row.setD13(dataItem.getD13());
                            row.setD14(dataItem.getD14());
                            row.setD15(dataItem.getD15());
                            row.setD16(dataItem.getD16());
                            row.setD17(item.getPosCode());
                            row.setNHAPTAY(dataItem.getManualFlag());
                            row.setKIEUIN(dataItem.getStyle());

                            lstDulieuNt.add(row);
                        }
                    } catch (Exception e) {
                        CoreLogger.error("Error processing row for posCode " + item.getPosCode() + ": " + e.getMessage());
                    }
                }
            }

            // Sort the list by MAPGD
            lstDulieuNt.sort(Comparator.comparingInt((QT_DULIEU_NT obj) -> Integer.parseInt(obj.getMAPGD())));

        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> tin dung 2024: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> tin dung 2024: " + e.getMessage());
            addActionError("Đã xảy ra lỗi trong quá trình tải dữ liệu.");
            return ERROR;
        }
        return "success_2";
    }
    
     public String load_c3() {
        try {
            if (!getParaSession()) {
                return ERROR;
            }

            HashMap<String, Object> hmParameter = getParameter();
            Connection conn = new DaoConnect().getConnect();
            posMainModel = listKTNBDA.get_pos_main_pos(UserName, Grade);
            pos_cd_username = posMainModel.getPosCd();
            main_pos_username = posMainModel.getMainPosCd();
//            if (pos_cd_username.equals(main_pos_username)) {
//                addActionError("Chú ý: Hội sở tỉnh không nhập tại cấp (1) PGD!");
//                return ERROR;
//            }
            String dateStr = hmParameter.get("ngay_bc").toString();
            final String _reportDate = new SimpleDateFormat("yyyyMMdd").format(new SimpleDateFormat("dd-MMM-yyyy").parse(dateStr));
            ArrayList<LockSendModel> lstData_tmp = _serverAPI.getDataLockManual("CB_KTKSNB", pos_cd_username, "S", _reportDate);
            try {
                setChotsl(lstData_tmp.get(0).getStatus());
            } catch (Exception e) {
                setChotsl("0");
            }
            DaoChtrinhcnMain daoMain = new DaoChtrinhcnMain();
            lstDulieuNt = daoMain.getCanbo_Ktksnb(conn, "AAA2", dateStr, UserName, pos_cd_username, Grade);
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> ktksnb2024: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> ktksnb2024: " + e.getMessage());
        }

        return "success_3";
    }
     
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
            DaoChtrinhcnMain daoMain = new DaoChtrinhcnMain();
            HashMap hmParameter = getParameter();
            String dateStr = hmParameter.get("ngay_bc").toString();
            final String _reportDate = new SimpleDateFormat("yyyyMMdd").format(new SimpleDateFormat("dd-MMM-yyyy").parse(dateStr));
            final String _reportDate1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS").format(new Date());
            posMainModel = listKTNBDA.get_pos_main_pos(UserName, Grade);
            pos_cd_username = posMainModel.getPosCd();
            main_pos_username = posMainModel.getMainPosCd();
            ArrayList<QT_DULIEU_NT> lstLocalDataUpdate = new ArrayList<>();
            ArrayList<DuLieuNTRowX> lstUpdateDate = new ArrayList<>();
            for (QT_DULIEU_NT tmp : lstDulieuNt) {
                UUID uuid = UUID.randomUUID(); // Tạo UUID mới
                long timestamp = System.currentTimeMillis(); // Lấy timestamp hiện tại
                String sysGuid = uuid.toString().replace("-", "") + timestamp;
                setsCode(sysGuid);
                QT_DULIEU_NT temlocal = new QT_DULIEU_NT();
                temlocal.setKHOA("CB_KTKSNB");
                temlocal.setTHUTU(tmp.getTHUTU());
                temlocal.setTT_HIENTHI(tmp.getTT_HIENTHI());
                temlocal.setTEN(tmp.getTEN());
                temlocal.setMA(tmp.getMA() == null || tmp.getMA().equals("") ? sCode : tmp.getMA());
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
                lstLocalDataUpdate.add(temlocal);

                DuLieuNTRowX tempadd = new DuLieuNTRowX();
                tempadd.setKey("CB_KTKSNB");
                tempadd.setOrderValue(tmp.getTHUTU());
                tempadd.setOrderDescription(tmp.getTT_HIENTHI());
                tempadd.setName(tmp.getTEN());
                tempadd.setCode(tmp.getMA() == null || tmp.getMA().equals("") ? sCode : tmp.getMA());
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

                lstUpdateDate.add(tempadd);
            }

            int status = _serverAPI.updateKTKSNB("CB_KTKSNB", pos_cd_username, "S", _reportDate, "", "", lstUpdateDate);
            if (status == 200) {
                if (!daoMain.save_Canbo_ktksnb("CB_KTKSNB", dateStr, UserName, pos_cd_username, lstLocalDataUpdate)) {
                    addActionError("Bạn chưa lưu được báo cáo tại chi nhánh vui lòng liên hệ quản trị viên!");
                    String code = String.valueOf(2);
                    this.pageResult = new ByteArrayInputStream(code.getBytes(StandardCharsets.UTF_8));
                    return SUCCESS;

                }
            }
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> bao so 3: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> bao so 3: " + e.getMessage());
        }
        addActionMessage("Bạn đã lưu dữ liệu thành công");
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
            Connection conn = new DaoConnect().getConnect();
            HashMap hmParameter = getParameter();
            String dateStr = hmParameter.get("ngay_bc").toString();
            final String _reportDate = new SimpleDateFormat("yyyyMMdd").format(new SimpleDateFormat("dd-MMM-yyyy").parse(dateStr));
            final String _reportDate1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS").format(new Date());
            posMainModel = listKTNBDA.get_pos_main_pos(UserName, Grade);
            pos_cd_username = posMainModel.getPosCd();
            main_pos_username = posMainModel.getMainPosCd();
            DaoChtrinhcnMain daoMain = new DaoChtrinhcnMain();
            lstData = daoMain.getCanbo_Ktksnb(conn, "AAA1", dateStr, UserName, pos_cd_username, Grade);
            ArrayList<DuLieuNTRowX> lstUpdateDate = new ArrayList<>();
            for (QT_DULIEU_NT tmp : lstData) {
                DuLieuNTRowX tempadd = new DuLieuNTRowX();
                tempadd.setKey("CB_KTKSNB");
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
                tempadd.setPosCode(tmp.getMAPGD());
                tempadd.setPosFlag(tmp.getCO_TONGHOP());
                tempadd.setBranchCode(tmp.getMACN());
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

                lstUpdateDate.add(tempadd);
            }

            _serverAPI = new DuLieuNTService();
            int status = _serverAPI.updateKTKSNB("CB_KTKSNB", pos_cd_username, "S", _reportDate, "", "", lstUpdateDate);
            System.out.println(" số dòng " + lstUpdateDate.size());
            if (status == 200) {
                _serverAPI.updateChotSL("CB_KTKSNB", pos_cd_username, "S", _reportDate, "1", UserName, null);
                String code = String.valueOf(200);
                this.pageResult = new ByteArrayInputStream(code.getBytes(StandardCharsets.UTF_8));
                return SUCCESS;
            } else {
                String code = String.valueOf(status);
                this.pageResult = new ByteArrayInputStream(code.getBytes(StandardCharsets.UTF_8));
                return ERROR;
            }
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> send ktksnb 2024: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> send ktksnb 2024: " + e.getMessage());
        }
        String code = String.valueOf(200);
        this.pageResult = new ByteArrayInputStream(code.getBytes(StandardCharsets.UTF_8));
        return SUCCESS;
    }

    public String status() {
        try {
            String D1 = ServletActionContext.getRequest().getParameter("madiemgd");
            String D7 = ServletActionContext.getRequest().getParameter("ngaybc");
            String D8 = ServletActionContext.getRequest().getParameter("type");
            int status = _serverAPI.updateChotSL("CB_KTKSNB", D1, "S", D7, "0", UserName, null);
            if (status == 200) {
                ArrayList<DuLieuNTRowX> lstUpdateDate = new ArrayList<>();
                lstData_Api = _serverAPI.getDataKTKSNB("CB_KTKSNB", D1, "S", D7, "", "0");
                for (DuLieuNTRow tmp : lstData_Api) {
                    DuLieuNTRowX tempadd = new DuLieuNTRowX();
                    tempadd.setKey("CB_KTKSNB");
                    tempadd.setOrderValue(Integer.valueOf(tmp.getOrderValue()));
                    tempadd.setOrderDescription(tmp.getOrderDescription());
                    tempadd.setName(tmp.getName());
                    tempadd.setCode(tmp.getCode());
                    tempadd.setMakerId(tmp.getMakerId());
                    tempadd.setMakerDate(tmp.getMakerDate());
                    tempadd.setAuthoriseId(UserName);
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

                    lstUpdateDate.add(tempadd);
                }
                _serverAPI.deleteKTKSNB("CB_KTKSNB", D1, "S", D7, "", "", lstUpdateDate);
                String code = String.valueOf(200);
                this.pageResult = new ByteArrayInputStream(code.getBytes(StandardCharsets.UTF_8));
                return SUCCESS;
            } else {
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

    public String popupTableCanbo() throws Exception {
        try {
            String D1 = ServletActionContext.getRequest().getParameter("madiemgd");

            String D7 = ServletActionContext.getRequest().getParameter("ngaybc");
            String type = ServletActionContext.getRequest().getParameter("type");
            ArrayList<LockSendModel> lstData_tmp = _serverAPI.getDataLockManual("CB_KTKSNB", D1, "S", D7);
            try {
                setChotsl(lstData_tmp.get(0).getStatus());
            } catch (Exception e) {
                setChotsl("0");
            }
            lstData_Api = _serverAPI.getDataKTKSNB("CB_KTKSNB", D1, "S", D7, "", "0");
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
                    row.setD13(item.getD13());
                    row.setD14(item.getD13());
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

}
