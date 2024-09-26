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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
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

/**
 *
 * @author ducanh
 */
public class Khacphuc_Baoso3 extends ActionNhaptaycnMain
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
    private String chotsl_tw;
    private String txtGetData;
    private String status;
    private String message;
    private String check_cn;
    private String title1;
    private String tento;
    private String tenxa;
//<editor-fold defaultstate="collapsed" desc="khai báo get,set">

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

            String dateStr = hmParameter.get("ngay_bc").toString();
            String maxa = hmParameter.get("maxa").toString();
            String mato = hmParameter.get("mato").toString().substring(7, 14);
            if (maxa.equals("000000")) {
                addActionError("Bạn chưa chọn xã!");
                return ERROR;
            }
            if (mato.equals("0000000")) {
                addActionError("Bạn chưa chọn tổ TK&VV!");
                return ERROR;
            }
            final String _reportDate = new SimpleDateFormat("yyyyMMdd").format(new SimpleDateFormat("dd-MMM-yyyy").parse(dateStr));
            DaoNghiquyet11cp daoMain = new DaoNghiquyet11cp();

            if (!Arrays.asList("03", "26", "07", "09", "17", "18", "19", "16", "15", "63", "25", "24", "21", "20",
                    "23", "11", "12", "22", "13", "14", "04", "06", "05", "08", "10", "27").contains(main_pos_username.substring(2, 4))) {
                addActionError("Đơn vị không thuộc danh sách ảnh hưởng bời bão lũ do cơn bão số 3!");
                return ERROR;
            }
            ArrayList<LockSendModel> lstData_tmp = _serverAPI.getDataLockManual("KPBL_01", pos_cd_username, "S", _reportDate);
            try {
                setChotsl_tw(lstData_tmp.get(0).getStatus());
            } catch (Exception e) {
                setChotsl_tw("0");
            }
            lstData = daoMain.getLock_Baoso3(conn, "KPBL_LOCK_C1", dateStr, UserName, pos_cd_username, maxa, mato);
            try {
                setChotsl(lstData.get(0).getD2());
                setTenxa(lstData.get(0).getD4());
                setTento(lstData.get(0).getD3());
            } catch (Exception e) {
                setChotsl("10");
                setTento("");
                setTenxa("");
            }
            if (chotsl.equals("1")) {
                setTitle1("Tổ TK&VV:" + tento + " - Xã: " + tenxa + " đã gửi dữ liệu lên CN!");
            } else {
                setTitle1("Tổ TK&VV:" + mato + " - Xã: " + maxa);
            }
//            System.out.println("title :" +title1);
            lstDulieuNt = daoMain.getData_baoso3(conn, dateStr, "AAA", UserName, pos_cd_username, maxa, "S", mato);
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> load method: " + e.getMessage());
            return ERROR;
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
            String dateStr = hmParameter.get("ngay_bc").toString();
            String nghiepvu = hmParameter.get("txtGetData").toString();
            final String _reportDate = new SimpleDateFormat("yyyyMMdd").format(new SimpleDateFormat("dd-MMM-yyyy").parse(dateStr));
            DaoNghiquyet11cp daoMain = new DaoNghiquyet11cp();
            setTxtGetData(nghiepvu);
            if (nghiepvu.equals("1")) {
                lstDulieuNt = daoMain.getLock_Baoso3_2(conn, "BBB", dateStr, UserName, main_pos_username, "", "");
            } else {
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
                        row.setD4(null);
                        row.setD5(null);
                        row.setD6(null);
                        row.setD7(dateStr);
                        row.setD8(null);
                        lstData_tmp = _serverAPI.getDataLockManual("KPBL_01", item.getPosCode(), "S", _reportDate);

                        // If there are data rows to process
                        if (!lstData_tmp.isEmpty()) {
                            for (LockSendModel item_tmp : lstData_tmp) {
                                QT_DULIEU_NT dataRow = new QT_DULIEU_NT();
                                try {
                                    dataRow.setD1(item.getPosCode());
                                    dataRow.setD2(item.getPosName());
                                    dataRow.setD3(item_tmp.getUpdateDate());
                                    dataRow.setD4(item_tmp.getStatus());
                                    dataRow.setD5(item_tmp.getReportDate());
                                    dataRow.setD6(item_tmp.getPosFlag());
                                    dataRow.setD7(dateStr);
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
            String sngaybc = hmParameter.get("ngay_bc").toString();
            String smacn = hmParameter.get("lstCN").toString();
            Connection conn = new DaoConnect().getConnect();
            DaoNghiquyet11cp daoMain = new DaoNghiquyet11cp();
            lstDulieuNt = daoMain.getData_THTK_c3(conn, sngaybc, "KPBL_01", smacn, "S");
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
            String maxa = hmParameter.get("maxa").toString();
            String mato = hmParameter.get("mato").toString().substring(7, 14);
            final String _reportDate = new SimpleDateFormat("yyyyMMdd").format(new SimpleDateFormat("dd-MMM-yyyy").parse(dateStr));
            final String _reportDate1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS").format(new Date());
            posMainModel = listKTNBDA.get_pos_main_pos(UserName, Grade);
            pos_cd_username = posMainModel.getPosCd();
            main_pos_username = posMainModel.getMainPosCd();
            ArrayList<QT_DULIEU_NT> lstLocalDataUpdate = new ArrayList<>();
            for (QT_DULIEU_NT tmp : lstDulieuNt) {
                QT_DULIEU_NT temlocal = new QT_DULIEU_NT();
                temlocal.setKHOA("KPBL_01");
                temlocal.setTHUTU(tmp.getTHUTU());
                temlocal.setTT_HIENTHI(tmp.getTT_HIENTHI());
                temlocal.setTEN(tmp.getTEN());
                temlocal.setMA(tmp.getMA().equals("") ? tmp.getMAPGD() : tmp.getMA());
                temlocal.setNGUOI_DUYET(UserName);
//                temlocal.setNGAY_DUYET(date);
                temlocal.setNGUOI_NHAP(UserName);
//                temlocal.setNGAY_NHAP(date);
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
            }

//            _serverAPI = new DuLieuNTService();
//            int status = _serverAPI.getGQVL2023("THTK_2024", pos_cd_username, "S", _reportDate, "", "", lstUpdateDate);
            int status = 200;
            if (status == 200) {
//                _serverAPI.updateChotSL("THTK_2024", pos_cd_username, "S", _reportDate, "0", UserName, null);
                if (!daoMain.save_KPBL_2024("KPBL_01", dateStr, UserName, pos_cd_username, maxa, mato, lstLocalDataUpdate)) {
                    addActionError("Bạn chưa lưu được báo cáo tại chi nhánh vui lòng liên hệ quản trị viên!");
                    String code = String.valueOf(2);
                    this.pageResult = new ByteArrayInputStream(code.getBytes(StandardCharsets.UTF_8));
                    return ERROR;

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
                addActionError("Chưa có dữ liệu để gửi");
                return ERROR;
            }

            HashMap hmParameter = getParameter();
            String dateStr = hmParameter.get("ngay_bc").toString();
            String maxa = hmParameter.get("maxa").toString();
            String mato = hmParameter.get("mato").toString().substring(7, 14);

            DaoNghiquyet11cp daoMain = new DaoNghiquyet11cp();
            int status = 200;

            if (status == 200 && !daoMain.send_KPBL_2024_C1("KPBL_01", dateStr, UserName, pos_cd_username, maxa, mato)) {
                addActionError("Bạn chưa gửi được báo cáo tại chi nhánh, vui lòng liên hệ quản trị viên!");
                this.pageResult = new ByteArrayInputStream("2".getBytes(StandardCharsets.UTF_8));
                return ERROR;
            }
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> send: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> send: " + e.getMessage());
        }

        addActionMessage("Bạn đã gửi dữ liệu thành công");
        this.pageResult = new ByteArrayInputStream("200".getBytes(StandardCharsets.UTF_8));
        return SUCCESS;
    }

    public String unlock_c2() {
        try {

            String D1 = ServletActionContext.getRequest().getParameter("smaxa");
            String D3 = ServletActionContext.getRequest().getParameter("smato");
            String D7 = ServletActionContext.getRequest().getParameter("sngaybc");
            String type = ServletActionContext.getRequest().getParameter("type");
            DaoNghiquyet11cp daoMain = new DaoNghiquyet11cp();
            GenericResult<String> _result;
            if (type.equals("1")) {
                _result = daoMain.unlock_baoso3_c2("AAA", D1, D3, D7);
            } else {
                _result = daoMain.unlock_baoso3_c2("AAA1", D1, D3, D7);
            }

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

    public String sendTW() {
        System.out.println("vao váe");
        try {
            if (!getParaSession()) {
                return ERROR;
            }
            DaoNghiquyet11cp daoMain = new DaoNghiquyet11cp();
            Connection conn = new DaoConnect().getConnect();
            String D1 = ServletActionContext.getRequest().getParameter("madiemgd");
            String D5 = ServletActionContext.getRequest().getParameter("ngaybc");
            final String _reportDate = new SimpleDateFormat("yyyyMMdd").format(new SimpleDateFormat("dd-MMM-yyyy").parse(D5));
            final String _reportDate1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS").format(new Date());
            lstData = daoMain.getData_baoso3(conn, D5, "AAA1", UserName, D1, "", "S", "");
            ArrayList<DuLieuNTRowX> lstUpdateDate = new ArrayList<>();
            for (QT_DULIEU_NT tmp : lstData) {
                DuLieuNTRowX tempadd = new DuLieuNTRowX();
                tempadd.setKey("KPBL_01");
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
            int status = _serverAPI.getGQVL2023("KPBL_01", D1, "S", _reportDate, "", "", lstUpdateDate);
            int skhoa = _serverAPI.updateChotSL("KPBL_01", D1, "S", _reportDate, "2", UserName, null);
            if (status != 200 || skhoa != 200) {
                addActionError("Bạn chưa lưu được báo cáo tại chi nhánh vui lòng liên hệ quản trị viên!");
                String code = String.valueOf(2);
                this.pageResult = new ByteArrayInputStream(code.getBytes(StandardCharsets.UTF_8));
                return ERROR;
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

    public String popupTablePos() throws Exception {
        try {
            String D1 = ServletActionContext.getRequest().getParameter("madiemgd");
            String D5 = ServletActionContext.getRequest().getParameter("ngaybc");
            String type = ServletActionContext.getRequest().getParameter("type");
            final String _reportDate = new SimpleDateFormat("yyyyMMdd").format(new SimpleDateFormat("dd-MMM-yyyy").parse(D5));
            DaoNghiquyet11cp daoMain = new DaoNghiquyet11cp();
            Connection conn = new DaoConnect().getConnect();
            ArrayList<LockSendModel> lstData_tmp = _serverAPI.getDataLockManual("KPBL_01", D1, "S", _reportDate);
            try {
                setChotsl_tw(lstData_tmp.get(0).getStatus());
            } catch (Exception e) {
                setChotsl_tw("0");
            }
            setTxtGetData(type);
            if (txtGetData.equals("1")) {
                lstDulieuNt = daoMain.getLock_Baoso3_2(conn, "BBB1", D5, UserName, D1, "", "");
            } else {
                lstDulieuNt = daoMain.getLock_Baoso3_2(conn, "BBB2", D5, UserName, D1, "", "");
            }

        } catch (Exception e) {
            System.err.println("Loi trong ham saveDataaa " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " saveDataaa -> " + e.getMessage());
        }
        return "success";
    }

    public String unlock_c3() {
        try {
            String D1 = ServletActionContext.getRequest().getParameter("madiemgd");
            String D5 = ServletActionContext.getRequest().getParameter("ngaybc");
            String D6 = ServletActionContext.getRequest().getParameter("pos_flag");
            String D7 = ServletActionContext.getRequest().getParameter("key_lock");
            SimpleDateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat outputFormat = new SimpleDateFormat("dd/MMM/yyyy");
            Date date = inputFormat.parse(D5);
            String formattedDate = outputFormat.format(date);
            DaoNghiquyet11cp daoMain = new DaoNghiquyet11cp();
            GenericResult<String> _result = daoMain.unlock_c3_THTK("KPBL_01", D1, D6, formattedDate, D7);

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
