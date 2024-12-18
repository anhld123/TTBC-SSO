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
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import org.apache.struts2.ServletActionContext;
import vbsp.ims.action.ktktnb.DULIEU_NT_TQ;
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
public class HTLS_2024 extends ActionNhaptaycnMain
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
    private String ssduan1;
    private String ssngay1;
//<editor-fold defaultstate="collapsed" desc="khai báo get,set">

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
            lstData_Api = _serverAPI.getDataKTKSNB_2024("KKTS_01", main_pos_username, "M", _reportDate, conditions, "0");
            int tableapi = lstData_Api.size();
//            System.out.println("table= "+ table +" tableapi="+tableapi);
            if (table != tableapi) {
                String code = "1";
                this.pageResult = new ByteArrayInputStream(code.getBytes(StandardCharsets.UTF_8));
                return SUCCESS;
            }
            int status = _serverAPI.updateChotSL(sKyeLock, main_pos_username, "M", _reportDate, "2", UserName, null);
            if (status == 200) {
                ArrayList<DULIEU_NT_TQ> lstLocalDataUpdate = new ArrayList<>();
                for (DuLieuNTRow tmp : lstData_Api) {
                    DULIEU_NT_TQ tempadd = new DULIEU_NT_TQ();
                    tempadd.setKHOA("KKTS_01");
                    tempadd.setTHUTU(Integer.valueOf(tmp.getOrderValue()));
                    tempadd.setTT_HIENTHI(tmp.getOrderDescription());
                    tempadd.setTEN(tmp.getName());
                    tempadd.setMA(tmp.getCode());
                    tempadd.setNGUOI_NHAP(tmp.getMakerId());
                    tempadd.setNGUOI_DUYET(tmp.getAuthoriseId());
                    Date reportDate = DateUtil.toDate(dateStr);
                    tempadd.setNGAYBC(reportDate);
                    tempadd.setNAMBC(tmp.getReportYear());
                    tempadd.setMAPGD(tmp.getPosCode());
                    tempadd.setCO_TONGHOP(tmp.getPosFlag());
                    tempadd.setMACN(tmp.getBranchCode());
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
                    lstLocalDataUpdate.add(tempadd);

                }
                DaoNghiquyet11cp daoMain = new DaoNghiquyet11cp();
//                System.out.println("dateStr=== " + dateStr +" main_pos_username== " +main_pos_username+" sDu_an=== " +sDu_an +" UserName== " +UserName);
                daoMain.saveKKTS2024("KKTS_01", UserName, main_pos_username, "M", dateStr, sDu_an, lstLocalDataUpdate);
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

}
