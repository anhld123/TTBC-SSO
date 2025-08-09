/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.menu_dcpln.menu_sp;

import static com.opensymphony.xwork2.Action.ERROR;
import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionContext;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import vbsp.ims.nhaptaycn.action.*;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.apache.struts2.ServletActionContext;
import vbsp.ims.bcqt.model.QT_DULIEU_NT;
import vbsp.ims.chtrinh_cn.ActionChtrinhcnMain;
import vbsp.ims.log.CoreLogger;
import vbsp.ims.nghiquyet11cp.DaoNghiquyet11cp;
import vbsp.ims.report.fast.ListValue;
import vbsp.ims.restapi.DuLieuNTRow;
import vbsp.ims.restapi.DuLieuNTService;
import vbsp.ims.restapi.DuLieuPLN;
import vbsp.ims.restapi.DuLieuPLNResp_T;
import vbsp.ims.restapi.DuLieuPLN_Save;
import vbsp.ims.restapi.DuLieuPLN_T;
import vbsp.ims.restapi.ListCommune;
import vbsp.ims.restapi.ListMainPos;
import vbsp.ims.restapi.ListOfValue;
import vbsp.ims.restapi.ListPosCode;
import vbsp.ims.restapi.LockSendModel;

/**
 *
 * @author ducanh
 */
public class DcplnAction extends ActionChtrinhcnMain
        implements NhaptaycnFunction {
//<editor-fold defaultstate="collapsed" desc="khai biến">

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
    protected String tong_kh;
    protected String tong_monvay;
    protected int tong_duno;
    protected int tong_than;
    protected int tong_qhan;
    protected int tong_khoanh;
    protected int tong_nlai;
    protected String mato_to;
    private List<ListMainPos> lstCN_API;
    protected List<DuLieuPLN_T> lstDulieuNtPLN_T = new ArrayList<>();
    private List<DuLieuPLN_T> lstPhanLoaiNo_T;
    protected List<DuLieuPLNResp_T> lstDulieuNtPLN_TT = new ArrayList<>();
    private List<ListOfValue> lstDmKhac57;
    private List<ListOfValue> lstDmKhac106;
    private int total;
    private int per_page;
    private int current_page;
    private int last_page;
    private int from;
    private int to;
    protected List<ListValue> lstMato_T = new ArrayList<ListValue>();
    private List<DuLieuPLN> lstPhanLoaiNo;
    protected List<DuLieuPLN> lstDulieuNtPLN = new ArrayList<>();
    private String thongbao;

    public String getThongbao() {
        return thongbao;
    }

    public void setThongbao(String thongbao) {
        this.thongbao = thongbao;
    }

    public List<DuLieuPLN> getLstPhanLoaiNo() {
        return lstPhanLoaiNo;
    }

    public void setLstPhanLoaiNo(List<DuLieuPLN> lstPhanLoaiNo) {
        this.lstPhanLoaiNo = lstPhanLoaiNo;
    }

    public List<DuLieuPLN> getLstDulieuNtPLN() {
        return lstDulieuNtPLN;
    }

    public void setLstDulieuNtPLN(List<DuLieuPLN> lstDulieuNtPLN) {
        this.lstDulieuNtPLN = lstDulieuNtPLN;
    }

    //</editor-fold>
//<editor-fold defaultstate="collapsed" desc="khai báo get,set">
    public List<ListValue> getLstMato_T() {
        return lstMato_T;
    }

    public void setLstMato_T(List<ListValue> lstMato_T) {
        this.lstMato_T = lstMato_T;
    }

    public List<ListMainPos> getLstCN_API() {
        return lstCN_API;
    }

    public void setLstCN_API(List<ListMainPos> lstCN_API) {
        this.lstCN_API = lstCN_API;
    }

    public List<DuLieuPLN_T> getLstDulieuNtPLN_T() {
        return lstDulieuNtPLN_T;
    }

    public void setLstDulieuNtPLN_T(List<DuLieuPLN_T> lstDulieuNtPLN_T) {
        this.lstDulieuNtPLN_T = lstDulieuNtPLN_T;
    }

    public List<DuLieuPLN_T> getLstPhanLoaiNo_T() {
        return lstPhanLoaiNo_T;
    }

    public void setLstPhanLoaiNo_T(List<DuLieuPLN_T> lstPhanLoaiNo_T) {
        this.lstPhanLoaiNo_T = lstPhanLoaiNo_T;
    }

    public List<DuLieuPLNResp_T> getLstDulieuNtPLN_TT() {
        return lstDulieuNtPLN_TT;
    }

    public void setLstDulieuNtPLN_TT(List<DuLieuPLNResp_T> lstDulieuNtPLN_TT) {
        this.lstDulieuNtPLN_TT = lstDulieuNtPLN_TT;
    }

    public List<ListOfValue> getLstDmKhac57() {
        return lstDmKhac57;
    }

    public void setLstDmKhac57(List<ListOfValue> lstDmKhac57) {
        this.lstDmKhac57 = lstDmKhac57;
    }

    public List<ListOfValue> getLstDmKhac106() {
        return lstDmKhac106;
    }

    public void setLstDmKhac106(List<ListOfValue> lstDmKhac106) {
        this.lstDmKhac106 = lstDmKhac106;
    }

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

    public int getTong_duno() {
        return tong_duno;
    }

    public void setTong_duno(int tong_duno) {
        this.tong_duno = tong_duno;
    }

    public int getTong_than() {
        return tong_than;
    }

    public void setTong_than(int tong_than) {
        this.tong_than = tong_than;
    }

    public int getTong_qhan() {
        return tong_qhan;
    }

    public void setTong_qhan(int tong_qhan) {
        this.tong_qhan = tong_qhan;
    }

    public int getTong_khoanh() {
        return tong_khoanh;
    }

    public void setTong_khoanh(int tong_khoanh) {
        this.tong_khoanh = tong_khoanh;
    }

    public int getTong_nlai() {
        return tong_nlai;
    }

    public void setTong_nlai(int tong_nlai) {
        this.tong_nlai = tong_nlai;
    }

    public String getMato_to() {
        return mato_to;
    }

    public void setMato_to(String mato_to) {
        this.mato_to = mato_to;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPer_page() {
        return per_page;
    }

    public void setPer_page(int per_page) {
        this.per_page = per_page;
    }

    public int getCurrent_page() {
        return current_page;
    }

    public void setCurrent_page(int current_page) {
        this.current_page = current_page;
    }

    public int getLast_page() {
        return last_page;
    }

    public void setLast_page(int last_page) {
        this.last_page = last_page;
    }

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public int getTo() {
        return to;
    }

    public void setTo(int to) {
        this.to = to;
    }

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
            ActionContext.getContext().getSession().put("UserName", UserName);
            ActionContext.getContext().getSession().put("pos_cd_username", pos_cd_username);
            lstDmKhac106 = _serverAPI.getListOfValue("192", "");
            lstDmKhac57 = _serverAPI.getListOfValue("193", "");
            String dateStr = hmParameter.get("ngay_bc").toString();
            ActionContext.getContext().getSession().put("dateStr", dateStr);
            String mahoi = "";
            String mato = "";

            String soku = (hmParameter.get("soku") == null || hmParameter.get("soku").toString().trim().isEmpty()) ? "" : hmParameter.get("soku").toString();
            if (!soku.trim().equals("")) {
                lstPhanLoaiNo_T = _serverAPI.postDataPLN(pos_cd_username, "S", dateStr, "", "", soku, "", "", "", "");
                if (lstPhanLoaiNo_T == null || lstPhanLoaiNo_T.isEmpty()) {
                    lstPhanLoaiNo_T = _serverAPI.postDataPLN(pos_cd_username, "S", dateStr, "", "", "", "", "", "", soku);
                    if (lstPhanLoaiNo_T == null || lstPhanLoaiNo_T.isEmpty()) {
                        addActionError("Không tìm thấy dữ liệu!");
                        return ERROR;
                    }
                }
                lstCN_API = _serverAPI.getListCn("");
                lstPGD_API = _serverAPI.getListPgd("", "");
            } else {
                String nguonvon = (hmParameter.get("nguonvon") == null || "0".equals(hmParameter.get("nguonvon").toString())) ? "" : hmParameter.get("nguonvon").toString();
                String trangthai = (hmParameter.get("trangthai") == null || "0".equals(hmParameter.get("trangthai").toString())) ? "" : hmParameter.get("trangthai").toString();
                String chtrinh = (hmParameter.get("chtrinh") == null || "0".equals(hmParameter.get("chtrinh").toString())) ? "" : hmParameter.get("chtrinh").toString();
                mahoi = hmParameter.get("mahoi").toString();
                if (mahoi.equals("0")) {
                    addActionError("Bạn chưa chọn Hội ủy thác!");
                    return ERROR;
                }
                mato = hmParameter.get("mato").toString();
                String[] values = mato.split("\\_");
                String smato = values[2];
                if (mato.equals("0000000")) {
                    addActionError("Bạn chưa chọn tổ TK&VV!");
                    return ERROR;
                }

                lstPhanLoaiNo_T = _serverAPI.postDataPLN(pos_cd_username, "S", dateStr, mahoi,
                        smato.equals("NOGROUP") ? "" : smato, "", trangthai, nguonvon, chtrinh, "");

            }
            if (lstPhanLoaiNo_T == null || lstPhanLoaiNo_T.isEmpty()) {
                addActionError("Không có dữ liệu đối chiếu!");
                return ERROR;
            }

            for (DuLieuPLN_T item : lstPhanLoaiNo_T) {
                if (!"1".equals(item.getD7())) {
                    DuLieuPLN_T row = convertPLN_T(item);
                    mato = item.getPlnMato();
                    mahoi = item.getPlnDvut();
                    lstDulieuNtPLN_T.add(row);
                }

            }
            if (lstDulieuNtPLN_T == null || lstDulieuNtPLN_T.isEmpty()) {
                addActionError("Không có dữ liệu đối chiếu!");
                return ERROR;
            }
            lstDulieuNtPLN_T.sort(
                    Comparator.comparing(obj -> layTen(obj.getPlnTenkh()), String.CASE_INSENSITIVE_ORDER)
            );
            List<DuLieuPLN_T> lstData = _serverAPI.postDataPLN(pos_cd_username, "S", dateStr, mahoi, mato, "", "", "", "", "");
            Set<String> setKhachHang = new HashSet<>();
            int tongThan = 0;
            int tongQhan = 0;
            int tongKhoanh = 0;
            int tongDuno = 0;
            int tongNlai = 0;
            int tongMonvay = 0;
            String stmato_to = "";
            for (DuLieuPLN_T item : lstData) {
                int dnoThan = Optional.ofNullable(item.getPlnDnothan()).orElse(0);
                int dnoQhan = Optional.ofNullable(item.getPlnDnoqhan()).orElse(0);
                int dnoKhoanh = Optional.ofNullable(item.getPlnDnokhoanh()).orElse(0);
                int laiTon = Optional.ofNullable(item.getPlnTonglaiton()).orElse(0);

                tongThan += dnoThan;
                tongQhan += dnoQhan;
                tongKhoanh += dnoKhoanh;
                tongDuno += dnoThan + dnoQhan + dnoKhoanh;
                tongNlai += laiTon;
                tongMonvay++;
                stmato_to = item.getPlnMato() + " - " + item.getPlnTentt();
                if (item.getPlnMakh() != null && !item.getPlnMakh().isEmpty()) {
                    setKhachHang.add(item.getPlnMakh());
                }
            }
            this.tong_monvay = String.valueOf(tongMonvay);
            this.tong_kh = String.valueOf(setKhachHang.size());
            this.tong_than = tongThan;
            this.tong_qhan = tongQhan;
            this.tong_khoanh = tongKhoanh;
            this.tong_duno = tongDuno;
            this.tong_nlai = tongNlai;
            this.mato_to = stmato_to.toUpperCase();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " getDataDcNo -> " + e.getMessage());
            addActionError("Lỗi không thể load số liệu chi tiết lỗi " + e.getMessage());
            return ERROR;
        }
        return SUCCESS;
    }

    public String load_c3() {
        try {
            if (!getParaSession()) {
                return ERROR;
            }
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> ktksnb2024: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> ktksnb2024: " + e.getMessage());
        }

        return "success_3";
    }

    private DuLieuPLN convertPLN(DuLieuPLN item) {
        DuLieuPLN row = new DuLieuPLN();

        if (item == null) {
            return row;
        }

        try {
            row.setPlnSoku(item.getPlnSoku());
            row.setPlnMakh(item.getPlnMakh());
            row.setPlnTenkh(item.getPlnTenkh());
            row.setPlnMato(item.getPlnMato());
            row.setPlnTentt(item.getPlnTentt());
            row.setPlnDvut(item.getPlnDvut());
            row.setPlnDvutTen(item.getPlnDvutTen());
            row.setPlnMadp(item.getPlnMadp());
            row.setPlnNguonvon(item.getPlnNguonvon());
            row.setPlnNguonvonTen(item.getPlnNguonvonTen());

            row.setPlnSprdCd(item.getPlnSprdCd());
            row.setPlnSprdCdTen(item.getPlnSprdCdTen());
            row.setPlnChtrinh(item.getPlnChtrinh());
            row.setPlnChtrinhTen(item.getPlnChtrinhTen());
            row.setPlnChtrinhTenvt(item.getPlnChtrinhTenvt());

            int dnothan = safeInt(item.getPlnDnothan());
            int dnoqhan = safeInt(item.getPlnDnoqhan());
            int dnokhoanh = safeInt(item.getPlnDnokhoanh());
            row.setPlnDnothan(dnothan);
            row.setPlnDnoqhan(dnoqhan);
            row.setPlnDnokhoanh(dnokhoanh);
            row.setPlnTongDno(dnothan + dnoqhan + dnokhoanh);

            row.setPlnLaitonthan(safeInt(item.getPlnLaitonthan()));
            row.setPlnLaitonqhan(safeInt(item.getPlnLaitonqhan()));
            row.setPlnTonglaiton(safeInt(item.getPlnTonglaiton()));
            row.setPlnTonglaiTt(safeInt(item.getPlnTonglaiTt()));
            row.setPlnCKntnSodu(safeInt(item.getPlnCKntnSodu()));
            row.setPlnKKntnSodu(safeInt(item.getPlnKKntnSodu()));
            row.setNgnhanKckntn(item.getNgnhanKckntn());

            row.setPlnQuanheKh(item.getPlnQuanheKh());
            row.setPlnTrangthai(item.getPlnTrangthai());
            row.setPlnTrangthaiTen(item.getPlnTrangthaiTen());
            row.setPlnNogocClech(safeInt(item.getPlnNogocClech()));
            row.setPlnNolaiClech(safeInt(item.getPlnNolaiClech()));
            row.setPlnNgnhanClech(item.getPlnNgnhanClech());
            row.setPlnTtMonvay(item.getPlnTtMonvay());
            row.setPlnNgaybc(item.getPlnNgaybc());
            row.setPlnNguoiPln(item.getPlnNguoiPln());
            row.setPlnNgayPln(item.getPlnNgayPln());
            row.setPlnTrangthaino(item.getPlnTrangthaino());
            row.setPlnTrangthainoTen(item.getPlnTrangthainoTen());
            row.setTchatNo(item.getTchatNo());
            row.setTchatNoTen(item.getTchatNoTen());
            row.setPlnLoaito(item.getPlnLoaito());
            row.setPlnLoaitoTen(item.getPlnLoaitoTen());
            row.setPlnNgaycn(item.getPlnNgaycn());
            row.setPlnMacn(item.getPlnMacn());
            row.setPlnMapgd(item.getPlnMapgd());
            row.setPlnMaxa(item.getPlnMaxa());
        } catch (Exception e) {
            // Không log
        }
        return row;
    }

    private DuLieuPLN_T convertPLN_T(DuLieuPLN_T item) {
        DuLieuPLN_T row = new DuLieuPLN_T();

        if (item == null) {
            return row;
        }

        try {
            row.setPlnMacn(item.getPlnMacn());
            row.setPlnMapgd(item.getPlnMapgd());
            row.setPlnMaxa(item.getPlnMaxa());
            row.setPlnNguonvon(item.getPlnNguonvon());
            row.setPlnNguonvonTen(item.getPlnNguonvonTen());
            row.setPlnDvut(item.getPlnDvut());
            row.setPlnDvutTen(item.getPlnDvutTen());
            row.setPlnChtrinh(item.getPlnChtrinh());
            row.setPlnChtrinhTen(item.getPlnChtrinhTen());
            row.setPlnChtrinhTenvt(item.getPlnChtrinhTenvt());
            row.setPlnMato(item.getPlnMato());
            row.setPlnTentt(item.getPlnTentt());
            row.setPlnMakh(item.getPlnMakh());
            row.setPlnTenkh(item.getPlnTenkh());
            row.setPlnSoku(item.getPlnSoku());

            int dnothan = safeInt(item.getPlnDnothan());
            int dnoqhan = safeInt(item.getPlnDnoqhan());
            int dnokhoanh = safeInt(item.getPlnDnokhoanh());
            row.setPlnDnothan(dnothan);
            row.setPlnDnoqhan(dnoqhan);
            row.setPlnDnokhoanh(dnokhoanh);
            row.setPlnTongDno(dnothan + dnoqhan + dnokhoanh);

            row.setPlnTonglaiton(safeInt(item.getPlnTonglaiton()));
            row.setPlnCKntnSodu(safeInt(item.getPlnCKntnSodu()));

            row.setNgnhanKckntn(item.getNgnhanKckntn());
            row.setkNgnhanKh(item.getkNgnhanKh());
            row.setPlnQuanheKh(item.getPlnQuanheKh());
            row.setPlnTrangthai(item.getPlnTrangthai());
            row.setPlnNogocClech(safeInt(item.getPlnNogocClech()));
            row.setPlnNolaiClech(safeInt(item.getPlnNolaiClech()));
            row.setPlnNgnhanClech(item.getPlnNgnhanClech());
            row.setPlnTtMonvay(item.getPlnTtMonvay());
            row.setPlnNgaybc(item.getPlnNgaybc());
            row.setPlnNguoiPln(item.getPlnNguoiPln());
            row.setPlnNgayPln(item.getPlnNgayPln());
            row.setPlnNguyennhanC2(item.getPlnNguyennhanC2());

            String kntnSodu01 = item.getkKntnSodu01();
            row.setPlnKKntnSodu(parseIntSafe(kntnSodu01));

            row.setkKntnSodu02(item.getkKntnSodu02());
            row.setkKntnSodu03(item.getkKntnSodu03());
            row.setkKntnSodu04(item.getkKntnSodu04());
            row.setkKntnSodu05(item.getkKntnSodu05());
            row.setkKntnSodu06(item.getkKntnSodu06());
            row.setkKntnSodu07(item.getkKntnSodu07());
            row.setkKntnSodu08(item.getkKntnSodu08());
            row.setkKntnSodu09(item.getkKntnSodu09());
            row.setkKntnSodu10(item.getkKntnSodu10());
            row.setkKntnSodu11(item.getkKntnSodu11());

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

            row.setCheckrow("0");
        } catch (Exception e) {
            // Không log, chỉ bỏ qua lỗi
        }
        return row;
    }

    private int safeInt(Integer val) {
        return val != null ? val : 0;
    }

    private int parseIntSafe(String val) {
        if (val == null || val.isEmpty()) {
            return 0;
        }
        try {
            return Integer.parseInt(val);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public String getDb_mato() {
        try {
            System.err.println("Vào hàm getDb_mato");

            HashMap<String, Object> hmParameter = getParameter();
            if (hmParameter == null) {
                return ERROR;
            }

            String UserName = (String) ActionContext.getContext().getSession().get("UserName");
            if (UserName == null || UserName.isEmpty()) {
                return ERROR;
            }

            Object maxaObj = hmParameter.get("maxa");
            Object mahoiObj = hmParameter.get("mahoi");

            if (maxaObj == null || mahoiObj == null) {
                return ERROR;
            }

            String maxa_key = maxaObj.toString();
            String[] values = maxa_key.split("\\|");
            if (values.length < 2) {
                return ERROR;
            }

            String mapgd = values[0];
            String maxa = values[1];
            String mahoi = mahoiObj.toString();

            if (mapgd.isEmpty() || maxa.isEmpty() || mahoi.isEmpty()) {
                return ERROR;
            }

            String danhMucKey = "2_" + mapgd + "_" + maxa + "_" + mahoi;
            DaoNghiquyet11cp daoMain11 = new DaoNghiquyet11cp();
            setLstMato_T(daoMain11.getDanhMuc(UserName, "MATO_PLN", danhMucKey));

        } catch (Exception e) {
            System.err.println("Lỗi trong getDb_mato: " + e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " getDb_mato -> " + e.getMessage(), e);
            return ERROR;
        }
        return SUCCESS;
    }

    private static String layTen(String hoTen) {
        if (hoTen == null || hoTen.trim().isEmpty()) {
            return "";
        }
        String[] parts = hoTen.trim().split("\\s+");
        return parts[parts.length - 1]; // lấy tên cuối
    }

    public String save() {
        System.out.println("vao váe");
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
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");
            String date2 = LocalDateTime.now().format(formatter);

            ArrayList<DuLieuPLN_Save> lstUpdateDate = new ArrayList<>();
            for (DuLieuPLN_T tmp : lstDulieuNtPLN_T) {
                DuLieuPLN_Save tempadd = new DuLieuPLN_Save();
                if ("1".equals(tmp.getCheckrow())) {
                    tempadd.setLoanId(tmp.getPlnSoku());
                    tempadd.setCustomerId(tmp.getPlnMakh());
//                    -- 4 trường màn hình cha
                    tempadd.setReason_Deviant(tmp.getPlnNgnhanClech());
                    tempadd.setUpdateTime(date2);
                    tempadd.setAble_ToPay_Amt(tmp.getPlnCKntnSodu());
                    tempadd.setReason_Deviant02(tmp.getPlnNguyennhanC2() == null ? "0" : tmp.getPlnNguyennhanC2());
                    tempadd.setD1(tmp.getD1());
                    tempadd.setD2(tmp.getD2());
                    tempadd.setD3(tmp.getD3());
                    tempadd.setD4(tmp.getD4());
                    tempadd.setD5(tmp.getD5());
                    tempadd.setD7(tmp.getD7());
                    tempadd.setStatus("S");
                    lstUpdateDate.add(tempadd);
                }

            }
            int status = _serverAPI.savePLN_2025(pos_cd_username, _reportDate, UserName, lstUpdateDate);
            if (status != 200) {
                this.pageResult = new ByteArrayInputStream(String.valueOf(status).getBytes(StandardCharsets.UTF_8));
                return ERROR;
            }
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> send 123: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> send 123: " + e.getMessage());
            this.pageResult = new ByteArrayInputStream("500".getBytes(StandardCharsets.UTF_8));
            return ERROR;
        }
        String code = String.valueOf(200);
        this.pageResult = new ByteArrayInputStream(code.getBytes(StandardCharsets.UTF_8));
        return SUCCESS;
    }

    public String saveDataDcPLN_Loan() {
        System.out.println("vao váe");
        try {
            if (!getParaSession()) {
                return ERROR;
            }
            String _reportDate = (String) ActionContext.getContext().getSession().get("_reportDate");
            String soku = (String) ActionContext.getContext().getSession().get("soku");
            String mapgd = (String) ActionContext.getContext().getSession().get("mapgd");
            if (mapgd != null) {
                mapgd = mapgd.replace("[", "").replace("]", "");
            }
            System.out.println(" mapgd= " + mapgd);
            String UserName = (String) ActionContext.getContext().getSession().get("UserName");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");
            String date2 = LocalDateTime.now().format(formatter);

            ArrayList<DuLieuPLN_Save> lstUpdateDate = new ArrayList<>();
            for (DuLieuPLN_T tmp : lstDulieuNtPLN_T) {
                DuLieuPLN_Save tempadd = new DuLieuPLN_Save();
                tempadd.setLoanId(soku);
                tempadd.setCustomerId(tmp.getPlnMakh());
//              lưu  màn hình con
                tempadd.setDeviant_Amt(tmp.getPlnNogocClech());
                tempadd.setDeviant_Int(tmp.getPlnNolaiClech());
                tempadd.setStatus(tmp.getPlnTrangthai());
                tempadd.setReason_Deviant(tmp.getPlnNgnhanClech());
                tempadd.setCustRelationship(tmp.getPlnQuanheKh());
                tempadd.setUpdateTime(date2);

                lstUpdateDate.add(tempadd);
            }

            _serverAPI = new DuLieuNTService();
            int status = _serverAPI.savePLN_2025(mapgd, _reportDate, UserName, lstUpdateDate);
            if (status != 200) {
                this.pageResult = new ByteArrayInputStream(String.valueOf(status).getBytes(StandardCharsets.UTF_8));
                return ERROR;
            }
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> send 23: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> send 23: " + e.getMessage());
            this.pageResult = new ByteArrayInputStream("500".getBytes(StandardCharsets.UTF_8));
            return ERROR;
        }
        String code = String.valueOf(200);

        this.pageResult = new ByteArrayInputStream(code.getBytes(StandardCharsets.UTF_8));
        return SUCCESS;
    }

    private long parseSafeLong(String s) {
        if (s == null || s.trim().isEmpty()) {
            return Long.MAX_VALUE;
        }
        try {
            return Long.parseLong(s.trim());
        } catch (NumberFormatException e) {
            System.err.println("Cannot parse long from: [" + s + "]");
            return Long.MAX_VALUE;
        }
    }

    public String sendSupportDcPln() {
        try {
            if (!getParaSession()) {
                return ERROR;
            }
            String makh = ServletActionContext.getRequest().getParameter("makh");
            String macn_sp = ServletActionContext.getRequest().getParameter("macn_sp");
            String mapgd_sp = ServletActionContext.getRequest().getParameter("mapgd_sp");
            String mapgd = ServletActionContext.getRequest().getParameter("mapgd");
            String maxa_sp = ServletActionContext.getRequest().getParameter("maxa_sp");
            String dateStr = (String) ActionContext.getContext().getSession().get("dateStr");
            final String _reportDate = new SimpleDateFormat("yyyyMMdd").format(new SimpleDateFormat("dd-MMM-yyyy").parse(dateStr));
            lstPhanLoaiNo_T = _serverAPI.postDataPLN(mapgd, "S", dateStr, "", "", makh, "", "", "", "");
            ArrayList<DuLieuPLN_Save> lstUpdateDate = new ArrayList<>();
            for (DuLieuPLN_T tmp : lstDulieuNtPLN_T) {
                DuLieuPLN_Save tempadd = new DuLieuPLN_Save();
                tempadd.setLoanId(tmp.getPlnSoku());
                tempadd.setCustomerId(tmp.getPlnMakh());
                tempadd.setD1(macn_sp);
                tempadd.setD2(mapgd_sp);
                tempadd.setD4("0");
                tempadd.setD7("1");
                tempadd.setD9(maxa_sp);
                lstUpdateDate.add(tempadd);
            }
            _serverAPI = new DuLieuNTService();
            int status = _serverAPI.savePLN_2025(mapgd, _reportDate, UserName, lstUpdateDate);
            if (status != 200) {
                this.pageResult = new ByteArrayInputStream(String.valueOf(status).getBytes(StandardCharsets.UTF_8));
                return ERROR;
            }
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> send 23: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> send 23: " + e.getMessage());
            this.pageResult = new ByteArrayInputStream("500".getBytes(StandardCharsets.UTF_8));
            return ERROR;
        }
        String code = String.valueOf(200);
        this.pageResult = new ByteArrayInputStream(code.getBytes(StandardCharsets.UTF_8));
        return SUCCESS;
    }

    public String load_sp() {
        try {
            if (!getParaSession()) {
                return ERROR;
            }
            HashMap<String, Object> hmParameter = getParameter();
            posMainModel = listKTNBDA.get_pos_main_pos(UserName, Grade);
            pos_cd_username = posMainModel.getPosCd();
            main_pos_username = posMainModel.getMainPosCd();
            ActionContext.getContext().getSession().put("UserName", UserName);
            ActionContext.getContext().getSession().put("pos_cd_username", pos_cd_username);
            String dateStr = hmParameter.get("ngay_bc").toString();
            ActionContext.getContext().getSession().put("dateStr", dateStr);
            lstDmKhac57 = _serverAPI.getListOfValue("192", "");
            String soku = (hmParameter.get("soku") == null || hmParameter.get("soku").toString().trim().isEmpty()) ? "" : hmParameter.get("soku").toString();
            if (!soku.trim().equals("")) {
                lstPhanLoaiNo_T = _serverAPI.postDataPLNPos(pos_cd_username, "S", dateStr, "", "", soku, "", "", "", "");
                if (lstPhanLoaiNo_T == null || lstPhanLoaiNo_T.isEmpty()) {
                    lstPhanLoaiNo_T = _serverAPI.postDataPLNPos(pos_cd_username, "S", dateStr, "", "", "", "", "", "", soku);
                    if (lstPhanLoaiNo_T == null || lstPhanLoaiNo_T.isEmpty()) {
                        addActionError("Không tìm thấy dữ liệu!");
                        return ERROR;
                    }
                }
            }
            if (lstPhanLoaiNo_T == null || lstPhanLoaiNo_T.isEmpty()) {
                addActionError("Không có dữ liệu đối chiếu!");
                return ERROR;
            }
            lstCN_API = _serverAPI.getListCn("");
            lstPGD_API = _serverAPI.getListPgd("", "");
            String smapgd = "";
            for (DuLieuPLN_T item : lstPhanLoaiNo_T) {
                DuLieuPLN_T row = convertPLN_T(item);
                if ("1".equals(item.getD7())) {
                    smapgd = item.getPlnMapgd();
                    lstDulieuNtPLN_T.add(row);
                }
                this.check_cn = smapgd;
                ActionContext.getContext().getSession().put("check_cn", check_cn);
                System.out.println("check_cn= " + check_cn);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " getDataDcNo -> " + e.getMessage());
            addActionError("Lỗi không thể load số liệu chi tiết lỗi " + e.getMessage());
            return ERROR;
        }
        return SUCCESS;
    }

    public String saveDataSp_Co() {
        System.out.println("vao váe");
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
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");
            String date2 = LocalDateTime.now().format(formatter);
            String smapgd = (String) ActionContext.getContext().getSession().get("check_cn");
            System.out.println("smapgd= " + smapgd);
            ArrayList<DuLieuPLN_Save> lstUpdateDate = new ArrayList<>();
            for (DuLieuPLN_T tmp : lstDulieuNtPLN_T) {
                DuLieuPLN_Save tempadd = new DuLieuPLN_Save();
                if ("1".equals(tmp.getCheckrow())) {
                    tempadd.setLoanId(tmp.getPlnSoku());
                    tempadd.setCustomerId(tmp.getPlnMakh());
//                    -- 4 trường màn hình cha
                    tempadd.setReason_Deviant(tmp.getPlnNgnhanClech());
                    tempadd.setUpdateTime(date2);
                    tempadd.setAble_ToPay_Amt(tmp.getPlnCKntnSodu());
                    tempadd.setReason_Deviant02(tmp.getPlnNguyennhanC2() == null ? "0" : tmp.getPlnNguyennhanC2());
                    tempadd.setD1(tmp.getD1());
                    tempadd.setD2(tmp.getD2());
                    tempadd.setD3(tmp.getD3());
                    tempadd.setD10(tmp.getD10());
                    tempadd.setD5(tmp.getD5());
                    tempadd.setD4("0");
                    tempadd.setD6("1");
                    tempadd.setD7("2");
                    tempadd.setStatus("S");
                    lstUpdateDate.add(tempadd);
                }

            }
            int status = _serverAPI.savePLN_2025(smapgd, _reportDate, UserName, lstUpdateDate);
            if (status != 200) {
                this.pageResult = new ByteArrayInputStream(String.valueOf(status).getBytes(StandardCharsets.UTF_8));
                return ERROR;
            }
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> send 123: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> send 123: " + e.getMessage());
            this.pageResult = new ByteArrayInputStream("500".getBytes(StandardCharsets.UTF_8));
            return ERROR;
        }
        String code = String.valueOf(200);
        this.pageResult = new ByteArrayInputStream(code.getBytes(StandardCharsets.UTF_8));
        return SUCCESS;
    }

    public String getDetialLoanDcPLN() {
        String lock = "";
        try {
            if (!getParaSession()) {
                return ERROR;
            }

            HashMap hmParameter = getParameter();
            String dateStr = hmParameter.get("ngay_bc").toString();
            String soku = hmParameter.get("soku").toString();
            String mapgd = hmParameter.get("poscd").toString();
            String slock = hmParameter.get("lock").toString();
            String[] values = slock.split("\\-");
            String sD6 = values[0];
            String sD7 = values[1];
            if ((!sD7.equals("0") && !sD7.equals("1")) || sD6.equals("1")) {
                lock = "1";
            }

            final String _reportDate = new SimpleDateFormat("yyyyMMdd")
                    .format(new SimpleDateFormat("dd/MM/yyyy").parse(dateStr));

            // Lưu các tham số vào session
            ActionContext.getContext().getSession().put("_reportDate", _reportDate);
            ActionContext.getContext().getSession().put("soku", soku);
            ActionContext.getContext().getSession().put("mapgd", mapgd);

            // Gọi dữ liệu phân loại nợ
            lstPhanLoaiNo = _serverAPI.getDataPLN(mapgd, soku, _reportDate);
            if (lstPhanLoaiNo == null || lstPhanLoaiNo.isEmpty()) {
                addActionError("Chưa có dữ liệu, liên hệ TTCNTT để được hỗ trợ");
                return ERROR;
            }

            for (DuLieuPLN item : lstPhanLoaiNo) {
                DuLieuPLN row = convertPLN(item);
                lstDulieuNtPLN.add(row);
            }

        } catch (Exception e) {
            System.err.println(e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " getDetialLoanDcPLN -> " + e.getMessage());
            return ERROR;
        }
        return !lock.equals("1") ? "success" : "success_1";
    }

    public String load_c2() {
        try {
            if (!getParaSession()) {
                return ERROR;
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " getDataDcNo -> " + e.getMessage());
            addActionError("Lỗi không thể load số liệu chi tiết lỗi " + e.getMessage());
            return ERROR;
        }
        return SUCCESS;
    }

    public String reloadMaxaPln() {
        try {
            System.err.println("Vào hàm reloadMaxaPln");

            HashMap<String, Object> hmParameter = getParameter();
            if (hmParameter == null) {
                return ERROR;
            }

            String userName = (String) ActionContext.getContext().getSession().get("UserName");
            if (userName == null || userName.isEmpty()) {
                return ERROR;
            }

            // Lấy trực tiếp mapgd từ request
            Object mapgdObj = hmParameter.get("mapgd");
            if (mapgdObj == null) {
                return ERROR;
            }

            String mapgd = mapgdObj.toString().trim();
            if (mapgd.isEmpty()) {
                return ERROR;
            }

            // Nếu vẫn cần danh mục key, chỉ dùng mapgd
            String danhMucKey = "2_" + mapgd;

            DaoNghiquyet11cp daoMain11 = new DaoNghiquyet11cp();
            lstXa_API = _serverAPI.getListXa("", "", "", mapgd);

        } catch (Exception e) {
            System.err.println("Lỗi trong reloadMaxaPln: " + e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " reloadMaxaPln -> " + e.getMessage(), e);
            return ERROR;
        }
        return SUCCESS;
    }

}
