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
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.apache.struts2.ServletActionContext;
import vbsp.ims.bcqt.model.QT_DULIEU_NT;
import vbsp.ims.dao.DaoConnect;
import vbsp.ims.define.GenericResult;
import vbsp.ims.huydongtk.clsCanBo;
import vbsp.ims.huydongtk.clsHuyDongTK;
import vbsp.ims.log.CoreLogger;
import vbsp.ims.nghiquyet11cp.DaoNghiquyet11cp;
import vbsp.ims.restapi.DuLieuNTRow;
import vbsp.ims.restapi.DuLieuNTRowX;
import vbsp.ims.restapi.DuLieuNTService;
import vbsp.ims.restapi.ListOfValue;
import vbsp.ims.restapi.ListPosCode;
import vbsp.ims.restapi.LockSendModel;
import vbsp.ims.util.DateUtil;

/**
 *
 * @author ducanh
 */
public class Service_KKTS_2024 extends ActionNhaptaycnMain
        implements NhaptaycnFunction {

    Service_GQVL2023 _server;
    DaoNghiquyet11cp _serverlocal;
    private List<QT_DULIEU_NT> lstData;
    private List<DuLieuNTRow> lstData_Api;
    private List<ListPosCode> lstPGD_API;
    private List<LockSendModel> lstData_tmp;
    private List<ListOfValue> lstDmKhac111;
    private List<ListOfValue> lstDmKhac112;
    private List<ListOfValue> lstDmKhac113;
    private List<ListOfValue> lstDmKhac114;
    private List<ListOfValue> lstDmKhac116;
    private List<ListOfValue> lstDmKhac118;
    private List<ListOfValue> lstDmKhac119;
    private List<ListOfValue> lstDmKhac117;
    protected String main_pos_username;
    private InputStream pageResult;
    DuLieuNTService _serverAPI = new DuLieuNTService();
    private String chotsl;
    private String txtGetData;
    private String status;
    private String message;
    private String sCode;
    private String stype;
    private String ssduan1;
    private String ssngay1;
    public List<clsCanBo> lstCanBo = new ArrayList<>();
//<editor-fold defaultstate="collapsed" desc="khai báo get,set">

    public List<ListOfValue> getLstDmKhac117() {
        return lstDmKhac117;
    }

    public void setLstDmKhac117(List<ListOfValue> lstDmKhac117) {
        this.lstDmKhac117 = lstDmKhac117;
    }

    public List<clsCanBo> getLstCanBo() {
        return lstCanBo;
    }

    public void setLstCanBo(List<clsCanBo> lstCanBo) {
        this.lstCanBo = lstCanBo;
    }

    public List<ListOfValue> getLstDmKhac118() {
        return lstDmKhac118;
    }

    public void setLstDmKhac118(List<ListOfValue> lstDmKhac118) {
        this.lstDmKhac118 = lstDmKhac118;
    }

    public List<ListOfValue> getLstDmKhac119() {
        return lstDmKhac119;
    }

    public void setLstDmKhac119(List<ListOfValue> lstDmKhac119) {
        this.lstDmKhac119 = lstDmKhac119;
    }

    public String getSsduan1() {
        return ssduan1;
    }

    public void setSsduan1(String ssduan1) {
        this.ssduan1 = ssduan1;
    }

    public String getSsngay1() {
        return ssngay1;
    }

    public void setSsngay1(String ssngay1) {
        this.ssngay1 = ssngay1;
    }

    public List<ListOfValue> getLstDmKhac116() {
        return lstDmKhac116;
    }

    public void setLstDmKhac116(List<ListOfValue> lstDmKhac116) {
        this.lstDmKhac116 = lstDmKhac116;
    }

    public List<ListOfValue> getLstDmKhac114() {
        return lstDmKhac114;
    }

    public void setLstDmKhac114(List<ListOfValue> lstDmKhac114) {
        this.lstDmKhac114 = lstDmKhac114;
    }

    public String getStype() {
        return stype;
    }

    public void setStype(String stype) {
        this.stype = stype;
    }

    public String getsCode() {
        return sCode;
    }

    public void setsCode(String sCode) {
        this.sCode = sCode;
    }

    public List<DuLieuNTRow> getLstData_Api() {
        return lstData_Api;
    }

    public void setLstData_Api(List<DuLieuNTRow> lstData_Api) {
        this.lstData_Api = lstData_Api;
    }

    public List<LockSendModel> getLstData_tmp() {
        return lstData_tmp;
    }

    public void setLstData_tmp(List<LockSendModel> lstData_tmp) {
        this.lstData_tmp = lstData_tmp;
    }

    public List<ListOfValue> getLstDmKhac111() {
        return lstDmKhac111;
    }

    public void setLstDmKhac111(List<ListOfValue> lstDmKhac111) {
        this.lstDmKhac111 = lstDmKhac111;
    }

    public List<ListOfValue> getLstDmKhac112() {
        return lstDmKhac112;
    }

    public void setLstDmKhac112(List<ListOfValue> lstDmKhac112) {
        this.lstDmKhac112 = lstDmKhac112;
    }

    public List<ListOfValue> getLstDmKhac113() {
        return lstDmKhac113;
    }

    public void setLstDmKhac113(List<ListOfValue> lstDmKhac113) {
        this.lstDmKhac113 = lstDmKhac113;
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
                return null;
            case "2":
                return load_c2();
            default:
                return load_c3();
        }
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
            String sDu_an = hmParameter.get("lstsDmkhac").toString();
            setSsduan1(sDu_an);

            ActionContext.getContext().getSession().put("ssmacn", main_pos_username);
            ActionContext.getContext().getSession().put("sDu_an", ssduan1);
            ActionContext.getContext().getSession().put("UserName", UserName);
            final String _reportDate = new SimpleDateFormat("yyyyMMdd").format(new SimpleDateFormat("dd-MMM-yyyy").parse(dateStr));
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            final String _reportDate1 = new SimpleDateFormat("dd/MM/yyyy").format(new SimpleDateFormat("dd-MMM-yyyy").parse(dateStr));
            setSsngay1(_reportDate1);
            String conditions = "D14=" + sDu_an + "|";
            String sKyeLock = "KKTS_01_" + sDu_an;
            ArrayList<LockSendModel> lstData_tmp = _serverAPI.getDataLockManual(sKyeLock, main_pos_username, "M", _reportDate);
            try {
                setChotsl(lstData_tmp.get(0).getStatus());
            } catch (Exception e) {
                setChotsl("0");
            }
            ActionContext.getContext().getSession().put("_reportDate", _reportDate);
            lstPGD_API = _serverAPI.getListPgd(main_pos_username, "");
            lstDmKhac111 = _serverAPI.getListOfValue("111", sDu_an);
            lstDmKhac112 = _serverAPI.getListOfValue("112", sDu_an);
            lstDmKhac113 = _serverAPI.getListOfValue("113", "");
            lstDmKhac114 = _serverAPI.getListOfValue("114", "");
            lstDmKhac116 = _serverAPI.getListOfValue("116", "");
            lstData_Api = _serverAPI.getDataKTKSNB_2024("KKTS_01", pos_cd_username, "M", _reportDate, conditions, "0");
            if (lstData_Api == null || lstData_Api.isEmpty()) {
                setStype("1");
                lstData_Api = _serverAPI.getDataKTKSNB_2024("KKTS_01", pos_cd_username, "M", _reportDate, conditions, "1");
            } else {
                setStype("0");
            }
            for (DuLieuNTRow item : lstData_Api) {
                QT_DULIEU_NT row = new QT_DULIEU_NT();
                try {
                    UUID uuid = UUID.randomUUID(); // Tạo UUID mới
                    long timestamp = System.currentTimeMillis(); // Lấy timestamp hiện tại
                    String sysGuid = uuid.toString().replace("-", "") + timestamp;
                    setsCode(sysGuid);
                    row.setKHOA(item.getKey());
                    row.setTHUTU(Integer.parseInt(item.getOrderValue()));
                    row.setTT_HIENTHI(item.getOrderDescription());
                    if (stype.equals("0")) {
                        row.setMA(item.getCode());
                    } else {
                        row.setMA(sCode);
                    }
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
                    row.setD6(item.getD6() == null || item.getD6().equals("") ? "0" : item.getD6());
                    row.setD7(item.getD7() == null || item.getD7().equals("") ? "0" : item.getD7());
                    row.setD8(item.getD8() == null || item.getD8().equals("") ? "0" : item.getD8());
                    row.setD9(item.getD9());
                    row.setD10(item.getD10());
                    row.setD11(item.getD11());
                    row.setD12(item.getD12());
                    row.setD13(item.getD13());
                    row.setD14(sDu_an);
                    String formattedDate = dateFormat.format(reportDate);
                    row.setD15(formattedDate);
                    lstDulieuNt.add(row);
                } catch (Exception e) {
                }
            }
            lstDulieuNt.sort(Comparator.comparingInt((QT_DULIEU_NT obj) -> Integer.parseInt(obj.getD1()))
                    .thenComparingInt(obj -> Integer.parseInt(obj.getD2())));
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> tin dung 2024 : " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> tin dung 2024: " + e.getMessage());
        }
        return "success_1";
    }

    public String load_c3() {
        try {
            if (!getParaSession()) {
                return ERROR;
            }
            HashMap hmParameter = getParameter();
            String sngaybc = hmParameter.get("ngay_bc").toString();
            String sDu_an = hmParameter.get("lstsDmkhac").toString();
            String sKyeLock = "KKTS_01_" + sDu_an;
            Connection conn = new DaoConnect().getConnect();
            DaoNghiquyet11cp daoMain = new DaoNghiquyet11cp();
            lstDulieuNt = daoMain.getData_THTK_c3(conn, sngaybc, sKyeLock, "31121992", "M");
//            System.out.println(sngaybc);
            if (conn != null) {
                conn.close();
            }

        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> gdx: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> gdx: " + e.getMessage());
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
            if (lstDulieuNt == null || lstDulieuNt.isEmpty()) {
                addActionError("Bạn chưa lưu được báo cáo xin liên hệ với quản trị để khắc phục");
                return ERROR;
            }
            DaoNghiquyet11cp daoMain = new DaoNghiquyet11cp();
            HashMap hmParameter = getParameter();
            String dateStr = hmParameter.get("ngay_bc").toString();
            String[] values = dateStr.split("\\-");
            String snambc = values[2];
            String sDu_an = hmParameter.get("lstsDmkhac").toString();
            String sKyeLock = "KKTS_01_" + sDu_an;
            final String _reportDate = new SimpleDateFormat("yyyyMMdd").format(new SimpleDateFormat("dd-MMM-yyyy").parse(dateStr));
            final String _reportDate1 = new SimpleDateFormat("yyyy-MM-dd'T'00:00:00.000").format(new SimpleDateFormat("yyyyMMdd").parse(_reportDate));
            posMainModel = listKTNBDA.get_pos_main_pos(UserName, Grade);
            pos_cd_username = posMainModel.getPosCd();
            main_pos_username = posMainModel.getMainPosCd();
            ArrayList<DuLieuNTRowX> lstUpdateDate = new ArrayList<>();
//            ArrayList<QT_DULIEU_NT> lstLocalDataUpdate = new ArrayList<>();
            for (QT_DULIEU_NT tmp : lstDulieuNt) {
                UUID uuid = UUID.randomUUID(); // Tạo UUID mới
                long timestamp = System.currentTimeMillis(); // Lấy timestamp hiện tại
                String sysGuid = uuid.toString().replace("-", "") + timestamp;
                setsCode(sysGuid);
                DuLieuNTRowX tempadd = new DuLieuNTRowX();
                tempadd.setKey("KKTS_01");
                tempadd.setOrderValue(tmp.getTHUTU());
                tempadd.setOrderDescription(tmp.getTT_HIENTHI());
                tempadd.setName(tmp.getD1());
                tempadd.setCode(tmp.getMA() == null ? sCode : tmp.getMA());
                tempadd.setMakerId(UserName);
                tempadd.setMakerDate(_reportDate1);
                tempadd.setAuthoriseId(UserName);
                tempadd.setAuthoriseDate(_reportDate1);
                tempadd.setReportDate(_reportDate1);
                tempadd.setReportYear(Integer.valueOf(snambc));
                tempadd.setPosCode(main_pos_username);
                tempadd.setPosFlag("M");
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
                tempadd.setD12(tmp.getD1());
                tempadd.setD13(tmp.getMA() == null ? sCode : tmp.getMA());
                tempadd.setD14(sDu_an);
                lstUpdateDate.add(tempadd);

            }

            _serverAPI = new DuLieuNTService();
            int status = _serverAPI.updateKTKSNB("KKTS_01", main_pos_username, "M", _reportDate, "", "", lstUpdateDate);
            if (status == 200) {
                _serverAPI.updateChotSL(sKyeLock, main_pos_username, "M", _reportDate, "0", UserName, null);
                String code = String.valueOf(status);
                this.pageResult = new ByteArrayInputStream(code.getBytes(StandardCharsets.UTF_8));
                return SUCCESS;
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
            String sDu_an = hmParameter.get("lstsDmkhac").toString();
            String sKyeLock = "KKTS_01_" + sDu_an;
            String conditions = "D14=" + sDu_an + "|";
            final String _reportDate = new SimpleDateFormat("yyyyMMdd").format(new SimpleDateFormat("dd-MMM-yyyy").parse(dateStr));
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
            posMainModel = listKTNBDA.get_pos_main_pos(UserName, Grade);
            pos_cd_username = posMainModel.getPosCd();
            main_pos_username = posMainModel.getMainPosCd();
            int table = lstDulieuNt.size();
            lstData_Api = _serverAPI.getDataKTKSNB_2024("KKTS_01", main_pos_username, "M", _reportDate, conditions, "1");
            int tableapi = lstData_Api.size();
//            System.out.println("table= "+ table +" tableapi="+tableapi);
            if (table != tableapi) {
                String code = "1";
                this.pageResult = new ByteArrayInputStream(code.getBytes(StandardCharsets.UTF_8));
                return SUCCESS;
            }
            int status = _serverAPI.updateChotSL(sKyeLock, main_pos_username, "M", _reportDate, "2", UserName, null);
            if (status == 200) {
                String code = String.valueOf(status);
                this.pageResult = new ByteArrayInputStream(code.getBytes(StandardCharsets.UTF_8));
                return SUCCESS;
            }
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> muats2024: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> muats2024: " + e.getMessage());
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
            System.out.println(D7 + "  " + D8);
            SimpleDateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat outputFormat = new SimpleDateFormat("dd/MMM/yyyy");
            Date date = inputFormat.parse(D5);
            String formattedDate = outputFormat.format(date);
            DaoNghiquyet11cp daoMain = new DaoNghiquyet11cp();
            GenericResult<String> _result = daoMain.unlock_c3_THTK(D8, D1, D6, formattedDate, D7);

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

    public String loadDmKhac111() {
        String sDu_an = (String) ActionContext.getContext().getSession().get("sDu_an");
        List<ListOfValue> listOfValues = _serverAPI.getListOfValue("111", sDu_an);
        Map<String, String> mapAllChtrinh = new LinkedHashMap<>();
        for (ListOfValue value : listOfValues) {
            mapAllChtrinh.put(value.getDescription(), value.getValue());
        }

        setLstDmKhac111(listOfValues);
        return SUCCESS;
    }

    public String loadDmKhac112() {
        String sDu_an = (String) ActionContext.getContext().getSession().get("sDu_an");
        List<ListOfValue> listOfValues = _serverAPI.getListOfValue("112", sDu_an);
        Map<String, String> mapAllChtrinh1 = new LinkedHashMap<>();
        for (ListOfValue value : listOfValues) {
            mapAllChtrinh1.put(value.getDescription(), value.getValue());
        }
        setLstDmKhac112(listOfValues);
        return SUCCESS;
    }

    public String loadDmKhac113() {
        List<ListOfValue> listOfValues = _serverAPI.getListOfValue("113", "");
        Map<String, String> mapAllChtrinh1 = new LinkedHashMap<>();
        for (ListOfValue value : listOfValues) {
            mapAllChtrinh1.put(value.getCode(), value.getValue());
        }
        setLstDmKhac113(listOfValues);
        return SUCCESS;
    }

    public String loadDmKhac114() {
        List<ListOfValue> listOfValues = _serverAPI.getListOfValue("114", "");
        Map<String, String> mapAllChtrinh1 = new LinkedHashMap<>();
        for (ListOfValue value : listOfValues) {
            mapAllChtrinh1.put(value.getCode(), value.getValue());
        }
        setLstDmKhac114(listOfValues);
        return SUCCESS;
    }

    public String loadDmKhac116() {
        List<ListOfValue> listOfValues = _serverAPI.getListOfValue("116", "");
        Map<String, String> mapAllChtrinh1 = new LinkedHashMap<>();
        for (ListOfValue value : listOfValues) {
            mapAllChtrinh1.put(value.getCode(), value.getValue());
        }
        setLstDmKhac116(listOfValues);
        return SUCCESS;
    }

    public String loadPGD() {
        String ssmacn = (String) ActionContext.getContext().getSession().get("ssmacn");
//        System.out.println("ssmacn: " +ssmacn);
        List<ListPosCode> ListPosCode = _serverAPI.getListPgd(ssmacn, "");
        Map<String, String> mapAllPgd = new LinkedHashMap<>();
        for (ListPosCode value : ListPosCode) {
            mapAllPgd.put(value.getPosCode(), value.getPosName());
        }
        setLstPGD_API(ListPosCode);
        return SUCCESS;
    }

    public String delete() {
        try {
            String smapgd = ServletActionContext.getRequest().getParameter("sD1");
            String MA = ServletActionContext.getRequest().getParameter("sMA");
            String sD12 = ServletActionContext.getRequest().getParameter("sD12");
            String _reportDate = (String) ActionContext.getContext().getSession().get("_reportDate");
            String ssmacn = (String) ActionContext.getContext().getSession().get("ssmacn");
            String sDu_an = (String) ActionContext.getContext().getSession().get("sDu_an");
            String conditions = "D13=" + MA + "|D1=" + sD12 + "|D14=" + sDu_an + "|";
//            System.out.println(smapgd + " " + sD12 + " " + conditions);
            lstData_Api = _serverAPI.getDataKTKSNB_2024("KKTS_01", ssmacn, "M", _reportDate, conditions, "1");
            ArrayList<DuLieuNTRowX> lstDelete = new ArrayList<>();
            for (DuLieuNTRow tmp : lstData_Api) {
                DuLieuNTRowX tempadd = new DuLieuNTRowX();
                tempadd.setKey("KKTS_01");
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
                lstDelete.add(tempadd);
            }
            _serverAPI = new DuLieuNTService();
            int status = _serverAPI.deleteKTKSNB("KKTS_01", ssmacn, "M", _reportDate, "", "", lstDelete);
            this.pageResult = new ByteArrayInputStream(String.valueOf(status).getBytes(StandardCharsets.UTF_8));
            return SUCCESS;
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> unlock_c2: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> unlock_c2: " + e.getMessage());
            String code = String.valueOf(3);
            this.pageResult = new ByteArrayInputStream(code.getBytes(StandardCharsets.UTF_8));
            return SUCCESS;
        }
    }

    public String popupTableHoidong() throws Exception {
        try {
            String skhoa = ServletActionContext.getRequest().getParameter("skhoa");
            String smapgd = ServletActionContext.getRequest().getParameter("smapgd");
            String smacn = ServletActionContext.getRequest().getParameter("smacn");
            String sngaybc = ServletActionContext.getRequest().getParameter("sngaybc");
            String sduan = ServletActionContext.getRequest().getParameter("sduan");
            String sUserName = (String) ActionContext.getContext().getSession().get("UserName");
            String _reportDate = (String) ActionContext.getContext().getSession().get("_reportDate");
            ActionContext.getContext().getSession().put("smapgd", smapgd);
            ActionContext.getContext().getSession().put("smacn", smacn);
            ActionContext.getContext().getSession().put("_reportDate", _reportDate);
            ActionContext.getContext().getSession().put("sngaybc", sngaybc);
            ActionContext.getContext().getSession().put("skhoa", skhoa);
            ActionContext.getContext().getSession().put("sduan", sduan);
            ActionContext.getContext().getSession().put("sUserName", sUserName);
            String sKyeLock = "KKTS_01_" + sduan;
            ArrayList<LockSendModel> lstData_tmp = _serverAPI.getDataLockManual(sKyeLock, smacn, "M", _reportDate);
            try {
                setChotsl(lstData_tmp.get(0).getStatus());
            } catch (Exception e) {
                setChotsl("0");
            }
            lstDmKhac118 = _serverAPI.getListOfValue("118", "");
            lstDmKhac119 = _serverAPI.getListOfValue("119", "");
            lstDmKhac117 = _serverAPI.getListOfValue("117", "");
            clsHuyDongTK Canbo2024 = new clsHuyDongTK();
            setLstCanBo(Canbo2024.getCanBo("M", sUserName));
            try {
                setChotsl(lstData_tmp.get(0).getStatus());
            } catch (Exception e) {
                setChotsl("0");
            }
//            System.out.println("api: " + skhoa + " " + smacn + " " + _reportDate);
            lstData_Api = _serverAPI.getDataKTKSNB_2024(skhoa, smacn, "M", _reportDate, "", "0");
            if (lstData_Api == null || lstData_Api.isEmpty()) {
                setStype("1");
                lstData_Api = _serverAPI.getDataKTKSNB_2024("ANHLD_KYE", smacn, "M", _reportDate, "", "1");
            } else {
                setStype("0");
            }
            for (DuLieuNTRow item : lstData_Api) {
                QT_DULIEU_NT row = new QT_DULIEU_NT();
                try {
                    row.setKHOA(item.getKey());
                    row.setTHUTU(Integer.parseInt(item.getOrderValue()));
                    row.setTT_HIENTHI(item.getOrderDescription());
                    UUID uuid = UUID.randomUUID(); // Tạo UUID mới
                    long timestamp = System.currentTimeMillis(); // Lấy timestamp hiện tại
                    String sysGuid = uuid.toString().replace("-", "") + timestamp;
                    setsCode(sysGuid);
                    row.setKHOA(item.getKey());
                    row.setTHUTU(Integer.parseInt(item.getOrderValue()));
                    row.setTT_HIENTHI(item.getOrderDescription());
                    if (stype.equals("0")) {
                        row.setMA(item.getCode());
                    } else {
                        row.setMA(sCode);
                    }
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
                    lstDulieuNt.add(row);
                } catch (Exception e) {
                }
            }
            lstDulieuNt.sort(Comparator.comparingInt((QT_DULIEU_NT obj) -> Integer.parseInt(obj.getD4()))
                    .thenComparingInt(obj -> Integer.parseInt(obj.getD3())));
        } catch (Exception e) {
            System.err.println("Loi trong ham table pos " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " table pos -> " + e.getMessage());
        }
        return "success";
    }

    public String save_popup() {
        System.out.println("vao váe");
        try {
            if (!getParaSession()) {
                return ERROR;
            }
            String skhoa = (String) ActionContext.getContext().getSession().get("skhoa");
            String smapgd = (String) ActionContext.getContext().getSession().get("smapgd");
            String smacn = (String) ActionContext.getContext().getSession().get("smacn");
            String sngaybc = (String) ActionContext.getContext().getSession().get("sngaybc");
            String sUserName = (String) ActionContext.getContext().getSession().get("sUserName");
            String _reportDate = (String) ActionContext.getContext().getSession().get("_reportDate");
            String snambc = _reportDate.substring(4);
//            System.out.println("skhoa= " + skhoa + " sngaybc= " + sngaybc + " _reportDate= " + _reportDate);
            final String _reportDate1 = new SimpleDateFormat("yyyy-MM-dd'T'00:00:00.000").format(new SimpleDateFormat("yyyyMMdd").parse(_reportDate));
            ArrayList<DuLieuNTRowX> lstUpdateDate = new ArrayList<>();
            for (QT_DULIEU_NT tmp : lstDulieuNt) {
                UUID uuid = UUID.randomUUID(); // Tạo UUID mới
                long timestamp = System.currentTimeMillis(); // Lấy timestamp hiện tại
                String sysGuid = uuid.toString().replace("-", "") + timestamp;
                setsCode(sysGuid);
                DuLieuNTRowX tempadd = new DuLieuNTRowX();
                tempadd.setKey(skhoa);
                tempadd.setOrderValue(tmp.getTHUTU());
                tempadd.setOrderDescription(tmp.getTT_HIENTHI());
                tempadd.setName(tmp.getD1());
                tempadd.setCode(tmp.getMA() == null ? sCode : tmp.getMA());
                tempadd.setMakerId(sUserName);
                tempadd.setMakerDate(_reportDate1);
                tempadd.setAuthoriseId(sUserName);
                tempadd.setAuthoriseDate(_reportDate1);
                tempadd.setReportDate(sngaybc);
                tempadd.setReportYear(Integer.valueOf(snambc));
                tempadd.setPosCode(smapgd);
                tempadd.setPosFlag("M");
                tempadd.setBranchCode(smacn);
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
                tempadd.setD12(tmp.getD1());
                tempadd.setD13(tmp.getMA() == null ? sCode : tmp.getMA());
                lstUpdateDate.add(tempadd);
            }
            _serverAPI = new DuLieuNTService();
            int status = _serverAPI.updateKTKSNB(skhoa, smacn, "M", _reportDate, "", "", lstUpdateDate);
            if (status == 200) {
                String code = String.valueOf(status);
                this.pageResult = new ByteArrayInputStream(code.getBytes(StandardCharsets.UTF_8));
                return SUCCESS;
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

    public String delete_popup() {
        try {
            String skhoa = ServletActionContext.getRequest().getParameter("skhoa");
            String sma = ServletActionContext.getRequest().getParameter("sma");
            String sten = ServletActionContext.getRequest().getParameter("sten");
            String _reportDate = (String) ActionContext.getContext().getSession().get("_reportDate");
            String ssmacn = (String) ActionContext.getContext().getSession().get("ssmacn");
            String conditions = "D13=" + sma + "|D12=" + sten + "|";
            lstData_Api = _serverAPI.getDataKTKSNB_2024(skhoa, ssmacn, "M", _reportDate, conditions, "1");
            ArrayList<DuLieuNTRowX> lstDelete = new ArrayList<>();
            for (DuLieuNTRow tmp : lstData_Api) {
                DuLieuNTRowX tempadd = new DuLieuNTRowX();
                tempadd.setKey(skhoa);
                tempadd.setOrderValue(Integer.valueOf(tmp.getOrderValue()));
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
                tempadd.setD10(tmp.getD10());
                tempadd.setD11(tmp.getD11());
                tempadd.setD12(tmp.getD12());
                tempadd.setD13(tmp.getD13());
                tempadd.setD14(tmp.getD14());
                lstDelete.add(tempadd);
            }
            _serverAPI = new DuLieuNTService();
            int status = _serverAPI.deleteKTKSNB(skhoa, ssmacn, "M", _reportDate, "", "", lstDelete);
            this.pageResult = new ByteArrayInputStream(String.valueOf(status).getBytes(StandardCharsets.UTF_8));
            return SUCCESS;
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> unlock_c2: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> unlock_c2: " + e.getMessage());
            String code = String.valueOf(3);
            this.pageResult = new ByteArrayInputStream(code.getBytes(StandardCharsets.UTF_8));
            return SUCCESS;
        }
    }

    public String loadDmKhac118() {
        List<ListOfValue> listOfValues = _serverAPI.getListOfValue("118", "");
        Map<String, String> mapAllChtrinh = new LinkedHashMap<>();
        for (ListOfValue value : listOfValues) {
            mapAllChtrinh.put(String.valueOf(value.getDescription()), value.getValue());
        }
        setLstDmKhac118(listOfValues);
        return SUCCESS;
    }

    public String loadDmKhac119() {
        List<ListOfValue> listOfValues = _serverAPI.getListOfValue("119", "");
        Map<String, String> mapAllChtrinh = new LinkedHashMap<>();
        for (ListOfValue value : listOfValues) {
            mapAllChtrinh.put(value.getDescription(), value.getValue());
        }
        setLstDmKhac119(listOfValues);
        return SUCCESS;
    }

    public String loadDmKhac117() {
        List<ListOfValue> listOfValues = _serverAPI.getListOfValue("117", "");
        Map<String, String> mapAllChtrinh = new LinkedHashMap<>();
        for (ListOfValue value : listOfValues) {
            mapAllChtrinh.put(value.getDescription(), value.getValue());
        }
        setLstDmKhac117(listOfValues);
        return SUCCESS;
    }

    public String loadcanbo() {
        String sUserName = (String) ActionContext.getContext().getSession().get("sUserName");
        List<clsCanBo> listOfValues = new clsHuyDongTK().getCanBo("M", sUserName);
        Map<String, String> mapAllChtrinh = new LinkedHashMap<>();

        for (clsCanBo value : listOfValues) {
            String smacb = value.getMaCB();
            String stencb = value.getTenCB();
            mapAllChtrinh.put(smacb, stencb);
        }
        setLstCanBo(listOfValues);

        return SUCCESS;
    }

}
