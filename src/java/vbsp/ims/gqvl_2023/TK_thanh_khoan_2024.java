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
import java.nio.charset.StandardCharsets;
import vbsp.ims.nhaptaycn.action.*;
import java.sql.Connection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
import vbsp.ims.util.DateUtil;

/**
 *
 * @author ducanh
 */
public class TK_thanh_khoan_2024 extends ActionNhaptaycnMain
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
//<editor-fold defaultstate="collapsed" desc="khai báo get,set">

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
            HashMap hmParameter = getParameter();
            Connection conn = new DaoConnect().getConnect();
            posMainModel = listKTNBDA.get_pos_main_pos(UserName, Grade);
            pos_cd_username = posMainModel.getPosCd();
            main_pos_username = posMainModel.getMainPosCd();
            String dateStr = hmParameter.get("ngay_bc").toString();
            final String _reportDate = new SimpleDateFormat("yyyyMMdd").format(new SimpleDateFormat("dd-MMM-yyyy").parse(dateStr));
            String Pos_Flag = "";
            txtGetData = hmParameter.get("txtGetData").toString();
            switch (txtGetData) {
                case "1":
                    Pos_Flag = "D";
                    break;
                case "2":
                    Pos_Flag = "M";
                    break;
                case "3":
                    Pos_Flag = "Y";
                    break;
                default:
                    break;
            }
            ArrayList<LockSendModel> lstData_tmp = _serverAPI.getDataLockManual("THTK_2024", pos_cd_username, Pos_Flag, _reportDate);
            try {
                setChotsl(lstData_tmp.get(0).getStatus());
            } catch (Exception e) {
                setChotsl("0");
            }
            lstData_Api = _serverAPI.getData("THTK_2024", pos_cd_username, Pos_Flag, _reportDate);
            if (lstData_Api == null || lstData_Api.isEmpty()) {
                addActionError("Chưa có dữ liệu, liên hệ TTCNTT để được hỗ trợ");
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
                    lstDulieuNt.add(row);
                } catch (Exception e) {
                }
            }
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

            // Chuyển đổi ngày báo cáo
            String dateStr = hmParameter.get("ngay_bc").toString();
            DateFormat dateHienthi = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            final String _reportDate = new SimpleDateFormat("yyyyMMdd").format(new SimpleDateFormat("dd-MMM-yyyy").parse(dateStr));

            // Xác định Pos_Flag
            String Pos_Flag = "";
            txtGetData = hmParameter.get("txtGetData").toString();
            switch (txtGetData) {
                case "1":
                    Pos_Flag = "D";
                    break;
                case "2":
                    Pos_Flag = "M";
                    break;
                case "3":
                    Pos_Flag = "Y";
                    break;
                default:
                    break;
            }

            // Gọi API để lấy danh sách PGD
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
                    row.setD6(Pos_Flag);
                    lstData_tmp = _serverAPI.getDataLockManual("THTK_2024", item.getPosCode(), Pos_Flag, _reportDate);

                    // If there are data rows to process
                    if (!lstData_tmp.isEmpty()) {
                        for (LockSendModel item_tmp : lstData_tmp) {
                            QT_DULIEU_NT dataRow = new QT_DULIEU_NT();
                            try {
                                dataRow.setD1(item.getPosCode());
                                dataRow.setD2(item.getPosName());
                                if (item_tmp.getUpdateDate() != null) {
                                    dataRow.setD3(dateHienthi.format(sdf.parse(item_tmp.getUpdateDate())));
                                }
                                dataRow.setD4(item_tmp.getStatus());
                                dataRow.setD5(item_tmp.getReportDate());
                                dataRow.setD6(item_tmp.getPosFlag());
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
            String Pos_Flag = "";
            txtGetData = hmParameter.get("txtGetData").toString();
            switch (txtGetData) {
                case "1":
                    Pos_Flag = "D";
                    break;
                case "2":
                    Pos_Flag = "M";
                    break;
                case "3":
                    Pos_Flag = "Y";
                    break;
                default:
                    break;
            }
            Connection conn = new DaoConnect().getConnect();
            DaoNghiquyet11cp daoMain = new DaoNghiquyet11cp();
            lstDulieuNt = daoMain.getData_THTK_c3(conn, sngaybc, "THTK_2024", smacn, Pos_Flag);
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
            if (lstDulieuNt == null || lstDulieuNt.isEmpty()) {
                addActionError("Bạn chưa lưu được báo cáo xin liên hệ với quản trị để khắc phục");
                return ERROR;
            }
            DaoNghiquyet11cp daoMain = new DaoNghiquyet11cp();
            HashMap hmParameter = getParameter();
            String dateStr = hmParameter.get("ngay_bc").toString();
            final String _reportDate = new SimpleDateFormat("yyyyMMdd").format(new SimpleDateFormat("dd-MMM-yyyy").parse(dateStr));
            final String _reportDate1 = new SimpleDateFormat("yyyy-MM-dd'T'00:00:00.000").format(new SimpleDateFormat("yyyyMMdd").parse(_reportDate));
            posMainModel = listKTNBDA.get_pos_main_pos(UserName, Grade);
            pos_cd_username = posMainModel.getPosCd();
            main_pos_username = posMainModel.getMainPosCd();
            String Pos_Flag = "";
            txtGetData = hmParameter.get("txtGetData").toString();
            switch (txtGetData) {
                case "1":
                    Pos_Flag = "D";
                    break;
                case "2":
                    Pos_Flag = "M";
                    break;
                case "3":
                    Pos_Flag = "Y";
                    break;
                default:
                    break;
            }
            ArrayList<DuLieuNTRowX> lstUpdateDate = new ArrayList<>();
            ArrayList<QT_DULIEU_NT> lstLocalDataUpdate = new ArrayList<>();
            for (QT_DULIEU_NT tmp : lstDulieuNt) {
                DuLieuNTRowX tempadd = new DuLieuNTRowX();
                tempadd.setKey("THTK_2024");
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
                lstUpdateDate.add(tempadd);
                QT_DULIEU_NT temlocal = new QT_DULIEU_NT();
                temlocal.setKHOA("THTK_2024");
                temlocal.setTHUTU(tmp.getTHUTU());
                temlocal.setTT_HIENTHI(tmp.getTT_HIENTHI());
                temlocal.setTEN(tmp.getTEN());
                temlocal.setMA(tmp.getMA());
                temlocal.setNGUOI_DUYET(UserName);
                temlocal.setNGUOI_NHAP(UserName);
                temlocal.setNAMBC(tmp.getNAMBC());
                temlocal.setMAPGD(tmp.getMAPGD());
                temlocal.setCO_TONGHOP(Pos_Flag);
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
                lstLocalDataUpdate.add(temlocal);
            }

            _serverAPI = new DuLieuNTService();
            int status = _serverAPI.getGQVL2023("THTK_2024", pos_cd_username, Pos_Flag, _reportDate, "", "", lstUpdateDate);
            if (status == 200) {
//                _serverAPI.updateChotSL("THTK_2024", pos_cd_username, Pos_Flag, _reportDate, "0", UserName, null);
                if (!daoMain.save_THTK_2024("THTK_2024", UserName, Pos_Flag, hmParameter.get("ngay_bc").toString(), lstLocalDataUpdate, pos_cd_username)) {
//                    addActionError("Bạn chưa lưu được báo cáo tại chi nhánh vui lòng liên hệ quản trị viên!");
                    String code = String.valueOf(2);
                    this.pageResult = new ByteArrayInputStream(code.getBytes(StandardCharsets.UTF_8));
                    return ERROR;
                }
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
            final String _reportDate = new SimpleDateFormat("yyyyMMdd").format(new SimpleDateFormat("dd-MMM-yyyy").parse(dateStr));
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
            posMainModel = listKTNBDA.get_pos_main_pos(UserName, Grade);
            pos_cd_username = posMainModel.getPosCd();
            main_pos_username = posMainModel.getMainPosCd();
            String Pos_Flag = "";
            txtGetData = hmParameter.get("txtGetData").toString();
            switch (txtGetData) {
                case "1":
                    Pos_Flag = "D";
                    break;
                case "2":
                    Pos_Flag = "M";
                    break;
                case "3":
                    Pos_Flag = "Y";
                    break;
                default:
                    break;
            }
            int status = 200;
            if (status == 200) {
                _serverAPI.updateChotSL("THTK_2024", pos_cd_username, Pos_Flag, _reportDate, "1", UserName, null);
                String code = String.valueOf(status);
                this.pageResult = new ByteArrayInputStream(code.getBytes(StandardCharsets.UTF_8));
                return SUCCESS;
            } else {
                addActionError("Bạn chưa gửi được dữ liệu!");
                String code = String.valueOf(2);
                this.pageResult = new ByteArrayInputStream(code.getBytes(StandardCharsets.UTF_8));
                return ERROR;
            }
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> muats2024: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> muats2024: " + e.getMessage());
        }
        addActionMessage("Bạn gửi lưu dữ liệu thành công");
        String code = String.valueOf(200);
        this.pageResult = new ByteArrayInputStream(code.getBytes(StandardCharsets.UTF_8));
        return SUCCESS;
    }

    public String unlock_c2() {
        try {
            String D1 = ServletActionContext.getRequest().getParameter("madiemgd");
            String D5 = ServletActionContext.getRequest().getParameter("ngaybc");
            String D6 = ServletActionContext.getRequest().getParameter("pos_flag");
            Date date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(D5);
            String _reportDate = new SimpleDateFormat("yyyyMMdd").format(date);
            int status = _serverAPI.updateChotSL("THTK_2024", D1, D6, _reportDate, "0", UserName, null);;
            if (status != 200) {
                String code = String.valueOf(1);
                this.pageResult = new ByteArrayInputStream(code.getBytes(StandardCharsets.UTF_8));
                return ERROR;
            }
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> unlock_c2: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> unlock_c2: " + e.getMessage());
            addActionError("Có lỗi xảy ra, vui lòng thử lại!");
        }

        addActionMessage("Mở dữ liệu thành công");
        String code = String.valueOf(200);
        this.pageResult = new ByteArrayInputStream(code.getBytes(StandardCharsets.UTF_8));
        return SUCCESS;
    }

    public String lock_c2() {
        try {
            String D1 = ServletActionContext.getRequest().getParameter("madiemgd");
            String D5 = ServletActionContext.getRequest().getParameter("ngaybc");
            String D6 = ServletActionContext.getRequest().getParameter("pos_flag");
            Date date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(D5);
            String _reportDate = new SimpleDateFormat("yyyyMMdd").format(date);
            int status = _serverAPI.updateChotSL("THTK_2024", D1, D6, _reportDate, "2", UserName, null);;
            if (status != 200) {
                String code = String.valueOf(1);
                this.pageResult = new ByteArrayInputStream(code.getBytes(StandardCharsets.UTF_8));
                return ERROR;
            }
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> unlock_c2: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> unlock_c2: " + e.getMessage());
            addActionError("Có lỗi xảy ra, vui lòng thử lại!");
        }

        addActionMessage("Chốt dữ liệu thành công");
        String code = String.valueOf(200);
        this.pageResult = new ByteArrayInputStream(code.getBytes(StandardCharsets.UTF_8));
        return SUCCESS;
    }

    public String popupTablePos() throws Exception {
        try {
            String D1 = ServletActionContext.getRequest().getParameter("madiemgd");
            String D5 = ServletActionContext.getRequest().getParameter("ngaybc");
            String D6 = ServletActionContext.getRequest().getParameter("pos_flag");
            Date date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(D5);
            String _reportDate = new SimpleDateFormat("yyyyMMdd").format(date);
            ArrayList<LockSendModel> lstData_tmp = _serverAPI.getDataLockManual("THTK_2024", D1, D6, _reportDate);
            try {
                setChotsl(lstData_tmp.get(0).getStatus());
            } catch (Exception e) {
                setChotsl("0");
            }
            lstData_Api = _serverAPI.getData("THTK_2024", D1, D6, _reportDate);
            if (lstData_Api == null || lstData_Api.isEmpty()) {
                addActionError("Chưa có dữ liệu, liên hệ TTCNTT để được hỗ trợ");
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
            GenericResult<String> _result = daoMain.unlock_c3_THTK("THTK_2024", D1, D6, formattedDate, D7);

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
