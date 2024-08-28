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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.apache.struts2.ServletActionContext;
import vbsp.ims.bcqt.model.QT_DULIEU_NT;
import vbsp.ims.dao.DaoConnect;
import vbsp.ims.define.GenericResult;
import vbsp.ims.log.CoreLogger;
import vbsp.ims.nghiquyet11cp.DaoNghiquyet11cp;
import vbsp.ims.restapi.DuLieuNTRow;
import vbsp.ims.restapi.DuLieuNTRowX;
import vbsp.ims.restapi.DuLieuNTService;
import vbsp.ims.restapi.ListOfValue;
import vbsp.ims.restapi.LockSendModel;
import vbsp.ims.util.DateUtil;

/**
 *
 * @author ducanh
 */
public class Mua_Tsan_2024 extends ActionNhaptaycnMain
        implements NhaptaycnFunction {

    Service_GQVL2023 _server;
    DaoNghiquyet11cp _serverlocal;
    DuLieuNTService _serverAPI;
    private List<QT_DULIEU_NT> lstData;
    private List<DuLieuNTRow> lstData_Api;
    private List<ListOfValue> lstTaisan;
    private List<LockSendModel> lstData_tmp1;
    protected List<String> poscd_face = new ArrayList<String>();
    protected String main_pos_username;
    private String namBc;
    private String nambc_next;
    private InputStream pageResult;
    DuLieuNTService _service_listts = new DuLieuNTService();
    private String maPgd;
    private String tenPgd;
    private String status;
    private String message;
    private String lock_PGD;
    private String lock_CN;
    private String chotsl;
//<editor-fold defaultstate="collapsed" desc="khai báo get,set">

    public DuLieuNTService getServerAPI() {
        return _serverAPI;
    }

    public void setServerAPI(DuLieuNTService _serverAPI) {
        this._serverAPI = _serverAPI;
    }

    public List<LockSendModel> getLstData_tmp1() {
        return lstData_tmp1;
    }

    public void setLstData_tmp1(List<LockSendModel> lstData_tmp1) {
        this.lstData_tmp1 = lstData_tmp1;
    }

    public String getChotsl() {
        return chotsl;
    }

    public void setChotsl(String chotsl) {
        this.chotsl = chotsl;
    }

    public String getLock_PGD() {
        return lock_PGD;
    }

    public void setLock_PGD(String lock_PGD) {
        this.lock_PGD = lock_PGD;
    }

    public String getLock_CN() {
        return lock_CN;
    }

    public void setLock_CN(String lock_CN) {
        this.lock_CN = lock_CN;
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

    public DuLieuNTService getService_listts() {
        return _service_listts;
    }

    public void setService_listts(DuLieuNTService _service_listts) {
        this._service_listts = _service_listts;
    }

    public String getMaPgd() {
        return maPgd;
    }

    public void setMaPgd(String maPgd) {
        this.maPgd = maPgd;
    }

    public String getTenPgd() {
        return tenPgd;
    }

    public void setTenPgd(String tenPgd) {
        this.tenPgd = tenPgd;
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

    public List<ListOfValue> getLstTaisan() {
        return lstTaisan;
    }

    public void setLstTaisan(List<ListOfValue> lstTaisan) {
        this.lstTaisan = lstTaisan;
    }

    public List<String> getPoscd_face() {
        return poscd_face;
    }

    public void setPoscd_face(List<String> poscd_face) {
        this.poscd_face = poscd_face;
    }

    public String getNambc_next() {
        return nambc_next;
    }

    public void setNambc_next(String nambc_next) {
        this.nambc_next = nambc_next;
    }

    public String getNamBc() {
        return namBc;
    }

    public void setNamBc(String namBc) {
        this.namBc = namBc;
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
        try {
            if (!getParaSession()) {
                return ERROR;
            }
            HashMap hmParameter = getParameter();
            Connection conn = new DaoConnect().getConnect();
            posMainModel = listKTNBDA.get_pos_main_pos(UserName, Grade);
            pos_cd_username = posMainModel.getPosCd();
            main_pos_username = posMainModel.getMainPosCd();
            _serverlocal = new DaoNghiquyet11cp();
            _server = new Service_GQVL2023();
            poscd_face = Arrays.asList(pos_cd_username);
            lstTaisan = _service_listts.getListOfValue("110", "");
            String pos = poscd.toString().replace("[", "").replace("]", "") == null || poscd.toString().replace("[", "").replace("]", "").isEmpty() ? pos_cd_username : poscd.toString().replace("[", "").replace("]", "");
            if (!Grade.equals("1") && (poscd.isEmpty() || poscd.get(0).equals("999999") || poscd.size() > 1)) {
                addActionError("Vui lòng chọn từng phòng giao dịch để xem dữ liệu!");
                return ERROR;
            }

            String dateStr = hmParameter.get("ngay_bc").toString();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
            LocalDate date = LocalDate.parse(dateStr, formatter);
            final String _reportDate = new SimpleDateFormat("yyyyMMdd").format(new SimpleDateFormat("dd-MMM-yyyy").parse(dateStr));
            int year = date.getYear();
            setNamBc(String.valueOf(year));
            setNambc_next(String.valueOf(year + 1));
            ArrayList<LockSendModel> lstData_tmp = _service_listts.getDataLockManual("KTTC_MUASAM_01", pos, "S", _reportDate);
            try {
                setChotsl(lstData_tmp.get(0).getStatus());
            } catch (Exception e) {
                setChotsl("0");
            }

            lstData_Api = _server.getCustomers(pos, "S", dateStr, "1", "KTTC_MUASAM_01");
            for (DuLieuNTRow item : lstData_Api) {
                QT_DULIEU_NT row = new QT_DULIEU_NT();
                try {
                    row.setKHOA(item.getKey());
                    row.setTHUTU(0);
                    row.setTT_HIENTHI(item.getOrderDescription());
                    row.setMA(item.getCode());
                    Date reportDate = DateUtil.toDate(item.getReportDate());
                    row.setNGAYBC(reportDate);
                    row.setNAMBC(year);
                    row.setMAPGD(item.getPosCode());
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
                    lstDulieuNt.add(row);
                } catch (Exception e) {
                }
                setMaPgd(item.getPosCode());
                setTenPgd(item.getD1());
                setLock_PGD(item.getD12());
            }
            if (lstData_Api == null || lstData_Api.isEmpty()) {
                lstData = _serverlocal.getData_Muasam_2024(conn, "KTTC_MUASAM_01", hmParameter.get("ngay_bc").toString(), UserName, Grade, poscd);

                for (QT_DULIEU_NT item : lstData) {
                    QT_DULIEU_NT row = new QT_DULIEU_NT();
                    try {
                        row.setKHOA(item.getKHOA());
                        row.setTHUTU(item.getTHUTU());
                        row.setTT_HIENTHI(item.getTT_HIENTHI());
                        row.setMA(item.getMA());
                        row.setNGAYBC(item.getNGAYBC());
                        row.setNAMBC(year);
                        row.setMAPGD(item.getMAPGD());
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
                        lstDulieuNt.add(row);
                    } catch (Exception e) {
                    }
                    setMaPgd(item.getMAPGD());
                    setTenPgd(item.getD1());
                    setLock_PGD(item.getD12());
                }
                if (conn != null) {
                    conn.close();
                }
            }
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> tin dung 2024 : " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> tin dung 2024: " + e.getMessage());
        }
        if (!Grade.equals(3)) {
            return SUCCESS;
        } else {
            return "SUCCESS_3";
        }
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
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
            LocalDate date = LocalDate.parse(dateStr, formatter);
            String pos = poscd.toString().replace("[", "").replace("]", "") == null || poscd.toString().replace("[", "").replace("]", "").isEmpty() ? pos_cd_username : poscd.toString().replace("[", "").replace("]", "");
            main_pos_username = posMainModel.getMainPosCd();
            int year = date.getYear();
            String PosFlag = "S";
            ArrayList<DuLieuNTRow> lstUpdateDate = new ArrayList<>();
            List<QT_DULIEU_NT> lstLocalDataUpdate = new ArrayList<>();
            for (QT_DULIEU_NT tmp : lstDulieuNt) {
                DuLieuNTRow tempadd = new DuLieuNTRow();
                int iStt = 1;
                iStt++;
                tempadd.setKey("KTTC_MUASAM_01");
                tempadd.setOrderDescription(tmp.getTT_HIENTHI());
                tempadd.setName(tmp.getD1());
                tempadd.setCode(tmp.getMA() == null && tmp.getMA().isEmpty() ? pos + iStt + PosFlag + _reportDate + tmp.getD6() + tmp.getTT_HIENTHI() : tmp.getMA());
                tempadd.setMakerId(UserName);
                tempadd.setMakerDate(_reportDate1);
                tempadd.setAuthoriseId(UserName);
                tempadd.setAuthoriseDate(_reportDate1);
                tempadd.setReportDate(_reportDate1);
                tempadd.setReportYear(year);
                tempadd.setPosCode(pos);
                tempadd.setPosFlag(PosFlag);
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
//                tempadd.setD12("0");
                lstUpdateDate.add(tempadd);
//
//                QT_DULIEU_NT temlocal = new QT_DULIEU_NT();
//                temlocal.setKHOA("KTTC_MUASAM_01");
//                temlocal.setTT_HIENTHI(tmp.getTT_HIENTHI());
//                temlocal.setTEN(tmp.getD1());
//                temlocal.setMA(pos + iStt + PosFlag + _reportDate + tmp.getD6() + tmp.getTT_HIENTHI());
//                temlocal.setNGUOI_DUYET(UserName);
//                temlocal.setNGUOI_NHAP(UserName);
//                temlocal.setNAMBC(year);
//                temlocal.setMAPGD(pos);
//                temlocal.setCO_TONGHOP(PosFlag);
//                temlocal.setMACN(main_pos_username);
//                temlocal.setD1(tmp.getD1());
//                temlocal.setD2(tmp.getD2());
//                temlocal.setD3(tmp.getD3());
//                temlocal.setD4(tmp.getD4());
//                temlocal.setD5(tmp.getD5());
//                temlocal.setD6(tmp.getD6());
//                temlocal.setD7(tmp.getD7());
//                temlocal.setD8(tmp.getD8());
//                temlocal.setD9(tmp.getD9());
//                temlocal.setD10(tmp.getD10());
//                temlocal.setD11(tmp.getD11());
//                temlocal.setD12("0");
//                lstLocalDataUpdate.add(temlocal);
            }

            _service_listts = new DuLieuNTService();
            int status = _service_listts.updateData("KTTC_MUASAM_01", pos, PosFlag, _reportDate, "", "", lstUpdateDate);
//            System.out.println(status);
//            if (status == 200) {
//                if (!daoMain.saveMSTS_2024("KTTC_MUASAM_01", UserName, Grade, hmParameter.get("ngay_bc").toString(), lstLocalDataUpdate, pos)) {
//                    addActionError("Thất bại: Lưu dữ liệu tại chi nhánh không thành công!");
//                    String code = String.valueOf(2);
//                    this.pageResult = new ByteArrayInputStream(code.getBytes(StandardCharsets.UTF_8));
//                    return ERROR;
//                }
//            }
            if (status != 200) {
                String code = String.valueOf(status);
                this.pageResult = new ByteArrayInputStream(code.getBytes(StandardCharsets.UTF_8));
                return SUCCESS;
            } else if (status == 200 && !Grade.equals("1")) {
                _service_listts.updateChotSL("KTTC_MUASAM_01", pos, "S", _reportDate, "0", UserName, null);
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
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
            LocalDate date = LocalDate.parse(dateStr, formatter);
            String pos = poscd.toString().replace("[", "").replace("]", "") == null || poscd.toString().replace("[", "").replace("]", "").isEmpty() ? pos_cd_username : poscd.toString().replace("[", "").replace("]", "");
            main_pos_username = posMainModel.getMainPosCd();
            int year = date.getYear();
            String PosFlag = "S";
            ArrayList<DuLieuNTRow> lstUpdateDate = new ArrayList<>();
            List<QT_DULIEU_NT> lstLocalDataUpdate = new ArrayList<>();
//            for (QT_DULIEU_NT tmp : lstDulieuNt) {
//                DuLieuNTRow tempadd = new DuLieuNTRow();
//                int iStt = 1;
//                iStt++;
//                tempadd.setKey("KTTC_MUASAM_01");
//                tempadd.setOrderDescription(tmp.getTT_HIENTHI());
//                tempadd.setName(tmp.getD1());
//                tempadd.setCode(pos + iStt + PosFlag + _reportDate + tmp.getD6() + tmp.getTT_HIENTHI());
//                tempadd.setMakerId(UserName);
//                tempadd.setMakerDate(_reportDate1);
//                tempadd.setAuthoriseId(UserName);
//                tempadd.setAuthoriseDate(_reportDate1);
//                tempadd.setReportDate(_reportDate1);
//                tempadd.setReportYear(year);
//                tempadd.setPosCode(pos);
//                tempadd.setPosFlag(PosFlag);
//                tempadd.setBranchCode(main_pos_username);
//                tempadd.setD1(tmp.getD1());
//                tempadd.setD2(tmp.getD2());
//                tempadd.setD3(tmp.getD3());
//                tempadd.setD4(tmp.getD4());
//                tempadd.setD5(tmp.getD5());
//                tempadd.setD6(tmp.getD6());
//                tempadd.setD7(tmp.getD7());
//                tempadd.setD8(tmp.getD8());
//                tempadd.setD9(tmp.getD9());
//                tempadd.setD10(tmp.getD10());
//                tempadd.setD11(tmp.getD11());
//                tempadd.setD12("1");
//                lstUpdateDate.add(tempadd);
//
//                QT_DULIEU_NT temlocal = new QT_DULIEU_NT();
//                temlocal.setKHOA("KTTC_MUASAM_01");
//                temlocal.setTT_HIENTHI(tmp.getTT_HIENTHI());
//                temlocal.setTEN(tmp.getD1());
//                temlocal.setMA(pos + iStt + PosFlag + _reportDate + tmp.getD6() + tmp.getTT_HIENTHI());
//                temlocal.setNGUOI_DUYET(UserName);
//                temlocal.setNGUOI_NHAP(UserName);
//                temlocal.setNAMBC(year);
//                temlocal.setMAPGD(pos);
//                temlocal.setCO_TONGHOP(PosFlag);
//                temlocal.setMACN(main_pos_username);
//                temlocal.setD1(tmp.getD1());
//                temlocal.setD2(tmp.getD2());
//                temlocal.setD3(tmp.getD3());
//                temlocal.setD4(tmp.getD4());
//                temlocal.setD5(tmp.getD5());
//                temlocal.setD6(tmp.getD6());
//                temlocal.setD7(tmp.getD7());
//                temlocal.setD8(tmp.getD8());
//                temlocal.setD9(tmp.getD9());
//                temlocal.setD10(tmp.getD10());
//                temlocal.setD11(tmp.getD11());
//                temlocal.setD12("1");
//                lstLocalDataUpdate.add(temlocal);
//            }

            _service_listts = new DuLieuNTService();
            int status = 200;
//                    _service_listts.updateData("KTTC_MUASAM_01", pos, PosFlag, _reportDate, "", "", lstUpdateDate);
//            System.out.println(status);
//            if (status == 200) {
//                if (!daoMain.saveMSTS_2024("KTTC_MUASAM_01", UserName, Grade, hmParameter.get("ngay_bc").toString(), lstLocalDataUpdate, pos)) {
//                    addActionError("Thất bại: Gửi dữ liệu cấp chi nhánh không thành công!");
//                    String code = String.valueOf(2);
//                    this.pageResult = new ByteArrayInputStream(code.getBytes(StandardCharsets.UTF_8));
//                    return ERROR;
//                }
//            }
            if (status == 200) {
                _service_listts.updateChotSL("KTTC_MUASAM_01", pos, "S", _reportDate, "1", UserName, null);
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

    public String seach() {
        Connection conn = null;
        try {
            if (!getParaSession()) {
                return ERROR;
            }

            // Lấy tham số và định dạng ngày
            HashMap hmParameter = getParameter();
            String sngaybc = hmParameter.get("ngay_bc").toString();
            DateFormat dateHienthi = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            final String _reportDate = new SimpleDateFormat("yyyyMMdd").format(new SimpleDateFormat("dd-MMM-yyyy").parse(sngaybc));

            // Lấy thông tin chính từ posMainModel
            posMainModel = listKTNBDA.get_pos_main_pos(UserName, Grade);
            pos_cd_username = posMainModel.getPosCd();
            main_pos_username = posMainModel.getMainPosCd();

            _serverlocal = new DaoNghiquyet11cp();
            conn = new DaoConnect().getConnect();

            List<LockSendModel> allCustomers = new ArrayList<>();

            // Kiểm tra và xử lý dữ liệu
            if (poscd.isEmpty()) {
                addActionError("Chọn 'Hội sở' để xem tình trạng gửi dữ liệu của các PGD");
                return ERROR;
            }
//            if (poscd.get(0).equals("999999")) {
//                lstDulieuNt = _serverlocal.seach_StatusMSTS(conn, sngaybc, "KTTC_MUASAM_01", main_pos_username);

            if (poscd.get(0).equals("999999")) {
                for (String pos : poscd) {
                    if (!pos.equals("999999")) {
                        ArrayList<LockSendModel> lstData_tmp = _service_listts.getDataLockManual("KTTC_MUASAM_01", pos, "S", _reportDate);

                        // Định dạng ngày trước khi thêm vào danh sách
                        for (LockSendModel item : lstData_tmp) {
                            if (item.getUpdateDate() != null) {
                                item.setUpdateDate(dateHienthi.format(sdf.parse(item.getUpdateDate())));
                            }
                            if (item.getReportDate() != null) {
                                item.setReportDate(dateHienthi.format(sdf.parse(item.getReportDate())));
                            }
                        }
                        allCustomers.addAll(lstData_tmp);
                    }
                }
                this.lstData_tmp1 = allCustomers;
            } //            } 
            else {
                addActionError("Chọn 'Hội sở' để xem tình trạng gửi dữ liệu của các PGD");
                return ERROR;
            }

        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> seach 2024 : " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> seach 2024: " + e.getMessage());
            return ERROR;
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                CoreLogger.error(this.getClass().getName() + " Exception in closing connection: " + e.getMessage());
            }
        }
        return SUCCESS;
    }

    public String loadc3() {
        try {
            if (!getParaSession()) {
                return ERROR;
            }
            HashMap hmParameter = getParameter();
            String sngaybc = hmParameter.get("ngay_bc").toString();
            String smacn = hmParameter.get("lstCN").toString();
            Connection conn = new DaoConnect().getConnect();
            DaoNghiquyet11cp daoMain = new DaoNghiquyet11cp();
            lstDulieuNt = daoMain.getData_load_c3(conn, sngaybc, "KTTC_MUASAM_01", smacn);
            System.out.println(sngaybc + " " + smacn);
            if (conn != null) {
                conn.close();
            }

        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> gdx: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> gdx: " + e.getMessage());
        }
        return SUCCESS;
    }

    public String loadDMChtrinh() {
        List<ListOfValue> listOfValues = _service_listts.getListOfValue("110", "");
        Map<String, String> mapAllChtrinh = new LinkedHashMap<>();
        for (ListOfValue value : listOfValues) {
            mapAllChtrinh.put(value.getDescription(), value.getValue());
        }
        setLstTaisan(listOfValues);
        return SUCCESS;
    }

    public String lock() {
        try {
            HashMap hmParameter = getParameter();
            String dateStr = hmParameter.get("ngay_bc").toString();
            final String _reportDate = new SimpleDateFormat("yyyyMMdd").format(new SimpleDateFormat("dd-MMM-yyyy").parse(dateStr));
            String pos = poscd.toString().replace("[", "").replace("]", "") == null || poscd.toString().replace("[", "").replace("]", "").isEmpty() ? pos_cd_username : poscd.toString().replace("[", "").replace("]", "");

            _service_listts = new DuLieuNTService();
            System.out.println(pos);
            if (pos == null || pos == "999999" || poscd.size() > 1) {
                addActionError("Chọn 1 PGD 'Tải dữ liệu' để xem và 'Chốt số liệu'");
                return ERROR;
            }
            ArrayList<LockSendModel> lstData_tmp = _service_listts.getDataLockManual("KTTC_MUASAM_01", pos, "S", _reportDate);

            // Kiểm tra trạng thái và thực hiện chốt số liệu
            if (lstData_tmp != null && !lstData_tmp.isEmpty()) {
                if (lstData_tmp.get(0).getStatus().equals("2")) {
                    addActionError("Dữ liệu đã chốt với TW, không thể chốt dữ liệu nữa.");
                    return ERROR;
                }
            }
            _service_listts.updateChotSL("KTTC_MUASAM_01", pos, "S", _reportDate, "2", UserName, null);
            addActionMessage("Bạn đã chốt dữ liệu và gửi lên TW thành công");
            return SUCCESS;
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> mua sam 2024: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception ->  mua sam 2024: " + e.getMessage());
            addActionError("Bạn chưa lưu được báo cáo xin liên hệ với quản trị để khắc phục");
            return ERROR;
        }
    }

    public String unlock_C3() {
        try {
            String D2 = ServletActionContext.getRequest().getParameter("madiemgd");
            String D5 = ServletActionContext.getRequest().getParameter("ssngaybc");
            SimpleDateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat outputFormat = new SimpleDateFormat("dd/MMM/yyyy");
            Date date = inputFormat.parse(D5);
            String formattedDate = outputFormat.format(date);
            String D6 = ServletActionContext.getRequest().getParameter("skhoa");
            System.out.println(D6 + " " + formattedDate + " " + D2);
            DaoNghiquyet11cp daoMain = new DaoNghiquyet11cp();
            GenericResult<String> _result = daoMain.cancelAssign("KTTC_MUASAM_01", D2, formattedDate);

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
            if (lstDulieuNt == null || lstDulieuNt.isEmpty()) {
                addActionError("Bạn chưa lưu được báo cáo xin liên hệ với quản trị để khắc phục");
                return ERROR;
            }
            DaoNghiquyet11cp daoMain = new DaoNghiquyet11cp();
            HashMap hmParameter = getParameter();
            String dateStr = hmParameter.get("ngay_bc").toString();
            final String _reportDate = new SimpleDateFormat("yyyyMMdd").format(new SimpleDateFormat("dd-MMM-yyyy").parse(dateStr));
            final String _reportDate1 = new SimpleDateFormat("yyyy-MM-dd'T'00:00:00.000").format(new SimpleDateFormat("yyyyMMdd").parse(_reportDate));
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
            LocalDate date = LocalDate.parse(dateStr, formatter);
            String pos = poscd.toString().replace("[", "").replace("]", "") == null || poscd.toString().replace("[", "").replace("]", "").isEmpty() ? pos_cd_username : poscd.toString().replace("[", "").replace("]", "");
            main_pos_username = posMainModel.getMainPosCd();
            int year = date.getYear();
            String PosFlag = "S";
            ArrayList<DuLieuNTRowX> lstUpdateDate = new ArrayList<>();
            List<QT_DULIEU_NT> lstLocalDataUpdate = new ArrayList<>();
            for (QT_DULIEU_NT tmp : lstDulieuNt) {
                DuLieuNTRowX tempadd = new DuLieuNTRowX();
                int iStt = 1;
                iStt++;
                tempadd.setKey("KTTC_MUASAM_01");
                tempadd.setOrderDescription(tmp.getTT_HIENTHI());
                tempadd.setName(tmp.getD1());
                tempadd.setCode(pos + iStt + PosFlag + _reportDate + tmp.getD6() + tmp.getTT_HIENTHI());
                tempadd.setMakerId(UserName);
                tempadd.setMakerDate(_reportDate1);
                tempadd.setAuthoriseId(UserName);
                tempadd.setAuthoriseDate(_reportDate1);
                tempadd.setReportDate(_reportDate1);
                tempadd.setReportYear(year);
                tempadd.setPosCode(pos);
                tempadd.setPosFlag(PosFlag);
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
//                tempadd.setD12("0");
                lstUpdateDate.add(tempadd);

//                QT_DULIEU_NT temlocal = new QT_DULIEU_NT();
//                temlocal.setKHOA("KTTC_MUASAM_01");
//                temlocal.setTT_HIENTHI(tmp.getTT_HIENTHI());
//                temlocal.setTEN(tmp.getD1());
//                temlocal.setMA(pos + iStt + PosFlag + _reportDate + tmp.getD6() + tmp.getTT_HIENTHI());
//                temlocal.setNGUOI_DUYET(UserName);
//                temlocal.setNGUOI_NHAP(UserName);
//                temlocal.setNAMBC(year);
//                temlocal.setMAPGD(pos);
//                temlocal.setCO_TONGHOP(PosFlag);
//                temlocal.setMACN(main_pos_username);
//                temlocal.setD1(tmp.getD1());
//                temlocal.setD2(tmp.getD2());
//                temlocal.setD3(tmp.getD3());
//                temlocal.setD4(tmp.getD4());
//                temlocal.setD5(tmp.getD5());
//                temlocal.setD6(tmp.getD6());
//                temlocal.setD7(tmp.getD7());
//                temlocal.setD8(tmp.getD8());
//                temlocal.setD9(tmp.getD9());
//                temlocal.setD10(tmp.getD10());
//                temlocal.setD11(tmp.getD11());
//                temlocal.setD12("0");
//                lstLocalDataUpdate.add(temlocal);
            }

            _service_listts = new DuLieuNTService();
            int status = _service_listts.deleteManualData("KTTC_MUASAM_01", pos, PosFlag, _reportDate, "", "", lstUpdateDate);
//            System.out.println(status);
//            if (status == 200) {
//                if (!daoMain.deleteMSTS_2024("KTTC_MUASAM_01", hmParameter.get("ngay_bc").toString(), pos)) {
//                    addActionError("Thất bại: Xóa dữ liệu tại chi nhánh không thành công!");
//                    String code = String.valueOf(2);
//                    this.pageResult = new ByteArrayInputStream(code.getBytes(StandardCharsets.UTF_8));
//                    return ERROR;
//                }
//            }
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
}
