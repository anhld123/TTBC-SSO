/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.nhaptaycn.action;

import static com.opensymphony.xwork2.Action.ERROR;
import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionContext;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.struts2.ServletActionContext;
import vbsp.ims.action.ktktnb.DULIEU_NT_TQ;
import vbsp.ims.bcqt.model.QT_DULIEU_NT;
import vbsp.ims.dao.DaoConnect;
import vbsp.ims.define.GenericResult;
import vbsp.ims.gqvl_2023.DaoBranchMain;
import vbsp.ims.gqvl_2023.Service_GQVL2023;
import vbsp.ims.log.CoreLogger;
import vbsp.ims.nghiquyet11cp.DaoNghiquyet11cp;
import vbsp.ims.restapi.DuLieuNTRow;
import vbsp.ims.restapi.DuLieuNTRowX;
import vbsp.ims.restapi.DuLieuNTService;
import vbsp.ims.restapi.ListCommune;
import vbsp.ims.restapi.ListOfValue;
import vbsp.ims.restapi.ListPosCode;
import vbsp.ims.restapi.LockSendModel;
import vbsp.ims.util.DateUtil;

public class HTLS_NGUONDP2025 extends ActionNhaptaycnMain
        implements NhaptaycnFunction {

    Service_GQVL2023 _server;
    DaoNghiquyet11cp _serverlocal;
    private List<QT_DULIEU_NT> lstData;
    private List<DuLieuNTRow> lstData_Api;
    private List<ListPosCode> lstPGD_API;
    private List<ListCommune> lstXa_API;
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
    private List<ListOfValue> lstDmKhac;
    protected String tong_kh;
    protected String tong_monvay;
    protected long tong_duno;
    protected long tong_than;
    protected long tong_qhan;
    protected long tong_khoanh;
    protected long lai_nhap;
    protected long lai_xnhan;
//<editor-fold defaultstate="collapsed" desc="khai báo get,set">

    public String getTong_kh() {
        return tong_kh;
    }

    public void setTong_kh(String tong_kh) {
        this.tong_kh = tong_kh;
    }

    public String getTong_monvay() {
        return tong_monvay;
    }

    public void setTong_monvay(String tong_monvay) {
        this.tong_monvay = tong_monvay;
    }

    public long getTong_duno() {
        return tong_duno;
    }

    public void setTong_duno(long tong_duno) {
        this.tong_duno = tong_duno;
    }

    public long getTong_than() {
        return tong_than;
    }

    public void setTong_than(long tong_than) {
        this.tong_than = tong_than;
    }

    public long getTong_qhan() {
        return tong_qhan;
    }

    public void setTong_qhan(long tong_qhan) {
        this.tong_qhan = tong_qhan;
    }

    public long getTong_khoanh() {
        return tong_khoanh;
    }

    public void setTong_khoanh(long tong_khoanh) {
        this.tong_khoanh = tong_khoanh;
    }

    public long getLai_nhap() {
        return lai_nhap;
    }

    public void setLai_nhap(long lai_nhap) {
        this.lai_nhap = lai_nhap;
    }

    public long getLai_xnhan() {
        return lai_xnhan;
    }

    public void setLai_xnhan(long lai_xnhan) {
        this.lai_xnhan = lai_xnhan;
    }

    @Override
    public List<ListCommune> getLstXa_API() {
        return lstXa_API;
    }

    @Override
    public List<ListOfValue> getLstDmKhac() {
        return lstDmKhac;
    }

    @Override
    public void setLstDmKhac(List<ListOfValue> lstDmKhac) {
        this.lstDmKhac = lstDmKhac;
    }

    @Override
    public void setLstXa_API(List<ListCommune> lstXa_API) {
        this.lstXa_API = lstXa_API;
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

    public List<DuLieuNTRow> getLstData_Api() {
        return lstData_Api;
    }

    public void setLstData_Api(List<DuLieuNTRow> lstData_Api) {
        this.lstData_Api = lstData_Api;
    }

    @Override
    public List<ListPosCode> getLstPGD_API() {
        return lstPGD_API;
    }

    @Override
    public void setLstPGD_API(List<ListPosCode> lstPGD_API) {
        this.lstPGD_API = lstPGD_API;
    }

    public List<LockSendModel> getLstData_tmp() {
        return lstData_tmp;
    }

    public void setLstData_tmp(List<LockSendModel> lstData_tmp) {
        this.lstData_tmp = lstData_tmp;
    }

    public String getMain_pos_username() {
        return main_pos_username;
    }

    public void setMain_pos_username(String main_pos_username) {
        this.main_pos_username = main_pos_username;
    }

    public InputStream getPageResult() {
        return pageResult;
    }

    public void setPageResult(InputStream pageResult) {
        this.pageResult = pageResult;
    }

    public DuLieuNTService getServerAPI() {
        return _serverAPI;
    }

    public void setServerAPI(DuLieuNTService _serverAPI) {
        this._serverAPI = _serverAPI;
    }

    public String getChotsl() {
        return chotsl;
    }

    public void setChotsl(String chotsl) {
        this.chotsl = chotsl;
    }

    public String getTxtGetData() {
        return txtGetData;
    }

    public void setTxtGetData(String txtGetData) {
        this.txtGetData = txtGetData;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public void setMessage(String message) {
        this.message = message;
    }

    public String getsCode() {
        return sCode;
    }

    public void setsCode(String sCode) {
        this.sCode = sCode;
    }

    public String getStype() {
        return stype;
    }

    public void setStype(String stype) {
        this.stype = stype;
    }

//</editor-fold>
    @Override
    public String load() {
        lstDmKhac = _serverAPI.getListOfValue("196", "GIAMLAI_2025");

        if ("1".equals(lstDmKhac.get(0).getValue()) && ("1".equals(Grade) || "2".equals(Grade))) {
            addActionError("Chương trình hiện tại chưa được quyền khai thác!");
            return ERROR;
        }

        return "1".equals(Grade) ? load_c1()
                : "2".equals(Grade) ? load_c2()
                : load_c3();
    }

    public String load_c1() {
        try {
            if (!getParaSession()) {
                return ERROR;
            }
            HashMap hmParameter = getParameter();
            posMainModel = listKTNBDA.get_pos_main_pos(UserName, Grade);
            pos_cd_username = posMainModel.getPosCd();
            main_pos_username = posMainModel.getMainPosCd();
            String dateStr = hmParameter.get("ngay_bc").toString();
            final String _reportDate = new SimpleDateFormat("yyyyMMdd").format(new SimpleDateFormat("dd-MMM-yyyy").parse(dateStr));
//            lstDmKhac = _serverAPI.getListOfValue("196", "GIAMLAI_2025");
//            if (lstDmKhac.get(0).getValue().equals("1")) {
//                addActionError("Chương trình hiện tại chưa được quyền khai thác!");
//                return ERROR;
//            }
            ArrayList<LockSendModel> lstData_tmp = _serverAPI.getDataLockManual("GIAMLAI_2025", main_pos_username, "M", _reportDate);
            try {
                setChotsl(lstData_tmp.get(0).getStatus());
            } catch (Exception e) {
                setChotsl("0");
            }
            ArrayList<LockSendModel> lstData_tmp1 = _serverAPI.getDataLockManual("GIAMLAI_2025", pos_cd_username, "S", _reportDate);
            try {
                setChotCic(lstData_tmp1.get(0).getStatus());
            } catch (Exception e) {
                setChotCic("0");
            }
            if (pos_cd_username.equals("000101")) {
                lstData_Api = _serverAPI.getData_condition("GIAMLAI_2025", pos_cd_username, "S", _reportDate, "");
            } else {
                String smaxa = (String) hmParameter.get("maxa");
                if (smaxa == null || smaxa.trim().isEmpty() || "000000".equals(smaxa)) {
                    addActionError("Bạn chưa chọn mã xã!");
                    return ERROR;
                }

                String ssmahoi = (hmParameter.get("mahoi") + "").trim();
                String smato = (hmParameter.get("mato") + "").trim();
                String snguonvon = (hmParameter.get("nguonvon") + "").trim();
                String schtrinh = (hmParameter.get("chtrinh") + "").trim();
                String sphanloai = (hmParameter.get("phanloai") + "").trim();
                setStype(sphanloai);
                String smahoi = "";
                String ssmato = "";

                if (smato != null && !smato.isEmpty()) {
                    String[] values = smato.split("_");
                    if (values.length >= 3) {
                        smahoi = values[0];
                        ssmato = values[2];
                    }
                }
                String condition = "D6=" + smaxa + "|"
                        + (ssmato.isEmpty() || ssmato.equals("0000000") ? "" : "D4=" + ssmato + "|")
                        + (ssmahoi.isEmpty() || ssmahoi.equals("0") ? "" : "D20=" + ssmahoi + "|")
                        + (snguonvon.isEmpty() || snguonvon.equals("0") ? "" : "D17=" + snguonvon + "|")
                        + (schtrinh.isEmpty() || schtrinh.equals("0") ? "" : "D10=" + schtrinh + "|")
                        + (sphanloai.isEmpty() || sphanloai.equals("99") ? "" : "D19=" + sphanloai + "|");
//                String condition = "D6=" + smaxa + "|"
//                        + (snguonvon.isEmpty() || snguonvon.equals("0") ? "" : "D17=" + snguonvon + "|")
//                        + (schtrinh.isEmpty() || schtrinh.equals("0") ? "" : "D10=" + schtrinh + "|")
//                        + (sphanloai.isEmpty() || sphanloai.equals("99") ? "" : "D19=" + sphanloai + "|");
                System.out.println("condition= " + condition);
                lstData_Api = _serverAPI.getData_condition("GIAMLAI_2025", pos_cd_username, "S", _reportDate, condition);
                if (lstData_Api.size() > 500) {
                    if (ssmahoi.isEmpty() || ssmahoi.equals("0")) {
                        addActionError("Dữ liệu quá lớn, vui lòng chọn mã hội!");
                        return ERROR;
                    }
                    if (ssmato.isEmpty() || ssmato.equals("0000000")) {
                        addActionError("Dữ liệu vẫn quá lớn, vui lòng chọn mã tổ!");
                        return ERROR;
                    }
                }
            }

            if (lstData_Api == null || lstData_Api.isEmpty()) {
                addActionError("Không có dữ liệu giảm lãi!");
                return ERROR;
            }
            for (DuLieuNTRow item : lstData_Api) {
                lstDulieuNt.add(convertToQT_DULIEU_NT(item));
            }
            lstDulieuNt.sort(
                    Comparator.comparing(obj -> layTen(obj.getD2()), String.CASE_INSENSITIVE_ORDER)
            );
//            lstDulieuNt.sort(
//                    Comparator.comparingInt((QT_DULIEU_NT obj) -> Integer.parseInt(obj.getD18())) 
//                            .thenComparing(obj -> layTen(obj.getD2()), String.CASE_INSENSITIVE_ORDER) 
//            );

            List<QT_DULIEU_NT> lstData = new ArrayList<>(lstDulieuNt);
            long tongThan = 0, tongQhan = 0, tongKhoanh = 0, tongMonvay = 0, lainhap = 0, laixnhan = 0;
            Set<String> setKhachHang = new HashSet<>();

            for (QT_DULIEU_NT item : lstData) {
                tongThan += toLong(item.getD14());
                tongQhan += toLong(item.getD15());
                tongKhoanh += toLong(item.getD16());
                tongMonvay++;

                if ("1".equals(item.getD19())) {
                    laixnhan++;
                } else {
                    lainhap++;
                }

                if (item.getD1() != null && !item.getD1().isEmpty()) {
                    setKhachHang.add(item.getD1());
                }
            }

            long tongDuno = tongThan + tongQhan + tongKhoanh;

            this.tong_monvay = String.valueOf(tongMonvay);
            this.tong_kh = String.valueOf(setKhachHang.size());
            this.tong_than = tongThan;
            this.tong_qhan = tongQhan;
            this.tong_khoanh = tongKhoanh;
            this.tong_duno = tongDuno;
            this.lai_nhap = lainhap;
            this.lai_xnhan = laixnhan;

        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> giam lai dp cap 1 : " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> giam lai dp cap 1: " + e.getMessage());
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
//            System.out.println("main_pos_username =" + main_pos_username);
            String dateStr = hmParameter.get("ngay_bc").toString();
            final String _reportDate = new SimpleDateFormat("yyyyMMdd").format(new SimpleDateFormat("dd-MMM-yyyy").parse(dateStr));
            lstPGD_API = _serverAPI.getListPgd(main_pos_username, "");
            if (lstPGD_API == null || lstPGD_API.isEmpty()) {
                addActionError("Hội sở Pos " + main_pos_username + " đã bị sát nhập không được xử dụng chương trình này!");
                return ERROR;
            }
            ArrayList<LockSendModel> lstData_tmp = _serverAPI.getDataLockManual("GIAMLAI_2025", main_pos_username, "M", _reportDate);
            try {
                setChotsl(lstData_tmp.get(0).getStatus());
            } catch (Exception e) {
                setChotsl("0");
            }

            for (ListPosCode item : lstPGD_API) {
                if (!Arrays.asList("001114", "000197", "002734", "002821", "004532").contains(item.getPosCode()) && item.getStatus().equals("O")) {
                    QT_DULIEU_NT row = new QT_DULIEU_NT();
                    try {
                        row.setD1(item.getPosCode());
                        row.setD2(item.getPosName());
                        row.setD3(item.getMainPos());
                        row.setD4("0");
                        row.setD5(dateStr);
                        row.setD6(null);
                        row.setD7(_reportDate);
                        String supdateId = "";
                        String supdateDate = "";

                        ArrayList<LockSendModel> lstData_tmp1 = _serverAPI.getDataLockManual("GIAMLAI_2025", item.getPosCode(), "S", _reportDate);

                        try {
                            if (lstData_tmp1 != null && !lstData_tmp1.isEmpty()) {
                                LockSendModel data = lstData_tmp1.get(0);
                                setChotCic(data.getStatus());
                                supdateId = data.getUpdateId();
                                supdateDate = data.getUpdateDate();
                            } else {
                                setChotCic("0");
                            }
                        } catch (Exception e) {
                            setChotCic("0");
                        }
                        row.setD8(getChotCic());
                        row.setD14(supdateId);
                        row.setD15(supdateDate);

                        lstDulieuNt.add(row);
                    } catch (Exception e) {
                        System.err.println("Error processing posCode " + item.getPosCode() + ": " + e.getMessage());
                    }
                }
            }
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> giam lai dp cap 2: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> giam lai dp cap 2: " + e.getMessage());
        }

        return "success_2";
    }

    public String load_c3() {
        try {
            if (!getParaSession()) {
                return ERROR;
            }
            DaoBranchMain daoMain = new DaoBranchMain();
            Connection conn = new DaoConnect().getConnect();
            HashMap hmParameter = getParameter();
            String dateStr = hmParameter.get("ngay_bc").toString();
            ActionContext.getContext().getSession().put("sUserName", UserName);
            lstDulieuNt = daoMain.get_data_giamlai(conn, "GIAMLAI_2025", "000100", dateStr, "S", "GIAMLAI_SHOW");
            if (lstDulieuNt == null || lstDulieuNt.isEmpty()) {
                addActionError("Chưa có dữ liệu!");
                return ERROR;
            }
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> giam lai dp cap 3: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> giam lai dp cap 3: " + e.getMessage());
        }
        return "success_3";
    }

    @Override
    public String save() {
        System.out.println("vao váe 1");
        try {
            if (!getParaSession()) {
                return ERROR;
            }
            HashMap hmParameter = getParameter();
            String dateStr = hmParameter.get("ngay_bc").toString();
            String[] values1 = dateStr.split("\\-");
            String snambc = values1[2];
//            String smato = hmParameter.get("mato").toString();
//            String[] values = smato.split("\\_");
//            String smahoi = values[0];
//            String smaxa = values[1];
//            String ssmato = values[2];
            posMainModel = listKTNBDA.get_pos_main_pos(UserName, Grade);
            pos_cd_username = posMainModel.getPosCd();
            main_pos_username = posMainModel.getMainPosCd();
            final String _reportDate = new SimpleDateFormat("yyyyMMdd").format(new SimpleDateFormat("dd-MMM-yyyy").parse(dateStr));
            final String _reportDate1 = new SimpleDateFormat("yyyy-MM-dd'T'00:00:00.000").format(new SimpleDateFormat("yyyyMMdd").parse(_reportDate));
            DaoBranchMain daoMain = new DaoBranchMain();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");
            String reportDate2 = LocalDateTime.now().format(formatter);

            ArrayList<DuLieuNTRowX> lstUpdateDate = new ArrayList<>();
            ArrayList<DULIEU_NT_TQ> lstLocalDataUpdate = new ArrayList<>();
            for (QT_DULIEU_NT tmp : lstDulieuNt) {
                if ("1".equals(tmp.getD30())) {

                    DuLieuNTRowX tempadd = new DuLieuNTRowX();
                    tempadd.setKey("GIAMLAI_2025");
                    tempadd.setOrderValue(tmp.getTHUTU());
                    tempadd.setOrderDescription(tmp.getTT_HIENTHI());
                    tempadd.setName(tmp.getTEN());
                    tempadd.setCode(tmp.getMA());
                    tempadd.setMakerId(UserName);
                    tempadd.setMakerDate(reportDate2);
                    tempadd.setAuthoriseId(UserName);
                    tempadd.setAuthoriseDate(reportDate2);
                    tempadd.setReportDate(_reportDate1);
                    tempadd.setReportYear(Integer.parseInt(snambc));
                    tempadd.setPosCode(tmp.getMAPGD());
                    tempadd.setPosFlag("S");
                    tempadd.setBranchCode(tmp.getMACN());
                    tempadd.setD18(tmp.getD18() == null ? "0" : tmp.getD18());
                    tempadd.setD19(tmp.getD19() == null ? "0" : tmp.getD19());

                    lstUpdateDate.add(tempadd);

                    DULIEU_NT_TQ temp1 = new DULIEU_NT_TQ();
                    temp1.setD1(tmp.getD1());
                    temp1.setD3(tmp.getD3());
                    temp1.setD4(tmp.getD4());
                    temp1.setD6(tmp.getD6());
                    temp1.setD18(tmp.getD18());
                    temp1.setD19(tmp.getD19());
                    lstLocalDataUpdate.add(temp1);
                }
            }

            _serverAPI = new DuLieuNTService();
            int status = _serverAPI.getGQVL2023("GIAMLAI_2025", pos_cd_username, "S", _reportDate, "", "", lstUpdateDate);

            if (status == 200 && !daoMain.save_giamlai_2025("GIAMLAI_2025", dateStr, pos_cd_username, "", "", UserName, lstLocalDataUpdate)) {
                String code = String.valueOf(status);
                this.pageResult = new ByteArrayInputStream(code.getBytes(StandardCharsets.UTF_8));
                return SUCCESS;
            }
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> save giam lai 1: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> save giam lai 1: " + e.getMessage());
        }
        String code = String.valueOf(200);
        this.pageResult = new ByteArrayInputStream(code.getBytes(StandardCharsets.UTF_8));
        return SUCCESS;
    }

    public String search() {
        try {
            DaoBranchMain daoMain = new DaoBranchMain();
            Connection conn = new DaoConnect().getConnect();
            HashMap hmParameter = getParameter();
            posMainModel = listKTNBDA.get_pos_main_pos(UserName, Grade);
            pos_cd_username = posMainModel.getPosCd();
            main_pos_username = posMainModel.getMainPosCd();
            String dateStr = hmParameter.get("ngay_bc").toString();
            final String _reportDate = new SimpleDateFormat("yyyyMMdd").format(new SimpleDateFormat("dd-MMM-yyyy").parse(dateStr));
            ActionContext.getContext().getSession().put("smapgd", pos_cd_username);
            ActionContext.getContext().getSession().put("sUserName", UserName);
            ArrayList<LockSendModel> lstData_tmp = _serverAPI.getDataLockManual("GIAMLAI_2025", main_pos_username, "M", _reportDate);
            try {
                setChotsl(lstData_tmp.get(0).getStatus());
            } catch (Exception e) {
                setChotsl("0");
            }
            ArrayList<LockSendModel> lstData_tmp1 = _serverAPI.getDataLockManual("GIAMLAI_2025", pos_cd_username, "S", _reportDate);
            try {
                setChotCic(lstData_tmp1.get(0).getStatus());
            } catch (Exception e) {
                setChotCic("0");
            }
//            System.err.println("pos_cd_username= " + pos_cd_username);
//            lstDulieuNt = daoMain.get_data_giamlai(conn, "GIAMLAI_2025", pos_cd_username, dateStr, "S", "GIAMLAI_SEARCH");
            lstXa_API = _serverAPI.getListXa("", "", "", pos_cd_username);
            for (ListCommune item : lstXa_API) {
                QT_DULIEU_NT row = new QT_DULIEU_NT();
                try {
                    row.setD1(item.getCommuneCode());
                    row.setD2(item.getCommuneName());
                    lstData_Api = _serverAPI.getData_condition("GIAMLAI_2025", pos_cd_username, "S", _reportDate, "D6=" + item.getCommuneCode() + "|");

                    long tongThan = 0, tongQhan = 0, tongKhoanh = 0, tongMonvay = 0, lainhap = 0, laixnhan = 0, tongDuno = 0;
                    Set<String> setKhachHang = new HashSet<>();
                    if (lstData_Api != null) {
                        for (DuLieuNTRow apiRow : lstData_Api) {
                            tongThan += toLong(apiRow.getD14());
                            tongQhan += toLong(apiRow.getD15());
                            tongKhoanh += toLong(apiRow.getD16());
                            tongMonvay++;
                            tongDuno = tongThan + tongQhan + tongKhoanh;
                            if ("1".equals(apiRow.getD19())) {
                                laixnhan++;
                            } else {
                                lainhap++;
                            }
                            if (apiRow.getD1() != null && !apiRow.getD1().isEmpty()) {
                                setKhachHang.add(apiRow.getD1());
                            }
                        }
                    }
                    row.setD3(String.valueOf(setKhachHang.size()));
                    row.setD4(String.valueOf(tongMonvay));
                    row.setD5(String.valueOf(tongThan));
                    row.setD6(String.valueOf(tongQhan));
                    row.setD7(String.valueOf(tongKhoanh));
                    row.setD8(String.valueOf(lainhap));
                    row.setD9(String.valueOf(laixnhan));
                    row.setD10(String.valueOf(tongDuno));
                    lstDulieuNt.add(row);
                } catch (Exception e) {
                    System.err.println("Error processing posCode " + item.getPosCode() + ": " + e.getMessage());
                }
            }

            if (conn != null) {
                conn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SUCCESS;
    }

    public String unlock_c1() {
        try {
            if (!getParaSession()) {
                return ERROR;
            }
            HashMap hmParameter = getParameter();
//            lay tu load send xuong
            String mapgd = (String) ActionContext.getContext().getSession().get("smapgd");
            UserName = (String) ActionContext.getContext().getSession().get("sUserName");
            String dateStr = hmParameter.get("ngay_bc").toString();
            final String _reportDate = new SimpleDateFormat("yyyyMMdd").format(new SimpleDateFormat("dd-MMM-yyyy").parse(dateStr));
            int status = _serverAPI.updateChotSL("GIAMLAI_2025", mapgd, "S", _reportDate, "1", UserName, null);
            if (status != 200) {
                this.pageResult = new ByteArrayInputStream(String.valueOf(status).getBytes(StandardCharsets.UTF_8));
                return ERROR;
            }
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> unlock_c1 giamlai: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> unlock_c1 giamlai: " + e.getMessage());
            this.pageResult = new ByteArrayInputStream("500".getBytes(StandardCharsets.UTF_8));
            return ERROR;
        }
        String code = String.valueOf(200);
        this.pageResult = new ByteArrayInputStream(code.getBytes(StandardCharsets.UTF_8));
        return SUCCESS;
    }

    public String send_cn() {
        try {
            if (!getParaSession()) {
                return ERROR;
            }
            String dateStr = ServletActionContext.getRequest().getParameter("sngaybc");
            String[] values = dateStr.split("\\/");
            String sngay = values[0];
            String sthang = values[1];
            String snam = values[2];
            String _reportDate = snam + sthang + sngay;
            String mapgd = ServletActionContext.getRequest().getParameter("smapgd");
            String stype = ServletActionContext.getRequest().getParameter("sstype");
            int status = _serverAPI.updateChotSL("GIAMLAI_2025", mapgd, "S", _reportDate, stype, UserName, null);
            if (status != 200) {
                this.pageResult = new ByteArrayInputStream(String.valueOf(status).getBytes(StandardCharsets.UTF_8));
                return ERROR;
            }
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> sendCn: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> sendCn: " + e.getMessage());
            this.pageResult = new ByteArrayInputStream("500".getBytes(StandardCharsets.UTF_8));
            return ERROR;
        }
        String code = String.valueOf(200);
        this.pageResult = new ByteArrayInputStream(code.getBytes(StandardCharsets.UTF_8));
        return SUCCESS;
    }

    public String unlock_tw() {
        try {
            String smapgd = ServletActionContext.getRequest().getParameter("mapgd");
            String sngaybc = ServletActionContext.getRequest().getParameter("ngaybc");
            String stype = ServletActionContext.getRequest().getParameter("type");
            String UserName = (String) ActionContext.getContext().getSession().get("sUserName");
            DaoBranchMain daoMain = new DaoBranchMain();
            GenericResult<String> _result = daoMain.unlock_giamlai_tw("GIAMLAI_2025", smapgd, UserName, sngaybc, stype);

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

    public String popupTablePos() throws Exception {
        try {
            String smapgd = ServletActionContext.getRequest().getParameter("mapgd");
            String sngaybc = ServletActionContext.getRequest().getParameter("ngaybc");
//            System.out.println("sngaybc= " + sngaybc);
            String type = ServletActionContext.getRequest().getParameter("type");
            DaoBranchMain daoMain = new DaoBranchMain();
            Connection conn = new DaoConnect().getConnect();
            lstDulieuNt = daoMain.get_data_giamlai(conn, "GIAMLAI_2025", smapgd, sngaybc, "S", "GIAMLAI_PGD");
            if (lstDulieuNt == null || lstDulieuNt.isEmpty()) {
                addActionError("Chưa có dữ liệu!");
                return ERROR;
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham saveDataaa " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " saveDataaa -> " + e.getMessage());
        }
        return "success";

    }

    private static String layTen(String hoTen) {
        if (hoTen == null || hoTen.trim().isEmpty()) {
            return "";
        }
        String[] parts = hoTen.trim().split("\\s+");
        return parts[parts.length - 1]; // lấy tên cuối
    }

    private int toInt(String value) {
        if (value == null || value.trim().isEmpty()) {
            return 0;
        }
        try {
            return Integer.parseInt(value.trim());
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    private long toLong(String value) {
        if (value == null || value.trim().isEmpty()) {
            return 0L;
        }
        try {
            return Long.parseLong(value.trim());
        } catch (NumberFormatException e) {
            return 0L; // hoặc log lỗi nếu cần
        }
    }

    private QT_DULIEU_NT convertToQT_DULIEU_NT(DuLieuNTRow item) {
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
            row.setD13(item.getD13());
            row.setD14(item.getD14());
            row.setD15(item.getD15());
            row.setD16(item.getD16());
            row.setD17(item.getD17());
            row.setD18(item.getD18());
            row.setD19(item.getD19());
            row.setD20(item.getD20());
            row.setD21(item.getD21());
            row.setD22(item.getD22());
            row.setD23(item.getD23());
            row.setD24(item.getD24());
            row.setD25(item.getD25());
            row.setD26(item.getD26());
            row.setD27(item.getD27());
            row.setD28(item.getD28());
            row.setD29(item.getD29());
            row.setD30(item.getD30());
            row.setD31(item.getD31());
            row.setD32(item.getD32());
            row.setD33(item.getD33());
            row.setD34(item.getD34());
            row.setD35(item.getD35());

        } catch (Exception e) {
        }
        return row;
    }

}
