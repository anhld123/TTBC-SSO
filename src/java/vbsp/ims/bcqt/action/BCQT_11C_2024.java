/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.bcqt.action;

import static com.opensymphony.xwork2.Action.ERROR;
import vbsp.ims.gqvl_2023.*;
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
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import org.apache.struts2.ServletActionContext;
import vbsp.ims.action.ktktnb.DULIEU_NT_TQ;
import vbsp.ims.bcqt.dao.DaoBcqtMain;
import vbsp.ims.bcqt.model.QT_DULIEU_NT;
import vbsp.ims.dao.DaoConnect;
import vbsp.ims.define.GenericResult;
import vbsp.ims.log.CoreLogger;
import vbsp.ims.restapi.DuLieuNTRow;
import vbsp.ims.restapi.DuLieuNTRowX;
import vbsp.ims.restapi.DuLieuNTService;
import vbsp.ims.restapi.ListOfValue;
import vbsp.ims.restapi.ListPosCode;
import vbsp.ims.restapi.LockSendModel;

/**
 *
 * @author ducanh
 */
public class BCQT_11C_2024 extends ActionNhaptaycnMain
        implements NhaptaycnFunction {

    Service_GQVL2023 _server;
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
    private String sssoku;
    private String skhoa;
//<editor-fold defaultstate="collapsed" desc="khai báo get,set">

    public String getSkhoa() {
        return skhoa;
    }

    public void setSkhoa(String skhoa) {
        this.skhoa = skhoa;
    }

    public String getSssoku() {
        return sssoku;
    }

    public void setSssoku(String sssoku) {
        this.sssoku = sssoku;
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
            HashMap hmParameter = getParameter();
            Connection conn = new DaoConnect().getConnect();
            posMainModel = listKTNBDA.get_pos_main_pos(UserName, Grade);
            pos_cd_username = posMainModel.getPosCd();
            main_pos_username = posMainModel.getMainPosCd();
            String dateStr = hmParameter.get("ngay_bc").toString();
            final String _reportDate = new SimpleDateFormat("yyyyMMdd").format(new SimpleDateFormat("dd-MMM-yyyy").parse(dateStr));
            ActionContext.getContext().getSession().put("_reportDate", _reportDate);
            ActionContext.getContext().getSession().put("pos_cd_username", pos_cd_username);
            ActionContext.getContext().getSession().put("main_pos_username", main_pos_username);
            ActionContext.getContext().getSession().put("UserName", UserName);
            ActionContext.getContext().getSession().put("dateStr", dateStr);
            DaoBcqtMain daoMain = new DaoBcqtMain();
            lstData = daoMain.getbcqt_11c_2024(conn, "AAA2", dateStr, UserName, pos_cd_username);
            try {
                setChotsl(lstData.get(0).getD1());
            } catch (Exception e) {
                setChotsl("0");
            }

            lstDulieuNt = daoMain.getbcqt_11c_2024(conn, "AAA", dateStr, UserName, pos_cd_username);
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> tin dung 2024 : " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> tin dung 2024: " + e.getMessage());
        }
        return "success_1";
    }

    public String load_c2() {
        try {
            if (!getParaSession()) {
                return ERROR;
            }
            HashMap hmParameter = getParameter();
            Connection conn = new DaoConnect().getConnect();
            posMainModel = listKTNBDA.get_pos_main_pos(UserName, Grade);
            pos_cd_username = posMainModel.getPosCd();
            main_pos_username = posMainModel.getMainPosCd();
            setSkhoa("BCQT_MS11C");
            String dateStr = hmParameter.get("ngay_bc").toString();
            ActionContext.getContext().getSession().put("pos_cd_username", pos_cd_username);
            ActionContext.getContext().getSession().put("main_pos_username", main_pos_username);
            ActionContext.getContext().getSession().put("UserName", UserName);
            ActionContext.getContext().getSession().put("dateStr", dateStr);
            DaoBcqtMain daoMain = new DaoBcqtMain();
            lstDulieuNt = daoMain.getbcqt_11c_2024(conn, "AAA3", dateStr, UserName, pos_cd_username);
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> cap 2 bcqt 11c : " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> cap 2 bcqt 11c: " + e.getMessage());
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
            setSkhoa("BCQT_MS11C");
            ActionContext.getContext().getSession().put("UserName", UserName);
            DaoBcqtMain daoMain = new DaoBcqtMain();
            lstDulieuNt = daoMain.getData_bcqt11c_c3(conn, sngaybc, "BCQT_MS11C", smacn, "S");
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
            if (lstDulieuNt == null || lstDulieuNt.isEmpty()) {
                addActionError("Bạn chưa lưu được báo cáo xin liên hệ với quản trị để khắc phục");
                return ERROR;
            }
            DaoBcqtMain daoMain = new DaoBcqtMain();
            HashMap hmParameter = getParameter();
            String dateStr = hmParameter.get("ngay_bc").toString();
            String[] values = dateStr.split("\\-");
            String snambc = values[2];
            final String _reportDate = new SimpleDateFormat("yyyyMMdd").format(new SimpleDateFormat("dd-MMM-yyyy").parse(dateStr));
            final String _reportDate1 = new SimpleDateFormat("yyyy-MM-dd'T'00:00:00.000").format(new SimpleDateFormat("yyyyMMdd").parse(_reportDate));
            posMainModel = listKTNBDA.get_pos_main_pos(UserName, Grade);
            pos_cd_username = posMainModel.getPosCd();
            main_pos_username = posMainModel.getMainPosCd();
            ArrayList<DULIEU_NT_TQ> lstLocalDataUpdate = new ArrayList<>();
            for (QT_DULIEU_NT tmp : lstDulieuNt) {
                DULIEU_NT_TQ temlocal = new DULIEU_NT_TQ();
                temlocal.setKHOA("BCQT_MS11C");
                temlocal.setTHUTU(tmp.getTHUTU());
                temlocal.setTT_HIENTHI(tmp.getTT_HIENTHI());
                temlocal.setTEN(tmp.getTEN());
                temlocal.setMA(tmp.getMA());
                temlocal.setNGUOI_DUYET(UserName);
                temlocal.setNGUOI_NHAP(UserName);
                temlocal.setNAMBC(Integer.valueOf(snambc));
                temlocal.setMAPGD(pos_cd_username);
                temlocal.setCO_TONGHOP("S");
                temlocal.setMACN(main_pos_username);
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
                temlocal.setD21(tmp.getD21());
                temlocal.setD22(tmp.getD22());
                temlocal.setD23(tmp.getD23());
                temlocal.setD24(tmp.getD24());
                temlocal.setD25(tmp.getD25());
                lstLocalDataUpdate.add(temlocal);
            }
            if (!daoMain.savebcqt_11c_2024("BCQT_MS11C", dateStr, UserName, pos_cd_username, lstLocalDataUpdate)) {
                String code = String.valueOf(1);
                this.pageResult = new ByteArrayInputStream(code.getBytes(StandardCharsets.UTF_8));
                return SUCCESS;
            }
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> muats2024: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> muats2024: " + e.getMessage());
        }
        String code = String.valueOf(200);
        this.pageResult = new ByteArrayInputStream(code.getBytes(StandardCharsets.UTF_8));
        return SUCCESS;
    }

    public String addline() {
        try {
            if (!getParaSession()) {
                return ERROR;
            }
            String stype = ServletActionContext.getRequest().getParameter("stype");
            String sSoku = "";
            String sngaybc = "";
            String smapgd = "";
            if (stype.equals("1") || stype.equals("2")) {
                sSoku = ServletActionContext.getRequest().getParameter("sSoku");
                smapgd = (String) ActionContext.getContext().getSession().get("pos_cd_username");
            } else if (stype.equals("4") || stype.equals("5")) {
                sngaybc = ServletActionContext.getRequest().getParameter("sngaybc");
                smapgd = ServletActionContext.getRequest().getParameter("smapgd");
            }
            String dateStr = (String) ActionContext.getContext().getSession().get("dateStr");

            String UserName = (String) ActionContext.getContext().getSession().get("UserName");
            DaoBcqtMain daoMain = new DaoBcqtMain();
            GenericResult<String> _result = daoMain.addline_data_11c("BCQT_MS11C", dateStr, smapgd, sSoku, "", UserName, stype);
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
            Connection conn = new DaoConnect().getConnect();
            String dateStr = hmParameter.get("ngay_bc").toString();
            final String _reportDate = new SimpleDateFormat("yyyyMMdd").format(new SimpleDateFormat("dd-MMM-yyyy").parse(dateStr));
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
            posMainModel = listKTNBDA.get_pos_main_pos(UserName, Grade);
            pos_cd_username = posMainModel.getPosCd();
            main_pos_username = posMainModel.getMainPosCd();
            int table = lstDulieuNt.size();
            DaoBcqtMain daoMain = new DaoBcqtMain();
            lstData = daoMain.getbcqt_11c_2024(conn, "AAA", dateStr, UserName, pos_cd_username);
            int tableapi = lstData.size();
            if (table != tableapi) {
                String code = "1";
                this.pageResult = new ByteArrayInputStream(code.getBytes(StandardCharsets.UTF_8));
                return SUCCESS;
            }
            if (!daoMain.sendbcqt_11c_2024("BCQT_MS11C_LOCK_C1", dateStr, UserName, pos_cd_username)) {
                String code = String.valueOf(1);
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
            String UserName = (String) ActionContext.getContext().getSession().get("UserName");
            SimpleDateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat outputFormat = new SimpleDateFormat("dd/MMM/yyyy");
            Date date = inputFormat.parse(D5);
            String formattedDate = outputFormat.format(date);
            DaoBcqtMain daoMain = new DaoBcqtMain();
            GenericResult<String> _result = daoMain.unlock_c3_bcqt("BCQT_MS11C", D1, "S", formattedDate, UserName);

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

    public String senTw() {
        try {
            String stype = ServletActionContext.getRequest().getParameter("stype");
            String sngaybc = ServletActionContext.getRequest().getParameter("sngaybc");
            String smapgd = ServletActionContext.getRequest().getParameter("smapgd");
            String UserName = (String) ActionContext.getContext().getSession().get("UserName");
            String dateStr = (String) ActionContext.getContext().getSession().get("dateStr");
            final String _reportDate = new SimpleDateFormat("yyyyMMdd").format(new SimpleDateFormat("dd-MMM-yyyy").parse(dateStr));
            final String _reportDate1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS").format(new Date());
            DaoBcqtMain daoMain = new DaoBcqtMain();
            Connection conn = new DaoConnect().getConnect();
            lstData = daoMain.getbcqt_11c_2024(conn, "AAA", sngaybc, UserName, smapgd);
            ArrayList<DuLieuNTRowX> lstUpdateDate = new ArrayList<>();
            for (QT_DULIEU_NT tmp : lstData) {
                DuLieuNTRowX tempadd = new DuLieuNTRowX();
                tempadd.setKey("BCQT_MS11C");
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
                tempadd.setD21(tmp.getD21());
                tempadd.setD22(tmp.getD22());
                tempadd.setD23(tmp.getD23());
                tempadd.setD24(tmp.getD24());
                tempadd.setD25(tmp.getD25());
                lstUpdateDate.add(tempadd);
            }

            _serverAPI = new DuLieuNTService();
            int status = _serverAPI.getGQVL2023("BCQT_MS11C", smapgd, "S", _reportDate, "", "", lstUpdateDate);
            _serverAPI.updateChotSL("BCQT_MS11C", smapgd, "S", _reportDate, "2", UserName, null);
            if (status != 200) {
                String code = String.valueOf(2);
                this.pageResult = new ByteArrayInputStream(code.getBytes(StandardCharsets.UTF_8));
                return SUCCESS;
            } else {
                List<QT_DULIEU_NT> lstDulieuNt_temp = daoMain.getbcqt_11c_2024(conn, "AAA2", dateStr, UserName, smapgd);
                ArrayList<DULIEU_NT_TQ> lstLocalDataUpdate = new ArrayList<>();
                for (QT_DULIEU_NT tmp : lstDulieuNt_temp) {
                    DULIEU_NT_TQ temlocal = new DULIEU_NT_TQ();
                    temlocal.setKHOA("BCQT_MS11C_LOCK");
                    temlocal.setTHUTU(tmp.getTHUTU());
                    temlocal.setTT_HIENTHI(tmp.getTT_HIENTHI());
                    temlocal.setTEN(tmp.getTEN());
                    temlocal.setMA(tmp.getMA());
                    temlocal.setNGUOI_DUYET(UserName);
                    temlocal.setNGUOI_NHAP(UserName);
                    temlocal.setNAMBC(tmp.getNAMBC());
                    temlocal.setMAPGD(tmp.getMAPGD());
                    temlocal.setCO_TONGHOP(tmp.getCO_TONGHOP());
                    temlocal.setMACN(tmp.getMACN());
                    temlocal.setD1("2");
                    lstLocalDataUpdate.add(temlocal);
                }
                if (!daoMain.savebcqt_11c_2024("BCQT_MS11C_LOCK", dateStr, UserName, smapgd, lstLocalDataUpdate)) {
                    String code = String.valueOf(3);
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
